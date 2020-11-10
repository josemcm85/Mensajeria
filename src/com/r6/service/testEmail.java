package com.r6.service;

import java.io.File;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class testEmail {

	public static void main(String[] args) {
		Envio enviar = new Envio();
		ArrayList<File> files = new ArrayList<>();
        File file = new File("path");       
        files.add(file);
        
		/*Correo correo = new Correo(
				//sender
				"correo", 
				"password",
				//receiver 
				"correo", 
				"Subject --", 
				"Body --", 
				files);
		enviar.setCorreo(correo);
		*/
		//enviar.enviarSincronico();
		
		//asincronico
		//try {
			
		//	enviar.execute();
			
		//} catch (Exception e) {
		//	e.printStackTrace();
		}
		//.out.println("enviando");
		//JOptionPane.showMessageDialog(null, "XXXX");
		//System.out.println("Se envio su correo");		
	}


