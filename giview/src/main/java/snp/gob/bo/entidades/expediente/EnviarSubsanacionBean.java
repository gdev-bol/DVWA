/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.expediente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.gimodel.entidad.Etapa;
import snp.gob.bo.gimodel.entidad.ObservacionTramite;
import snp.gob.bo.gimodel.entidad.ObservacionTramiteSig;
import snp.gob.bo.gimodel.entidad.SigObservacionTramite;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.EtapaService;
import snp.gob.bo.gimodel.servicio.ObservacionTramiteService;
import snp.gob.bo.gimodel.servicio.impl.ComunServiceImpl;
import snp.gob.bo.gimodel.solicitudes.servicio.FormularioPI100Service;
import snp.gob.bo.gimodel.solicitudes.servicio.FormularioService;

/**
 *
 * @author eddy
 */
@ManagedBean(name = "enviarSubsanacionBean")
@ViewScoped
public class EnviarSubsanacionBean extends AbstractManagedBean implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Definicion de servicios y atributos">
    private String template;
    public List<ObservacionTramite> listaObservacion = new ArrayList<ObservacionTramite>();
    public List<ObservacionTramiteSig> listaObservacionSig = new ArrayList<ObservacionTramiteSig>();
    private String observacion;
    private Boolean editable = Boolean.TRUE;
    private String areaRegistro;
    private String numeroFormulario;
    private Date fechaServidor;
    private String[] opcionSecciones = {"SIG", "SOL", "APO", "DIR", "PRI"};
    private ObservacionTramite observacionTramite = new ObservacionTramite();
    private Long idEtapa;
    private List<SelectItem> cars;
    private String[] selectedCars;
    private String idFormularioExterno;
    @ManagedProperty(value = "#{observacionTramiteService}")
    private ObservacionTramiteService observacionTramiteService;

    @ManagedProperty(value = "#{comunService}")
    private ComunService comunService;

    @ManagedProperty(value = "#{dominioService}")
    private DominioService dominioService;

    @ManagedProperty(value = "#{etapaService}")
    private EtapaService etapaService;

    @ManagedProperty(value = "#{formularioPI100Service}")
    private FormularioPI100Service formularioPI100Service;
    
    @ManagedProperty(value = "#{formularioService}")
    private FormularioService formularioService;
    
    //@Autowired
    //private FormularioService formularioService;    
    
    //</editor-fold>
    @PostConstruct
    public void iniciarBeanObservacionTramite() {

        try {
            HashMap mapResultado = new HashMap();
            idEtapa = super.getIdEtapaSession();
            Map<String, String[]> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterValuesMap();

            if (params.get("datosGenerales")[0] != null) {
                template = "./../WEB-INF/facelets/templates/DialogTemplate.xhtml";
            } else {
                template = "./../WEB-INF/facelets/templates/Template.xhtml";
            }

            //System.out.println(" prefijo   " + params.get("datosGenerales")[1] + "   id   " + params.get("datosGenerales")[2]);
            areaRegistro = params.get("datosGenerales")[1];
            if (params.get("datosGenerales")[2] != null) {
                numeroFormulario = params.get("datosGenerales")[2];
                //listaObservacion = observacionTramiteService.obtListadoObservacionPorTramite(areaRegistro, idarea);
                //listaObservacionSig = observacionTramiteService.obtListadoObservacionPorTramite2(areaRegistro, idarea);
            }
            mapResultado = this.formularioPI100Service.obtenerNumeroFormularioSubsanacion(numeroFormulario);
            this.idFormularioExterno = mapResultado.get("idFormulario").toString();
            
            cars = new ArrayList<SelectItem>();
            SelectItemGroup germanCars = new SelectItemGroup("Seleccione secciones");
            germanCars.setSelectItems(new SelectItem[]{
                new SelectItem("SIG", "Datos del signo distintivo"),
                new SelectItem("SOL", "Datos del solicitante o titular"),
                new SelectItem("APO", "Datos del apoderado o representante legal"),
                new SelectItem("DIR", "Dirección de Notificación"),
                new SelectItem("PRI", "Prioridades")
            });

            cars.add(germanCars);
            

        } catch (Exception ex) {
            Logger.getLogger(EnviarSubsanacionBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //<editor-fold defaultstate="collapsed" desc="Definicion de metodos">
    //Metodos
    public Boolean preguntaUltimo(Long idobservacion) {
        return listaObservacion.get(listaObservacion.size() - 1).getIdObservacionTramite().equals(idobservacion);
    }

    public void habilitarSubsanacion() {
        //if (!this.observacion.equals("")) {
        try {
             //   observacionTramiteService.regObservacion(areaRegistro, 1L, 1L, 1L, Boolean.FALSE, "etapa", observacion);
            //refrescar el listado de las observaciones

            //listaObservacion = observacionTramiteService.obtListadoObservacionPorTramite(areaRegistro, 1L);
            //limpiar la variable de observacion
            String subsanacion = "";
            fechaServidor = comunService.obtenerFechaHoraServidor(1L);
            int sw;
            for (String op : opcionSecciones) {
                sw = 0;
                for (String sel : selectedCars) {
                    if (op.equals(sel)) {
                        sw = 1;
                        break;
                    }
                }
                if (sw == 1) {
                    subsanacion += "1";
                } else {
                    subsanacion += "0";
                }
            }

            System.out.println("Las secciones para subsanacion son: " + subsanacion);
            System.out.println("ID formulario es: " + this.idFormularioExterno);
            //Actualizar el estado del formulario
            formularioService.habilitarFormularioSubsanacion(Long.parseLong(this.idFormularioExterno), subsanacion);

        } catch (Exception ex) {
            Logger.getLogger(EnviarSubsanacionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        //}

    }

    public void guardarObservacion() {
        if (!this.observacion.equals("")) {
            try {
             //   observacionTramiteService.regObservacion(areaRegistro, 1L, 1L, 1L, Boolean.FALSE, "etapa", observacion);
                //refrescar el listado de las observaciones

                //listaObservacion = observacionTramiteService.obtListadoObservacionPorTramite(areaRegistro, 1L);
                //limpiar la variable de observacion
                fechaServidor = comunService.obtenerFechaHoraServidor(1L);

                //observacionTramite.setId(idarea);
                observacionTramite.setIdUsuario(1l);
                observacionTramite.setIdLogTrans(1l);
                observacionTramite.setEditable(true);
                observacionTramite.setFechaObservacion(fechaServidor);
                Etapa etapa = etapaService.etapaXIdEtapa(idEtapa);
                observacionTramite.setEtapaTramite(etapa.getTipoEtapa());
                observacionTramite.setDescripcion(observacion);
                observacionTramite.setEstado("AC");

                if (observacionTramite.getIdObservacionTramite() != null) {

                }

                observacionTramiteService.guardar_modificar_listar_ObservacionTramite(observacionTramite, areaRegistro, 1);
                //listaObservacion = observacionTramiteService.obtListadoObservacionPorTramite(areaRegistro, idarea);
                observacion = "";
                observacionTramite = new ObservacionTramite();

            } catch (Exception ex) {
                Logger.getLogger(EnviarSubsanacionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void eliminarItemObservacion(ObservacionTramite observacionTramite) {
        try {
            // observacionTramiteService.eliminarObservacion(areaRegistro, idObservacionTramite);
            observacionTramite.setEstado("NA");
            observacionTramiteService.guardar_modificar_listar_ObservacionTramite(observacionTramite, areaRegistro, 2);
            //listaObservacion = observacionTramiteService.obtListadoObservacionPorTramite(areaRegistro, idarea);
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
            Logger.getLogger(EnviarSubsanacionBean.class.getName()).log(Level.SEVERE, null, ex);
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
        //listaObservacion = observacionTramiteService.obtListadoObservacionPorTramite(areaRegistro, idarea);
        observacion = "";

        try {
            //observacionTramiteService.actualizarObservacion(areaRegistro,siGObservacionTramite.getIdObservacionTramite(), siGObservacionTramite.getDescripcion());

        } catch (Exception ex) {
            Logger.getLogger(EnviarSubsanacionBean.class.getName()).log(Level.SEVERE, null, ex);
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

    public List<ObservacionTramiteSig> getListaObservacionSig() {
        return listaObservacionSig;
    }

    public void setListaObservacionSig(List<ObservacionTramiteSig> listaObservacionSig) {
        this.listaObservacionSig = listaObservacionSig;
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

    public FormularioPI100Service getFormularioPI100Service() {
        return formularioPI100Service;
    }

    public void setFormularioPI100Service(FormularioPI100Service formularioPI100Service) {
        this.formularioPI100Service = formularioPI100Service;
    }
    
    public FormularioService getFormularioService() {
		return formularioService;
	}

	public void setFormularioService(FormularioService formularioService) {
		this.formularioService = formularioService;
	}

	public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public List<SelectItem> getCars() {
        return cars;
    }

    public void setCars(List<SelectItem> cars) {
        this.cars = cars;
    }

    public String[] getSelectedCars() {
        return selectedCars;
    }

    public void setSelectedCars(String[] selectedCars) {
        this.selectedCars = selectedCars;
    }

    public String getNumeroFormulario() {
        return numeroFormulario;
    }

    public void setNumeroFormulario(String numeroFormulario) {
        this.numeroFormulario = numeroFormulario;
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

}
