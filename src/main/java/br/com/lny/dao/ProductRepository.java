package br.com.lny.dao;

import br.com.lny.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select product from Product product join fetch product.children as children join fetch product.images as images")
    List<Product> listProductsWithAllProperties();
}
