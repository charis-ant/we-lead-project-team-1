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
@Table(name = "STORE_ADDRESSES")
@SequenceGenerator(name = "idGenerator", sequenceName = "STORE_SEQ", initialValue = 1, allocationSize = 1)
public class StoreAddress extends BaseModel {
    @NotNull(message = "Street name cannot be null")
    @Column(length = 50, nullable = false)
    private String streetName;

    @NotNull(message = "Street number cannot be null")
    @Column(length = 4, nullable = false)
    private Integer streetNumber;

    @NotNull(message = "Postal code cannot be null")
    @Column(length = 4, nullable = false)
    private Integer postalCode;

    @OneToOne(fetch = FetchType.EAGER)
    private Store store;

    @NotNull(message = "City cannot be null")
    @Column(nullable = false)
    private String city;
}