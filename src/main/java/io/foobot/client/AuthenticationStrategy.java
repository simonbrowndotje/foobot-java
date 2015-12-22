package io.foobot.client;

import org.apache.http.HttpRequest;

abstract class AuthenticationStrategy {

    abstract void addAuthenticationInformation(HttpRequest request);

}
