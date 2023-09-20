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
public class ModDireccionDeNotificacion implements Serializable, Cloneable {

    Long iddirecciondenotificacion;
    Long idmodificacion;
    Long idlogtrans;
    String ciudad_notificacion;
    String zona_barrio;
    String avenida_calle;
    String numero;
    String edificio;
    String piso;
    String departamento;
    String correo_electronico;
    String telefono;
    String celular;
    String estado;
    String referencia_direccion;

    public Long getIddirecciondenotificacion() {
        return iddirecciondenotificacion;
    }

    public void setIddirecciondenotificacion(Long iddirecciondenotificacion) {
        this.iddirecciondenotificacion = iddirecciondenotificacion;
    }

    public Long getIdmodificacion() {
        return idmodificacion;
    }

    public void setIdmodificacion(Long idmodificacion) {
        this.idmodificacion = idmodificacion;
    }

    public Long getIdlogtrans() {
        return idlogtrans;
    }

    public void setIdlogtrans(Long idlogtrans) {
        this.idlogtrans = idlogtrans;
    }

    public String getCiudad_notificacion() {
        return ciudad_notificacion;
    }

    public void setCiudad_notificacion(String ciudad_notificacion) {
        this.ciudad_notificacion = ciudad_notificacion;
    }

    public String getZona_barrio() {
        return zona_barrio;
    }

    public void setZona_barrio(String zona_barrio) {
        this.zona_barrio = zona_barrio;
    }

    public String getAvenida_calle() {
        return avenida_calle;
    }

    public void setAvenida_calle(String avenida_calle) {
        this.avenida_calle = avenida_calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEdificio() {
        return edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getReferencia_direccion() {
        return referencia_direccion;
    }

    public void setReferencia_direccion(String referencia_direccion) {
        this.referencia_direccion = referencia_direccion;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            // No deberia ocurrir
        }
        return clone;
    }

    @Override
    public String toString() {
        //AQUI MEJORAR PARA LOS NULOS 
        if (ciudad_notificacion == null) {
            ciudad_notificacion = "";
        }
        if (zona_barrio == null) {
            zona_barrio = "";
        }
        if (avenida_calle == null) {
            avenida_calle = "";
        }
        if (numero == null) {
            numero = "";
        }
        if (edificio == null) {
            edificio = "";
        }
        if (piso == null) {
            piso = "";
        }
        if (departamento == null) {
            departamento = "";
        }
        if (correo_electronico == null) {
            correo_electronico = "";
        }
        if (referencia_direccion == null) {
            referencia_direccion = "";
        }
        if (telefono == null) {
            telefono = "";
        }
        if (celular == null) {
            celular = "";
        }
        return "|" + "iddirecciondenotificacion=" + iddirecciondenotificacion
                + "|" + "idmodificacion=" + idmodificacion
                + "|" + "idlogtrans=" + idlogtrans
                + "|" + "ciudad_notificacion=" + ciudad_notificacion
                + "|" + "zona_barrio=" + zona_barrio
                + "|" + "avenida_calle=" + avenida_calle
                + "|" + "numero=" + numero
                + "|" + "edificio=" + edificio
                + "|" + "piso=" + piso
                + "|" + "departamento=" + departamento
                + "|" + "correo_electronico=" + correo_electronico
                + "|" + "referencia_direccion=" + referencia_direccion
                + "|" + "telefono=" + telefono
                + "|" + "celular=" + celular
                + "|" + "estado=" + estado + "|";
    }
}
