package gr.athtech.spring.app.service;

import gr.athtech.spring.app.model.*;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService extends BaseService<Order, Long> {
    Order initiateOrder(Account account, Store store);

    void addItem(final Order order, final Product product, final int quantity);

    void updateItem(Order order, Product product, int quantity);

    void removeItem(Order order, Product product);

    void checkout(Order order, PaymentMethod paymentMethod, BigDecimal deliveryTip);

    void changeStatus(Long orderId, Status status);

    void rateOrder(Long orderId, Integer orderRating);

    List<Order> findByAccountId(Long accountId);

    List<Order> findByStoreId(Long storeId);

}
