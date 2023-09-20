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
public enum EnumFormularioIngreso {

    PI100("FORM-PI100"),
    PI101("FORM-PI101"),
    PI102("FORM-PI102"),
    PI103("FORM-PI103"),
    PI104("FORM-PI104"),
    PI105("FORM-PI105");
        
    private String codigo;

    private EnumFormularioIngreso(String codigo) {
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
