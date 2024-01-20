package gr.athtech.spring.app.service;

import gr.athtech.spring.app.model.ProductCategory;

public interface ProductCategoryService extends BaseService<ProductCategory, Long> {
    ProductCategory findByName(String name);
}
