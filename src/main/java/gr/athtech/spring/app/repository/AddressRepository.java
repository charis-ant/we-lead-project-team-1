package gr.athtech.spring.app.repository;

import gr.athtech.spring.app.model.Address;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends BaseRepository<Address, Long>{
}
