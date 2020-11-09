/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r6.funciones;

import com.r6.mensajeria.Correo;
import com.r6.service.CorreoDao;
import com.r6.service.RecordatorioDao;
import java.util.Date;
import javax.persistence.EntityManager;

/**
 *
 * @author Daniel
 */
public class CorreoFunc {
    CorreoDao daoCorreo = new CorreoDao();
    RecordatorioDao daoRec = new RecordatorioDao();
    RecordatorioFunc funcion = new RecordatorioFunc();
    private static EntityManager em = null;
      
   
    public void setEm(EntityManager emNew) {
        em = emNew;
        daoCorreo.setEm(emNew);
        daoRec.setEm(emNew);
    }

    public EntityManager getEm() {
        return em;
    }
     
     
    public Correo crearCorreo(String asunto, String cuerpo,boolean estado, Date fecha, String tipo){
        Correo newCorreo = new Correo();
        newCorreo.setAsunto(asunto);
        newCorreo.setCuerpo(cuerpo);
        newCorreo.setEnviado(estado);
        newCorreo.setFechaEnvio(fecha);
        newCorreo.setTipo(tipo);
        return  newCorreo;
    }
    
    public void saveCorreo(Correo c){
        daoCorreo.save(c);
    }
    
    public void crearRecxMes(Correo correo , int mes){
        this.saveCorreo(correo);
        funcion.testMeses(correo, mes);
    }
    
     public void crearRecxVez(Correo correo, int veces, int mes){
          this.saveCorreo(correo);
         funcion.testVecesxMes(correo, veces, mes);
    }
     
    public void crearRecxFrecuencia(Correo correo,int veces, int mes, int frecuencia){
         this.saveCorreo(correo);
        funcion.testerMesesXFrecuencia(correo, veces, mes, frecuencia);
    }
}
