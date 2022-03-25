package tools;

import entity.Brand;
import entity.History;
import entity.Sneaker;
import entity.Buyer;
import interfaces.Keeping;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class SaverToBase implements Keeping {
    private EntityManagerFactory emf=Persistence.createEntityManagerFactory("VanjaGuiPU");
    private EntityManager em=emf.createEntityManager();
    private EntityTransaction tx=em.getTransaction();
    
    @Override
    public void saveSneaker(List<Sneaker> models) {
        tx.begin();
        for (int i = 0;i< models.size(); i++) {
            if (models.get(i).getId()==null) {
                em.persist(models.get(i));
            }
        }
        tx.commit();
    }
    @Override
    public List<Sneaker> loadSneaker() {
        List<Sneaker> sneakers=null;
        try{
            return em.createQuery("SELECT m FROM Sneaker m").getResultList();
        }catch(Exception e){
            sneakers=new ArrayList<>();
        }
        return sneakers;
    }
    @Override
    public void saveBuyers(List<Buyer> users) {
        tx.begin();
        for (int i = 0;i< users.size(); i++) {
            if (users.get(i).getId()==null) {
                em.persist(users.get(i));
            }
        }
        tx.commit();
    }
    @Override
    public List<Buyer> loadBuyers() {
        List<Buyer> buyers=null;
        try{
            return em.createQuery("SELECT u FROM Buyer u").getResultList();
        }catch(Exception e){
            buyers=new ArrayList<>();
        }
        return buyers;
    }
    @Override
    public void saveHistory(List<History> histories) {
        tx.begin();
        for (int i = 0;i< histories.size(); i++) {
            if (histories.get(i).getId()==null) {
                em.persist(histories.get(i));
            }
        }
        tx.commit();
    }
    @Override
    public List<History> loadHistory() {
        List<History> histories=null;
        try{
            return em.createQuery("SELECT h FROM History h").getResultList();
        }catch(Exception e){
            histories=new ArrayList<>();
        }
        return histories;
    }
    @Override
    public void saveBrand(List<Brand> brands) {
        tx.begin();
        for (int i = 0;i< brands.size(); i++) {
            if (brands.get(i).getId()==null) {
                em.persist(brands.get(i));
            }
        }
        tx.commit();
    }
    @Override
    public List<Brand> loadBrand() {
        List<Brand> brands=null;
        try{
            return em.createQuery("SELECT h FROM Brand h").getResultList();
        }catch(Exception e){
            brands=new ArrayList<>();
        }
        return brands;
    }

}
