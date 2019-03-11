package com.cashback.service;

import com.cashback.constants.Category;
import com.cashback.document.Product;
import com.cashback.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DefaultProductService implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Flux<Product> findByCategoryPageableOrderByNameAsc(Category category, int pageNumber, int pageSize) {
        return this.productRepository.findByCategoryOrderByNameAsc(category, this.createPageable(pageNumber, pageSize));
    }

    private Pageable createPageable(int pageNumber, int pageSize) {
        return PageRequest.of(pageNumber, pageSize);
    }

    @Override
    public Mono<Product> findById(final String id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Mono<Product> save(Product product) {
        return this.productRepository.save(product);
    }
}
