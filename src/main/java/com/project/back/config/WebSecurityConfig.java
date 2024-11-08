package com.project.back.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.project.back.filter.JwtAuthenticationFilter;
import com.project.back.handler.OAuth2SuccessHandler;
import com.project.back.service.implementation.OAuth2UserServiceImplementation;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Configurable
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

        private final JwtAuthenticationFilter jwtAuthenticationFilter;
        private final OAuth2UserServiceImplementation oAuth2UserService;
        private final OAuth2SuccessHandler oAuth2SuccessHandler;

        @Bean
        protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
                httpSecurity
                                .httpBasic(HttpBasicConfigurer::disable)
                                .csrf(CsrfConfigurer::disable)
                                .sessionManagement(sessionManagement -> sessionManagement
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                                .authorizeHttpRequests(request -> request
                                                // 모두 접근 가능
                                                .requestMatchers("/", "/api/shop/auth/*", "/api/shop/user/**",
                                                                "/api/shop/auth/password-update/**",
                                                                "/oauth2/callback/*", "/api/shop/*/list",
                                                                "/api/shop/*/list/*", "/api/shop/*/list/category/**",
                                                                "/api/shop/*/*/increase-view-count", "/upload",
                                                                "/file/**", "/api/shop/cloth-detail/**")
                                                .permitAll()
                                                // user 접근 가능
                                                .requestMatchers(HttpMethod.POST, "/api/shop/qna/regist",
                                                                "/api/shop/qna/*/modify",
                                                                "/api/shop/qna/*/delete", "api/shop/qna/mylist")
                                                .hasRole("USER")
                                                // admin 접근 가능
                                                .requestMatchers(HttpMethod.POST, "/api/shop/qna/*/comment",
                                                                "/api/shop/notice/regist",
                                                                "/api/shop/notice/*/modify",
                                                                "/api/shop/notice/*/delete",
                                                                "/api/shop/faq/regist", "/api/shop/faq/*/modify",
                                                                "/api/shop/faq/*/delete", 
                                                                "/api/shop/admin/**")
                                                .hasRole("ADMIN")
                                                .anyRequest().authenticated())
                                .oauth2Login(oauth2 -> oauth2
                                                .authorizationEndpoint(
                                                                endpoint -> endpoint.baseUri("/api/shop/auth/oauth2"))
                                                .redirectionEndpoint(endpoint -> endpoint.baseUri("/oauth2/callback/*"))
                                                .userInfoEndpoint(endpoint -> endpoint.userService(oAuth2UserService))
                                                .successHandler(oAuth2SuccessHandler))
                                .exceptionHandling(exception -> exception
                                                .authenticationEntryPoint(new AuthorizationFailEntryPoint()))
                                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

                return httpSecurity.build();
        }

        // Cors 정책 설정
        @Bean
        protected CorsConfigurationSource corsConfigurationSource() {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.addAllowedOrigin("*");
                configuration.addAllowedHeader("*");
                configuration.addAllowedMethod("*");

                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);

                return source;
        }
}

class AuthorizationFailEntryPoint implements AuthenticationEntryPoint {

        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response,
                        AuthenticationException authException)
                        throws IOException, ServletException {

                authException.printStackTrace();

                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("{ \"code\": \"AF\", \"message\": \"Authorization Failed\" }");
        }
}