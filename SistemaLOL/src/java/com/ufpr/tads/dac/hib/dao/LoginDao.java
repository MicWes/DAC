/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.dac.hib.dao;

import com.ufpr.tads.dac.hib.HibernateUtil;
import com.ufpr.tads.dac.model.Cliente;
import com.ufpr.tads.dac.model.Funcionario;
import com.ufpr.tads.dac.utils.Encriptador;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Marcos
 */
public class LoginDao {

    public Object auth(String usuario, String senha) {
        senha = Encriptador.toMD5(senha);
        Object item = valida_cli(usuario, senha);
        item = item == null ? valida_func(usuario, senha) : item;
        return item;
    }

    private Object valida_func(String usuario, String senha) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            String hql = "from Funcionario where matricula = '" + usuario
                    + "' and senha = '" + senha + "'";
            Query query = session.createQuery(hql);
            query.setMaxResults(1);
            if (query.uniqueResult() != null) {
                return (Funcionario) query.uniqueResult();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private Object valida_cli(String usuario, String senha) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            String hql = "from Cliente where email = '" + usuario
                    + "' and senha = '" + senha + "'";
            Query query = session.createQuery(hql);
            query.setMaxResults(1);
            if (query.uniqueResult() != null) {
                return (Cliente) query.uniqueResult();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
