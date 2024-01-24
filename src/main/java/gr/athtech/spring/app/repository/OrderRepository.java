package gr.athtech.spring.app.repository;

import gr.athtech.spring.app.model.Account;
import gr.athtech.spring.app.model.Order;
import gr.athtech.spring.app.model.Store;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends BaseRepository<Order, Long>{

    List<Order> findAllAccountOrders(Account account);

    List<Order> findAllStoreOrders(Store store);
}
