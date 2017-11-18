package br.com.lny.service;

import br.com.lny.model.Image;

import java.util.List;

public interface ImageService {
    List<Image> list();

    Image findById(Long id);

    Image saveImage(Image image);

    Image updateImage(Image image);

    void deleteImage(Long id);

    List<Image> listImagesWithAllProperties();

    List<Image> findByIdProduct(Long idProduct);
}
