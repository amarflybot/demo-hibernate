package com.example.demohibernate;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;

@Service
@Slf4j
public class PersonCustomRepo {

    private final EntityManagerFactory entityManagerFactory;
    private final SessionFactory sessionFactory;

    public PersonCustomRepo(final EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        this.sessionFactory = this.entityManagerFactory.unwrap(SessionFactory.class);
    }

    public Person findByName(String name) {
        Session currentSession = null;
        try {
            currentSession = this.sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            log.warn("Cannot find currentSession: {}", e.getMessage());
            currentSession = this.sessionFactory.openSession();
        }
        Query query = currentSession.createQuery("from Person where name = :name");
        query.setParameter("name", name);
        Object uniqueResult = query.uniqueResult();
        return (Person) uniqueResult;

    }
}
