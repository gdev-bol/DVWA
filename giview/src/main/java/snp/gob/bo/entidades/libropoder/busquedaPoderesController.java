/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.libropoder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.gimodel.entidad.Poder;
import snp.gob.bo.gimodel.servicio.PoderService;

/**
 *
 * @author levi
 */

@ManagedBean
@ViewScoped
public class busquedaPoderesController extends AbstractManagedBean implements Serializable{
    
    @ManagedProperty(value = "#{poderService}")
    private PoderService poderService;
    private int radioAgarrado;
    private String aBuscar;
    private List<Poder> listaPoderes= new ArrayList<Poder>();
    
     @PostConstruct
    public void init() {
        
        
    }
   public void busca() throws Exception{
      listaPoderes= poderService.listaPoderBusqueda(aBuscar, radioAgarrado);
      
   }
   public void limpia() {
      listaPoderes.clear();
       radioAgarrado=1;
       aBuscar=" ";
   }

           
    public PoderService getPoderService() {
        return poderService;
    }

    public void setPoderService(PoderService poderService) {
        this.poderService = poderService;
    }

    public int getRadioAgarrado() {
        return radioAgarrado;
    }

    public void setRadioAgarrado(int radioAgarrado) {
        this.radioAgarrado = radioAgarrado;
    }

  
    public List<Poder> getListaPoderes() {
        return listaPoderes;
    }

    public void setListaPoderes(List<Poder> listaPoderes) {
        this.listaPoderes = listaPoderes;
    }

    public String getaBuscar() {
        return aBuscar;
    }

    public void setaBuscar(String aBuscar) {
        this.aBuscar = aBuscar;
    }

   
    
    
}
