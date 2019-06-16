/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.T06Delivery;

/**
 *
 * @author Michael
 */
@Stateless
public class T06DeliveryFacade extends AbstractFacade<T06Delivery> {

    @PersistenceContext(unitName = "TADSdeliveryPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public T06DeliveryFacade() {
        super(T06Delivery.class);
    }
    
}
