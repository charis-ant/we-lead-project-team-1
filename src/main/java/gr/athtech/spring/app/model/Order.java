package gr.athtech.spring.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "ORDERS")
@SequenceGenerator(name = "idGenerator", sequenceName = "ORDERS_SEQ", initialValue = 1, allocationSize = 1)
public class Order extends BaseModel{
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Account account;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date date;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private final Set<OrderItem> orderItems = new HashSet<>();

    @Min(value = 1, message = "The order rating cannot be under 1")
    @Max(value = 5, message = "The order rating cannot be above 5")
    private Integer orderRating;

    @NotNull
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(length = 11, nullable = false)
    private PaymentMethod paymentMethod;

    @Column(precision = 10, scale = 2)
    private BigDecimal deliveryTip;

    @Enumerated(EnumType.STRING)
    @Column(length = 11, nullable = false)
    private Status status;
}


