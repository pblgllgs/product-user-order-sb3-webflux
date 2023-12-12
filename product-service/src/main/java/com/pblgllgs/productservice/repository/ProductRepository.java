package com.pblgllgs.productservice.repository;

import com.pblgllgs.productservice.entity.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

    Flux<Product> findProductsByPriceBetween(Integer min, Integer max);
}
