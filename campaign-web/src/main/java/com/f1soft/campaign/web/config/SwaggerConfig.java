package com.f1soft.campaign.web.config;

import com.google.common.base.Predicate;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@Profile({"dev", "test"})
@EnableSwagger2
public class SwaggerConfig {

    Predicate<RequestHandler> predicate = (RequestHandler input) -> {
        Class<?> declaringClass = input.declaringClass();
        if (declaringClass.getPackage().getName().startsWith("com.f1soft.campaign")) {
            if (declaringClass == BasicErrorController.class) {
                return false;
            }
            if (declaringClass.isAnnotationPresent(RestController.class)) {
                return true;
            }
            return input.isAnnotatedWith(ResponseBody.class);
        }
        return false;
    };

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(predicate)
                .build()
                .apiInfo(apiInfo())
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Campaign ").description("Web portal for Campaign Web service")
                .termsOfServiceUrl("https://f1soft.com").contact(new Contact("f1soft", "https://wwww.f1soft.com", "banksmart@f1soft.com"))
                .license("JavaInUse License")
                .licenseUrl("banksmart@f1soft.com").version("1.0").build();
    }

    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }

    @Bean
    SecurityConfiguration security() {
        return new SecurityConfiguration(
                null,
                null,
                null, // realm Needed for authenticate button to work
                null, // appName Needed for authenticate button to work
                "",// apiKeyValue
                ApiKeyVehicle.HEADER,
                "Authorization", //apiKeyName
                "");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(
                new SecurityReference("Authorization", authorizationScopes));
    }
}
