package com.cashback.controller;

import com.cashback.constants.Category;
import com.cashback.constants.URLConstants;
import com.cashback.document.Product;
import com.cashback.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController(URLConstants.API_PRODUCT_BASE_URL)
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(URLConstants.API_PRODUCT_BASE_URL + "/by-category-pageable-orderby-name-asc")
    public Flux<Product> findByCategoryPageableOrderByNameAsc(@RequestParam Category category, @RequestParam int pageNumber, @RequestParam int pageSize) {
        return this.productService.findByCategoryPageableOrderByNameAsc(category, pageNumber, pageSize);
    }

    @GetMapping(URLConstants.API_PRODUCT_BASE_URL + "/{id}")
    public Mono<Product> findById(@PathVariable String id) {
        return this.productService.findById(id);
    }

    @PostMapping(URLConstants.API_PRODUCT_BASE_URL)
    public Mono<Product> save(@RequestBody Product product) {
        return this.productService.save(product);
    }
}
