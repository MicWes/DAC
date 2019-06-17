/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.T05User;
import org.hibernate.Query;
import org.hibernate.Session;
import util.Encriptador;
import util.HibernateUtil;

/**
 *
 * @author Michael
 */
public class LoginDao {

    public Object auth(String login, String password) {
        password = Encriptador.toMD5(password);
        Object user = valida_login(login, password);
        return user;
    }

    private Object valida_login(String login, String password) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            String hql = "from T05User where userLogin = '" + login
                    + "' and userPassword = '" + password + "'";
            Query query = session.createQuery(hql);
            query.setMaxResults(1);
            if (query.uniqueResult() != null) {
                return (T05User) query.uniqueResult();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}