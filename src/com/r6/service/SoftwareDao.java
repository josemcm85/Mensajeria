package com.r6.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.r6.mensajeria.Producto;
import com.r6.mensajeria.Software;

public class SoftwareDao implements Serializable, Dao<Software>{
	
	private static EntityManager em = null;

	@Override
	public Optional<Software> get(Integer id) {
		Optional<Software> softwareID = Optional.ofNullable((Software) em.createNamedQuery("Software.findByIdSoftware").setParameter("idSoftware", id).getSingleResult());
		return softwareID;
	}
	
	public List<Software> getByLicencia(String licencia) {
		return em.createNamedQuery("Software.findByLicencia", Software.class).setParameter("licencia", licencia).getResultList();
	}

	@Override
	public List<Software> getAll() {
		TypedQuery<Software> softwareList = em.createNamedQuery("Software.findAll", Software.class);
        return softwareList.getResultList();
	}

	@Override
	public void save(Software t) {
		em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
	}
	
	@Override
	public void update(Software t) {
		em.getTransaction().begin();
        em.merge(t);
        em.getTransaction().commit();
	}
	
	@Override
	public void delete(Software t) {
		em.getTransaction().begin();
        em.remove(t);
        em.getTransaction().commit();
	}

	public static EntityManager getEm() {
		return em;
	}

	public static void setEm(EntityManager em) {
		SoftwareDao.em = em;
	}
	
	
	
}
