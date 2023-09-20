/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.entidad;

import java.io.Serializable;


/**
 * Entidad que recuperar todos los objetos eliminados de cualquier tabla
 * 
 * @author Eddy Valero
 * @version 1.0, 28/11/2016
 */
public class ObjetoEliminadoGenerico implements Serializable,Cloneable {
    
    Long id;
    String objetoEliminado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObjetoEliminado() {
        return objetoEliminado;
    }

    public void setObjetoEliminado(String objetoEliminado) {
        this.objetoEliminado = objetoEliminado;
    }
    
}
