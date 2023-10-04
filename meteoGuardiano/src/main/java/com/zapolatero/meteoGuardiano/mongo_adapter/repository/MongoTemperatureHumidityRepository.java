package com.zapolatero.meteoGuardiano.mongo_adapter.repository;

import com.zapolatero.meteoGuardiano.mongo_adapter.model.TemperatureHumidityModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MongoTemperatureHumidityRepository extends MongoRepository<TemperatureHumidityModel, String>{
    TemperatureHumidityModel findFirstByOrderByDateDesc();
}
