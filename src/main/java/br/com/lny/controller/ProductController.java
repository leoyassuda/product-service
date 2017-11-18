package br.com.lny.controller;

import br.com.lny.model.ErrorInfo;
import br.com.lny.model.Product;
import br.com.lny.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProjectionFactory projectionFactory;

    @Autowired
    private ProductService productService;

    @Autowired
    public ProductController(ProjectionFactory projectionFactory) {
        this.projectionFactory = projectionFactory;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Product> getProducts() {
        return productService.list().stream().map(product -> projectionFactory.createProjection(Product.class, product)).collect(Collectors.toList());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable Long id) {
        return productService.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Product saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @RequestMapping(value = "/listWithAllProperties", method = RequestMethod.GET)
    public List<Product> listProductsWithAllProperties() {
        return productService.listProductsWithAllProperties();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    ErrorInfo
    handleBadRequest(HttpServletRequest req, Exception ex) {
        return new ErrorInfo(req.getRequestURL().toString(), ex);
    }
}
