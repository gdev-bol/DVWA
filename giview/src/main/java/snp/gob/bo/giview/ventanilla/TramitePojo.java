/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.giview.ventanilla;

/**
 *
 * @author ruben
 */
public class TramitePojo {
    private Integer numero;
    private String sigla;
    private Integer numerotramite;
    private Integer gestion;
    private String numeroregistro;
    private String marca;
    private Integer clase_niza;
    
    public TramitePojo(){
        
    }

    public TramitePojo(Integer numero, String sigla, Integer numerotramite, Integer gestion, String numeroregistro, String marca, Integer clase_niza) {
        this.numero = numero;
        this.sigla = sigla;
        this.numerotramite = numerotramite;
        this.gestion = gestion;
        this.numeroregistro = numeroregistro;
        this.marca = marca;
        this.clase_niza = clase_niza;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Integer getNumerotramite() {
        return numerotramite;
    }

    public void setNumerotramite(Integer numerotramite) {
        this.numerotramite = numerotramite;
    }

    public Integer getGestion() {
        return gestion;
    }

    public void setGestion(Integer gestion) {
        this.gestion = gestion;
    }

    public String getNumeroregistro() {
        return numeroregistro;
    }

    public void setNumeroregistro(String numeroregistro) {
        this.numeroregistro = numeroregistro;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getClase_niza() {
        return clase_niza;
    }

    public void setClase_niza(Integer clase_niza) {
        this.clase_niza = clase_niza;
    }
    
}
