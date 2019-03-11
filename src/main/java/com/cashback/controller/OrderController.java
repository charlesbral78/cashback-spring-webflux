package com.cashback.controller;

import com.cashback.constants.URLConstants;
import com.cashback.document.Order;
import com.cashback.dto.CartDTO;
import com.cashback.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController(URLConstants.API_ORDER_BASE_URL)
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(URLConstants.API_ORDER_BASE_URL + "/by-salestartdate-and-saleenddate-pageable-orderby-saledate-desc")
    public Flux<Order> findBySaleStartDateAndSaleEndDatePageableOrderBySaleDateDesc(
            @RequestParam Date saleStartDate, @RequestParam Date saleEndDate,
            @RequestParam int pageNumber, @RequestParam int pageSize) {

        return this.orderService.findBySaleStartDateAndSaleEndDatePageableOrderBySaleDateDesc(saleStartDate, saleEndDate, pageNumber, pageSize);
    }

    @GetMapping(URLConstants.API_ORDER_BASE_URL + "/{id}")
    public Mono<Order> findById(@PathVariable String id) {
        return this.orderService.findById(id);
    }

    @PostMapping(URLConstants.API_ORDER_BASE_URL)
    public Mono<Order> createOrder(@RequestBody CartDTO cart) {
        return this.orderService.createOrder(cart);
    }
}
