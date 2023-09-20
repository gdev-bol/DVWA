/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.bean.publicacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author roco
 */
public class DatosTipoSignoReporBean implements Serializable {

    private int numeroSeccion;
    private String tipoSeccion;
    private String tiposSignos;
    private List<TramitesReporBean> datosDeTipoSigno = new ArrayList();
    private List<TramitesReporBean> datosDeTipoSignoMixto = new ArrayList();

    public int getNumeroSeccion() {
        return numeroSeccion;
    }

    public void setNumeroSeccion(int numeroSeccion) {
        this.numeroSeccion = numeroSeccion;
    }

    public String getTipoSeccion() {
        return tipoSeccion;
    }

    public void setTipoSeccion(String tipoSeccion) {
        this.tipoSeccion = tipoSeccion;
    }

    public String getTiposSignos() {
        return tiposSignos;
    }

    public void setTiposSignos(String tiposSignos) {
        this.tiposSignos = tiposSignos;
    }

    public List<TramitesReporBean> getDatosDeTipoSigno() {
        return datosDeTipoSigno;
    }

    public void setDatosDeTipoSigno(List<TramitesReporBean> datosDeTipoSigno) {
        this.datosDeTipoSigno = datosDeTipoSigno;
    }

    public List<TramitesReporBean> getDatosDeTipoSignoMixto() {
        return datosDeTipoSignoMixto;
    }

    public void setDatosDeTipoSignoMixto(List<TramitesReporBean> datosDeTipoSignoMixto) {
        this.datosDeTipoSignoMixto = datosDeTipoSignoMixto;
    }

    public JRDataSource getDatosTipoSigno() {
        return new JRBeanCollectionDataSource(datosDeTipoSigno);
    }

    public JRDataSource getDatosTipoSignoMixto() {
        return new JRBeanCollectionDataSource(datosDeTipoSignoMixto);
    }

}
