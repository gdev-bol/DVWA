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
public class MenuPaginaUsuario {
    
    private String sistema;
    private Long idmenu;
    private String menu;
    private Long idpagina;
    private String pagina;
    private boolean muestra;
    private String estado;

 

    public Long getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(Long idmenu) {
        this.idmenu = idmenu;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public Long getIdpagina() {
        return idpagina;
    }

    public void setIdpagina(Long idpagina) {
        this.idpagina = idpagina;
    }

    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }

    public boolean getMuestra() {
        return muestra;
    }

    public void setMuestra(boolean muestra) {
        this.muestra = muestra;
    }


    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSistema() {
        return sistema;
    }

    public void setSistema(String sistema) {
        this.sistema = sistema;
    }
    
    
    
}
