/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.dac.hib.dao;

import com.ufpr.tads.dac.hib.HibernateUtil;
import com.ufpr.tads.dac.model.Funcionario;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Marcos
 */
public class GenericDao {

    public static int inserir(Object item) {
        int id = 0;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            try {
                id = (int) session.save(item);
                tx.commit();
            } catch (Exception ex) {
                ex.printStackTrace();
                tx.rollback();
            } finally {
                if (session.isOpen()) {
                    session.close();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (item instanceof Funcionario) {
            return 333;
        } else {
            return id;
        }
    }

    public static int alterar(Object item) {
        Object ck = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            try {
                ck = (Object) session.merge(item);
                tx.commit();
            } catch (Exception ex) {
                tx.rollback();
                ex.printStackTrace();
            } finally {
                if (session.isOpen()) {
                    session.close();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (ck != null) {
            return 1;
        } else {
            return 0;
        }
    }

    public static void excluir(Object item) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            try {
                session.refresh(item);
                session.delete(item);
                tx.commit();
            } catch (Exception ex) {
                tx.rollback();
                ex.printStackTrace();
            } finally {
                if (session.isOpen()) {
                    session.close();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Object getBy(Class c, String attribute, String value) {
        Object item = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Criteria cr = session.createCriteria(c);
            cr.add(Restrictions.eq(attribute, value));
            cr.setMaxResults(1);
            item = cr.uniqueResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return item;
    }

    public static Object getByInt(Class c, String attribute, int valor) {
        Object item = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Criteria cr = session.createCriteria(c);
            cr.add(Restrictions.eq(attribute, valor));
            cr.setMaxResults(1);
            item = cr.uniqueResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return item;
    }

    public static List getListBy(Class c, String attribute, String param,
            String attributeOrder) {
        List lista = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Criteria cr = session.createCriteria(c);
            if (param != null && attribute != null) {
                cr.add(Restrictions.eq(attribute, param));
            }
            if (attributeOrder != null) {
                cr.addOrder(Order.asc(attributeOrder));
            }
            lista = cr.list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public static List getListByInt(Class c, int id, String attribute, String attributeOrder) {
        List lista = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Criteria cr = session.createCriteria(c);
            cr.add(Restrictions.eq(attribute, id));
            if (attributeOrder != null) {
                cr.addOrder(Order.desc(attributeOrder));
            }
            lista = cr.list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public static List getList(Class c, String attributeOrder) {
        return getListBy(c, null, null, attributeOrder);
    }

    public static List getList(Class c) {
        return getList(c, null);
    }

}
