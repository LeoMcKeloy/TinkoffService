package com.pyryanov.tinkoffservice.service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.tinkoff.invest.openapi.OpenApi;
import ru.tinkoff.invest.openapi.model.rest.MarketInstrumentList;
import ru.tinkoff.invest.openapi.model.rest.Orderbook;

@Component
@RequiredArgsConstructor
@Slf4j
public class StockSubService implements SubService {
    private final OpenApi openApi;

    @Async
    public CompletableFuture<MarketInstrumentList> getMarketInstrumentTicker(String ticker) {
        return openApi.getMarketContext().searchMarketInstrumentsByTicker(ticker);
    }

    @Async
    public CompletableFuture<Optional<Orderbook>> getOrderBookByFigi(String figi) {
        log.info("Getting price {} from Tinkoff", figi);
        return openApi.getMarketContext().getMarketOrderbook(figi, 0);
    }
}
