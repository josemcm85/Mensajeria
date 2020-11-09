/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r6.service;

import com.r6.mensajeria.Contacto;
import com.r6.mensajeria.Sistema;
import com.r6.mensajeria.Usuario;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;

/**
 *
 * @author Nvidi
 */
public class ContactoDao implements Dao<Contacto> {

    private static Usuario usuario;

    private static EntityManager em = null;

    @Override
    public Optional<Contacto> get(Integer id) {
        Optional<Contacto> cont = Optional.ofNullable((Contacto) em.createNamedQuery("Contacto.findPk", Contacto.class)
                .setParameter("idParam", id)
                .setParameter("idUserParam", usuario.getIdUsuario())
                .getSingleResult());

        return cont;
    }

    @Override
    public List<Contacto> getAll() {
        List<Contacto> all = em.createNamedQuery("Contacto.findAll", Contacto.class)
                .setParameter("idUserParam", usuario.getIdUsuario()).getResultList();
        return all;
    }

    public List<Contacto> getAllSubs() {
        List<Contacto> all = em.createNamedQuery("Contacto.findSubs", Contacto.class)
                .setParameter("idUserParam", usuario.getIdUsuario()).getResultList();
        return all;
    }

    public Integer maxId() {
        Integer c =  em.createNamedQuery("Contacto.maxId", Integer.class).getSingleResult();
        return c;
    }

    @Override
    public void save(Contacto t) {
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
    }

    @Override
    public void update(Contacto t) {
        em.getTransaction().begin();
        em.merge(t);
        em.getTransaction().commit();

    }

    @Override
    public void delete(Contacto t) {
        em.getTransaction().begin();
        em.remove(t);
        em.getTransaction().commit();
    }

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        ContactoDao.usuario = usuario;
    }

    public static EntityManager getEm() {
        return em;
    }

    public static void setEm(EntityManager em) {
        ContactoDao.em = em;
    }

}
