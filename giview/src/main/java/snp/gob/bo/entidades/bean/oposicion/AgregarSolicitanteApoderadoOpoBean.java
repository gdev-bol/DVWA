/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.bean.oposicion;

import static java.lang.Boolean.TRUE;
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
import snp.gob.bo.gimodel.entidad.OpoHistorial;
import snp.gob.bo.gimodel.entidad.OpoMarcademandada;
import snp.gob.bo.gimodel.entidad.OpoSolicitanteapoderado;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.OpoHistorialService;

/**
 *
 * @author Luis Angel Quispe Limachi
 */
@ManagedBean(name = "agregarSolicitanteApoderadoOpoBean")
@ViewScoped
public class AgregarSolicitanteApoderadoOpoBean {

    public AgregarSolicitanteApoderadoOpoBean() {
    }
    private String tipoSolicitante = "JUR";
    private Boolean panelSolicitanteRendered = true;
    private List<OpoMarcademandada> lstPersonas;

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
    private OpoSolicitanteapoderado solicitanteApoderado = new OpoSolicitanteapoderado();
    private OpoSolicitanteapoderado solicitanteApoderadodmda = new OpoSolicitanteapoderado();
    private String valorRecibido = "";
    private Boolean mensaje = false;
    private Boolean panelSolicitante = true;
    private String tipo_persona = "";
    private String fax;
    private String celular;
    private String poder;
    private String tituloPagina = "Agregar Persona";
    private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private List<Dominio> listaLugarExpedicion;
    private List<Dominio> listaPais;
    private List<Dominio> listaTipoTitular;
    private List<Dominio> listaGenero;
    private List<Dominio> listaTipoDocumentoNatural;
    private List<Dominio> listaTipoDocumentoJuridico;
    private Boolean banderadmte;
    private Boolean banderadmdo;
    private Long clavepri;
    private OpoHistorial datoshistorialopo = new OpoHistorial();
    @ManagedProperty("#{dominioService}")
    private DominioService dominioService;

    @ManagedProperty(value = "#{opoHistorialService}")
    private OpoHistorialService opoHistorialService;
    @ManagedProperty("#{comunService}")
    private ComunService comunService;

    @PostConstruct
    public void AgregarSoliApoOpoBeanInit() {

        try {
            listaLugarExpedicion = dominioService.obtenerListadoDominio("lugar_expedicion");
            listaPais = dominioService.obtenerListadoDominio("pais");
            listaTipoTitular = dominioService.obtenerListadoDominio("tipo_titular");
            listaGenero = dominioService.obtenerListadoDominio("genero");
            listaTipoDocumentoNatural = dominioService.obtenerListadoDominio("tipo_documento");
            listaTipoDocumentoJuridico = dominioService.listar_dominio_dominiopadre("tipo_documento", "TODO");
        } catch (Exception ex) {
            Logger.getLogger(AgregarSolicitanteApoderadoOpoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        valorRecibido = params.get("id");

        
        panelSolicitante = valorRecibido == null;
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        Map<String, Object> sessionMapcla = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        if (sessionMapcla.get("clavepri") != null) {
            clavepri = (Long) sessionMapcla.get("clavepri");
        }
       
        if (sessionMap.get("persona") != null) {
            solicitanteApoderado = (OpoSolicitanteapoderado) sessionMap.get("persona");
            tipoSolicitante = solicitanteApoderado.getTipo_titular();
            panelSolicitanteRendered = !tipoSolicitante.equals("NAT");
            inicializaPersona(tipoSolicitante);
            tituloPagina = "Agregar Representante Legal";
        }

//        System.out.println("SESSION MAP demmteee " + sessionMapdmda.get("personadmda"));
//        if (sessionMapdmda.get("personadmda") != null) {
//            solicitanteApoderadodmda = (OpoSolicitanteapoderado) sessionMapdmda.get("personadmda");
//            tipoSolicitante = solicitanteApoderadodmda.getTipo_titular();
//            panelSolicitanteRendered = !tipoSolicitante.equals("NAT");
//            inicializaPersonadmda(tipoSolicitante);
//            tituloPagina = "Agregar Representante Legal";
//            System.out.println("IDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD===========" + solicitanteApoderadodmda.getIdsolicitanteapoderado());
//        }
    }

    /**
     * *
     * Método para inicializar persona
     *
     * Creado: Luis Quispe Fecha:17/10/2016
     *
     */
    public void inicializaPersona(String tipoSolicitante) {

        if (tipoSolicitante.equals("JUR")) {
            razonSocial = solicitanteApoderado.getNombre_razonsocial();
            tipoDocumento = solicitanteApoderado.getTipo_documento();
            numeroDocumento = solicitanteApoderado.getNumero_documento();
            pais = solicitanteApoderado.getPais();
            departamento = solicitanteApoderado.getSolicitud_depa();
        } else {
            nombre = solicitanteApoderado.getNombre_razonsocial();
            primerApellido = solicitanteApoderado.getPrimer_apellido();
            segundoApellido = solicitanteApoderado.getSegundo_apellido();
            numeroDocumento = solicitanteApoderado.getNumero_documento();
            tipoDocumento = solicitanteApoderado.getTipo_documento();
            lugarExpedicion = solicitanteApoderado.getLugar_expedicion();
            pais = solicitanteApoderado.getPais();
            generoPersona = solicitanteApoderado.getGenero();
            departamento = solicitanteApoderado.getSolicitud_depa();
            poder = solicitanteApoderado.getPoder();
        }
        telefono = solicitanteApoderado.getTelefono();
        domicilio = solicitanteApoderado.getDireccion();
        correoElectronico = solicitanteApoderado.getEmail();
        fax = solicitanteApoderado.getFax();
        celular = solicitanteApoderado.getCelular();

    }

    /**
     * *
     * Método para inicializar persona
     *
     * Creado: Luis Quispe Fecha:17/10/2016
     *
     */
    public void inicializaPersonadmda(String tipoSolicitante) {

        if (tipoSolicitante.equals("JUR")) {
            razonSocial = solicitanteApoderadodmda.getNombre_razonsocial();
            tipoDocumento = solicitanteApoderadodmda.getTipo_documento();
            numeroDocumento = solicitanteApoderadodmda.getNumero_documento();
            pais = solicitanteApoderadodmda.getPais();
            departamento = solicitanteApoderadodmda.getSolicitud_depa();
        } else {
            nombre = solicitanteApoderadodmda.getNombre_razonsocial();
            primerApellido = solicitanteApoderadodmda.getPrimer_apellido();
            segundoApellido = solicitanteApoderadodmda.getSegundo_apellido();
            numeroDocumento = solicitanteApoderadodmda.getNumero_documento();
            tipoDocumento = solicitanteApoderadodmda.getTipo_documento();
            lugarExpedicion = solicitanteApoderadodmda.getLugar_expedicion();
            pais = solicitanteApoderadodmda.getPais();
            generoPersona = solicitanteApoderadodmda.getGenero();
            departamento = solicitanteApoderadodmda.getSolicitud_depa();
            poder = solicitanteApoderadodmda.getPoder();
        }
        telefono = solicitanteApoderadodmda.getTelefono();
        domicilio = solicitanteApoderadodmda.getDireccion();
        correoElectronico = solicitanteApoderadodmda.getEmail();
        fax = solicitanteApoderadodmda.getFax();
        celular = solicitanteApoderadodmda.getCelular();

    }

    /**
     * *
     * Método Valor tipo persona
     *
     * Creado: Luis Quispe Fecha:17/10/2016
     *
     */
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

    /**
     * *
     * Método Valida Email
     *
     * Creado: Luis Quispe Fecha:17/10/2016
     *
     */
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

    /**
     * *
     * Método Adiciona solicitante juridico
     *
     * Creado: Luis Quispe Fecha:17/10/2016
     *
     */
    public void addSolicitanteJuridico() throws Exception {
      
        tipo_persona = valorTipoPersona(panelSolicitante, valorRecibido);

        if (validateEmail(correoElectronico)) {
            solicitanteApoderado.setIdmarcademandante(1L);
            solicitanteApoderado.setTipo_titular(tipoSolicitante);
            solicitanteApoderado.setTipo_persona(tipo_persona);
            solicitanteApoderado.setNombre_razonsocial(razonSocial);
            solicitanteApoderado.setTipo_documento(tipoDocumento);
            solicitanteApoderado.setNumero_documento(numeroDocumento);
            solicitanteApoderado.setPais(pais);

            solicitanteApoderado.setSolicitud_depa(departamento);

            solicitanteApoderado.setDireccion(domicilio);
            if (!correoElectronico.equals("")) {
                solicitanteApoderado.setEmail(correoElectronico);
            }
            solicitanteApoderado.setTelefono(telefono);
            solicitanteApoderado.setFax(fax);
            solicitanteApoderado.setCelular(celular);
            solicitanteApoderado.setEstado("AC");
            
             if (clavepri != null) {
                datoshistorialopo.setIdoposicion(clavepri);
                datoshistorialopo.setIdlogtrans(1L);
              //  datoshistorialopo.setEstado(datoshistorialaux.getEstado());
                datoshistorialopo.setOperacion("MOD-SOLI-DMTE-DMDO");
                datoshistorialopo.setFecha_operacion(comunService.obtenerFechaServidor(1L));
                datoshistorialopo.setId_usuario(2L);
                opoHistorialService.guardaOpoHistorial(datoshistorialopo);
                 
            }
            RequestContext.getCurrentInstance().closeDialog(solicitanteApoderado);
        } else {
            mensaje = true;
            
        }
       
    }

    /**
     * *
     * Método Adiciona solicitante Natural
     *
     * Creado: Luis Quispe Fecha:17/10/2016
     *
     */
    public void addSolicitanteNatural() throws Exception {
        
        //tipo_persona = valorTipoPersona(panelSolicitante, valorRecibido);
        tipo_persona = "SOLI";
        if (validateEmail(correoElectronico)) {
            solicitanteApoderado.setIdmarcademandante(1L);

            solicitanteApoderado.setTipo_titular(tipoSolicitante);
            solicitanteApoderado.setTipo_persona(tipo_persona);
            solicitanteApoderado.setNombre_razonsocial(nombre);
            solicitanteApoderado.setPrimer_apellido(primerApellido);

            solicitanteApoderado.setSegundo_apellido(segundoApellido);

            solicitanteApoderado.setNumero_documento(numeroDocumento);
            solicitanteApoderado.setTipo_documento(tipoDocumento);

            solicitanteApoderado.setLugar_expedicion(lugarExpedicion);

            solicitanteApoderado.setPais(pais);
            solicitanteApoderado.setGenero(generoPersona);

            solicitanteApoderado.setSolicitud_depa(departamento);

            solicitanteApoderado.setDireccion(domicilio);
            if (!correoElectronico.equals("")) {
                solicitanteApoderado.setEmail(correoElectronico);
            }
            solicitanteApoderado.setTelefono(telefono);
            solicitanteApoderado.setFax(fax);
            solicitanteApoderado.setCelular(celular);
            solicitanteApoderado.setEstado("AC");
            
             if (clavepri != null) {
                datoshistorialopo.setIdoposicion(clavepri);
                datoshistorialopo.setIdlogtrans(1L);
              //  datoshistorialopo.setEstado(datoshistorialaux.getEstado());
                datoshistorialopo.setOperacion("MOD-SOLI-DMTE-DMDO");
                datoshistorialopo.setFecha_operacion(comunService.obtenerFechaServidor(1L));
                datoshistorialopo.setId_usuario(2L);
                opoHistorialService.guardaOpoHistorial(datoshistorialopo);
            }
            
            
        } else {
            mensaje = true;
           
        }

        RequestContext.getCurrentInstance().closeDialog(solicitanteApoderado);
    }

    /**
     * *
     * Método Adiciona Apoderado
     *
     * Creado: Luis Quispe Fecha:17/10/2016
     *
     */
    public void addApoderado() throws Exception {
        solicitanteApoderado.setIdmarcademandante(1L);
        //solicitanteApoderado.setIdLogTrans(1L);
        solicitanteApoderado.setTipo_titular("NAT");
        solicitanteApoderado.setTipo_persona("APOD");
        solicitanteApoderado.setNombre_razonsocial(nombre);
        solicitanteApoderado.setPrimer_apellido(primerApellido);
        solicitanteApoderado.setSegundo_apellido(segundoApellido);
        solicitanteApoderado.setNumero_documento(numeroDocumento);
        solicitanteApoderado.setTipo_documento(tipoDocumento);
        solicitanteApoderado.setLugar_expedicion(lugarExpedicion);
        solicitanteApoderado.setPais(pais);
        solicitanteApoderado.setGenero(generoPersona);
        solicitanteApoderado.setSolicitud_depa(departamento);
        solicitanteApoderado.setDireccion(domicilio);
        if (!correoElectronico.equals("")) {
            solicitanteApoderado.setEmail(correoElectronico);
        }

        solicitanteApoderado.setTelefono(telefono);
        solicitanteApoderado.setFax(fax);
        solicitanteApoderado.setCelular(celular);
        solicitanteApoderado.setPoder(poder);
        solicitanteApoderado.setEstado("AC");
        
         if (clavepri != null) {
                datoshistorialopo.setIdoposicion(clavepri);
                datoshistorialopo.setIdlogtrans(1L);
              //  datoshistorialopo.setEstado(datoshistorialaux.getEstado());
                datoshistorialopo.setOperacion("MOD-APOD-DMTE-DMDO");
                datoshistorialopo.setFecha_operacion(comunService.obtenerFechaServidor(1L));
                datoshistorialopo.setId_usuario(2L);
                opoHistorialService.guardaOpoHistorial(datoshistorialopo);
            }
         
        RequestContext.getCurrentInstance().closeDialog(solicitanteApoderado);
    }

    /**
     * *
     * Método Adiciona solicitante juridico
     *
     * Creado: Luis Quispe Fecha:17/10/2016
     *
     */
//    public void addSolicitanteJuridicodmda() {
//        System.out.println("tipo personaSolApo---  " + tipo_persona + "  tipo titularJN ---  " + tipoSolicitante);
//        tipo_persona = valorTipoPersona(panelSolicitante, valorRecibido);
//
//        if (validateEmail(correoElectronico)) {
//            solicitanteApoderadodmda.setIdmarcademandante(1L);
//            solicitanteApoderadodmda.setTipo_titular(tipoSolicitante);
//            solicitanteApoderadodmda.setTipo_persona(tipo_persona);
//            solicitanteApoderadodmda.setNombre_razonsocial(razonSocial);
//            solicitanteApoderadodmda.setTipo_documento(tipoDocumento);
//            solicitanteApoderadodmda.setNumero_documento(numeroDocumento);
//            solicitanteApoderadodmda.setPais(pais);
//
//            solicitanteApoderadodmda.setSolicitud_depa(departamento);
//
//            solicitanteApoderadodmda.setDireccion(domicilio);
//            if (!correoElectronico.equals("")) {
//                solicitanteApoderadodmda.setEmail(correoElectronico);
//            }
//            solicitanteApoderadodmda.setTelefono(telefono);
//            solicitanteApoderadodmda.setFax(fax);
//            solicitanteApoderadodmda.setCelular(celular);
//            solicitanteApoderadodmda.setEstado("AC");
//            RequestContext.getCurrentInstance().closeDialog(solicitanteApoderadodmda);
//        } else {
//            mensaje = true;
//            System.out.println("el correo no es esta bien");
//        }
//        System.out.println("IDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD========2222222222222222===" + solicitanteApoderadodmda.getIdsolicitanteapoderado());
//    }
    /**
     * *
     * Método Adiciona solicitante Natural
     *
     * Creado: Luis Quispe Fecha:20/10/2016
     *
     */
    /**
     * *
     * Método Adiciona solicitante Natural
     *
     * Creado: Luis Quispe Fecha:20/10/2016
     *
     */
    public void addSolicitanteNaturaldmda() {
        
        //tipo_persona = valorTipoPersona(panelSolicitante, valorRecibido);
        tipo_persona = "SOLI";
        if (validateEmail(correoElectronico)) {
            solicitanteApoderadodmda.setIdmarcademandante(1L);

            solicitanteApoderadodmda.setTipo_titular(tipoSolicitante);
            solicitanteApoderadodmda.setTipo_persona(tipo_persona);
            solicitanteApoderadodmda.setNombre_razonsocial(nombre);
            solicitanteApoderadodmda.setPrimer_apellido(primerApellido);

            solicitanteApoderadodmda.setSegundo_apellido(segundoApellido);

            solicitanteApoderadodmda.setNumero_documento(numeroDocumento);
            solicitanteApoderadodmda.setTipo_documento(tipoDocumento);

            solicitanteApoderadodmda.setLugar_expedicion(lugarExpedicion);

            solicitanteApoderadodmda.setPais(pais);
            solicitanteApoderadodmda.setGenero(generoPersona);

            solicitanteApoderadodmda.setSolicitud_depa(departamento);

            solicitanteApoderadodmda.setDireccion(domicilio);
            if (!correoElectronico.equals("")) {
                solicitanteApoderadodmda.setEmail(correoElectronico);
            }
            solicitanteApoderadodmda.setTelefono(telefono);
            solicitanteApoderadodmda.setFax(fax);
            solicitanteApoderadodmda.setCelular(celular);
            solicitanteApoderadodmda.setEstado("AC");
        } else {
            mensaje = true;
            
        }

        RequestContext.getCurrentInstance().closeDialog(solicitanteApoderadodmda);
    }

    /**
     * *
     * Método Adiciona apoderado
     *
     * Creado: Luis Quispe Fecha:20/10/2016
     *
     */
    /**
     * *
     * Método para cerrar dialogo
     *
     * Creado: Luis Quispe Fecha:20/10/2016
     *
     */
    public void cerrarDialogo() {

        //pass back to root
        RequestContext.getCurrentInstance().closeDialog("Exit");
    }

    /**
     * *
     * Método Validar correo
     *
     * Creado: Luis Quispe Fecha:20/10/2016
     *
     */
    public void metodoCorreo() {
        if (validaEmail(correoElectronico)) {//validador correcto 
            mensaje = false;
        } else {
            mensaje = !correoElectronico.equals("");
        }
    }

    /**
     * *
     * Método Validar email
     *
     * Creado: Luis Quispe Fecha:20/10/2016
     *
     */
    public boolean validaEmail(String email) {
        // Compiles the given regular expression into a pattern.
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        // Match the given input against this pattern
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void activaCamposNaturalJuridico() {

        panelSolicitanteRendered = !tipoSolicitante.equals("NAT");
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

    public List<OpoMarcademandada> getLstPersonas() {
        return lstPersonas;
    }

    public void setLstPersonas(List<OpoMarcademandada> lstPersonas) {
        this.lstPersonas = lstPersonas;
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

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
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

    public OpoSolicitanteapoderado getSolicitanteApoderado() {
        return solicitanteApoderado;
    }

    public void setSolicitanteApoderado(OpoSolicitanteapoderado solicitanteApoderado) {
        this.solicitanteApoderado = solicitanteApoderado;
    }

    public String getValorRecibido() {
        return valorRecibido;
    }

    public void setValorRecibido(String valorRecibido) {
        this.valorRecibido = valorRecibido;
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

    public String getTipo_persona() {
        return tipo_persona;
    }

    public void setTipo_persona(String tipo_persona) {
        this.tipo_persona = tipo_persona;
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

    public DominioService getDominioService() {
        return dominioService;
    }

    public void setDominioService(DominioService dominioService) {
        this.dominioService = dominioService;
    }

    public OpoSolicitanteapoderado getSolicitanteApoderadodmda() {
        return solicitanteApoderadodmda;
    }

    public void setSolicitanteApoderadodmda(OpoSolicitanteapoderado solicitanteApoderadodmda) {
        this.solicitanteApoderadodmda = solicitanteApoderadodmda;
    }

    public Boolean getBanderadmte() {
        return banderadmte;
    }

    public void setBanderadmte(Boolean banderadmte) {
        this.banderadmte = banderadmte;
    }

    public Boolean getBanderadmdo() {
        return banderadmdo;
    }

    public void setBanderadmdo(Boolean banderadmdo) {
        this.banderadmdo = banderadmdo;
    }

    public Long getClavepri() {
        return clavepri;
    }

    public void setClavepri(Long clavepri) {
        this.clavepri = clavepri;
    }

    public OpoHistorial getDatoshistorialopo() {
        return datoshistorialopo;
    }

    public void setDatoshistorialopo(OpoHistorial datoshistorialopo) {
        this.datoshistorialopo = datoshistorialopo;
    }

    public OpoHistorialService getOpoHistorialService() {
        return opoHistorialService;
    }

    public void setOpoHistorialService(OpoHistorialService opoHistorialService) {
        this.opoHistorialService = opoHistorialService;
    }

    public ComunService getComunService() {
        return comunService;
    }

    public void setComunService(ComunService comunService) {
        this.comunService = comunService;
    }
    
    

}
