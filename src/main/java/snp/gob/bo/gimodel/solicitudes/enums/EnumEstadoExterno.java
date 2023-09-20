/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.solicitudes.enums;


/**
 *
 * @author Eddy Hernan Valero Kari
 * @version 1.0 28/09/2016
 */
public enum EnumEstadoExterno {

    ACTIVO("AC"),
    NO_ACTIVO("NA"); 
        
    private String codigo;

    private EnumEstadoExterno(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
