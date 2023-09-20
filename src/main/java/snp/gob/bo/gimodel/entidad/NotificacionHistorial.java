/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.entidad;

/**
 *
 * @author levi
 */
public class NotificacionHistorial {
    private String historial ;
    private String fecha;
    private String tipo_tramite_notificacion;
    private String expediente;
    private Integer gestion;
    private String extension;        

    public String getHistorial() {
        return historial;
    }

    public void setHistorial(String historial) {
        this.historial = historial;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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
    
    
    
}
