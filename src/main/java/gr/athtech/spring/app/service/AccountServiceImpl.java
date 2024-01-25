package gr.athtech.spring.app.service;

import gr.athtech.spring.app.model.Account;
import gr.athtech.spring.app.model.Order;
import gr.athtech.spring.app.repository.AccountRepository;
import gr.athtech.spring.app.repository.BaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl extends BaseServiceImpl<Account> implements AccountService {
    private final AccountRepository accountRepository;
    private final OrderService orderService;

    @Override
    protected BaseRepository<Account, Long> getRepository() {
        return accountRepository;
    }

    @Override
    public Account findByEmail(final String email) {
        return accountRepository.findByEmail(email);
    }

    public Account findByPhone(final String phone) {
        return accountRepository.findByPhone(phone);
    }

    //Check this again!
    public void signup(Account account) {
        //1) Check through Repository account already exists
        if (accountRepository.exists(account)) {
            logger.warn("User already exists");
        } else {
            //2) Create new account
            accountRepository.create(account);
        }
    }

    @Override
    public void changePassword(Long id, String password) {
        Account account = accountRepository.get(id);
        if (accountRepository.exists(account)) {
            if (password == null) {
                throw new IllegalArgumentException("Password cannot be null");
            }
            account.setPassword(password);
            accountRepository.update(account);
        }
        else {
            throw new NoSuchElementException("Account not found");
        }

    }

    @Override
    public List<Order> viewPlacedOrders(Long id) {
        Account account = accountRepository.get(id);
        return orderService.findAllAccountOrders(account);
    }

    @Override
    public boolean login(final String email, String password) {
        // Retrieve user by username from the repository
        Account account = accountRepository.findByEmail(email);

        // Check if the user exists and the provided password is correct
        return account != null && password.equals(account.getPassword());
    }

}
