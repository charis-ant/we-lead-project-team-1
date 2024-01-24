package gr.athtech.spring.app.service;

import gr.athtech.spring.app.model.Product;
import gr.athtech.spring.app.repository.BaseRepository;
import gr.athtech.spring.app.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService {
    private final ProductRepository productRepository;

    @Override
    protected BaseRepository<Product, Long> getRepository() {
        return productRepository;
    }

    @Override
    public Product findByName(final String Name) {
        return productRepository.findByName(Name);
    }
}
