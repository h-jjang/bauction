package com.hjjang.backend.global.config.security;

import static com.hjjang.backend.domain.user.entity.RoleType.*;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.hjjang.backend.domain.user.repository.UserRefreshTokenRepository;
import com.hjjang.backend.global.config.security.properties.AuthProperties;
import com.hjjang.backend.global.security.exception.RestAuthenticationEntryPoint;
import com.hjjang.backend.global.security.filter.TokenAuthenticationFilter;
import com.hjjang.backend.global.security.handler.OAuth2AuthenticationFailureHandler;
import com.hjjang.backend.global.security.handler.OAuth2AuthenticationSuccessHandler;
import com.hjjang.backend.global.security.handler.TokenAccessDeniedHandler;
import com.hjjang.backend.global.security.repository.OAuth2AuthorizationRequestBasedOnCookieRepository;
import com.hjjang.backend.global.security.service.CustomOAuth2UserService;
import com.hjjang.backend.global.security.token.AuthTokenProvider;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthProperties authProperties;
    private final AuthTokenProvider tokenProvider;
    private final CustomOAuth2UserService oAuth2UserService;
    private final TokenAccessDeniedHandler tokenAccessDeniedHandler;
    private final UserRefreshTokenRepository userRefreshTokenRepository;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/css/**", "/static/js/**", "*.ico"); // ????????? ??????

        // swagger
        web.ignoring().antMatchers(
            "/v2/api-docs", "/configuration/ui", "/swagger-resources",
            "/configuration/security", "/swagger-ui.html", "/webjars/**", "/swagger/**", "/h2-console/**");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // spring security ?????? ????????? ????????? ???????????? ??????.
        http
            .csrf().disable()  // csrf??? ?????? ???????????? ?????? ??????
            .httpBasic().disable()
            .formLogin().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt??? ????????? ???????????? ?????????.
            .and()
            .logout().disable();

        http
            .cors()
            .configurationSource(corsConfigurationSource());

        http
            .exceptionHandling()
            .authenticationEntryPoint(new RestAuthenticationEntryPoint()) // ???????????? ??? ?????? ??????
            .accessDeniedHandler(tokenAccessDeniedHandler) // ????????? ?????? ??????
            .and()
            .authorizeRequests()
            .antMatchers("/login", "/accounts", "/swagger-resources/**", "/swagger-ui/**", "/h2-console/**").permitAll()
            .antMatchers("/oauth2/authorization/**", "**/oauth2/code/*").permitAll()
            .requestMatchers(CorsUtils::isPreFlightRequest).permitAll() //cors??? ?????? ?????? option ????????? ?????? ????????? filter ?????? ??????
//            .antMatchers("/api/**").hasAnyAuthority(RoleType.USER.getCode())
            .antMatchers("/api/v1/users/**").hasAnyAuthority(USER.getCode())
            .antMatchers("/api/mail/**").hasAnyAuthority(USER.getCode())
            .anyRequest().permitAll();

        http

            .oauth2Login()
            .authorizationEndpoint()
            .baseUri("/oauth2/authorization")
            .authorizationRequestRepository(oAuth2AuthorizationRequestBasedOnCookieRepository())
            .and()
            .redirectionEndpoint()
            .baseUri("/*/oauth2/code/*")
            .and()
            .userInfoEndpoint()
            .userService(oAuth2UserService)
            .and()
            .successHandler(oAuth2AuthenticationSuccessHandler())
            .failureHandler(oAuth2AuthenticationFailureHandler());

        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * CORS ??????
     */

    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource corsConfigSource = new UrlBasedCorsConfigurationSource();

        AuthProperties.CorsProperties corsProperties = authProperties.getCorsProperties();

        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedHeaders(Arrays.asList(corsProperties.getAllowedHeaders().split(",")));
        corsConfig.setAllowedMethods(Arrays.asList(corsProperties.getAllowedMethods().split(",")));
        corsConfig.setAllowedOrigins(Arrays.asList(corsProperties.getAllowedOrigins().split(",")));
        corsConfig.setAllowCredentials(true);
        corsConfig.setMaxAge(corsProperties.getMaxAge());

        corsConfigSource.registerCorsConfiguration("/**", corsConfig);

        return corsConfigSource;
    }

    /*
     * auth ????????? ??????
     * */
    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

    /*
     * ?????? ?????? ??????
     * */
    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter(tokenProvider);
    }

    /*
     * ?????? ?????? ?????? Repository?????? ?????? ????????? ?????? ?????? ????????? ??? ??????.
     * */
    @Bean
    public OAuth2AuthorizationRequestBasedOnCookieRepository oAuth2AuthorizationRequestBasedOnCookieRepository() {
        return new OAuth2AuthorizationRequestBasedOnCookieRepository();
    }

    /*
     * Oauth ?????? ??????
    @Bean ?????????
     * */
    public OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler() {
        return new OAuth2AuthenticationSuccessHandler(
            tokenProvider,
            authProperties,
            userRefreshTokenRepository,
            oAuth2AuthorizationRequestBasedOnCookieRepository()
        );
    }

    /*
     * Oauth ?????? ?????? ?????????
     * */
    @Bean
    public OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler() {
        return new OAuth2AuthenticationFailureHandler(oAuth2AuthorizationRequestBasedOnCookieRepository());
    }

    /*
     * security ?????? ???, ????????? ????????? ??????
     * */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}