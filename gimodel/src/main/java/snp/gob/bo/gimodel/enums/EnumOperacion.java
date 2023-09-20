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
public enum EnumOperacion {

    /**
     * Este enum a diferencia de los demas no cuenta con datos en el dominio es
     * un valor sacado directamente
     *
     */
    ADICIONAR("ADD"),
    MODIFICAR("MOD"),
    MODIFICACION("MODIFICACION"),
    CD_MODIFICACION("MODIFICACION DE REGISTRO CD"),
    CN_MODIFICACION("MODIFICACION DE REGISTRO CN"),
    LU_MODIFICACION("MODIFICACION DE REGISTRO LU"),
    SF_MODIFICACION("MODIFICACION DE REGISTRO SF"),
    ST_MODIFICACION("MODIFICACION DE REGISTRO ST"),
    PUBLICACION("PUB"),
    ELIMINAR("DEL"),
    RENOVACION("RENOVACION");
    

    private String codigo;

    private EnumOperacion(String codigo) {
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
