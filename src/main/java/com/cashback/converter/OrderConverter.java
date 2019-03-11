package com.cashback.converter;

import com.cashback.document.Order;
import com.cashback.document.OrderEntry;
import com.cashback.dto.CartDTO;
import com.cashback.dto.CartEntryDTO;
import com.cashback.service.CashbackService;
import com.cashback.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

@Component
public class OrderConverter {

    @Autowired
    private ProductService productService;

    @Autowired
    private CashbackService cashbackService;

    public Order convert(final CartDTO cart) {

        Order order = new Order();
        order.setSaleDate(new Date());
        order.setEntries(cart.getEntries().stream().map(this::convert).collect(Collectors.toList()));
        order.setTotalCashback(order.getEntries().stream().mapToDouble(OrderEntry::getCashback).sum());

        return order;
    }

    private OrderEntry convert(CartEntryDTO cartEntry) {

        OrderEntry orderEntry = new OrderEntry();
        orderEntry.setProduct(cartEntry.getProduct());
        orderEntry.setQuantity(cartEntry.getQuantity());
        orderEntry.setCashback(this.cashbackService.calculate(orderEntry));

        return orderEntry;
    }
}
