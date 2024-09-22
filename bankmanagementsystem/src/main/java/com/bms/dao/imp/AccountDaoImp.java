package com.bms.dao.imp;

import com.bms.Account;
import com.bms.dao.AccountDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AccountDaoImp implements AccountDao {
    private SessionFactory sessionFactory;

    public AccountDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveAccount(Account account) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(account);
            transaction.commit();
        }
    }

    @Override
    public Account getAccount(long accountID) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Account.class, accountID);
        }
    }

    @Override
    public List<Account> getAllAccounts() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Account", Account.class).list();
        }
    }

    @Override
    public void updateAccount(Account account) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(account);
            transaction.commit();
        }
    }

    @Override
    public void deleteAccount(long accountID) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Account account = session.get(Account.class, accountID);
            if (account != null) {
                session.delete(account);
            }
            transaction.commit();
        }
    }

    @Override
    public Account getAccountByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            Query<Account> query = session.createQuery("from Account where email = :email", Account.class);
            query.setParameter("email", email);
            return query.uniqueResult();
        }
    }

    @Override
    public Account getAccountByNumber(long accountID) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Account where accountID = :accountID", Account.class)
                          .setParameter("accountID", accountID)
                          .uniqueResult();
        }
    }

    @Override
    public Account getAccountByID(long accountID) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Account.class, accountID);
        }
    }
    
   
    @Override
    public long getMaxAccountID() {
        try (Session session = sessionFactory.openSession()) {
            // Retrieve the maximum accountID from the database
            Query<Long> query = session.createQuery("SELECT MAX(a.accountID) FROM Account a", Long.class);
            Long maxAccountID = query.uniqueResult();

            // If no accountID is found, start from 1001000
            return (maxAccountID != null) ? maxAccountID : 1001000L;
        } catch (Exception e) {
            e.printStackTrace();
            // In case of an error, return a default value or handle it accordingly
            return 1001000L;
        }
    }




    
    @Override
    public boolean deleteAccountByEmail(String email) {
        boolean isDeleted = false;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Account account = getAccountByEmail(email);
            if (account != null) {
                session.delete(account);
                isDeleted = true;
                System.out.println("Account with email " + email + " has been deleted.");
            } else {
                System.out.println("No account found with email " + email);
            }
            transaction.commit();
        }
        return isDeleted;
    }
}
