package gr.athtech.spring.app.service;

import gr.athtech.spring.app.model.*;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService extends BaseService<Order, Long> {
    Order initiateOrder(Account account, Store store);

    void addItem(Order order, Product product);

    void removeItem(Order order, Product product);

    void emptyOrder(Account account, Order order);

    void checkout(Order order, PaymentMethod paymentMethod, BigDecimal deliveryTip);

    void changeStatus(Order order, Status status);

    void rateOrder(Order order, Integer orderRating);

    List<Order> findAllAccountOrders(Account account);

    List<Order> findAllStoreOrders(Store store);
}
