/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.dac.hib.dao;

import com.ufpr.tads.dac.hib.HibernateUtil;
import com.ufpr.tads.dac.model.Funcionario;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Marcos
 */
public class LoginDao {

    public Object auth(String usuario, String senha) {
        Object item = valida_cli(usuario, senha);
        item = item == null ? valida_func(usuario, senha) : item;
        return item;
    }

    private Object valida_func(String usuario, String senha) {
        if (usuario.toLowerCase().equals(usuario.toUpperCase())) {
            try {
                Session session = HibernateUtil.getInstance().getSession();
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
        }
        return null;
    }

    private Object valida_cli(String usuario, String senha) {
        try {
            Session session = HibernateUtil.getInstance().getSession();
            String hql = "from Cliente where email = '" + usuario
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

    private String md5(String s) {
        String md5 = null;
        if (null == s) {
            return null;
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes(), 0, s.length());
            md5 = new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5;
    }

}
