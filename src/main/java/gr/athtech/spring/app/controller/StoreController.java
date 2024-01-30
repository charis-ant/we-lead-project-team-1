package gr.athtech.spring.app.controller;

import gr.athtech.spring.app.mapper.BaseMapper;
import gr.athtech.spring.app.mapper.StoreMapper;
import gr.athtech.spring.app.model.Store;
import gr.athtech.spring.app.model.StoreCategory;
import gr.athtech.spring.app.service.BaseService;
import gr.athtech.spring.app.service.StoreService;
import gr.athtech.spring.app.transfer.ApiResponse;
import gr.athtech.spring.app.transfer.resource.StoreResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("stores")
@RequiredArgsConstructor
public class StoreController extends BaseController<Store, StoreResource> {
    private final StoreService storeService;
    private final StoreMapper storeMapper;


    @Override
    protected BaseService<Store, Long> getBaseService() {
        return storeService;
    }

    @Override
    protected BaseMapper<Store, StoreResource> getMapper() {
        return storeMapper;
    }

    @GetMapping(params = {"name"})
    public ResponseEntity<ApiResponse<StoreResource>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(
                ApiResponse.<StoreResource>builder()
                        .data(storeMapper.toResource(storeService.findByName(name)))
                        .build());
    }

    @GetMapping(params = {"storeCategory"})
    public ResponseEntity<ApiResponse<List<StoreResource>>> findByStoreCategory(@RequestParam StoreCategory storeCategory) {
        return ResponseEntity.ok(
                ApiResponse.<List<StoreResource>>builder()
                        .data(storeMapper.toResources(storeService.findByStoreCategory(storeCategory)))
                        .build());
    }

    @PatchMapping(params = "storeId")
    public ResponseEntity<ApiResponse<StoreResource>> calculateStoreRating(@RequestParam Long storeId) {
        storeService.calculateStoreRating(storeId);
        return new ResponseEntity<>(
                getNoCacheHeaders(),
                HttpStatus.ACCEPTED
        );
    }

}
