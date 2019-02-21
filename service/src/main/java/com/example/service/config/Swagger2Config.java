package com.example.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author zhoubo
 */
@EnableSwagger2
@Configuration
public class Swagger2Config {
    @Value("${swagger2.config.oauth2.tokenUrl}")
    private String tokenUrl;
    @Value("${swagger2.config.oauth2.clientId}")
    private String clientId;
    @Value("${swagger2.config.oauth2.secret}")
    private String secret;

    private OAuth securitySchema() {
        List<GrantType> grantTypes = new ArrayList();
        GrantType passwordCredentialsGrant = new ResourceOwnerPasswordCredentialsGrant(tokenUrl);
        grantTypes.add(passwordCredentialsGrant);
        return new OAuth("oauth2", scopes(), grantTypes);
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth())
                .forPaths(PathSelectors.ant("/api/**"))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        return Collections.singletonList(new SecurityReference("oauth2", scopes().toArray(new AuthorizationScope[0])));
    }


    private List<AuthorizationScope> scopes() {
        List<AuthorizationScope> list = new ArrayList();
        list.add(new AuthorizationScope("app", "Grants write access"));
        return list;
    }

    @Bean
    public SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder().clientId(clientId).clientSecret(secret).build();
    }


    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.service.controller"))
                .paths(PathSelectors.ant("/api/**"))
//                .paths(PathSelectors.any())
                .build().securityContexts(Collections.singletonList(securityContext()))
                .securitySchemes(Arrays.asList(securitySchema()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("resource文档")
                .description("resource接口说明文档")
                .termsOfServiceUrl("")
                .contact(new Contact("周博", "48715564@qq.com", "48715564@qq.com"))
                .version("1.0")
                .build();
    }
}