package gr.athtech.spring.app.repository;

import gr.athtech.spring.app.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByTelephoneNumber(String telephoneNumber);
    Account findByEmail(String email);
}