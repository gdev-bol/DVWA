/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. 
 */
package snp.gob.bo.entidades.recaudacionesReportes;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
/**
 *
 * @author Ruben Ramirez
 */
public class ResumenConceptoMesPojo {
    private String mes;
    private String mesGestion;
    private double totalMes;
    private List<ResumenConceptoDiaPojo>diaDataR = new ArrayList();

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public double getTotalMes() {
        return totalMes;
    }

    public void setTotalMes(double totalMes) {
        this.totalMes = totalMes;
    }

    public List<ResumenConceptoDiaPojo> getDiaDataR() {
        return diaDataR;
    }

    public void setDiaDataR(List<ResumenConceptoDiaPojo> diaDataR) {
        this.diaDataR = diaDataR;
    }

    public JRDataSource getDiaData() {
        return new JRBeanCollectionDataSource(diaDataR);
    }

    public String getMesGestion() {
        return mesGestion;
    }

    public void setMesGestion(String mesGestion) {
        this.mesGestion = mesGestion;
    }
    
    
}
