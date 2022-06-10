package com.hjjang.backend.global.config;

import java.util.Arrays;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
    SecurityScheme securityScheme = new SecurityScheme()
        .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
        .in(SecurityScheme.In.HEADER).name("Authorization");
    SecurityRequirement securityRequirement = new SecurityRequirement().addList("Bearer Token");

    @Bean
    public GroupedOpenApi categoryApi() {
        return GroupedOpenApi.builder()
                .group("categories")
                .pathsToMatch("/categories/**")
                .build();
    }

    @Bean
    public GroupedOpenApi mailApi() {
        return GroupedOpenApi.builder()
            .group("mail")
            .pathsToMatch("/api/mail/**")
            .build();
    }

    @Bean
    public OpenAPI bauctionOpenAPI() {
        return new OpenAPI()
            .info(new Info().title("bauction API")
                    .description("중고거래 서비스 bauction의 API 입니다.")
                    .version("v0.0.1")
                    .license(new License().name("Apache 2.0").url("http://springdoc.org")))
            .externalDocs(new ExternalDocumentation()
                    .description("github link")
                    .url("https://github.com/h-jjang/bauction"))
            .components(new Components().addSecuritySchemes("Bearer Token", securityScheme))
            .security(Arrays.asList(securityRequirement));
    }

}