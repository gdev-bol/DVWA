/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.entidad;

import java.io.Serializable;
import java.util.List;
/**
 *
 * @author Luis Angel Quispe Limachi
 *  * @see OpoObjetoDmteDmdoNotiSoliapo 
 * @version 1.0, 06/09/2016
 */
public class OpoObjetoDmteDmdoNotiSoliapo implements Serializable{
    
    Oposicion objopsicion;
    OpoMarcademandada objmarcademandada;
    OpoMarcademandante objmarcademandante;
    OpoNotificacion objnotificaciondmda;
    OpoNotificacion objnotificaciondmte;
    List<OpoSolicitanteapoderado> listasolidmdo;
    List<OpoSolicitanteapoderado> listaapodmte;
    List<OpoSolicitanteapoderado> listaapodmdo;
    List<OpoSolicitanteapoderado> listasolidmte;
    
    public Oposicion getObjopsicion() {
        return objopsicion;
    }

    public void setObjopsicion(Oposicion objopsicion) {
        this.objopsicion = objopsicion;
    }

    public OpoMarcademandada getObjmarcademandada() {
        return objmarcademandada;
    }

    public void setObjmarcademandada(OpoMarcademandada objmarcademandada) {
        this.objmarcademandada = objmarcademandada;
    }

    public OpoMarcademandante getObjmarcademandante() {
        return objmarcademandante;
    }

    public void setObjmarcademandante(OpoMarcademandante objmarcademandante) {
        this.objmarcademandante = objmarcademandante;
    }

    public OpoNotificacion getObjnotificaciondmda() {
        return objnotificaciondmda;
    }

    public void setObjnotificaciondmda(OpoNotificacion objnotificaciondmda) {
        this.objnotificaciondmda = objnotificaciondmda;
    }

    public OpoNotificacion getObjnotificaciondmte() {
        return objnotificaciondmte;
    }

    public void setObjnotificaciondmte(OpoNotificacion objnotificaciondmte) {
        this.objnotificaciondmte = objnotificaciondmte;
    }

    public List<OpoSolicitanteapoderado> getListasolidmdo() {
        return listasolidmdo;
    }

    public void setListasolidmdo(List<OpoSolicitanteapoderado> listasolidmdo) {
        this.listasolidmdo = listasolidmdo;
    }

    public List<OpoSolicitanteapoderado> getListaapodmte() {
        return listaapodmte;
    }

    public void setListaapodmte(List<OpoSolicitanteapoderado> listaapodmte) {
        this.listaapodmte = listaapodmte;
    }

    public List<OpoSolicitanteapoderado> getListaapodmdo() {
        return listaapodmdo;
    }

    public void setListaapodmdo(List<OpoSolicitanteapoderado> listaapodmdo) {
        this.listaapodmdo = listaapodmdo;
    }

    public List<OpoSolicitanteapoderado> getListasolidmte() {
        return listasolidmte;
    }

    public void setListasolidmte(List<OpoSolicitanteapoderado> listasolidmte) {
        this.listasolidmte = listasolidmte;
    }

   
    
    
    
}
