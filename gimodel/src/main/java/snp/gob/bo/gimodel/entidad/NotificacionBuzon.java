/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.entidad;

import java.io.Serializable;

/**
 *
 * @author levi
 */
public class NotificacionBuzon implements Serializable{
    
    
    private Long conteo;
    private Long idnotificacion;
    private Long id_usuario_solicitante;
    private String nombre;
    private Integer bloque;
    private Integer nro_exped;
    private String tipo_tramite_notificacion;
    private String expediente;
    private Integer gestion;
    private String extension;

    public Long getConteo() {
        return conteo;
    }

    public void setConteo(Long conteo) {
        this.conteo = conteo;
    }

    public Long getIdnotificacion() {
        return idnotificacion;
    }

    public void setIdnotificacion(Long idnotificacion) {
        this.idnotificacion = idnotificacion;
    }

    public Long getId_usuario_solicitante() {
        return id_usuario_solicitante;
    }

    public void setId_usuario_solicitante(Long id_usuario_solicitante) {
        this.id_usuario_solicitante = id_usuario_solicitante;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
    
    
}
