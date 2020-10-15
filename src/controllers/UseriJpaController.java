/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
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
public class UseriJpaController implements Serializable {

    public UseriJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Useri useri) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(useri);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Useri useri) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            useri = em.merge(useri);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = useri.getId();
                if (findUseri(id) == null) {
                    throw new NonexistentEntityException("The useri with id " + id + " no longer exists.");
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
            Useri useri;
            try {
                useri = em.getReference(Useri.class, id);
                useri.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The useri with id " + id + " no longer exists.", enfe);
            }
            em.remove(useri);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Useri> findUseriEntities() {
        return findUseriEntities(true, -1, -1);
    }

    public List<Useri> findUseriEntities(int maxResults, int firstResult) {
        return findUseriEntities(false, maxResults, firstResult);
    }

    private List<Useri> findUseriEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Useri.class));
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

    public Useri findUseri(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Useri.class, id);
        } finally {
            em.close();
        }
    }

    public int getUseriCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Useri> rt = cq.from(Useri.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Useri findUserByUser(String user){
        
        EntityManager em = getEntityManager();
        Query q = em.createNamedQuery("Useri.findByUser");
        q.setParameter("user", user);
        List<Useri>resultList = q.getResultList();
        if(resultList.isEmpty())return null;
        return resultList.get(0);
        
    }
    
}
