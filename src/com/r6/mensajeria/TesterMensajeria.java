/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r6.mensajeria;
import com.r6.funciones.CorreoFunc;
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
import com.r6.service.CorreoDao;

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
    	/*
        CorreoFunc funtion = new CorreoFunc();
        RecordatorioFunc recFuntion = new RecordatorioFunc();
        funtion.setEm(em);
        recFuntion.setEm(em);
        
        Date date = new Date();
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 3);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        Correo c = new Correo();
        c.setAsunto(" Prueba con Recordatorios ");
        c.setCuerpo(" Esta es una prueba con recordatorios ");
        c.setFechaEnvio(cal.getTime());
        c.setTipo("HTML");
        c.setEnviado(Boolean.FALSE);
        */
        
        
        /* Crear y enviar Correo
      
        funtion.crearRecxMes(c, 2);
        */
        /* Devuelve correos con recordatorios
        funtion.getCorreoswRecordatorios();
 	*/
        
        /* Editar Correo por ID
        funtion.editarCorreo(1, "Correo Edit", "Correo Edit", true, cal.getTime(),"HTML");
        */
        
        /* Eliminar Correo
        funtion.eliminarCorreo(1);
        */
        
        /* Editar recordatorio por ID 
         cal.set(Calendar.DAY_OF_MONTH, Calendar.DAY_OF_MONTH-1);
         recFuntion.editarRecordatorio(1, true, cal.getTime());
         */
        /* Eliminar recordatorio por ID
         recFuntion.deleteRecordatorio(1);
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
