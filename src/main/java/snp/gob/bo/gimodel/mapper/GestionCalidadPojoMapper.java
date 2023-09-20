/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.pojo.GestionCalidadPojo;

/**
 *
 * @author susana
 */
public class GestionCalidadPojoMapper implements RowMapper {

    @Override
    public GestionCalidadPojo mapRow(ResultSet rs, int i) throws SQLException {
        
        GestionCalidadPojo gestionCalidadPojo= new GestionCalidadPojo();
        gestionCalidadPojo.setSm(rs.getBigDecimal("num_sm"));
        gestionCalidadPojo.setGestion(rs.getString("ges_sm"));
        gestionCalidadPojo.setExtension(rs.getString("ext_sm"));
        gestionCalidadPojo.setDiaSol(rs.getInt("dia_fecha_sol"));
        gestionCalidadPojo.setMesSol(rs.getString("mes_fecha_sol"));
        gestionCalidadPojo.setGestionSol(rs.getInt("año_fecha_sol"));
        gestionCalidadPojo.setEtapa(rs.getString("etapa"));
        gestionCalidadPojo.setVentResp(rs.getString("vent_resp"));
        gestionCalidadPojo.setDiaVentRec(rs.getInt("dia_vent_recep"));
        gestionCalidadPojo.setMesVentRec(rs.getString("mes_vent_recep"));
        gestionCalidadPojo.setGestionVentRec(rs.getInt("año_vent_recep"));
        gestionCalidadPojo.setDiaVentFin(rs.getInt("dia_vent_fin"));
        gestionCalidadPojo.setMesVentFin(rs.getString("mes_vent_fin"));
        gestionCalidadPojo.setGestionVentFin(rs.getInt("año_vent_fin"));
        gestionCalidadPojo.setPlazoReal(rs.getInt("plazo_real"));
        gestionCalidadPojo.setMarca(rs.getString("marca"));
        gestionCalidadPojo.setNombreTitular(rs.getString("nom_tit"));
        gestionCalidadPojo.setPaisTitular(rs.getString("pais_titular"));
        gestionCalidadPojo.setNombreApoderado(rs.getString("nom_apo"));
        gestionCalidadPojo.setClase(rs.getInt("clase"));
        gestionCalidadPojo.setEstado(rs.getString("estado"));
        gestionCalidadPojo.setTipoSigno(rs.getString("string_agg"));
        gestionCalidadPojo.setTipoGenero(rs.getString("genero"));
        gestionCalidadPojo.setSucursal(rs.getString("sucursal"));
        gestionCalidadPojo.setDiaReg(rs.getInt("dia_fecha_registro"));
        gestionCalidadPojo.setMesReg(rs.getString("mes_fecha_registro"));
        gestionCalidadPojo.setGestionReg(rs.getInt("año_fecha_registro"));
        return gestionCalidadPojo;
        
      
    }

}
