/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.expediente;

import snp.gob.bo.entidades.bean.modificacion.*;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.entidad.SigSolicitanteApoderado;
import snp.gob.bo.gimodel.entidad.ModTitularLicenciatarioNuevo;
import snp.gob.bo.gimodel.entidad.ModTitularLicenciatarioRegistrado;
import snp.gob.bo.gimodel.servicio.DominioService;

/**
 *
 * @author susana
 */
@ManagedBean(name = "agregarSolicitanteApoderadoSignoBean")
@ViewScoped
public class AgregarSolicitanteApoderadoSignoBean {

    /**
     * Creates a new instance of AgregarPersonaEmpresaBean
     */
    public AgregarSolicitanteApoderadoSignoBean() {
    }
    private String tipoSolicitante = "JUR";
    private Boolean panelSolicitanteRendered = true;
//    private List<persona> lstPersonas;

    private String razonSocial;
    private String numeroDocumento;
    private String tipoDocumento;
    private String lugarExpedicion;
    private String pais;
    private String departamento;
    private String generoPersona = "";
    private String telefono;
    private String domicilio;
    private String correoElectronico = "";
    private String nombre;
    private String primerApellido;
    private String segundoApellido;

    private SigSolicitanteApoderado solicitanteApoderado = new SigSolicitanteApoderado();

    private Boolean mensaje = false;
    private Boolean panelSolicitante = true;
    private String tipo_persona = "";
    private String fax;
    private String celular;
    private String poder;
    private String tituloPagina = "Agregar Persona";

    private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private String valorRecibido = "";
    private Boolean panelNombre = false;
    private Boolean panelDomicilio = false;
    private Boolean panelTransFusLu = false;
    private Boolean panelTitularRegistrado = false;
    private Boolean comboTipoSolicitante = false;

    private ModTitularLicenciatarioNuevo titularLicencitarioNuevo = new ModTitularLicenciatarioNuevo();
    private ModTitularLicenciatarioRegistrado titularLicenciatarioRegistrado = new ModTitularLicenciatarioRegistrado();
    private List<Dominio> listaLugarExpedicion;
    private List<Dominio> listaPais;
    private List<Dominio> listaTipoTitular;
    private List<Dominio> listaGenero;
    private List<Dominio> listaTipoDocumentoNatural;
    private List<Dominio> listaTipoDocumentoJuridico;

    @ManagedProperty("#{dominioService}")
    private DominioService dominioService;

    @PostConstruct
    public void AgregarPersonaEmpresaBeanInit() {

        try {
            listaLugarExpedicion = dominioService.obtenerListadoDominio("lugar_expedicion");
            listaPais = dominioService.obtenerListadoDominio("pais");
            listaTipoTitular = dominioService.obtenerListadoDominio("tipo_titular");
            listaGenero = dominioService.obtenerListadoDominio("genero");
            listaTipoDocumentoNatural = dominioService.obtenerListadoDominio("tipo_documento");
            listaTipoDocumentoJuridico = dominioService.listar_dominio_dominiopadre("tipo_documento", "TODO");
        } catch (Exception ex) {
            Logger.getLogger(AgregarSolicitanteApoderadoSignoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        valorRecibido = params.get("id");

        panelSolicitante = valorRecibido == null;
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        if (sessionMap.get("persona") != null) {
            solicitanteApoderado = (SigSolicitanteApoderado) sessionMap.get("persona");
            tipoSolicitante = solicitanteApoderado.getTipoTitular();
            panelSolicitanteRendered = !tipoSolicitante.equals("NAT");
            inicializaPersona(tipoSolicitante);
            if (solicitanteApoderado.getTipoPersona().equals("APOD")) {
                tituloPagina = "Agregar Representante Legal";
            } else {
                tituloPagina = "Agregar persona";
            }
        }

        if (valorRecibido != null) {
            activaCambios(valorRecibido);
            if (sessionMap.get("nuevoTitular") != null) {
                titularLicencitarioNuevo = (ModTitularLicenciatarioNuevo) sessionMap.get("nuevoTitular");
                tipoSolicitante = titularLicencitarioNuevo.getTipo_titular();
                panelSolicitanteRendered = !tipoSolicitante.equals("NAT");
                inicializaNuevoTitular(tipoSolicitante);
                tituloPagina = "Agregar Nuevo Titular";
            }

            if (sessionMap.get("titularRegistrado") != null) {

                titularLicenciatarioRegistrado = (ModTitularLicenciatarioRegistrado) sessionMap.get("titularRegistrado");
                razonSocial = titularLicenciatarioRegistrado.getNombre_razonsocial();
                domicilio = titularLicenciatarioRegistrado.getDireccion();
                tituloPagina = "Agregar Titular";
            }
            if (valorRecibido.equals("APOD")) {
                tituloPagina = "Agregar Representante Legal";
            }
        }
    }

    public void inicializaNuevoTitular(String tipoSolicitante) {
        if (tipoSolicitante.equals("JUR")) {
            razonSocial = titularLicencitarioNuevo.getNombre_razonsocial();
            tipoDocumento = titularLicencitarioNuevo.getTipo_documento();
            numeroDocumento = titularLicencitarioNuevo.getNumero_documento();
            pais = titularLicencitarioNuevo.getPais();
            departamento = titularLicencitarioNuevo.getSolicitud_departamento();
        } else {
            nombre = titularLicencitarioNuevo.getNombre_razonsocial();
            primerApellido = titularLicencitarioNuevo.getPrimer_apellido();
            segundoApellido = titularLicencitarioNuevo.getSegundo_apellido();
            numeroDocumento = titularLicencitarioNuevo.getNumero_documento();
            tipoDocumento = titularLicencitarioNuevo.getTipo_documento();
            lugarExpedicion = titularLicencitarioNuevo.getLugar_expedicion();
            pais = titularLicencitarioNuevo.getPais();
            generoPersona = titularLicencitarioNuevo.getGenero();
            departamento = titularLicencitarioNuevo.getSolicitud_departamento();
        }
        telefono = titularLicencitarioNuevo.getTelefono();
        domicilio = titularLicencitarioNuevo.getDireccion();
        correoElectronico = titularLicencitarioNuevo.getEmail();
        fax = titularLicencitarioNuevo.getFax();
        celular = titularLicencitarioNuevo.getCelular();
        if (valorRecibido.equals("LU")) {
            tipo_persona = "LICE";
        } else {
            tipo_persona = "NTIT";
        }
    }

    public void inicializaPersona(String tipoSolicitante) {

        if (tipoSolicitante.equals("JUR")) {
            razonSocial = solicitanteApoderado.getNombreRazonSocial();
            tipoDocumento = solicitanteApoderado.getTipoDocumento();
            numeroDocumento = solicitanteApoderado.getNumeroDocumento();
            pais = solicitanteApoderado.getPais();
            departamento = solicitanteApoderado.getSolicitudDepartamento();
        } else {
            nombre = solicitanteApoderado.getNombreRazonSocial();
            primerApellido = solicitanteApoderado.getPrimerApellido();
            segundoApellido = solicitanteApoderado.getSegundoApellido();
            numeroDocumento = solicitanteApoderado.getNumeroDocumento();
            tipoDocumento = solicitanteApoderado.getTipoDocumento();
            lugarExpedicion = solicitanteApoderado.getLugarExpedicion();
            pais = solicitanteApoderado.getPais();
            generoPersona = solicitanteApoderado.getGenero();
            departamento = solicitanteApoderado.getSolicitudDepartamento();
            poder = solicitanteApoderado.getPoder();
        }
        telefono = solicitanteApoderado.getTelefono();
        domicilio = solicitanteApoderado.getDireccion();
        correoElectronico = solicitanteApoderado.getEmail();
        fax = solicitanteApoderado.getFax();
        celular = solicitanteApoderado.getCelular();
    }

    public void activaCamposNaturalJuridico() {

        panelSolicitanteRendered = !tipoSolicitante.equals("NAT");
    }

    public String valorTipoPersona(Boolean panelSolicitante, String valorRecibido) {
        String tipoPersona = "SOLI";
        if (panelSolicitante) {
            tipoPersona = "SOLI";
        }
        if (valorRecibido != null) {
            tipoPersona = "NTIT";
        }
        return tipoPersona;
    }

    public void addSolicitanteJuridico() {
        tipo_persona = valorTipoPersona(panelSolicitante, valorRecibido);
        if (validateEmail(correoElectronico)) {
//            solicitanteApoderado.setIdSignoMarca(1L);
            solicitanteApoderado.setIdLogTrans(1L);
            solicitanteApoderado.setTipoTitular(tipoSolicitante);
            if (solicitanteApoderado.getTipoTitular().equalsIgnoreCase("JUR")) {
                solicitanteApoderado.setPrimerApellido("");
                solicitanteApoderado.setSegundoApellido("");
            }
            solicitanteApoderado.setTipoPersona(tipo_persona);
            solicitanteApoderado.setNombreRazonSocial(razonSocial);
            solicitanteApoderado.setTipoDocumento(tipoDocumento);

            if (solicitanteApoderado.getTipoDocumento().equalsIgnoreCase("CI")) {
                solicitanteApoderado.setLugarExpedicion(lugarExpedicion);
            } else {
                solicitanteApoderado.setLugarExpedicion("");
            }

            solicitanteApoderado.setNumeroDocumento(numeroDocumento);
            solicitanteApoderado.setPais(pais);
            if (solicitanteApoderado.getPais().equalsIgnoreCase("BO")) {
                solicitanteApoderado.setSolicitudDepartamento(departamento);
            } else {
                solicitanteApoderado.setSolicitudDepartamento("");
            }

            solicitanteApoderado.setDireccion(domicilio);
            //if (!correoElectronico.equals("")) {
            solicitanteApoderado.setEmail(correoElectronico);
            //}
            solicitanteApoderado.setTelefono(telefono);
            solicitanteApoderado.setFax(fax);
            solicitanteApoderado.setCelular(celular);
            solicitanteApoderado.setEstado("AC");
            RequestContext.getCurrentInstance().closeDialog(solicitanteApoderado);
        } else {
            mensaje = true;
        }

    }

    public void addSolicitanteNatural() {
        tipo_persona = valorTipoPersona(panelSolicitante, valorRecibido);

//        if (validateEmail(correoElectronico)) {
//            solicitanteApoderado.setIdSignoMarca(1L);
        solicitanteApoderado.setIdLogTrans(1L);
        solicitanteApoderado.setTipoTitular(tipoSolicitante);
        if (solicitanteApoderado.getTipoTitular().equalsIgnoreCase("JUR")) {
            solicitanteApoderado.setPrimerApellido("");
            solicitanteApoderado.setSegundoApellido("");
        } else {
            solicitanteApoderado.setPrimerApellido(primerApellido);
            solicitanteApoderado.setSegundoApellido(segundoApellido);
        }
        solicitanteApoderado.setTipoPersona(tipo_persona);
        solicitanteApoderado.setNombreRazonSocial(nombre);

        solicitanteApoderado.setNumeroDocumento(numeroDocumento);
        solicitanteApoderado.setTipoDocumento(tipoDocumento);
        if (solicitanteApoderado.getTipoDocumento().equalsIgnoreCase("CI")) {
            solicitanteApoderado.setLugarExpedicion(lugarExpedicion);
        } else {
            solicitanteApoderado.setLugarExpedicion("");
        }

        solicitanteApoderado.setPais(pais);
        solicitanteApoderado.setGenero(generoPersona);
        if (solicitanteApoderado.getPais().equalsIgnoreCase("BO")) {
            solicitanteApoderado.setSolicitudDepartamento(departamento);
        } else {
            solicitanteApoderado.setSolicitudDepartamento("");
        }

        solicitanteApoderado.setDireccion(domicilio);
        //if (!correoElectronico.equals("")) {
        solicitanteApoderado.setEmail(correoElectronico);
        //}
        solicitanteApoderado.setTelefono(telefono);
        solicitanteApoderado.setFax(fax);
        solicitanteApoderado.setCelular(celular);
        solicitanteApoderado.setEstado("AC");
//        } else {
//            mensaje = true;
//        }
        RequestContext.getCurrentInstance().closeDialog(solicitanteApoderado);
    }

    public void addApoderado() {
//        solicitanteApoderado.setIdSignoMarca(1L);
        solicitanteApoderado.setIdLogTrans(1L);
        solicitanteApoderado.setTipoTitular("NAT");
        solicitanteApoderado.setTipoPersona("APOD");
        solicitanteApoderado.setNombreRazonSocial(nombre);
        solicitanteApoderado.setPrimerApellido(primerApellido);
        solicitanteApoderado.setSegundoApellido(segundoApellido);
        solicitanteApoderado.setNumeroDocumento(numeroDocumento);
        solicitanteApoderado.setTipoDocumento(tipoDocumento);
        if (solicitanteApoderado.getTipoDocumento().equalsIgnoreCase("CI")) {
            solicitanteApoderado.setLugarExpedicion(lugarExpedicion);
        } else {
            solicitanteApoderado.setLugarExpedicion("");
        }
        solicitanteApoderado.setPais("");
        solicitanteApoderado.setGenero(generoPersona);
        if (solicitanteApoderado.getPais().equalsIgnoreCase("BO")) {
            solicitanteApoderado.setSolicitudDepartamento(departamento);
        } else {
            solicitanteApoderado.setSolicitudDepartamento("");
        }
        solicitanteApoderado.setDireccion(domicilio);
        if (!correoElectronico.equals("")) {
            solicitanteApoderado.setEmail(correoElectronico);
        }

        solicitanteApoderado.setTelefono(telefono);
        solicitanteApoderado.setFax(fax);
        solicitanteApoderado.setCelular(celular);
        solicitanteApoderado.setPoder(poder);
        solicitanteApoderado.setEstado("AC");
        RequestContext.getCurrentInstance().closeDialog(solicitanteApoderado);
    }

    ///////////////////nuevo titular//////////////////
    public void activaCambios(String valorRecibido) {
        tituloPagina = "Agregar Nuevo Titular o Licenciatario";
        switch (valorRecibido) {
            case "CN":
                panelNombre = true;
                panelDomicilio = false;
                panelTransFusLu = false;
                comboTipoSolicitante = true;
                tipo_persona = "NTIT";
                panelTitularRegistrado = false;
                break;
            case "CD":
                panelNombre = false;
                panelDomicilio = true;
                panelTransFusLu = false;
                comboTipoSolicitante = false;
                tipo_persona = "NTIT";
                panelTitularRegistrado = false;
                break;
            case "ST":
                panelNombre = false;
                panelDomicilio = false;
                panelTransFusLu = true;
                comboTipoSolicitante = true;
                tipo_persona = "NTIT";
                panelTitularRegistrado = false;
                break;
            case "SF":
                panelNombre = false;
                panelDomicilio = false;
                panelTransFusLu = true;
                comboTipoSolicitante = true;
                tipo_persona = "NTIT";
                panelTitularRegistrado = false;
                break;
            case "LU":
                panelNombre = false;
                panelDomicilio = false;
                panelTransFusLu = true;
                comboTipoSolicitante = true;
                tipo_persona = "LICE";
                panelTitularRegistrado = false;
                break;
            case "TREG":
                panelNombre = false;
                panelDomicilio = false;
                panelTransFusLu = false;
                comboTipoSolicitante = false;
                tipo_persona = "TREG";
                panelTitularRegistrado = true;
                tituloPagina = "Agregar Titular";
                break;
            default:
                panelNombre = false;
                panelDomicilio = false;
                panelTransFusLu = false;
                comboTipoSolicitante = false;
                panelTitularRegistrado = false;
                tipo_persona = "NTIT";
                break;
        }
    }

    public void addNuevoTitularJuridico() {

        if (validateEmail(correoElectronico)) {
            titularLicencitarioNuevo.setIdmodificacion(1L);
            titularLicencitarioNuevo.setIdlogtrans(1L);
            titularLicencitarioNuevo.setTipo_titular(tipoSolicitante);
            titularLicencitarioNuevo.setTipo_persona(tipo_persona);
            titularLicencitarioNuevo.setNombre_razonsocial(razonSocial);
            titularLicencitarioNuevo.setTipo_documento(tipoDocumento);
            titularLicencitarioNuevo.setNumero_documento(numeroDocumento);
            titularLicencitarioNuevo.setPais(pais);
            titularLicencitarioNuevo.setSolicitud_departamento(departamento);
            titularLicencitarioNuevo.setDireccion(domicilio);
            if (!correoElectronico.equals("")) {
                titularLicencitarioNuevo.setEmail(correoElectronico);
            }
            titularLicencitarioNuevo.setTelefono(telefono);
            titularLicencitarioNuevo.setFax(fax);
            titularLicencitarioNuevo.setCelular(celular);
            titularLicencitarioNuevo.setEstado("AC");
            RequestContext.getCurrentInstance().closeDialog(titularLicencitarioNuevo);
        } else {
            mensaje = true;
        }
    }

    public void addNuevoTitularNatural() {
        if (validateEmail(correoElectronico)) {
            titularLicencitarioNuevo.setIdmodificacion(1L);
            titularLicencitarioNuevo.setIdlogtrans(1L);
            titularLicencitarioNuevo.setTipo_titular(tipoSolicitante);
            titularLicencitarioNuevo.setTipo_persona(tipo_persona);
            titularLicencitarioNuevo.setNombre_razonsocial(nombre);
            titularLicencitarioNuevo.setPrimer_apellido(primerApellido);
            titularLicencitarioNuevo.setSegundo_apellido(segundoApellido);
            titularLicencitarioNuevo.setNumero_documento(numeroDocumento);
            titularLicencitarioNuevo.setTipo_documento(tipoDocumento);
            titularLicencitarioNuevo.setLugar_expedicion(lugarExpedicion);
            titularLicencitarioNuevo.setPais(pais);
            titularLicencitarioNuevo.setGenero(generoPersona);
            titularLicencitarioNuevo.setSolicitud_departamento(departamento);
            titularLicencitarioNuevo.setDireccion(domicilio);
            if (!correoElectronico.equals("")) {
                titularLicencitarioNuevo.setEmail(correoElectronico);
            }
            titularLicencitarioNuevo.setTelefono(telefono);
            titularLicencitarioNuevo.setFax(fax);
            titularLicencitarioNuevo.setCelular(celular);
            titularLicencitarioNuevo.setEstado("AC");
        } else {
            mensaje = true;
        }
        RequestContext.getCurrentInstance().closeDialog(titularLicencitarioNuevo);
    }

    public void addNuevoTitularNombre() {
        titularLicencitarioNuevo.setTipo_titular(tipoSolicitante);
        titularLicencitarioNuevo.setTipo_persona(tipo_persona);
        if (tipoSolicitante.equals("JUR")) {
            titularLicencitarioNuevo.setNombre_razonsocial(razonSocial);
        } else {
            titularLicencitarioNuevo.setNombre_razonsocial(nombre);
            titularLicencitarioNuevo.setPrimer_apellido(primerApellido);
            titularLicencitarioNuevo.setSegundo_apellido(segundoApellido);
        }
        RequestContext.getCurrentInstance().closeDialog(titularLicencitarioNuevo);
    }

    /////////////////////////////////////////////////
    public void addTitularRegistrado() {

        titularLicenciatarioRegistrado.setNombre_razonsocial(razonSocial);
        titularLicenciatarioRegistrado.setDireccion(domicilio);
        titularLicenciatarioRegistrado.setTipo_persona_registrado("TREG");
        RequestContext.getCurrentInstance().closeDialog(titularLicenciatarioRegistrado);
    }

    public void cerrarDialogo() {

        //pass back to root
        RequestContext.getCurrentInstance().closeDialog("Exit");
    }

    public void metodoCorreo() {
        if (validaEmail(correoElectronico)) {//validador correcto 
            mensaje = false;
        } else {
            mensaje = !correoElectronico.equals("");
        }
    }

    public boolean validaEmail(String email) {
        // Compiles the given regular expression into a pattern.
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        // Match the given input against this pattern
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validateEmail(String email) {
        Boolean valor = true;
        if (!email.equals("")) {
            // Compiles the given regular expression into a pattern.
            Pattern pattern = Pattern.compile(PATTERN_EMAIL);
            // Match the given input against this pattern
            Matcher matcher = pattern.matcher(email);
            valor = matcher.matches();
        }
        return valor;
    }

    public String getTipoSolicitante() {
        return tipoSolicitante;
    }

    public void setTipoSolicitante(String tipoSolicitante) {
        this.tipoSolicitante = tipoSolicitante;
    }

    public Boolean getPanelSolicitanteRendered() {
        return panelSolicitanteRendered;
    }

    public void setPanelSolicitanteRendered(Boolean panelSolicitanteRendered) {
        this.panelSolicitanteRendered = panelSolicitanteRendered;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getLugarExpedicion() {
        return lugarExpedicion;
    }

    public void setLugarExpedicion(String lugarExpedicion) {
        this.lugarExpedicion = lugarExpedicion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getSolicitud_departamento() {
        return departamento;
    }

    public void setSolicitud_departamento(String departamento) {
        this.departamento = departamento;
    }

    public String getGeneroPersona() {
        return generoPersona;
    }

    public void setGeneroPersona(String generoPersona) {
        this.generoPersona = generoPersona;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

//    public List<persona> getLstPersonas() {
//        return lstPersonas;
//    }
//
//    public void setLstPersonas(List<persona> lstPersonas) {
//        this.lstPersonas = lstPersonas;
//    }
    public Boolean getMensaje() {
        return mensaje;
    }

    public void setMensaje(Boolean mensaje) {
        this.mensaje = mensaje;
    }

    public Boolean getPanelSolicitante() {
        return panelSolicitante;
    }

    public void setPanelSolicitante(Boolean panelSolicitante) {
        this.panelSolicitante = panelSolicitante;
    }

    public Boolean getPanelNombre() {
        return panelNombre;
    }

    public void setPanelNombre(Boolean panelNombre) {
        this.panelNombre = panelNombre;
    }

    public Boolean getPanelDomicilio() {
        return panelDomicilio;
    }

    public void setPanelDomicilio(Boolean panelDomicilio) {
        this.panelDomicilio = panelDomicilio;
    }

    public Boolean getPanelTransFusLu() {
        return panelTransFusLu;
    }

    public void setPanelTransFusLu(Boolean panelTransFusLu) {
        this.panelTransFusLu = panelTransFusLu;
    }

    public Boolean getComboTipoSolicitante() {
        return comboTipoSolicitante;
    }

    public void setComboTipoSolicitante(Boolean comboTipoSolicitante) {
        this.comboTipoSolicitante = comboTipoSolicitante;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getPoder() {
        return poder;
    }

    public void setPoder(String poder) {
        this.poder = poder;
    }

    public String getTituloPagina() {
        return tituloPagina;
    }

    public void setTituloPagina(String tituloPagina) {
        this.tituloPagina = tituloPagina;
    }

    public Boolean getPanelTitularRegistrado() {
        return panelTitularRegistrado;
    }

    public void setPanelTitularRegistrado(Boolean panelTitularRegistrado) {
        this.panelTitularRegistrado = panelTitularRegistrado;
    }

    public DominioService getDominioService() {
        return dominioService;
    }

    public void setDominioService(DominioService dominioService) {
        this.dominioService = dominioService;
    }

    public List<Dominio> getListaLugarExpedicion() {
        return listaLugarExpedicion;
    }

    public void setListaLugarExpedicion(List<Dominio> listaLugarExpedicion) {
        this.listaLugarExpedicion = listaLugarExpedicion;
    }

    public List<Dominio> getListaPais() {
        return listaPais;
    }

    public void setListaPais(List<Dominio> listaPais) {
        this.listaPais = listaPais;
    }

    public List<Dominio> getListaTipoTitular() {
        return listaTipoTitular;
    }

    public void setListaTipoTitular(List<Dominio> listaTipoTitular) {
        this.listaTipoTitular = listaTipoTitular;
    }

    public List<Dominio> getListaGenero() {
        return listaGenero;
    }

    public void setListaGenero(List<Dominio> listaGenero) {
        this.listaGenero = listaGenero;
    }

    public List<Dominio> getListaTipoDocumentoNatural() {
        return listaTipoDocumentoNatural;
    }

    public void setListaTipoDocumentoNatural(List<Dominio> listaTipoDocumentoNatural) {
        this.listaTipoDocumentoNatural = listaTipoDocumentoNatural;
    }

    public List<Dominio> getListaTipoDocumentoJuridico() {
        return listaTipoDocumentoJuridico;
    }

    public void setListaTipoDocumentoJuridico(List<Dominio> listaTipoDocumentoJuridico) {
        this.listaTipoDocumentoJuridico = listaTipoDocumentoJuridico;
    }

}
