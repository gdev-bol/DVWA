/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.expediente;

import java.awt.Event;
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
import snp.gob.bo.gimodel.entidad.ClaseNiza;
import snp.gob.bo.gimodel.entidad.LogTrans;
import snp.gob.bo.gimodel.entidad.SigSignoClaseNiza;
import snp.gob.bo.gimodel.servicio.ClaseNizaService;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.LogTransService;

/**
 *
 * @author susana
 */
@ManagedBean(name = "agregarClaseNizaBean")
@ViewScoped
public class AgregarClaseNizaBean extends AbstractManagedBean implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Definicion de servicios y atributos">
    private String tituloPagina = "Agregar clase niza";
    private String producto = "";
    private Long numeroClaseNiza;
    private Long idsigsignomarca;
    private SigSignoClaseNiza sigSignoClaseNiza = new SigSignoClaseNiza();
    private ClaseNiza claseNiza = new ClaseNiza();
    private List<ClaseNiza> lstClaseNiza = new ArrayList<ClaseNiza>();
    private String validacion;
    private List<ClaseNiza> lstClaseNiza2 = new ArrayList<ClaseNiza>();
    private List<String> lstClaseNizaVersion = new ArrayList<String>();
    private Long idclaseniza;
    private ClaseNiza claseNiza2 = new ClaseNiza();
    private String version;
    private Long idUsuario;
    private Date fechaSistema;
    @ManagedProperty("#{claseNizaService}")
    private ClaseNizaService claseNizaService;

    @ManagedProperty("#{logTransService}")
    private LogTransService logTransService;
    @ManagedProperty("#{comunService}")
    private ComunService comunService;

    //</editor-fold>
    /**
     * Creates a new instance of AgregarPersonaEmpresaBean
     */
    public AgregarClaseNizaBean() {

    }

    /**
     * Metodo inicial del bean
     *
     * Creado: Eddy Valero Fecha: 04/10/2016
     */
    @PostConstruct
    public void initAgregarClaseNizaBean() {
        try {
            //obtener el listado de clase Niza
            lstClaseNiza = claseNizaService.obtenerListadoClaseNiza();
            idUsuario = super.getIdUsuarioSession();
            fechaSistema = fechaSistema = comunService.obtenerFechaHoraServidor(1L);
            //cargar los parametros enviados desde la vista
            Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

            //si viene de la opcion de modificar clase niza se ejecuta el siguiente codigo, caso contrario se inicializan variables
            if (sessionMap.get("claseniza") != null) {

                this.sigSignoClaseNiza = (SigSignoClaseNiza) sessionMap.get("claseniza");
                this.numeroClaseNiza = this.sigSignoClaseNiza.getNumeroClaseNiza();

                this.claseNiza.setNumeroClaseNiza(numeroClaseNiza);
                this.claseNiza.setProteccion(this.sigSignoClaseNiza.getListaProducto());

                claseNiza2 = claseNizaService.listarClaseNiza_id(sigSignoClaseNiza.getIdClaseNiza());
                idclaseniza = claseNiza2.getIdClaseNiza();
                //version = claseNiza2.getVersion();
                 version = claseNiza2.getNumeroEdicion();
                lstClaseNiza2 = claseNizaService.obtenerListadoClaseNizaVersion(version);
                this.claseNiza2.setProteccion(this.sigSignoClaseNiza.getListaProducto());
            } else if (sessionMap.get("idsigsignomarca") != null) {
                idsigsignomarca = (Long) sessionMap.get("idsigsignomarca");
            }

        } catch (Exception ex) {
            Logger.getLogger(AgregarClaseNizaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cambiarClaseNiza() {

        if (this.numeroClaseNiza == null) {
            this.validacion = "Eliga una opcion";
        } else {
            try {
                //obtener la clase niza
                this.claseNiza = claseNizaService.obtenerRegistroClaseNiza(this.numeroClaseNiza);

            } catch (Exception ex) {
                Logger.getLogger(AgregarClaseNizaBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     * Metodo cerrar el dialogo
     *
     * Creado: Eddy Valero Fecha: 04/10/2016
     */
    public void cerrarDialogoClaseNiza() {
        RequestContext.getCurrentInstance().closeDialog("Exit");
    }

    /**
     * Metodo agregar Clase Niza
     *
     * Creado: Eddy Valero Fecha: 06/10/2016
     */
    public void addClaseNiza() {
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        if (sessionMap.get("claseniza") != null) {
            sigSignoClaseNiza = (SigSignoClaseNiza) sessionMap.get("claseniza");
        }
        //this.sigSignoClaseNiza.setIdClaseNiza(claseNiza.getIdClaseNiza());
        if (sigSignoClaseNiza.getIdSignoMarca() == null) {
            this.sigSignoClaseNiza.setIdSignoMarca(idsigsignomarca);
            this.sigSignoClaseNiza.setIdLogTrans(1L);
        }
        //else
        //this.sigSignoClaseNiza.setIdSignoMarca(null);

        this.sigSignoClaseNiza.setNumeroClaseNiza(claseNiza.getNumeroClaseNiza());
        this.sigSignoClaseNiza.setIdClaseNiza(numeroClaseNiza);
        this.sigSignoClaseNiza.setListaProducto(claseNiza.getProteccion());
        this.sigSignoClaseNiza.setEstado("AC");
        RequestContext.getCurrentInstance().closeDialog(this.sigSignoClaseNiza);
    }

    public void addClaseNiza2() throws Exception {

        LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idUsuario, fechaSistema), 1);
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        if (this.version.equals("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Por favor", "Elija Versión y Clase niza"));
        } else if (this.idclaseniza == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Por favor", "Elija Clase niza"));
        } else {

            if (sessionMap.get("claseniza") != null) {
                sigSignoClaseNiza = (SigSignoClaseNiza) sessionMap.get("claseniza");
            }

            if (sigSignoClaseNiza.getIdSignoMarca() == null) {
                this.sigSignoClaseNiza.setIdSignoMarca(idsigsignomarca);
                this.sigSignoClaseNiza.setIdLogTrans(logTransGuardado.getIdLogTrans());
            }

            this.sigSignoClaseNiza.setNumeroClaseNiza(claseNiza2.getNumeroClaseNiza());
            this.sigSignoClaseNiza.setIdClaseNiza(idclaseniza);
            this.sigSignoClaseNiza.setListaProducto(claseNiza2.getProteccion());
            this.sigSignoClaseNiza.setEstado("AC");
            RequestContext.getCurrentInstance().closeDialog(this.sigSignoClaseNiza);
        }

    }

    //<editor-fold defaultstate="collapsed" desc="Definicion de servicios y atributos">
    public String getTituloPagina() {
        return tituloPagina;
    }

    public void setTituloPagina(String tituloPagina) {
        this.tituloPagina = tituloPagina;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Long getNumeroClaseNiza() {
        return numeroClaseNiza;
    }

    public void setNumeroClaseNiza(Long numeroClaseNiza) {
        this.numeroClaseNiza = numeroClaseNiza;
    }

    public SigSignoClaseNiza getSigSignoClaseNiza() {
        return sigSignoClaseNiza;
    }

    public void setSigSignoClaseNiza(SigSignoClaseNiza sigSignoClaseNiza) {
        this.sigSignoClaseNiza = sigSignoClaseNiza;
    }

    public ClaseNiza getClaseNiza() {
        return claseNiza;
    }

    public void setClaseNiza(ClaseNiza claseNiza) {
        this.claseNiza = claseNiza;
    }

    public List<ClaseNiza> getLstClaseNiza() {
        return lstClaseNiza;
    }

    public void setLstClaseNiza(List<ClaseNiza> lstClaseNiza) {
        this.lstClaseNiza = lstClaseNiza;
    }

    public String getValidacion() {
        return validacion;
    }

    public void setValidacion(String validacion) {
        this.validacion = validacion;
    }

    public ClaseNizaService getClaseNizaService() {
        return claseNizaService;
    }

    public void setClaseNizaService(ClaseNizaService claseNizaService) {
        this.claseNizaService = claseNizaService;
    }

    //</editor-fold>
    public void cambiaClase() {
        if (idclaseniza != null) {
            claseNiza2 = claseNizaService.listarClaseNiza_id(idclaseniza);
        }
    }

    public void cambiaListaClase() throws Exception {
        lstClaseNiza2 = claseNizaService.obtenerListadoClaseNizaVersion(version);
        claseNiza2 = new ClaseNiza();
    }

    public Long getIdclaseniza() {
        return idclaseniza;
    }

    public void setIdclaseniza(Long idclaseniza) {
        this.idclaseniza = idclaseniza;
    }

    public List<ClaseNiza> getLstClaseNiza2() {
        return lstClaseNiza2;
    }

    public void setLstClaseNiza2(List<ClaseNiza> lstClaseNiza2) {
        this.lstClaseNiza2 = lstClaseNiza2;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<String> getLstClaseNizaVersion() {
        return lstClaseNizaVersion = claseNizaService.lista_version_ClaseNiza();
    }

    public void setLstClaseNizaVersion(List<String> lstClaseNizaVersion) {
        this.lstClaseNizaVersion = lstClaseNizaVersion;
    }

    public ClaseNiza getClaseNiza2() {
        return claseNiza2;
    }

    public void setClaseNiza2(ClaseNiza claseNiza2) {
        this.claseNiza2 = claseNiza2;
    }

    public LogTransService getLogTransService() {
        return logTransService;
    }

    public void setLogTransService(LogTransService logTransService) {
        this.logTransService = logTransService;
    }

    public ComunService getComunService() {
        return comunService;
    }

    public void setComunService(ComunService comunService) {
        this.comunService = comunService;
    }

}
