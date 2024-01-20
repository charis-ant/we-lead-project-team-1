package gr.athtech.spring.app.mapper;

import gr.athtech.spring.app.model.Product;
import gr.athtech.spring.app.transfer.resource.ProductResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface ProductMapper extends BaseMapper<Product, ProductResource> {
}
