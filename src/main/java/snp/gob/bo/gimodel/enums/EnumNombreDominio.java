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
public enum EnumNombreDominio {

    SERIE_RECIBO("serie_recibo"),
    SERIE_TITULO("serie_titulo"),
    SERIE_REGISTRO("serie_registro"),
    TIPO_SIGNO("tipo_signo"),
    TIPO_GENERO("tipo_genero"),
    ESTADO_RENOVACION("estado_renovacion"),
    CIUDAD_NOTIFICACION("ciudad_notificacion"),
    LUGAR_EXPEDICION("lugar_expedicion"),
    PAIS("pais"),
    GENERO_MAS_FEM("genero"),
    TIPO_DOCUMENTO("tipo_documento"),
    TIPO_TITULAR("tipo_titular"),
    TIPO_PERSONA("tipo_persona"),
    SERIE_RENOVACION("serie_renovacion"),
    ESTADO_MODIFICACION("estado_modificacion"),
    ESTADO_PUBLICACION("estado_publicacion"),
    TIPO_MODIFICACION("tipo_modificacion"),
    ESTADO_MARCA("estado_marca"),
    UBICACION("ubicacion")
    ;
    
        
    private String codigo;

    private EnumNombreDominio(String codigo) {
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
