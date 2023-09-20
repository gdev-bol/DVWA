/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.entidad;

import java.io.Serializable;

/**
 *
 * @author susana
 */
public class ModSolicitanteApoderado implements Serializable {

    Long idsolicitanteapoderado;
    Long idmodificacion;
    Long idLogTrans;
    String tipo_titular;
    String tipo_persona;   
    String nombre_razonsocial;
    String primer_apellido;
    String segundo_apellido;
    String numero_documento;
    String tipo_documento;
    String lugar_expedicion;
    String pais;
    String genero;
    String solicitud_departamento;
    String poder;
    String direccion;
    String telefono;
    String email;
    String fax;
    String celular;
    String estado;
    Long idsolicitanteapoderado_modificar;
    Long idSipi;

    public Long getIdsolicitanteapoderado() {
        return idsolicitanteapoderado;
    }

    public void setIdsolicitanteapoderado(Long idsolicitanteapoderado) {
        this.idsolicitanteapoderado = idsolicitanteapoderado;
    }

    public Long getIdsolicitanteapoderado_modificar() {
        return idsolicitanteapoderado_modificar;
    }

    public void setIdsolicitanteapoderado_modificar(Long idsolicitanteapoderado_modificar) {
        this.idsolicitanteapoderado_modificar = idsolicitanteapoderado_modificar;
    }

    public Long getIdLogTrans() {
        return idLogTrans;
    }

    public void setIdLogTrans(Long idLogTrans) {
        this.idLogTrans = idLogTrans;
    }

    public String getTipo_titular() {
        return tipo_titular;
    }

    public void setTipo_titular(String tipo_titular) {
        this.tipo_titular = tipo_titular;
    }

    public String getNombre_razonsocial() {
        return nombre_razonsocial;
    }

    public void setNombre_razonsocial(String nombre_razonsocial) {
        this.nombre_razonsocial = nombre_razonsocial;
    }

    public String getNumero_documento() {
        return numero_documento;
    }

    public void setNumero_documento(String numero_documento) {
        this.numero_documento = numero_documento;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getLugar_expedicion() {
        return lugar_expedicion;
    }

    public void setLugar_expedicion(String lugar_expedicion) {
        this.lugar_expedicion = lugar_expedicion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getPrimer_apellido() {
        return primer_apellido;
    }

    public void setPrimer_apellido(String primer_apellido) {
        this.primer_apellido = primer_apellido;
    }

    public String getSegundo_apellido() {
        return segundo_apellido;
    }

    public void setSegundo_apellido(String segundo_apellido) {
        this.segundo_apellido = segundo_apellido;
    }

    public Long getIdmodificacion() {
        return idmodificacion;
    }

    public void setIdmodificacion(Long idmodificacion) {
        this.idmodificacion = idmodificacion;
    }

    public String getTipo_persona() {
        return tipo_persona;
    }

    public void setTipo_persona(String tipo_persona) {
        this.tipo_persona = tipo_persona;
    }

    public String getSolicitud_departamento() {
        return solicitud_departamento;
    }

    public void setSolicitud_departamento(String solicitud_departamento) {
        this.solicitud_departamento = solicitud_departamento;
    }

    public String getPoder() {
        return poder;
    }

    public void setPoder(String poder) {
        this.poder = poder;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    @Override
    public String toString() {
        if (nombre_razonsocial == null) {
            nombre_razonsocial = "";
        }
        if (primer_apellido == null) {
            primer_apellido = "";
        }
        if (segundo_apellido == null) {
            segundo_apellido = "";
        }
        if (numero_documento == null) {
            numero_documento = "";
        }
        if (tipo_documento == null) {
            tipo_documento = "";
        }
        if (lugar_expedicion == null) {
            lugar_expedicion = "";
        }
        if (pais == null) {
            pais = "";
        }
        if (genero == null) {
            genero = "";
        }
        if (solicitud_departamento == null) {
            solicitud_departamento = "";
        }
        if (poder == null) {
            poder = "";
        }
        if (direccion == null) {
            direccion = "";
        }
        if (telefono == null) {
            telefono = "";
        }
        if (email == null) {
            email = "";
        }
        if (fax == null) {
            fax = "";
        }
        if (celular == null) {
            celular = "";
        }        
        return "|" + "idsolicitanteapoderado=" + idsolicitanteapoderado
                + "|" + "idmodificacion=" + idmodificacion
            //    + "|" + "idLogTrans=" + idLogTrans
                + "|" + "tipo_titular=" + tipo_titular
                + "|" + "tipo_persona=" + tipo_persona
                + "|" + "nombre_razonsocial=" + nombre_razonsocial
                + "|" + "primer_apellido=" + primer_apellido
                + "|" + "segundo_apellido=" + segundo_apellido
                + "|" + "numero_documento=" + numero_documento
                + "|" + "tipo_documento=" + tipo_documento
                + "|" + "lugar_expedicion=" + lugar_expedicion
                + "|" + "pais=" + pais
                + "|" + "genero=" + genero
                + "|" + "solicitud_departamento=" + solicitud_departamento
                + "|" + "poder=" + poder
                + "|" + "direccion=" + direccion
                + "|" + "telefono=" + telefono
                + "|" + "email=" + email
                + "|" + "fax=" + fax
                + "|" + "celular=" + celular
                + "|" + "estado=" + estado + "|";
    }

    public Long getIdSipi() {
        return idSipi;
    }

    public void setIdSipi(Long idSipi) {
        this.idSipi = idSipi;
    }    
}
