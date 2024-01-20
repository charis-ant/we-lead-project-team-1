package gr.athtech.spring.app.transfer.resource;

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

//    private ArrayList<Product> products;   need to add this

    @NotNull(message = "Phone cannot be null")
    @Pattern(regexp = "^69\\d{8}$", message = "The phone number format is not correct")
    private Integer telephoneNumber;

    private String description;

    @NotNull(message = "Store Rating cannot be null") //To be discussed <3
    @Pattern(regexp = "^[1-5](\\.\\d{2})?$", message = "The Store Rating format is not correct")
    private Double storeRating;

    private ArrayList<StoreCategory> storeCategories;

    private LocalTime[][] schedule = new LocalTime[7][2];

    @NotNull(message = "Minimum order Price cannot be null")
    private BigDecimal minimumOrderPrice;

    @NotNull(message = "Delivery Cost cannot be null")
    private BigDecimal deliveryCost;
}
