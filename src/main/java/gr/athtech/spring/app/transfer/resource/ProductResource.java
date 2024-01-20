package gr.athtech.spring.app.transfer.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
@ToString(callSuper = true)
public class ProductResource extends BaseResource {
    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Price cannot be null")
    private BigDecimal price;

    private String description;

    @NotNull(message = "Store cannot be null")
    private StoreResource store;

    private ProductCategoryResource productCategory;
}