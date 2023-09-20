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
 * @author levi
 */
public class Notificacion implements Serializable{
  private Long idnotificacion;
  private Long idlogtrans;
  private Integer bloque ;
  private Integer nro_exped;
  private Long id_usuario_solicitante;
  private String tipo_tramite_notificacion ;
  private String expediente;
  private Integer gestion;
  private String extension;
  private String demandante;
  private String demandante_cod_estado;
  private Date demandante_fecha_devol;
  private Date   demandante_fecha_noti;
  private String demandante_lugar_notificacion;
  private String demandante_solic;
  private String demandante_apod;
  private String demandante_fojas;
  private String demandante_con;
  private String demandante_direc;
  private String demandante_cel;
  private String demandante_ciudad_notif;
  private String demandado;
  private String demandado_cod_estado;
  private Date demandado_fecha_devol ;
  private Date demandado_fecha_noti;
  private String demandado_lugar_notificacion;
  private String demandado_solic;
  private String demandado_apod;
  private String demandado_fojas ;
  private String demandado_con;
  private String demandado_direc;
  private String demandado_cel;
  private String demandado_ciudad_notif;
  private Date fecha_ingreso;
  private Integer tipo;
  private String obs;
  private String historial;
  private Date fecha_recep ;
  private String obs_notifi;
  private Long id_usuario_notificador;
 // private String ciudad_notificacion;
  private String estado_marca ;
  private String form_noti_pre;
  private String form_noti_cuerpo;

    public Long getIdnotificacion() {
        return idnotificacion;
    }

    public void setIdnotificacion(Long idnotificacion) {
        this.idnotificacion = idnotificacion;
    }

    public Long getIdlogtrans() {
        return idlogtrans;
    }

    public void setIdlogtrans(Long idlogtrans) {
        this.idlogtrans = idlogtrans;
    }

    public Integer getBloque() {
        return bloque;
    }

    public void setBloque(Integer bloque) {
        this.bloque = bloque;
    }

    public Integer getNro_exped() {
        return nro_exped;
    }

    public void setNro_exped(Integer nro_exped) {
        this.nro_exped = nro_exped;
    }

    public Long getId_usuario_solicitante() {
        return id_usuario_solicitante;
    }

    public void setId_usuario_solicitante(Long id_usuario_solicitante) {
        this.id_usuario_solicitante = id_usuario_solicitante;
    }

    public String getTipo_tramite_notificacion() {
        return tipo_tramite_notificacion;
    }

    public void setTipo_tramite_notificacion(String tipo_tramite_notificacion) {
        this.tipo_tramite_notificacion = tipo_tramite_notificacion;
    }

    public String getExpediente() {
        return expediente;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

    public Integer getGestion() {
        return gestion;
    }

    public void setGestion(Integer gestion) {
        this.gestion = gestion;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getDemandante() {
        return demandante;
    }

    public void setDemandante(String demandante) {
        this.demandante = demandante;
    }

    public String getDemandante_cod_estado() {
        return demandante_cod_estado;
    }

    public void setDemandante_cod_estado(String demandante_cod_estado) {
        this.demandante_cod_estado = demandante_cod_estado;
    }

    public Date getDemandante_fecha_devol() {
        return demandante_fecha_devol;
    }

    public void setDemandante_fecha_devol(Date demandante_fecha_devol) {
        this.demandante_fecha_devol = demandante_fecha_devol;
    }

    public Date getDemandante_fecha_noti() {
        return demandante_fecha_noti;
    }

    public void setDemandante_fecha_noti(Date demandante_fecha_noti) {
        this.demandante_fecha_noti = demandante_fecha_noti;
    }

    public String getDemandante_lugar_notificacion() {
        return demandante_lugar_notificacion;
    }

    public void setDemandante_lugar_notificacion(String demandante_lugar_notificacion) {
        this.demandante_lugar_notificacion = demandante_lugar_notificacion;
    }

    public String getDemandante_solic() {
        return demandante_solic;
    }

    public void setDemandante_solic(String demandante_solic) {
        this.demandante_solic = demandante_solic;
    }

    public String getDemandante_apod() {
        return demandante_apod;
    }

    public void setDemandante_apod(String demandante_apod) {
        this.demandante_apod = demandante_apod;
    }

    public String getDemandante_fojas() {
        return demandante_fojas;
    }

    public void setDemandante_fojas(String demandante_fojas) {
        this.demandante_fojas = demandante_fojas;
    }

    public String getDemandante_con() {
        return demandante_con;
    }

    public void setDemandante_con(String demandante_con) {
        this.demandante_con = demandante_con;
    }

    public String getDemandante_direc() {
        return demandante_direc;
    }

    public void setDemandante_direc(String demandante_direc) {
        this.demandante_direc = demandante_direc;
    }

    public String getDemandante_cel() {
        return demandante_cel;
    }

    public void setDemandante_cel(String demandante_cel) {
        this.demandante_cel = demandante_cel;
    }

    public String getDemandado() {
        return demandado;
    }

    public void setDemandado(String demandado) {
        this.demandado = demandado;
    }

    public String getDemandado_cod_estado() {
        return demandado_cod_estado;
    }

    public void setDemandado_cod_estado(String demandado_cod_estado) {
        this.demandado_cod_estado = demandado_cod_estado;
    }

    public Date getDemandado_fecha_devol() {
        return demandado_fecha_devol;
    }

    public void setDemandado_fecha_devol(Date demandado_fecha_devol) {
        this.demandado_fecha_devol = demandado_fecha_devol;
    }

    public Date getDemandado_fecha_noti() {
        return demandado_fecha_noti;
    }

    public void setDemandado_fecha_noti(Date demandado_fecha_noti) {
        this.demandado_fecha_noti = demandado_fecha_noti;
    }

    public String getDemandado_lugar_notificacion() {
        return demandado_lugar_notificacion;
    }

    public void setDemandado_lugar_notificacion(String demandado_lugar_notificacion) {
        this.demandado_lugar_notificacion = demandado_lugar_notificacion;
    }

    public String getDemandado_solic() {
        return demandado_solic;
    }

    public void setDemandado_solic(String demandado_solic) {
        this.demandado_solic = demandado_solic;
    }

    public String getDemandado_apod() {
        return demandado_apod;
    }

    public void setDemandado_apod(String demandado_apod) {
        this.demandado_apod = demandado_apod;
    }

    public String getDemandado_fojas() {
        return demandado_fojas;
    }

    public void setDemandado_fojas(String demandado_fojas) {
        this.demandado_fojas = demandado_fojas;
    }

    public String getDemandado_con() {
        return demandado_con;
    }

    public void setDemandado_con(String demandado_con) {
        this.demandado_con = demandado_con;
    }

    public String getDemandado_direc() {
        return demandado_direc;
    }

    public void setDemandado_direc(String demandado_direc) {
        this.demandado_direc = demandado_direc;
    }

    public String getDemandado_cel() {
        return demandado_cel;
    }

    public void setDemandado_cel(String demandado_cel) {
        this.demandado_cel = demandado_cel;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getHistorial() {
        return historial;
    }

    public void setHistorial(String historial) {
        this.historial = historial;
    }

    public Date getFecha_recep() {
        return fecha_recep;
    }

    public void setFecha_recep(Date fecha_recep) {
        this.fecha_recep = fecha_recep;
    }

    public String getObs_notifi() {
        return obs_notifi;
    }

    public void setObs_notifi(String obs_notifi) {
        this.obs_notifi = obs_notifi;
    }

    public Long getId_usuario_notificador() {
        return id_usuario_notificador;
    }

    public void setId_usuario_notificador(Long id_usuario_notificador) {
        this.id_usuario_notificador = id_usuario_notificador;
    }

   /* public String getCiudad_notificacion() {
        return ciudad_notificacion;
    }

    public void setCiudad_notificacion(String ciudad_notificacion) {
        this.ciudad_notificacion = ciudad_notificacion;
    }*/

    public String getEstado_marca() {
        return estado_marca;
    }

    public void setEstado_marca(String estado_marca) {
        this.estado_marca = estado_marca;
    }

    public String getForm_noti_pre() {
        return form_noti_pre;
    }

    public void setForm_noti_pre(String form_noti_pre) {
        this.form_noti_pre = form_noti_pre;
    }

    public String getForm_noti_cuerpo() {
        return form_noti_cuerpo;
    }

    public void setForm_noti_cuerpo(String form_noti_cuerpo) {
        this.form_noti_cuerpo = form_noti_cuerpo;
    }

    public String getDemandante_ciudad_notif() {
        return demandante_ciudad_notif;
    }

    public void setDemandante_ciudad_notif(String demandante_ciudad_notif) {
        this.demandante_ciudad_notif = demandante_ciudad_notif;
    }

    public String getDemandado_ciudad_notif() {
        return demandado_ciudad_notif;
    }

    public void setDemandado_ciudad_notif(String demandado_ciudad_notif) {
        this.demandado_ciudad_notif = demandado_ciudad_notif;
    }

  
  
}
