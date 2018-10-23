package com.team404.bookstore.dao;

import com.team404.bookstore.entity.CountEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class CountDao {

    private static SessionFactory sessionFactory;

    public CountEntity getCount() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();

        CountEntity countEntity = null;
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM CountEntity ");
            List<CountEntity> list = query.list();
            transaction.commit();
            if(list.size() != 0) {
                countEntity = list.get(0);
            }

        } catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return countEntity;
    }

    public void CountUpdate() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            CountEntity countEntity = getCount();
            int count = countEntity.getCount();
            int newCount = count+1;
            Query query = session.createQuery("UPDATE CountEntity set count = :newCount");
            query.setParameter("newCount", newCount);
            int result = query.executeUpdate();
            System.out.println("Rows affected: " + result);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
