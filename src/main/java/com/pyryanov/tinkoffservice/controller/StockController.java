package com.pyryanov.tinkoffservice.controller;

import com.pyryanov.tinkoffservice.dto.FigiesDto;
import com.pyryanov.tinkoffservice.dto.StockPrice;
import com.pyryanov.tinkoffservice.dto.StocksPricesDto;
import com.pyryanov.tinkoffservice.dto.StocksDto;
import com.pyryanov.tinkoffservice.dto.TickersDto;
import com.pyryanov.tinkoffservice.model.Stock;
import com.pyryanov.tinkoffservice.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;

    @GetMapping("/stocks/{ticker}")
    public Stock getStock(@PathVariable String ticker) {
        return stockService.getStockByTicker(ticker);
    }

    @PostMapping("/stocks")
    public StocksDto getStocksByTickers(@RequestBody TickersDto tickers) {
        return stockService.getStocksByTickers(tickers);
    }

    @GetMapping("/prices/{figi}")
    public StockPrice getPrice(@PathVariable String figi) {
        return stockService.getPrice(figi);
    }

    @PostMapping("/prices")
    public StocksPricesDto getPrices(@RequestBody FigiesDto figiesDto) {
        return stockService.getPrices(figiesDto);
    }
}
