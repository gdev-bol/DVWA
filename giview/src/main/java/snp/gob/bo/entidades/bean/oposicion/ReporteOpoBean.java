/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.bean.oposicion;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.gimodel.entidad.OpoMarcademandada;
import snp.gob.bo.gimodel.entidad.OpoMarcademandante;
import snp.gob.bo.gimodel.entidad.OpoSolicitanteapoderado;
import snp.gob.bo.gimodel.entidad.Oposicion;
import snp.gob.bo.gimodel.servicio.OpoMarcademandadaService;
import snp.gob.bo.gimodel.servicio.OpoMarcademandanteService;
import snp.gob.bo.gimodel.servicio.OpoSolicitanteaopderadoService;
import snp.gob.bo.gimodel.servicio.OposicionService;

/**
 *
 * @author susana
 */
@ManagedBean(name = "reporteOpoBean")
@ViewScoped
public class ReporteOpoBean extends AbstractManagedBean implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Definicion de servicios y atributos">
    // variables de la vista
    private Integer numeroGaceta;
    private Boolean reporteRecibo = false;

    // parametros del reporte
    private Map<String, Object> parametros = new HashMap<>();
    private List<ReportePojo> listaReporte = new ArrayList<>();
    private List<Oposicion> listaOposicion = new ArrayList<>();

    private String cantidad;
    private Integer gaceta;
    private Integer numero;
    private Long publicacion;
    private String marcaDemandante;
    private String marcaDemandada;
    private String apoderadoDemandante;
    private String apoderadoDemandada;
    private String firmaDemandante;
    private String firmaDemandada;

    @ManagedProperty(value = "#{oposicionService}")
    private OposicionService oposicionService;

    @ManagedProperty(value = "#{opoSolicitanteApoderadoService}")
    private OpoSolicitanteaopderadoService opoSolicitanteApoderadoService;

    @ManagedProperty(value = "#{opoMarcaDemandanteService}")
    private OpoMarcademandanteService opoMarcaDemandanteService;

    @ManagedProperty(value = "#{opoMarcaDemandadaService}")
    private OpoMarcademandadaService opoMarcaDemandadaService;

    ;

    /**
     * Creates a new instance of BuscadorPublicacionBean
     */
    public ReporteOpoBean() {
    }

    @PostConstruct
    public void ReporteOpoBeanInit() {
        try {

        } catch (Exception ex) {
            Logger.getLogger(ReporteOpoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * metodo para generacion de la lista de publicaciones de acuerdo al numero
     * de gaceta.
     *
     * Creado: Ruben Ramirez Fecha: 26/07/2017
     *
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void imprimir() throws JRException, IOException, Exception {

        listaOposicion = oposicionService.obtenerListadoOposicionXgaceta(numeroGaceta);

        int num;
        if (listaOposicion.size() > 0) {
            int i = 1;
            for (Oposicion item : listaOposicion) {
                OpoMarcademandada opoMarcaDemandada = opoMarcaDemandadaService.obtenerOpomarcademandadaobj(item.getIdoposicion());
                OpoMarcademandante opoMarcaDemandante = opoMarcaDemandanteService.obtieneelobjetodmtexidoposicion(item.getIdoposicion());
                List<OpoSolicitanteapoderado> listaSolDmte = opoSolicitanteApoderadoService.obtenerListadoSoliDmte(opoMarcaDemandante.getIdmarcademandante());
                List<OpoSolicitanteapoderado> listaApoDmte = opoSolicitanteApoderadoService.obtenerListadoApoDmte(opoMarcaDemandante.getIdmarcademandante());
                List<OpoSolicitanteapoderado> listaSolDmda = opoSolicitanteApoderadoService.obtenerListadoSoliDmda(opoMarcaDemandada.getIdmarcademandada());
                List<OpoSolicitanteapoderado> listaApoDmda = opoSolicitanteApoderadoService.obtenerListadoApodDmda(opoMarcaDemandada.getIdmarcademandada());

                String solDmte = "";
                int numListaSolDmte = listaSolDmte.size();
                if (numListaSolDmte > 0) {
                    if (numListaSolDmte == 1) {
                        OpoSolicitanteapoderado sol = listaSolDmte.get(0);
                        solDmte = devuelveNombreJuridicoONatural(sol.getNombre_razonsocial(), sol.getPrimer_apellido(), sol.getSegundo_apellido());
                    } else {
                        for (int j = 0; j < numListaSolDmte - 1; j++) {
                            OpoSolicitanteapoderado sol = listaSolDmte.get(j);
                            solDmte += devuelveNombreJuridicoONatural(sol.getNombre_razonsocial(), sol.getPrimer_apellido(), sol.getSegundo_apellido()) + ", ";
                        }
                        OpoSolicitanteapoderado sol = listaSolDmte.get(numListaSolDmte - 1);
                        solDmte += devuelveNombreJuridicoONatural(sol.getNombre_razonsocial(), sol.getPrimer_apellido(), sol.getSegundo_apellido());
                    }
                }

                String apoDmte = "";
                int numListaApoDmte = listaApoDmte.size();
                if (numListaApoDmte > 0) {
                    if (numListaApoDmte == 1) {
                        OpoSolicitanteapoderado sol = listaApoDmte.get(0);
                        apoDmte = devuelveNombreJuridicoONatural(sol.getNombre_razonsocial(), sol.getPrimer_apellido(), sol.getSegundo_apellido());
                    } else {
                        for (int j = 0; j < numListaApoDmte - 1; j++) {
                            OpoSolicitanteapoderado sol = listaApoDmte.get(j);
                            apoDmte += devuelveNombreJuridicoONatural(sol.getNombre_razonsocial(), sol.getPrimer_apellido(), sol.getSegundo_apellido()) + ", ";
                        }
                        OpoSolicitanteapoderado sol = listaApoDmte.get(numListaApoDmte - 1);
                        apoDmte += devuelveNombreJuridicoONatural(sol.getNombre_razonsocial(), sol.getPrimer_apellido(), sol.getSegundo_apellido());
                    }
                }

                String solDmda = "";
                int numListaSolDmda = listaSolDmda.size();
                if (numListaSolDmda > 0) {
                    if (numListaSolDmda == 1) {
                        OpoSolicitanteapoderado sol = listaSolDmda.get(0);
                        solDmda = devuelveNombreJuridicoONatural(sol.getNombre_razonsocial(), sol.getPrimer_apellido(), sol.getSegundo_apellido());
                    } else {
                        for (int j = 0; j < numListaSolDmda - 1; j++) {
                            OpoSolicitanteapoderado sol = listaSolDmda.get(j);
                            solDmda += devuelveNombreJuridicoONatural(sol.getNombre_razonsocial(), sol.getPrimer_apellido(), sol.getSegundo_apellido()) + ", ";
                        }
                        OpoSolicitanteapoderado sol = listaSolDmda.get(numListaSolDmda - 1);
                        solDmda += devuelveNombreJuridicoONatural(sol.getNombre_razonsocial(), sol.getPrimer_apellido(), sol.getSegundo_apellido());
                    }
                }

                String apoDmda = "";
                int numListaApoDmda = listaApoDmda.size();
                if (numListaApoDmda > 0) {
                    if (numListaApoDmda == 1) {
                        OpoSolicitanteapoderado apo = listaApoDmda.get(0);
                        apoDmda = devuelveNombreJuridicoONatural(apo.getNombre_razonsocial(), apo.getPrimer_apellido(), apo.getSegundo_apellido());
                    } else {
                        for (int j = 0; j < numListaApoDmda - 1; j++) {
                            OpoSolicitanteapoderado apo = listaApoDmda.get(j);
                            apoDmda += devuelveNombreJuridicoONatural(apo.getNombre_razonsocial(), apo.getPrimer_apellido(), apo.getSegundo_apellido()) + ", ";
                        }
                        OpoSolicitanteapoderado apo = listaApoDmda.get(numListaApoDmda - 1);
                        apoDmda += devuelveNombreJuridicoONatural(apo.getNombre_razonsocial(), apo.getPrimer_apellido(), apo.getSegundo_apellido());
                    }
                }

                num = listaReporte.size();
                if (num == 0) {
                    ReportePojo reporte = new ReportePojo();
                    reporte.setCantidad(i);
                    reporte.setGaceta(numeroGaceta);
                    reporte.setNumero(i);
                    reporte.setPublicacion(item.getNro_opo());
                    reporte.setMarcaDemandante(opoMarcaDemandante.getDmte_marca_lnv());
                    reporte.setMarcaDemandada(opoMarcaDemandada.getDmdo_marca_lnv());
                    reporte.setApoderadoDemandante(apoDmte);
                    reporte.setApoderadoDemandada(apoDmda);
                    reporte.setFirmaDemandante(solDmte);
                    reporte.setFirmaDemandada(solDmda);
                    listaReporte.add(reporte);
                } else {
                    ReportePojo reporteAnterior = listaReporte.get(num - 1);
                    if (Objects.equals(reporteAnterior.getPublicacion(), item.getNro_opo())) {
                        reporteAnterior.setCantidad(reporteAnterior.getCantidad() + 1);
                        reporteAnterior.setMarcaDemandante(reporteAnterior.getMarcaDemandante() + " / " + opoMarcaDemandante.getDmte_marca_lnv());
                        if (!apoDmte.isEmpty()) {
                            reporteAnterior.setApoderadoDemandante(reporteAnterior.getApoderadoDemandante() + " / " + apoDmte);
                        }
//                        if (!apoDmda.isEmpty()) {
//                            reporteAnterior.setApoderadoDemandada(reporteAnterior.getApoderadoDemandada() + " / " + apoDmda);
//                        }
                        if (!solDmte.isEmpty()) {
                            reporteAnterior.setFirmaDemandante(reporteAnterior.getFirmaDemandante() + " / " + solDmte);
                        }
//                        if (!solDmda.isEmpty()) {
//                            reporteAnterior.setFirmaDemandada(reporteAnterior.getFirmaDemandada() + " / " + solDmda);
//                        }
                        listaReporte.set(num - 1, reporteAnterior);                       
                    } else {
                        i++;
                        ReportePojo reportActual = new ReportePojo();
                        reportActual.setCantidad(1);
                        reportActual.setGaceta(numeroGaceta);
                        reportActual.setNumero(i);
                        reportActual.setPublicacion(item.getNro_opo());
                        reportActual.setMarcaDemandante(opoMarcaDemandante.getDmte_marca_lnv());
                        reportActual.setMarcaDemandada(opoMarcaDemandada.getDmdo_marca_lnv());
                        reportActual.setApoderadoDemandante(apoDmte);
                        reportActual.setApoderadoDemandada(apoDmda);
                        reportActual.setFirmaDemandante(solDmte);
                        reportActual.setFirmaDemandada(solDmda);
                        listaReporte.add(reportActual);
                    }
                }
            }

            String filename = "Reporte.xls";
            String jasperPath = "/template/oposiciones/reporte_uno.jasper";
            this.excel(parametros, jasperPath, listaReporte, filename);
            listaReporte.clear();
        }
    }

    public void excel(Map<String, Object> params, String jasperPath, List<?> dataSource, String fileName) throws JRException, IOException {

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
        response.setContentType("application/vnd.ms-excel");
        ServletOutputStream out = response.getOutputStream();
        String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(jasperPath);
        File file = new File(relativeWebPath);
        JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(dataSource, false);
        JasperPrint print = JasperFillManager.fillReport(file.getPath(), params, source);

        JRXlsExporter exporterXls = new JRXlsExporter();
        exporterXls.setParameter(JRXlsExporterParameter.JASPER_PRINT, print);
        exporterXls.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, out);
        exporterXls.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
        exporterXls.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
        exporterXls.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
        exporterXls.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        exporterXls.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
        exporterXls.exportReport();

        FacesContext.getCurrentInstance().responseComplete();
    }

    public void PDF(Map<String, Object> params, String jasperPath, List<?> dataSource, String fileName) throws JRException, IOException {
        String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(jasperPath);
        File file = new File(relativeWebPath);
        JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(dataSource, false);
        JasperPrint print = JasperFillManager.fillReport(file.getPath(), params, source);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(print, stream);
        FacesContext.getCurrentInstance().responseComplete();
    }

    public String devuelveNombreJuridicoONatural(String nombre, String primerApellido, String segundoApellido) {
        String campoNombreRazonSocial = " ";
        if (nombre != null && !nombre.equals("")) {
            campoNombreRazonSocial = nombre;
        }
        if (primerApellido != null && !primerApellido.equals("")) {
            campoNombreRazonSocial = campoNombreRazonSocial + " " + primerApellido;
        }
        if (segundoApellido != null && !segundoApellido.equals("")) {
            campoNombreRazonSocial = campoNombreRazonSocial + " " + segundoApellido;
        }
        return campoNombreRazonSocial;
    }

    public Integer getNumeroGaceta() {
        return numeroGaceta;
    }

    public void setNumeroGaceta(Integer numeroGaceta) {
        this.numeroGaceta = numeroGaceta;
    }

    public Boolean getReporteRecibo() {
        return reporteRecibo;
    }

    public void setReporteRecibo(Boolean reporteRecibo) {
        this.reporteRecibo = reporteRecibo;
    }

    public Map<String, Object> getParametros() {
        return parametros;
    }

    public void setParametros(Map<String, Object> parametros) {
        this.parametros = parametros;
    }

    public List<ReportePojo> getListaReporte() {
        return listaReporte;
    }

    public void setListaReporte(List<ReportePojo> listaReporte) {
        this.listaReporte = listaReporte;
    }

    public List<Oposicion> getListaOposicion() {
        return listaOposicion;
    }

    public void setListaOposicion(List<Oposicion> listaOposicion) {
        this.listaOposicion = listaOposicion;
    }

    public OposicionService getOposicionService() {
        return oposicionService;
    }

    public void setOposicionService(OposicionService oposicionService) {
        this.oposicionService = oposicionService;
    }

    public OpoSolicitanteaopderadoService getOpoSolicitanteApoderadoService() {
        return opoSolicitanteApoderadoService;
    }

    public void setOpoSolicitanteApoderadoService(OpoSolicitanteaopderadoService opoSolicitanteApoderadoService) {
        this.opoSolicitanteApoderadoService = opoSolicitanteApoderadoService;
    }

    public OpoMarcademandanteService getOpoMarcaDemandanteService() {
        return opoMarcaDemandanteService;
    }

    public void setOpoMarcaDemandanteService(OpoMarcademandanteService opoMarcaDemandanteService) {
        this.opoMarcaDemandanteService = opoMarcaDemandanteService;
    }

    public OpoMarcademandadaService getOpoMarcaDemandadaService() {
        return opoMarcaDemandadaService;
    }

    public void setOpoMarcaDemandadaService(OpoMarcademandadaService opoMarcaDemandadaService) {
        this.opoMarcaDemandadaService = opoMarcaDemandadaService;
    }

}
