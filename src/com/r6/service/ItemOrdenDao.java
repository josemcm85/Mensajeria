/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r6.service;

import com.r6.mensajeria.Itemorden;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;

/**
 *
 * @author Nvidi
 */
public class ItemOrdenDao implements Serializable, Dao<Itemorden> {

    private EntityManager em = null;

    @Override
    public Optional<Itemorden> get(Integer id) {
        Optional opt = Optional.ofNullable((Itemorden) em.createNamedQuery("Itemorden.findByIdItem", Itemorden.class).setParameter("idItem", id).getSingleResult());
        return opt;
    }

    @Override
    public List<Itemorden> getAll() {
        return em.createNamedQuery("Itemorden.findAll", Itemorden.class).getResultList();
    }

    public List<Itemorden> getByCantidad(int cantidad) {
        return em.createNamedQuery("Itemorden.findByCantidad", Itemorden.class).setParameter("cantidad", cantidad).getResultList();
    }

    public List<Itemorden> getByImpuesto(Double impuesto) {
        return em.createNamedQuery("Itemorden.findByImpuesto", Itemorden.class).setParameter("impuesto", impuesto).getResultList();
    }

    //PrecioUnitario
    public List<Itemorden> getByPrecioUnitario(Double precio) {
        return em.createNamedQuery("Itemorden.findByPrecioUnitario", Itemorden.class).setParameter("precioUnitario", precio).getResultList();
    }

    //Total
    public List<Itemorden> getByTotalItem(Double totalItem) {
        return em.createNamedQuery("Itemorden.findByTotalItem", Itemorden.class).setParameter("totalItem", totalItem).getResultList();
    }

    @Override
    public void save(Itemorden t) {
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
    }

    @Override
    public void update(Itemorden t) {
        em.getTransaction().begin();
        em.merge(t);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Itemorden t) {
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
