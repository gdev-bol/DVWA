package snp.gob.bo.gimodel.enums;

/**
 *
 * @author Eddy Valero
 * @version 1.0 24/10/2016
 */
public enum EnumTipoFormulario {

    REGISTRO_SIGNO_DISTINTIVO("PI100"),
    REGISTRO_NOMBRE_COMERCIAL("PI101"),
    REGISTRO_LEMA_COMERCIAL("PI102"),
    REGISTRO_MODIFICACION("PI103"),
    REGISTRO_RENOVACION("PI104"),
    REGISTRO_OPOSICION("PI105");
        
    private String codigo;

    private EnumTipoFormulario(String codigo) {
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
