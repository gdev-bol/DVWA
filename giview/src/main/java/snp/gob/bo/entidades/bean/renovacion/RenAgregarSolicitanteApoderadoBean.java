 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.bean.renovacion;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.entidad.RenSolicitanteApoderado;
import snp.gob.bo.gimodel.entidad.RenTitularRegistrado;
import snp.gob.bo.gimodel.enums.EnumEstado;
import snp.gob.bo.gimodel.enums.EnumNombreDominio;
import snp.gob.bo.gimodel.enums.EnumTipoPersona;
import snp.gob.bo.gimodel.enums.EnumTipoTitular;
import snp.gob.bo.gimodel.servicio.DominioService;

/**
 *
 * @author Chano_Rojas
 */
@ManagedBean(name = "renAgregarSolicitanteApoderadoBean")
@ViewScoped
public class RenAgregarSolicitanteApoderadoBean {

    /**
     * Creates a new instance of AgregarPersonaEmpresaBean
     */
    public RenAgregarSolicitanteApoderadoBean() {
    }

    //<editor-fold defaultstate="collapsed" desc="INYECCION DE VARIABLES">
    @ManagedProperty("#{dominioService}")
    private DominioService dominioService;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="-------------------------VARIABLES DE ENTORNO----------------------">
    private String tipoSolicitante = "JUR";
    private Boolean panelSolicitanteRendered = true;
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
    private RenSolicitanteApoderado renSolicitante = new RenSolicitanteApoderado();
    private RenSolicitanteApoderado renApoderado = new RenSolicitanteApoderado();
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

//    private ModTitularLicenciatarioNuevo titularLicencitarioNuevo = new ModTitularLicenciatarioNuevo();
//    private ModTitularLicenciatarioRegistrado titularLicenciatarioRegistrado = new ModTitularLicenciatarioRegistrado();
    private RenTitularRegistrado titularLicenciatarioRegistrado = new RenTitularRegistrado();
    private List<Dominio> listaLugarExpedicion;
    private List<Dominio> listaPais;
    private List<Dominio> listaTipoTitular;
    private List<Dominio> listaGenero;
    private List<Dominio> listaTipoDocumentoNatural;
    private List<Dominio> listaTipoDocumentoJuridico;
//</editor-fold>

    @PostConstruct
    public void InitRenAgregarPersonaSolicitanteBean() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        valorRecibido = params.get("id");
        panelSolicitante = valorRecibido == null;
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        if (sessionMap.get("persona") != null) {
            renSolicitante = (RenSolicitanteApoderado) sessionMap.get("persona");
            
            tipoSolicitante = renSolicitante.getTipo_titular();
            panelSolicitanteRendered = !tipoSolicitante.equals(EnumTipoTitular.NATURAL.getCodigo());
            inicializaPersona(tipoSolicitante);
            tituloPagina = "Agregar Representante Legal";
        } else {
//            renSolicitante = new RenSolicitanteApoderado();
        }
        if (valorRecibido != null) {
            //   System.out.println("entro por if");
            //    System.out.println("SESSION NEW TIT" + sessionMap.get("nuevoTitular"));
            activaCambios(valorRecibido);
//            if (sessionMap.get("nuevoTitular") != null) {
//                titularLicencitarioNuevo = (ModTitularLicenciatarioNuevo) sessionMap.get("nuevoTitular");
//                tipoSolicitante = titularLicencitarioNuevo.getTipo_titular();
//                panelSolicitanteRendered = !tipoSolicitante.equals(EnumTipoTitular.NATURAL.getCodigo());
//                inicializaNuevoTitular(tipoSolicitante);
//                tituloPagina = "Agregar Nuevo Titular";
//            }
            if (sessionMap.get("titularRegistrado") != null) {
                titularLicenciatarioRegistrado = (RenTitularRegistrado) sessionMap.get("titularRegistrado");
                razonSocial = titularLicenciatarioRegistrado.getNombre_razonsocial();
                domicilio = titularLicenciatarioRegistrado.getDireccion();
                tituloPagina = "Agregar Titular";
            }
            if (valorRecibido.equals(EnumTipoPersona.APODERADO.getCodigo())) {
                tituloPagina = "Agregar Representante Legal";
            }
        } else {
//            System.out.println("entro por ESLE...........................................");
//            renSolicitante = new RenSolicitanteApoderado();
        }
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

    //<editor-fold defaultstate="collapsed" desc="---------------------------------METODOS DEL BEAN--------------------">
    public void inicializaNuevoTitular(String tipoSolicitante) {
//        if (tipoSolicitante.equals(EnumTipoTitular.JURIDICO.getCodigo())) {
//            razonSocial = titularLicencitarioNuevo.getNombre_razonsocial();
//            tipoDocumento = titularLicencitarioNuevo.getTipo_documento();
//            numeroDocumento = titularLicencitarioNuevo.getNumero_documento();
//            pais = titularLicencitarioNuevo.getPais();
//            departamento = titularLicencitarioNuevo.getSolicitud_departamento();
//        } else {
//            nombre = titularLicencitarioNuevo.getNombre_razonsocial();
//            primerApellido = titularLicencitarioNuevo.getPrimer_apellido();
//            segundoApellido = titularLicencitarioNuevo.getSegundo_apellido();
//            numeroDocumento = titularLicencitarioNuevo.getNumero_documento();
//            tipoDocumento = titularLicencitarioNuevo.getTipo_documento();
//            lugarExpedicion = titularLicencitarioNuevo.getLugar_expedicion();
//            pais = titularLicencitarioNuevo.getPais();
//            generoPersona = titularLicencitarioNuevo.getGenero();
//            departamento = titularLicencitarioNuevo.getSolicitud_departamento();
//        }
//        telefono = titularLicencitarioNuevo.getTelefono();
//        domicilio = titularLicencitarioNuevo.getDireccion();
//        correoElectronico = titularLicencitarioNuevo.getEmail();
//        fax = titularLicencitarioNuevo.getFax();
//        celular = titularLicencitarioNuevo.getCelular();
//        if (valorRecibido.equals("LU")) {
//            tipo_persona = "LIC";
//        } else {
//            tipo_persona = "NTI";
//        }
    }

    public void inicializaPersona(String tipoSolicitante) {
        if (tipoSolicitante.equals(EnumTipoTitular.JURIDICO.getCodigo())) {
            razonSocial = renSolicitante.getNombre_razonsocial();
            tipoDocumento = renSolicitante.getTipo_documento();
            numeroDocumento = renSolicitante.getNumero_documento();
            pais = renSolicitante.getPais();
            departamento = renSolicitante.getSolicitud_departamento();
        } else {
            nombre = renSolicitante.getNombre_razonsocial();
            primerApellido = renSolicitante.getPrimer_apellido();
            segundoApellido = renSolicitante.getSegundo_apellido();
            numeroDocumento = renSolicitante.getNumero_documento();
            tipoDocumento = renSolicitante.getTipo_documento();
            lugarExpedicion = renSolicitante.getLugar_expedicion();
            pais = renSolicitante.getPais();
            generoPersona = renSolicitante.getGenero();
            departamento = renSolicitante.getSolicitud_departamento();
            poder = renSolicitante.getPoder();
        }
        telefono = renSolicitante.getTelefono();
        domicilio = renSolicitante.getDireccion();
        correoElectronico = renSolicitante.getEmail();
        fax = renSolicitante.getFax();
        celular = renSolicitante.getCelular();
    }

    public void activaCamposNaturalJuridico() {

        panelSolicitanteRendered = !tipoSolicitante.equals(EnumTipoTitular.NATURAL.getCodigo());
    }

    public String valorTipoPersona(Boolean panelSolicitante, String valorRecibido) {
        String tipoPersona = EnumTipoPersona.SOLICITANTE.getCodigo();
        if (panelSolicitante) {
            tipoPersona = EnumTipoPersona.SOLICITANTE.getCodigo();
        }
        if (valorRecibido != null) {
            tipoPersona = "NTIT";
        }
        return tipoPersona;
    }

    public void accion_guardaSolicitanteJuridico() {
        tipo_persona = valorTipoPersona(panelSolicitante, valorRecibido);
        if (validateEmail(correoElectronico)) {
//            renSolicitante.setIdmodificacion(1L);
//            renSolicitante.setIdLogTrans(1L);
            renSolicitante.setTipo_titular(tipoSolicitante);
            renSolicitante.setTipo_persona(tipo_persona);
            renSolicitante.setNombre_razonsocial(razonSocial);
            renSolicitante.setTipo_documento(tipoDocumento);
            renSolicitante.setNumero_documento(numeroDocumento);
            renSolicitante.setPais(pais);
            renSolicitante.setSolicitud_departamento(departamento);
            renSolicitante.setDireccion(domicilio);
            if (!correoElectronico.equals("")) {
                renSolicitante.setEmail(correoElectronico);
            }
            renSolicitante.setTelefono(telefono);
            renSolicitante.setFax(fax);
            renSolicitante.setCelular(celular);
            renSolicitante.setEstado(EnumEstado.ACTIVO.getCodigo());
            System.out.println("idddddddddddddddddddddddddddddddddddddevolver" + renSolicitante.getIdsolicitanteapoderado());
            RequestContext.getCurrentInstance().closeDialog(renSolicitante);
        } else {
            mensaje = true;
            System.out.println("el correo no es valido");
        }

    }

    public void accion_guardaSolicitanteNatural() {
        tipo_persona = valorTipoPersona(panelSolicitante, valorRecibido);
        if (validateEmail(correoElectronico)) {
//            renSolicitante.setIdmodificacion(1L);
//            renSolicitante.setIdLogTrans(1L);
            renSolicitante.setTipo_titular(tipoSolicitante);
            renSolicitante.setTipo_persona(tipo_persona);
            renSolicitante.setNombre_razonsocial(nombre);
            renSolicitante.setPrimer_apellido(primerApellido);
            renSolicitante.setSegundo_apellido(segundoApellido);
            renSolicitante.setNumero_documento(numeroDocumento);
            renSolicitante.setTipo_documento(tipoDocumento);
            renSolicitante.setLugar_expedicion(lugarExpedicion);
            renSolicitante.setPais(pais);
            renSolicitante.setSolicitud_departamento(departamento);
            renSolicitante.setGenero(generoPersona);
            renSolicitante.setDireccion(domicilio);
            if (!correoElectronico.equals("")) {
                renSolicitante.setEmail(correoElectronico);
            }
            renSolicitante.setTelefono(telefono);
            renSolicitante.setFax(fax);
            renSolicitante.setCelular(celular);
            renSolicitante.setEstado(EnumEstado.ACTIVO.getCodigo());
        } else {
            mensaje = true;
            System.out.println("el correo no es valido");
        }

        System.out.println("idddddddddddddddddddddddddddddddddddddevolver" + renSolicitante.getIdsolicitanteapoderado());
        RequestContext.getCurrentInstance().closeDialog(renSolicitante);
    }

    public void accion_guardaApoderado() {
//        renSolicitante.setIdmodificacion(1L);
//        renSolicitante.setIdLogTrans(1L);
        renSolicitante.setTipo_titular(EnumTipoTitular.NATURAL.getCodigo());
        renSolicitante.setTipo_persona(EnumTipoPersona.APODERADO.getCodigo());
        renSolicitante.setNombre_razonsocial(nombre);
        renSolicitante.setPrimer_apellido(primerApellido);
        renSolicitante.setSegundo_apellido(segundoApellido);
        renSolicitante.setNumero_documento(numeroDocumento);
        renSolicitante.setTipo_documento(tipoDocumento);
        renSolicitante.setLugar_expedicion(lugarExpedicion);
        renSolicitante.setPais(pais);
        renSolicitante.setGenero(generoPersona);
        renSolicitante.setSolicitud_departamento(departamento);
        renSolicitante.setDireccion(domicilio);
        if (!correoElectronico.equals("")) {
            renSolicitante.setEmail(correoElectronico);
        }
        renSolicitante.setTelefono(telefono);
        renSolicitante.setFax(fax);
        renSolicitante.setCelular(celular);
        renSolicitante.setPoder(poder);
        renSolicitante.setEstado(EnumEstado.ACTIVO.getCodigo());
        RequestContext.getCurrentInstance().closeDialog(renSolicitante);
    }

    public void addNuevoTitularJuridico() {

        //        if (validateEmail(correoElectronico)) {
////            titularLicencitarioNuevo.setIdmodificacion(1L);
////            titularLicencitarioNuevo.setIdlogtrans(1L);
//            titularLicencitarioNuevo.setTipo_titular(tipoSolicitante);
//            titularLicencitarioNuevo.setTipo_persona(tipo_persona);
//            titularLicencitarioNuevo.setNombre_razonsocial(razonSocial);
//            titularLicencitarioNuevo.setTipo_documento(tipoDocumento);
//            titularLicencitarioNuevo.setNumero_documento(numeroDocumento);
//            titularLicencitarioNuevo.setPais(pais);
//            titularLicencitarioNuevo.setSolicitud_departamento(departamento);
//            titularLicencitarioNuevo.setDireccion(domicilio);
//            if (!correoElectronico.equals("")) {
//                titularLicencitarioNuevo.setEmail(correoElectronico);
//            }
//            titularLicencitarioNuevo.setTelefono(telefono);
//            titularLicencitarioNuevo.setFax(fax);
//            titularLicencitarioNuevo.setCelular(celular);
//            titularLicencitarioNuevo.setEstado(EnumEstado.ACTIVO.getCodigo());
//            RequestContext.getCurrentInstance().closeDialog(titularLicencitarioNuevo);
//        } else {
//            mensaje = true;
//            System.out.println("el correo no es valido");
//        }
    }

    public void addNuevoTitularNatural() {
//        if (validateEmail(correoElectronico)) {
//            titularLicencitarioNuevo.setIdmodificacion(1L);
//            titularLicencitarioNuevo.setIdlogtrans(1L);
//            titularLicencitarioNuevo.setTipo_titular(tipoSolicitante);
//            titularLicencitarioNuevo.setTipo_persona(tipo_persona);
//            titularLicencitarioNuevo.setNombre_razonsocial(nombre);
//            titularLicencitarioNuevo.setPrimer_apellido(primerApellido);
//            titularLicencitarioNuevo.setSegundo_apellido(segundoApellido);
//            titularLicencitarioNuevo.setNumero_documento(numeroDocumento);
//            titularLicencitarioNuevo.setTipo_documento(tipoDocumento);
//            titularLicencitarioNuevo.setLugar_expedicion(lugarExpedicion);
//            titularLicencitarioNuevo.setPais(pais);
//            titularLicencitarioNuevo.setGenero(generoPersona);
//            titularLicencitarioNuevo.setSolicitud_departamento(departamento);
//            titularLicencitarioNuevo.setDireccion(domicilio);
//            if (!correoElectronico.equals("")) {
//                titularLicencitarioNuevo.setEmail(correoElectronico);
//            }
//            titularLicencitarioNuevo.setTelefono(telefono);
//            titularLicencitarioNuevo.setFax(fax);
//            titularLicencitarioNuevo.setCelular(celular);
//            titularLicencitarioNuevo.setEstado(EnumEstado.ACTIVO.getCodigo());
//        } else {
//            mensaje = true;
//            System.out.println("el correo no es valido");
//        }
//        RequestContext.getCurrentInstance().closeDialog(titularLicencitarioNuevo);
    }

    public void addNuevoTitularNombre() {
        //        System.out.println("tipo solicitante  " + tipoSolicitante);
        //        titularLicencitarioNuevo.setTipo_titular(tipoSolicitante);
        //        titularLicencitarioNuevo.setTipo_persona(tipo_persona);
        //        if (tipoSolicitante.equals(EnumTipoTitular.JURIDICO.getCodigo())) {
        //            titularLicencitarioNuevo.setNombre_razonsocial(razonSocial);
        //        } else {
        //            titularLicencitarioNuevo.setNombre_razonsocial(nombre);
        //            titularLicencitarioNuevo.setPrimer_apellido(primerApellido);
        //            titularLicencitarioNuevo.setSegundo_apellido(segundoApellido);
        //        }
        //        RequestContext.getCurrentInstance().closeDialog(titularLicencitarioNuevo);
    }

    /////////////////////////////////////////////////
    public void addTitularRegistrado() {
        titularLicenciatarioRegistrado.setNombre_razonsocial(razonSocial);
        titularLicenciatarioRegistrado.setDireccion(domicilio);
        RequestContext.getCurrentInstance().closeDialog(titularLicenciatarioRegistrado);
    }

    public void cerrarDialogo() {
        System.out.println("exit:::::::::::::::::::::::::::::::::::::::::::::::::");
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
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="-----------------------------GETS  Y SETS-------------">
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

    public List<Dominio> getListaLugarExpedicion() throws Exception {
        return listaLugarExpedicion = dominioService.obtenerListadoDominio(EnumNombreDominio.LUGAR_EXPEDICION.getCodigo());
    }

    public void setListaLugarExpedicion(List<Dominio> listaLugarExpedicion) {
        this.listaLugarExpedicion = listaLugarExpedicion;
    }

    public List<Dominio> getListaPais() throws Exception {
        return listaPais = dominioService.obtenerListadoDominio(EnumNombreDominio.PAIS.getCodigo());
    }

    public void setListaPais(List<Dominio> listaPais) {
        this.listaPais = listaPais;
    }

    public List<Dominio> getListaTipoTitular() throws Exception {
        return listaTipoTitular = dominioService.obtenerListadoDominio(EnumNombreDominio.TIPO_TITULAR.getCodigo());
    }

    public void setListaTipoTitular(List<Dominio> listaTipoTitular) {
        this.listaTipoTitular = listaTipoTitular;
    }

    public List<Dominio> getListaGenero() throws Exception {
        return listaGenero = dominioService.obtenerListadoDominio(EnumNombreDominio.GENERO_MAS_FEM.getCodigo());
    }

    public void setListaGenero(List<Dominio> listaGenero) {
        this.listaGenero = listaGenero;
    }

    public List<Dominio> getListaTipoDocumentoNatural() throws Exception {
        return listaTipoDocumentoNatural = dominioService.obtenerListadoDominio(EnumNombreDominio.TIPO_DOCUMENTO.getCodigo());
    }

    public void setListaTipoDocumentoNatural(List<Dominio> listaTipoDocumentoNatural) {
        this.listaTipoDocumentoNatural = listaTipoDocumentoNatural;
    }

    public RenSolicitanteApoderado getRenApoderado() {
        return renApoderado;
    }

    public void setRenApoderado(RenSolicitanteApoderado renApoderado) {
        this.renApoderado = renApoderado;
    }

    public RenSolicitanteApoderado getRenSolicitante() {
        return renSolicitante;
    }

    public void setRenSolicitante(RenSolicitanteApoderado renSolicitante) {
        this.renSolicitante = renSolicitante;
    }

    public String getTipo_persona() {
        return tipo_persona;
    }

    public void setTipo_persona(String tipo_persona) {
        this.tipo_persona = tipo_persona;
    }

    public String getValorRecibido() {
        return valorRecibido;
    }

    public void setValorRecibido(String valorRecibido) {
        this.valorRecibido = valorRecibido;
    }

    public RenTitularRegistrado getTitularLicenciatarioRegistrado() {
        return titularLicenciatarioRegistrado;
    }

    public void setTitularLicenciatarioRegistrado(RenTitularRegistrado titularLicenciatarioRegistrado) {
        this.titularLicenciatarioRegistrado = titularLicenciatarioRegistrado;
    }

    public List<Dominio> getListaTipoDocumentoJuridico() throws Exception {
        return listaTipoDocumentoJuridico = dominioService.listar_dominio_dominiopadre(EnumNombreDominio.TIPO_DOCUMENTO.getCodigo(), "TODO");
    }

    public void setListaTipoDocumentoJuridico(List<Dominio> listaTipoDocumentoJuridico) {
        this.listaTipoDocumentoJuridico = listaTipoDocumentoJuridico;
    }

//</editor-fold>
}
