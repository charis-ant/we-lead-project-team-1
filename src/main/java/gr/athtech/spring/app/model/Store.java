package gr.athtech.spring.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

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
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private StoreAddress storeAddress;

    @Column(length = 10)
    private String telephoneNumber;

    @NotNull
    @Column(length = 50, nullable = false)
    private String description;

    @Column()
    private Double storeRating;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private final Set<Product> products = new HashSet<>();

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
