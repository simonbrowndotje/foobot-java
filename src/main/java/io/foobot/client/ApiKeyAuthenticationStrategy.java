package io.foobot.client;

import org.apache.http.HttpRequest;

class ApiKeyAuthenticationStrategy extends AuthenticationStrategy {

    private static final String API_KEY_HEADER  = "X-API-KEY-TOKEN";

    private String apiKey;

    ApiKeyAuthenticationStrategy(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    void addAuthenticationInformation(HttpRequest request) {
        request.addHeader(API_KEY_HEADER, apiKey);
    }

}
