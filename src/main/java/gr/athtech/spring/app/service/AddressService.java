package gr.athtech.spring.app.service;

import gr.athtech.spring.app.model.Address;

public interface AddressService extends BaseService<Address, Long> {
    void addAddress(Long id, Address address);
}
