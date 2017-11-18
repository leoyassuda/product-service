package br.com.lny.dao;

import br.com.lny.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    @Query(value = "select image from Image image left join fetch image.product as products")
    List<Image> listImagesWithAllProperties();

    @Query(value = "select image from Image image join fetch image.product as product where product.id = :idProduct")
    List<Image> listByIdProduct(@Param("idProduct") Long idProduct);
}
