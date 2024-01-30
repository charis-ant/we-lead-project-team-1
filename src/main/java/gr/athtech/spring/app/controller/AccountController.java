package gr.athtech.spring.app.controller;

import gr.athtech.spring.app.mapper.AccountMapper;
import gr.athtech.spring.app.mapper.BaseMapper;
import gr.athtech.spring.app.model.Account;
import gr.athtech.spring.app.model.AccountAddress;
import gr.athtech.spring.app.service.AccountService;
import gr.athtech.spring.app.service.BaseService;
import gr.athtech.spring.app.transfer.ApiResponse;
import gr.athtech.spring.app.transfer.resource.AccountResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("accounts")
@RequiredArgsConstructor
public class AccountController extends BaseController<Account, AccountResource> {
    private final AccountService accountService;
    private final AccountMapper accountMapper;

    @Override
    protected BaseService<Account, Long> getBaseService() {
        return accountService;
    }

    @Override
    protected BaseMapper<Account, AccountResource> getMapper() {
        return accountMapper;
    }

    @GetMapping(params = {"email"})
    public ResponseEntity<ApiResponse<AccountResource>> findByEmail(@RequestParam String email) {
        return ResponseEntity.ok(
                ApiResponse.<AccountResource>builder()
                        .data(accountMapper.toResource(accountService.findByEmail(email)))
                        .build());
    }

    @GetMapping(params = {"telemphoneNumber"})
    public ResponseEntity<ApiResponse<AccountResource>> findByTelephoneNumber(@RequestParam String telephoneNumber) {
        return ResponseEntity.ok(
                ApiResponse.<AccountResource>builder()
                        .data(accountMapper.toResource(accountService.findByTelephoneNumber(telephoneNumber)))
                        .build());
    }

    @PostMapping(params = "accountId")
    public ResponseEntity<ApiResponse<AccountResource>> addAddress(@RequestBody final AccountAddress accountAddress,
                                                                   @RequestParam Long accountId) {
        accountService.addAddress(accountId, accountAddress);

        return new ResponseEntity<>(
                getNoCacheHeaders(),
                HttpStatus.ACCEPTED
        );
    }

    @DeleteMapping(params = "accountId, addressId")
    public ResponseEntity<ApiResponse<AccountResource>> removeAddress(@RequestParam Long accountId, @RequestParam Long accountAddressId) {
        accountService.removeAddress(accountId, accountAddressId);
        return new ResponseEntity<>(
                getNoCacheHeaders(),
                HttpStatus.NO_CONTENT
        );
    }

    @PatchMapping(params = "accountId, password")
    public ResponseEntity<ApiResponse<AccountResource>> changePassword(@RequestParam Long accountId, @RequestParam String password) {
        accountService.changePassword(accountId, password);
        return new ResponseEntity<>(
                getNoCacheHeaders(),
                HttpStatus.ACCEPTED
        );
    }

}
