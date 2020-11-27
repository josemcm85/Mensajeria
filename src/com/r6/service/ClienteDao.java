/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r6.service;

import com.r6.mensajeria.Cliente;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;

/**
 *
 * @author Nvidi
 */
public class ClienteDao implements Serializable, Dao<Cliente> {

    private EntityManager em;

    @Override
    public Optional<Cliente> get(Integer id) {
        Optional<Cliente> returned = Optional.ofNullable((Cliente) em.createNamedQuery("Cliente.findByIdCliente", Cliente.class).setParameter("idCliente", id).
                getSingleResult());
        return returned;
    }

    public Optional<Cliente> getByNombre(String nombre) {
        Optional<Cliente> returned = Optional.ofNullable((Cliente) em.createNamedQuery("Cliente.findByNombre", Cliente.class).setParameter("nombre", nombre).
                getSingleResult());
        return returned;
    }

    public List<Cliente> getByTelefono(String telefono) {
        return  em.createNamedQuery("Cliente.findByTelefono", Cliente.class).setParameter("telefono", telefono).getResultList();

    }

    public List<Cliente> getByDireccion(String telefono) {
        return em.createNamedQuery("Cliente.findByDireccion", Cliente.class).setParameter("direccion", telefono).getResultList();

    }

    public List<Cliente> getByTipo(int tipo) {
        return em.createNamedQuery("Cliente.findByTipo", Cliente.class).setParameter("tipo", tipo).getResultList();
    }

    @Override
    public List<Cliente> getAll() {
        return em.createNamedQuery("Cliente.findAll",Cliente.class).getResultList();

    }

    @Override
    public void save(Cliente t) {
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
    }

    @Override
    public void update(Cliente t) {
        em.getTransaction().begin();
        em.merge(t);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Cliente t) {
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
