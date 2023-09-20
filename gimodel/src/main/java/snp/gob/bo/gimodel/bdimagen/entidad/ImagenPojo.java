/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.bdimagen.entidad;

import snp.gob.bo.gimodel.entidad.*;
import java.io.Serializable;

/**
 *
 * @author eddy
 * @version 1.0, 13/10/2016
 */
public class ImagenPojo implements Serializable{
    SigLogoTipo sigLogoTipo = new SigLogoTipo();
    SigImagen sigImagen = new SigImagen();

    public SigLogoTipo getSigLogoTipo() {
        return sigLogoTipo;
    }

    public void setSigLogoTipo(SigLogoTipo sigLogoTipo) {
        this.sigLogoTipo = sigLogoTipo;
    }

    public SigImagen getSigImagen() {
        return sigImagen;
    }

    public void setSigImagen(SigImagen sigImagen) {
        this.sigImagen = sigImagen;
    }
    
    
}
