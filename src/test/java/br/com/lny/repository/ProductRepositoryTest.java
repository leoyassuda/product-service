package br.com.lny.repository;

import br.com.lny.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataMongoTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void dataSetup() {
        productRepository.save(
                Product.builder()
                        .name("tv")
                        .description("Start TV")
                        .build())
                .subscribe();
    }

    @DisplayName("given name to find a product"
            + " when find product using MongoDB template"
            + " then product is returned")
    @Test
    void givenName_whenFindByName_thenReturnProduct() {

        var product = productRepository.findFirstByName("tv").block();

        assert product != null;
        Assertions.assertEquals("tv", product.getName());

    }

}