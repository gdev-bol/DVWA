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
public enum EnumTipoTramite {

    RENOVACION("RENO"),
    REGISTRO_MARCAS("REGS"),
    RENOVACION_RESOLUCION("RERE"),
    NUMERO_RENOVACION("RENR"),
    LEMACOMERCIAL("RELC"),
    DEPOSITO_LEMA_COMERCIAL_ROTULO("RDNC"),
    RECURSO_JERARQUICO("RECJ"),
    RECURSO_REVOCATORIAS("RECR"),
    OPOSICION("OPOS"),
    ENTREGA_TRAMITE("ENTR"),
    MODIFICACION("MODI");
    
        
    private String codigo;

    private EnumTipoTramite(String codigo) {
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
