/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.servicio.ComunService;

/**
 *
 * @author Eddy Valero
 * @see ComunService
 * @version 1.0, 16/09/2016
 */
@Service("comunService")
public class ComunServiceImpl implements ComunService {

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
    public Date obtenerFechaServidor(Long idRegional) throws Exception {
        try {
            String SQL = "select * from obtenerfechasistema(?);";
            Date fechaServidor = jdbcTemplate.queryForObject(SQL, new Object[]{idRegional}, Date.class);
            return fechaServidor;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Date obtenerFechaHoraServidor(Long idRegional) throws Exception {
        try {
            String SQL = "select * from obtenerfechahorasistema(?);";
            Date fechaServidor = jdbcTemplate.queryForObject(SQL, new Object[]{idRegional}, Date.class);
            return fechaServidor;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Long codificarCodigoSM(String numero, String gestion, String extension) throws Exception {
        try {
            Long codigoSM;

            String strNumero = "";
            String strGestion = "";
            String strExtension = "";
            //codificar el numero
            strNumero = String.format("%6s", numero).replace(' ', '0');
            //codificar la gestion
            Date fechaActual = obtenerFechaHoraServidor(1L);
            //cargar el objeto calendar con la fecha del sistema
            Calendar fecha = Calendar.getInstance();
            fecha.setTime(fechaActual);
            if (Long.valueOf(gestion) >= 1900
                    && Long.valueOf(gestion) <= fecha.get(Calendar.YEAR)) {
                strGestion = gestion;
            } else {
                strGestion = "0";
            }
            //codificar la extension
            if (!extension.equals("")) {
                strExtension = Long.toString((int) extension.charAt(0) - 55);
            } else {
                strExtension = "00";
            }
            return (Long.valueOf(strGestion + strNumero + strExtension));
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public HashMap obtenerNumeroGestionExtensionCodigoSM(Long codigoSM) throws Exception {
        try {
            HashMap mapResultado = new HashMap();
            String strCodigoSM = Long.toString(codigoSM);
            String strGestion = strCodigoSM.substring(0, 4);
            String strNumero = strCodigoSM.substring(4, 10);
            String strExtension = strCodigoSM.substring(10, 12);
            mapResultado.put("gestion", strGestion);
            //obtener el numero
            strNumero = Long.toString(Long.valueOf(strNumero));
            mapResultado.put("numero", strNumero);
            //obtener la extension
            if (strExtension.equals("00")) {
                mapResultado.put("extension", "");
            } else {
                char ch = (char) (Integer.valueOf(strExtension) + 55);
                mapResultado.put("extension", ch);
            }
            return mapResultado;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public HashMap obtenerNumeroGestionExtensionNumerico(Long codigoSM) throws Exception {
        try {
            HashMap mapResultado = new HashMap();
            String strCodigoSM = Long.toString(codigoSM);
            String strGestion = strCodigoSM.substring(0, 4);
            String strNumero = strCodigoSM.substring(4, 10);
            String strExtension = strCodigoSM.substring(10, 12);
            mapResultado.put("gestion", strGestion);
            //obtener el numero
            strNumero = Long.toString(Long.valueOf(strNumero));
            mapResultado.put("numero", strNumero);
            //obtener la extension

            mapResultado.put("extension",Integer.parseInt(strExtension));

            return mapResultado;
        } catch (Exception e) {
            throw e;
        }
    }

}
