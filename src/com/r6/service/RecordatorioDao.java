package com.r6.service;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import com.r6.mensajeria.Recordatorio;





public class RecordatorioDao implements Dao<Recordatorio> {
	
	private static EntityManager em = null;

	@Override
	public Optional<Recordatorio> get(Integer id) {
		Optional<Recordatorio> rec= Optional.ofNullable(
		(Recordatorio)em.createNamedQuery("Recordatorio.find").setParameter("idParam",id).getSingleResult());
		return rec;
	}

	@Override
	public List<Recordatorio> getAll() {
		@SuppressWarnings("unchecked")
		List<Recordatorio> recordatorios = (List<Recordatorio>) em.createQuery("FROM Empleado").getResultList();
		return recordatorios;
	}

	@Override
	public void save(Recordatorio t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Recordatorio t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Recordatorio t) {
		// TODO Auto-generated method stub
		
	}

}
