package com.zapolatero.meteoGuardiano.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Measurement {
    double value;
    Unit unit;
}
