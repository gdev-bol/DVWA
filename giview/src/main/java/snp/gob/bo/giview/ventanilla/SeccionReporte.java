/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.giview.ventanilla;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Ruben Nico Ramirez
 * 
 * @version 1.0, 07/11/2016
 */
public class SeccionReporte {
    
    private String titulo;
    private List<SubseccionReporte> datosSubseccion = new ArrayList<SubseccionReporte>();

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<SubseccionReporte> getDatosSubseccion() {
        return datosSubseccion;
    }

    public void setDatosSubseccion(List<SubseccionReporte> datosSubseccion) {
        this.datosSubseccion = datosSubseccion;
    }

    public JRDataSource getSubseccion() {
        return new JRBeanCollectionDataSource(datosSubseccion);
    }
    
}
