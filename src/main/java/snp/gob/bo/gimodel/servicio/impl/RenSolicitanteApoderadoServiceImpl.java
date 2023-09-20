/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.RenSolicitanteApoderado;
import snp.gob.bo.gimodel.entidad.RenDireccionDeNotificacion;
import snp.gob.bo.gimodel.entidad.RenSolicitudRenovacion;
import snp.gob.bo.gimodel.entidad.SigSolicitanteApoderado;
import snp.gob.bo.gimodel.enums.EnumEstado;
import snp.gob.bo.gimodel.enums.EnumTipoPersona;
import snp.gob.bo.gimodel.enums.EnumTipoTitular;
import snp.gob.bo.gimodel.mapper.RenDireccionDeNotificacionMapper;
import snp.gob.bo.gimodel.mapper.RenSolicitanteApoderadoMapper;
import snp.gob.bo.gimodel.mapper.SigSolicitanteApoderadoMapper;
import snp.gob.bo.gimodel.servicio.RenSolicitanteApoderadoService;
import snp.gob.bo.gimodel.solicitudes.entidades.Apoderados;
import snp.gob.bo.gimodel.solicitudes.entidades.Solicitantes;

/**
 *
 * @author Chano Rojas
 * @see RenRenovacionDominio
 * @see RenrenovacionServiceImpl
 * @version 1.0, 05/06/2016
 */
@Service("renSolicitanteApoderadoService")
public class RenSolicitanteApoderadoServiceImpl implements RenSolicitanteApoderadoService {

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
    public RenSolicitanteApoderado crudRenSolicitanteApoderado(RenSolicitanteApoderado renSolicitanteApoderado, int parametro) throws Exception {
        try {
            String SQL = "select * from crud_rensolicitanteapoderado("
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?);";

            RenSolicitanteApoderado renSoliciApoderado = (RenSolicitanteApoderado) jdbcTemplate.query(SQL, new RenSolicitanteApoderadoMapper(),
                    renSolicitanteApoderado.getIdsolicitanteapoderado(),
                    renSolicitanteApoderado.getIdsolicitudrenovacion(),
                    renSolicitanteApoderado.getIdlogtrans(),
                    renSolicitanteApoderado.getTipo_titular(),
                    renSolicitanteApoderado.getTipo_persona(),
                    renSolicitanteApoderado.getNombre_razonsocial(),
                    renSolicitanteApoderado.getPrimer_apellido(),
                    renSolicitanteApoderado.getSegundo_apellido(),
                    renSolicitanteApoderado.getNumero_documento(),
                    renSolicitanteApoderado.getTipo_documento(),
                    renSolicitanteApoderado.getLugar_expedicion(),
                    renSolicitanteApoderado.getPais(),
                    renSolicitanteApoderado.getGenero(),
                    renSolicitanteApoderado.getSolicitud_departamento(),
                    renSolicitanteApoderado.getPoder(),
                    renSolicitanteApoderado.getDireccion(),
                    renSolicitanteApoderado.getTelefono(),
                    renSolicitanteApoderado.getCelular(),
                    renSolicitanteApoderado.getEmail(),
                    renSolicitanteApoderado.getFax(),
                    renSolicitanteApoderado.getEstado(),
                    renSolicitanteApoderado.getIdSipi(),
                    parametro);
            return renSoliciApoderado;
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public List<RenSolicitanteApoderado> obtenerListadoRenSolicitanteApoderado(RenSolicitanteApoderado renSolicitanteApoderado, int parametro) throws Exception {
        try {
            String SQL = "select * from crud_rensolicitanteapoderado("
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?);";

            List<RenSolicitanteApoderado> listaRenSoliciApoderado = jdbcTemplate.query(SQL, new RenSolicitanteApoderadoMapper(),
                    renSolicitanteApoderado.getIdsolicitanteapoderado(),
                    renSolicitanteApoderado.getIdsolicitudrenovacion(),
                    renSolicitanteApoderado.getIdlogtrans(),
                    renSolicitanteApoderado.getTipo_titular(),
                    renSolicitanteApoderado.getTipo_persona(),
                    renSolicitanteApoderado.getNombre_razonsocial(),
                    renSolicitanteApoderado.getPrimer_apellido(),
                    renSolicitanteApoderado.getSegundo_apellido(),
                    renSolicitanteApoderado.getNumero_documento(),
                    renSolicitanteApoderado.getTipo_documento(),
                    renSolicitanteApoderado.getLugar_expedicion(),
                    renSolicitanteApoderado.getPais(),
                    renSolicitanteApoderado.getGenero(),
                    renSolicitanteApoderado.getSolicitud_departamento(),
                    renSolicitanteApoderado.getPoder(),
                    renSolicitanteApoderado.getDireccion(),
                    renSolicitanteApoderado.getTelefono(),
                    renSolicitanteApoderado.getCelular(),
                    renSolicitanteApoderado.getEmail(),
                    renSolicitanteApoderado.getFax(),
                    renSolicitanteApoderado.getEstado(),
                    renSolicitanteApoderado.getIdSipi(),
                    parametro);

            if (!listaRenSoliciApoderado.isEmpty()) {
                return listaRenSoliciApoderado;
            }
            return Collections.EMPTY_LIST;
        } catch (DataAccessException e) {
            throw e;
        }

    }

    @Override
    public RenSolicitanteApoderado crudDosRenSolicitanteApoderado(RenSolicitanteApoderado renSolicitanteApoderado, int parametro) throws Exception {
        try {
            String SQL = "select * from crud_rensolicitanteapoderado("
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?);";

            List<RenSolicitanteApoderado> listaRenSoliciApoderado = jdbcTemplate.query(SQL, new RenSolicitanteApoderadoMapper(),
                    renSolicitanteApoderado.getIdsolicitanteapoderado(),
                    renSolicitanteApoderado.getIdsolicitudrenovacion(),
                    renSolicitanteApoderado.getIdlogtrans(),
                    renSolicitanteApoderado.getTipo_titular(),
                    renSolicitanteApoderado.getTipo_persona(),
                    renSolicitanteApoderado.getNombre_razonsocial(),
                    renSolicitanteApoderado.getPrimer_apellido(),
                    renSolicitanteApoderado.getSegundo_apellido(),
                    renSolicitanteApoderado.getNumero_documento(),
                    renSolicitanteApoderado.getTipo_documento(),
                    renSolicitanteApoderado.getLugar_expedicion(),
                    renSolicitanteApoderado.getPais(),
                    renSolicitanteApoderado.getGenero(),
                    renSolicitanteApoderado.getSolicitud_departamento(),
                    renSolicitanteApoderado.getPoder(),
                    renSolicitanteApoderado.getDireccion(),
                    renSolicitanteApoderado.getTelefono(),
                    renSolicitanteApoderado.getCelular(),
                    renSolicitanteApoderado.getEmail(),
                    renSolicitanteApoderado.getFax(),
                    renSolicitanteApoderado.getEstado(),
                    renSolicitanteApoderado.getIdSipi(),
                    parametro);

            if (!listaRenSoliciApoderado.isEmpty()) {
                return listaRenSoliciApoderado.get(0);
            }
            return new RenSolicitanteApoderado();
        } catch (DataAccessException e) {
            throw e;
        }

    }

    @Override
    public List<RenSolicitanteApoderado> buscaSolicitanteApoderadoPoridSolicitudyTipoPersona(Long numeroSr, String tipoPersona) throws Exception {
        try {
            String SQL = "select * from obtiene_rensolicitanteapoderadoporidsolicitudytipopersona("
                    + "?,"
                    + "?);";
            List<RenSolicitanteApoderado> listaren = jdbcTemplate.query(SQL, new RenSolicitanteApoderadoMapper(),
                    numeroSr,
                    tipoPersona);
            if (!listaren.isEmpty()) {
                return listaren;
            } else {
                return new ArrayList<RenSolicitanteApoderado>();
            }
        } catch (DataAccessException e) {
            throw e;
        }

    }

    @Override
    public RenDireccionDeNotificacion buscarRenDireccionDeNotificacionXidsolicitudrenovacion(Long idSolicitudRenovacion) throws Exception {
        try {

            RenDireccionDeNotificacion direccionNotificacion = new RenDireccionDeNotificacion();

            String SQL = "select * from rendirecciondenotificacion where  idsolicitudrenovacion = ?;";
            if (!jdbcTemplate.query(SQL, new RenDireccionDeNotificacionMapper(), idSolicitudRenovacion).isEmpty()) {
                direccionNotificacion = (RenDireccionDeNotificacion) jdbcTemplate.queryForObject(SQL, new RenDireccionDeNotificacionMapper(), idSolicitudRenovacion);
                return direccionNotificacion;
            }
            return direccionNotificacion;
        } catch (DataAccessException e) {
            throw e;
        }

    }

    @Override
    public List<RenSolicitanteApoderado> buscaSolicitanteApoderadoPoridSolicitudyTipoPersonaRenovacion(Long idrenSolicitudRenovacion, String tipoPersona) throws Exception {
        try {
//            System.out.println("long"+idrenSolicitudRenovacion);
//            System.out.println("tipo"+tipoPersona);
            String SQL = "select * from rensolicitanteapoderado  where idsolicitudrenovacion =?   and tipo_persona= ? and estado='AC' ";
            List<RenSolicitanteApoderado> listaren = jdbcTemplate.query(SQL, new RenSolicitanteApoderadoMapper(),
                    idrenSolicitudRenovacion, tipoPersona);
            if (!listaren.isEmpty()) {
//                System.out.println("122111111111111111111111111111");
                return listaren;
            } else {
//                System.out.println("222222222222222222222222222222222");
                return new ArrayList<RenSolicitanteApoderado>();
            }
        } catch (DataAccessException e) {
            throw e;
        }

    }

    @Override
    public int encuentraPosicionListadoSolicitanteApoderado(List<RenSolicitanteApoderado> listadoSolicitanteApoderado, RenSolicitanteApoderado rensolicitanteApoderado) {
        int posicion = 0;
        int cont = 0;
        for (RenSolicitanteApoderado item : listadoSolicitanteApoderado) {
            if (!rensolicitanteApoderado.getTipo_titular().equals("N")) {
                if (item.getNombre_razonsocial().equals(rensolicitanteApoderado.getNombre_razonsocial())) {
                    posicion = cont;
                }
            } else {
                if (item.getNombre_razonsocial().equals(rensolicitanteApoderado.getNombre_razonsocial())
                        && item.getPrimer_apellido().equals(rensolicitanteApoderado.getPrimer_apellido())
                        && item.getSegundo_apellido().equals(rensolicitanteApoderado.getSegundo_apellido())) {
                    posicion = cont;
                }
            }
            cont++;
        }
        return posicion;
    }

    @Override
    public void modificaListaSolicitanteApoderado(RenSolicitudRenovacion rensolicitudRenovacion, List<RenSolicitanteApoderado> listaSolicitanteApoderado, String tipoSoliOApo) throws Exception {
        try {
            int parametro = 2;
            int parametroGuardar = 1;
            int sw = 0;
            List<RenSolicitanteApoderado> listaSolicitantesBase;
//            System.out.println("rensolicitudREnovacionVista"+rensolicitudRenovacion.getIdsolicitudrenovacion());
            listaSolicitantesBase = buscaSolicitanteApoderadoPoridSolicitudyTipoPersonaRenovacion(rensolicitudRenovacion.getIdsolicitudrenovacion(), "SOLI");
//            System.out.println("listabaseeeeeeeeee"+listaSolicitantesBase.size());
//            System.out.println("listavista"+listaSolicitanteApoderado.size()+"*/*/*/*/*/*/*/*/*/*/*/*/*//*/*//*/*/*/*/*");
            for (RenSolicitanteApoderado renSolicitanteApoderado : listaSolicitantesBase) {
                sw = 0;
                for (RenSolicitanteApoderado renSolicitanteApoderadoVista : listaSolicitanteApoderado) {
                    if (renSolicitanteApoderado.getIdsolicitanteapoderado().equals(renSolicitanteApoderadoVista.getIdsolicitanteapoderado())) {
//                        System.out.println("11111111111111");
                        renSolicitanteApoderadoVista.setIdlogtrans(2L);
                        crudDosRenSolicitanteApoderado(renSolicitanteApoderadoVista, 2);
                        sw = 1;
                    } else {
//                        System.out.println("no modifico nadaaaaaaaaaaaaaaaaaaa");
                    }
                }
                if (sw == 0) {
                    renSolicitanteApoderado.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                    crudDosRenSolicitanteApoderado(renSolicitanteApoderado, 2);
                }
            }
            for (RenSolicitanteApoderado renSolicitanteApoderadoNuevo : listaSolicitanteApoderado) {
                if (renSolicitanteApoderadoNuevo.getIdsolicitanteapoderado() == null) {
                    renSolicitanteApoderadoNuevo.setIdsolicitudrenovacion(rensolicitudRenovacion.getIdsolicitudrenovacion());
                    renSolicitanteApoderadoNuevo.setIdlogtrans(1L);
                    renSolicitanteApoderadoNuevo.setEstado(EnumEstado.ACTIVO.getCodigo());
                    crudDosRenSolicitanteApoderado(renSolicitanteApoderadoNuevo, 1);
                }
            }
            listaSolicitantesBase = buscaSolicitanteApoderadoPoridSolicitudyTipoPersonaRenovacion(rensolicitudRenovacion.getIdsolicitudrenovacion(), "SOLI");
//            System.out.println("listaaaaaaaaaaaaaaaa"+listaSolicitantesBase.size());

        } catch (Exception ex) {
            Logger.getLogger(RenSolicitanteApoderadoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

    }

    @Override
    public void modificaListaApoderado(RenSolicitudRenovacion rensolicitudRenovacion, List<RenSolicitanteApoderado> listaSolicitanteApoderado, String tipoSoliOApo) throws Exception {
        try {
            System.out.println("apoderado");
            int parametro = 2;
            int parametroGuardar = 1;
            int sw = 0;
            List<RenSolicitanteApoderado> listaSolicitantesBase;
//            System.out.println("rensolicitudREnovacionVista"+rensolicitudRenovacion.getIdsolicitudrenovacion());
            listaSolicitantesBase = buscaSolicitanteApoderadoPoridSolicitudyTipoPersonaRenovacion(rensolicitudRenovacion.getIdsolicitudrenovacion(), "APOD");
//            System.out.println("listabaseeeeeeeeee"+listaSolicitantesBase.size());
            for (RenSolicitanteApoderado renSolicitanteApoderado : listaSolicitantesBase) {
                sw = 0;
                for (RenSolicitanteApoderado renSolicitanteApoderadoVista : listaSolicitanteApoderado) {
                    if (renSolicitanteApoderado.getIdsolicitanteapoderado().equals(renSolicitanteApoderadoVista.getIdsolicitanteapoderado())) {
                        renSolicitanteApoderadoVista.setIdlogtrans(2L);
                        crudDosRenSolicitanteApoderado(renSolicitanteApoderadoVista, parametro);
                        sw = 1;
                    }
                }
                if (sw == 0) {
                    renSolicitanteApoderado.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                    crudDosRenSolicitanteApoderado(renSolicitanteApoderado, parametro);
                }
            }
            for (RenSolicitanteApoderado renSolicitanteApoderadoNuevo : listaSolicitanteApoderado) {
                if (renSolicitanteApoderadoNuevo.getIdsolicitanteapoderado() == null) {
                    renSolicitanteApoderadoNuevo.setIdsolicitudrenovacion(rensolicitudRenovacion.getIdsolicitudrenovacion());
                    renSolicitanteApoderadoNuevo.setIdlogtrans(1L);
                    renSolicitanteApoderadoNuevo.setEstado(EnumEstado.ACTIVO.getCodigo());
                    crudDosRenSolicitanteApoderado(renSolicitanteApoderadoNuevo, parametroGuardar);
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(RenSolicitanteApoderadoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

    }

    @Override
    public RenSolicitanteApoderado obtenerRenSolicitanteApoderado(Long idSolicitanteApoderado, String tipoPersona) throws Exception {
        try {
            String SQL = "select * from rensolicitanteapoderado "
                    + " where idsolicitanteapoderado = ? "
                    + " and tipo_persona = ? "
                    + " and estado = 'AC' ";
            RenSolicitanteApoderado renSolicitanteApoderado = jdbcTemplate.queryForObject(SQL,
                    new RenSolicitanteApoderadoMapper(),
                    idSolicitanteApoderado, tipoPersona);

            return renSolicitanteApoderado;

        } catch (EmptyResultDataAccessException e) {
            return new RenSolicitanteApoderado();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void modificarListaSolicitanteSubsanacion(Long idSolicitudRenovacion, List<Solicitantes> listaSolicitantesSipi, String tipoPersona, Long IdLogTrans) throws Exception {
        try {
            int operacionAdicionar = 1;
            int operacionModificar = 2;
            int swExiste = 0;

            // Recuperamos el solicitante desde la base de datos
            List<RenSolicitanteApoderado> listaSolicitantesHidra = buscarSolicitanteApoderadoPoridSolicitudyTipoPersona(idSolicitudRenovacion, tipoPersona);

            if (!listaSolicitantesHidra.isEmpty()) {
                if (!listaSolicitantesSipi.isEmpty()) {
                    //modificar
                    for (RenSolicitanteApoderado solicitanteHidra : listaSolicitantesHidra) {
                        swExiste = 0;
                        for (Solicitantes solicitanteSipi : listaSolicitantesSipi) {
                            if (solicitanteHidra.getIdSipi().equals(solicitanteSipi.getIdPadre())) {

                                solicitanteHidra.setTipo_titular(solicitanteSipi.getTipoPersona());
                                solicitanteHidra.setNombre_razonsocial(solicitanteSipi.getNombreRazonSocial());
                                solicitanteHidra.setPrimer_apellido(solicitanteSipi.getPrimerApellido());
                                solicitanteHidra.setSegundo_apellido(solicitanteSipi.getSegundoApellido());
                                solicitanteHidra.setNumero_documento(solicitanteSipi.getNumeroDocumento());
                                solicitanteHidra.setTipo_documento(solicitanteSipi.getTipoDocumento());
                                solicitanteHidra.setGenero(solicitanteSipi.getGenero());
                                solicitanteHidra.setLugar_expedicion(solicitanteSipi.getLugarExpedicion());
                                solicitanteHidra.setPais(solicitanteSipi.getPais());
                                solicitanteHidra.setSolicitud_departamento(solicitanteSipi.getDepartamentoSolicitud());
                                solicitanteHidra.setTelefono(solicitanteSipi.getTelefono());
                                solicitanteHidra.setCelular(solicitanteSipi.getCelular());
                                solicitanteHidra.setDireccion(solicitanteSipi.getDomicilio());
                                solicitanteHidra.setEmail(solicitanteSipi.getCorreoElectronico());
                                solicitanteHidra.setIdlogtrans(IdLogTrans);
                                solicitanteHidra.setIdSipi(solicitanteSipi.getId());
                                crudDosRenSolicitanteApoderado(solicitanteHidra, operacionModificar);
                                swExiste = 1;
                                break;
                            }
                        }
                        if (swExiste == 0) {
                            //eliminar
                            solicitanteHidra.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                            solicitanteHidra.setIdlogtrans(IdLogTrans);
                            crudDosRenSolicitanteApoderado(solicitanteHidra, operacionModificar);
                        }
                    }

                    for (Solicitantes solicitanteSipi : listaSolicitantesSipi) {
                        swExiste = 0;
                        for (RenSolicitanteApoderado solicitanteHidra : listaSolicitantesHidra) {
                            if (solicitanteHidra.getIdSipi().equals(solicitanteSipi.getId())) {
                                swExiste = 1;
                                break;
                            }
                        }
                        if (swExiste == 0) {
                            RenSolicitanteApoderado solicitanteNuevo = new RenSolicitanteApoderado();
                            solicitanteNuevo.setIdsolicitudrenovacion(idSolicitudRenovacion);
                            solicitanteNuevo.setNombre_razonsocial(solicitanteSipi.getNombreRazonSocial());
                            solicitanteNuevo.setPrimer_apellido(solicitanteSipi.getPrimerApellido());
                            solicitanteNuevo.setSegundo_apellido(solicitanteSipi.getSegundoApellido());
                            solicitanteNuevo.setNumero_documento(solicitanteSipi.getNumeroDocumento());
                            solicitanteNuevo.setTipo_documento(solicitanteSipi.getTipoDocumento());
                            solicitanteNuevo.setGenero(solicitanteSipi.getGenero());
                            solicitanteNuevo.setLugar_expedicion(solicitanteSipi.getLugarExpedicion());
                            solicitanteNuevo.setPais(solicitanteSipi.getPais());
                            solicitanteNuevo.setSolicitud_departamento(solicitanteSipi.getDepartamentoSolicitud());
                            solicitanteNuevo.setTelefono(solicitanteSipi.getTelefono());
                            solicitanteNuevo.setCelular(solicitanteSipi.getCelular());
                            solicitanteNuevo.setPoder(null);
                            solicitanteNuevo.setDireccion(solicitanteSipi.getDomicilio());
                            solicitanteNuevo.setEmail(solicitanteSipi.getCorreoElectronico());
                            solicitanteNuevo.setFax(null);
                            solicitanteNuevo.setIdlogtrans(IdLogTrans);
                            if (solicitanteSipi.getTipoPersona().equals(EnumTipoTitular.JURIDICO.getCodigo())) {
                                solicitanteNuevo.setTipo_titular(EnumTipoTitular.JURIDICO.getCodigo());
                            } else {
                                solicitanteNuevo.setTipo_titular(EnumTipoTitular.NATURAL.getCodigo());
                            }
                            solicitanteNuevo.setTipo_persona(EnumTipoPersona.SOLICITANTE.getCodigo());
                            solicitanteNuevo.setEstado(EnumEstado.ACTIVO.getCodigo());
                            solicitanteNuevo.setIdSipi(solicitanteSipi.getId());
                            crudDosRenSolicitanteApoderado(solicitanteNuevo, operacionAdicionar);
                        }
                    }
                } else {
                    //eliminar
                    for (RenSolicitanteApoderado solicitanteHidra : listaSolicitantesHidra) {
                        solicitanteHidra.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                        solicitanteHidra.setIdlogtrans(IdLogTrans);
                        crudDosRenSolicitanteApoderado(solicitanteHidra, operacionModificar);
                    }
                }
            } else {
                //adicionar
                RenSolicitanteApoderado solicitanteNuevo = new RenSolicitanteApoderado();
                for (Solicitantes solicitanteSipi : listaSolicitantesSipi) {

                    solicitanteNuevo.setIdsolicitudrenovacion(idSolicitudRenovacion);
                    solicitanteNuevo.setNombre_razonsocial(solicitanteSipi.getNombreRazonSocial());
                    solicitanteNuevo.setPrimer_apellido(solicitanteSipi.getPrimerApellido());
                    solicitanteNuevo.setSegundo_apellido(solicitanteSipi.getSegundoApellido());
                    solicitanteNuevo.setNumero_documento(solicitanteSipi.getNumeroDocumento());
                    solicitanteNuevo.setTipo_documento(solicitanteSipi.getTipoDocumento());
                    solicitanteNuevo.setGenero(solicitanteSipi.getGenero());
                    solicitanteNuevo.setLugar_expedicion(solicitanteSipi.getLugarExpedicion());
                    solicitanteNuevo.setPais(solicitanteSipi.getPais());
                    solicitanteNuevo.setSolicitud_departamento(solicitanteSipi.getDepartamentoSolicitud());
                    solicitanteNuevo.setTelefono(solicitanteSipi.getTelefono());
                    solicitanteNuevo.setCelular(solicitanteSipi.getCelular());
                    solicitanteNuevo.setPoder(null);
                    solicitanteNuevo.setDireccion(solicitanteSipi.getDomicilio());
                    solicitanteNuevo.setEmail(solicitanteSipi.getCorreoElectronico());
                    solicitanteNuevo.setFax(null);
                    solicitanteNuevo.setIdlogtrans(IdLogTrans);
                    if (solicitanteSipi.getTipoPersona().equals(EnumTipoTitular.JURIDICO.getCodigo())) {
                        solicitanteNuevo.setTipo_titular(EnumTipoTitular.JURIDICO.getCodigo());
                    } else {
                        solicitanteNuevo.setTipo_titular(EnumTipoTitular.NATURAL.getCodigo());
                    }
                    solicitanteNuevo.setTipo_persona(EnumTipoPersona.SOLICITANTE.getCodigo());
                    solicitanteNuevo.setEstado(EnumEstado.ACTIVO.getCodigo());
                    solicitanteNuevo.setIdSipi(solicitanteSipi.getId());
                    crudDosRenSolicitanteApoderado(solicitanteNuevo, operacionAdicionar);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void modificarListaApoderadoSubsanacion(Long idSolicitudRenovacion, List<Apoderados> listaApoderadosSipi, String tipoPersona, Long IdLogTrans) throws Exception {
        try {
            int parametroAdicionar = 1;
            int parametroModificar = 2;
            int swExiste = 0;

            // Recuperamos al apoderado desde la base de datos
            List<RenSolicitanteApoderado> listaApoderadosHidra = buscarSolicitanteApoderadoPoridSolicitudyTipoPersona(idSolicitudRenovacion, tipoPersona);

            if (!listaApoderadosHidra.isEmpty()) {
                if (!listaApoderadosSipi.isEmpty()) {
                    //modificar
                    for (RenSolicitanteApoderado apoderadoHidra : listaApoderadosHidra) {
                        swExiste = 0;
                        for (Apoderados apoderadoSipi : listaApoderadosSipi) {
                            if (apoderadoHidra.getIdSipi().equals(apoderadoSipi.getIdPadre())) {

                                apoderadoHidra.setNombre_razonsocial(apoderadoSipi.getNombres());
                                apoderadoHidra.setNombre_razonsocial(apoderadoSipi.getNombres());
                                apoderadoHidra.setPrimer_apellido(apoderadoSipi.getPrimerApellido());
                                apoderadoHidra.setSegundo_apellido(apoderadoSipi.getSegundoApellido());
                                apoderadoHidra.setNumero_documento(apoderadoSipi.getNumeroDocumento());
                                apoderadoHidra.setTipo_documento(apoderadoSipi.getTipoDocumento());
                                apoderadoHidra.setGenero(apoderadoSipi.getGenero());
                                apoderadoHidra.setLugar_expedicion(apoderadoSipi.getLugarExpedicion());
                                apoderadoHidra.setTelefono(apoderadoSipi.getTelefono());
                                apoderadoHidra.setCelular(apoderadoSipi.getCelular());
                                apoderadoHidra.setDireccion(apoderadoSipi.getDomicilio());
                                apoderadoHidra.setEmail(apoderadoSipi.getCorreoElectronico());
                                apoderadoHidra.setPoder(apoderadoSipi.getNumeroPoder());
                                apoderadoHidra.setIdlogtrans(IdLogTrans);
                                apoderadoHidra.setIdSipi(apoderadoSipi.getId());
                                crudDosRenSolicitanteApoderado(apoderadoHidra, parametroModificar);
                                swExiste = 1;
                                break;
                            }
                        }
                        if (swExiste == 0) {
                            //eliminar
                            apoderadoHidra.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                            apoderadoHidra.setIdlogtrans(IdLogTrans);
                            crudDosRenSolicitanteApoderado(apoderadoHidra, parametroModificar);

                        }
                    }

                    for (Apoderados apoderadoSipi : listaApoderadosSipi) {
                        swExiste = 0;
                        for (RenSolicitanteApoderado apoderadoHidra : listaApoderadosHidra) {
                            if (apoderadoHidra.getIdSipi().equals(apoderadoSipi.getId())) {
                                swExiste = 1;
                                break;
                            }
                        }
                        if (swExiste == 0) {
                            //adicionar
                            RenSolicitanteApoderado apoderadoNuevo = new RenSolicitanteApoderado();
                            apoderadoNuevo.setIdsolicitudrenovacion(idSolicitudRenovacion);
                            apoderadoNuevo.setNombre_razonsocial(apoderadoSipi.getNombres());
                            apoderadoNuevo.setPrimer_apellido(apoderadoSipi.getPrimerApellido());
                            apoderadoNuevo.setSegundo_apellido(apoderadoSipi.getSegundoApellido());
                            apoderadoNuevo.setNumero_documento(apoderadoSipi.getNumeroDocumento());
                            apoderadoNuevo.setTipo_documento(apoderadoSipi.getTipoDocumento());
                            apoderadoNuevo.setGenero(apoderadoSipi.getGenero());
                            apoderadoNuevo.setLugar_expedicion(apoderadoSipi.getLugarExpedicion());
                            apoderadoNuevo.setTelefono(apoderadoSipi.getTelefono());
                            apoderadoNuevo.setCelular(apoderadoSipi.getCelular());
                            apoderadoNuevo.setDireccion(apoderadoSipi.getDomicilio());
                            apoderadoNuevo.setEmail(apoderadoSipi.getCorreoElectronico());
                            apoderadoNuevo.setIdlogtrans(IdLogTrans);
                            apoderadoNuevo.setTipo_titular(EnumTipoTitular.NATURAL.getCodigo());
                            apoderadoNuevo.setTipo_persona(EnumTipoPersona.APODERADO.getCodigo());
                            apoderadoNuevo.setEstado(EnumEstado.ACTIVO.getCodigo());
                            apoderadoNuevo.setIdSipi(apoderadoSipi.getId());
                            crudDosRenSolicitanteApoderado(apoderadoNuevo, parametroAdicionar);
                        }
                    }

                } else {
                    //eliminar
                    for (RenSolicitanteApoderado apoderadoHidra : listaApoderadosHidra) {
                        apoderadoHidra.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                        apoderadoHidra.setIdlogtrans(IdLogTrans);
                        crudDosRenSolicitanteApoderado(apoderadoHidra, parametroModificar);
                    }
                }
            } else {
                //adicionar
                RenSolicitanteApoderado apoderadoNuevo = new RenSolicitanteApoderado();
                for (Apoderados apoderadoSipi : listaApoderadosSipi) {

                    apoderadoNuevo.setIdsolicitudrenovacion(idSolicitudRenovacion);
                    apoderadoNuevo.setNombre_razonsocial(apoderadoSipi.getNombres());
                    apoderadoNuevo.setPrimer_apellido(apoderadoSipi.getPrimerApellido());
                    apoderadoNuevo.setSegundo_apellido(apoderadoSipi.getSegundoApellido());
                    apoderadoNuevo.setNumero_documento(apoderadoSipi.getNumeroDocumento());
                    apoderadoNuevo.setTipo_documento(apoderadoSipi.getTipoDocumento());
                    apoderadoNuevo.setGenero(apoderadoSipi.getGenero());
                    apoderadoNuevo.setLugar_expedicion(apoderadoSipi.getLugarExpedicion());
                    apoderadoNuevo.setTelefono(apoderadoSipi.getTelefono());
                    apoderadoNuevo.setCelular(apoderadoSipi.getCelular());
                    apoderadoNuevo.setDireccion(apoderadoSipi.getDomicilio());
                    apoderadoNuevo.setEmail(apoderadoSipi.getCorreoElectronico());
                    apoderadoNuevo.setIdlogtrans(IdLogTrans);
                    apoderadoNuevo.setTipo_titular(EnumTipoTitular.NATURAL.getCodigo());
                    apoderadoNuevo.setTipo_persona(EnumTipoPersona.APODERADO.getCodigo());
                    apoderadoNuevo.setEstado(EnumEstado.ACTIVO.getCodigo());
                    apoderadoNuevo.setIdSipi(apoderadoSipi.getId());
                    crudDosRenSolicitanteApoderado(apoderadoNuevo, parametroAdicionar);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<RenSolicitanteApoderado> buscarSolicitanteApoderadoPoridSolicitudyTipoPersona(Long idSolicitudRenovacion, String tipoPersona) throws Exception {
        try {
            String SQL = "select * from obtiene_rensolicitanteapoderadoporidsolicitudytipopersona("
                    + "?,"
                    + "?);";
            List<RenSolicitanteApoderado> listaRen = jdbcTemplate.query(SQL, new RenSolicitanteApoderadoMapper(),
                    idSolicitudRenovacion,
                    tipoPersona);
            if (!listaRen.isEmpty()) {
                return listaRen;
            } else {
                return new ArrayList<RenSolicitanteApoderado>();
            }
        } catch (Exception e) {
            throw e;
        }

    }

}
