/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.ModTitularLicenciatarioNuevo;
import snp.gob.bo.gimodel.entidad.ObjetoEliminadoGenerico;
import snp.gob.bo.gimodel.enums.EnumEstado;
import snp.gob.bo.gimodel.enums.EnumTipoPersona;
import snp.gob.bo.gimodel.enums.EnumTipoTitular;
import snp.gob.bo.gimodel.mapper.ModTitularLicenciatarioNuevoMapper;
import snp.gob.bo.gimodel.mapper.ObjetoEliminadoGenericoMapper;
import snp.gob.bo.gimodel.servicio.ModTitularLicenciatarioNuevoService;
import snp.gob.bo.gimodel.solicitudes.entidades.TitularLicenciatariosNuevos;

/**
 *
 * @author susana
 */
@Service("modTitularLicencitarioNuevoService")
public class ModTitularLicenciatarioNuevoServiceImpl implements ModTitularLicenciatarioNuevoService {

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
    //@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Override
    public int encuentraPosicionListadoNuevoTitular(List<ModTitularLicenciatarioNuevo> listaNuevoTitular, ModTitularLicenciatarioNuevo titularLicencitarioNuevo) {
        int posicion = 0;
        int cont = 0;
        for (ModTitularLicenciatarioNuevo item : listaNuevoTitular) {

            if (!titularLicencitarioNuevo.getTipo_titular().equals(EnumTipoTitular.NATURAL.getCodigo())) {
                if (item.getNombre_razonsocial().equals(titularLicencitarioNuevo.getNombre_razonsocial())) {

                    posicion = cont;
                }
            } else {

                if (item.getNombre_razonsocial().equals(titularLicencitarioNuevo.getNombre_razonsocial()) // se quito la validacion para IMPORTANTE
                        // && item.getPrimer_apellido() == titularLicencitarioNuevo.getPrimer_apellido()
                        // && item.getSegundo_apellido().equals(titularLicencitarioNuevo.getSegundo_apellido())
                        ) {
                    posicion = cont;
                }
            }
            cont++;
        }
        return posicion;
    }

    @Override
    public ModTitularLicenciatarioNuevo guardar_modificar_listar_ModTitularLicenciatarioNuevo(ModTitularLicenciatarioNuevo modtitularlicencitarionuevo, Integer opcion) {

        String SQL = "select * from crud_modtitularlicenciatarionuevo(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        ModTitularLicenciatarioNuevo modTitularLicenciatarioNuevo = (ModTitularLicenciatarioNuevo) jdbcTemplate.queryForObject(SQL, new ModTitularLicenciatarioNuevoMapper(),
                modtitularlicencitarionuevo.getIdtitularlicenciatario(),
                modtitularlicencitarionuevo.getIdmodificacion(),
                modtitularlicencitarionuevo.getIdlogtrans(),
                modtitularlicencitarionuevo.getTipo_persona(),
                modtitularlicencitarionuevo.getTipo_titular(),
                modtitularlicencitarionuevo.getNombre_razonsocial(),
                modtitularlicencitarionuevo.getPrimer_apellido(),
                modtitularlicencitarionuevo.getSegundo_apellido(),
                modtitularlicencitarionuevo.getNumero_documento(),
                modtitularlicencitarionuevo.getTipo_documento(),
                modtitularlicencitarionuevo.getLugar_expedicion(),
                modtitularlicencitarionuevo.getPais(),
                modtitularlicencitarionuevo.getPais_constitucion(),
                modtitularlicencitarionuevo.getGenero(),
                modtitularlicencitarionuevo.getSolicitud_departamento(),
                modtitularlicencitarionuevo.getDireccion(),
                modtitularlicencitarionuevo.getTelefono(),
                modtitularlicencitarionuevo.getCelular(),
                modtitularlicencitarionuevo.getEmail(),
                modtitularlicencitarionuevo.getFax(),
                modtitularlicencitarionuevo.getEstado(),
                modtitularlicencitarionuevo.getModificar(),
                modtitularlicencitarionuevo.getId_referencia(),
                modtitularlicencitarionuevo.getIdSipi(),
                opcion
        );
        return modTitularLicenciatarioNuevo;
    }

    @Override
    public void guardaListaNuevoTitular(List<ModTitularLicenciatarioNuevo> listaNuevoTitular, Long idmodificacion) {
        for (ModTitularLicenciatarioNuevo item : listaNuevoTitular) {
            item.setIdmodificacion(idmodificacion);
            item.setEstado(EnumEstado.ACTIVO.getCodigo());
            guardar_modificar_listar_ModTitularLicenciatarioNuevo(item, 1);
        }
    }

    @Override
    public List<ModTitularLicenciatarioNuevo> listaNuevoTitularXidmodificacion(Long idmodificacion) {
        String SQL = "select * from lista_modtitularlicenciatarionuevo_idmodificacion(?);";
        List<ModTitularLicenciatarioNuevo> listaNuevoTitular = jdbcTemplate.query(SQL, new ModTitularLicenciatarioNuevoMapper(), idmodificacion);
        if (!listaNuevoTitular.isEmpty()) {
            return listaNuevoTitular;
        }
        return new ArrayList<ModTitularLicenciatarioNuevo>();
    }

    @Override
    public void modificarListaTitularLicenciatarioNuevo(List<ModTitularLicenciatarioNuevo> listaNuevoTitular, Long idmodificacion) {
        filtrarListaTitularLicenciatarioNuevo(listaNuevoTitular, idmodificacion);
        for (ModTitularLicenciatarioNuevo item : listaNuevoTitular) {
            if (item.getIdtitularlicenciatario() != null) {
                guardar_modificar_listar_ModTitularLicenciatarioNuevo(item, 2);

            } else {
                item.setIdmodificacion(idmodificacion);
                item.setEstado(EnumEstado.ACTIVO.getCodigo());
                guardar_modificar_listar_ModTitularLicenciatarioNuevo(item, 1);

            }
        }
    }

    @Override
    public void modificarListaTitularLicenciatarioNuevoSubsanacion(List<TitularLicenciatariosNuevos> listaTitularLicenciatariosNuevoSipi, Long idModificacion, Long idLogTrans) throws Exception {
        try {
            int operacionAdicionar = 1;
            int operacionModificar = 2;
            int swExiste = 0;

            // Recuperamos el TitularLicenciatarioNuevo desde la base de datos
            List<ModTitularLicenciatarioNuevo> listaTitularLicenciatariosNuevoHidra = listaNuevoTitularXidmodificacion(idModificacion);

            if (!listaTitularLicenciatariosNuevoHidra.isEmpty()) {
                if (!listaTitularLicenciatariosNuevoSipi.isEmpty()) {
                    //modificar
                    for (ModTitularLicenciatarioNuevo titularLicenciatarioNuevoHidra : listaTitularLicenciatariosNuevoHidra) {
                        swExiste = 0;
                        for (TitularLicenciatariosNuevos titularLicenciatarioNuevoSipi : listaTitularLicenciatariosNuevoSipi) {
                            if (titularLicenciatarioNuevoHidra.getIdSipi().equals(titularLicenciatarioNuevoSipi.getIdPadre())) {

                                titularLicenciatarioNuevoHidra.setNombre_razonsocial(titularLicenciatarioNuevoSipi.getNombreRazonSocial());
                                titularLicenciatarioNuevoHidra.setPrimer_apellido(titularLicenciatarioNuevoSipi.getPrimerApellido());
                                titularLicenciatarioNuevoHidra.setSegundo_apellido(titularLicenciatarioNuevoSipi.getSegundoApellido());
                                titularLicenciatarioNuevoHidra.setNumero_documento(titularLicenciatarioNuevoSipi.getNroDocumento());
                                titularLicenciatarioNuevoHidra.setTipo_documento(titularLicenciatarioNuevoSipi.getTipoDocumento());
                                titularLicenciatarioNuevoHidra.setGenero(titularLicenciatarioNuevoSipi.getGenero());
                                titularLicenciatarioNuevoHidra.setLugar_expedicion(titularLicenciatarioNuevoSipi.getLugarExpedicion());
                                titularLicenciatarioNuevoHidra.setPais(titularLicenciatarioNuevoSipi.getPais());
                                titularLicenciatarioNuevoHidra.setSolicitud_departamento(titularLicenciatarioNuevoSipi.getSolicitudDepartamento());
                                titularLicenciatarioNuevoHidra.setTelefono(titularLicenciatarioNuevoSipi.getTelefono());
                                titularLicenciatarioNuevoHidra.setCelular(titularLicenciatarioNuevoSipi.getCelular());
                                titularLicenciatarioNuevoHidra.setDireccion(titularLicenciatarioNuevoSipi.getDireccion());
                                titularLicenciatarioNuevoHidra.setEmail(titularLicenciatarioNuevoSipi.getEmail());
                                titularLicenciatarioNuevoHidra.setTipo_persona(titularLicenciatarioNuevoSipi.getTipoPersona());
                                titularLicenciatarioNuevoHidra.setTipo_titular(titularLicenciatarioNuevoSipi.getTipoTitular());
                                titularLicenciatarioNuevoHidra.setIdlogtrans(idLogTrans);
                                titularLicenciatarioNuevoHidra.setIdSipi(titularLicenciatarioNuevoSipi.getId());
                                guardar_modificar_listar_ModTitularLicenciatarioNuevo(titularLicenciatarioNuevoHidra, operacionModificar);
                                swExiste = 1;
                                break;
                            }
                        }
                        if (swExiste == 0) {
                            //eliminar
                            titularLicenciatarioNuevoHidra.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                            titularLicenciatarioNuevoHidra.setIdlogtrans(idLogTrans);
                            guardar_modificar_listar_ModTitularLicenciatarioNuevo(titularLicenciatarioNuevoHidra, operacionModificar);
                        }
                    }

                    for (TitularLicenciatariosNuevos titularLicenciatarioNuevoSipi : listaTitularLicenciatariosNuevoSipi) {
                        swExiste = 0;
                        for (ModTitularLicenciatarioNuevo titularLicenciatarioNuevoHidra : listaTitularLicenciatariosNuevoHidra) {
                            if (titularLicenciatarioNuevoHidra.getIdSipi().equals(titularLicenciatarioNuevoSipi.getId())) {

                                swExiste = 1;
                                break;
                            }
                        }
                        if (swExiste == 0) {
                            //adicionar
                            ModTitularLicenciatarioNuevo titularLicenciatarioNuevo = new ModTitularLicenciatarioNuevo();
                            titularLicenciatarioNuevo.setNombre_razonsocial(titularLicenciatarioNuevoSipi.getNombreRazonSocial());
                            titularLicenciatarioNuevo.setPrimer_apellido(titularLicenciatarioNuevoSipi.getPrimerApellido());
                            titularLicenciatarioNuevo.setSegundo_apellido(titularLicenciatarioNuevoSipi.getSegundoApellido());
                            titularLicenciatarioNuevo.setNumero_documento(titularLicenciatarioNuevoSipi.getNroDocumento());
                            titularLicenciatarioNuevo.setTipo_documento(titularLicenciatarioNuevoSipi.getTipoDocumento());
                            titularLicenciatarioNuevo.setGenero(titularLicenciatarioNuevoSipi.getGenero());
                            titularLicenciatarioNuevo.setLugar_expedicion(titularLicenciatarioNuevoSipi.getLugarExpedicion());
                            titularLicenciatarioNuevo.setTelefono(titularLicenciatarioNuevoSipi.getTelefono());
                            titularLicenciatarioNuevo.setCelular(titularLicenciatarioNuevoSipi.getCelular());
                            titularLicenciatarioNuevo.setDireccion(titularLicenciatarioNuevoSipi.getDireccion());
                            titularLicenciatarioNuevo.setEmail(titularLicenciatarioNuevoSipi.getEmail());
                            titularLicenciatarioNuevo.setPais(titularLicenciatarioNuevoSipi.getPais());
                            titularLicenciatarioNuevo.setIdmodificacion(idModificacion);
                            titularLicenciatarioNuevo.setIdlogtrans(idLogTrans);
                            titularLicenciatarioNuevo.setTipo_titular(EnumTipoTitular.NATURAL.getCodigo());
                            titularLicenciatarioNuevo.setTipo_persona(titularLicenciatarioNuevoSipi.getTipoPersona());
                            titularLicenciatarioNuevo.setEstado(EnumEstado.ACTIVO.getCodigo());
                            titularLicenciatarioNuevo.setIdSipi(titularLicenciatarioNuevoSipi.getId());
                            guardar_modificar_listar_ModTitularLicenciatarioNuevo(titularLicenciatarioNuevo, operacionAdicionar);
                        }
                    }

                } else {
                    //eliminar
                    for (ModTitularLicenciatarioNuevo titularLicenciatarioNuevoHidra : listaTitularLicenciatariosNuevoHidra) {
                        titularLicenciatarioNuevoHidra.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                        titularLicenciatarioNuevoHidra.setIdlogtrans(idLogTrans);
                        guardar_modificar_listar_ModTitularLicenciatarioNuevo(titularLicenciatarioNuevoHidra, operacionModificar);
                    }
                }
            } else {
                //adicionar
                ModTitularLicenciatarioNuevo titularLicenciatarioNuevo = new ModTitularLicenciatarioNuevo();
                for (TitularLicenciatariosNuevos titularLicenciatarioNuevoSipi : listaTitularLicenciatariosNuevoSipi) {

                    titularLicenciatarioNuevo.setNombre_razonsocial(titularLicenciatarioNuevoSipi.getNombreRazonSocial());
                    titularLicenciatarioNuevo.setPrimer_apellido(titularLicenciatarioNuevoSipi.getPrimerApellido());
                    titularLicenciatarioNuevo.setSegundo_apellido(titularLicenciatarioNuevoSipi.getSegundoApellido());
                    titularLicenciatarioNuevo.setNumero_documento(titularLicenciatarioNuevoSipi.getNroDocumento());
                    titularLicenciatarioNuevo.setTipo_documento(titularLicenciatarioNuevoSipi.getTipoDocumento());
                    titularLicenciatarioNuevo.setGenero(titularLicenciatarioNuevoSipi.getGenero());
                    titularLicenciatarioNuevo.setLugar_expedicion(titularLicenciatarioNuevoSipi.getLugarExpedicion());
                    titularLicenciatarioNuevo.setTelefono(titularLicenciatarioNuevoSipi.getTelefono());
                    titularLicenciatarioNuevo.setCelular(titularLicenciatarioNuevoSipi.getCelular());
                    titularLicenciatarioNuevo.setDireccion(titularLicenciatarioNuevoSipi.getDireccion());
                    titularLicenciatarioNuevo.setEmail(titularLicenciatarioNuevoSipi.getEmail());
                    titularLicenciatarioNuevo.setPais(titularLicenciatarioNuevoSipi.getPais());
                    titularLicenciatarioNuevo.setIdmodificacion(idModificacion);
                    titularLicenciatarioNuevo.setIdlogtrans(idLogTrans);
                    titularLicenciatarioNuevo.setTipo_titular(EnumTipoTitular.NATURAL.getCodigo());
                    titularLicenciatarioNuevo.setTipo_persona(titularLicenciatarioNuevoSipi.getTipoPersona());
                    titularLicenciatarioNuevo.setEstado(EnumEstado.ACTIVO.getCodigo());
                    titularLicenciatarioNuevo.setIdSipi(titularLicenciatarioNuevoSipi.getId());
                    guardar_modificar_listar_ModTitularLicenciatarioNuevo(titularLicenciatarioNuevo, operacionAdicionar);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void filtrarListaTitularLicenciatarioNuevo(List<ModTitularLicenciatarioNuevo> listaNuevoTitular, Long idmodificacion) {
        Integer sw;
        Long idNuevoTitular;
        List<ModTitularLicenciatarioNuevo> listaSolicitanteApoderadoBase = listaNuevoTitularXidmodificacion(idmodificacion);
        for (ModTitularLicenciatarioNuevo listaBase : listaSolicitanteApoderadoBase) {
            if (listaBase.getIdtitularlicenciatario() != null) {
                sw = 0;
                idNuevoTitular = listaBase.getIdtitularlicenciatario();
                for (ModTitularLicenciatarioNuevo item : listaNuevoTitular) {
                    if (idNuevoTitular.equals(item.getIdtitularlicenciatario())) {
                        sw = 1;
                    }
                }
                if (sw == 0) {
                    listaBase.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                    borrar_modTitularLicenciatarioNuevo(listaBase, 2);
                }
            }
        }
    }

    @Override
    public void borrar_modTitularLicenciatarioNuevo(ModTitularLicenciatarioNuevo modtitularlicencitarionuevo, Integer opcion) {
        String SQL = "select * from crud_modtitularlicenciatarionuevo(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        jdbcTemplate.query(SQL, new ModTitularLicenciatarioNuevoMapper(),
                modtitularlicencitarionuevo.getIdtitularlicenciatario(),
                modtitularlicencitarionuevo.getIdmodificacion(),
                modtitularlicencitarionuevo.getIdlogtrans(),
                modtitularlicencitarionuevo.getTipo_persona(),
                modtitularlicencitarionuevo.getTipo_titular(),
                modtitularlicencitarionuevo.getNombre_razonsocial(),
                modtitularlicencitarionuevo.getPrimer_apellido(),
                modtitularlicencitarionuevo.getSegundo_apellido(),
                modtitularlicencitarionuevo.getNumero_documento(),
                modtitularlicencitarionuevo.getTipo_documento(),
                modtitularlicencitarionuevo.getLugar_expedicion(),
                modtitularlicencitarionuevo.getPais(),
                modtitularlicencitarionuevo.getPais_constitucion(),
                modtitularlicencitarionuevo.getGenero(),
                modtitularlicencitarionuevo.getSolicitud_departamento(),
                modtitularlicencitarionuevo.getDireccion(),
                modtitularlicencitarionuevo.getTelefono(),
                modtitularlicencitarionuevo.getCelular(),
                modtitularlicencitarionuevo.getEmail(),
                modtitularlicencitarionuevo.getFax(),
                modtitularlicencitarionuevo.getEstado(),
                modtitularlicencitarionuevo.getModificar(),
                modtitularlicencitarionuevo.getId_referencia(),
                modtitularlicencitarionuevo.getIdSipi(),
                opcion
        );
    }

    @Override
    public List<ObjetoEliminadoGenerico> obtenerRegistrosEliminadosModTitularLicenciatarioNuevo(Long idmodificacion, String criterioBusqueda) {
        try {
            String criterio = criterioBusqueda;
            String SQL = "select idmodificacion id, "
                    + " string_agg(concat(idtitularlicenciatario, ' ', nombre_razonsocial,' ', primer_apellido, ' ',segundo_apellido, ' ', direccion), '; ') objeto_eliminado  "
                    + " from modtitularlicenciatarionuevo "
                    + " where idtitularlicenciatario not in (" + criterio + ") "
                    + " and idmodificacion = ? "
                    + " and estado = 'AC' "
                    + " group by 1";

            List<ObjetoEliminadoGenerico> objetoEliminadoGenerico = jdbcTemplate.query(SQL,
                    new ObjetoEliminadoGenericoMapper(),
                    idmodificacion);
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

    @Override
    public int encuentraPosicionListadoNuevoTitularDomicilio(List<ModTitularLicenciatarioNuevo> listaNuevoTitular, ModTitularLicenciatarioNuevo titularLicencitarioNuevo) {
        int posicion = 0;
        int cont = 0;
        for (ModTitularLicenciatarioNuevo item : listaNuevoTitular) {
            if (item.getDireccion().equals(titularLicencitarioNuevo.getDireccion())
                    && item.getPais().equals(titularLicencitarioNuevo.getPais())) {
                posicion = cont;
            }
            cont++;
        }
        return posicion;
    }
}
