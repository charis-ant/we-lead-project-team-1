package gr.athtech.spring.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "ACCOUNTS", indexes = {@Index(columnList = "email")})
@SequenceGenerator(name = "idGenerator", sequenceName = "ACCOUNTS_SEQ", initialValue = 1, allocationSize = 1)
public class Account extends BaseModel {
    @NotNull(message = "Email address cannot be null")
    @Email
    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @NotNull(message = "Password cannot be null")
    private String password;

    @NotNull(message = "First name cannot be null")
    @Column(length = 20, nullable = false)
    private String firstname;

    @NotNull(message = "Last name cannot be null")
    @Column(length = 30, nullable = false)
    private String lastname;

    @Column(length = 10)
    private String telephoneNumber;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private final ArrayList<Address> addresses = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private AccountCategory accountCategory;
}
