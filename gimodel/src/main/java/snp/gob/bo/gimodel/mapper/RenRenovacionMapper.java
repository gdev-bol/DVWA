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

/**
 *
 * @author eddy
 */
public class RenRenovacionMapper implements RowMapper<RenRenovacion> {

       /**
     * Método que permite mapear la tabla renrenovacion con la entidad renrenovacion
     *
     * @param re
     * @param ren
     * @param i
     * @return RenRenovacion
     * @throws java.sql.SQLException
     */
    @Override
    public RenRenovacion mapRow(ResultSet re, int i) throws SQLException {
        try {
            RenRenovacion renRenovacion = new RenRenovacion();
            renRenovacion.setIdrenovacion(re.getLong("idrenovacion"));
            renRenovacion.setIdsolicitudrenovacion(re.getLong("idsolicitudrenovacion"));
            renRenovacion.setIdlogtrans(re.getLong("idlogtrans"));
            renRenovacion.setNumero_renovacion(re.getInt("numero_renovacion"));
            renRenovacion.setSerie_renovacion(re.getString("serie_renovacion"));
            renRenovacion.setOrden_renovacion(re.getInt("orden_renovacion"));
            renRenovacion.setIdclase_niza_renovacion(re.getLong("idclase_niza_renovacion"));
            renRenovacion.setFecha_concesion(re.getTimestamp("fecha_concesion"));
            renRenovacion.setTitular(re.getString("titular"));
            renRenovacion.setApoderado(re.getString("apoderado"));
            renRenovacion.setTipo_marca(re.getString("tipo_marca"));
            renRenovacion.setTipo_genero(re.getString("tipo_genero"));
            renRenovacion.setSigno_registrado(re.getString("signo_registrado"));
            renRenovacion.setEtiqueta_renovacion(re.getString("etiqueta_renovacion"));
            renRenovacion.setNumero_registro(re.getInt("numero_registro"));
            renRenovacion.setSerie_registro(re.getString("serie_registro"));
            renRenovacion.setIdclase_niza_actual(re.getLong("idclase_niza_actual"));
            renRenovacion.setSr_manual(re.getInt("sr_manual"));
            renRenovacion.setGestion_sr_manual(re.getInt("gestion_sr_manual"));
            renRenovacion.setFecha_registro_manual(re.getTimestamp("fecha_registro_manual"));
            renRenovacion.setFecha_ingreso(re.getTimestamp("fecha_ingreso"));
            renRenovacion.setLista_producto_actual(re.getString("lista_producto_actual"));
            renRenovacion.setLista_producto_actual(re.getString("lista_producto_renovacion"));
            renRenovacion.setVersion_clase_niza(re.getString("version_clase_niza"));
            renRenovacion.setFecha_renovacion(re.getTimestamp("fecha_renovacion"));
            renRenovacion.setGestion_renovacion(re.getInt("gestion_renovacion"));
            renRenovacion.setEstado(re.getString("estado"));
            return renRenovacion;
        } catch (Exception e) {
            throw e;
        }
    }
}
