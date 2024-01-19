package gr.athtech.spring.app.transfer.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;

@Getter
@Setter
@ToString(callSuper = true)
public class ProductResource extends BaseResource {
    private String name;
    private BigDecimal price;
    private String description;
    private StoreResource store;
    private ArrayList<ProductCategoryResource> productCategories;
}
