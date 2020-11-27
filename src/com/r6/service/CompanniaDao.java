/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r6.service;

import com.r6.mensajeria.Compannia;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;

/**
 *
 * @author Nvidi
 */
public class CompanniaDao implements Serializable, Dao<Compannia> {

    private EntityManager em;

    @Override
    public Optional<Compannia> get(Integer id) {
        Optional<Compannia> opt = Optional.ofNullable((Compannia) em.createNamedQuery("Compannia.findByIdCompannia",Compannia.class).setParameter("idCompannia", id).getSingleResult());
        return opt;
    }

    public List<Compannia> getByDescuento(int descuento) {
        return em.createNamedQuery("Compannia.findByDescuento",Compannia.class).setParameter("descuento", descuento).getResultList();
    }

    public Optional<Compannia> getByContacto(String contacto) {
        Optional<Compannia> opt = Optional.ofNullable((Compannia) em.createNamedQuery("Compannia.findByContacto",Compannia.class).setParameter("contacto", contacto).getSingleResult());
        return opt;
    }

    @Override
    public List<Compannia> getAll() {
        return em.createNamedQuery("Compannia.findAll",Compannia.class).getResultList();
    }

    @Override
    public void save(Compannia t) {
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
    }

    @Override
    public void update(Compannia t) {
        em.getTransaction().begin();
        em.merge(t);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Compannia t) {
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
