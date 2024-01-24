package gr.athtech.spring.app.service;


import gr.athtech.spring.app.model.Product;

import java.util.List;

public interface ProductService extends BaseService<Product, Long> {
    Product findByName(String name);

    List<Product> findByProductCategory(Long productCategoryId);

    //Product create(Product product, Long storeId);
}
