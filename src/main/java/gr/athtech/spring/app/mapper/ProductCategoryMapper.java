package gr.athtech.spring.app.mapper;

import gr.athtech.spring.app.model.ProductCategory;
import gr.athtech.spring.app.transfer.resource.ProductCategoryResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface ProductCategoryMapper extends BaseMapper<ProductCategory, ProductCategoryResource> {
}
