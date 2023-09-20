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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.RenSolicitudRenovacion;
import snp.gob.bo.gimodel.entidad.RenTitularRegistrado;
import snp.gob.bo.gimodel.enums.EnumEstado;
import snp.gob.bo.gimodel.mapper.RenTitularRegistradoMapper;
import snp.gob.bo.gimodel.servicio.RenTitularRegistradoService;
import snp.gob.bo.gimodel.solicitudes.entidades.RenTitularRegistrados;

/**
 *
 * @author Chano Rojas
 * @see RenRenovacionDominio
 * @see RenrenovacionServiceImpl
 * @version 1.0, 05/06/2016
 */
@Service("renTitularRegistradoService")
public class RenTitularRegistradoServiceImpl implements RenTitularRegistradoService {

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
    public RenTitularRegistrado crudRenTitularRegistrado(RenTitularRegistrado renTitularRegistrado, int parametro) throws Exception {
        try {
            String SQL = "select * from crud_rentitularregistrado("
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
            RenTitularRegistrado renTitular = (RenTitularRegistrado) jdbcTemplate.queryForObject(SQL, new RenTitularRegistradoMapper(),
                    renTitularRegistrado.getIdtitularregistrado(),
                    renTitularRegistrado.getIdsolicitudrenovacion(),
                    renTitularRegistrado.getIdlogtrans(),
                    renTitularRegistrado.getNombre_razonsocial(),
                    renTitularRegistrado.getPrimer_apellido(),
                    renTitularRegistrado.getSegundo_apellido(),
                    renTitularRegistrado.getDireccion(),
                    renTitularRegistrado.getEstado(),
                    renTitularRegistrado.getIdSipi(),
                    parametro);
            return renTitular;
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public List<RenTitularRegistrado> obtieneListadoRenTitularRegistrado(RenTitularRegistrado renTitularRegistrado, int parametro) throws Exception {
        try {
            String SQL = "select * from crud_rentitularregistrado("
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
            List<RenTitularRegistrado> listaren = jdbcTemplate.query(SQL, new RenTitularRegistradoMapper(),
                    renTitularRegistrado.getIdtitularregistrado(),
                    renTitularRegistrado.getIdsolicitudrenovacion(),
                    renTitularRegistrado.getIdlogtrans(),
                    renTitularRegistrado.getNombre_razonsocial(),
                    renTitularRegistrado.getPrimer_apellido(),
                    renTitularRegistrado.getSegundo_apellido(),
                    renTitularRegistrado.getDireccion(),
                    renTitularRegistrado.getEstado(),
                    renTitularRegistrado.getIdSipi(),
                    parametro);

            if (!listaren.isEmpty()) {
                return listaren;
            }
            return Collections.EMPTY_LIST;

        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public RenTitularRegistrado crudDosRenTitularRegistrado(RenTitularRegistrado renTitularRegistrado, int parametro) throws Exception {
        try {
            String SQL = "select * from crud_rentitularregistrado("
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
            List<RenTitularRegistrado> listaren = jdbcTemplate.query(SQL, new RenTitularRegistradoMapper(),
                    renTitularRegistrado.getIdtitularregistrado(),
                    renTitularRegistrado.getIdsolicitudrenovacion(),
                    renTitularRegistrado.getIdlogtrans(),
                    renTitularRegistrado.getNombre_razonsocial(),
                    renTitularRegistrado.getPrimer_apellido(),
                    renTitularRegistrado.getSegundo_apellido(),
                    renTitularRegistrado.getDireccion(),
                    renTitularRegistrado.getEstado(),
                    renTitularRegistrado.getIdSipi(),
                    parametro);

            if (!listaren.isEmpty()) {
                return listaren.get(0);
            }
            return new RenTitularRegistrado();

        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public List<RenTitularRegistrado> buscaRenTitularRegistradoPorIdSolicitud(Long idSolicitud) {
        try {
            String SQL = "select * from obtiene_rentitularregistradoporidsolicitudrenovacion("
                    + "?);";
            List<RenTitularRegistrado> listaren = jdbcTemplate.query(SQL, new RenTitularRegistradoMapper(),
                    idSolicitud);
            if (!listaren.isEmpty()) {
                return listaren;
            } else {
                return new ArrayList<RenTitularRegistrado>();
            }

        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public int encuentraPosicionListadoTitularRegistrado(List<RenTitularRegistrado> listaTitularRegistrado, RenTitularRegistrado titularRegistrado) {
        int posicion = 0;
        int cont = 0;
        for (RenTitularRegistrado item : listaTitularRegistrado) {

            if (item.getNombre_razonsocial().equals(titularRegistrado.getNombre_razonsocial())
                    && item.getDireccion().equals(titularRegistrado.getDireccion())) {
//                System.out.println("ES el objeto");
                posicion = cont;
            }
            cont++;
        }
        return posicion;
    }

    @Override
    public void modificaListaTitularRegistrado(RenSolicitudRenovacion rensolicitudRenovacion, List<RenTitularRegistrado> listaTitularRegistrado) throws Exception {
        try {
            int parametro = 2;
            int parametroGuardar = 1;
            int sw = 0;
            List<RenTitularRegistrado> listaTitularBase;
            listaTitularBase = buscaRenTitularRegistradoPorIdSolicitud(rensolicitudRenovacion.getIdsolicitudrenovacion());
            System.out.println("litsa de titulares" + listaTitularBase.size());

            for (RenTitularRegistrado renTitularRegistradoBase : listaTitularBase) {
                sw = 0;
                for (RenTitularRegistrado rensTitularRegistradoVista : listaTitularRegistrado) {
                    if (renTitularRegistradoBase.getIdtitularregistrado().equals(rensTitularRegistradoVista.getIdtitularregistrado())) {
                        rensTitularRegistradoVista.setIdlogtrans(2L);
                        crudDosRenTitularRegistrado(rensTitularRegistradoVista, parametro);
                        sw = 1;
                    }
                }
                if (sw == 0) {
                    renTitularRegistradoBase.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                    crudDosRenTitularRegistrado(renTitularRegistradoBase, parametro);
                }
            }
            for (RenTitularRegistrado rensTitularRegistradoVista : listaTitularRegistrado) {
                if (rensTitularRegistradoVista.getIdtitularregistrado() == null) {
                    rensTitularRegistradoVista.setIdsolicitudrenovacion(rensolicitudRenovacion.getIdsolicitudrenovacion());
                    rensTitularRegistradoVista.setIdlogtrans(1L);
                    rensTitularRegistradoVista.setEstado(EnumEstado.ACTIVO.getCodigo());
                    crudDosRenTitularRegistrado(rensTitularRegistradoVista, parametroGuardar);
                }

            }

        } catch (Exception ex) {
            Logger.getLogger(RenSolicitanteApoderadoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

    }

    @Override
    public RenTitularRegistrado buscaRenTitularRegistradoPorIdSolicitud(RenTitularRegistrado renTitularRegistrado) {
        try {
            String SQL = "select * from rentitularregistrado where idtitularregistrado='" + renTitularRegistrado.getIdtitularregistrado() + "';";
            List<RenTitularRegistrado> listaren = jdbcTemplate.query(SQL, new RenTitularRegistradoMapper());
            if (!listaren.isEmpty()) {
                return listaren.get(0);
            } else {
                return new RenTitularRegistrado();
            }

        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public void modificarListaTitularRegistradoSubsanacion(Long idSolicitudRenovacion, List<RenTitularRegistrados> listaTitularRegistradosSipi, Long idLogTrans) throws Exception {
        try {

            int parametroAdicionar = 1;
            int parametroModificar = 2;
            int swExiste = 0;

            // Recuperamos al titular registrado desde la base de datos
            List<RenTitularRegistrado> listaTitularRegistradosHidra = buscaRenTitularRegistradoPorIdSolicitud(idSolicitudRenovacion);

            if (!listaTitularRegistradosHidra.isEmpty()) {
                if (!listaTitularRegistradosSipi.isEmpty()) {
                    //modificar
                    for (RenTitularRegistrado titularRegistradoHidra : listaTitularRegistradosHidra) {
                        swExiste = 0;
                        for (RenTitularRegistrados titularRegistradoSipi : listaTitularRegistradosSipi) {
                            if (titularRegistradoHidra.getIdSipi().equals(titularRegistradoSipi.getIdPadre())) {

                                titularRegistradoHidra.setNombre_razonsocial(titularRegistradoSipi.getNombreRazonSocial());
                                titularRegistradoHidra.setPrimer_apellido(titularRegistradoSipi.getPrimerApellido());
                                titularRegistradoHidra.setSegundo_apellido(titularRegistradoSipi.getSegundoApellido());
                                titularRegistradoHidra.setDireccion(titularRegistradoSipi.getDireccion());
                                titularRegistradoHidra.setIdlogtrans(idLogTrans);
                                titularRegistradoHidra.setIdSipi(titularRegistradoSipi.getId());
                                crudDosRenTitularRegistrado(titularRegistradoHidra, parametroModificar);
                                swExiste = 1;
                                break;
                            }
                        }
                        if (swExiste == 0) {
                            //eliminar
                            titularRegistradoHidra.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                            titularRegistradoHidra.setIdlogtrans(idLogTrans);
                            crudDosRenTitularRegistrado(titularRegistradoHidra, parametroModificar);
                        }
                    }

                    for (RenTitularRegistrados titularRegistradoSipi : listaTitularRegistradosSipi) {
                        swExiste = 0;
                        for (RenTitularRegistrado titularRegistradoHidra : listaTitularRegistradosHidra) {
                            if (titularRegistradoHidra.getIdSipi().equals(titularRegistradoSipi.getId())) {
                                swExiste = 1;
                                break;
                            }
                        }
                        if (swExiste == 0) {
                            //adicionar
                            RenTitularRegistrado titularRegistradoNuevo = new RenTitularRegistrado();
                            titularRegistradoNuevo.setIdsolicitudrenovacion(idSolicitudRenovacion);
                            titularRegistradoNuevo.setNombre_razonsocial(titularRegistradoSipi.getNombreRazonSocial());
                            titularRegistradoNuevo.setPrimer_apellido(titularRegistradoSipi.getPrimerApellido());
                            titularRegistradoNuevo.setSegundo_apellido(titularRegistradoSipi.getSegundoApellido());
                            titularRegistradoNuevo.setDireccion(titularRegistradoSipi.getDireccion());
                            titularRegistradoNuevo.setIdlogtrans(idLogTrans);
                            titularRegistradoNuevo.setEstado(EnumEstado.ACTIVO.getCodigo());
                            titularRegistradoNuevo.setIdSipi(titularRegistradoSipi.getId());
                            crudDosRenTitularRegistrado(titularRegistradoNuevo, parametroAdicionar);
                        }
                    }

                } else {
                    //eliminar
                    for (RenTitularRegistrado titularRegistradoHidra : listaTitularRegistradosHidra) {
                        titularRegistradoHidra.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                        titularRegistradoHidra.setIdlogtrans(idLogTrans);
                        crudDosRenTitularRegistrado(titularRegistradoHidra, parametroModificar);
                    }
                }
            } else {
                //adicionar
                RenTitularRegistrado titularRegistradoNuevo = new RenTitularRegistrado();
                for (RenTitularRegistrados titularRegistradoSipi : listaTitularRegistradosSipi) {

                    titularRegistradoNuevo.setIdsolicitudrenovacion(idSolicitudRenovacion);
                    titularRegistradoNuevo.setNombre_razonsocial(titularRegistradoSipi.getNombreRazonSocial());
                    titularRegistradoNuevo.setPrimer_apellido(titularRegistradoSipi.getPrimerApellido());
                    titularRegistradoNuevo.setSegundo_apellido(titularRegistradoSipi.getSegundoApellido());
                    titularRegistradoNuevo.setDireccion(titularRegistradoSipi.getDireccion());
                    titularRegistradoNuevo.setIdlogtrans(idLogTrans);
                    titularRegistradoNuevo.setEstado(EnumEstado.ACTIVO.getCodigo());
                    titularRegistradoNuevo.setIdSipi(titularRegistradoSipi.getId());
                    crudDosRenTitularRegistrado(titularRegistradoNuevo, parametroAdicionar);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

}
