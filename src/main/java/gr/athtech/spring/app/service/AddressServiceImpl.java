package gr.athtech.spring.app.service;

import gr.athtech.spring.app.model.Account;
import gr.athtech.spring.app.model.Address;
import gr.athtech.spring.app.repository.AccountRepository;
import gr.athtech.spring.app.repository.AddressRepository;
import gr.athtech.spring.app.repository.BaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl extends  BaseServiceImpl<Address> implements AddressService {
    private final AddressRepository addressRepository;
    private final AccountRepository accountRepository;

    @Override
    protected BaseRepository<Address, Long> getRepository() {
        return addressRepository;
    }

    @Override
    public void addAddress(Long id, Address address) {

        if( address.getStreetName() == null || address.getStreetNumber() == null || address.getPostalCode() == null) {
            throw new IllegalArgumentException("Address field cannot be null");
        } else {
            Account account = accountRepository.get(id);
            if (account.getAddresses().contains(address)) {
                throw new IllegalStateException("Address already exists for the account");
            } else {
                addressRepository.create(address);
                account.getAddresses().add(address);
                accountRepository.update(account);
            }
        }

    }

}
