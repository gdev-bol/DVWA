/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.solicitudes.entidades;

import snp.gob.bo.gimodel.entidad.*;
import java.util.Date;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 23/08/2016
 */
public class SmTipoSignos {
    
    Long signoMarcaId;
    String tipoSigno;
    String descripcionOtro;

    public Long getSignoMarcaId() {
        return signoMarcaId;
    }

    public void setSignoMarcaId(Long signoMarcaId) {
        this.signoMarcaId = signoMarcaId;
    }

    public String getTipoSigno() {
        return tipoSigno;
    }

    public void setTipoSigno(String tipoSigno) {
        this.tipoSigno = tipoSigno;
    }

    public String getDescripcionOtro() {
        return descripcionOtro;
    }

    public void setDescripcionOtro(String descripcionOtro) {
        this.descripcionOtro = descripcionOtro;
    }
    
    
    
}
