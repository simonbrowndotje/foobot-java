package io.foobot.client;

public class FoobotClientException extends Exception {

    public FoobotClientException(String message) {
        super(message);
    }

    public FoobotClientException(String message, Throwable cause) {
        super(message, cause);
    }

}
