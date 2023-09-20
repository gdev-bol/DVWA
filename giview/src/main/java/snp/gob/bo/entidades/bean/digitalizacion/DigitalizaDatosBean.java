/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.bean.digitalizacion;

/**
 *
 * @author levi
 */
public class DigitalizaDatosBean {
  private Long iddocumento;
  //Aqui se va a colocar el ya sea de signos, modificacion o renovacion
  private Long idservicio;
  private String nombre_archivo;
  private String  extension_archivo ;
  private String descripcion;
  private Integer nro_folios;
  private  String mime;
  private String tipo_documento;

    public Long getIddocumento() {
        return iddocumento;
    }

    public void setIddocumento(Long iddocumento) {
        this.iddocumento = iddocumento;
    }

    public Long getIdservicio() {
        return idservicio;
    }

    public void setIdservicio(Long idservicio) {
        this.idservicio = idservicio;
    }

    public String getNombre_archivo() {
        return nombre_archivo;
    }

    public void setNombre_archivo(String nombre_archivo) {
        this.nombre_archivo = nombre_archivo;
    }

    public String getExtension_archivo() {
        return extension_archivo;
    }

    public void setExtension_archivo(String extension_archivo) {
        this.extension_archivo = extension_archivo;
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

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }
  
  
  
  
  
}
