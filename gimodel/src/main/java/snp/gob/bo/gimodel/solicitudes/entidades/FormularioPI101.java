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
 * @version 1.0, 23/08/2016
 */
public class FormularioPI101 {
    
    String mensaje;
    Formularios formularios;
    SignoMarcas signoMarca;
    List <Solicitantes> solicitantes;
    List <Apoderados> apoderados;
    List <DireccionNotificaciones> direcciones;
    List <Prioridades> prioridades;
    List <SmSignoClaseNizas> smSignoClaseNizases;
    List <SmTipoSignos> smTipoSignos;
    List <String> smTipoSignosRespuestas;
    List <Logotipos> logotipos;

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

    public List<Prioridades> getPrioridades() {
        return prioridades;
    }

    public void setPrioridades(List<Prioridades> prioridades) {
        this.prioridades = prioridades;
    }

    public List<SmSignoClaseNizas> getSmSignoClaseNizases() {
        return smSignoClaseNizases;
    }

    public void setSmSignoClaseNizases(List<SmSignoClaseNizas> smSignoClaseNizases) {
        this.smSignoClaseNizases = smSignoClaseNizases;
    }

    public List<SmTipoSignos> getSmTipoSignos() {
        return smTipoSignos;
    }

    public void setSmTipoSignos(List<SmTipoSignos> smTipoSignos) {
        this.smTipoSignos = smTipoSignos;
    }

    public List<String> getSmTipoSignosRespuestas() {
        return smTipoSignosRespuestas;
    }

    public void setSmTipoSignosRespuestas(List<String> smTipoSignosRespuestas) {
        this.smTipoSignosRespuestas = smTipoSignosRespuestas;
    }
    
    

    public List<Logotipos> getLogotipos() {
        return logotipos;
    }

    public void setLogotipos(List<Logotipos> logotipos) {
        this.logotipos = logotipos;
    }
    
    
    

    
    
    
    
    
    
    
}
