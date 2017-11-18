package br.com.lny.model;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "imageFull", types = {Image.class})
public interface ImageFull {
    Product getProduct();
}
