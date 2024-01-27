package gr.athtech.spring.app.service;

import gr.athtech.spring.app.model.Account;
import gr.athtech.spring.app.model.Address;
import gr.athtech.spring.app.model.Order;

import java.util.List;
import java.util.Optional;

public interface AccountService extends BaseService<Account, Long> {
    Account findByEmail(String email);

    Account findByTelephoneNumber(String telephoneNumber);

    void signup(Account account);

    void changePassword(Long id, String password);

    Optional<Order> viewPlacedOrders(Long id);

    //Pending Methods
    boolean login(String email, String password);

    void addAddress(Long accountId, Address address);

    void removeAddress(Long accountId, Address address);
}
