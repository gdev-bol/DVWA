/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.solicitudes.entidades;

import java.util.List;

/**
 *
 * @author luis_angel
 */
public class FormularioPI105 {
    
    String mensaje;
    Long sm;
    Integer nropublicacion;
    String marca;
    String sinmarca;
    String tipoFormulario;
    String nroopoandina;
    Formularios formularios;
    SignoMarcas signoMarca;
    OpoMarcaDemandanteExt opomarcaDemandanteExt;
    OpoMarcaDemandadaExt opoMarcademandadaExt;
    List <Solicitantes> solicitantes;
    List <Apoderados> apoderados;
    List <DireccionNotificaciones> direcciones;
    List <SmTipoSignos> smTipoSignos;
    List <String> smTipoSignosRespuestas;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Long getSm() {
        return sm;
    }

    public void setSm(Long sm) {
        this.sm = sm;
    }

    public Integer getNropublicacion() {
        return nropublicacion;
    }

    public void setNropublicacion(Integer nropublicacion) {
        this.nropublicacion = nropublicacion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getSinmarca() {
        return sinmarca;
    }

    public void setSinmarca(String sinmarca) {
        this.sinmarca = sinmarca;
    }

    public String getTipoFormulario() {
        return tipoFormulario;
    }

    public void setTipoFormulario(String tipoFormulario) {
        this.tipoFormulario = tipoFormulario;
    }

    public String getNroopoandina() {
        return nroopoandina;
    }

    public void setNroopoandina(String nroopoandina) {
        this.nroopoandina = nroopoandina;
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

    public OpoMarcaDemandanteExt getOpomarcaDemandanteExt() {
        return opomarcaDemandanteExt;
    }

    public void setOpomarcaDemandanteExt(OpoMarcaDemandanteExt opomarcaDemandanteExt) {
        this.opomarcaDemandanteExt = opomarcaDemandanteExt;
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

    public OpoMarcaDemandadaExt getOpoMarcademandadaExt() {
        return opoMarcademandadaExt;
    }

    public void setOpoMarcademandadaExt(OpoMarcaDemandadaExt opoMarcademandadaExt) {
        this.opoMarcademandadaExt = opoMarcademandadaExt;
    }

    
    
}
