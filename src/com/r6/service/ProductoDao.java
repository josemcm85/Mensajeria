/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r6.service;

import com.r6.mensajeria.Producto;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;

/**
 *
 * @author Nvidi
 */
public class ProductoDao implements Serializable, Dao<Producto> {

    private EntityManager em = null;

    public List<Producto> getByNombre(String nombre) {
        return em.createNamedQuery("Producto.findByNombre", Producto.class).setParameter("nombre", nombre).getResultList();
    }

    public List<Producto> getByDescripcion(String descripcion) {
        return em.createNamedQuery("Producto.findByDescripcion", Producto.class).setParameter("descripcion", descripcion).getResultList();
    }

    public List<Producto> getByTipo(int tipo) {
        return em.createNamedQuery("Producto.findByTipo", Producto.class).setParameter("tipo", tipo).getResultList();
    }

    @Override
    public Optional<Producto> get(Integer id) {
        return Optional.ofNullable((Producto) em.createNamedQuery("Producto.findByIdProducto", Producto.class).setParameter("idProducto", id).getSingleResult());
    }

    @Override
    public List<Producto> getAll() {
        return em.createNamedQuery("Producto.findAll", Producto.class).getResultList();
    }

    @Override
    public void save(Producto t) {
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
    }

    @Override
    public void update(Producto t) {
        em.getTransaction().begin();
        em.merge(t);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Producto t) {
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
