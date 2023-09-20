/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.bean.modificacion;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;

/**
 *
 * @author susana
 */

@ManagedBean(name = "plantillaExamenModificacionBean")
@ViewScoped
public class PlantillaExamenModificacionBean extends AbstractManagedBean implements Serializable{

    /**
     * Creates a new instance of PlantillaExamenModificacionBean
     */
    public PlantillaExamenModificacionBean() {
    }
    
    
     @PostConstruct
    public void PlantillaExamenModificacionBeanInit() {
         System.out.println("plantilla de examen modificacion  ");
         
    }
    
    public String cerrarDialogo() {
       
            RequestContext.getCurrentInstance().closeDialog("Exit");
            return "";
    }
}
