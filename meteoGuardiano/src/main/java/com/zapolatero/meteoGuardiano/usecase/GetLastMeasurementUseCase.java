package com.zapolatero.meteoGuardiano.usecase;

import com.zapolatero.meteoGuardiano.entity.TemperatureHumidityMeasurement;
import com.zapolatero.meteoGuardiano.usecase.port.TemperatureHumidityService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetLastMeasurementUseCase {
    private final TemperatureHumidityService temperatureHumidityService;

    public TemperatureHumidityMeasurement execute(){
        return temperatureHumidityService.findLastMeasurement().orElseThrow(
                () -> new RuntimeException("No measurement found")
        );
    }
}
