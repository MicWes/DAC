package com.ufpr.tads.dac.hib;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Marcos
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static final String CONFIG_LOCATION = "/com/ufpr/tads/dac/hib/hibernate.cfg.xml";
    private static final ThreadLocal currentThread = new ThreadLocal();
    private static HibernateUtil instance;
    
    public static HibernateUtil getInstance() {
        if(instance == null) {
            instance = new HibernateUtil();
        }
        return instance;
    }
    
    public static SessionFactory getSessionFactory() {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration().configure(CONFIG_LOCATION).buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        return sessionFactory;
    }
    
    public Session getSession() {
        Session session = (Session) currentThread.get();
        if(session == null || session.isOpen() == false) {
            session = getSessionFactory().openSession();
            currentThread.set(session);
        }
        return session;
    }
    
}
