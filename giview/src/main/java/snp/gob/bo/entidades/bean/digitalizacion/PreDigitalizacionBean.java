/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.bean.digitalizacion;

import snp.gob.bo.entidades.bean.examenforma.*;
import snp.gob.bo.giview.ventanilla.*;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

/**
 *
 * @author eddy
 */
@ManagedBean(name = "preDigitalizacionBean")
@ViewScoped
public class PreDigitalizacionBean {


    @PostConstruct
    public void initPreDigitalizacionBean() {

    }

    
    public String ingresarDigitalizacion(){
        System.out.println("abrir digitalizacion");
        return "abrirDigitalizacion";
    }

}
