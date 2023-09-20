/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.bean.digitalizacion;

import java.io.FileOutputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.gimodel.bdimagen.entidad.ModArchivoDig;
import snp.gob.bo.gimodel.bdimagen.entidad.ModDocumento;
import snp.gob.bo.gimodel.bdimagen.entidad.RenArchivoDig;
import snp.gob.bo.gimodel.bdimagen.entidad.RenDocumento;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.bdimagen.entidad.SigArchivoDig;
import snp.gob.bo.gimodel.bdimagen.entidad.SigDocumento;
import snp.gob.bo.gimodel.bdimagen.servicio.ModArchivoDigService;
import snp.gob.bo.gimodel.bdimagen.servicio.ModDocumentoService;
import snp.gob.bo.gimodel.bdimagen.servicio.RenArchivoDigService;
import snp.gob.bo.gimodel.bdimagen.servicio.RenDocumentoService;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.enums.EnumPrefijoTablas;
import snp.gob.bo.gimodel.enums.EnumTipoTramite;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.bdimagen.servicio.SigArchivoDigService;
import snp.gob.bo.gimodel.bdimagen.servicio.SigDocumentoService;
import snp.gob.bo.gimodel.entidad.LogTrans;
import snp.gob.bo.gimodel.entidad.ModModificacion;
import snp.gob.bo.gimodel.entidad.RenSolicitudRenovacion;
import snp.gob.bo.gimodel.entidad.Seguimiento;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.servicio.LogTransService;
import snp.gob.bo.gimodel.servicio.ModModificacionService;
import snp.gob.bo.gimodel.servicio.RenSolicitudRenovacionService;
import snp.gob.bo.gimodel.servicio.SeguimientoService;
import snp.gob.bo.gimodel.servicio.SigSignoMarcaService;
import snp.gob.bo.gimodel.servicio.UsuarioService;

/**
 *
 * @author eddy
 */
@ManagedBean(name = "digitalizacionBean")
@ViewScoped
public class DigitalizacionBean extends AbstractManagedBean implements Serializable {

    private String busq = "SM";
    private Boolean valorSM = Boolean.TRUE;
    private Boolean valorNP = Boolean.FALSE;
    private Boolean valorNR = Boolean.FALSE;
    private Boolean activaPanelAdjuntarDocumentos = Boolean.FALSE;

    private String filtroArea = "SIG";
    private Boolean buscadorSignosRender = true;
    private Boolean buscadorModificacionesRender = false;
    private Boolean buscadorRenovacionesRender = false;
    private String filtroSolicitud = "SM";
    private Boolean buscadorSigSMRender = true;
    private Boolean buscadorSigPublicacionRender = false;
    private Boolean buscadorSigRegistroRender = false;
    public String extensionExpediente = "";
    private Long numeroSolicitud;
    private Integer gestionSolicitud;
    private Long registroSolicitud;
    private String serieRegistro = "C";
    private Long numeroPublicacion;
    private String valorTipoModificacion = "CANO";
    private Long smBuscar;
    private boolean pintaDocumentos;
    private UploadedFile file;
    byte[] fotoByte;
    byte[] fotoByteImprime;
    private String mimeImprime;
    private SigSignoMarca sigSignoMarca;
    private ModModificacion modModifica;
    private RenSolicitudRenovacion renSolicitudRenovacion;

    private Long numSolRen;
    private Integer gesSolRen;
    /**
     * *Datos para guardar en la base de datos***
     */
    private String nombreArchivo;
    private String descripcion;
    private Integer nro_folios;
    private String tipo_documento;
    private String tipo_archivo;
    private Date fecha_creacion;
    private Date fechaRegistro;
    private String estado;
    private String mime;
    private String nombreArchivosubir;

    private SigDocumento sigDocuAGuarda;
    private ModDocumento modDocuAGuarda;
    private RenDocumento renDocuAGuarda;
    private LogTrans logTransGuardado;
    private List<SigDocumento> listaDocumenMostrar = new ArrayList<SigDocumento>();
    private List<ModDocumento> listaDocuModiMostrar = new ArrayList<ModDocumento>();
    private List<RenDocumento> listaDocuRenMostrar = new ArrayList<RenDocumento>();
    private List<DigitalizaDatosBean> listaDatosMostrar = new ArrayList<DigitalizaDatosBean>();
    private String template = "./../WEB-INF/facelets/templates/Template.xhtml";

    private List<Dominio> listaTipoNotifi = new ArrayList<Dominio>();
    @ManagedProperty(value = "#{comunService}")
    private ComunService comunService;
    @ManagedProperty(value = "#{sigSignoMarcaService}")
    private SigSignoMarcaService sigSignoMarcaService;
    @ManagedProperty(value = "#{sigDocumentoService}")
    private SigDocumentoService sigDocumentoService;
    @ManagedProperty(value = "#{sigArchivoDigService}")
    private SigArchivoDigService sigArchivoDigService;
    @ManagedProperty(value = "#{dominioService}")
    private DominioService dominioService;
    @ManagedProperty(value = "#{modModificacionService}")
    private ModModificacionService modModificacionService;

    @ManagedProperty(value = "#{modDocumentoService}")
    private ModDocumentoService modDocumentoService;
    @ManagedProperty(value = "#{modArchivoDigService}")
    private ModArchivoDigService modArchivoDigService;

    @ManagedProperty(value = "#{renSolicitudRenovacionService}")
    private RenSolicitudRenovacionService renSolicitudRenovacionService;

    @ManagedProperty(value = "#{renDocumentoService}")
    private RenDocumentoService renDocumentoService;
    @ManagedProperty(value = "#{renArchivoDigService}")
    private RenArchivoDigService renArchivoDigService;
    @ManagedProperty(value = "#{seguimientoService}")
    private SeguimientoService seguimientoService;
    @ManagedProperty(value = "#{logTransService}")
    private LogTransService logTransService;
    @ManagedProperty(value = "#{usuarioService}")
    private UsuarioService usuarioService;

    @PostConstruct
    public void initDigitalizacionBean() {
        try {
            Map<String, String[]> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterValuesMap();
            if (params.get("datosEnviados") != null) {
                template = "./../WEB-INF/facelets/templates/DialogTemplate.xhtml";
                if (params.get("datosEnviados")[0] != null) {
                    //areaRegistro = params.get("datosEnviados")[1];
                    // filtroArea = areaRegistro;
                    // filtroAreaDisabled = true;
                    //if (params.get("datosEnviados")[2] != null) {
                        //id = Long.parseLong(params.get("datosEnviados")[2]);
                        //solicitud = params.get("datosEnviados")[3];
                        //for (Seguimiento item : listaSeguimiento) {
                        //platoRealTotal = platoRealTotal + item.getPlazoReal();
                        //}
                    //}                    
                }
            } else {
                template = "./../WEB-INF/facelets/templates/Template.xhtml";
            }
            pintaDocumentos = false;
            listaTipoNotifi = dominioService.obtenerListadoDominio("tipo_documento_dig");
            fechaRegistro = comunService.obtenerFechaHoraServidor(1L);
            List<Usuario> listUsuario = usuarioService.listaUsuarioXidPagina(super.getIdUsuarioSession());
            logTransGuardado = logTransService.crudLogTrans(new LogTrans(super.getIdUsuarioSession(), fechaRegistro), 1);

        } catch (Exception ex) {
            Logger.getLogger(DigitalizacionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void activaCambios() {
        listaDatosMostrar.clear();
        switch (filtroArea) {
            case "SIG":
                buscadorSignosRender = true;
                buscadorModificacionesRender = false;
                buscadorRenovacionesRender = false;
                filtroSolicitud = "SMfiltroSoli";
                break;
            case "MOD":
                buscadorSignosRender = false;
                buscadorModificacionesRender = true;
                buscadorRenovacionesRender = false;
                filtroSolicitud = "MO";

                break;
            case "REN":
                buscadorSignosRender = false;
                buscadorModificacionesRender = false;
                buscadorRenovacionesRender = true;
                filtroSolicitud = "SR";

                break;
            default:
                buscadorSignosRender = false;
                buscadorModificacionesRender = false;
                buscadorRenovacionesRender = false;
                break;
        }
        activaCambiosBuscadorSolicitud();
    }

    public void activaCambiosBuscadorSolicitud() {

        listaDocumenMostrar.clear();
        listaDatosMostrar.clear();
        nro_folios = null;
        descripcion = null;
        switch (filtroSolicitud) {
            case "SM":
                buscadorSigSMRender = true;
                buscadorSigPublicacionRender = false;
                buscadorSigRegistroRender = false;
                break;
            case "MO":
                buscadorSigSMRender = true;
                buscadorSigPublicacionRender = false;
                buscadorSigRegistroRender = false;
                break;
            case "SR":
                buscadorSigSMRender = true;
                buscadorSigPublicacionRender = false;
                buscadorSigRegistroRender = false;
                break;
            case "PUB":
                buscadorSigSMRender = false;
                buscadorSigPublicacionRender = true;
                buscadorSigRegistroRender = false;
                break;
            case "REG":
                buscadorSigSMRender = false;
                buscadorSigPublicacionRender = false;
                buscadorSigRegistroRender = true;
                break;
            default:
                buscadorSigSMRender = false;
                buscadorSigPublicacionRender = false;
                buscadorSigRegistroRender = false;
                break;
        }
    }

    public void buscaTramiteSigno() throws Exception {
        listaDatosMostrar.clear();
        nro_folios = null;
        descripcion = null;

        //   System.out.println("numeroSolicitud:"+numeroSolicitud);
        if (filtroSolicitud.equals("SM")) {
            smBuscar = comunService.codificarCodigoSM(numeroSolicitud.toString(), gestionSolicitud.toString(), extensionExpediente);
            // System.out.println("SM codificacdo ::"+smBuscar);
            sigSignoMarca = sigSignoMarcaService.listaSigSignoMarcaXSM(smBuscar);
            //  System.out.println("sigSignoMarca::"+sigSignoMarca);
        }
        if (filtroSolicitud.equals("REG")) {
            sigSignoMarca = sigSignoMarcaService.listaSigSignoMarcaXRegistro(registroSolicitud, serieRegistro, " ");

        }
        if (filtroSolicitud.equals("PUB")) {
            sigSignoMarca = sigSignoMarcaService.listaSigSignoMarcaXPublicacion(numeroPublicacion);
        }
        if (sigSignoMarca != null) {

            pintaDocumentos = true;
         
            listaDocumenMostrar = sigDocumentoService.listaXidSignoMarca(sigSignoMarca.getIdSignoMarca());
            //System.out.println("listaDocumenMostrar tam::"+listaDocumenMostrar.size());
            for (int i = 0; i <= listaDocumenMostrar.size() - 1; i++) { //System.out.println("listaDocumenMostrar.get(i).getIddocumento()::"+listaDocumenMostrar.get(i).getIddocumento());
                DigitalizaDatosBean dato = new DigitalizaDatosBean();
                dato.setIddocumento(listaDocumenMostrar.get(i).getIddocumento());
                dato.setIdservicio(listaDocumenMostrar.get(i).getIdsignomarca());
                dato.setNombre_archivo(listaDocumenMostrar.get(i).getNombre_archivo());
                dato.setExtension_archivo(listaDocumenMostrar.get(i).getExtension_archivo());
                dato.setDescripcion(listaDocumenMostrar.get(i).getDescripcion());
                dato.setNro_folios(listaDocumenMostrar.get(i).getNro_folios());
                dato.setMime(listaDocumenMostrar.get(i).getMime());
                dato.setTipo_documento(listaDocumenMostrar.get(i).getTipo_documento());
                listaDatosMostrar.add(dato);
            }

        } else {
            pintaDocumentos = false;
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No Existe el Tramite", "");
            FacesContext.getCurrentInstance().addMessage(null, message);

        }
    }

    public void buscaTramiteModi() throws Exception {
        listaDatosMostrar.clear();
        modModifica = modModificacionService.buscarModModificacionXtipo(valorTipoModificacion, numeroSolicitud, gestionSolicitud);

        if (modModifica != null) {

            pintaDocumentos = true;

            listaDocuModiMostrar = modDocumentoService.listaXidModificacion(modModifica.getIdmodificacion());
            // System.out.println("tam listaDocuModiMostrar::"+listaDocuModiMostrar.size());
            for (int i = 0; i <= listaDocuModiMostrar.size() - 1; i++) {
                //listaDocumenMostrar.
                DigitalizaDatosBean dato = new DigitalizaDatosBean();
                dato.setIddocumento(listaDocuModiMostrar.get(i).getIddocumento());
                dato.setIdservicio(listaDocuModiMostrar.get(i).getIdmodificacion());
                dato.setNombre_archivo(listaDocuModiMostrar.get(i).getNombre_archivo());
                dato.setExtension_archivo(listaDocuModiMostrar.get(i).getExtension_archivo());
                dato.setDescripcion(listaDocuModiMostrar.get(i).getDescripcion());
                dato.setNro_folios(listaDocuModiMostrar.get(i).getNro_folios());
                dato.setMime(listaDocuModiMostrar.get(i).getMime());
                dato.setTipo_documento(listaDocuModiMostrar.get(i).getTipo_documento());
                listaDatosMostrar.add(dato);

            }
        } else {
            pintaDocumentos = false;
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "No Existe el Tramite en Base de Datos", "");
            FacesContext.getCurrentInstance().addMessage(null, message);

        }

    }

    public void buscaTramiteRen() throws Exception {
        listaDatosMostrar.clear();
        renSolicitudRenovacion = renSolicitudRenovacionService.buscaSolicitudRenovacionPorNumeroSrYGestion(this.numSolRen, this.gesSolRen);
        //  System.out.println("getIdsolicitudrenovacion es"+renSolicitudRenovacion.getIdsolicitudrenovacion());
        //  System.out.println("estado::"+renSolicitudRenovacion.getEstado());
        //  System.out.println("gestionsr::"+renSolicitudRenovacion.getGestion_sr());
        if (renSolicitudRenovacion.getIdsolicitudrenovacion() != null) {
            listaDocuRenMostrar = renDocumentoService.listaXidsolicitudrenovacion(renSolicitudRenovacion.getIdsolicitudrenovacion());
            pintaDocumentos = true;

            for (int i = 0; i <= listaDocuRenMostrar.size() - 1; i++) {
                //listaDocumenMostrar.
                DigitalizaDatosBean dato = new DigitalizaDatosBean();
                dato.setIddocumento(listaDocuRenMostrar.get(i).getIddocumento());
                dato.setIdservicio(listaDocuRenMostrar.get(i).getIdsolicitudrenovacion());
                dato.setNombre_archivo(listaDocuRenMostrar.get(i).getNombre_archivo());
                dato.setExtension_archivo(listaDocuRenMostrar.get(i).getExtension_archivo());
                dato.setDescripcion(listaDocuRenMostrar.get(i).getDescripcion());
                dato.setNro_folios(listaDocuRenMostrar.get(i).getNro_folios());
                dato.setMime(listaDocuRenMostrar.get(i).getMime());
                dato.setTipo_documento(listaDocuRenMostrar.get(i).getTipo_documento());
                listaDatosMostrar.add(dato);
            }

        } else {
            pintaDocumentos = false;
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "No Existe el Tramite en Base de Datos", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void accionCargarImagenProyecto(FileUploadEvent event) {
        //System.out.println(" el nombre archivo es: " + event.getFile().getFileName() + "tamaño *** " + event.getFile().getSize());
        // System.out.println("contect type::"+event.getFile().getContentType());
        String[] nomArchi = event.getFile().getFileName().split("\\.");
        //System.out.println("nomArchi"+nomArchi.length);
        if (nomArchi.length != 0) {
            tipo_archivo = nomArchi[1];
        } else {
            tipo_archivo = null;
        }

        fotoByte = event.getFile().getContents();
        nombreArchivo = event.getFile().getFileName();
        mime = event.getFile().getContentType();
        nombreArchivosubir = event.getFile().getFileName();

    }

    public void adicionarArchivo() throws Exception {
        //System.out.println("Entra adiconar");

        if (filtroArea.equals("SIG")) {
            SigDocumento sigdocumento = new SigDocumento();
            sigdocumento.setIdsignomarca(sigSignoMarca.getIdSignoMarca());
            sigdocumento.setNombre_archivo(nombreArchivo);
            System.out.println("descripcion::" + this.descripcion);
            sigdocumento.setDescripcion(this.descripcion);
            System.out.println("descripcion" + sigdocumento.getDescripcion());
            sigdocumento.setNro_folios(nro_folios);
            sigdocumento.setTipo_documento(tipo_documento);
            sigdocumento.setExtension_archivo(tipo_archivo);
            sigdocumento.setFecha_creacion(new Date());
            sigdocumento.setMime(mime);
            sigdocumento.setEstado("AC");

            sigDocuAGuarda = sigDocumentoService.crudSigDocumento(sigdocumento, 1);

            SigArchivoDig sigArchivoDig = new SigArchivoDig();
            sigArchivoDig.setIddocumento(sigDocuAGuarda.getIddocumento());
            sigArchivoDig.setArchivo(fotoByte);

            sigArchivoDig = sigArchivoDigService.crudSigArchivoDig(sigArchivoDig, 1);
            nombreArchivosubir = "";
            buscaTramiteSigno();

//            Seguimiento sigSeguimiento = new Seguimiento();
//            sigSeguimiento = seguimientoService.registraSeguimientoSignos(
//                    sigSignoMarca.getIdSignoMarca(),//sigSignoMarca.getIdSignoMarca(),
//                    super.getIdUsuarioSession(),
//                    logTransGuardado.getIdLogTrans(),////
//                    sigSignoMarca.getSm(),
//                    "DIG",
//                    new Timestamp(fechaRegistro.getTime()),
//                    new Timestamp(fechaRegistro.getTime()),
//                    "",
//                    "AC",1
//            );

        }
        if (filtroArea.equals("MOD")) {
            ModDocumento moddocumento = new ModDocumento();
            moddocumento.setIdmodificacion(modModifica.getIdmodificacion());
            moddocumento.setNombre_archivo(nombreArchivo);
            //System.out.println("descripcion::" + this.descripcion);
            moddocumento.setDescripcion(this.descripcion);
            //System.out.println("descripcion" + sigdocumento.getDescripcion());
            moddocumento.setNro_folios(nro_folios);
            moddocumento.setTipo_documento(tipo_documento);
            moddocumento.setExtension_archivo(tipo_archivo);
            moddocumento.setFecha_creacion(new Date());
            moddocumento.setMime(mime);
            moddocumento.setEstado("AC");

            modDocuAGuarda = modDocumentoService.crudModDocumento(moddocumento, 1);
            ModArchivoDig modArchivoDig = new ModArchivoDig();
            modArchivoDig.setIddocumento(modDocuAGuarda.getIddocumento());
            modArchivoDig.setArchivo(fotoByte);

            modArchivoDigService.crudModArchivoDig(modArchivoDig, 1);
            nombreArchivosubir = "";
            buscaTramiteModi();
            /*Guardamos seguimiento de modificacion*/

            Seguimiento modSeguimiento = new Seguimiento();
            modSeguimiento.setId(modModifica.getIdmodificacion());
            modSeguimiento.setIdUsuario(super.getIdUsuarioSession());
            modSeguimiento.setIdLogTrans(logTransGuardado.getIdLogTrans());
            modSeguimiento.setSm(modModifica.getSm());
            modSeguimiento.setEtapa("DIG");
            modSeguimiento.setFechaRecepcion(new Timestamp(fechaRegistro.getTime()));
            modSeguimiento.setFechaFin(new Timestamp(fechaRegistro.getTime()));
            modSeguimiento.setEditable(Boolean.FALSE);
            modSeguimiento.setEstado("AC");
            Seguimiento modSeguimientoIngresado = new Seguimiento();
            modSeguimientoIngresado = seguimientoService.guardar_modificar_listar_Seguimiento(modSeguimiento, "MOD", 1);

        }
        if (filtroArea.equals("REN")) {
            RenDocumento rendocumento = new RenDocumento();
           
            rendocumento.setIdsolicitudrenovacion(renSolicitudRenovacion.getIdsolicitudrenovacion());
            rendocumento.setNombre_archivo(nombreArchivo);
            rendocumento.setDescripcion(descripcion);
            rendocumento.setNro_folios(nro_folios);
            rendocumento.setTipo_documento(tipo_documento);
            rendocumento.setExtension_archivo(tipo_archivo);
            rendocumento.setFecha_creacion(new Date());
            rendocumento.setMime(mime);
            rendocumento.setEstado("AC");
            renDocuAGuarda = renDocumentoService.crudRenDocumento(rendocumento, 1);
            RenArchivoDig renArchivoDig = new RenArchivoDig();
            renArchivoDig.setIddocumento(renDocuAGuarda.getIddocumento());
            renArchivoDig.setArchivo(fotoByte);

            renArchivoDigService.crudRenArchivoDig(renArchivoDig, 1);
            nombreArchivosubir = "";
            buscaTramiteRen();
            /*Guardamos seguimiento de modificacion*/
            Seguimiento renSeguimiento = new Seguimiento();
            renSeguimiento.setId(renSolicitudRenovacion.getIdsolicitudrenovacion());
            renSeguimiento.setIdUsuario(super.getIdUsuarioSession());
            renSeguimiento.setIdLogTrans(logTransGuardado.getIdLogTrans());
            RenSolicitudRenovacion ren = renSolicitudRenovacionService.buscaSolicitudRenovacionPorNumeroSrYGestion(renSolicitudRenovacion.getSr(),
                    renSolicitudRenovacion.getGestion_sr());
            renSeguimiento.setSm(ren.getSm());

            renSeguimiento.setEtapa("DIG");
            renSeguimiento.setFechaRecepcion(new Timestamp(fechaRegistro.getTime()));
            renSeguimiento.setFechaFin(new Timestamp(fechaRegistro.getTime()));
            renSeguimiento.setEditable(Boolean.FALSE);
            renSeguimiento.setEstado("AC");
            Seguimiento modSeguimientoIngresado = new Seguimiento();
            modSeguimientoIngresado = seguimientoService.guardar_modificar_listar_Seguimiento(renSeguimiento, "REN", 1);
        }

        nro_folios = null;
        descripcion = null;
        System.out.println("entra3");

    }

    public void bajaArchivo(DigitalizaDatosBean tramite) throws Exception {
        if (filtroArea.equals("SIG")) {
            // System.out.println("Entra al botono de vbajar, iddocumento:"+documento.getIddocumento());
            List<SigArchivoDig> listSigArch = sigArchivoDigService.listaSigArchivoXiddoc(tramite.getIddocumento());
            // System.out.println("tam de el el byte::"+listSigArch.get(0).getArchivo().length);
            fotoByteImprime = listSigArch.get(0).getArchivo();
            mimeImprime = tramite.getMime();

        }
        if (filtroArea.equals("MOD")) {
            List<ModArchivoDig> lisModArch = modArchivoDigService.listaModArchivoXiddoc(tramite.getIddocumento());
            fotoByteImprime = lisModArch.get(0).getArchivo();
            mimeImprime = tramite.getMime();

        }
        if (filtroArea.equals("REN")) {
            List<RenArchivoDig> lisRenArch = renArchivoDigService.listaRenArchivoXiddoc(tramite.getIddocumento());
            fotoByteImprime = lisRenArch.get(0).getArchivo();
            mimeImprime = tramite.getMime();

        }
        //String fileName = "Cifrado Impro.txt"; //The file that will be saved on your computer
        String fileName = tramite.getNombre_archivo();
        HttpServletResponse resp = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        // resp.setContentType("text/plain");
        resp.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        // resp.getOutputStream().write(fotoByteImprime);

        resp.setContentType(mimeImprime);
        resp.setContentLength(fotoByteImprime.length);

        ServletOutputStream outStream = resp.getOutputStream();
        outStream.write(fotoByteImprime, 0, fotoByteImprime.length);
        outStream.flush();
        outStream.close();
        FacesContext.getCurrentInstance().responseComplete();

    }

    public void eliminaArchivo(DigitalizaDatosBean tramite) throws Exception {

        if (filtroArea.equals("SIG")) {
            SigArchivoDig signulo = new SigArchivoDig();
            signulo.setIddocumento(tramite.getIddocumento());
            sigArchivoDigService.crudSigArchivoDig(signulo, 3);

            SigDocumento sigdocunulo = new SigDocumento();
            sigdocunulo.setIddocumento(tramite.getIddocumento());

            sigDocumentoService.crudSigDocumento(sigdocunulo, 3);

            buscaTramiteSigno();
        }
        if (filtroArea.equals("MOD")) {
            ModArchivoDig modnulo = new ModArchivoDig();
            modnulo.setIddocumento(tramite.getIddocumento());
            modArchivoDigService.crudModArchivoDig(modnulo, 3);

            ModDocumento moddocunulo = new ModDocumento();
            moddocunulo.setIddocumento(tramite.getIddocumento());

            modDocumentoService.crudModDocumento(moddocunulo, 3);

            this.buscaTramiteModi();
        }
        if (filtroArea.equals("REN")) {
            RenArchivoDig rennulo = new RenArchivoDig();
            rennulo.setIddocumento(tramite.getIddocumento());
            renArchivoDigService.crudRenArchivoDig(rennulo, 3);

            RenDocumento rendocunulo = new RenDocumento();
            rendocunulo.setIddocumento(tramite.getIddocumento());

            renDocumentoService.crudRenDocumento(rendocunulo, 3);
            this.buscaTramiteRen();
        }
    }

    public String finalizarDigitalizacion() {
        return "abrirPrincipal";
    }

    public void activaCambios3() {

        switch (busq) {
            case "SM":
                this.valorSM = Boolean.TRUE;
                this.valorNP = Boolean.FALSE;
                this.valorNR = Boolean.FALSE;
                break;
            case "NP":
                this.valorSM = Boolean.FALSE;
                this.valorNP = Boolean.TRUE;
                this.valorNR = Boolean.FALSE;
                break;
            case "NR":
                this.valorSM = Boolean.FALSE;
                this.valorNP = Boolean.FALSE;
                this.valorNR = Boolean.TRUE;
                break;
            default:
                this.valorSM = Boolean.FALSE;
                this.valorNP = Boolean.FALSE;
                this.valorNR = Boolean.FALSE;
                break;

        }
    }

    public RenDocumento getRenDocuAGuarda() {
        return renDocuAGuarda;
    }

    public void setRenDocuAGuarda(RenDocumento renDocuAGuarda) {
        this.renDocuAGuarda = renDocuAGuarda;
    }

    public List<RenDocumento> getListaDocuRenMostrar() {
        return listaDocuRenMostrar;
    }

    public void setListaDocuRenMostrar(List<RenDocumento> listaDocuRenMostrar) {
        this.listaDocuRenMostrar = listaDocuRenMostrar;
    }

    public RenDocumentoService getRenDocumentoService() {
        return renDocumentoService;
    }

    public void setRenDocumentoService(RenDocumentoService renDocumentoService) {
        this.renDocumentoService = renDocumentoService;
    }

    public RenArchivoDigService getRenArchivoDigService() {
        return renArchivoDigService;
    }

    public void setRenArchivoDigService(RenArchivoDigService renArchivoDigService) {
        this.renArchivoDigService = renArchivoDigService;
    }

    public List<ModDocumento> getListaDocuModiMostrar() {
        return listaDocuModiMostrar;
    }

    public void setListaDocuModiMostrar(List<ModDocumento> listaDocuModiMostrar) {
        this.listaDocuModiMostrar = listaDocuModiMostrar;
    }

    public ModDocumentoService getModDocumentoService() {
        return modDocumentoService;
    }

    public void setModDocumentoService(ModDocumentoService modDocumentoService) {
        this.modDocumentoService = modDocumentoService;
    }

    public List<DigitalizaDatosBean> getListaDatosMostrar() {
        return listaDatosMostrar;
    }

    public void setListaDatosMostrar(List<DigitalizaDatosBean> listaDatosMostrar) {
        this.listaDatosMostrar = listaDatosMostrar;
    }

    public ModModificacion getModModifica() {
        return modModifica;
    }

    public void setModModifica(ModModificacion modModifica) {
        this.modModifica = modModifica;
    }

    public ModDocumento getModDocuAGuarda() {
        return modDocuAGuarda;
    }

    public void setModDocuAGuarda(ModDocumento modDocuAGuarda) {
        this.modDocuAGuarda = modDocuAGuarda;
    }

    public ModArchivoDigService getModArchivoDigService() {
        return modArchivoDigService;
    }

    public void setModArchivoDigService(ModArchivoDigService modArchivoDigService) {
        this.modArchivoDigService = modArchivoDigService;
    }

    public String getMimeImprime() {
        return mimeImprime;
    }

    public void setMimeImprime(String mimeImprime) {
        this.mimeImprime = mimeImprime;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public String getNombreArchivosubir() {
        return nombreArchivosubir;
    }

    public void setNombreArchivosubir(String nombreArchivosubir) {
        this.nombreArchivosubir = nombreArchivosubir;
    }

    public byte[] getFotoByteImprime() {
        return fotoByteImprime;
    }

    public void setFotoByteImprime(byte[] fotoByteImprime) {
        this.fotoByteImprime = fotoByteImprime;
    }

    public List<SigDocumento> getListaDocumenMostrar() {
        return listaDocumenMostrar;
    }

    public void setListaDocumenMostrar(List<SigDocumento> listaDocumenMostrar) {
        this.listaDocumenMostrar = listaDocumenMostrar;
    }

    public SigDocumento getSigDocuAGuarda() {
        return sigDocuAGuarda;
    }

    public void setSigDocuAGuarda(SigDocumento sigDocuAGuarda) {
        this.sigDocuAGuarda = sigDocuAGuarda;
    }

    public SigArchivoDigService getSigArchivoDigService() {
        return sigArchivoDigService;
    }

    public void setSigArchivoDigService(SigArchivoDigService sigArchivoDigService) {
        this.sigArchivoDigService = sigArchivoDigService;
    }

    public List<Dominio> getListaTipoNotifi() {
        return listaTipoNotifi;
    }

    public void setListaTipoNotifi(List<Dominio> listaTipoNotifi) {
        this.listaTipoNotifi = listaTipoNotifi;
    }

    public DominioService getDominioService() {
        return dominioService;
    }

    public void setDominioService(DominioService dominioService) {
        this.dominioService = dominioService;
    }

    public byte[] getFotoByte() {
        return fotoByte;
    }

    public void setFotoByte(byte[] fotoByte) {
        this.fotoByte = fotoByte;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getNro_folios() {
        return nro_folios;
    }

    public void setNro_folios(Integer nro_folios) {
        this.nro_folios = nro_folios;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getTipo_archivo() {
        return tipo_archivo;
    }

    public void setTipo_archivo(String tipo_archivo) {
        this.tipo_archivo = tipo_archivo;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public SigDocumentoService getSigDocumentoService() {
        return sigDocumentoService;
    }

    public void setSigDocumentoService(SigDocumentoService sigDocumentoService) {
        this.sigDocumentoService = sigDocumentoService;
    }

    public ModModificacionService getModModificacionService() {
        return modModificacionService;
    }

    public void setModModificacionService(ModModificacionService modModificacionService) {
        this.modModificacionService = modModificacionService;
    }

    public String getBusq() {
        return busq;
    }

    public void setBusq(String busq) {
        this.busq = busq;
    }

    public Boolean getValorSM() {
        return valorSM;
    }

    public void setValorSM(Boolean valorSM) {
        this.valorSM = valorSM;
    }

    public Boolean getValorNP() {
        return valorNP;
    }

    public void setValorNP(Boolean valorNP) {
        this.valorNP = valorNP;
    }

    public Boolean getValorNR() {
        return valorNR;
    }

    public void setValorNR(Boolean valorNR) {
        this.valorNR = valorNR;
    }

    public Boolean getActivaPanelAdjuntarDocumentos() {
        return activaPanelAdjuntarDocumentos;
    }

    public void setActivaPanelAdjuntarDocumentos(Boolean activaPanelAdjuntarDocumentos) {
        this.activaPanelAdjuntarDocumentos = activaPanelAdjuntarDocumentos;
    }

    public String getFiltroArea() {
        return filtroArea;
    }

    public void setFiltroArea(String filtroArea) {
        this.filtroArea = filtroArea;
    }

    public Boolean getBuscadorSignosRender() {
        return buscadorSignosRender;
    }

    public void setBuscadorSignosRender(Boolean buscadorSignosRender) {
        this.buscadorSignosRender = buscadorSignosRender;
    }

    public Boolean getBuscadorModificacionesRender() {
        return buscadorModificacionesRender;
    }

    public void setBuscadorModificacionesRender(Boolean buscadorModificacionesRender) {
        this.buscadorModificacionesRender = buscadorModificacionesRender;
    }

    public Boolean getBuscadorRenovacionesRender() {
        return buscadorRenovacionesRender;
    }

    public void setBuscadorRenovacionesRender(Boolean buscadorRenovacionesRender) {
        this.buscadorRenovacionesRender = buscadorRenovacionesRender;
    }

    public String getFiltroSolicitud() {
        return filtroSolicitud;
    }

    public void setFiltroSolicitud(String filtroSolicitud) {
        this.filtroSolicitud = filtroSolicitud;
    }

    public Boolean getBuscadorSigSMRender() {
        return buscadorSigSMRender;
    }

    public void setBuscadorSigSMRender(Boolean buscadorSigSMRender) {
        this.buscadorSigSMRender = buscadorSigSMRender;
    }

    public Boolean getBuscadorSigPublicacionRender() {
        return buscadorSigPublicacionRender;
    }

    public void setBuscadorSigPublicacionRender(Boolean buscadorSigPublicacionRender) {
        this.buscadorSigPublicacionRender = buscadorSigPublicacionRender;
    }

    public Boolean getBuscadorSigRegistroRender() {
        return buscadorSigRegistroRender;
    }

    public void setBuscadorSigRegistroRender(Boolean buscadorSigRegistroRender) {
        this.buscadorSigRegistroRender = buscadorSigRegistroRender;
    }

    public String getExtensionExpediente() {
        return extensionExpediente;
    }

    public void setExtensionExpediente(String extensionExpediente) {
        this.extensionExpediente = extensionExpediente;
    }

    public Long getNumeroSolicitud() {
        return numeroSolicitud;
    }

    public void setNumeroSolicitud(Long numeroSolicitud) {
        this.numeroSolicitud = numeroSolicitud;
    }

    public Integer getGestionSolicitud() {
        return gestionSolicitud;
    }

    public void setGestionSolicitud(Integer gestionSolicitud) {
        this.gestionSolicitud = gestionSolicitud;
    }

    public Long getRegistroSolicitud() {
        return registroSolicitud;
    }

    public void setRegistroSolicitud(Long registroSolicitud) {
        this.registroSolicitud = registroSolicitud;
    }

    public String getSerieRegistro() {
        return serieRegistro;
    }

    public void setSerieRegistro(String serieRegistro) {
        this.serieRegistro = serieRegistro;
    }

    public Long getNumeroPublicacion() {
        return numeroPublicacion;
    }

    public void setNumeroPublicacion(Long numeroPublicacion) {
        this.numeroPublicacion = numeroPublicacion;
    }

    public String getValorTipoModificacion() {
        return valorTipoModificacion;
    }

    public void setValorTipoModificacion(String valorTipoModificacion) {
        this.valorTipoModificacion = valorTipoModificacion;
    }

    public Long getSmBuscar() {
        return smBuscar;
    }

    public void setSmBuscar(Long smBuscar) {
        this.smBuscar = smBuscar;
    }

    public SigSignoMarca getSigSignoMarca() {
        return sigSignoMarca;
    }

    public void setSigSignoMarca(SigSignoMarca sigSignoMarca) {
        this.sigSignoMarca = sigSignoMarca;
    }

    public ComunService getComunService() {
        return comunService;
    }

    public void setComunService(ComunService comunService) {
        this.comunService = comunService;
    }

    public SigSignoMarcaService getSigSignoMarcaService() {
        return sigSignoMarcaService;
    }

    public void setSigSignoMarcaService(SigSignoMarcaService sigSignoMarcaService) {
        this.sigSignoMarcaService = sigSignoMarcaService;
    }

    public boolean isPintaDocumentos() {
        return pintaDocumentos;
    }

    public void setPintaDocumentos(boolean pintaDocumentos) {
        this.pintaDocumentos = pintaDocumentos;
    }

    public UploadedFile getFile() {

        return file;
    }

    public void setFile(UploadedFile file) {

        this.file = file;
    }

    public RenSolicitudRenovacion getRenSolicitudRenovacion() {
        return renSolicitudRenovacion;
    }

    public void setRenSolicitudRenovacion(RenSolicitudRenovacion renSolicitudRenovacion) {
        this.renSolicitudRenovacion = renSolicitudRenovacion;
    }

    public Long getNumSolRen() {
        return numSolRen;
    }

    public void setNumSolRen(Long numSolRen) {
        this.numSolRen = numSolRen;
    }

    public Integer getGesSolRen() {
        return gesSolRen;
    }

    public void setGesSolRen(Integer gesSolRen) {
        this.gesSolRen = gesSolRen;
    }

    public RenSolicitudRenovacionService getRenSolicitudRenovacionService() {
        return renSolicitudRenovacionService;
    }

    public void setRenSolicitudRenovacionService(RenSolicitudRenovacionService renSolicitudRenovacionService) {
        this.renSolicitudRenovacionService = renSolicitudRenovacionService;
    }

    public SeguimientoService getSeguimientoService() {
        return seguimientoService;
    }

    public void setSeguimientoService(SeguimientoService seguimientoService) {
        this.seguimientoService = seguimientoService;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public LogTrans getLogTransGuardado() {
        return logTransGuardado;
    }

    public void setLogTransGuardado(LogTrans logTransGuardado) {
        this.logTransGuardado = logTransGuardado;
    }

    public LogTransService getLogTransService() {
        return logTransService;
    }

    public void setLogTransService(LogTransService logTransService) {
        this.logTransService = logTransService;
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

}
