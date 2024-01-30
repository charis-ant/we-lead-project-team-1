package gr.athtech.spring.app.service;

import gr.athtech.spring.app.model.Store;
import gr.athtech.spring.app.model.StoreCategory;

import java.util.List;

public interface StoreService extends BaseService<Store, Long> {
    Store findByName(String name);

    List<Store> findByStoreCategory(StoreCategory storeCategory);

//    List<Store> findMostFamousStores();
//
//    List<Store> findMostFamousStoresByStoreCategory(StoreCategory storeCategory);

    void calculateStoreRating(Long storeId);
}
