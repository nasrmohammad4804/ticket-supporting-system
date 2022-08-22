package com.nasr.supportingsystemproject.config.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.nasr.supportingsystemproject.constant.ConstantField.TOKEN_PREFIX;
import static com.nasr.supportingsystemproject.util.TokenUtility.getToken;

@Component
public class CustomAuthorizationFilter extends OncePerRequestFilter {

    @Value("${secret-key}")
    private String secretkey;

    private static final String ROLE_PREFIX = "ROLE_";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith(TOKEN_PREFIX)) {
            String token = getToken(authorization);

            Algorithm algorithm = Algorithm.HMAC256(secretkey.getBytes());
            JWTVerifier verifier = JWT.require(algorithm).build();
            try {
                DecodedJWT decodedJWT = verifier.verify(token);

                String email = decodedJWT.getSubject();
                String[] roles = decodedJWT.getClaim("roles").asArray(String.class);

                List<SimpleGrantedAuthority> grantedAuthorities = Arrays.stream(roles)
                        .map(role -> new SimpleGrantedAuthority(ROLE_PREFIX.concat(role)))
                        .collect(Collectors.toList());

                var authentication = new UsernamePasswordAuthenticationToken(
                        email, null, grantedAuthorities
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getOutputStream().print(e.getMessage());
            }


        } else filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().startsWith("/api/login");
    }
}
