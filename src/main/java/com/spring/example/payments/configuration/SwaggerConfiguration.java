package com.spring.example.payments.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

import java.net.URI;
import java.util.function.Predicate;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.temporaryRedirect;
import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
@EnableSwagger2WebFlux
public class SwaggerConfiguration {

    @Bean
    public Docket docket() {
        return new Docket(SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .paths(paths())
                .build();
    }

    private Predicate<String> paths() {
        return regex("/actuator.*")
                .or(regex("/error.*"))
                .or(regex("/"))
                .negate();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Payments")
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> redirectToSwaggerUi() {
        return route(GET("/"),
                     req -> temporaryRedirect(URI.create("/swagger-ui.html")).build());
    }

}
