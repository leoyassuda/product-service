package br.com.lny.controller;

import br.com.lny.model.ErrorInfo;
import br.com.lny.model.ImageFilter;
import br.com.lny.model.Product;
import br.com.lny.model.ProductFilter;
import br.com.lny.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    private ProductFilter productFilter = new ProductFilter();
    private ImageFilter imageFilter = new ImageFilter();

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getProducts() throws JsonProcessingException {
        FilterProvider filters = new SimpleFilterProvider().addFilter("productFilter", productFilter.getBasicProperties());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writer(filters).writeValueAsString(productService.list());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public String getProduct(@PathVariable Long id) throws JsonProcessingException {
        FilterProvider filters = new SimpleFilterProvider().addFilter("productFilter", productFilter.getBasicProperties());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writer(filters).writeValueAsString(productService.findById(id));
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public String saveProduct(@RequestBody Product product) throws JsonProcessingException {
        FilterProvider filters = new SimpleFilterProvider().addFilter("productFilter", productFilter.getBasicProperties());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writer(filters).writeValueAsString(productService.saveProduct(product));
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public String updateProduct(@RequestBody Product product) throws JsonProcessingException {
        FilterProvider filters = new SimpleFilterProvider().addFilter("productFilter", productFilter.getBasicProperties());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writer(filters).writeValueAsString(productService.updateProduct(product));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @RequestMapping(value = "/listWithAllProperties", method = RequestMethod.GET, produces = "application/json")
    public String listProductsWithAllProperties() throws JsonProcessingException {
        FilterProvider filters = new SimpleFilterProvider().addFilter("productFilter", productFilter.getAllProperties())
                .addFilter("imageFilter", imageFilter.getAllProperties());
        ObjectMapper mapper = new ObjectMapper();

        List<Product> list = productService.listProductsWithAllProperties();

        return mapper.writer(filters).writeValueAsString(list);
    }

    @RequestMapping(value = "/{id}/childrenBasic", method = RequestMethod.GET, produces = "application/json")
    public String getChildrenBasic(@PathVariable Long id) throws JsonProcessingException {
        FilterProvider filters = new SimpleFilterProvider().addFilter("productFilter", productFilter.getBasicProperties());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writer(filters).writeValueAsString(productService.findChildren(id));
    }

    @RequestMapping(value = "/{id}/childrenAllProperties", method = RequestMethod.GET, produces = "application/json")
    public String getChildrenAllProperties(@PathVariable Long id) throws JsonProcessingException {
        FilterProvider filters = new SimpleFilterProvider().addFilter("productFilter", productFilter.getAllProperties())
                .addFilter("imageFilter", imageFilter.getAllProperties());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writer(filters).writeValueAsString(productService.findChildren(id));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    ErrorInfo
    handleBadRequest(HttpServletRequest req, Exception ex) {
        return new ErrorInfo(req.getRequestURL().toString(), ex);
    }

}
