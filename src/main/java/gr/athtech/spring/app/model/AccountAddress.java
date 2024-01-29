package gr.athtech.spring.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "ACCOUNT_ADDRESSES")
@SequenceGenerator(name = "idGenerator", sequenceName = "ADDRESSES_SEQ", initialValue = 1, allocationSize = 1)
public class AccountAddress extends BaseModel{
    @NotNull(message = "Street name cannot be null")
    @Column(length = 50, nullable = false)
    private String streetName;

    @NotNull(message = "Street number cannot be null")
    @Column(length = 4, nullable = false)
    private Integer streetNumber;

    @NotNull(message = "Postal code cannot be null")
    @Column(length = 4, nullable = false)
    private Integer postalCode;

    @NotNull
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    @NotNull(message = "City cannot be null")
    @Column(nullable = false)
    private String city;

    @Column(length = 2, nullable = false)
    private Integer floor;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private PropertyType propertyType;
}
