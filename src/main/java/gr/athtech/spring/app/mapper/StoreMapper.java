package gr.athtech.spring.app.mapper;

import gr.athtech.spring.app.model.Store;
import gr.athtech.spring.app.transfer.resource.StoreResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface StoreMapper extends BaseMapper<Store, StoreResource> {
}
