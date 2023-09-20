/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.notificacion;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import snp.gob.bo.gimodel.servicio.ListaUsuarioRolService;
import snp.gob.bo.gimodel.servicio.UsuarioPaginaService;
/**
 *
 * @author levi
 */
@ManagedBean
@ViewScoped
public class NotiPeticionController {
    private Integer numIntoBloque;
    private String descripcion;
    private String demanSolicitante;
    private String demanApoderado;
    private String demanFojas;
    private String demanNotificarCon;
    private String demanCelular;
    private String demanDomicilio;
    private String creaModifieli;
    private String expediente;
    private boolean habAdiciona;
    private boolean habModifica;
    private boolean habElimina;
    private Integer pos;
    private List<TramiteAPedir> listaAPeticion =    new ArrayList<TramiteAPedir>();
    private List<TramiteAPedir> listaEscoge =    new ArrayList<TramiteAPedir>();
    private Boolean habBusca;
    
    private String text;
    
     @ManagedProperty(value = "#{usuarioPaginaService}")
    private UsuarioPaginaService usuarioPaginaService;
    @PostConstruct
    public void init() {
         
        try {
            habBusca=usuarioPaginaService.estadoBotonUsuario("1", "noti_busca");
            System.out.println("habBusca::"+habBusca);
            
            numIntoBloque=1;
            creaModifieli="1";
            habModifica=true;
            habAdiciona=false;
            habElimina=false;
        } catch (Exception ex) {
            Logger.getLogger(NotiPeticionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void buscaModi(){
        listaAPeticion.clear();
        TramiteAPedir tramite= new TramiteAPedir();
        tramite.setNumero(1);
        tramite.setExpediente("SM-00001-2016");
        tramite.setDemandante("PIL s.r.l");
        tramite.setCon("Decreto");
        tramite.setFojas("10");
        /*De aqui ena delante lo que se va añadiendo al objeto tramite es para recuperar en su modificacion para el caso de CREAR*/
        tramite.setDemanSolicitante("PIL s.r.l");
        tramite.setDemanApoderado("Juan Moreno");
        tramite.setDemanFojas("50");
        tramite.setDemanNotificarCon("Decreto");
        tramite.setDemanCelular("64542151");
        tramite.setDemanDomicilio("11213544");
        listaAPeticion.add(tramite);
        
        
        TramiteAPedir tramite2= new TramiteAPedir();
        tramite2.setNumero(2);
        tramite2.setExpediente("SM-00002-2016");
        tramite2.setDemandante("AFGANO");
        tramite2.setCon("Decreto");
        tramite2.setFojas("45");
        /*De aqui ena delante lo que se va añadiendo al objeto tramite es para recuperar en su modificacion para el caso de CREAR*/
        tramite2.setDemanSolicitante("AFGANO");
        tramite2.setDemanApoderado("LISNER Asociados");
        tramite2.setDemanFojas("2");
        tramite2.setDemanNotificarCon("Auto");
        tramite2.setDemanCelular("");
        tramite2.setDemanDomicilio("3242334");
        listaAPeticion.add(tramite2);
        
        
        
        TramiteAPedir tramite3= new TramiteAPedir();
        tramite3.setNumero(3);
        tramite3.setExpediente("SM-00003-2016");
        tramite3.setDemandante("Vino Sangria");
        tramite3.setCon("Auto");
        tramite3.setFojas("3");
        /*De aqui ena delante lo que se va añadiendo al objeto tramite es para recuperar en su modificacion para el caso de CREAR*/
        tramite3.setDemanSolicitante("Vino Snagria");
        tramite3.setDemanApoderado("Juan García");
        tramite3.setDemanFojas("34");
        tramite3.setDemanNotificarCon("Decreto");
        tramite3.setDemanCelular("");
        tramite3.setDemanDomicilio("3242334");
        listaAPeticion.add(tramite3);
    
    }
    
    public void busca(){
        /*Esta parte se va a cambiar a futuro*/
            descripcion ="FRIGOLIN";
            demanSolicitante="Industrias Americanas";
            demanApoderado="Juan Jose Nieto"; 
            demanFojas="34 ";
            demanNotificarCon ="";
            demanCelular="6714587858";
            demanDomicilio="254151585";
    
    
    
    
    }
    public void adicionaTabla(){
        
        
        TramiteAPedir tramite= new TramiteAPedir();
        tramite.setNumero(listaAPeticion.size()+1);
        tramite.setExpediente("SM-0000"+listaAPeticion.size()+"-2016");
        tramite.setDemandante(this.descripcion);
        tramite.setCon(this.demanNotificarCon);
        tramite.setFojas(this.demanFojas);
        /*De aqui ena delante lo que se va añadiendo al objeto tramite es para recuperar en su modificacion para el caso de CREAR*/
        tramite.setDemanSolicitante(demanSolicitante);
        tramite.setDemanApoderado(demanApoderado);
        tramite.setDemanFojas(demanFojas);
        tramite.setDemanNotificarCon(demanNotificarCon);
        tramite.setDemanCelular(demanCelular);
        tramite.setDemanDomicilio(demanDomicilio);
        listaAPeticion.add(tramite);
        limpiaVar();
        numIntoBloque=(Integer)listaAPeticion.size()+1;
        System.out.println("num aqui::"+numIntoBloque);
        //System.out.println("tasm listaAPeticion"+listaAPeticion.size());
        
        
        
    }
    public void modificaTabla1(){
        System.out.println("tam listaEscoge"+listaEscoge.size());
        if(listaEscoge.size()!=1)
        {
             FacesContext context = FacesContext.getCurrentInstance();
          context.addMessage(null, new FacesMessage("DEBE ESCOGER UN REGISTRO PARA HACER SU MODIFICACION", ""));
        }
        else{
            descripcion =listaEscoge.get(0).getDemandante();
            demanSolicitante=listaEscoge.get(0).getDemanSolicitante();
            demanApoderado=listaEscoge.get(0).getDemanApoderado(); 
            demanFojas=listaEscoge.get(0).getDemanFojas();
            demanNotificarCon =listaEscoge.get(0).getDemanNotificarCon();
            demanCelular=listaEscoge.get(0).getDemanCelular();
            demanDomicilio=listaEscoge.get(0).getDemanDomicilio();
            System.out.println("descripcion::"+descripcion);
            System.out.println("demanSolicitante::"+demanSolicitante);
            System.out.println("demanApoderado::"+demanApoderado);
            System.out.println("demanFojas::"+demanFojas);
            System.out.println("demanNotificarCon::"+demanNotificarCon);
            listaAPeticion.remove(listaEscoge.get(0));
            ordenaTabla();
            numIntoBloque=(Integer)listaAPeticion.size()+1;
        }
    
    
    }
    public void modificaTabla(){
        TramiteAPedir tramite= new TramiteAPedir();
        tramite.setNumero(pos+1);
        tramite.setExpediente(expediente);
        tramite.setDemandante(this.descripcion);
        tramite.setCon(this.demanNotificarCon);
        tramite.setFojas(this.demanFojas);
        /*De aqui ena delante lo que se va añadiendo al objeto tramite es para recuperar en su modificacion para el caso de CREAR*/
        tramite.setDemanSolicitante(demanSolicitante);
        tramite.setDemanApoderado(demanApoderado);
        tramite.setDemanFojas(demanFojas);
        tramite.setDemanNotificarCon(demanNotificarCon);
        tramite.setDemanCelular(demanCelular);
        tramite.setDemanDomicilio(demanDomicilio);
        listaAPeticion.set(pos, tramite);
         
    
    
    }
    public void onRowSelect(SelectEvent event){
        

      
         /*Esta variable de pos se queda en memoria del ultimo doble click que haga*/
            pos=((TramiteAPedir)event.getObject()).getNumero()-1;
            //System.out.println("pos"+pos);
    //    System.out.println("posicion::"+posicion);
            expediente=listaEscoge.get(0).getExpediente();
            descripcion =listaEscoge.get(0).getDemandante();
            demanSolicitante=listaEscoge.get(0).getDemanSolicitante();
            demanApoderado=listaEscoge.get(0).getDemanApoderado(); 
            demanFojas=listaEscoge.get(0).getDemanFojas();
            demanNotificarCon =listaEscoge.get(0).getDemanNotificarCon();
            demanCelular=listaEscoge.get(0).getDemanCelular();
            demanDomicilio=listaEscoge.get(0).getDemanDomicilio();
    
    }
    public void eliminaTabla(){
    
        System.out.println("tam listaEscoge"+listaEscoge.size());
        for(int i=0;i<=listaEscoge.size()-1;i++)
        {
          listaAPeticion.remove(listaEscoge.get(i));
        }
        ordenaTabla();
         numIntoBloque=(Integer)listaAPeticion.size()+1;
         limpiaVar();
        System.out.println("Nuevo tamaño::"+listaAPeticion.size());
    
    }
    public void limpiaVar(){
            descripcion ="";
            demanSolicitante="";
            demanApoderado=""; 
            demanFojas="";
            demanNotificarCon ="";
            demanCelular="";
            demanDomicilio="";
    }
    public void ordenaTabla(){
        for(int i=0;i<=listaAPeticion.size()-1;i++){
            listaAPeticion.get(i).setNumero(i+1);
        
        }
    
    }
    
    public void cambiaRadioButon(){
    
       if(creaModifieli.equals("1"))
       {   habAdiciona=false;
           habElimina=false;
           habModifica=true;
           limpiaVar();
       }     
       if(creaModifieli.equals("2"))
       {    habAdiciona=false;
            habElimina=false;
            habModifica=false;
       }     
       if(creaModifieli.equals("3"))
       {    habAdiciona=true;
            habElimina=true;
            habModifica=true;
       }     
    }

    public String getText() {
        System.out.println("en el geter::"+text);
        return text;
    }

    public void setText(String text) {
        System.out.println("en el seter::"+text);
        this.text = text;
    }

    public String getExpediente() {
        return expediente;
    }

    public Boolean getHabBusca() {
        return habBusca;
    }

    public void setHabBusca(Boolean habBusca) {
        this.habBusca = habBusca;
    }

    public UsuarioPaginaService getUsuarioPaginaService() {
        return usuarioPaginaService;
    }

    public void setUsuarioPaginaService(UsuarioPaginaService usuarioPaginaService) {
        this.usuarioPaginaService = usuarioPaginaService;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }

    public boolean getHabAdiciona() {
        return habAdiciona;
    }

    public void setHabAdiciona(boolean habAdiciona) {
        this.habAdiciona = habAdiciona;
    }

    public boolean getHabModifica() {
        return habModifica;
    }

    public void setHabModifica(boolean habModifica) {
        this.habModifica = habModifica;
    }

    public boolean getHabElimina() {
        return habElimina;
    }

    public void setHabElimina(boolean habElimina) {
        this.habElimina = habElimina;
    }

    
    
    
    public Integer getNumIntoBloque() {
        return numIntoBloque;
    }

    public void setNumIntoBloque(Integer numIntoBloque) {
        this.numIntoBloque = numIntoBloque;
    }

    public String getCreaModifieli() {
        return creaModifieli;
    }

    public void setCreaModifieli(String creaModifieli) {
        this.creaModifieli = creaModifieli;
    }
    
    
    
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDemanSolicitante() {
        return demanSolicitante;
    }

    public void setDemanSolicitante(String demanSolicitante) {
        this.demanSolicitante = demanSolicitante;
    }

    public String getDemanApoderado() {
        return demanApoderado;
    }

    public void setDemanApoderado(String demanApoderado) {
        this.demanApoderado = demanApoderado;
    }

    public String getDemanFojas() {
        return demanFojas;
    }

    public void setDemanFojas(String demanFojas) {
        this.demanFojas = demanFojas;
    }

    public String getDemanNotificarCon() {
        return demanNotificarCon;
    }

    public void setDemanNotificarCon(String demanNotificarCon) {
        this.demanNotificarCon = demanNotificarCon;
    }

    public String getDemanCelular() {
        return demanCelular;
    }

    public void setDemanCelular(String demanCelular) {
        this.demanCelular = demanCelular;
    }

    public String getDemanDomicilio() {
        return demanDomicilio;
    }

    public void setDemanDomicilio(String demanDomicilio) {
        this.demanDomicilio = demanDomicilio;
    }

    public List<TramiteAPedir> getListaAPeticion() {
        return listaAPeticion;
    }

    public void setListaAPeticion(List<TramiteAPedir> listaAPeticion) {
        this.listaAPeticion = listaAPeticion;
    }

    public List<TramiteAPedir> getListaEscoge() {
        return listaEscoge;
    }

    public void setListaEscoge(List<TramiteAPedir> listaEscoge) {
        this.listaEscoge = listaEscoge;
    }
    
    
 
    
    
    
    
}
