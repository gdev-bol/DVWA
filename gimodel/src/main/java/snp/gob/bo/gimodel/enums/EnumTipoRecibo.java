/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.enums;

/**
 *
 * @author Jano Roajs
 * @version 1.0 01/09/2016
 */
public enum EnumTipoRecibo {

    RECIBO("RECI"),
    TITULO("TITU");

    private String codigo;

    private EnumTipoRecibo(String codigo) {
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
