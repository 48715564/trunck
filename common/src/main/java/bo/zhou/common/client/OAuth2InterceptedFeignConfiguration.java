package bo.zhou.common.client;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

import java.io.IOException;

public class OAuth2InterceptedFeignConfiguration {
    @Value("${oauth2.tokenUrl}")
    private String tokenUrl;
    @Value("${oauth2.clientId}")
    private String clientId;
    @Value("${oauth2.secret}")
    private String secret;

    @Bean("paascloudClientCredentialsResourceDetails")
    public ClientCredentialsResourceDetails resourceDetails() {
        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
        details.setAccessTokenUri(tokenUrl);
        details.setClientId(clientId);
        details.setClientSecret(secret);
        return details;
    }
    @Bean("paascloudOAuth2RestTemplate")
    public OAuth2RestTemplate oAuth2RestTemplate() {
        final OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(resourceDetails(), new DefaultOAuth2ClientContext());
        oAuth2RestTemplate.setRequestFactory(new SimpleClientHttpRequestFactory());
        return oAuth2RestTemplate;

    }

    @Bean(name = "oauth2RequestInterceptor")
    public RequestInterceptor getOAuth2RequestInterceptor(@Qualifier("paascloudOAuth2RestTemplate") OAuth2RestTemplate oAuth2RestTemplate) throws IOException {
        return new OAuth2FeignRequestInterceptor(oAuth2RestTemplate);
    }
}
