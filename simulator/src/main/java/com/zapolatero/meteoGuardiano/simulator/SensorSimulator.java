package com.zapolatero.meteoGuardiano.simulator;

import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class SensorSimulator {
    private final HttpClient httpClient;
    private final String url;

    public SensorSimulator(HttpClient httpClient, String url) {
        this.httpClient = httpClient;
        this.url = url;
    }

    public void run() {
        while (true){
            sendRequest();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    private void sendRequest() {
        Measurement temperature = new Measurement(inRangeRandom(15, 25), "CELSIUS");
        Measurement humidity = new Measurement(inRangeRandom(30, 70), "PERCENT");
        HashMap<String, Measurement> body = new HashMap<>();
        body.put("temperature", temperature);
        body.put("humidity", humidity);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(java.net.URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(new Data(temperature, humidity).toString()))
                .build();
        try {
            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private double inRangeRandom(double min, double max) {
        return Math.random() * (max - min) + min;
    }

    private record Measurement(double value, String unit) {
        @Override
        public String toString() {
            return "{\"value\": " + value + ", \"unit\": \"" + unit + "\"}";
        }
    }
    private record Data(Measurement temperature, Measurement humidity) {
        @Override
        public String toString() {
            return "{\"temperature\": " + temperature + ", \"humidity\": " + humidity + "}";
        }
    }
}
