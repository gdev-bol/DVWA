/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.Deposito;


/**
 *
 * @author chano rojas
 */
public class DepositoMapper implements RowMapper<Deposito> {
    @Override
    public Deposito mapRow(ResultSet r, int i) throws SQLException {
        Deposito deposito = new Deposito();
        deposito.setIdDeposito(r.getLong("iddeposito"));
        deposito.setIdLogTrans(r.getLong("idlogtrans"));
        deposito.setBanco(r.getString("banco"));
        deposito.setNumeroDeposito(r.getLong("numero_deposito"));
        deposito.setFechaDeposito(r.getTimestamp("fecha_deposito"));
        deposito.setMonto(r.getBigDecimal("monto"));
        deposito.setDeposCodDep(r.getInt("depos_cod_dep"));
        deposito.setDeposCodAgencia(r.getInt("depos_cod_agencia"));
        deposito.setNombreDepositante(r.getString("nombre_depositante"));
        deposito.setCodAgencia(r.getString("cod_agencia"));
        deposito.setCodDepositante(r.getString("cod_depositante"));
        deposito.setSaldo(r.getBigDecimal("saldo"));
        deposito.setFechaRegistroDeposito(r.getTimestamp("fecha_registro_deposito"));
        deposito.setSucursalBanco(r.getString("sucursal_banco"));
        deposito.setEstado(r.getString("estado"));
        deposito.setIdUsuario(r.getLong("idusuario"));
        return deposito;
    }
    
}
