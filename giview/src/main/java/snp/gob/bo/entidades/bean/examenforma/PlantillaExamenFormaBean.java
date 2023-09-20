/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.bean.examenforma;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author eddy
 */
@ManagedBean(name = "plantillaExamenFormaBean")
@ViewScoped
public class PlantillaExamenFormaBean {

    

    @PostConstruct
    public void initPlantillaBean() {

    }

    
    //Metodos de botones
    public String retornarPagina(){
        System.out.println("examen renovacion");
     return "examenRenovacion";
    
    }
    

}
