/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r6.funciones;

import com.r6.mensajeria.Correo;
import com.r6.mensajeria.Recordatorio;
import com.r6.service.CorreoDao;
import com.r6.service.RecordatorioDao;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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

    public Correo crearCorreo(String asunto, String cuerpo,Date fecha, String tipo,boolean infinito) {
        Correo newCorreo = new Correo();
        newCorreo.setAsunto(asunto);
        newCorreo.setCuerpo(cuerpo);
        newCorreo.setFechaEnvio(fecha);
        newCorreo.setTipo(tipo);
        newCorreo.setInifinito(infinito);
        return newCorreo;
    }

    public void saveCorreo(Correo c) {
        try {
            daoCorreo.save(c);
        } catch (Exception e) {
            System.out.println("Error en creacion del correo. \n" + e);
        }

    }

    public void crearRecxMes(Correo correo, int mes) {
        try {
            correo.setMeses(mes);
            this.saveCorreo(correo);
            funcion.testMeses(correo, mes);
        } catch (Exception e) {
            System.out.println("Error en creacion del correo. \n" + e);
        }

    }

    public void crearRecxVez(Correo correo, int veces, int mes) {
        try {
            correo.setVeces(veces);
            correo.setMeses(mes);
            this.saveCorreo(correo);
            funcion.testVecesxMes(correo, veces, mes);
        } catch (Exception e) {
            System.out.println("Error en creacion del correo. \n" + e);
        }

    }

    public void crearRecxFrecuencia(Correo correo, int veces, int mes, int frecuencia) {
        try {
            correo.setVeces(veces);
            correo.setMeses(mes);
            correo.setFrecuencia(frecuencia);
            this.saveCorreo(correo);
            funcion.testerMesesXFrecuencia(correo, veces, mes, frecuencia);
        } catch (Exception e) {
            System.out.println("Error en creacion del correo. \n" + e);
        }

    }

    public void getCorreoswRecordatorios() {

        try {
            List<Correo> allCorreos = daoCorreo.getAll();

            RecordatorioDao recDao=new RecordatorioDao();
            recDao.setEm(em);
            
            for (Correo correo : allCorreos) {
                System.out.println("Correo ID: " + correo.getId());
                System.out.println("Asunto: " + correo.getAsunto());
                System.out.println("    --  Recordatorios  --  ");
                
                
              
          
                List<Recordatorio> recList=recDao.getByMail(correo);
                
                //Hacer m�todo get recordatorios por idcorreo
                
               
                if (recList.size() == 0) {
                    System.out.println(" ! No hay Recordatorios ! \n");
                }
                
              
                for (Recordatorio rec : recList) {
                    System.out.println("Recordatorio No: " + rec.getId());
                    System.out.println("Fecha recordatorio: " + rec.getFechaEnvio().toGMTString());
                }
            }
        } catch (Exception e) {
            System.out.println("Error en obtener lista \n" + e);
        }

    }

    public void editarCorreo(int target, String asunto, String cuerpo, Date fecha, String tipo,boolean infinito) {
        try {
            Correo correo = daoCorreo.getById(target);
            correo.setAsunto(asunto);
            correo.setCuerpo(cuerpo);
            correo.setFechaEnvio(fecha);
            correo.setTipo(tipo);
            correo.setInifinito(infinito);
            daoCorreo.update(correo);
            System.out.println("Correo editado!");
        } catch (Exception e) {
            System.out.println("Error en actualizar correo! \n" + e);
        }

    }

    public void eliminarCorreo(int target) {

        try {
            Correo correo = daoCorreo.getById(target);
            daoCorreo.delete(correo);
            System.out.println("Correo eliminado!");
        } catch (Exception e) {
            System.out.println(" Error en eliminar correo: \n" + e);
        }

    }

}
