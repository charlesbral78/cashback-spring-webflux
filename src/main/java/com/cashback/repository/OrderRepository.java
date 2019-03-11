package com.cashback.repository;

import com.cashback.document.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface OrderRepository extends ReactiveSortingRepository<Order, String> {

    Flux<Order> findBySaleDateBetweenOrderBySaleDateDesc(Date saleStartDate, Date saleEndDate, Pageable pageable);
}
