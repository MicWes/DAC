/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.T08Message;

/**
 *
 * @author Michael
 */
@Stateless
public class T08MessageFacade extends AbstractFacade<T08Message> {

    @PersistenceContext(unitName = "TADSdeliveryPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public T08MessageFacade() {
        super(T08Message.class);
    }
    
}
