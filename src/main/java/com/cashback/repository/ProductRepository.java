package com.cashback.repository;

import com.cashback.constants.Category;
import com.cashback.document.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveSortingRepository<Product, String> {

    Flux<Product> findByCategoryOrderByNameAsc(Category category, Pageable pageable);
}
