package br.com.lny;

import br.com.lny.config.MongoConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MongoConfig.class)
public class ProductApplicationTest {

    @Test
    void whenStart_thenNoExceptions(){}

}