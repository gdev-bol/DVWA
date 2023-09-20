/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.bean.renovacion;

import java.io.Serializable;
import snp.gob.bo.entidades.bean.examenforma.*;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;

/**
 *
 * @author eddy
 */
@ManagedBean(name = "plantillaExamenRenovacionBean")
@ViewScoped
public class PlantillaExamenRenovacionBean extends AbstractManagedBean implements Serializable{

    

    @PostConstruct
    public void initPlantillaBean() {

    }

    
    //Metodos de botones
    public String retornarPagina(){
     return "abrirExpediente";
    
    }
    
    public String abrirPaginaHistorial() {
        this.setNavegaPagina("examenRenovacion");
        return ("abrirPlantillaHistorial");
    }

    public String abrirSeguimiento() {
        this.setNavegaPagina("examenRenovacion");
        return ("abrirSeguimiento");
    }

}
