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
public enum EnumTipoGenero {

    DENOMINACION_ORIGEN("DO"),
    LEMA_COMERCIAL("LC"),
    MARCA_COLECTIVA("MC"),
    MARCA_CERTIFICACION("MCE"),
    MARCA_PRODUCTO("MP"),
    MARCA_SERVICIO("MS"),
    NOMBRE_COMERCIAL("NC"),
    ROTULO_COMERCIAL("RC"),
    ENSENIA_COMERCIAL("EC"),
    MARCA_FABRICA("MF");
        
    private String codigo;

    private EnumTipoGenero(String codigo) {
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
