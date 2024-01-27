package gr.athtech.spring.app.repository;

import gr.athtech.spring.app.model.Account;
import gr.athtech.spring.app.model.Order;
import gr.athtech.spring.app.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query
    Optional<Order> findAllAccountOrders(Account account);

    @Query
    List<Order> findAllStoreOrders(Store store);
}