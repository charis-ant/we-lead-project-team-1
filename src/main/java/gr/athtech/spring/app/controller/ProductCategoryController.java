package gr.athtech.spring.app.controller;

import gr.athtech.spring.app.mapper.BaseMapper;
import gr.athtech.spring.app.mapper.ProductCategoryMapper;
import gr.athtech.spring.app.model.ProductCategory;
import gr.athtech.spring.app.service.BaseService;
import gr.athtech.spring.app.service.ProductCategoryService;
import gr.athtech.spring.app.transfer.resource.ProductCategoryResource;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productCategories")
@RequiredArgsConstructor
public class ProductCategoryController extends BaseController<ProductCategory, ProductCategoryResource> {
    private final ProductCategoryService productCategoryService;
    private final ProductCategoryMapper productCategoryMapper;


    @Override
    protected BaseService<ProductCategory, Long> getBaseService() {
        return productCategoryService;
    }

    @Override
    protected BaseMapper<ProductCategory, ProductCategoryResource> getMapper() {
        return productCategoryMapper;
    }
}
