package com.example.airqualityservice.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum City {
    서울시("서울시"),
    부산시("부산시");

    private final String description;
}
