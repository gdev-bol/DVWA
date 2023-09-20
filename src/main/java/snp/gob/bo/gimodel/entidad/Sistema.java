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
public class Sistema {

    private Long idsistema;
    private Long idlogtrans;
    private String descripcion;
    private String estado;

    public Long getIdsistema() {
        return idsistema;
    }

    public void setIdsistema(Long idsistema) {
        this.idsistema = idsistema;
    }

    public Long getIdlogtrans() {
        return idlogtrans;
    }

    public void setIdlogtrans(Long idlogtrans) {
        this.idlogtrans = idlogtrans;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
