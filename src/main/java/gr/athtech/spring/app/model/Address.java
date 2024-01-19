package gr.athtech.spring.app.model;

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
public class Address extends BaseModel{
    private String streetName;
    private Integer streetNumber;
    private Integer postalCode;
    private String city;
    private Integer floor;
    private PropertyType propertyType;

}
