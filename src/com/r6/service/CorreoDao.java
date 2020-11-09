/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r6.service;

import com.r6.mensajeria.Correo;
import com.r6.mensajeria.Recordatorio;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;

/**
 *
 * @author Daniel
 */
public class CorreoDao implements Dao<Correo> {

    private static EntityManager em = null;

    public void setEm(EntityManager emNew) {
        em = emNew;
    }

    public EntityManager getEm() {
        return em;
    }

    @Override
    public Optional<Correo> get(Integer id) {
        Optional<Correo> correo = Optional.ofNullable(
                (Correo) em.createNamedQuery("Correo.find").setParameter("idParam", id).getSingleResult());
        return correo;
    }

    @Override
    public List<Correo> getAll() {
       @SuppressWarnings("unchecked")
        List<Correo> correos = (List<Correo>) em.createQuery("FROM Correo").getResultList();
        return correos;

    }

    @Override
    public void save(Correo t) {
       em.getTransaction().begin();
       em.persist(t);
       em.getTransaction().commit();

    }

    @Override
    public void update(Correo t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Correo t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
