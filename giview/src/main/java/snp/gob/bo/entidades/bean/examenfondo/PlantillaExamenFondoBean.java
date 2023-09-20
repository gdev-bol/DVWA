/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.bean.examenfondo;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author eddy
 */
@ManagedBean(name = "plantillaExamenFondoBean")
@ViewScoped
public class PlantillaExamenFondoBean {

    

    @PostConstruct
    public void initPlantillaBean() {

    }

    
    //Metodos de botones
    public String retornarPagina(){
     return "abrirExpediente";
    
    }
    

}
