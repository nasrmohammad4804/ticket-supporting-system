package com.nasr.supportingsystemproject.config.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nasr.supportingsystemproject.exception.UserNotValidException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;



@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter {



    private final AuthenticationManager authenticationManager;
    private final String secretkey;

    public CustomAuthenticationFilter(@Value("${secret-key}") String secretkey, AuthenticationManager authenticationManager) {
        this.secretkey = secretkey;
        this.authenticationManager = authenticationManager;
    }
//after authenticate we send access-token with refresh token with username and panel
    //for in router of front show suitable panel and get extra information with username

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String email = request.getParameter("username");
        String password = request.getParameter("password");

        var usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(email, password);

        try {

            Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            if (authenticate != null && authenticate.isAuthenticated()) {
                final ObjectMapper mapper = new ObjectMapper();
                UserDetails userDetails = (UserDetails) authenticate.getPrincipal();
                Algorithm algorithm = Algorithm.HMAC256(secretkey);
                String token = getAccessToken(request, userDetails, algorithm);
                String refresh = getRefreshToken(request, userDetails, algorithm);

                Map<String, String> info = new LinkedHashMap<>();
                info.put("accessToken", token);
                info.put("refreshToken", refresh);
                info.put("email",userDetails.getUsername());
                info.put("role",userDetails.getAuthorities().stream().findFirst().get().getAuthority());
                mapper.writeValue(response.getOutputStream(), info);
            }

        }catch( UsernameNotFoundException | BadCredentialsException | UserNotValidException e){
            SecurityContextHolder.clearContext();
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
        }
    }


        private String getAccessToken (HttpServletRequest request, UserDetails userDetails, Algorithm algorithm){
            return JWT.create()
                    .withSubject(userDetails.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + (5 * 60 * 1000)))
                    .withIssuer(request.getRequestURI())
                    .withClaim("roles", userDetails.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                    .sign(algorithm);
        }

        private String getRefreshToken (HttpServletRequest request, UserDetails userDetails, Algorithm algorithm){
            return JWT.create()
                    .withSubject(userDetails.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + (10 * 60 * 1000)))
                    .withIssuer(request.getRequestURI())
                    .withClaim("roles", userDetails.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                    .sign(algorithm);
        }

        @Override
        protected boolean shouldNotFilter (HttpServletRequest request) throws ServletException {
            return !(request.getServletPath().equals("/api/login") && request.getMethod().equals("POST"));
        }

    }
