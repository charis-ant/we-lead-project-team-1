package gr.athtech.spring.app.repository;


import gr.athtech.spring.app.model.Product;
import org.springframework.stereotype.Repository;

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
}
