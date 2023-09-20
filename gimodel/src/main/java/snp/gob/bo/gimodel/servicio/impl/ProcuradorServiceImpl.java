/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.Procurador;
import snp.gob.bo.gimodel.mapper.ProcuradorMapper;
import snp.gob.bo.gimodel.servicio.ProcuradorService;

/**
 *
 * @author luisangel
 */
@Service("procuradorService")
public class ProcuradorServiceImpl implements ProcuradorService{
    
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    
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
    public Procurador guardar_modificar_listar_Procurador(Procurador procurador, Integer operacion) {
       
        Procurador procuradorNuevo = new Procurador();
        String SQL = "select * from crud_procurador(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        procuradorNuevo = (Procurador) jdbcTemplate.queryForObject(SQL, new ProcuradorMapper(),
               procurador.getIdprocurador(),
               procurador.getIdlogtrans(),
               procurador.getNombre_razonsocial(),
               procurador.getPrimer_apellido(),
               procurador.getSegundo_apellido(),
               procurador.getTipo_titular(),
               procurador.getNumero_documento(),
               procurador.getTipo_documento(),
               procurador.getLugar_expedicion(),
               procurador.getTelefono(),
               procurador.getCelular(),
               procurador.getTipodocumentoautorizacion(),
               procurador.getDescripciondocumentoautorizacion(),
               procurador.getEstado(),
                operacion
        );
        return procuradorNuevo;
                
    }
    
    @Override
    public Procurador procuradorPorId(Long id) {
        String SQL = "select * from procurador where idprocurador=?;";
        Procurador procuradorNuevo = (Procurador) jdbcTemplate.queryForObject(SQL, new ProcuradorMapper(),id);
        return procuradorNuevo;        
    }
   
}
