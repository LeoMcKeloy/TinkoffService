package com.pyryanov.tinkoffservice.exception;

public class StockNotFoundException extends RuntimeException {
    public StockNotFoundException(String msg) {
        super(msg);
    }
}
