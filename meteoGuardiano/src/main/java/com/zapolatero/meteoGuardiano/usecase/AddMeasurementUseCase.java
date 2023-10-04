package com.zapolatero.meteoGuardiano.usecase;

import com.zapolatero.meteoGuardiano.entity.Measurement;
import com.zapolatero.meteoGuardiano.entity.TemperatureHumidityMeasurement;
import com.zapolatero.meteoGuardiano.usecase.port.TemperatureHumidityService;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@RequiredArgsConstructor
public class AddMeasurementUseCase {
    private final TemperatureHumidityService temperatureHumidityService;

    public void execute(AddMeasurementCommand command) {
        temperatureHumidityService.save(TemperatureHumidityMeasurement.builder()
                .temperature(command.temperature)
                .humidity(command.humidity)
                .date(new Date())
                .build());
    }

    public static class AddMeasurementCommand {
        public Measurement temperature;
        public Measurement humidity;
    }
}

