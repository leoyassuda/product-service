package br.com.lny.controller;

import br.com.lny.model.Product;
import br.com.lny.service.ProductService;
import io.netty.handler.codec.http.HttpResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("products")
public class ProductController {

    private final Flux<Product> productFlux;

    private final ProductService productService;

    public Flux<Product> getProducts() {
        log.info("ProductController#getProducts: get all products");
        return productService.getProducts();
    }

    @GetMapping
    public Flux<Product> getProducts(@RequestParam(required = false, name = "name") final String name) {
        if (StringUtils.isEmpty(name))
            return this.getProducts();

        log.info("ProductController#getProductsByName: name=" + name);
        return productService.getByName(name);
    }

    @GetMapping("{id}")
    public Mono<Product> getById(@PathVariable("id") final String id) {
        log.info("ProductController#getById: id=" + id);
        return productService.getById(id);
    }

    @PutMapping("{id}")
    public Mono<Product> updateById(@PathVariable("id") final String id, @RequestBody final Product product) {
        log.info("ProductController#updateById: id=" + id);
        return productService.update(id, product);
    }

    @DeleteMapping("{id}")
    public Mono<Void> delete(@PathVariable final String id) {
        log.info("ProductController#delete: id=" + id);
        return productService.delete(id);
    }

    @PostMapping
    public Mono<Product> create(@RequestBody final Product product) {
        return productService.save(product);
    }

    @GetMapping(
            produces = MediaType.TEXT_EVENT_STREAM_VALUE,
            value = "/ws"
    )
    Flux<Product> getWSProducts() {
        return this.productFlux;
    }
}
