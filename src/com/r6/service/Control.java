package com.r6.service;

import com.r6.mensajeria.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;

import javax.persistence.EntityManager;

public class Control {

    private static EntityManager em = null;

    public void controlCorreos() {

        CorreoDao cDao = new CorreoDao();
        cDao.setEm(em);

        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        String stringDate = DateFor.format(today);

        for (com.r6.mensajeria.Correo c : cDao.getAll()) {
            Date currentMail = c.getFechaEnvio();
            Calendar cal = Calendar.getInstance();
            cal.setTime(currentMail);
            cal.add(Calendar.HOUR, 6);
            currentMail = cal.getTime();
            SimpleDateFormat DateCurrent = new SimpleDateFormat("dd/MM/yyyy");
            String CurrentMailStr = DateCurrent.format(currentMail);
            System.out.println("Today:" + stringDate);
            System.out.println("Correo:" + CurrentMailStr);

            try {
                Date currentMailDate = new SimpleDateFormat("dd/MM/yyyy").parse(CurrentMailStr);
                Date stringdateDate = new SimpleDateFormat("dd/MM/yyyy").parse(stringDate);

                if (((currentMailDate.before(stringdateDate) || currentMailDate.equals(stringdateDate)))) {

                    enviarCorreo(c);

                    enviarBitacoraCorreo(c);

                }

            } catch (ParseException ex) {
                Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MessagingException e) {
                //No se pudo enviar el correo
                e.printStackTrace();
            } catch (ClassNotFoundException ez) {
                ez.printStackTrace();
            }

        }

    }

    public void controlRecordatorios() {

        RecordatorioDao rDao = new RecordatorioDao();
        rDao.setEm(em);

        Date today = Calendar.getInstance().getTime();

        for (Recordatorio r : rDao.getAll()) {

            if ((r.getFechaEnvio().before(today) || r.getFechaEnvio().equals(today))) {

                try {
                    enviarRecordatorio(r);
                    enviarBitacoraRecordatorio(r);
                } catch (MessagingException ex) {
                    //Error en ele envio del correo
                    ex.printStackTrace();
                }

            }

        }

    }

    ///Un job 
    public void enviarBitacoraCorreo(com.r6.mensajeria.Correo correo) {

        String copiados = "";
        String de = "";
        String para = "";

        BitacoraDao dao = new BitacoraDao();
        dao.setEm(em);

        AdjuntoDao adDao = new AdjuntoDao();
        adDao.setEm(em);

        CorreoDao cDao = new CorreoDao();
        cDao.setEm(em);

        Bitacora bit = new Bitacora();

        Set<Usuario> deLista = new HashSet<>();
        deLista = correo.getUsuarios();

        for (Usuario u : deLista) {

            de = u.getCorreo() + "-" + u.getIdUsuario() + ";" + u.getSistema().getIdSistema(); 

        }

        Set<Contacto> paraLista = new HashSet<>();
        paraLista = correo.getRemitentes();

        for (Contacto c : paraLista) {

            para = c.getCorreo() + ";" + para;

        }

        Set<Usuario> deCopiados = new HashSet<>();
        deCopiados = correo.getUsuarioscopiados();

        for (Usuario u : deCopiados) {

            copiados = u.getCorreo() + ";" + copiados;

        }

        bit.setAsunto(correo.getAsunto());
        bit.setCopiados(copiados);
        bit.setCuerpo(correo.getCuerpo());
        bit.setDe(de);
        bit.setEnviado(true);
        bit.setFechaEnvio(correo.getFechaEnvio());
        bit.setPara(para);
        bit.setTipo(correo.getTipo());
        bit.setInfinito(correo.getInifinito());

        Set<Adjunto> cc = correo.getAdjuntos();

        if (!cc.isEmpty()) {
            bit.setAdjuntosBitacora(cc);
        }

        dao.save(bit);

        if (!correo.getInifinito()) {
            cDao.delete(correo);
        } else {
            Calendar cal = Calendar.getInstance();
            //Update de fecha
            switch (correo.getIntervalo()) {

                case 1: {
                    //DIA
                    cal.setTime(correo.getFechaEnvio());
                    cal.add(Calendar.DATE, 1);
                    correo.setFechaEnvio(cal.getTime());
                    cDao.update(correo);

                    break;
                }

                case 2: {
                    //SEMANA
                    cal.setTime(correo.getFechaEnvio());
                    cal.add(Calendar.DATE, 7);
                    correo.setFechaEnvio(cal.getTime());
                    cDao.update(correo);
                    break;
                }

                case 3: {
                    //MES
                    cal.setTime(correo.getFechaEnvio());
                    cal.add(Calendar.MONTH,1);
                    correo.setFechaEnvio(cal.getTime());
                    cDao.update(correo);
                    break;
                }

                case 4: {
                    //ANIO
                    cal.setTime(correo.getFechaEnvio());
                    cal.add(Calendar.YEAR, 1);
                    correo.setFechaEnvio(cal.getTime());
                    cDao.update(correo);
                    break;
                }

            };

        }

    }

    public void enviarBitacoraCorreoNBorra(com.r6.mensajeria.Correo correo) {
    	  String copiados = "";
          String de = "";
          String para = "";

          BitacoraDao dao = new BitacoraDao();
          dao.setEm(em);

          AdjuntoDao adDao = new AdjuntoDao();
          adDao.setEm(em);

          CorreoDao cDao = new CorreoDao();
          cDao.setEm(em);

          Bitacora bit = new Bitacora();

          Set<Usuario> deLista = new HashSet<>();
          deLista = correo.getUsuarios();

          for (Usuario u : deLista) {

              de = u.getCorreo() + "-" + u.getIdUsuario() + ";" + u.getSistema().getIdSistema(); 

          }

          Set<Contacto> paraLista = new HashSet<>();
          paraLista = correo.getRemitentes();

          for (Contacto c : paraLista) {

              para = c.getCorreo() + ";" + para;

          }

          Set<Usuario> deCopiados = new HashSet<>();
         
          if(correo.getUsuarioscopiados()!=null) {
        	  deCopiados = correo.getUsuarioscopiados();	
        	  for (Usuario u : deCopiados) {

                  copiados = u.getCorreo() + ";" + copiados;

              }
          }
        

          bit.setAsunto(correo.getAsunto());
          bit.setCopiados(copiados);
          bit.setCuerpo(correo.getCuerpo());
          bit.setDe(de);
          bit.setEnviado(true);
          bit.setFechaEnvio(correo.getFechaEnvio());
          bit.setPara(para);
          bit.setTipo(correo.getTipo());
          bit.setInfinito(correo.getInifinito());
          if(correo.getAdjuntos()!=null) {
        	
        	  Set<Adjunto> cc = correo.getAdjuntos();

              if (!cc.isEmpty()) {
                  bit.setAdjuntosBitacora(cc);
              }

          }
         
          dao.save(bit);
    }
    
    public void enviarBitacoraRecordatorio(Recordatorio recordatorio) {

        String copiados = "";
        String de = "";
        String para = "";

        BitacoraDao dao = new BitacoraDao();
        dao.setEm(em);

        RecordatorioDao rDao = new RecordatorioDao();
        rDao.setEm(em);

        Bitacora bit = new Bitacora();

        com.r6.mensajeria.Correo correo = recordatorio.getCorreo();

        Set<Usuario> deLista = new HashSet<>();
        deLista = correo.getUsuarios();

        for (Usuario u : deLista) {

            de = u.getCorreo() + "-" + u.getIdUsuario() + ";" + u.getSistema().getIdSistema(); 

        }

        Set<Contacto> paraLista = new HashSet<>();
        paraLista = correo.getDestinatarios();

        for (Contacto c : paraLista) {

            para = c.getCorreo() + ";" + para;

        }

        Set<Usuario> deCopiados = new HashSet<>();
        deCopiados = correo.getUsuarioscopiados();

        for (Usuario u : deCopiados) {

            copiados = u.getCorreo() + ";" + copiados;

        }

        bit.setAsunto(correo.getAsunto());
        bit.setCopiados(copiados);
        bit.setCuerpo(correo.getCuerpo());
        bit.setDe(de);
        bit.setEnviado(true);
        bit.setFechaEnvio(correo.getFechaEnvio());
        bit.setPara(para);
        bit.setTipo(correo.getTipo());

        dao.save(bit);

        rDao.delete(recordatorio);

    }

    public void enviarCorreo(com.r6.mensajeria.Correo correo) throws ClassNotFoundException, MessagingException {
    	
    	System.out.println("dsdsd");
        String de = "";
        String password = "";
        String Destinatario = "";
        String copiados = "";
        Correo correoEnviar= new Correo();
        Set<Usuario> deLista = new HashSet<>();
        
        
        if(correo.getUsuarios()!=null) {
        	deLista = correo.getUsuarios();
        	 for (Usuario u : deLista) {

                 de = u.getCorreo();
                 password = u.getContrasenia();

             }
        	 
        	 correoEnviar.setEmailSender(de);
        	 correoEnviar.setPassword(password);
        }
       

        Set<Usuario> CopiadosLista = new HashSet<>();
     
        int cop = 1;
        if(correo.getUsuarioscopiados()!=null) {
        	   CopiadosLista = correo.getUsuarioscopiados();
        	   for (Usuario u : CopiadosLista) {
                   if (cop == 1) {
                       copiados = u.getCorreo();
                   } else {
                       copiados = copiados + "," + u.getCorreo();
                   }
                   cop++;
               }
        	 
        }
        correoEnviar.setCopiados(copiados);

        Set<Contacto> paraLista = new HashSet<>();
       

       
        if(correo.getDestinatarios()!=null) {
        	 int i = 1;
        	 paraLista = correo.getDestinatarios();
        	
        	   for (Contacto c : paraLista) {

                   if (i == 1) {
                       Destinatario = c.getCorreo();
                   } else {
                       Destinatario = Destinatario + "," + c.getCorreo();
                   }

                   i++;
               }
        	   
        	
        }
     
        correoEnviar.setEmailReceiver(Destinatario);
        String asunto = correo.getAsunto();
        correoEnviar.setSubject(asunto);
        String cuerpo = correo.getCuerpo();
        correoEnviar.setBody(cuerpo);
        String tipo = correo.getTipo();
        correoEnviar.setTipo(tipo);

        Envio enviar = new Envio();

        Set<Adjunto> files = new HashSet<Adjunto>();

        if(correo.getAdjuntos()!=null) {
        	
        files=correo.getAdjuntos();
        }
        
   	 correoEnviar.setAttachedFiles(files);
       

 
     
        enviar.setCorreo(correoEnviar);

        enviar.enviarSincronico();

    }

    public void enviarRecordatorio(Recordatorio recordatorio) throws MessagingException {

        String de = "";
        String password = "";
        String Destinatarios = "";
        String copiados = "";

        com.r6.mensajeria.Correo correo = recordatorio.getCorreo();

        Set<Usuario> deLista = new HashSet<>();
        deLista = correo.getUsuarios();

        for (Usuario u : deLista) {

            de = u.getCorreo();
            password = u.getContrasenia();

        }

        Set<Contacto> paraLista = new HashSet<>();
        paraLista = correo.getDestinatarios();

        int i = 1;
        for (Contacto c : paraLista) {

            if (i == 1) {
                Destinatarios = c.getCorreo();
            } else {
                Destinatarios = Destinatarios + ";" + c.getCorreo();
            }

            i++;
        }

        Set<Usuario> CopiadosLista = new HashSet<>();
        CopiadosLista = correo.getUsuarioscopiados();

        int cop = 1;
        for (Usuario u : CopiadosLista) {
            if (cop == 1) {
                copiados = u.getCorreo();
            } else {
                copiados = copiados + ";" + u.getCorreo();
            }

        }

        String asunto = "Recordatorio " + correo.getAsunto();
        String cuerpo = correo.getCuerpo();
        String tipo = correo.getTipo();

        Envio enviar = new Envio();
        Set<Adjunto> files = new HashSet<>();
      //  File file = new File("path");
       // files.add(file);

        Correo correoEnviar = new Correo(
                de,
                password,
                Destinatarios,
                asunto,
                cuerpo,
                files,
                tipo,
                copiados);
        enviar.setCorreo(correoEnviar);

        enviar.enviarSincronico();

    }

    public static EntityManager getEm() {
        return em;
    }

    public static void setEm(EntityManager em) {
        Control.em = em;
    }

}
