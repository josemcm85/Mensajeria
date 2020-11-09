/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r6.mensajeria;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.r6.service.SistemaDao;
import com.r6.service.UsuarioDao;

/**
 *
 * @author Nvidi
 */
public class TesterMensajeria {

    private static EntityManagerFactory entityManagerFactory = null;
    private static EntityManager em = null;

    public static void main(String[] args) {
        try{
            startEntityManagerFactory();
            
            	/*Por favor, testear lo que programaron usando un método para ello, para 
            	 que sea más facil de leer y de activar/desactivar */
            	 
            
            
            sistemaTest();
            UsuarioTest();
            
            
            stopEntityManagerFactory();
        }catch(Exception e){
            e.printStackTrace();
        }

        
     
        
    }
    
    public static void sistemaTest() {
        SistemaDao sisDao = new SistemaDao(em);

        
        //GUARDAR
        
       /*
        Sistema sistema =new Sistema();
     
       sistema.setActivo(true);
        sistema.setNombre("Recursos Humanos");
   
       sisDao.save(sistema);
        
        */
        
        //ACTUALIZAR
        
        /*
        Sistema sis1 =new Sistema();
        sis1.setId(1);
        sis1.setNombre("Contabilidad");
        sis1.setActivo(true);
        sisDao.update(sis1);
        */
        
        
        //BUSCAR TODOS
        
      /*  
        for(Sistema s: sisDao.getAll()) {
        	System.out.println("ID:"+s.getId()+" Nombre:"+s.getNombre());
        }
        */
        
        
        
        //BUSCAR
        
        /*
        Optional<Sistema> sis =sisDao.get(new Integer(1));
        if(sis.isPresent()) {
        	System.out.println("ID:"+sis.get().getId()+ " Nombre:"+sis.get().getNombre());
        }else {
        	System.out.println("No se encontró el sistema");
        }
       */ 
        
        //DESACTIVAR/ACTIVAR
        
        /*
        Optional<Sistema> sistDis =sisDao.get(new Integer(1));
        sisDao.EnableDisable(sistDis.get());
        */
    }
    
    public static void UsuarioTest() {
    	
    	UsuarioDao usDao = new UsuarioDao(em);

    	
        //GUARDAR
    	//Orden para crear usuario (Integer idSistema, String correo, String contrasenia, Boolean admin, Boolean superUser, Boolean activo)
    
    	/*
        Usuario usuario = new Usuario(1,"test@mail.com","abc",false,false,false);
 
       usDao.save(usuario);

    	 */      
        
        //ACTUALIZAR
        
        /*
        Usuario us2 =new Usuario();
        us2.actualizarUsuario(2);
        us2.setCorreo("testeando@mail.com");
        us2.setContrasenia("123");
        usDao.update(us2);
        */
        
        //Asignar a usuario nuevo sistema
            
        /*
         Usuario us3 =new Usuario();
         us3.actualizarUsuario(2);
         us3.nuevoSistemaUsuario(2);
         usDao.update(us3);
          
         */
        
        
        //BUSCAR TODOS

    	/*

        for(Usuario u: usDao.getAll()) {
        	System.out.println("ID:"+u.getIdUsuario()+" Correo: "+u.getCorreo());
        }

        */
        
        
        //BUSCAR
        
        /*
         
        Optional<Usuario> us =usDao.get(new Integer(1));
        if(us.isPresent()) {
        	System.out.println("ID: "+us.get().getIdUsuario()+ " Correo: "+us.get().getCorreo());
        }else {
        	System.out.println("No se encontró el Usuario");
        }
        
        */
        //DESACTIVAR/ACTIVAR
        
        /*
        Optional<Usuario> us = usDao.get(new Integer(2));
        usDao.EnableDisable(us.get());
        */
    	
    	
    	
    }
    

    public static void startEntityManagerFactory() {
        if (entityManagerFactory == null) {
            try {
                entityManagerFactory = Persistence
                        .createEntityManagerFactory("Mensajeria");
                em = entityManagerFactory.createEntityManager();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void stopEntityManagerFactory() {
        if (entityManagerFactory != null) {
            if (entityManagerFactory.isOpen()) {
                try {
                    entityManagerFactory.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            em.close();
            entityManagerFactory = null;
        }
    }

}
