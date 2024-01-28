package gr.athtech.spring.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "STORES")
@SequenceGenerator(name = "idGenerator", sequenceName = "STORES_SEQ", initialValue = 1, allocationSize = 1)
public class Store extends BaseModel{
    @NotNull(message = "Store name cannot be null")
    @Column(length = 20, nullable = false)
    private String name;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Address address;

    @Column(length = 10)
    private String telephoneNumber;

    @NotNull
    @Column(length = 50, nullable = false)
    private String description;

    @NotNull
    @Column(nullable = false)
    private Double storeRating;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private final ArrayList<Product> products = new ArrayList<>();

    @NotNull
    @Enumerated(EnumType.STRING)
    private StoreCategory storeCategory;

    @NotNull
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal minimumOrderPrice;

    @NotNull
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal deliveryCost;
}
