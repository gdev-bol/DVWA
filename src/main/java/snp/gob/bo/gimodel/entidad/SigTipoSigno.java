/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.entidad;

import java.util.Date;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 26/08/2016
 */
public class SigTipoSigno {

    Long idTipoSigno;
    Long idSignoMarca;
    Long idLogTrans;
    String tipoSigno;
    String descripcionOtro;
    String estado;

    public Long getIdTipoSigno() {
        return idTipoSigno;
    }

    public void setIdTipoSigno(Long idTipoSigno) {
        this.idTipoSigno = idTipoSigno;
    }

    public Long getIdSignoMarca() {
        return idSignoMarca;
    }

    public void setIdSignoMarca(Long idSignoMarca) {
        this.idSignoMarca = idSignoMarca;
    }

    public Long getIdLogTrans() {
        return idLogTrans;
    }

    public void setIdLogTrans(Long idLogTrans) {
        this.idLogTrans = idLogTrans;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        if (tipoSigno==null) {
            tipoSigno="";
        }
        if (descripcionOtro==null) {
            descripcionOtro="";
        }
        
        
        return "|"+"tipoSigno="+tipoSigno
            +"|"+"descripcionOtro="+descripcionOtro+"|";
    }

}
