package gr.athtech.spring.app.service;

import gr.athtech.spring.app.model.Account;
import gr.athtech.spring.app.model.AccountAddress;
import gr.athtech.spring.app.model.Order;
import gr.athtech.spring.app.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl extends BaseServiceImpl<Account> implements AccountService {
    private final AccountRepository accountRepository;
    private final OrderService orderService;

    @Override
    protected JpaRepository<Account, Long> getRepository() {
        return accountRepository;
    }

    @Override
    public Account findByEmail(final String email) {
        return accountRepository.findByEmail(email);
    }

    public Account findByTelephoneNumber(final String telephoneNumber) {
        return accountRepository.findByTelephoneNumber(telephoneNumber);
    }

    //Check this again!
    public void signup(Account account) {
        //1) Check through Repository account already exists
        if (!(findByEmail(account.getEmail()) == null)) {
            logger.warn("User already exists");
        } else {
            //2) Create new account
            create(account);
        }
    }

    @Transactional
    public void changePassword(Long id, String password) {
        Account account = get(id);

        if (!(findByEmail(account.getEmail()) == null)) {
            if (password == null) {
                throw new IllegalArgumentException("Password cannot be null");
            }
            account.setPassword(password);
            update(account);
        } else {
            throw new NoSuchElementException("Account not found");
        }
    }

    @Transactional
    @Override
    public Optional<Order> viewPlacedOrders(Long id) {
        Account account = get(id);
        return orderService.findByAccount(account);
    }

    @Override
    public boolean login(final String email, String password) {
        // Retrieve user by username from the repository
        Account account = accountRepository.findByEmail(email);

        // Check if the user exists and the provided password is correct
        return account != null && password.equals(account.getPassword());
    }

    @Transactional
    @Override
    public void addAddress(Long accountId, AccountAddress accountAddress) {
        var account = get(accountId);

        if (accountAddress.getStreetName() == null || accountAddress.getStreetNumber() == null || accountAddress.getPostalCode() == null) {
            throw new IllegalArgumentException("Address field cannot be null");
        } else {
            if (account.getAccountAddresses().contains(accountAddress)) {
                throw new IllegalStateException("Address already exists for the account");
            } else {
                account.getAccountAddresses().add(accountAddress);
                update(account);
            }
        }

    }

    @Transactional
    @Override
    public void removeAddress(Long accountId, AccountAddress accountAddress) {
        var account = get(accountId);
        if (account.getAccountAddresses().contains(accountAddress)) {
            if (account.getAccountAddresses().size() >= 2) {
                accountRepository.deleteById(accountAddress.getId());
                account.getAccountAddresses().remove(accountAddress);
                update(account);
            } else {
                throw new NullPointerException("Address field cannot be null");
            }

        }
    }

}
