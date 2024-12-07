package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ClientDemo {
    public static void main(String[] args) {
        // Hibernate configuration
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        cfg.addAnnotatedClass(Department.class);
        
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            // HQL Update Query using Positional Parameters
            String hql = "UPDATE Department SET name = ?1, location = ?2 WHERE departmentId = ?3";
            int updatedCount = session.createQuery(hql)
                    .setParameter(1, "Updated Department Name")
                    .setParameter(2, "Updated Location")
                    .setParameter(3, 1) // Assuming departmentId = 1
                    .executeUpdate();

            System.out.println(updatedCount + " record(s) updated.");

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
