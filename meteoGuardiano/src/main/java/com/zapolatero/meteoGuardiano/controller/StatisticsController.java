package com.zapolatero.meteoGuardiano.controller;

import com.zapolatero.meteoGuardiano.usecase.GetAverageInRangeUseCase;
import com.zapolatero.meteoGuardiano.usecase.port.TemperatureHumidityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statistics")
public class StatisticsController {
    private final TemperatureHumidityService temperatureHumidityService;

    @GetMapping("/avg/temperature")
    public GetAverageInRangeUseCase.BasicStatistics getAvgTemperature(@RequestParam String start, @RequestParam String end) {
        return new GetAverageInRangeUseCase(temperatureHumidityService).getTemperatureAvg(java.sql.Date.valueOf(start), java.sql.Date.valueOf(end));
    }

    @GetMapping("/avg/humidity")
    public GetAverageInRangeUseCase.BasicStatistics getAvgHumidity(@RequestParam String start, @RequestParam String end) {
        return new GetAverageInRangeUseCase(temperatureHumidityService).getHumidityAvg(java.sql.Date.valueOf(start), java.sql.Date.valueOf(end));
    }
}
