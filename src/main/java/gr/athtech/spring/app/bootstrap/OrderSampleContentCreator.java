package gr.athtech.spring.app.bootstrap;

import gr.athtech.spring.app.base.BaseComponent;
import gr.athtech.spring.app.model.*;
import gr.athtech.spring.app.service.AccountService;
import gr.athtech.spring.app.service.OrderService;
import gr.athtech.spring.app.service.ProductService;
import gr.athtech.spring.app.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Profile("generate-orders")
@RequiredArgsConstructor
public class OrderSampleContentCreator extends BaseComponent implements CommandLineRunner {
    private final AccountService accountService;
    private final OrderService orderService;
    private final ProductService productService;
    private final StoreService storeService;

    @Override
    public void run(String... args) throws Exception {

        // Load customer and create an order by adding/updating/removing content before checking it out
        Account firstCustomer = accountService.findByEmail("johndoe@mail.com");
        Store firstStore = storeService.findByName("Burger House");

        Order firstOrder = orderService.initiateOrder(firstCustomer, firstStore);

        // Add item(s)
        orderService.addItem(firstOrder, productService.findByName("Burger Classic"), 1);
        orderService.addItem(firstOrder, productService.findByName("Burger Cheese"), 1);
        orderService.addItem(firstOrder, productService.findByName("Burger Veggie"), 1);
        orderService.addItem(firstOrder, productService.findByName("Burger Veggie"), 1);
        orderService.addItem(firstOrder, productService.findByName("Burger Veggie"), 1);
        // Update item(s)
        orderService.updateItem(firstOrder, productService.findByName("Burger Cheese"), 3);
        // Remove item(s)
        orderService.removeItem(firstOrder, productService.findByName("Burger Veggie"));
        // Checkout order
        orderService.checkout(firstOrder, PaymentMethod.CARD, BigDecimal.valueOf(0.5));
    }
}
