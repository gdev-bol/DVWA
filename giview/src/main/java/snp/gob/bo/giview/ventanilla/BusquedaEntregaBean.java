/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.giview.ventanilla;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
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
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.entidad.Procurador;
import snp.gob.bo.gimodel.entidad.TramiteEntrega;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.enums.EnumCarpetasTemporales;
import snp.gob.bo.gimodel.enums.EnumEstado;
import snp.gob.bo.gimodel.enums.EnumTipoTitular;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.EntregaTramiteService;
import snp.gob.bo.gimodel.servicio.ProcuradorService;
import snp.gob.bo.gimodel.servicio.UsuarioService;

/**
 *
 * @author levi
 */
@ManagedBean(name = "busquedaEntregaBean")
@ViewScoped
public class BusquedaEntregaBean extends AbstractManagedBean implements Serializable {

    private String numeroBloque;
    private Long valorSerie;
    private String gestionBloque;
    private String procuNombre;
    private String procuPrimerApe;
    private String procuSegundoApe;
    private String procuTipoTitular;
    private String procuNumeroDocumento;
    private String procuTipoDocumento;
    private String procuLugarExpedicion;
    private String procuTelefono;
    private String procuCelular;
    private String procuEstado;
    private String procuTipoDocAuto;
    private String procuDescDocuAuto;
    private Integer totalBuzon;
    private Procurador procurador;
    private boolean muestraImprime = true;
    private boolean muestraModifica = true;
    private String bloqueMuestra;
    private String letraRegionalMuestra;
    private String GestionMuestra;
    private String bloqueResult;

    @ManagedProperty("#{entregaTramiteService}")
    private EntregaTramiteService entregaTramiteService;
    @ManagedProperty("#{procuradorService}")
    private ProcuradorService procuradorService;
    @ManagedProperty("#{usuarioService}")
    private UsuarioService usuarioService;
    @ManagedProperty("#{dominioService}")
    private DominioService dominioService;
    @ManagedProperty("#{comunService}")
    private ComunService comunService;

    private List<TramiteEntrega> tramitesDer = new ArrayList<TramiteEntrega>();
    private List<TramiteEntrega> tramitesDerAgarrados = new ArrayList<TramiteEntrega>();

    private List<Dominio> listTipoTitular = new ArrayList<Dominio>();
    private List<Dominio> listTipoDocumento = new ArrayList<Dominio>();
    private List<Dominio> listDepartamento = new ArrayList<Dominio>();
    private List<Dominio> listTipoDocuAutoriza = new ArrayList<Dominio>();
    private Map<String, Object> parametros = new HashMap();
    private Boolean[] validaDatosEntrega = new Boolean[15];

    @PostConstruct
    public void initEntregaTramiteBean() {

        try {
            listTipoTitular = dominioService.obtenerListadoDominio("tipo_titular");
            System.out.println("listTipoTitular::" + listTipoTitular.size());
            listTipoDocumento = dominioService.obtenerListadoDominio("tipo_documento");
            listDepartamento = dominioService.obtenerListadoDominio("departamento");

        } catch (Exception ex) {
            Logger.getLogger(EntregaTramiteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void buscaBloque() throws Exception {
        System.out.println("numeroBloque:" + numeroBloque);
        System.out.println("gestionBloque:" + gestionBloque);
        System.out.println("valorSerie:" + valorSerie);
        tramitesDer = entregaTramiteService.tramitesPorBloque(Integer.parseInt(numeroBloque), Integer.parseInt(gestionBloque), valorSerie);
        if (tramitesDer.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El bloque no existe", ""));
        } else {
            bloqueMuestra = numeroBloque;
            letraRegionalMuestra = muestraSerie(valorSerie.intValue());
            GestionMuestra = gestionBloque;
            bloqueResult = bloqueMuestra + letraRegionalMuestra + "-" + GestionMuestra;

//tramitesDer
            procurador = procuradorService.procuradorPorId(tramitesDer.get(0).getIdprocurador());
            procuTipoDocAuto = procurador.getTipodocumentoautorizacion();
            procuDescDocuAuto = procurador.getDescripciondocumentoautorizacion();
            procuTipoTitular = procurador.getTipo_titular();
            procuNombre = procurador.getNombre_razonsocial();
            procuPrimerApe = procurador.getPrimer_apellido();
            procuSegundoApe = procurador.getSegundo_apellido();
            procuTipoDocumento = procurador.getTipo_documento();
            procuNumeroDocumento = procurador.getNumero_documento();
            procuLugarExpedicion = procurador.getLugar_expedicion();
            procuTelefono = procurador.getTelefono();
            procuCelular = procurador.getCelular();
            muestraModifica = false;
            muestraImprime = true;
        }
    }

    public String convierteStr(TramiteEntrega tra) {
        String valor = "";
        if (!tra.getTipo_tramite().equals("OTR")) {
            valor = tra.getNumero_registro() + "-" + tra.getSerie_registro();
        }
        String str = String.valueOf(valor);
        return str;
    }

    public String claseNiza(TramiteEntrega tra) {
        String valor = "";
        if (tra != null) {
            if (!tra.getTipo_tramite().equals("OTR")) {
                valor = tra.getClase_niza().toString();
            } else {
                if (tra.getClase_niza() != null) {
                    valor = tra.getClase_niza().toString();
                }
            }
            return valor;
        }
        return null;
    }

    public String muestraSerie(int valorSerie) {
        String labelSigla = "";
        switch (valorSerie) {
            case 2:
                labelSigla = "-E";
                break;
            case 3:
                labelSigla = "-C";
                break;
            case 4:
                labelSigla = "-S";
                break;
            case 5:
                labelSigla = "-T";
                break;
            case 6:
                labelSigla = "-H";
                break;
            case 7:
                labelSigla = "-O";
                break;
            default:
                labelSigla = "";
                break;

        }
        return labelSigla;
    }

    public String codigoRegional(Usuario usuario) {
        String labelSigla;
        int ope;
        ope = (usuario.getIdregional().intValue());
        switch (ope) {
            case 2:
                labelSigla = "-E";
                break;
            case 3:
                labelSigla = "-C";
                break;
            case 4:
                labelSigla = "-S";
                break;
            case 5:
                labelSigla = "-T";
                break;
            case 6:
                labelSigla = "-H";
                break;
            case 7:
                labelSigla = "-O";
                break;
            default:
                labelSigla = "";
                break;
        }
        return labelSigla;
    }

    public void imprimeBloque() {
        muestraModifica = true;
        muestraImprime = false;
    }

    public void devuelveValores() {
        //   System.out.println("tam tramitesAgarrados2::"+tramitesAgarrados2.size());
        if (!tramitesDerAgarrados.isEmpty()) {
            for (int i = 0; i <= tramitesDerAgarrados.size() - 1; i++) {  // System.out.println("tramite de vuelta::"+tramitesAgarrados2.get(i).getOperador());
                tramitesDer.remove(tramitesDerAgarrados.get(i));
            }
            tramitesDerAgarrados.clear();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Datos devueltos", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No hay datos para devolver", ""));
        }
    }

    public void modificaEntrega() throws Exception {
        validaDatosEntrega = validadorGeneral();
        Procurador procumod = (Procurador) procurador.clone();
        if (validaDatosEntrega[4]) {
            procumod.setNombre_razonsocial(procuNombre);
            procumod.setNumero_documento(procuNumeroDocumento);
            if (procuPrimerApe != null && !procuPrimerApe.equals("")) {
                procumod.setPrimer_apellido(procuPrimerApe);
            }
            if (procuSegundoApe != null && !procuSegundoApe.equals("")) {
                procumod.setSegundo_apellido(procuSegundoApe);
            }
            procumod.setTipo_titular(procuTipoTitular);
            procumod.setTipo_documento(procuTipoDocumento);
            procumod.setLugar_expedicion(procuLugarExpedicion);
            procumod.setTelefono(procuTelefono);
            procumod.setCelular(procuCelular);
            procumod.setTipodocumentoautorizacion(procuTipoDocAuto);
            procumod.setDescripciondocumentoautorizacion(procuDescDocuAuto);
            TramiteEntrega tra = entregaTramiteService.modificaTramiteEntrega(procumod, tramitesDer, "ENTR", usuarioService.listaUsuarioXidPagina(super.getIdUsuarioSession()).get(0));
            //  System.out.println("tra regional"+tra.getIdregionalentrega());
            bloqueMuestra = tra.getNumerobloque().toString();
            letraRegionalMuestra = codigoRegional(usuarioService.listaUsuarioXidPagina(super.getIdUsuarioSession()).get(0));
            GestionMuestra = tra.getGestionbloque().toString();
            bloqueResult = bloqueMuestra + letraRegionalMuestra + "-" + GestionMuestra;
            muestraImprime = false;
            //   muestraBuscar=true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Datos guardados", ""));

        } else {
            muestraMensajes(validaDatosEntrega);
        }
//        Procurador procumod = (Procurador) procurador.clone();
//        if (procuNombre != null && !procuNombre.equals("")) {
//            procumod.setNombre_razonsocial(procuNombre);
//            if (procuNumeroDocumento != null && !procuNumeroDocumento.equals("")) {
//                procumod.setNumero_documento(procuNumeroDocumento);
//                if (procuPrimerApe != null && !procuPrimerApe.equals("")) {
//                    procumod.setPrimer_apellido(procuPrimerApe);
//                }
//                if (procuSegundoApe != null && !procuSegundoApe.equals("")) {
//                    procumod.setSegundo_apellido(procuSegundoApe);
//                }
//                procumod.setTipo_titular(procuTipoTitular);
//                procumod.setTipo_documento(procuTipoDocumento);
//                procumod.setLugar_expedicion(procuLugarExpedicion);
//                procumod.setTelefono(procuTelefono);
//                procumod.setCelular(procuCelular);
//                TramiteEntrega tra = entregaTramiteService.modificaTramiteEntrega(procumod, tramitesDer, "ENTR", usuarioService.listaUsuarioXidPagina(super.getIdUsuarioSession()).get(0));
//                bloqueMuestra = tra.getNumerobloque().toString();
//                letraRegionalMuestra = codigoRegional(usuarioService.listaUsuarioXidPagina(super.getIdUsuarioSession()).get(0));
//                GestionMuestra = tra.getGestionbloque().toString();
//                bloqueResult = bloqueMuestra + letraRegionalMuestra + "-" + GestionMuestra;
//                muestraImprime = false;
//
//            } else {
//                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingrese Número de Documento", "");
//                FacesContext.getCurrentInstance().addMessage(null, message);
//            }
//        } else {
//
//            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingrese Nombre/Razón Social", "");
//            FacesContext.getCurrentInstance().addMessage(null, message);
//        }

    }

    public void Imprimir() throws JRException, IOException, Exception {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String rutaCarpeta = servletContext.getRealPath(EnumCarpetasTemporales.CARPETA_QR.getCodigo());
        File carpeta = new File(rutaCarpeta);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
        File[] ficheros = carpeta.listFiles();
        for (File fichero : ficheros) {
            fichero.delete();
        }

        String nroBloque = bloqueResult;

        List<TramiteEntrega> lista = entregaTramiteService.tramitesPorBloque(Integer.parseInt(bloqueMuestra), Integer.parseInt(GestionMuestra), usuarioService.listaUsuarioXidPagina(super.getIdUsuarioSession()).get(0).getIdregional());
        if (lista.size() > 0) {
            Procurador p = procuradorService.procuradorPorId(lista.get(0).getIdprocurador());

            String imageSenapi = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/logoNuevo.jpg");
            String imageEscudoBol = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/esc-Bolivia");

            Date fechaSistema = comunService.obtenerFechaHoraServidor(1L);

            SimpleDateFormat formateador1 = new SimpleDateFormat("dd/MM/yyyy");
            String fechaIngreso = formateador1.format(fechaSistema);
            SimpleDateFormat formateador2 = new SimpleDateFormat("HH:mm:ss");
            String horaIngreso = formateador2.format(fechaSistema);

            Integer total = lista.size();

            String solicitante = this.devuelveNombreJuridicoONatural(p.getNombre_razonsocial(), p.getPrimer_apellido(), p.getSegundo_apellido());
            String documento = this.devuelveNombreJuridicoONatural(p.getTipo_documento(), p.getNumero_documento(), p.getLugar_expedicion());

            String autorizacion = "";

            if (p.getTipodocumentoautorizacion() != null) {
                if (p.getDescripciondocumentoautorizacion() != null && !p.getDescripciondocumentoautorizacion().equals("")) {
                    autorizacion = dominioService.obtenerNombrePorCodigoDominio(p.getTipodocumentoautorizacion(), "tipo_doc_entrega") + ", " + p.getDescripciondocumentoautorizacion();
                } else {
                    if (!p.getTipodocumentoautorizacion().equals("null") && !p.getTipodocumentoautorizacion().equals("")) {
                        autorizacion = dominioService.obtenerNombrePorCodigoDominio(p.getTipodocumentoautorizacion(), "tipo_doc_entrega");
                    }
                }
            } else if (p.getCelular() != null) {
                autorizacion = p.getDescripciondocumentoautorizacion();
            }

            String telefono = "";
            if (p.getTelefono() != null) {
                if (p.getCelular() != null) {
                    telefono = p.getTelefono() + "-" + p.getCelular();
                } else {
                    telefono = p.getTelefono();
                }
            } else if (p.getCelular() != null) {
                telefono = p.getCelular();
            }

            int i = 0;
            List<TramitePojo> listaTramite = new ArrayList<>();
            for (TramiteEntrega item : lista) {
                TramitePojo tramite = new TramitePojo();
                i++;
                tramite.setNumero(i);
                tramite.setSigla(item.getSigla());
                tramite.setNumerotramite(item.getNumerotramite());
                tramite.setGestion(item.getGestion());
                String numeroreg = "";
                if (item.getNumero_registro() != null && item.getNumero_registro() != 0) {
                    if (item.getSerie_registro() != null) {
                        numeroreg = item.getNumero_registro() + "-" + item.getSerie_registro();
                    } else {
                        numeroreg = item.getNumero_registro().toString();
                    }
                }
                tramite.setNumeroregistro(numeroreg);
                tramite.setMarca(item.getMarca());
                tramite.setClase_niza(item.getClase_niza());
                listaTramite.add(tramite);
            }
            String codigo = solicitante + "|" + documento + "|" + fechaIngreso + "|" + nroBloque + "|" + total;
            String imagenDibuja = rutaCarpeta + File.separator + nroBloque;
            this.creaCodigoQR(codigo, imagenDibuja);
            String imgQR = imagenDibuja;

            parametros.put("imageSenapi", imageSenapi);
            parametros.put("imageEscudoBol", imageEscudoBol);
            parametros.put("nroBloque", nroBloque);
            parametros.put("total", total);
            parametros.put("fechaIngreso", fechaIngreso);
            parametros.put("horaIngreso", horaIngreso);
            parametros.put("solicitante", solicitante);
            parametros.put("documento", documento);
            parametros.put("telefono", telefono);
            parametros.put("imgQR", imgQR);
            parametros.put("autorizacion", autorizacion);

            String filename = "listaTramites.pdf";
            String jasperPath = "/template/formulario/reporListaEntregados.jasper";
            this.PDF(parametros, jasperPath, listaTramite, filename);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No existe el bloque", ""));
        }
    }

    public String devuelveNombreJuridicoONatural(String nombre, String primerApellido, String segundoApellido) {
        String campoNombreRazonSocial = " ";
        if (nombre != null && !nombre.equals("")) {
            campoNombreRazonSocial = nombre;
        }
        if (primerApellido != null && !primerApellido.equals("")) {
            campoNombreRazonSocial = campoNombreRazonSocial + " " + primerApellido;
        }
        if (segundoApellido != null && !segundoApellido.equals("")) {
            campoNombreRazonSocial = campoNombreRazonSocial + " " + segundoApellido;
        }
        return campoNombreRazonSocial;
    }

    public void creaCodigoQR(String texto, String ruta) throws WriterException, IOException {
        int width = 100;
        int height = 100;
        String imageFormat = "png";
        Writer writer = new QRCodeWriter();
        BitMatrix matriz = writer.encode(texto, BarcodeFormat.QR_CODE, width, height);
        FileOutputStream outputStream = new FileOutputStream(new File(ruta));
        MatrixToImageWriter.writeToStream(matriz, imageFormat, outputStream);
    }

    public void PDF(Map<String, Object> params, String jasperPath, List<?> dataSource, String fileName) throws JRException, IOException {
        String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(jasperPath);
        File file = new File(relativeWebPath);
        JasperPrint print = new JasperPrint();
        JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(dataSource, false);
        print = JasperFillManager.fillReport(file.getPath(), params, source);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(print, stream);
        FacesContext.getCurrentInstance().responseComplete();
    }

    public Boolean[] validadorGeneral() {

        Boolean swGeneral = true;
        Boolean[] validaSectoresAux = {false, false, false, false, swGeneral};

        validaSectoresAux[0] = !tramitesDer.isEmpty();
        if (procuNombre != null && !procuNombre.equals("")) {
            validaSectoresAux[1] = true;
        }
        if (procuTipoTitular.equals(EnumTipoTitular.NATURAL.getCodigo())) {
            if (procuPrimerApe != null && !procuPrimerApe.equals("")) {
                validaSectoresAux[2] = true;
            }
        } else {
            validaSectoresAux[2] = true;
        }

        if (procuNumeroDocumento != null && !procuNumeroDocumento.equals("")) {
            validaSectoresAux[3] = true;
        }

        for (Boolean validaSector : validaSectoresAux) {
            System.out.println("VALIDA SECTORES  " + validaSector);
            if (validaSector == false) {
                swGeneral = false;
                validaSectoresAux[4] = swGeneral;
            }
        }

        return validaSectoresAux;
    }

    public void muestraMensajes(Boolean[] validaDatosEntrega) {
        if (validaDatosEntrega[0] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No hay registros en la lista de entrega.", ""));
        }
        if (validaDatosEntrega[1] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ingrese Nombre/Razón Social", ""));
        }
        if (validaDatosEntrega[2] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ingrese apellido", ""));
        }

        if (validaDatosEntrega[3] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ingrese Número de Documento", ""));
        }

    }

    public boolean isMuestraModifica() {
        return muestraModifica;
    }

    public void setMuestraModifica(boolean muestraModifica) {
        this.muestraModifica = muestraModifica;
    }

    public String getNumeroBloque() {
        return numeroBloque;
    }

    public void setNumeroBloque(String numeroBloque) {
        this.numeroBloque = numeroBloque;
    }

    public Long getValorSerie() {
        return valorSerie;
    }

    public void setValorSerie(Long valorSerie) {
        this.valorSerie = valorSerie;
    }

    public String getGestionBloque() {
        return gestionBloque;
    }

    public void setGestionBloque(String gestionBloque) {
        this.gestionBloque = gestionBloque;
    }

    public String getProcuTipoTitular() {
        return procuTipoTitular;
    }

    public void setProcuTipoTitular(String procuTipoTitular) {
        this.procuTipoTitular = procuTipoTitular;
    }

    public String getProcuNumeroDocumento() {
        return procuNumeroDocumento;
    }

    public void setProcuNumeroDocumento(String procuNumeroDocumento) {
        this.procuNumeroDocumento = procuNumeroDocumento;
    }

    public String getProcuTipoDocumento() {
        return procuTipoDocumento;
    }

    public void setProcuTipoDocumento(String procuTipoDocumento) {
        this.procuTipoDocumento = procuTipoDocumento;
    }

    public String getProcuLugarExpedicion() {
        return procuLugarExpedicion;
    }

    public void setProcuLugarExpedicion(String procuLugarExpedicion) {
        this.procuLugarExpedicion = procuLugarExpedicion;
    }

    public String getProcuTelefono() {
        return procuTelefono;
    }

    public void setProcuTelefono(String procuTelefono) {
        this.procuTelefono = procuTelefono;
    }

    public String getProcuCelular() {
        return procuCelular;
    }

    public void setProcuCelular(String procuCelular) {
        this.procuCelular = procuCelular;
    }

    public String getProcuEstado() {
        return procuEstado;
    }

    public void setProcuEstado(String procuEstado) {
        this.procuEstado = procuEstado;
    }

    public EntregaTramiteService getEntregaTramiteService() {
        return entregaTramiteService;
    }

    public void setEntregaTramiteService(EntregaTramiteService entregaTramiteService) {
        this.entregaTramiteService = entregaTramiteService;
    }

    public ProcuradorService getProcuradorService() {
        return procuradorService;
    }

    public void setProcuradorService(ProcuradorService procuradorService) {
        this.procuradorService = procuradorService;
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public List<TramiteEntrega> getTramitesDer() {
        return tramitesDer;
    }

    public void setTramitesDer(List<TramiteEntrega> tramitesDer) {
        this.tramitesDer = tramitesDer;
    }

    public List<TramiteEntrega> getTramitesDerAgarrados() {
        return tramitesDerAgarrados;
    }

    public void setTramitesDerAgarrados(List<TramiteEntrega> tramitesDerAgarrados) {
        this.tramitesDerAgarrados = tramitesDerAgarrados;
    }

    public List<Dominio> getListTipoTitular() {
        return listTipoTitular;
    }

    public void setListTipoTitular(List<Dominio> listTipoTitular) {
        this.listTipoTitular = listTipoTitular;
    }

    public List<Dominio> getListTipoDocumento() {
        return listTipoDocumento;
    }

    public void setListTipoDocumento(List<Dominio> listTipoDocumento) {
        this.listTipoDocumento = listTipoDocumento;
    }

    public List<Dominio> getListDepartamento() {
        return listDepartamento;
    }

    public void setListDepartamento(List<Dominio> listDepartamento) {
        this.listDepartamento = listDepartamento;
    }

    public String getProcuNombre() {
        return procuNombre;
    }

    public void setProcuNombre(String procuNombre) {
        this.procuNombre = procuNombre;
    }

    public String getProcuPrimerApe() {
        return procuPrimerApe;
    }

    public void setProcuPrimerApe(String procuPrimerApe) {
        this.procuPrimerApe = procuPrimerApe;
    }

    public String getProcuSegundoApe() {
        return procuSegundoApe;
    }

    public void setProcuSegundoApe(String procuSegundoApe) {
        this.procuSegundoApe = procuSegundoApe;
    }

    public Integer getTotalBuzon() {
        return totalBuzon;
    }

    public void setTotalBuzon(Integer totalBuzon) {
        this.totalBuzon = totalBuzon;
    }

    public boolean isMuestraImprime() {
        return muestraImprime;
    }

    public void setMuestraImprime(boolean muestraImprime) {
        this.muestraImprime = muestraImprime;
    }

    public DominioService getDominioService() {
        return dominioService;
    }

    public void setDominioService(DominioService dominioService) {
        this.dominioService = dominioService;
    }

    public Procurador getProcurador() {
        return procurador;
    }

    public void setProcurador(Procurador procurador) {
        this.procurador = procurador;
    }

    public String getBloqueMuestra() {
        return bloqueMuestra;
    }

    public void setBloqueMuestra(String bloqueMuestra) {
        this.bloqueMuestra = bloqueMuestra;
    }

    public String getLetraRegionalMuestra() {
        return letraRegionalMuestra;
    }

    public void setLetraRegionalMuestra(String letraRegionalMuestra) {
        this.letraRegionalMuestra = letraRegionalMuestra;
    }

    public String getGestionMuestra() {
        return GestionMuestra;
    }

    public void setGestionMuestra(String GestionMuestra) {
        this.GestionMuestra = GestionMuestra;
    }

    public String getBloqueResult() {
        return bloqueResult;
    }

    public void setBloqueResult(String bloqueResult) {
        this.bloqueResult = bloqueResult;
    }

    public ComunService getComunService() {
        return comunService;
    }

    public void setComunService(ComunService comunService) {
        this.comunService = comunService;
    }

    public Map<String, Object> getParametros() {
        return parametros;
    }

    public void setParametros(Map<String, Object> parametros) {
        this.parametros = parametros;
    }

    public String getProcuTipoDocAuto() {
        return procuTipoDocAuto;
    }

    public void setProcuTipoDocAuto(String procuTipoDocAuto) {
        this.procuTipoDocAuto = procuTipoDocAuto;
    }

    public String getProcuDescDocuAuto() {
        return procuDescDocuAuto;
    }

    public void setProcuDescDocuAuto(String procuDescDocuAuto) {
        this.procuDescDocuAuto = procuDescDocuAuto;
    }

    public List<Dominio> getListTipoDocuAutoriza() throws Exception {
        return listTipoDocuAutoriza = dominioService.obtenerListadoDominio("tipo_doc_entrega");
    }

    public void setListTipoDocuAutoriza(List<Dominio> listTipoDocuAutoriza) {
        this.listTipoDocuAutoriza = listTipoDocuAutoriza;
    }

    public Boolean[] getValidaDatosEntrega() {
        return validaDatosEntrega;
    }

    public void setValidaDatosEntrega(Boolean[] validaDatosEntrega) {
        this.validaDatosEntrega = validaDatosEntrega;
    }

}
