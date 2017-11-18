package br.com.lny.dao;

import br.com.lny.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select DISTINCT p from Product p left join fetch p.children as c join fetch p.images as i order by p.id")
    List<Product> listProductsWithAllProperties();

    @Query(value = "select DISTINCT children from Product children where children.parent.id = :id")
    List<Product> listChildren(@Param("id") Long id);

    List<Product> findByName(String name);
}
