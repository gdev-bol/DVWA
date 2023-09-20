/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.bean.oposicion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import snp.gob.bo.gimodel.entidad.OpoHistorial;
import snp.gob.bo.gimodel.servicio.OpoHistorialService;
import snp.gob.bo.gimodel.servicio.OposicionService;

/**
 *
 * @author Luis Angel Quispe Limachi
 */
@ManagedBean(name = "historialOpoBean")
@ViewScoped
public class HistorialOpoBean {

    private Long numeropublihis;
    private Integer ordenopohis;
    private String marcasvs;
    private String estadohis;
    private String ubicacionhist;
    private Date fechalimhist;
    private String apoderadodmdohis; 
    private String observacionhis;
    private String operacionhist;
    private Date horaoperahist;
    private Long usuariohist;
    private OpoHistorial selectedOpoHisto;
        private List<OpoHistorial> listahistorial = new ArrayList<>();
    
    @ManagedProperty(value = "#{oposicionService}")
    private OposicionService oposicionService;
    
     @ManagedProperty(value = "#{opoHistorialService}")
    private OpoHistorialService opoHistorialService;
    
    public HistorialOpoBean() {
    }

    @PostConstruct
    public void HistorialOposBeanInit() {

        

        try {
            Map<String, String[]> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterValuesMap();
            //template = "./../WEB-INF/facelets/templates/DialogTemplate.xhtml";
            if (params.get("enviarDatosHisto") != null) {

                numeropublihis = Long.parseLong(params.get("enviarDatosHisto")[0]);
                ordenopohis = Integer.parseInt(params.get("enviarDatosHisto")[1]);

                Long claveprimariaopo = oposicionService.encuentraclaveprin(numeropublihis, ordenopohis);
                String marcadmda = oposicionService.obtenernombremarcaxidopo(claveprimariaopo);
                String marcadmte = oposicionService.obtenernombremarcaxidopo2(claveprimariaopo);
                marcasvs = marcadmda + " vs " + marcadmte;
    //            System.out.println("============================>" + marcasvs);
//                estadohis=oposicionService.obtienestadoopo(claveprimariaopo);
//                apoderadodmdohis=oposicionService.obtienenombreapoderado(claveprimariaopo);      
//                observacionhis=oposicionService.obtienerobservacionopo(claveprimariaopo);
                
                listahistorial=opoHistorialService.listarhistorialxnroopo(claveprimariaopo);
                
                
            } else {
                
            }

//         numeropubliseg=Long.parseLong(params.get("enviarDatos")[0]);
//         ordenoposeg=Integer.parseInt(params.get("enviarDatos")[1]);
        } catch (Exception ex) {
            Logger.getLogger(SeguimientoOpoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    public void onRowSelectOposicion(SelectEvent event) throws Exception {

        Long opos1 = ((OpoHistorial) event.getObject()).getIdhistorial();
        

        
//        marcadmtevist = opoMarcaDemandanteService.obtieneelobjetodmtexidoposicion(opos1);
//        txtorden_o = oposicionService.extranroorden(opos1);
//        System.out.println("Muestra ACambioo$$$$#########" + txtorden_o);
        
    estadohis=((OpoHistorial) event.getObject()).getEstado();
    ubicacionhist=((OpoHistorial) event.getObject()).getUbicacion();
    fechalimhist=((OpoHistorial) event.getObject()).getFecha_lim();
    apoderadodmdohis=((OpoHistorial) event.getObject()).getApoderado();
    observacionhis=((OpoHistorial) event.getObject()).getObservacion();
    operacionhist=((OpoHistorial) event.getObject()).getOperacion();
    horaoperahist=((OpoHistorial) event.getObject()).getFecha_operacion();
    usuariohist=((OpoHistorial) event.getObject()).getId_usuario();
    
    
    }
    
    
    
    
    public void onRowUnselectOposicion(UnselectEvent event) {

        
    }
    
    
    
    
     public String comvertiriduserxname(Long idusuario) throws Exception {
        if (idusuario != 0) {
            String nameuser = opoHistorialService.mostrarusuarioxid(idusuario);

            return nameuser;
        } else {
            return "";
        }
    }
    
    
    
//     String marcdmte = oposicionService.obtenernombremarcaxidopo(inidopodmtei) + "  Vs.  " + oposicionService.obtenernombremarcaxidopo2(inidopodmtei);
//    
//     public String comvertirnroopo(Long inidopodmtei) {
//        if (inidopodmtei != 0) {
//            String marcdmte = oposicionService.obtenernombremarcaxidopo(inidopodmtei) + "  Vs.  " + oposicionService.obtenernombremarcaxidopo2(inidopodmtei);
//
//            return marcdmte;
//        } else {
//            return "";
//        }
//    }
//    public String enviarlasdosmarca() throws Exception {
//        
//        Long claveprimariaopo=oposicionService.encuentraclaveprin(numeropublihis, ordenopohis);
//        
//        String marcadmda=oposicionService.obtapoderadodmdoxnroopo(claveprimariaopo);
//        String marcadmte=oposicionService.obtapoderadodmtexnroopo(claveprimariaopo);
//        return marcadmda + " vs " +marcadmte;
//        
//    }
//    
    
     /**
     * *
     * Método cerrar dialogo
     *
     * Creado: Luis Quispe Fecha:20/10/2016
     *
     */
    public void cerrarDialogo() {
        //pass back to root
        RequestContext.getCurrentInstance().closeDialog("Exit");
    }

    public Long getNumeropublihis() {
        return numeropublihis;
    }

    public void setNumeropublihis(Long numeropublihis) {
        this.numeropublihis = numeropublihis;
    }

    public Integer getOrdenopohis() {
        return ordenopohis;
    }

    public void setOrdenopohis(Integer ordenopohis) {
        this.ordenopohis = ordenopohis;
    }

    public OposicionService getOposicionService() {
        return oposicionService;
    }

    public void setOposicionService(OposicionService oposicionService) {
        this.oposicionService = oposicionService;
    }

    public String getMarcasvs() {
        return marcasvs;
    }

    public void setMarcasvs(String marcasvs) {
        this.marcasvs = marcasvs;
    }

    public String getEstadohis() {
        return estadohis;
    }

    public void setEstadohis(String estadohis) {
        this.estadohis = estadohis;
    }

    public String getApoderadodmdohis() {
        return apoderadodmdohis;
    }

    public void setApoderadodmdohis(String apoderadodmdohis) {
        this.apoderadodmdohis = apoderadodmdohis;
    }

    public String getObservacionhis() {
        return observacionhis;
    }

    public void setObservacionhis(String observacionhis) {
        this.observacionhis = observacionhis;
    }

    public List<OpoHistorial> getListahistorial() {
        return listahistorial;
    }

    public void setListahistorial(List<OpoHistorial> listahistorial) {
        this.listahistorial = listahistorial;
    }

    public OpoHistorialService getOpoHistorialService() {
        return opoHistorialService;
    }

    public void setOpoHistorialService(OpoHistorialService opoHistorialService) {
        this.opoHistorialService = opoHistorialService;
    }

    public OpoHistorial getSelectedOpoHisto() {
        return selectedOpoHisto;
    }

    public void setSelectedOpoHisto(OpoHistorial selectedOpoHisto) {
        this.selectedOpoHisto = selectedOpoHisto;
    }

    public String getUbicacionhist() {
        return ubicacionhist;
    }

    public void setUbicacionhist(String ubicacionhist) {
        this.ubicacionhist = ubicacionhist;
    }

    public Date getFechalimhist() {
        return fechalimhist;
    }

    public void setFechalimhist(Date fechalimhist) {
        this.fechalimhist = fechalimhist;
    }

    public String getOperacionhist() {
        return operacionhist;
    }

    public void setOperacionhist(String operacionhist) {
        this.operacionhist = operacionhist;
    }

    public Date getHoraoperahist() {
        return horaoperahist;
    }

    public void setHoraoperahist(Date horaoperahist) {
        this.horaoperahist = horaoperahist;
    }

    public Long getUsuariohist() {
        return usuariohist;
    }

    public void setUsuariohist(Long usuariohist) {
        this.usuariohist = usuariohist;
    }

   
    
    
}
