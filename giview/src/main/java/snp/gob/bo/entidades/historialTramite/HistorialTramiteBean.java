/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.historialTramite;
 
import java.io.Serializable;
import java.util.ArrayList;
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
import snp.gob.bo.gimodel.entidad.HistorialResultado;
import snp.gob.bo.gimodel.servicio.HistorialResultadoService;
 
/**
 *
 * @author Eddy Valero
 * @version 1.0, 19/11/2016
 */
@ManagedBean(name = "historialTramiteBean")
@ViewScoped
public class HistorialTramiteBean extends AbstractManagedBean implements Serializable {
 
    //<editor-fold defaultstate="collapsed" desc="Definicion de servicios y atributos">
    public List<HistorialResultado> listaHistorial = new ArrayList<HistorialResultado>();
   
    @ManagedProperty(value = "#{historialResultadoService}")
    private HistorialResultadoService historialResultadoService;
 
    //</editor-fold>
 
    //<editor-fold defaultstate="collapsed" desc="Metodos del Bean">
    /**
     * Metodo inicializar el Bean
     *
     * Creado: Eddy Valero Fecha:20/09/2016
     */
    @PostConstruct
    public void iniciarBeanHistorial() {
 
        try {
            //obtener datos enviados desde la vista
            Map<String, String[]> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterValuesMap();
 
            //cargar datos de acuerdo al id solicitado
            Long id = null;
            String prefijo = null;
            //obtener el idSignoMarca
            id = Long.parseLong(params.get("datosEnviados")[0]);
            //obtener la tabla a la cual se debe realizar la consulta
            prefijo = params.get("datosEnviados")[1];
 
            listaHistorial = historialResultadoService.obtenerListaHistorialCompletoXId(id, prefijo);
 
        } catch (Exception ex) {
            Logger.getLogger(HistorialTramiteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }
   
    /**
     * Metodo cerrar el popup de mensaje
     *
     * Creado: Eddy Valero Fecha:21/11/2016
     * @return
     */
    public String accionCerrar() {
        RequestContext.getCurrentInstance().closeDialog("Exit");
        return "";
    }
 
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="getters y setters">
    public List<HistorialResultado> getListaHistorial() {
        return listaHistorial;
    }
 
    public void setListaHistorial(List<HistorialResultado> listaHistorial) {
        this.listaHistorial = listaHistorial;
    }
 
    public HistorialResultadoService getHistorialResultadoService() {
        return historialResultadoService;
    }
 
    public void setHistorialResultadoService(HistorialResultadoService historialResultadoService) {
        this.historialResultadoService = historialResultadoService;
    }
 
    //</editor-fold>
   
   
   
}
