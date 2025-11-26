package com.stepa7.bank.service;

import com.stepa7.bank.model.dto.AccountDto;
import com.stepa7.bank.model.entity.Account;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    List<Account> getAll();
    Account getById(UUID id);
    Account create(AccountDto dto);
    Account update(UUID id, AccountDto dto);
    void delete(UUID id);
    void validateAccountDto(AccountDto dto);
}
