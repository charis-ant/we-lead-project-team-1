package gr.athtech.spring.app.repository;

import gr.athtech.spring.app.model.Account;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class AccountRepositoryImpl extends BaseRepositoryImpl<Account> implements AccountRepository {
    private final ConcurrentHashMap<Long, Account> storage = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);

    @Override
    protected ConcurrentHashMap<Long, Account> getStorage() {
        return storage;
    }

    @Override
    protected AtomicLong getSequence() {
        return sequence;
    }

    @Override
    public Account findByEmail(final String email) {
        return storage.values()
                .stream()
                .filter(c -> email.equalsIgnoreCase(c.getEmail()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Account findByPhone(final String phone) {
        return storage.values()
                .stream()
                .filter(c -> phone.equals(c.getTelephoneNumber()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void signup(final String email, final String phone, final String password) {
        if (email != null && findByEmail(email) != null) {
            throw new RuntimeException("Email already exists");
        }

        if (phone != null && findByPhone(phone) != null) {
            throw new RuntimeException("Phone number already exists");
        }

        if (password.length() < 8) {
            throw new RuntimeException("Password must be at least 8 characters long");
        }
    }
}
