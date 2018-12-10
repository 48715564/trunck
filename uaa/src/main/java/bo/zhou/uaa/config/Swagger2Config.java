package bo.zhou.uaa.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zhoubo
 */
@EnableSwagger2
@Configuration
public class Swagger2Config {
    @Value("${swagger2.config.oauth2.authorizeUrl}")
    private String authorizeUrl;
    @Value("${swagger2.config.oauth2.tokenUrl}")
    private String tokenUrl;
    @Value("${swagger2.config.oauth2.clientId}")
    private String clientId;
    @Value("${swagger2.config.oauth2.secret}")
    private String secret;
    @Bean
    List<GrantType> grantTypes() {
        List<GrantType> grantTypes = new ArrayList<>();
        TokenRequestEndpoint tokenRequestEndpoint = new TokenRequestEndpoint(
                authorizeUrl,
                clientId, secret);
        TokenEndpoint tokenEndpoint = new TokenEndpoint(tokenUrl, "access_token");
        grantTypes.add(new AuthorizationCodeGrant(tokenRequestEndpoint, tokenEndpoint));
        return grantTypes;
    }

    @Bean
    SecurityScheme oauth() {
        return new OAuthBuilder()
                .name("OAuth2")
                .scopes(scopes())
                .grantTypes(grantTypes())
                .build();
    }

    private List<AuthorizationScope> scopes() {
        return Lists.newArrayList(new AuthorizationScope("app", "Grants openid access"));
    }

    @Bean
    public SecurityConfiguration securityInfo() {
        return new SecurityConfiguration(clientId,secret,
                "realm", "api",
                "", Maps.newHashMap(),true);
    }
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()).securitySchemes(Collections.singletonList(oauth()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("bo.zhou.uaa.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("resource文档")
                .description("resource接口说明文档")
                .termsOfServiceUrl("")
                .contact(new Contact("周博","48715564@qq.com","48715564@qq.com"))
                .version("1.0")
                .build();
    }
}