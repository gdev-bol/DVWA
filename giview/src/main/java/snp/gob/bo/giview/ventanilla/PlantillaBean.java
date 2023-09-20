/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.giview.ventanilla;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

/**
 *
 * @author eddy
 */
@ManagedBean(name = "plantillaBean")
@ViewScoped
public class PlantillaBean {

    private String[] selectedCars;

    private List<SelectItem> cars;

    @PostConstruct
    public void initPlantillaBean() {
        cars = new ArrayList<SelectItem>();
        SelectItemGroup germanCars = new SelectItemGroup("Requisitos Obligatorios");
        germanCars.setSelectItems(new SelectItem[]{
            new SelectItem("Solicitud de signos distintivos mediante nota o memorial", "Solicitud de signos distintivos mediante nota o memorial"),
            new SelectItem("Formularios de registro", "Formularios de registro"),
            new SelectItem("Pago por concepto de solicitud de registro", "Pago por concepto de solicitud de registro")
        });

        SelectItemGroup americanCars = new SelectItemGroup("Requisitos Opcionales");
        americanCars.setSelectItems(new SelectItem[]{
            new SelectItem("En caso de marcas mixtas o con diseño adjuente un juego de logo 4x4 en tres ejemplares", "En caso de marcas mixtas o con diseño adjuente un juego de logo 4x4 en tres ejemplares"),
            new SelectItem("Poderes necesarios en caso de representación(testimonios)", "Poderes necesarios en caso de representación(testimonios)"),
            new SelectItem("Empresa Unipersonal o Registro de Comercio", "Empresa Unipersonal o Registro de Comercio")
        });

        cars.add(germanCars);
        cars.add(americanCars);
    }

    public String[] getSelectedCars() {
        return selectedCars;
    }

    public void setSelectedCars(String[] selectedCars) {
        this.selectedCars = selectedCars;
    }

    public List<SelectItem> getCars() {
        return cars;
    }

    public void setCars(List<SelectItem> cars) {
        this.cars = cars;
    }
    
    
    //Metodos de botones
    public String retornarPagina(){
     return "ingresoSolicitud";
    
    }
    

}
