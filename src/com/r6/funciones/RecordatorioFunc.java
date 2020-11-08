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

    public void setRecorDao(RecordatorioDao dao) {
        this.dao = dao;
    }

    /*                     !-- Seccion veces/meses-- !                    */
 /* Funcion cuando el usuario define la veces en que quiere una notificacion 
	 * por X meses sin definir un periodo de frecuencia, solo la cantidad por mes.
	 * 
	 * 
	 *                           - checkDatos-
	 *  A partir de la fecha del correo y los datos ingresados por el
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
    public boolean checkDatosVecesxMeses(Correo correo, int veces, int meses) {

        boolean allOK = false;

        /*Anno en que se envia el correo meta*/
        int yearSend = getYear(correo.getFechaEnvio());

        /*Anno actual*/
        int yearActual = getYear(today);

        System.out.println(" ! Anno meta: " + yearSend + " Anno Actual: " + yearActual);

        /*Dias entre dia actual y la fecha meta*/
        int diasRestantes = (int) daysBetween(convertToLocal(today), convertToLocal(correo.getFechaEnvio()));
        System.out.println(" ! Dias entre dia actual y la fecha meta " + diasRestantes);

        /* Meses entre el dia actual y la fecha meta*/
        int mesesRestantes = (int) monthsBetween(convertToLocal(today), convertToLocal(correo.getFechaEnvio()));
        System.out.println(" ! Meses entre dia actual y la fecha meta " + mesesRestantes);

        /* Si el anno meta es mayor o igual que el anno actual*/
        /* Si la cantidad de veces propuestas es mayor a cero y si la cantidad de meses es mayor a 0*/
        if (yearActual <= yearSend && veces > 0 && meses > 0) {

            /* Si la cantidad de meses es valida*/
            if (meses <= mesesRestantes) {

                /* Si la cantidad de veces que se puede avisar es factble (Por dias restantes)*/
                if (veces <= diasRestantes) {

                    /* Si las veces no exceden el limite de dias estandar por mes*/
                    if (veces <= 31) {
                        System.out.println(" All set! ( Veces x Mes )");
                        allOK = true;

                    } else {
                        System.out.println(" Error: Datos proporcionados son erroneos. \n"
                                + "* La cantidad de veces proporcionada sobrepasa los dias en un mes\n");
                    }

                } else {
                    System.out.println(" Error: Datos proporcionados son erroneos. \n"
                            + "* La cantidad de veces proporcionada sobrepasa los dias actuales\n");
                }

            } else {
                System.out.println(" Error: Datos proporcionados son erroneos. \n"
                        + "* La cantidad de meses proporcionada sobrepasa los meses actuales\n");
            }

        } else {
            System.out.println(" Error: Datos proporcionados son erroneos. +\n"
                    + "* El anno porporcionado es menor al actual \n"
                    + "* La cantidad de veces es igual o menor a 0 \n"
                    + "* La cantidad de meses es menor a 0 \n");
        }

        return allOK;

    }


    
   /*
	 *           - randomDatePicker- 
	 * A partir de un parametro tipo calendario, se genera una
         * fecha random para setearla a un recordatorio. 
     */
   
    public Date randomDatePicker(Calendar cal) {

       Date fecha;
       /* Dias Maximos en X mes*/
       int maxDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
       /* Dia random generado*/
       int ranDay = (int)(Math.random() * (maxDays - 1 + 1) + 1);
       /* Se settea el dia random ne le mismo mes*/
       cal.set(Calendar.DAY_OF_MONTH, ranDay);
       fecha =cal.getTime();
       
       /*   SI hay errores revisar aca */
       boolean chk = false;
       
       Date dummyDate =  calendarToDate(cal);
       
       do{
           /* Revisa si las fechas son iguales al dia de hoy o si la fehca generada esta tarde
           * a la fecha de hoy*/  
           if(compareDates(dummyDate ,today) && isLate(today,dummyDate)){
                ranDay = (int)(Math.random() * (maxDays - 1 + 1) + 1);
           }else{
               chk =true;
           }
       }while(!chk);
       
       
       return fecha;
    }
    
     /*
	 *           - dayListGen- 
	 * A partir de un parametro tipo calendario y los datos ingresados
         * por el usuario, se generan la lista de dias a settear.
     */
   
    public List<Date> dayListGen(Calendar calendar, int veces, int meses){
        List<Date> dias = new ArrayList<>();
         /* Por cada mes se generan X veces de fechas random*/
         for(int mes=0; mes < meses; mes++ ){
                
                for(int vez=0; vez < veces; vez++){
                    dias.add(randomDatePicker(calendar));
                }
                
                /* Se suma al calendario un mes cada vez que se repita el loop.*/
                calendar.set(Calendar.MONTH, calendar.getTime().getMonth() + mes);
            }
         
         return dias;
    }
    
    public void testVecesxMes(Correo correo, int veces, int meses) {

        boolean allOk = this.checkDatosVecesxMeses(correo, veces, meses);
        List<Date> fechas = new ArrayList<>();
        
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(today);
        
        /* Si todos los parametro cumplen se realiza la funcion*/
        if (allOk) {
            
            fechas = dayListGen(calendar,veces,meses);
            
        }
        
        for(Date fecha: fechas){
            System.out.println(" Fecha: "+ fecha.toLocaleString());
        }
    }

    /*                                -- Fin Seccion veces/Meses--                                  */
 /*----------------------------- Funcionalidades y convertores ----------------------------------*/
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
    public int monthsBetween(LocalDate d1, LocalDate d2) {
        int restantes =  d2.getMonthValue() - d1.getMonthValue();
        return restantes;
    }

    /* - getYear - 
	 * Devuelve el anno en base a la fecha*/
    public int getYear(Date fecha) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(fecha);
        int year = calendar.get(Calendar.YEAR);
        return year;
    }
    
    /*   -- genCalendar-- */
    /*Pasa un objeto tipo date a calendario */
    public Calendar genCalendar(Date fecha){
        Calendar cal = new GregorianCalendar(); 
        cal.setTime(fecha);
        return cal;
    }
    
    
    /* genDate */
    /* Genera un valor tipo date con los valores definidos de YYYY/MM/DD*/
    public Date dateGen(int y, int m, int d){
        Date fecha =  new Date();
        fecha.setYear(y);
        fecha.setMonth(m);
        fecha.setDate(d);
        return fecha;
    }
    /* -- calendarToDate -- 
     Pasa calores calendar a Date*/
    public Date calendarToDate(Calendar cal){
        return cal.getTime();
    }
    
    public boolean isLate(Date today, Date due){
       boolean late =false;
       if(today.after(due)){
           late=true;
       }
       return late;   
    }
    
    /* -- compareDates-- */
   /* Compara dos fechas si son iguales*/
    public boolean compareDates(Date cal1 , Date cal2){
        boolean chk = false;
         
        if(cal1.equals(cal2)){
            chk=true;
        }
        return chk;
    }
}
