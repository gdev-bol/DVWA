/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import snp.gob.bo.gimodel.entidad.ModSolicitanteApoderado;
import snp.gob.bo.gimodel.entidad.ObjetoEliminadoGenerico;
import snp.gob.bo.gimodel.enums.EnumEstado;
import snp.gob.bo.gimodel.enums.EnumTipoTitular;
import snp.gob.bo.gimodel.enums.EnumTipoPersona;

import snp.gob.bo.gimodel.mapper.ModSolicitanteApoderadoMapper;
import snp.gob.bo.gimodel.mapper.ObjetoEliminadoGenericoMapper;

import snp.gob.bo.gimodel.servicio.ModSolicitanteApoderadoService;
import snp.gob.bo.gimodel.solicitudes.entidades.Apoderados;
import snp.gob.bo.gimodel.solicitudes.entidades.Solicitantes;

/**
 *
 * @author susana
 */
@Service("modSolicitanteApoderadoService")
public class ModSolicitanteApoderadoServiceImpl implements ModSolicitanteApoderadoService {

//    @Autowired
//    private DatoContactoService datoContactoService;
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    @Override
    public void setDataSource(DataSource dataSource) {
        try {
            this.dataSource = dataSource;
            this.jdbcTemplate = new JdbcTemplate(dataSource);
        } catch (Exception e) {
            throw e;
        }
    }

    //@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Override
    public ModSolicitanteApoderado guardar_modificar_listar_SolicitanteApoderado(ModSolicitanteApoderado modsolicitanteapoderado, Integer operacion) {

        String SQL = "select * from crud_modsolicitanteapoderado(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        ModSolicitanteApoderado modSolicitanteApoderado = (ModSolicitanteApoderado) jdbcTemplate.queryForObject(SQL, new ModSolicitanteApoderadoMapper(),
                modsolicitanteapoderado.getIdsolicitanteapoderado(),
                modsolicitanteapoderado.getIdmodificacion(),
                modsolicitanteapoderado.getIdLogTrans(),
                modsolicitanteapoderado.getTipo_titular(),
                modsolicitanteapoderado.getTipo_persona(),
                modsolicitanteapoderado.getNombre_razonsocial(),
                modsolicitanteapoderado.getPrimer_apellido(),
                modsolicitanteapoderado.getSegundo_apellido(),
                modsolicitanteapoderado.getNumero_documento(),
                modsolicitanteapoderado.getTipo_documento(),
                modsolicitanteapoderado.getLugar_expedicion(),
                modsolicitanteapoderado.getPais(),
                modsolicitanteapoderado.getGenero(),
                modsolicitanteapoderado.getSolicitud_departamento(),
                modsolicitanteapoderado.getPoder(),
                modsolicitanteapoderado.getDireccion(),
                modsolicitanteapoderado.getTelefono(),
                modsolicitanteapoderado.getCelular(),
                modsolicitanteapoderado.getEmail(),
                modsolicitanteapoderado.getFax(),
                modsolicitanteapoderado.getEstado(),
                modsolicitanteapoderado.getIdSipi(),
                operacion
        );
        return modSolicitanteApoderado;
    }

    @Override
    public List<ModSolicitanteApoderado> listadoSolicitanteXidmodificacion(Long idmodificacion) {
        String SQL = "select * from lista_modsolicitanteapoderado_idmodificacionsoli(?);";
        List<ModSolicitanteApoderado> listaSolicitanteApoderado = jdbcTemplate.query(SQL, new ModSolicitanteApoderadoMapper(), idmodificacion);
        if (!listaSolicitanteApoderado.isEmpty()) {
            return listaSolicitanteApoderado;
        }
        return new ArrayList<ModSolicitanteApoderado>();
    }

    @Override
    public List<ModSolicitanteApoderado> listadoApoderadoXidmodificacion(Long idmodificacion) {
        String SQL = "select * from lista_modsolicitanteapoderado_idmodificacion(?);";
        List<ModSolicitanteApoderado> listaApoderado = jdbcTemplate.query(SQL, new ModSolicitanteApoderadoMapper(), idmodificacion);
        if (!listaApoderado.isEmpty()) {
            return listaApoderado;
        }
        return new ArrayList<ModSolicitanteApoderado>();
    }

    @Override
    public int encuentraPosicionListadoSolicitanteApoderado(List<ModSolicitanteApoderado> listadoSolicitanteApoderado, ModSolicitanteApoderado smsolicitanteApoderado) {
        int posicion = 0;
        int cont = 0;
        for (ModSolicitanteApoderado item : listadoSolicitanteApoderado) {

            if (!smsolicitanteApoderado.getTipo_titular().equals(EnumTipoTitular.NATURAL.getCodigo())) {
                if (item.getNombre_razonsocial().equals(smsolicitanteApoderado.getNombre_razonsocial())) {

                    posicion = cont;
                }
            } else {

                if (smsolicitanteApoderado.getPrimer_apellido() != null && smsolicitanteApoderado.getSegundo_apellido() != null) {
                    if (item.getNombre_razonsocial().equals(smsolicitanteApoderado.getNombre_razonsocial())
                            && item.getPrimer_apellido().equals(smsolicitanteApoderado.getPrimer_apellido())
                            && item.getSegundo_apellido().equals(smsolicitanteApoderado.getSegundo_apellido())) {
                        posicion = cont;
                    }
                } else if (smsolicitanteApoderado.getPrimer_apellido() != null) {
                    if (item.getNombre_razonsocial().equals(smsolicitanteApoderado.getNombre_razonsocial())
                            && item.getPrimer_apellido().equals(smsolicitanteApoderado.getPrimer_apellido())) {
                        posicion = cont;
                    }
                } else {
                    if (item.getNombre_razonsocial().equals(smsolicitanteApoderado.getNombre_razonsocial())) {
                        posicion = cont;
                    }
                }
            }

            cont++;
        }
        return posicion;
    }

    @Override
    public void guardaListaSolicitantes(List<ModSolicitanteApoderado> listadoSolicitanteApoderado, Long idmodificacion, Long idLogTrans) {
        for (ModSolicitanteApoderado item : listadoSolicitanteApoderado) {
            item.setIdmodificacion(idmodificacion);
            item.setIdLogTrans(idLogTrans);
            item.setEstado(EnumEstado.ACTIVO.getCodigo());
            guardar_modificar_listar_SolicitanteApoderado(item, 1);
        }
    }

    @Override
    public void modificarListaSolicitanteApoderado(List<ModSolicitanteApoderado> listadoSolicitanteApoderado, Long idmodificacion, String tipo, Long idLogTrans) {

        filtrarListaSolicitanteApoderado(listadoSolicitanteApoderado, idmodificacion, tipo);
        for (ModSolicitanteApoderado item : listadoSolicitanteApoderado) {
            if (item.getIdsolicitanteapoderado() != null) {
                guardar_modificar_listar_SolicitanteApoderado(item, 2);
            } else {
                item.setIdmodificacion(idmodificacion);
                item.setIdLogTrans(idLogTrans);
                item.setEstado(EnumEstado.ACTIVO.getCodigo());
                guardar_modificar_listar_SolicitanteApoderado(item, 1);
            }
        }
    }

    @Override
    public void modificarListaSolicitanteSubsanacion(List<Solicitantes> listaSolicitantesSipi, Long idModificacion, Long idLogTrans) {

        try {
            int operacionAdicionar = 1;
            int operacionModificar = 2;
            int swExiste = 0;

            // Recuperamos el solicitante desde la base de datos
            List<ModSolicitanteApoderado> listaSolicitantesHidra = listadoSolicitanteXidmodificacion(idModificacion);

            if (!listaSolicitantesHidra.isEmpty()) {
                if (!listaSolicitantesSipi.isEmpty()) {
                    //modificar
                    for (ModSolicitanteApoderado solicitanteHidra : listaSolicitantesHidra) {
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
                                solicitanteHidra.setIdLogTrans(idLogTrans);
                                solicitanteHidra.setIdSipi(solicitanteSipi.getId());
                                guardar_modificar_listar_SolicitanteApoderado(solicitanteHidra, operacionModificar);
                                swExiste = 1;
                                break;
                            }
                        }
                        if (swExiste == 0) {
                            //eliminar
                            solicitanteHidra.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                            solicitanteHidra.setIdLogTrans(idLogTrans);
                            guardar_modificar_listar_SolicitanteApoderado(solicitanteHidra, operacionModificar);
                        }
                    }

                    for (Solicitantes solicitanteSipi : listaSolicitantesSipi) {
                        swExiste = 0;
                        for (ModSolicitanteApoderado solicitanteHidra : listaSolicitantesHidra) {
                            if (solicitanteHidra.getIdSipi().equals(solicitanteSipi.getId())) {

                                swExiste = 1;
                                break;
                            }
                        }
                        if (swExiste == 0) {
                            //adicionar
                            ModSolicitanteApoderado solicitanteNuevo = new ModSolicitanteApoderado();
                            solicitanteNuevo.setIdmodificacion(idModificacion);
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
                            solicitanteNuevo.setDireccion(solicitanteSipi.getDomicilio());
                            solicitanteNuevo.setEmail(solicitanteSipi.getCorreoElectronico());
                            solicitanteNuevo.setIdLogTrans(idLogTrans);
                            if (solicitanteSipi.getTipoPersona().equals(EnumTipoTitular.JURIDICO.getCodigo())) {
                                solicitanteNuevo.setTipo_titular(EnumTipoTitular.JURIDICO.getCodigo());
                            } else {
                                solicitanteNuevo.setTipo_titular(EnumTipoTitular.NATURAL.getCodigo());
                            }
                            solicitanteNuevo.setTipo_persona(EnumTipoPersona.SOLICITANTE.getCodigo());
                            solicitanteNuevo.setEstado(EnumEstado.ACTIVO.getCodigo());
                            solicitanteNuevo.setIdSipi(solicitanteSipi.getId());
                            guardar_modificar_listar_SolicitanteApoderado(solicitanteNuevo, operacionAdicionar);
                        }
                    }
                } else {
                    //eliminar
                    for (ModSolicitanteApoderado solicitanteHidra : listaSolicitantesHidra) {
                        solicitanteHidra.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                        solicitanteHidra.setIdLogTrans(idLogTrans);
                        guardar_modificar_listar_SolicitanteApoderado(solicitanteHidra, operacionModificar);
                    }
                }
            } else {
                //adicionar
                ModSolicitanteApoderado solicitanteNuevo = new ModSolicitanteApoderado();
                for (Solicitantes solicitanteSipi : listaSolicitantesSipi) {

                    //Agregar un nuevo Solicitante
                    solicitanteNuevo.setIdmodificacion(idModificacion);
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
                    solicitanteNuevo.setDireccion(solicitanteSipi.getDomicilio());
                    solicitanteNuevo.setEmail(solicitanteSipi.getCorreoElectronico());
                    solicitanteNuevo.setIdLogTrans(idLogTrans);
                    if (solicitanteSipi.getTipoPersona().equals(EnumTipoTitular.JURIDICO.getCodigo())) {
                        solicitanteNuevo.setTipo_titular(EnumTipoTitular.JURIDICO.getCodigo());
                    } else {
                        solicitanteNuevo.setTipo_titular(EnumTipoTitular.NATURAL.getCodigo());
                    }
                    solicitanteNuevo.setTipo_persona(EnumTipoPersona.SOLICITANTE.getCodigo());
                    solicitanteNuevo.setEstado(EnumEstado.ACTIVO.getCodigo());
                    solicitanteNuevo.setIdSipi(solicitanteSipi.getId());
                    guardar_modificar_listar_SolicitanteApoderado(solicitanteNuevo, operacionAdicionar);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void modificarListaApoderadoSubsanacion(List<Apoderados> listaApoderadosSipi, Long idModificacion, Long idLogTrans) {

        try {
            int operacionAdicionar = 1;
            int operacionModificar = 2;
            int swExiste = 0;

            // Recuperamos el apoderado desde la base de datos
            List<ModSolicitanteApoderado> listaApoderadosHidra = listadoApoderadoXidmodificacion(idModificacion);

            if (!listaApoderadosHidra.isEmpty()) {
                if (!listaApoderadosSipi.isEmpty()) {
                    //modificar
                    for (ModSolicitanteApoderado apoderadoHidra : listaApoderadosHidra) {
                        swExiste = 0;
                        for (Apoderados apoderadoSipi : listaApoderadosSipi) {
                            if (apoderadoHidra.getIdSipi().equals(apoderadoSipi.getIdPadre())) {
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
                                apoderadoHidra.setIdLogTrans(idLogTrans);
                                apoderadoHidra.setIdSipi(apoderadoSipi.getId());
                                guardar_modificar_listar_SolicitanteApoderado(apoderadoHidra, operacionModificar);
                                swExiste = 1;
                                break;
                            }
                        }
                        if (swExiste == 0) {
                            //eliminar
                            apoderadoHidra.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                            apoderadoHidra.setIdLogTrans(idLogTrans);
                            guardar_modificar_listar_SolicitanteApoderado(apoderadoHidra, operacionModificar);
                        }
                    }
                    for (Apoderados apoderadoSipi : listaApoderadosSipi) {
                        swExiste = 0;
                        for (ModSolicitanteApoderado apoderadoHidra : listaApoderadosHidra) {
                            if (apoderadoHidra.getIdSipi().equals(apoderadoSipi.getId())) {

                                swExiste = 1;
                                break;
                            }
                        }
                        if (swExiste == 0) {
                            //adicionar
                            ModSolicitanteApoderado apoderadoNuevo = new ModSolicitanteApoderado();
                            apoderadoNuevo.setIdmodificacion(idModificacion);
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
                            apoderadoNuevo.setIdLogTrans(idLogTrans);
                            apoderadoNuevo.setTipo_titular(EnumTipoTitular.NATURAL.getCodigo());
                            apoderadoNuevo.setTipo_persona(EnumTipoPersona.APODERADO.getCodigo());
                            apoderadoNuevo.setEstado(EnumEstado.ACTIVO.getCodigo());
                            apoderadoNuevo.setIdSipi(apoderadoSipi.getId());
                            guardar_modificar_listar_SolicitanteApoderado(apoderadoNuevo, operacionAdicionar);
                        }
                    }

                } else {
                    //eliminar
                    for (ModSolicitanteApoderado apoderadoHidra : listaApoderadosHidra) {
                        apoderadoHidra.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                        apoderadoHidra.setIdLogTrans(idLogTrans);
                        guardar_modificar_listar_SolicitanteApoderado(apoderadoHidra, operacionModificar);
                    }
                }
            } else {
                //adicionar
                ModSolicitanteApoderado apoderadoNuevo = new ModSolicitanteApoderado();
                for (Apoderados apoderadoSipi : listaApoderadosSipi) {

                    apoderadoNuevo.setIdmodificacion(idModificacion);
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
                    apoderadoNuevo.setIdLogTrans(idLogTrans);
                    apoderadoNuevo.setTipo_titular(EnumTipoTitular.NATURAL.getCodigo());
                    apoderadoNuevo.setTipo_persona(EnumTipoPersona.APODERADO.getCodigo());
                    apoderadoNuevo.setEstado(EnumEstado.ACTIVO.getCodigo());
                    apoderadoNuevo.setIdSipi(apoderadoSipi.getId());
                    guardar_modificar_listar_SolicitanteApoderado(apoderadoNuevo, operacionAdicionar);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void filtrarListaSolicitanteApoderado(List<ModSolicitanteApoderado> listadoSolicitanteApoderado, Long idmodificacion, String tipo) {

        Integer sw;
        Long idSolicitanteApoderado;
        List<ModSolicitanteApoderado> listaSolicitanteApoderadoBase = new ArrayList<ModSolicitanteApoderado>();
        if (tipo.equals(EnumTipoPersona.SOLICITANTE.getCodigo())) {
            listaSolicitanteApoderadoBase = listadoSolicitanteXidmodificacion(idmodificacion);
        } else {
            listaSolicitanteApoderadoBase = listadoApoderadoXidmodificacion(idmodificacion);
        }

        for (ModSolicitanteApoderado listaBase : listaSolicitanteApoderadoBase) {
            if (listaBase.getIdsolicitanteapoderado() != null) {
                sw = 0;
                idSolicitanteApoderado = listaBase.getIdsolicitanteapoderado();
                for (ModSolicitanteApoderado item : listadoSolicitanteApoderado) {
                    if (idSolicitanteApoderado.equals(item.getIdsolicitanteapoderado())) {
                        sw = 1;
                    }
                }
                if (sw == 0) {
                    // System.out.println("NO EXISTE BORRAR ");  
                    listaBase.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                    borrar_solicitanteApoderado(listaBase, 2);
                }
            }
        }
    }

    @Override
    public void borrar_solicitanteApoderado(ModSolicitanteApoderado modsolicitanteapoderado, Integer operacion) {
        String SQL = "select * from crud_modsolicitanteapoderado(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        jdbcTemplate.query(SQL, new ModSolicitanteApoderadoMapper(),
                modsolicitanteapoderado.getIdsolicitanteapoderado(),
                modsolicitanteapoderado.getIdmodificacion(),
                modsolicitanteapoderado.getIdLogTrans(),
                modsolicitanteapoderado.getTipo_titular(),
                modsolicitanteapoderado.getTipo_persona(),
                modsolicitanteapoderado.getNombre_razonsocial(),
                modsolicitanteapoderado.getPrimer_apellido(),
                modsolicitanteapoderado.getSegundo_apellido(),
                modsolicitanteapoderado.getNumero_documento(),
                modsolicitanteapoderado.getTipo_documento(),
                modsolicitanteapoderado.getLugar_expedicion(),
                modsolicitanteapoderado.getPais(),
                modsolicitanteapoderado.getGenero(),
                modsolicitanteapoderado.getSolicitud_departamento(),
                modsolicitanteapoderado.getPoder(),
                modsolicitanteapoderado.getDireccion(),
                modsolicitanteapoderado.getTelefono(),
                modsolicitanteapoderado.getCelular(),
                modsolicitanteapoderado.getEmail(),
                modsolicitanteapoderado.getFax(),
                modsolicitanteapoderado.getEstado(),
                modsolicitanteapoderado.getIdSipi(),
                operacion
        );
    }

    @Override
    public ModSolicitanteApoderado listar_ModSolicitanteApoderado_id(Long idSolicitanteApoderado, String tipoPersona) {
        try {
            String SQL = "select * from modsolicitanteapoderado "
                    + " where idsolicitanteapoderado = ? "
                    + " and tipo_persona = ? "
                    + " and estado = 'AC' ";
            ModSolicitanteApoderado modSolicitanteApoderado = jdbcTemplate.queryForObject(SQL,
                    new ModSolicitanteApoderadoMapper(),
                    idSolicitanteApoderado, tipoPersona);
            return modSolicitanteApoderado;
        } catch (EmptyResultDataAccessException e) {
            return new ModSolicitanteApoderado();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<ObjetoEliminadoGenerico> obtenerRegistrosEliminadosModSolicitanteApoderado(Long idmodificacion, String criterioBusqueda, String tipoPersona) throws Exception {
        try {
            String criterio = criterioBusqueda;
            String SQL = "select idmodificacion id, "
                    + " string_agg(concat(idsolicitanteapoderado, ' ', nombre_razonsocial,' ', primer_apellido, ' ',segundo_apellido, ' ', numero_documento), '; ') objeto_eliminado  "
                    + " from modsolicitanteapoderado "
                    + " where idsolicitanteapoderado not in (" + criterio + ") "
                    + " and idmodificacion = ? "
                    + " and tipo_persona= ?"
                    + " and estado = 'AC' "
                    + " group by 1";
            List<ObjetoEliminadoGenerico> objetoEliminadoGenerico = jdbcTemplate.query(SQL,
                    new ObjetoEliminadoGenericoMapper(),
                    idmodificacion, tipoPersona);
            if (!objetoEliminadoGenerico.isEmpty()) {
                return objetoEliminadoGenerico;
            }
            return new ArrayList<ObjetoEliminadoGenerico>();
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<ObjetoEliminadoGenerico>();
        } catch (Exception e) {
            throw e;
        }
    }

}
