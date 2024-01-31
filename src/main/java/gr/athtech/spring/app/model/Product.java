package gr.athtech.spring.app.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "PRODUCTS")
@SequenceGenerator(name = "idGenerator", sequenceName = "PRODUCTS_SEQ", initialValue = 1, allocationSize = 1)
public class Product extends BaseModel {
    @NotNull
    @Column(length = 50, nullable = false)
    private String name;

    @NotNull
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

    @NotNull
    @Column(length = 100, nullable = false)
    private String description;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private ProductCategory productCategory;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
    private Store store;
}
