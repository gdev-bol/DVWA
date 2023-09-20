/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.entidad;

import java.io.Serializable;


/**
 *
 * @author Eddy Valero
 * @version 1.0, 29/08/2016
 */
public class SigSolicitanteApoderado implements Serializable,Cloneable {
    
    Long idSolicitanteApoderado;
    Long idSignoMarca;
    Long idLogTrans;
    String tipoTitular;
    String tipoPersona;
    String nombreRazonSocial;
    String primerApellido;
    String segundoApellido;
        String numeroDocumento;
        String tipoDocumento;
    String lugarExpedicion;
    String pais;
    String genero;
    String solicitudDepartamento;
    String poder;
    String direccion;
    String telefono;
    String celular;
    String email;
    String fax;
    String estado;
    Long idSipi;

    public Long getIdSolicitanteApoderado() {
        return idSolicitanteApoderado;
    }

    public void setIdSolicitanteApoderado(Long idSolicitanteApoderado) {
        this.idSolicitanteApoderado = idSolicitanteApoderado;
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

    public String getTipoTitular() {
        return tipoTitular;
    }

    public void setTipoTitular(String tipoTitular) {
        this.tipoTitular = tipoTitular;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public String getNombreRazonSocial() {
        return nombreRazonSocial;
    }

    public void setNombreRazonSocial(String nombreRazonSocial) {
        this.nombreRazonSocial = nombreRazonSocial;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getLugarExpedicion() {
        return lugarExpedicion;
    }

    public void setLugarExpedicion(String lugarExpedicion) {
        this.lugarExpedicion = lugarExpedicion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSolicitudDepartamento() {
        return solicitudDepartamento;
    }

    public void setSolicitudDepartamento(String solicitudDepartamento) {
        this.solicitudDepartamento = solicitudDepartamento;
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

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        Object clone = null;
        try
        {
            clone = super.clone();
        } 
        catch(CloneNotSupportedException e)
        {
            // No deberia ocurrir
        }
        return clone;
    }
    
    @Override
    public String toString(){
    return   "|"+"idSolicitanteApoderado="+idSolicitanteApoderado
            +"|"+"idSignoMarca="+idSignoMarca
//            +"|"+"idLogTrans="+idLogTrans
            +"|"+"tipoTitular="+tipoTitular
            +"|"+"tipoPersona="+tipoPersona
            +"|"+"nombreRazonSocial="+nombreRazonSocial
            +"|"+"primerApellido="+primerApellido
            +"|"+"segundoApellido="+segundoApellido
            +"|"+"numeroDocumento="+numeroDocumento
            +"|"+"tipoDocumento="+tipoDocumento
            +"|"+"lugarExpedicion="+lugarExpedicion
            +"|"+"pais="+pais
            +"|"+"genero="+genero
            +"|"+"solicitudDepartamento="+solicitudDepartamento
            +"|"+"poder="+poder
            +"|"+"direccion="+direccion
            +"|"+"telefono="+telefono
            +"|"+"celular="+celular
            +"|"+"email="+email
            +"|"+"fax="+fax
            +"|"+"estado="+estado+"|";
    }

	public Long getIdSipi() {
		return idSipi;
	}

	public void setIdSipi(Long idSipi) {
		this.idSipi = idSipi;
	}
    
}
