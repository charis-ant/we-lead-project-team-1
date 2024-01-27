package gr.athtech.spring.app.service;

import gr.athtech.spring.app.model.ProductCategory;
import gr.athtech.spring.app.repository.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl extends BaseServiceImpl<ProductCategory> implements ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;

    @Override
    protected JpaRepository<ProductCategory, Long> getRepository() {
        return productCategoryRepository;
    }

    @Override
    public ProductCategory findByName(final String name) {
        return productCategoryRepository.findByName(name);
    }
}
