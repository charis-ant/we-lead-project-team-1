package gr.athtech.spring.app.repository;

import gr.athtech.spring.app.model.Account;
import gr.athtech.spring.app.model.Order;
import gr.athtech.spring.app.model.Store;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class OrderRepositoryImpl extends BaseRepositoryImpl<Order> implements OrderRepository {
    private final ConcurrentHashMap<Long, Order> storage = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);

    @Override
    protected ConcurrentHashMap<Long, Order> getStorage() {
        return storage;
    }

    @Override
    protected AtomicLong getSequence() {
        return sequence;
    }

    @Override
    public List<Order> findAllStoreOrders(Store store) {
        List<Order> orders = storage.values()
                .stream()
                .filter(o -> store.equals(o.getStore()))
                .toList();

        return orders.isEmpty() ? null : orders;
    }

    @Override
    public List<Order> findAllAccountOrders(Account account) {
        List<Order> orders = storage.values()
                .stream()
                .filter(o -> account.equals(o.getAccount()))
                .toList();

        return orders.isEmpty() ? null : orders;
    }
}
