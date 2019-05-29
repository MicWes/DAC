/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.dac.hib.dao;

import com.ufpr.tads.dac.hib.HibernateUtil;
import org.hibernate.SessionFactory;

/**
 *
 * @author Marcos
 */
public class ClienteDao {
    
    private final SessionFactory factory = HibernateUtil.getSessionFactory();

    
}
