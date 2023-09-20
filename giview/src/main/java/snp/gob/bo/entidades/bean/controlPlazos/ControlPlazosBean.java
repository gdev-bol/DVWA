/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.bean.controlPlazos;

import java.io.Serializable;
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
import org.primefaces.context.RequestContext;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.gimodel.entidad.Etapa;
import snp.gob.bo.gimodel.entidad.ModModificacion;
import snp.gob.bo.gimodel.entidad.RenSolicitudRenovacion;
import snp.gob.bo.gimodel.entidad.Seguimiento;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.enums.EnumOperacion;
import snp.gob.bo.gimodel.enums.EnumPrefijoTablas;
import snp.gob.bo.gimodel.enums.EnumTipoTramite;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.EtapaService;
import snp.gob.bo.gimodel.servicio.HistorialService;
import snp.gob.bo.gimodel.servicio.ModModificacionService;
import snp.gob.bo.gimodel.servicio.RegionalService;
import snp.gob.bo.gimodel.servicio.RenSolicitudRenovacionService;
import snp.gob.bo.gimodel.servicio.SeguimientoService;
import snp.gob.bo.gimodel.servicio.SigSignoMarcaService;
import snp.gob.bo.gimodel.servicio.UsuarioService;

/**
 *
 * @author eddy
 */
@ManagedBean(name = "controlPlazosBean")
@ViewScoped
public class ControlPlazosBean extends AbstractManagedBean implements Serializable {

    private String template = "./../WEB-INF/facelets/templates/Template.xhtml";
    private String areaRegistro = EnumPrefijoTablas.SIGNO.getCodigo();
    private Long id;
    private Date fechaServidor;
    private String filtroArea = "SIG";
    Date fechaExamenForma = new Date();
    private List<Seguimiento> listaSeguimiento;
    private Boolean buscadorSignosRender = true;
    private Boolean buscadorModificacionesRender = false;
    private Boolean buscadorRenovacionesRender = false;
    private String filtroSolicitud = "SM";
    private Boolean buscadorSigSMRender = true;
    private Boolean buscadorSigPublicacionRender = false;
    private Boolean buscadorSigRegistroRender = false;
    private String valorTipoModificacion = "CANO";
    public String extensionExpediente = "";
    private Long numeroSolicitud;
    private Integer gestionSolicitud;
    private SigSignoMarca encontradoSigno = new SigSignoMarca();
    private Long registroSolicitud;
    private String serieRegistro = "C";
    private Long numeroPublicacion;
    private ModModificacion encontradoModificacion;
    private RenSolicitudRenovacion encontradoRenovacion = new RenSolicitudRenovacion();
    private String solicitud = "";
    private Boolean filtroAreaDisabled = false;
    private String tipoTramite;
    private Long platoRealTotal = 0l;
    private Integer plazoTotal = 0;
    private Integer totalPlazoLimite = 0;
    private Long idregional;
    private Integer tiempoPasivo = 0;
    private Integer plazoEstablecido = 0;

    @ManagedProperty("#{seguimientoService}")
    private SeguimientoService seguimientoService;
    @ManagedProperty("#{modModificacionService}")
    private ModModificacionService modModificacionService;
    @ManagedProperty("#{sigSignoMarcaService}")
    private SigSignoMarcaService sigSignoMarcaService;
    @ManagedProperty("#{comunService}")
    private ComunService comunService;
    @ManagedProperty("#{renSolicitudRenovacionService}")
    private RenSolicitudRenovacionService renSolicitudRenovacionService;
    @ManagedProperty("#{etapaService}")
    private EtapaService etapaService;
    @ManagedProperty("#{regionalService}")
    private RegionalService regionalService;
    @ManagedProperty("#{historialService}")
    private HistorialService historialService;
    @ManagedProperty("#{usuarioService}")
    private UsuarioService usuarioService;

    @PostConstruct
    public void initPlazosBean() {

        try {
            Map<String, String[]> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterValuesMap();
            if (params.get("datosEnviados") != null) {
                template = "./../WEB-INF/facelets/templates/DialogTemplate.xhtml";
                if (params.get("datosEnviados")[0] != null) {
                    areaRegistro = params.get("datosEnviados")[1];
                    filtroArea = areaRegistro;
                    filtroAreaDisabled = true;
                    if (params.get("datosEnviados")[2] != null) {
                        id = Long.parseLong(params.get("datosEnviados")[2]);
                        listaSeguimiento = seguimientoService.lista_SeguimientoXid(areaRegistro, id);
                        solicitud = params.get("datosEnviados")[3];
                        idregional = Long.parseLong(params.get("datosEnviados")[4]);
                        platoRealTotal = seguimientoService.conteoTotalTiempo(listaSeguimiento, areaRegistro, idregional);
                        totalPlazoLimite = seguimientoService.conteoPlazoLimite(areaRegistro, id);
                        tiempoPasivo = seguimientoService.conteoTiempoPasivo(areaRegistro, id);
                        if (areaRegistro.equals(EnumPrefijoTablas.SIGNO.getCodigo())) {
                            plazoEstablecido = 150;
                        }
                        if (areaRegistro.equals(EnumPrefijoTablas.RENOVACION.getCodigo())) {
                            plazoEstablecido = 26;
                        }
                        if (areaRegistro.equals(EnumPrefijoTablas.MODIFICACION.getCodigo())) {
                            plazoEstablecido = 26;
                        }
                        // for (Seguimiento item : listaSeguimiento) {
                        //    platoRealTotal = platoRealTotal + item.getPlazoReal();                                                                                                                                                                                                               
                        // plazoTotal = plazoTotal + etapaService.listar_etapa_tipoTramite_tipoEtapa("", item.getEtapa()).getPlazo();
                        //System.out.println("etapaService.listar_etapa_tipoTramite_tipoEtapa(\"\",item.getEtapa()).getPlazo()::"+etapaService.listar_etapa_tipoTramite_tipoEtapa("",item.getEtapa()).getPlazo());
                        //System.out.println("plazoTotal:"+plazoTotal);                                            
                        //}
//                        if(!listaSeguimiento.isEmpty()){
//                        platoRealTotal=listaSeguimiento.get(listaSeguimiento.size()-1).getTotalTiempo().intValue();}
                    }
                    activaCambios();
                    activaCambiosBuscadorSolicitud();
                }
            } else {
                template = "./../WEB-INF/facelets/templates/Template.xhtml";
                vaciarVariables();

            }
        } catch (Exception ex) {
            Logger.getLogger(ControlPlazosBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void modificarObservacion(Seguimiento seguimiento) throws Exception {
        if (super.getIdEtapaSession() == 15) {
            Long regional = usuarioService.buscaUsuarioPorIdUsuario(super.getIdUsuarioSession()).getIdregional();
            List<Seguimiento> lista = seguimientoService.lista_SeguimientoXid(areaRegistro, seguimiento.getId());
            if (lista.size() >= 2) {
                int diapasivo = seguimientoService.diasLaboralesSigno(lista.get(lista.size() - 2).getFechaFin(), seguimiento.getFechaRecepcion(), regional);
                seguimiento.setDia_pasivo(diapasivo);                
            }
        }

        Seguimiento miseguimiento = seguimientoService.guardar_modificar_listar_Seguimiento(seguimiento, areaRegistro, 4);
        seguimientoService.guardar_modificar_listar_Seguimiento(seguimiento, areaRegistro, 2);
        String observacion = "";
        if (miseguimiento.getObservacion() != null) {
            observacion = miseguimiento.getObservacion();
        }
        historialService.guardarHistorialGenerico(areaRegistro, seguimiento.getId(), super.getIdUsuarioSession(), "SEGUIMIENTO",
                EnumOperacion.MODIFICAR.getCodigo(), null,
                "Generado por módulo de seguimiento", null, "Módulo Seguimiento [fecha_recepcion.:" + miseguimiento.getFechaRecepcion() + "][Observación.:" + observacion + "]");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Datos guardados", ""));
    }

    public void vaciarVariables() {
        registroSolicitud = null;
        serieRegistro = "";
        numeroPublicacion = null;
        extensionExpediente = "";
        numeroSolicitud = null;
        gestionSolicitud = null;
        platoRealTotal = 0l;
        plazoTotal = 0;
        totalPlazoLimite = 0;
        tiempoPasivo = 0;
        plazoEstablecido = 0;
        listaSeguimiento = new ArrayList<Seguimiento>();
        solicitud = "";
    }

    public String[] etapaDescripcion(String codigo, Long idseguimiento) throws Exception {

        String obs = "";
        Integer plazoObservado;
        if (codigo.equals("EXFM") || codigo.equals("ANR")) //if(codigo.equals("EXFM"))
        {
            //List<Seguimiento> listSeguimientoUltimos = seguimientoService.listaSeguimientoUltimoXIdsignoEtapaUltMov(listaSeguimiento.get(0).getId(), codigo);
            if (seguimientoService.listaSeguimientoUltimoXIdsignoEtapaUltMov(listaSeguimiento.get(0).getId(), codigo, idseguimiento) != null) {
                obs = "(obs)";
            }

        }

        String[] descripcion = {"", ""};
        Etapa etapa = etapaService.listar_etapa_tipoTramite_tipoEtapa(tipoTramite, codigo);
        if (etapa != null) {
            descripcion[0] = etapa.getDescripcion() + obs;
            //La devolución  del plazo ya seria inservible porq tengo  en la tabla segumiento el campo plazo_limite qeu directo imprimo en la vista  
            descripcion[1] = etapa.getPlazo().toString();

        }
        return descripcion;
    }

    public String colorAlerta(Integer plazoLimite, Integer plazoReal) throws Exception {
        String color = "5AEA12";
        Integer valor = plazoLimite - plazoReal;
        if (valor >= 3) {
            color = "#000000";//azul
        }
        if (valor == 2 || valor == 1 || valor == 0) {
            color = "#32A02F";//lila
        }
        if (valor < 0) {
            color = "#F60310";//rojo
        }

        return color;
    }

    public void activaCambios() {
        switch (filtroArea) {
            case "SIG":
                buscadorSignosRender = true;
                buscadorModificacionesRender = false;
                buscadorRenovacionesRender = false;
                filtroSolicitud = "SM";
                areaRegistro = EnumPrefijoTablas.SIGNO.getCodigo();
                tipoTramite = EnumTipoTramite.REGISTRO_MARCAS.getCodigo();
                break;
            case "MOD":
                buscadorSignosRender = false;
                buscadorModificacionesRender = true;
                buscadorRenovacionesRender = false;
                filtroSolicitud = "MO";
                areaRegistro = EnumPrefijoTablas.MODIFICACION.getCodigo();
                tipoTramite = EnumTipoTramite.MODIFICACION.getCodigo();
                break;
            case "REN":
                buscadorSignosRender = false;
                buscadorModificacionesRender = false;
                buscadorRenovacionesRender = true;
                filtroSolicitud = "SR";
                areaRegistro = EnumPrefijoTablas.RENOVACION.getCodigo();
                tipoTramite = EnumTipoTramite.RENOVACION.getCodigo();
                break;
            default:
                buscadorSignosRender = false;
                buscadorModificacionesRender = false;
                buscadorRenovacionesRender = false;
                break;
        }
        activaCambiosBuscadorSolicitud();
        if (filtroAreaDisabled == false) {
            vaciarVariables();
        }
    }

    public void activaCambiosBuscadorSolicitud() {
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

    public void llenarOtrosDatos() {
        platoRealTotal = seguimientoService.conteoTotalTiempo(listaSeguimiento, areaRegistro, idregional);
        totalPlazoLimite = seguimientoService.conteoPlazoLimite(areaRegistro, id);
        tiempoPasivo = seguimientoService.conteoTiempoPasivo(areaRegistro, id);
        if (areaRegistro.equals(EnumPrefijoTablas.SIGNO.getCodigo())) {
            plazoEstablecido = 150;
        }
        if (areaRegistro.equals(EnumPrefijoTablas.RENOVACION.getCodigo())) {
            plazoEstablecido = 15;
        }
        if (areaRegistro.equals(EnumPrefijoTablas.MODIFICACION.getCodigo())) {
            plazoEstablecido = 15;
        }
    }

    public void buscaSeguimientoSignos() throws Exception {
        switch (filtroSolicitud) {
            case "SM":
                if (numeroSolicitud != null && gestionSolicitud != null) {//&& gestionExpediente != null
                    if (extensionExpediente == null) {
                        extensionExpediente = "";
                    }
                    Long expediente = creaSM("SM", numeroSolicitud, gestionSolicitud, extensionExpediente);
                    encontradoSigno = sigSignoMarcaService.listaSigSignoMarcaXSM(expediente);
                }
                break;
            case "REG":
                if (registroSolicitud != null) {
                    encontradoSigno = sigSignoMarcaService.listaSigSignoMarcaXRegistro(registroSolicitud, serieRegistro, " ");
                }
                break;
            case "PUB":
                if (numeroPublicacion != null) {
                    encontradoSigno = sigSignoMarcaService.listaSigSignoMarcaXPublicacion(numeroPublicacion);
                }
                break;
            default:
                break;
        }
        if (encontradoSigno != null) {
            listaSeguimiento = seguimientoService.lista_SeguimientoXid(filtroArea, encontradoSigno.getIdSignoMarca());
            this.numeroSolicitud = encontradoSigno.getNumero().longValue();
            this.gestionSolicitud = encontradoSigno.getGestion();
            this.registroSolicitud = encontradoSigno.getNumeroRegistro();
            this.serieRegistro = encontradoSigno.getSerieRegistro();
            this.numeroPublicacion = encontradoSigno.getNumeroPublicacion();
            if (encontradoSigno.getExtensionMarca() == 00) {
            } else {
                char ch = (char) (encontradoSigno.getExtensionMarca() + 55);
                extensionExpediente = "-" + ch;
            }
            solicitud = "SM-" + String.format("%6s", numeroSolicitud).replace(' ', '0') + "-" + gestionSolicitud + extensionExpediente;
            
            idregional = regionalService.lista_IdRegional_TipoCiudadNotificacion(encontradoSigno.getOficina());
            id = encontradoSigno.getIdSignoMarca();
            llenarOtrosDatos();
        } else {
            solicitud="";
            listaSeguimiento= new ArrayList<>();
            this.numeroSolicitud = null;
            this.gestionSolicitud = null;
            this.registroSolicitud = null;
            this.serieRegistro = "";
            this.numeroPublicacion = null;
            platoRealTotal = null;
            totalPlazoLimite = 0;
            tiempoPasivo = 0;
            plazoEstablecido = 0;
            plazoEstablecido = 0;
            plazoEstablecido = 0;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El tramite no existe", ""));
        }
    }

    public void buscaSeguimientoModificacion() throws Exception {
        switch (filtroSolicitud) {
            case "MO":
                if (numeroSolicitud != null && gestionSolicitud != null) {//&& gestionExpediente != null                       
                    encontradoModificacion = modModificacionService.buscarModModificacionXtipo(valorTipoModificacion, numeroSolicitud, gestionSolicitud);
                }
                break;
            case "REG":
                if (registroSolicitud != null) {
                    encontradoSigno = sigSignoMarcaService.listaSigSignoMarcaXRegistro(registroSolicitud, serieRegistro, " ");
                }
                break;
            case "PUB":
                if (numeroPublicacion != null) {
                    encontradoSigno = sigSignoMarcaService.listaSigSignoMarcaXPublicacion(numeroPublicacion);
                }
                break;
            default:
                break;
        }

        if (encontradoModificacion != null) {
            listaSeguimiento = seguimientoService.lista_SeguimientoXid(filtroArea, encontradoModificacion.getIdmodificacion());
            idregional = regionalService.lista_IdRegional_TipoCiudadNotificacion(encontradoModificacion.getOficina());
            id = encontradoModificacion.getIdmodificacion();
            solicitud = encontradoModificacion.getSigla() + "-" + String.format("%6s", numeroSolicitud).replace(' ', '0') + "-" + gestionSolicitud;
            llenarOtrosDatos();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El tramite no existe", ""));
        }
    }

    public void buscaSeguimientoRenovacion() throws Exception {
        // System.out.println("filtro  " + filtroSolicitud);
        switch (filtroSolicitud) {
            case "SR":
                if (numeroSolicitud != null && gestionSolicitud != null) {//&& gestionExpediente != null                    
                    encontradoRenovacion = renSolicitudRenovacionService.buscaSolicitudRenovacionPorNumeroSrYGestion(numeroSolicitud, gestionSolicitud);
                }
                break;
            case "REG":
                if (registroSolicitud != null) {

                }
                break;
            case "PUB":
                if (numeroPublicacion != null) {

                }
                break;
            default:
                break;
        }
        if (encontradoRenovacion != null) {
            listaSeguimiento = seguimientoService.lista_SeguimientoXid(filtroArea, encontradoRenovacion.getIdsolicitudrenovacion());
            idregional = regionalService.lista_IdRegional_TipoCiudadNotificacion(encontradoRenovacion.getOficina());
            id = encontradoRenovacion.getIdsolicitudrenovacion();
            solicitud = "SR-" + String.format("%6s", numeroSolicitud).replace(' ', '0') + "-" + gestionSolicitud;
            llenarOtrosDatos();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El tramite no existe", ""));
        }
    }

    public Long creaSM(String textoSM, Long numeroSM, Integer gestionSM, String extension) throws Exception {
        Long sm = 0l;
        //Long extension = new Long(Long.parseLong(extensionSM));
        if (numeroSM != null && gestionSM != null) {
            //sm = numeroSM + gestionSM;
            sm = comunService.codificarCodigoSM(numeroSM.toString(), gestionSM.toString(), extension);
        }
        return sm;
    }

    public Boolean mostrarCalendario(String etapa, Date fechafin) {
        //calendario aparece
        //calendario no aparece        
        Long idetapa = etapaService.idetapaXCodigo(etapa);
        return idetapa == 15 && super.getIdEtapaSession() == 15 && fechafin == null; //&& areaRegistro.equals(EnumPrefijoTablas.SIGNO.getCodigo())
    }

    public String retornarPagina() {

        return getNavegaPagina();
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public SeguimientoService getSeguimientoService() {
        return seguimientoService;
    }

    public void setSeguimientoService(SeguimientoService seguimientoService) {
        this.seguimientoService = seguimientoService;
    }

    public List<Seguimiento> getListaSeguimiento() {
        return listaSeguimiento;
    }

    public void setListaSeguimiento(List<Seguimiento> listaSeguimiento) {
        this.listaSeguimiento = listaSeguimiento;
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

    public String getValorTipoModificacion() {
        return valorTipoModificacion;
    }

    public void setValorTipoModificacion(String valorTipoModificacion) {
        this.valorTipoModificacion = valorTipoModificacion;
    }

    public ModModificacionService getModModificacionService() {
        return modModificacionService;
    }

    public void setModModificacionService(ModModificacionService modModificacionService) {
        this.modModificacionService = modModificacionService;
    }

    public SigSignoMarcaService getSigSignoMarcaService() {
        return sigSignoMarcaService;
    }

    public void setSigSignoMarcaService(SigSignoMarcaService sigSignoMarcaService) {
        this.sigSignoMarcaService = sigSignoMarcaService;
    }

    public String getExtensionExpediente() {
        return extensionExpediente;
    }

    public void setExtensionExpediente(String extensionExpediente) {
        this.extensionExpediente = extensionExpediente;
    }

    public ComunService getComunService() {
        return comunService;
    }

    public void setComunService(ComunService comunService) {
        this.comunService = comunService;
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

    public RenSolicitudRenovacionService getRenSolicitudRenovacionService() {
        return renSolicitudRenovacionService;
    }

    public void setRenSolicitudRenovacionService(RenSolicitudRenovacionService renSolicitudRenovacionService) {
        this.renSolicitudRenovacionService = renSolicitudRenovacionService;
    }

    public String getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(String solicitud) {
        this.solicitud = solicitud;
    }

    public Boolean getFiltroAreaDisabled() {
        return filtroAreaDisabled;
    }

    public void setFiltroAreaDisabled(Boolean filtroAreaDisabled) {
        this.filtroAreaDisabled = filtroAreaDisabled;
    }

    public EtapaService getEtapaService() {
        return etapaService;
    }

    public void setEtapaService(EtapaService etapaService) {
        this.etapaService = etapaService;
    }

    public Long getPlatoRealTotal() {
        return platoRealTotal;
    }

    public void setPlatoRealTotal(Long platoRealTotal) {
        this.platoRealTotal = platoRealTotal;
    }

    public Integer getPlazoTotal() {
        return plazoTotal;
    }

    public void setPlazoTotal(Integer plazoTotal) {
        this.plazoTotal = plazoTotal;
    }

    public Integer getTotalPlazoLimite() {
        return totalPlazoLimite;
    }

    public void setTotalPlazoLimite(Integer totalPlazoLimite) {
        this.totalPlazoLimite = totalPlazoLimite;
    }

    public Integer getTiempoPasivo() {
        return tiempoPasivo;
    }

    public void setTiempoPasivo(Integer tiempoPasivo) {
        this.tiempoPasivo = tiempoPasivo;
    }

    public Integer getPlazoEstablecido() {
        return plazoEstablecido;
    }

    public void setPlazoEstablecido(Integer plazoEstablecido) {
        this.plazoEstablecido = plazoEstablecido;
    }

    public RegionalService getRegionalService() {
        return regionalService;
    }

    public void setRegionalService(RegionalService regionalService) {
        this.regionalService = regionalService;
    }

    public HistorialService getHistorialService() {
        return historialService;
    }

    public void setHistorialService(HistorialService historialService) {
        this.historialService = historialService;
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

}
