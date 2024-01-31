package gr.athtech.spring.app.repository;

import gr.athtech.spring.app.model.Store;
import gr.athtech.spring.app.model.StoreCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    Store findByName(String name);

    List<Store> findByStoreCategory(StoreCategory storeCategory);

//    List<Store> findMostFamousStores();
//    List<Store> findMostFamousStoresByStoreCategory(StoreCategory storeCategory);
}
