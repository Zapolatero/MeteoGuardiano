package com.zapolatero.meteoGuardiano.mongo_adapter.repository;

import com.zapolatero.meteoGuardiano.mongo_adapter.model.TemperatureHumidityModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MongoTemperatureHumidityRepository extends MongoRepository<TemperatureHumidityModel, String>{
    TemperatureHumidityModel findFirstByOrderByDateDesc();
    List<TemperatureHumidityModel> findAllByDateBetween(Date start, Date end);
}
