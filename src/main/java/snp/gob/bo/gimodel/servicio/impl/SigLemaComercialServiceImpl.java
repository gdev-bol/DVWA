/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.SigLemaComercial;
import snp.gob.bo.gimodel.mapper.SigLemaComercialMapper;
import snp.gob.bo.gimodel.servicio.SigLemaComercialService;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 02/09/2016
 */
@Service("sigLemaComercialService")
public class SigLemaComercialServiceImpl implements SigLemaComercialService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    
    
    @Override
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    
    
//    @Override
//    public SigLogoTipo registrarSigLogoTipo(SigLogoTipo sigLogoTipo) throws Exception {
//        try {
//            String SQL = "select * from reg_siglogotipo(?, ?, ?, ?, ?, ?, ?)";
//            SigLogoTipo sigLogo = (SigLogoTipo) jdbcTemplate.queryForObject(
//                    SQL,
//                    new SigLogoTipoMapper(),
//                    sigLogoTipo.getIdSignoMarca(),
//                    sigLogoTipo.getIdLogTrans(),
//                    sigLogoTipo.getUrlLogoTipo(),
//                    sigLogoTipo.getPrincipal(),
//                    sigLogoTipo.getNombreArchivo(),
//                    sigLogoTipo.getExtensionArchivo(),
//                    sigLogoTipo.getEstado()
//            );
//            return sigLogo;
//        } catch (Exception e) {
//            throw e;
//        }
//    }

    @Override
    public SigLemaComercial guardarSigLemaComercial(SigLemaComercial sigLemaComercial) throws Exception {
        try {
            String SQL = "select * from crud_siglemacomercial(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            SigLemaComercial sigLemaComercialResultado = (SigLemaComercial) jdbcTemplate.queryForObject(
                    SQL,
                    new SigLemaComercialMapper(),
                    sigLemaComercial.getIdLemaComercial(),
                    sigLemaComercial.getIdSignoMarca(),
                    sigLemaComercial.getSignoPadre(),
                    sigLemaComercial.getSmPadre(),
                    sigLemaComercial.getCodigoSmPadre(),
                    sigLemaComercial.getNumeroRegistroPadre(),
                    sigLemaComercial.getSerieRegistroPadre(),
                    sigLemaComercial.getVigencia(),
                    sigLemaComercial.getEstado(),
                    "REGISTRAR"
            );
            return sigLemaComercialResultado;
        } catch (Exception e) {
            throw e;
        }
    }

    
    @Override
    public SigLemaComercial obtenerSigLemaComercial(Long idsignomarca) throws Exception {
        try {
            String SQL = "select * from siglemacomercial where idsignomarca=? and estado='AC';";
            SigLemaComercial sigLemaComercial = jdbcTemplate.queryForObject(SQL, new Object[]{idsignomarca}, new SigLemaComercialMapper());    
            return sigLemaComercial;
        } catch (EmptyResultDataAccessException e) {
            return new SigLemaComercial();  
        } catch (Exception e) {
            throw e;
        }
    }
}
