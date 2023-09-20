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
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.ModTipoSigno;
import snp.gob.bo.gimodel.enums.EnumEstado;
import snp.gob.bo.gimodel.mapper.ModTipoSignoMapper;
import snp.gob.bo.gimodel.servicio.ModTipoSignoService;
import snp.gob.bo.gimodel.solicitudes.entidades.ModTipoSignos;

/**
 *
 * @author Susana Escobar
 * @see ModTipoSignoDominio
 * @version 1.0, 15/09/2016
 */
@Service("modTipoSignoService")
public class ModTipoSignoServiceImpl implements ModTipoSignoService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    @Override
    public void setDataSource(DataSource dataSource) throws Exception {
        try {
            this.dataSource = dataSource;
            this.jdbcTemplate = new JdbcTemplate(dataSource);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ModTipoSigno guardar_modificar_listar_ModTipoSigno(ModTipoSigno modTipoSigno, int parametro) {
        try {
            String SQL = "select * from crud_modtiposigno("
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?);";
            ModTipoSigno modtipo = (ModTipoSigno) jdbcTemplate.queryForObject(SQL, new ModTipoSignoMapper(),
                    modTipoSigno.getIdtiposigno(),
                    modTipoSigno.getIdmodificacion(),
                    modTipoSigno.getIdlogtrans(),
                    modTipoSigno.getTipo_signo(),
                    modTipoSigno.getDescripcion_otro(),
                    modTipoSigno.getEstado(),
                    parametro);
            return modtipo;
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public void guardar_listaModTipoSigno(List<ModTipoSigno> listaModTipoSigno, Long idmodificacion, int parametro, Long idLogTrans) {
        borrar_listamodtiposignoXidmodificacion(idmodificacion);
        for (ModTipoSigno item : listaModTipoSigno) {
            try {
                item.setIdmodificacion(idmodificacion);
                item.setIdlogtrans(idLogTrans);
                item.setEstado(EnumEstado.ACTIVO.getCodigo());
                guardar_modificar_listar_ModTipoSigno(item, parametro);
            } catch (Exception ex) {
                Logger.getLogger(ModTipoSignoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void modificarListaTipoSignoSubsanacion(Long idmodificacion, List<ModTipoSigno> listaTipoSignosHidra, 
            List<ModTipoSignos> listaTipoSignosSipi, Long idLogTrans)  throws Exception{
        
        try {
            
            int operacionAdicionar = 1;
            int operacionModificar = 2;
            int sw;
            
            for (ModTipoSigno tipoSignoHidra : listaTipoSignosHidra) {
                sw = 0;
                for (ModTipoSignos tipoSignoSipi : listaTipoSignosSipi) {
                    if (tipoSignoHidra.getTipo_signo().equals(tipoSignoSipi.getTiposigno())) {
                        sw = 1;
                        break;
                    }
                }
                if (sw == 0) {  // Eliminación logica del tipo signo
                    tipoSignoHidra.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                    guardar_modificar_listar_ModTipoSigno(tipoSignoHidra, operacionModificar);
                }
            }

            for (ModTipoSignos tipoSignoSipi : listaTipoSignosSipi) {
                sw = 0;
                for (ModTipoSigno tipoSignoHidra : listaTipoSignosHidra) {
                    if (tipoSignoSipi.getTiposigno().equals(tipoSignoHidra.getTipo_signo())) {
                        sw = 1;
                        break;
                    }
                }
                if (sw == 0) { // Adicionar nuevo tipo signo
                    ModTipoSigno tipoSignoNuevo = new ModTipoSigno();
                    tipoSignoNuevo.setIdmodificacion(idmodificacion);
                    tipoSignoNuevo.setTipo_signo(tipoSignoSipi.getTiposigno());
                    tipoSignoNuevo.setDescripcion_otro(tipoSignoSipi.getDescripcionotro());
                    tipoSignoNuevo.setEstado(EnumEstado.ACTIVO.getCodigo());
                    guardar_modificar_listar_ModTipoSigno(tipoSignoNuevo, operacionAdicionar);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    @Override
    public List<ModTipoSigno> listado_modtiposignoXidmodificacion(Long idmodificacion) {
        String SQL = "select * from lista_modtiposigno_idmodificacion(?);";
        List<ModTipoSigno> listaTipoSigno = jdbcTemplate.query(SQL, new ModTipoSignoMapper(), idmodificacion);
        if (!listaTipoSigno.isEmpty()) {
            return listaTipoSigno;
        }
        return new ArrayList<ModTipoSigno>();
    }

    @Override
    public void borrar_listamodtiposignoXidmodificacion(Long idmodificacion) {
        List<ModTipoSigno> lstModTipoSignoBorrar = listado_modtiposignoXidmodificacion(idmodificacion);
        for (ModTipoSigno item : lstModTipoSignoBorrar) {
            item.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
            borrar_modtiposigno(item, 2);
        }
    }

    @Override
    public void borrar_modtiposigno(ModTipoSigno modTipoSigno, int parametro) {
        String SQL = "select * from crud_modtiposigno("
                + "?,"
                + "?,"
                + "?,"
                + "?,"
                + "?,"
                + "?,"
                + "?);";
        jdbcTemplate.query(SQL, new ModTipoSignoMapper(),
                modTipoSigno.getIdtiposigno(),
                modTipoSigno.getIdmodificacion(),
                modTipoSigno.getIdlogtrans(),
                modTipoSigno.getTipo_signo(),
                modTipoSigno.getDescripcion_otro(),
                modTipoSigno.getEstado(),
                parametro);
    }
}
