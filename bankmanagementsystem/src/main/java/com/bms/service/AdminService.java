package com.bms.service;

import com.bms.Account;
import com.bms.Transactionn;
import com.bms.dao.AccountDao;
import com.bms.dao.TransactionnDao;
import com.bms.exception.AdminException;

import java.util.List;

public class AdminService {
    private AccountDao accountDao;
    private TransactionnDao transactionnDao;

    // Admin credentials
    private static final String ADMIN_USERNAME = "Admin";
    private static final String ADMIN_PASSWORD = "Admin@123";

    public AdminService(AccountDao accountDao, TransactionnDao transactionnDao) {
        this.accountDao = accountDao;
        this.transactionnDao = transactionnDao;
    }

    public boolean authenticate(String username, String password) throws AdminException {
        if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
            return true;
        } else {
            throw new AdminException("Invalid Admin Credentials!");
        }
    }

    public List<Account> getAllAccounts() {
        return accountDao.getAllAccounts();
    }

    public List<Transactionn> getAllTransactions() {
        return transactionnDao.getAllTransactionns();
    }

    public boolean deleteAccountByEmail(String email) {
        return accountDao.deleteAccountByEmail(email);
    }
}
