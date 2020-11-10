package com.r6.service;

import com.r6.mensajeria.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;


public class Control {

	private static EntityManager em = null;
	
	public void controlCorreos() {
		
		CorreoDao cDao = new CorreoDao();
		cDao.setEm(em);
		
		Date today = Calendar.getInstance().getTime();
		
		for(com.r6.mensajeria.Correo c : cDao.getAll()) {
			
			if(c.getEnviado() == false && c.getFechaEnvio() == today) {
				
				try {
					enviarCorreo(c);
				}catch(Exception e) {
					
				}
				
				enviarBitacoraCorreo(c);
				
			}
			
				
		}
		
	}
	
	public void controlRecordatorios() {
		
		RecordatorioDao rDao = new RecordatorioDao();
		rDao.setEm(em);
	
		Date today = Calendar.getInstance().getTime();
		
		for(Recordatorio r : rDao.getAll()) {
			
			if(r.getEstado() == false && r.getFechaEnvio() == today) {
				
				enviarRecordatorio(r);
				enviarBitacoraRecordatorio(r);
				
			}
			
		}
		
	}
	
	
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

		for(Usuario u : deLista) {
			
		de = u.getCorreo();
		
		
		}
        
		Set<Contacto> paraLista = new HashSet<>();
		paraLista = correo.getRemitentes();
		
		for(Contacto c : paraLista) {
			
				para = c.getCorreo() + ";" + para;		
			
		}
		
        Set<Usuario> deCopiados = new HashSet<>();
		deCopiados = correo.getUsuarioscopiados();

		for(Usuario u : deCopiados) {
			
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
        
        Set<Adjunto> cc = correo.getAdjuntos();
       
        bit.setAdjuntosBitacora(cc);
        
        dao.save(bit);
        
        cDao.delete(correo);
        
		
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

		for(Usuario u : deLista) {
			
		de = u.getCorreo();
		
		
		}
        
		Set<Contacto> paraLista = new HashSet<>();
		paraLista = correo.getDestinatarios();
		
		for(Contacto c : paraLista) {
			
				para = c.getCorreo() + ";" + para;		
			
		}
		
        Set<Usuario> deCopiados = new HashSet<>();
		deCopiados = correo.getUsuarioscopiados();

		for(Usuario u : deCopiados) {
			
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
	
	public void enviarCorreo(com.r6.mensajeria.Correo correo) throws ClassNotFoundException {
		
		String de = "";
		String password = "";
		String Destinatario = "";
		String copiados = "";
		
		Set<Usuario> deLista = new HashSet<>();
		deLista = correo.getUsuarios();

		
		for(Usuario u : deLista) {
			
		de = u.getCorreo();
		password = u.getContrasenia();
		
		}
		
		Set<Usuario> CopiadosLista = new HashSet<>();
		CopiadosLista = correo.getUsuarioscopiados();

		
		for(Usuario u : CopiadosLista) {
			
		copiados = u.getCorreo() + ";" + copiados;
		
				}
		
		
		
		Set<Contacto> paraLista = new HashSet<>();
		paraLista = correo.getDestinatarios();
		
		for(Contacto c : paraLista) {
			
			Destinatario = c.getCorreo() + ";" + Destinatario;		
			
		}



		String asunto = correo.getAsunto();
		String cuerpo = correo.getCuerpo();
		String tipo = correo.getTipo();

		
		Envio enviar = new Envio();
		
		ArrayList<File> files = new ArrayList<>();
		
		for(Adjunto a : correo.getAdjuntos()) {
			
			byte[] b= a.getArchivo();

			
			try {
				ByteArrayInputStream bis=new ByteArrayInputStream(b);
				ObjectInputStream ois;
				ois = new ObjectInputStream(bis);
				File file=(File)ois.readObject();
				files.add(file);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
        
		Correo correoEnviar = new Correo(
				de, 
				password,
				Destinatario, 
				asunto, 
				cuerpo, 
				files,
				tipo,
				copiados);
		enviar.setCorreo(correoEnviar);
		
		enviar.enviarSincronico();
		

		
		
	}
	
	public void enviarRecordatorio(Recordatorio recordatorio) {
	
		String de = "";
		String password = "";
		String Destinatarios = "";
		String copiados = "";
		
		com.r6.mensajeria.Correo correo = recordatorio.getCorreo();
        
		Set<Usuario> deLista = new HashSet<>();
		deLista = correo.getUsuarios();

		
		for(Usuario u : deLista) {
			
		de = u.getCorreo();
		password = u.getContrasenia();
		
		}
		
		Set<Contacto> paraLista = new HashSet<>();
		paraLista = correo.getDestinatarios();
		
		for(Contacto c : paraLista) {
			
			Destinatarios = c.getCorreo() + ";" + Destinatarios;		
			
		}
		
		Set<Usuario> CopiadosLista = new HashSet<>();
		CopiadosLista = correo.getUsuarioscopiados();

		
		for(Usuario u : CopiadosLista) {
			
		copiados = u.getCorreo() + ";" + copiados;
		
				}



		String asunto = correo.getAsunto();
		String cuerpo = correo.getCuerpo();
		String tipo = correo.getTipo();

		
		Envio enviar = new Envio();
		ArrayList<File> files = new ArrayList<>();
        File file = new File("path");       
        files.add(file);
        
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
	
	
	
		
}
