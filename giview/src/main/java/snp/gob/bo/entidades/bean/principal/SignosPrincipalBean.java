/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.bean.principal;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;

/**
 *
 * @author eddy
 */
@ManagedBean(name = "signosPrincipalBean")
@ViewScoped
public class SignosPrincipalBean extends AbstractManagedBean implements Serializable {

    private BarChartModel barModel;
    private PieChartModel pieModel;
    private PieChartModel pieModel1;
    private PieChartModel pieModelRecauda;
    private PieChartModel pieModel1Recauda;
    private BarChartModel barRecaudaciones;
    private Boolean renderizaPantallaModuloRecaudaciones;
    private Boolean renderizaPantallaModuloSignos;
    private Boolean renderizaPantallaModuloNotificaciones;
    private Boolean renderizaPantallaModuloDigitalizacion;

    @PostConstruct
    public void initsignosPrincipalBean() {
        if (getCodigoPaginaSession() != null) {
            recuperaVariable(getCodigoPaginaSession());
        }
        createBarModels();
        createPieModels();
        createbarRecaudaciones();
        createpieRecaudaciones();
        setActivaPantallaInicioSession(null);

    }

    private String recuperaVariable(String codigoPagina) {
        if (codigoPagina.equals("RECA")) {
            renderizaPantallaModuloRecaudaciones = true;
            renderizaPantallaModuloSignos = false;
        }
        if (codigoPagina.equals("REGS") || codigoPagina.equals("NOTI") || codigoPagina.equals("DIGI")) {
            renderizaPantallaModuloRecaudaciones = false;
            renderizaPantallaModuloSignos = true;
        }

        return null;
    }

    private void createBarModels() {
        createBarModel();
    }

    private void createPieModels() {
        createPieModel();
    }

    private void createbarRecaudaciones() {
        createbarRecaudacionesGrafico();
    }

    private void createpieRecaudaciones() {
        createPieModelRecaudaciones();
    }

    private void createBarModel() {
        barModel = initBarModel();
        barModel.setTitle("Tramites ingresados por año");
        barModel.setLegendPosition("ne");

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Años");

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Número de Trámites");
        yAxis.setMin(0);
        yAxis.setMax(200);

    }

    private void createbarRecaudacionesGrafico() {
        barRecaudaciones = initBarModelRecaudaciones();
        barRecaudaciones.setTitle("Recibos generados por año");
        barRecaudaciones.setLegendPosition("ne");
        Axis xAxis = barRecaudaciones.getAxis(AxisType.X);
        xAxis.setLabel("Gestión");
        Axis yAxis = barRecaudaciones.getAxis(AxisType.Y);
        yAxis.setLabel("Número de Recibos canjeados");
        yAxis.setMin(0);
        yAxis.setMax(5000);

    }

    private void createPieModel() {
        pieModel = new PieChartModel();
        pieModel.set("2014", 120);
        pieModel.set("2015", 160);
        pieModel.set("2016", 180);
        pieModel.setTitle("Solicitados");
        pieModel.setLegendPosition("w");
        pieModel1 = new PieChartModel();
        pieModel1.set("2014", 80);
        pieModel1.set("2015", 100);
        pieModel1.set("2016", 80);
        pieModel1.setTitle("Otorgados");
        pieModel1.setLegendPosition("w");

    }

    private void createPieModelRecaudaciones() {
        pieModelRecauda = new PieChartModel();

        pieModelRecauda.set("2014", 3560);
        pieModelRecauda.set("2015", 4000);
        pieModelRecauda.set("2016", 4850);

        pieModelRecauda.setTitle("Recibos");
        pieModelRecauda.setLegendPosition("w");

        pieModel1Recauda = new PieChartModel();

        pieModel1Recauda.set("Patentes", 500);
        pieModel1Recauda.set("Marcas", 600);
        pieModel1Recauda.set("Derechos de Autor", 800);

        pieModel1Recauda.setTitle("Recibos generados por tipo de tramite 2016");
        pieModel1Recauda.setLegendPosition("w");

    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();

        ChartSeries solicitadas = new ChartSeries();
        solicitadas.setLabel("Solicitados");
        solicitadas.set("2014", 120);
        solicitadas.set("2015", 160);
        solicitadas.set("2016", 180);

        ChartSeries otorgadas = new ChartSeries();
        otorgadas.setLabel("Otorgados");
        otorgadas.set("2014", 80);
        otorgadas.set("2015", 100);
        otorgadas.set("2016", 80);

        model.addSeries(solicitadas);
        model.addSeries(otorgadas);

        return model;
    }

    private BarChartModel initBarModelRecaudaciones() {
        BarChartModel model = new BarChartModel();
        ChartSeries recibosGenerados = new ChartSeries();
        recibosGenerados.setLabel("Solicitados");
        recibosGenerados.set("2014", 1300);
        recibosGenerados.set("2015", 1800);
        recibosGenerados.set("2016", 2350);
        model.addSeries(recibosGenerados);
        return model;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    public PieChartModel getPieModel1() {
        return pieModel1;
    }

    public void setPieModel1(PieChartModel pieModel1) {
        this.pieModel1 = pieModel1;
    }

    public BarChartModel getBarRecaudaciones() {
        return barRecaudaciones;
    }

    public void setBarRecaudaciones(BarChartModel barRecaudaciones) {
        this.barRecaudaciones = barRecaudaciones;
    }

    public PieChartModel getPieModelRecauda() {
        return pieModelRecauda;
    }

    public void setPieModelRecauda(PieChartModel pieModelRecauda) {
        this.pieModelRecauda = pieModelRecauda;
    }

    public PieChartModel getPieModel1Recauda() {
        return pieModel1Recauda;
    }

    public void setPieModel1Recauda(PieChartModel pieModel1Recauda) {
        this.pieModel1Recauda = pieModel1Recauda;
    }

    public Boolean getRenderizaPantallaModuloRecaudaciones() {
        return renderizaPantallaModuloRecaudaciones;
    }

    public void setRenderizaPantallaModuloRecaudaciones(Boolean renderizaPantallaModuloRecaudaciones) {
        this.renderizaPantallaModuloRecaudaciones = renderizaPantallaModuloRecaudaciones;
    }

    public Boolean getRenderizaPantallaModuloSignos() {
        return renderizaPantallaModuloSignos;
    }

    public void setRenderizaPantallaModuloSignos(Boolean renderizaPantallaModuloSignos) {
        this.renderizaPantallaModuloSignos = renderizaPantallaModuloSignos;
    }

    public Boolean getRenderizaPantallaModuloNotificaciones() {
        return renderizaPantallaModuloNotificaciones;
    }

    public void setRenderizaPantallaModuloNotificaciones(Boolean renderizaPantallaModuloNotificaciones) {
        this.renderizaPantallaModuloNotificaciones = renderizaPantallaModuloNotificaciones;
    }

    public Boolean getRenderizaPantallaModuloDigitalizacion() {
        return renderizaPantallaModuloDigitalizacion;
    }

    public void setRenderizaPantallaModuloDigitalizacion(Boolean renderizaPantallaModuloDigitalizacion) {
        this.renderizaPantallaModuloDigitalizacion = renderizaPantallaModuloDigitalizacion;
    }

}
