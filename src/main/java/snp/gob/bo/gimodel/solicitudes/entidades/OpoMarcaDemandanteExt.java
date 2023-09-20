/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.solicitudes.entidades;

import java.util.Date;

/**
 *
 * @author luis_angel
 */
public class OpoMarcaDemandanteExt {
  Long idmarcademandante;
  Integer idformulario;
  Integer dmte_reg;
  String dmte_serie;
  Integer dmte_public;
  Long dmte_sm; 
  String dmte_marca;
  String dmte_sin_marca;
  String dmte_opoandina;
  Date fechacreacion;
  Date fechamod;
  String estado;

    public Long getIdmarcademandante() {
        return idmarcademandante;
    }

    public void setIdmarcademandante(Long idmarcademandante) {
        this.idmarcademandante = idmarcademandante;
    }

    public Integer getIdformulario() {
        return idformulario;
    }

    public void setIdformulario(Integer idformulario) {
        this.idformulario = idformulario;
    }

    public Integer getDmte_reg() {
        return dmte_reg;
    }

    public void setDmte_reg(Integer dmte_reg) {
        this.dmte_reg = dmte_reg;
    }

    public String getDmte_serie() {
        return dmte_serie;
    }

    public void setDmte_serie(String dmte_serie) {
        this.dmte_serie = dmte_serie;
    }

    public Integer getDmte_public() {
        return dmte_public;
    }

    public void setDmte_public(Integer dmte_public) {
        this.dmte_public = dmte_public;
    }

    public Long getDmte_sm() {
        return dmte_sm;
    }

    public void setDmte_sm(Long dmte_sm) {
        this.dmte_sm = dmte_sm;
    }

    public String getDmte_marca() {
        return dmte_marca;
    }

    public void setDmte_marca(String dmte_marca) {
        this.dmte_marca = dmte_marca;
    }

    public String getDmte_sin_marca() {
        return dmte_sin_marca;
    }

    public void setDmte_sin_marca(String dmte_sin_marca) {
        this.dmte_sin_marca = dmte_sin_marca;
    }

    public String getDmte_opoandina() {
        return dmte_opoandina;
    }

    public void setDmte_opoandina(String dmte_opoandina) {
        this.dmte_opoandina = dmte_opoandina;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public Date getFechamod() {
        return fechamod;
    }

    public void setFechamod(Date fechamod) {
        this.fechamod = fechamod;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
  
}
