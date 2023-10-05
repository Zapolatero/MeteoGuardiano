package com.zapolatero.meteoGuardiano.usecase;

import com.zapolatero.meteoGuardiano.entity.TemperatureHumidityMeasurement;
import com.zapolatero.meteoGuardiano.usecase.port.TemperatureHumidityService;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.DoubleStream;

@RequiredArgsConstructor
public class GetAverageInRangeUseCase {
    private final TemperatureHumidityService temperatureHumidityService;

    public BasicStatistics getTemperatureAvg(Date start, Date end){
        List<TemperatureHumidityMeasurement> temperatureHumidityMeasurementList = temperatureHumidityService.findAllInDateRange(start, end);
        DoubleStream tempStream = temperatureHumidityMeasurementList.stream().mapToDouble(t -> t.getTemperature().getValue());
        return BasicStatistics.builder()
                .avg(temperatureHumidityMeasurementList.stream().mapToDouble(t -> t.getTemperature().getValue()).average().orElse(0))
                .min(temperatureHumidityMeasurementList.stream().mapToDouble(t -> t.getTemperature().getValue()).min().orElse(0))
                .max(temperatureHumidityMeasurementList.stream().mapToDouble(t -> t.getTemperature().getValue()).max().orElse(0))
                .build();
    }

    public BasicStatistics getHumidityAvg(Date start, Date end){
        List<TemperatureHumidityMeasurement> temperatureHumidityMeasurementList = temperatureHumidityService.findAllInDateRange(start, end);
        return BasicStatistics.builder()
                .avg(temperatureHumidityMeasurementList.stream().mapToDouble(t -> t.getHumidity().getValue()).average().orElse(0))
                .min(temperatureHumidityMeasurementList.stream().mapToDouble(t -> t.getHumidity().getValue()).min().orElse(0))
                .max(temperatureHumidityMeasurementList.stream().mapToDouble(t -> t.getHumidity().getValue()).max().orElse(0))
                .build();
    }

    @Getter
    @Builder
    public static class BasicStatistics{
        double avg;
        double min;
        double max;
    }
}
