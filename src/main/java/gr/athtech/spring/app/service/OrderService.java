package gr.athtech.spring.app.service;

import gr.athtech.spring.app.model.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface OrderService extends BaseService<Order, Long> {
    Order initiateOrder(Account account, Store store);

    void addItem(final Order order, final Product product, final int quantity);

    void removeItem(Order order, Product product);

    void emptyOrder(Account account, Order order);

    void checkout(Order order, PaymentMethod paymentMethod, BigDecimal deliveryTip);

    void changeStatus(Order order, Status status);

    void rateOrder(Order order, Integer orderRating);

    void updateItem(Order order, Product product, int quantity);

    Optional<Order> findByAccount(Account account);

    List<Order> findByStore(Store store);

}
