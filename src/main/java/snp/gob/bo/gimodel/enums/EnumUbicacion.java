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
public enum EnumUbicacion {

    DESCONOCIDO("DESC"),
    VENT("VENT"),
    PROPIEDAD_INDUSTRIAL("PIND"),
    SISTEMAS_ASIGNACION_NUMERO_PUBLICACION("SIPU"),
    COMUNICACION_PARA_PUBLICACION("COMU"),
    SISTEMAS_GACETA_PARA_PUBLICACION("SIGA"),
    PROPIEDAD_INDUSTRIAL_PERIODO_DEMANDA_OPOSICIONES("PIPE"),
    PROPIEDAD_INDUSTRIAL_OPOSICION("PIOP"),
    PROPIEDAD_INDUSTRIAL_ESPERA_SOLICITUD("PIES"),
    PROPIEDAD_INDUSTRIAL_ANALISIS_REGISTRABILIDAD("PIAR"),
    BIBLIOTECA("BIBL"),
    ARCHIVO_PROPIEDAD_INDUSTRIAL("API"),
    ARCHIVO_REGISTRADA_OTORGADA("ARO"),
    ARCHIVO_DENEGADA_PROPIEDAD_INDUSTRIAL("ADPI"),
    ARCHIVO_ABANDONADA("AA"),
    ARCHIVO_OPOSICIONES("AO"),
    COMUNICACIONES_ENVIADO_GACETA_PARA_PUBLICACION("COGA"),
    COMUNICACIONES_ESPERA_OPOSICION("COOP"),
    PROPIEDAD_INDUSTRIAL_DENEGADA("PIDE"),
    RECAUDACIONES("RECA")
    ;
    private String codigo;

    private EnumUbicacion(String codigo) {
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
