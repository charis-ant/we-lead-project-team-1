package gr.athtech.spring.app.service;

import gr.athtech.spring.app.model.*;
import gr.athtech.spring.app.repository.BaseRepository;
import gr.athtech.spring.app.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    protected BaseRepository<Order, Long> getRepository() {
        return orderRepository;
    }

    @Override
    public Order initiateOrder(final Account account, final Store store) {
        return Order.builder().account(account).store(store).build();
    }

    @Override
    public void addItem(final Order order, final Product product, final int quantity) {
        DayOfWeek currentDay = LocalDate.now().getDayOfWeek();
        LocalTime currentTime = LocalTime.now();

        LocalTime[][] checkSchedule = check(currentDay, order);

        if (currentTime.isAfter(checkSchedule[0][0]) && currentTime.isBefore(checkSchedule[0][1])){
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

            logger.info("Product added to Order");
        } else {
            logger.warn("Store closed!");
        }




        }

    @Override
    public void removeItem(Order order, Product product) {
        DayOfWeek currentDay = LocalDate.now().getDayOfWeek();
        LocalTime currentTime = LocalTime.now();

        LocalTime[][] checkSchedule = check(currentDay, order);

        if (currentTime.isAfter(checkSchedule[0][0]) && currentTime.isBefore(checkSchedule[0][1])) {
            if (checkNullability(order, product)) {
                return;
            }

            order.getOrderItems().removeIf(oi -> oi.getProduct().getId().equals(product.getId()));

            logger.info("Product removed from Order");
        } else {
        logger.warn("Store closed!");
    }


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
        DayOfWeek currentDay = LocalDate.now().getDayOfWeek();
        LocalTime currentTime = LocalTime.now();

        LocalTime[][] checkSchedule = check(currentDay, order);

        if (currentTime.isAfter(checkSchedule[0][0]) && currentTime.isBefore(checkSchedule[0][1])) {
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
        } else {
            logger.warn("Store closed!");
        }
    }

    @Override
    public void changeStatus(Order order, Status status) {
        order.setStatus(status);
        orderRepository.update(order);
    }

    @Override
    public void rateOrder(Order order, Integer orderRating) {
        order.setOrderRating(orderRating);
        orderRepository.update(order);
    }

    @Override
    public List<Order> findAllAccountOrders(Account account) {
        return orderRepository.findAllAccountOrders(account);
    }

    @Override
    public List<Order> findAllStoreOrders(Store store) {
        return orderRepository.findAllStoreOrders(store);
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

    private LocalTime[][] check(DayOfWeek currentDay, Order order) {
        LocalTime[][] checkSchedule = new LocalTime[1][2];

        if (currentDay == DayOfWeek.MONDAY) {
            checkSchedule[0][0] = order.getStore().getSchedule()[0][0];
            checkSchedule[0][1] = order.getStore().getSchedule()[0][1];
        } else if (currentDay == DayOfWeek.TUESDAY) {
            checkSchedule[0][0] = order.getStore().getSchedule()[1][0];
            checkSchedule[0][1] = order.getStore().getSchedule()[1][1];
        } else if (currentDay == DayOfWeek.WEDNESDAY) {
            checkSchedule[0][0] = order.getStore().getSchedule()[2][0];
            checkSchedule[0][1] = order.getStore().getSchedule()[2][1];
        } else if (currentDay == DayOfWeek.THURSDAY) {
            checkSchedule[0][0] = order.getStore().getSchedule()[3][0];
            checkSchedule[0][1] = order.getStore().getSchedule()[3][1];
        } else if (currentDay == DayOfWeek.FRIDAY) {
            checkSchedule[0][0] = order.getStore().getSchedule()[4][0];
            checkSchedule[0][1] = order.getStore().getSchedule()[4][1];
        } else if (currentDay == DayOfWeek.SATURDAY) {
            checkSchedule[0][0] = order.getStore().getSchedule()[5][0];
            checkSchedule[0][1] = order.getStore().getSchedule()[5][1];
        } else if (currentDay == DayOfWeek.SUNDAY) {
            checkSchedule[0][0] = order.getStore().getSchedule()[6][0];
            checkSchedule[0][1] = order.getStore().getSchedule()[6][1];
        }

        return checkSchedule;
    }

    private OrderItem newOrderItem(Order order, Product product, int quantity) {
        return OrderItem.builder().product(product).quantity(quantity).price(product.getPrice()).order(order).build();
    }
}
