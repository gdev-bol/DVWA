/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.util.Collections;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.DatoElementoFormulario;
import snp.gob.bo.gimodel.mapper.DatoElementoFormularioMapper;
import snp.gob.bo.gimodel.servicio.DatoElementoFormularioService;

/**
 *
 * @author Eddy Valero
 * @see DatoElementoFormulario
 * @see DatoElementoFormularioServiceImpl
 * @version 1.0, 05/09/2016
 */
@Service("datoElementoFormularioService")
public class DatoElementoFormularioServiceImpl implements DatoElementoFormularioService {

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
    public int generarRegistrosPlantillaVentanilla(Long idSeguimiento, Long idFormulario) throws Exception {

        try {
            String SQL = "select generarplantillaventanilla(?,?) as table;";

            return jdbcTemplate.queryForObject(SQL, Integer.class, idSeguimiento, idFormulario);

        } catch (Exception e) {
            throw e;
        }

    }

//    @Override
//    public List<DatoElementoFormulario> obtplantillaventanillaseguimiento(Long idSeguimiento, String nombreTabla) throws Exception {
//
//        try {
//            String SQL = "select * from obtplantillaventanillaseguimiento(?, ?);";
//            List<DatoElementoFormulario> listaDominio = jdbcTemplate.query(SQL, new DatoElementoFormularioMapper(), idSeguimiento, nombreTabla);
//            if (!listaDominio.isEmpty()) {
//                return listaDominio;
//            }
//            return Collections.EMPTY_LIST;
//        } catch (Exception e) {
//            throw e;
//        }
//
//    }
    @Override
    public void actualizarRegistrosPlantillaVentanilla(List<DatoElementoFormulario> listaPlantilla, String prefijoTabla) throws Exception {
        try {

            for (DatoElementoFormulario datoElementoFormulario : listaPlantilla) {
                String SQL = "select * from crud_datoelementoformulario(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

                jdbcTemplate.queryForObject(SQL,
                        new DatoElementoFormularioMapper(),
                        datoElementoFormulario.getIdDatoElementoFormulario(),
                        datoElementoFormulario.getNombreTabla(),
                        datoElementoFormulario.getIdSeguimiento(),
                        datoElementoFormulario.getIdLogTrans(),
                        datoElementoFormulario.getPestania(),
                        datoElementoFormulario.getSeccion(),
                        datoElementoFormulario.getFila(),
                        datoElementoFormulario.getTipoElemento(),
                        datoElementoFormulario.getNombreElemento(),
                        datoElementoFormulario.getOrden(),
                        datoElementoFormulario.getOrdenLiteral(),
                        datoElementoFormulario.getIdpadre(),
                        null,
                        null,
                        datoElementoFormulario.getRespuesta(),
                        datoElementoFormulario.getOpcionRespuesta(),
                        datoElementoFormulario.getEstado(),
                        prefijoTabla,
                        2
                );
//                String sql = "update datoelementoformulario "
//                        + "set"
//                        + " respuesta = ?, "
//                        + " opcion_respuesta = ? "
//                        + " where iddatoelementoformulario = ?";
//
//                jdbcTemplate.update(sql, datoElementoFormulario.getRespuesta(), datoElementoFormulario.getOpcionRespuesta(), datoElementoFormulario.getIdDatoElementoFormulario());

            }

        } catch (Exception e) {
            throw e;
        }
    }

//    @Override
//    public boolean actPlantillaRequerimientos(List<SMDatoElementoFormulario> listaPlantilla) throws Exception {
//        try {
//            String SQL = "select * from actplantillaventanillaseguimiento(?);";
//            List<SMDatoElementoFormulario> listaDominio = jdbcTemplate.query(SQL, new DatoElementoFormularioMapper(), listaPlantilla);
//            if (!listaDominio.isEmpty()) {
//                return Boolean.TRUE;
//            }
//
//        } catch (Exception e) {
//            throw e;
//        }
//        return Boolean.FALSE;
//        /**
//         * create or replace function actplantillaventanillaseguimiento(
//         * smdatoelementoformulario[] ) returns bool as $BODY$ declare
//         * itemplantilla smdatoelementoformulario; begin
//         *
//         * foreach itemplantilla in array smdatoelementoformulario loop update
//         * smdatoelementoformulario set respuesta = itemplantilla.respuesta,
//         * opcionrespuesta = itemplantilla.opcionrespuesta where
//         * iddatoelementoformulario = itemplantilla.iddatoelementoformulario;
//         * end loop;
//         *
//         * return true; end;
//         *
//         * $BODY$ LANGUAGE 'plpgsql'
//         */
//
//    }
    /**
     *
     * @param listaPlantilla
     * @param prefijo
     * @throws Exception
     */
    @Override
    public void guardarRegistrosPlantillaVentanilla(List<DatoElementoFormulario> listaPlantilla, String prefijo) throws Exception {
        try {
            for (DatoElementoFormulario datoElementoFormulario : listaPlantilla) {
                String SQL = "select * from crud_datoelementoformulario(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

                jdbcTemplate.queryForObject(SQL,
                        new DatoElementoFormularioMapper(),
                        datoElementoFormulario.getIdDatoElementoFormulario(),
                        datoElementoFormulario.getNombreTabla(),
                        datoElementoFormulario.getIdSeguimiento(),
                        datoElementoFormulario.getIdLogTrans(),
                        datoElementoFormulario.getPestania(),
                        datoElementoFormulario.getSeccion(),
                        datoElementoFormulario.getFila(),
                        datoElementoFormulario.getTipoElemento(),
                        datoElementoFormulario.getNombreElemento(),
                        datoElementoFormulario.getOrden(),
                        datoElementoFormulario.getOrdenLiteral(),
                        datoElementoFormulario.getIdpadre(),
                        null,
                        null,
                        datoElementoFormulario.getRespuesta(),
                        datoElementoFormulario.getOpcionRespuesta(),
                        datoElementoFormulario.getEstado(),
                        prefijo,
                        1
                );

            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<DatoElementoFormulario> obtplantillaventanillatramiteingresado(String numeroFormulario, String tipoFormulario) throws Exception {

        try {

            String SQL = "select * from obtplantillaventanillatramiteingresado(?, ?);";
            List<DatoElementoFormulario> listaDominio = jdbcTemplate.query(SQL, new DatoElementoFormularioMapper(), numeroFormulario, tipoFormulario);
            if (!listaDominio.isEmpty()) {
                return listaDominio;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }

    }

}
