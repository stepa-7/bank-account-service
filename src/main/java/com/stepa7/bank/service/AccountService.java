package com.stepa7.bank.service;

import com.stepa7.bank.model.dto.AccountDto;
import com.stepa7.bank.model.entity.Account;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    @Transactional(readOnly = true)
    List<Account> getAll();

    @Transactional(readOnly = true)
    Account getById(UUID id);

    @Transactional
    Account create(AccountDto dto);

    @Transactional
    Account update(UUID id, AccountDto dto);

    @Transactional
    void delete(UUID id);
}
