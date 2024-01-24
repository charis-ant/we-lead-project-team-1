package gr.athtech.spring.app.service;

import gr.athtech.spring.app.model.Account;
import gr.athtech.spring.app.model.Address;
import gr.athtech.spring.app.repository.AddressRepository;
import gr.athtech.spring.app.repository.BaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl extends  BaseServiceImpl<Address> implements AddressService {
    private final AddressRepository addressRepository;
    private final AccountService accountService;

    @Override
    protected BaseRepository<Address, Long> getRepository() {
        return addressRepository;
    }

    @Override
    public void addAddress(Long accountId, Address address) {

        if (address.getStreetName() == null || address.getStreetNumber() == null || address.getPostalCode() == null) {
            throw new IllegalArgumentException("Address field cannot be null");
        } else {
            Account account = accountService.get(accountId);
            if (account.getAddresses().contains(address)) {
                throw new IllegalStateException("Address already exists for the account");
            } else {
                addressRepository.create(address);
                account.getAddresses().add(address);
                accountService.update(account);
            }
        }

    }

    @Override
    public void removeAddress(Long accountId, Long addressId) {
        Account account = accountService.get(accountId);
        Address address = addressRepository.get(addressId);
        if (account.getAddresses().contains(address)) {
            if (account.getAddresses().size() >= 2) {
                addressRepository.deleteById(addressId);
                account.getAddresses().remove(address);
                accountService.update(account);
            } else {
                throw new NullPointerException("Address field cannot be null");
            }

        }
    }

}
