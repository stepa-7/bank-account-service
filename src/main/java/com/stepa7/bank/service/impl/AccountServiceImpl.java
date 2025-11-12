package com.stepa7.bank.service.impl;

import com.stepa7.bank.exception.NotFoundException;
import com.stepa7.bank.model.dto.AccountDto;
import com.stepa7.bank.model.entity.Account;
import com.stepa7.bank.repository.AccountRepository;
import com.stepa7.bank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Account getById(UUID id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Account not found"));
    }

    @Transactional
    @Override
    public Account create(AccountDto dto) {
        validateAccountDto(dto);

        Account account = Account.builder()
                .id(UUID.randomUUID())
                .owner(dto.getOwner().trim())
                .currency(dto.getCurrency().toUpperCase().trim())
                .amountCurrency(dto.getAmountCurrency() != null ? dto.getAmountCurrency() : BigDecimal.ZERO)
                .amountRub(dto.getAmountRub() != null ? dto.getAmountRub() : BigDecimal.ZERO)
                .build();

        return accountRepository.save(account);
    }

    @Transactional
    @Override
    public Account update(UUID id, AccountDto dto) {
        validateAccountDto(dto);

        Account account = getById(id);
        account.setOwner(dto.getOwner());
        account.setCurrency(dto.getCurrency());
        account.setAmountCurrency(dto.getAmountCurrency() != null ? dto.getAmountCurrency() : BigDecimal.ZERO);
        account.setAmountRub(dto.getAmountRub() != null ? dto.getAmountRub() : BigDecimal.ZERO);

        return accountRepository.save(account);
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        Account account = getById(id);
        accountRepository.delete(account);
    }

    @Override
    public void validateAccountDto(AccountDto dto) {
        if (dto.getOwner() == null || dto.getOwner().trim().isEmpty()) {
            throw new IllegalArgumentException("Owner is required");
        }
        if (dto.getCurrency() == null || dto.getCurrency().trim().isEmpty()) {
            throw new IllegalArgumentException("Currency is required");
        }
        if (dto.getCurrency().length() != 3) {
            throw new IllegalArgumentException("Currency must be 3 characters");
        }
    }
}
