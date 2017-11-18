package br.com.lny.service;

import br.com.lny.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> list();

    Product findById(Long id);

    Product saveProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(Long id);

    List<Product> listProductsWithAllProperties();

    List<Product> findChildren(Long id);
}
