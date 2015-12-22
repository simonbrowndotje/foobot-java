package io.foobot.util;

import io.foobot.domain.AirQualitySample;

import java.math.BigDecimal;

public class AirQualitySampleCsvParser {

    public AirQualitySample parse(String data) {
        AirQualitySample sample = null;

        if (data != null) {
            String[] lines = data.split("[\\r\\n]+");
            if (lines.length > 1) {
                String[] sampleAsCsv = lines[1].split(",");

                long timestamp = Long.parseLong(sampleAsCsv[0]) * 1000; // the timestamp is measured in seconds
                String localDate = sampleAsCsv[1]; // currently ignoring this
                BigDecimal particulateMatter = new BigDecimal(sampleAsCsv[2]);
                BigDecimal temperature = new BigDecimal(sampleAsCsv[3]);
                BigDecimal humidity = new BigDecimal(sampleAsCsv[4]);
                BigDecimal carbonDioxide = new BigDecimal(sampleAsCsv[5]);
                BigDecimal volatileOrganicCompounds = new BigDecimal(sampleAsCsv[6]);
                BigDecimal pollution = new BigDecimal(sampleAsCsv[7]);

                sample = new AirQualitySample(timestamp, particulateMatter, temperature, humidity, carbonDioxide, volatileOrganicCompounds, pollution);
            }
        }

        return sample;
    }

}
