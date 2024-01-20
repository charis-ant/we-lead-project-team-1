package gr.athtech.spring.app.bootstrap;

import gr.athtech.spring.app.base.BaseComponent;
import gr.athtech.spring.app.model.Account;
import gr.athtech.spring.app.model.Order;
import gr.athtech.spring.app.model.PaymentMethod;
import gr.athtech.spring.app.service.AccountService;
import gr.athtech.spring.app.service.OrderService;
import gr.athtech.spring.app.service.ProductService;
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
    @Override
    public void run(String... args) throws Exception {
        // Get all accounts
        accountService.findAll().forEach(a -> logger.info("{}", a));

        // We don't mind if a "find" method returns a null
        logger.info("Does customer exist? {}.", (accountService.findByEmail("sixseasonsandamovie@gmail.com") != null));
        logger.info("Does customer exist? {}.", (accountService.findByEmail("non-existing@gmail.com") != null));

        // Load customer and create an order by adding/updating/removing content before checking it out
        Account firstCustomer = accountService.findByEmail("sixseasonsandamovie@gmail.com");
        Order firstOrder = orderService.initiateOrder(firstCustomer);

        // Add item(s) both existing and non-existing
        orderService.addItem(firstOrder, productService.findByName("Hamburger"));
        orderService.addItem(firstOrder, productService.findByName("Cheeseburger"));
        orderService.addItem(firstOrder, productService.findByName("Veggie"));
        orderService.addItem(firstOrder, productService.findByName("Veggie"));
        orderService.addItem(firstOrder, productService.findByName("Veggie"));
        // Add a non-existing product
        orderService.addItem(firstOrder, productService.findByName("SN1000-0008"));
        // Update item(s)
        orderService.addItem(firstOrder, productService.findByName("Cheeseburger"));
        // Remove item(s)
        orderService.removeItem(firstOrder, productService.findByName("Veggie"));
        // Checkout order
        orderService.checkout(firstOrder, PaymentMethod.CARD, BigDecimal.valueOf(0.5));
    }
}
