package com.zapolatero.meteoGuardiano.usecase.port;

import com.zapolatero.meteoGuardiano.entity.TemperatureHumidityMeasurement;

import java.util.Optional;

public interface TemperatureHumidityService {
    Optional<TemperatureHumidityMeasurement> findLastMeasurement();

    void save(TemperatureHumidityMeasurement measurement);
}
