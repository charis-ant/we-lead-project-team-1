package gr.athtech.spring.app.service;


import gr.athtech.spring.app.model.Product;
public interface ProductService extends BaseService<Product, Long> {
    Product findByName(String name);
}
