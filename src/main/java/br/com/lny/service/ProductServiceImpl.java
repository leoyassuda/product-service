package br.com.lny.service;

import br.com.lny.dao.ProductRepository;
import br.com.lny.model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private static final Logger logger = LogManager.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> list() {
        logger.info("Obtaining all products ...");
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        logger.info("Finding a product ...");
        return productRepository.findOne(id);
    }

    @Override
    public Product saveProduct(Product product) {
        logger.info("Saving a product ...");
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        logger.info("Finding a product ...");
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        logger.info("Deleting a product ...");
        productRepository.delete(id);
    }

    @Override
    public List<Product> listProductsWithAllProperties() {
        logger.info("Obtaining all products...");
        return productRepository.listProductsWithAllProperties();
    }


}
