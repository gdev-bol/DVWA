/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import java.util.Date;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.Recibo;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.ReciboService;
import snp.gob.bo.gimodel.servicio.RenSolicitudRenovacionService;
import snp.gob.bo.gimodel.servicio.UsuarioService;

/**
 *
 * @author chanoRojas
 */
public class ReciboTest {

    public static void main(String[] args) throws Exception {
//      ApplicationContext context = new FileSystemXmlApplicationContext("//home//levi//Documentos//PROYECTOS//SISInterno//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
//        ApplicationContext context = new FileSystemXmlApplicationContext("//home//chano//sipi_v1.0//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        ApplicationContext context = new FileSystemXmlApplicationContext("//home//chano//sipi_v1.0//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
//        UsuarioPaginaService servicio = (UsuarioPaginaService) context.getBean("usuarioPaginaService");
//        RenRenovacionService renRenovacionService = (RenRenovacionService) context.getBean("renRenovacionService");
//        RenSolicitanteApoderadoService renSolicitanteApoderadoService = (RenSolicitanteApoderadoService) context.getBean("renSolicitanteApoderadoService");
        RenSolicitudRenovacionService renSolicitudRenovacionService = (RenSolicitudRenovacionService) context.getBean("renSolicitudRenovacionService");
        ReciboService reciboService = (ReciboService) context.getBean("reciboService");
        UsuarioService usuarioService = (UsuarioService) context.getBean("usuarioService");
        ComunService comunService = (ComunService) context.getBean("comunService");
//        RenSolicitudRenovacion renSolicitudRenovacion = new RenSolicitudRenovacion();
//        Long parametro = 1820L;
//        int parametro1 = 1;
        Recibo recibo = new Recibo();
//        recibo.setIdTasa(1L);
//        recibo.setIdLogTrans(1L);
//        recibo.setNumeroRecibo(45454L);
//        recibo.setIdTasa(1L);
//        recibo.setSerieRecibo("A-01");
//        recibo.setNumeroTramiteIngresado("SR26");
//        reciboService.crudRecibo(recibo, 1);
//        renSolicitudRenovacion=renSolicitudRenovacionService.buscaSolicitudRenovacionPorNumeroSrYGestion( parametro,parametro1);
//        System.out.println("rensolicitud"+renSolicitudRenovacion.getIdsolicitudrenovacion());
//        System.out.println("rensolicitud"+renSolicitudRenovacion.getSerie_recibo());
//        renSolicitudRenovacion.getIdsolicitudrenovacion();
//                    renSolicitudRenovacion.setIdsolicitudrenovacion(16L);
//                    renSolicitudRenovacion.setIdlogtrans(14L);
//                    renSolicitudRenovacion.setId_recibo_solicitud(1L);
//                    renSolicitudRenovacion.setId_recibo_titulo(1L);
//                    renSolicitudRenovacion.setSr(11111111L);
//                    renSolicitudRenovacion.setGestion_sr(2026);
//                    renSolicitudRenovacion.setFecha_ingreso(new Date());
//                    renSolicitudRenovacion.setEstado_renovacion("AC");
//                    renSolicitudRenovacion.setUbicacion();
//                    renSolicitudRenovacion.setNumero_ultima_renovacion();
//                    renSolicitudRenovacion.setSerie_ultima_renovacion();
//                    renSolicitudRenovacion.setNumero_penultima_renovacion();
//                    renSolicitudRenovacion.setSerie_penultima_renovacion();
//                    renSolicitudRenovacion.setOficina();
//                    renSolicitudRenovacion.setNumero_recibo();
//                    renSolicitudRenovacion.setSerie_recibo();
//                    renSolicitudRenovacion.setNumero_titulo();
//                    renSolicitudRenovacion.setSerie_titulo();
//                    renSolicitudRenovaat snp.gob.bo.gimodel.servicio.impl.ModModificacionServiceImpl.buscarModModificacion_NumeroFormulario(ModModificacionServiceImpl.java:420)cion.setClase_niza_reclasificacion();
//                    renSolicitudRenovacion.setLista_productos_limitacion();
//                    renSolicitudRenovacion.setSm();
//                    renSolicitudRenovacion.setSigno_registrado();
//                    renSolicitudRenovacion.setClase_niza_registrado();
//                    renSolicitudRenovacion.setTipo_genero();
//                    renSolicitudRenovacion.setTipo_signo();
//                    renSolicitudRenovacion.setNumero_registro_registrado();
//                    renSolicitudRenovacion.setSerie_registro_registrado();
//                    renSolicitudRenovacion.setFecha_registro_registrado();
//                    renSolicitudRenovacion.setEstado();
//                    Long numero=Long.valueOf("212121");
//                    System.out.println("dsadsadsa"+renSolicitudRenovacionService.buscarRenSolicitud_NumeroFormulario("78787878").getIdsolicitudrenovacion());
//        List<Recibo> lista = reciboService.listaReciboEmitidoPorFecha(new Date());
//        for (Recibo recibo1 : lista) {
//            System.out.println("recibo" + recibo.getNumeroRecibo());
//        }
//        System.out.println("ya tiene dos recibos derivar"+reciboService.validaRegistroDeRecibos(recibo));
//        System.out.println("recibo encontrado"+reciboService.buscaReciboPorIdRecibo(2L).getIdRecibo());
//        System.out.println("recibo id"+reciboService.buscaReciboPorNumeroYSerieRecibo(13,"B").getIdRecibo());
//        Recibo recibodfs= reciboService.buscaReciboPorIdRecibo(10L);
//        System.out.println("id recibo"+recibodfs.getConcepto());
//        recibodfs.setConcepto("SOLICITUD DE REGISTRO DE SIGNO DISTINTIVO (NACIONAL)");
//        reciboService.crudRecibo(recibodfs, 2);
        Date fecha = comunService.obtenerFechaHoraServidor(1L);
//        Date fecha =null;
        Usuario usuario = usuarioService.buscaUsuarioPorIdUsuario(82L);
        System.out.println("fecha" + fecha);
        List<Recibo> lista = reciboService.listaReciboEmitidoPorFechas(fecha, usuario);
        if (!lista.isEmpty()) {
            for (Recibo recibo1 : lista) {
                System.out.println("los recibos son " + recibo1.getIdRecibo());
            }
        } else {
            System.out.println("lista vacia");
        }                                                                                                                                                                                                                                                                                            
//       
//       Long numeroSR=12120000L;
//        System.out.println("numero "+numeroSR);
//       int iTamanioNumero=(numeroSR.toString()).length();
//        int iDesplazamiento = Double.valueOf(Math.pow(10, iTamanioNumero-1)).intValue();
//        int numeroSRInt=numeroSR.intValue();
//        System.out.println("el primer digito es"+(numeroSRInt/iDesplazamiento));

//       String oficina="LPZ";
//        String tipoTramiteDevolver="null";
//        switch (oficina) {
//            case "CBA":
//                tipoTramiteDevolver = EnumTipoTramiteRecibo.RENOVACION_COCHABAMBA.getCodigo();
//                break;
//            case "LPZ":
//                tipoTramiteDevolver = EnumTipoTramiteRecibo.RENOVACION.getCodigo();
//                break;
//            case "SCZ":
//                tipoTramiteDevolver = EnumTipoTramiteRecibo.RENOVACION_SANTA_CRUZ.getCodigo();
//                break;
//            case "ALT":
//                tipoTramiteDevolver = EnumTipoTramiteRecibo.RENOVACION_EL_ALTO.getCodigo();
//                break;
//        }
//        
//        System.out.println("tipoTRAMITEEEEEEEEEEEEeeee"+tipoTramiteDevolver);
//       
//       
//        
    }
}
