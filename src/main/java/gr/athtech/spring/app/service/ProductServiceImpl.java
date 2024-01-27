package gr.athtech.spring.app.service;

import gr.athtech.spring.app.model.Product;
import gr.athtech.spring.app.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService {
    private final ProductRepository productRepository;
    private final StoreService storeService;
    private final ProductCategoryService productCategoryService;

    @Override
    protected JpaRepository<Product, Long> getRepository() {
        return productRepository;
    }

    @Override
    public Product findByName(final String Name) {
        return productRepository.findByName(Name);
    }

    @Override
    public List<Product> findByProductCategory(Long productCategoryId) {

        return productRepository.findByProductCategoryId(productCategoryId);
    }

    @Override
    public Product create(final Product product, final Long storeId) {
        var store = storeService.get(storeId);
        var productCategory = product.getProductCategory();
        productCategoryService.create(productCategory);
        store.getProducts().add(product);
        storeService.update(store);
        return create(product);
    }
}
