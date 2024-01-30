package gr.athtech.spring.app.repository;

import gr.athtech.spring.app.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByAccountId(Long accountId);
    List<Order> findByStoreId(Long storeId);
}
