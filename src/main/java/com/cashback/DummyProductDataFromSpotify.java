package com.cashback;

import com.cashback.client.spotify.ArtistData;
import com.cashback.client.spotify.ResponseData;
import com.cashback.client.spotify.SpopifyApiConfig;
import com.cashback.client.spotify.SpotifyApiClient;
import com.cashback.constants.Category;
import com.cashback.document.Product;
import com.cashback.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class DummyProductDataFromSpotify implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SpopifyApiConfig spopifyApiConfig;

    @Autowired
    private SpotifyApiClient spotifyClient;

    private Random random = new Random();

    @Override
    public void run(String... args) throws Exception {

        productRepository.deleteAll()
                         .thenMany(getProducts()
                                        .flatMap(productRepository::save))
                         .subscribe(System.out::println);
    }

    private Flux<Product> getProducts() {
        return Flux.fromStream(Arrays.stream(Category.values()).map(this::getProducts).flatMap(Collection::stream));
    }

    private List<Product> getProducts(Category category) {

        List<ArtistData> artists = this.getArtists(category);

        return artists.stream().map(this::createProduct).peek(product -> product.setCategory(category)).collect(Collectors.toList());
    }

    private List<ArtistData> getArtists(Category category) {

        ResponseData response = this.spotifyClient.getArtists(category.name(), spopifyApiConfig.getLimit());

        return response.getArtists().getItems();
    }

    private Product createProduct(ArtistData artist) {

        Product product = new Product();
        product.setName(artist.getName());
        product.setPrice(this.generatePrice());

        return product;
    }

    private Double generatePrice() {
        return new BigDecimal(random.nextDouble()*100).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
    }

}
