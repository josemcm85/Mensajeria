/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r6.service;

import com.r6.mensajeria.Adjunto;
import com.r6.mensajeria.Bitacora;
import com.r6.mensajeria.Usuario;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;

/**
 *
 * @author Nvidi
 */
public class BitacoraDao implements Dao<Bitacora> {

    private static EntityManager em = null;

    @Override
    public Optional<Bitacora> get(Integer id) {
        Optional<Bitacora> ret = Optional.ofNullable((Bitacora) em.createNamedQuery("Bitacora.findByPk", Bitacora.class)
                .setParameter("idParam", id)
                .getSingleResult());
        return ret;
    }

    @Override
    public List<Bitacora> getAll() {
        List<Bitacora> returned = em.createNamedQuery("Bitacora.findAll", Bitacora.class).getResultList();
        return returned;
    }

    public List<Bitacora> getByUser(String email) {
        List<Bitacora> ret = em.createNamedQuery("Bitacora.findByUser", Bitacora.class)
                .setParameter("emailParam", email)
                .getResultList();
        return ret;
    }

    public List<Bitacora> getByFile(Adjunto t) {
        List<Bitacora> ret = em.createNamedQuery("Bitacora.findByFile", Bitacora.class)
                .setParameter("idParam", t.getId()).getResultList();
        return ret;
    }

    public List<Bitacora> getByDateRange(Date fIni, Date fFin) {
        List<Bitacora> ret = em.createNamedQuery("Bitacora.findByDateRange", Bitacora.class)
                .setParameter("fIniParam", fIni)
                .setParameter("fFinParam", fFin)
                .getResultList();
        return ret;
    }

    public List<Bitacora> getByUserAndSys(String paramString1, String paramString2) {
        return em.createNamedQuery("Bitacora.findByUserAndSys", Bitacora.class).setParameter("idUserParam", paramString1).setParameter("idSysParam", paramString2).getResultList();
    }

    @Override
    public void save(Bitacora t) {
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
    }

    @Override
    public void update(Bitacora t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public void delete(Bitacora t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static EntityManager getEm() {
        return em;
    }

    public static void setEm(EntityManager em) {
        BitacoraDao.em = em;
    }

}
