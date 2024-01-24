package gr.athtech.spring.app.repository;

import gr.athtech.spring.app.model.Product;
import gr.athtech.spring.app.model.Store;
import gr.athtech.spring.app.model.StoreCategory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class StoreRepositoryImpl extends BaseRepositoryImpl<Store> implements StoreRepository {
    private final ConcurrentHashMap<Long, Store> storage = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);
    @Override
    protected ConcurrentHashMap<Long, Store> getStorage() {
        return storage;
    }

    @Override
    protected AtomicLong getSequence() {
        return sequence;
    }

    @Override
    public Store findByName(String name) {
        return storage.values()
                .stream()
                .filter(s -> name.equalsIgnoreCase(s.getName()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Store> findByStoreCategory(StoreCategory storeCategory) {
        List<Store> stores = storage.values()
                .stream()
                .filter(s -> s.getStoreCategories().contains(storeCategory))
                .toList();

        return stores.isEmpty() ? null : stores;
    }

    //changeSchedule

}
