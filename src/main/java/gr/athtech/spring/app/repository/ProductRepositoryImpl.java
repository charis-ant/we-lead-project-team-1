package gr.athtech.spring.app.repository;


import gr.athtech.spring.app.model.Order;
import gr.athtech.spring.app.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepositoryImpl extends BaseRepositoryImpl<Product> implements ProductRepository {
    private final ConcurrentHashMap<Long, Product> storage = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);

    @Override
    protected ConcurrentHashMap<Long, Product> getStorage() {
        return storage;
    }

    @Override
    protected AtomicLong getSequence() {
        return sequence;
    }

    @Override
    public Product findByName(final String name) {
        return storage.values()
                .stream()
                .filter(c -> name.equalsIgnoreCase(c.getName()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Product> findByProductCategory(Long productCategoryId) {
        List<Product> products = storage.values()
                .stream()
                .filter(p -> productCategoryId.equals(p.getProductCategory().getId()))
                .toList();

        return products.isEmpty() ? null : products;
    }
}
