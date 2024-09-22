package com.bms.dao.imp;

import com.bms.dao.TransactionnDao;
import com.bms.Transactionn;
import com.bms.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class TransactionnDaoImp implements TransactionnDao {
    private SessionFactory sessionFactory;

    // Constructor
    public TransactionnDaoImp(SessionFactory sessionFactory2) {
        this.sessionFactory = HibernateUtil.getSessionFactory(); // Initialize sessionFactory from HibernateUtil
    }

    @Override
    public void saveTransactionn(Transactionn transactionn) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction(); // Begin a new transaction

            session.save(transactionn); // Save the transaction record

            transaction.commit(); // Commit the transaction
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Rollback transaction in case of error
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close(); // Always close the session
            }
        }
    }

    @Override
    public Transactionn getTransactionn(int transactionID) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Transactionn.class, transactionID);
        }
    }

    @Override
    public List<Transactionn> getAllTransactionns() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Transactionn", Transactionn.class).list();
        }
    }

    @Override
    public void updateTransactionn(Transactionn transactionn) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(transactionn);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace(); // Consider using a logging framework instead
        }
    }

    @Override
    public void deleteTransactionn(int transactionID) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Transactionn transactionn = session.get(Transactionn.class, transactionID);
            if (transactionn != null) {
                session.delete(transactionn);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace(); // Consider using a logging framework instead
        }
    }

    @Override
    public void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
