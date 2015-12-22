package io.foobot;

import io.foobot.client.FoobotClient;
import io.foobot.client.FoobotClientFactory;
import io.foobot.domain.AirQualitySample;

public class Main {

    public static void main(String[] args) throws Exception {
        FoobotClient foobotClient = FoobotClientFactory.getFoobotClient("username", "password");

        AirQualitySample sample = foobotClient.getLastAirQualitySample("deviceUuid");
        System.out.println(sample.getPollution());
    }

}
