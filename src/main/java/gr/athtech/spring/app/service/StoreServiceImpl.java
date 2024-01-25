package gr.athtech.spring.app.service;

import gr.athtech.spring.app.model.Order;
import gr.athtech.spring.app.model.Product;
import gr.athtech.spring.app.model.Store;
import gr.athtech.spring.app.model.StoreCategory;
import gr.athtech.spring.app.repository.BaseRepository;
import gr.athtech.spring.app.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl extends BaseServiceImpl<Store> implements StoreService {
    private final StoreRepository storeRepository;
    private final OrderService orderService;
    private final AddressService addressService;

    @Override
    protected BaseRepository<Store, Long> getRepository() {
        return storeRepository;
    }

    @Override
    public Store findByName(String name) {
        return storeRepository.findByName(name);
    }

    @Override
    public Store create(final Store store) {
        var address = store.getAddress();
        addressService.create(address);
        return storeRepository.create(store);
    }

    @Override
    public List<Store> findByStoreCategory(StoreCategory storeCategory) {
        return storeRepository.findByStoreCategory(storeCategory);
    }

    @Override
    public void changeSchedule(Long id, DayOfWeek day, LocalTime opening, LocalTime closing) {
        Store store = storeRepository.get(id);
        LocalTime[][] updatedHours = store.getSchedule();
        if (day == DayOfWeek.MONDAY) {
            updatedHours[0][0] = opening;
            updatedHours[0][1] = closing;
        } else if (day == DayOfWeek.TUESDAY) {
            updatedHours[1][0] = opening;
            updatedHours[1][1] = closing;
        } else if (day == DayOfWeek.WEDNESDAY) {
            updatedHours[2][0] = opening;
            updatedHours[2][1] = closing;
        } else if (day == DayOfWeek.THURSDAY) {
            updatedHours[3][0] = opening;
            updatedHours[3][1] = closing;
        } else if (day == DayOfWeek.FRIDAY) {
            updatedHours[4][0] = opening;
            updatedHours[4][1] = closing;
        } else if (day == DayOfWeek.SATURDAY) {
            updatedHours[5][0] = opening;
            updatedHours[5][1] = closing;
        } else if (day == DayOfWeek.SUNDAY) {
            updatedHours[6][0] = opening;
            updatedHours[6][1] = closing;
        }
        store.setSchedule(updatedHours);
        storeRepository.update(store);
    }

    @Override
    public void calculateStoreRating(Long id) {
        Store store = storeRepository.get(id);
        List<Order> orders = orderService.findAllStoreOrders(store);
        int sum = 0;
        for (Order o: orders) {
            sum = sum + o.getOrderRating();
        }

        double finalRating = (double) sum / orders.size();

        store.setStoreRating(finalRating);
        storeRepository.update(store);
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
