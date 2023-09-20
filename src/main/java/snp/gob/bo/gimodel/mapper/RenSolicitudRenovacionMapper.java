/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.RenRenovacion;
import snp.gob.bo.gimodel.entidad.RenSolicitudRenovacion;

/**
 *
 * @author 
 */
public class RenSolicitudRenovacionMapper implements RowMapper<RenSolicitudRenovacion> {
       /**
     * Método que permite mapear la tabla renrenovacion con la entidad renrenovacion
     *
     * @param re
     * @param i
     * @return RenRenovacion
     * @throws java.sql.SQLException
     */
    @Override
    public RenSolicitudRenovacion mapRow(ResultSet re, int i) throws SQLException {
        try {
            RenSolicitudRenovacion renSolicitudRenovacion = new RenSolicitudRenovacion();
            renSolicitudRenovacion.setIdsolicitudrenovacion(re.getLong("idsolicitudrenovacion"));
            renSolicitudRenovacion.setIdlogtrans(re.getLong("idlogtrans"));
            renSolicitudRenovacion.setId_recibo_solicitud(re.getLong("id_recibo_solicitud"));
            renSolicitudRenovacion.setId_recibo_titulo(re.getLong("id_recibo_titulo"));
            renSolicitudRenovacion.setSr(re.getLong("sr"));
            renSolicitudRenovacion.setGestion_sr(re.getInt("gestion_sr"));
            renSolicitudRenovacion.setFecha_ingreso(re.getTimestamp("fecha_ingreso"));
            renSolicitudRenovacion.setNumero_formulario(re.getLong("nro_formulario"));
            renSolicitudRenovacion.setEstado_renovacion(re.getString("estado_renovacion"));
            renSolicitudRenovacion.setUbicacion_renovacion(re.getString("ubicacion_renovacion"));
            renSolicitudRenovacion.setNumero_ultima_renovacion(re.getInt("numero_ultima_renovacion"));
            renSolicitudRenovacion.setSerie_ultima_renovacion(re.getString("serie_ultima_renovacion"));
            renSolicitudRenovacion.setNumero_penultima_renovacion(re.getInt("numero_penultima_renovacion"));
            renSolicitudRenovacion.setSerie_penultima_renovacion(re.getString("serie_penultima_renovacion"));
            renSolicitudRenovacion.setOficina(re.getString("oficina"));
            renSolicitudRenovacion.setNumero_recibo(re.getLong("numero_recibo"));
            renSolicitudRenovacion.setSerie_recibo(re.getString("serie_recibo"));
            renSolicitudRenovacion.setNumero_titulo(re.getLong("numero_titulo"));
            renSolicitudRenovacion.setSerie_titulo(re.getString("serie_titulo"));
            renSolicitudRenovacion.setIdclase_niza_reclasificacion(re.getLong("idclase_niza_reclasificacion"));
            renSolicitudRenovacion.setLista_productos_limitacion(re.getString("lista_productos_limitacion"));
            renSolicitudRenovacion.setSm(re.getLong("sm"));
            renSolicitudRenovacion.setSigno_registrado(re.getString("signo_registrado"));
            renSolicitudRenovacion.setIdclase_niza_registrado(re.getLong("idclase_niza_registrado"));
            renSolicitudRenovacion.setTipo_genero(re.getString("tipo_genero"));
            renSolicitudRenovacion.setNumero_registro_registrado(re.getLong("numero_registro_registrado"));
            renSolicitudRenovacion.setSerie_registro_registrado(re.getString("serie_registro_registrado"));
            renSolicitudRenovacion.setFecha_registro_registrado(re.getTimestamp("fecha_registro_registrado"));
            renSolicitudRenovacion.setMarca_acomp(re.getString("marca_acomp"));
            renSolicitudRenovacion.setReg_lc_registrado(re.getLong("reg_lc_registrado"));
            renSolicitudRenovacion.setSerie_lc_registrado(re.getString("serie_lc_registrado"));
            renSolicitudRenovacion.setFecha_lc_registrado(re.getTimestamp("fecha_lc_registrado"));
            renSolicitudRenovacion.setEstado(re.getString("estado"));
            return renSolicitudRenovacion;
        } catch (Exception e) {
            throw e;
        }
    }
}
