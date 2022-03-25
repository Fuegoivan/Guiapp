/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Sneaker;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author user
 */
public class SneakerFacade extends AbstractFacade<Sneaker>{
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("VanjaGuiPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SneakerFacade(Class<Sneaker> entityClass) {
        super(entityClass);
    }
    
}
