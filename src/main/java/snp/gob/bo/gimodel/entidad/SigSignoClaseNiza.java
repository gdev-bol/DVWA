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
 * @author Eddy Valero
 * @version 1.0, 26/08/2016
 */
public class SigSignoClaseNiza implements Serializable, Cloneable{

    Long idSignoClaseNiza;
    Long idSignoMarca;
    Long idClaseNiza;
    Long idLogTrans;
    Long numeroClaseNiza;
    String listaProducto;
    String estado;
    Long idSipi;

    public Long getIdSignoClaseNiza() {
        return idSignoClaseNiza;
    }

    public void setIdSignoClaseNiza(Long idSignoClaseNiza) {
        this.idSignoClaseNiza = idSignoClaseNiza;
    }

    public Long getIdSignoMarca() {
        return idSignoMarca;
    }

    public void setIdSignoMarca(Long idSignoMarca) {
        this.idSignoMarca = idSignoMarca;
    }

    public Long getIdClaseNiza() {
        return idClaseNiza;
    }

    public void setIdClaseNiza(Long idClaseNiza) {
        this.idClaseNiza = idClaseNiza;
    }

    public Long getIdLogTrans() {
        return idLogTrans;
    }

    public void setIdLogTrans(Long idLogTrans) {
        this.idLogTrans = idLogTrans;
    }

    public Long getNumeroClaseNiza() {
        return numeroClaseNiza;
    }

    public void setNumeroClaseNiza(Long numeroClaseNiza) {
        this.numeroClaseNiza = numeroClaseNiza;
    }

    public String getListaProducto() {
        return listaProducto;
    }

    public void setListaProducto(String listaProducto) {
        this.listaProducto = listaProducto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
    public String toString(){
    
        return "|"+"idSignoClaseNiza="+idSignoClaseNiza
                +"|"+"idSignoMarca="+idSignoMarca
                +"|"+"idClaseNiza="+idClaseNiza
                +"|"+"idLogTrans="+idLogTrans
                +"|"+"numeroClaseNiza="+numeroClaseNiza
                +"|"+"listaProducto="+listaProducto
                +"|"+"estado="+estado+"|";
    
    }

	public Long getIdSipi() {
		return idSipi;
	}

	public void setIdSipi(Long idSipi) {
		this.idSipi = idSipi;
	}
    
    
    
    
    
    
}
