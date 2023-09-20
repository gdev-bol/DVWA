/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import static java.lang.Boolean.TRUE;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.List;
import javax.rmi.CORBA.Util;
import javax.sql.DataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.BusquedaOposicion;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.entidad.OpoActividad;
import snp.gob.bo.gimodel.entidad.OpoEvento;
import snp.gob.bo.gimodel.entidad.OpoFechalimite;
import snp.gob.bo.gimodel.entidad.OpoHistorial;
import snp.gob.bo.gimodel.entidad.OpoMarcademandada;
import snp.gob.bo.gimodel.entidad.OpoMarcademandante;
import snp.gob.bo.gimodel.entidad.OpoNotificacion;
import snp.gob.bo.gimodel.entidad.OpoObjetoDmteDmdoNotiSoliapo;
import snp.gob.bo.gimodel.entidad.OpoRecurso;
import snp.gob.bo.gimodel.entidad.OpoResolucion;
import snp.gob.bo.gimodel.entidad.OpoSolicitanteapoderado;
import snp.gob.bo.gimodel.entidad.Oposicion;
import snp.gob.bo.gimodel.entidad.Seguimiento;
import snp.gob.bo.gimodel.servicio.BusquedaOposicionService;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.OpoActividadService;
import snp.gob.bo.gimodel.servicio.OpoEstadoService;
import snp.gob.bo.gimodel.servicio.OpoEventoService;
import snp.gob.bo.gimodel.servicio.OpoFechaLimiteService;
import snp.gob.bo.gimodel.servicio.OpoGeneralService;
import snp.gob.bo.gimodel.servicio.OpoHistorialService;
import snp.gob.bo.gimodel.servicio.OpoMarcademandadaService;
import snp.gob.bo.gimodel.servicio.OpoMarcademandanteService;
import snp.gob.bo.gimodel.servicio.OpoNotificacionService;
import snp.gob.bo.gimodel.servicio.OpoRecursoService;
import snp.gob.bo.gimodel.servicio.OpoResolucionService;
import snp.gob.bo.gimodel.servicio.OpoSolicitanteaopderadoService;
import snp.gob.bo.gimodel.servicio.OposicionService;
import snp.gob.bo.gimodel.servicio.SeguimientoService;
import snp.gob.bo.gimodel.servicio.impl.OpoSolicitanteapoderadoServiceImpl;

/**
 *
 * @author desarrollo
 */
public class oposicionesTest {

    public static void main(String[] args) throws Exception {

       ApplicationContext context = new FileSystemXmlApplicationContext("//home//luisangel//HIDRA//hidra//giview//src//main//webapp//WEB-INF//applicationContext.xml");
 
        System.out.println("holaaaaaaaaa");
        Formatter fmt = new Formatter();
        OpoMarcademandadaService oposicionserdmda = (OpoMarcademandadaService) context.getBean("opoMarcaDemandadaService");
        OpoNotificacionService oponotificasrv = (OpoNotificacionService) context.getBean("opoNotificacionService");
        OpoSolicitanteaopderadoService posolapo1 = (OpoSolicitanteaopderadoService) context.getBean("opoSolicitanteApoderadoService");
        OposicionService vardmte = (OposicionService) context.getBean("oposicionService");
        OposicionService datosss = (OposicionService) context.getBean("oposicionService");
        OpoEventoService varievento = (OpoEventoService) context.getBean("opoEventoService");
        OpoResolucionService variresolucion = (OpoResolucionService) context.getBean("opoResolucionService");
        ComunService vaiento = (ComunService) context.getBean("comunService");
        
        SeguimientoService variablesegui=(SeguimientoService) context.getBean("seguimientoService");

        OpoRecursoService varirecurso = (OpoRecursoService) context.getBean("opoRecursoService");

        OpoActividadService opoacti = (OpoActividadService) context.getBean("opoActividadService");

        OpoHistorialService opohisto = (OpoHistorialService) context.getBean("opoHistorialService");

        BusquedaOposicionService listbus = (BusquedaOposicionService) context.getBean("busquedaOposicionService");

        List<Oposicion> listaofi1 = new ArrayList<>();

        List<OpoActividad> listaofi = new ArrayList<>();

        List<OpoEvento> listaevento = new ArrayList<>();

        List<OpoHistorial> listahisto = new ArrayList<>();

        List<Dominio> listaresolucion = new ArrayList<>();

        List<OpoRecurso> listaoporecurso = new ArrayList<>();

        List<BusquedaOposicion> listadebusqueda = new ArrayList<>();

        Date Fecha = new Date();
        Calendar gregfech = new GregorianCalendar();
        gregfech.setTime(Fecha);
        System.out.println("fsadfsa " + gregfech.get(Calendar.YEAR));
        Integer a = 0;
        
        
        
        
        

//        OpoResolucion datosresolucion = new OpoResolucion();
//               OpoRecurso datosrecurso = new OpoRecurso();
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
//        
//        
//        listaoporecurso=varirecurso.validacionrecursorepetido(110);
//        
//        if(!listaoporecurso.isEmpty()){
//            int coa=1;
//            int coj=0;
//            int cor=0;
//        for (OpoRecurso bare : listaoporecurso) {
//         gregfech.setTime(bare.getFecha_resolucion());
//            System.out.println("============="+gregfech.get(Calendar.YEAR));
//                if(gregfech.get(Calendar.YEAR)==2016){
//                    coa++;
//                if(bare.getTipo().equals("j")){
//                    coj++;
//                }
//                if(bare.getTipo().equals("rev")){
//                    cor++;
//                }                
//                }
//            
//        } 
//            System.out.println("Repetidos el mismo año = "+coa);
//            System.out.println("==========>j = "+coj);
//            System.out.println("==========>rev = "+cor);
//            if(coa<=2 && coj<=1 && cor<=1){
//                System.out.println("===========>VERDAD");
//            }else
//            {
//                System.out.println("===========>FALSO");
//            }
//            
//        
//        }
        
        
        
        
//        
//        
//             Seguimiento seguimientoNuevo = new Seguimiento();
//             
//                seguimientoNuevo.setId(160117L);
//                seguimientoNuevo.setIdUsuario(42L);
//                seguimientoNuevo.setIdLogTrans(658L);
//                seguimientoNuevo.setSm(201700052600L);
//                seguimientoNuevo.setNumeroPublicacion(null);
//                seguimientoNuevo.setNumeroRegistro(null);
//                seguimientoNuevo.setSerieRegistro(null);
//                seguimientoNuevo.setEtapa("OPO");
//                seguimientoNuevo.setFechaRecepcion(Fecha);
//               
//                //seguimientoNuevo.setFechaFin();                
//                seguimientoNuevo.setObservacion(null);
//                seguimientoNuevo.setEditable(false);
//                seguimientoNuevo.setEstado("AC");
//                
//  variablesegui.guardar_modificar_listar_Seguimiento(seguimientoNuevo, "SIG", 1);
//        
        
//        System.out.println("::::::::::::::::::::::::::::::>"+Long.parseLong(""));
        
        
        
//        Long xx=posolapo1.obtieneelultimonumerodesolicitanteapoderado()+1L;
//        System.out.println("**************"+xx);

//        
//        datosresolucion.setIdoposicion(20L);
//            datosresolucion.setIdevento(15L);
//            datosresolucion.setIdlogtrans(1L);
//            datosresolucion.setNumero_resolucion(6969);
//            datosresolucion.setFecha(Fecha);
//            datosresolucion.setResolucion_cancelacion("concedida");
//            datosresolucion.setResolucion_oposicion("improvada");
//            datosresolucion.setResolucion_signo("alegada");
//            datosresolucion.setOrden_o(1);
//            datosresolucion.setEstado("AC");
//        
//            
//        variresolucion.guardaOpoResolucion(datosresolucion);
        
//         datosrecurso.setIdevento(20L);
//                datosrecurso.setIdoposicion(15L);
//                datosrecurso.setIdlogtrans(1L);
//                datosrecurso.setNumero_resolucion(69);
//                datosrecurso.setFecha_resolucion(Fecha);
//                datosrecurso.setTipo("j");
//                 datosrecurso.setResolucion("Recurso");
//                datosrecurso.setOrden_op(2);
//                datosrecurso.setEstado("AC");
//                varirecurso.guardaOpoRecurso(datosrecurso);
        
        
       //System.out.println("EL Archivo existe================="+vardmte.verificarexi(135005L));
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
//        listaresolucion=variresolucion.obtenerListadoOpoRecursoxtipodominio("tipoResuelveSeguimiento");
//        System.out.println("*****************"+listaresolucion);
//        for (Dominio data1 : listaresolucion) {
//            
//            System.out.println("==================>>"+data1.getNombre());
//            
//        }
//        
//
        //listadebusqueda = listbus.realizarBusquedaxnroregistro(116028, "C", "DMTE");     
      //  listadebusqueda = listbus.realizarBusquedaxnropublicacion(126613L, "DMDO");
//         listadebusqueda = listbus.realizarBusquedaxapoderado("MORENO","DMTE");
//        
//        System.out.println(":::::" + listadebusqueda);
//        for (BusquedaOposicion list1 : listadebusqueda) {
//            
//            System.out.println("===========================>"+list1.getNrocasopubl());
//        }
      //  System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxx"+fmt.format("%06d",15451));
//           String concate=Long.toString(2016L)+fmt.format("%06d",512L)+"00";
//           Formatter fmt1 = new Formatter();
//             String concate1=Long.toString(2016L)+fmt1.format("%06d",512L)+"00";
//           System.out.println("$$$$$$$$$$$$$$$$$$$$$"+concate1);
//        
//           
//           System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"+vaiento.obtenerFechaHoraServidor(1L));
//         listaoporecurso=varirecurso.obtenerListadoOpoRecursoxnrooposicion(754L);
//         
//         for (OpoRecurso listago : listaoporecurso) {
//         
//             
//             System.out.println("====>"+listago.getFecha_resolucion());
//             
//                 
//
//        }
//        objresolu=variresolucion.obtenerObjOporesolucion(754L);
//        System.out.println(";;;;;;;;;;;;;;"+objresolu);
//        System.out.println(objresolu.getIdresolucion()+"   "+objresolu.getIdoposicion()+"   >>>>>>>>"+objresolu.getResolucion_oposicion());
//        
//        OpoEvento objevento=new OpoEvento();
//        objevento.setIdevento(38344L);
//        objevento.setIdactividad(2L);
//        objevento.setIdoposicion(1475L);
//        objevento.setIdlogtrans(1L);
//        objevento.setFecha(Fecha);
//        objevento.setObservacion("Esta es la segunda observacion");
//        objevento.setUsuario(1L);
//        objevento.setOrden_o(1);
//        objevento.setFecha_operacion(Fecha);
//        objevento.setEstado("NA");
//        varievento.modificarOpoEvento(objevento);
//        
//        
//        listahisto = opohisto.listarhistorialxnroopo(9L);
//        for (OpoHistorial listhisto : listahisto) {
//
//            System.out.println("Datos==" + listhisto.getIdhistorial());
//        }
        // System.out.println("::::::::::::::::::::::::=>"+oposicionserdmda.verificasiexistenumeroopodmda(100));
//       listaofi1=vardmte.obtenerListadoOposicion(100L);
//          for (Oposicion lis1 : listaofi1) {
//              
//              System.out.println("================>"+lis1.getIdoposicion());
//            
//        }
//         
        //OpoFechaLimiteService varifechalim=(OpoFechaLimiteService)context.getBean("opoFechaLimiteService");
        //OpoHistorialService varihisto=(OpoHistorialService)context.getBean("opoHistorialService");
//        OpoActividadService activ = (OpoActividadService) context.getBean("opoActividadService");
//        OpoEstadoService opoesta = (OpoEstadoService) context.getBean("opoEstadoService");
//        System.out.println("===================$>" + opoesta.devuelvenombredelestadoxidestado(2L));
        //System.out.println("::::::::::::::::::::::::::>"+activ.muestranomdelaactividadxidactividad(2L));
        //System.out.println("============>"+varievento.extraeridenento(357L));
//        String abc = "";
//    
//        //varievento.eliminarOpoEvento(4L);
//        System.out.println("======================$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$======================>"+ oponotificasrv.obtenerListadoNotificaciondmda(4L));
//        
//        for (OpoNotificacion listaevento1 : oponotificasrv.obtenerListadoNotificaciondmda(4L)) {
//            System.out.println("====="+listaevento1.getEmail());
//        }
//        listaofi=opoacti.obtenerListadoActividades();
//        
//        for (OpoActividad op1 : listaofi) {
//            
//            System.out.println("::::::::::::::::>"+op1.getIdactividad()+"   "+op1.getDescri_idactividad()+"   "+op1.getActividad());
//            
//        }
//        
//        
        //System.out.println("========="+datosss.obtexpedientexnroopo(10L));
//        listaevento=varievento.obtenerListadoeventoxidoposicion(8L);
//        
//        for (OpoEvento op1 : listaevento) {
//            System.out.println("==================>"+op1.getIdevento()+"  "+op1.getIdactividad());
//            
//        }
//        Oposicion nueobjeto=new Oposicion();
//        
//        listaofi=datosss.obtnroopoxnroydmte(753L,"DMTE");
//        for (Oposicion opo1 : listaofi) {
//            System.out.println("============>"+opo1.getIdoposicion());            
//        }
//        
//        OpoEvento nuevoevent = new OpoEvento();
//        nuevoevent = varievento.obtenereventoxidpublica(datosss.encuentraclaveprin(357L, 1));
//
//        System.out.println("========>"+nuevoevent.getIdevento());
//        System.out.println("========>"+nuevoevent.getIdactividad());
//        System.out.println("========>"+nuevoevent.getIdoposicion());
//        System.out.println("========>"+nuevoevent.getOrden_o());
//  
//        OpoHistorial objhisto =new OpoHistorial();             
//        objhisto.setIdoposicion(20L);
//        objhisto.setIdlogtrans(1L);
//        objhisto.setEstado("PRESENTADO");
//        objhisto.setFecha_lim(Fecha);
//        objhisto.setObservacion("Esta siendo observada");
//        objhisto.setUbicacion("En EXFF");
//        objhisto.setApoderado("MARIEL");
//        objhisto.setOperacion("UPDATEE");
//        objhisto.setFecha_operacion(Fecha);
//        objhisto.setId_usuario(1L);
//        
//        varihisto.guardaOpoHistorial(objhisto);
//        
//        OpoFechalimite objfechalim=new OpoFechalimite();        
//        objfechalim.setIdevento(1L);
//        objfechalim.setIdactividadplazo(1L);
//        objfechalim.setIdoposicion(20L);
//        objfechalim.setIdlogtramite(1L);
//        objfechalim.setOrden(2);
//        objfechalim.setFechalimite(Fecha);
//        objfechalim.setOrden_o(5);
//        
//        varifechalim.guardaOpoFechalimite(objfechalim);
//        
//        OpoEvento objevento=new OpoEvento();
//        objevento.setIdactividad(2L);
//        objevento.setIdoposicion(21L);
//        objevento.setIdlogtrans(1L);
//        objevento.setFecha(Fecha);
//        objevento.setObservacion("Esta es la segunda observacion");
//        objevento.setUsuario(4L);
//        objevento.setOrden_o(50);
//        objevento.setFecha_operacion(Fecha);
//        
//        varievento.guardaOpoEvento(objevento);
//        
//        vardmte.eliminaOposicion(3L);
//        OpoMarcademandadaService opomarcadmdasrv = (OpoMarcademandadaService) context.getBean("opoMarcaDemandanteService");
//        OpoMarcademandanteService opomarcadmtesrv = (OpoMarcademandanteService) context.getBean("opomarcademandanteservice");
        //opomarcadmdasrv.eliminarOpomarcademandada(3L, 3L);
        //  opomarcadmtesrv.eliminarOpomarcademandante(2L,2L);
        //  posolapo1.eliminarOposolicitanteapoderadoxdmda(8L,2L);
        //  posolapo1.eliminarOposolicitanteapoderadoxdmte(27L, 8L);
        //  oponotificasrv.eliminarOponotificacionxdmda(31L,6L);
        // oponotificasrv.eliminarOponotificacionxdmte(8L, 5L);
        // oponotificasrv.eliminarOponotificacionxdmda(29L,4L);
        //System.out.println("==================>"+oponotificasrv.eliminarOponotificacionxdmda(30L,5L));
        //    oponotificasrv.eliminarOponotificacionxdmte(10L, 6L);
        //   System.out.println("================>"+oponotificasrv.eliminarOponotificacionxdmda(7L, 5L));
        //    System.out.println("================>"+oponotificasrv.eliminarOponotificacionxdmte(10L, 6L));
//        List<OpoMarcademandante> listmarcadmtebn = new ArrayList<OpoMarcademandante>();
//        OpoMarcademandanteService marcademtesrv = (OpoMarcademandanteService) context.getBean("opomarcademandanteservice");
//
//        listmarcadmtebn = marcademtesrv.obtenerListadoOpomarcademandantexnroopo(35L);
//
//        for (OpoMarcademandante opok : listmarcadmtebn) {
//
//            System.out.println("==Id Marca :=="+opok.getIdmarcademandante());
//            System.out.println("==Marca=="+opok.getDmte_marca_lnv());
//            System.out.println("==Pais="+opok.getDmte_pais());
//            System.out.println("==Id Oposicion="+opok.getIdoposicion());
//            System.out.println("==Nro Orden="+opok.getOrden_opo());
//            System.out.println("***************************************************************************");
//        }
        /**
         * ***************************
         */
//        OposicionService vardmte = (OposicionService) context.getBean("oposicionService ");
//                
//        String marca=vardmte.obtenernombremarcaxidopo(2L);
//        
//        System.out.println("Marca= "+marca);
//       OpoMarcademandada marcago=new OpoMarcademandada();
//        OpoMarcademandadaService vardmte1 = (OpoMarcademandadaService) context.getBean("opoMarcaDemandanteService");
//                
//         marcago=vardmte1.obtenerOpomarcademandadaobj(1L);
//        
//        System.out.println("Marca= "+marcago.getDmdo_marca_lnv());
//        Date Fecha = new Date();
//        OposicionService oposol = (OposicionService) context.getBean("oposicionService ");
//        OpoMarcademandadaService oposicionser = (OpoMarcademandadaService) context.getBean("opoMarcaDemandanteService");
//        OpoMarcademandanteService podmte = (OpoMarcademandanteService) context.getBean("opomarcademandanteservice");
//        OpoNotificacionService notiser=(OpoNotificacionService) context.getBean("opoNotificacionService");  
//        OpoSolicitanteaopderadoService posolapo=(OpoSolicitanteaopderadoService) context.getBean("opoSolicitanteApoderadoService");
//        OpoSolicitanteapoderado oposol1=new OpoSolicitanteapoderado();
//        oposol1.setIdsolicitanteapoderado(17L);
//         oposol1.setIdmarcademandada(null);
//         oposol1.setIdmarcademandante(4L);
//         oposol1.setNombre_razonsocial("La Orquidea");
//         oposol1.setPrimer_apellido("perez");
//         oposol1.setSegundo_apellido("Aliaga");
//         oposol1.setNumero_documento("434");
//         oposol1.setTipo_documento("CI");
//         oposol1.setLugar_expedicion("LPZ");
//         oposol1.setPais("Peru");
//         oposol1.setGenero("FEM");
//         oposol1.setSolicitud_depa("oruro");
//         oposol1.setPoder("34");
//         oposol1.setDireccion("Calle buena Calidad");
//         oposol1.setTelefono("34543324");
//         oposol1.setCelular("7654323");
//         oposol1.setEmail("luisangelql10@gmail.com");
//         oposol1.setFax("456");
//         oposol1.setTiposoliapo("DMDO");
//         oposol1.setTipo_titular("Apod");
//         oposol1.setNropoder("345");
//         oposol1.setTipo_persona("juridico");         
//         oposol1.setEstado("AC");
//        
//        posolapo.modificarOposolicitanteapoderado(oposol1);
        //notiser.obtenreidnotificacionxmarcadmda(2L);
        //          System.out.println("====================>"+notiser.obtenreidnotificacionxmarcadmte(4L));
//        OpoNotificacion objnoti=new OpoNotificacion();
//        objnoti.setIdnotificacion(1L);
//        objnoti.setIdmarcademandada(1L);
//      //  objnoti.setIdmarcademandante(2L);
//        objnoti.setCiudad_notificacion("Cochabamba");
//        objnoti.setZona_barrio("La Merced");
//        objnoti.setAvenida_calle("Gregorian");
//        objnoti.setNumero("12");
//        objnoti.setEdificio("Las Dos Torres");
//        objnoti.setPiso("2");
//        objnoti.setNumero_departamento("15");
//        objnoti.setReferencia_direccion("Las esquina de la libertad");
//        objnoti.setEmail("luisangelql@gmail.com");
//        objnoti.setTelefono("7456466");
//        objnoti.setCelular("71978309");
//        objnoti.setEstado("AC");
//         notiser.modificarOponotificacionmodi(objnoti);
        //System.out.println("**********--" + podmte.obtenerOpomarcademandnteXidopo(14L));
//        OpoMarcademandante datosmarcadante = new OpoMarcademandante();
//        datosmarcadante.setIdmarcademandante(2L);
//        datosmarcadante.setIdoposicion(2L);
//        datosmarcadante.setDmte_reg(14);
//        datosmarcadante.setDmte_marca_lnv("PaitoSman");
//        datosmarcadante.setDmte_uso("bart");
//        podmte.modificarOpomarcademandnte(datosmarcadante);
//        OpoMarcademandada datosmarcadem = new OpoMarcademandada();
//        datosmarcadem.setIdmarcademandada(14L);
//        datosmarcadem.setIdoposicion(15L);
//        datosmarcadem.setFecha_public(Fecha);
//        datosmarcadem.setVerif(TRUE);
//        oposicionser.modificarOpomarcademandada(datosmarcadem);
        //       System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°>"+oposicionser.obtenerOpomarcademandadaXidopo(13L));
  //        System.out.println("============>"+vardmte.encuentraclaveprin(194503L, 1));
//       Oposicion datosoposicion=new Oposicion();
//         datosoposicion.setIdoposicion(1L);
//            datosoposicion.setNro_opo(335L); 
//            datosoposicion.setFecha_presentacion(Fecha); 
//            datosoposicion.setGaceta(45);
//            datosoposicion.setObservacion("Esta es la observacion de oposicion");
//            datosoposicion.setOrden_o(null);
//            oposol.modificaOposicion(datosoposicion);
//OpoSolicitanteapoderado datosaux=new OpoSolicitanteapoderado();
//datosaux.setIdsolicitanteapoderado(5L);
//datosaux.setPoder("4");
//        OposicionService oposerv = (OposicionService) context.getBean("oposicionService ");
//
//        OpoMarcademandadaService opomarcadmdasrv = (OpoMarcademandadaService) context.getBean("opoMarcaDemandanteService");
//        OpoMarcademandanteService opomarcadmtesrv = (OpoMarcademandanteService) context.getBean("opomarcademandanteservice");
//        OpoNotificacionService oponotificasrv = (OpoNotificacionService) context.getBean("opoNotificacionService");
//        OpoSolicitanteaopderadoService oposoliaposrv = (OpoSolicitanteaopderadoService) context.getBean("opoSolicitanteApoderadoService");
//
//        List<Oposicion> listaopo = new ArrayList<Oposicion>();
//        List<OpoMarcademandada> listmarcadmda = new ArrayList<OpoMarcademandada>();
//        List<OpoMarcademandante> listmarcadmte = new ArrayList<OpoMarcademandante>();
//        List<OpoNotificacion> listnotidmda = new ArrayList<OpoNotificacion>();
//        List<OpoNotificacion> listnotidmte = new ArrayList<OpoNotificacion>();
//        List<OpoSolicitanteapoderado> listoposoliapodmda = new ArrayList<OpoSolicitanteapoderado>();
//        List<OpoSolicitanteapoderado> listoposoliapodmte = new ArrayList<OpoSolicitanteapoderado>();
//
//        listaopo = oposerv.obtenerListadoOposicion(35L);
//        System.out.println("tam::::::::::" + listaopo.size());
//
//        for (Oposicion opok : listaopo) {
//
//            listmarcadmda = opomarcadmdasrv.obtenerListadoOpomarcademandada(opok.getIdoposicion());
//            System.out.println("Tamaño lista demandada" + listmarcadmda.size() + "=========== ide demandada>" + opok.getIdoposicion());
//
//            for (OpoMarcademandada opoMarcadmda : listmarcadmda) {
//
//                listnotidmda = oponotificasrv.obtenerListadoNotificaciondmda(opoMarcadmda.getIdmarcademandada());
//                System.out.println("Tamaño lista noti" + listnotidmda.size() + "===========>" + opoMarcadmda.getIdoposicion());
//
//                for (OpoNotificacion oponotidmda : listnotidmda) {
//
//                    System.out.println("Numero Notificacion" + "===========>" + oponotidmda.getIdnotificacion());
//                }
//
//                listoposoliapodmda = oposoliaposrv.obtenerListadoSoliApodmda(opoMarcadmda.getIdmarcademandada());
//                System.out.println("Tamaño lista soli apo" + listoposoliapodmda.size() + "===========>" + opoMarcadmda.getIdoposicion());
//                for (OpoSolicitanteapoderado oposoliapodmda : listoposoliapodmda) {
//
//                    System.out.println("Numero Soli APo dmda" + "===========>" + oposoliapodmda.getIdmarcademandada());
//                }
//
//            }
//           
//
//            listmarcadmte = opomarcadmtesrv.obtenerListadoOpomarcademandante(opok.getIdoposicion());
//            System.out.println("Tamaño lista demandante" + listmarcadmte.size() + "=========== ide demandante>" + opok.getIdoposicion());
//
//            for (OpoMarcademandante opoMarcadmte : listmarcadmte) {
//
//                listnotidmte = oponotificasrv.obtenerListadoNotificaciondmte(opoMarcadmte.getIdmarcademandante());
//                System.out.println("Tamaño lista noti dmda" + listnotidmda.size() + "===========>" + opoMarcadmte.getIdoposicion());
//
//                for (OpoNotificacion oponotidmte : listnotidmte) {
//
//                    System.out.println("Numero Notificacion dmte" + "===========>" + oponotidmte.getIdnotificacion());
//                }
//
//                listoposoliapodmte = oposoliaposrv.obtenerListadoSoliApodmte(opoMarcadmte.getIdmarcademandante());
//                System.out.println("Tamaño" + listoposoliapodmda.size() + "===========>" + opoMarcadmte.getIdoposicion());
//                for (OpoSolicitanteapoderado oposoliapodmte : listoposoliapodmte) {
//
//                    System.out.println("Numero Soli aPo dmte" + "===========>" + oposoliapodmte.getIdmarcademandante());
//                }
//
//            }
// System.out.println(":::::::::::::::::::::::::::---------------------.::::::::::::::::::::::::::::::::::");
//        }
//
    }

}
