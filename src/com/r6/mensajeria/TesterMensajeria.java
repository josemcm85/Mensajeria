/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r6.mensajeria;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.r6.service.RecordatorioDao;
import com.r6.service.SistemaDao;
import com.r6.funciones.RecordatorioFunc;

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
            
            recordatoriosTest();
            
            stopEntityManagerFactory();
        }catch(Exception e){
            e.printStackTrace();
        }

        
     
        
    }
    
    public static void sistemaTest() {
    	//<editor-fold defaultstate="collapsed" desc=" Variables ">
    	
        SistemaDao sisDao = new SistemaDao();
        sisDao.setEm(em);
        
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
        	System.out.println("No se encontr� el sistema");
        }
       */ 
        
        //DESACTIVAR/ACTIVAR
        
        /*
        Optional<Sistema> sistDis =sisDao.get(new Integer(1));
        sisDao.EnableDisable(sistDis.get());
        */
      //<editor-fold>
    }

    
    
    public static void recordatoriosTest() {
    	
     RecordatorioFunc funcion = new RecordatorioFunc();
     RecordatorioDao dao = new RecordatorioDao();
     dao.setEm(em);
     
     funcion.setRecorDao(dao);
     for(Recordatorio c: dao.getAll()) {
    	 System.out.println("C : "+c.getId());	
    	 
     }
 	 /* vecesXMeses
         @SuppressWarnings("unchecked")
	 List<Correo> correos = (List<Correo>) em.createQuery("FROM Correo").getResultList();
 	 Correo dummyCorreo = correos.get(0);
 	 System.out.println(" DummyCorreo: "+dummyCorreo.getId()+" Due to: "+dummyCorreo.getFechaEnvio().toString());
 	 funcion.testVecesxMes(dummyCorreo, 18, 1);
         */
      	
         /* xMeses
      @SuppressWarnings("unchecked")
      List<Correo> correos = (List<Correo>) em.createQuery("FROM Correo").getResultList();
      Correo dummyCorreo = correos.get(0);
      System.out.println(" DummyCorreo: "+dummyCorreo.getId()+" Due to: "+dummyCorreo.getFechaEnvio().toString());
      funcion.testMeses(dummyCorreo, 7);
       */
         
      List<Correo> correos = (List<Correo>) em.createQuery("FROM Correo").getResultList();
      Correo dummyCorreo = correos.get(0);
      System.out.println(" DummyCorreo: "+dummyCorreo.getId()+" Due to: "+dummyCorreo.getFechaEnvio().toString());
      funcion.testerMesesXFrecuencia(dummyCorreo, 4, 1,4 );
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
