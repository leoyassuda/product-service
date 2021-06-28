package br.com.lny.repository;

import br.com.lny.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

    Flux<Product> findAllByName(String name);

    Mono<Product> findFirstByName(String name);
}
