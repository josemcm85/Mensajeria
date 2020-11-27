/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r6.service;

import com.r6.mensajeria.Individuo;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;

/**
 *
 * @author Nvidi
 */
public class IndividuoDao implements Serializable, Dao<Individuo> {

    private EntityManager em = null;

    @Override
    public Optional<Individuo> get(Integer id) {
        Optional<Individuo> returned = Optional.ofNullable((Individuo) em.createNamedQuery("Individuo.findByIdIndividuo", Individuo.class).setParameter("idIndividuo", id).
                getSingleResult());
        return returned;
    }

    public Optional<Individuo> getByNumLic(String numeroLic) {
        Optional<Individuo> returned = Optional.ofNullable((Individuo) em.createNamedQuery("Individuo.findByNumeroLic", Individuo.class).setParameter("numeroLic", numeroLic).
                getSingleResult());
        return returned;
    }

    @Override
    public List<Individuo> getAll() {
        return em.createNamedQuery("Individuo.findAll").getResultList();
    }

    @Override
    public void save(Individuo t) {
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
    }

    @Override
    public void update(Individuo t) {
        em.getTransaction().begin();
        em.merge(t);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Individuo t) {
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
