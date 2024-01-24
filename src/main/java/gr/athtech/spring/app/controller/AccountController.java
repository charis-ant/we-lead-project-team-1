package gr.athtech.spring.app.controller;

import gr.athtech.spring.app.mapper.AccountMapper;
import gr.athtech.spring.app.mapper.BaseMapper;
import gr.athtech.spring.app.model.Account;
import gr.athtech.spring.app.service.AccountService;
import gr.athtech.spring.app.service.BaseService;
import gr.athtech.spring.app.transfer.ApiResponse;
import gr.athtech.spring.app.transfer.resource.AccountResource;
import lombok.RequiredArgsConstructor;
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
}
