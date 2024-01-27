package gr.athtech.spring.app.service;

import gr.athtech.spring.app.model.Order;
import gr.athtech.spring.app.model.Store;
import gr.athtech.spring.app.model.StoreCategory;
import gr.athtech.spring.app.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl extends BaseServiceImpl<Store> implements StoreService {
    private final StoreRepository storeRepository;
    private final OrderService orderService;

    @Override
    protected JpaRepository<Store, Long> getRepository() {
        return storeRepository;
    }

    @Override
    public Store findByName(String name) {
        return storeRepository.findByName(name);
    }

    @Override
    public List<Store> findByStoreCategory(StoreCategory storeCategory) {
        return storeRepository.findByStoreCategory(storeCategory);
    }

    @Transactional
    @Override
    public void calculateStoreRating(Long id) {
        Store store = get(id);
        List<Order> orders = orderService.findAllStoreOrders(store);
        int sum = 0;
        for (Order o: orders) {
            sum = sum + o.getOrderRating();
        }

        double finalRating = (double) sum / orders.size();

        store.setStoreRating(finalRating);
        update(store);
    }

    @Override
    public List<Store> findMostFamousStores() {
        List<Store> stores = storeRepository.findAll();
        stores.sort(Comparator.comparingDouble(Store::getStoreRating).reversed());
        int topCount = Math.min(5, stores.size());
        return stores.subList(0, topCount);
    }

    @Override
    public List<Store> findMostFamousStoresByCategory(StoreCategory storeCategory) {
        List<Store> stores = storeRepository.findByStoreCategory(storeCategory);
        stores.sort(Comparator.comparingDouble(Store::getStoreRating).reversed());
        int topCount = Math.min(5, stores.size());
        return stores.subList(0, topCount);
    }
}
