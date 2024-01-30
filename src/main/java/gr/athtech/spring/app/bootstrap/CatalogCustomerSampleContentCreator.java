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
                "Burgers",
                "Pizzas",
                "Sandwiches",
                "Sushi",
                "Salads",
                "Pasta",
                "Desserts"
        };

        for (String categoryName : productCategoryNames) {
            // Create new product category
            ProductCategory newProductCategory = productCategoryService.create(
                    ProductCategory.builder()
                            .name(categoryName.toLowerCase())
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

    //Creating a ProductCategory
    ProductCategory newProductCategory = productCategoryService.create(
            ProductCategory.builder().name("burgers").build());
    logger.info("Created {}.", newProductCategory);

    //Data for stores and addresses
    String[][] storeData = {
            {"Burger House", "6900000000", "best burgers in town", "Zografou", "120", "20000", "Athens", "BURGER"},
            {"Pizza Place", "6900000001", "delicious pizzas", "Monastiraki", "10", "10000", "Athens", "PIZZA"},
            {"Sushi Corner", "6900000002", "fresh sushi delights", "Glyfada", "5", "16675", "Athens", "SUSHI"}
    };

        //Data for products
        String[][] productData = {
                {"Burger Classic", "4.5", "Classic beef burger"},
                {"Burger Cheese", "5.0", "Cheeseburger with extra cheese"},
                {"Burger Veggie", "4.0", "Healthy veggie burger"}
        };

        for (String[] storeInfo : storeData) {
            //Create and save the store
            Store newStore = Store.builder()
                    .name(storeInfo[0])
                    .telephoneNumber(storeInfo[1])
                    .description(storeInfo[2])
                    .storeRating(4.0)
                    .storeCategory(StoreCategory.valueOf(storeInfo[7]))
                    .minimumOrderPrice(BigDecimal.valueOf(6))
                    .deliveryCost(BigDecimal.valueOf(2))
                    .build();

            //Create the store address and associate it with the store
            StoreAddress newStoreAddress = StoreAddress.builder()
                    .streetName(storeInfo[3])
                    .streetNumber(Integer.parseInt(storeInfo[4]))
                    .postalCode(Integer.parseInt(storeInfo[5]))
                    .city(storeInfo[6])
                    .store(newStore) // Associate with the Store
                    .build();

            //Set the store address in the store and save the store
            newStore.setStoreAddress(newStoreAddress);
            newStore = storeService.create(newStore); // Assuming storeService is available

            //Create and save products for this store
            for (String[] productInfo : productData) {
                Product newProduct = Product.builder()
                        .name(productInfo[0])
                        .price(new BigDecimal(productInfo[1]))
                        .description(productInfo[2])
                        .productCategory(newProductCategory) // Assuming newProductCategory is already created and available
                        .store(newStore)
                        .build();

                newStore.getProducts().add(newProduct);
                productService.create(newProduct); // Assuming productService is available
            }
        }
    }
}
