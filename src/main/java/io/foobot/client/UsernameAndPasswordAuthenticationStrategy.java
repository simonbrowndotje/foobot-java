package io.foobot.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpRequest;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.util.Base64;

class UsernameAndPasswordAuthenticationStrategy extends AuthenticationStrategy {

    private static final Log log = LogFactory.getLog(UsernameAndPasswordAuthenticationStrategy.class);

    private static final String JSON_WEB_TOKEN_HEADER = "x-auth-token";

    private String username;
    private String password;

    private String jsonWebToken;

    UsernameAndPasswordAuthenticationStrategy(String username, String password) throws FoobotClientException {
        this.username = username;
        this.password = password;

        try {
            login();
        } catch (Exception e) {
            log.error(e);
            throw new FoobotClientException("Error authenticating with Foobot API using username/password", e);
        }
    }

    private void login() throws Exception {
        String base64EncodedAuthorizationHeader = Base64.getEncoder().encodeToString((username + ":" + password).getBytes("UTF-8"));

        CloseableHttpClient httpClient = HttpClients.createSystem();
        HttpGet httpGet = new HttpGet(FoobotClient.FOOBOT_API_URL + "/v2/user/" + username + "/login/");
        httpGet.addHeader(HttpHeaders.AUTHORIZATION, base64EncodedAuthorizationHeader);

        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                jsonWebToken = response.getFirstHeader(JSON_WEB_TOKEN_HEADER).getValue();
            } else {
                log.error(response.getStatusLine().getStatusCode());
            }
        }
    }

    @Override
    void addAuthenticationInformation(HttpRequest request) {
        request.addHeader(JSON_WEB_TOKEN_HEADER, jsonWebToken);
    }

}
