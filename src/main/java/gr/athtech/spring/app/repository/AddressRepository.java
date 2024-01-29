package gr.athtech.spring.app.repository;

import gr.athtech.spring.app.model.AccountAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AccountAddress, Long> {
}