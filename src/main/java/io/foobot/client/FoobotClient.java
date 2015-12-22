package io.foobot.client;

import io.foobot.domain.AirQualitySample;
import io.foobot.util.AirQualitySampleCsvParser;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class FoobotClient {

    static final String FOOBOT_API_URL = "https://api.foobot.io";

    private AuthenticationStrategy authenticationStrategy;

    FoobotClient(AuthenticationStrategy authenticationStrategy) {
        this.authenticationStrategy = authenticationStrategy;
    }

    public AirQualitySample getLastAirQualitySample(String deviceUuid) throws FoobotClientException {
        return getLastAirQualitySample(deviceUuid, 0, 0);
    }

    public AirQualitySample getLastAirQualitySample(String deviceUuid, int period, int sampling) throws FoobotClientException {
        try {
            CloseableHttpClient httpClient = HttpClients.createSystem();
            HttpGet httpGet = new HttpGet(FOOBOT_API_URL + "/v2/device/" + deviceUuid + "/datapoint/" + period + "/last/" + sampling + "/");
            authenticationStrategy.addAuthenticationInformation(httpGet);
            addHeader(httpGet, HttpHeaders.ACCEPT, "text/csv;charset=UTF-8");

            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    String data = EntityUtils.toString(response.getEntity());
                    return new AirQualitySampleCsvParser().parse(data);
                } else {
                    int statusCode = response.getStatusLine().getStatusCode();
                    throw new FoobotClientException("Status code " + statusCode);
                }
            }
        } catch (Exception e) {
            throw new FoobotClientException("Error getting last air quality sample", e);
        }
    }

    private void addHeader(HttpRequestBase httpRequest, String key, String value) throws Exception {
        httpRequest.addHeader(key, value);
    }

}
