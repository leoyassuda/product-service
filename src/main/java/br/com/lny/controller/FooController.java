package br.com.lny.controller;

import br.com.lny.model.Product;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/foo")
public class FooController {

	private static final Logger logger = LogManager.getLogger(FooController.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private Environment env;

	@RequestMapping("/")
	public String home() {
		// This is useful for debugging
		// When having multiple instance of gallery service running at different ports.
		// We load balance among them, and display which instance received the request.
		return "Hello from Product Foo Service running at port: " + env.getProperty("local.server.port");
	}

	@HystrixCommand(fallbackMethod = "fallback")
	@RequestMapping("/{id}")
	public Product getProduct(@PathVariable final long id) {
		logger.info("Creating product object ... ");

		// create product object
		Product product = new Product();
		product.setId(id);

		// get list of available images
		// @SuppressWarnings("unchecked")    // we'll throw an exception from image service to simulate a failure
//		List<Object> images = restTemplate.getForObject("http://image-service/images/", List.class);
//		product.setImages(images);

		return product;
	}

	// -------- Admin Area --------
	// This method should only be accessed by users with role of 'admin'
	// We'll add the logic of role based auth later
	@RequestMapping("/admin")
	public String homeAdmin() {
		return "This is the admin area of Product Foo service running at port: " + env.getProperty("local.server.port");
	}

	// a fallback method to be called if failure happened
	public Product fallback(long productId, Throwable hystrixCommand) {
		return new Product(productId);
	}


}
