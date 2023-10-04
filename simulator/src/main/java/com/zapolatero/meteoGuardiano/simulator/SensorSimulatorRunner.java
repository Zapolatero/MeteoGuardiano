package com.zapolatero.meteoGuardiano.simulator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.http.HttpClient;

@Component
public class SensorSimulatorRunner implements CommandLineRunner {
    @Value("${meteo_guardiano.url}")
    private static String url = "http://localhost:8080/temperature-humidity";
    @Override
    public void run(String... args) throws Exception {
        new SensorSimulator(HttpClient.newHttpClient(), url).run();
    }
}
