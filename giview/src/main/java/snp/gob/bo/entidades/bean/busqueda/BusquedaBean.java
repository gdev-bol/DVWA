/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.bean.busqueda;


import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

/**
 *
 * @author desarrollo
 */
@ManagedBean(name = "busquedaBean")
@ViewScoped
public class BusquedaBean {

    public BusquedaBean() {
    }
   

    private Boolean encabezado = Boolean.FALSE;

    private List<String> listado = new ArrayList<String>();

    private String cadena;
    private List<SelectItem> categories;
    private String selection;

    @PostConstruct
    public void ProtoOpoInit() {
        System.out.println("ESTOY AQUI  ");
        // nombre = "Mensaje Constructivo ";
        

        categories = new ArrayList<SelectItem>();
        SelectItemGroup group1 = new SelectItemGroup("CUERPOS CELESTES");
        SelectItemGroup group2 = new SelectItemGroup("SERES HUMANOS");
        SelectItemGroup group3 = new SelectItemGroup("ANIMALES");
        SelectItemGroup group4 = new SelectItemGroup("VEGETALES");

        SelectItemGroup group11 = new SelectItemGroup("ESTRELLAS COMETAS");
        SelectItemGroup group12 = new SelectItemGroup("SOL");
        SelectItemGroup group13 = new SelectItemGroup("TIERRA PLANETAS ");
        SelectItemGroup group14 = new SelectItemGroup("LUNA");

        SelectItemGroup group21 = new SelectItemGroup("FILANTROPOS");
        SelectItemGroup group22 = new SelectItemGroup("NORMALES");
        SelectItemGroup group23 = new SelectItemGroup("EXTRAORDINARIOS");
        SelectItemGroup group24 = new SelectItemGroup("NATURAL");
        SelectItemGroup group25 = new SelectItemGroup("JURIDICO");

        SelectItem option31 = new SelectItem("CARNIVORO", "CARNIVORO");
        SelectItem option32 = new SelectItem("HERBIVORO", "HERBIVORO");
        SelectItem option33 = new SelectItem("CUADRUPEDO", "CUADRUPEDO");
        SelectItem option34 = new SelectItem("AVE", "AVE");

        SelectItem option41 = new SelectItem("VERDES", "VERDES");

        SelectItem option111 = new SelectItem("COMETAS");
        SelectItem option112 = new SelectItem("METEOROS");
        group11.setSelectItems(new SelectItem[]{option111, option112});

        SelectItem option121 = new SelectItem("OIRIS", "OIRIS");
        SelectItem option122 = new SelectItem("RIGEL", "RIGEL");
        SelectItem option123 = new SelectItem("SIRIO", "SIRIO");
        SelectItem option131 = new SelectItem("MERCURIO", "MERCURIO");
        SelectItem option132 = new SelectItem("VENUS", "VENUS");
        SelectItem option133 = new SelectItem("TIERRA", "TIERRA");
        SelectItem option141 = new SelectItem("MENGUANTE", "MENGUANTE");
        SelectItem option142 = new SelectItem("CRECIENTE", "CRECIENTE");
        SelectItem option143 = new SelectItem("NUEVA", "NUEVA");
        group12.setSelectItems(new SelectItem[]{option121, option122, option123});
        group13.setSelectItems(new SelectItem[]{option131, option132, option133});
        group14.setSelectItems(new SelectItem[]{option141, option142, option143});

        SelectItem option221 = new SelectItem("VEGETARIANOS", "VEGETARIANOS");
        SelectItem option222 = new SelectItem("ETEROTROFO", "ETEROTROFO");
        SelectItem option223 = new SelectItem("VIVIPEDOS", "VIVIPEDOS");
        SelectItem option231 = new SelectItem("PIRAMIDES", "PIRAMIDES");
        SelectItem option232 = new SelectItem("CATARATAS", "CATARATAS");
        SelectItem option233 = new SelectItem("OXIGENO", "OXIGENO");
        SelectItem option241 = new SelectItem("Option 2.4.1", "Option 2.4.1");
        SelectItem option242 = new SelectItem("Option 2.4.2", "Option 2.4.2");
        SelectItem option243 = new SelectItem("Option 2.4.3", "Option 2.4.3");
        group22.setSelectItems(new SelectItem[]{option221, option222, option223});
        group23.setSelectItems(new SelectItem[]{option231, option232, option233});
        group24.setSelectItems(new SelectItem[]{option241, option242, option243});

        SelectItem option211 = new SelectItem("JURIDICOS", "JURIDICOS");
        group21.setSelectItems(new SelectItem[]{option211});

        group1.setSelectItems(new SelectItem[]{group11, group12, group13, group14});
        group2.setSelectItems(new SelectItem[]{group21, group22, group23, group24});
        group3.setSelectItems(new SelectItem[]{option31, option32, option33, option34});
        group4.setSelectItems(new SelectItem[]{option41});

        categories.add(group1);
        categories.add(group2);
        categories.add(group3);
        categories.add(group4);
    }


   


    public void retcar() {
        System.out.println("Entro al metodo esperado");
    }

    public String llevaalpricipal() {
        return "abrirExpediente";
    }



   
    public Boolean getEncabezado() {
        return encabezado;
    }

    public void setEncabezado(Boolean encabezado) {
        this.encabezado = encabezado;
    }

    public List<String> getListado() {
        return listado;
    }

    public void setListado(List<String> listado) {
        this.listado = listado;
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public String finalizarOposicion() {
        return "abrirPrincipal";
    }

    

    public List<SelectItem> getCategories() {
        return categories;
    }

    public void setCategories(List<SelectItem> categories) {
        this.categories = categories;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

}
