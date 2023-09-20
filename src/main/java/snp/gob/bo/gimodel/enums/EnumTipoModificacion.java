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
public enum EnumTipoModificacion {
    CAMBIO_NOMBRE("CANO"),
    CAMBIO_DOMICILIO("CADO"),
    TRANSFERENCIA("CATR"),
    FUSION("CAFU"),
    LICENCIA_DE_USO("CALU");
    
    private String codigo;

    private EnumTipoModificacion(String codigo) {
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
