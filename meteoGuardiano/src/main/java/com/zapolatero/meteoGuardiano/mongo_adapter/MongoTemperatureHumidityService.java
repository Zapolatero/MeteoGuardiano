package com.zapolatero.meteoGuardiano.mongo_adapter;

import com.zapolatero.meteoGuardiano.entity.TemperatureHumidityMeasurement;
import com.zapolatero.meteoGuardiano.mongo_adapter.repository.MongoTemperatureHumidityRepository;
import com.zapolatero.meteoGuardiano.usecase.port.TemperatureHumidityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.zapolatero.meteoGuardiano.mongo_adapter.model.TemperatureHumidityModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MongoTemperatureHumidityService implements TemperatureHumidityService {
    private final MongoTemperatureHumidityRepository repository;

    @Override
    public Optional<TemperatureHumidityMeasurement> findLastMeasurement() {
        return Optional.of(repository.findFirstByOrderByDateDesc().toEntity());
    }

    @Override
    public void save(TemperatureHumidityMeasurement measurement) {
        repository.save(TemperatureHumidityModel.fromDomain(measurement));
    }

    @Override
    public List<TemperatureHumidityMeasurement> findAllInDateRange(Date start, Date end) {
        return repository.findAllByDateBetween(start, end).stream().map(TemperatureHumidityModel::toEntity).toList();
    }
}
