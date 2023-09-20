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
public enum EnumTipoTramiteRecibo {

    TRANSFERENCIA("ST"),
    TRANSFERENCIA_COCHABAMBA("ST-C"),
    TRANSFERENCIA_SANTA_CRUZ("ST-S"),
    TRANSFERENCIA_EL_ALTO("ST-E"),
    RENOVACION("SR"),
    RENOVACION_COCHABAMBA("SR-C"),
    RENOVACION_SANTA_CRUZ("SR-S"),
    RENOVACION_EL_ALTO("SR-E"),
    RENOVACION_ORURO("SR-O"),
    RENOVACION_TARIJA("SR-T"),
    RENOVACION_SUCRE("SR-H"),
    PATENTES("SP"),
    PATENTES_COCHABAMBA("SP-C"),
    PATENTES_SANTA_CRUZ("SP-S"),
    PATENTES_EL_ALTO("SP-E"),
    REGISTRO_MARCAS("SM"),
    REGISTRO_MARCAS_COCHABAMBA("SM-C"),
    REGISTRO_MARCAS_SANTA_CRUZ("SM-S"),
    REGISTRO_MARCAS_EL_ALTO("SM-E"),
    REGISTRO_MARCAS_ORURO("SM-O"),
    REGISTRO_MARCAS_SUCRE("SM-H"),
    REGISTRO_MARCAS_TARIJA("SM-T"),
    FUSION("SF"),
    FUSION_COCHABAMBA("SF-C"),
    FUSION_SANTA_CRUZ("SF-S"),
    FUSION_EL_ALTO("SF-E"),
    LICENCIA_USO("LU"),
    LICENCIA_USO_COCHABAMBA("LU-C"),
    LICENCIA_USO_SANTA_CRUZ("LU-S"),
    LICENCIA_USO_EL_ALTO("LU-E"),
    DENOMINACION_ORIGEN("DEN"),
    DENOMINACION_ORIGEN_COCHABAMBA("DEN-C"),
    DENOMINACION_ORIGEN_SANTA_CRUZ("DEN-S"),
    DENOMINACION_ORIGEN_EL_ALTO("DEN-E"),
    DERECHOS_AUTOR("DA"),
    DERECHOS_AUTOR_COCHABAMBA("DA-C"),
    DERECHOS_AUTOR_SANTA_CRUZ("DA-S"),
    DERECHOS_AUTOR_EL_ALTO("DA-E"),
    CAMBIO_NOMBRE("CN"),
    CAMBIO_NOMBRE_COCHABAMBA("CN-C"),
    CAMBIO_NOMBRE_SANTA_CRUZ("CN-S"),
    CAMBIO_NOMBRE_EL_ALTO("CN-E"),
    CAMBIO_DOMICILIO("CD"),
    CAMBIO_DOMICILIO_COCHABAMBA("CD-C"),
    CAMBIO_DOMICILIO_SANTA_CRUZ("CD-S"),
    CAMBIO_DOMICILIO_EL_ALTO("CD-E"),
//    Aumentado para oposicion
    OPOSICION("OP"),
    OPOSICION_EL_ALTO("OP-E"),
    OPOSICION_SANTA_CRUZ("OP-S"),
    OPOSICION_TARIJA("OP-T"),
    OPOSICION_COCHABAMABA("OP-C"),
    OPOSICION_ORURO("OP-O"),
    OPOSICION_CHIQUISACA("OP-H");
    
    

    private String codigo;

    private EnumTipoTramiteRecibo(String codigo) {
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
