/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.entidad;

import java.util.Date;

/**
 *
 * @author levi
 */
public class UsuarioPagina {
    private Long idUsuarioPagina;
    private Long idUsuario;        
    private Long idPagina;
    private Long idLogTrans;
    private String  acceso;          
    private String  habilitado;
    private Date    fecha_vig_ini;
    private Date    fecha_vig_fin;
    private String  hora_vig_ini; 
    private String  hora_vig_fin;
    private boolean muestra;
    private String  estado;

    public Long getIdUsuarioPagina() {
        return idUsuarioPagina;
    }

    public void setIdUsuarioPagina(Long idUsuarioPagina) {
        this.idUsuarioPagina = idUsuarioPagina;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdPagina() {
        return idPagina;
    }

    public void setIdPagina(Long idPagina) {
        this.idPagina = idPagina;
    }

    public Long getIdLogTrans() {
        return idLogTrans;
    }

    public void setIdLogTrans(Long idLogTrans) {
        this.idLogTrans = idLogTrans;
    }

  

    public String getAcceso() {
        return acceso;
    }

    public void setAcceso(String acceso) {
        this.acceso = acceso;
    }

    public String getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }

    public Date getFecha_vig_ini() {
        return fecha_vig_ini;
    }

    public void setFecha_vig_ini(Date fecha_vig_ini) {
        this.fecha_vig_ini = fecha_vig_ini;
    }

    public Date getFecha_vig_fin() {
        return fecha_vig_fin;
    }

    public void setFecha_vig_fin(Date fecha_vig_fin) {
        this.fecha_vig_fin = fecha_vig_fin;
    }

    public String getHora_vig_ini() {
        return hora_vig_ini;
    }

    public void setHora_vig_ini(String hora_vig_ini) {
        this.hora_vig_ini = hora_vig_ini;
    }

    public String getHora_vig_fin() {
        return hora_vig_fin;
    }

    public void setHora_vig_fin(String hora_vig_fin) {
        this.hora_vig_fin = hora_vig_fin;
    }




    public boolean getMuestra() {
        return muestra;
    }

    public void setMuestra(boolean muestra) {
        this.muestra = muestra;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
