package gr.athtech.spring.app.mapper;

import gr.athtech.spring.app.model.Order;
import gr.athtech.spring.app.transfer.resource.OrderResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface OrderMapper extends BaseMapper<Order, OrderResource> {
}
