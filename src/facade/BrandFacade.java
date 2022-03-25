/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Brand;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author user
 */
public class BrandFacade extends AbstractFacade<Brand>{
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("VanjaGuiPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BrandFacade(Class<Brand> entityClass) {
        super(entityClass);
    }
    
}
