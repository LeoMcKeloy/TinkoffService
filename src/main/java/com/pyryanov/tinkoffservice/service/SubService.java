package com.pyryanov.tinkoffservice.service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import ru.tinkoff.invest.openapi.model.rest.MarketInstrumentList;
import ru.tinkoff.invest.openapi.model.rest.Orderbook;

public interface SubService {
    CompletableFuture<MarketInstrumentList> getMarketInstrumentTicker(String ticker);

    CompletableFuture<Optional<Orderbook>> getOrderBookByFigi(String figi);
}
