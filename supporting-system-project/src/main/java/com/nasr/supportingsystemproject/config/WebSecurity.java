package com.nasr.supportingsystemproject.config;

import com.nasr.supportingsystemproject.config.filter.CustomAuthenticationFilter;
import com.nasr.supportingsystemproject.config.filter.CustomAuthorizationFilter;
import com.nasr.supportingsystemproject.constant.ConstantField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.nasr.supportingsystemproject.constant.ConstantField.*;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity {

    @Autowired
    private CustomAuthorizationFilter authorizationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Value("${secret-key}")
    private String secretKey;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable();
        http.authorizeHttpRequests()
                .mvcMatchers("/user/register","/swagger-ui/**","/v3/**")
                .permitAll()
                .and()
                .authorizeHttpRequests()
                .mvcMatchers("/customer/all","/customer/change-role/*").hasRole(MANAGER)
                .mvcMatchers("/ticket/saveAnswer").hasAnyRole(SUPPORT)
                .mvcMatchers("/ticket/all").hasAnyRole(MANAGER,SUPPORT)
                .mvcMatchers(HttpMethod.DELETE,"/ticket/{id}").hasAnyRole(MANAGER)
                .mvcMatchers("/ticket/save/**").hasRole(CUSTOMER)
                .mvcMatchers("/manager/**").hasRole(MANAGER)
                .anyRequest()
                .authenticated();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterAt(new CustomAuthenticationFilter(secretKey,authenticationManager), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(authorizationFilter, CustomAuthenticationFilter.class);

        return http.build();
    }


    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;

}
