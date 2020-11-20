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
import com.r6.service.CorreoDao;
import com.r6.service.RecordatorioDao;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;
import javax.persistence.EntityManager;

public class RecordatorioFunc {

    Date today = new Date();
    Calendar calendar = Calendar.getInstance();
    RecordatorioDao dao = new RecordatorioDao();
    CorreoDao daoCorreo = new CorreoDao();
    private static EntityManager em = null;

    public void setRecorDao(RecordatorioDao dao) {
        this.dao = dao;
    }

    public void setEm(EntityManager emNew) {
        em = emNew;
        daoCorreo.setEm(emNew);
        dao.setEm(emNew);
    }

    public EntityManager getEm() {
        return em;
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
                if (this.checkMonthAv(today, correo.getFechaEnvio(), veces, meses)) {

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
        /*Cambiar de void a Date*/
        int maxDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int ranDay = 0;
        boolean allOk = false;
        Date fecha;
        do {
            ranDay = (int) (Math.random() * (maxDays - 1 + 1) + 1);
            cal.set(Calendar.DAY_OF_MONTH, ranDay);
            fecha = cal.getTime();

            if (fecha.compareTo(today) == 0 || fecha.compareTo(today) < 0 || fecha.getMonth() > cal.getTime().getMonth()) {
                ranDay = (int) (Math.random() * (maxDays - 1 + 1) + 1);
                allOk = false;
            } else {
                allOk = true;
            }
        } while (!allOk);

        return fecha;
    }


    /*
	 *           - dayListGen- 
	 * A partir de un parametro tipo calendario y los datos ingresados
         * por el usuario, se generan la lista de dias a settear.
     */
    public List<Date> dayListGen(Calendar calendar, int veces, int meses) {
        List<Date> dias = new ArrayList<>();
        /* Por cada mes se generan X veces de fechas random*/
        for (int mes = 1; mes <= meses; mes++) {

            for (int vez = 0; vez < veces; vez++) {

                Date randay = randomDatePicker(calendar);
                boolean notRepeated = false;

                do {
                    if (Arrays.asList(dias).contains(randay)) {
                        randay = randomDatePicker(calendar);
                    } else {
                        dias.add(randay);
                        notRepeated = true;
                    }
                } while (!notRepeated);

               
            }

            /* Se suma al calendario un mes cada vez que se repita el loop.*/
           
             calendar.set(Calendar.MONTH, calendar.getTime().getMonth() + 1);
          
           
        }

        return dias;
    }

    /*     -- cleanList--   
    Este metodo recibe una lista de fechas y la purga de fechas repetidas*/
    public List<Date> cleanList(List<Date> original) {

        List<Date> newList = new ArrayList<>();

        for (int i = 0; i <= original.size() - 1; i++) {

            for (int y = 0; y <= original.size() - 1; y++) {

                if (i != y && original.get(i).compareTo(original.get(y)) == 0) {
                    Date traget = original.get(i);
                    Calendar cal = this.dateToCalendar(traget);
                    original.set(y, randomDatePicker(cal));
                }
            }
        }
        Collections.sort(original);
        return original;
    }

    public void testVecesxMes(Correo correo, int veces, int meses) {

        boolean allOk = this.checkDatosVecesxMeses(correo, veces, meses);
        List<Date> fechas = new ArrayList<>();

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(today);

        /* Si todos los parametro cumplen se realiza la funcion*/
        if (allOk) {

            fechas = dayListGen(calendar, veces, meses);
            cleanList(fechas);

        }

        List<Recordatorio> recordatorios = this.asignarCuerpo(fechas, correo);
        for (Recordatorio rec : recordatorios) {
            System.out.println("Recordatorio: " + rec.getId() + "\n"
                    + "Fecha de Envio: " + rec.getFechaEnvio() + "\n");
        }

    }

    /*                                -- Fin Seccion veces/Meses--                                  */
 /*                              -- Seccion Mes --                                              */
 /*   Cuando el usuario decide solo notificar en X cantidad de meses antes                      */
    public boolean checkDatosxMeses(Correo correo, int meses) {
        boolean allOk = false;
        int mesesRestantes = (int) monthsBetween(convertToLocal(today), convertToLocal(correo.getFechaEnvio()));
        System.out.println(" ! Meses entre dia actual y la fecha meta " + mesesRestantes);

        if (meses <= mesesRestantes && meses > 0) {
            allOk = true;
        } else {
            System.out.println(" Error: Datos proporcionados son erroneos. \n"
                    + "* La cantidad de meses proporcionada sobrepasa los meses actuales\n");
        }

        return allOk;
    }

    public Date genFechasxMes(Calendar calendario) {
        int orgMonth = calendario.getTime().getMonth();

        calendario.set(Calendar.MONTH, orgMonth - 1);
        Date fecha = calendario.getTime();

        return fecha;
    }

    public void testMeses(Correo correo, int meses) {

        Calendar cal = new GregorianCalendar();
        cal.setTime(correo.getFechaEnvio());
        List<Date> fechas = new ArrayList<>();

        for (int i = 1; i <= meses; i++) {
            fechas.add(this.genFechasxMes(cal));
        }

        Collections.sort(fechas);

        List<Recordatorio> recordatorios = this.asignarCuerpo(fechas, correo);
        for (Recordatorio rec : recordatorios) {
            System.out.println("Rec: " + rec.getId());
            System.out.println("Fecha: " + rec.getFechaEnvio());
        }

    }

    /*                           -- Fin seccion Meses --                                         */
 /*                          -- Seccion MesesXVecesX Frecuencia --                            */
    public boolean checkDatosMesesXFrecuencia(Correo correo, int veces, int meses, int frecuencia) {

        boolean allOk = true;
        Calendar cal = new GregorianCalendar();
        cal.setTime(correo.getFechaEnvio());
        List<Date> fechas = new ArrayList<>();
        int maxDay = 0;
        int capxMes = 0;

        for (int i = 1; i <= meses; i++) {
            Date fecha = this.genFechasxMes(cal);
            fechas.add(fecha);
        }
        Collections.sort(fechas);

        for (Date fecha : fechas) {
            Calendar calendario = new GregorianCalendar();
            calendario.setTime(fecha);
            maxDay = calendario.getActualMaximum(Calendar.DAY_OF_MONTH);
            capxMes = Math.round(maxDay / veces);

            if (capxMes < frecuencia) {
                allOk = false;
                System.out.println(" Error: Frecuencia digitada no se soporta en el mes " + calendario.getTime().getMonth() + " del año " + calendario.getTime().getYear());
            }

        }

        if (allOk) {
            System.out.println(" All set! (Meses x Frecuencia)");
        }
        return allOk;
    }

    public void testerMesesXFrecuencia(Correo correo, int veces, int meses, int frecuencia) {

        boolean chk = this.checkDatosMesesXFrecuencia(correo, veces, meses, frecuencia);
        List<Date> fechas = new ArrayList<>();
        List<Date> borrador = new ArrayList<>();
        Calendar cal = new GregorianCalendar();
        cal.setTime(correo.getFechaEnvio());

        if (chk) {
            for (int i = 1; i <= meses; i++) {
                Date fecha = this.genFechasxMes(cal);
                fechas.add(fecha);
            }

            for (Date lista : fechas) {
                System.out.println("gen Fecha: " + lista.toGMTString());
            }

            for (Date fechaIN : fechas) {

                Calendar dummyCal = new GregorianCalendar();
                dummyCal.setTime(fechaIN);
                dummyCal.set(Calendar.DAY_OF_MONTH, 1);
                borrador.add(dummyCal.getTime());

                for (int vez = 1; vez < veces; vez++) {
                    dummyCal.add(Calendar.DAY_OF_MONTH, frecuencia);
                    borrador.add(dummyCal.getTime());
                }
            }

            List<Recordatorio> recordatorios = this.asignarCuerpo(borrador, correo);
            for (Recordatorio rec : recordatorios) {
                System.out.println("Rec: " + rec.getId());
                System.out.println("Fecha: " + rec.getFechaEnvio());
            }

        }

    }

    /*                         -- Fin seccion MesesXVecesX Frecuencia*/
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
        int restantes = 0;
        if (d1.getYear() <= d2.getYear()) {
            restantes = d1.getMonthValue() - d2.getMonthValue();
        } else {
            restantes = d2.getMonthValue() - d1.getMonthValue();
        }

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
    public Calendar genCalendar(Date fecha) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(fecha);
        return cal;
    }

    /* genDate */
 /* Genera un valor tipo date con los valores definidos de YYYY/MM/DD*/
    public Date dateGen(int y, int m, int d) {
        Date fecha = new Date();
        fecha.setYear(y);
        fecha.setMonth(m);
        fecha.setDate(d);
        return fecha;
    }

    /* -- calendarToDate -- 
     Pasa calores calendar a Date*/
    public Date calendarToDate(Calendar cal) {
        return cal.getTime();
    }

    public Calendar dateToCalendar(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal;
    }

    public boolean isLate(Date today, Date due) {
        boolean late = false;
        if (due.before(today)) {
            late = true;
        }
        return late;
    }

    /* -- compareDates-- */
 /* Compara dos fechas si son iguales*/
    public boolean compareDates(Date cal1, Date cal2) {
        boolean chk = false;

        if (cal1.equals(cal2)) {
            chk = true;
        }
        return chk;
    }

    /*   --  asignarCuerpo  -- 
    Le asigna el cuerpo a un recordatorio en base a las fechas generadas*/
    public List<Recordatorio> asignarCuerpo(List<Date> fechas, Correo correo) {
        List<Recordatorio> recordatorios = new ArrayList<>();

        for (Date fecha : fechas) {
            Recordatorio recordatorio = new Recordatorio();
            recordatorio.setCorreo(correo);
            recordatorio.setFechaEnvio(fecha);
            recordatorio.setEstado(Boolean.FALSE);
            recordatorios.add(recordatorio);
            dao.save(recordatorio);
        }

        return recordatorios;
    }

    public void editarRecordatorio(int target, boolean estado, Date fechaEnvio) {
        try {
            Recordatorio rec = dao.getById(target);
            Correo correo = daoCorreo.getById(rec.getCorreo().getId());
            Date now = new Date();

            if (fechaEnvio.compareTo(now) > 0 && correo.getFechaEnvio().compareTo(fechaEnvio) > 0) {
                rec.setEstado(estado);
                rec.setFechaEnvio(fechaEnvio);
                dao.update(rec);
            } else {
                System.out.println("Error: Fecha no valdia! \n");
            }

        } catch (Exception e) {
            System.out.println("Error en editar recordatorio. " + e);
        }

    }

    public void deleteRecordatorio(int target) {
        dao.delete(dao.getById(target));
    }

    public boolean checkMonthAv(Date fechaActual, Date fechaMeta, int veces, int meses) {
        boolean allOk = true;
        YearMonth yearMonthObject = null;
        int daysInMonth =0;
        
        yearMonthObject = YearMonth.of(fechaActual.getYear(), fechaActual.getMonth());
        daysInMonth = yearMonthObject.lengthOfMonth();
        
        /* Si las veces caben en el primer mes se ejecuta el resto de checks*/
        if(veces <= daysInMonth ){
            
        Calendar cal = new GregorianCalendar();
        cal.setTime(fechaActual);
         
        for(int i=1; i < meses ; i++){
         cal.add(Calendar.MONTH, cal.getTime().getMonth()+i);
         yearMonthObject = YearMonth.of(cal.getTime().getYear(),cal.getTime().getMonth());
         daysInMonth = yearMonthObject.lengthOfMonth();
         
            if(veces > daysInMonth){
                allOk=false;
            }
        }
        
        }else{
            allOk = false;
        }
        
        
        return allOk;
    }
}
