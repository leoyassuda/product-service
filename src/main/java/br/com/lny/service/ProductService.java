package br.com.lny.service;

import br.com.lny.model.Product;
import br.com.lny.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Flux<Product> getProducts() {
        return productRepository.findAll();
    }

    public Flux<Product> getByName(String name) {
        return productRepository.findAllByName(name);
    }

    public Mono<Product> save(final Product product) {
        return productRepository.save(product);
    }

    public Mono<Product> getById(String id) {
        return productRepository.findById(id);
    }

    public Mono<Product> update(String id, final Product product) {
        return productRepository.save(product);
    }

    public Mono<Void> delete(String id) {
        return productRepository.deleteById(id);
    }
}

