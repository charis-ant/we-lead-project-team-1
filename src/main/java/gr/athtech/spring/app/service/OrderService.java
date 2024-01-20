package gr.athtech.spring.app.service;

import gr.athtech.spring.app.model.*;

import java.math.BigDecimal;

public interface OrderService extends BaseService<Order, Long> {
    Order initiateOrder(Account account);

    void addItem(Order order, Product product);

    void removeItem(Order order, Product product);

    void emptyOrder(Account account, Order order);

    Order checkout(Order order, PaymentMethod paymentMethod, BigDecimal deliveryTip);

    void changeStatus(Order order, Status status);

    void rateOrder(Order order, Integer orderRating);
}
