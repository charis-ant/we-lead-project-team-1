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
@Table(name = "ORDER_ITEMS")
@SequenceGenerator(name = "idGenerator", sequenceName = "ORDER_ITEMS_SEQ", initialValue = 1, allocationSize = 1)
public class OrderItem extends BaseModel {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Product product;

    @ToString.Exclude
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Order order;

    @NotNull
    @Column(nullable = false)
    private Integer quantity;

    @NotNull
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal price;
}




