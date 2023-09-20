/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.RenResolucion;

/**
 *
 * @author ChanoRojas
 */
public class RenResolucionMapper implements RowMapper<RenResolucion> {

    /**
     * Método que permite mapear la tabla renresolucion
     *
     * @param re
     * @param i
     * @return RenResolucion
     * @throws java.sql.SQLException
     */
    @Override
    public RenResolucion mapRow(ResultSet re, int i) throws SQLException {
        try {
            RenResolucion renResolucion = new RenResolucion();
            renResolucion.setIdresolucion(re.getLong("idresolucion"));
            renResolucion.setIdrenovacion(re.getLong("idrenovacion"));
            renResolucion.setNumero_resolucion(re.getInt("numero_resolucion"));
            renResolucion.setGestion_resolucion(re.getInt("gestion_resolucion"));
            renResolucion.setFecha_resolucion(re.getTimestamp("fecha_resolucion"));
            renResolucion.setObservacion_resolucion(re.getString("observacion_resolucion"));
            renResolucion.setDocumento_resolucion(re.getString("documento_resolucion"));
            renResolucion.setEstado(re.getString("estado"));
            return renResolucion;
        } catch (SQLException e) {
            throw e;
        }
    }

}
