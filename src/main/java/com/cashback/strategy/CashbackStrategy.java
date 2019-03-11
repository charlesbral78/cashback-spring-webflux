package com.cashback.strategy;

import com.cashback.document.OrderEntry;

public interface CashbackStrategy {

    Double calculate(OrderEntry orderEntry);
}
