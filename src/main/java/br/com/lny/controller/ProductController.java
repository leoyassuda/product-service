package br.com.lny.controller;

import br.com.lny.model.Product;
import br.com.lny.service.ProductService;
import io.netty.handler.codec.http.HttpResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("products")
public class ProductController {

    private final Flux<Product> productFlux;

    private final ProductService productService;


    @GetMapping
    public Flux<Product> getProducts() {
        log.info("ProductController#getProducts: get all products");

        return productService.getProducts();
    }

    @PostMapping
    public Mono<HttpResponseStatus> create() {
        productService.save();
        return Mono.just(HttpResponseStatus.CREATED);
    }

    @GetMapping(
            produces = MediaType.TEXT_EVENT_STREAM_VALUE,
            value = "/ws"
    )
    Flux<Product> getWSProducts() {
        return this.productFlux;
    }
}
