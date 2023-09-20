/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.recaudacionesReportes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Ruben Ramirez
 */
public class TasaPorUnidadPojo implements Serializable {

    private String titulo;
    private List<TasaPojo> tasaPojo = new ArrayList<>();
    private Integer numDia;
    
    public JRDataSource getTasa() {
        return new JRBeanCollectionDataSource(tasaPojo);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<TasaPojo> getTasaPojo() {
        return tasaPojo;
    }

    public void setTasaPojo(List<TasaPojo> tasaPojo) {
        this.tasaPojo = tasaPojo;
    }

    public Integer getNumDia() {
        return numDia;
    }

    public void setNumDia(Integer numDia) {
        this.numDia = numDia;
    }
}
