/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.entidad;

import java.io.Serializable;

/**
 *
 * @author Luis Angel Quispe Limachi
 *  * @see OpoMarcaDemandante
 * @version 1.0, 06/09/2016
 */
public class OpoMarcademandante implements Serializable{
 
   Long idmarcademandante;
   Long idoposicion;
   Long idtramite;
   Long idpatente;
   Long idarea;
   Long idmarca;
   Long idlogtrans;
   Integer orden_opo;
   Integer dmte_reg;
   String dmte_serie;
   Integer dmte_public;
   Long dmte_sm;
   Integer dmte_sp;
   String dmte_marca_lnv;
   String dmte_uso;
   Integer dmte_clase;
   String dmte_nombre;
   String dmte_direccion;
   String dmte_tel;
   String dmte_email;
   String dmte_fax;
   String dmte_pais;
   String dmte_departamento;
   String estado;

    public Long getIdmarcademandante() {
        return idmarcademandante;
    }

    public void setIdmarcademandante(Long idmarcademandante) {
        this.idmarcademandante = idmarcademandante;
    }

  

    public Long getIdoposicion() {
        return idoposicion;
    }

    public void setIdoposicion(Long idoposicion) {
        this.idoposicion = idoposicion;
    }

    public Long getIdtramite() {
        return idtramite;
    }

    public void setIdtramite(Long idtramite) {
        this.idtramite = idtramite;
    }

    public Long getIdpatente() {
        return idpatente;
    }

    public void setIdpatente(Long idpatente) {
        this.idpatente = idpatente;
    }

    public Long getIdarea() {
        return idarea;
    }

    public void setIdarea(Long idarea) {
        this.idarea = idarea;
    }

    public Long getIdmarca() {
        return idmarca;
    }

    public void setIdmarca(Long idmarca) {
        this.idmarca = idmarca;
    }

    public Long getIdlogtrans() {
        return idlogtrans;
    }

    public void setIdlogtrans(Long idlogtrans) {
        this.idlogtrans = idlogtrans;
    }

    public Integer getOrden_opo() {
        return orden_opo;
    }

    public void setOrden_opo(Integer orden_opo) {
        this.orden_opo = orden_opo;
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

    public Integer getDmte_sp() {
        return dmte_sp;
    }

    public void setDmte_sp(Integer dmte_sp) {
        this.dmte_sp = dmte_sp;
    }

    public String getDmte_marca_lnv() {
        return dmte_marca_lnv;
    }

    public void setDmte_marca_lnv(String dmte_marca_lnv) {
        this.dmte_marca_lnv = dmte_marca_lnv;
    }

    public String getDmte_uso() {
        return dmte_uso;
    }

    public void setDmte_uso(String dmte_uso) {
        this.dmte_uso = dmte_uso;
    }

    public Integer getDmte_clase() {
        return dmte_clase;
    }

    public void setDmte_clase(Integer dmte_clase) {
        this.dmte_clase = dmte_clase;
    }

    public String getDmte_nombre() {
        return dmte_nombre;
    }

    public void setDmte_nombre(String dmte_nombre) {
        this.dmte_nombre = dmte_nombre;
    }

    public String getDmte_direccion() {
        return dmte_direccion;
    }

    public void setDmte_direccion(String dmte_direccion) {
        this.dmte_direccion = dmte_direccion;
    }

    public String getDmte_tel() {
        return dmte_tel;
    }

    public void setDmte_tel(String dmte_tel) {
        this.dmte_tel = dmte_tel;
    }

    public String getDmte_email() {
        return dmte_email;
    }

    public void setDmte_email(String dmte_email) {
        this.dmte_email = dmte_email;
    }

    public String getDmte_fax() {
        return dmte_fax;
    }

    public void setDmte_fax(String dmte_fax) {
        this.dmte_fax = dmte_fax;
    }

    public String getDmte_pais() {
        return dmte_pais;
    }

    public void setDmte_pais(String dmte_pais) {
        this.dmte_pais = dmte_pais;
    }

    public String getDmte_departamento() {
        return dmte_departamento;
    }

    public void setDmte_departamento(String dmte_departamento) {
        this.dmte_departamento = dmte_departamento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
  
}
