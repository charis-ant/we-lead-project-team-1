package gr.athtech.spring.app.mapper;

import gr.athtech.spring.app.model.Address;
import gr.athtech.spring.app.transfer.resource.AddressResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface AddressMapper extends BaseMapper<Address, AddressResource> {
}
