/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.dac.hib.dao;

import com.ufpr.tads.dac.hib.HibernateUtil;
import com.ufpr.tads.dac.model.Cliente;
import com.ufpr.tads.dac.model.Funcionario;
import com.ufpr.tads.dac.model.Pedido;
import com.ufpr.tads.dac.model.Roupa;
import java.util.ArrayList;
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
            Session session = HibernateUtil.getInstance().getSession();
            Transaction tx = session.beginTransaction();
            try {
                session.save(item);

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
        return id;
    }

    public static void alterar(Object item) {
        try {
            Session session = HibernateUtil.getInstance().getSession();
            Transaction tx = session.beginTransaction();
            try {
                session.merge(item);
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

    public static void excluir(Object item) {
        try {
            Session session = HibernateUtil.getInstance().getSession();
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

    public static Object getById(Class c, String attribute, int valor) {
        Object item = null;
        try {
            Session session = HibernateUtil.getInstance().getSession();
            Criteria cr = session.createCriteria(c);
            cr.add(Restrictions.eq(attribute, valor));
            cr.setMaxResults(1);
            item = cr.uniqueResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return item;
    }

    public static List getList(Class c, String attributeOrder) {
        List lista = new ArrayList();
        try {
            Session session = HibernateUtil.getInstance().getSession();
            Criteria cr = session.createCriteria(c);
            if (attributeOrder != null) {
                cr.addOrder(Order.asc(attributeOrder));
            }
            lista = cr.list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public static List getList(Class c) {
        return getList(c, null);
    }

}
