package br.com.lny;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"spring.jmx.enabled:true", "spring.datasource.jmx-enabled:true"})
@ActiveProfiles("test")
public class ProductApplicationTest {

	private static final Logger logger = LogManager.getLogger(ProductApplicationTest.class);

	@Rule
	public OutputCapture output = new OutputCapture();

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private TestRestTemplate restTemplate;

	private URL base;

	@LocalServerPort
	int port;

	@Before
	public void setUp() throws MalformedURLException {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
		restTemplate = new TestRestTemplate("user", "password");
		base = new URL("http://localhost:" + port);
	}

	@Test
	public void whenLoggedUserRequestsHomePage_ThenSuccess()
			throws IllegalStateException, IOException {
		ResponseEntity<String> response = restTemplate.getForEntity(base.toString(), String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.getBody().contains("ok"));
	}

	@Test
	public void whenUserWithWrongCredentials_thenUnauthorizedPage() throws Exception {

		this.restTemplate = new TestRestTemplate("user", "wrongpassword");
		ResponseEntity<String> response = this.restTemplate.getForEntity(this.base.toString(), String.class);

		assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
		assertTrue(Objects.requireNonNull(response.getBody()).contains("Unauthorized"));
	}

	@Test
	public void testLogger() {
		logger.info("Hello World - Teste de log4j");
		this.output.expect(containsString("Hello World - Teste de log4j"));
	}

	@Ignore
	public void validateLoggersEndpoint() throws Exception {
		this.mvc.perform(get("/application/loggers/org.apache.coyote.http11.Http11NioProtocol"))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("{\"configuredLevel\":\"WARN\","
						+ "\"effectiveLevel\":\"WARN\"}")));
	}

	@Test
	public void verifyStatus() {
		String body = this.restTemplate.getForObject(this.base.toString(), String.class);
		assertEquals(body.toUpperCase(), "OK");
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