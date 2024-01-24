package gr.athtech.spring.app.controller;

import gr.athtech.spring.app.mapper.AddressMapper;
import gr.athtech.spring.app.mapper.BaseMapper;
import gr.athtech.spring.app.model.Address;
import gr.athtech.spring.app.service.AddressService;
import gr.athtech.spring.app.service.BaseService;
import gr.athtech.spring.app.transfer.resource.AddressResource;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("addresses")
@RequiredArgsConstructor
public class AddressController extends BaseController<Address, AddressResource> {
    private final AddressService addressService;
    private final AddressMapper addressMapper;

    @Override
    protected BaseService<Address, Long> getBaseService() {
        return addressService;
    }

    @Override
    protected BaseMapper<Address, AddressResource> getMapper() {
        return addressMapper;
    }
}
