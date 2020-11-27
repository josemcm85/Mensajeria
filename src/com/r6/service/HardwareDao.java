package com.r6.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import com.r6.mensajeria.Hardware;
import com.r6.mensajeria.Producto;

public class HardwareDao implements Serializable, Dao<Hardware>{
	
	private static EntityManager em = null;

	@Override
	public Optional<Hardware> get(Integer id) {
		Optional<Hardware> hardwareID = Optional.ofNullable((Hardware) em.createNamedQuery("Hardware.findByIdHardware").setParameter("idHardware", id).getSingleResult());
		return hardwareID;
	}
	
	public List<Hardware> getByPeriodoGarantia(int periodoGarantia) {
		return em.createNamedQuery("Hardware.findByPeriodoGarantia", Hardware.class).setParameter("periodoGarantia", periodoGarantia).getResultList();
	}
	
	public List<Hardware> getByImpuesto(Double impuesto) {
		return em.createNamedQuery("Hardware.findByImpuesto", Hardware.class).setParameter("impuesto", impuesto).getResultList();
	}

	@Override
	public List<Hardware> getAll() {
		TypedQuery<Hardware> hardwareList = em.createNamedQuery("Hardware.findAll", Hardware.class);
        return hardwareList.getResultList();
	}

	@Override
	public void save(Hardware t) {
		em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
	}

	@Override
	public void update(Hardware t) {
		em.getTransaction().begin();
        em.merge(t);
        em.getTransaction().commit();
		
	}

	@Override
	public void delete(Hardware t) {
		em.getTransaction().begin();
        em.remove(t);
        em.getTransaction().commit();
	}

	public static EntityManager getEm() {
		return em;
	}

	public static void setEm(EntityManager em) {
		HardwareDao.em = em;
	}
	
	

}
