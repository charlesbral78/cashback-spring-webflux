package com.cashback.service;

import com.cashback.document.Order;
import com.cashback.dto.CartDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface OrderService {

    Flux<Order> findBySaleStartDateAndSaleEndDatePageableOrderBySaleDateDesc(Date saleStartDate, Date saleEndDate, int pageNumber, int pageSize);

    Mono<Order> findById(final String id);

    Mono<Order> createOrder(final CartDTO cart);
}
