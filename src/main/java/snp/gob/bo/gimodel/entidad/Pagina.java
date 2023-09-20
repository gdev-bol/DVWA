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
public class Pagina {
    private Long idpagina;
    private Long idlogtrans;
    private String descripcion;
    private String url;
    private String estado;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
    
    
    
    
}
