package com.zapolatero.meteoGuardiano.controller;

import com.zapolatero.meteoGuardiano.entity.TemperatureHumidityMeasurement;
import com.zapolatero.meteoGuardiano.mongo_adapter.MongoTemperatureHumidityService;
import com.zapolatero.meteoGuardiano.usecase.AddMeasurementUseCase;
import com.zapolatero.meteoGuardiano.usecase.GetLastMeasurementUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/temperature-humidity")
public class TemperatureHumidityController {
    private final MongoTemperatureHumidityService mongoTemperatureHumidityService;

    @GetMapping("/last")
    public GetLastMeasurementUseCase.GetLastMeasurementResponse getLastTemperatureHumidity() {
        return new GetLastMeasurementUseCase(mongoTemperatureHumidityService).execute();
    }

    @PostMapping
    public void addTemperatureHumidity(@RequestBody AddMeasurementUseCase.AddMeasurementCommand command) {
        new AddMeasurementUseCase(mongoTemperatureHumidityService).execute(command);
    }
}
