package snp.gob.bo.giview.ventanilla;

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
import snp.gob.bo.gimodel.entidad.ElementoFormularioTramite;
import snp.gob.bo.gimodel.servicio.ElementoFormularioTramiteService;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 06/09/2016
 */
@ManagedBean(name = "preplantillaIngresoSolicitudBean")
@ViewScoped
public class PreplantillaIngresoSolicitudBean {

    private String template;

    private List<ElementoFormularioTramite> plantillaVentanilla = new ArrayList<ElementoFormularioTramite>();

    @ManagedProperty(value = "#{elementoFormularioTramiteService}")
    private ElementoFormularioTramiteService elementoFormularioTramiteService;

    @PostConstruct
    public void init() {

        Map<String, String[]> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterValuesMap();

        if (params.get("datosGenerales") == null || params.get("datosGenerales").equals("")) {
            template = "./../WEB-INF/facelets/templates/Template.xhtml";
        } else {
            template = "./../WEB-INF/facelets/templates/DialogTemplate.xhtml";
        }
        
        //generar los registros de una plantilla.
        try {
            //se debe discriminar la plantilla de acuerdo a si la plantilla enviada es SM, REN o MOD
            plantillaVentanilla = elementoFormularioTramiteService.obtPlantillaVentanilla(params.get("datosGenerales")[0]);
            
        } catch (Exception ex) {
            Logger.getLogger(PreplantillaIngresoSolicitudBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void accionCerrarDialogo() {
        RequestContext.getCurrentInstance().closeDialog(plantillaVentanilla);
    }
    
    public void accionCancelarDialogo() {
        RequestContext.getCurrentInstance().closeDialog("Exit");
    }
    
    
    //<editor-fold defaultstate="collapsed" desc="Getter y setters del Bean">
    //atributos
    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    //servicios
    public ElementoFormularioTramiteService getElementoFormularioTramiteService() {
        return elementoFormularioTramiteService;
    }

    public void setElementoFormularioTramiteService(ElementoFormularioTramiteService elementoFormularioTramiteService) {
        this.elementoFormularioTramiteService = elementoFormularioTramiteService;
    }

    public List<ElementoFormularioTramite> getPlantillaVentanilla() {
        return plantillaVentanilla;
    }

    public void setPlantillaVentanilla(List<ElementoFormularioTramite> plantillaVentanilla) {
        this.plantillaVentanilla = plantillaVentanilla;
    }

    //</editor-fold>
}
