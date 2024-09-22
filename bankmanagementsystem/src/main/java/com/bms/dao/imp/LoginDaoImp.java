package com.bms.dao.imp;

import com.bms.dao.LoginDao;
import com.bms.Login;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class LoginDaoImp implements LoginDao {
    private SessionFactory factory;

    public LoginDaoImp() {
        factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    @Override
    public void saveLogin(Login login) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(login);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public Login getLogin(String email) {
        Session session = factory.openSession();
        Login login = null;
        try {
            login = session.get(Login.class, email);
        } finally {
            session.close();
        }
        return login;
    }

    @Override
    public List<Login> getAllLogins() {
        Session session = factory.openSession();
        List<Login> logins = null;
        try {
            logins = session.createQuery("from Login", Login.class).list();
        } finally {
            session.close();
        }
        return logins;
    }

    @Override
    public void updateLogin(Login login) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(login);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteLogin(String email) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Login login = session.get(Login.class, email);
            if (login != null) {
                session.delete(login);
                transaction.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    public void close() {
        factory.close();
    }
}

