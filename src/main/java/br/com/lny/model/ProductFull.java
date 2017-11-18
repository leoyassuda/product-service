package br.com.lny.model;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "productFull", types = {Product.class})
public interface ProductFull {

    Product getParent();

}
