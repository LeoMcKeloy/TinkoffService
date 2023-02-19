package com.pyryanov.tinkoffservice.dto;

import com.pyryanov.tinkoffservice.model.Stock;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class StocksDto {
    List<Stock> stocks;
}
