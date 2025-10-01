package com.ankit.webflux.dao;

import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import com.ankit.webflux.dto.Product;
import com.ankit.webflux.dto.ProductDto;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

  Flux<ProductDto> findByPriceBetween(Range<Double> closed);

}
