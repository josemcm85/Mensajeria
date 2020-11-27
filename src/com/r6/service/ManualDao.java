package com.r6.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import com.r6.mensajeria.Manual;
import com.r6.mensajeria.Producto;

public class ManualDao implements Serializable, Dao<Manual>{
	
	private static EntityManager em = null;

	@Override
	public Optional<Manual> get(Integer id) {
		Optional<Manual> maunualID = Optional.ofNullable((Manual) em.createNamedQuery("Manual.findByIdManual").setParameter("idManual", id).getSingleResult());
		return maunualID;
	}
	
	public List<Manual> getByAutor(String autor) {
		return em.createNamedQuery("Manual.findByAutor", Manual.class).setParameter("autor", autor).getResultList();
	}

	@Override
	public List<Manual> getAll() {
		TypedQuery<Manual> manualList = em.createNamedQuery("Manual.findAll", Manual.class);
        return manualList.getResultList();
	}

	@Override
	public void save(Manual t) {
		em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
		
	}

	@Override
	public void update(Manual t) {
		em.getTransaction().begin();
        em.merge(t);
        em.getTransaction().commit();
		
	}

	@Override
	public void delete(Manual t) {
		em.getTransaction().begin();
        em.remove(t);
        em.getTransaction().commit();
	}

	public static EntityManager getEm() {
		return em;
	}

	public static void setEm(EntityManager em) {
		ManualDao.em = em;
	}
	
	

}
