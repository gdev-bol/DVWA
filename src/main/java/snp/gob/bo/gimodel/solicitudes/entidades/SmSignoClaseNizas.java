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
public class SmSignoClaseNizas {

    Long id;
    Long claseNizaId;
    Long signoMarcaId;
    Long numeroClaseNiza;
    String listaProductos;
    Long idPadre;

    public Long getClaseNizaId() {
        return claseNizaId;
    }

    public void setClaseNizaId(Long claseNizaId) {
        this.claseNizaId = claseNizaId;
    }

    public Long getSignoMarcaId() {
        return signoMarcaId;
    }

    public void setSignoMarcaId(Long signoMarcaId) {
        this.signoMarcaId = signoMarcaId;
    }

    public Long getNumeroClaseNiza() {
        return numeroClaseNiza;
    }

    public void setNumeroClaseNiza(Long numeroClaseNiza) {
        this.numeroClaseNiza = numeroClaseNiza;
    }

    public String getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(String listaProductos) {
        this.listaProductos = listaProductos;
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
