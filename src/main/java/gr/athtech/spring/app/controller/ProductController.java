package gr.athtech.spring.app.controller;


import gr.athtech.spring.app.mapper.BaseMapper;
import gr.athtech.spring.app.mapper.ProductMapper;
import gr.athtech.spring.app.model.Product;
import gr.athtech.spring.app.service.BaseService;
import gr.athtech.spring.app.service.ProductService;
import gr.athtech.spring.app.transfer.ApiResponse;
import gr.athtech.spring.app.transfer.resource.AccountResource;
import gr.athtech.spring.app.transfer.resource.ProductResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
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

//    @PostMapping(params = "storeId")
//    public ResponseEntity<ApiResponse<ProductResource>> create(@RequestBody final ProductResource productResource,
//                                                               @RequestParam Long storeId) {
//        var product = productMapper.toDomain(productResource);
//        return new ResponseEntity<>(
//                ApiResponse.<ProductResource>builder()
//                        .data(getMapper().toResource(productService.create(product, storeId)))
//                        .build(),
//                getNoCacheHeaders(),
//                HttpStatus.CREATED
//        );
//    }

    @GetMapping(params = {"productCategoryId"})
    public ResponseEntity<ApiResponse<List<ProductResource>>> findByProductCategory(@RequestParam Long productCategoryId) {
        return ResponseEntity.ok(
                ApiResponse.<List<ProductResource>>builder()
                        .data(productMapper.toResources(productService.findByProductCategory(productCategoryId)))
                        .build());
    }
}
