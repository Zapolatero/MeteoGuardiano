package com.zapolatero.meteoGuardiano.usecase;

import com.zapolatero.meteoGuardiano.entity.Measurement;
import com.zapolatero.meteoGuardiano.entity.TemperatureHumidityMeasurement;
import com.zapolatero.meteoGuardiano.usecase.port.TemperatureHumidityService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.text.DecimalFormat;
import java.util.Date;

@RequiredArgsConstructor
public class GetLastMeasurementUseCase {
    private final TemperatureHumidityService temperatureHumidityService;

    public GetLastMeasurementResponse execute(){
        return GetLastMeasurementResponse.fromDomain(temperatureHumidityService.findLastMeasurement().orElseThrow(
                () -> new RuntimeException("No measurement found")
        ));
    }

    @Getter
    @RequiredArgsConstructor
    public static class GetLastMeasurementResponse {
        final Date date;
        final MeasurementDTO temperature;
        final MeasurementDTO humidity;

        public static GetLastMeasurementResponse fromDomain(TemperatureHumidityMeasurement measurement) {
            return new GetLastMeasurementResponse(
                    measurement.getDate(),
                    new MeasurementDTO(measurement.getTemperature()),
                    new MeasurementDTO(measurement.getHumidity())
            );
        }

        public record MeasurementDTO(String value, String unit) {
            MeasurementDTO(Measurement measurement) {
                this(
                        new DecimalFormat("##.##").format(measurement.getValue()),
                        measurement.getUnit().getSymbol()
                );
            }
        }
    }
}
