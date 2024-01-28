package gr.athtech.spring.app.service;

import gr.athtech.spring.app.model.Order;
import gr.athtech.spring.app.model.OrderItem;
import gr.athtech.spring.app.model.Account;
import gr.athtech.spring.app.model.Status;
import gr.athtech.spring.app.model.PaymentMethod;
import gr.athtech.spring.app.model.Product;
import gr.athtech.spring.app.model.Store;
import gr.athtech.spring.app.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    protected JpaRepository<Order, Long> getRepository() {
        return orderRepository;
    }

    @Override
    public Order initiateOrder(final Account account, final Store store) {
        return Order.builder().account(account).store(store).build();
    }

    @Override
    public void addItem(final Order order, final Product product, final int quantity) {
        if (checkNullability(order, product)) {
            return;
        }

        boolean increasedQuantity = false;

        // If product is already contained in the order, don't add it again, just increase the quantity accordingly
        for (OrderItem oi : order.getOrderItems()) {
            if (oi.getProduct().getId().equals(product.getId())) {
                oi.setQuantity(oi.getQuantity() + quantity);
                increasedQuantity = true;
                break;
            }
        }

        if (!increasedQuantity) {
            order.getOrderItems().add(newOrderItem(order, product, quantity));
        }

        logger.trace("Product[{}] added to Order[{}]", product, order);
    }

    @Override
    public void updateItem(final Order order, final Product product, final int quantity) {
        if (checkNullability(order, product)) {
            return;
        }

        order.getOrderItems().removeIf(oi -> oi.getProduct().getId().equals(product.getId()));
        order.getOrderItems().add(newOrderItem(order, product, quantity));

        logger.trace("Product[{}] updated in Order[{}]", product, order);
    }


    @Override
    public void removeItem(Order order, Product product) {
        if (checkNullability(order, product)) {
            return;
        }

        order.getOrderItems().removeIf(oi -> oi.getProduct().getId().equals(product.getId()));

        logger.info("Product removed from Order");
    }

    @Override
    public void emptyOrder(Account account, Order order) {
        if (order.getAccount().equals(account)) {
            orderRepository.delete(order);
        }
        //orderRepository.deleteById(order.getId());
    }

    @Override
    public void checkout(final Order order, final PaymentMethod paymentMethod, final BigDecimal deliveryTip) {

        if (!validate(order)) {
            logger.warn("Order should have account, products, and payment method defined, before being able to " +
                    "checkout the order.");
        }

        BigDecimal total = BigDecimal.ZERO;

        for (OrderItem o : order.getOrderItems()) {
            BigDecimal price = o.getPrice();
            total = total.add(price);
        }

        order.setTotal(total);

        //Check if order cost is above minimum order value
        if (order.getTotal().compareTo(order.getStore().getMinimumOrderPrice()) < 0) {
            logger.warn("Order cost must be above minimum order cost");
        }

        if (order.getDeliveryTip() != null){
            order.setDeliveryTip(deliveryTip);
            total = total.add(deliveryTip);
        }

        // Set all order fields with proper values
        order.setStatus(Status.PENDING);
        order.setPaymentMethod(paymentMethod);
        order.setDate(new Date());
        order.setTotal(total);

        create(order);
    }


    @Override
    public void changeStatus(Order order, Status status) {
        order.setStatus(status);
        update(order);
    }

    @Override
    public void rateOrder(Order order, Integer orderRating) {
        order.setOrderRating(orderRating);
        update(order);
    }

    @Override
    public Optional<Order> findByAccount(Account account) {
        return orderRepository.findByAccount(account);
    }

    @Override
    public List<Order> findByStore(Store store) {
        return orderRepository.findByStore(store);
    }

    private boolean checkNullability(Order order, Product product) {
        if (order == null) {
            logger.warn("Order is null therefore it cannot be processed.");
            return true;
        }
        if (product == null) {
            logger.warn("Product is null therefore it cannot be added to the order.");
            return true;
        }
        return false;
    }

    private boolean validate(Order order) {
        return order != null && !order.getOrderItems().isEmpty() && order.getAccount() != null;
    }


    private OrderItem newOrderItem(Order order, Product product, int quantity) {
        return OrderItem.builder().product(product).quantity(quantity).price(product.getPrice()).order(order).build();
    }
}
