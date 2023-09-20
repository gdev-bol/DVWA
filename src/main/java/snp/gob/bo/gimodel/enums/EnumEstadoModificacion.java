/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.enums;

/**
 *
 * @author susana
 */
public enum EnumEstadoModificacion {

    INGRESADA("INGR"),
    ACEPTADA("ACEP"),
    RETIRADA("RETI"),
    RECHAZADA("RECH"),
    OBSERVADA("OBSE"),
    DESISTIDA("DESI"),
    SUSPENDIDA("SUSP"),
    ARCHIVO_OBRADOS("ARCH");

    private String codigo;

    private EnumEstadoModificacion(String codigo) {
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
