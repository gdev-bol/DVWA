/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.bdimagen.servicio.impl;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.bdimagen.entidad.SigLogoTipo;
import snp.gob.bo.gimodel.bdimagen.mapper.SigLogoTipoMapper;
import snp.gob.bo.gimodel.bdimagen.servicio.SigLogoTipoService;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 27/08/2016
 */
@Service("sigLogoTipoService")
public class SigLogoTipoServiceImpl implements SigLogoTipoService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public SigLogoTipo registrarSigLogoTipo(SigLogoTipo sigLogoTipo) throws Exception {
        try {
            String SQL = "select * from reg_siglogotipo(?, ?, ?, ?, ?, ?, ?)";
                SigLogoTipo sigLogo = (SigLogoTipo) jdbcTemplate.queryForObject(
                    SQL,
                    new SigLogoTipoMapper(),
                    sigLogoTipo.getIdSignoMarca(),
                    sigLogoTipo.getIdLogTrans(),
                    sigLogoTipo.getUrlLogoTipo(),
                    sigLogoTipo.getPrincipal(),
                    sigLogoTipo.getNombreArchivo(),
                    sigLogoTipo.getExtensionArchivo(),
                    sigLogoTipo.getEstado()
            );
            return sigLogo;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<SigLogoTipo> obtenerSigLogoTipoXIdSignoMarca(Long idSignoMarca) throws Exception {
        try {
            String SQL = "select * from siglogotipo where idsignomarca = ? and estado = 'AC' ";

            List<SigLogoTipo> listaSigLogoTipo = this.jdbcTemplate.query(SQL, new Object[]{idSignoMarca}, new SigLogoTipoMapper());
            return listaSigLogoTipo;

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public SigLogoTipo guardar_modificar_listar_SigLogoTipo(SigLogoTipo sigLogoTipo, Integer operacion) throws Exception {
        try {
            String SQL = "select * from crud_siglogotipo( ?, ?, ?, ?, ?, ?, ?, ?, ?); ";
            SigLogoTipo sigLogotipoBase = (SigLogoTipo) jdbcTemplate.queryForObject(SQL,
                    new SigLogoTipoMapper(),
                    sigLogoTipo.getIdLogoTipo(),
                    sigLogoTipo.getIdSignoMarca(),
                    sigLogoTipo.getIdLogTrans(),
                    sigLogoTipo.getUrlLogoTipo(),
                    sigLogoTipo.getPrincipal(),
                    sigLogoTipo.getNombreArchivo(),
                    sigLogoTipo.getExtensionArchivo(),
                    sigLogoTipo.getEstado(),
                    operacion
            );
            if (sigLogotipoBase.getIdLogoTipo() != null) {
                return sigLogotipoBase;
            }
            return new SigLogoTipo();
        } catch (EmptyResultDataAccessException e) {
            return new SigLogoTipo();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public SigLogoTipo obtenerRegistroSigLogoTipoXIdSignoMarca(Long idSignoMarca) throws Exception {
        try {
            String SQL = " select * from siglogotipo where idsignomarca = ? and estado = 'AC' and principal = true limit 1";
            SigLogoTipo sigLogoTipo = (SigLogoTipo) jdbcTemplate.queryForObject(
                    SQL,
                    new SigLogoTipoMapper(),
                    idSignoMarca
            );
            return sigLogoTipo;

        }catch (EmptyResultDataAccessException e) {
            return new SigLogoTipo();
        }
        catch (Exception e) {
            throw e;
        }
    }

    @Override
    public SigLogoTipo obtenerRegistroPrincipalSigLogoTipoXIdSignoMarca(Long idSignoMarca) throws Exception {
         try {
            String SQL = "select * from siglogotipo where idsignomarca = ? and estado = 'AC' and principal = true limit 1";
            SigLogoTipo sigLogoTipo = (SigLogoTipo) jdbcTemplate.queryForObject(
                    SQL,
                    new SigLogoTipoMapper(),
                    idSignoMarca
            );
            return sigLogoTipo;

        }catch (EmptyResultDataAccessException e) {
            return new SigLogoTipo();
        }
        catch (Exception e) {
            throw e;
        }
    }

}
