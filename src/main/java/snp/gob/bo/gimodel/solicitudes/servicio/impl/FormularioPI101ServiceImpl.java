/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.solicitudes.servicio.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.solicitudes.entidades.Apoderados;
import snp.gob.bo.gimodel.solicitudes.entidades.DireccionNotificaciones;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI100;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI101;
import snp.gob.bo.gimodel.solicitudes.entidades.Formularios;
import snp.gob.bo.gimodel.solicitudes.entidades.Logotipos;
import snp.gob.bo.gimodel.solicitudes.entidades.Prioridades;
import snp.gob.bo.gimodel.solicitudes.entidades.SignoMarcas;
import snp.gob.bo.gimodel.solicitudes.entidades.SmSignoClaseNizas;
import snp.gob.bo.gimodel.solicitudes.entidades.SmTipoSignos;
import snp.gob.bo.gimodel.solicitudes.entidades.Solicitantes;
import snp.gob.bo.gimodel.solicitudes.enums.EnumEstadoExterno;
import snp.gob.bo.gimodel.solicitudes.mapper.FormulariosMapper;
import snp.gob.bo.gimodel.solicitudes.mapper.SignoMarcasMapper;
import snp.gob.bo.gimodel.solicitudes.servicio.FormularioPI100Service;
import snp.gob.bo.gimodel.solicitudes.servicio.FormularioPI101Service;

/**
 *
 * @author Eddy Valero
 * @see FormularioPI100
 * @see FormularioPI100Service
 * @version 1.0, 31/08/2016
 */
@Service("formularioPI101Service")
public class FormularioPI101ServiceImpl implements FormularioPI101Service {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
    public FormularioPI101 obtenerDatosFormularioPI101(Long idFormulario) throws Exception {
        try {
            
            FormularioPI101 formularioPI101 = new FormularioPI101();
            formularioPI101.setMensaje(null);

            //obtener el idFormulario
            Formularios formularios = jdbcTemplate.queryForObject("select * from formulario.formularios where id = ? ", new FormulariosMapper(), idFormulario);
            

            formularioPI101.setFormularios(formularios);
            
            //Obtener el listado de todos los solicitantes
            List<Solicitantes> listaSolicitantes = new ArrayList<>();

            List<Map<String, Object>> solicitantes = jdbcTemplate.queryForList("select * from formulario.solicitantes where formulario_id = ? and estado='AC'", formularios.getId());
            for (Map<String, Object> map : solicitantes) {
                Solicitantes solicitante = new Solicitantes();
                
                solicitante.setId(Long.parseLong(map.get("id").toString()));
                solicitante.setNumeroDocumento(map.get("numerodocumento").toString());
                
                if (map.get("tipodocumento") != null) {
                    solicitante.setTipoDocumento(map.get("tipodocumento").toString());
                } else {
                    solicitante.setTipoDocumento(null);
                }
                
                if (map.get("lugarexpedicion") != null) {
                    solicitante.setLugarExpedicion(map.get("lugarexpedicion").toString());
                } else {
                    solicitante.setLugarExpedicion(null);
                }

                if (map.get("tipopersona") != null) {
                    solicitante.setTipoPersona(map.get("tipopersona").toString());
                } else {
                    solicitante.setTipoPersona(null);
                }
                
                if (map.get("pais") != null) {
                    solicitante.setPais(map.get("pais").toString());
                } else {
                    solicitante.setPais(null);
                }
                
                if (map.get("departamentosolicitud") != null) {
                    solicitante.setDepartamentoSolicitud(map.get("departamentosolicitud").toString());
                } else {
                    solicitante.setDepartamentoSolicitud(null);
                }

                if (map.get("nombrerazonsocial") != null) {
                    solicitante.setNombreRazonSocial(map.get("nombrerazonsocial").toString());
                } else {
                    solicitante.setNombreRazonSocial(null);
                }

                if (map.get("primerapellido") != null) {
                    solicitante.setPrimerApellido(map.get("primerapellido").toString());
                } else {
                    solicitante.setPrimerApellido(null);
                }

                if (map.get("segundoapellido") != null) {
                    solicitante.setSegundoApellido(map.get("segundoapellido").toString());
                } else {
                    solicitante.setSegundoApellido(null);
                }

                if (map.get("genero") != null) {
                    solicitante.setGenero(map.get("genero").toString());
                } else {
                    solicitante.setGenero(null);
                }

                if (map.get("domicilio") != null) {
                    solicitante.setDomicilio(map.get("domicilio").toString());
                } else {
                    solicitante.setDomicilio(null);
                }

                if (map.get("telefono") != null) {
                    solicitante.setTelefono(map.get("telefono").toString());
                } else {
                    solicitante.setTelefono(null);
                }

                if (map.get("celular") != null) {
                    solicitante.setCelular(map.get("celular").toString());
                } else {
                    solicitante.setCelular(null);
                }

                if (map.get("correoelectronico") != null) {
                    solicitante.setCorreoElectronico(map.get("correoelectronico").toString());
                } else {
                    solicitante.setCorreoElectronico(null);
                }

                if (map.get("estado") != null) {
                    solicitante.setEstado(map.get("estado").toString());
                } else {
                    solicitante.setEstado(null);
                }

                if (map.get("id_padre") != null) {
                    solicitante.setIdPadre(Long.parseLong(map.get("id_padre").toString()));
                } else {
                    solicitante.setIdPadre(null);
                }
                
                //armar la lista con cada solicitante
                listaSolicitantes.add(solicitante);
            }
            
            formularioPI101.setSolicitantes(listaSolicitantes);

            //Obtener el listado de todos los apoderados del formulario determinado
            List<Apoderados> listaApoderados = new ArrayList<>();
            List<Map<String, Object>> apoderados = jdbcTemplate.queryForList("select * from formulario.apoderados where formulario_id = ? and estado='AC'", formularios.getId());
            for (Map<String, Object> map : apoderados) {
                Apoderados apoderado = new Apoderados();
                
                apoderado.setId(Long.parseLong(map.get("id").toString()));

                if (map.get("nombres") != null) {
                    apoderado.setNombres(map.get("nombres").toString());
                } else {
                    apoderado.setNombres(null);
                }

                if (map.get("primerapellido") != null) {
                    apoderado.setPrimerApellido(map.get("primerapellido").toString());
                } else {
                    apoderado.setPrimerApellido(null);
                }

                if (map.get("segundoapellido") != null) {
                    apoderado.setSegundoApellido(map.get("segundoapellido").toString());
                } else {
                    apoderado.setSegundoApellido(null);
                }

                if (map.get("tipodocumento") != null) {
                    apoderado.setTipoDocumento(map.get("tipodocumento").toString());
                } else {
                    apoderado.setTipoDocumento(null);
                }

                if (map.get("numerodocumento") != null) {
                    apoderado.setNumeroDocumento(map.get("numerodocumento").toString());
                } else {
                    apoderado.setNumeroDocumento(null);
                }

                if (map.get("lugarexpedicion") != null) {
                    apoderado.setLugarExpedicion(map.get("lugarexpedicion").toString());
                } else {
                    apoderado.setLugarExpedicion(null);
                }
                
                if (map.get("genero") != null) {
                    apoderado.setGenero(map.get("genero").toString());
                } else {
                    apoderado.setGenero(null);
                }

                if (map.get("domicilio") != null) {
                    apoderado.setDomicilio(map.get("domicilio").toString());
                } else {
                    apoderado.setDomicilio(null);
                }

                if (map.get("telefono") != null) {
                    apoderado.setTelefono(map.get("telefono").toString());
                } else {
                    apoderado.setTelefono(null);
                }

                if (map.get("celular") != null) {
                    apoderado.setCelular(map.get("celular").toString());
                } else {
                    apoderado.setCelular(null);
                }

                if (map.get("numeropoder") != null) {
                    apoderado.setNumeroPoder(map.get("numeropoder").toString());
                } else {
                    apoderado.setNumeroPoder(null);
                }

                if (map.get("fechapoder") != null) {
                    apoderado.setFechaPoder((Date) map.get("fechapoder"));
                } else {
                    apoderado.setFechaPoder(null);
                }

                if (map.get("correoelectronico") != null) {
                    apoderado.setCorreoElectronico(map.get("correoelectronico").toString());
                } else {
                    apoderado.setCorreoElectronico(null);
                }

                if (map.get("id_padre") != null) {
                    apoderado.setIdPadre(Long.parseLong(map.get("id_padre").toString()));
                } else {
                    apoderado.setIdPadre(null);
                }
                
                listaApoderados.add(apoderado);
            }

            formularioPI101.setApoderados(listaApoderados);

            //Obtener el listado de las direcciones
            List<DireccionNotificaciones> listaDirecciones = new ArrayList<>();
            List<Map<String, Object>> direcciones = jdbcTemplate.queryForList("select * from formulario.direccionnotificaciones where formulario_id = ? and estado='AC'", formularios.getId());
            for (Map<String, Object> map : direcciones) {
                DireccionNotificaciones direccion = new DireccionNotificaciones();
                if (map.get("id") != null) {
                    direccion.setId(Long.parseLong(map.get("id").toString()));
                } else {
                    direccion.setId(null);
                }
                
                if (map.get("ciudadnotificacion") != null) {
                    direccion.setCiudadNotificacion(map.get("ciudadnotificacion").toString());
                } else {
                    direccion.setCiudadNotificacion(null);
                }

                if (map.get("zonabarrio") != null) {
                    direccion.setZonaBarrio(map.get("zonabarrio").toString());
                } else {
                    direccion.setZonaBarrio(null);
                }

                if (map.get("avenidacalle") != null) {
                    direccion.setAvenidaCalle(map.get("avenidacalle").toString());
                } else {
                    direccion.setAvenidaCalle(null);
                }

                if (map.get("numerodomicilio") != null) {
                    direccion.setNumeroDomicilio(map.get("numerodomicilio").toString());
                } else {
                    direccion.setNumeroDomicilio(null);
                }

                if (map.get("nombreedificio") != null) {
                    direccion.setNombreEdificio(map.get("nombreedificio").toString());
                } else {
                    direccion.setNombreEdificio(null);
                }

                if (map.get("piso") != null) {
                    direccion.setPiso(map.get("piso").toString());
                } else {
                    direccion.setPiso(null);
                }

                if (map.get("departamento") != null) {
                    direccion.setDepartamento(map.get("departamento").toString());
                } else {
                    direccion.setDepartamento(null);
                }

                if (map.get("telefono") != null) {
                    direccion.setTelefono(map.get("telefono").toString());
                } else {
                    direccion.setTelefono(null);
                }

                if (map.get("celular") != null) {
                    direccion.setCelular(map.get("celular").toString());
                } else {
                    direccion.setCelular(null);
                }

                if (map.get("correoelectronico") != null) {
                    direccion.setCorreoelectronico(map.get("correoelectronico").toString());
                } else {
                    direccion.setCorreoelectronico(null);
                }

                if (map.get("referencia") != null) {
                    direccion.setReferencia(map.get("referencia").toString());
                } else {
                    direccion.setReferencia(null);
                }

                if (map.get("estado") != null) {
                    direccion.setEstado(map.get("estado").toString());
                } else {
                    direccion.setEstado(null);
                }
                listaDirecciones.add(direccion);
            }
            formularioPI101.setDirecciones(listaDirecciones);

//                //Obtener el listado de las prioridades
            List<Prioridades> listaPrioridades = new ArrayList<>();
            List<Map<String, Object>> prioridades = jdbcTemplate.queryForList("select * from formulario.prioridades where formulario_id = ? and estado='AC'", formularios.getId());
            for (Map<String, Object> map : prioridades) {
                Prioridades prioridad = new Prioridades();

                prioridad.setId(Long.parseLong(map.get("id").toString()));
                
                if (map.get("tipoprioridad") != null) {
                    prioridad.setTipoPrioridad(map.get("tipoprioridad").toString());
                } else {
                    prioridad.setTipoPrioridad(null);
                }

                if (map.get("tipointeres") != null) {
                    prioridad.setTipoInteres(map.get("tipointeres").toString());
                } else {
                    prioridad.setTipoInteres(null);
                }

                if (map.get("nombre") != null) {
                    prioridad.setNombre(map.get("nombre").toString());
                } else {
                    prioridad.setNombre(null);
                }

                if (map.get("fecha") != null) {
                    prioridad.setFecha((Date)map.get("fecha"));
                } else {
                    prioridad.setFecha(null);
                }
                if (map.get("lugar") != null) {
                    prioridad.setLugar(map.get("lugar").toString());
                } else {
                    prioridad.setLugar(null);
                }
                
                if (map.get("id_padre") != null) {
                    prioridad.setIdPadre(Long.parseLong(map.get("id_padre").toString()));
                } else {
                    prioridad.setIdPadre(null);
                }                

                listaPrioridades.add(prioridad);
            }

            formularioPI101.setPrioridades(listaPrioridades);

            List<SmSignoClaseNizas> listaSmSignoClaseNizas = new ArrayList<>();
            SignoMarcas signoMarcas = jdbcTemplate.queryForObject("select * from formulario.signomarcas where formulario_id = ? and estado='AC'", new SignoMarcasMapper(), formularios.getId());
            
            formularioPI101.setSignoMarca(signoMarcas);

            //Obtener el listado de ClasesNiza
            List<Map<String, Object>> smSignosClasesNiza = jdbcTemplate.queryForList("select * from formulario.smsignoclasenizas where signomarca_id = ? and estado='AC'", signoMarcas.getId());
            for (Map<String, Object> map : smSignosClasesNiza) {
                SmSignoClaseNizas smSignoClaseNizas = new SmSignoClaseNizas();

                smSignoClaseNizas.setId(Long.parseLong(map.get("id").toString()));
                
                if (map.get("numeroclaseniza") != null) {
                    smSignoClaseNizas.setNumeroClaseNiza(Long.parseLong(map.get("numeroclaseniza").toString()));
                } else {
                    smSignoClaseNizas.setNumeroClaseNiza(null);
                }
                
                if (map.get("claseniza_id") != null) {
                    smSignoClaseNizas.setClaseNizaId(Long.parseLong(map.get("claseniza_id").toString()));
                } else {
                    smSignoClaseNizas.setClaseNizaId(169L);
                }

                if (map.get("listaproductos") != null) {
                    smSignoClaseNizas.setListaProductos(map.get("listaproductos").toString());
                } else {
                    smSignoClaseNizas.setListaProductos(null);
                }

                if (map.get("id_padre") != null) {
                    smSignoClaseNizas.setIdPadre(Long.parseLong(map.get("id_padre").toString()));
                } else {
                    smSignoClaseNizas.setIdPadre(null);
                }
                
                listaSmSignoClaseNizas.add(smSignoClaseNizas);
            }
            formularioPI101.setSmSignoClaseNizases(listaSmSignoClaseNizas);

            //Obtener el listado de TiposSigno
            List<SmTipoSignos> listaSmTipoSignos = new ArrayList<>();

            List<Map<String, Object>> smTipoSignos = jdbcTemplate.queryForList("select * from formulario.smtiposignos where signomarca_id = ? and estado='AC'", signoMarcas.getId());
            for (Map<String, Object> map : smTipoSignos) {

                SmTipoSignos smTipoSigno = new SmTipoSignos();

                if (map.get("tiposigno") != null) {
                    smTipoSigno.setTipoSigno(map.get("tiposigno").toString());
                } else {
                    smTipoSigno.setTipoSigno(null);
                }

                if (map.get("descripcionotro") != null) {
                    smTipoSigno.setDescripcionOtro(map.get("descripcionotro").toString());
                } else {
                    smTipoSigno.setDescripcionOtro(null);
                }
                listaSmTipoSignos.add(smTipoSigno);
            }
            formularioPI101.setSmTipoSignos(listaSmTipoSignos);

            //Obtener el listado de Logotipos
            List<Logotipos> listaLogotipos = new ArrayList<>();

            List<Map<String, Object>> logotipos = jdbcTemplate.queryForList("select * from formulario.logotipos"
                    + " where signomarca_id = ? and estado = '"+ EnumEstadoExterno.ACTIVO.getCodigo() +"'", signoMarcas.getId());
            for (Map<String, Object> map : logotipos) {
                Logotipos logotipo = new Logotipos();

                if (map.get("urlimagen") != null) {
                    logotipo.setUrlimagen(map.get("urlimagen").toString());
                } else {
                    logotipo.setUrlimagen(null);
                }

                if (map.get("imagen") != null) {
                    logotipo.setImagen((byte[]) map.get("imagen"));
                } else {
                    logotipo.setImagen(null);
                }

                if (map.get("principal") != null) {
                    logotipo.setPrincipal(Boolean.parseBoolean(map.get("principal").toString()));
                } else {
                    logotipo.setPrincipal(Boolean.FALSE);
                }
                
                if (map.get("nombrearchivo") != null) {
                    logotipo.setNombreArchivo(map.get("nombrearchivo").toString());
                } else {
                    logotipo.setNombreArchivo(null);
                }

                if (map.get("extensionarchivo") != null) {
                    logotipo.setExtensionArchivo(map.get("extensionarchivo").toString());
                } else {
                    logotipo.setExtensionArchivo(null);
                }
                
                listaLogotipos.add(logotipo);
            }
            
            formularioPI101.setLogotipos(listaLogotipos);
            
            
            return formularioPI101;

        } catch (Exception e) {
            throw e;
        }
    }

}
