package gr.athtech.spring.app.service;

import gr.athtech.spring.app.model.Account;
import gr.athtech.spring.app.model.AccountAddress;

public interface AccountService extends BaseService<Account, Long> {
    Account findByEmail(String email);

    Account findByTelephoneNumber(String telephoneNumber);

    void addAddress(Long accountId, AccountAddress accountAddress);

    void removeAddress(Long accountId, Long accountAddressId);

    void changePassword(Long id, String password);

    //Pending Methods
    boolean login(String email, String password);

    void signup(Account account);


}
