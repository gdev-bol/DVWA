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
public class SigDetallePublicacion implements Serializable{
    Long iddetallepublicacion;
    Long idpublicacion;
    Long idsignomarca;
    Long idlogtrans;
    Long numero_publicacion;
    Long sm;
    String sm_descripcion;
    Date fecha_ingreso;
    String marca;
    Integer clase;
    String tipo_signo_descripcion;
    String tipo_genero;
    String lista_productos;
    String descripcion_signo;
    String nombre_titular;
    String documento_titular;
    String pais_titular;
    String descripcion_pais_titular;
    String descripcion_departamento_titular;
    String direccion_titular;
    String nombre_apoderado;
    String documento_apoderado;
    String direccion_apoderado;
    String prioridad;
    String fecha_prioridad;
    String pais_prioridad;
    String pais_prio_descripcion;
    Integer numero_seccion;
    String seccion;
    String subseccion;
    Boolean publicado;
    String estado;
    String estado_marca;
   

    public Long getIddetallepublicacion() {
        return iddetallepublicacion;
    }

    public void setIddetallepublicacion(Long iddetallepublicacion) {
        this.iddetallepublicacion = iddetallepublicacion;
    }

    public Long getIdpublicacion() {
        return idpublicacion;
    }

    public void setIdpublicacion(Long idpublicacion) {
        this.idpublicacion = idpublicacion;
    }

    public Long getIdsignomarca() {
        return idsignomarca;
    }

    public void setIdsignomarca(Long idsignomarca) {
        this.idsignomarca = idsignomarca;
    }

    public Long getIdlogtrans() {
        return idlogtrans;
    }

    public void setIdlogtrans(Long idlogtrans) {
        this.idlogtrans = idlogtrans;
    }

    public Long getNumero_publicacion() {
        return numero_publicacion;
    }

    public void setNumero_publicacion(Long numero_publicacion) {
        this.numero_publicacion = numero_publicacion;
    }

    public Long getSm() {
        return sm;
    }

    public void setSm(Long sm) {
        this.sm = sm;
    }

    public String getSm_descripcion() {
        return sm_descripcion;
    }

    public void setSm_descripcion(String sm_descripcion) {
        this.sm_descripcion = sm_descripcion;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getClase() {
        return clase;
    }

    public void setClase(Integer clase) {
        this.clase = clase;
    }

    public String getTipo_signo_descripcion() {
        return tipo_signo_descripcion;
    }

    public void setTipo_signo_descripcion(String tipo_signo_descripcion) {
        this.tipo_signo_descripcion = tipo_signo_descripcion;
    }

    public String getTipo_genero() {
        return tipo_genero;
    }

    public void setTipo_genero(String tipo_genero) {
        this.tipo_genero = tipo_genero;
    }

    public String getLista_productos() {
        return lista_productos;
    }

    public void setLista_productos(String lista_productos) {
        this.lista_productos = lista_productos;
    }

    public String getDescripcion_signo() {
        return descripcion_signo;
    }

    public void setDescripcion_signo(String descripcion_signo) {
        this.descripcion_signo = descripcion_signo;
    }

    public String getNombre_titular() {
        return nombre_titular;
    }

    public void setNombre_titular(String nombre_titular) {
        this.nombre_titular = nombre_titular;
    }

    public String getDocumento_titular() {
        return documento_titular;
    }

    public void setDocumento_titular(String documento_titular) {
        this.documento_titular = documento_titular;
    }

    public String getPais_titular() {
        return pais_titular;
    }

    public void setPais_titular(String pais_titular) {
        this.pais_titular = pais_titular;
    }
  
    public String getDescripcion_pais_titular() {
        return descripcion_pais_titular;
    }

    public void setDescripcion_pais_titular(String descripcion_pais_titular) {
        this.descripcion_pais_titular = descripcion_pais_titular;
    }

    public String getDescripcion_departamento_titular() {
        return descripcion_departamento_titular;
    }

    public void setDescripcion_departamento_titular(String descripcion_departamento_titular) {
        this.descripcion_departamento_titular = descripcion_departamento_titular;
    }

    public String getDireccion_titular() {
        return direccion_titular;
    }

    public void setDireccion_titular(String direccion_titular) {
        this.direccion_titular = direccion_titular;
    }

    public String getNombre_apoderado() {
        return nombre_apoderado;
    }

    public void setNombre_apoderado(String nombre_apoderado) {
        this.nombre_apoderado = nombre_apoderado;
    }

    public String getDocumento_apoderado() {
        return documento_apoderado;
    }

    public void setDocumento_apoderado(String documento_apoderado) {
        this.documento_apoderado = documento_apoderado;
    }

    public String getDireccion_apoderado() {
        return direccion_apoderado;
    }

    public void setDireccion_apoderado(String direccion_apoderado) {
        this.direccion_apoderado = direccion_apoderado;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getFecha_prioridad() {
        return fecha_prioridad;
    }

    public void setFecha_prioridad(String fecha_prioridad) {
        this.fecha_prioridad = fecha_prioridad;
    }

    public String getPais_prioridad() {
        return pais_prioridad;
    }

    public void setPais_prioridad(String pais_prioridad) {
        this.pais_prioridad = pais_prioridad;
    }

    public String getPais_prio_descripcion() {
        return pais_prio_descripcion;
    }

    public void setPais_prio_descripcion(String pais_prio_descripcion) {
        this.pais_prio_descripcion = pais_prio_descripcion;
    }

    public Integer getNumero_seccion() {
        return numero_seccion;
    }

    public void setNumero_seccion(Integer numero_seccion) {
        this.numero_seccion = numero_seccion;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getSubseccion() {
        return subseccion;
    }

    public void setSubseccion(String subseccion) {
        this.subseccion = subseccion;
    }

    public Boolean getPublicado() {
        return publicado;
    }

    public void setPublicado(Boolean publicado) {
        this.publicado = publicado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado_marca() {
        return estado_marca;
    }

    public void setEstado_marca(String estado_marca) {
        this.estado_marca = estado_marca;
    }
       
}
