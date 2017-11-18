package br.com.lny;

import br.com.lny.dao.ProductRepository;
import br.com.lny.model.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void persistProductRepositoryTest() throws Exception {

        Product product = new Product();
        product.setName("Product Test Persist");
        product.setDescription("Description Test Persist");

        this.entityManager.persist(product);
        List<Product> result = this.productRepository.findByName("Product Test Persist");

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("Product Test Persist");
        assertThat(result.get(0).getDescription()).isEqualTo("Description Test Persist");
    }

    @Test
    public void updateProductRepositoryTest() throws Exception {

        Product product = new Product();
        product.setName("Product Test Update");
        product.setDescription("Description Test Update");

        this.entityManager.persist(product);
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
    public void deleteProductRepositoryTest() throws Exception {

        Product product = new Product();
        product.setName("Product Test Delete");
        product.setDescription("Description Test Delete");

        this.entityManager.persist(product);
        List<Product> result = this.productRepository.findByName("Product Test Delete");

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("Product Test Delete");

        Product productDelete = result.get(0);
        this.productRepository.delete(productDelete.getId());

        result = this.productRepository.findByName("Product Test Delete");
        assertThat(result.isEmpty()).isEqualTo(true);

    }
}
