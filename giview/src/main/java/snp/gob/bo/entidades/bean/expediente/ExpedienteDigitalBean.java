/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.entidades.bean.expediente;

import java.io.InputStream;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author eddy
 */

@ManagedBean(name = "expedienteDigitalBean")
@ViewScoped
public class ExpedienteDigitalBean {
    
    
    private StreamedContent file;
    
    @PostConstruct
    public void initPlantillaBean() {

    }
    
    
     
    public ExpedienteDigitalBean() {        
        InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/images/caratulanot.jpg");
        file = new DefaultStreamedContent(stream, "image/jpg", "descarga.jpg");
    }
 
    public StreamedContent getFile() {
        return file;
    }
    
    
}
