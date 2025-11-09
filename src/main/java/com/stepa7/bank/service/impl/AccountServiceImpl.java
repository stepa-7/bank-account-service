package com.stepa7.bank.service.impl;

import com.stepa7.bank.model.dto.AccountDto;
import com.stepa7.bank.model.entity.Account;
import com.stepa7.bank.repository.AccountRepository;
import com.stepa7.bank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    public List<Account> getAll() {
        return List.of();
    }

    @Override
    public Account getById(UUID id) {
        return null;
    }

    @Override
    public Account create(AccountDto dto) {
        return null;
    }

    @Override
    public Account update(UUID id, AccountDto dto) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
