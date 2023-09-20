/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.enums;

/**
 *
 * @author Eddy Valero
 * @version 1.0 21/10/2016
 */
public enum EnumTipoPrioridad {

    OPOSICION_ANDINA("OA"),
    PRIORIDAD("PRI"),
    PREFERENCIA("PRE");
        
    private String codigo;

    private EnumTipoPrioridad(String codigo) {
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
