package com.nikosera.clientweb.config;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

/**
 * @author Narayan Joshi
 * <narenzoshi@gmail.com>
 */
//@Profile({"dev", "test"})
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    Predicate<RequestHandler> predicate = (RequestHandler input) -> {
        Class<?> declaringClass = input.declaringClass();
        if (declaringClass.getPackage().getName().startsWith("com.nikosera")) {
            if (declaringClass == BasicErrorController.class) {
                return false;
            }
            if (declaringClass.isAnnotationPresent(RestController.class)) {
                return true;
            }
            if (input.isAnnotatedWith(ResponseBody.class)) {
                return true;
            }
        }
        return false;
    };

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(predicate)
                .build()
                .securityContexts(Lists.newArrayList(securityContext()))
                .securitySchemes(Lists.newArrayList(apiKey()))
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails() {
        return new ApiInfo(
                "Server Web Panel",
                "Server Web API management",
                "1.0",
                "Free to use",
                new springfox.documentation.service.Contact("Nikosera", "http://www.nikosera.com/", "test@nikosra.com"),
                "API License",
                "http://www.nikosera.com/",
                Collections.emptyList());
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(addSecurity)
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(
                new SecurityReference("Authorization", authorizationScopes));
    }

    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }

    Predicate<String> addSecurity = (path) -> {
        if (path.equals("/login") || path.equals("/echo"))
            return false;
        return true;
    };

}
