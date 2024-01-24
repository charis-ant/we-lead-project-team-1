package gr.athtech.spring.app.repository;

import gr.athtech.spring.app.model.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends BaseRepository<Account, Long> {
    Account findByEmail(String email);
    Account findByPhone(String phone);
    void signup(String email, String phone, String password);
}
