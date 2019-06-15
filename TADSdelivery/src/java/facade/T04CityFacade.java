/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.T04City;

/**
 *
 * @author Michael
 */
@Stateless
public class T04CityFacade extends AbstractFacade<T04City> {

    @PersistenceContext(unitName = "TADSdeliveryPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public T04CityFacade() {
        super(T04City.class);
    }
    
}
