package gr.athtech.spring.app.repository;

import gr.athtech.spring.app.model.ProductCategory;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends BaseRepository<ProductCategory, Long> {
    ProductCategory findByName(String name);
}
