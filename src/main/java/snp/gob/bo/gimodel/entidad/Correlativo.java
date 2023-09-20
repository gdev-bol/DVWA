/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.entidad;

/**
 * Entidad responsable de la Tabla regional
 *
 * @author Chano Rojas
 * @see DosificacionMapper
 * @version 1.0, 11/08/2016
 */
public class Correlativo {
 Long idcorrelativo;
 Long  idlogtrans;
  String nombre_criterio;
  int incremento;
  int ultimo_numero_asignado;
  String acronimo;
  String separador;
  int gestion;
  String estado;

    public Long getIdcorrelativo() {
        return idcorrelativo;
    }

    public void setIdcorrelativo(Long idcorrelativo) {
        this.idcorrelativo = idcorrelativo;
    }

    public Long getIdlogtrans() {
        return idlogtrans;
    }

    public void setIdlogtrans(Long idlogtrans) {
        this.idlogtrans = idlogtrans;
    }

    public String getNombre_criterio() {
        return nombre_criterio;
    }

    public void setNombre_criterio(String nombre_criterio) {
        this.nombre_criterio = nombre_criterio;
    }

    public int getIncremento() {
        return incremento;
    }

    public void setIncremento(int incremento) {
        this.incremento = incremento;
    }

    public int getUltimo_numero_asignado() {
        return ultimo_numero_asignado;
    }

    public void setUltimo_numero_asignado(int ultimo_numero_asignado) {
        this.ultimo_numero_asignado = ultimo_numero_asignado;
    }

    public String getAcronimo() {
        return acronimo;
    }

    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    public String getSeparador() {
        return separador;
    }

    public void setSeparador(String separador) {
        this.separador = separador;
    }

    public int getGestion() {
        return gestion;
    }

    public void setGestion(int gestion) {
        this.gestion = gestion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
  
  
  
}