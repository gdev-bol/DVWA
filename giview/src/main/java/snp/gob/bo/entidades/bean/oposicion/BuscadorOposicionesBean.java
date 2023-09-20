/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.bean.oposicion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.gimodel.entidad.BusquedaOposicion;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.servicio.BusquedaOposicionService;
import snp.gob.bo.gimodel.servicio.DominioService;

/**
 *
 * @author Luis Angel Quispe Limachi
 */
@ManagedBean(name = "buscadorOposicionesBean")
@ViewScoped
public class BuscadorOposicionesBean extends AbstractManagedBean implements Serializable {

    private String nombre = "ST";
    private String puntero = "NO";
    private Boolean variok = false;
    private Boolean variacombo = false;
    private Boolean variacombo2 = false;
    private Boolean variok1 = false;
    private Boolean valorPB = false;
    private Boolean valorRE = false;
    private Boolean valorEX = false;
    private Boolean valorMA = false;
    private Boolean valorDO = false;
    private Boolean valorDE = false;
    private Boolean valorAP = false;
    private Boolean valorFP = false;
    private Boolean valorOT = false;
    private Boolean valorEP = false;
    private Boolean valorPT = false;
    private Boolean radioNombre = false;
    private Boolean valorXX = false;
    private Boolean ajusta = false;
    private Boolean valorCombo = false;
    
    private Long txtpublicabusqueda;
    private Integer txtregistrobusqueda;
    private Long txtexpedientebusqueda;
    private Long txtexpedientebusquedagestion;
    private String txtmarcabusqueda;
    private String txtdemandantebusqueda;
    private String txtapoderadobusqueda;
    private Date cldfecahpubbusqueda;
    private String cmbregistrobusqueda;
    private String nombrefechabusqueda = "NF";
    private String cmbtipodmdodmte;
    private List<Dominio> lstDmteDmdo;
       private List<Dominio> lstSerieRegistro;
    private List<BusquedaOposicion> listaBusquedaOposicion = new ArrayList<>();
    private String template;
    private String valorRecibido;

    @ManagedProperty(value = "#{busquedaOposicionService}")
    private BusquedaOposicionService busquedaOposicionService;
  @ManagedProperty("#{dominioService}")
    private DominioService dominioService;
        @PostConstruct
    public void BuscadorModificacionBeanInit() {
         
        
         try {
        puntero = "SD";
         lstSerieRegistro = dominioService.obtenerListadoDominio("serie_registro");
            lstDmteDmdo = dominioService.obtenerListadoDominio("tiposoliapo"); 
        
        if (getNavegaPagina() != null) {
            
        }
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        valorRecibido = params.get("valor");
        if (params.get("valor") != null) {
            template = "./../WEB-INF/facelets/templates/DialogTemplate.xhtml";
        } else {
            
            template = "./../WEB-INF/facelets/templates/Template.xhtml";
        }
         } catch (Exception e) {
        }
          radioNombre = true;
        puntero = "SD";
        ajusta = false;

        variacombo = true;
         
//        listaDocumento = documentoService.obtenerListadoDocumento();
//        for (Documento imagen : listaDocumento) {
//            convierteImagenYGuardaCarpeta(imagen);
//        }
    }
    
    
    
    /**
     * Metodo habilitar botones de la parte baja de la bandeja para que se
     * renderice Nombre Luis Angel Quispe limachi Fecha 12/09/2016
     *
     */
    public void activaCambios2() {

        if (puntero.equals("SD")) {

            this.variacombo = Boolean.TRUE;
            this.variacombo2 = Boolean.FALSE;
            this.valorPB = Boolean.FALSE;
            this.valorRE = Boolean.FALSE;
            this.valorEX = Boolean.FALSE;
            this.valorMA = Boolean.FALSE;
            this.valorDO = Boolean.FALSE;
            this.valorDE = Boolean.FALSE;
            this.valorAP = Boolean.FALSE;
            this.valorOT = Boolean.FALSE;
            this.valorEP = Boolean.FALSE;
            this.valorPT = Boolean.FALSE;
        } else {

            this.variacombo = Boolean.FALSE;
            this.variacombo2 = Boolean.TRUE;
            this.valorPB = Boolean.FALSE;
            this.valorRE = Boolean.FALSE;
            this.valorEX = Boolean.FALSE;
            this.valorMA = Boolean.FALSE;
            this.valorDO = Boolean.FALSE;
            this.valorDE = Boolean.FALSE;
            this.valorAP = Boolean.FALSE;
            this.valorOT = Boolean.FALSE;
            this.valorEP = Boolean.FALSE;
            this.valorPT = Boolean.FALSE;
        }

    }

    /**
     * Método que permite Activar o desactivar cambios en el combo de busquedas
     *
     */
    public void activaCambios1() {

        switch (nombre) {
            case "PB":

                this.valorPB = Boolean.TRUE;
                this.valorRE = Boolean.FALSE;
                this.valorEX = Boolean.FALSE;
                this.valorMA = Boolean.FALSE;
                this.valorDO = Boolean.FALSE;
                this.valorDE = Boolean.FALSE;
                this.valorAP = Boolean.FALSE;
                this.valorOT = Boolean.FALSE;
                this.valorEP = Boolean.FALSE;
                this.valorPT = Boolean.FALSE;
                this.valorFP = Boolean.FALSE;
                this.valorCombo = Boolean.TRUE;
                txtregistrobusqueda = null;
                cmbregistrobusqueda = null;
                txtexpedientebusqueda = null;
                txtexpedientebusquedagestion = null;
                txtmarcabusqueda = null;
                txtdemandantebusqueda = null;
                txtapoderadobusqueda = null;
                cldfecahpubbusqueda = null;
                break;
            case "RE":

                this.valorPB = Boolean.FALSE;
                this.valorRE = Boolean.TRUE;
                this.valorEX = Boolean.FALSE;
                this.valorMA = Boolean.FALSE;
                this.valorDO = Boolean.FALSE;
                this.valorDE = Boolean.FALSE;
                this.valorAP = Boolean.FALSE;
                this.valorOT = Boolean.FALSE;
                this.valorEP = Boolean.FALSE;
                this.valorPT = Boolean.FALSE;
                this.valorFP = Boolean.FALSE;
                this.valorCombo = Boolean.FALSE;
                txtpublicabusqueda = null;
                txtexpedientebusqueda = null;
                txtexpedientebusquedagestion = null;
                txtmarcabusqueda = null;
                txtdemandantebusqueda = null;
                txtapoderadobusqueda = null;
                cldfecahpubbusqueda = null;
                break;
            case "EX":

                this.valorPB = Boolean.FALSE;
                this.valorRE = Boolean.FALSE;
                this.valorEX = Boolean.TRUE;
                this.valorMA = Boolean.FALSE;
                this.valorDO = Boolean.FALSE;
                this.valorDE = Boolean.FALSE;
                this.valorAP = Boolean.FALSE;
                this.valorOT = Boolean.FALSE;
                this.valorEP = Boolean.FALSE;
                this.valorPT = Boolean.FALSE;
                this.valorFP = Boolean.FALSE;
                this.valorCombo = Boolean.FALSE;
                txtpublicabusqueda = null;
                txtregistrobusqueda = null;
                cmbregistrobusqueda = null;
                txtmarcabusqueda = null;
                txtdemandantebusqueda = null;
                txtapoderadobusqueda = null;
                cldfecahpubbusqueda = null;
                break;
            case "MA":

                this.valorPB = Boolean.FALSE;
                this.valorRE = Boolean.FALSE;
                this.valorEX = Boolean.FALSE;
                this.valorMA = Boolean.TRUE;
                this.valorDO = Boolean.FALSE;
                this.valorDE = Boolean.FALSE;
                this.valorAP = Boolean.FALSE;
                this.valorOT = Boolean.FALSE;
                this.valorEP = Boolean.FALSE;
                this.valorPT = Boolean.FALSE;
                this.valorFP = Boolean.FALSE;
                this.valorCombo = Boolean.TRUE;
                txtpublicabusqueda = null;
                txtregistrobusqueda = null;

                cmbregistrobusqueda = null;
                txtexpedientebusqueda = null;
                txtexpedientebusquedagestion = null;
                txtdemandantebusqueda = null;
                txtapoderadobusqueda = null;
                cldfecahpubbusqueda = null;
                break;
            case "DO":

                this.valorPB = Boolean.FALSE;
                this.valorRE = Boolean.FALSE;
                this.valorEX = Boolean.FALSE;
                this.valorMA = Boolean.FALSE;
                this.valorDO = Boolean.TRUE;
                this.valorDE = Boolean.FALSE;
                this.valorAP = Boolean.FALSE;
                this.valorOT = Boolean.FALSE;
                this.valorEP = Boolean.FALSE;
                this.valorPT = Boolean.FALSE;
                this.valorFP = Boolean.FALSE;
                this.valorCombo = Boolean.TRUE;
                txtpublicabusqueda = null;
                txtregistrobusqueda = null;
                cmbregistrobusqueda = null;
                txtexpedientebusqueda = null;
                txtexpedientebusquedagestion = null;
                txtmarcabusqueda = null;
                txtapoderadobusqueda = null;
                cldfecahpubbusqueda = null;
                break;

            case "DE":

                this.valorPB = Boolean.FALSE;
                this.valorRE = Boolean.FALSE;
                this.valorEX = Boolean.FALSE;
                this.valorMA = Boolean.FALSE;
                this.valorDO = Boolean.FALSE;
                this.valorDE = Boolean.TRUE;
                this.valorAP = Boolean.FALSE;
                this.valorOT = Boolean.FALSE;
                this.valorEP = Boolean.FALSE;
                this.valorPT = Boolean.FALSE;
                this.valorFP = Boolean.FALSE;
                this.valorCombo = Boolean.TRUE;
                txtpublicabusqueda = null;
                txtregistrobusqueda = null;
                cmbregistrobusqueda = null;
                txtexpedientebusqueda = null;
                txtexpedientebusquedagestion = null;
                txtmarcabusqueda = null;
                txtapoderadobusqueda = null;
                cldfecahpubbusqueda = null;
                break;

            case "AP":

                this.valorPB = Boolean.FALSE;
                this.valorRE = Boolean.FALSE;
                this.valorEX = Boolean.FALSE;
                this.valorMA = Boolean.FALSE;
                this.valorDO = Boolean.FALSE;
                this.valorDE = Boolean.FALSE;
                this.valorAP = Boolean.TRUE;
                this.valorOT = Boolean.FALSE;
                this.valorEP = Boolean.FALSE;
                this.valorPT = Boolean.FALSE;
                this.valorFP = Boolean.FALSE;
                this.valorCombo = Boolean.TRUE;
                txtpublicabusqueda = null;
                txtregistrobusqueda = null;
                cmbregistrobusqueda = null;
                txtexpedientebusqueda = null;
                txtexpedientebusquedagestion = null;
                txtmarcabusqueda = null;
                txtdemandantebusqueda = null;
                cldfecahpubbusqueda = null;
                break;
            case "OT":

                this.valorPB = Boolean.FALSE;
                this.valorRE = Boolean.FALSE;
                this.valorEX = Boolean.FALSE;
                this.valorMA = Boolean.FALSE;
                this.valorDO = Boolean.FALSE;
                this.valorDE = Boolean.FALSE;
                this.valorAP = Boolean.FALSE;
                this.valorOT = Boolean.TRUE;
                this.valorEP = Boolean.FALSE;
                this.valorPT = Boolean.FALSE;
                this.valorFP = Boolean.FALSE;
                break;
            case "EP":

                this.valorPB = Boolean.FALSE;
                this.valorRE = Boolean.FALSE;
                this.valorEX = Boolean.FALSE;
                this.valorMA = Boolean.FALSE;
                this.valorDO = Boolean.FALSE;
                this.valorDE = Boolean.FALSE;
                this.valorAP = Boolean.FALSE;
                this.valorOT = Boolean.FALSE;
                this.valorEP = Boolean.TRUE;
                this.valorPT = Boolean.FALSE;
                this.valorFP = Boolean.FALSE;

                break;
            case "PT":

                this.valorPB = Boolean.FALSE;
                this.valorRE = Boolean.FALSE;
                this.valorEX = Boolean.FALSE;
                this.valorMA = Boolean.FALSE;
                this.valorDO = Boolean.FALSE;
                this.valorDE = Boolean.FALSE;
                this.valorAP = Boolean.FALSE;
                this.valorOT = Boolean.FALSE;
                this.valorEP = Boolean.FALSE;
                this.valorPT = Boolean.TRUE;
                this.valorFP = Boolean.FALSE;
                break;

            case "FP":

                this.valorPB = Boolean.FALSE;
                this.valorRE = Boolean.FALSE;
                this.valorEX = Boolean.FALSE;
                this.valorMA = Boolean.FALSE;
                this.valorDO = Boolean.FALSE;
                this.valorDE = Boolean.FALSE;
                this.valorAP = Boolean.FALSE;
                this.valorOT = Boolean.FALSE;
                this.valorEP = Boolean.FALSE;
                this.valorPT = Boolean.FALSE;
                this.valorFP = Boolean.TRUE;
                this.valorCombo = Boolean.FALSE;
                txtpublicabusqueda = null;
                txtregistrobusqueda = null;
                cmbregistrobusqueda = null;
                txtexpedientebusqueda = null;
                txtexpedientebusquedagestion = null;
                txtmarcabusqueda = null;
                txtdemandantebusqueda = null;
                txtapoderadobusqueda = null;

                break;

            default:
                this.valorPB = Boolean.FALSE;
                this.valorRE = Boolean.FALSE;
                this.valorEX = Boolean.FALSE;
                this.valorMA = Boolean.FALSE;
                this.valorDO = Boolean.FALSE;
                this.valorDE = Boolean.FALSE;
                this.valorAP = Boolean.FALSE;
                this.valorOT = Boolean.FALSE;
                this.valorEP = Boolean.FALSE;
                this.valorPT = Boolean.FALSE;
                this.valorFP = Boolean.FALSE;
                break;
        }
    }
    
    
       public String cerrarDialogo() {
        if (valorRecibido != null) {
            RequestContext.getCurrentInstance().closeDialog("Exit");
            return "";
        } else {
            return "examenoposicion";
        }
    }

    /**
     * Metodo buscar registros especificos propios de oposicion Nombre Luis
     * Angel Quispe limachi Fecha 12/09/2016
     *
     * @throws java.lang.Exception
     */
    public void muestraDato() throws Exception {

        //Long txtpublicadmdodmte=devuelvenropublicacionxnropubydmdodmte(txtpublicabusqueda,cmbtipodmdodmte);
        switch (nombre) {
            case "PB":

                listaBusquedaOposicion = busquedaOposicionService.realizarBusquedaxnropublicacion(txtpublicabusqueda, cmbtipodmdodmte);

                break;
            case "RE":
                listaBusquedaOposicion = busquedaOposicionService.realizarBusquedaxnroregistro(txtregistrobusqueda, cmbregistrobusqueda, "DMTE");
                break;
            case "EX":
                listaBusquedaOposicion = busquedaOposicionService.realizarBusquedaxnrosm(txtexpedientebusqueda, txtexpedientebusquedagestion, "DMTE");
                break;
            case "MA":
                listaBusquedaOposicion = busquedaOposicionService.realizarBusquedaxmarca(txtmarcabusqueda, cmbtipodmdodmte);
                break;
            case "DE":
                listaBusquedaOposicion = busquedaOposicionService.realizarBusquedaxdmdodmte(txtdemandantebusqueda, cmbtipodmdodmte);
                break;
            case "AP":
                listaBusquedaOposicion = busquedaOposicionService.realizarBusquedaxapoderado(txtapoderadobusqueda, cmbtipodmdodmte);
                break;
            case "FP":
                listaBusquedaOposicion = busquedaOposicionService.realizarBusquedaxfechapresentacion(cldfecahpubbusqueda, "DMTE");

                break;
            case "EP":

                break;
            case "PT":

                break;

            default:

                break;
        }

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuntero() {
        return puntero;
    }

    public void setPuntero(String puntero) {
        this.puntero = puntero;
    }

    public Boolean getVariok() {
        return variok;
    }

    public void setVariok(Boolean variok) {
        this.variok = variok;
    }

    public Boolean getVariacombo() {
        return variacombo;
    }

    public void setVariacombo(Boolean variacombo) {
        this.variacombo = variacombo;
    }

    public Boolean getVariacombo2() {
        return variacombo2;
    }

    public void setVariacombo2(Boolean variacombo2) {
        this.variacombo2 = variacombo2;
    }

    public Boolean getVariok1() {
        return variok1;
    }

    public void setVariok1(Boolean variok1) {
        this.variok1 = variok1;
    }

    public Boolean getValorPB() {
        return valorPB;
    }

    public void setValorPB(Boolean valorPB) {
        this.valorPB = valorPB;
    }

    public Boolean getValorRE() {
        return valorRE;
    }

    public void setValorRE(Boolean valorRE) {
        this.valorRE = valorRE;
    }

    public Boolean getValorEX() {
        return valorEX;
    }

    public void setValorEX(Boolean valorEX) {
        this.valorEX = valorEX;
    }

    public Boolean getValorMA() {
        return valorMA;
    }

    public void setValorMA(Boolean valorMA) {
        this.valorMA = valorMA;
    }

    public Boolean getValorDO() {
        return valorDO;
    }

    public void setValorDO(Boolean valorDO) {
        this.valorDO = valorDO;
    }

    public Boolean getValorDE() {
        return valorDE;
    }

    public void setValorDE(Boolean valorDE) {
        this.valorDE = valorDE;
    }

    public Boolean getValorAP() {
        return valorAP;
    }

    public void setValorAP(Boolean valorAP) {
        this.valorAP = valorAP;
    }

    public Boolean getValorFP() {
        return valorFP;
    }

    public void setValorFP(Boolean valorFP) {
        this.valorFP = valorFP;
    }

    public Boolean getValorOT() {
        return valorOT;
    }

    public void setValorOT(Boolean valorOT) {
        this.valorOT = valorOT;
    }

    public Boolean getValorEP() {
        return valorEP;
    }

    public void setValorEP(Boolean valorEP) {
        this.valorEP = valorEP;
    }

    public Boolean getValorPT() {
        return valorPT;
    }

    public void setValorPT(Boolean valorPT) {
        this.valorPT = valorPT;
    }

    public Boolean getRadioNombre() {
        return radioNombre;
    }

    public void setRadioNombre(Boolean radioNombre) {
        this.radioNombre = radioNombre;
    }

    public Boolean getValorXX() {
        return valorXX;
    }

    public void setValorXX(Boolean valorXX) {
        this.valorXX = valorXX;
    }

    public Boolean getAjusta() {
        return ajusta;
    }

    public void setAjusta(Boolean ajusta) {
        this.ajusta = ajusta;
    }

    public Boolean getValorCombo() {
        return valorCombo;
    }

    public void setValorCombo(Boolean valorCombo) {
        this.valorCombo = valorCombo;
    }

    public Long getTxtpublicabusqueda() {
        return txtpublicabusqueda;
    }

    public void setTxtpublicabusqueda(Long txtpublicabusqueda) {
        this.txtpublicabusqueda = txtpublicabusqueda;
    }

    public Integer getTxtregistrobusqueda() {
        return txtregistrobusqueda;
    }

    public void setTxtregistrobusqueda(Integer txtregistrobusqueda) {
        this.txtregistrobusqueda = txtregistrobusqueda;
    }

    public Long getTxtexpedientebusqueda() {
        return txtexpedientebusqueda;
    }

    public void setTxtexpedientebusqueda(Long txtexpedientebusqueda) {
        this.txtexpedientebusqueda = txtexpedientebusqueda;
    }

    public Long getTxtexpedientebusquedagestion() {
        return txtexpedientebusquedagestion;
    }

    public void setTxtexpedientebusquedagestion(Long txtexpedientebusquedagestion) {
        this.txtexpedientebusquedagestion = txtexpedientebusquedagestion;
    }

    public String getTxtmarcabusqueda() {
        return txtmarcabusqueda;
    }

    public void setTxtmarcabusqueda(String txtmarcabusqueda) {
        this.txtmarcabusqueda = txtmarcabusqueda;
    }

    public String getTxtdemandantebusqueda() {
        return txtdemandantebusqueda;
    }

    public void setTxtdemandantebusqueda(String txtdemandantebusqueda) {
        this.txtdemandantebusqueda = txtdemandantebusqueda;
    }

    public String getTxtapoderadobusqueda() {
        return txtapoderadobusqueda;
    }

    public void setTxtapoderadobusqueda(String txtapoderadobusqueda) {
        this.txtapoderadobusqueda = txtapoderadobusqueda;
    }

    public Date getCldfecahpubbusqueda() {
        return cldfecahpubbusqueda;
    }

    public void setCldfecahpubbusqueda(Date cldfecahpubbusqueda) {
        this.cldfecahpubbusqueda = cldfecahpubbusqueda;
    }

    public String getCmbregistrobusqueda() {
        return cmbregistrobusqueda;
    }

    public void setCmbregistrobusqueda(String cmbregistrobusqueda) {
        this.cmbregistrobusqueda = cmbregistrobusqueda;
    }

    public String getNombrefechabusqueda() {
        return nombrefechabusqueda;
    }

    public void setNombrefechabusqueda(String nombrefechabusqueda) {
        this.nombrefechabusqueda = nombrefechabusqueda;
    }

    public String getCmbtipodmdodmte() {
        return cmbtipodmdodmte;
    }

    public void setCmbtipodmdodmte(String cmbtipodmdodmte) {
        this.cmbtipodmdodmte = cmbtipodmdodmte;
    }

    public List<Dominio> getLstDmteDmdo() {
        return lstDmteDmdo;
    }

    public void setLstDmteDmdo(List<Dominio> lstDmteDmdo) {
        this.lstDmteDmdo = lstDmteDmdo;
    }

    public List<BusquedaOposicion> getListaBusquedaOposicion() {
        return listaBusquedaOposicion;
    }

    public void setListaBusquedaOposicion(List<BusquedaOposicion> listaBusquedaOposicion) {
        this.listaBusquedaOposicion = listaBusquedaOposicion;
    }

    public BusquedaOposicionService getBusquedaOposicionService() {
        return busquedaOposicionService;
    }

    public void setBusquedaOposicionService(BusquedaOposicionService busquedaOposicionService) {
        this.busquedaOposicionService = busquedaOposicionService;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getValorRecibido() {
        return valorRecibido;
    }

    public void setValorRecibido(String valorRecibido) {
        this.valorRecibido = valorRecibido;
    }

    public List<Dominio> getLstSerieRegistro() {
        return lstSerieRegistro;
    }

    public void setLstSerieRegistro(List<Dominio> lstSerieRegistro) {
        this.lstSerieRegistro = lstSerieRegistro;
    }

    public DominioService getDominioService() {
        return dominioService;
    }

    public void setDominioService(DominioService dominioService) {
        this.dominioService = dominioService;
    }
    
    
    
    
    

}
