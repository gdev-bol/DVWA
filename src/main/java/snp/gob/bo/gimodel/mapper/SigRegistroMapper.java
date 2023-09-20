/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.SigRegistro;

/**
 *
 * @author susana
 */
public class SigRegistroMapper implements RowMapper<SigRegistro> {

    @Override
    public SigRegistro mapRow(ResultSet rs, int i) throws SQLException {
        SigRegistro sigRegistro = new SigRegistro();
        sigRegistro.setIdRegistro(rs.getLong("idregistro"));
        sigRegistro.setIdSignoMarca(rs.getLong("idsignomarca"));
        sigRegistro.setIdLogTrans(rs.getLong("idlogtrans"));
        sigRegistro.setNumeroRegistro(rs.getLong("numero_registro"));
        sigRegistro.setSerie(rs.getString("serie"));
        sigRegistro.setSm(rs.getLong("sm"));
        sigRegistro.setSmDescripcion(rs.getString("sm_descripcion"));
        sigRegistro.setEstadoRegistro(rs.getString("estado_registro"));
        sigRegistro.setFechaIngreso(rs.getTimestamp("fecha_ingreso"));
        sigRegistro.setIdTipoResolucion(rs.getInt("idtipo_resolucion"));
        sigRegistro.setDocumentoResolucion(rs.getString("documento_resolucion"));
        sigRegistro.setSigno(rs.getString("signo"));
        sigRegistro.setTipoSignoDescripcion(rs.getString("tipo_signo_descripcion"));
        sigRegistro.setTipoGeneroDescripcion(rs.getString("tipo_genero_descripcion"));
        sigRegistro.setClase(rs.getInt("clase"));
        sigRegistro.setNumeroResolucion(rs.getLong("numero_resolucion"));
        sigRegistro.setGestionResolucion(rs.getInt("gestion_resolucion"));
        sigRegistro.setFechaRegistro(rs.getTimestamp("fecha_registro"));
        sigRegistro.setNumeroPublicacion(rs.getLong("numero_publicacion"));
        sigRegistro.setFechaPublicacion(rs.getTimestamp("fecha_publicacion"));
        sigRegistro.setNumeroGaceta(rs.getLong("numero_gaceta"));
        sigRegistro.setFechaPrimerUso(rs.getTimestamp("fecha_primer_uso"));
        sigRegistro.setTitular(rs.getString("titular"));
        sigRegistro.setDireccionTitular(rs.getString("direccion_titular"));
        sigRegistro.setPaisTitular(rs.getString("pais_titular"));
        sigRegistro.setPaisTitularDescripcion(rs.getString("pais_titular_descripcion"));
        sigRegistro.setNombreApoderado(rs.getString("nombre_apoderado"));
        sigRegistro.setDireccionApoderado(rs.getString("direccion_apoderado"));
        sigRegistro.setListaProductos(rs.getString("lista_productos"));
        sigRegistro.setObservacion(rs.getString("observacion"));
        sigRegistro.setEstado(rs.getString("estado"));
        return sigRegistro;
    }
}
