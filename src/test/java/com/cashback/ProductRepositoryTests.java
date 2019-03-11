package com.cashback;

import com.cashback.document.Product;
import com.cashback.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

    private static final String PRODUCT_NAME = "Nirvana";

    @Test
    public void shouldSaveProduct() {

        productRepository.save(this.createProduct()).block();

        Flux<Product> productFlux = productRepository.findAll();

        StepVerifier
                .create(productFlux)
                .assertNext(product -> {
                    Assert.assertEquals(PRODUCT_NAME, product.getName());
                    Assert.assertNotNull(product.getId());
                })
                .expectComplete()
                .verify();
    }

    private Product createProduct() {

        Product product = new Product();
        product.setName(PRODUCT_NAME);

        return product;
    }
}
