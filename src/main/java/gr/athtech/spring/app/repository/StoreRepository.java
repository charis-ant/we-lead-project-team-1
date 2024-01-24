package gr.athtech.spring.app.repository;

import gr.athtech.spring.app.model.Store;
import gr.athtech.spring.app.model.StoreCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends BaseRepository<Store, Long> {
    Store findByName(String name);

    List<Store> findByStoreCategory(StoreCategory storeCategory);

    //void changeSchedule(Store store, DayOfWeek day, LocalTime opening, LocalTime closing);
}
