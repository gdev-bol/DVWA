    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.enums;

/**
 *
 * @author chanoRojas
 */
public enum EnumEstadoRenovacion {

    SOLICITADO("SOLI"),
    OBSERVADO_ARCHIVO("OBSA"),
    OBSERVADO("OBSE"),
    ESPERA_MODIFICACION("EMOD"),
    RECHAZADO("RECH"),
    CADUCADO("CADU"),
    CONCEDIDO("CONC"),
    RETIRADO_DESISTIDO("REDE"),
    DESCONOCIDO("DESC"),
    EN_NOTIFICACION("NOTR");
    
   
    

    private String codigo;

    private EnumEstadoRenovacion(String codigo) {
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
