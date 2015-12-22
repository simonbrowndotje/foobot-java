package io.foobot.client;

public final class FoobotClientFactory {

    public static FoobotClient getFoobotClient(String username, String password) throws FoobotClientException {
        return new FoobotClient(new UsernameAndPasswordAuthenticationStrategy(username, password));
    }

    public static FoobotClient getFoobotClient(String apiKey) throws FoobotClientException {
        return new FoobotClient(new ApiKeyAuthenticationStrategy(apiKey));
    }

}
