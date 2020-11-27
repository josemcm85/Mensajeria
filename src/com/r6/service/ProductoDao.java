package com.r6.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import com.r6.mensajeria.Producto;

public class ProductoDao implements Serializable, Dao<Producto> {
	
	private static EntityManager em = null;

	@Override
	public Optional<Producto> get(Integer id) {
		Optional<Producto> productID = Optional.ofNullable((Producto) em.createNamedQuery("Producto.findByIdProducto").setParameter("idProducto", id).getSingleResult());
		return productID;
	}
	
	public Optional<Producto> get(String nombre) {
		Optional<Producto> productName = Optional.ofNullable((Producto) em.createNamedQuery("Producto.findByNombre").setParameter("nombre", nombre).
                getSingleResult());
		return productName;
	}

	public List<Producto> getByPrecio(Double precio) {
		return em.createNamedQuery("Producto.findByPrecio", Producto.class).setParameter("precio", precio).getResultList();
	}
	
	public List<Producto> getByTipo(int tipo){
		return em.createNamedQuery("Producto.findByTipo", Producto.class).setParameter("tipo", tipo).getResultList();
	}
//o esta opcion	
//	public Optional<Producto> get(int tipo) {
//		Optional<Producto> productTipo = Optional.ofNullable((Producto) em.createNamedQuery("Producto.findByTipo").setParameter("tipo", tipo).
//                getSingleResult());
//		return productTipo;
//	}

//	public Optional<Producto> getDescripcionOpt(String descripcion) {
//		Optional<Producto> productDescription = Optional.ofNullable((Producto) em.createNamedQuery("Producto.findByDescripcion").setParameter("descripcion", descripcion).
//                getSingleResult());
//		return productDescription;
//	}

	
	@Override
	public List<Producto> getAll() {
		TypedQuery<Producto> productList = em.createNamedQuery("Producto.findAll", Producto.class);
        return productList.getResultList();
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

	public static EntityManager getEm() {
		return em;
	}

	public static void setEm(EntityManager em) {
		ProductoDao.em = em;
	}
	
}
