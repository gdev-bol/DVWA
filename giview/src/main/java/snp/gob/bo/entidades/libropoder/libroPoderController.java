/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.libropoder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import org.primefaces.context.RequestContext;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.entidad.Poder;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.PoderService;
import snp.gob.bo.gimodel.servicio.SigSignoMarcaService;

/**
 *
 * @author levi
 */
@ManagedBean
@ViewScoped
public class libroPoderController extends AbstractManagedBean implements Serializable {

    private List<Dominio> listaTipoTramite = new ArrayList<Dominio>();
    private List<Dominio> listaTipoPoder = new ArrayList<Dominio>();
    private List<Poder> listaPoderes = new ArrayList<Poder>();
    private String comboTipoTramite;
    private String comboTipoPoder;
    private Long numTramite;
    private Integer gestion;
    private String nro_testimonio;
    private Date fecha_testimonio;
    private String notario;
    private String nom_confiere_poder;
    private String dom_confiere_poder;
    private String apoderado;
    private String comboPoderRevocado;
    private String obs;
    private String template;
    private SigSignoMarca signoMarca;
    int sw;
    private Poder poderAgarrado;
    private Long idPoderAModificar;

    @ManagedProperty(value = "#{dominioService}")
    private DominioService dominioService;
    @ManagedProperty(value = "#{poderService}")
    private PoderService poderService;
    @ManagedProperty(value = "#{sigSignoMarcaService}")
    private SigSignoMarcaService sigSignoMarcaService;
    @ManagedProperty(value = "#{comunService}")
    private ComunService comunService;

    @PostConstruct
    public void init() {

        sw = 0;
        try {
            Map<String, String[]> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterValuesMap();
            //template = "./../WEB-INF/facelets/templates/DialogTemplate.xhtml";
            if (params.get("datosGenerales") == null || params.get("datosGenerales").equals("")) {
                template = "./../WEB-INF/facelets/templates/Template.xhtml";

            } else {

                template = "./../WEB-INF/facelets/templates/DialogTemplate.xhtml";
                /*System.out.println("********************************************************");
                System.out.println("pos0 al otro lado::" + params.get("datosGenerales")[0]);
                System.out.println("pos1 otro lado::" + params.get("datosGenerales")[1]);
                System.out.println("pos2 al otro lado::" + params.get("datosGenerales")[2]);
                System.out.println("pos3 al otro lado::" + params.get("datosGenerales")[3]);
                System.out.println("testimonio al otro lado::" + params.get("datosGenerales")[4]);
                System.out.println("confiere al otro lado::" + params.get("datosGenerales")[5]);
                System.out.println("domicilio al otro lado::" + params.get("datosGenerales")[6]);
                System.out.println("apoderado al otro lado::" + params.get("datosGenerales")[7]);
                System.out.println("********************************************************");*/
                if(!params.get("datosGenerales")[4].equals("") && params.get("datosGenerales")[4] != null)
                {
                    nro_testimonio=params.get("datosGenerales")[4];
                }
                if(!params.get("datosGenerales")[5].equals("") && params.get("datosGenerales")[5] != null)
                {
                    nom_confiere_poder=params.get("datosGenerales")[5];
                }
                
                if(!params.get("datosGenerales")[6].equals("") && params.get("datosGenerales")[6] != null)
                {
                    dom_confiere_poder=params.get("datosGenerales")[6];
                }
                
                if(!params.get("datosGenerales")[7].equals("") && params.get("datosGenerales")[7] != null)
                {
                    
                    apoderado=params.get("datosGenerales")[7];
                }
                //System.out.println("NUMERO::" + params.get("datosGenerales")[2]);
                //System.out.println("GESTION::" + params.get("datosGenerales")[3]);
                //signoMarca= sigSignoMarcaService.obtenerSigSignoMarcaXidSignoMarca(Long.parseLong(params.get("datosGenerales")[2]));
                //signoMarca= sigSignoMarcaService.obtenerSigSignoMarcaXidSignoMarca(Long.parseLong(params.get("datosGenerales")[2]));
                //comboTipoTramite="SM";
                if (params.get("datosGenerales")[1].equals("SM")) {
                    comboTipoTramite = params.get("datosGenerales")[1];
                    HashMap datosSM = comunService.obtenerNumeroGestionExtensionCodigoSM(Long.parseLong(params.get("datosGenerales")[2]));
                    
                    numTramite = Long.parseLong((String) datosSM.get("numero"));
                    gestion = Integer.parseInt((String) datosSM.get("gestion"));
                } else {
                    comboTipoTramite = params.get("datosGenerales")[1];
                    numTramite = Long.parseLong(params.get("datosGenerales")[2]);
                    gestion = Integer.parseInt(params.get("datosGenerales")[3]);
                }

//                 HashMap datosSM=comunService.obtenerNumeroGestionExtensionCodigoSM(signoMarca.getSm());
//                 numTramite=Long.parseLong((String)datosSM.get("numero"));
//                 gestion=Integer.parseInt((String)datosSM.get("gestion"));
                listaPoderes = poderService.listaPoderXFiltro(comboTipoTramite, numTramite, gestion);
            }
            listaTipoTramite = dominioService.obtenerListadoDominio("tipo_tramite_poder");
            listaTipoPoder = dominioService.obtenerListadoDominio("tipo_poder");
        } catch (Exception ex) {
            Logger.getLogger(libroPoderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void busca() throws Exception {
        //   System.out.println("entra a buscar"+comboTipoTramite+ numTramite+ gestion);
        listaPoderes = poderService.listaPoderXFiltro(comboTipoTramite, numTramite, gestion);
        //     System.out.println("tam listaPoderes::"+listaPoderes.size());

    }

    public void muestraBusquedaPoder() {
//        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
//            sessionMap.put("notifiObj", notifica);
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", true);
        options.put("height", 560);
        options.put("width", "900");
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("modal", true);

        Map<String, List<String>> var = new HashMap<String, List<String>>();
        List<String> params = new ArrayList();
        params.add("1");//Pondria el primary key para encontrar todos los datos que necesito, aunque lo mejor es que me pase todo lo que necesito en un objeto
        params.add("signo");//Para otros tendría que ser modificacion u oposicion o renovación
        var.put("datosGenerales", params);
        RequestContext.getCurrentInstance().openDialog("/libropoder/busquedaPoderes", options, var);

    }
    
     public void accionCerrar() {
        RequestContext.getCurrentInstance().closeDialog("Exit");        
    }

    public String obtieneTipoPoder(String codigo) throws Exception {
        String tipoPoder = "";
        // System.out.println("codigo::"+codigo);
        List<Dominio> listDominio = dominioService.obtenerListadoDominioXCodigo("tipo_poder", codigo);
        // System.out.println("tam listDominio"+listDominio.size());

        tipoPoder = listDominio.get(0).getNombre();
        return tipoPoder;

    }

    public void adicionaPoder() throws Exception {
        //si el sw es igual  a cero es solo adicion ar si es 1 es que va pro modificacion
        if (sw == 0) {
           
            Poder poder = new Poder();
            //  poder.setIdpoder(1L);

            poder.setIdlogtrans(1L);
            poder.setTipo_poder(this.comboTipoPoder);
            poder.setTipo_tramite(comboTipoTramite);
            poder.setNro_exped(numTramite);
            poder.setGestion(gestion);
            poder.setNro_testimonio(nro_testimonio);
            poder.setFecha_testimonio(fecha_testimonio);
            poder.setNotario(notario);
            poder.setNom_confiere_poder(nom_confiere_poder);
            poder.setDom_confiere_poder(dom_confiere_poder);
            poder.setApoderado(apoderado);
            poder.setPoder_revocado(comboPoderRevocado);
            poder.setObs(obs);
            poder.setEstado("AC");
            poderService.crudPoder(poder, 1);

            listaPoderes.add(poder);
        } else {
            System.out.println("Entra por elese");
            System.out.println("idPoderAModificar:::" + idPoderAModificar);
            Poder poder = new Poder();
            //  poder.setIdpoder(1L);

            poder.setIdpoder(idPoderAModificar);
            poder.setIdlogtrans(1L);
            poder.setTipo_poder(this.comboTipoPoder);
            poder.setTipo_tramite(comboTipoTramite);
            poder.setNro_exped(numTramite);
            poder.setGestion(gestion);
            poder.setNro_testimonio(nro_testimonio);
            poder.setFecha_testimonio(fecha_testimonio);
            poder.setNotario(notario);
            poder.setNom_confiere_poder(nom_confiere_poder);
            poder.setDom_confiere_poder(dom_confiere_poder);
            poder.setApoderado(apoderado);
            poder.setPoder_revocado(comboPoderRevocado);
            poder.setObs(obs);
            poder.setEstado("AC");
            poderService.crudPoder(poder, 2);

            listaPoderes.add(poder);
            sw = 0;
        }
        // System.out.println("AL adicionar tam de l alista:::"+listaPoderes.size());
        //limpiaFiltros();
        limpiaFiltrosNecesario();
        /**
         * ***********************************************************************
         */

    }
//    public void guardaPoder() throws Exception{
//    // System.out.println("AL aceptarr al final:::"+listaPoderes.size());
//        List<Poder> listPoderes=poderService.listaPoderXFiltro(comboTipoTramite, numTramite, gestion);
//         if(listPoderes.size()>listaPoderes.size())
//         {
//               for (int i=0;i<=listPoderes.size()-1;i++)
//               {
//                   poderService.crudPoder(listPoderes.get(i), 3);   
//               }
//               for (int i=0;i<=listaPoderes.size()-1;i++)
//               {
//                    poderService.crudPoder(listaPoderes.get(i), 1); 
//               }
//               
//               
//               
//         } 
//         else{
//         
//         
//        
//           for (int i=0;i<=listaPoderes.size()-1;i++)
//           {
//          // System.out.println("idpoder::"+listaPoderes.get(i).getIdpoder());
//            //coloca if y verifica si tiene poderes su idpoder o no, los uqe tienen se hacen update los uqe no  hacen insert
//               if(listaPoderes.get(i).getIdpoder() != null && !listaPoderes.get(i).getIdpoder().equals(""))
//                 {  
//                     poderService.crudPoder(listaPoderes.get(i), 2);   
//            
//                 }
//               else{
//                  poderService.crudPoder(listaPoderes.get(i), 1);   
//                 }
//           
//           }     
//       }
//         FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "SE REALIZÓ LA ADICION/MODFICACION CORRECTAMENTE", "");
//         FacesContext.getCurrentInstance().addMessage(null, message);
//    }

    public void llenaCamposModifica(Long idpoder, int index) throws Exception {
        poderAgarrado = listaPoderes.get(index);
        idPoderAModificar = poderAgarrado.getIdpoder();
        comboTipoTramite = poderAgarrado.getTipo_tramite();
        numTramite = poderAgarrado.getNro_exped();
        gestion = poderAgarrado.getGestion();
        comboTipoPoder = poderAgarrado.getTipo_poder();
        nro_testimonio = poderAgarrado.getNro_testimonio();
        fecha_testimonio = poderAgarrado.getFecha_testimonio();
        notario = poderAgarrado.getNotario();
        nom_confiere_poder = poderAgarrado.getNom_confiere_poder();
        dom_confiere_poder = poderAgarrado.getDom_confiere_poder();
        apoderado = poderAgarrado.getApoderado();
        comboPoderRevocado = poderAgarrado.getPoder_revocado();
        obs = poderAgarrado.getObs();
        // System.out.println("tam listaPoderes1"+listaPoderes.size());
        listaPoderes.remove(index);
        //  poderService.crudPoder(poderAgarrado, 3);
        // System.out.println("tam listaPoderes2"+listaPoderes.size());
        sw = 1;
        //  System.out.println("TAM listaPoderes::"+listaPoderes.size());     

        /*Poder poderAgarrado=listaPoderes.get(index);
         comboTipoTramite=poderAgarrado.getTipo_tramite();
         numTramite=poderAgarrado.getNro_exped();
         gestion=poderAgarrado.getGestion();
         comboTipoPoder=poderAgarrado.getTipo_poder();
         nro_testimonio=poderAgarrado.getNro_testimonio();
         fecha_testimonio=poderAgarrado.getFecha_testimonio();
         notario=poderAgarrado.getNotario();
         nom_confiere_poder=poderAgarrado.getNom_confiere_poder();
         dom_confiere_poder=poderAgarrado.getDom_confiere_poder();
         apoderado=poderAgarrado.getApoderado();
         comboPoderRevocado=poderAgarrado.getPoder_revocado();
         obs=poderAgarrado.getObs();
         // System.out.println("tam listaPoderes1"+listaPoderes.size());
         listaPoderes.remove(index);
         poderService.crudPoder(poderAgarrado, 3);
         // System.out.println("tam listaPoderes2"+listaPoderes.size());
         sw=1;
         //  System.out.println("TAM listaPoderes::"+listaPoderes.size());   
         */
    }
    
    

    public void eliminaCampos(Long idpoder, int index) throws Exception {
        listaPoderes.remove(index);
        Poder poderElimina = new Poder();
        poderElimina.setIdpoder(idpoder);

        poderService.crudPoder(poderElimina, 3);

    }
   public void limpiaFiltrosNecesario(){
     nro_testimonio = "";
        fecha_testimonio = null;
        notario = "";
   comboPoderRevocado = "";
        obs = "";
   }
    public void limpiaFiltros() {
        comboTipoTramite="SM";
        numTramite=null;
        gestion=null;
        comboTipoPoder = "PO";
        nro_testimonio = "";
        fecha_testimonio = null;
        notario = "";
        nom_confiere_poder = "";
        dom_confiere_poder = "";
        apoderado = "";
        comboPoderRevocado = "";
        obs = "";

    }
    public void limpiaSegunCombo(){
        
        nro_testimonio = "";
        fecha_testimonio = null;
        notario = "";
        nom_confiere_poder ="";
        dom_confiere_poder ="";
        apoderado ="";
        comboPoderRevocado = "";
        obs = "";
        
       }

    public void limpiaTodo() {
        limpiaFiltros();
        listaPoderes.clear();
    }

    public DominioService getDominioService() {
        return dominioService;
    }

    public void setDominioService(DominioService dominioService) {
        this.dominioService = dominioService;
    }

    public String getComboTipoTramite() {
        return comboTipoTramite;
    }

    public void setComboTipoTramite(String comboTipoTramite) {
        this.comboTipoTramite = comboTipoTramite;
    }

    public Long getNumTramite() {
        return numTramite;
    }

    public void setNumTramite(Long numTramite) {
        this.numTramite = numTramite;
    }

    public Integer getGestion() {
        return gestion;
    }

    public void setGestion(Integer gestion) {
        this.gestion = gestion;
    }

    public List<Dominio> getListaTipoTramite() {
        return listaTipoTramite;
    }

    public void setListaTipoTramite(List<Dominio> listaTipoTramite) {
        this.listaTipoTramite = listaTipoTramite;
    }

    public List<Dominio> getListaTipoPoder() {
        return listaTipoPoder;
    }

    public void setListaTipoPoder(List<Dominio> listaTipoPoder) {
        this.listaTipoPoder = listaTipoPoder;
    }

    public String getComboTipoPoder() {
        return comboTipoPoder;
    }

    public void setComboTipoPoder(String comboTipoPoder) {
        this.comboTipoPoder = comboTipoPoder;
    }

    public List<Poder> getListaPoderes() {
        return listaPoderes;
    }

    public void setListaPoderes(List<Poder> listaPoderes) {
        this.listaPoderes = listaPoderes;
    }

    public PoderService getPoderService() {
        return poderService;
    }

    public void setPoderService(PoderService poderService) {
        this.poderService = poderService;
    }

    public String getNro_testimonio() {
        return nro_testimonio;
    }

    public void setNro_testimonio(String nro_testimonio) {
        this.nro_testimonio = nro_testimonio;
    }

    public Date getFecha_testimonio() {
        return fecha_testimonio;
    }

    public void setFecha_testimonio(Date fecha_testimonio) {
        this.fecha_testimonio = fecha_testimonio;
    }

    public String getNotario() {
        return notario;
    }

    public void setNotario(String notario) {
        this.notario = notario;
    }

    public String getNom_confiere_poder() {
        return nom_confiere_poder;
    }

    public void setNom_confiere_poder(String nom_confiere_poder) {
        this.nom_confiere_poder = nom_confiere_poder;
    }

    public String getDom_confiere_poder() {
        return dom_confiere_poder;
    }

    public void setDom_confiere_poder(String dom_confiere_poder) {
        this.dom_confiere_poder = dom_confiere_poder;
    }

    public String getApoderado() {
        return apoderado;
    }

    public void setApoderado(String apoderado) {
        this.apoderado = apoderado;
    }

    public String getComboPoderRevocado() {
        return comboPoderRevocado;
    }

    public void setComboPoderRevocado(String comboPoderRevocado) {
        this.comboPoderRevocado = comboPoderRevocado;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public int getSw() {
        return sw;
    }

    public void setSw(int sw) {
        this.sw = sw;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public SigSignoMarca getSignoMarca() {
        return signoMarca;
    }

    public void setSignoMarca(SigSignoMarca signoMarca) {
        this.signoMarca = signoMarca;
    }

    public SigSignoMarcaService getSigSignoMarcaService() {
        return sigSignoMarcaService;
    }

    public void setSigSignoMarcaService(SigSignoMarcaService sigSignoMarcaService) {
        this.sigSignoMarcaService = sigSignoMarcaService;
    }

    public ComunService getComunService() {
        return comunService;
    }

    public void setComunService(ComunService comunService) {
        this.comunService = comunService;
    }

}
