package com.pyryanov.tinkoffservice.service;

import com.pyryanov.tinkoffservice.dto.FigiesDto;
import com.pyryanov.tinkoffservice.dto.StockPrice;
import com.pyryanov.tinkoffservice.dto.StocksPricesDto;
import com.pyryanov.tinkoffservice.dto.StocksDto;
import com.pyryanov.tinkoffservice.dto.TickersDto;
import com.pyryanov.tinkoffservice.model.Stock;

public interface StockService {
    Stock getStockByTicker(String ticker);

    StocksDto getStocksByTickers(TickersDto tickers);

    StockPrice getPrice(String figi);

    StocksPricesDto getPrices(FigiesDto figiesDto);
}
