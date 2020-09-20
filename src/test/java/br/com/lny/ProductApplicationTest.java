package br.com.lny;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(TraceUnitExtension.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductApplicationTest {

    private static final Logger logger = LogManager.getLogger(ProductApplicationTest.class);

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private TestRestTemplate restTemplate;

    private URL base;

    @LocalServerPort
    int port;

    @BeforeEach
    void init() throws MalformedURLException {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        this.base = new URL("http://localhost:" + port);
    }

    // TODO: verificar log level
    @Disabled
    public void validateLoggersEndpoint() throws Exception {
        this.mvc.perform(get("/application/loggers/org.apache.coyote.http11.Http11NioProtocol"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"configuredLevel\":\"WARN\","
                        + "\"effectiveLevel\":\"WARN\"}")));
    }

    @Test
    public void verifyHealth() throws Exception {
        this.mvc.perform(get("/actuator/health"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"status\":\"UP\"")));
    }

    @Test
    public void verifyGetProductSpecific() throws Exception {
        this.mvc.perform(get("/api/product/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"name\":\"Product 1\"")));
    }
}