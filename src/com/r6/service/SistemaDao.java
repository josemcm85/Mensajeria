package com.r6.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.r6.mensajeria.Sistema;
public class SistemaDao implements Dao<Sistema> {

	private static EntityManager em = null;
	
	@Override
	public Optional<Sistema> get(Integer id) {

		Optional<Sistema> sist=Optional.ofNullable((Sistema)em.createNamedQuery("Sistema.find").setParameter("idParam",id).
				getSingleResult());
		
		return sist;
	}
	@Override
	public List<Sistema> getAll() {
		TypedQuery<Sistema> sistList = em.createNamedQuery("Sistema.findAll", Sistema.class);
		return sistList.getResultList();
	}

	@Override
	public void save(Sistema t) {
	
	em.getTransaction().begin();
	em.persist(t);
	em.getTransaction().commit();
	
	}

	@Override
	public void update(Sistema t) {
	em.getTransaction().begin();
	em.merge(t);
	em.getTransaction().commit();
		
	}

	public void EnableDisable(Sistema t) {
		if(t.getActivo()) {
			t.setActivo(false);
		}else {
			t.setActivo(true);
		}
		
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();		
		
	}
	
	
	public static EntityManager getEm() {
		return em;
	}

	public static void setEm(EntityManager em) {
		SistemaDao.em = em;
	}
	
	
	public void delete(Sistema t) {
		// TODO Auto-generated method stub
		
	}

	

}
