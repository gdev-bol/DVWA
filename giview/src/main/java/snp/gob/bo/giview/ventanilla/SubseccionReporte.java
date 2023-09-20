/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.giview.ventanilla;

/**
 *
 * @author Ruben Nico Ramirez
 * 
 * @version 1.0, 07/11/2016
 */
public class SubseccionReporte {
    
    private String campo1;
    private String campo2;
    private String campo3;
    private Boolean fojas;
    
    public SubseccionReporte(){
        
    }

    public SubseccionReporte(String campo1, String campo2, String campo3, Boolean fojas) {
        this.campo1 = campo1;
        this.campo2 = campo2;
        this.campo3 = campo3;
        this.fojas = fojas;
    }

    public String getCampo1() {
        return campo1;
    }

    public void setCampo1(String campo1) {
        this.campo1 = campo1;
    }

    public String getCampo2() {
        return campo2;
    }

    public void setCampo2(String campo2) {
        this.campo2 = campo2;
    }

    public String getCampo3() {
        return campo3;
    }

    public void setCampo3(String campo3) {
        this.campo3 = campo3;
    }

    public Boolean getFojas() {
        return fojas;
    }

    public void setFojas(Boolean fojas) {
        this.fojas = fojas;
    }
}
