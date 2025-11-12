package com.stepa7.bank.controller;

import com.stepa7.bank.model.dto.AccountDto;
import com.stepa7.bank.model.entity.Account;
import com.stepa7.bank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
//@ConditionalOnProperty()
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<List<Account>> getAccounts() {
        return ResponseEntity.ok(accountService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable @Valid UUID id) {
        return ResponseEntity.ok(accountService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody @Valid AccountDto dto) {
        Account created = accountService.create(dto);
        return ResponseEntity.ok(created);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable @Valid UUID id, @RequestBody @Valid AccountDto dto) {
        Account updated = accountService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable @Valid UUID id) {
        accountService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
