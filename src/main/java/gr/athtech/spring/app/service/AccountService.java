package gr.athtech.spring.app.service;

import gr.athtech.spring.app.model.Account;
import gr.athtech.spring.app.model.AccountAddress;
import gr.athtech.spring.app.model.Order;

import java.util.Optional;

public interface AccountService extends BaseService<Account, Long> {
    Account findByEmail(String email);

    Account findByTelephoneNumber(String telephoneNumber);

    void signup(Account account);

    void changePassword(Long id, String password);

    Optional<Order> viewPlacedOrders(Long id);

    //Pending Methods
    boolean login(String email, String password);

    void addAddress(Long accountId, AccountAddress accountAddress);

    void removeAddress(Long accountId, AccountAddress accountAddress);
}
