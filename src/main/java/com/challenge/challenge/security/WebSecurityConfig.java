package com.challenge.challenge.security;


import com.challenge.challenge.constants.ServerRoutes;
import com.challenge.challenge.model.Permissions;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final AuthManager authManager;
    private final SecurityCtxRepository securityCtxRepository;
    private final String[] whiteList = {"/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**"};

    private static class CorsConfig implements CorsConfigurationSource {
        @Override
        public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
            CorsConfiguration corsConfig = new CorsConfiguration();
            corsConfig.applyPermitDefaultValues();
            corsConfig.addAllowedMethod(HttpMethod.POST);
            corsConfig.addAllowedMethod(HttpMethod.GET);
            corsConfig.addAllowedMethod(HttpMethod.DELETE);
//            corsConfig.setAllowedOrigins(List.of("https://172.16.32.37:8023", "https://197.243.3.212:2244"));
            return corsConfig;
        }
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(configurer -> configurer.configurationSource(new CorsConfig()))
                .csrf(configurer -> configurer.disable())
                .httpBasic(configurer -> configurer.disable())
                .formLogin(configurer -> configurer.disable())
                // "frame-ancestors" is used to allow iframes and embed on specific domains
                .authenticationManager(authManager)
                .securityContext(configurer -> configurer.securityContextRepository(securityCtxRepository))
                .authorizeHttpRequests(configurer -> configurer
                        .requestMatchers(whiteList).permitAll()
                        .requestMatchers(ServerRoutes.AUTH).permitAll()
                        .requestMatchers("/thread").permitAll()
                        .requestMatchers(ServerRoutes.TEST).hasAuthority(Permissions.USER_MANAGEMENT.name())
                        .requestMatchers(ServerRoutes.POST).permitAll()
                        .requestMatchers(ServerRoutes.POST_GET).permitAll()
                        .requestMatchers(ServerRoutes.POST_DELETE).permitAll()
                        .requestMatchers(ServerRoutes.POST_UPDATE).permitAll()
                        .requestMatchers(ServerRoutes.POST_GET_BY_ID).permitAll()
                        .requestMatchers(ServerRoutes.POST_SEARCH).permitAll()
                        .requestMatchers(ServerRoutes.LIKE).permitAll()
                        .requestMatchers(ServerRoutes.COMMENT).permitAll()
                        .anyRequest().permitAll()).build();
    }
}
