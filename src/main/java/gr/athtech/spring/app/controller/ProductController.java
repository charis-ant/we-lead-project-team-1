package gr.athtech.spring.app.controller;


import gr.athtech.spring.app.mapper.BaseMapper;
import gr.athtech.spring.app.mapper.ProductMapper;
import gr.athtech.spring.app.model.Product;
import gr.athtech.spring.app.service.BaseService;
import gr.athtech.spring.app.service.ProductService;
import gr.athtech.spring.app.transfer.resource.ProductResource;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController extends BaseController<Product, ProductResource> {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @Override
    protected BaseService<Product, Long> getBaseService() {
        return productService;
    }

    @Override
    protected BaseMapper<Product, ProductResource> getMapper() {
        return productMapper;
    }
}
