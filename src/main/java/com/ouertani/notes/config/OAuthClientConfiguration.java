package com.ouertani.notes.config;

import com.ouertani.notes.constants.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.registration.*;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class OAuthClientConfiguration {

    @Bean
    ReactiveClientRegistrationRepository clientRegistrations(
//            @Value("${spring.security.oauth2.client.provider.okta.token-uri}") String token_uri,
//            @Value("${spring.security.oauth2.client.registration.okta.client-id}") String client_id,
//            @Value("${spring.security.oauth2.client.registration.okta.client-secret}") String client_secret,
//            @Value("${spring.security.oauth2.client.registration.okta.scope}") String scope,
//            @Value("${spring.security.oauth2.client.registration.okta.authorization-grant-type}") String authorizationGrantType

    ) {
        ClientRegistration registration = ClientRegistration
                .withRegistrationId("okta")
                .tokenUri(Constants.OKTA_TOKEN_URI)
                .clientId(Constants.OKTA_CLIENT_ID)
                .clientSecret(Constants.OKTA_CLIENT_SECRET)
                .scope(Constants.OKTA_CLIENT_SCOPE)
                .authorizationGrantType(new AuthorizationGrantType("client_credentials"))
                .build();
        return new InMemoryReactiveClientRegistrationRepository(registration);
    }

    @Bean
    WebClient webClient(ReactiveClientRegistrationRepository clientRegistrations) {
        InMemoryReactiveOAuth2AuthorizedClientService clientService = new InMemoryReactiveOAuth2AuthorizedClientService(clientRegistrations);
        AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager authorizedClientManager = new AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager(clientRegistrations, clientService);
        ServerOAuth2AuthorizedClientExchangeFilterFunction oauth = new ServerOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);
        oauth.setDefaultClientRegistrationId("okta");
        return WebClient.builder()
                .filter(oauth)
                .build();
    }

}