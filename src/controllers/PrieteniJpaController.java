/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import dbbeans.Prieteni;
import dbbeans.Useri;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author serge
 */
public class PrieteniJpaController implements Serializable {

    public PrieteniJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Prieteni prieteni) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(prieteni);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Prieteni prieteni) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            prieteni = em.merge(prieteni);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = prieteni.getId();
                if (findPrieteni(id) == null) {
                    throw new NonexistentEntityException("The prieteni with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Prieteni prieteni;
            try {
                prieteni = em.getReference(Prieteni.class, id);
                prieteni.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The prieteni with id " + id + " no longer exists.", enfe);
            }
            em.remove(prieteni);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Prieteni> findPrieteniEntities() {
        return findPrieteniEntities(true, -1, -1);
    }

    public List<Prieteni> findPrieteniEntities(int maxResults, int firstResult) {
        return findPrieteniEntities(false, maxResults, firstResult);
    }

    private List<Prieteni> findPrieteniEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Prieteni.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Prieteni findPrieteni(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Prieteni.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrieteniCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Prieteni> rt = cq.from(Prieteni.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Prieteni findPrietenByNume(String nume){
        EntityManager em = getEntityManager();
        Query q = em.createNamedQuery("Prieteni.findByNume");
        q.setParameter("nume", nume);
        List <Prieteni> prieteni = (List<Prieteni>)q.getResultList();
        
        if(prieteni.isEmpty()) return null;
        return prieteni.get(0);
        
    }
    
    public List<Prieteni> getPrieteniByUser(int user){
        EntityManager em = getEntityManager();
        Query q = em.createNamedQuery("Prieteni.findByUser"); 
        q.setParameter("user", user);
        return q.getResultList();
        
    }
    
}
