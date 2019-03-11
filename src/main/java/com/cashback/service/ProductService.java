package com.cashback.service;

import com.cashback.constants.Category;
import com.cashback.document.Product;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    Flux<Product> findByCategoryPageableOrderByNameAsc(final Category category, int pageNumber, int pageSize);
    Mono<Product> findById(final String id);
    Mono<Product> save(final Product product);
}
