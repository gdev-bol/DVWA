/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.servicio.FlujoSeguimientoService;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import snp.gob.bo.gimodel.entidad.Etapa;
import snp.gob.bo.gimodel.entidad.FlujoSeguimiento;
import snp.gob.bo.gimodel.enums.EnumPrefijoTablas;
import snp.gob.bo.gimodel.mapper.FlujoSeguimientoMapper;

/**
 *
 * @author levi
 */
@Service("flujoSeguimientoService")
public class FlujoSeguimientoServiceImpl implements FlujoSeguimientoService {

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

    @Override
    public List<FlujoSeguimiento> verificaSiguienteEtapa(Integer ordenActual) throws Exception {

        String SQL = "select * from flujoseguimiento where orden > ? limit 1";
        List<FlujoSeguimiento> flujoseguimiento = jdbcTemplate.query(SQL, new FlujoSeguimientoMapper(), ordenActual);
        if (!flujoseguimiento.isEmpty()) {
            return flujoseguimiento;
        }
        return null;
    }

    @Override
    public List<FlujoSeguimiento> sigueFlujoSeguimiento(String etapa) throws Exception {
        List<FlujoSeguimiento> flujoSeguimientoSig = null;
        //primeramente saco la etapa actual
        String SQL = "select * from flujoseguimiento where etapa= ? and estado='AC' limit 1";
        List<FlujoSeguimiento> flujoseguimiento = jdbcTemplate.query(SQL, new FlujoSeguimientoMapper(), etapa);
        //segundo  se verifica si hay un flujo siguiente    y si existe se saca su flujoseguijmietno
        if (verificaSiguienteEtapa(flujoseguimiento.get(0).getOrden()) != null) {    //saco el flujoseguimiento siguiente del actual 
            flujoSeguimientoSig = verificaSiguienteEtapa(flujoseguimiento.get(0).getOrden());

        } else {

        }
        return flujoSeguimientoSig;
    }

    @Override
    public FlujoSeguimiento lista_FlujoSeguimiento_idetapa(Long idetapa, String prefijo) {
        Integer idflujo=0;
        if (prefijo.equals(EnumPrefijoTablas.SIGNO.getCodigo())) {
            idflujo=1;
        }
        if (prefijo.equals(EnumPrefijoTablas.MODIFICACION.getCodigo())) {
            idflujo=2;
        }
        if (prefijo.equals(EnumPrefijoTablas.RENOVACION.getCodigo())) {
            idflujo=3;
        }
        try {
            String SQL = "select * from flujoseguimiento where idetapa= ? and idflujo =? and estado='AC' limit 1";
            FlujoSeguimiento flujoseguimiento = jdbcTemplate.queryForObject(SQL, new FlujoSeguimientoMapper(), idetapa, idflujo);
            return flujoseguimiento;
        } catch (EmptyResultDataAccessException e) {
            // throw e;
            return null;
        }
    }

    @Override
    public Boolean[] configuracionBotonesRecepcionarFinalizar(Long idetapa, String prefijo) {

        FlujoSeguimiento flujoSeguimiento = lista_FlujoSeguimiento_idetapa(idetapa, prefijo);
        String tipoEtapa = "";
        if (flujoSeguimiento != null) {
            tipoEtapa = flujoSeguimiento.getTipo_etapa();
        }

        Boolean[] configuracion = {false, false, false, false};
        switch (tipoEtapa) {
            case "NR":
                configuracion[0] = true;
                configuracion[1] = true;
                configuracion[2] = false;
//                configuracion[3] = true;
                break;
            case "IN":
                configuracion[0] = false;
                configuracion[1] = false;
                configuracion[2] = false;
                break;
            case "AT":
                configuracion[0] = false;
                configuracion[1] = true;
                configuracion[2] = true;
                break;
            case "":
                configuracion[0] = false;
                configuracion[1] = false;
                configuracion[2] = false;
                break;
            default:
                configuracion[0] = false;
                configuracion[1] = false;
                configuracion[2] = false;
                break;
        }

        return configuracion;
    }

}
