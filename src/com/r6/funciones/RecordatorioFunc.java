package com.r6.funciones;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
	RecordatorioDao dao = new RecordatorioDao();

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
		
		int yearSend = getYear(fecha);
		int yearActual = getYear(today);
		
		/*Meses Sumados*/
		Long mesesRestantes = monthsBetween(convertToLocal(today),convertToLocal(fecha));
		
		
		/*Dias restantes*/
		
		Long diasRestantes = daysBetween(convertToLocal(today),convertToLocal(fecha));
		
		/* Revisa si el anno es valido y si los valores son reales*/
		if(yearSend <= yearActual  && veces > 0 && meses >= 0  ) {
			
			/* Revisa si los dias restantes dan para la cantidad de veces a repetir*/
			if(veces <= diasRestantes.intValue()) {
				
				/* Revisa si la cantidad de meses es menor o igual a la
				 * cantidad de meses entre ambas fechas*/
				if(meses <= mesesRestantes.intValue()) {
					
					System.out.println("El correo se enviara el anno "+yearSend+" y es el Anno "+yearActual+"\n"
				    +"Quedan "+diasRestantes.intValue()+" dias disponibles para enviar "+veces+" Notificaciones"+"\n"
					+"durante "+meses+" meses");
					
					allOk = true;
					
					
				}
				
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
	
	
	public int getYear(Date fecha) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(fecha);
		int year = calendar.get(Calendar.YEAR);
		return year;
	}
	
	/*
	 *           - Generar recordatorios- 
	 * A partir de la cantidad de veces que el usuario
	 * indique, se genera la lista de recordatorios.
	 * 
	 * Como parametros, el metoddo recibe: - La fecha final del correo - La cantidad
	 * de veces al mes que se repite - La cantidad de meses que se debe de repetir
	 */
	
	 public List<Integer> dias = new ArrayList<>();
	
	public List<Recordatorio> generarRecordatorios(Correo correo, Date fecha, int veces, int meses) {
		
		Date currentDate = new Date();
		Calendar modedCal = new GregorianCalendar();
		modedCal.setTime(currentDate);
		Calendar dummyCalendar = new GregorianCalendar();
		
		List<Recordatorio> recordatorios = new ArrayList<>();
		
		int id = dao.getAll().size()+1;
		
		if(meses == 0) {
			meses = 1;
		}
		
		for(int mes = 1; mes <= meses; mes++) {
			
			dias.clear();
			modedCal.add(Calendar.MONTH, mes);
			
			for(int vez =1; vez <= veces;vez++ ) {
			
				dummyCalendar = modedCal;
				dummyCalendar = randomDatePicker(dummyCalendar);
				
				Recordatorio recordatorio = new Recordatorio();
				 recordatorio .setId(id);
				 recordatorio.setCorreo(correo);
				 recordatorio.setFechaEnvio(dummyCalendar.getTime());
				 recordatorio.setEstado(false);
			   	 id++;
				
			}
			
		}

		return recordatorios;
	}
 
 
 public Calendar randomDatePicker(Calendar cal) {
	
	 Calendar fecha = cal;
	 int maxDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	 int randomDay = (int) Math.random() * (maxDays - 1 + 1) + 1;
	 boolean isRepeated =false;
	 
	 if(dias.isEmpty()) {	 
		 dias.add(randomDay);
		 fecha.set(Calendar.DAY_OF_MONTH, randomDay);
	 }else {
		 
		 do {
			 
			 boolean contains = dias.contains(randomDay);
			
			 if(contains) {
				 randomDay = (int) Math.random() * (maxDays - 1 + 1) + 1;
			 }else {
				 fecha.set(Calendar.DAY_OF_MONTH, randomDay);
				 dias.add(randomDay);
				 isRepeated = true;
			 }
			 
		 }while(!isRepeated);
	 }
	 
	 return fecha;
 }
 
 public void tester(Correo correo, Date fecha, int veces, int meses) {
	 boolean chk =  checkDatos(fecha,veces,meses);
	 
	 if(chk){
		 
		 List<Recordatorio> recordatorios = generarRecordatorios(correo,fecha,veces,meses);
		 for(Recordatorio rec : recordatorios)
		    {
		      rec.toString();
		    }
		 
	 }else{
		 System.out.println(" Los datos no son validos! ");
	 }
 }
}
