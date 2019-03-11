package com.cashback.service;

import com.cashback.converter.OrderConverter;
import com.cashback.document.Order;
import com.cashback.dto.CartDTO;
import com.cashback.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class DefaultOrderService implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderConverter orderConverter;

    @Override
    public Flux<Order> findBySaleStartDateAndSaleEndDatePageableOrderBySaleDateDesc(Date saleStartDate, Date saleEndDate, int pageNumber, int pageSize) {
        return this.orderRepository.findBySaleDateBetweenOrderBySaleDateDesc(saleStartDate, saleEndDate, this.createPageable(pageNumber, pageSize));
    }

    private Pageable createPageable(int pageNumber, int pageSize) {
        return PageRequest.of(pageNumber, pageSize);
    }

    @Override
    public Mono<Order> findById(final String id) {
        return this.orderRepository.findById(id);
    }

    @Override
    public Mono<Order> createOrder(final CartDTO cart) {

        Order order = this.orderConverter.convert(cart);

        return this.orderRepository.save(order);
    }
}
