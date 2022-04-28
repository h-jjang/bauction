package com.hjjang.backend.global.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

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
    public GroupedOpenApi searchApi() {
        return GroupedOpenApi.builder()
            .group("search")
            .pathsToMatch("/api/search/**")
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
                        .url("https://github.com/h-jjang/bauction"));
    }

}