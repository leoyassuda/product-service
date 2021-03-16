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

    public Mono<Product> getByName(String name) {
        return productRepository.findFirstByName(name);
    }

    public void save() {
        productRepository.save(
                Product.builder()
                        .name("notebook")
                        .description("aaaa")
                        .build())
        .subscribe();
    }
}

