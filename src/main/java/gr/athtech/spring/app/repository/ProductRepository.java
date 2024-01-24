package gr.athtech.spring.app.repository;

import gr.athtech.spring.app.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends BaseRepository<Product, Long> {
    Product findByName(final String name);

    List<Product> findByProductCategory(Long productCategoryId);
}
