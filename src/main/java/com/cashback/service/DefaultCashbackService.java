package com.cashback.service;

import com.cashback.document.OrderEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class DefaultCashbackService implements CashbackService {

    @Autowired
    private ApplicationContext context;

    @Override
    public Double calculate(OrderEntry orderEntry) {
        return orderEntry.getProduct().getCategory().cashbackStrategy(context).calculate(orderEntry);
    }
}
