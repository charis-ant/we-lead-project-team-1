package gr.athtech.spring.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Store extends BaseModel{
    private String name;
    private Address address;
    private Integer telephoneNumber;
    private String description;
    private Double storeRating;
    private ArrayList<Product> products;
    private ArrayList<StoreCategory> storeCategories;
    private LocalTime[][] schedule = new LocalTime[7][2];
    private BigDecimal minimumOrderPrice;
    private BigDecimal deliveryCost;
}
