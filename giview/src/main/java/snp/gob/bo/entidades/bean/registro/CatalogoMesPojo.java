/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.bean.registro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Ruben Ramirez
 */
public class CatalogoMesPojo implements Serializable {

    private String mesGestion;
    private List<CatalogoDetallePojo> datosCatalogoDetallePojo = new ArrayList();

    public String getMesGestion() {
        return mesGestion;
    }

    public void setMesGestion(String mesGestion) {
        this.mesGestion = mesGestion;
    }

    public List<CatalogoDetallePojo> getDatosCatalogoDetallePojo() {
        return datosCatalogoDetallePojo;
    }

    public void setDatosCatalogoDetallePojo(List<CatalogoDetallePojo> datosCatalogoDetallePojo) {
        this.datosCatalogoDetallePojo = datosCatalogoDetallePojo;
    }

    public JRDataSource getDatosCatalogoDetalle() {
        return new JRBeanCollectionDataSource(datosCatalogoDetallePojo);
    }

}
