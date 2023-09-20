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
public class ReciboPorPeriodoDiaPojo {
    private String fechaRecibo;
    private List<ReciboPorPeriodoDetallePojo> listaRecibo = new ArrayList();

    public String getFechaRecibo() {
        return fechaRecibo;
    }

    public void setFechaRecibo(String fechaRecibo) {
        this.fechaRecibo = fechaRecibo;
    }

    public List<ReciboPorPeriodoDetallePojo> getListaRecibo() {
        return listaRecibo;
    }

    public void setListaRecibo(List<ReciboPorPeriodoDetallePojo> listaRecibo) {
        this.listaRecibo = listaRecibo;
    }

    public JRDataSource getListaReciboDia() {
        return new JRBeanCollectionDataSource(this.listaRecibo);
    }
    
}
