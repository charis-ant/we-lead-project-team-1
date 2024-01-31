package gr.athtech.spring.app.bootstrap;

import gr.athtech.spring.app.base.BaseComponent;
import gr.athtech.spring.app.model.*;
import gr.athtech.spring.app.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Profile("generate-catalog-customers")
@RequiredArgsConstructor
public class CatalogCustomerSampleContentCreator extends BaseComponent implements CommandLineRunner  {
    private final ProductService productService;
    private final ProductCategoryService productCategoryService;
    private final AccountService accountService;
    private final StoreService storeService;

    @Override
    public void run(String... args) throws Exception {
        // Product category names
        String[] productCategoryNames = {
                "burgers",
                "pizzas",
                "sandwiches",
                "sushi",
                "salads",
                "sasta",
                "desserts"
        };

        for (String categoryName : productCategoryNames) {
            // Create new product category
            ProductCategory newProductCategory = productCategoryService.create(
                    ProductCategory.builder()
                            .name(categoryName)
                            .build()
            );

            // Log the creation of the new product category
            logger.info("Created {}.", newProductCategory);
        }

        //Customer names and details
        String[][] customerDetails = {
                {"John Doe", "johndoe@mail.com", "Karaiskaki", "Patras"},
                {"Jane Smith", "janesmith@mail.com", "Riga Fereou", "Patras"},
                {"Alice Johnson", "alicejohnson@mail.com", "Ermou", "Athens"},
                {"Bob Brown", "bobbrown@mail.com", "Stadio", "Athens"},
                {"Tom Harris", "tomharris@mail.com", "Tsimiski", "Thessaloniki"},
                {"Emily White", "emilywhite@mail.com", "Vasilisis Olgas", "Thessaloniki"},
                {"David Green", "davidgreen@mail.com", "Kanari", "Volos"}
        };

        for (int i = 0; i < customerDetails.length; i++) {
            // Splitting the name into first and last name
            String[] name = customerDetails[i][0].split(" ");

            // Create new account
            Account newAccount = Account.builder()
                    .email(customerDetails[i][1])
                    .firstname(name[0])
                    .lastname(name[1])
                    .telephoneNumber("690000000" + (i + 1))
                    .password("pass" + (i + 1))
                    .accountCategory(AccountCategory.CUSTOMER)
                    .build();

            // Create new address
            AccountAddress newAccountAddress = AccountAddress.builder()
                    .streetName(customerDetails[i][2])
                    .streetNumber(i + 1)
                    .postalCode(10000 + i)
                    .propertyType(PropertyType.WORK) // or any other type you see fit
                    .city(customerDetails[i][3])
                    .floor(i + 1)
                    .account(newAccount) // Link the address to the account
                    .build();

            // Add address to account
            newAccount.getAccountAddresses().add(newAccountAddress);

            // Persist the new account with the address
            accountService.create(newAccount);
        }

        //Create and save the store
        Store newStore = Store.builder()
            .name("Burger House")
            .telephoneNumber(String.valueOf(2100000000))
            .description("best burgers in town")
            .storeRating(4.0)
            .storeCategory(StoreCategory.valueOf("BURGER"))
            .minimumOrderPrice(BigDecimal.valueOf(6))
            .deliveryCost(BigDecimal.valueOf(2))
            .build();

        //Create the store address and associate it with the store
        StoreAddress newStoreAddress = StoreAddress.builder()
            .streetName("Ermou")
            .streetNumber(120)
            .postalCode(20000)
            .city("Athens")
            .store(newStore) // Associate with the Store
            .build();

        //Set the store address in the store and save the store
        newStore.setStoreAddress(newStoreAddress);
        storeService.create(newStore); // Assuming storeService is available

        Store newStore1 = Store.builder()
                .name("Pizza Place")
                .telephoneNumber(String.valueOf(2100000001))
                .description("delicious pizzas")
                .storeCategory(StoreCategory.valueOf("PIZZA"))
                .minimumOrderPrice(BigDecimal.valueOf(6))
                .deliveryCost(BigDecimal.valueOf(2))
                .build();

        StoreAddress newStoreAddress1 = StoreAddress.builder()
                .streetName("Vasileos Georgiou")
                .streetNumber(10)
                .postalCode(10000)
                .city("Patra")
                .store(newStore1) // Associate with the Store
                .build();

        //Set the store address in the store and save the store
        newStore1.setStoreAddress(newStoreAddress1);
        storeService.create(newStore1); // Assuming storeService is available

        Store newStore2 = Store.builder()
                .name("Sushi Corner")
                .telephoneNumber(String.valueOf(2100000002))
                .description("fresh sushi delights")
                .storeRating(5.0)
                .storeCategory(StoreCategory.valueOf("SUSHI"))
                .minimumOrderPrice(BigDecimal.valueOf(6))
                .deliveryCost(BigDecimal.valueOf(2))
                .build();

        StoreAddress newStoreAddress2 = StoreAddress.builder()
                .streetName("Tsimiski")
                .streetNumber(5)
                .postalCode(16675)
                .city("Thessaloniki")
                .store(newStore2) // Associate with the Store
                .build();

        //Set the store address in the store and save the store
        newStore2.setStoreAddress(newStoreAddress2);
        storeService.create(newStore2); // Assuming storeService is available

        Store newStore3 = Store.builder()
                .name("Sweet Memories")
                .telephoneNumber(String.valueOf(2100000003))
                .description("fresh delicacies")
                .storeRating(2.0)
                .storeCategory(StoreCategory.valueOf("BAKERY"))
                .minimumOrderPrice(BigDecimal.valueOf(6))
                .deliveryCost(BigDecimal.valueOf(2))
                .build();

        StoreAddress newStoreAddress3 = StoreAddress.builder()
                .streetName("Charilaou Trikoupi")
                .streetNumber(50)
                .postalCode(16675)
                .city("Athens")
                .store(newStore3) // Associate with the Store
                .build();

        //Set the store address in the store and save the store
        newStore3.setStoreAddress(newStoreAddress3);
        storeService.create(newStore3); // Assuming storeService is available

        //Data for products
        String[][] productData = {
                {"Hamburger", "4.5", "Classic beef burger"},
                {"Cheeseburger", "5.0", "Cheeseburger with extra cheese"},
                {"Vegetarian burger", "4.0", "Healthy veggie burger"},
                {"MARGHERITA", "10", "SAN MARZANO TOMATOES, GARLIC, OLIVE OIL, DRIED AND FRESH OREGANO"},
                {"BIANCA", "11.30", "BURRATA, GARLIC, BASIL, OLIVE OIL, PARMESAN, BLACK PEPPER"},
                {"TRUFFA", "14.50", "MUSHROOM CREAM, MOZZARELLA, PORTOBELLO MUSHROOMS, FRESH BLACK TRUFFLE"},
                {"Salmon Avocado", "6", "Inside out roll with salmon, avocado, cream cheese filling & sesame seeds on top"},
                {"California Roll", "6", "Inside out roll with surimi, avocado, wasabi mayo filling & sesame seeds on top"},
                {"Spicy Crab Roll", "4.50", "Inside out roll with surimi, avocado, spicy sauce filling & red massago on top"},
                {"Galaktoboureko", "6", "Buttery cream made of buffalo milk in phyllo pastry"},
                {"Sokolatopita", "7", "Homemade chocolate cake with bitter chocolate sauce and vanilla ice cream"},
                {"Banoffee", "7.5", "Delicious caramel with bananas and cream cheese on a crunchy cookie base"}
        };

        //Create and save products for this store

        //Store newStore = storeService.findByName("Burger House");
        Product newProduct = Product.builder()
            .name(productData[0][0])
            .price(new BigDecimal(productData[0][1]))
            .description(productData[0][2])
            .productCategory(productCategoryService.findByName("burgers"))
            .store(newStore)
            .build();

        newStore.getProducts().add(newProduct);
        productService.create(newProduct); // Assuming productService is available

        Product newProduct1 = Product.builder()
            .name(productData[1][0])
            .price(new BigDecimal(productData[1][1]))
            .description(productData[1][2])
            .productCategory(productCategoryService.findByName("burgers"))
            .store(newStore)
            .build();

        newStore.getProducts().add(newProduct1);
        productService.create(newProduct1); // Assuming productService is available

        Product newProduct2 = Product.builder()
            .name(productData[2][0])
            .price(new BigDecimal(productData[2][1]))
            .description(productData[2][2])
            .productCategory(productCategoryService.findByName("burgers"))
            .store(newStore)
            .build();

        newStore.getProducts().add(newProduct2);
        productService.create(newProduct2); // Assuming productService is available

        //Store newStore1 = storeService.findByName("Pizza Place");
        Product newProduct3 = Product.builder()
            .name(productData[3][0])
            .price(new BigDecimal(productData[3][1]))
            .description(productData[3][2])
            .productCategory(productCategoryService.findByName("pizzas"))
            .store(newStore1)
            .build();

        newStore1.getProducts().add(newProduct3);
        productService.create(newProduct3); // Assuming productService is available

        Product newProduct4 = Product.builder()
            .name(productData[4][0])
            .price(new BigDecimal(productData[4][1]))
            .description(productData[4][2])
            .productCategory(productCategoryService.findByName("pizzas"))
            .store(newStore1)
            .build();

        newStore1.getProducts().add(newProduct4);
        productService.create(newProduct4); // Assuming productService is available

        Product newProduct5 = Product.builder()
            .name(productData[5][0])
            .price(new BigDecimal(productData[5][1]))
            .description(productData[5][2])
            .productCategory(productCategoryService.findByName("pizzas"))
            .store(newStore1)
            .build();

        newStore1.getProducts().add(newProduct5);
        productService.create(newProduct5); // Assuming productService is available

        //Store newStore2 = storeService.findByName("Sushi Corner");
        Product newProduct6 = Product.builder()
            .name(productData[6][0])
            .price(new BigDecimal(productData[6][1]))
            .description(productData[6][2])
            .productCategory(productCategoryService.findByName("sushi"))
            .store(newStore2)
            .build();

        newStore2.getProducts().add(newProduct6);
        productService.create(newProduct6); // Assuming productService is available

        Product newProduct7 = Product.builder()
            .name(productData[7][0])
            .price(new BigDecimal(productData[7][1]))
            .description(productData[7][2])
            .productCategory(productCategoryService.findByName("sushi"))
            .store(newStore2)
            .build();

        newStore2.getProducts().add(newProduct7);
        productService.create(newProduct7); // Assuming productService is available

        Product newProduct8 = Product.builder()
            .name(productData[8][0])
            .price(new BigDecimal(productData[8][1]))
            .description(productData[8][2])
            .productCategory(productCategoryService.findByName("sushi"))
            .store(newStore2)
            .build();

        newStore2.getProducts().add(newProduct8);
        productService.create(newProduct8); // Assuming productService is available

        //Store newStore3 = storeService.findByName("Sweet Memories");
        Product newProduct9 = Product.builder()
                .name(productData[6][0])
                .price(new BigDecimal(productData[6][1]))
                .description(productData[6][2])
                .productCategory(productCategoryService.findByName("desserts"))
                .store(newStore3)
                .build();

        newStore3.getProducts().add(newProduct9);
        productService.create(newProduct9); // Assuming productService is available

        Product newProduct10 = Product.builder()
                .name(productData[7][0])
                .price(new BigDecimal(productData[7][1]))
                .description(productData[7][2])
                .productCategory(productCategoryService.findByName("desserts"))
                .store(newStore3)
                .build();

        newStore3.getProducts().add(newProduct10);
        productService.create(newProduct10); // Assuming productService is available

        Product newProduct11 = Product.builder()
                .name(productData[8][0])
                .price(new BigDecimal(productData[8][1]))
                .description(productData[8][2])
                .productCategory(productCategoryService.findByName("desserts"))
                .store(newStore3)
                .build();

        newStore3.getProducts().add(newProduct11);
        productService.create(newProduct11); // Assuming productService is available
    }
}
