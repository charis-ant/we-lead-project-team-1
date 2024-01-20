package gr.athtech.spring.app.transfer.resource;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import gr.athtech.spring.app.model.PropertyType;

@Getter
@Setter
@ToString(callSuper = true)
public class AddressResource extends BaseResource {
    @NotNull(message = "Street name cannot be null")
    private String streetName;

    @NotNull(message = "Street number cannot be null")
    private Integer streetNumber;

    @Pattern(regexp = "[0-9]{5}", message = "The postal code format is not correct")
    @NotNull(message = "Postal Code cannot be null")
    private Integer postalCode;

    @Pattern(regexp = "[A-Za-z\\s]+", message = "The City name format is not correct")
    @NotNull(message = "String city cannot be null")
    private String city;

    private Integer floor;

    @NotNull(message = "Property Type cannot be null")
    private PropertyType propertyType;
}