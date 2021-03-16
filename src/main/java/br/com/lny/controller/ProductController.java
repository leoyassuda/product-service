package br.com.lny.controller;

import br.com.lny.model.Product;
import br.com.lny.service.ProductService;
import io.netty.handler.codec.http.HttpResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Flux<Product> getProducts() {
        log.info("ProductController#getProducts: get all products");
        return productService.getProducts();
    }

    @PostMapping
    public String create() {
        productService.save();
        return "aaa";
    }
}
