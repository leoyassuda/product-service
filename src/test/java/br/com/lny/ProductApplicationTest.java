package br.com.lny;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
	public void init() throws MalformedURLException {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
		restTemplate = new TestRestTemplate("user", "password");
		base = new URL("http://localhost:" + port);
	}

	@Test
	public void whenLoggedUserRequestsHomePage_ThenSuccess()
			throws IllegalStateException, IOException {
		ResponseEntity<String> response = restTemplate.getForEntity(base.toString(), String.class);
//TODO: Fix test
//		assertEquals(HttpStatus.OK, response.getStatusCode());
//		assertTrue(Objects.requireNonNull(response.getBody()).contains("ok"));
	}

	@Test
	public void whenUserWithWrongCredentials_thenUnauthorizedPage() throws Exception {

		this.restTemplate = new TestRestTemplate("user", "wrongpassword");
		ResponseEntity<String> response = this.restTemplate.getForEntity(this.base.toString(), String.class);

		assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
		assertTrue(Objects.requireNonNull(response.getBody()).contains("unauthorized"));
	}

	@Test
	public void testLogger() {
		logger.info("Hello World - Teste de log4j");
//		this.ooutput.expect(containsString("Hello World - Teste de log4j"));
	}

	public void validateLoggersEndpoint() throws Exception {
		this.mvc.perform(get("/application/loggers/org.apache.coyote.http11.Http11NioProtocol"))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("{\"configuredLevel\":\"WARN\","
						+ "\"effectiveLevel\":\"WARN\"}")));
	}

	@Test
	public void verifyStatus() {
//		String body = this.restTemplate.getForObject(this.base.toString(), String.class);
//		base = new URL("http://localhost:" + port);
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
		restTemplate = new TestRestTemplate("user", "password");
//		restTemplate.getForObject(this.base.toString(), String.class);
		String body = this.restTemplate.getForObject(this.base.toString(), String.class);
		//TODO: fix test
//		assertEquals(body.toUpperCase(), "OK");
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