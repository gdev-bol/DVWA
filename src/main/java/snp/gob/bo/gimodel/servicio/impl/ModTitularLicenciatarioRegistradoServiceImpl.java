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
import snp.gob.bo.gimodel.entidad.ModTitularLicenciatarioRegistrado;
import snp.gob.bo.gimodel.entidad.ObjetoEliminadoGenerico;
import snp.gob.bo.gimodel.enums.EnumEstado;
import snp.gob.bo.gimodel.mapper.ModTitularLicenciatarioRegistradoMapper;
import snp.gob.bo.gimodel.mapper.ObjetoEliminadoGenericoMapper;
import snp.gob.bo.gimodel.servicio.ModTitularLicenciatarioRegistradoService;
import snp.gob.bo.gimodel.solicitudes.entidades.TitularLicenciatarios;

/**
 *
 * @author susana
 */
@Service("modTitularLicenciatarioRegistradoService")
public class ModTitularLicenciatarioRegistradoServiceImpl implements ModTitularLicenciatarioRegistradoService {

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
    ///metodos

    @Override
    public int encuentraPosicionListadoTitularRegistrado(List<ModTitularLicenciatarioRegistrado> listaTitularRegistrado, ModTitularLicenciatarioRegistrado titularLicenciatarioRegistrado) {
        int posicion = 0;
        int cont = 0;
        for (ModTitularLicenciatarioRegistrado item : listaTitularRegistrado) {

            if (item.getNombre_razonsocial().equals(titularLicenciatarioRegistrado.getNombre_razonsocial())
                    && item.getDireccion().equals(titularLicenciatarioRegistrado.getDireccion())) {
                posicion = cont;
            }
            cont++;
        }
        return posicion;
    }

    //  @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Override
    public ModTitularLicenciatarioRegistrado guardar_modificar_listar_ModTitularLicenciatarioRegistrado(ModTitularLicenciatarioRegistrado modtitularlicenciatarioregistrado, Integer opcion) {

        String SQL = "select * from crud_modtitularlicenciatarioregistrado(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        ModTitularLicenciatarioRegistrado modTitularLicenciatarioRegistrado = (ModTitularLicenciatarioRegistrado) jdbcTemplate.queryForObject(SQL, new ModTitularLicenciatarioRegistradoMapper(),
                modtitularlicenciatarioregistrado.getIdtitularlicenciatarioregistrado(),
                modtitularlicenciatarioregistrado.getIdmodificacion(),
                modtitularlicenciatarioregistrado.getIdlogtrans(),
                modtitularlicenciatarioregistrado.getTipo_persona_registrado(),
                modtitularlicenciatarioregistrado.getTipo_titular(),
                modtitularlicenciatarioregistrado.getNombre_razonsocial(),
                modtitularlicenciatarioregistrado.getPrimer_apellido(),
                modtitularlicenciatarioregistrado.getSegundo_apellido(),
                modtitularlicenciatarioregistrado.getDireccion(),
                modtitularlicenciatarioregistrado.getEstado(),
                modtitularlicenciatarioregistrado.getIdSipi(),
                opcion
        );
        return modTitularLicenciatarioRegistrado;
    }

    @Override
    public void guardaListaTitularLicenciatarioRegistrado(List<ModTitularLicenciatarioRegistrado> listaTitularRegistrado, Long idmodificacion, Long idLogTrans) {
        for (ModTitularLicenciatarioRegistrado item : listaTitularRegistrado) {
            item.setIdmodificacion(idmodificacion);
            item.setIdlogtrans(idLogTrans);
            item.setEstado(EnumEstado.ACTIVO.getCodigo());
            guardar_modificar_listar_ModTitularLicenciatarioRegistrado(item, 1);
        }
    }

    @Override
    public List<ModTitularLicenciatarioRegistrado> listaTitularRegistradoXidmodificacion(Long idmodificacion) {
        String SQL = "select * from lista_modtitularlicenciatarioregistrado_idmodificacion(?);";
        List<ModTitularLicenciatarioRegistrado> listaTitularRegistrado = jdbcTemplate.query(SQL, new ModTitularLicenciatarioRegistradoMapper(), idmodificacion);
        if (!listaTitularRegistrado.isEmpty()) {
            return listaTitularRegistrado;
        }
        return new ArrayList<ModTitularLicenciatarioRegistrado>();
    }

    @Override
    public List<ModTitularLicenciatarioRegistrado> listaTitularFusionXidmodificacion(Long idmodificacion) {
        String SQL = "select * from lista_modtitularlicenciatarioregistrado_idmodificacionfusion(?);";
        List<ModTitularLicenciatarioRegistrado> listaTitularFusion = jdbcTemplate.query(SQL, new ModTitularLicenciatarioRegistradoMapper(), idmodificacion);
        if (!listaTitularFusion.isEmpty()) {
            return listaTitularFusion;
        }
        return new ArrayList<ModTitularLicenciatarioRegistrado>();
    }

    @Override
    public void modificarListaTitularLicenciatarioRegistrado(List<ModTitularLicenciatarioRegistrado> listaTitularRegistrado,
            Long idmodificacion, String tipo, Long idLogTrans) {
        filtrarListaTitularLicenciatarioRegistrado(listaTitularRegistrado, idmodificacion, tipo);
        for (ModTitularLicenciatarioRegistrado item : listaTitularRegistrado) {
            if (item.getIdtitularlicenciatarioregistrado() != null) {
                guardar_modificar_listar_ModTitularLicenciatarioRegistrado(item, 2);
            } else {
                item.setIdmodificacion(idmodificacion);
                item.setEstado(EnumEstado.ACTIVO.getCodigo());
                item.setIdlogtrans(idLogTrans);
                guardar_modificar_listar_ModTitularLicenciatarioRegistrado(item, 1);
            }
        }
    }

    @Override
    public void modificarListaTitularLicenciatarioRegistradoSubsanacion(List<TitularLicenciatarios> listaTitularLicenciatariosSipi, Long idModificacion, Long idLogTrans) throws Exception {

        try {
            int operacionAdicionar = 1;
            int operacionModificar = 2;
            int swExiste = 0;

            // Recuperamos el  TitularLicenciatarioRegistrado desde la base de datos
            List<ModTitularLicenciatarioRegistrado> listaTitularLicenciatarioRegistradosHidra = listaTitularRegistradoXidmodificacion(idModificacion);

            if (!listaTitularLicenciatarioRegistradosHidra.isEmpty()) {
                if (!listaTitularLicenciatariosSipi.isEmpty()) {
                    //modificar
                    for (ModTitularLicenciatarioRegistrado titularLicenciatarioRegistradoHidra : listaTitularLicenciatarioRegistradosHidra) {
                        swExiste = 0;
                        for (TitularLicenciatarios titularLicenciatarioRegistradoSipi : listaTitularLicenciatariosSipi) {
                            if (titularLicenciatarioRegistradoHidra.getIdSipi().equals(titularLicenciatarioRegistradoSipi.getIdPadre())) {

                                titularLicenciatarioRegistradoHidra.setTipo_persona_registrado(titularLicenciatarioRegistradoSipi.getTipoTitularRegistrado());
                                titularLicenciatarioRegistradoHidra.setTipo_titular(titularLicenciatarioRegistradoSipi.getTipoTitular());
                                titularLicenciatarioRegistradoHidra.setNombre_razonsocial(titularLicenciatarioRegistradoSipi.getNombreRazonSocial());
                                titularLicenciatarioRegistradoHidra.setPrimer_apellido(null);
                                titularLicenciatarioRegistradoHidra.setSegundo_apellido(null);
                                titularLicenciatarioRegistradoHidra.setDireccion(titularLicenciatarioRegistradoSipi.getDireccion());
                                titularLicenciatarioRegistradoHidra.setIdlogtrans(idLogTrans);
                                titularLicenciatarioRegistradoHidra.setIdSipi(titularLicenciatarioRegistradoSipi.getId());
                                guardar_modificar_listar_ModTitularLicenciatarioRegistrado(titularLicenciatarioRegistradoHidra, operacionModificar);
                                swExiste = 1;
                                break;
                            }
                        }
                        if (swExiste == 0) {
                            //eliminar
                            titularLicenciatarioRegistradoHidra.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                            titularLicenciatarioRegistradoHidra.setIdlogtrans(idLogTrans);
                            guardar_modificar_listar_ModTitularLicenciatarioRegistrado(titularLicenciatarioRegistradoHidra, operacionModificar);
                        }
                    }

                    for (TitularLicenciatarios titularLicenciatarioRegistradoSipi : listaTitularLicenciatariosSipi) {
                        swExiste = 0;
                        for (ModTitularLicenciatarioRegistrado titularLicenciatarioRegistradoHidra : listaTitularLicenciatarioRegistradosHidra) {
                            if (titularLicenciatarioRegistradoHidra.getIdSipi().equals(titularLicenciatarioRegistradoSipi.getId())) {

                                swExiste = 1;
                                break;
                            }
                        }
                        if (swExiste == 0) {
                            //adicionar
                            ModTitularLicenciatarioRegistrado titularLicenciatarioRegistradoNuevo = new ModTitularLicenciatarioRegistrado();
                            titularLicenciatarioRegistradoNuevo.setIdmodificacion(idModificacion);
                            titularLicenciatarioRegistradoNuevo.setTipo_persona_registrado(titularLicenciatarioRegistradoSipi.getTipoTitularRegistrado());
                            titularLicenciatarioRegistradoNuevo.setTipo_titular(titularLicenciatarioRegistradoSipi.getTipoTitular());
                            titularLicenciatarioRegistradoNuevo.setNombre_razonsocial(titularLicenciatarioRegistradoSipi.getNombreRazonSocial());
                            titularLicenciatarioRegistradoNuevo.setPrimer_apellido(null);
                            titularLicenciatarioRegistradoNuevo.setSegundo_apellido(null);
                            titularLicenciatarioRegistradoNuevo.setDireccion(titularLicenciatarioRegistradoSipi.getDireccion());
                            titularLicenciatarioRegistradoNuevo.setIdlogtrans(idLogTrans);
                            titularLicenciatarioRegistradoNuevo.setEstado(EnumEstado.ACTIVO.getCodigo());
                            titularLicenciatarioRegistradoNuevo.setIdSipi(titularLicenciatarioRegistradoSipi.getId());
                            guardar_modificar_listar_ModTitularLicenciatarioRegistrado(titularLicenciatarioRegistradoNuevo, operacionAdicionar);
                        }
                    }

                } else {
                    //eliminar
                    for (ModTitularLicenciatarioRegistrado titularLicenciatarioRegistradoHidra : listaTitularLicenciatarioRegistradosHidra) {
                        titularLicenciatarioRegistradoHidra.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                        titularLicenciatarioRegistradoHidra.setIdlogtrans(idLogTrans);
                        guardar_modificar_listar_ModTitularLicenciatarioRegistrado(titularLicenciatarioRegistradoHidra, operacionModificar);
                    }
                }
            } else {
                //adicionar
                ModTitularLicenciatarioRegistrado titularLicenciatarioRegistradoNuevo = new ModTitularLicenciatarioRegistrado();
                for (TitularLicenciatarios titularLicenciatarioRegistradoSipi : listaTitularLicenciatariosSipi) {

                    titularLicenciatarioRegistradoNuevo.setIdmodificacion(idModificacion);
                    titularLicenciatarioRegistradoNuevo.setTipo_persona_registrado(titularLicenciatarioRegistradoSipi.getTipoTitularRegistrado());
                    titularLicenciatarioRegistradoNuevo.setTipo_titular(titularLicenciatarioRegistradoSipi.getTipoTitular());
                    titularLicenciatarioRegistradoNuevo.setNombre_razonsocial(titularLicenciatarioRegistradoSipi.getNombreRazonSocial());
                    titularLicenciatarioRegistradoNuevo.setPrimer_apellido(null);
                    titularLicenciatarioRegistradoNuevo.setSegundo_apellido(null);
                    titularLicenciatarioRegistradoNuevo.setDireccion(titularLicenciatarioRegistradoSipi.getDireccion());
                    titularLicenciatarioRegistradoNuevo.setIdlogtrans(idLogTrans);
                    titularLicenciatarioRegistradoNuevo.setEstado(EnumEstado.ACTIVO.getCodigo());
                    titularLicenciatarioRegistradoNuevo.setIdSipi(titularLicenciatarioRegistradoSipi.getId());
                    guardar_modificar_listar_ModTitularLicenciatarioRegistrado(titularLicenciatarioRegistradoNuevo, operacionAdicionar);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void filtrarListaTitularLicenciatarioRegistrado(List<ModTitularLicenciatarioRegistrado> listaTitularRegistrado, Long idmodificacion, String tipo) {
        Integer sw;
        Long idTitularRegistrado;
        List<ModTitularLicenciatarioRegistrado> listaTitularRegistradoBase = new ArrayList<ModTitularLicenciatarioRegistrado>();
        if (tipo.equals("TREG")) {
            listaTitularRegistradoBase = listaTitularRegistradoXidmodificacion(idmodificacion);
        } else {
            listaTitularRegistradoBase = listaTitularFusionXidmodificacion(idmodificacion);
        }

        for (ModTitularLicenciatarioRegistrado listaBase : listaTitularRegistradoBase) {

            if (listaBase.getIdtitularlicenciatarioregistrado() != null) {
                sw = 0;
                idTitularRegistrado = listaBase.getIdtitularlicenciatarioregistrado();

                for (ModTitularLicenciatarioRegistrado item : listaTitularRegistrado) {
                    if (idTitularRegistrado.equals(item.getIdtitularlicenciatarioregistrado())) {

                        sw = 1;
                    }
                }
                if (sw == 0) {

                    listaBase.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                    borrar_ModTitularLicenciatarioRegistrado(listaBase, 2);
                }
            }
        }
    }

    @Override
    public void borrar_ModTitularLicenciatarioRegistrado(ModTitularLicenciatarioRegistrado modtitularlicenciatarioregistrado, Integer opcion) {
        String SQL = "select * from crud_modtitularlicenciatarioregistrado(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        jdbcTemplate.query(SQL, new ModTitularLicenciatarioRegistradoMapper(),
                modtitularlicenciatarioregistrado.getIdtitularlicenciatarioregistrado(),
                modtitularlicenciatarioregistrado.getIdmodificacion(),
                modtitularlicenciatarioregistrado.getIdlogtrans(),
                modtitularlicenciatarioregistrado.getTipo_persona_registrado(),
                modtitularlicenciatarioregistrado.getTipo_titular(),
                modtitularlicenciatarioregistrado.getNombre_razonsocial(),
                modtitularlicenciatarioregistrado.getPrimer_apellido(),
                modtitularlicenciatarioregistrado.getSegundo_apellido(),
                modtitularlicenciatarioregistrado.getDireccion(),
                modtitularlicenciatarioregistrado.getEstado(),
                modtitularlicenciatarioregistrado.getIdSipi(),
                opcion
        );
    }

    @Override
    public List<ObjetoEliminadoGenerico> obtenerRegistrosEliminadosModTitularRegistrado(Long idmodificacion, String criterioBusqueda, String tipo) throws Exception {
        try {
            String criterio = criterioBusqueda;
            String SQL = "select idmodificacion id, "
                    + " string_agg(concat(idtitularlicenciatarioregistrado, ' ', nombre_razonsocial,' ', primer_apellido, ' ',segundo_apellido, ' ', direccion), '; ') objeto_eliminado  "
                    + " from modtitularlicenciatarioregistrado "
                    + " where idtitularlicenciatarioregistrado not in (" + criterio + ") "
                    + " and idmodificacion = ? "
                    + " and estado = 'AC' "
                    + " and tipo_persona_registrado = ?"
                    + " group by 1";
            List<ObjetoEliminadoGenerico> objetoEliminadoGenerico = jdbcTemplate.query(SQL,
                    new ObjetoEliminadoGenericoMapper(),
                    idmodificacion, tipo);
            return objetoEliminadoGenerico;
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<ObjetoEliminadoGenerico>();
        } catch (Exception e) {
            throw e;
        }
    }

}
