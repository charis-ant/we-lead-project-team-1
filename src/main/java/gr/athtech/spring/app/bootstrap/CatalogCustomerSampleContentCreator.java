package gr.athtech.spring.app.bootstrap;

import gr.athtech.spring.app.base.BaseComponent;
import gr.athtech.spring.app.model.*;
import gr.athtech.spring.app.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
@Profile("generate-catalog-customers")
@RequiredArgsConstructor
public class CatalogCustomerSampleContentCreator extends BaseComponent implements CommandLineRunner  {
    private final ProductService productService;
    private final ProductCategoryService productCategoryService;
    private final AccountService accountService;
    private final StoreService storeService;
    private final AddressService addressService;

    private LocalTime[][] initStoreSchedule() {
        LocalTime[][] storeSchedule = new LocalTime[7][2];
        for (int day = 0; day < 7; day++) {
            storeSchedule[day][0] = LocalTime.of(9, 0);
            storeSchedule[day][1] = LocalTime.of(23, 0);
        }
        return storeSchedule;
    }

    @Override
    public void run(String... args) throws Exception {
        ProductCategory newProductCategory = productCategoryService.create(ProductCategory.builder().name("burgers").build());

        List<Product> products = List.of(
                Product.builder().name("Hamburger").price(BigDecimal.valueOf(7)).description("classic hamburger")
                        .productCategory( newProductCategory).build(),
                Product.builder().name("Cheeseburger").price(BigDecimal.valueOf(8)).description("delicious cheeseburger")
                        .productCategory( newProductCategory).build(),
                Product.builder().name("Holy cow").price(BigDecimal.valueOf(10)).description("cheeseburger with a modern twist")
                        .productCategory( newProductCategory).build(),
                Product.builder().name("Veggie").price(BigDecimal.valueOf(9)).description("vegetarian burger")
                        .productCategory( newProductCategory).build()
        );


        var productsCreated = productService.createAll(products);
        logger.info("Created {} products.", productsCreated.size());

        productsCreated.stream()
                .sorted(Comparator.comparing(Product::getId))
                .forEach(p -> logger.debug("{}. {}", p.getId(), p));

        Store newStore = storeService.create(Store.builder().name("Burger House")
                .address(addressService.create(Address.builder()
                        .streetName("Ermou").streetNumber(120).postalCode(10000)
                        .city("Athens").floor(0).propertyType(PropertyType.WORK).build()))
                .telephoneNumber(2100000000).description("best burgers in town")
                .storeRating(null).storeCategories(new ArrayList<>(List.of(StoreCategory.BURGER)))
                .schedule(initStoreSchedule()).products(new ArrayList<>(productsCreated))
                .minimumOrderPrice(BigDecimal.valueOf(6)).deliveryCost(BigDecimal.valueOf(2))
                .build()
        );


        List<Account> accounts = List.of(
                Account.builder().email("martybyrde@gmail.com")
                        .firstname("Marty").lastname("Byrde")
                        .phone(697777777).password("1234").accountCategory(AccountCategory.CUSTOMER)
                        .addresses(new ArrayList<>(List.of(addressService.create(Address.builder()
                                .streetName("Stadiou").streetNumber(10)
                                .postalCode(10000).propertyType(PropertyType.HOME)
                                .city("Athens").floor(5).build()))))
                        .build(),
                Account.builder().email("sixseasonsandamovie@gmail.com")
                        .firstname("Abed").lastname("Nadir")
                        .phone(698888888).password("54321").accountCategory(AccountCategory.CUSTOMER)
                        .addresses(new ArrayList<> (List.of(addressService.create(Address.builder()
                                .streetName("Athinas").streetNumber(23)
                                .postalCode(10000).propertyType(PropertyType.WORK)
                                .city("Athens").floor(4).build()))))
                        .build()
        );

        var accountsCreated = accountService.createAll(accounts);
        logger.info("Created {} accounts.", accountsCreated.size());
        accountsCreated.stream()
                .sorted(Comparator.comparing(Account::getId))
                .forEach(c -> logger.debug("{}. {}", c.getId(), c));
    }
}
