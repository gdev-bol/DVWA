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
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI104;
import snp.gob.bo.gimodel.solicitudes.entidades.Formularios;
import snp.gob.bo.gimodel.solicitudes.entidades.RenTipoSignos;
import snp.gob.bo.gimodel.solicitudes.entidades.RenTitularRegistrados;
import snp.gob.bo.gimodel.solicitudes.entidades.Solicitantes;
import snp.gob.bo.gimodel.solicitudes.entidades.SolicitudRenovaciones;
import snp.gob.bo.gimodel.solicitudes.mapper.FormulariosMapper;
import snp.gob.bo.gimodel.solicitudes.servicio.FormularioPI100Service;
import snp.gob.bo.gimodel.solicitudes.servicio.FormularioPI104Service;

/**
 *
 * @author Eddy Valero
 * @see FormularioPI100
 * @see FormularioPI100Service
 * @version 1.0, 26/10/2016
 */
@Service("formularioPI104Service")
public class FormularioPI104ServiceImpl implements FormularioPI104Service {

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
    public FormularioPI104 obtenerDatosFormularioPI104(Long idFormulario) throws Exception {
        try {

            FormularioPI104 formularioPI104 = new FormularioPI104();
            formularioPI104.setMensaje(null);

            //obtener el idFormulario
            Formularios formularios = jdbcTemplate.queryForObject("select * from formulario.formularios where id = ? and estado='AC'", new FormulariosMapper(), idFormulario);

            formularioPI104.setFormularios(formularios);
            //Obtener el listado de todos los solicitantes
            List<Solicitantes> listaSolicitantes = new ArrayList<Solicitantes>();

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

            formularioPI104.setSolicitantes(listaSolicitantes);

            //Obtener el listado de todos los apoderados del formulario determinado
            List<Apoderados> listaApoderados = new ArrayList<Apoderados>();
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

            formularioPI104.setApoderados(listaApoderados);

            //Obtener el listado de las direcciones
            List<DireccionNotificaciones> listaDirecciones = new ArrayList<DireccionNotificaciones>();
            List<Map<String, Object>> direcciones = jdbcTemplate.queryForList("select * from formulario.direccionnotificaciones where formulario_id = ? and estado='AC'", formularios.getId());
            for (Map<String, Object> map : direcciones) {
                DireccionNotificaciones direccion = new DireccionNotificaciones();

                direccion.setId(Long.parseLong(map.get("id").toString()));

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
            formularioPI104.setDirecciones(listaDirecciones);

            //cargar el registro de SolicitudRenovaciones
            List<SolicitudRenovaciones> listaSolicitudRenovaciones = new ArrayList<SolicitudRenovaciones>();

//            String SQL = "SELECT sore.id, tipogenero, signoregistrado, cn.numero_clase_niza AS clasenizaregistrado, numeroregistro, serieregistro, fechaotorgacionmarca,"
//                    + " numeroultimarenovacion, numeropenultimarenovacion, listaproductoslimitacion, clasenizareclasificacion, sore.estado, formulario_id"
//                    + " FROM formulario.solicitudrenovaciones sore"
//                    + " JOIN formulario.claseniza cn ON (cn.id = sore.clasenizaregistrado AND cn.estado = 'AC')"
//                    + " AND sore.estado='AC' AND sore.formulario_id = ?;";
            String SQL = "SELECT sore.id, tipogenero, signoregistrado, \n"
                    + "cn.id AS clasenizaregistrado, \n"
                    + "numeroregistro,\n"
                    + "serieregistro, fechaotorgacionmarca, \n"
                    + "numeroultimarenovacion, numeropenultimarenovacion, \n"
                    + "listaproductoslimitacion, \n"
                    + "cnr.id AS clasenizareclasificacion, \n"
                    + "sore.estado, formulario_id \n"
                    + "FROM formulario.solicitudrenovaciones sore \n"
                    + "JOIN formulario.claseniza cn \n"
                    + "ON (cn.id = sore.clasenizaregistrado AND cn.estado = 'AC')\n"
                    + "FULL  OUTER JOIN formulario.claseniza cnr \n"
                    + "ON (cnr.id = sore.clasenizareclasificacion AND cn.estado = 'AC')  \n"
                    + "where sore.formulario_id =?";

            System.out.println("hdjsahdkjas" + SQL);

            List<Map<String, Object>> solicitudRenovaciones = jdbcTemplate.queryForList(SQL, formularios.getId());
            SolicitudRenovaciones solicitudRenovacion = new SolicitudRenovaciones();
            for (Map<String, Object> map : solicitudRenovaciones) {

                solicitudRenovacion.setId(Long.valueOf(map.get("id").toString()));

                if (map.get("tipogenero") != null) {
                    solicitudRenovacion.setTipoGenero(map.get("tipogenero").toString());
                } else {
                    solicitudRenovacion.setTipoGenero(null);
                }

                if (map.get("signoregistrado") != null) {
                    solicitudRenovacion.setSignoRegistrado(map.get("signoregistrado").toString());
                } else {
                    solicitudRenovacion.setSignoRegistrado(null);
                }

                if (map.get("clasenizaregistrado") != null) {
                    solicitudRenovacion.setClaseNizaRegistrado(Integer.valueOf(map.get("clasenizaregistrado").toString()));
                } else {
                    Integer variable = null;
                    solicitudRenovacion.setClaseNizaRegistrado(variable);
                }

                if (map.get("numeroregistro") != null) {
                    solicitudRenovacion.setNumeroRegistro(Long.valueOf(map.get("numeroregistro").toString()));
                } else {
                    solicitudRenovacion.setNumeroRegistro(null);
                }

                if (map.get("serieregistro") != null) {
                    solicitudRenovacion.setSerieRegistro((map.get("serieregistro").toString()));
                } else {
                    solicitudRenovacion.setSerieRegistro(null);
                }

                if (map.get("fechaotorgacionmarca") != null) {
                    solicitudRenovacion.setFechaOtorgacionMarca((Date) map.get("fechaotorgacionmarca"));
                } else {
                    solicitudRenovacion.setFechaOtorgacionMarca(null);
                }

                try {

                    if (map.get("numeroultimarenovacion") != null) {
                        if (map.get("numeroultimarenovacion").toString().trim().equals("")) {
                            Integer variable = null;
                            solicitudRenovacion.setNumeroUltimaRenovacion(variable);
                        } else {
                            solicitudRenovacion.setNumeroUltimaRenovacion(Integer.valueOf(map.get("numeroultimarenovacion").toString()));
                        }
                    }

                } catch (Exception e) {
                    Integer variable = null;
                    solicitudRenovacion.setNumeroUltimaRenovacion(variable);
                }

                try {
                    if (map.get("numeropenultimarenovacion") != null) {
                        if (map.get("numeropenultimarenovacion").toString().trim().equals("")) {
                            Integer variable = null;
                            solicitudRenovacion.setNumeroPenultimaRenovacion(variable);
                        } else {
                            solicitudRenovacion.setNumeroPenultimaRenovacion(Integer.valueOf(map.get("numeropenultimarenovacion").toString()));
                        }

                    }
                } catch (Exception e) {
                    Integer variable = null;
                    solicitudRenovacion.setNumeroPenultimaRenovacion(variable);
                }

                if (map.get("listaproductoslimitacion") != null) {
                    solicitudRenovacion.setListaProductosLimitacion(map.get("listaproductoslimitacion").toString());
                } else {
                    solicitudRenovacion.setListaProductosLimitacion(null);
                }

                if (map.get("clasenizareclasificacion") != null) {
                    solicitudRenovacion.setClaseNizaReclasificacion(Integer.valueOf(map.get("clasenizareclasificacion").toString()));
                } else {
                    Integer variable = null;
                    solicitudRenovacion.setClaseNizaReclasificacion(variable);
                }

                solicitudRenovacion.setFormularioId(Long.valueOf(map.get("formulario_id").toString()));

                //listaSolicitudRenovaciones.add(solicitudRenovacion);
            }
            formularioPI104.setSolicitudRenovaciones(solicitudRenovacion);

            //cargar el registro de rentitularRegistrado
            List<RenTitularRegistrados> listaRenTitularRegistrados = new ArrayList<RenTitularRegistrados>();
            List<Map<String, Object>> renTitularRegistrados = jdbcTemplate.queryForList("select * from formulario.rentitularregistrados where solicitudrenovacion_id = ? and estado='AC'", formularioPI104.getSolicitudRenovaciones().getId());

            for (Map<String, Object> map : renTitularRegistrados) {
                RenTitularRegistrados renTitularRegistrado = new RenTitularRegistrados();

                renTitularRegistrado.setId(Long.parseLong(map.get("id").toString()));

                if (map.get("tipopersona") != null) {
                    renTitularRegistrado.setTipoPersona(map.get("tipopersona").toString());
                } else {
                    renTitularRegistrado.setTipoPersona(null);
                }

                if (map.get("nombre_razonsocial") != null) {
                    renTitularRegistrado.setNombreRazonSocial(map.get("nombre_razonsocial").toString());
                } else {
                    renTitularRegistrado.setNombreRazonSocial(null);
                }

                if (map.get("primerapellido") != null) {
                    renTitularRegistrado.setPrimerApellido(map.get("primerapellido").toString());
                } else {
                    renTitularRegistrado.setPrimerApellido(null);

                }

                if (map.get("segundoapellido") != null) {
                    renTitularRegistrado.setSegundoApellido(map.get("segundoapellido").toString());
                } else {
                    renTitularRegistrado.setSegundoApellido(null);
                }

                if (map.get("direccion") != null) {
                    renTitularRegistrado.setDireccion(map.get("direccion").toString());
                } else {
                    renTitularRegistrado.setDireccion(null);
                }
                if (map.get("solicitudrenovacion_id") != null) {
                    renTitularRegistrado.setSolicitudRenovacionId(Integer.valueOf(map.get("solicitudrenovacion_id").toString()));
                } else {
                    Integer valor = null;
                    renTitularRegistrado.setSolicitudRenovacionId(valor);
                }
                if (map.get("id_padre") != null) {
                    renTitularRegistrado.setIdPadre(Long.parseLong(map.get("id_padre").toString()));
                } else {
                    renTitularRegistrado.setIdPadre(null);
                }
                listaRenTitularRegistrados.add(renTitularRegistrado);

            }

            formularioPI104.setRenTitularRegistrados(listaRenTitularRegistrados);

            //agregar el tipo signo de Renovaciones
            List<RenTipoSignos> listaRenTipoSignos = new ArrayList<RenTipoSignos>();
            List<Map<String, Object>> smTipoSignos = jdbcTemplate.queryForList("select * from formulario.rentiposigno where renovacion_id = ? and estado='AC'",
                    formularioPI104.getSolicitudRenovaciones().getId());

            for (Map<String, Object> map : smTipoSignos) {

                RenTipoSignos renTipoSignos = new RenTipoSignos();

                if (map.get("tiposigno") != null) {
                    renTipoSignos.setTiposigno(map.get("tiposigno").toString());
                } else {
                    renTipoSignos.setTiposigno(null);
                }

                if (map.get("descripcionotro") != null) {
                    renTipoSignos.setDescripcionotro(map.get("descripcionotro").toString());
                } else {
                    renTipoSignos.setDescripcionotro(null);
                }
                listaRenTipoSignos.add(renTipoSignos);
            }
            formularioPI104.setRenTipoSignos(listaRenTipoSignos);

            return formularioPI104;

        } catch (Exception e) {
            throw e;
        }
    }

}
