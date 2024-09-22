package com.bms;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        System.out.println(factory);
        System.out.println(factory.isClosed());

        Session session = factory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try {
            // Create objects
//            Register reg = new Register();
//            reg.setUsernameID(1001);
//            reg.setEmail("vikas@gmail.com");
//            reg.setPassword(1234);
//            reg.setContactNo(9899989998L);
//
//            Login lg = new Login();
//            lg.setEmail("satyam@gmail.com");
//            lg.setPassword(1111);

//            Account ac = new Account();
//            ac.setAccountID(0001);
//            ac.setName("kumkum");
//            ac.setEmail("kumkum@gmail.com");
//            ac.setPassword(3019);
//            ac.setContactNo(8585404045L);
//            ac.setAddress("santosh nagar, goregaon (east)");
//            ac.setDateOfBirth("28/09/2001");
//            ac.setAccountType("Saving Account");
//            ac.setAadharNo(123456780009L);
//            ac.setPancardNo("PANN3440N");
            
            
//            Transactionn trans = new Transactionn();
//            trans.setTransactionID(1);
//            trans.setCreditMoney(5000);
//            trans.setDebitMoney(500);
//            trans.setTransferMoney(500);
//            trans.setCheckBalance(4000);

            //System.out.println(ac);
        	
//        	Admin admin = new Admin();
//        	admin.setUsername("Admin");
//        	admin.setPassword("Admin@2114");
//        	System.out.println(admin);
//        	
            // Save objects
            
//            Register
//            session.save(reg);
            
//            Login
//            session.save(lg);
            
//            Account
//            session.save(ac);
            
//            Transactionn
//            session.save(trans);
        	
//        	 Admin
//        	session.save(admin);
        	
        	
            // Commit transaction
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            // Rollback transaction if exception occurs
            transaction.rollback();
        } finally {
            // Close session and factory
            session.close();
            factory.close();
        }

        System.out.println("Successfully done...");
    }
}