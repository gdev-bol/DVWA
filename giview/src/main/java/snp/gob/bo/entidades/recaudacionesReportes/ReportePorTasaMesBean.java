  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. 
 */
package snp.gob.bo.entidades.recaudacionesReportes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.gimodel.entidad.ReciboTasaResultado;
import snp.gob.bo.gimodel.entidad.Tasa;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.ReciboTasaResultadoService;
import snp.gob.bo.gimodel.servicio.TasaService;

/**
 *
 * @author Ruben Ramirez
 */
@ManagedBean(name = "reportePorTasaMesBean")
@ViewScoped
public class ReportePorTasaMesBean extends AbstractManagedBean implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Definicion de servicios y atributos">
    // variables de la vista
    private Boolean reporteRecibo;
    private String gestion;
    private String mes;
    private Date fechaPresente;
    private List<Integer> anios = new ArrayList<>();
    private Integer anioFin;
    private Integer anioIni;
    private List<String> listaPrueba = new ArrayList<>();

    // parametros del reporte
    private Map<String, Object> parametros = new HashMap<>();
    private List<ResumenConceptoMesPojo> listaResumenConceptoMesPojo = new ArrayList();
    private List<Date> listaFecha = new ArrayList();
    private String fechaHoy;
    private String horaHoy;
    private String imgSenapi;

    @ManagedProperty(value = "#{comunService}")
    private ComunService comunService;
    @ManagedProperty(value = "#{tasaService}")
    private TasaService tasaService;
    @ManagedProperty(value = "#{reciboTasaResultadoService}")
    private ReciboTasaResultadoService reciboTasaResultadoService;

    /**
     * Creates a new instance of ReportePorConceptoBean
     */
    public ReportePorTasaMesBean() {
        reporteRecibo = false;
        parametros = new HashMap<>();
    }

    @PostConstruct
    public void ReportePorTasaBeanInit() {
        try {
            fechaPresente = comunService.obtenerFechaServidor(1L);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fechaPresente);
            anioFin = calendar.get(Calendar.YEAR);
            anioIni = 2006;
            for (int i = anioFin; i >= anioIni; i--) {
                anios.add(i);
            }
            this.addLista();

        } catch (Exception ex) {
            Logger.getLogger(ReportePorTasaMesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     * Metodo para convertir un numero entre 1 a 12 a mes en literal.
     *
     * Creado: Ruben Ramirez Fecha: 19/12/2016
     * @param cadena
     * @return 
     */
    public String gestionLiteral(int gestion) {
        String gestionLiteral = "";
        switch (gestion) {
            case 1:
                gestionLiteral = "ENERO";
                break;
            case 2:
                gestionLiteral = "FEBRERO";
                break;
            case 3:
                gestionLiteral = "MARZO";
                break;
            case 4:
                gestionLiteral = "ABRIL";
                break;
            case 5:
                gestionLiteral = "MAYO";
                break;
            case 6:
                gestionLiteral = "JUNIO";
                break;
            case 7:
                gestionLiteral = "JULIO";
                break;
            case 8:
                gestionLiteral = "AGOSTO";
                break;
            case 9:
                gestionLiteral = "SEPTIEMBRE";
                break;
            case 10:
                gestionLiteral = "OCTUBRE";
                break;
            case 11:
                gestionLiteral = "NOVIEMBRE";
                break;
            case 12:
                gestionLiteral = "DICIEMBRE";
                break;
        }
        return gestionLiteral;
    }

    public void addLista() {
        listaPrueba.add("TITU");
        listaPrueba.add("PRIN");
        listaPrueba.add("SIDI");
        listaPrueba.add("PAIN");
        listaPrueba.add("DEAU");
        listaPrueba.add("GENE");
    }

    /**
     * Metodo para la generación del reporte de resumen diario por concepto
     * segun la gestion y mes o de todo el año seleccionado.
     *
     * Creado: Ruben Ramirez Fecha: 19/12/2016
     *
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void imprimir() throws JRException, IOException, Exception {
        reporteRecibo = true;
        DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
        dateFormatSymbols.setWeekdays(new String[]{
            "",
            "domingo",
            "lunes",
            "martes",
            "miércoles",
            "jueves",
            "viernes",
            "sábado"});
        dateFormatSymbols.setMonths(new String[]{
            "enero",
            "febrero",
            "marzo",
            "abril",
            "mayo",
            "junio",
            "julio",
            "agosto",
            "septiembre",
            "octubre",
            "noviembre",
            "diciembre"});
        SimpleDateFormat formateador1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formateador2 = new SimpleDateFormat("EEEEE '\n' d");
        SimpleDateFormat formateador3 = new SimpleDateFormat("EEEEE, d MMMM, yyyy", dateFormatSymbols);
        SimpleDateFormat formateador4 = new SimpleDateFormat("H:mm a");

        if (gestion != null && mes != null) {
            if (validar(mes) && validar(gestion)) {
                int mesEntero = Integer.parseInt(mes);
                int gestionEntero = Integer.parseInt(gestion);
                List<TasaPorMesPojo> tasaMeses = new ArrayList<>();
                int ini = 0;
                int fin = 0;
                if(mesEntero == 0){
                    ini = 1;
                    fin = 12;
                }else{
                    ini = mesEntero;
                    fin = mesEntero;
                }
                for (int m = ini; m <= fin; m++) {
                    TasaPorMesPojo tasaMes = new TasaPorMesPojo();
                    tasaMes.setNumMes("" + m);
                    tasaMes.setMesGestion(gestionLiteral(m) + " - " + gestionEntero);
                    List<Date> listafecha = fechasMes(m, gestionEntero);
                    int numDia = listafecha.size();
                    List<BigDecimal> listaMontoV = new ArrayList<>();
                    for (Date item : listafecha) {
                        BigDecimal montoCero = new BigDecimal("0.00");
                        listaMontoV.add(montoCero);
                    }
                    int cantidadTotal = 0;
                    BigDecimal montoTotal = new BigDecimal("0.00");
                    List<TasaPorUnidadPojo> listaTasaPorUnidadPojo = new ArrayList<>();
                    for (String item : listaPrueba) {
                        TasaPorUnidadPojo tasaUnidad = new TasaPorUnidadPojo();
                        tasaUnidad.setTitulo(item);
                        List<Tasa> listaTasaXUnidad = tasaService.obtenerListaTasaActivasPorUnidad(item);
                        List<TasaPojo> listaTasaPojo = new ArrayList<>();
                        for (Tasa item1 : listaTasaXUnidad) {
                            TasaPojo tasa = new TasaPojo(0);
                            tasa.setCosto(item1.getCosto().toBigInteger().intValueExact());
                            tasa.setConcepto(item1.getDescripcion());
                            List<ReciboTasaResultado> listaRecibos = reciboTasaResultadoService.listaReciboResultadoFecha(item1.getIdTasa(), m, gestionEntero);
                            int cantidadTasa = 0;
                            BigDecimal totalTasa = new BigDecimal("0.00");
                            for (ReciboTasaResultado item2 : listaRecibos) {
                                for (int i = 0; i < listafecha.size(); i++) {
                                    String fecha1 = "";
                                    if (listafecha.get(i) != null) {
                                        fecha1 = formateador1.format(listafecha.get(i));
                                        if (item2.getFecha().equals(fecha1)) {
                                            listaMontoV.set(i, listaMontoV.get(i).add(item2.getTotal()));
                                            switch (i + 1) {
                                                case 1:
                                                    tasa.setCant1(item2.getCantidad());
                                                    tasa.setMont1(item2.getTotal().toBigInteger().intValueExact());
                                                    break;
                                                case 2:
                                                    tasa.setCant2(item2.getCantidad());
                                                    tasa.setMont2(item2.getTotal().toBigInteger().intValueExact());
                                                    break;
                                                case 3:
                                                    tasa.setCant3(item2.getCantidad());
                                                    tasa.setMont3(item2.getTotal().toBigInteger().intValueExact());
                                                    break;
                                                case 4:
                                                    tasa.setCant4(item2.getCantidad());
                                                    tasa.setMont4(item2.getTotal().toBigInteger().intValueExact());
                                                    break;
                                                case 5:
                                                    tasa.setCant5(item2.getCantidad());
                                                    tasa.setMont5(item2.getTotal().toBigInteger().intValueExact());
                                                    break;
                                                case 6:
                                                    tasa.setCant6(item2.getCantidad());
                                                    tasa.setMont6(item2.getTotal().toBigInteger().intValueExact());
                                                    break;
                                                case 7:
                                                    tasa.setCant7(item2.getCantidad());
                                                    tasa.setMont7(item2.getTotal().toBigInteger().intValueExact());
                                                    break;
                                                case 8:
                                                    tasa.setCant8(item2.getCantidad());
                                                    tasa.setMont8(item2.getTotal().toBigInteger().intValueExact());
                                                    break;
                                                case 9:
                                                    tasa.setCant9(item2.getCantidad());
                                                    tasa.setMont9(item2.getTotal().toBigInteger().intValueExact());
                                                    break;
                                                case 10:
                                                    tasa.setCant10(item2.getCantidad());
                                                    tasa.setMont10(item2.getTotal().toBigInteger().intValueExact());
                                                    break;
                                                case 11:
                                                    tasa.setCant11(item2.getCantidad());
                                                    tasa.setMont11(item2.getTotal().toBigInteger().intValueExact());
                                                    break;
                                                case 12:
                                                    tasa.setCant12(item2.getCantidad());
                                                    tasa.setMont12(item2.getTotal().toBigInteger().intValueExact());
                                                    break;
                                                case 13:
                                                    tasa.setCant13(item2.getCantidad());
                                                    tasa.setMont13(item2.getTotal().toBigInteger().intValueExact());
                                                    break;
                                                case 14:
                                                    tasa.setCant14(item2.getCantidad());
                                                    tasa.setMont14(item2.getTotal().toBigInteger().intValueExact());
                                                    break;
                                                case 15:
                                                    tasa.setCant15(item2.getCantidad());
                                                    tasa.setMont15(item2.getTotal().toBigInteger().intValueExact());
                                                    break;
                                                case 16:
                                                    tasa.setCant16(item2.getCantidad());
                                                    tasa.setMont16(item2.getTotal().toBigInteger().intValueExact());
                                                    break;
                                                case 17:
                                                    tasa.setCant17(item2.getCantidad());
                                                    tasa.setMont17(item2.getTotal().toBigInteger().intValueExact());
                                                    break;
                                                case 18:
                                                    tasa.setCant18(item2.getCantidad());
                                                    tasa.setMont18(item2.getTotal().toBigInteger().intValueExact());
                                                    break;
                                                case 19:
                                                    tasa.setCant19(item2.getCantidad());
                                                    tasa.setMont19(item2.getTotal().toBigInteger().intValueExact());
                                                    break;
                                                case 20:
                                                    tasa.setCant20(item2.getCantidad());
                                                    tasa.setMont20(item2.getTotal().toBigInteger().intValueExact());
                                                    break;
                                                case 21:
                                                    tasa.setCant21(item2.getCantidad());
                                                    tasa.setMont21(item2.getTotal().toBigInteger().intValueExact());
                                                    break;
                                                case 22:
                                                    tasa.setCant22(item2.getCantidad());
                                                    tasa.setMont22(item2.getTotal().toBigInteger().intValueExact());
                                                    break;
                                                case 23:
                                                    tasa.setCant23(item2.getCantidad());
                                                    tasa.setMont23(item2.getTotal().toBigInteger().intValueExact());
                                                    break;
                                                case 24:
                                                    tasa.setCant24(item2.getCantidad());
                                                    tasa.setMont24(item2.getTotal().toBigInteger().intValueExact());
                                                    break;
                                                case 25:
                                                    tasa.setCant25(item2.getCantidad());
                                                    tasa.setMont25(item2.getTotal().toBigInteger().intValueExact());
                                                    break;
                                                case 26:
                                                    tasa.setCant26(item2.getCantidad());
                                                    tasa.setMont26(item2.getTotal().toBigInteger().intValueExact());
                                                    break;
                                                case 27:
                                                    tasa.setCant27(item2.getCantidad());
                                                    tasa.setMont27(item2.getTotal().toBigInteger().intValueExact());
                                                    break;
                                                case 28:
                                                    tasa.setCant28(item2.getCantidad());
                                                    tasa.setMont28(item2.getTotal().toBigInteger().intValueExact());
                                                    break;
                                                case 29:
                                                    tasa.setCant29(item2.getCantidad());
                                                    tasa.setMont29(item2.getTotal().toBigInteger().intValueExact());
                                                    break;
                                                case 30:
                                                    tasa.setCant30(item2.getCantidad());
                                                    tasa.setMont30(item2.getTotal().toBigInteger().intValueExact());
                                                    break;
                                                case 31:
                                                    tasa.setCant31(item2.getCantidad());
                                                    tasa.setMont31(item2.getTotal().toBigInteger().intValueExact());
                                                    break;
                                            }
                                            break;
                                        }
                                    }
                                }
                                cantidadTasa = cantidadTasa + item2.getCantidad();
                                totalTasa = totalTasa.add(item2.getTotal());
                            }
                            tasa.setCantTotal(cantidadTasa);
                            tasa.setMontTotal(totalTasa);
                            tasa.setNumDia(numDia);
                            if (cantidadTasa > 0) {
                                listaTasaPojo.add(tasa);
                                cantidadTotal = cantidadTotal + cantidadTasa;
                                montoTotal = montoTotal.add(totalTasa);
                            }
                        }
                        tasaUnidad.setTasaPojo(listaTasaPojo);
                        tasaUnidad.setNumDia(numDia);
                        if (listaTasaPojo.size() > 0) {
                            listaTasaPorUnidadPojo.add(tasaUnidad);
                        }
                    }
                    for (int i = 0; i < listafecha.size(); i++) {
                        if (listafecha.get(i) != null) {
                            switch (i + 1) {
                                case 1:
                                    if (listaMontoV.get(i).compareTo(BigDecimal.ZERO) == 0) {
                                        tasaMes.setFecha1("-\n0");
                                    } else {
                                        tasaMes.setFecha1(formateador2.format(listafecha.get(i)));
                                    }
                                    tasaMes.setTotal1(listaMontoV.get(i));
                                    break;
                                case 2:
                                    if (listaMontoV.get(i).compareTo(BigDecimal.ZERO) == 0) {
                                        tasaMes.setFecha2("-\n0");
                                    } else {
                                        tasaMes.setFecha2(formateador2.format(listafecha.get(i)));
                                    }
                                    tasaMes.setTotal2(listaMontoV.get(i));
                                    break;
                                case 3:
                                    if (listaMontoV.get(i).compareTo(BigDecimal.ZERO) == 0) {
                                        tasaMes.setFecha3("-\n0");
                                    } else {
                                        tasaMes.setFecha3(formateador2.format(listafecha.get(i)));
                                    }
                                    tasaMes.setTotal3(listaMontoV.get(i));
                                    break;
                                case 4:
                                    if (listaMontoV.get(i).compareTo(BigDecimal.ZERO) == 0) {
                                        tasaMes.setFecha4("-\n0");
                                    } else {
                                        tasaMes.setFecha4(formateador2.format(listafecha.get(i)));
                                    }
                                    tasaMes.setTotal4(listaMontoV.get(i));
                                    break;
                                case 5:
                                    if (listaMontoV.get(i).compareTo(BigDecimal.ZERO) == 0) {
                                        tasaMes.setFecha5("-\n0");
                                    } else {
                                        tasaMes.setFecha5(formateador2.format(listafecha.get(i)));
                                    }
                                    tasaMes.setTotal5(listaMontoV.get(i));
                                    break;
                                case 6:
                                    if (listaMontoV.get(i).compareTo(BigDecimal.ZERO) == 0) {
                                        tasaMes.setFecha6("-\n0");
                                    } else {
                                        tasaMes.setFecha6(formateador2.format(listafecha.get(i)));
                                    }
                                    tasaMes.setTotal6(listaMontoV.get(i));
                                    break;
                                case 7:
                                    if (listaMontoV.get(i).compareTo(BigDecimal.ZERO) == 0) {
                                        tasaMes.setFecha7("-\n0");
                                    } else {
                                        tasaMes.setFecha7(formateador2.format(listafecha.get(i)));
                                    }
                                    tasaMes.setTotal7(listaMontoV.get(i));
                                    break;
                                case 8:
                                    if (listaMontoV.get(i).compareTo(BigDecimal.ZERO) == 0) {
                                        tasaMes.setFecha8("-\n0");
                                    } else {
                                        tasaMes.setFecha8(formateador2.format(listafecha.get(i)));
                                    }
                                    tasaMes.setTotal8(listaMontoV.get(i));
                                    break;
                                case 9:
                                    if (listaMontoV.get(i).compareTo(BigDecimal.ZERO) == 0) {
                                        tasaMes.setFecha9("-\n0");
                                    } else {
                                        tasaMes.setFecha9(formateador2.format(listafecha.get(i)));
                                    }
                                    tasaMes.setTotal9(listaMontoV.get(i));
                                    break;
                                case 10:
                                    if (listaMontoV.get(i).compareTo(BigDecimal.ZERO) == 0) {
                                        tasaMes.setFecha10("-\n0");
                                    } else {
                                        tasaMes.setFecha10(formateador2.format(listafecha.get(i)));
                                    }
                                    tasaMes.setTotal10(listaMontoV.get(i));
                                    ;
                                    break;
                                case 11:
                                    if (listaMontoV.get(i).compareTo(BigDecimal.ZERO) == 0) {
                                        tasaMes.setFecha11("-\n0");
                                    } else {
                                        tasaMes.setFecha11(formateador2.format(listafecha.get(i)));
                                    }
                                    tasaMes.setTotal11(listaMontoV.get(i));
                                    break;
                                case 12:
                                    if (listaMontoV.get(i).compareTo(BigDecimal.ZERO) == 0) {
                                        tasaMes.setFecha12("-\n0");
                                    } else {
                                        tasaMes.setFecha12(formateador2.format(listafecha.get(i)));
                                    }
                                    tasaMes.setTotal12(listaMontoV.get(i));
                                    break;
                                case 13:
                                    if (listaMontoV.get(i).compareTo(BigDecimal.ZERO) == 0) {
                                        tasaMes.setFecha13("-\n0");
                                    } else {
                                        tasaMes.setFecha13(formateador2.format(listafecha.get(i)));
                                    }
                                    tasaMes.setTotal13(listaMontoV.get(i));
                                    break;
                                case 14:
                                    if (listaMontoV.get(i).compareTo(BigDecimal.ZERO) == 0) {
                                        tasaMes.setFecha14("-\n0");
                                    } else {
                                        tasaMes.setFecha14(formateador2.format(listafecha.get(i)));
                                    }
                                    tasaMes.setTotal14(listaMontoV.get(i));
                                    break;
                                case 15:
                                    if (listaMontoV.get(i).compareTo(BigDecimal.ZERO) == 0) {
                                        tasaMes.setFecha15("-\n0");
                                    } else {
                                        tasaMes.setFecha15(formateador2.format(listafecha.get(i)));
                                    }
                                    tasaMes.setTotal15(listaMontoV.get(i));
                                    ;
                                    break;
                                case 16:
                                    if (listaMontoV.get(i).compareTo(BigDecimal.ZERO) == 0) {
                                        tasaMes.setFecha16("-\n0");
                                    } else {
                                        tasaMes.setFecha16(formateador2.format(listafecha.get(i)));
                                    }
                                    tasaMes.setTotal16(listaMontoV.get(i));
                                    break;
                                case 17:
                                    if (listaMontoV.get(i).compareTo(BigDecimal.ZERO) == 0) {
                                        tasaMes.setFecha17("-\n0");
                                    } else {
                                        tasaMes.setFecha17(formateador2.format(listafecha.get(i)));
                                    }
                                    tasaMes.setTotal17(listaMontoV.get(i));
                                    break;
                                case 18:
                                    if (listaMontoV.get(i).compareTo(BigDecimal.ZERO) == 0) {
                                        tasaMes.setFecha18("-\n0");
                                    } else {
                                        tasaMes.setFecha18(formateador2.format(listafecha.get(i)));
                                    }
                                    tasaMes.setTotal18(listaMontoV.get(i));
                                    break;
                                case 19:
                                    if (listaMontoV.get(i).compareTo(BigDecimal.ZERO) == 0) {
                                        tasaMes.setFecha19("-\n0");
                                    } else {
                                        tasaMes.setFecha19(formateador2.format(listafecha.get(i)));
                                    }
                                    tasaMes.setTotal19(listaMontoV.get(i));
                                    break;
                                case 20:
                                    if (listaMontoV.get(i).compareTo(BigDecimal.ZERO) == 0) {
                                        tasaMes.setFecha20("-\n0");
                                    } else {
                                        tasaMes.setFecha20(formateador2.format(listafecha.get(i)));
                                    }
                                    tasaMes.setTotal20(listaMontoV.get(i));
                                    break;
                                case 21:
                                    if (listaMontoV.get(i).compareTo(BigDecimal.ZERO) == 0) {
                                        tasaMes.setFecha21("-\n0");
                                    } else {
                                        tasaMes.setFecha21(formateador2.format(listafecha.get(i)));
                                    }
                                    tasaMes.setTotal21(listaMontoV.get(i));
                                    break;
                                case 22:
                                    if (listaMontoV.get(i).compareTo(BigDecimal.ZERO) == 0) {
                                        tasaMes.setFecha22("-\n0");
                                    } else {
                                        tasaMes.setFecha22(formateador2.format(listafecha.get(i)));
                                    }
                                    tasaMes.setTotal22(listaMontoV.get(i));
                                    break;
                                case 23:
                                    if (listaMontoV.get(i).compareTo(BigDecimal.ZERO) == 0) {
                                        tasaMes.setFecha23("-\n0");
                                    } else {
                                        tasaMes.setFecha23(formateador2.format(listafecha.get(i)));
                                    }
                                    tasaMes.setTotal23(listaMontoV.get(i));
                                    break;
                                case 24:
                                    if (listaMontoV.get(i).compareTo(BigDecimal.ZERO) == 0) {
                                        tasaMes.setFecha24("-\n0");
                                    } else {
                                        tasaMes.setFecha24(formateador2.format(listafecha.get(i)));
                                    }
                                    tasaMes.setTotal24(listaMontoV.get(i));
                                    break;
                                case 25:
                                    if (listaMontoV.get(i).compareTo(BigDecimal.ZERO) == 0) {
                                        tasaMes.setFecha25("-\n0");
                                    } else {
                                        tasaMes.setFecha25(formateador2.format(listafecha.get(i)));
                                    }
                                    tasaMes.setTotal25(listaMontoV.get(i));
                                    break;
                                case 26:
                                    if (listaMontoV.get(i).compareTo(BigDecimal.ZERO) == 0) {
                                        tasaMes.setFecha26("-\n0");
                                    } else {
                                        tasaMes.setFecha26(formateador2.format(listafecha.get(i)));
                                    }
                                    tasaMes.setTotal26(listaMontoV.get(i));
                                    break;
                                case 27:
                                    if (listaMontoV.get(i).compareTo(BigDecimal.ZERO) == 0) {
                                        tasaMes.setFecha27("-\n0");
                                    } else {
                                        tasaMes.setFecha27(formateador2.format(listafecha.get(i)));
                                    }
                                    tasaMes.setTotal27(listaMontoV.get(i));
                                    break;
                                case 28:
                                    if (listaMontoV.get(i).compareTo(BigDecimal.ZERO) == 0) {
                                        tasaMes.setFecha28("-\n0");
                                    } else {
                                        tasaMes.setFecha28(formateador2.format(listafecha.get(i)));
                                    }
                                    tasaMes.setTotal28(listaMontoV.get(i));
                                    break;
                                case 29:
                                    if (listaMontoV.get(i).compareTo(BigDecimal.ZERO) == 0) {
                                        tasaMes.setFecha29("-\n0");
                                    } else {
                                        tasaMes.setFecha29(formateador2.format(listafecha.get(i)));
                                    }
                                    tasaMes.setTotal29(listaMontoV.get(i));
                                    break;
                                case 30:
                                    if (listaMontoV.get(i).compareTo(BigDecimal.ZERO) == 0) {
                                        tasaMes.setFecha30("-\n0");
                                    } else {
                                        tasaMes.setFecha30(formateador2.format(listafecha.get(i)));
                                    }
                                    tasaMes.setTotal30(listaMontoV.get(i));
                                    break;
                                case 31:
                                    if (listaMontoV.get(i).compareTo(BigDecimal.ZERO) == 0) {
                                        tasaMes.setFecha31("-\n0");
                                    } else {
                                        tasaMes.setFecha31(formateador2.format(listafecha.get(i)));
                                    }
                                    tasaMes.setTotal31(listaMontoV.get(i));
                                    break;
                            }
                        }
                    }
                    tasaMes.setCantidad(cantidadTotal);
                    tasaMes.setTotal(montoTotal);
                    tasaMes.setUnidadPojo(listaTasaPorUnidadPojo);
                    tasaMes.setNumDia(numDia);
                    if(cantidadTotal>0){
                        tasaMeses.add(tasaMes);
                    } 
                }

                if (reporteRecibo) {
                    imgSenapi = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/logoNuevo.jpg");
                    fechaPresente = comunService.obtenerFechaHoraServidor(1L);
                    if (fechaPresente != null) {
                        fechaHoy = formateador3.format(fechaPresente);
                        horaHoy = formateador4.format(fechaPresente).toLowerCase();
                    }
                    parametros.put("imgSenapi", imgSenapi);
                    parametros.put("fechaHoy", fechaHoy);
                    parametros.put("horaHoy", horaHoy);
                    parametros.put("mes", getDatos(tasaMeses));
                    String filename = "ResumenPorTasaMes.pdf";
                    String jasperPath = "/template/recibo/tasaPorGestion.jasper";
                    this.generateReport(parametros, jasperPath, filename);

                    tasaMeses.clear();

                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se encontraron registros.", ""));
                }

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Introdusca los datos validos.", ""));
            }

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Introdusca los datos necesarios.", ""));
        }
    }

    public JRDataSource getDatos(List<?> listaObjeto) {
        return new JRBeanCollectionDataSource(listaObjeto);
    }

    /**
     * Metodo para generar el reporte y pasarle en formato StreamContente a la
     * variable de sesion stream. Para posterior despliegue en la vista.
     *
     * Creado: Ruben Ramirez Fecha: 19/12/2016
     *
     * @param params
     * @param jasperPath
     * @param fileName
     * @throws java.io.IOException
     * @throws net.sf.jasperreports.engine.JRException
     *
     */
    public void generateReport(Map<String, Object> params, String jasperPath, String fileName) throws IOException, JRException, Exception {
        String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(jasperPath);
        File file = new File(relativeWebPath);
        ByteArrayOutputStream outputStream = getOutputStreamFromReport(params, file.getPath());
        setStreamSession(getStreamContentFromOutputStream(outputStream, "application/pdf", fileName));

    }

    /**
     * Metodo para generar el reporte en formato ByteArrayOutputStream.
     *
     * Creado: Ruben Ramirez Fecha: 19/12/2016
     *
     * @param map
     * @param pathJasper
     *
     * @return ByteArrayOutputStream
     * @throws java.lang.Exception
     */
    public static ByteArrayOutputStream getOutputStreamFromReport(Map<String, Object> map, String pathJasper) throws Exception {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        JasperPrint jp = JasperFillManager.fillReport(pathJasper, map, new JREmptyDataSource());
        JasperExportManager.exportReportToPdfStream(jp, os);
        os.flush();
        os.close();
        return os;
    }

    /**
     * Metodo para generar el reporte en formato StreamedContent.
     *
     * Creado: Ruben Ramirez Fecha: 19/12/2016
     *
     * @param os
     * @param contentType
     * @param nameFile
     *
     * @return StreamedContent
     * @throws java.lang.Exception
     * @throws java.io.IOException
     */
    public static StreamedContent getStreamContentFromOutputStream(ByteArrayOutputStream os, String contentType, String nameFile) throws Exception, IOException {
        StreamedContent file = null;
        InputStream is = new ByteArrayInputStream(os.toByteArray());
        file = new DefaultStreamedContent(is, "application/pdf", nameFile);
        return file;
    }

    /*
     * Metodo para validar si una cadena string es un numero.
     *
     * Creado: Ruben Ramirez Fecha: 19/12/2016
     * @param cadena
     * @return 
     */
    public boolean validar(String cadena) {
        if (cadena.equals("") || cadena == null) {
            return false;
        } else {
            return cadena.matches("[0-9]*");
        }
    }

    public List<Date> fechasMes(int mes, int gestion) {
        List<Date> fechas = new ArrayList<>();

        for (int i = 0; i < 31; i++) {
            Calendar fecha = new GregorianCalendar(gestion, mes - 1, 1);
            fecha.add(Calendar.DAY_OF_MONTH, i);
            if ((fecha.get(Calendar.MONTH) + 1) == mes) {
                fechas.add(fecha.getTime());
            } else {
                break;
            }
        }
        return fechas;
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

    public ComunService getComunService() {
        return comunService;
    }

    public void setComunService(ComunService comunService) {
        this.comunService = comunService;
    }

    public String getGestion() {
        return gestion;
    }

    public void setGestion(String gestion) {
        this.gestion = gestion;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public Date getFechaPresente() {
        return fechaPresente;
    }

    public void setFechaPresente(Date fechaPresente) {
        this.fechaPresente = fechaPresente;
    }

    public List<ResumenConceptoMesPojo> getListaResumenConceptoMesPojo() {
        return listaResumenConceptoMesPojo;
    }

    public void setListaResumenConceptoMesPojo(List<ResumenConceptoMesPojo> listaResumenConceptoMesPojo) {
        this.listaResumenConceptoMesPojo = listaResumenConceptoMesPojo;
    }

    public List<Date> getListaFecha() {
        return listaFecha;
    }

    public void setListaFecha(List<Date> listaFecha) {
        this.listaFecha = listaFecha;
    }

    public List<Integer> getAnios() {
        return anios;
    }

    public void setAnios(List<Integer> anios) {
        this.anios = anios;
    }

    public Integer getAnioFin() {
        return anioFin;
    }

    public void setAnioFin(Integer anioFin) {
        this.anioFin = anioFin;
    }

    public Integer getAnioIni() {
        return anioIni;
    }

    public void setAnioIni(Integer anioIni) {
        this.anioIni = anioIni;
    }

    public String getFechaHoy() {
        return fechaHoy;
    }

    public void setFechaHoy(String fechaHoy) {
        this.fechaHoy = fechaHoy;
    }

    public String getHoraHoy() {
        return horaHoy;
    }

    public void setHoraHoy(String horaHoy) {
        this.horaHoy = horaHoy;
    }

    public String getImgSenapi() {
        return imgSenapi;
    }

    public void setImgSenapi(String imgSenapi) {
        this.imgSenapi = imgSenapi;
    }

    public TasaService getTasaService() {
        return tasaService;
    }

    public void setTasaService(TasaService tasaService) {
        this.tasaService = tasaService;
    }

    public ReciboTasaResultadoService getReciboTasaResultadoService() {
        return reciboTasaResultadoService;
    }

    public void setReciboTasaResultadoService(ReciboTasaResultadoService reciboTasaResultadoService) {
        this.reciboTasaResultadoService = reciboTasaResultadoService;
    }
}
