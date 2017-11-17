package br.com.lny.service;

import br.com.lny.dao.ImageRepository;
import br.com.lny.model.Image;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    private static final Logger logger = LogManager.getLogger(ImageServiceImpl.class);

    @Autowired
    private ImageRepository imageRepository;

    @Transactional
    @Override
    public List<Image> list() {
        logger.info("Obtaining all images ...");
        return imageRepository.findAll();
    }

    @Override
    public Image findById(Long id) {
        logger.info("Finding a image ...");
        return imageRepository.findOne(id);
    }

    @Override
    public Image saveImage(Image image) {
        logger.info("Saving a image ...");
        return imageRepository.save(image);
    }

    @Override
    public Image updateImage(Image image) {
        logger.info("Updating a image ...");
        return imageRepository.save(image);
    }

    @Override
    public void deleteImage(Long id) {
        logger.info("Deleting a image ...");
        imageRepository.delete(id);
    }

    @Transactional
    @Override
    public List<Image> listImagesWithAllProperties() {
        logger.info("Obtaining all images...");
        return imageRepository.listImagesWithAllProperties();
    }
}