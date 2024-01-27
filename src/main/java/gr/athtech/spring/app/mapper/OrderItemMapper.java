package gr.athtech.spring.app.mapper;

import org.mapstruct.Mapper;
import gr.athtech.spring.app.model.OrderItem;
import gr.athtech.spring.app.transfer.resource.OrderItemResource;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface OrderItemMapper extends BaseMapper<OrderItem, OrderItemResource> {
}
