package com.tekarch.AccountMSv.Services.Interface;

import com.tekarch.AccountMSv.Models.Accounts;

import java.util.List;

public interface AccountService {
    List<Accounts> getAllAccounts();
    Accounts getAccountById(Long accountId);
    Accounts addAccount(Accounts accounts);
    void deleteAccount(Long accountId);
    Accounts updateAccount(Accounts accounts);
}
