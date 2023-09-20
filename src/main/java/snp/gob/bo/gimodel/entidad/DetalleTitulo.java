/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.entidad;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Entidad responsable de la Tabla regional
 *
 * @author Chano Rojas
 * @see DetalleTituloMapper
 * @version 1.0, 11/08/2016
 */
public class DetalleTitulo {

   
    
  Long iddetalletitulo;
  Long idrecibo;
  Long idlogtrans;

    public Long getIddetalletitulo() {
        return iddetalletitulo;
    }

    public void setIddetalletitulo(Long iddetalletitulo) {
        this.iddetalletitulo = iddetalletitulo;
    }

    public Long getIdrecibo() {
        return idrecibo;
    }

    public void setIdrecibo(Long idrecibo) {
        this.idrecibo = idrecibo;
    }

    public Long getIdlogtrans() {
        return idlogtrans;
    }

    public void setIdlogtrans(Long idlogtrans) {
        this.idlogtrans = idlogtrans;
    }
    
    
    
    
    
    
}
