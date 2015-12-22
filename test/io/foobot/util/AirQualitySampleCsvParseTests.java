package io.foobot.util;

import io.foobot.domain.AirQualitySample;
import io.foobot.util.AirQualitySampleCsvParser;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AirQualitySampleCsvParseTests {

    private AirQualitySampleCsvParser parser;

    @Before
    public void setUp() {
        this.parser = new AirQualitySampleCsvParser();
    }

    @Test
    public void test_parse_ReturnsNull_WhenPassedNull() {
        assertNull(parser.parse(null));
    }

    @Test
    public void test_parse_returnsNull_WhenOnlyAHeaderLineIsPassed() {
        String data = "#time (s), device local time, pm (ugm3), tmp (C), hum (pc), co2 (ppm), voc (ppb), global (%)";
        assertNull(parser.parse(data));
    }

    @Test
    public void test_parse_ReturnsAnAirQualitySampleObject_WhenPassedSomeCsvData() {
        String data =
                "#time (s), device local time, pm (ugm3), tmp (C), hum (pc), co2 (ppm), voc (ppb), global (%)\n" +
                "1450790441,2015/12/22 14:20:41,0.0,20.94,57.367,565.0,157.0,4.571429";

        AirQualitySample sample = parser.parse(data);
        assertEquals(1450790441000L, sample.getTimestamp());
        assertEquals("0.0", sample.getParticulateMatter().toString());
        assertEquals("20.94", sample.getTemperature().toString());
        assertEquals("57.367", sample.getHumidity().toString());
        assertEquals("565.0", sample.getCarbonDioxide().toString());
        assertEquals("157.0", sample.getVolatileOrganicCompounds().toString());
        assertEquals("4.571429", sample.getPollution().toString());
    }

}
