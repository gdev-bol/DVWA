/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.bean.login;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import snp.gob.bo.entidades.bean.common.SessionStateManagedBean;

/**
 *
 * @author levi
 */
@ManagedBean
@ViewScoped
public class SignosPrincipal {
    private String texto="Hola";
    @PostConstruct
    public void init() {
        System.out.println("Entra a signosprincipalbean");
  //  sessionState.setMuestraMenu(true);
    
    
    
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    

}
