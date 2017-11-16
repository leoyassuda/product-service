package br.com.lny.controller;

import br.com.lny.model.Product;
import br.com.lny.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Product> getProducts(){
        return productService.list();
    }

    @RequestMapping(value = "/{id}" ,method = RequestMethod.GET)
    public Product getProduct(@PathVariable Long id){
        return productService.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Product saveProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Product updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }

    @RequestMapping(value = "/{id}" ,method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }

    @RequestMapping(value = "/listWithAllProperties" ,method = RequestMethod.GET)
    public List<Product> listProductsWithAllProperties(){
        return productService.listProductsWithAllProperties();
    }


}
