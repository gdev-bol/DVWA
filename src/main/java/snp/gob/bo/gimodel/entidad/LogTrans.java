/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.entidad;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 22/11/2016
 */
public class LogTrans implements Serializable {
    Long idLogTrans;
    Long idUsuario;
    Date fecha;
    
    public LogTrans(){
    }
    
    //definir constructor
    public LogTrans(Long idUsuario, Date fecha){
        this.idUsuario = idUsuario;
        this.fecha = fecha;
    }
    

    public Long getIdLogTrans() {
        return idLogTrans;
    }

    public void setIdLogTrans(Long idLogTrans) {
        this.idLogTrans = idLogTrans;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
