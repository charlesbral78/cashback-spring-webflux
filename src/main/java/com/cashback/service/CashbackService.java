package com.cashback.service;

import com.cashback.document.OrderEntry;

public interface CashbackService {

    Double calculate(OrderEntry orderEntry);
}
