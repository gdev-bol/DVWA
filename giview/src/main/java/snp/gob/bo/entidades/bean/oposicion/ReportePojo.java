/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.bean.oposicion;

/**
 *
 * @author Ruben Ramirez
 */
public class ReportePojo {
    private Integer cantidad;
    private Integer gaceta;
    private Integer numero;
    private Long publicacion;
    private String marcaDemandante;
    private String marcaDemandada;
    private String apoderadoDemandante;
    private String apoderadoDemandada;
    private String firmaDemandante;
    private String firmaDemandada;

    public ReportePojo() {
    }
    
    public ReportePojo(Integer cantidad, Integer gaceta, Integer numero, Long publicacion, String marcaDemandante, String marcaDemandada, String apoderadoDemandante, String apoderadoDemandada, String firmaDemandante, String firmaDemandada) {
        this.cantidad = cantidad;
        this.gaceta = gaceta;
        this.numero = numero;
        this.publicacion = publicacion;
        this.marcaDemandante = marcaDemandante;
        this.marcaDemandada = marcaDemandada;
        this.apoderadoDemandante = apoderadoDemandante;
        this.apoderadoDemandada = apoderadoDemandada;
        this.firmaDemandante = firmaDemandante;
        this.firmaDemandada = firmaDemandada;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getGaceta() {
        return gaceta;
    }

    public void setGaceta(Integer gaceta) {
        this.gaceta = gaceta;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Long getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Long publicacion) {
        this.publicacion = publicacion;
    }

    public String getMarcaDemandante() {
        return marcaDemandante;
    }

    public void setMarcaDemandante(String marcaDemandante) {
        this.marcaDemandante = marcaDemandante;
    }

    public String getMarcaDemandada() {
        return marcaDemandada;
    }

    public void setMarcaDemandada(String marcaDemandada) {
        this.marcaDemandada = marcaDemandada;
    }

    public String getApoderadoDemandante() {
        return apoderadoDemandante;
    }

    public void setApoderadoDemandante(String apoderadoDemandante) {
        this.apoderadoDemandante = apoderadoDemandante;
    }

    public String getApoderadoDemandada() {
        return apoderadoDemandada;
    }

    public void setApoderadoDemandada(String apoderadoDemandada) {
        this.apoderadoDemandada = apoderadoDemandada;
    }

    public String getFirmaDemandante() {
        return firmaDemandante;
    }

    public void setFirmaDemandante(String firmaDemandante) {
        this.firmaDemandante = firmaDemandante;
    }

    public String getFirmaDemandada() {
        return firmaDemandada;
    }

    public void setFirmaDemandada(String firmaDemandada) {
        this.firmaDemandada = firmaDemandada;
    }
    
}
