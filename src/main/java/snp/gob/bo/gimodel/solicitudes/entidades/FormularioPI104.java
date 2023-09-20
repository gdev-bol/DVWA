/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.solicitudes.entidades;

import java.util.List;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 26/10/2016
 */
public class FormularioPI104 {
    
    String mensaje;
    Formularios formularios;
    SignoMarcas signoMarca;
    LemasComerciales lemasComerciales;
    List <Solicitantes> solicitantes;
    List<Apoderados> apoderados;
    List<DireccionNotificaciones> direcciones;
    SolicitudRenovaciones solicitudRenovaciones;
    List<RenTipoSignos> renTipoSignos;
    List<RenTitularRegistrados> renTitularRegistrados;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Formularios getFormularios() {
        return formularios;
    }

    public void setFormularios(Formularios formularios) {
        this.formularios = formularios;
    }
    
    public SignoMarcas getSignoMarca() {
        return signoMarca;
    }

    public void setSignoMarca(SignoMarcas signoMarca) {
        this.signoMarca = signoMarca;
    }

    public LemasComerciales getLemasComerciales() {
        return lemasComerciales;
    }

    public void setLemasComerciales(LemasComerciales lemasComerciales) {
        this.lemasComerciales = lemasComerciales;
    }
    
    public List<Solicitantes> getSolicitantes() {
        return solicitantes;
    }

    public void setSolicitantes(List<Solicitantes> solicitantes) {
        this.solicitantes = solicitantes;
    }

    public List<Apoderados> getApoderados() {
        return apoderados;
    }

    public void setApoderados(List<Apoderados> apoderados) {
        this.apoderados = apoderados;
    }

    public List<DireccionNotificaciones> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<DireccionNotificaciones> direcciones) {
        this.direcciones = direcciones;
    }

    public List<RenTipoSignos> getRenTipoSignos() {
        return renTipoSignos;
    }

    public void setRenTipoSignos(List<RenTipoSignos> renTipoSignos) {
        this.renTipoSignos = renTipoSignos;
    }
    
    public List<RenTitularRegistrados> getRenTitularRegistrados() {
        return renTitularRegistrados;
    }

    public void setRenTitularRegistrados(List<RenTitularRegistrados> renTitularRegistrados) {
        this.renTitularRegistrados = renTitularRegistrados;
    }

    public SolicitudRenovaciones getSolicitudRenovaciones() {
        return solicitudRenovaciones;
    }

    public void setSolicitudRenovaciones(SolicitudRenovaciones solicitudRenovaciones) {
        this.solicitudRenovaciones = solicitudRenovaciones;
    }
    
}
