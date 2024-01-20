package gr.athtech.spring.app.controller;

import gr.athtech.spring.app.mapper.BaseMapper;
import gr.athtech.spring.app.mapper.StoreMapper;
import gr.athtech.spring.app.model.Store;
import gr.athtech.spring.app.service.BaseService;
import gr.athtech.spring.app.service.StoreService;
import gr.athtech.spring.app.transfer.resource.StoreResource;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stores")
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
}
