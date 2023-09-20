/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.enums;

/**
 *
 * @author Eddy Valero
 * @version 1.0 21/05/2017
 */
public enum EnumTipoTramiteUsuario {

    SIGNOS("SIG"),
    RENOVACION("REN"),
    OPOSICION("OPO"),
    MOD("MOD");
    
        
    private String codigo;

    private EnumTipoTramiteUsuario(String codigo) {
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
