package com.cashback.controller;

import com.cashback.constants.Constants;
import com.cashback.constants.DateUtils;
import com.cashback.document.Order;
import com.cashback.dto.CartDTO;
import com.cashback.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.ParseException;

@RestController(Constants.API_ORDER_BASE_URL)
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(Constants.API_ORDER_BASE_URL + "/by-salestartdate-and-saleenddate-pageable-orderby-saledate-desc")
    public Flux<Order> findBySaleStartDateAndSaleEndDatePageableOrderBySaleDateDesc(
            @RequestParam String saleStartDate,
            @RequestParam String saleEndDate,
            @RequestParam int pageNumber,
            @RequestParam int pageSize) throws ParseException {

        return this.orderService.findBySaleStartDateAndSaleEndDatePageableOrderBySaleDateDesc(DateUtils.parseDate(saleStartDate),DateUtils.parseDate(saleEndDate), pageNumber, pageSize);
    }

    @GetMapping(Constants.API_ORDER_BASE_URL + "/{id}")
    public Mono<Order> findById(@PathVariable String id) {
        return this.orderService.findById(id);
    }

    @PostMapping(Constants.API_ORDER_BASE_URL)
    public Mono<Order> createOrder(@RequestBody CartDTO cart) {
        return this.orderService.createOrder(cart);
    }
}
