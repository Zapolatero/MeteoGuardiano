package com.zapolatero.meteoGuardiano.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Unit {
    CELSIUS("°C"),
    FAHRENHEIT("°F"),
    PERCENT("%");

    private final String symbol;
}
