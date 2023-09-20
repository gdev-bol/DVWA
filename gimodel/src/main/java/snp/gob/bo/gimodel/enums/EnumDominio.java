/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.enums;

/**
 *
 * @author Chano Rojas
 * @version 1.0 01/09/2016
 */
public enum EnumDominio {

    TIPO_TRAMITE_RECIBO("tipo_tramite_recibo"),
    ESTADO_MARCA("estado_marca"),
    ESTADO_MODIFICACION("estado_modificacion"),
    ESTADO_RENOVACION("estado_renovacion"),
    ESTADO_OPOSICION("estado_oposicion"),
    SUCURSAL_BANCARIA("sucursal_bancaria_recibos"),
    UBICACION("ubicacion");
    
    
        
    private String codigo;

    private EnumDominio(String codigo) {
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
