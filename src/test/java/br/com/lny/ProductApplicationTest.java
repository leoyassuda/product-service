package br.com.lny;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProductApplicationTest {

    private static final Logger logger = LogManager.getLogger(ProductApplicationTest.class);

    @Rule
    public OutputCapture output = new OutputCapture();

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testLogger() {

        logger.info("Hello World - Teste de log4j");
        this.output.expect(containsString("Hello World - Teste de log4j"));
    }

    public void validateLoggersEndpoint() throws Exception {
        this.mvc.perform(get("/application/loggers/org.apache.coyote.http11.Http11NioProtocol"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"configuredLevel\":\"WARN\","
                        + "\"effectiveLevel\":\"WARN\"}")));
    }

    @Test
    public void verifyStatus() {
        String body = this.restTemplate.getForObject("/", String.class);
        Assert.assertEquals(body.toUpperCase(), "OK");
    }

    @Test
    public void verifyGetProductSpecific() throws Exception {
        this.mvc.perform(get("/api/product/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"name\":\"Product 1\"")));
    }
}