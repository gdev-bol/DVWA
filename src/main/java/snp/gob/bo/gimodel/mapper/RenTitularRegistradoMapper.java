/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.RenDireccionDeNotificacion;
import snp.gob.bo.gimodel.entidad.RenRenovacion;
import snp.gob.bo.gimodel.entidad.RenSolicitanteApoderado;
import snp.gob.bo.gimodel.entidad.RenSolicitudRenovacion;
import snp.gob.bo.gimodel.entidad.RenTitularRegistrado;

/**
 *
 * @author 
 */
public class RenTitularRegistradoMapper implements RowMapper<RenTitularRegistrado> {
       /**
     * Método que permite mapear la tabla rensolicitanteapoderado
     *
     * @param re
     * @param i
     * @return RenRenovacion
     * @throws java.sql.SQLException
     */
    @Override
    public RenTitularRegistrado  mapRow(ResultSet re, int i) throws SQLException {
        try {
            RenTitularRegistrado renTitularRegistrado = new RenTitularRegistrado();
            renTitularRegistrado.setIdtitularregistrado(re.getLong("idtitularregistrado"));
            renTitularRegistrado.setIdsolicitudrenovacion(re.getLong("idsolicitudrenovacion"));
            renTitularRegistrado.setIdlogtrans(re.getLong("idlogtrans"));
            renTitularRegistrado.setNombre_razonsocial(re.getString("nombre_razonsocial"));
            renTitularRegistrado.setPrimer_apellido(re.getString("primer_apellido"));
            renTitularRegistrado.setSegundo_apellido(re.getString("segundo_apellido"));
            renTitularRegistrado.setDireccion(re.getString("direccion"));
            renTitularRegistrado.setEstado(re.getString("estado"));
            renTitularRegistrado.setIdSipi(re.getLong("id_sipi"));
            return renTitularRegistrado;
        } catch (SQLException e) {
            throw e;
        }
    }
}
