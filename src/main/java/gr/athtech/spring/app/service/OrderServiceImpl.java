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
import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    protected BaseRepository<Order, Long> getRepository() {
        return orderRepository;
    }

    @Override
    public Order initiateOrder(final Account account) {
        return Order.builder().account(account).build();
    }

    @Override
    public void addItem(final Order order, final Product product) {
        DayOfWeek currentDay = LocalDate.now().getDayOfWeek();
        LocalTime currentTime = LocalTime.now();

        LocalTime[][] checkSchedule = check(currentDay, order);

        if (currentTime.isAfter(checkSchedule[0][0]) && currentTime.isBefore(checkSchedule[0][1])){
            if (checkNullability(order, product)) {
                return;
            }

            Map<Product, Integer> products = order.getProducts();

            // If product is already contained in the order, don't add it again, just increase the quantity accordingly
            for (Product p : products.keySet()) {
                if (p.getId().equals(product.getId())) {
                    products.put(p, products.getOrDefault(p, 0) + 1);
                    break;
                }
            }

            orderRepository.update(order);
            logger.trace("Product[{}] added to Order[{}]", product, order);
        } else {
            logger.trace("Store closed!");
        }
    }

    @Override
    public void removeItem(Order order, Product product) {
        if (checkNullability(order, product)) {
            return;
        }

        Map<Product, Integer> products = order.getProducts();

        if (products.containsKey(product)){
            if (products.get(product).equals(1)) {
                products.remove(product);
            }else{
                products.put(product, products.get(product) - 1);
            }
        }
        orderRepository.update(order);
    }

    @Override
    public void emptyOrder(Account account, Order order) {
        if (order.getAccount().equals(account)) {
            orderRepository.delete(order);
        }

        //orderRepository.deleteById(order.getId());
    }

    @Override
    public Order checkout(final Order order, final PaymentMethod paymentMethod, final BigDecimal deliveryTip) {
        if (!validate(order)) {
            logger.warn("Order should have account, products, and payment method defined, before being able to " +
                    "checkout the order.");
            return null;
        }

        BigDecimal total = BigDecimal.ZERO;

        for (Product p : order.getProducts().keySet()) {
            BigDecimal price = p.getPrice();
            Integer quantity = order.getProducts().get(p);
            total = total.add(price.multiply(BigDecimal.valueOf(quantity)));
        }

        order.setTotal(total);

        //Check if order cost is above minimum order value
        if (order.getTotal().compareTo(order.getStore().getMinimumOrderPrice()) < 0) {
            logger.warn("Order cost must be above minimum order cost");
            return null;
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

        orderRepository.update(order);

        return create(order);
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
        return order != null && !order.getProducts().isEmpty() && order.getAccount() != null;
    }

    private LocalTime[][] check(DayOfWeek currentDay, Order order) {
        LocalTime[][] checkSchedule = new LocalTime[1][1];


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
}
