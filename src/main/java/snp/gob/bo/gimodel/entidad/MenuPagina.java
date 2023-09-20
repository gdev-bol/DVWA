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
public class MenuPagina {
     private Long idmenupagina;
     private Long idmenu;
     private Long idpagina;
     private Long idlogtrans;
     private int orden;
     private String estado;

    public Long getIdmenupagina() {
        return idmenupagina;
    }

    public void setIdmenupagina(Long idmenupagina) {
        this.idmenupagina = idmenupagina;
    }

    public Long getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(Long idmenu) {
        this.idmenu = idmenu;
    }

    public Long getIdpagina() {
        return idpagina;
    }

    public void setIdpagina(Long idpagina) {
        this.idpagina = idpagina;
    }

    public Long getIdlogtrans() {
        return idlogtrans;
    }

    public void setIdlogtrans(Long idlogtrans) {
        this.idlogtrans = idlogtrans;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
     
     
     
}
