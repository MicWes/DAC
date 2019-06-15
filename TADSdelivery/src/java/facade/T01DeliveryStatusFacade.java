/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.T01DeliveryStatus;

/**
 *
 * @author Michael
 */
@Stateless
public class T01DeliveryStatusFacade extends AbstractFacade<T01DeliveryStatus> {

    @PersistenceContext(unitName = "TADSdeliveryPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public T01DeliveryStatusFacade() {
        super(T01DeliveryStatus.class);
    }
    
}
