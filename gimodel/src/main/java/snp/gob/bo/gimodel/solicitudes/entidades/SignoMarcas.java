/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.solicitudes.entidades;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 23/08/2016
 */
public class SignoMarcas {

    Long id;
    String marca;
    String tipoGenero;
    String descripcionLogo;
    String estado;
    Long formularioId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipoGenero() {
        return tipoGenero;
    }

    public void setTipoGenero(String tipoGenero) {
        this.tipoGenero = tipoGenero;
    }

    public String getDescripcionLogo() {
        return descripcionLogo;
    }

    public void setDescripcionLogo(String descripcionLogo) {
        this.descripcionLogo = descripcionLogo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getFormularioId() {
        return formularioId;
    }

    public void setFormularioId(Long formularioId) {
        this.formularioId = formularioId;
    }

    
}
