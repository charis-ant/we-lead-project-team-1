package gr.athtech.spring.app.repository;

import gr.athtech.spring.app.model.ProductCategory;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductCategoryRepositoryImpl extends BaseRepositoryImpl<ProductCategory> implements ProductCategoryRepository {
    private final ConcurrentHashMap<Long, ProductCategory> storage = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);

    @Override
    protected ConcurrentHashMap<Long, ProductCategory> getStorage() {
        return storage;
    }

    @Override
    protected AtomicLong getSequence() {
        return sequence;
    }

    @Override
    public ProductCategory findByName(final String name) {
        return storage.values()
                .stream()
                .filter(c -> name.equalsIgnoreCase(c.getName()))
                .findFirst()
                .orElse(null);
    }
}
