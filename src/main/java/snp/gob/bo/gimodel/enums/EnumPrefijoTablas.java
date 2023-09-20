/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.enums;

/**
 *
 * @author Eddy Valero
 * @version 1.0 27/09/2016
 */
public enum EnumPrefijoTablas {

    SIGNO("SIG"),
    RENOVACION("REN"),
    MODIFICACION("MOD"),
    OPOSICION("OPO");
   
        
    private String codigo;

    private EnumPrefijoTablas(String codigo) {
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
