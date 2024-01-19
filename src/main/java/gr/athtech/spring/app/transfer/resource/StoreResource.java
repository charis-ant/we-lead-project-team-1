package gr.athtech.spring.app.transfer.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import gr.athtech.spring.app.model.StoreCategory;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;

@Getter
@Setter
@ToString(callSuper = true)
public class StoreResource extends BaseResource {
    private String name;
    private AddressResource address;
    private Integer telephoneNumber;
    private String description;
    private Double storeRating;
    private ArrayList<StoreCategory> storeCategories;
    private LocalTime[][] schedule = new LocalTime[7][2];
    private BigDecimal minimumOrderPrice;
    private BigDecimal deliveryCost;
}
