/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.entidad;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author susana
 */
public class Documento implements Serializable {

    Long iddocumento;
    Long idarea;
    Long idtramite;
    Long idlogtrans;
    String nombre_archivo;
    String descripcion;
    String nro_folios;
    Date fecha_creacion;
    String tipo_documentacion;
    String tipo_archivo;
    byte[] imagen;
    String estado;

    public Long getIddocumento() {
        return iddocumento;
    }

    public void setIddocumento(Long iddocumento) {
        this.iddocumento = iddocumento;
    }

    public Long getIdarea() {
        return idarea;
    }

    public void setIdarea(Long idarea) {
        this.idarea = idarea;
    }

    public Long getIdtramite() {
        return idtramite;
    }

    public void setIdtramite(Long idtramite) {
        this.idtramite = idtramite;
    }

    public Long getIdlogtrans() {
        return idlogtrans;
    }

    public void setIdlogtrans(Long idlogtrans) {
        this.idlogtrans = idlogtrans;
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

    public String getNro_folios() {
        return nro_folios;
    }

    public void setNro_folios(String nro_folios) {
        this.nro_folios = nro_folios;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getTipo_documentacion() {
        return tipo_documentacion;
    }

    public void setTipo_documentacion(String tipo_documentacion) {
        this.tipo_documentacion = tipo_documentacion;
    }

    public String getTipo_archivo() {
        return tipo_archivo;
    }

    public void setTipo_archivo(String tipo_archivo) {
        this.tipo_archivo = tipo_archivo;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
