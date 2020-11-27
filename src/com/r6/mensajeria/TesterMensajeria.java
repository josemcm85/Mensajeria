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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Instant;

import com.r6.service.Dao;
import com.r6.service.HardwareDao;
import com.r6.service.ManualDao;
import com.r6.service.ProductoDao;
import com.r6.service.AdjuntoDao;
import com.r6.service.BitacoraDao;
import com.r6.service.ContactoDao;
import com.r6.service.Control;
import com.r6.service.RecordatorioDao;
import com.r6.service.SistemaDao;
import com.r6.service.SoftwareDao;
import com.r6.service.CorreoDao;
import com.r6.service.UsuarioDao;
import com.r6.funciones.RecordatorioFunc;
import com.r6.service.ClienteDao;
import com.r6.service.CompanniaDao;
import com.r6.service.IndividuoDao;
import com.r6.service.OrdenDao;
import com.r6.service.Servicio;

/**
 *
 * @author Nvidi
 */
public class TesterMensajeria {

	private static EntityManagerFactory entityManagerFactory = null;
	private static EntityManager em = null;
	private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        try {

            Servicio.setServerURL("jdbc:mysql://localhost:3306/mensajeria?serverTimezone=UTC");
            Servicio.setUsername("root");
            Servicio.setPassword("123");
            Servicio.setHbm2DDLprotocol("create");
            Servicio.setDriver("com.mysql.jdbc.Driver");
            Servicio.setDialect("org.hibernate.dialect.MySQLDialect");

            Servicio.startEntityManagerFactory();
            em = Servicio.getEm();

            //startEntityManagerFactory();
            // Control prueba = new Control();
            // prueba.setEm(em);
            // prueba.controlCorreos();
            //daoM = new SistemaDao();
            //((SistemaDao) daoM).setEm(em);
            //Sistema s = (Sistema) ((SistemaDao) daoM).get(new Integer(1)).get();
            //Dao daoM = new UsuarioDao();
            //((UsuarioDao) daoM).setEm(em);
            //Usuario u = (Usuario) ((UsuarioDao) daoM).get(new Integer(1)).get();
//            AdjuntoDao adDao = new AdjuntoDao();
//            adDao.setEm(Servicio.getEm());
//            
//            String directorioAdjunto = "C:/Users/Nvidi/Documents/AA.docx";
//            String directorioAdjunto2 = "C:/Users/Nvidi/Documents/AB.docx";
//            File file = new File(directorioAdjunto);
//            File file2 = new File(directorioAdjunto2);
//            
//            byte[] fileContent = Files.readAllBytes(file.toPath());
//            byte[] fileContent2 = Files.readAllBytes(file2.toPath());
//
//            //Adjunto   \
//            //List<File> files  = new ArrayList<>();
//            Adjunto adj = new Adjunto();
//            adj.setArchivo(fileContent);
//            
//            Adjunto adj2 = new Adjunto();
//            adj2.setArchivo(fileContent2);
//            
//            adDao.save(adj);
//            adDao.save(adj2);
            

              // startEntityManagerFactory();
              // Control prueba = new Control();
              // prueba.setEm(em);
              // prueba.controlCorreos();

              //daoM = new SistemaDao();
              //((SistemaDao) daoM).setEm(em);
              //Sistema s = (Sistema) ((SistemaDao) daoM).get(new Integer(1)).get();
              //Dao daoM = new UsuarioDao();
              //((UsuarioDao) daoM).setEm(em);
              //Usuario u = (Usuario) ((UsuarioDao) daoM).get(new Integer(1)).get();
            

            AdjuntoDao adDao = new AdjuntoDao();
            adDao.setEm(Servicio.getEm());

            String directorioAdjunto = "C:\\Users\\andya\\Downloads\\text.txt";
            String directorioAdjunto2 = "C:\\Users\\andya\\Downloads\\text.txt";
            File file = new File(directorioAdjunto);
            File file2 = new File(directorioAdjunto2);

            byte[] fileContent = Files.readAllBytes(file.toPath());
            byte[] fileContent2 = Files.readAllBytes(file2.toPath());

            //Adjunto   \
            //List<File> files  = new ArrayList<>();
            Adjunto adj = new Adjunto();
            adj.setArchivo(fileContent);

            Adjunto adj2 = new Adjunto();
            adj2.setArchivo(fileContent2);

            adDao.save(adj);
            adDao.save(adj2);

            //prueba.controlRecordatorios();
//            sistemaTest();
//            UsuarioTest();
//            contactoTest();
//            bitacoraTest(); 
//            recordatoriosTest();
            // recordatoriosTest();
             nuevosDaosGCTest();
           // recordatoriosTest();
            
            //productos
//            productoTest();
//            softwareDaoTest();
//            hardwareDaoTest();
//            manualDaoTest();

            Servicio.stopEntityManagerFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
}

    }

    public static void nuevosDaosGCTest() {
        
        //Se testean los siguientes DAOS : Cliente, individuo,Compania,orden,itemorden
        
        Dao masterDao = new ClienteDao();
        ((ClienteDao) masterDao).setEm(Servicio.getEm());

        //Modelos para los Clientes
        Cliente c1 = new Cliente();
        Cliente c2 = new Cliente();

        c1.setNombre("Cliente 1");
        c1.setDireccion("orem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        c1.setTipo(2);
        c1.setTelefono("6544654");

        c2.setNombre("Cliente 2");
        c2.setDireccion("orem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        c2.setTipo(1);
        c2.setTelefono("6846464");

        //Guardado de clientes
        ((ClienteDao) masterDao).save(c1);
        ((ClienteDao) masterDao).save(c2);

        //Consultas de clientes
        System.out.println("-Consulta de clientes-");
        for (Cliente c : ((ClienteDao) masterDao).getAll()) {
            System.out.println("Cliente: " + c.getIdCliente() + " " + c.getNombre() + " " + c.getDireccion() + " " + c.getTelefono());
        }
        System.out.println("");

        //Modelos para los individuos
        Individuo per1 = new Individuo();
        Individuo per2 = new Individuo();
        Individuo per3 = new Individuo();

        per1.setNumeroLic("NJDKAI-32");
        per1.setCliente(c1);

        per2.setNumeroLic("JKADHS-22");
        per2.setCliente(c1);

        per3.setNumeroLic("DSFSDF-65");
        per3.setCliente(c2);

        masterDao = new IndividuoDao();
        ((IndividuoDao) masterDao).setEm(Servicio.getEm());

        //Insercion de individuos
        ((IndividuoDao) masterDao).save(per1);
        ((IndividuoDao) masterDao).save(per2);
        ((IndividuoDao) masterDao).save(per3);

        System.out.println("Consulta de individuos");
        //Consultas de individuos
        for (Individuo ind : ((IndividuoDao) masterDao).getAll()) {
            System.out.println("Individuo: " + ind.getIdIndividuo() + " " + ind.getCliente().getNombre() + " " + ind.getNumeroLic());
        }
        System.out.println("");

        //Modelos para las companias
        Compannia comp1 = new Compannia();
        Compannia comp2 = new Compannia();
        Compannia comp3 = new Compannia();

        comp1.setCliente(c2);
        comp1.setContacto("82938298");
        comp1.setDescuento(10);

        comp2.setCliente(c2);
        comp2.setContacto("34655454");
        comp2.setDescuento(10);

        comp3.setCliente(c1);
        comp3.setContacto("56457354");
        comp3.setDescuento(15);

        //Insercion de companias
        masterDao = new CompanniaDao();
        ((CompanniaDao) masterDao).setEm(Servicio.getEm());

        ((CompanniaDao) masterDao).save(comp1);
        ((CompanniaDao) masterDao).save(comp2);
        ((CompanniaDao) masterDao).save(comp3);

        System.out.println("Consulta de compannias");
        //Consultas de companias
        for (Compannia comp : ((CompanniaDao) masterDao).getAll()) {
            System.out.println("Compania: " + comp.getIdCompannia() + " " + comp.getDescuento() + " " + comp.getCliente().getNombre());
        }
        System.out.println("");

        //Modelos para las ordenes
        Orden ord1 = new Orden();
        Orden ord2 = new Orden();
        Orden ord3 = new Orden();
        Orden ord4 = new Orden();
        Orden ord5 = new Orden();

        ord1.setCliente(c1);
        ord1.setFechaOrden(Calendar.getInstance().getTime());
        ord1.setTotalOrden(108.9);

        ord2.setCliente(c1);
        ord2.setFechaOrden(Calendar.getInstance().getTime());
        ord2.setTotalOrden(208.9);

        ord3.setCliente(c2);
        ord3.setFechaOrden(Calendar.getInstance().getTime());
        ord3.setTotalOrden(308.9);

        ord4.setCliente(c2);
        ord4.setFechaOrden(Calendar.getInstance().getTime());
        ord4.setTotalOrden(408.9);

        ord5.setCliente(c2);
        ord5.setFechaOrden(Calendar.getInstance().getTime());
        ord5.setTotalOrden(508.9);
        
        //Insercion de ordenes
        masterDao  = new OrdenDao();
        ((OrdenDao)masterDao).setEm(Servicio.getEm());
        
        ((OrdenDao)masterDao).save(ord1);
        ((OrdenDao)masterDao).save(ord2);
        ((OrdenDao)masterDao).save(ord3);
        ((OrdenDao)masterDao).save(ord4);
        ((OrdenDao)masterDao).save(ord5);
        
        System.out.println("Consultas de ordenes");
        //Consultas de ordenes
        for(Orden ord: ((OrdenDao)masterDao).getAll()){
            System.out.println("Orden : "+ord.getCliente().getNombre()+" "+ord.getIdOrden()+" "+ord.getTotalOrden()+" "+ord.getFechaOrden());
        }
        
//        //Modelos de productos
//        Producto prod1 = new  Producto();
//        Producto prod2 = new  Producto();
//        Producto prod3 = new  Producto();
//        
//        prod1.setDescripcion("orem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
//        
//        
//        
//        //Modelos de itemorden
//        Itemorden iord1 = new Itemorden();
//        Itemorden iord2 = new Itemorden();
//        Itemorden iord3 = new Itemorden();
//        
//        iord1.setCantidad(2);
//        iord1.setImpuesto(new Double(13));
//        iord1.setNumLinea(1);
//        iord1.setPrecioUnitario(new Double(5));
//        iord1.setTotalItem(new Double(10));
//        iord1.setOrden(ord1);
//
//        iord2.setImpuesto(new Double(13));
//        iord2.setNumLinea(1);
//        iord2.setPrecioUnitario(new Double(5));
//        iord2.setTotalItem(new Double(10));
//        iord2.setOrden(ord1);
        
        
        
        
    }

    public static void poblarModelo() {

        /*

        //Colocar aqu� el directorio en que est� el archivo adjunto
        String directorioAdjunto = "C:/Users/Daniel/Documents/Setup/setupMensajeria.txt";
        try {
            startEntityManagerFactory();

            SistemaDao sis = new SistemaDao(em);

            Sistema sist = new Sistema();
            sist.setNombre("Contabilidad");
            sist.setActivo(true);

            sis.save(sist);

            UsuarioDao uDao = new UsuarioDao(em);

            Usuario usuario = new Usuario(2, "teamr6cr@gmail.com", "bada907817", false, false, false);
            usuario.setSistema(sist);
            uDao.save(usuario);
            Usuario usuario2 = new Usuario(2, "ventasestructurasulatina@gmail.com", "wvjjk611", false, false, false);
            usuario2.setSistema(sist);
            uDao.save(usuario2);

            ContactoDao conc = new ContactoDao();
            conc.setEm(em);

            Contacto contacto1 = new Contacto();
            contacto1.setNombre("Wes");
            contacto1.setApellido("Mena");
            contacto1.setCorreo("weslinmena@gmail.com");
            contacto1.setSuscriptor(true);
            contacto1.setUsuario(usuario);
            conc.save(contacto1);

            Contacto cont = new Contacto();
            cont.setNombre("Jese");
            cont.setApellido("Chavez");
            cont.setCorreo("jese.chavez@ulatina.net");
            cont.setSuscriptor(false);
            cont.setUsuario(usuario);
            conc.save(cont);

            Contacto contacto3 = new Contacto();
            contacto3.setNombre("Jose");
            contacto3.setApellido("Chaves");
            contacto3.setCorreo("josemcm85@gmail.com");
            contacto3.setSuscriptor(true);
            contacto3.setUsuario(usuario2);
            conc.save(contacto3);

            Contacto contacto4 = new Contacto();
            contacto4.setNombre("Daniel");
            contacto4.setApellido("Hern�ndez");
            contacto4.setCorreo("daniel.hernandez20@ulatina.net");
            contacto4.setSuscriptor(false);
            contacto4.setUsuario(usuario2);
            conc.save(contacto4);

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
            c.setAsunto(" Asunto1 ");
            c.setCuerpo(" Cuerpo1 ");
            c.setFechaEnvio(cal.getTime());
            c.setTipo("html/text");

            //Remitente
            Set<Usuario> usuarioList1 = new HashSet<>();
            usuarioList1.add(usuario);
            c.setUsuarios(usuarioList1);

            //Destinatario
            Set<Contacto> contactList1 = new HashSet<>();
            contactList1.add(contacto1);
            contactList1.add(cont);
            c.setDestinatarios(contactList1);

            //Copiados
            Set<Usuario> usuarioCopyList = new HashSet<>();
            usuarioCopyList.add(usuario2);
            c.setUsuarioscopiados(usuarioCopyList);

            //Adjuntos
            AdjuntoDao adDao = new AdjuntoDao();
            adDao.setEm(em);

            File file = new File(directorioAdjunto);
            byte[] fileContent = Files.readAllBytes(file.toPath());
            //Adjunto   \

            List<File> files = new ArrayList<>();
            Adjunto adj = new Adjunto();
            adj.setArchivo(fileContent);

            adDao.save(adj);

            Set<Adjunto> adjuntoList = new HashSet<>();
            adjuntoList.add(adj);

            c.setAdjuntos(adjuntoList);

            funtion.crearRecxMes(c, 2);

            //Correo 2 
            Date date2 = new Date();
            Calendar cal2 = new GregorianCalendar();
            cal2.setTime(date2);
            Correo correo2 = new Correo();
            correo2.setAsunto(" Asunto2 ");
            correo2.setCuerpo(" Cuerpo2 ");
            correo2.setFechaEnvio(cal2.getTime());
            correo2.setTipo("html/text");

            //Remitente
            Set<Usuario> usuarioList2 = new HashSet<>();
            usuarioList2.add(usuario);
            correo2.setUsuarios(usuarioList2);

            //Destinatario
            Set<Contacto> contactList2 = new HashSet<>();
            contactList2.add(cont);

            correo2.setDestinatarios(contactList2);

            funtion.crearRecxMes(correo2, 0);

            //Correo3
            Date date3 = new Date();
            Calendar cal3 = new GregorianCalendar();
            cal3.setTime(date3);
            Correo correo3 = new Correo();
            correo3.setAsunto(" Asunto3 ");
            correo3.setCuerpo(" Cuerpo3 ");
            correo3.setFechaEnvio(cal3.getTime());
            correo3.setTipo("html/text");

            //Remitente
            Set<Usuario> usuarioList3 = new HashSet<>();
            usuarioList3.add(usuario2);
            correo2.setUsuarios(usuarioList3);

            //Destinatario
            Set<Contacto> contactList3 = new HashSet<>();
            contactList3.add(contacto3);

            correo3.setDestinatarios(contactList3);

            funtion.crearRecxMes(correo3, 0);

            //Correo4
            Date date4 = new Date();
            Calendar cal4 = new GregorianCalendar();
            cal4.setTime(date4);
            Correo correo4 = new Correo();
            correo4.setAsunto(" Asunto4");
            correo4.setCuerpo(" Cuerpo4 ");
            correo4.setFechaEnvio(cal4.getTime());
            correo4.setTipo("html/text");

            //Remitente
            Set<Usuario> usuarioList4 = new HashSet<>();
            usuarioList4.add(usuario2);
            correo4.setUsuarios(usuarioList4);

            //Destinatario
            Set<Contacto> contactList4 = new HashSet<>();
            contactList4.add(contacto4);

            correo4.setDestinatarios(contactList4);

            funtion.crearRecxMes(correo4, 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
         */
    }

    public static void bitacoraTest() throws IOException {
        /*
        BitacoraDao dao = new BitacoraDao();
        dao.setEm(em);

        AdjuntoDao adDao = new AdjuntoDao();
        adDao.setEm(em);

        File file = new File("C:/Users/Daniel/Documents/Setup/setupMensajeria.txt");
        byte[] fileContent = Files.readAllBytes(file.toPath());
        //Adjunto   \

        List<File> files = new ArrayList<>();
        Adjunto adj = new Adjunto();
        adj.setArchivo(fileContent);

        adDao.save(adj);

        Bitacora bit = new Bitacora();
        bit.setAsunto("Asunto");
        bit.setCopiados("jesechx@gmail.com");
        bit.setCuerpo("Lorem ipsum dolor sit amet, consectetur adipiscing"
                + " elit, "
                + "sed do eiusmod tempor incididunt ut labore et dolore magna "
                + "aliqua. "
                + "Ut enim ad minim veniam, quis nostrud exercitation ullamco l");
        bit.setDe("jesechx@gmail.com");
        bit.setEnviado(true);
        Calendar c = Calendar.getInstance();
        c.set(2020, 8, 2);
        bit.setFechaEnvio(c.getTime());
        bit.setPara("jesechavez009@gmail.com");
        bit.setTipo("HTML");
        Set<Adjunto> cc = new HashSet<>();
        cc.add(adj);
        bit.setAdjuntosBitacora(cc);

        //Guardar
        dao.save(bit);

        //Listar
        System.out.println("Listar todos despues de un guardado");
        for (Bitacora b : dao.getAll()) {
            System.out.println("Bitacora : ");
            System.out.println("De :" + b.getDe());
            System.out.println("Para :" + b.getPara());
            System.out.println("Asunto :" + b.getAsunto());
            System.out.println("Cuerpo :" + b.getCuerpo());
            System.out.println("Copiados :" + b.getCopiados());
            System.out.println("Enviado :" + b.getEnviado());
            System.out.println("Fecha :" + b.getFechaEnvio());
            for (Adjunto ad : b.getAdjuntosBitacora()) {
                System.out.println("ADJ: " + ad.getArchivo().toString());
            }

        }
        System.out.println("");
        //Seleccionar por llave primaria
        System.out.println("Seleccion por llave primaria");
        try {

            Optional<Bitacora> b = dao.get(new Integer(1));
            System.out.println("Bitacora : ");
            System.out.println("ID :" + b.get().getId());
            System.out.println("De :" + b.get().getDe());
            System.out.println("Para :" + b.get().getPara());
            System.out.println("Asunto :" + b.get().getAsunto());
            System.out.println("Cuerpo :" + b.get().getCuerpo());
            System.out.println("Copiados :" + b.get().getCopiados());
            System.out.println("Enviado :" + b.get().getEnviado());
            System.out.println("Fecha envio :" + b.get().getFechaEnvio());
            for (Adjunto ad : b.get().getAdjuntosBitacora()) {
                System.out.println("ADJ: " + ad.getArchivo().toString());
            }

        } catch (Exception e) {
            System.err.println("No se ha encontrado");
        }

        //Busqueda por usuario
        System.out.println("");
        System.out.println("Buscando con el criterio : %jese%%");
        for (Bitacora b : dao.getByUser("%jese%")) {
            System.out.println("ID :" + b.getId());
            System.out.println("Fecha :" + b.getFechaEnvio());
        }

        //Busqueda por Archivo
        em.refresh(adj);
        System.out.println("Buscando por archivo : " + adj.getArchivo().toString());
        for (Bitacora b : dao.getByFile(adj)) {
            System.out.println("ID: " + b.getId());
            System.out.println("ARCHIVOS :");
            for (Adjunto da : b.getAdjuntosBitacora()) {
                System.out.println("" + da.getArchivo().toString());
            }
        }

        //Busqueda por rango de fechas 
        Date d1, d2;

        c.set(2020, 8, 01);
        d1 = c.getTime();

        c.set(2020, 8, 31);
        d2 = c.getTime();

        System.out.println("Busqueda por rango de fechas De: " + d1.toString() + " a: " + d2.toString());
        for (Bitacora b : dao.getByDateRange(d1, d2)) {
            System.out.println("Bitacora : ");
            System.out.println("ID :" + b.getId());
            System.out.println("De :" + b.getDe());
            System.out.println("Para :" + b.getPara());
            System.out.println("Asunto :" + b.getAsunto());
            System.out.println("Cuerpo :" + b.getCuerpo());
            System.out.println("Copiados :" + b.getCopiados());
            System.out.println("Enviado :" + b.getEnviado());
            System.out.println("Fecha envio :" + b.getFechaEnvio());
            for (Adjunto ad : b.getAdjuntosBitacora()) {
                System.out.println("ADJ: " + ad.getArchivo().toString());
            }
        }
        
         */
    }

    public static void contactoTest() {
        /*
        //Inicializacion del DAO
        ContactoDao contDao = new ContactoDao();
        contDao.setEm(em);

        //Aca deberia haber una query a la entidad de Usuario, pero de momento probare los efectos con  un objeto
        Usuario user = new Usuario();
        user.setIdUsuario(1);
        contDao.setUsuario(user);

        //Crear
        Contacto cont = new Contacto();
        //cont.setId(contDao.maxId() + 1);
        cont.setNombre("Jese");
        cont.setApellido("Chavez");
        cont.setCorreo("jese.chavez@ylatina.net");
        cont.setSuscriptor(false);
        cont.setUsuario(user);

        contDao.save(cont);

        //Buscar por un ID
        System.out.println("Busqueda por ID");
        Optional<Contacto> ans = null;
        try {

            ans = contDao.get(new Integer(1));
            System.out.println("DATA : " + ans.get().getNombre());
        } catch (Exception e) {

            System.err.println("No se encontro");
        }

        //Buscarlos a todos
        System.out.println("Encontrados (Todos)");
        for (Contacto c : contDao.getAll()) {
            System.out.println(c.getNombre() + "-" + c.getCorreo() + c.getSuscriptor().toString());
        }

        System.out.println("Encontrados (Subs)");
        //Buscar a los suscriptores

        for (Contacto c : contDao.getAllSubs()) {
            System.out.println(c.getNombre() + "-" + c.getCorreo() + c.getSuscriptor().toString());
        }

        //Update 
        cont.setCorreo("jesechavez009@gmail.com");
        contDao.update(cont);

        //Leer despues del update
        System.out.println("Encontrados  (Despues de update)");
        for (Contacto c : contDao.getAll()) {
            System.out.println(c.getNombre() + "-" + c.getCorreo() + c.getSuscriptor().toString());
        }

        //Remove
        contDao.delete(cont);

        //Leer despues de un delete
        System.out.println("Encontrados  (Despues de eliminacion)");
        for (Contacto c : contDao.getAll()) {
            System.out.println(c.getNombre() + "-" + c.getCorreo() + c.getSuscriptor().toString());
        }
         */
    }

    public static void sistemaTest() {

        //SistemaDao sisDao = new SistemaDao(em);
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
    }

    public static void UsuarioTest() {

        //  UsuarioDao usDao = new UsuarioDao(em);
        //GUARDAR
        //Orden para crear usuario (Integer idSistema, String correo, String contrasenia, Boolean admin, Boolean superUser, Boolean activo)
        /*
        Usuario usuario = new Usuario(1,"test@mail.com","abc",false,false,false);
 
       usDao.save(usuario);

         */
        //ACTUALIZAR
        /*
        Usuario us2 =new Usuario();
        us2.actualizarUsuario(2);
        us2.setCorreo("testeando@mail.com");
        us2.setContrasenia("123");
        usDao.update(us2);
         */
        //Asignar a usuario nuevo sistema
        /*
         Usuario us3 =new Usuario();
         us3.actualizarUsuario(2);
         us3.nuevoSistemaUsuario(2);
         usDao.update(us3);
          
         */
        //BUSCAR TODOS

        /*

        for(Usuario u: usDao.getAll()) {
        	System.out.println("ID:"+u.getIdUsuario()+" Correo: "+u.getCorreo());
        }

         */
        //BUSCAR
        /*
         
        Optional<Usuario> us =usDao.get(new Integer(1));
        if(us.isPresent()) {
        	System.out.println("ID: "+us.get().getIdUsuario()+ " Correo: "+us.get().getCorreo());
        }else {
        	System.out.println("No se encontr� el Usuario");
        }
        
         */
        //DESACTIVAR/ACTIVAR
        /*
        Optional<Usuario> us = usDao.get(new Integer(2));
        usDao.EnableDisable(us.get());
         */
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
        c.setAsunto(" Prueba con Recordatorios Veces meses ");
        c.setCuerpo(" Esta es una prueba con recordatorios ");
        c.setFechaEnvio(cal.getTime());
        c.setTipo("HTML");
         */
 /* Crear y enviar Correo 	*/
//        funtion.crearRecxMes(c, 2);
        //Devuelve correos con recordatorios
        //  funtion.crearRecxVez(c, 4, 3);
        //  funtion.getCorreoswRecordatorios();

        // Editar Correo por ID
        //funtion.editarCorreo(1, "Correo Edit", "Correo Edit", true, cal.getTime(),"HTML");
        // Eliminar Correo
        // funtion.eliminarCorreo(1);
        /* Editar recordatorio por ID 
         cal.set(Calendar.DAY_OF_MONTH, Calendar.DAY_OF_MONTH-1);
         recFuntion.editarRecordatorio(1, true, cal.getTime());
         */
 /* Eliminar recordatorio por ID
         recFuntion.deleteRecordatorio(1);
         */
    }
    /*
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
     */
    }

	public static void poblarModelo() {

		/*
		 * 
		 * //Colocar aqu� el directorio en que est� el archivo adjunto String
		 * directorioAdjunto = "C:/Users/Daniel/Documents/Setup/setupMensajeria.txt";
		 * try { startEntityManagerFactory();
		 * 
		 * SistemaDao sis = new SistemaDao(em);
		 * 
		 * Sistema sist = new Sistema(); sist.setNombre("Contabilidad");
		 * sist.setActivo(true);
		 * 
		 * sis.save(sist);
		 * 
		 * UsuarioDao uDao = new UsuarioDao(em);
		 * 
		 * Usuario usuario = new Usuario(2, "teamr6cr@gmail.com", "bada907817", false,
		 * false, false); usuario.setSistema(sist); uDao.save(usuario); Usuario usuario2
		 * = new Usuario(2, "ventasestructurasulatina@gmail.com", "wvjjk611", false,
		 * false, false); usuario2.setSistema(sist); uDao.save(usuario2);
		 * 
		 * ContactoDao conc = new ContactoDao(); conc.setEm(em);
		 * 
		 * Contacto contacto1 = new Contacto(); contacto1.setNombre("Wes");
		 * contacto1.setApellido("Mena"); contacto1.setCorreo("weslinmena@gmail.com");
		 * contacto1.setSuscriptor(true); contacto1.setUsuario(usuario);
		 * conc.save(contacto1);
		 * 
		 * Contacto cont = new Contacto(); cont.setNombre("Jese");
		 * cont.setApellido("Chavez"); cont.setCorreo("jese.chavez@ulatina.net");
		 * cont.setSuscriptor(false); cont.setUsuario(usuario); conc.save(cont);
		 * 
		 * Contacto contacto3 = new Contacto(); contacto3.setNombre("Jose");
		 * contacto3.setApellido("Chaves"); contacto3.setCorreo("josemcm85@gmail.com");
		 * contacto3.setSuscriptor(true); contacto3.setUsuario(usuario2);
		 * conc.save(contacto3);
		 * 
		 * Contacto contacto4 = new Contacto(); contacto4.setNombre("Daniel");
		 * contacto4.setApellido("Hern�ndez");
		 * contacto4.setCorreo("daniel.hernandez20@ulatina.net");
		 * contacto4.setSuscriptor(false); contacto4.setUsuario(usuario2);
		 * conc.save(contacto4);
		 * 
		 * CorreoFunc funtion = new CorreoFunc(); RecordatorioFunc recFuntion = new
		 * RecordatorioFunc(); funtion.setEm(em); recFuntion.setEm(em);
		 * 
		 * Date date = new Date(); Calendar cal = new GregorianCalendar();
		 * cal.setTime(date); cal.add(Calendar.MONTH, 3); cal.add(Calendar.DAY_OF_MONTH,
		 * 1); Correo c = new Correo(); c.setAsunto(" Asunto1 ");
		 * c.setCuerpo(" Cuerpo1 "); c.setFechaEnvio(cal.getTime());
		 * c.setTipo("html/text");
		 * 
		 * //Remitente Set<Usuario> usuarioList1 = new HashSet<>();
		 * usuarioList1.add(usuario); c.setUsuarios(usuarioList1);
		 * 
		 * //Destinatario Set<Contacto> contactList1 = new HashSet<>();
		 * contactList1.add(contacto1); contactList1.add(cont);
		 * c.setDestinatarios(contactList1);
		 * 
		 * //Copiados Set<Usuario> usuarioCopyList = new HashSet<>();
		 * usuarioCopyList.add(usuario2); c.setUsuarioscopiados(usuarioCopyList);
		 * 
		 * //Adjuntos AdjuntoDao adDao = new AdjuntoDao(); adDao.setEm(em);
		 * 
		 * File file = new File(directorioAdjunto); byte[] fileContent =
		 * Files.readAllBytes(file.toPath()); //Adjunto \
		 * 
		 * List<File> files = new ArrayList<>(); Adjunto adj = new Adjunto();
		 * adj.setArchivo(fileContent);
		 * 
		 * adDao.save(adj);
		 * 
		 * Set<Adjunto> adjuntoList = new HashSet<>(); adjuntoList.add(adj);
		 * 
		 * c.setAdjuntos(adjuntoList);
		 * 
		 * funtion.crearRecxMes(c, 2);
		 * 
		 * //Correo 2 Date date2 = new Date(); Calendar cal2 = new GregorianCalendar();
		 * cal2.setTime(date2); Correo correo2 = new Correo();
		 * correo2.setAsunto(" Asunto2 "); correo2.setCuerpo(" Cuerpo2 ");
		 * correo2.setFechaEnvio(cal2.getTime()); correo2.setTipo("html/text");
		 * 
		 * //Remitente Set<Usuario> usuarioList2 = new HashSet<>();
		 * usuarioList2.add(usuario); correo2.setUsuarios(usuarioList2);
		 * 
		 * //Destinatario Set<Contacto> contactList2 = new HashSet<>();
		 * contactList2.add(cont);
		 * 
		 * correo2.setDestinatarios(contactList2);
		 * 
		 * funtion.crearRecxMes(correo2, 0);
		 * 
		 * //Correo3 Date date3 = new Date(); Calendar cal3 = new GregorianCalendar();
		 * cal3.setTime(date3); Correo correo3 = new Correo();
		 * correo3.setAsunto(" Asunto3 "); correo3.setCuerpo(" Cuerpo3 ");
		 * correo3.setFechaEnvio(cal3.getTime()); correo3.setTipo("html/text");
		 * 
		 * //Remitente Set<Usuario> usuarioList3 = new HashSet<>();
		 * usuarioList3.add(usuario2); correo2.setUsuarios(usuarioList3);
		 * 
		 * //Destinatario Set<Contacto> contactList3 = new HashSet<>();
		 * contactList3.add(contacto3);
		 * 
		 * correo3.setDestinatarios(contactList3);
		 * 
		 * funtion.crearRecxMes(correo3, 0);
		 * 
		 * //Correo4 Date date4 = new Date(); Calendar cal4 = new GregorianCalendar();
		 * cal4.setTime(date4); Correo correo4 = new Correo();
		 * correo4.setAsunto(" Asunto4"); correo4.setCuerpo(" Cuerpo4 ");
		 * correo4.setFechaEnvio(cal4.getTime()); correo4.setTipo("html/text");
		 * 
		 * //Remitente Set<Usuario> usuarioList4 = new HashSet<>();
		 * usuarioList4.add(usuario2); correo4.setUsuarios(usuarioList4);
		 * 
		 * //Destinatario Set<Contacto> contactList4 = new HashSet<>();
		 * contactList4.add(contacto4);
		 * 
		 * correo4.setDestinatarios(contactList4);
		 * 
		 * funtion.crearRecxMes(correo4, 0);
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 * 
		 */
	}

	public static void bitacoraTest() throws IOException {
		/*
		 * BitacoraDao dao = new BitacoraDao(); dao.setEm(em);
		 * 
		 * AdjuntoDao adDao = new AdjuntoDao(); adDao.setEm(em);
		 * 
		 * File file = new File("C:/Users/Daniel/Documents/Setup/setupMensajeria.txt");
		 * byte[] fileContent = Files.readAllBytes(file.toPath()); //Adjunto \
		 * 
		 * List<File> files = new ArrayList<>(); Adjunto adj = new Adjunto();
		 * adj.setArchivo(fileContent);
		 * 
		 * adDao.save(adj);
		 * 
		 * Bitacora bit = new Bitacora(); bit.setAsunto("Asunto");
		 * bit.setCopiados("jesechx@gmail.com");
		 * bit.setCuerpo("Lorem ipsum dolor sit amet, consectetur adipiscing" +
		 * " elit, " + "sed do eiusmod tempor incididunt ut labore et dolore magna " +
		 * "aliqua. " + "Ut enim ad minim veniam, quis nostrud exercitation ullamco l");
		 * bit.setDe("jesechx@gmail.com"); bit.setEnviado(true); Calendar c =
		 * Calendar.getInstance(); c.set(2020, 8, 2); bit.setFechaEnvio(c.getTime());
		 * bit.setPara("jesechavez009@gmail.com"); bit.setTipo("HTML"); Set<Adjunto> cc
		 * = new HashSet<>(); cc.add(adj); bit.setAdjuntosBitacora(cc);
		 * 
		 * //Guardar dao.save(bit);
		 * 
		 * //Listar System.out.println("Listar todos despues de un guardado"); for
		 * (Bitacora b : dao.getAll()) { System.out.println("Bitacora : ");
		 * System.out.println("De :" + b.getDe()); System.out.println("Para :" +
		 * b.getPara()); System.out.println("Asunto :" + b.getAsunto());
		 * System.out.println("Cuerpo :" + b.getCuerpo());
		 * System.out.println("Copiados :" + b.getCopiados());
		 * System.out.println("Enviado :" + b.getEnviado());
		 * System.out.println("Fecha :" + b.getFechaEnvio()); for (Adjunto ad :
		 * b.getAdjuntosBitacora()) { System.out.println("ADJ: " +
		 * ad.getArchivo().toString()); }
		 * 
		 * } System.out.println(""); //Seleccionar por llave primaria
		 * System.out.println("Seleccion por llave primaria"); try {
		 * 
		 * Optional<Bitacora> b = dao.get(new Integer(1));
		 * System.out.println("Bitacora : "); System.out.println("ID :" +
		 * b.get().getId()); System.out.println("De :" + b.get().getDe());
		 * System.out.println("Para :" + b.get().getPara());
		 * System.out.println("Asunto :" + b.get().getAsunto());
		 * System.out.println("Cuerpo :" + b.get().getCuerpo());
		 * System.out.println("Copiados :" + b.get().getCopiados());
		 * System.out.println("Enviado :" + b.get().getEnviado());
		 * System.out.println("Fecha envio :" + b.get().getFechaEnvio()); for (Adjunto
		 * ad : b.get().getAdjuntosBitacora()) { System.out.println("ADJ: " +
		 * ad.getArchivo().toString()); }
		 * 
		 * } catch (Exception e) { System.err.println("No se ha encontrado"); }
		 * 
		 * //Busqueda por usuario System.out.println("");
		 * System.out.println("Buscando con el criterio : %jese%%"); for (Bitacora b :
		 * dao.getByUser("%jese%")) { System.out.println("ID :" + b.getId());
		 * System.out.println("Fecha :" + b.getFechaEnvio()); }
		 * 
		 * //Busqueda por Archivo em.refresh(adj);
		 * System.out.println("Buscando por archivo : " + adj.getArchivo().toString());
		 * for (Bitacora b : dao.getByFile(adj)) { System.out.println("ID: " +
		 * b.getId()); System.out.println("ARCHIVOS :"); for (Adjunto da :
		 * b.getAdjuntosBitacora()) { System.out.println("" +
		 * da.getArchivo().toString()); } }
		 * 
		 * //Busqueda por rango de fechas Date d1, d2;
		 * 
		 * c.set(2020, 8, 01); d1 = c.getTime();
		 * 
		 * c.set(2020, 8, 31); d2 = c.getTime();
		 * 
		 * System.out.println("Busqueda por rango de fechas De: " + d1.toString() +
		 * " a: " + d2.toString()); for (Bitacora b : dao.getByDateRange(d1, d2)) {
		 * System.out.println("Bitacora : "); System.out.println("ID :" + b.getId());
		 * System.out.println("De :" + b.getDe()); System.out.println("Para :" +
		 * b.getPara()); System.out.println("Asunto :" + b.getAsunto());
		 * System.out.println("Cuerpo :" + b.getCuerpo());
		 * System.out.println("Copiados :" + b.getCopiados());
		 * System.out.println("Enviado :" + b.getEnviado());
		 * System.out.println("Fecha envio :" + b.getFechaEnvio()); for (Adjunto ad :
		 * b.getAdjuntosBitacora()) { System.out.println("ADJ: " +
		 * ad.getArchivo().toString()); } }
		 * 
		 */
	}

	public static void contactoTest() {
		/*
		 * //Inicializacion del DAO ContactoDao contDao = new ContactoDao();
		 * contDao.setEm(em);
		 * 
		 * //Aca deberia haber una query a la entidad de Usuario, pero de momento
		 * probare los efectos con un objeto Usuario user = new Usuario();
		 * user.setIdUsuario(1); contDao.setUsuario(user);
		 * 
		 * //Crear Contacto cont = new Contacto(); //cont.setId(contDao.maxId() + 1);
		 * cont.setNombre("Jese"); cont.setApellido("Chavez");
		 * cont.setCorreo("jese.chavez@ylatina.net"); cont.setSuscriptor(false);
		 * cont.setUsuario(user);
		 * 
		 * contDao.save(cont);
		 * 
		 * //Buscar por un ID System.out.println("Busqueda por ID"); Optional<Contacto>
		 * ans = null; try {
		 * 
		 * ans = contDao.get(new Integer(1)); System.out.println("DATA : " +
		 * ans.get().getNombre()); } catch (Exception e) {
		 * 
		 * System.err.println("No se encontro"); }
		 * 
		 * //Buscarlos a todos System.out.println("Encontrados (Todos)"); for (Contacto
		 * c : contDao.getAll()) { System.out.println(c.getNombre() + "-" +
		 * c.getCorreo() + c.getSuscriptor().toString()); }
		 * 
		 * System.out.println("Encontrados (Subs)"); //Buscar a los suscriptores
		 * 
		 * for (Contacto c : contDao.getAllSubs()) { System.out.println(c.getNombre() +
		 * "-" + c.getCorreo() + c.getSuscriptor().toString()); }
		 * 
		 * //Update cont.setCorreo("jesechavez009@gmail.com"); contDao.update(cont);
		 * 
		 * //Leer despues del update
		 * System.out.println("Encontrados  (Despues de update)"); for (Contacto c :
		 * contDao.getAll()) { System.out.println(c.getNombre() + "-" + c.getCorreo() +
		 * c.getSuscriptor().toString()); }
		 * 
		 * //Remove contDao.delete(cont);
		 * 
		 * //Leer despues de un delete
		 * System.out.println("Encontrados  (Despues de eliminacion)"); for (Contacto c
		 * : contDao.getAll()) { System.out.println(c.getNombre() + "-" + c.getCorreo()
		 * + c.getSuscriptor().toString()); }
		 */
	}

	public static void sistemaTest() {

		// SistemaDao sisDao = new SistemaDao(em);

		// GUARDAR
		/*
		 * Sistema sistema =new Sistema();
		 * 
		 * sistema.setActivo(true); sistema.setNombre("Recursos Humanos");
		 * 
		 * sisDao.save(sistema);
		 */
		// ACTUALIZAR
		/*
		 * Sistema sis1 =new Sistema(); sis1.setId(1); sis1.setNombre("Contabilidad");
		 * sis1.setActivo(true); sisDao.update(sis1);
		 */
		// BUSCAR TODOS
		/*
		 * for(Sistema s: sisDao.getAll()) {
		 * System.out.println("ID:"+s.getId()+" Nombre:"+s.getNombre()); }
		 */
		// BUSCAR
		/*
		 * Optional<Sistema> sis =sisDao.get(new Integer(1)); if(sis.isPresent()) {
		 * System.out.println("ID:"+sis.get().getId()+
		 * " Nombre:"+sis.get().getNombre()); }else {
		 * System.out.println("No se encontr� el sistema"); }
		 */
		// DESACTIVAR/ACTIVAR
		/*
		 * Optional<Sistema> sistDis =sisDao.get(new Integer(1));
		 * sisDao.EnableDisable(sistDis.get());
		 */

	}

	public static void UsuarioTest() {

		// UsuarioDao usDao = new UsuarioDao(em);

		// GUARDAR
		// Orden para crear usuario (Integer idSistema, String correo, String
		// contrasenia, Boolean admin, Boolean superUser, Boolean activo)
		/*
		 * Usuario usuario = new Usuario(1,"test@mail.com","abc",false,false,false);
		 * 
		 * usDao.save(usuario);
		 * 
		 */
		// ACTUALIZAR
		/*
		 * Usuario us2 =new Usuario(); us2.actualizarUsuario(2);
		 * us2.setCorreo("testeando@mail.com"); us2.setContrasenia("123");
		 * usDao.update(us2);
		 */
		// Asignar a usuario nuevo sistema
		/*
		 * Usuario us3 =new Usuario(); us3.actualizarUsuario(2);
		 * us3.nuevoSistemaUsuario(2); usDao.update(us3);
		 * 
		 */
		// BUSCAR TODOS

		/*
		 * 
		 * for(Usuario u: usDao.getAll()) {
		 * System.out.println("ID:"+u.getIdUsuario()+" Correo: "+u.getCorreo()); }
		 * 
		 */
		// BUSCAR
		/*
		 * 
		 * Optional<Usuario> us =usDao.get(new Integer(1)); if(us.isPresent()) {
		 * System.out.println("ID: "+us.get().getIdUsuario()+
		 * " Correo: "+us.get().getCorreo()); }else {
		 * System.out.println("No se encontr� el Usuario"); }
		 * 
		 */
		// DESACTIVAR/ACTIVAR
		/*
		 * Optional<Usuario> us = usDao.get(new Integer(2));
		 * usDao.EnableDisable(us.get());
		 */
	}

	public static void recordatoriosTest() {

		/*
		 * CorreoFunc funtion = new CorreoFunc(); RecordatorioFunc recFuntion = new
		 * RecordatorioFunc(); funtion.setEm(em); recFuntion.setEm(em);
		 * 
		 * Date date = new Date(); Calendar cal = new GregorianCalendar();
		 * cal.setTime(date); cal.add(Calendar.MONTH, 3); cal.add(Calendar.DAY_OF_MONTH,
		 * 1); Correo c = new Correo();
		 * c.setAsunto(" Prueba con Recordatorios Veces meses ");
		 * c.setCuerpo(" Esta es una prueba con recordatorios ");
		 * c.setFechaEnvio(cal.getTime()); c.setTipo("HTML");
		 */
		/* Crear y enviar Correo */
//        funtion.crearRecxMes(c, 2);
		// Devuelve correos con recordatorios
		// funtion.crearRecxVez(c, 4, 3);
		// funtion.getCorreoswRecordatorios();

		// Editar Correo por ID
		// funtion.editarCorreo(1, "Correo Edit", "Correo Edit", true,
		// cal.getTime(),"HTML");
		// Eliminar Correo
		// funtion.eliminarCorreo(1);
		/*
		 * Editar recordatorio por ID cal.set(Calendar.DAY_OF_MONTH,
		 * Calendar.DAY_OF_MONTH-1); recFuntion.editarRecordatorio(1, true,
		 * cal.getTime());
		 */
		/*
		 * Eliminar recordatorio por ID recFuntion.deleteRecordatorio(1);
		 */
	}

	public static void productoTest() {

		// ProductoDao Test
//      Producto product1 = new Producto();
//      ProductoDao productDao = new ProductoDao();
//      productDao.setEm(Servicio.getEm());
//      Optional<Producto> opt;

		// insert
//      
//      product1.setNombre("compu andres");
//      product1.setPrecio(200.0);
//      product1.setDescripcion("esta es la compu de andres");
//      product1.setTipo(1);
//      productDao.save(product1);

		// getall
//      for (Producto p : productDao.getAll()) {
//          System.out.println(p.getNombre() + "-" + p.getIdProducto());
//      }

		// update
//      product1.setIdProducto(1);
//      product1.setNombre("compu2");
//      productDao.update(product1);

		// Buscar con optional id
//      try {
//          opt = productDao.get(new Integer(1));
//          System.out.println("Valor :"+opt.get().getNombre());
//      } catch (Exception ex) {
//          System.err.println("No hubo resultados : "+ex.getMessage());
//      }
//      
//      //buscar con optional nombre
//      try {
//          opt = productDao.get(new String("compu andres"));
//          System.out.println("Valor :"+opt.get().getIdProducto());
//      } catch (Exception ex) {
//          System.err.println("No hubo resultados : "+ex.getMessage());
//      }
		// Buscar Lista precio
//      for (Producto p : productDao.getByPrecio(200.0)) {
//      	System.out.println(p.getIdProducto() + "-" + p.getNombre());
//      }
		// buscar lista de tipo
//      for (Producto p : productDao.getByTipo(1)) {
//      	System.out.println(p.getIdProducto() + "-" + p.getNombre());
//      }
	}
	public static void softwareDaoTest() {
		//SoftwareDao Test
//        Software software1 = new Software();
//        Producto product1 = new Producto();
//        Producto product2 = new Producto();
//        SoftwareDao softwareDao = new SoftwareDao();
//        softwareDao.setEm(Servicio.getEm());
//        Optional<Software> opt;
        
        //insert
        
//        software1.setLicencia("1234a");
//        software1.setProducto(product1);
//        softwareDao.save(software1);
        //delete 
       // softwareDao.delete(software1);
        //getall
//        for (Software s : softwareDao.getAll()) {
//            System.out.println(s.getLicencia() + "-" + s.getIdSoftware() + "-" + s.getProducto());
//        }

        //update
//        software1.setLicencia("abc");
//        software1.setProducto(product2);
//        softwareDao.update(software1);
        
        //Buscar con optional id
//        try {
//            opt = softwareDao.get(new Integer(1));
//            System.out.println("Valor :"+opt.get().getLicencia());
//        } catch (Exception ex) {
//            System.err.println("No hubo resultados : "+ex.getMessage());
//        }
        
        
       
        //Buscar Lista Productos
//        for (Software s : softwareDao.getByLicencia("abc")) {
//        	System.out.println(s.getIdSoftware());
//        }

          // startEntityManagerFactory();
          // Control prueba = new Control();
          // prueba.setEm(em);
          // prueba.controlCorreos();

          //daoM = new SistemaDao();
          //((SistemaDao) daoM).setEm(em);
          //Sistema s = (Sistema) ((SistemaDao) daoM).get(new Integer(1)).get();
          //Dao daoM = new UsuarioDao();
          //((UsuarioDao) daoM).setEm(em);
          //Usuario u = (Usuario) ((UsuarioDao) daoM).get(new Integer(1)).get();
	}
	public static void hardwareDaoTest() {
		//SoftwareDao Test
//        Hardware hardware1 = new Hardware();
//        Producto product1 = new Producto();
//        Producto product2 = new Producto();
//        HardwareDao hardwareDao = new HardwareDao();
//        hardwareDao.setEm(Servicio.getEm());
//        Optional<Hardware> opt;
        
        //insert           
//        hardware1.setImpuesto(2000.0);
//        hardware1.setPeriodoGarantia(3);
//        hardware1.setProducto(product1);
//        hardwareDao.save(hardware1);
        //delete 
       
        
        //getall
//        for (Hardware h : hardwareDao.getAll()) {
//            System.out.println(h.getIdHardware());
//        }

        //update
//        hardware1.setIdHardware(1);
//        hardware1.setPeriodoGarantia(2);
//        hardwareDao.update(hardware1);
        
        //Buscar con optional id
//        try {
//            opt = hardwareDao.get(new Integer(1));
//            System.out.println("Valor :"+opt.get().getIdHardware());
//        } catch (Exception ex) {
//            System.err.println("No hubo resultados : "+ex.getMessage());
//        }
//        //buscar getByPeriodoGarantia
//        for (Hardware h : hardwareDao.getByPeriodoGarantia(3)) {
//        	System.out.println(h.getIdHardware());
//        }
//        //Buscar getByImpuesto
//        for (Hardware h : hardwareDao.getByImpuesto(2000.0)) {
//        	System.out.println(h.getIdHardware());
//        }

          // startEntityManagerFactory();
          // Control prueba = new Control();
          // prueba.setEm(em);
          // prueba.controlCorreos();

          //daoM = new SistemaDao();
          //((SistemaDao) daoM).setEm(em);
          //Sistema s = (Sistema) ((SistemaDao) daoM).get(new Integer(1)).get();
          //Dao daoM = new UsuarioDao();
          //((UsuarioDao) daoM).setEm(em);
          //Usuario u = (Usuario) ((UsuarioDao) daoM).get(new Integer(1)).get();
        
	}
	public static void manualDaoTest() {
        //ManualDao Test
//        Manual manual1 = new Manual();
//        Producto product1 = new Producto();
//        Producto product2 = new Producto();
//        ManualDao manualDao = new ManualDao();
//        manualDao.setEm(Servicio.getEm());
//        Optional<Manual> opt;
        
        //insert           
//        manual1.setAutor("andres");
//        manual1.setProducto(product1);
//        manualDao.save(manual1);
        //delete 
       
        
        //getall
//        for (Manual h : manualDao.getAll()) {
//            System.out.println(h.getAutor());
//        }

        //update
//        manual1.setIdManual(1);
//        manual1.setAutor("andres a");
//        manual1.setProducto(product2);
//        manualDao.update(manual1);
        
        //Buscar con optional id
//        try {
//            opt = manualDao.get(new Integer(1));
//            System.out.println("Valor :"+opt.get().getAutor());
//        } catch (Exception ex) {
//            System.err.println("No hubo resultados : "+ex.getMessage());
//        }
//        //buscar por nombre 
//        for (Manual m : manualDao.getByAutor("andres a")) {
//        	System.out.println(m.getIdManual());
//        }

	}

	public static void startEntityManagerFactory() {
		if (entityManagerFactory == null) {
			try {
				entityManagerFactory = Persistence.createEntityManagerFactory("Mensajeria");
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

