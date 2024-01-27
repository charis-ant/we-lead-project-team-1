package gr.athtech.spring.app.repository;

import gr.athtech.spring.app.model.Product;
import gr.athtech.spring.app.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);

    @Query
    List<Product> findByProductCategoryId(Long productCategoryId);
}