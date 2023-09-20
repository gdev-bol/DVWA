/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.expediente;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import snp.gob.bo.gimodel.entidad.BusquedaModificacionResultado;
import snp.gob.bo.gimodel.entidad.ModTitularLicenciatarioNuevo;
import snp.gob.bo.gimodel.entidad.ObservacionTramite;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.enums.EnumPrefijoTablas;
import snp.gob.bo.gimodel.servicio.BusquedaModificacionResultadoService;
import snp.gob.bo.gimodel.servicio.ModTitularLicenciatarioNuevoService;
import snp.gob.bo.gimodel.servicio.ObservacionTramiteService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import snp.gob.bo.gimodel.entidad.ModModificacion;
import snp.gob.bo.gimodel.entidad.ModResolucion;
import snp.gob.bo.gimodel.entidad.ModSolicitanteApoderado;
import snp.gob.bo.gimodel.enums.EnumTipoModificacion;
import snp.gob.bo.gimodel.enums.EnumTipoPersona;
import snp.gob.bo.gimodel.servicio.ModModificacionService;
import snp.gob.bo.gimodel.servicio.ModResolucionService;
import snp.gob.bo.gimodel.servicio.ModSolicitanteApoderadoService;
import snp.gob.bo.gimodel.servicio.SigSolicitanteApoderadoService;

/**
 *
 * @author susana
 */
@ManagedBean(name = "historialModificacionBean")
@ViewScoped
public class HistorialModificacionBean {

    /**
     * Creates a new instance of HistorialModificacionBean
     */
    public HistorialModificacionBean() {
    }

    private BusquedaModificacionResultado busquedaModificacionResultadoSeleccionada;
    private List<BusquedaModificacionResultado> listaModificacion = new ArrayList<BusquedaModificacionResultado>();
    private List<ModModificacion> listaModModificacion = new ArrayList<ModModificacion>();
    private String ultimaObervacion = "";
    private String[] datosTitularActual = {"", "", "", ""};
    private String tipoModificacionDescripcion = "";
    private String expediente = "";
    private ModModificacion mimodificacion = new ModModificacion();
    private String nombreApoderado = "";

    @ManagedProperty("#{modModificacionService}")
    private ModModificacionService modModificacionService;
    @ManagedProperty("#{modTitularLicencitarioNuevoService}")
    private ModTitularLicenciatarioNuevoService modTitularLicencitarioNuevoService;
    @ManagedProperty("#{busquedaModificacionResultadoService}")
    private BusquedaModificacionResultadoService busquedaModificacionResultadoService;
    @ManagedProperty("#{observacionTramiteService}")
    private ObservacionTramiteService observacionTramiteService;
    @ManagedProperty("#{modResolucionService}")
    private ModResolucionService modResolucionService;
    @ManagedProperty("#{sigSolicitanteApoderadoService}")
    private SigSolicitanteApoderadoService sigSolicitanteApoderadoService;
    @ManagedProperty("#{modSolicitanteApoderadoService}")
    private ModSolicitanteApoderadoService modSolicitanteApoderadoService;

    @PostConstruct
    public void HistorialModificacionBeanInit() {

        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        if (sessionMap.get("signoMarca") != null) {
            //  System.out.println("sessionMap.get(\"signoMarca\")  " + sessionMap.get("signoMarca"));
            SigSignoMarca signoMarca = (SigSignoMarca) sessionMap.get("signoMarca");
            expediente = signoMarca.getSm().toString();
            //System.out.println(signoMarca.getSm() + " " + signoMarca.getNumeroPublicacion() + "  " + signoMarca.getNumeroRegistro() + " " + signoMarca.getSerieRegistro());
            //datosTitularActual = sigSolicitanteApoderadoService.datos_SigSolicitanteApoderado_concatenado(signoMarca.getIdSignoMarca(), EnumTipoPersona.SOLICITANTE.getCodigo());
            listaModificacion = busquedaModificacionResultadoService.lista_BusquedaModificacionResultado_sm_publicacion_registro_or(signoMarca.getSm(), signoMarca.getNumeroPublicacion(), signoMarca.getNumeroRegistro(), signoMarca.getSerieRegistro());

            for (BusquedaModificacionResultado item : listaModificacion) {
                mimodificacion = modModificacionService.buscarModModificacionXid(item.getIdmodificacion());
                if (mimodificacion.getTitular_signo() != null) {
                    break;
                }
            }
            mostrarPrimerTitularEncontrado();
            //    listaModModificacion = modModificacionService.lista_ModModificacion_sm_publicacion_registro_or(signoMarca.getSm(), signoMarca.getNumeroPublicacion(), signoMarca.getNumeroRegistro(), signoMarca.getSerieRegistro());            
        }

    }

    void mostrarPrimerTitularEncontrado() {
        if (mimodificacion.getIdmodificacion() != null) {
            if (mimodificacion.getTitular_signo() != null) {
                datosTitularActual[0] = mimodificacion.getTitular_signo();
            } else {
                datosTitularActual[0] = "";
            }
            if (mimodificacion.getDomicilio_signo() != null) {
                datosTitularActual[1] = mimodificacion.getDomicilio_signo();
            } else {
                datosTitularActual[1] = "";
            }
            if (mimodificacion.getNacionalidad_signo() != null) {
                datosTitularActual[2] = mimodificacion.getNacionalidad_signo();
            } else {
                datosTitularActual[2] = "";
            }
            if (mimodificacion.getDepartamento_signo() != null) {
                datosTitularActual[3] = mimodificacion.getDepartamento_signo();
            } else {
                datosTitularActual[3] = "";
            }
        }
    }

    public String devuelveTitularNuevo(Long idmodificacion) {
        List<ModTitularLicenciatarioNuevo> lstTitularNuevo = modTitularLicencitarioNuevoService.listaNuevoTitularXidmodificacion(idmodificacion);
        String textoSolicitante = "";
        for (ModTitularLicenciatarioNuevo item : lstTitularNuevo) {
            String dato = devuelveNombreJuridicoONatural(item.getNombre_razonsocial(), item.getPrimer_apellido(), item.getSegundo_apellido());
            textoSolicitante = textoSolicitante + dato.trim() + ", ";
        }
        if (textoSolicitante.length() > 2) {
            textoSolicitante = textoSolicitante.substring(0, textoSolicitante.length() - 2);
        }
        return textoSolicitante;//SOLICITA        
    }

    public String devuelveNombreJuridicoONatural(String nombre, String primerApellido, String segundoApellido) {
        String campoNombreRazonSocial = " ";
        if (nombre != null && !nombre.equals("")) {
            campoNombreRazonSocial = nombre;
        }
        if (primerApellido != null && !primerApellido.equals("")) {
            campoNombreRazonSocial = campoNombreRazonSocial + " " + primerApellido;
        }
        if (segundoApellido != null && !segundoApellido.equals("")) {
            campoNombreRazonSocial = campoNombreRazonSocial + " " + segundoApellido;
        }
        return campoNombreRazonSocial;
    }

    public void accionCerrar() {
        RequestContext.getCurrentInstance().closeDialog("Exit");
    }

    public String textoTipoModificacion(String tipo) {
        String mensaje = "";

        if (tipo.equals(EnumTipoModificacion.CAMBIO_NOMBRE.getCodigo())) {
            mensaje = "Cambio de Nombre";
        }
        if (tipo.equals(EnumTipoModificacion.CAMBIO_DOMICILIO.getCodigo())) {
            mensaje = "Cambio de Domicilio";
        }
        if (tipo.equals(EnumTipoModificacion.TRANSFERENCIA.getCodigo())) {
            mensaje = "Transferencia";
        }
        if (tipo.equals(EnumTipoModificacion.FUSION.getCodigo())) {
            mensaje = "Fusion";
        }
        if (tipo.equals(EnumTipoModificacion.LICENCIA_DE_USO.getCodigo())) {
            mensaje = "Licencia de Uso";
        }
        return mensaje;
    }

    public void onRowSelect(SelectEvent event) throws Exception {

        //FacesMessage msg = new FacesMessage("Publicacion Seleccionada", ((Publicacion) event.getObject()).getIdPublicacion().toString());
        String tipo = ((BusquedaModificacionResultado) event.getObject()).getTipo_modificacion();
        String mensaje = textoTipoModificacion(tipo);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, ""));
        tipoModificacionDescripcion = mensaje;
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(((BusquedaModificacionResultado) event.getObject()).getTipo_modificacion()));
        busquedaModificacionResultadoSeleccionada = ((BusquedaModificacionResultado) event.getObject());
        List<ModSolicitanteApoderado> apoderado = modSolicitanteApoderadoService.listadoApoderadoXidmodificacion(busquedaModificacionResultadoSeleccionada.getIdmodificacion());
        if (!apoderado.isEmpty()) {
            nombreApoderado = apoderado.get(0).getNombre_razonsocial();           
            if (apoderado.get(0).getPrimer_apellido() != null) {
                nombreApoderado = nombreApoderado + " "+apoderado.get(0).getPrimer_apellido();           
            }
            if (apoderado.get(0).getSegundo_apellido() != null) {
                nombreApoderado = nombreApoderado +"  "+ apoderado.get(0).getSegundo_apellido();           
            }
        }
        ObservacionTramite observacion = observacionTramiteService.obtenerUltimaObservacionTramite(EnumPrefijoTablas.MODIFICACION.getCodigo(), busquedaModificacionResultadoSeleccionada.getIdmodificacion());

        if (observacion != null) {
            ultimaObervacion = observacion.getDescripcion();
        }

    }

    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Publicacion No Selecccionada", ((BusquedaModificacionResultado) event.getObject()).getSigla());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public Integer[] resolucion(Long idModificacion) {

        Integer[] resultado = {null, null};
        ModResolucion modresolucion = modResolucionService.buscar_ModResolucionXidmodificacion(idModificacion);
        if (modresolucion != null) {
            resultado[0] = modresolucion.getNumero_resolucion();
            resultado[1] = modresolucion.getGestion_resolucion();
        }

        return resultado;
    }

    public Date fecha_resolucion(Long idModificacion) {

        Date fecha_resolucion = null;
        ModResolucion modresolucion = modResolucionService.buscar_ModResolucionXidmodificacion(idModificacion);
        if (modresolucion != null) {
            fecha_resolucion = modresolucion.getFecha_resolucion();
        }

        return fecha_resolucion;
    }

    public List<BusquedaModificacionResultado> getListaModificacion() {
        return listaModificacion;
    }

    public void setListaModificacion(List<BusquedaModificacionResultado> listaModificacion) {
        this.listaModificacion = listaModificacion;
    }

    public BusquedaModificacionResultadoService getBusquedaModificacionResultadoService() {
        return busquedaModificacionResultadoService;
    }

    public void setBusquedaModificacionResultadoService(BusquedaModificacionResultadoService busquedaModificacionResultadoService) {
        this.busquedaModificacionResultadoService = busquedaModificacionResultadoService;
    }

    public ModModificacionService getModModificacionService() {
        return modModificacionService;
    }

    public void setModModificacionService(ModModificacionService modModificacionService) {
        this.modModificacionService = modModificacionService;
    }

    public ModTitularLicenciatarioNuevoService getModTitularLicencitarioNuevoService() {
        return modTitularLicencitarioNuevoService;
    }

    public void setModTitularLicencitarioNuevoService(ModTitularLicenciatarioNuevoService modTitularLicencitarioNuevoService) {
        this.modTitularLicencitarioNuevoService = modTitularLicencitarioNuevoService;
    }

    public BusquedaModificacionResultado getBusquedaModificacionResultadoSeleccionada() {
        return busquedaModificacionResultadoSeleccionada;
    }

    public void setBusquedaModificacionResultadoSeleccionada(BusquedaModificacionResultado busquedaModificacionResultadoSeleccionada) {
        this.busquedaModificacionResultadoSeleccionada = busquedaModificacionResultadoSeleccionada;
    }

    public String getUltimaObervacion() {
        return ultimaObervacion;
    }

    public void setUltimaObervacion(String ultimaObervacion) {
        this.ultimaObervacion = ultimaObervacion;
    }

    public ObservacionTramiteService getObservacionTramiteService() {
        return observacionTramiteService;
    }

    public void setObservacionTramiteService(ObservacionTramiteService observacionTramiteService) {
        this.observacionTramiteService = observacionTramiteService;
    }

    public List<ModModificacion> getListaModModificacion() {
        return listaModModificacion;
    }

    public void setListaModModificacion(List<ModModificacion> listaModModificacion) {
        this.listaModModificacion = listaModModificacion;
    }

    public ModResolucionService getModResolucionService() {
        return modResolucionService;
    }

    public void setModResolucionService(ModResolucionService modResolucionService) {
        this.modResolucionService = modResolucionService;
    }

    public String[] getDatosTitularActual() {
        return datosTitularActual;
    }

    public void setDatosTitularActual(String[] datosTitularActual) {
        this.datosTitularActual = datosTitularActual;
    }

    public SigSolicitanteApoderadoService getSigSolicitanteApoderadoService() {
        return sigSolicitanteApoderadoService;
    }

    public void setSigSolicitanteApoderadoService(SigSolicitanteApoderadoService sigSolicitanteApoderadoService) {
        this.sigSolicitanteApoderadoService = sigSolicitanteApoderadoService;
    }

    public String getTipoModificacionDescripcion() {
        return tipoModificacionDescripcion;
    }

    public void setTipoModificacionDescripcion(String tipoModificacionDescripcion) {
        this.tipoModificacionDescripcion = tipoModificacionDescripcion;
    }

    public String getExpediente() {
        return expediente;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

    public ModSolicitanteApoderadoService getModSolicitanteApoderadoService() {
        return modSolicitanteApoderadoService;
    }

    public void setModSolicitanteApoderadoService(ModSolicitanteApoderadoService modSolicitanteApoderadoService) {
        this.modSolicitanteApoderadoService = modSolicitanteApoderadoService;
    }

    public String getNombreApoderado() {
        return nombreApoderado;
    }

    public void setNombreApoderado(String nombreApoderado) {
        this.nombreApoderado = nombreApoderado;
    }

}
