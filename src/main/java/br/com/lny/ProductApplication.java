package br.com.lny;

import br.com.lny.model.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@SpringBootApplication
public class ProductApplication {

    private final AtomicInteger counter = new AtomicInteger();

    private final String[] names = "Monitor,TV,Mobile,Notebook,PC".split(",");

    private final Flux<Product> productFlux = Flux.fromStream(
            Stream.generate(() -> {
                var id = UUID.randomUUID().toString();
                return Product.builder()
                        .id(id)
                        .name(names[counter.incrementAndGet() % names.length])
                        .description("It is a test")
                        .build();
            })
    ).delayElements(Duration.ofSeconds(3));

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

    @Bean
    Flux<Product> products(){
        return this.productFlux.publish().autoConnect();
    }

}