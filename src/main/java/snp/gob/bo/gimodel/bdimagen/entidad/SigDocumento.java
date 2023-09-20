/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.bdimagen.entidad;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author levi
 */
public class SigDocumento implements Serializable{
  private Long iddocumento;
  private Long idsignomarca;
  private String nombre_archivo;
  private String descripcion;
  private Integer nro_folios;
  private String tipo_documento;
  private String extension_archivo;
  
  private Date fecha_creacion;
  private String mime;
  private String estado; 

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public Long getIddocumento() {
        return iddocumento;
    }

    public void setIddocumento(Long iddocumento) {
        this.iddocumento = iddocumento;
    }

    public Long getIdsignomarca() {
        return idsignomarca;
    }

    public void setIdsignomarca(Long idsignomarca) {
        this.idsignomarca = idsignomarca;
    }

    public String getNombre_archivo() {
        return nombre_archivo;
    }

    public void setNombre_archivo(String nombre_archivo) {
        this.nombre_archivo = nombre_archivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getNro_folios() {
        return nro_folios;
    }

    public void setNro_folios(Integer nro_folios) {
        this.nro_folios = nro_folios;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }


    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getExtension_archivo() {
        return extension_archivo;
    }

    public void setExtension_archivo(String extension_archivo) {
        this.extension_archivo = extension_archivo;
    }
          
          
          
          
}
