package com.r6.funciones;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import com.r6.mensajeria.Correo;
import com.r6.mensajeria.Recordatorio;
import com.r6.service.RecordatorioDao;

public class RecordatorioFunc {

	Date today = new Date();
	Calendar calendar = Calendar.getInstance();

	/*
	 * - checkDatos- A partir de la fecha del correo y los datos ingresados por el
	 * usuario, se verifica si es posible realizar los recordatorios indicados.
	 * 
	 * Como parametros, el metoddo recibe: - La fecha a enviar del correo. - La
	 * cantidad de veces al mes que se repite - La cantidad de meses que se debe de
	 * repetir
	 * 
	 * Se pretende controlar: - Que la cantidad de veces no sea mayor a la fecha de
	 * envio - Que la cantidad de meses no sea mayor a la fecha de entrega - Que
	 * ningun valor de veces no sea <= 0 y de meses sea < 0 - Que el anno de la
	 * fecha de envio sea => al anno actual
	 */

	public boolean checkDatos(Date fecha,int veces, int meses) {
	
		boolean allOk=false;
		
		int yearSend = fecha.getYear();
		int yearActual = today.getYear();
		
		/*Meses Sumados*/
		long mesesRestantes = monthsBetween(convertToLocal(today),convertToLocal(fecha));
		
		
		/*Dias restantes*/
		
		Long diasRestantes = daysBetween(convertToLocal(today),convertToLocal(fecha));
		
		/* Revisa si el anno es valido y si los valores son reales*/
		if(yearSend <= yearActual  && veces > 0 && meses >= 0  ) {
			
			/* Revisa si los dias restantes dan para la cantidad de veces a repetir*/
			if(veces <= diasRestantes.intValue()) {
				
				
				
			}
			
		}
		
		return allOk;
	}
	
	/*   -convertToLocal-   
	 * Convierte los datos tipo Date a Local date 
	 * para calcular los dias restantes   */
	public LocalDate convertToLocal(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
	
	/*   -daysBetween-   
	 * Calcula los dias restantes entre dos local dates*/
	public long daysBetween(LocalDate d1, LocalDate d2) {
		long restantes = ChronoUnit.DAYS.between(d1, d2);
		return restantes;
	}
	
	/*  -monthsBetween- 
	 * Calcula los meses entre las dos fechas tipo LocalDate*/
	public long monthsBetween(LocalDate d1, LocalDate d2) {
		long restantes = ChronoUnit.MONTHS.between(d1, d1);
		return restantes;
	}
	
	
	/*
	 * - Generar recordatorios- A partir de la cantidad de veces que el usuario
	 * indique, se genera la lista de recordatorios.
	 * 
	 * Como parametros, el metoddo recibe: - La fecha final del correo - La cantidad
	 * de veces al mes que se repite - La cantidad de meses que se debe de repetir
	 */

	public List<Recordatorio> generarRecordatorios(Date fecha, int veces, int meses) {

		List<Recordatorio> recordatorios = new ArrayList<>();

		return recordatorios;
	}

}
