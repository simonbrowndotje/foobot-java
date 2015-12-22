package io.foobot.domain;

import java.math.BigDecimal;

public class AirQualitySample {

    private long timestamp;
    private BigDecimal particulateMatter;
    private BigDecimal temperature;
    private BigDecimal humidity;
    private BigDecimal carbonDioxide;
    private BigDecimal volatileOrganicCompounds;
    private BigDecimal pollution;

    public AirQualitySample(long timestamp, BigDecimal particulateMatter, BigDecimal temperature, BigDecimal humidity, BigDecimal carbonDioxide, BigDecimal volatileOrganicCompounds, BigDecimal pollution) {
        this.timestamp = timestamp;
        this.particulateMatter = particulateMatter;
        this.temperature = temperature;
        this.humidity = humidity;
        this.carbonDioxide = carbonDioxide;
        this.volatileOrganicCompounds = volatileOrganicCompounds;
        this.pollution = pollution;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public BigDecimal getParticulateMatter() {
        return particulateMatter;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public BigDecimal getHumidity() {
        return humidity;
    }

    public BigDecimal getCarbonDioxide() {
        return carbonDioxide;
    }

    public BigDecimal getVolatileOrganicCompounds() {
        return volatileOrganicCompounds;
    }

    public BigDecimal getPollution() {
        return pollution;
    }

}
