/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.T07Action;

/**
 *
 * @author Michael
 */
@Stateless
public class T07ActionFacade extends AbstractFacade<T07Action> {

    @PersistenceContext(unitName = "TADSdeliveryPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public T07ActionFacade() {
        super(T07Action.class);
    }
    
}
