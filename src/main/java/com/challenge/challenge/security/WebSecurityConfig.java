package com.challenge.challenge.security;


import com.challenge.challenge.constants.ServerRoutes;
import com.challenge.challenge.model.Permissions;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final AuthProvider authManager;
    private final SecurityCtxRepository securityCtxRepository;
    private final String[] whiteList = {"/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
            "/configuration/security", "/swagger-ui.html", "/webjars/**"};

    private static class CorsConfig implements CorsConfigurationSource {
        @Override
        public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
            CorsConfiguration corsConfig = new CorsConfiguration();
            corsConfig.applyPermitDefaultValues();
            corsConfig.addAllowedMethod(HttpMethod.POST);
            corsConfig.addAllowedMethod(HttpMethod.PUT);
            corsConfig.addAllowedMethod(HttpMethod.GET);
            corsConfig.addAllowedMethod(HttpMethod.DELETE);
//            corsConfig.setAllowedOrigins(Arrays.asList("https://172.16.32.37:2233", "https://197.243.3.212:2244"));
            return corsConfig;
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().configurationSource(new CorsConfig()).and()
                .csrf().disable()
                .authenticationProvider(authManager)
                .securityContext()
                .securityContextRepository(securityCtxRepository)
                .and()
                .authorizeRequests()
                .antMatchers(whiteList).permitAll()
                .antMatchers(ServerRoutes.AUTH).permitAll()
                .antMatchers(ServerRoutes.TEST).hasAuthority(Permissions.USER_MANAGEMENT.name())
                .antMatchers(ServerRoutes.POST).permitAll()
                .antMatchers(ServerRoutes.POST_GET).permitAll()
                .antMatchers(ServerRoutes.POST_DELETE).permitAll()
                .antMatchers(ServerRoutes.POST_UPDATE).permitAll()
                .antMatchers(ServerRoutes.POST_GET_BY_ID).permitAll()
                .antMatchers(ServerRoutes.POST_SEARCH).permitAll()
                .anyRequest().denyAll()
                .and()
                .httpBasic().disable()
                .formLogin().disable()
                .csrf().disable();
    }
}
