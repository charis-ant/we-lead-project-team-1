package gr.athtech.spring.app.service;

import gr.athtech.spring.app.model.Account;
import gr.athtech.spring.app.model.Order;

import java.util.List;

public interface AccountService extends BaseService<Account, Long> {
    Account findByEmail(String email);
    Account findByPhone(Integer phone);

    boolean signup(Account account);

    void changePassword(Long id, String password);

    List<Order> viewPlacedOrders(Long id);

    //Pending Methods
    boolean login(String email, String password);
    void logout();
}
