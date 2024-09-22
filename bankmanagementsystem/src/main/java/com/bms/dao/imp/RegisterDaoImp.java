package com.bms.dao.imp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.bms.Register;
import com.bms.dao.RegisterDao;

import java.util.List;

public class RegisterDaoImp implements RegisterDao {
    private SessionFactory sessionFactory; // Keep it f2inal

    // Constructor to initialize sessionFactory
    public RegisterDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory; // Initialize here
    }

    @Override
    public void saveRegister(Register register) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(register);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Register getRegisterByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            Query<Register> query = session.createQuery("FROM Register WHERE email = :email", Register.class);
            query.setParameter("email", email);
            return query.uniqueResult();
        }
    }

    @Override
    public int getLastUsernameID() {
        try (Session session = sessionFactory.openSession()) {
            Integer lastUsernameID = (Integer) session.createQuery("SELECT COALESCE(MAX(r.usernameID), 0) FROM Register r")
                                                       .uniqueResult();
            return lastUsernameID != null ? lastUsernameID : 0;
        }
    }

    @Override
    public Register getRegister(int usernameID) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Register.class, usernameID);
        }
    }

    @Override
    public List<Register> getAllRegisters() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Register";
            Query<Register> query = session.createQuery(hql, Register.class);
            return query.list();
        }
    }

    @Override
    public void updateRegister(Register register) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(register);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRegister(int usernameID) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Register register = session.get(Register.class, usernameID);
            if (register != null) {
                session.delete(register);
                System.out.println("Register deleted successfully");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
