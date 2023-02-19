package com.pyryanov.tinkoffservice.service;

import com.pyryanov.tinkoffservice.dto.FigiesDto;
import com.pyryanov.tinkoffservice.dto.StockPrice;
import com.pyryanov.tinkoffservice.dto.StocksPricesDto;
import com.pyryanov.tinkoffservice.dto.StocksDto;
import com.pyryanov.tinkoffservice.dto.TickersDto;
import com.pyryanov.tinkoffservice.exception.StockNotFoundException;
import com.pyryanov.tinkoffservice.model.Currency;
import com.pyryanov.tinkoffservice.model.Stock;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tinkoff.invest.openapi.OpenApi;
import ru.tinkoff.invest.openapi.model.rest.MarketInstrumentList;

@Service
@RequiredArgsConstructor
@Slf4j
public class TinkoffStockService implements StockService {

    private final OpenApi openApi;
    private final SubService subService;

    @Override
    public Stock getStockByTicker(String ticker) {
        var listCF = subService.getMarketInstrumentTicker(ticker);
        var list = listCF.join().getInstruments();
        if (list.isEmpty()) {
            throw new StockNotFoundException(String.format("Ticker %S not found", ticker));
        }

        var item = list.get(0);

        return Stock.builder()
                .ticker(item.getTicker())
                .figi(item.getFigi())
                .name(item.getName())
                .currency(Currency.valueOf(item.getCurrency().getValue()))
                .type(item.getType().getValue())
                .source("TINKOFF")
                .build();
    }

    @Override
    public StocksDto getStocksByTickers(TickersDto tickers) {
        List<CompletableFuture<MarketInstrumentList>> marketInstrument = new ArrayList<>();

        tickers.getTickers().forEach(ticker -> marketInstrument.add(subService.getMarketInstrumentTicker(ticker)));
        List<Stock> stocks = marketInstrument.stream()
                .map(CompletableFuture::join)
                .map(mi -> {
                    if (!mi.getInstruments().isEmpty()) {
                        return mi.getInstruments().get(0);
                    } else {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .map(item -> Stock.builder()
                        .ticker(item.getTicker())
                        .figi(item.getFigi())
                        .name(item.getName())
                        .currency(Currency.valueOf(item.getCurrency().getValue()))
                        .type(item.getType().getValue())
                        .source("TINKOFF")
                        .build())
                .collect(Collectors.toList());
        return new StocksDto(stocks);
    }

    @Override
    public StockPrice getPrice(String figi) {
        var orderBook = openApi.getMarketContext().getMarketOrderbook(figi, 0).join().get();
        return new StockPrice(figi, orderBook.getLastPrice().doubleValue());
    }

    @Override
    public StocksPricesDto getPrices(FigiesDto figiesDto) {
        long start = System.currentTimeMillis();

        var stockPrices = figiesDto.getFigies().stream()
                .map(subService::getOrderBookByFigi)
                .map(CompletableFuture::join)
                .map(ob -> ob.orElseThrow(
                        () -> new StockNotFoundException("Stock not found"))
                )
                .map(el -> new StockPrice(
                        el.getFigi(), el.getLastPrice().doubleValue())
                )
                .collect(Collectors.toList());
        log.info("Time - {}", System.currentTimeMillis() - start);
        return new StocksPricesDto(stockPrices);
    }
}
