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
public class ResumenConceptoDiaPojo {
    private String fecha;
    private String total;
    private List<ResumenConceptoDetallePojo>detalleDataR = new ArrayList();

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<ResumenConceptoDetallePojo> getDetalleDataR() {
        return detalleDataR;
    }

    public void setDetalleDataR(List<ResumenConceptoDetallePojo> detalleDataR) {
        this.detalleDataR = detalleDataR;
    }
    
    public JRDataSource getDetalleData() {
        return new JRBeanCollectionDataSource(detalleDataR);
    }
    
}
