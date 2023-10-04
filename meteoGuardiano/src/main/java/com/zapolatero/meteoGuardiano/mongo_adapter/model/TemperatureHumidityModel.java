package com.zapolatero.meteoGuardiano.mongo_adapter.model;

import com.zapolatero.meteoGuardiano.entity.Measurement;
import com.zapolatero.meteoGuardiano.entity.TemperatureHumidityMeasurement;
import com.zapolatero.meteoGuardiano.entity.Unit;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Builder
@Document("measurements")
public class TemperatureHumidityModel {
    @Id
    private String _id;
    private Date date;
    private double temperature;
    private double humidity;
    private String temperatureUnit;
    private String humidityUnit;

    public static TemperatureHumidityModel fromDomain(TemperatureHumidityMeasurement measurement) {
        return TemperatureHumidityModel.builder()
                .date(measurement.getDate())
                .humidity(measurement.getHumidity().getValue())
                .humidityUnit(measurement.getHumidity().getUnit().toString())
                .temperature(measurement.getTemperature().getValue())
                .temperatureUnit(measurement.getTemperature().getUnit().toString())
                .build();
    }

    public TemperatureHumidityMeasurement toEntity(){
        return TemperatureHumidityMeasurement.builder()
                .temperature(new Measurement(temperature, Unit.valueOf(temperatureUnit)))
                .humidity(new Measurement(humidity, Unit.valueOf(humidityUnit)))
                .date(date)
                .build();
    }
}
