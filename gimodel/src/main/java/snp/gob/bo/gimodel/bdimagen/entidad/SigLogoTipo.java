package snp.gob.bo.gimodel.bdimagen.entidad;



/**
 *
 * @author Eddy Valero
 * @version 1.0, 13/11/2016
 */
public class SigLogoTipo {
    
    
    Long idLogoTipo;
    Long idSignoMarca;
    Long idLogTrans;
    String urlLogoTipo;
    Boolean principal;
    String nombreArchivo;
    String extensionArchivo;
    String estado;

    public Long getIdLogoTipo() {
        return idLogoTipo;
    }

    public void setIdLogoTipo(Long idLogoTipo) {
        this.idLogoTipo = idLogoTipo;
    }

    public Long getIdSignoMarca() {
        return idSignoMarca;
    }

    public void setIdSignoMarca(Long idSignoMarca) {
        this.idSignoMarca = idSignoMarca;
    }

    public Long getIdLogTrans() {
        return idLogTrans;
    }

    public void setIdLogTrans(Long idLogTrans) {
        this.idLogTrans = idLogTrans;
    }

    public String getUrlLogoTipo() {
        return urlLogoTipo;
    }

    public void setUrlLogoTipo(String urlLogoTipo) {
        this.urlLogoTipo = urlLogoTipo;
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getExtensionArchivo() {
        return extensionArchivo;
    }

    public void setExtensionArchivo(String extensionArchivo) {
        this.extensionArchivo = extensionArchivo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

  

    
    
}
