package com.r6.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.r6.mensajeria.Usuario;
import com.r6.mensajeria.Sistema;

public class UsuarioDao implements Dao<Usuario> {

	private static EntityManager em = null;
	
	public UsuarioDao(EntityManager em) {
		
		SistemaDao sisDao = new SistemaDao();
		UsuarioDao.em = em;
		
		try {
			 Optional<Usuario> sis =get(new Integer(1));
		}catch(javax.persistence.NoResultException e) {
			Usuario us=new Usuario();
			us.setActivo(true);
			us.setAdmin(true);
			us.setContrasenia("123");
			us.setCorreo("super@mail.com");
			us.setSuperUser(true);
			
			for(Sistema s: sisDao.getAll()) {
	        	if (s.getId()==1) {
	        		us.setSistema(s);
	        	}
				
	        }
			save(us);
			
		}
		
	}
	
	public UsuarioDao() {}
	
	
	
	@Override
	public Optional<Usuario> get(Integer id) {
		Optional<Usuario> us=Optional.ofNullable((Usuario)em.createNamedQuery("Usuario.find").setParameter("idParam",id).
				getSingleResult());
		
		return us;
	}

	@Override
	public List<Usuario> getAll() {
		TypedQuery<Usuario> usList = em.createNamedQuery("Usuario.findAll", Usuario.class);
		return usList.getResultList();
	}

	@Override
	public void save(Usuario t) {
		
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		
	}

	@Override
	public void update(Usuario t) {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		
	}


	public void EnableDisable(Usuario t) {
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
		UsuarioDao.em = em;
	}

	@Override
	public void delete(Usuario t) {
		// TODO Auto-generated method stub
		
	}

	
	
}
