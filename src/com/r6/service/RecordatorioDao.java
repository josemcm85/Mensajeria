package com.r6.service;

import com.r6.mensajeria.Correo;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.r6.mensajeria.Recordatorio;
import com.r6.mensajeria.Sistema;

public class RecordatorioDao implements Dao<Recordatorio> {

    private static EntityManager em = null;

    public void setEm(EntityManager emNew) {
        em = emNew;
    }

    public EntityManager getEm() {
        return em;
    }

    
    public Recordatorio getById(int id) {
        return em.getReference(Recordatorio.class, id);
    }
    
    public List<Recordatorio> getByMail(Correo correo){
    	TypedQuery<Recordatorio> recList =em.createNamedQuery("Recordatorio.findByMailId",Recordatorio.class).setParameter("correoParam",correo);
    	
    	return recList.getResultList();
    }
    
    @Override
    public Optional<Recordatorio> get(Integer id) {
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Recordatorio> getAll() {

        @SuppressWarnings("unchecked")
        List<Recordatorio> recordatorios = (List<Recordatorio>) em.createQuery("FROM Recordatorio").getResultList();
        return recordatorios;

    }

    @Override
    public void save(Recordatorio t) {
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();

    }

    @Override
    public void update(Recordatorio t) {
       em.getTransaction().begin();
	em.merge(t);
	em.getTransaction().commit();

    }

    @Override
    public void delete(Recordatorio t) {
        em.getTransaction().begin();
	em.remove(t);
	em.getTransaction().commit();


    }

}
