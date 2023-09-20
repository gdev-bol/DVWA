/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.enums;

/**
 *
 * @author Eddy Valero
 * @version 1.0 09/10/2016
 */
public enum EnumCarpetasTemporales {

    CARPETA_LOGOTIPOS("logotipos"),
    CARPETA_STICKERS("stickeres"),
    CARPETA_IMAGENES("temp"),
    CARPETA_QR("qrs"),
    CARPETA_ARCHIVOS("tempFile");
        
    private String codigo;

    private EnumCarpetasTemporales(String codigo) {
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
