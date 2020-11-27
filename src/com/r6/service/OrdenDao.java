/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r6.service;

import com.r6.mensajeria.Orden;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;

/**
 *
 * @author Nvidi
 */
public class OrdenDao  implements  Serializable, Dao<Orden>{

    private EntityManager em = null;
    
    @Override
    public Optional<Orden> get(Integer id) {
        Optional opt = Optional.ofNullable((Orden)em.createNamedQuery("Orden.findByIdOrden",Orden.class).setParameter("idOrden", id).getSingleResult());
        return opt;
    }
    
    
    public List<Orden> getByFechaOrden(Calendar fechaOrden){
        return em.createNamedQuery("Orden.findByFechaOrden",Orden.class).setParameter("fechaOrden", fechaOrden.getTime()).getResultList();
    }
    
    public List<Orden> getByTotal(Double total){
        return em.createNamedQuery("Orden.findByTotalOrden",Orden.class).setParameter("totalOrden", total).getResultList();
    }
    
    @Override
    public List<Orden> getAll() {
        return em.createNamedQuery("Orden.findAll",Orden.class).getResultList();
    }

    @Override
    public void save(Orden t) {
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
    }

    @Override
    public void update(Orden t) {
        em.getTransaction().begin();
        em.merge(t);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Orden t) {
        em.getTransaction().begin();
        em.remove(t);
        em.getTransaction().commit();
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
    
    
}
