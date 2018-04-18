package br.com.lny;

import br.com.lny.dao.ProductRepository;
import br.com.lny.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void persistProductRepositoryTest() {

        Product product = new Product();
        product.setName("Product Test Persist");
        product.setDescription("Description Test Persist");

        this.productRepository.save(product);
        this.productRepository.flush();
        List<Product> result = this.productRepository.findByName("Product Test Persist");

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("Product Test Persist");
        assertThat(result.get(0).getDescription()).isEqualTo("Description Test Persist");
    }

    @Test
    public void updateProductRepositoryTest() {

        Product product = new Product();
        product.setName("Product Test Update");
        product.setDescription("Description Test Update");

        this.productRepository.save(product);
        List<Product> result = this.productRepository.findByName("Product Test Update");

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("Product Test Update");

        Product productUpdate = result.get(0);
        productUpdate.setName("Product Test Update A");
        this.productRepository.save(productUpdate);

        result = this.productRepository.findByName("Product Test Update A");
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("Product Test Update A");

    }

    @Test
    public void deleteProductRepositoryTest() {

        Product product = new Product();
        product.setName("Product Test Delete");
        product.setDescription("Description Test Delete");

        this.productRepository.save(product);
        List<Product> result = this.productRepository.findByName("Product Test Delete");

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("Product Test Delete");

        Product productDelete = result.get(0);
        this.productRepository.delete(productDelete);

        result = this.productRepository.findByName("Product Test Delete");
        assertThat(result.isEmpty()).isEqualTo(true);

    }
}
