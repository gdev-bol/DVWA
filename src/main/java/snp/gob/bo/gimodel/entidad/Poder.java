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
public class Poder implements Serializable{
    private Long idpoder;                       
    private Long idlogtrans;                   
    private String tipo_poder;                  
    private String tipo_tramite;                 
    private Long nro_exped;             
    private Integer gestion;
    private String nro_testimonio;                
    private Date fecha_testimonio;             
    private String notario;
    private String nom_confiere_poder;             
    private String dom_confiere_poder;            
    private String apoderado;                 
    private String poder_revocado;                 
    private String obs;                 
    private String estado;

    public Long getIdpoder() {
        return idpoder;
    }

    public void setIdpoder(Long idpoder) {
        this.idpoder = idpoder;
    }

    public Long getIdlogtrans() {
        return idlogtrans;
    }

    public void setIdlogtrans(Long idlogtrans) {
        this.idlogtrans = idlogtrans;
    }

    public String getTipo_poder() {
        return tipo_poder;
    }

    public void setTipo_poder(String tipo_poder) {
        this.tipo_poder = tipo_poder;
    }

    public String getTipo_tramite() {
        return tipo_tramite;
    }

    public void setTipo_tramite(String tipo_tramite) {
        this.tipo_tramite = tipo_tramite;
    }

    public Long getNro_exped() {
        return nro_exped;
    }

    public void setNro_exped(Long nro_exped) {
        this.nro_exped = nro_exped;
    }

    public Integer getGestion() {
        return gestion;
    }

    public void setGestion(Integer gestion) {
        this.gestion = gestion;
    }

    public String getNro_testimonio() {
        return nro_testimonio;
    }

    public void setNro_testimonio(String nro_testimonio) {
        this.nro_testimonio = nro_testimonio;
    }

    public Date getFecha_testimonio() {
        return fecha_testimonio;
    }

    public void setFecha_testimonio(Date fecha_testimonio) {
        this.fecha_testimonio = fecha_testimonio;
    }

    public String getNotario() {
        return notario;
    }

    public void setNotario(String notario) {
        this.notario = notario;
    }

    public String getNom_confiere_poder() {
        return nom_confiere_poder;
    }

    public void setNom_confiere_poder(String nom_confiere_poder) {
        this.nom_confiere_poder = nom_confiere_poder;
    }

    public String getDom_confiere_poder() {
        return dom_confiere_poder;
    }

    public void setDom_confiere_poder(String dom_confiere_poder) {
        this.dom_confiere_poder = dom_confiere_poder;
    }

    public String getApoderado() {
        return apoderado;
    }

    public void setApoderado(String apoderado) {
        this.apoderado = apoderado;
    }

    public String getPoder_revocado() {
        return poder_revocado;
    }

    public void setPoder_revocado(String poder_revocado) {
        this.poder_revocado = poder_revocado;
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
    
    
    
    
}
