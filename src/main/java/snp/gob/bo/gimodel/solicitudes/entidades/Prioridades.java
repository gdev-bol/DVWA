/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.solicitudes.entidades;

import snp.gob.bo.gimodel.entidad.*;
import java.util.Date;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 23/08/2016
 */
public class Prioridades {

    Long id;
    Long formularioId;
    String tipoPrioridad;
    String tipoInteres;
    String nombre;
    Date fecha;
    String lugar;
    Long idPadre;

    public Long getFormularioId() {
        return formularioId;
    }

    public void setFormularioId(Long formularioId) {
        this.formularioId = formularioId;
    }

    public String getTipoPrioridad() {
        return tipoPrioridad;
    }

    public void setTipoPrioridad(String tipoPrioridad) {
        this.tipoPrioridad = tipoPrioridad;
    }

    public String getTipoInteres() {
        return tipoInteres;
    }

    public void setTipoInteres(String tipoInteres) {
        this.tipoInteres = tipoInteres;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

	public Long getIdPadre() {
		return idPadre;
	}

	public void setIdPadre(Long idPadre) {
		this.idPadre = idPadre;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
