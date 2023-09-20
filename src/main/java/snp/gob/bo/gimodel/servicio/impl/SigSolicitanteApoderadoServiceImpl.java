/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.entidad.ObjetoEliminadoGenerico;
import snp.gob.bo.gimodel.entidad.SigSolicitanteApoderado;
import snp.gob.bo.gimodel.enums.EnumEstado;
import snp.gob.bo.gimodel.enums.EnumNombreDominio;
import snp.gob.bo.gimodel.enums.EnumTipoPersona;
import snp.gob.bo.gimodel.enums.EnumTipoTitular;
import snp.gob.bo.gimodel.mapper.ObjetoEliminadoGenericoMapper;
import snp.gob.bo.gimodel.mapper.SigSolicitanteApoderadoMapper;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.SigSolicitanteApoderadoService;
import snp.gob.bo.gimodel.solicitudes.entidades.Apoderados;
import snp.gob.bo.gimodel.solicitudes.entidades.Solicitantes;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 29/08/2016
 */
@Service("sigSolicitanteApoderadoService")
public class SigSolicitanteApoderadoServiceImpl implements SigSolicitanteApoderadoService {

    @Autowired
    private DominioService dominioService;
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public SigSolicitanteApoderado registrarSigSigSolicitanteApoderado(SigSolicitanteApoderado sigSolicitanteApoderado) throws Exception {
        try {
            String SQL = "select * from reg_sigsolicitanteapoderado(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            SigSolicitanteApoderado sigSolicApod = (SigSolicitanteApoderado) jdbcTemplate.queryForObject(
                    SQL,
                    new SigSolicitanteApoderadoMapper(),
                    sigSolicitanteApoderado.getIdSignoMarca(),
                    sigSolicitanteApoderado.getIdLogTrans(),
                    sigSolicitanteApoderado.getTipoTitular(),
                    sigSolicitanteApoderado.getTipoPersona(),
                    sigSolicitanteApoderado.getNombreRazonSocial(),
                    sigSolicitanteApoderado.getPrimerApellido(),
                    sigSolicitanteApoderado.getSegundoApellido(),
                    sigSolicitanteApoderado.getNumeroDocumento(),
                    sigSolicitanteApoderado.getTipoDocumento(),
                    sigSolicitanteApoderado.getLugarExpedicion(),
                    sigSolicitanteApoderado.getPais(),
                    sigSolicitanteApoderado.getGenero(),
                    sigSolicitanteApoderado.getSolicitudDepartamento(),
                    sigSolicitanteApoderado.getPoder(),
                    sigSolicitanteApoderado.getDireccion(),
                    sigSolicitanteApoderado.getTelefono(),
                    sigSolicitanteApoderado.getCelular(),
                    sigSolicitanteApoderado.getEmail(),
                    sigSolicitanteApoderado.getFax(),
                    sigSolicitanteApoderado.getEstado(),
                    sigSolicitanteApoderado.getIdSipi()
            );

            return sigSolicApod;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<SigSolicitanteApoderado> listadoSolicitanteXidsignomarca(Long idsignomarca) {
        String SQL = "select * from lista_sigsolicitanteapoderado_idsignomarca(?);";
        List<SigSolicitanteApoderado> listaSolicitanteApoderado = jdbcTemplate.query(SQL, new SigSolicitanteApoderadoMapper(), idsignomarca);
        if (!listaSolicitanteApoderado.isEmpty()) {
            return listaSolicitanteApoderado;
        }
        return new ArrayList<SigSolicitanteApoderado>();
    }

    @Override
    public List<SigSolicitanteApoderado> listadoApoderadoXidsignomarca(Long idsignomarca) throws Exception {
        try {
            String SQL = "select * from lista_apoderados_idsignomarca(?);";
            List<SigSolicitanteApoderado> listaApoderados = jdbcTemplate.query(SQL, new SigSolicitanteApoderadoMapper(), idsignomarca);
            if (!listaApoderados.isEmpty()) {
                return listaApoderados;
            }
            return new ArrayList<SigSolicitanteApoderado>();
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public SigSolicitanteApoderado guardar_modificar_listar_SigSolicitanteApoderado(SigSolicitanteApoderado sigSolicitanteApoderado, Integer operacion) {
        String SQL = "select * from crud_sigsolicitanteapoderado(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        SigSolicitanteApoderado sigSolicApod = (SigSolicitanteApoderado) jdbcTemplate.queryForObject(
                SQL,
                new SigSolicitanteApoderadoMapper(),
                sigSolicitanteApoderado.getIdSolicitanteApoderado(),
                sigSolicitanteApoderado.getIdSignoMarca(),
                sigSolicitanteApoderado.getIdLogTrans(),
                sigSolicitanteApoderado.getTipoTitular(),
                sigSolicitanteApoderado.getTipoPersona(),
                sigSolicitanteApoderado.getNombreRazonSocial(),
                sigSolicitanteApoderado.getPrimerApellido(),
                sigSolicitanteApoderado.getSegundoApellido(),
                sigSolicitanteApoderado.getNumeroDocumento(),
                sigSolicitanteApoderado.getTipoDocumento(),
                sigSolicitanteApoderado.getLugarExpedicion(),
                sigSolicitanteApoderado.getPais(),
                sigSolicitanteApoderado.getGenero(),
                sigSolicitanteApoderado.getSolicitudDepartamento(),
                sigSolicitanteApoderado.getPoder(),
                sigSolicitanteApoderado.getDireccion(),
                sigSolicitanteApoderado.getTelefono(),
                sigSolicitanteApoderado.getCelular(),
                sigSolicitanteApoderado.getEmail(),
                sigSolicitanteApoderado.getFax(),
                sigSolicitanteApoderado.getEstado(),
                sigSolicitanteApoderado.getIdSipi(),
                operacion
        );

        return sigSolicApod;
    }

    @Override
    public void guardaListaSolicitantes(List<SigSolicitanteApoderado> listadoSolicitanteApoderado, Long idSigSignoMarca) throws Exception {
        try {
            for (SigSolicitanteApoderado solicitanteApoderado : listadoSolicitanteApoderado) {
                solicitanteApoderado.setIdSignoMarca(idSigSignoMarca);
                solicitanteApoderado.setEstado("AC");
                guardar_modificar_listar_SigSolicitanteApoderado(solicitanteApoderado, 1);
            }

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void guardaListaSolicitantes(List<SigSolicitanteApoderado> listadoSolicitanteApoderado, Long idSigSignoMarca, Long idLogTrans) throws Exception {
        try {
            for (SigSolicitanteApoderado solicitanteApoderado : listadoSolicitanteApoderado) {
                solicitanteApoderado.setIdSignoMarca(idSigSignoMarca);
                solicitanteApoderado.setIdLogTrans(idLogTrans);
                solicitanteApoderado.setEstado("AC");
                guardar_modificar_listar_SigSolicitanteApoderado(solicitanteApoderado, 1);
            }

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public int encuentraPosicionListadoSolicitanteApoderado(List<SigSolicitanteApoderado> listadoSolicitanteApoderado, SigSolicitanteApoderado sigSolicitanteApoderado) throws Exception {
        try {
            int posicion = 0;
            int cont = 0;
            for (SigSolicitanteApoderado item : listadoSolicitanteApoderado) {

                if (!sigSolicitanteApoderado.getTipoTitular().equals(EnumTipoTitular.NATURAL.getCodigo())) {
                    if (item.getNombreRazonSocial().equals(sigSolicitanteApoderado.getNombreRazonSocial())) {

                        posicion = cont;
                    }
                } else {

                    if (sigSolicitanteApoderado.getPrimerApellido() != null && sigSolicitanteApoderado.getSegundoApellido() != null) {
                        if (item.getNombreRazonSocial().equals(sigSolicitanteApoderado.getNombreRazonSocial())
                                && item.getPrimerApellido().equals(sigSolicitanteApoderado.getPrimerApellido())
                                && item.getSegundoApellido().equals(sigSolicitanteApoderado.getSegundoApellido())) {
                            posicion = cont;
                        }
                    } else if (sigSolicitanteApoderado.getPrimerApellido() != null) {
                        if (item.getNombreRazonSocial().equals(sigSolicitanteApoderado.getNombreRazonSocial())
                                && item.getPrimerApellido().equals(sigSolicitanteApoderado.getPrimerApellido())) {
                            posicion = cont;
                        }
                    } else {
                        if (item.getNombreRazonSocial().equals(sigSolicitanteApoderado.getNombreRazonSocial())) {
                            posicion = cont;
                        }
                    }
                }

                cont++;
            }
            return posicion;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<SigSolicitanteApoderado> buscaSolicitanteApoderadoPoridSolicitudyTipoPersona(Long numeroSr, String tipoPersona) throws Exception {
        try {
            String SQL = "select * from obtiene_sigsolicitanteapoderadoporidsolicitudytipopersona("
                    + "?,"
                    + "?);";
            List<SigSolicitanteApoderado> listaren = jdbcTemplate.query(SQL, new SigSolicitanteApoderadoMapper(),
                    numeroSr,
                    tipoPersona);
            if (!listaren.isEmpty()) {
                return listaren;
            } else {
                return new ArrayList<SigSolicitanteApoderado>();
            }
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public void modificaListaSolicitanteApoderado(Long idSignoMarca, List<SigSolicitanteApoderado> listaSolicitanteApoderado, String tipoSoliOApo, Long IdLogTrans) throws Exception {
        try {
            int parametroModificar = 2;
            int parametroGuardar = 1;
            int sw;
            List<SigSolicitanteApoderado> listaSolicitanteApoderadoBase;
            listaSolicitanteApoderadoBase = buscaSolicitanteApoderadoPoridSolicitudyTipoPersona(idSignoMarca, tipoSoliOApo);

            //actualizar los registros modificados
            for (SigSolicitanteApoderado sigSolicitanteApoderadoBase : listaSolicitanteApoderadoBase) {
                sw = 0;
                for (SigSolicitanteApoderado sigSolicitanteApoderado : listaSolicitanteApoderado) {
                    if (sigSolicitanteApoderadoBase.getIdSolicitanteApoderado().equals(sigSolicitanteApoderado.getIdSolicitanteApoderado())) {

                        crudSigSolicitanteApoderado(sigSolicitanteApoderado, parametroModificar);
                        sw = 1;
                    }
                }

                if (sw == 0) {
                    sigSolicitanteApoderadoBase.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                    sigSolicitanteApoderadoBase.setIdLogTrans(IdLogTrans);
                    crudSigSolicitanteApoderado(sigSolicitanteApoderadoBase, parametroModificar);
                }
            }

            //agregar los nuevos solicitantes
            for (SigSolicitanteApoderado sigSolicitanteApoderadoNuevo : listaSolicitanteApoderado) {
                if (sigSolicitanteApoderadoNuevo.getIdSolicitanteApoderado() == null) {
                    sigSolicitanteApoderadoNuevo.setIdSignoMarca(idSignoMarca);
                    sigSolicitanteApoderadoNuevo.setIdLogTrans(IdLogTrans);
                    sigSolicitanteApoderadoNuevo.setEstado(EnumEstado.ACTIVO.getCodigo());
                    crudSigSolicitanteApoderado(sigSolicitanteApoderadoNuevo, parametroGuardar);

                }

            }

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void modificarListaSolicitanteSubsanacion(Long idSignoMarca, List<Solicitantes> listaSolicitantesSipi, String tipoSoliOApo, Long IdLogTrans) throws Exception {
        try {
            int operacionAdicionar = 1;
            int operacionModificar = 2;
            int swExiste = 0;

            // Recuperamos el solicitante desde la base de datos
            List<SigSolicitanteApoderado> listaSolicitantesHidra = buscaSolicitanteApoderadoPoridSolicitudyTipoPersona(idSignoMarca, tipoSoliOApo);

            if (!listaSolicitantesHidra.isEmpty()) {
                if (!listaSolicitantesSipi.isEmpty()) {
                    //modificar
                        for (SigSolicitanteApoderado solicitanteHidra : listaSolicitantesHidra) {
                            swExiste = 0;
                            for (Solicitantes solicitanteSipi : listaSolicitantesSipi) {
                                if (solicitanteHidra.getIdSipi().equals(solicitanteSipi.getIdPadre())) {

                                    solicitanteHidra.setNombreRazonSocial(solicitanteSipi.getNombreRazonSocial());
                                    solicitanteHidra.setPrimerApellido(solicitanteSipi.getPrimerApellido());
                                    solicitanteHidra.setSegundoApellido(solicitanteSipi.getSegundoApellido());
                                    solicitanteHidra.setNumeroDocumento(solicitanteSipi.getNumeroDocumento());
                                    solicitanteHidra.setTipoDocumento(solicitanteSipi.getTipoDocumento());
                                    solicitanteHidra.setGenero(solicitanteSipi.getGenero());
                                    solicitanteHidra.setLugarExpedicion(solicitanteSipi.getLugarExpedicion());
                                    solicitanteHidra.setPais(solicitanteSipi.getPais());
                                    solicitanteHidra.setSolicitudDepartamento(solicitanteSipi.getDepartamentoSolicitud());
                                    solicitanteHidra.setTelefono(solicitanteSipi.getTelefono());
                                    solicitanteHidra.setCelular(solicitanteSipi.getCelular());
                                    solicitanteHidra.setDireccion(solicitanteSipi.getDomicilio());
                                    solicitanteHidra.setEmail(solicitanteSipi.getCorreoElectronico());
                                    solicitanteHidra.setIdLogTrans(IdLogTrans);
                                    solicitanteHidra.setIdSipi(solicitanteSipi.getId());
                                    crudSigSolicitanteApoderado(solicitanteHidra, operacionModificar);
                                    swExiste = 1;
                                    break;
                                }
                            }
                            if (swExiste == 0) {
                                //eliminar
                                solicitanteHidra.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                                solicitanteHidra.setIdLogTrans(IdLogTrans);
                                crudSigSolicitanteApoderado(solicitanteHidra, operacionModificar);
                            }
                        }

                        for (Solicitantes solicitanteSipi : listaSolicitantesSipi) {
                            swExiste = 0;
                            for (SigSolicitanteApoderado solicitanteHidra : listaSolicitantesHidra) {
                                if (solicitanteHidra.getIdSipi().equals(solicitanteSipi.getId())) {
                                    swExiste = 1;
                                    break;
                                }
                            }
                            if (swExiste == 0) {
                                //adicionar
                                SigSolicitanteApoderado solicitanteNuevo = new SigSolicitanteApoderado();
                                solicitanteNuevo.setIdSignoMarca(idSignoMarca);
                                solicitanteNuevo.setNombreRazonSocial(solicitanteSipi.getNombreRazonSocial());
                                solicitanteNuevo.setPrimerApellido(solicitanteSipi.getPrimerApellido());
                                solicitanteNuevo.setSegundoApellido(solicitanteSipi.getSegundoApellido());
                                solicitanteNuevo.setNumeroDocumento(solicitanteSipi.getNumeroDocumento());
                                solicitanteNuevo.setTipoDocumento(solicitanteSipi.getTipoDocumento());
                                solicitanteNuevo.setGenero(solicitanteSipi.getGenero());
                                solicitanteNuevo.setLugarExpedicion(solicitanteSipi.getLugarExpedicion());
                                solicitanteNuevo.setPais(solicitanteSipi.getPais());
                                solicitanteNuevo.setSolicitudDepartamento(solicitanteSipi.getDepartamentoSolicitud());
                                solicitanteNuevo.setTelefono(solicitanteSipi.getTelefono());
                                solicitanteNuevo.setCelular(solicitanteSipi.getCelular());
                                solicitanteNuevo.setDireccion(solicitanteSipi.getDomicilio());
                                solicitanteNuevo.setEmail(solicitanteSipi.getCorreoElectronico());
                                solicitanteNuevo.setIdLogTrans(IdLogTrans);
                                if (solicitanteSipi.getTipoPersona().equals(EnumTipoTitular.JURIDICO.getCodigo())) {
                                    solicitanteNuevo.setTipoTitular(EnumTipoTitular.JURIDICO.getCodigo());
                                } else {
                                    solicitanteNuevo.setTipoTitular(EnumTipoTitular.NATURAL.getCodigo());
                                }
                                solicitanteNuevo.setTipoPersona(EnumTipoPersona.SOLICITANTE.getCodigo());
                                solicitanteNuevo.setEstado(EnumEstado.ACTIVO.getCodigo());
                                solicitanteNuevo.setIdSipi(solicitanteSipi.getId());
                                crudSigSolicitanteApoderado(solicitanteNuevo, operacionAdicionar);
                            }
                        }

                } else {
                    //eliminar
                    for (SigSolicitanteApoderado solicitanteHidra : listaSolicitantesHidra) {
                        solicitanteHidra.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                        solicitanteHidra.setIdLogTrans(IdLogTrans);
                        crudSigSolicitanteApoderado(solicitanteHidra, operacionModificar);
                    }
                }
            } else {
                //adicionar
                SigSolicitanteApoderado solicitanteNuevo = new SigSolicitanteApoderado();
                for (Solicitantes solicitanteSipi : listaSolicitantesSipi) {
                    solicitanteNuevo.setIdSignoMarca(idSignoMarca);
                    solicitanteNuevo.setNombreRazonSocial(solicitanteSipi.getNombreRazonSocial());
                    solicitanteNuevo.setPrimerApellido(solicitanteSipi.getPrimerApellido());
                    solicitanteNuevo.setSegundoApellido(solicitanteSipi.getSegundoApellido());
                    solicitanteNuevo.setNumeroDocumento(solicitanteSipi.getNumeroDocumento());
                    solicitanteNuevo.setTipoDocumento(solicitanteSipi.getTipoDocumento());
                    solicitanteNuevo.setGenero(solicitanteSipi.getGenero());
                    solicitanteNuevo.setLugarExpedicion(solicitanteSipi.getLugarExpedicion());
                    solicitanteNuevo.setPais(solicitanteSipi.getPais());
                    solicitanteNuevo.setSolicitudDepartamento(solicitanteSipi.getDepartamentoSolicitud());
                    solicitanteNuevo.setTelefono(solicitanteSipi.getTelefono());
                    solicitanteNuevo.setCelular(solicitanteSipi.getCelular());
                    solicitanteNuevo.setDireccion(solicitanteSipi.getDomicilio());
                    solicitanteNuevo.setEmail(solicitanteSipi.getCorreoElectronico());
                    solicitanteNuevo.setIdLogTrans(IdLogTrans);
                    if (solicitanteSipi.getTipoPersona().equals(EnumTipoTitular.JURIDICO.getCodigo())) {
                        solicitanteNuevo.setTipoTitular(EnumTipoTitular.JURIDICO.getCodigo());
                    } else {
                        solicitanteNuevo.setTipoTitular(EnumTipoTitular.NATURAL.getCodigo());
                    }
                    solicitanteNuevo.setTipoPersona(EnumTipoPersona.SOLICITANTE.getCodigo());
                    solicitanteNuevo.setEstado(EnumEstado.ACTIVO.getCodigo());
                    solicitanteNuevo.setIdSipi(solicitanteSipi.getId());
                    crudSigSolicitanteApoderado(solicitanteNuevo, operacionAdicionar);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void modificarListaApoderadoSubsanacion(Long idSignoMarca, List<Apoderados> listaApoderadosSipi, String tipoSoliOApo, Long IdLogTrans) throws Exception {
        try {
            int parametroAdicionar = 1;
            int parametroModificar = 2;
            int swExiste = 0;

            // Recuperamos al apoderado desde la base de datos
            List<SigSolicitanteApoderado> listaApoderadosHidra = buscaSolicitanteApoderadoPoridSolicitudyTipoPersona(idSignoMarca, tipoSoliOApo);

            if (!listaApoderadosHidra.isEmpty()) {
                if (!listaApoderadosSipi.isEmpty()) {
                    //modificar
                        for (SigSolicitanteApoderado apoderadoHidra : listaApoderadosHidra) {
                            swExiste = 0;
                            for (Apoderados apoderadoSipi : listaApoderadosSipi) {
                                if (apoderadoHidra.getIdSipi().equals(apoderadoSipi.getIdPadre())) {
                                    apoderadoHidra.setNombreRazonSocial(apoderadoSipi.getNombres());
                                    apoderadoHidra.setPrimerApellido(apoderadoSipi.getPrimerApellido());
                                    apoderadoHidra.setSegundoApellido(apoderadoSipi.getSegundoApellido());
                                    apoderadoHidra.setNumeroDocumento(apoderadoSipi.getNumeroDocumento());
                                    apoderadoHidra.setTipoDocumento(apoderadoSipi.getTipoDocumento());
                                    apoderadoHidra.setGenero(apoderadoSipi.getGenero());
                                    apoderadoHidra.setLugarExpedicion(apoderadoSipi.getLugarExpedicion());
                                    apoderadoHidra.setTelefono(apoderadoSipi.getTelefono());
                                    apoderadoHidra.setCelular(apoderadoSipi.getCelular());
                                    apoderadoHidra.setDireccion(apoderadoSipi.getDomicilio());
                                    apoderadoHidra.setEmail(apoderadoSipi.getCorreoElectronico());
                                    apoderadoHidra.setPoder(apoderadoSipi.getNumeroPoder());
                                    apoderadoHidra.setIdLogTrans(IdLogTrans);
                                    apoderadoHidra.setIdSipi(apoderadoSipi.getId());
                                    crudSigSolicitanteApoderado(apoderadoHidra, parametroModificar);
                                    swExiste = 1;
                                    break;
                                }
                            }
                            if (swExiste == 0) {
                                //eliminar
                                apoderadoHidra.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                                apoderadoHidra.setIdLogTrans(IdLogTrans);
                                crudSigSolicitanteApoderado(apoderadoHidra, parametroModificar);
                            }
                        }

                        for (Apoderados apoderadoSipi : listaApoderadosSipi) {
                            swExiste = 0;
                            for (SigSolicitanteApoderado apoderadoHidra : listaApoderadosHidra) {
                                if (apoderadoHidra.getIdSipi().equals(apoderadoSipi.getId())) {
                                    swExiste = 1;
                                    break;
                                }
                            }
                            if (swExiste == 0) {
                                //adicionar
                                SigSolicitanteApoderado apoderadoNuevo = new SigSolicitanteApoderado();
                                apoderadoNuevo.setNombreRazonSocial(apoderadoSipi.getNombres());
                                apoderadoNuevo.setPrimerApellido(apoderadoSipi.getPrimerApellido());
                                apoderadoNuevo.setSegundoApellido(apoderadoSipi.getSegundoApellido());
                                apoderadoNuevo.setNumeroDocumento(apoderadoSipi.getNumeroDocumento());
                                apoderadoNuevo.setTipoDocumento(apoderadoSipi.getTipoDocumento());
                                apoderadoNuevo.setGenero(apoderadoSipi.getGenero());
                                apoderadoNuevo.setLugarExpedicion(apoderadoSipi.getLugarExpedicion());
                                apoderadoNuevo.setTelefono(apoderadoSipi.getTelefono());
                                apoderadoNuevo.setCelular(apoderadoSipi.getCelular());
                                apoderadoNuevo.setDireccion(apoderadoSipi.getDomicilio());
                                apoderadoNuevo.setEmail(apoderadoSipi.getCorreoElectronico());
                                apoderadoNuevo.setIdSignoMarca(idSignoMarca);
                                apoderadoNuevo.setIdLogTrans(IdLogTrans);
                                apoderadoNuevo.setTipoTitular(EnumTipoTitular.NATURAL.getCodigo());
                                apoderadoNuevo.setTipoPersona(EnumTipoPersona.APODERADO.getCodigo());
                                apoderadoNuevo.setEstado(EnumEstado.ACTIVO.getCodigo());
                                apoderadoNuevo.setIdSipi(apoderadoSipi.getId());
                                crudSigSolicitanteApoderado(apoderadoNuevo, parametroAdicionar);
                            }
                        }

                } else {
                    //eliminar
                    for (SigSolicitanteApoderado sigSolicitanteBase : listaApoderadosHidra) {
                        sigSolicitanteBase.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                        sigSolicitanteBase.setIdLogTrans(IdLogTrans);
                        
                        crudSigSolicitanteApoderado(sigSolicitanteBase, parametroModificar);
                    }
                }
            } else {
                //adicionar
                SigSolicitanteApoderado apoderadoNuevo = new SigSolicitanteApoderado();
                for (Apoderados apoderadoSipi : listaApoderadosSipi) {
                    apoderadoNuevo.setNombreRazonSocial(apoderadoSipi.getNombres());
                    apoderadoNuevo.setPrimerApellido(apoderadoSipi.getPrimerApellido());
                    apoderadoNuevo.setSegundoApellido(apoderadoSipi.getSegundoApellido());
                    apoderadoNuevo.setNumeroDocumento(apoderadoSipi.getNumeroDocumento());
                    apoderadoNuevo.setTipoDocumento(apoderadoSipi.getTipoDocumento());
                    apoderadoNuevo.setGenero(apoderadoSipi.getGenero());
                    apoderadoNuevo.setLugarExpedicion(apoderadoSipi.getLugarExpedicion());
                    apoderadoNuevo.setTelefono(apoderadoSipi.getTelefono());
                    apoderadoNuevo.setCelular(apoderadoSipi.getCelular());
                    apoderadoNuevo.setDireccion(apoderadoSipi.getDomicilio());
                    apoderadoNuevo.setEmail(apoderadoSipi.getCorreoElectronico());
                    apoderadoNuevo.setIdSignoMarca(idSignoMarca);
                    apoderadoNuevo.setIdLogTrans(IdLogTrans);
                    apoderadoNuevo.setTipoTitular(EnumTipoTitular.NATURAL.getCodigo());
                    apoderadoNuevo.setTipoPersona(EnumTipoPersona.APODERADO.getCodigo());
                    apoderadoNuevo.setEstado(EnumEstado.ACTIVO.getCodigo());
                    apoderadoNuevo.setIdSipi(apoderadoSipi.getId());
                    crudSigSolicitanteApoderado(apoderadoNuevo, parametroAdicionar);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public SigSolicitanteApoderado crudSigSolicitanteApoderado(SigSolicitanteApoderado sigSolicitanteApoderado, int parametro) throws Exception {
        try {
            String SQL = "select * from crud_sigsolicitanteapoderado("
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

            List<SigSolicitanteApoderado> listaSigSoliciApoderado = jdbcTemplate.query(SQL, new SigSolicitanteApoderadoMapper(),
                    sigSolicitanteApoderado.getIdSolicitanteApoderado(),
                    sigSolicitanteApoderado.getIdSignoMarca(),
                    sigSolicitanteApoderado.getIdLogTrans(),
                    sigSolicitanteApoderado.getTipoTitular(),
                    sigSolicitanteApoderado.getTipoPersona(),
                    sigSolicitanteApoderado.getNombreRazonSocial(),
                    sigSolicitanteApoderado.getPrimerApellido(),
                    sigSolicitanteApoderado.getSegundoApellido(),
                    sigSolicitanteApoderado.getNumeroDocumento(),
                    sigSolicitanteApoderado.getTipoDocumento(),
                    sigSolicitanteApoderado.getLugarExpedicion(),
                    sigSolicitanteApoderado.getPais(),
                    sigSolicitanteApoderado.getGenero(),
                    sigSolicitanteApoderado.getSolicitudDepartamento(),
                    sigSolicitanteApoderado.getPoder(),
                    sigSolicitanteApoderado.getDireccion(),
                    sigSolicitanteApoderado.getTelefono(),
                    sigSolicitanteApoderado.getCelular(),
                    sigSolicitanteApoderado.getEmail(),
                    sigSolicitanteApoderado.getFax(),
                    sigSolicitanteApoderado.getEstado(),
                    sigSolicitanteApoderado.getIdSipi(),
                    parametro);

            if (!listaSigSoliciApoderado.isEmpty()) {
                return listaSigSoliciApoderado.get(0);
            }
            return new SigSolicitanteApoderado();

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String[] datos_SigSolicitanteApoderado_concatenado(Long idsignomarca, String tipo) {
        String[] datosSolicitanteApoderado = new String[10];
        try {
            List<SigSolicitanteApoderado> listaSolicitanteApoderadoSig;

            String textoNombre = "";
            String textoDomicilio = "";
            String textoPais = "";
            String textoPaisDescripcion = "";
            String textoDepartamentoDescripcion = "";
            String textoTelefono = "";

            if (tipo.equals(EnumTipoPersona.SOLICITANTE.getCodigo())) {
                listaSolicitanteApoderadoSig = listadoSolicitanteXidsignomarca(idsignomarca);
            } else {

                listaSolicitanteApoderadoSig = listadoApoderadoXidsignomarca(idsignomarca);

            }

            for (SigSolicitanteApoderado item : listaSolicitanteApoderadoSig) {
                String campoNombreRazonSocial = " ";

                if (item.getNombreRazonSocial() != null && !item.getNombreRazonSocial().equals("")) {
                    campoNombreRazonSocial = item.getNombreRazonSocial();
                }
                if (item.getPrimerApellido() != null && !item.getPrimerApellido().equals("")) {
                    campoNombreRazonSocial = campoNombreRazonSocial + " " + item.getPrimerApellido();
                }
                if (item.getSegundoApellido() != null && !item.getSegundoApellido().equals("")) {
                    campoNombreRazonSocial = campoNombreRazonSocial + " " + item.getSegundoApellido();
                }
                textoNombre = textoNombre + campoNombreRazonSocial.trim() + "; ";
                ////////////////////////////////////////////////////////////////////////////////////////
                if (item.getDireccion() != null && !item.getDireccion().equals("")) {
                    textoDomicilio = textoDomicilio + item.getDireccion() + "; ";
                }
                //////////////////////////////////////////////////////////////////////////////////////
                String nacionalidad = "";
                String paisDescripcion = "";
                if (item.getPais() != null && !item.getPais().equals("")) {
                    //nacionalidad = dominioService.obtenerListadoDominioXCodigo(EnumNombreDominio.PAIS.getCodigo(), item.getPais()).get(0).getCodigo();
                    nacionalidad = item.getPais();
                    List<Dominio> listaDominio = dominioService.obtenerListadoDominioXCodigo("pais", item.getPais());
                    if (!listaDominio.isEmpty()) {
                        paisDescripcion = listaDominio.get(0).getNombre();
                    }

                    textoPais = textoPais + nacionalidad + "; ";
                    textoPaisDescripcion = textoPaisDescripcion + paisDescripcion + "; ";
                }
                //////////////////////////////////////////////////////////////////////////////////////
                String departamentoDescripcion = "";
                if (item.getSolicitudDepartamento() != null && !item.getSolicitudDepartamento().equals("")) {
                    departamentoDescripcion = dominioService.obtenerListadoDominioXCodigo(EnumNombreDominio.LUGAR_EXPEDICION.getCodigo(), item.getSolicitudDepartamento()).get(0).getNombre();
                    textoDepartamentoDescripcion = textoDepartamentoDescripcion + departamentoDescripcion + "; ";
                }
                ////////////////////////////////////////////////////////////////////////////////////////
                if (item.getTelefono() != null && !item.getTelefono().equals("")) {
                    textoTelefono = textoTelefono + item.getTelefono() + "; ";
                }
            }

            if (textoNombre.length() > 1) {
                textoNombre = textoNombre.substring(0, textoNombre.length() - 2);
            }
            if (textoDomicilio.length() > 1) {
                textoDomicilio = textoDomicilio.substring(0, textoDomicilio.length() - 2);
            }
            if (textoPais.length() > 1) {
                textoPais = textoPais.substring(0, textoPais.length() - 2);
            }

            if (textoPaisDescripcion.length() > 1) {
                textoPaisDescripcion = textoPaisDescripcion.substring(0, textoPaisDescripcion.length() - 2);
            }

            if (textoDepartamentoDescripcion.length() > 1) {
                textoDepartamentoDescripcion = textoDepartamentoDescripcion.substring(0, textoDepartamentoDescripcion.length() - 2);
            }

            if (textoTelefono.length() > 1) {
                textoTelefono = textoTelefono.substring(0, textoTelefono.length() - 2);
            }

            datosSolicitanteApoderado[0] = textoNombre;
            datosSolicitanteApoderado[1] = textoDomicilio;
            datosSolicitanteApoderado[2] = textoPais;
            datosSolicitanteApoderado[3] = textoPaisDescripcion;
            datosSolicitanteApoderado[4] = textoDepartamentoDescripcion;
            datosSolicitanteApoderado[5] = textoTelefono;

        } catch (Exception ex) {
            Logger.getLogger(SigSolicitanteApoderadoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datosSolicitanteApoderado;
    }

    @Override
    public SigSolicitanteApoderado obtenerSigSolicitanteApoderado(Long idSolicitanteApoderado, String tipoPersona) throws Exception {
        try {
            String SQL = "select * from sigsolicitanteapoderado "
                    + " where idsolicitanteapoderado = ? "
                    + " and tipo_persona = ? "
                    + " and estado = 'AC' ";
            SigSolicitanteApoderado sigSolicitanteApoderado = jdbcTemplate.queryForObject(SQL,
                    new SigSolicitanteApoderadoMapper(),
                    idSolicitanteApoderado, tipoPersona);

            return sigSolicitanteApoderado;

        } catch (EmptyResultDataAccessException e) {
            return new SigSolicitanteApoderado();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ObjetoEliminadoGenerico obtenerRegistrosEliminadosSigSolicitanteApoderado(Long idSignoMarca, String criterioBusqueda, String tipoPersona) throws Exception {
        try {

            String criterio = criterioBusqueda;
            String SQL = "select idsignomarca id, "
                    + " string_agg(concat('Nombre='||nombre_razonsocial,' ', primer_apellido, ' ',segundo_apellido, '; ', 'N° Doc='||numero_documento), '; ') objeto_eliminado  "
                    + " from sigsolicitanteapoderado "
                    + " where idsolicitanteapoderado not in (" + criterio + ") "
                    + " and idsignomarca = ? "
                    + " and estado = 'AC' "
                    + " and tipo_persona = ?"
                    + " group by 1";

            ObjetoEliminadoGenerico objetoEliminadoGenerico = jdbcTemplate.queryForObject(SQL,
                    new ObjetoEliminadoGenericoMapper(),
                    idSignoMarca,
                    tipoPersona);

            return objetoEliminadoGenerico;

        } catch (EmptyResultDataAccessException e) {
            return new ObjetoEliminadoGenerico();
        } catch (Exception e) {
            throw e;
        }
    }
}
