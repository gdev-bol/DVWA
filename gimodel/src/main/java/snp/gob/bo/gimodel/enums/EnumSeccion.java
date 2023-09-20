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
public enum EnumSeccion {

    /**
     * Este enum a diferencia de los demas no cuenta con datos en el dominio es un
     * valor sacado directamente
     */
    
    APODERADO("APODERADO"),
    COD_VIENA("COD VIENNA"),
    INTERES_OPOSICION_ANDINA("INTERES OP ANDINA"),
    PRIORIDAD("PRIORIDAD"),
    PRIORIDAD_EXPOSICION("PRIORIDAD EXPOSICION"),
    PRIORIDAD_EXTRANJERA("PRIORIDAD EXTRANJERA"),
    PUBLICACION("PUBLICACION"),
    IMAGEN("IMAGEN"),
    REGISTRO("REGISTRO"),
    SIGNO("SIGNO"),
    SOLICITANTE("SOLICITANTE"),
    STATUS("STATUS"),
    DIRECCION_DE_NOTIFICACION("DIRECCION DE NOTIFICACION"),
    REFERENCIA_SOLICITUD("REFERENCIA SOLICITUD"),
    DATOS_SIGNO_REGISTRADO("SIGNO REGISTRADO"),
    DATOS_ADMINISTRATIVOS("DATOS ADMINISTRATIVOS"),
    DATOS_NUEVO_TITULAR("DATOS NUEVO TITULAR");
        
    private String codigo;

    private EnumSeccion(String codigo) {
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
