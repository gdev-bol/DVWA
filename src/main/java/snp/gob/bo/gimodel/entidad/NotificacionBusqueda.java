/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.entidad;

import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;


/**
 *
 * @author levi
 */
public class NotificacionBusqueda implements Serializable {
       private Long idnotificacion;
       private Integer nro_exped;
       private Integer bloque;        
       private String  tipo_tramite_notificacion;      
       private String  expediente;       
       private Integer gestion;       
       private String  extension;
       private String  demandante;
       private String  demandado;
       private String  demandante_con;
       private Date    fecha_ingreso;
       private String  obs;
       private String  estado;
       private String  nombre_solicitante;
       private String  nombre_notificador;      
       private Date    fecha_recep;    
       private Timestamp    demandante_fecha_noti;
       private Timestamp    demandante_fecha_devol;
       private Timestamp    demandado_fecha_noti;
       private Timestamp    demandado_fecha_devol;
       private String  obs_notifi;    
       
       
       
       
       

    public Long getIdnotificacion() {
        return idnotificacion;
    }

    public void setIdnotificacion(Long idnotificacion) {
        this.idnotificacion = idnotificacion;
    }

    public Integer getNro_exped() {
        return nro_exped;
    }

    public void setNro_exped(Integer nro_exped) {
        this.nro_exped = nro_exped;
    }

    public Integer getBloque() {
        return bloque;
    }

    public void setBloque(Integer bloque) {
        this.bloque = bloque;
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

    public String getDemandado() {
        return demandado;
    }

    public void setDemandado(String demandado) {
        this.demandado = demandado;
    }

    public String getDemandante_con() {
        return demandante_con;
    }

    public void setDemandante_con(String demandante_con) {
        this.demandante_con = demandante_con;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre_solicitante() {
        return nombre_solicitante;
    }

    public void setNombre_solicitante(String nombre_solicitante) {
        this.nombre_solicitante = nombre_solicitante;
    }

    public String getNombre_notificador() {
        return nombre_notificador;
    }

    public void setNombre_notificador(String nombre_notificador) {
        this.nombre_notificador = nombre_notificador;
    }

    public Date getFecha_recep() {
        return fecha_recep;
    }

    public void setFecha_recep(Date fecha_recep) {
        this.fecha_recep = fecha_recep;
    }

    public Timestamp getDemandante_fecha_noti() {
        return demandante_fecha_noti;
    }

    public void setDemandante_fecha_noti(Timestamp demandante_fecha_noti) {
        this.demandante_fecha_noti = demandante_fecha_noti;
    }

    public Timestamp getDemandante_fecha_devol() {
        return demandante_fecha_devol;
    }

    public void setDemandante_fecha_devol(Timestamp demandante_fecha_devol) {
        this.demandante_fecha_devol = demandante_fecha_devol;
    }

    public Timestamp getDemandado_fecha_noti() {
        return demandado_fecha_noti;
    }

    public void setDemandado_fecha_noti(Timestamp demandado_fecha_noti) {
        this.demandado_fecha_noti = demandado_fecha_noti;
    }

    public Timestamp getDemandado_fecha_devol() {
        return demandado_fecha_devol;
    }

    public void setDemandado_fecha_devol(Timestamp demandado_fecha_devol) {
        this.demandado_fecha_devol = demandado_fecha_devol;
    }

    
    
    
    
    
    public String getObs_notifi() {
        return obs_notifi;
    }

    public void setObs_notifi(String obs_notifi) {
        this.obs_notifi = obs_notifi;
    }




}
