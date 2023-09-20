/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snp.gob.bo.gimodel.bdimagen.entidad.ImagenPojo;
import snp.gob.bo.gimodel.bdimagen.servicio.ImagenPojoService;
import snp.gob.bo.gimodel.entidad.Historial;
import snp.gob.bo.gimodel.entidad.LogTrans;
import snp.gob.bo.gimodel.entidad.ModModificacion;
import snp.gob.bo.gimodel.entidad.ObjetoEliminadoGenerico;
import snp.gob.bo.gimodel.entidad.RenSolicitudRenovacion;
import snp.gob.bo.gimodel.entidad.SigDireccionDeNotificacion;
import snp.gob.bo.gimodel.entidad.SigPrioridadPreferencia;
import snp.gob.bo.gimodel.entidad.SigSignoClaseNiza;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.entidad.SigSolicitanteApoderado;
import snp.gob.bo.gimodel.entidad.SigTipoSigno;
import snp.gob.bo.gimodel.enums.EnumDominio;
import snp.gob.bo.gimodel.enums.EnumEstado;
import snp.gob.bo.gimodel.enums.EnumNombreDominio;
import snp.gob.bo.gimodel.enums.EnumOperacion;
import snp.gob.bo.gimodel.enums.EnumSeccion;
import snp.gob.bo.gimodel.enums.EnumTipoInteres;
import snp.gob.bo.gimodel.enums.EnumTipoPersona;
import snp.gob.bo.gimodel.mapper.SigPrioridadPreferenciaMapper;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.HistorialService;
import snp.gob.bo.gimodel.servicio.LogTransService;
import snp.gob.bo.gimodel.servicio.SigDireccionDeNotificacionService;
import snp.gob.bo.gimodel.servicio.SigHistorialService;
import snp.gob.bo.gimodel.servicio.SigPrioridadPreferenciaService;
import snp.gob.bo.gimodel.servicio.SigSignoClaseNizaService;
import snp.gob.bo.gimodel.servicio.SigSignoMarcaService;
import snp.gob.bo.gimodel.servicio.SigSolicitanteApoderadoService;
import snp.gob.bo.gimodel.servicio.SigTipoSignoService;
import snp.gob.bo.gimodel.solicitudes.entidades.Apoderados;
import snp.gob.bo.gimodel.solicitudes.entidades.DireccionNotificaciones;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI100;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI101;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI102;
import snp.gob.bo.gimodel.solicitudes.entidades.Prioridades;
import snp.gob.bo.gimodel.solicitudes.entidades.SignoMarcas;
import snp.gob.bo.gimodel.solicitudes.entidades.SmSignoClaseNizas;
import snp.gob.bo.gimodel.solicitudes.entidades.SmTipoSignos;
import snp.gob.bo.gimodel.solicitudes.entidades.Solicitantes;

/**
 *
 * @author Eddy Valero
 * @see Historial
 * @see SigHistorialServiceImpl
 * @see HistorialService
 * @see HistorialServiceImpl
 * @version 1.0, 28/11/2016
 */
@Service("sigHistorialService")
public class SigHistorialServiceImpl implements SigHistorialService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    SigSolicitanteApoderadoService sigSolicitanteApoderadoService;

    @Autowired
    SigDireccionDeNotificacionService sigDireccionDeNotificacionService;

    @Autowired
    SigPrioridadPreferenciaService sigPrioridadPreferenciaService;

    @Autowired
    SigSignoMarcaService sigSignoMarcaService;

    @Autowired
    HistorialService historialService;

    @Autowired
    DominioService dominioService;

    @Autowired
    SigTipoSignoService sigTipoSignoService;

    @Autowired
    SigSignoClaseNizaService sigSignoClaseNizaService;

    @Autowired
    ImagenPojoService imagenPojoService;

    @Autowired
    ComunService comunService;

    @Autowired
    LogTransService logTransService;

    @Override
    @Autowired
    public void setDataSource(DataSource dataSource) throws Exception {
        try {
            this.dataSource = dataSource;
            this.jdbcTemplate = new JdbcTemplate(dataSource);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional
    public void generarHistorialSigSignoMarca(Long idSignoMarca, SigSignoMarca sigSignoMarca, List<SigTipoSigno> lstTipoSignos,
            List<SigSignoClaseNiza> lstSigSignoClaseNiza,
            Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception {
        try {

            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

            //obtener el historial para signos
            String historial = "", historialCalseAdd = "";
            int sw = 0, swClase = 0;

            //preguntar si el id del objeto que viene es diferente de nulo
            if (sigSignoMarca.getIdSignoMarca() != null) {

                //Obtener SigSignoMarca de la Base de datos
                SigSignoMarca sigSignoMarcaBase = sigSignoMarcaService.obtenerSigSignoMarcaXidSignoMarca(idSignoMarca);

                //Guardar en historico, si cambió la oficina
                if (sigSignoMarca.getOficina() != null) {
                    if (sigSignoMarcaBase.getOficina() != null) {
                        if (!sigSignoMarcaBase.getOficina().equals("")) {
                            if (!sigSignoMarca.getOficina().equals(sigSignoMarcaBase.getOficina())) {
                                historial = historial + "Oficina=" + dominioService.obtenerNombreCodigoDominio("oficina", sigSignoMarcaBase.getOficina()) + "; ";
                                sw = 1;
                            }
                        }
                    }
                }

                //Guardar en historico, si cambió el numero recibo
                if (sigSignoMarca.getNumeroRecibo() != null) {
                    if (sigSignoMarcaBase.getNumeroRecibo() != null) {
                        if (!sigSignoMarca.getNumeroRecibo().equals(sigSignoMarcaBase.getNumeroRecibo())) {
                            historial = historial + "No recibo=" + sigSignoMarcaBase.getNumeroRecibo() + "; ";
                            sw = 1;
                        }
                    }
                }

                //Guardar en historico, si cambió la serie
                if (sigSignoMarca.getSerie() != null) {
                    if (sigSignoMarcaBase.getSerie() != null) {
                        if (!sigSignoMarcaBase.getSerie().equals("")) {
                            if (!sigSignoMarca.getSerie().equals(sigSignoMarcaBase.getSerie())) {
                                historial = historial + "Serie=" + sigSignoMarcaBase.getSerie() + "; ";
                                sw = 1;
                            }
                        }
                    }
                }

                //Guardar en historico, si cambió la marca
                if (sigSignoMarca.getSigno() != null) {
                    if (sigSignoMarcaBase.getSigno() != null) {
                        if (!sigSignoMarcaBase.getSigno().equals("")) {
                            if (!sigSignoMarca.getSigno().equals(sigSignoMarcaBase.getSigno())) {
                                historial = historial + "Marca=" + sigSignoMarcaBase.getSigno() + "; ";
                                sw = 1;
                            }
                        }
                    }
                }

                //preguntar por la descripcion ya que en la vista viene con un valor vacio y en la base se encuentra vacio
                if (sigSignoMarca.getDescripcionSigno() != null) {
                    if (sigSignoMarcaBase.getDescripcionSigno() != null) {
                        if (!sigSignoMarcaBase.getDescripcionSigno().equals("")) {
                            if (!sigSignoMarca.getDescripcionSigno().equals(sigSignoMarcaBase.getDescripcionSigno())) {
                                historial = historial + "Descripción=" + sigSignoMarcaBase.getDescripcionSigno() + "; ";
                                sw = 1;
                            }
                        }
                    }
                }

                //Guardar en historico, si cambió el numero titulo
                if (sigSignoMarca.getNumeroTitulo() != null) {
                    if (sigSignoMarcaBase.getNumeroTitulo() != null) {
                        if (!sigSignoMarca.getNumeroTitulo().equals(sigSignoMarcaBase.getNumeroTitulo())) {
                            historial = historial + "No Titulo=" + sigSignoMarcaBase.getNumeroTitulo() + "; ";
                            sw = 1;
                        }
                    }
                }

                //Guardar en historico, si cambió el genero
                if (sigSignoMarca.getTipoGenero() != null) {
                    if (sigSignoMarcaBase.getTipoGenero() != null) {
                        if (!sigSignoMarcaBase.getTipoGenero().equals("")) {
                            if (!sigSignoMarca.getTipoGenero().equals(sigSignoMarcaBase.getTipoGenero())) {
                                historial = historial + "Género=" + dominioService.obtenerNombreCodigoDominio(EnumNombreDominio.TIPO_GENERO.getCodigo(), sigSignoMarcaBase.getTipoGenero()) + "; ";
                                sw = 1;
                            }
                        }
                    }
                }

                //Guardar en historico, si cambió la serie titulo
                if (sigSignoMarca.getSerieTitulo() != null) {
                    if (sigSignoMarcaBase.getSerieTitulo() != null) {
                        if (!sigSignoMarcaBase.getSerieTitulo().equals("")) {
                            if (!sigSignoMarca.getSerieTitulo().equals(sigSignoMarcaBase.getSerieTitulo())) {
                                historial = historial + "Serie titulo=" + sigSignoMarcaBase.getSerieTitulo() + "; ";
                                sw = 1;
                            }
                        }
                    }
                }

                //verificar la cadena origen
                String cadenaVistaTipoSigno = "";

                //obtener el TipoSigno que se encuentra en la base de datos.
                List<SigTipoSigno> listaTipoSignoBase = sigTipoSignoService.listaSigTipoSignoXidSIgnoMarca(sigSignoMarca.getIdSignoMarca());

                int bandera, banderaOtro;
                for (SigTipoSigno sigTipoSignoBase : listaTipoSignoBase) {
                    bandera = 0;
                    banderaOtro = 0;
                    for (SigTipoSigno sigTipoSignoVista : lstTipoSignos) {
                        if (sigTipoSignoBase.getTipoSigno().equals(sigTipoSignoVista.getTipoSigno())) {
                            if (sigTipoSignoVista.getDescripcionOtro() != null) {
                                if (sigTipoSignoBase.getDescripcionOtro() != null) {
                                    if (!sigTipoSignoVista.getDescripcionOtro().equals(sigTipoSignoBase.getDescripcionOtro())) {
                                        if (!sigTipoSignoBase.getDescripcionOtro().trim().equals("")) {
                                            banderaOtro = 1;
                                            bandera = 1;
                                            break;
                                        }
                                    }
                                }
                            }
                            bandera = 1;
                            break;
                        }
                    }

                    if (bandera == 0) {
                        cadenaVistaTipoSigno = cadenaVistaTipoSigno + dominioService.obtenerNombreCodigoDominio("tipo_signo", sigTipoSignoBase.getTipoSigno()) + "; ";
                    }
                    if (banderaOtro == 1) {
                        cadenaVistaTipoSigno = cadenaVistaTipoSigno + dominioService.obtenerNombreCodigoDominio("tipo_signo", sigTipoSignoBase.getTipoSigno()) + ":" + sigTipoSignoBase.getDescripcionOtro() + "; ";
                    }
                    if (banderaOtro == 1) {
                        cadenaVistaTipoSigno = cadenaVistaTipoSigno + dominioService.obtenerNombreCodigoDominio("tipo_signo", sigTipoSignoBase.getTipoSigno()) + ":" + sigTipoSignoBase.getDescripcionOtro() + "; ";
                    }
                }

                //Guardar en historico, si cambió el tipo signo
                cadenaVistaTipoSigno = cadenaVistaTipoSigno.trim();
                if (!cadenaVistaTipoSigno.equals("")) {
                    historial = historial + "Tipo signo=" + cadenaVistaTipoSigno;
                    sw = 1;
                }

                //Guardar en historico, si cambió la fecha ingreso
                if (sigSignoMarca.getFechaIngreso() != null) {
                    if (sigSignoMarcaBase.getFechaIngreso() != null) {
                        if (!sigSignoMarca.getFechaIngreso().equals(sigSignoMarcaBase.getFechaIngreso())) {
                            historial = historial + "Fecha ingreso=" + formatoFecha.format(sigSignoMarcaBase.getFechaIngreso()) + "; ";
                            sw = 1;
                        }
                    }
                }

                //Guardar en historico, si cambió descripcion diseño
                if (sigSignoMarca.getDescripcionLogotipo() != null) {
                    if (sigSignoMarcaBase.getDescripcionLogotipo() != null) {
                        if (!sigSignoMarcaBase.getDescripcionLogotipo().equals("")) {
                            if (!sigSignoMarca.getDescripcionLogotipo().equals(sigSignoMarcaBase.getDescripcionLogotipo())) {
                                historial = historial + "Descripción diseño=" + sigSignoMarcaBase.getDescripcionLogotipo() + "; ";
                                sw = 1;
                            }
                        }
                    }
                }

                String historialClaseNiza = "";
                //obtener la Clase niza de la base de datos
                List<SigSignoClaseNiza> listaSigSignoClaseNizaBase = sigSignoClaseNizaService.obtenerListaSigSignoClazeNizaXIdSignoMarca(idSignoMarca);

                if (!listaSigSignoClaseNizaBase.isEmpty()) { // Si la lista de base de datos no esta vacia
                    if (!lstSigSignoClaseNiza.isEmpty()) { // Si la lista de la vista no esta vacia
                        // generar historial de modificación Clase Niza
                        historialClaseNiza = "MOD:";
                        for (SigSignoClaseNiza sigSignoClaseNiza : lstSigSignoClaseNiza) {
                            for (SigSignoClaseNiza sigSignoClaseNizaBase : listaSigSignoClaseNizaBase) {
                                if (sigSignoClaseNiza.getIdSignoClaseNiza().equals(sigSignoClaseNizaBase.getIdSignoClaseNiza())) {
                                    if (!Objects.equals(sigSignoClaseNiza.getNumeroClaseNiza(), sigSignoClaseNizaBase.getNumeroClaseNiza())) {
                                        historialClaseNiza = historialClaseNiza + "Clase=" + sigSignoClaseNizaBase.getNumeroClaseNiza() + "; ";
                                        swClase = 1;
                                    }
                                    if (!sigSignoClaseNiza.getListaProducto().equals(sigSignoClaseNizaBase.getListaProducto())) {

                                        if (!sigSignoClaseNiza.getListaProducto().equals(sigSignoClaseNizaBase.getListaProducto())) {
                                            historialClaseNiza = historialClaseNiza + "Productos=" + sigSignoClaseNizaBase.getListaProducto() + "; ";
                                            swClase = 1;
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                    } else {
                        // generar historial de eliminación Clase Niza
                        historialClaseNiza = "DEL:";
                        for (SigSignoClaseNiza sigSignoClaseNizaBase : listaSigSignoClaseNizaBase) {
                            if (sigSignoClaseNizaBase.getNumeroClaseNiza() != null) {
                                historialClaseNiza = historialClaseNiza + "Clase=" + sigSignoClaseNizaBase.getNumeroClaseNiza() + "; ";
                                swClase = 1;
                            }

                            if (sigSignoClaseNizaBase.getListaProducto() != null) {
                                if (!sigSignoClaseNizaBase.getListaProducto().equals("")) {
                                    historialClaseNiza = historialClaseNiza + "Productos=" + sigSignoClaseNizaBase.getListaProducto();
                                    swClase = 1;
                                }
                            }
                        }
                    }

                } else {
                    // generar historial de registro Clase Niza
                    historialClaseNiza = "ADD:";
                    for (SigSignoClaseNiza sigSignoClaseNiza : lstSigSignoClaseNiza) {
                        if (sigSignoClaseNiza.getNumeroClaseNiza() != null) {
                            historialClaseNiza = historialClaseNiza + "Clase=" + sigSignoClaseNiza.getNumeroClaseNiza() + "; ";
                            swClase = 1;
                        }

                        if (sigSignoClaseNiza.getListaProducto() != null) {
                            if (!sigSignoClaseNiza.getListaProducto().equals("")) {
                                historialClaseNiza = historialClaseNiza + "Productos=" + sigSignoClaseNiza.getListaProducto();
                                swClase = 1;
                            }
                        }
                    }
                }

                if (swClase == 1) {
                    historial = historial + historialClaseNiza;
                }

                //preguntar por la clase niza
                /*String historialEliminacionClaseNiza = "";
                 for (SigSignoClaseNiza sigSignoClaseNiza : lstSigSignoClaseNiza) {
                 //es diferente de nulo
                 if (sigSignoClaseNiza.getIdSignoClaseNiza() != null) {

                 //obtener la Clase niza de la base de datos
                 SigSignoClaseNiza sigSignoClaseNizaBase = sigSignoClaseNizaService.obtenerSigSignoClaseNiza(sigSignoClaseNiza.getIdSignoClaseNiza());
                 historialEliminacionClaseNiza = historialEliminacionClaseNiza + sigSignoClaseNizaBase.getIdSignoClaseNiza() + ",";

                 //preguntar si el objeto que viene su id es diferente de nulo
                 if (sigSignoClaseNizaBase.getIdSignoClaseNiza() != null) {

                 if (sigSignoClaseNiza.getNumeroClaseNiza() != null) {
                 if (sigSignoClaseNizaBase.getNumeroClaseNiza() != null) {
                 if (!sigSignoClaseNiza.getNumeroClaseNiza().equals(sigSignoClaseNizaBase.getNumeroClaseNiza())) {
                 historial = historial + "Clase=" + sigSignoClaseNizaBase.getNumeroClaseNiza() + "; ";
                 sw = 1;
                 }
                 }
                 }

                 if (sigSignoClaseNiza.getListaProducto() != null) {
                 if (sigSignoClaseNizaBase.getListaProducto() != null) {
                 if (!sigSignoClaseNiza.getListaProducto().equals(sigSignoClaseNizaBase.getListaProducto())) {
                 historial = historial + "Productos=" + sigSignoClaseNizaBase.getListaProducto() + "; ";
                 sw = 1;
                 }
                 }
                 }
                 }

                 } else {

                 //Agregar un nuevo elemento
                 if (sigSignoClaseNiza.getNumeroClaseNiza() != null) {
                 historialCalseAdd = historialCalseAdd + "Clase=" + sigSignoClaseNiza.getNumeroClaseNiza() + "; ";
                 swClaseAdd = 1;
                 }

                 if (sigSignoClaseNiza.getListaProducto() != null) {
                 if (!sigSignoClaseNiza.getListaProducto().equals("")) {
                 historialCalseAdd = historialCalseAdd + "Productos=" + sigSignoClaseNiza.getListaProducto();
                 swClaseAdd = 1;
                 }
                 }
                 }
                 }

                 if (swClaseAdd == 1) {
                 historialCalseAdd = "[ADD: " + historialCalseAdd + "]";
                 }

                 //buscar si no hubo eliminacion
                 //en caso que no se encuentra nada en la base se debe preguntar eso
                 try {
                 ObjetoEliminadoGenerico objetoEliminadoGenerico
                 = sigSignoClaseNizaService.obtenerRegistrosEliminadosSigSignoClaseNiza(idSignoMarca,
                 historialEliminacionClaseNiza.substring(0, historialEliminacionClaseNiza.length() - 1));

                 if (objetoEliminadoGenerico.getId() != null) {
                 //concatenar con la descripcion
                 historial = historial + "DEL: " + objetoEliminadoGenerico.getObjetoEliminado() + "; ";
                 sw = 1;
                 }

                 } catch (Exception e) {
                 //significa que no habia nada en la base
                 }*/
                //Guardar en historico, si cambió la situacion actual
                if (sigSignoMarca.getEstadoMarca() != null) {
                    if (sigSignoMarcaBase.getEstadoMarca() != null) {
                        if (!sigSignoMarcaBase.getEstadoMarca().equals("")) {
                            if (!sigSignoMarca.getEstadoMarca().equals(sigSignoMarcaBase.getEstadoMarca())) {
                                historial = historial + "Situación actual=" + dominioService.obtenerNombreCodigoDominio(EnumDominio.ESTADO_MARCA.getCodigo(), sigSignoMarcaBase.getEstadoMarca()) + "; ";
                                sw = 1;
                            }
                        }
                    }
                }

                //Guardar en historico, si cambió la ubicacion
                if (sigSignoMarca.getUbicacion() != null) {
                    if (sigSignoMarcaBase.getUbicacion() != null) {
                        if (!sigSignoMarcaBase.getUbicacion().equals("")) {
                            if (!sigSignoMarca.getUbicacion().equals(sigSignoMarcaBase.getUbicacion())) {
                                historial = historial + "Ubicación=" + dominioService.obtenerNombreCodigoDominio(EnumDominio.UBICACION.getCodigo(), sigSignoMarcaBase.getUbicacion()) + "; ";
                                sw = 1;
                            }
                        }
                    }
                }

                //Guardar en historico, si cambió el numero publicacion
                if (sigSignoMarca.getNumeroPublicacion() != null) {
                    if (sigSignoMarcaBase.getNumeroPublicacion() != null) {
                        if (sigSignoMarcaBase.getNumeroPublicacion() > 0) {
                            if (!sigSignoMarca.getNumeroPublicacion().equals(sigSignoMarcaBase.getNumeroPublicacion())) {
                                historial = historial + "No publicacion=" + sigSignoMarcaBase.getNumeroPublicacion() + "; ";
                                sw = 1;
                            }
                        }
                    }
                } else if (sigSignoMarcaBase.getNumeroPublicacion() != null) {
                    historial = historial + "No publicacion=" + sigSignoMarcaBase.getNumeroPublicacion() + "; ";
                    sw = 1;
                }

                //Guardar en historico, si cambió la Gaceta
                if (sigSignoMarca.getNumeroGaceta() != null) {
                    if (sigSignoMarcaBase.getNumeroGaceta() != null) {
                        if (sigSignoMarcaBase.getNumeroGaceta() > 0) {
                            if (!sigSignoMarca.getNumeroGaceta().equals(sigSignoMarcaBase.getNumeroGaceta())) {
                                historial = historial + "Gaceta=" + sigSignoMarcaBase.getNumeroGaceta() + "; ";
                                sw = 1;
                            }
                        }
                    }
                } else if (sigSignoMarcaBase.getNumeroGaceta() != null) {
                    historial = historial + "Gaceta=" + sigSignoMarcaBase.getNumeroGaceta() + "; ";
                    sw = 1;
                }

                //Guardar en historico, si cambió la fecha publicacion
                if (sigSignoMarca.getFechaPublicacion() != null) {
                    if (sigSignoMarcaBase.getFechaPublicacion() != null) {
                        if (!sigSignoMarca.getFechaPublicacion().equals(sigSignoMarcaBase.getFechaPublicacion())) {
                            historial = historial + "Fecha Publicación=" + formatoFecha.format(sigSignoMarcaBase.getFechaPublicacion()) + "; ";
                            sw = 1;
                        }
                    }
                } else if (sigSignoMarcaBase.getFechaPublicacion() != null) {
                    historial = historial + "Fecha Publicación=" + formatoFecha.format(sigSignoMarcaBase.getFechaPublicacion()) + "; ";
                    sw = 1;
                }

                //Guardar en historico, si cambió el numero registro
                if (sigSignoMarca.getNumeroRegistro() != null) {
                    if (sigSignoMarcaBase.getNumeroRegistro() != null) {
                        if (sigSignoMarcaBase.getNumeroRegistro() > 0l) {
                            if (!sigSignoMarca.getNumeroRegistro().equals(sigSignoMarcaBase.getNumeroRegistro())) {
                                historial = historial + "No registro=" + sigSignoMarcaBase.getNumeroRegistro() + "; ";
                                sw = 1;
                            }
                        }
                    }
                } else if (sigSignoMarcaBase.getNumeroRegistro() != null) {
                    historial = historial + "No registro=" + sigSignoMarcaBase.getNumeroRegistro() + "; ";
                    sw = 1;
                }

                //Guardar en historico, si cambió la serie registro
                if (sigSignoMarca.getSerieRegistro() != null) {
                    if (sigSignoMarcaBase.getSerieRegistro() != null) {
                        if (!sigSignoMarca.getSerieRegistro().equals(sigSignoMarcaBase.getSerieRegistro())) {
                            historial = historial + "Serie=" + sigSignoMarcaBase.getSerieRegistro() + "; ";
                            sw = 1;
                        }
                    }
                } else if (sigSignoMarcaBase.getSerieRegistro() != null) {
                    historial = historial + "Serie=" + sigSignoMarcaBase.getSerieRegistro() + "; ";
                    sw = 1;
                }

                //Guardar en historico, si cambió la resolucion registro
                if (sigSignoMarca.getResolucionRegistro() != null) {
                    if (sigSignoMarcaBase.getResolucionRegistro() != null) {
                        if (!sigSignoMarcaBase.getResolucionRegistro().equals("")) {
                            if (!sigSignoMarca.getResolucionRegistro().equals(sigSignoMarcaBase.getResolucionRegistro())) {
                                historial = historial + "Resolucion=" + sigSignoMarcaBase.getResolucionRegistro() + "; ";
                                sw = 1;
                            }
                        }
                    }
                } else if (sigSignoMarcaBase.getResolucionRegistro() != null) {
                    if (!sigSignoMarcaBase.getResolucionRegistro().equals("")) {
                        historial = historial + "Resolucion=" + sigSignoMarcaBase.getResolucionRegistro() + "; ";
                        sw = 1;
                    }
                }

                //Guardar en historico, si cambió la fecha registro
                if (sigSignoMarca.getFechaRegistro() != null) {
                    if (sigSignoMarcaBase.getFechaRegistro() != null) {
                        if (!sigSignoMarca.getFechaRegistro().equals(sigSignoMarcaBase.getFechaRegistro())) {
                            historial = historial + "Fecha registro=" + formatoFecha.format(sigSignoMarcaBase.getFechaRegistro()) + "; ";
                            sw = 1;
                        }
                    }
                } else if (sigSignoMarcaBase.getFechaRegistro() != null) {
                    historial = historial + "Fecha registro=" + formatoFecha.format(sigSignoMarcaBase.getFechaRegistro()) + "; ";
                    sw = 1;
                }

                //Guardar en historico, si cambió el numero renovacion
                if (sigSignoMarca.getNumeroRenovacion() != null) {
                    if (sigSignoMarcaBase.getNumeroRenovacion() != null) {
                        if (sigSignoMarcaBase.getNumeroRenovacion() > 0l) {
                            if (sigSignoMarca.getNumeroRenovacion().longValue() != sigSignoMarcaBase.getNumeroRenovacion().longValue()) {
                                historial = historial + "No renovación=" + sigSignoMarcaBase.getNumeroRenovacion() + "; ";
                                sw = 1;
                            }
                        }
                    }
                } else if (sigSignoMarcaBase.getNumeroRenovacion() != null) {
                    historial = historial + "No renovación=" + sigSignoMarcaBase.getNumeroRenovacion() + "; ";
                    sw = 1;
                }

                //Guardar en historico, si cambió la resolucion renovacion
                if (sigSignoMarca.getNumeroResolucionRenovacion() != null) {
                    if (sigSignoMarcaBase.getNumeroResolucionRenovacion() != null) {
                        if (sigSignoMarcaBase.getNumeroResolucionRenovacion() > 0l) {
                            if (!sigSignoMarca.getNumeroResolucionRenovacion().equals(sigSignoMarcaBase.getNumeroResolucionRenovacion())) {
                                historial = historial + "No resolución=" + sigSignoMarcaBase.getNumeroResolucionRenovacion() + "; ";
                                sw = 1;
                            }
                        }
                    }
                } else if (sigSignoMarcaBase.getNumeroResolucionRenovacion() != null) {
                    historial = historial + "No resolución=" + sigSignoMarcaBase.getNumeroResolucionRenovacion() + "; ";
                    sw = 1;
                }

                //Guardar en historico, si cambió la fecha renovacion
                if (sigSignoMarca.getFechaRenovacion() != null) {
                    if (sigSignoMarcaBase.getFechaRenovacion() != null) {
                        if (!sigSignoMarca.getFechaRenovacion().equals(sigSignoMarcaBase.getFechaRenovacion())) {
                            historial = historial + "Fecha renovación=" + formatoFecha.format(sigSignoMarcaBase.getFechaRenovacion()) + "; ";
                            sw = 1;
                        }
                    }
                } else if (sigSignoMarcaBase.getFechaRenovacion() != null) {
                    historial = historial + "Fecha renovación=" + formatoFecha.format(sigSignoMarcaBase.getFechaRenovacion()) + "; ";
                    sw = 1;
                }
            }

            //si sw=1, entonces hay al menos una modificacion
            if (sw == 1) {
                historial = historial.trim();
                historial = historial.substring(0, historial.length() - 1);
                historial = "[" + historial.trim() + "]";
            }

            historial = historial + historialCalseAdd;

            //se debe proceder a guardar el historial siempre y cuando haya existido por lo menos una modificacion
            if (!historial.equals("")) {

                //si todo esta bien guardar la observacion
                Historial historialTramite = new Historial();
                historialTramite.setId(idSignoMarca);
                historialTramite.setIdUsuario(idUsuario);
                historialTramite.setIdLogTrans(idLogTrans);
                historialTramite.setOperacion(EnumOperacion.MODIFICAR.getCodigo());

                //obtener la descripcion de la marca
                historialTramite.setEstadoMarcaDescripcion(dominioService.obtenerNombreCodigoDominio(EnumNombreDominio.ESTADO_MARCA.getCodigo(), sigSignoMarca.getEstadoMarca()));
                historialTramite.setUbicacionDescripcion(dominioService.obtenerNombreCodigoDominio(EnumNombreDominio.UBICACION.getCodigo(), sigSignoMarca.getUbicacion()));
                historialTramite.setSeccion(EnumSeccion.SIGNO.getCodigo());
                historialTramite.setDescripcion(historial);
                historialTramite.setFechaOperacion(fechaSistema);
                historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());

                historialService.crudHistorial(historialTramite, "SIG", 1);
            }

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional
    public void guardarSigHistorialSubsanacionPI100(SigSignoMarca sigSignoMarca, Date fechaSistema, String estado_marca, String ubicacion_marca,
            Long idLogTrans, Long idUsuario, FormularioPI100 formularioPI100) throws Exception {
        try {

            // guardar el historial de sigsignomarca
            generarHistorialSigSignoMarcaSubsanacionPI100(sigSignoMarca,
                    fechaSistema,
                    idLogTrans,
                    idUsuario,
                    formularioPI100);

            // guardar el historial tipo signo
            generarHistorialSigTipoSignoSubsanacion(sigSignoMarca,
                    fechaSistema,
                    idLogTrans,
                    idUsuario,
                    formularioPI100.getSmTipoSignos());

            // guardar el historial clase niza
            generarHistorialSigClaseNizaSubsanacion(sigSignoMarca,
                    fechaSistema,
                    idLogTrans,
                    idUsuario, formularioPI100.getSmSignoClaseNizases());

            // generar el historial del solicitante
            generarHistorialSigSolicitanteSubsanacion(sigSignoMarca.getIdSignoMarca(),
                    EnumTipoPersona.SOLICITANTE.getCodigo(),
                    estado_marca,
                    ubicacion_marca,
                    fechaSistema,
                    idLogTrans,
                    idUsuario, formularioPI100.getSolicitantes());

            // generar el historial del apoderado
            generarHistorialSigApoderadoSubsanacion(sigSignoMarca.getIdSignoMarca(),
                    EnumTipoPersona.APODERADO.getCodigo(),
                    estado_marca,
                    ubicacion_marca,
                    fechaSistema,
                    idLogTrans,
                    idUsuario, formularioPI100.getApoderados());

            // generar el historial de la dirección de notificación
            generarHistorialSigDireccionDeNotificacionSubsanacion(sigSignoMarca.getIdSignoMarca(),
                    estado_marca,
                    ubicacion_marca,
                    fechaSistema,
                    idLogTrans,
                    idUsuario, formularioPI100.getDirecciones());

            // guardar el historial de prioridades
            generarHistorialPrioridadesSubsanacion(sigSignoMarca.getIdSignoMarca(), formularioPI100.getPrioridades(),
                    estado_marca,
                    ubicacion_marca,
                    fechaSistema,
                    idLogTrans,
                    idUsuario);

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional
    public void guardarSigHistorialSubsanacionPI101(SigSignoMarca sigSignoMarca, Date fechaSistema, String estado_marca, String ubicacion_marca,
            Long idLogTrans, Long idUsuario, FormularioPI101 formularioPI101) throws Exception {
        try {

            // guardar el historial de sigsignomarca
            generarHistorialSigSignoMarcaSubsanacionPI101(sigSignoMarca,
                    fechaSistema,
                    idLogTrans,
                    idUsuario,
                    formularioPI101);

            // guardar el historial tipo signo
            generarHistorialSigTipoSignoSubsanacion(sigSignoMarca,
                    fechaSistema,
                    idLogTrans,
                    idUsuario,
                    formularioPI101.getSmTipoSignos());

            // guardar el historial clase niza
            generarHistorialSigClaseNizaSubsanacion(sigSignoMarca,
                    fechaSistema,
                    idLogTrans,
                    idUsuario, formularioPI101.getSmSignoClaseNizases());

            // generar el historial del solicitante
            generarHistorialSigSolicitanteSubsanacion(sigSignoMarca.getIdSignoMarca(),
                    EnumTipoPersona.SOLICITANTE.getCodigo(),
                    estado_marca,
                    ubicacion_marca,
                    fechaSistema,
                    idLogTrans,
                    idUsuario, formularioPI101.getSolicitantes());

            // generar el historial del apoderado
            generarHistorialSigApoderadoSubsanacion(sigSignoMarca.getIdSignoMarca(),
                    EnumTipoPersona.APODERADO.getCodigo(),
                    estado_marca,
                    ubicacion_marca,
                    fechaSistema,
                    idLogTrans,
                    idUsuario, formularioPI101.getApoderados());

            // generar el historial de la dirección de notificación
            generarHistorialSigDireccionDeNotificacionSubsanacion(sigSignoMarca.getIdSignoMarca(),
                    estado_marca,
                    ubicacion_marca,
                    fechaSistema,
                    idLogTrans,
                    idUsuario, formularioPI101.getDirecciones());

            // guardar el historial de prioridades
            generarHistorialPrioridadesSubsanacion(sigSignoMarca.getIdSignoMarca(), formularioPI101.getPrioridades(),
                    estado_marca,
                    ubicacion_marca,
                    fechaSistema,
                    idLogTrans,
                    idUsuario);

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional
    public void guardarSigHistorialSubsanacionPI102(SigSignoMarca sigSignoMarca, Date fechaSistema, String estado_marca, String ubicacion_marca,
            Long idLogTrans, Long idUsuario, FormularioPI102 formularioPI102) throws Exception {
        try {

            // guardar el historial de sigsignomarca
            generarHistorialSigSignoMarcaSubsanacionPI102(sigSignoMarca,
                    fechaSistema,
                    idLogTrans,
                    idUsuario,
                    formularioPI102);

            // guardar el historial tipo signo
            generarHistorialSigTipoSignoSubsanacion(sigSignoMarca,
                    fechaSistema,
                    idLogTrans,
                    idUsuario,
                    formularioPI102.getSmTipoSignos());

            // guardar el historial clase niza
            generarHistorialSigClaseNizaSubsanacion(sigSignoMarca,
                    fechaSistema,
                    idLogTrans,
                    idUsuario, formularioPI102.getSmSignoClaseNizases());

            // generar el historial del solicitante
            generarHistorialSigSolicitanteSubsanacion(sigSignoMarca.getIdSignoMarca(),
                    EnumTipoPersona.SOLICITANTE.getCodigo(),
                    estado_marca,
                    ubicacion_marca,
                    fechaSistema,
                    idLogTrans,
                    idUsuario, formularioPI102.getSolicitantes());

            // generar el historial del apoderado
            generarHistorialSigApoderadoSubsanacion(sigSignoMarca.getIdSignoMarca(),
                    EnumTipoPersona.APODERADO.getCodigo(),
                    estado_marca,
                    ubicacion_marca,
                    fechaSistema,
                    idLogTrans,
                    idUsuario, formularioPI102.getApoderados());

            // generar el historial de la dirección de notificación
            generarHistorialSigDireccionDeNotificacionSubsanacion(sigSignoMarca.getIdSignoMarca(),
                    estado_marca,
                    ubicacion_marca,
                    fechaSistema,
                    idLogTrans,
                    idUsuario, formularioPI102.getDirecciones());

            // guardar el historial de prioridades
            generarHistorialPrioridadesSubsanacion(sigSignoMarca.getIdSignoMarca(), formularioPI102.getPrioridades(),
                    estado_marca,
                    ubicacion_marca,
                    fechaSistema,
                    idLogTrans,
                    idUsuario);

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional
    public void generarHistorialSigSignoMarcaSubsanacionPI100(SigSignoMarca sigSignoMarca,
            Date fechaSistema, Long idLogTrans, Long idUsuario, FormularioPI100 formularioPI100) throws Exception {
        try {

            // obtener el historial para signos
            String historial = "";
            int sw = 0;

            // preguntar si el id del objeto que viene es diferente de nulo
            if (sigSignoMarca.getIdSignoMarca() != null) {

                // Obtener SigSignoMarca de la Base de datos
                // SigSignoMarca sigSignoMarcaBase = sigSignoMarcaService.obtenerSigSignoMarcaXidSignoMarca(idSignoMarca);
                SignoMarcas signoMarcaSipi = formularioPI100.getSignoMarca();

                // Guardar en historico, si cambió el nuemero de formulario
                if (sigSignoMarca.getNumeroFormulario() != null) {
                    if (formularioPI100.getFormularios().getNumeroFormulario() != null) {
                        if (!formularioPI100.getFormularios().getNumeroFormulario().equals("")) {
                            if (!sigSignoMarca.getNumeroFormulario().equals(formularioPI100.getFormularios().getNumeroFormulario())) {
                                historial = historial + "Nro formulario=" + sigSignoMarca.getNumeroFormulario() + "; ";
                                sw = 1;
                            }
                        }
                    }
                }

                // Guardar en historico, si cambió la marca
                if (sigSignoMarca.getSigno() != null) {
                    if (signoMarcaSipi.getMarca() != null) {
                        if (!signoMarcaSipi.getMarca().equals("")) {
                            if (!sigSignoMarca.getSigno().equals(signoMarcaSipi.getMarca())) {
                                historial = historial + "Marca=" + sigSignoMarca.getSigno() + "; ";
                                sw = 1;
                            }
                        }
                    }
                }

                // preguntar por la descripcion ya que en la vista viene con un valor vacio y en la base se encuentra vacio
                if (sigSignoMarca.getDescripcionSigno() != null) {
                    if (signoMarcaSipi.getDescripcionLogo() != null) {
                        if (!signoMarcaSipi.getDescripcionLogo().equals("")) {
                            if (!sigSignoMarca.getDescripcionSigno().equals(signoMarcaSipi.getDescripcionLogo())) {
                                historial = historial + "Descripción=" + sigSignoMarca.getDescripcionSigno() + "; ";
                                sw = 1;
                            }
                        }
                    }
                }

                // Guardar en historico, si cambió el genero
                if (sigSignoMarca.getTipoGenero() != null) {
                    if (signoMarcaSipi.getTipoGenero() != null) {
                        if (!signoMarcaSipi.getTipoGenero().equals("")) {
                            if (!sigSignoMarca.getTipoGenero().equals(signoMarcaSipi.getTipoGenero())) {
                                historial = historial + "Género=" + dominioService.obtenerNombreCodigoDominio(EnumNombreDominio.TIPO_GENERO.getCodigo(), sigSignoMarca.getTipoGenero()) + "; ";
                                sw = 1;
                            }
                        }
                    }
                }
            }

            //si sw=1, entonces hay al menos una modificacion
            if (sw == 1) {
                historial = historial.trim();
                historial = historial.substring(0, historial.length() - 1);
                historial = "[" + historial.trim() + "]";
            }

            //se debe proceder a guardar el historial siempre y cuando haya existido por lo menos una modificacion
            if (!historial.equals("")) {

                //si todo esta bien guardar la observacion
                Historial historialTramite = new Historial();
                historialTramite.setId(sigSignoMarca.getIdSignoMarca());
                historialTramite.setIdUsuario(idUsuario);
                historialTramite.setIdLogTrans(idLogTrans);
                historialTramite.setOperacion(EnumOperacion.MODIFICAR.getCodigo());
                //obtener la descripcion de la marca
                historialTramite.setEstadoMarcaDescripcion(dominioService.obtenerNombreCodigoDominio(EnumNombreDominio.ESTADO_MARCA.getCodigo(), sigSignoMarca.getEstadoMarca()));
                historialTramite.setUbicacionDescripcion(dominioService.obtenerNombreCodigoDominio(EnumNombreDominio.UBICACION.getCodigo(), sigSignoMarca.getUbicacion()));
                historialTramite.setSeccion(EnumSeccion.SIGNO.getCodigo());
                historialTramite.setDescripcion(historial);
                historialTramite.setFechaOperacion(fechaSistema);
                historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());

                historialService.crudHistorial(historialTramite, "SIG", 1);
            }

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional
    public void generarHistorialSigSignoMarcaSubsanacionPI101(SigSignoMarca sigSignoMarca,
            Date fechaSistema, Long idLogTrans, Long idUsuario, FormularioPI101 formularioPI101) throws Exception {
        try {

            // obtener el historial para signos
            String historial = "";
            int sw = 0;

            // preguntar si el id del objeto que viene es diferente de nulo
            if (sigSignoMarca.getIdSignoMarca() != null) {

                // Obtener SigSignoMarca de la Base de datos
                // SigSignoMarca sigSignoMarcaBase = sigSignoMarcaService.obtenerSigSignoMarcaXidSignoMarca(idSignoMarca);
                SignoMarcas signoMarcaSipi = formularioPI101.getSignoMarca();

                // Guardar en historico, si cambió la marca
                if (sigSignoMarca.getNumeroFormulario() != null) {
                    if (formularioPI101.getFormularios().getNumeroFormulario() != null) {
                        if (!formularioPI101.getFormularios().getNumeroFormulario().equals("")) {
                            if (!sigSignoMarca.getNumeroFormulario().equals(formularioPI101.getFormularios().getNumeroFormulario())) {
                                historial = historial + "Nro formulario=" + sigSignoMarca.getNumeroFormulario() + "; ";
                                sw = 1;
                            }
                        }
                    }
                }

                // Guardar en historico, si cambió la marca
                if (sigSignoMarca.getSigno() != null) {
                    if (signoMarcaSipi.getMarca() != null) {
                        if (!signoMarcaSipi.getMarca().equals("")) {
                            if (!sigSignoMarca.getSigno().equals(signoMarcaSipi.getMarca())) {
                                historial = historial + "Marca=" + sigSignoMarca.getSigno() + "; ";
                                sw = 1;
                            }
                        }
                    }
                }

                // preguntar por la descripcion ya que en la vista viene con un valor vacio y en la base se encuentra vacio
                if (sigSignoMarca.getDescripcionSigno() != null) {
                    if (signoMarcaSipi.getDescripcionLogo() != null) {
                        if (!signoMarcaSipi.getDescripcionLogo().equals("")) {
                            if (!sigSignoMarca.getDescripcionSigno().equals(signoMarcaSipi.getDescripcionLogo())) {
                                historial = historial + "Descripción=" + sigSignoMarca.getDescripcionSigno() + "; ";
                                sw = 1;
                            }
                        }
                    }
                }

                // Guardar en historico, si cambió el genero
                if (sigSignoMarca.getTipoGenero() != null) {
                    if (signoMarcaSipi.getTipoGenero() != null) {
                        if (!signoMarcaSipi.getTipoGenero().equals("")) {
                            if (!sigSignoMarca.getTipoGenero().equals(signoMarcaSipi.getTipoGenero())) {
                                historial = historial + "Género=" + dominioService.obtenerNombreCodigoDominio(EnumNombreDominio.TIPO_GENERO.getCodigo(), sigSignoMarca.getTipoGenero()) + "; ";
                                sw = 1;
                            }
                        }
                    }
                }
            }

            //si sw=1, entonces hay al menos una modificacion
            if (sw == 1) {
                historial = historial.trim();
                historial = historial.substring(0, historial.length() - 1);
                historial = "[" + historial.trim() + "]";
            }

            //se debe proceder a guardar el historial siempre y cuando haya existido por lo menos una modificacion
            if (!historial.equals("")) {

                //si todo esta bien guardar la observacion
                Historial historialTramite = new Historial();
                historialTramite.setId(sigSignoMarca.getIdSignoMarca());
                historialTramite.setIdUsuario(idUsuario);
                historialTramite.setIdLogTrans(idLogTrans);
                historialTramite.setOperacion(EnumOperacion.MODIFICAR.getCodigo());
                //obtener la descripcion de la marca
                historialTramite.setEstadoMarcaDescripcion(dominioService.obtenerNombreCodigoDominio(EnumNombreDominio.ESTADO_MARCA.getCodigo(), sigSignoMarca.getEstadoMarca()));
                historialTramite.setUbicacionDescripcion(dominioService.obtenerNombreCodigoDominio(EnumNombreDominio.UBICACION.getCodigo(), sigSignoMarca.getUbicacion()));
                historialTramite.setSeccion(EnumSeccion.SIGNO.getCodigo());
                historialTramite.setDescripcion(historial);
                historialTramite.setFechaOperacion(fechaSistema);
                historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());

                historialService.crudHistorial(historialTramite, "SIG", 1);
            }

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional
    public void generarHistorialSigSignoMarcaSubsanacionPI102(SigSignoMarca sigSignoMarca,
            Date fechaSistema, Long idLogTrans, Long idUsuario, FormularioPI102 formularioPI102) throws Exception {
        try {

            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

            // obtener el historial para signos
            String historial = "";
            int sw = 0;

            // preguntar si el id del objeto que viene es diferente de nulo
            if (sigSignoMarca.getIdSignoMarca() != null) {

                // Obtener SigSignoMarca de la Base de datos
                // SigSignoMarca sigSignoMarcaBase = sigSignoMarcaService.obtenerSigSignoMarcaXidSignoMarca(idSignoMarca);
                SignoMarcas signoMarcaSipi = formularioPI102.getSignoMarca();

                // Guardar en historico, si cambió la marca
                if (sigSignoMarca.getNumeroFormulario() != null) {
                    if (formularioPI102.getFormularios().getNumeroFormulario() != null) {
                        if (!formularioPI102.getFormularios().getNumeroFormulario().equals("")) {
                            if (!sigSignoMarca.getNumeroFormulario().equals(formularioPI102.getFormularios().getNumeroFormulario())) {
                                historial = historial + "Nro formulario=" + sigSignoMarca.getNumeroFormulario() + "; ";
                                sw = 1;
                            }
                        }
                    }
                }

                // Guardar en historico, si cambió la marca
                if (sigSignoMarca.getSigno() != null) {
                    if (signoMarcaSipi.getMarca() != null) {
                        if (!signoMarcaSipi.getMarca().equals("")) {
                            if (!sigSignoMarca.getSigno().equals(signoMarcaSipi.getMarca())) {
                                historial = historial + "Marca=" + sigSignoMarca.getSigno() + "; ";
                                sw = 1;
                            }
                        }
                    }
                }

                // preguntar por la descripcion ya que en la vista viene con un valor vacio y en la base se encuentra vacio
                if (sigSignoMarca.getDescripcionSigno() != null) {
                    if (signoMarcaSipi.getDescripcionLogo() != null) {
                        if (!signoMarcaSipi.getDescripcionLogo().equals("")) {
                            if (!sigSignoMarca.getDescripcionSigno().equals(signoMarcaSipi.getDescripcionLogo())) {
                                historial = historial + "Descripción=" + sigSignoMarca.getDescripcionSigno() + "; ";
                                sw = 1;
                            }
                        }
                    }
                }

                // Guardar en historico, si cambió el genero
                if (sigSignoMarca.getTipoGenero() != null) {
                    if (signoMarcaSipi.getTipoGenero() != null) {
                        if (!signoMarcaSipi.getTipoGenero().equals("")) {
                            if (!sigSignoMarca.getTipoGenero().equals(signoMarcaSipi.getTipoGenero())) {
                                historial = historial + "Género=" + dominioService.obtenerNombreCodigoDominio(EnumNombreDominio.TIPO_GENERO.getCodigo(), sigSignoMarca.getTipoGenero()) + "; ";
                                sw = 1;
                            }
                        }
                    }
                }
            }

            //si sw=1, entonces hay al menos una modificacion
            if (sw == 1) {
                historial = historial.trim();
                historial = historial.substring(0, historial.length() - 1);
                historial = "[" + historial.trim() + "]";
            }

            //se debe proceder a guardar el historial siempre y cuando haya existido por lo menos una modificacion
            if (!historial.equals("")) {

                //si todo esta bien guardar la observacion
                Historial historialTramite = new Historial();
                historialTramite.setId(sigSignoMarca.getIdSignoMarca());
                historialTramite.setIdUsuario(idUsuario);
                historialTramite.setIdLogTrans(idLogTrans);
                historialTramite.setOperacion(EnumOperacion.MODIFICAR.getCodigo());
                //obtener la descripcion de la marca
                historialTramite.setEstadoMarcaDescripcion(dominioService.obtenerNombreCodigoDominio(EnumNombreDominio.ESTADO_MARCA.getCodigo(), sigSignoMarca.getEstadoMarca()));
                historialTramite.setUbicacionDescripcion(dominioService.obtenerNombreCodigoDominio(EnumNombreDominio.UBICACION.getCodigo(), sigSignoMarca.getUbicacion()));
                historialTramite.setSeccion(EnumSeccion.SIGNO.getCodigo());
                historialTramite.setDescripcion(historial);
                historialTramite.setFechaOperacion(fechaSistema);
                historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());

                historialService.crudHistorial(historialTramite, "SIG", 1);
            }

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional
    public void generarHistorialSigTipoSignoSubsanacion(SigSignoMarca sigSignoMarca, Date fechaSistema,
            Long idLogTrans, Long idUsuario, List<SmTipoSignos> listaTipoSignosSipi) throws Exception {
        try {
            //verificar la cadena origen
            String historial = "";
            int sw = 0, swExiste;

            //obtener el Tipo Signo desde la base de datos.
            List<SigTipoSigno> listaTipoSignoHidra = sigTipoSignoService.listaSigTipoSignoXidSIgnoMarca(sigSignoMarca.getIdSignoMarca());

            if (!listaTipoSignoHidra.isEmpty()) {
                if (!listaTipoSignosSipi.isEmpty()) {
                    // modificar
                    historial = "MOD: ";
                    // si tipo signo externo no se encuentra en base, entonces adicionar
                    for (SmTipoSignos smTipoSignosSipi : listaTipoSignosSipi) {
                        swExiste = 0;
                        for (SigTipoSigno sigTipoSignoHidra : listaTipoSignoHidra) {
                            if (sigTipoSignoHidra.getTipoSigno().equals(smTipoSignosSipi.getTipoSigno())) {
                                swExiste = 1;
                                break;
                            }
                        }
                        if (swExiste == 0) {
                            if (smTipoSignosSipi.getTipoSigno().equals("OTRO")) {
                                historial = historial + dominioService.obtenerNombreCodigoDominio("tipo_signo", smTipoSignosSipi.getTipoSigno()) + ":" + smTipoSignosSipi.getDescripcionOtro() + "; ";
                                sw = 1;
                            } else {
                                historial = historial + dominioService.obtenerNombreCodigoDominio("tipo_signo", smTipoSignosSipi.getTipoSigno()) + "; ";
                                sw = 1;
                            }
                        }
                    }
                    // si tipo signo base no se encuentra en externo, entonces eliminar
                    for (SigTipoSigno sigTipoSignoBase : listaTipoSignoHidra) {
                        swExiste = 0;
                        for (SmTipoSignos smTipoSignosSipi : listaTipoSignosSipi) {
                            if (smTipoSignosSipi.getTipoSigno().equals(sigTipoSignoBase.getTipoSigno())) {
                                swExiste = 1;
                                break;
                            }
                        }
                        if (swExiste == 0) {
                            if (sigTipoSignoBase.getTipoSigno().equals("OTRO")) {
                                historial = historial + dominioService.obtenerNombreCodigoDominio("tipo_signo", sigTipoSignoBase.getTipoSigno()) + ":" + sigTipoSignoBase.getDescripcionOtro() + "; ";
                                sw = 1;
                            } else {
                                historial = historial + dominioService.obtenerNombreCodigoDominio("tipo_signo", sigTipoSignoBase.getTipoSigno()) + "; ";
                                sw = 1;
                            }
                        }
                    }

                } else {
                    // eliminar
                    historial = "DEL: ";
                    for (SigTipoSigno sigTipoSignoBase : listaTipoSignoHidra) {
                        if (!sigTipoSignoBase.getTipoSigno().equals("")) {
                            if (sigTipoSignoBase.getTipoSigno().equals("OTRO")) {
                                historial = historial + dominioService.obtenerNombreCodigoDominio("tipo_signo", sigTipoSignoBase.getTipoSigno()) + ":" + sigTipoSignoBase.getDescripcionOtro() + "; ";
                                sw = 1;
                            } else {
                                historial = historial + dominioService.obtenerNombreCodigoDominio("tipo_signo", sigTipoSignoBase.getTipoSigno()) + "; ";
                                sw = 1;
                            }
                        }
                    }
                }
            } else {
                // adicionar
                historial = "ADD: ";
                for (SmTipoSignos sigTipoSignoExt : listaTipoSignosSipi) {
                    if (!sigTipoSignoExt.getTipoSigno().equals("")) {
                        if (sigTipoSignoExt.getTipoSigno().equals("OTRO")) {
                            historial = historial + dominioService.obtenerNombreCodigoDominio("tipo_signo", sigTipoSignoExt.getTipoSigno()) + ":" + sigTipoSignoExt.getDescripcionOtro() + "; ";
                            sw = 1;
                        } else {
                            historial = historial + dominioService.obtenerNombreCodigoDominio("tipo_signo", sigTipoSignoExt.getTipoSigno()) + "; ";
                            sw = 1;
                        }
                    }
                }
            }

            //si sw=1, entonces hay al menos una modificacion
            if (sw == 1) {
                historial = historial.trim();
                historial = historial.substring(0, historial.length() - 1);
                historial = "[" + historial + "]";
            } else {
                historial = "";
            }

            //se debe proceder a guardar el historial siempre y cuando haya existido por lo menos una modificacion
            if (!historial.equals("")) {

                Historial historialTramite = new Historial();
                historialTramite.setId(sigSignoMarca.getIdSignoMarca());
                historialTramite.setIdUsuario(idUsuario);
                historialTramite.setIdLogTrans(idLogTrans);
                historialTramite.setOperacion(EnumOperacion.MODIFICAR.getCodigo());
                //obtener la descripcion de la marca
                historialTramite.setEstadoMarcaDescripcion(dominioService.obtenerNombreCodigoDominio(EnumNombreDominio.ESTADO_MARCA.getCodigo(), sigSignoMarca.getEstadoMarca()));
                historialTramite.setUbicacionDescripcion(dominioService.obtenerNombreCodigoDominio(EnumNombreDominio.UBICACION.getCodigo(), sigSignoMarca.getUbicacion()));
                historialTramite.setSeccion(EnumSeccion.SIGNO.getCodigo());
                historialTramite.setDescripcion(historial);
                historialTramite.setFechaOperacion(fechaSistema);
                historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());

                historialService.crudHistorial(historialTramite, "SIG", 1);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional
    public void generarHistorialSigClaseNizaSubsanacion(SigSignoMarca sigSignoMarca, Date fechaSistema,
            Long idLogTrans, Long idUsuario, List<SmSignoClaseNizas> listaSignoClaseNizaSipi) throws Exception {
        try {

            String historial = "";
            int sw = 0;

            // Obtenemos la Clase niza desde la base de datos
            List<SigSignoClaseNiza> listaSigSignoClaseNizaHidra = sigSignoClaseNizaService.obtenerListaSigSignoClazeNizaXIdSignoMarca(sigSignoMarca.getIdSignoMarca());

            if (!listaSigSignoClaseNizaHidra.isEmpty()) {
                if (!listaSignoClaseNizaSipi.isEmpty()) {
                    // modificar
                    historial = "MOD: ";
                    for (SigSignoClaseNiza sigSignoClaseNizaHidra : listaSigSignoClaseNizaHidra) {
                        for (SmSignoClaseNizas sigSignoClaseNizaSipi : listaSignoClaseNizaSipi) {
                            if (!sigSignoClaseNizaSipi.getNumeroClaseNiza().equals(sigSignoClaseNizaHidra.getNumeroClaseNiza())) {
                                historial = historial + "Clase=" + sigSignoClaseNizaHidra.getNumeroClaseNiza() + "; ";
                                sw = 1;
                            }
                            if (!sigSignoClaseNizaSipi.getListaProductos().trim().equals(sigSignoClaseNizaHidra.getListaProducto().trim())) {
                                historial = historial + "Productos=" + sigSignoClaseNizaHidra.getListaProducto() + "; ";
                                sw = 1;
                            }
                        }
                    }
                } else {
                    // eliminar
                    historial = "DEL: ";
                    for (SigSignoClaseNiza sigSignoClaseNizaBase : listaSigSignoClaseNizaHidra) {
                        if (sigSignoClaseNizaBase.getNumeroClaseNiza() != null) {
                            historial = historial + "Clase=" + sigSignoClaseNizaBase.getNumeroClaseNiza() + "; ";
                            sw = 1;
                        }
                        if (!sigSignoClaseNizaBase.getListaProducto().trim().equals("")) {
                            historial = historial + "Productos=" + sigSignoClaseNizaBase.getListaProducto() + "; ";
                            sw = 1;
                        }
                    }
                }
            } else {
                // adicionar
                historial = "ADD: ";
                for (SmSignoClaseNizas sigSignoClaseNizaSipi : listaSignoClaseNizaSipi) {
                    if (sigSignoClaseNizaSipi.getNumeroClaseNiza() != null) {
                        historial = historial + "Clase=" + sigSignoClaseNizaSipi.getNumeroClaseNiza() + "; ";
                        sw = 1;
                    }
                    if (!sigSignoClaseNizaSipi.getListaProductos().trim().equals("")) {
                        historial = historial + "Productos=" + sigSignoClaseNizaSipi.getListaProductos().trim() + "; ";
                        sw = 1;
                    }
                }
            }

            //si sw=1, entonces hay al menos una modificacion
            if (sw == 1) {
                historial = historial.trim();
                historial = historial.substring(0, historial.length() - 1);
                historial = "[" + historial + "]";
            } else {
                historial = "";
            }

            //se debe proceder a guardar el historial siempre y cuando haya existido por lo menos una modificacion
            if (!historial.equals("")) {

                Historial historialTramite = new Historial();
                historialTramite.setId(sigSignoMarca.getIdSignoMarca());
                historialTramite.setIdUsuario(idUsuario);
                historialTramite.setIdLogTrans(idLogTrans);
                historialTramite.setOperacion(EnumOperacion.MODIFICAR.getCodigo());
                //obtener la descripcion de la marca
                historialTramite.setEstadoMarcaDescripcion(dominioService.obtenerNombreCodigoDominio(EnumNombreDominio.ESTADO_MARCA.getCodigo(), sigSignoMarca.getEstadoMarca()));
                historialTramite.setUbicacionDescripcion(dominioService.obtenerNombreCodigoDominio(EnumNombreDominio.UBICACION.getCodigo(), sigSignoMarca.getUbicacion()));
                historialTramite.setSeccion(EnumSeccion.SIGNO.getCodigo());
                historialTramite.setDescripcion(historial);
                historialTramite.setFechaOperacion(fechaSistema);
                historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());

                historialService.crudHistorial(historialTramite, "SIG", 1);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional
    public void generarHistorialSigSolicitanteApoderado(Long idSignoMarca, List<SigSolicitanteApoderado> lstSolicitantes,
            String tipoPersona, String estado_marca, String ubicacion, Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception {
        try {

            //obtener el historial para SolicitanteApoderado
            String historial = "", historialSolApoAdd = "";
            int sw = 0, swSolApodAdd = 0;
            String historialEliminacion = "";

            //recorrer la lista que viene desde la vista
            for (SigSolicitanteApoderado sigSolicitanteApoderado : lstSolicitantes) {
                sw = 0;
                if (sigSolicitanteApoderado.getIdSolicitanteApoderado() != null) {

                    //Obtener el SolicitanteApoderado de la Base
                    SigSolicitanteApoderado sigSolicitanteApoderadoBase
                            = sigSolicitanteApoderadoService.obtenerSigSolicitanteApoderado(sigSolicitanteApoderado.getIdSolicitanteApoderado(), tipoPersona);
                    historialEliminacion = historialEliminacion + sigSolicitanteApoderadoBase.getIdSolicitanteApoderado() + ",";

                    if (sigSolicitanteApoderadoBase.getIdSolicitanteApoderado() != null) {

                        if (sigSolicitanteApoderado.getNombreRazonSocial() != null) {
                            if (sigSolicitanteApoderadoBase.getNombreRazonSocial() != null) {
                                if (!sigSolicitanteApoderadoBase.getNombreRazonSocial().equals("")) {
                                    if (!sigSolicitanteApoderado.getNombreRazonSocial().equals(sigSolicitanteApoderadoBase.getNombreRazonSocial())) {
                                        historial = historial + "Nombre=" + sigSolicitanteApoderadoBase.getNombreRazonSocial() + "; ";
                                        sw = 1;
                                    }
                                }
                            }
                        }

                        if (sigSolicitanteApoderado.getPrimerApellido() != null) {
                            if (sigSolicitanteApoderadoBase.getPrimerApellido() != null) {
                                if (!sigSolicitanteApoderadoBase.getPrimerApellido().equals("")) {
                                    if (!sigSolicitanteApoderado.getPrimerApellido().equals(sigSolicitanteApoderadoBase.getPrimerApellido())) {
                                        historial = historial + "Primer Apellido=" + sigSolicitanteApoderadoBase.getPrimerApellido() + "; ";
                                        sw = 1;
                                    }
                                }
                            }
                        }

                        if (sigSolicitanteApoderado.getSegundoApellido() != null) {
                            if (sigSolicitanteApoderadoBase.getSegundoApellido() != null) {
                                if (!sigSolicitanteApoderadoBase.getSegundoApellido().equals("")) {
                                    if (!sigSolicitanteApoderado.getSegundoApellido().equals(sigSolicitanteApoderadoBase.getSegundoApellido())) {
                                        historial = historial + "Segundo Apellido=" + sigSolicitanteApoderadoBase.getSegundoApellido() + "; ";
                                        sw = 1;
                                    }
                                }
                            }
                        }

                        if (sigSolicitanteApoderado.getNumeroDocumento() != null) {
                            if (sigSolicitanteApoderadoBase.getNumeroDocumento() != null) {
                                if (!sigSolicitanteApoderadoBase.getNumeroDocumento().equals("")) {
                                    if (!sigSolicitanteApoderado.getNumeroDocumento().equals(sigSolicitanteApoderadoBase.getNumeroDocumento())) {
                                        historial = historial + "N° Doc.=" + sigSolicitanteApoderadoBase.getNumeroDocumento() + "; ";
                                        sw = 1;
                                    }
                                }
                            }
                        }

                        if (sigSolicitanteApoderado.getTipoDocumento() != null) {
                            if (sigSolicitanteApoderadoBase.getTipoDocumento() != null) {
                                if (!sigSolicitanteApoderadoBase.getTipoDocumento().equals("")) {
                                    if (!sigSolicitanteApoderado.getTipoDocumento().equals(sigSolicitanteApoderadoBase.getTipoDocumento())) {
                                        historial = historial + "Tipo Doc.=" + dominioService.obtenerNombreCodigoDominio("tipo_documento", sigSolicitanteApoderadoBase.getTipoDocumento()) + "; ";
                                        sw = 1;
                                    }
                                }
                            }
                        }

                        if (sigSolicitanteApoderado.getGenero() != null) {
                            if (sigSolicitanteApoderadoBase.getGenero() != null) {
                                if (!sigSolicitanteApoderadoBase.getGenero().equals("")) {
                                    if (!sigSolicitanteApoderado.getGenero().equals(sigSolicitanteApoderadoBase.getGenero())) {
                                        historial = historial + "Genero=" + sigSolicitanteApoderadoBase.getGenero() + "; ";
                                        sw = 1;
                                    }
                                }
                            }
                        }

                        if (sigSolicitanteApoderado.getLugarExpedicion() != null) {
                            if (sigSolicitanteApoderadoBase.getLugarExpedicion() != null) {
                                if (!sigSolicitanteApoderadoBase.getLugarExpedicion().equals("")) {
                                    if (!sigSolicitanteApoderado.getLugarExpedicion().equals(sigSolicitanteApoderadoBase.getLugarExpedicion())) {
                                        historial = historial + "Lugar Exp.=" + dominioService.obtenerNombreCodigoDominio("lugar_expedicion", sigSolicitanteApoderadoBase.getLugarExpedicion()) + "; ";
                                        sw = 1;
                                    }
                                }
                            }
                        }

                        if (sigSolicitanteApoderado.getPais() != null) {
                            if (sigSolicitanteApoderadoBase.getPais() != null) {
                                if (!sigSolicitanteApoderadoBase.getPais().equals("")) {
                                    if (!sigSolicitanteApoderado.getPais().equals(sigSolicitanteApoderadoBase.getPais())) {
                                        historial = historial + "País=" + dominioService.obtenerNombreCodigoDominio("pais", sigSolicitanteApoderadoBase.getPais()) + "; ";
                                        sw = 1;
                                    }
                                }
                            }
                        }

                        if (sigSolicitanteApoderado.getSolicitudDepartamento() != null) {
                            if (sigSolicitanteApoderadoBase.getSolicitudDepartamento() != null) {
                                if (!sigSolicitanteApoderadoBase.getSolicitudDepartamento().equals("")) {
                                    if (!sigSolicitanteApoderado.getSolicitudDepartamento().equals(sigSolicitanteApoderadoBase.getSolicitudDepartamento())) {
                                        historial = historial + "Departamento=" + dominioService.obtenerNombreCodigoDominio("departamento", sigSolicitanteApoderadoBase.getSolicitudDepartamento()) + "; ";
                                        sw = 1;
                                    }
                                }
                            }
                        }

                        if (sigSolicitanteApoderado.getTelefono() != null) {
                            if (sigSolicitanteApoderadoBase.getTelefono() != null) {
                                if (!sigSolicitanteApoderadoBase.getTelefono().equals("")) {
                                    if (!sigSolicitanteApoderado.getTelefono().equals(sigSolicitanteApoderadoBase.getTelefono())) {
                                        historial = historial + "Teléfono=" + sigSolicitanteApoderadoBase.getTelefono() + "; ";
                                        sw = 1;
                                    }
                                }
                            }
                        }

                        if (sigSolicitanteApoderado.getCelular() != null) {
                            if (sigSolicitanteApoderadoBase.getCelular() != null) {
                                if (!sigSolicitanteApoderadoBase.getCelular().equals("")) {
                                    if (!sigSolicitanteApoderado.getCelular().equals(sigSolicitanteApoderadoBase.getCelular())) {
                                        historial = historial + "Celular=" + sigSolicitanteApoderadoBase.getCelular() + "; ";
                                        sw = 1;
                                    }
                                }
                            }
                        }

                        if (sigSolicitanteApoderado.getFax() != null) {
                            if (sigSolicitanteApoderadoBase.getFax() != null) {
                                if (!sigSolicitanteApoderadoBase.getFax().equals("")) {
                                    if (!sigSolicitanteApoderado.getFax().equals(sigSolicitanteApoderadoBase.getFax())) {
                                        historial = historial + "Fax=" + sigSolicitanteApoderadoBase.getFax() + "; ";
                                        sw = 1;
                                    }
                                }
                            }
                        }

                        if (sigSolicitanteApoderado.getDireccion() != null) {
                            if (sigSolicitanteApoderadoBase.getDireccion() != null) {
                                if (!sigSolicitanteApoderadoBase.getDireccion().equals("")) {
                                    if (!sigSolicitanteApoderado.getDireccion().equals(sigSolicitanteApoderadoBase.getDireccion())) {
                                        historial = historial + "Dirección=" + sigSolicitanteApoderadoBase.getDireccion() + "; ";
                                        sw = 1;
                                    }
                                }
                            }
                        }

                        if (sigSolicitanteApoderado.getEmail() != null) {
                            if (sigSolicitanteApoderadoBase.getEmail() != null) {
                                if (!sigSolicitanteApoderadoBase.getEmail().equals("")) {
                                    if (!sigSolicitanteApoderado.getEmail().equals(sigSolicitanteApoderadoBase.getEmail())) {
                                        historial = historial + "E-mail=" + sigSolicitanteApoderadoBase.getEmail() + "; ";
                                        sw = 1;
                                    }
                                }
                            }
                        }

                        //si sw=1, entonces hay al menos una modificacion
                        if (sw == 1) {
                            historial = historial.trim();
                            historial = historial.substring(0, historial.length() - 1);
                            historial = "[" + historial + "]";
                        }

                    }

                } else {

                    //Agregar un nuevo SolicitanteApoderado
                    if (sigSolicitanteApoderado.getNombreRazonSocial() != null) {
                        if (!sigSolicitanteApoderado.getNombreRazonSocial().equals("")) {
                            historialSolApoAdd = historialSolApoAdd + "Nombre=" + sigSolicitanteApoderado.getNombreRazonSocial() + "; ";
                            swSolApodAdd = 1;
                        }
                    }

                    if (sigSolicitanteApoderado.getPrimerApellido() != null) {
                        if (!sigSolicitanteApoderado.getPrimerApellido().equals("")) {
                            historialSolApoAdd = historialSolApoAdd + "Primer Apellido=" + sigSolicitanteApoderado.getPrimerApellido() + "; ";
                            swSolApodAdd = 1;
                        }
                    }

                    if (sigSolicitanteApoderado.getSegundoApellido() != null) {
                        if (!sigSolicitanteApoderado.getSegundoApellido().equals("")) {
                            historialSolApoAdd = historialSolApoAdd + "Segundo Apellido=" + sigSolicitanteApoderado.getSegundoApellido() + "; ";
                            swSolApodAdd = 1;
                        }
                    }

                    if (sigSolicitanteApoderado.getNumeroDocumento() != null) {
                        if (!sigSolicitanteApoderado.getNumeroDocumento().equals("")) {
                            historialSolApoAdd = historialSolApoAdd + "N° Doc.=" + sigSolicitanteApoderado.getNumeroDocumento() + "; ";
                            swSolApodAdd = 1;
                        }
                    }

                    if (sigSolicitanteApoderado.getTipoDocumento() != null) {
                        if (!sigSolicitanteApoderado.getTipoDocumento().equals("")) {
                            historialSolApoAdd = historialSolApoAdd + "Tipo Doc.=" + sigSolicitanteApoderado.getTipoDocumento() + "; ";
                            swSolApodAdd = 1;
                        }
                    }

                    if (sigSolicitanteApoderado.getGenero() != null) {
                        if (!sigSolicitanteApoderado.getGenero().equals("")) {
                            historialSolApoAdd = historialSolApoAdd + "Genero=" + sigSolicitanteApoderado.getGenero() + "; ";
                            swSolApodAdd = 1;
                        }
                    }

                    if (sigSolicitanteApoderado.getLugarExpedicion() != null) {
                        if (!sigSolicitanteApoderado.getLugarExpedicion().equals("")) {
                            historialSolApoAdd = historialSolApoAdd + "Lugar Exp.=" + dominioService.obtenerNombreCodigoDominio("lugar_expedicion", sigSolicitanteApoderado.getLugarExpedicion()) + "; ";
                            swSolApodAdd = 1;
                        }
                    }

                    if (sigSolicitanteApoderado.getPais() != null) {
                        if (!sigSolicitanteApoderado.getPais().equals("")) {
                            historialSolApoAdd = historialSolApoAdd + "País=" + dominioService.obtenerNombreCodigoDominio("pais", sigSolicitanteApoderado.getPais()) + "; ";
                            swSolApodAdd = 1;
                        }
                    }

                    if (sigSolicitanteApoderado.getSolicitudDepartamento() != null) {
                        if (!sigSolicitanteApoderado.getSolicitudDepartamento().equals("")) {
                            historialSolApoAdd = historialSolApoAdd + "Departamento=" + dominioService.obtenerNombreCodigoDominio("departamento", sigSolicitanteApoderado.getSolicitudDepartamento()) + "; ";
                            swSolApodAdd = 1;
                        }
                    }

                    if (sigSolicitanteApoderado.getTelefono() != null) {
                        if (!sigSolicitanteApoderado.getTelefono().equals("")) {
                            historialSolApoAdd = historialSolApoAdd + "Teléfono=" + sigSolicitanteApoderado.getTelefono() + "; ";
                            swSolApodAdd = 1;
                        }
                    }

                    if (sigSolicitanteApoderado.getCelular() != null) {
                        if (!sigSolicitanteApoderado.getCelular().equals("")) {
                            historialSolApoAdd = historialSolApoAdd + "Celular=" + sigSolicitanteApoderado.getCelular() + "; ";
                            swSolApodAdd = 1;
                        }
                    }

                    if (sigSolicitanteApoderado.getFax() != null) {
                        if (!sigSolicitanteApoderado.getFax().equals("")) {
                            historialSolApoAdd = historialSolApoAdd + "Fax=" + sigSolicitanteApoderado.getFax() + "; ";
                            swSolApodAdd = 1;
                        }
                    }

                    if (sigSolicitanteApoderado.getDireccion() != null) {
                        if (!sigSolicitanteApoderado.getDireccion().equals("")) {
                            historialSolApoAdd = historialSolApoAdd + "Dirección=" + sigSolicitanteApoderado.getDireccion() + "; ";
                            swSolApodAdd = 1;
                        }
                    }

                    if (sigSolicitanteApoderado.getEmail() != null) {
                        if (!sigSolicitanteApoderado.getEmail().equals("")) {
                            historialSolApoAdd = historialSolApoAdd + "E-mail=" + sigSolicitanteApoderado.getEmail() + "; ";
                            swSolApodAdd = 1;
                        }
                    }
                }
            }

            if (swSolApodAdd == 1) {
                historialSolApoAdd = historialSolApoAdd.trim();
                historialSolApoAdd = historialSolApoAdd.substring(0, historialSolApoAdd.length() - 1);
                historialSolApoAdd = "[ADD: " + historialSolApoAdd + "]";
            }

            //busca si no hubo eliminacion
            //en caso que no se encuentra nada en la base se debe preguntar eso
            if (!historialEliminacion.equals("")) {
                try {

                    ObjetoEliminadoGenerico objetoEliminadoGenerico
                            = sigSolicitanteApoderadoService.obtenerRegistrosEliminadosSigSolicitanteApoderado(idSignoMarca, historialEliminacion.substring(0, historialEliminacion.length() - 1), tipoPersona);

                    if (objetoEliminadoGenerico.getId() != null) {
                        //concatenar con la descripcion
                        historial = historial + "[DEL:" + objetoEliminadoGenerico.getObjetoEliminado() + "]";
                    }
                } catch (Exception e) {
                    //no se hace nada en este caso:  "no habia nada en la base ***"
                }
            }

            historial = historial + historialSolApoAdd;

            //se debe proceder a guardar el historial siempre y cuando haya existido por lo menos una modificacion
            if (!historial.equals("")) {

                //si todo esta bien guardar la observacion
                Historial historialTramite = new Historial();
                historialTramite.setId(idSignoMarca);
                historialTramite.setIdUsuario(idUsuario);
                historialTramite.setIdLogTrans(idLogTrans);
                historialTramite.setOperacion(EnumOperacion.MODIFICAR.getCodigo());
                //obtener la descripcion de la marca
                historialTramite.setEstadoMarcaDescripcion(estado_marca);
                historialTramite.setUbicacionDescripcion(ubicacion);
                if (tipoPersona.equals(EnumTipoPersona.SOLICITANTE.getCodigo())) {
                    historialTramite.setSeccion(EnumSeccion.SOLICITANTE.getCodigo());
                } else {
                    historialTramite.setSeccion(EnumSeccion.APODERADO.getCodigo());
                }
                historialTramite.setDescripcion(historial);
                historialTramite.setFechaOperacion(fechaSistema);
                historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());

                historialService.crudHistorial(historialTramite, "SIG", 1);
            }

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional
    public void generarHistorialSigSolicitanteSubsanacion(Long idSignoMarca, String tipoPersona, String estado_marca, String ubicacion,
            Date fechaSistema, Long idLogTrans, Long idUsuario, List<Solicitantes> listaSolicitantesSipi) throws Exception {
        try {

            String historial = "";
            int swExiste = 0;

            // Recuperamos el Solicitante desde la base de datos
            List<SigSolicitanteApoderado> listaSolicitantesHidra = sigSolicitanteApoderadoService.listadoSolicitanteXidsignomarca(idSignoMarca);

            if (!listaSolicitantesHidra.isEmpty()) {
                if (!listaSolicitantesSipi.isEmpty()) {

                    for (SigSolicitanteApoderado solicitanteHidra : listaSolicitantesHidra) {
                        swExiste = 0;
                        for (Solicitantes solicitanteSipi : listaSolicitantesSipi) {
                            if (solicitanteHidra.getIdSipi().equals(solicitanteSipi.getIdPadre())) {
                                // modificar
                                historial = historial + modificarSigSolicitanteSubsanacion(solicitanteHidra, solicitanteSipi);
                                swExiste = 1;
                                break;
                            }
                        }
                        if (swExiste == 0) {
                            // eliminar
                            historial = historial + eliminarSigSolicitanteSubsanacion(solicitanteHidra);
                        }
                    }

                    for (Solicitantes solicitanteSipi : listaSolicitantesSipi) {
                        swExiste = 0;
                        for (SigSolicitanteApoderado solicitanteHidra : listaSolicitantesHidra) {
                            if (solicitanteHidra.getIdSipi().equals(solicitanteSipi.getIdPadre())) {
                                // solo virificar que exista
                                swExiste = 1;
                                break;
                            }
                        }
                        if (swExiste == 0) {
                            // adicionar
                            historial = historial + adicionarSigSolicitanteSubsanacion(solicitanteSipi);
                        }
                    }

                } else {
                    // eliminar
                    for (SigSolicitanteApoderado solicitanteHidra : listaSolicitantesHidra) {
                        historial = historial + eliminarSigSolicitanteSubsanacion(solicitanteHidra);
                    }
                }
            } else {
                // adicionar
                for (Solicitantes solicitanteSipi : listaSolicitantesSipi) {
                    historial = historial + adicionarSigSolicitanteSubsanacion(solicitanteSipi);
                }
            }

            historial = historial.trim();

            //si historial es vacío, entonces hay al menos una modificacion
            if (!historial.equals("")) {
                historial = "[" + historial + "]";

                Historial historialTramite = new Historial();
                historialTramite.setId(idSignoMarca);
                historialTramite.setIdUsuario(idUsuario);
                historialTramite.setIdLogTrans(idLogTrans);
                historialTramite.setOperacion(EnumOperacion.MODIFICAR.getCodigo());
                historialTramite.setEstadoMarcaDescripcion(estado_marca);
                historialTramite.setUbicacionDescripcion(ubicacion);
                historialTramite.setSeccion(EnumSeccion.SOLICITANTE.getCodigo());
                historialTramite.setDescripcion(historial);
                historialTramite.setFechaOperacion(fechaSistema);
                historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());
                historialService.crudHistorial(historialTramite, "SIG", 1);
            }

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String modificarSigSolicitanteSubsanacion(SigSolicitanteApoderado solicitanteHidra, Solicitantes solicitanteSipi) throws Exception {

        String historial = "MOD: ";
        int sw = 0;

        if (solicitanteSipi.getTipoPersona() != null) {
            if (solicitanteHidra.getTipoTitular() != null) {
                if (!solicitanteHidra.getTipoTitular().equals("")) {
                    if (!solicitanteSipi.getTipoPersona().equals(solicitanteHidra.getTipoTitular())) {
                        historial = historial + "Tipo Persona=" + dominioService.obtenerNombreCodigoDominio("tipo_titular", solicitanteHidra.getTipoTitular()) + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (solicitanteSipi.getTipoDocumento() != null) {
            if (solicitanteHidra.getTipoDocumento() != null) {
                if (!solicitanteHidra.getTipoDocumento().equals("")) {
                    if (!solicitanteSipi.getTipoDocumento().equals(solicitanteHidra.getTipoDocumento())) {
                        historial = historial + "Tipo Doc.=" + dominioService.obtenerNombreCodigoDominio("tipo_documento", solicitanteHidra.getTipoDocumento()) + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (solicitanteSipi.getNombreRazonSocial() != null) {
            if (solicitanteHidra.getNombreRazonSocial() != null) {
                if (!solicitanteHidra.getNombreRazonSocial().equals("")) {
                    if (!solicitanteSipi.getNombreRazonSocial().equals(solicitanteHidra.getNombreRazonSocial())) {
                        historial = historial + "Nombre=" + solicitanteHidra.getNombreRazonSocial() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (solicitanteSipi.getPrimerApellido() != null) {
            if (solicitanteHidra.getPrimerApellido() != null) {
                if (!solicitanteHidra.getPrimerApellido().equals("")) {
                    if (!solicitanteSipi.getPrimerApellido().equals(solicitanteHidra.getPrimerApellido())) {
                        historial = historial + "Primer Apellido=" + solicitanteHidra.getPrimerApellido() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (solicitanteSipi.getSegundoApellido() != null) {
            if (solicitanteHidra.getSegundoApellido() != null) {
                if (!solicitanteHidra.getSegundoApellido().equals("")) {
                    if (!solicitanteSipi.getSegundoApellido().equals(solicitanteHidra.getSegundoApellido())) {
                        historial = historial + "Segundo Apellido=" + solicitanteHidra.getSegundoApellido() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (solicitanteSipi.getNumeroDocumento() != null) {
            if (solicitanteHidra.getNumeroDocumento() != null) {
                if (!solicitanteHidra.getNumeroDocumento().equals("")) {
                    if (!solicitanteSipi.getNumeroDocumento().equals(solicitanteHidra.getNumeroDocumento())) {
                        historial = historial + "N° Doc.=" + solicitanteHidra.getNumeroDocumento() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (solicitanteSipi.getGenero() != null) {
            if (solicitanteHidra.getGenero() != null) {
                if (!solicitanteHidra.getGenero().equals("")) {
                    if (!solicitanteSipi.getGenero().equals(solicitanteHidra.getGenero())) {
                        historial = historial + "Genero=" + solicitanteHidra.getGenero() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (solicitanteSipi.getLugarExpedicion() != null) {
            if (solicitanteHidra.getLugarExpedicion() != null) {
                if (!solicitanteHidra.getLugarExpedicion().equals("")) {
                    if (!solicitanteSipi.getLugarExpedicion().equals(solicitanteHidra.getLugarExpedicion())) {
                        historial = historial + "Lugar Exp.=" + dominioService.obtenerNombreCodigoDominio("lugar_expedicion", solicitanteHidra.getLugarExpedicion()) + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (solicitanteSipi.getPais() != null) {
            if (solicitanteHidra.getPais() != null) {
                if (!solicitanteHidra.getPais().equals("")) {
                    if (!solicitanteSipi.getPais().equals(solicitanteHidra.getPais())) {
                        historial = historial + "País=" + dominioService.obtenerNombreCodigoDominio("pais", solicitanteHidra.getPais()) + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (solicitanteSipi.getDepartamentoSolicitud() != null) {
            if (solicitanteHidra.getSolicitudDepartamento() != null) {
                if (!solicitanteHidra.getSolicitudDepartamento().equals("")) {
                    if (!solicitanteSipi.getDepartamentoSolicitud().equals(solicitanteHidra.getSolicitudDepartamento())) {
                        historial = historial + "Departamento=" + dominioService.obtenerNombreCodigoDominio("departamento", solicitanteHidra.getSolicitudDepartamento()) + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (solicitanteSipi.getTelefono() != null) {
            if (solicitanteHidra.getTelefono() != null) {
                if (!solicitanteHidra.getTelefono().equals("")) {
                    if (!solicitanteSipi.getTelefono().equals(solicitanteHidra.getTelefono())) {
                        historial = historial + "Teléfono=" + solicitanteHidra.getTelefono() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (solicitanteSipi.getCelular() != null) {
            if (solicitanteHidra.getCelular() != null) {
                if (!solicitanteHidra.getCelular().equals("")) {
                    if (!solicitanteSipi.getCelular().equals(solicitanteHidra.getCelular())) {
                        historial = historial + "Celular=" + solicitanteHidra.getCelular() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (solicitanteSipi.getDomicilio() != null) {
            if (solicitanteHidra.getDireccion() != null) {
                if (!solicitanteHidra.getDireccion().equals("")) {
                    if (!solicitanteSipi.getDomicilio().equals(solicitanteHidra.getDireccion())) {
                        historial = historial + "Dirección=" + solicitanteHidra.getDireccion() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (solicitanteSipi.getCorreoElectronico() != null) {
            if (solicitanteHidra.getEmail() != null) {
                if (!solicitanteHidra.getEmail().equals("")) {
                    if (!solicitanteSipi.getCorreoElectronico().equals(solicitanteHidra.getEmail())) {
                        historial = historial + "E-mail=" + solicitanteHidra.getEmail() + "; ";
                        sw = 1;
                    }
                }
            }
        }
        if (sw == 1) {
            return historial;
        } else {
            return "";
        }
    }

    @Override
    public String eliminarSigSolicitanteSubsanacion(SigSolicitanteApoderado solicitanteHidra) throws Exception {

        String historial = "DEL: ";
        int sw = 0;

        if (solicitanteHidra.getTipoTitular() != null) {
            if (!solicitanteHidra.getTipoTitular().equals("")) {
                historial = historial + "Tipo Persona=" + dominioService.obtenerNombreCodigoDominio("tipo_titular", solicitanteHidra.getTipoTitular()) + "; ";
                sw = 1;
            }
        }

        if (solicitanteHidra.getNombreRazonSocial() != null) {
            if (!solicitanteHidra.getNombreRazonSocial().equals("")) {
                historial = historial + "Nombre=" + solicitanteHidra.getNombreRazonSocial() + "; ";
                sw = 1;
            }
        }

        if (solicitanteHidra.getPrimerApellido() != null) {
            if (!solicitanteHidra.getPrimerApellido().equals("")) {
                historial = historial + "Primer Apellido=" + solicitanteHidra.getPrimerApellido() + "; ";
                sw = 1;
            }
        }

        if (solicitanteHidra.getSegundoApellido() != null) {
            if (!solicitanteHidra.getSegundoApellido().equals("")) {
                historial = historial + "Segundo Apellido=" + solicitanteHidra.getSegundoApellido() + "; ";
                sw = 1;
            }
        }

        if (solicitanteHidra.getNumeroDocumento() != null) {
            if (!solicitanteHidra.getNumeroDocumento().equals("")) {
                historial = historial + "N° Doc.=" + solicitanteHidra.getNumeroDocumento() + "; ";
                sw = 1;
            }
        }

        if (solicitanteHidra.getTipoDocumento() != null) {
            if (!solicitanteHidra.getTipoDocumento().equals("")) {
                historial = historial + "Tipo Doc.=" + solicitanteHidra.getTipoDocumento() + "; ";
                sw = 1;
            }
        }

        if (solicitanteHidra.getGenero() != null) {
            if (!solicitanteHidra.getGenero().equals("")) {
                historial = historial + "Genero=" + solicitanteHidra.getGenero() + "; ";
                sw = 1;
            }
        }

        if (solicitanteHidra.getLugarExpedicion() != null) {
            if (!solicitanteHidra.getLugarExpedicion().equals("")) {
                historial = historial + "Lugar Exp.=" + dominioService.obtenerNombreCodigoDominio("lugar_expedicion", solicitanteHidra.getLugarExpedicion()) + "; ";
                sw = 1;
            }
        }

        if (solicitanteHidra.getPais() != null) {
            if (!solicitanteHidra.getPais().equals("")) {
                historial = historial + "País=" + dominioService.obtenerNombreCodigoDominio("pais", solicitanteHidra.getPais()) + "; ";
                sw = 1;
            }
        }

        if (solicitanteHidra.getSolicitudDepartamento() != null) {
            if (!solicitanteHidra.getSolicitudDepartamento().equals("")) {
                historial = historial + "Departamento=" + dominioService.obtenerNombreCodigoDominio("departamento", solicitanteHidra.getSolicitudDepartamento()) + "; ";
                sw = 1;
            }
        }

        if (solicitanteHidra.getTelefono() != null) {
            if (!solicitanteHidra.getTelefono().equals("")) {
                historial = historial + "Teléfono=" + solicitanteHidra.getTelefono() + "; ";
                sw = 1;
            }
        }

        if (solicitanteHidra.getCelular() != null) {
            if (!solicitanteHidra.getCelular().equals("")) {
                historial = historial + "Celular=" + solicitanteHidra.getCelular() + "; ";
                sw = 1;
            }
        }

        if (solicitanteHidra.getFax() != null) {
            if (!solicitanteHidra.getFax().equals("")) {
                historial = historial + "Fax=" + solicitanteHidra.getFax() + "; ";
                sw = 1;
            }
        }

        if (solicitanteHidra.getDireccion() != null) {
            if (!solicitanteHidra.getDireccion().equals("")) {
                historial = historial + "Dirección=" + solicitanteHidra.getDireccion() + "; ";
                sw = 1;
            }
        }

        if (solicitanteHidra.getEmail() != null) {
            if (!solicitanteHidra.getEmail().equals("")) {
                historial = historial + "E-mail=" + solicitanteHidra.getEmail() + "; ";
                sw = 1;
            }
        }
        if (sw == 1) {
            return historial;
        } else {
            return "";
        }
    }

    @Override
    public String adicionarSigSolicitanteSubsanacion(Solicitantes solicitanteSipi) throws Exception {

        String historial = "ADD: ";
        int sw = 0;

        if (solicitanteSipi.getTipoPersona() != null) {
            if (!solicitanteSipi.getTipoPersona().equals("")) {
                historial = historial + "Tipo Persona=" + dominioService.obtenerNombreCodigoDominio("tipo_titular", solicitanteSipi.getTipoPersona()) + "; ";
                sw = 1;
            }
        }

        if (solicitanteSipi.getNombreRazonSocial() != null) {
            if (!solicitanteSipi.getNombreRazonSocial().equals("")) {
                historial = historial + "Nombre=" + solicitanteSipi.getNombreRazonSocial() + "; ";
                sw = 1;
            }
        }

        if (solicitanteSipi.getPrimerApellido() != null) {
            if (!solicitanteSipi.getPrimerApellido().equals("")) {
                historial = historial + "Primer Apellido=" + solicitanteSipi.getPrimerApellido() + "; ";
                sw = 1;
            }
        }

        if (solicitanteSipi.getSegundoApellido() != null) {
            if (!solicitanteSipi.getSegundoApellido().equals("")) {
                historial = historial + "Segundo Apellido=" + solicitanteSipi.getSegundoApellido() + "; ";
                sw = 1;
            }
        }

        if (solicitanteSipi.getNumeroDocumento() != null) {
            if (!solicitanteSipi.getNumeroDocumento().equals("")) {
                historial = historial + "N° Doc.=" + solicitanteSipi.getNumeroDocumento() + "; ";
                sw = 1;
            }
        }

        if (solicitanteSipi.getTipoDocumento() != null) {
            if (!solicitanteSipi.getTipoDocumento().equals("")) {
                historial = historial + "Tipo Doc.=" + solicitanteSipi.getTipoDocumento() + "; ";
                sw = 1;
            }
        }

        if (solicitanteSipi.getGenero() != null) {
            if (!solicitanteSipi.getGenero().equals("")) {
                historial = historial + "Genero=" + solicitanteSipi.getGenero() + "; ";
                sw = 1;
            }
        }

        if (solicitanteSipi.getLugarExpedicion() != null) {
            if (!solicitanteSipi.getLugarExpedicion().equals("")) {
                historial = historial + "Lugar Exp.=" + dominioService.obtenerNombreCodigoDominio("lugar_expedicion", solicitanteSipi.getLugarExpedicion()) + "; ";
                sw = 1;
            }
        }

        if (solicitanteSipi.getPais() != null) {
            if (!solicitanteSipi.getPais().equals("")) {
                historial = historial + "País=" + dominioService.obtenerNombreCodigoDominio("pais", solicitanteSipi.getPais()) + "; ";
                sw = 1;
            }
        }

        if (solicitanteSipi.getDepartamentoSolicitud() != null) {
            if (!solicitanteSipi.getDepartamentoSolicitud().equals("")) {
                historial = historial + "Departamento=" + dominioService.obtenerNombreCodigoDominio("departamento", solicitanteSipi.getDepartamentoSolicitud()) + "; ";
                sw = 1;
            }
        }

        if (solicitanteSipi.getTelefono() != null) {
            if (!solicitanteSipi.getTelefono().equals("")) {
                historial = historial + "Teléfono=" + solicitanteSipi.getTelefono() + "; ";
                sw = 1;
            }
        }

        if (solicitanteSipi.getCelular() != null) {
            if (!solicitanteSipi.getCelular().equals("")) {
                historial = historial + "Celular=" + solicitanteSipi.getCelular() + "; ";
                sw = 1;
            }
        }

        if (solicitanteSipi.getDomicilio() != null) {
            if (!solicitanteSipi.getDomicilio().equals("")) {
                historial = historial + "Dirección=" + solicitanteSipi.getDomicilio() + "; ";
                sw = 1;
            }
        }

        if (solicitanteSipi.getCorreoElectronico() != null) {
            if (!solicitanteSipi.getCorreoElectronico().equals("")) {
                historial = historial + "E-mail=" + solicitanteSipi.getCorreoElectronico() + "; ";
                sw = 1;
            }
        }
        if (sw == 1) {
            return historial;
        } else {
            return "";
        }
    }

    @Override
    @Transactional
    public void generarHistorialSigApoderadoSubsanacion(Long idSignoMarca, String tipoPersona, String estado_marca, String ubicacion,
            Date fechaSistema, Long idLogTrans, Long idUsuario, List<Apoderados> listaApoderadosSipi) throws Exception {
        try {

            String historial = "";
            int swExiste = 0;

            // Recuperamos el Apoderado desde la base de datos
            List<SigSolicitanteApoderado> listaApoderadosHidra = sigSolicitanteApoderadoService.listadoApoderadoXidsignomarca(idSignoMarca);

            if (!listaApoderadosHidra.isEmpty()) {
                if (!listaApoderadosSipi.isEmpty()) {

                        for (SigSolicitanteApoderado apoderadoHidra : listaApoderadosHidra) {
                            swExiste = 0;
                            for (Apoderados apoderadoSipi : listaApoderadosSipi) {
                                if (apoderadoHidra.getIdSipi().equals(apoderadoSipi.getIdPadre())) {
                                    // solo virificar que exista
                                    swExiste = 1;
                                    break;
                                }
                            }
                            if (swExiste == 0) {
                                // eliminar
                                historial = historial + eliminarSigApoderadoSubsanacion(apoderadoHidra);
                            }
                        }

                        for (Apoderados apoderadoSipi : listaApoderadosSipi) {
                            swExiste = 0;
                            for (SigSolicitanteApoderado apoderadoHidra : listaApoderadosHidra) {
                                if (apoderadoHidra.getIdSipi().equals(apoderadoSipi.getIdPadre())) {
                                    // modificar
                                    historial = historial + modificarSigApoderadoSubsanacion(apoderadoHidra, apoderadoSipi);
                                    swExiste = 1;
                                    break;
                                }
                            }
                            if (swExiste == 0) {
                                // adicionar
                                historial = historial + adicionarSigApoderadoSubsanacion(apoderadoSipi);
                            }
                        }

                } else {
                    // eliminar
                    for (SigSolicitanteApoderado apoderadoHidra : listaApoderadosHidra) {
                        historial = historial + eliminarSigApoderadoSubsanacion(apoderadoHidra);
                    }
                }

            } else {
                // adicionar
                for (Apoderados apoderadoSipi : listaApoderadosSipi) {
                    historial = historial + adicionarSigApoderadoSubsanacion(apoderadoSipi);
                }
            }

            historial = historial.trim();

            //si historial es vacío, entonces hay al menos una modificacion
            if (!historial.equals("")) {
                historial = "[" + historial + "]";

                Historial historialTramite = new Historial();
                historialTramite.setId(idSignoMarca);
                historialTramite.setIdUsuario(idUsuario);
                historialTramite.setIdLogTrans(idLogTrans);
                historialTramite.setOperacion(EnumOperacion.MODIFICAR.getCodigo());
                historialTramite.setEstadoMarcaDescripcion(estado_marca);
                historialTramite.setUbicacionDescripcion(ubicacion);
                historialTramite.setSeccion(EnumSeccion.APODERADO.getCodigo());
                historialTramite.setDescripcion(historial);
                historialTramite.setFechaOperacion(fechaSistema);
                historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());

                historialService.crudHistorial(historialTramite, "SIG", 1);
            }

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String modificarSigApoderadoSubsanacion(SigSolicitanteApoderado apoderadoHidra, Apoderados apoderadoSipi) throws Exception {

        String historial = "MOD: ";
        int sw = 0;

        if (apoderadoSipi.getNombres() != null) {
            if (apoderadoHidra.getNombreRazonSocial() != null) {
                if (!apoderadoHidra.getNombreRazonSocial().equals("")) {
                    if (!apoderadoSipi.getNombres().equals(apoderadoHidra.getNombreRazonSocial())) {
                        historial = historial + "Nombre=" + apoderadoHidra.getNombreRazonSocial() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (apoderadoSipi.getPrimerApellido() != null) {
            if (apoderadoHidra.getPrimerApellido() != null) {
                if (!apoderadoHidra.getPrimerApellido().equals("")) {
                    if (!apoderadoSipi.getPrimerApellido().equals(apoderadoHidra.getPrimerApellido())) {
                        historial = historial + "Primer Apellido=" + apoderadoHidra.getPrimerApellido() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (apoderadoSipi.getSegundoApellido() != null) {
            if (apoderadoHidra.getSegundoApellido() != null) {
                if (!apoderadoHidra.getSegundoApellido().equals("")) {
                    if (!apoderadoSipi.getSegundoApellido().equals(apoderadoHidra.getSegundoApellido())) {
                        historial = historial + "Segundo Apellido=" + apoderadoHidra.getSegundoApellido() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (apoderadoSipi.getNumeroDocumento() != null) {
            if (apoderadoHidra.getNumeroDocumento() != null) {
                if (!apoderadoHidra.getNumeroDocumento().equals("")) {
                    if (!apoderadoSipi.getNumeroDocumento().equals(apoderadoHidra.getNumeroDocumento())) {
                        historial = historial + "N° Doc.=" + apoderadoHidra.getNumeroDocumento() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (apoderadoSipi.getTipoDocumento() != null) {
            if (apoderadoHidra.getTipoDocumento() != null) {
                if (!apoderadoHidra.getTipoDocumento().equals("")) {
                    if (!apoderadoSipi.getTipoDocumento().equals(apoderadoHidra.getTipoDocumento())) {
                        historial = historial + "Tipo Doc.=" + dominioService.obtenerNombreCodigoDominio("tipo_documento", apoderadoHidra.getTipoDocumento()) + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (apoderadoSipi.getGenero() != null) {
            if (apoderadoHidra.getGenero() != null) {
                if (!apoderadoHidra.getGenero().equals("")) {
                    if (!apoderadoSipi.getGenero().equals(apoderadoHidra.getGenero())) {
                        historial = historial + "Genero=" + apoderadoHidra.getGenero() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (apoderadoSipi.getLugarExpedicion() != null) {
            if (apoderadoHidra.getLugarExpedicion() != null) {
                if (!apoderadoHidra.getLugarExpedicion().equals("")) {
                    if (!apoderadoSipi.getLugarExpedicion().equals(apoderadoHidra.getLugarExpedicion())) {
                        historial = historial + "Lugar Exp.=" + dominioService.obtenerNombreCodigoDominio("lugar_expedicion", apoderadoHidra.getLugarExpedicion()) + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (apoderadoSipi.getTelefono() != null) {
            if (apoderadoHidra.getTelefono() != null) {
                if (!apoderadoHidra.getTelefono().equals("")) {
                    if (!apoderadoSipi.getTelefono().equals(apoderadoHidra.getTelefono())) {
                        historial = historial + "Teléfono=" + apoderadoHidra.getTelefono() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (apoderadoSipi.getCelular() != null) {
            if (apoderadoHidra.getCelular() != null) {
                if (!apoderadoHidra.getCelular().equals("")) {
                    if (!apoderadoSipi.getCelular().equals(apoderadoHidra.getCelular())) {
                        historial = historial + "Celular=" + apoderadoHidra.getCelular() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (apoderadoSipi.getNumeroPoder() != null) {
            if (apoderadoHidra.getPoder() != null) {
                if (!apoderadoHidra.getPoder().equals("")) {
                    if (!apoderadoSipi.getNumeroPoder().equals(apoderadoHidra.getPoder())) {
                        historial = historial + "Poder=" + apoderadoHidra.getPoder() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (apoderadoSipi.getDomicilio() != null) {
            if (apoderadoHidra.getDireccion() != null) {
                if (!apoderadoHidra.getDireccion().equals("")) {
                    if (!apoderadoSipi.getDomicilio().equals(apoderadoHidra.getDireccion())) {
                        historial = historial + "Dirección=" + apoderadoHidra.getDireccion() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (apoderadoSipi.getCorreoElectronico() != null) {
            if (apoderadoHidra.getEmail() != null) {
                if (!apoderadoHidra.getEmail().equals("")) {
                    if (!apoderadoSipi.getCorreoElectronico().equals(apoderadoHidra.getEmail())) {
                        historial = historial + "E-mail=" + apoderadoHidra.getEmail() + "; ";
                        sw = 1;
                    }
                }
            }
        }
        if (sw == 1) {
            return historial;
        } else {
            return "";
        }
    }

    @Override
    public String eliminarSigApoderadoSubsanacion(SigSolicitanteApoderado apoderadoHidra) throws Exception {

        String historial = "DEL: ";
        int sw = 0;

        if (apoderadoHidra.getNombreRazonSocial() != null) {
            if (!apoderadoHidra.getNombreRazonSocial().equals("")) {
                historial = historial + "Nombre=" + apoderadoHidra.getNombreRazonSocial() + "; ";
                sw = 1;
            }
        }

        if (apoderadoHidra.getPrimerApellido() != null) {
            if (!apoderadoHidra.getPrimerApellido().equals("")) {
                historial = historial + "Primer Apellido=" + apoderadoHidra.getPrimerApellido() + "; ";
                sw = 1;
            }
        }

        if (apoderadoHidra.getSegundoApellido() != null) {
            if (!apoderadoHidra.getSegundoApellido().equals("")) {
                historial = historial + "Segundo Apellido=" + apoderadoHidra.getSegundoApellido() + "; ";
                sw = 1;
            }
        }

        if (apoderadoHidra.getNumeroDocumento() != null) {
            if (!apoderadoHidra.getNumeroDocumento().equals("")) {
                historial = historial + "N° Doc.=" + apoderadoHidra.getNumeroDocumento() + "; ";
                sw = 1;
            }
        }

        if (apoderadoHidra.getTipoDocumento() != null) {
            if (!apoderadoHidra.getTipoDocumento().equals("")) {
                historial = historial + "Tipo Doc.=" + apoderadoHidra.getTipoDocumento() + "; ";
                sw = 1;
            }
        }

        if (apoderadoHidra.getGenero() != null) {
            if (!apoderadoHidra.getGenero().equals("")) {
                historial = historial + "Genero=" + apoderadoHidra.getGenero() + "; ";
                sw = 1;
            }
        }

        if (apoderadoHidra.getLugarExpedicion() != null) {
            if (!apoderadoHidra.getLugarExpedicion().equals("")) {
                historial = historial + "Lugar Exp.=" + dominioService.obtenerNombreCodigoDominio("lugar_expedicion", apoderadoHidra.getLugarExpedicion()) + "; ";
                sw = 1;
            }
        }

        if (apoderadoHidra.getTelefono() != null) {
            if (!apoderadoHidra.getTelefono().equals("")) {
                historial = historial + "Teléfono=" + apoderadoHidra.getTelefono() + "; ";
                sw = 1;
            }
        }

        if (apoderadoHidra.getCelular() != null) {
            if (!apoderadoHidra.getCelular().equals("")) {
                historial = historial + "Celular=" + apoderadoHidra.getCelular() + "; ";
                sw = 1;
            }
        }

        if (apoderadoHidra.getPoder() != null) {
            if (!apoderadoHidra.getPoder().equals("")) {
                historial = historial + "Poder=" + apoderadoHidra.getPoder() + "; ";
                sw = 1;
            }
        }

        if (apoderadoHidra.getDireccion() != null) {
            if (!apoderadoHidra.getDireccion().equals("")) {
                historial = historial + "Dirección=" + apoderadoHidra.getDireccion() + "; ";
                sw = 1;
            }
        }

        if (apoderadoHidra.getEmail() != null) {
            if (!apoderadoHidra.getEmail().equals("")) {
                historial = historial + "E-mail=" + apoderadoHidra.getEmail() + "; ";
                sw = 1;
            }
        }
        if (sw == 1) {
            return historial;
        } else {
            return "";
        }
    }

    @Override
    public String adicionarSigApoderadoSubsanacion(Apoderados apoderadoSipi) throws Exception {

        String historial = "ADD: ";
        int sw = 0;

        if (apoderadoSipi.getNombres() != null) {
            if (!apoderadoSipi.getNombres().equals("")) {
                historial = historial + "Nombre=" + apoderadoSipi.getNombres() + "; ";
                sw = 1;
            }
        }

        if (apoderadoSipi.getPrimerApellido() != null) {
            if (!apoderadoSipi.getPrimerApellido().equals("")) {
                historial = historial + "Primer Apellido=" + apoderadoSipi.getPrimerApellido() + "; ";
                sw = 1;
            }
        }

        if (apoderadoSipi.getSegundoApellido() != null) {
            if (!apoderadoSipi.getSegundoApellido().equals("")) {
                historial = historial + "Segundo Apellido=" + apoderadoSipi.getSegundoApellido() + "; ";
                sw = 1;
            }
        }

        if (apoderadoSipi.getNumeroDocumento() != null) {
            if (!apoderadoSipi.getNumeroDocumento().equals("")) {
                historial = historial + "N° Doc.=" + apoderadoSipi.getNumeroDocumento() + "; ";
                sw = 1;
            }
        }

        if (apoderadoSipi.getTipoDocumento() != null) {
            if (!apoderadoSipi.getTipoDocumento().equals("")) {
                historial = historial + "Tipo Doc.=" + apoderadoSipi.getTipoDocumento() + "; ";
                sw = 1;
            }
        }

        if (apoderadoSipi.getGenero() != null) {
            if (!apoderadoSipi.getGenero().equals("")) {
                historial = historial + "Genero=" + apoderadoSipi.getGenero() + "; ";
                sw = 1;
            }
        }

        if (apoderadoSipi.getLugarExpedicion() != null) {
            if (!apoderadoSipi.getLugarExpedicion().equals("")) {
                historial = historial + "Lugar Exp.=" + dominioService.obtenerNombreCodigoDominio("lugar_expedicion", apoderadoSipi.getLugarExpedicion()) + "; ";
                sw = 1;
            }
        }

        if (apoderadoSipi.getTelefono() != null) {
            if (!apoderadoSipi.getTelefono().equals("")) {
                historial = historial + "Teléfono=" + apoderadoSipi.getTelefono() + "; ";
                sw = 1;
            }
        }

        if (apoderadoSipi.getCelular() != null) {
            if (!apoderadoSipi.getCelular().equals("")) {
                historial = historial + "Celular=" + apoderadoSipi.getCelular() + "; ";
                sw = 1;
            }
        }

        if (apoderadoSipi.getNumeroPoder() != null) {
            if (!apoderadoSipi.getNumeroPoder().equals("")) {
                historial = historial + "Poder=" + apoderadoSipi.getNumeroPoder() + "; ";
                sw = 1;
            }
        }

        if (apoderadoSipi.getDomicilio() != null) {
            if (!apoderadoSipi.getDomicilio().equals("")) {
                historial = historial + "Dirección=" + apoderadoSipi.getDomicilio() + "; ";
                sw = 1;
            }
        }

        if (apoderadoSipi.getCorreoElectronico() != null) {
            if (!apoderadoSipi.getCorreoElectronico().equals("")) {
                historial = historial + "E-mail=" + apoderadoSipi.getCorreoElectronico() + "; ";
                sw = 1;
            }
        }
        if (sw == 1) {
            return historial;
        } else {
            return "";
        }
    }

    @Override
    @Transactional
    public void generarHistorialSigDireccionDeNotificacion(Long idSignoMarca, SigDireccionDeNotificacion sigDireccionDeNotificacion,
            String estado_marca, String ubicacion, Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception {
        try {

            //obtener el historial para la direccion de notificacion
            String historial = "";
            int sw = 0;

            //preguntar si el id del objeto que viene es diferente de nulo
            if (sigDireccionDeNotificacion.getIdDireccionDeNotificacion() != null) {

                //Obtener la direccion de notificacion de la Base
                SigDireccionDeNotificacion sigDireccionDeNotificacionBase = sigDireccionDeNotificacionService.obtenerDireccionNotificacionXSignoMarca(idSignoMarca);

                //Preguntar si la direccion obtenida es distinta de null
                if (sigDireccionDeNotificacionBase.getIdDireccionDeNotificacion() != null) {

                    //comenzar a preguntar por todos sus campos o atributos de direccion de notificacion
                    if (sigDireccionDeNotificacion.getCiudadNotificacion() != null) {
                        if (sigDireccionDeNotificacionBase.getCiudadNotificacion() != null) {
                            if (!sigDireccionDeNotificacionBase.getCiudadNotificacion().equals("")) {
                                if (!sigDireccionDeNotificacion.getCiudadNotificacion().equals(sigDireccionDeNotificacionBase.getCiudadNotificacion())) {
                                    historial = historial + "Ciudad=" + dominioService.obtenerNombreCodigoDominio("oficina", sigDireccionDeNotificacionBase.getCiudadNotificacion()) + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    if (sigDireccionDeNotificacion.getZonaBarrio() != null) {
                        if (sigDireccionDeNotificacionBase.getZonaBarrio() != null) {
                            if (!sigDireccionDeNotificacionBase.getZonaBarrio().equals("")) {
                                if (!sigDireccionDeNotificacion.getZonaBarrio().equals(sigDireccionDeNotificacionBase.getZonaBarrio())) {
                                    historial = historial + "Barrio/Zona=" + sigDireccionDeNotificacionBase.getZonaBarrio() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    if (sigDireccionDeNotificacion.getAvenidaCalle() != null) {
                        if (sigDireccionDeNotificacionBase.getAvenidaCalle() != null) {
                            if (!sigDireccionDeNotificacionBase.getAvenidaCalle().equals("")) {
                                if (!sigDireccionDeNotificacion.getAvenidaCalle().equals(sigDireccionDeNotificacionBase.getAvenidaCalle())) {
                                    historial = historial + "Av/Calle=" + sigDireccionDeNotificacionBase.getAvenidaCalle() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    if (sigDireccionDeNotificacion.getNumero() != null) {
                        if (sigDireccionDeNotificacionBase.getNumero() != null) {
                            if (!sigDireccionDeNotificacionBase.getNumero().equals("")) {
                                if (!sigDireccionDeNotificacion.getNumero().equals(sigDireccionDeNotificacionBase.getNumero())) {
                                    historial = historial + "N°=" + sigDireccionDeNotificacionBase.getNumero() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    if (sigDireccionDeNotificacion.getEdificio() != null) {
                        if (sigDireccionDeNotificacionBase.getEdificio() != null) {
                            if (!sigDireccionDeNotificacionBase.getEdificio().equals("")) {
                                if (!sigDireccionDeNotificacion.getEdificio().equals(sigDireccionDeNotificacionBase.getEdificio())) {
                                    historial = historial + "Edificio=" + sigDireccionDeNotificacionBase.getEdificio() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    if (sigDireccionDeNotificacion.getPiso() != null) {
                        if (sigDireccionDeNotificacionBase.getPiso() != null) {
                            if (!sigDireccionDeNotificacionBase.getPiso().equals("")) {
                                if (!sigDireccionDeNotificacion.getPiso().equals(sigDireccionDeNotificacionBase.getPiso())) {
                                    historial = historial + "Piso=" + sigDireccionDeNotificacionBase.getPiso() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    if (sigDireccionDeNotificacion.getDepartamento() != null) {
                        if (sigDireccionDeNotificacionBase.getDepartamento() != null) {
                            if (!sigDireccionDeNotificacionBase.getDepartamento().equals("")) {
                                if (!sigDireccionDeNotificacion.getDepartamento().equals(sigDireccionDeNotificacionBase.getDepartamento())) {
                                    historial = historial + "Dpto=" + sigDireccionDeNotificacionBase.getDepartamento() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    if (sigDireccionDeNotificacion.getCelular() != null) {
                        if (sigDireccionDeNotificacionBase.getCelular() != null) {
                            if (!sigDireccionDeNotificacionBase.getCelular().equals("")) {
                                if (!sigDireccionDeNotificacion.getCelular().equals(sigDireccionDeNotificacionBase.getCelular())) {
                                    historial = historial + "Celular=" + sigDireccionDeNotificacionBase.getCelular() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    if (sigDireccionDeNotificacion.getTelefono() != null) {
                        if (sigDireccionDeNotificacionBase.getTelefono() != null) {
                            if (!sigDireccionDeNotificacionBase.getTelefono().equals("")) {
                                if (!sigDireccionDeNotificacion.getTelefono().equals(sigDireccionDeNotificacionBase.getTelefono())) {
                                    historial = historial + "Teléfono=" + sigDireccionDeNotificacionBase.getTelefono() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    if (sigDireccionDeNotificacion.getCorreoElectronico() != null) {
                        if (sigDireccionDeNotificacionBase.getCorreoElectronico() != null) {
                            if (!sigDireccionDeNotificacionBase.getCorreoElectronico().equals("")) {
                                if (!sigDireccionDeNotificacion.getCorreoElectronico().equals(sigDireccionDeNotificacionBase.getCorreoElectronico())) {
                                    historial = historial + "E-mail=" + sigDireccionDeNotificacionBase.getCorreoElectronico() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    if (sigDireccionDeNotificacion.getReferenciaDireccion() != null) {
                        if (sigDireccionDeNotificacionBase.getReferenciaDireccion() != null) {
                            if (!sigDireccionDeNotificacionBase.getReferenciaDireccion().equals("")) {
                                if (!sigDireccionDeNotificacion.getReferenciaDireccion().equals(sigDireccionDeNotificacionBase.getReferenciaDireccion())) {
                                    historial = historial + "Referencia=" + sigDireccionDeNotificacionBase.getReferenciaDireccion() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    //si sw=1, entonces hay al menos una modificacion
                    if (sw == 1) {
                        historial = historial.trim();
                        historial = historial.substring(0, historial.length() - 1);
                        historial = "[" + historial + "]";
                    }
                }

            } else {
                //Agregar un nuevo elemento de sigdirecciondenotificacion

                if (sigDireccionDeNotificacion.getCiudadNotificacion() != null) {
                    if (!sigDireccionDeNotificacion.getCiudadNotificacion().equals("")) {
                        historial = historial + "Ciudad=" + sigDireccionDeNotificacion.getCiudadNotificacion();
                    }
                }

                if (sigDireccionDeNotificacion.getZonaBarrio() != null) {
                    if (!sigDireccionDeNotificacion.getZonaBarrio().equals("")) {
                        historial = historial + "Barrio/Zona=" + sigDireccionDeNotificacion.getZonaBarrio();
                    }
                }

                if (sigDireccionDeNotificacion.getAvenidaCalle() != null) {
                    if (!sigDireccionDeNotificacion.getAvenidaCalle().equals("")) {
                        historial = historial + "Av/Calle=" + sigDireccionDeNotificacion.getAvenidaCalle();
                    }
                }

                if (sigDireccionDeNotificacion.getNumero() != null) {
                    if (!sigDireccionDeNotificacion.getNumero().equals("")) {
                        historial = historial + "N°=" + sigDireccionDeNotificacion.getNumero();
                    }
                }

                if (sigDireccionDeNotificacion.getEdificio() != null) {
                    if (!sigDireccionDeNotificacion.getEdificio().equals("")) {
                        historial = historial + "Edificio=" + sigDireccionDeNotificacion.getEdificio();
                    }
                }

                if (sigDireccionDeNotificacion.getPiso() != null) {
                    if (!sigDireccionDeNotificacion.getPiso().equals("")) {
                        historial = historial + "Piso=" + sigDireccionDeNotificacion.getPiso();
                    }
                }

                if (sigDireccionDeNotificacion.getDepartamento() != null) {
                    if (!sigDireccionDeNotificacion.getDepartamento().equals("")) {
                        historial = historial + "Dpto=" + sigDireccionDeNotificacion.getDepartamento();
                    }
                }

                if (sigDireccionDeNotificacion.getCelular() != null) {
                    if (!sigDireccionDeNotificacion.getCelular().equals("")) {
                        historial = historial + "Celular=" + sigDireccionDeNotificacion.getCelular();
                    }
                }

                if (sigDireccionDeNotificacion.getTelefono() != null) {
                    if (!sigDireccionDeNotificacion.getTelefono().equals("")) {
                        historial = historial + "Teléfono=" + sigDireccionDeNotificacion.getTelefono();
                    }
                }

                if (sigDireccionDeNotificacion.getCorreoElectronico() != null) {
                    if (!sigDireccionDeNotificacion.getCorreoElectronico().equals("")) {
                        historial = historial + "E-mail=" + sigDireccionDeNotificacion.getCorreoElectronico();
                    }
                }

                if (sigDireccionDeNotificacion.getReferenciaDireccion() != null) {
                    if (!sigDireccionDeNotificacion.getReferenciaDireccion().equals("")) {
                        historial = historial + "Referencia=" + sigDireccionDeNotificacion.getReferenciaDireccion();
                    }
                }
                historial = historial.trim();
                historial = historial.substring(0, historial.length() - 1);
                historial = "[ADD: " + historial + "]";
            }

            //se debe proceder a guardar el historial siempre y cuando haya existido por lo menos una modificacion
            if (!historial.equals("")) {

                //si todo esta bien guardar la observacion
                Historial historialTramite = new Historial();
                historialTramite.setId(idSignoMarca);
                historialTramite.setIdUsuario(idUsuario);
                historialTramite.setIdLogTrans(idLogTrans);
                historialTramite.setOperacion(EnumOperacion.MODIFICAR.getCodigo());

                //obtener la descripcion de la marca
                historialTramite.setEstadoMarcaDescripcion(estado_marca);
                historialTramite.setUbicacionDescripcion(ubicacion);
                historialTramite.setSeccion(EnumSeccion.DIRECCION_DE_NOTIFICACION.getCodigo());

                historialTramite.setDescripcion(historial);
                historialTramite.setFechaOperacion(fechaSistema);
                historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());

                historialService.crudHistorial(historialTramite, "SIG", 1);
            }

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional
    public void generarHistorialSigDireccionDeNotificacionSubsanacion(Long idSignoMarca, String estado_marca, String ubicacion,
            Date fechaSistema, Long idLogTrans, Long idUsuario, List<DireccionNotificaciones> direccionNotificacionSipi) throws Exception {
        try {

            // obtener el historial para la direccion de notificacion
            String historial = "";
            int sw = 0;

            // Obtener la direccion de notificacion de la Base
            SigDireccionDeNotificacion direccionNotificacionHidra = sigDireccionDeNotificacionService.obtenerDireccionNotificacionXSignoMarca(idSignoMarca);

            // Preguntar si la direccion obtenida de Base es distinta de null
            if (direccionNotificacionHidra.getIdDireccionDeNotificacion() != null) {
                // Preguntar si la direccion obtenida de Externo es distinta de null
                if (!direccionNotificacionSipi.isEmpty()) {

                    //comenzar a preguntar por todos sus campos o atributos de direccion de notificacion
                    if (direccionNotificacionSipi.get(0).getCiudadNotificacion() != null) {
                        if (direccionNotificacionHidra.getCiudadNotificacion() != null) {
                            if (!direccionNotificacionHidra.getCiudadNotificacion().equals("")) {
                                if (!direccionNotificacionSipi.get(0).getCiudadNotificacion().equals(direccionNotificacionHidra.getCiudadNotificacion())) {
                                    historial = historial + "Ciudad=" + dominioService.obtenerNombreCodigoDominio("oficina", direccionNotificacionHidra.getCiudadNotificacion()) + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    if (direccionNotificacionSipi.get(0).getZonaBarrio() != null) {
                        if (direccionNotificacionHidra.getZonaBarrio() != null) {
                            if (!direccionNotificacionHidra.getZonaBarrio().equals("")) {
                                if (!direccionNotificacionSipi.get(0).getZonaBarrio().equals(direccionNotificacionHidra.getZonaBarrio())) {
                                    historial = historial + "Barrio/Zona=" + direccionNotificacionHidra.getZonaBarrio() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    if (direccionNotificacionSipi.get(0).getAvenidaCalle() != null) {
                        if (direccionNotificacionHidra.getAvenidaCalle() != null) {
                            if (!direccionNotificacionHidra.getAvenidaCalle().equals("")) {
                                if (!direccionNotificacionSipi.get(0).getAvenidaCalle().equals(direccionNotificacionHidra.getAvenidaCalle())) {
                                    historial = historial + "Av/Calle=" + direccionNotificacionHidra.getAvenidaCalle() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    if (direccionNotificacionSipi.get(0).getNumeroDomicilio() != null) {
                        if (direccionNotificacionHidra.getNumero() != null) {
                            if (!direccionNotificacionHidra.getNumero().equals("")) {
                                if (!direccionNotificacionSipi.get(0).getNumeroDomicilio().equals(direccionNotificacionHidra.getNumero())) {
                                    historial = historial + "N°=" + direccionNotificacionHidra.getNumero() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    if (direccionNotificacionSipi.get(0).getNombreEdificio() != null) {
                        if (direccionNotificacionHidra.getEdificio() != null) {
                            if (!direccionNotificacionHidra.getEdificio().equals("")) {
                                if (!direccionNotificacionSipi.get(0).getNombreEdificio().equals(direccionNotificacionHidra.getEdificio())) {
                                    historial = historial + "Edificio=" + direccionNotificacionHidra.getEdificio() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    if (direccionNotificacionSipi.get(0).getPiso() != null) {
                        if (direccionNotificacionHidra.getPiso() != null) {
                            if (!direccionNotificacionHidra.getPiso().equals("")) {
                                if (!direccionNotificacionSipi.get(0).getPiso().equals(direccionNotificacionHidra.getPiso())) {
                                    historial = historial + "Piso=" + direccionNotificacionHidra.getPiso() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    if (direccionNotificacionSipi.get(0).getDepartamento() != null) {
                        if (direccionNotificacionHidra.getDepartamento() != null) {
                            if (!direccionNotificacionHidra.getDepartamento().equals("")) {
                                if (!direccionNotificacionSipi.get(0).getDepartamento().equals(direccionNotificacionHidra.getDepartamento())) {
                                    historial = historial + "Dpto=" + direccionNotificacionHidra.getDepartamento() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    if (direccionNotificacionSipi.get(0).getCelular() != null) {
                        if (direccionNotificacionHidra.getCelular() != null) {
                            if (!direccionNotificacionHidra.getCelular().equals("")) {
                                if (!direccionNotificacionSipi.get(0).getCelular().equals(direccionNotificacionHidra.getCelular())) {
                                    historial = historial + "Celular=" + direccionNotificacionHidra.getCelular() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    if (direccionNotificacionSipi.get(0).getTelefono() != null) {
                        if (direccionNotificacionHidra.getTelefono() != null) {
                            if (!direccionNotificacionHidra.getTelefono().equals("")) {
                                if (!direccionNotificacionSipi.get(0).getTelefono().equals(direccionNotificacionHidra.getTelefono())) {
                                    historial = historial + "Teléfono=" + direccionNotificacionHidra.getTelefono() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    if (direccionNotificacionSipi.get(0).getCorreoelectronico() != null) {
                        if (direccionNotificacionHidra.getCorreoElectronico() != null) {
                            if (!direccionNotificacionHidra.getCorreoElectronico().equals("")) {
                                if (!direccionNotificacionSipi.get(0).getCorreoelectronico().equals(direccionNotificacionHidra.getCorreoElectronico())) {
                                    historial = historial + "E-mail=" + direccionNotificacionHidra.getCorreoElectronico() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    if (direccionNotificacionSipi.get(0).getReferencia() != null) {
                        if (direccionNotificacionHidra.getReferenciaDireccion() != null) {
                            if (!direccionNotificacionHidra.getReferenciaDireccion().equals("")) {
                                if (!direccionNotificacionSipi.get(0).getReferencia().equals(direccionNotificacionHidra.getReferenciaDireccion())) {
                                    historial = historial + "Referencia=" + direccionNotificacionHidra.getReferenciaDireccion() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    //si sw=1, entonces hay al menos una modificacion
                    if (sw == 1) {
                        historial = historial.trim();
                        historial = historial.substring(0, historial.length() - 1);
                        historial = "[MOD: " + historial + "]";
                    }
                }

            } else {
                //Agregar un nuevo elemento de sigdirecciondenotificacion

                if (direccionNotificacionSipi.get(0).getCiudadNotificacion() != null) {
                    if (!direccionNotificacionSipi.get(0).getCiudadNotificacion().equals("")) {
                        historial = historial + "Ciudad=" + direccionNotificacionSipi.get(0).getCiudadNotificacion();
                    }
                }

                if (direccionNotificacionSipi.get(0).getZonaBarrio() != null) {
                    if (!direccionNotificacionSipi.get(0).getZonaBarrio().equals("")) {
                        historial = historial + "Barrio/Zona=" + direccionNotificacionSipi.get(0).getZonaBarrio();
                    }
                }

                if (direccionNotificacionSipi.get(0).getAvenidaCalle() != null) {
                    if (!direccionNotificacionSipi.get(0).getAvenidaCalle().equals("")) {
                        historial = historial + "Av/Calle=" + direccionNotificacionSipi.get(0).getAvenidaCalle();
                    }
                }

                if (direccionNotificacionSipi.get(0).getNumeroDomicilio() != null) {
                    if (!direccionNotificacionSipi.get(0).getNumeroDomicilio().equals("")) {
                        historial = historial + "N°=" + direccionNotificacionSipi.get(0).getNumeroDomicilio();
                    }
                }

                if (direccionNotificacionSipi.get(0).getNombreEdificio() != null) {
                    if (!direccionNotificacionSipi.get(0).getNombreEdificio().equals("")) {
                        historial = historial + "Edificio=" + direccionNotificacionSipi.get(0).getNombreEdificio();
                    }
                }

                if (direccionNotificacionSipi.get(0).getPiso() != null) {
                    if (!direccionNotificacionSipi.get(0).getPiso().equals("")) {
                        historial = historial + "Piso=" + direccionNotificacionSipi.get(0).getPiso();
                    }
                }

                if (direccionNotificacionSipi.get(0).getDepartamento() != null) {
                    if (!direccionNotificacionSipi.get(0).getDepartamento().equals("")) {
                        historial = historial + "Dpto=" + direccionNotificacionSipi.get(0).getDepartamento();
                    }
                }

                if (direccionNotificacionSipi.get(0).getCelular() != null) {
                    if (!direccionNotificacionSipi.get(0).getCelular().equals("")) {
                        historial = historial + "Celular=" + direccionNotificacionSipi.get(0).getCelular();
                    }
                }

                if (direccionNotificacionSipi.get(0).getTelefono() != null) {
                    if (!direccionNotificacionSipi.get(0).getTelefono().equals("")) {
                        historial = historial + "Teléfono=" + direccionNotificacionSipi.get(0).getTelefono();
                    }
                }

                if (direccionNotificacionSipi.get(0).getCorreoelectronico() != null) {
                    if (!direccionNotificacionSipi.get(0).getCorreoelectronico().equals("")) {
                        historial = historial + "E-mail=" + direccionNotificacionSipi.get(0).getCorreoelectronico();
                    }
                }

                if (direccionNotificacionSipi.get(0).getReferencia() != null) {
                    if (!direccionNotificacionSipi.get(0).getReferencia().equals("")) {
                        historial = historial + "Referencia=" + direccionNotificacionSipi.get(0).getReferencia();
                    }
                }
                historial = historial.trim();
                historial = historial.substring(0, historial.length() - 1);
                historial = "[ADD: " + historial + "]";
            }

            //se debe proceder a guardar el historial siempre y cuando haya existido por lo menos una modificacion
            if (!historial.equals("")) {

                //si todo esta bien guardar la observacion
                Historial historialTramite = new Historial();
                historialTramite.setId(idSignoMarca);
                historialTramite.setIdUsuario(idUsuario);
                historialTramite.setIdLogTrans(idLogTrans);
                historialTramite.setOperacion(EnumOperacion.MODIFICAR.getCodigo());

                //obtener la descripcion de la marca
                historialTramite.setEstadoMarcaDescripcion(estado_marca);
                historialTramite.setUbicacionDescripcion(ubicacion);
                historialTramite.setSeccion(EnumSeccion.DIRECCION_DE_NOTIFICACION.getCodigo());

                historialTramite.setDescripcion(historial);
                historialTramite.setFechaOperacion(fechaSistema);
                historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());

                historialService.crudHistorial(historialTramite, "SIG", 1);
            }

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void generarHistorialPrioridadesSubsanacion(Long idSignoMarca, List<Prioridades> listaPrioridadPreferenciaSipi, String estado_marca,
            String ubicacion, Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception {
        try {

            String historial = "";
            int sw = 0;
            Locale currentLocale = new Locale("es", "ES");
            DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT, currentLocale);

            // Recuperamos prioridades desde la base de datos
            List<SigPrioridadPreferencia> listaSigPrioridadPreferenciaHidra = obtenerListaPrioridadPreferencia(idSignoMarca);

            // modificar
            for (Prioridades prioridadeSipi : listaPrioridadPreferenciaSipi) {
                sw = 0;
                for (SigPrioridadPreferencia prioridadSipi : listaSigPrioridadPreferenciaHidra) {
                    if (prioridadSipi.getTipoInteres().equals(prioridadeSipi.getTipoInteres())) {
                        if (prioridadSipi.getIdSipi().equals(prioridadeSipi.getIdPadre())) {
                            // generar el historial prioridades
                            historial = "MOD: ";
                            if (prioridadeSipi.getNombre() != null) {
                                if (prioridadSipi.getNombreNumero() != null) {
                                    if (!prioridadSipi.getNombreNumero().equals("")) {
                                        if (!prioridadeSipi.getNombre().equals(prioridadSipi.getNombreNumero())) {
                                            historial = historial + "Prioridad=" + prioridadSipi.getNombreNumero() + "; ";
                                            sw = 1;
                                        }
                                    }
                                }
                            }

                            if (prioridadeSipi.getFecha() != null) {
                                if (prioridadSipi.getFecha() != null) {
                                    if (!prioridadeSipi.getFecha().equals(prioridadSipi.getFecha())) {
                                        historial = historial + "Fecha=" + dateFormatter.format(prioridadSipi.getFecha()) + "; ";
                                        sw = 1;
                                    }
                                }
                            } else if (prioridadSipi.getFecha() != null) {
                                historial = historial + "Fecha=" + dateFormatter.format(prioridadSipi.getFecha()) + "; ";
                                sw = 1;
                            }

                            if (prioridadeSipi.getLugar() != null) {
                                if (prioridadSipi.getLugar() != null) {
                                    if (!prioridadSipi.getLugar().equals("")) {
                                        if (!prioridadeSipi.getLugar().equals(prioridadSipi.getLugar())) {
                                            if (prioridadSipi.getTipoInteres().equals("EXT")) {
                                                historial = historial + "País=" + dominioService.obtenerNombreCodigoDominio("pais", prioridadSipi.getLugar()) + "; ";
                                                sw = 1;
                                            } else {
                                                historial = historial + "Lugar=" + prioridadSipi.getLugar() + "; ";
                                                sw = 1;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    }
                }
                // si sw=1, entonces hay al menos una modificacion, eliminacion o adición
                if (sw == 1) {
                    historial = historial.trim();
                    historial = historial.substring(0, historial.length() - 1);
                    historial = "[" + historial + "]";
                } else {
                    historial = "";
                }

                // se debe proceder a guardar el historial siempre y cuando haya existido por lo menos una modificacion
                if (!historial.equals("")) {

                    Historial historialTramite = new Historial();
                    historialTramite.setId(idSignoMarca);
                    historialTramite.setIdUsuario(idUsuario);
                    historialTramite.setIdLogTrans(idLogTrans);
                    historialTramite.setOperacion(EnumOperacion.MODIFICAR.getCodigo());
                    historialTramite.setEstadoMarcaDescripcion(estado_marca);
                    historialTramite.setUbicacionDescripcion(ubicacion);
                    if (prioridadeSipi.getTipoInteres().equals(EnumTipoInteres.EXTRANJERA.getCodigo())) {
                        historialTramite.setSeccion(EnumSeccion.PRIORIDAD_EXTRANJERA.getCodigo());
                    } else if (prioridadeSipi.getTipoInteres().equals(EnumTipoInteres.EXPOSICION.getCodigo())) {
                        historialTramite.setSeccion(EnumSeccion.PRIORIDAD_EXPOSICION.getCodigo());
                    } else if (prioridadeSipi.getTipoInteres().equals(EnumTipoInteres.OPOSICION_ANDINA.getCodigo())) {
                        historialTramite.setSeccion(EnumSeccion.INTERES_OPOSICION_ANDINA.getCodigo());
                    }
                    historialTramite.setDescripcion(historial);
                    historialTramite.setFechaOperacion(fechaSistema);
                    historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());

                    historialService.crudHistorial(historialTramite, "SIG", 1);
                }

            }

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<SigPrioridadPreferencia> obtenerListaPrioridadPreferencia(Long idSignoMarca) throws Exception {
        try {
            String SQL = "select * from sigprioridadpreferencia "
                    + " where idsignomarca = ? "
                    + " and estado='AC'";
            List<SigPrioridadPreferencia> listaSigPrioridadPreferencia = jdbcTemplate.query(SQL,
                    new SigPrioridadPreferenciaMapper(),
                    idSignoMarca);

            return listaSigPrioridadPreferencia;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void generarHistorialPrioridadExtranjera(Long idSignoMarca, SigPrioridadPreferencia prioridadExtranjera, String estado_marca, String ubicacion, Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception {
        try {

            String historial = "";
            int sw = 0;
            Locale currentLocale = new Locale("es", "ES");
            DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT, currentLocale);

            // Obtener la prioridades desde la Base de datos
            SigPrioridadPreferencia sigPrioridadPreferenciaBase = sigPrioridadPreferenciaService.obtenerPrioridadExtranjera(idSignoMarca);

            // preguntar si el id objeto de Base es diferente de nulo
            if (sigPrioridadPreferenciaBase.getIdPrioridadPreferencia() != null) {
                if (prioridadExtranjera.getIdPrioridadPreferencia() != null) {

                    // generar el historial de modificación prioridad Extranjera
                    historial = "MOD: ";
                    if (prioridadExtranjera.getNombreNumero() != null) {
                        if (sigPrioridadPreferenciaBase.getNombreNumero() != null) {
                            if (!sigPrioridadPreferenciaBase.getNombreNumero().equals("")) {
                                if (!prioridadExtranjera.getNombreNumero().equals(sigPrioridadPreferenciaBase.getNombreNumero())) {
                                    historial = historial + "Prioridad=" + sigPrioridadPreferenciaBase.getNombreNumero() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    if (prioridadExtranjera.getFecha() != null) {
                        if (sigPrioridadPreferenciaBase.getFecha() != null) {
                            if (!prioridadExtranjera.getFecha().equals(sigPrioridadPreferenciaBase.getFecha())) {
                                historial = historial + "Fecha=" + dateFormatter.format(sigPrioridadPreferenciaBase.getFecha()) + "; ";
                                sw = 1;
                            }
                        }
                    } else if (sigPrioridadPreferenciaBase.getFecha() != null) {
                        historial = historial + "Fecha=" + dateFormatter.format(sigPrioridadPreferenciaBase.getFecha()) + "; ";
                        sw = 1;
                    }

                    if (prioridadExtranjera.getLugar() != null) {
                        if (sigPrioridadPreferenciaBase.getLugar() != null) {
                            if (!sigPrioridadPreferenciaBase.getLugar().equals("")) {
                                if (!prioridadExtranjera.getLugar().equals(sigPrioridadPreferenciaBase.getLugar())) {
                                    historial = historial + "País=" + dominioService.obtenerNombreCodigoDominio("pais", sigPrioridadPreferenciaBase.getLugar()) + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }
                } else {
                    // generar historial de eliminacion prioridad Extranjera
                    historial = "DEL: ";
                    if (sigPrioridadPreferenciaBase.getNombreNumero() != null) {
                        historial = historial + "Prioridad=" + sigPrioridadPreferenciaBase.getNombreNumero() + "; ";
                        sw = 1;
                    }

                    if (sigPrioridadPreferenciaBase.getFecha() != null) {
                        historial = historial + "Fecha=" + dateFormatter.format(sigPrioridadPreferenciaBase.getFecha()) + "; ";
                        sw = 1;
                    }

                    if (sigPrioridadPreferenciaBase.getLugar() != null) {
                        if (!sigPrioridadPreferenciaBase.getLugar().equals("")) {
                            historial = historial + "País=" + dominioService.obtenerNombreCodigoDominio("pais", sigPrioridadPreferenciaBase.getLugar()) + "; ";
                            sw = 1;
                        }
                    }
                }

            } else {

                // generar historial creación prioridad Extranjera
                historial = "ADD: ";
                if (prioridadExtranjera.getNombreNumero() != null) {

                    if (!prioridadExtranjera.getNombreNumero().equals("")) {
                        historial = historial + "Prioridad=" + prioridadExtranjera.getNombreNumero() + "; ";
                        sw = 1;
                    }

                    if (prioridadExtranjera.getFecha() != null) {
                        historial = historial + "Fecha=" + dateFormatter.format(prioridadExtranjera.getFecha()) + "; ";
                        sw = 1;
                    }

                    if (prioridadExtranjera.getLugar() != null) {
                        if (!prioridadExtranjera.getLugar().equals("")) {
                            historial = historial + "País=" + dominioService.obtenerNombreCodigoDominio("pais", prioridadExtranjera.getLugar()) + "; ";
                            sw = 1;
                        }
                    }
                }
            }

            // si sw=1, entonces hay al menos una modificacion, eliminacion o adición
            if (sw == 1) {
                historial = historial.trim();
                historial = historial.substring(0, historial.length() - 1);
                historial = "[" + historial + "]";
            } else {
                historial = "";
            }

            // se debe proceder a guardar el historial siempre y cuando haya existido por lo menos una modificacion
            if (!historial.equals("")) {

                Historial historialTramite = new Historial();
                historialTramite.setId(idSignoMarca);
                historialTramite.setIdUsuario(idUsuario);
                historialTramite.setIdLogTrans(idLogTrans);
                historialTramite.setOperacion(EnumOperacion.MODIFICAR.getCodigo());
                historialTramite.setEstadoMarcaDescripcion(estado_marca);
                historialTramite.setUbicacionDescripcion(ubicacion);
                historialTramite.setSeccion(EnumSeccion.PRIORIDAD_EXTRANJERA.getCodigo());
                historialTramite.setDescripcion(historial);
                historialTramite.setFechaOperacion(fechaSistema);
                historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());

                historialService.crudHistorial(historialTramite, "SIG", 1);
            }

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void generarHistorialPrioridadExposicion(Long idSignoMarca, SigPrioridadPreferencia prioridadExposicion, String estado_marca, String ubicacion, Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception {
        try {

            String historial = "";
            int sw = 0;
            Locale currentLocale = new Locale("es", "ES");
            DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT, currentLocale);

            //Obtener la direccion de notificacion desde la Base de datos
            SigPrioridadPreferencia sigprioridadExposicionBase = sigPrioridadPreferenciaService.obtenerPrioridadExposicion(idSignoMarca);

            //preguntar si el id objeto de Base es diferente de nulo
            if (sigprioridadExposicionBase.getIdPrioridadPreferencia() != null) {
                if (prioridadExposicion.getIdPrioridadPreferencia() != null) {

                    //generar el historial de modificación prioridad Exposicion
                    historial = "MOD: ";
                    if (prioridadExposicion.getNombreNumero() != null) {
                        if (sigprioridadExposicionBase.getNombreNumero() != null) {
                            if (!sigprioridadExposicionBase.getNombreNumero().equals("")) {
                                if (!prioridadExposicion.getNombreNumero().equals(sigprioridadExposicionBase.getNombreNumero())) {
                                    historial = historial + "Prioridad=" + sigprioridadExposicionBase.getNombreNumero() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    if (prioridadExposicion.getFecha() != null) {
                        if (sigprioridadExposicionBase.getFecha() != null) {
                            if (!prioridadExposicion.getFecha().equals(sigprioridadExposicionBase.getFecha())) {
                                historial = historial + "Fecha=" + dateFormatter.format(sigprioridadExposicionBase.getFecha()) + "; ";
                                sw = 1;
                            }
                        }
                    } else if (sigprioridadExposicionBase.getFecha() != null) {
                        historial = historial + "Fecha=" + dateFormatter.format(sigprioridadExposicionBase.getFecha()) + "; ";
                        sw = 1;
                    }

                    if (prioridadExposicion.getLugar() != null) {
                        if (sigprioridadExposicionBase.getLugar() != null) {
                            if (!sigprioridadExposicionBase.getLugar().equals("")) {
                                if (!prioridadExposicion.getLugar().equals(sigprioridadExposicionBase.getLugar())) {
                                    historial = historial + "Lugar=" + sigprioridadExposicionBase.getLugar() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                } else {
                    // generar historial de eliminacion prioridad Exposicion
                    historial = "DEL: ";

                    if (sigprioridadExposicionBase.getNombreNumero() != null) {
                        historial = historial + "Prioridad=" + sigprioridadExposicionBase.getNombreNumero() + "; ";
                        sw = 1;
                    }

                    if (sigprioridadExposicionBase.getFecha() != null) {
                        historial = historial + "Fecha=" + dateFormatter.format(sigprioridadExposicionBase.getFecha()) + "; ";
                        sw = 1;
                    }

                    if (sigprioridadExposicionBase.getLugar() != null) {
                        if (!sigprioridadExposicionBase.getLugar().equals("")) {
                            historial = historial + "Lugar=" + sigprioridadExposicionBase.getLugar() + "; ";
                            sw = 1;
                        }
                    }
                }
            } else {
                historial = "ADD: ";
                if (prioridadExposicion.getNombreNumero() != null) {

                    if (prioridadExposicion.getNombreNumero() != null) {
                        historial = historial + "Prioridad=" + prioridadExposicion.getNombreNumero() + "; ";
                        sw = 1;
                    }

                    if (prioridadExposicion.getFecha() != null) {
                        historial = historial + "Fecha=" + dateFormatter.format(prioridadExposicion.getFecha()) + "; ";
                        sw = 1;
                    }

                    if (prioridadExposicion.getLugar() != null) {
                        if (!prioridadExposicion.getLugar().equals("")) {
                            historial = historial + "Lugar=" + prioridadExposicion.getLugar() + "; ";
                            sw = 1;
                        }
                    }

                    historial = historial.trim();
                    historial = historial.substring(0, historial.length() - 1);
                    historial = "[ADD: " + historial + "]";
                }
            }

            //si sw=1, entonces hubo al menos una modificacion, eliminacion o adición
            if (sw == 1) {
                historial = historial.trim();
                historial = historial.substring(0, historial.length() - 1);
                historial = "[" + historial + "]";
            } else {
                historial = "";
            }

            //se debe proceder a guardar el historial siempre y cuando haya existido por lo menos una modificacion
            if (!historial.equals("")) {

                Historial historialTramite = new Historial();
                historialTramite.setId(idSignoMarca);
                historialTramite.setIdUsuario(idUsuario);
                historialTramite.setIdLogTrans(idLogTrans);
                historialTramite.setOperacion(EnumOperacion.MODIFICAR.getCodigo());
                historialTramite.setEstadoMarcaDescripcion(estado_marca);
                historialTramite.setUbicacionDescripcion(ubicacion);
                historialTramite.setSeccion(EnumSeccion.PRIORIDAD_EXPOSICION.getCodigo());
                historialTramite.setDescripcion(historial);
                historialTramite.setFechaOperacion(fechaSistema);
                historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());

                historialService.crudHistorial(historialTramite, "SIG", 1);
            }

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void generarHistorialOposicionAndina(Long idSignoMarca, SigPrioridadPreferencia oposicion, String estado_marca, String ubicacion, Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception {
        try {

            //obtener el historial para la Oposicion Andina
            String historial = "";
            int sw = 0;

            //Obtener la oposicion andina desde la Base de datos
            SigPrioridadPreferencia sigOposicionAndinaBase = sigPrioridadPreferenciaService.obtenerOposicionAndina(idSignoMarca);

            //preguntar si el id objeto de Base es diferente de nulo
            if (sigOposicionAndinaBase.getIdPrioridadPreferencia() != null) {
                if (oposicion.getIdPrioridadPreferencia() != null) {

                    //generar el historial de modificación de Oposicion Andina
                    historial = "MOD: ";
                    if (sigOposicionAndinaBase.getNombreNumero() != null) {
                        if (oposicion.getNombreNumero() != null) {
                            if (!oposicion.getNombreNumero().equals(sigOposicionAndinaBase.getNombreNumero())) {
                                if (!sigOposicionAndinaBase.getNombreNumero().equals("")) {
                                    historial = historial + "Oposición Andina=" + sigOposicionAndinaBase.getNombreNumero() + ";";
                                    sw = 1;
                                }
                            }
                        }
                    }
                } else {
                    //generar el historial de eliminación de Oposicion Andina
                    historial = "DEL: ";
                    if (sigOposicionAndinaBase.getNombreNumero() != null) {
                        historial = historial + "Prioridad=" + sigOposicionAndinaBase.getNombreNumero() + "; ";
                        sw = 1;
                    }
                }

            } else {
                //generar el historial de adición de Oposicion Andina
                historial = "ADD: ";
                if (oposicion.getNombreNumero() != null) {
                    if (!oposicion.getNombreNumero().equals("")) {
                        historial = "[ADD: Oposición Andina=" + oposicion.getNombreNumero() + "]";
                    }
                }
            }
            //si sw=1, entonces hay al menos una modificacion, eliminacion o adición
            if (sw == 1) {
                historial = historial.trim();
                historial = historial.substring(0, historial.length() - 1);
                historial = "[" + historial + "]";
            } else {
                historial = "";
            }

            //se debe proceder a guardar el historial siempre y cuando haya existido por lo menos una modificacion
            if (!historial.equals("")) {

                Historial historialTramite = new Historial();
                historialTramite.setId(idSignoMarca);
                historialTramite.setIdUsuario(idUsuario);
                historialTramite.setIdLogTrans(idLogTrans);
                historialTramite.setOperacion(EnumOperacion.MODIFICAR.getCodigo());
                historialTramite.setEstadoMarcaDescripcion(estado_marca);
                historialTramite.setUbicacionDescripcion(ubicacion);
                historialTramite.setSeccion(EnumSeccion.INTERES_OPOSICION_ANDINA.getCodigo());
                historialTramite.setDescripcion(historial);
                historialTramite.setFechaOperacion(fechaSistema);
                historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());

                historialService.crudHistorial(historialTramite, "SIG", 1);
            }

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void generarHistorialImagen(Long idSignoMarca, ImagenPojo imagenPojo, String estado_marca, String ubicacion, Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception {
        try {

            //obtener el historial para la direccion de notificacion
            String historial = "";

            if (imagenPojo.getSigLogoTipo().getIdLogoTipo() == null) {
                historial = "[ADD:Imagen = " + imagenPojo.getSigLogoTipo().getNombreArchivo() + "]";
            } else {
                historial = "[MOD:Imagen = " + imagenPojo.getSigLogoTipo().getNombreArchivo() + "]";
            }

            //se debe proceder a guardar el historial siempre y cuando haya existido por lo menos una modificacion
            if (!historial.equals("")) {

                //si todo esta bien guardar la observacion
                Historial historialTramite = new Historial();
                historialTramite.setId(idSignoMarca);
                historialTramite.setIdUsuario(idUsuario);
                historialTramite.setIdLogTrans(idLogTrans);
                historialTramite.setOperacion(EnumOperacion.MODIFICAR.getCodigo());

                //obtener la descripcion de la marca
                historialTramite.setEstadoMarcaDescripcion(estado_marca);
                historialTramite.setUbicacionDescripcion(ubicacion);
                historialTramite.setSeccion(EnumSeccion.IMAGEN.getCodigo());

                historialTramite.setDescripcion(historial);
                historialTramite.setFechaOperacion(fechaSistema);
                historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());

                historialService.crudHistorial(historialTramite, "SIG", 1);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void generarHistorialEliminacionImagen(Long idSignoMarca, ImagenPojo imagenPojo, String estado_marca, String ubicacion, Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception {
        try {

            //obtener el historial para la direccion de notificacion
            String historial = "";
            if (imagenPojo.getSigLogoTipo().getIdLogoTipo() == null) {
                historial = "[DEL:Imagen = " + imagenPojo.getSigLogoTipo().getNombreArchivo() + "]";
            }

            //descripcion = "[eliminación de la imagen]";
            //se debe proceder a guardar el historial siempre y cuando haya existido por lo menos una modificacion
            if (!historial.equals("")) {

                //si todo esta bien guardar la observacion
                Historial historialTramite = new Historial();
                historialTramite.setId(idSignoMarca);
                historialTramite.setIdUsuario(idUsuario);
                historialTramite.setIdLogTrans(idLogTrans);
                historialTramite.setOperacion(EnumOperacion.ELIMINAR.getCodigo());

                //obtener la descripcion de la marca
                historialTramite.setEstadoMarcaDescripcion(estado_marca);
                historialTramite.setUbicacionDescripcion(ubicacion);
                historialTramite.setSeccion(EnumSeccion.IMAGEN.getCodigo());

                historialTramite.setDescripcion(historial);
                historialTramite.setFechaOperacion(fechaSistema);
                historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());

                historialService.crudHistorial(historialTramite, "SIG", 1);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void generarHistorialNotificacion(SigSignoMarca signoMarca, Long idusuario, String estadoMarca, String descripcion
    ) {
        try {

            Date fechaSistema = comunService.obtenerFechaHoraServidor(1L);
            LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idusuario, fechaSistema), 1);

            Historial historialNoti = new Historial();
            historialNoti.setId(signoMarca.getIdSignoMarca());
            historialNoti.setIdUsuario(idusuario);
            historialNoti.setIdLogTrans(logTransGuardado.getIdLogTrans());
            historialNoti.setOperacion("MOD");
            //el estado Marca puede setr Notificacion o Observado  o el estado anterior
            historialNoti.setEstadoMarcaDescripcion(estadoMarca);
            historialNoti.setObservacion("");
            //Eso deberia agarrar del combo de ubicacion de la pantalla de examen de signos , lo hare despues
            //historialNoti.setUbicacionDescripcion(ubicacion_modificacion);
            historialNoti.setDescripcion(descripcion);
            historialNoti.setSeccion(EnumSeccion.DATOS_ADMINISTRATIVOS.getCodigo());
            historialNoti.setFechaOperacion(fechaSistema);
            historialNoti.setEstado(EnumEstado.ACTIVO.getCodigo());
            historialService.crudHistorial(historialNoti, "SIG", 1);
            /*   String estado_modificacion = dominioService.obtenerNombreCodigoDominio("estado_modificacion", modificacion.getEstado_modificacion());
             String ubicacion_modificacion = dominioService.obtenerNombreCodigoDominio("ubicacion_modificacion", modificacion.getUbicacion_modificacion());
             //   LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idusuario, fechaRegistro), 1);
             Map<String, String> datosMap = new LinkedHashMap<String, String>();
             String descripcion = "";
             DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
             datosMap.put("[Resol.Mod:", modresolucion.getNumero_resolucion() + "]");
             datosMap.put("[Gestion:", modresolucion.getNumero_resolucion() + "]");
             datosMap.put("[Fecha:", dateFormat.format(modresolucion.getFecha_resolucion()) + "]");

             for (Map.Entry e : datosMap.entrySet()) {
             descripcion = descripcion + e.getKey() + " " + e.getValue();
             }

             datosMap = new LinkedHashMap<String, String>();
             Date fechaSistema = comunService.obtenerFechaHoraServidor(1L);
             LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idusuario, fechaSistema), 1);

             Historial historialMod = new Historial();
             historialMod.setId(modificacion.getIdmodificacion());
             historialMod.setIdUsuario(idusuario); 
             historialMod.setIdLogTrans(logTransGuardado.getIdLogTrans());
             historialMod.setOperacion("ANULACIÓN DE REGISTRO ");
             historialMod.setEstadoMarcaDescripcion(estado_modificacion);
             historialMod.setObservacion(modresolucion.getNumero_resolucion().toString() + "/" + modresolucion.getNumero_resolucion().toString());
             historialMod.setUbicacionDescripcion(ubicacion_modificacion);
             historialMod.setDescripcion(descripcion);
             historialMod.setSeccion(EnumSeccion.DATOS_ADMINISTRATIVOS.getCodigo());
             historialMod.setFechaOperacion(fechaSistema);
             historialMod.setEstado(EnumEstado.ACTIVO.getCodigo());
             historialService.crudHistorial(historialMod, "MOD", 1);*/

        } catch (Exception ex) {
            Logger.getLogger(ModResolucionServiceImpl.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void generarHistorialModifiNotificacion(ModModificacion modi, Long idusuario, String estadoMarca, String descripcion
    ) {
        try {

            Date fechaSistema = comunService.obtenerFechaHoraServidor(1L);
            LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idusuario, fechaSistema), 1);

            Historial historialNoti = new Historial();
            historialNoti.setId(modi.getIdmodificacion());
            historialNoti.setIdUsuario(idusuario);
            historialNoti.setIdLogTrans(logTransGuardado.getIdLogTrans());
            historialNoti.setOperacion("MOD");
            //el estado Marca puede setr Notificacion o Observado  o el estado anterior
            historialNoti.setEstadoMarcaDescripcion(estadoMarca);
            historialNoti.setObservacion("");
            //Eso deberia agarrar del combo de ubicacion de la pantalla de examen de signos , lo hare despues
            //historialNoti.setUbicacionDescripcion(ubicacion_modificacion);
            historialNoti.setDescripcion(descripcion);
            historialNoti.setSeccion(EnumSeccion.DATOS_ADMINISTRATIVOS.getCodigo());
            historialNoti.setFechaOperacion(fechaSistema);
            historialNoti.setEstado(EnumEstado.ACTIVO.getCodigo());
            historialService.crudHistorial(historialNoti, "MOD", 1);

        } catch (Exception ex) {
            Logger.getLogger(ModResolucionServiceImpl.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void generarHistorialRenoNotificacion(RenSolicitudRenovacion ren, Long idusuario, String estadoMarca, String descripcion
    ) {
        try {

            Date fechaSistema = comunService.obtenerFechaHoraServidor(1L);
            LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idusuario, fechaSistema), 1);

            Historial historialNoti = new Historial();
            historialNoti.setId(ren.getIdsolicitudrenovacion());
            historialNoti.setIdUsuario(idusuario);
            historialNoti.setIdLogTrans(logTransGuardado.getIdLogTrans());
            historialNoti.setOperacion("MOD");
            //el estado Marca puede setr Notificacion o Observado  o el estado anterior
            historialNoti.setEstadoMarcaDescripcion(estadoMarca);
            historialNoti.setObservacion("");
            //Eso deberia agarrar del combo de ubicacion de la pantalla de examen de signos , lo hare despues
            //historialNoti.setUbicacionDescripcion(ubicacion_modificacion);
            historialNoti.setDescripcion(descripcion);
            historialNoti.setSeccion(EnumSeccion.DATOS_ADMINISTRATIVOS.getCodigo());
            historialNoti.setFechaOperacion(fechaSistema);
            historialNoti.setEstado(EnumEstado.ACTIVO.getCodigo());
            historialService.crudHistorial(historialNoti, "REN", 1);

        } catch (Exception ex) {
            Logger.getLogger(ModResolucionServiceImpl.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }
}
