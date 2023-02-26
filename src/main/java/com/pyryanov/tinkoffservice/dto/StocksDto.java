package com.pyryanov.tinkoffservice.dto;

import com.pyryanov.tinkoffservice.model.Stock;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.Value;

@Value
@AllArgsConstructor
@ToString
public class StocksDto {
    List<Stock> stocks;
}
