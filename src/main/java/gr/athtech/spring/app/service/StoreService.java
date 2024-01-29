package gr.athtech.spring.app.service;

import gr.athtech.spring.app.model.Store;
import gr.athtech.spring.app.model.StoreCategory;

import java.util.List;

public interface StoreService extends BaseService<Store, Long> {
    Store findByName(String name);

    Store create(Store store);

    List<Store> findByStoreCategory(StoreCategory storeCategory);

    void calculateStoreRating(Long id);

    List<Store> findMostFamousStores();

    List<Store> findMostFamousStoresByCategory(StoreCategory storeCategory);
}
