package com.hjjang.backend.global.config.security;

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

import com.hjjang.backend.domain.user.entity.RoleType;
import com.hjjang.backend.domain.user.repository.UserRefreshTokenRepository;
import com.hjjang.backend.global.config.properties.AuthProperties;
import com.hjjang.backend.global.config.security.exception.RestAuthenticationEntryPoint;
import com.hjjang.backend.global.config.security.filter.TokenAuthenticationFilter;
import com.hjjang.backend.global.config.security.handler.OAuth2AuthenticationFailureHandler;
import com.hjjang.backend.global.config.security.handler.OAuth2AuthenticationSuccessHandler;
import com.hjjang.backend.global.config.security.handler.TokenAccessDeniedHandler;
import com.hjjang.backend.global.config.security.repository.OAuth2AuthorizationRequestBasedOnCookieRepository;
import com.hjjang.backend.global.config.security.service.CustomOAuth2UserService;
import com.hjjang.backend.global.config.security.token.AuthTokenProvider;

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
        web.ignoring().antMatchers("/static/css/**", "/static/js/**", "*.ico"); // 나중에 수정

        // swagger
        web.ignoring().antMatchers(
            "/v2/api-docs", "/configuration/ui", "/swagger-resources",
            "/configuration/security", "/swagger-ui.html", "/webjars/**", "/swagger/**");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // spring security 기본 로그인 옵션을 비활성화 환다.
        http
            .csrf().disable()  // csrf은 세션 로그인용 보안 설정
            .httpBasic().disable()
            .formLogin().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt는 세션을 사용하지 않는다.
            .and()
            .logout().disable();

        http
            .cors()
            .configurationSource(corsConfigurationSource());

        http
            .exceptionHandling()
            .authenticationEntryPoint(new RestAuthenticationEntryPoint()) // 비로그인 시 예외 인증
            .accessDeniedHandler(tokenAccessDeniedHandler) // 로그인 거부 예외
            .and()
            .authorizeRequests()
            .antMatchers("/login", "/accounts", "/swagger-resources/**", "/swagger-ui/**").permitAll()
            .antMatchers("/oauth2/authorization/**", "**/oauth2/code/*").permitAll()
            .requestMatchers(CorsUtils::isPreFlightRequest).permitAll() //cors를 검증 하는 option 함수의 경우 별도의 filter 없이 허용
            .antMatchers("/api/**").hasAnyAuthority(RoleType.USER.getCode())
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
     * CORS 설정
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
     * auth 매니저 설정
     * */
    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /*
     * 토큰 필터 설정
     * */
    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter(tokenProvider);
    }

    /*
     * 쿠키 기반 인가 Repository으로 인가 응답을 연계 하고 검증할 때 사용.
     * */
    @Bean
    public OAuth2AuthorizationRequestBasedOnCookieRepository oAuth2AuthorizationRequestBasedOnCookieRepository() {
        return new OAuth2AuthorizationRequestBasedOnCookieRepository();
    }

    /*
     * Oauth 인증 성공
    @Bean 핸들러
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
     * Oauth 인증 실패 핸들러
     * */
    @Bean
    public OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler() {
        return new OAuth2AuthenticationFailureHandler(oAuth2AuthorizationRequestBasedOnCookieRepository());
    }

    /*
     * security 설정 시, 사용할 인코더 설정
     * */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}