package com.pyryanov.tinkoffservice.dto;

import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.Value;

@AllArgsConstructor
@Value
@ToString
public class StockPrice {
    String figi;
    Double price;
}
