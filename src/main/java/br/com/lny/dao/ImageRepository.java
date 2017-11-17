package br.com.lny.dao;

import br.com.lny.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    @Query(value = "select image from Image image join fetch image.product as products")
    List<Image> listImagesWithAllProperties();
}
