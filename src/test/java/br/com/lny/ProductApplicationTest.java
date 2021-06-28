package br.com.lny;

import br.com.lny.model.Product;
import br.com.lny.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class ProductApplicationTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void whenStart_thenNoExceptions() {
    }

    @Test
    void testSaveData() {
        productRepository.save(
                Product.builder()
                        .name("Test A")
                        .description("Description A")
                        .build())
                .block();

        Mono<Product> productResult = productRepository.findFirstByName("Test A");

        StepVerifier
                .create(productResult)
                .assertNext(product -> {
                    assertEquals("Test A", product.getName());
                    assertEquals("Description A", product.getDescription());
                    assertNotNull(product.getId());
                })
                .expectComplete()
                .verify();
    }

}