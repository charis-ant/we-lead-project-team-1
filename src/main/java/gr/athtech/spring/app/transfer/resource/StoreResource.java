package gr.athtech.spring.app.transfer.resource;

import gr.athtech.spring.app.model.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import gr.athtech.spring.app.model.StoreCategory;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;

@Getter
@Setter
@ToString(callSuper = true)
public class StoreResource extends BaseResource {
    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Address cannot be null")
    private AddressResource address;

    @NotNull(message = "Products cannot be null")
    private ArrayList<Product> products;

    @NotNull(message = "Phone cannot be null")
    @Pattern(regexp = "^(69\\d{8}|210\\d{7})$", message = "The phone number format is not correct")
    private String telephoneNumber;

    private String description;

    @DecimalMin(value = "1.00", message = "Store rating must be at least 1.00")
    @DecimalMax(value = "5.00", message = "Store rating must be at most 5.00")
    @Digits(integer = 1, fraction = 2, message = "Invalid store rating format")
    private Double storeRating;

    private ArrayList<StoreCategory> storeCategories;

    private LocalTime[][] schedule = new LocalTime[7][2];

    @NotNull(message = "Minimum order Price cannot be null")
    private BigDecimal minimumOrderPrice;

    @NotNull(message = "Delivery Cost cannot be null")
    private BigDecimal deliveryCost;
}
