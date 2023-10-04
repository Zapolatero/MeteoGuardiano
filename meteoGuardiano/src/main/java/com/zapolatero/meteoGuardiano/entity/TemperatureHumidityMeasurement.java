package com.zapolatero.meteoGuardiano.entity;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class TemperatureHumidityMeasurement {
    private Date date;
    private Measurement temperature;
    private Measurement humidity;
}
