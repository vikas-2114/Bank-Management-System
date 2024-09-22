package com.bms.dao;

import com.bms.Account;
import java.util.List;

public interface AccountDao {
    void saveAccount(Account account);
    Account getAccount(long accountNumber);
    List<Account> getAllAccounts();
    void updateAccount(Account account);
    void deleteAccount(long accountNumber);
    Account getAccountByEmail(String email);
    Account getAccountByNumber(long accountNumber);
    Account getAccountByID(long accountID);
    boolean deleteAccountByEmail(String email);
    long getMaxAccountID(); // New method to get the maximum account ID
}
