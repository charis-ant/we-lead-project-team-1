package gr.athtech.spring.app.transfer.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import gr.athtech.spring.app.model.PropertyType;

@Getter
@Setter
@ToString(callSuper = true)
public class AddressResource extends BaseResource {
    private String streetName;
    private Integer streetNumber;
    private Integer postalCode;
    private String city;
    private Integer floor;
    private PropertyType propertyType;
}
