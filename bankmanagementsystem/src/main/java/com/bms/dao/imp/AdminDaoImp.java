package com.bms.dao.imp;

import com.bms.Admin;
import com.bms.dao.AdminDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AdminDaoImp implements AdminDao {
    private SessionFactory sessionFactory;

    public AdminDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Admin getAdmin(String username) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Admin.class, username);
        }
    }
}
