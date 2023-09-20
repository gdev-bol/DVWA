/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.bdimagen.entidad;

import java.io.Serializable;

/**
 *
 * @author levi
 */
public class RenArchivoDig implements Serializable{
     private Long idarchivodig;
    private Long iddocumento;
    private byte[] archivo ;

    public Long getIdarchivodig() {
        return idarchivodig;
    }

    public void setIdarchivodig(Long idarchivodig) {
        this.idarchivodig = idarchivodig;
    }

    public Long getIddocumento() {
        return iddocumento;
    }

    public void setIddocumento(Long iddocumento) {
        this.iddocumento = iddocumento;
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }
    
    
    
}
