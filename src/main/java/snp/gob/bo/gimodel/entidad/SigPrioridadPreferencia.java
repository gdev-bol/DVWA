/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.entidad;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 29/08/2016
 */
public class SigPrioridadPreferencia {

    Long idPrioridadPreferencia;
    Long idSignoMarca;
    Long idLogTrans;
    String tipoPrioridad;
    String tipoInteres;
    String nombreNumero;
    Date fecha;
    String lugar;
    String estado;
    Long idSipi;

    public Long getIdPrioridadPreferencia() {
        return idPrioridadPreferencia;
    }

    public void setIdPrioridadPreferencia(Long idPrioridadPreferencia) {
        this.idPrioridadPreferencia = idPrioridadPreferencia;
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

    public String getNombreNumero() {
        return nombreNumero;
    }

    public void setNombreNumero(String nombreNumero) {
        this.nombreNumero = nombreNumero;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString(){
        
        //formatear la fecha, mediante estas instrucciones no hara diferencia entre lo que viene
        //de la base y lo que viene de la vista
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        
        String fecha_formato;
        if (fecha!=null) {
            fecha_formato = formateador.format(fecha);
        }else{
            fecha_formato = "";
        }
        
    return   	"|" + "idPrioridadPreferencia =" + idPrioridadPreferencia
                + "|" + "idSignoMarca =" + idSignoMarca
                + "|" + "idLogTrans =" + idLogTrans
                + "|" + "tipoPrioridad =" + tipoPrioridad
                + "|" + "tipoInteres =" + tipoInteres
                + "|" + "nombreNumero =" + nombreNumero
                + "|" + "fecha =" + fecha_formato
                + "|" + "lugar =" + lugar
                + "|" + "estado =" + estado;
    }

	public Long getIdSipi() {
		return idSipi;
	}

	public void setIdSipi(Long idSipi) {
		this.idSipi = idSipi;
	}
	
}
