package gr.athtech.spring.app.repository;

import gr.athtech.spring.app.model.Address;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class AddressRepositoryImpl extends BaseRepositoryImpl<Address> implements AddressRepository {
    private final ConcurrentHashMap<Long, Address> storage = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);

    @Override
    protected ConcurrentHashMap<Long, Address> getStorage() {
        return storage;
    }

    @Override
    protected AtomicLong getSequence() {
        return sequence;
    }
}
