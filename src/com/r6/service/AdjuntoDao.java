/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r6.service;

import com.r6.mensajeria.Adjunto;
import com.r6.mensajeria.Sistema;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Nvidi
 */
public class AdjuntoDao implements Dao<Adjunto> {

    private static EntityManager em = null;

    @Override
    public Optional<Adjunto> get(Integer id) {
		Optional<Adjunto> adj=Optional.ofNullable((Adjunto)em.createNamedQuery("Adjunto.find").setParameter("idParam",id).
				getSingleResult());
		
		return adj;
    }

    @Override
    public List<Adjunto> getAll() {
		TypedQuery<Adjunto> AdjuntoList = em.createNamedQuery("Adjunto.findAll", Adjunto.class);
		return AdjuntoList.getResultList();
    }

    public List<Adjunto> getAllFiles() {
		TypedQuery<Adjunto> AdjuntoList = em.createNamedQuery("Adjunto.findAllFiles", Adjunto.class);
		return AdjuntoList.getResultList();
    }
    
    @Override
    public void save(Adjunto t) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
    }

    @Override
    public void update(Adjunto t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public void delete(Adjunto t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static EntityManager getEm() {
        return em;
    }

    public static void setEm(EntityManager em) {
        AdjuntoDao.em = em;
    }
    
    
}
