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
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI103;
import snp.gob.bo.gimodel.solicitudes.entidades.Formularios;
import snp.gob.bo.gimodel.solicitudes.entidades.ModTipoSignos;
import snp.gob.bo.gimodel.solicitudes.entidades.Modificaciones;
import snp.gob.bo.gimodel.solicitudes.entidades.SmTipoSignos;
import snp.gob.bo.gimodel.solicitudes.entidades.Solicitantes;
import snp.gob.bo.gimodel.solicitudes.entidades.TitularLicenciatarios;
import snp.gob.bo.gimodel.solicitudes.entidades.TitularLicenciatariosNuevos;
import snp.gob.bo.gimodel.solicitudes.mapper.FormulariosMapper;
import snp.gob.bo.gimodel.solicitudes.mapper.ModificacionesMapper;
import snp.gob.bo.gimodel.solicitudes.servicio.FormularioPI100Service;
import snp.gob.bo.gimodel.solicitudes.servicio.FormularioPI103Service;

/**
 *
 * @author Eddy Valero
 * @see FormularioPI100
 * @see FormularioPI100Service
 * @version 1.0, 24/10/2016
 */
@Service("formularioPI103Service")
public class FormularioPI103ServiceImpl implements FormularioPI103Service {

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
    public FormularioPI103 obtenerDatosFormularioPI103(Long idFormulario) throws Exception {
        try {

            FormularioPI103 formularioPI103 = new FormularioPI103();
            formularioPI103.setMensaje(null);

            //obtener el idFormulario
            Formularios formularios = jdbcTemplate.queryForObject("select * from formulario.formularios where id = ? and estado='AC'", new FormulariosMapper(), idFormulario);

            formularioPI103.setFormularios(formularios);
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

            formularioPI103.setSolicitantes(listaSolicitantes);

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

            formularioPI103.setApoderados(listaApoderados);

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
                    direccion.setCorreoelectronico("");
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
            formularioPI103.setDirecciones(listaDirecciones);

            //cargar la entidad Modificaciones
            /*Modificaciones modificaciones = jdbcTemplate.queryForObject("select * from formulario.modificaciones "
             + "where formulario_id = ? and estado = 'AC'", new ModificacionesMapper(), formularios.getId());*/
            String SQL = "SELECT mod.id, sm, nroformulario, oficina, numeroregistro, serieregistro, numerorenovacion, serierenovacion, nropublicacion, signoregistro,"
                    + " cn.id AS claseregistrado, tipogeneroregistrado, marcaacomp, reg_lc_registrado, serie_lc_registrado, fecha_lc_registrado,"
                    + " nuevopaisconstitucion, luinicio, lufin, tipomodificacion, listaproducto, mod.estado, dependiente_mod, formulario_id"
                    + " FROM formulario.modificaciones mod"
                    + " JOIN formulario.claseniza cn ON (cn.id = mod.claseregistrado AND cn.estado = 'AC')"
                    + " AND mod.estado='AC' AND mod.formulario_id = ?;";
            
            Modificaciones modificaciones = jdbcTemplate.queryForObject(SQL, new ModificacionesMapper(), formularios.getId());

            formularioPI103.setModificaciones(modificaciones);

            //Agregar el titular licenciatario
            List<TitularLicenciatarios> listaTitularLicenciatarios = new ArrayList<TitularLicenciatarios>();
            List<Map<String, Object>> titularLicenciatarios = jdbcTemplate.queryForList("select * from formulario.titularlicenciatario "
                    + " where modificacion_id = ? and estado='AC'", modificaciones.getId());
            for (Map<String, Object> map : titularLicenciatarios) {
                TitularLicenciatarios titularLicenciatario = new TitularLicenciatarios();

                titularLicenciatario.setId(Long.parseLong(map.get("id").toString()));

                if (map.get("tipotitular") != null) {
                    titularLicenciatario.setTipoTitular(map.get("tipotitular").toString());
                } else {
                    titularLicenciatario.setTipoTitular(null);
                }

                if (map.get("tipotitularregistrado") != null) {

                    titularLicenciatario.setTipoTitularRegistrado(map.get("tipotitularregistrado").toString());
                } else {
                    titularLicenciatario.setTipoTitularRegistrado(null);
                }

                if (map.get("nombre_razonsocial") != null) {

                    titularLicenciatario.setNombreRazonSocial(map.get("nombre_razonsocial").toString());
                } else {
                    titularLicenciatario.setNombreRazonSocial(null);
                }

                if (map.get("direccion") != null) {

                    titularLicenciatario.setDireccion(map.get("direccion").toString());
                } else {
                    titularLicenciatario.setDireccion(null);
                }

                if (map.get("estado") != null) {

                    titularLicenciatario.setEstado(map.get("estado").toString());
                } else {
                    titularLicenciatario.setEstado("AC");
                }
                titularLicenciatario.setModificacionId(Integer.parseInt(map.get("modificacion_id").toString()));

                if (map.get("id_padre") != null) {
                    titularLicenciatario.setIdPadre(Long.parseLong(map.get("id_padre").toString()));
                } else {
                    titularLicenciatario.setIdPadre(null);
                }

                //agregar titular licenciatario
                listaTitularLicenciatarios.add(titularLicenciatario);

            }

            formularioPI103.setTitularLicenciatarios(listaTitularLicenciatarios);

            //Agregar el titular licenciatario nuevo
            List<TitularLicenciatariosNuevos> listaTitularLicenciatariosNuevos = new ArrayList<TitularLicenciatariosNuevos>();
            List<Map<String, Object>> titularLicenciatariosNuevos = jdbcTemplate.queryForList("select * from formulario.titularlicenciatarionuevo "
                    + " where modificacion_id = ? and estado='AC'", modificaciones.getId());

            for (Map<String, Object> map : titularLicenciatariosNuevos) {

                TitularLicenciatariosNuevos titularLicenciatarioNuevo = new TitularLicenciatariosNuevos();

                titularLicenciatarioNuevo.setId(Long.parseLong(map.get("id").toString()));

                if (map.get("tipopersona") != null) {
                    titularLicenciatarioNuevo.setTipoPersona(map.get("tipopersona").toString());
                } else {
                    titularLicenciatarioNuevo.setTipoPersona(null);
                }

                //para el caso de cambio de nombre el valor del tipotitular es null
                if (map.get("tipotitular") != null) {
                    titularLicenciatarioNuevo.setTipoTitular(map.get("tipotitular").toString());
                } else {
                    titularLicenciatarioNuevo.setTipoTitular(null);
                }

                //para el caso de cambio de nombre el valor del nombre_razonsocial es null
                if (map.get("nombre_razonsocial") != null) {
                    titularLicenciatarioNuevo.setNombreRazonSocial(map.get("nombre_razonsocial").toString());
                } else {
                    titularLicenciatarioNuevo.setNombreRazonSocial(null);
                }

                //se debe preguntar si son validos o no los campos
                if (map.get("primerapellido") != null) {
                    titularLicenciatarioNuevo.setPrimerApellido(map.get("primerapellido").toString());
                } else {
                    titularLicenciatarioNuevo.setPrimerApellido(null);
                }

                if (map.get("segundoapellido") != null) {
                    titularLicenciatarioNuevo.setSegundoApellido(map.get("segundoapellido").toString());
                } else {
                    titularLicenciatarioNuevo.setSegundoApellido(null);
                }

                if (map.get("nrodocumento") != null) {
                    titularLicenciatarioNuevo.setNroDocumento(map.get("nrodocumento").toString());
                } else {
                    titularLicenciatarioNuevo.setNroDocumento(null);
                }

                if (map.get("tipodocumento") != null) {
                    titularLicenciatarioNuevo.setTipoDocumento(map.get("tipodocumento").toString());
                } else {
                    titularLicenciatarioNuevo.setTipoDocumento(null);
                }

                if (map.get("lugarexpedicion") != null) {

                    titularLicenciatarioNuevo.setLugarExpedicion(map.get("lugarexpedicion").toString());
                } else {
                    titularLicenciatarioNuevo.setLugarExpedicion(null);
                }

                if (map.get("pais") != null) {
                    titularLicenciatarioNuevo.setPais(map.get("pais").toString());
                } else {
                    titularLicenciatarioNuevo.setPais(null);
                }

                if (map.get("genero") != null) {
                    titularLicenciatarioNuevo.setGenero(map.get("genero").toString());
                } else {
                    titularLicenciatarioNuevo.setGenero(null);
                }

                if (map.get("solicituddepartamento") != null) {
                    titularLicenciatarioNuevo.setSolicitudDepartamento(map.get("solicituddepartamento").toString());
                } else {
                    titularLicenciatarioNuevo.setSolicitudDepartamento(null);
                }

                if (map.get("direccion") != null) {
                    titularLicenciatarioNuevo.setDireccion(map.get("direccion").toString());
                } else {
                    titularLicenciatarioNuevo.setDireccion(null);
                }

                if (map.get("telefono") != null) {

                    titularLicenciatarioNuevo.setTelefono(map.get("telefono").toString());
                } else {
                    titularLicenciatarioNuevo.setTelefono(null);
                }

                if (map.get("celular") != null) {
                    titularLicenciatarioNuevo.setCelular(map.get("celular").toString());
                } else {
                    titularLicenciatarioNuevo.setCelular(null);
                }

                if (map.get("email") != null) {
                    titularLicenciatarioNuevo.setEmail(map.get("email").toString());

                } else {
                    titularLicenciatarioNuevo.setEmail(null);
                }

                if (map.get("estado") != null) {
                    titularLicenciatarioNuevo.setEstado(map.get("estado").toString());

                } else {
                    titularLicenciatarioNuevo.setEstado(null);
                }

                if (map.get("dependenciamod") != null) {
                    titularLicenciatarioNuevo.setDependenciaMod(Integer.parseInt(map.get("dependenciamod").toString()));
                } else {
                    titularLicenciatarioNuevo.setDependenciaMod(null);
                }

                if (map.get("modificacion_id") != null) {
                    titularLicenciatarioNuevo.setModificacionId(Integer.parseInt(map.get("modificacion_id").toString()));
                } else {
                    titularLicenciatarioNuevo.setModificacionId(null);
                }

                if (map.get("id_padre") != null) {
                    titularLicenciatarioNuevo.setIdPadre(Long.parseLong(map.get("id_padre").toString()));
                } else {
                    titularLicenciatarioNuevo.setIdPadre(null);
                }

                //agregar titular licenciatario
                listaTitularLicenciatariosNuevos.add(titularLicenciatarioNuevo);
            }
            formularioPI103.setTiTularLicenciatarioNuevos(listaTitularLicenciatariosNuevos);

            //agregar el tipo signo
            List<ModTipoSignos> listaModTipoSignos = new ArrayList<ModTipoSignos>();
            List<Map<String, Object>> smTipoSignos = jdbcTemplate.queryForList("select * from formulario.modtiposigno where modificacion_id = ? and estado='AC'", modificaciones.getId());
            for (Map<String, Object> map : smTipoSignos) {

                ModTipoSignos modTipoSignos = new ModTipoSignos();

                if (map.get("tiposigno") != null) {
                    modTipoSignos.setTiposigno(map.get("tiposigno").toString());
                } else {
                    modTipoSignos.setTiposigno(null);
                }

                if (map.get("descripcionotro") != null) {
                    modTipoSignos.setDescripcionotro(map.get("descripcionotro").toString());
                } else {
                    modTipoSignos.setDescripcionotro(null);
                }
                listaModTipoSignos.add(modTipoSignos);
            }
            formularioPI103.setModTipoSignos(listaModTipoSignos);

            return formularioPI103;

        } catch (Exception e) {
            throw e;
        }
    }

}
