/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.observacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.gimodel.entidad.Etapa;
import snp.gob.bo.gimodel.entidad.LogTrans;
import snp.gob.bo.gimodel.entidad.ObservacionTramite;
import snp.gob.bo.gimodel.entidad.SigObservacionTramite;
import snp.gob.bo.gimodel.enums.EnumEstado;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.EtapaService;
import snp.gob.bo.gimodel.servicio.LogTransService;
import snp.gob.bo.gimodel.servicio.ObservacionTramiteService;
import snp.gob.bo.gimodel.servicio.impl.ComunServiceImpl;

/**
 *
 * @author eddy
 */
@ManagedBean(name = "observacionTramiteBean")
@ViewScoped
public class ObservacionTramiteBean extends AbstractManagedBean implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Definicion de servicios y atributos">
    private String template;
    public List<ObservacionTramite> listaObservacion = new ArrayList<ObservacionTramite>();
    private String observacion;
    private Boolean editable = Boolean.TRUE;
    private String areaRegistro;
    private Long idarea;
    private Date fechaServidor;
    private ObservacionTramite observacionTramite = new ObservacionTramite();
    private Long idEtapa;
    private Long idUsuario;

    @ManagedProperty(value = "#{observacionTramiteService}")
    private ObservacionTramiteService observacionTramiteService;

    @ManagedProperty(value = "#{comunService}")
    private ComunService comunService;

    @ManagedProperty(value = "#{dominioService}")
    private DominioService dominioService;

    @ManagedProperty(value = "#{etapaService}")
    private EtapaService etapaService;
    @ManagedProperty("#{logTransService}")
    private LogTransService logTransService;

    //</editor-fold>
    @PostConstruct
    public void iniciarBeanObservacionTramite() {

        try {
            idEtapa = super.getIdEtapaSession();
            idUsuario = super.getIdUsuarioSession();
            Map<String, String[]> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterValuesMap();
            //   Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            // System.out.println("ID +  RECIBIDOOOOOO  " + params.get("datosGenerales")[0]);
//            if (params.get("datosGenerales") == null || params.get("datosGenerales").equals("")) {
//                template = "./../WEB-INF/facelets/templates/Template.xhtml";
//
//            } else {
//                template = "./../WEB-INF/facelets/templates/DialogTemplate.xhtml";
//            }
            if (params.get("datosGenerales")[0] != null) {
                template = "./../WEB-INF/facelets/templates/DialogTemplate.xhtml";
            } else {

                template = "./../WEB-INF/facelets/templates/Template.xhtml";
                // System.out.println("############################       P A G I N A");

            }

            //System.out.println(" prefijo   " + params.get("datosGenerales")[1] + "   id   " + params.get("datosGenerales")[2]);
            areaRegistro = params.get("datosGenerales")[1];
            if (params.get("datosGenerales")[2] != null) {
                idarea = Long.parseLong(params.get("datosGenerales")[2]);
                listaObservacion = observacionTramiteService.obtListadoObservacionPorTramite(areaRegistro, idarea);
            }

        } catch (Exception ex) {
            Logger.getLogger(ObservacionTramiteBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //<editor-fold defaultstate="collapsed" desc="Definicion de metodos">
    //Metodos
    public Boolean preguntaUltimo(Long idobservacion) {
        return listaObservacion.get(listaObservacion.size() - 1).getIdObservacionTramite().equals(idobservacion);
    }

    public void guardarObservacion() {
        if (!this.observacion.equals("")) {
            try {
             //   observacionTramiteService.regObservacion(areaRegistro, 1L, 1L, 1L, Boolean.FALSE, "etapa", observacion);
                //refrescar el listado de las observaciones

                //listaObservacion = observacionTramiteService.obtListadoObservacionPorTramite(areaRegistro, 1L);
                //limpiar la variable de observacion               
                fechaServidor = comunService.obtenerFechaHoraServidor(1L);
                LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idUsuario, fechaServidor), 1);
                observacionTramite.setId(idarea);
                observacionTramite.setIdUsuario(idUsuario);
                observacionTramite.setIdLogTrans(logTransGuardado.getIdLogTrans());
                observacionTramite.setEditable(true);
                observacionTramite.setFechaObservacion(fechaServidor);
                Etapa etapa = etapaService.etapaXIdEtapa(idEtapa);
                observacionTramite.setEtapaTramite(etapa.getTipoEtapa());
                observacionTramite.setDescripcion(observacion);
                observacionTramite.setEstado("AC");

                if (observacionTramite.getIdObservacionTramite() != null) {

                }

                observacionTramiteService.guardar_modificar_listar_ObservacionTramite(observacionTramite, areaRegistro, 1);
                listaObservacion = observacionTramiteService.obtListadoObservacionPorTramite(areaRegistro, idarea);
                observacion = "";
                observacionTramite = new ObservacionTramite();

            } catch (Exception ex) {
                Logger.getLogger(ObservacionTramiteBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void eliminarItemObservacion(ObservacionTramite observacionTramite) {
        try {
            // observacionTramiteService.eliminarObservacion(areaRegistro, idObservacionTramite);
            observacionTramite.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
            observacionTramiteService.guardar_modificar_listar_ObservacionTramite(observacionTramite, areaRegistro, 2);
            listaObservacion = observacionTramiteService.obtListadoObservacionPorTramite(areaRegistro, idarea);
            observacion = "";

            int posicion = 0;
//            for (ObservacionTramite sIGObservacionTramite : listaObservacion) {
//                if (sIGObservacionTramite.getIdObservacionTramite().equals(idObservacionTramite)) {
//                    break;
//                }
//                posicion++;
//            }
            //listaObservacion.remove(posicion);
            /**
             * ********************************************
             */
        } catch (Exception ex) {
            Logger.getLogger(ObservacionTramiteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void habilitarModificarItemObservacion(SigObservacionTramite siGObservacionTramite) {
        //System.out.println(" *** " + siGObservacionTramite.getIdObservacionTramite());

        if (validarObservacionTramite(siGObservacionTramite.getIdObservacionTramite())) {

            siGObservacionTramite.setEditable(Boolean.TRUE);
        }

    }

    public Boolean validarObservacionTramite(Long idObservacionTramite) {
        return Boolean.TRUE;
    }

    public void modificarObservacion(ObservacionTramite observacionTramite) {
        //observacion = observacionTramite.getDescripcion();
        //Metodo que realiza la actualizacion
        //observacionTramite.setEditable(Boolean.FALSE);
        observacionTramiteService.guardar_modificar_listar_ObservacionTramite(observacionTramite, areaRegistro, 2);
        listaObservacion = observacionTramiteService.obtListadoObservacionPorTramite(areaRegistro, idarea);
        observacion = "";

        try {
            //observacionTramiteService.actualizarObservacion(areaRegistro,siGObservacionTramite.getIdObservacionTramite(), siGObservacionTramite.getDescripcion());

        } catch (Exception ex) {
            Logger.getLogger(ObservacionTramiteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void limpiarObservacion() {

        this.observacion = "";
    }

    //
    public String retornarPagina() {
//        System.out.println("ingresar metodo *** " + getNavegaPagina());
//        return getNavegaPagina();
        RequestContext.getCurrentInstance().closeDialog("Exit");
        return "";
    }

    public String devuelveEtapa(String codigo) throws Exception {

        if (codigo != null && !codigo.equals("")) {
            Etapa etapa = etapaService.listar_etapa_tipoTramite_tipoEtapa(areaRegistro, codigo);
            return etapa.getDescripcion();
            //return codigo;
        }
        return "";
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getters y Setters">
    //Atributos servicios
    public ObservacionTramiteService getObservacionTramiteService() {
        return observacionTramiteService;
    }

    public void setObservacionTramiteService(ObservacionTramiteService observacionTramiteService) {
        this.observacionTramiteService = observacionTramiteService;
    }

    //Atributos 
    public List<ObservacionTramite> getListaObservacion() {
        return listaObservacion;
    }

    public void setListaObservacion(List<ObservacionTramite> listaObservacion) {
        this.listaObservacion = listaObservacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public String getAreaRegistro() {
        return areaRegistro;
    }

    public void setAreaRegistro(String areaRegistro) {
        this.areaRegistro = areaRegistro;
    }

    //</editor-fold>
    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public ComunService getComunService() {
        return comunService;
    }

    public void setComunService(ComunService comunService) {
        this.comunService = comunService;
    }

    public Date getFechaServidor() {
        return fechaServidor;
    }

    public void setFechaServidor(Date fechaServidor) {
        this.fechaServidor = fechaServidor;
    }

    public DominioService getDominioService() {
        return dominioService;
    }

    public void setDominioService(DominioService dominioService) {
        this.dominioService = dominioService;
    }

    public EtapaService getEtapaService() {
        return etapaService;
    }

    public void setEtapaService(EtapaService etapaService) {
        this.etapaService = etapaService;
    }

    public LogTransService getLogTransService() {
        return logTransService;
    }

    public void setLogTransService(LogTransService logTransService) {
        this.logTransService = logTransService;
    }
}
