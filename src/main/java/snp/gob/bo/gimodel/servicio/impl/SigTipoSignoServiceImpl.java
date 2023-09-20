/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.ObjetoEliminadoGenerico;
import snp.gob.bo.gimodel.entidad.SigTipoSigno;
import snp.gob.bo.gimodel.enums.EnumEstado;
import snp.gob.bo.gimodel.mapper.ObjetoEliminadoGenericoMapper;
import snp.gob.bo.gimodel.mapper.SigTipoSignoMapper;
import snp.gob.bo.gimodel.servicio.SigTipoSignoService;
import snp.gob.bo.gimodel.solicitudes.entidades.SmTipoSignos;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 26/08/2016
 */
@Service("sigTipoSignoService")
public class SigTipoSignoServiceImpl implements SigTipoSignoService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public SigTipoSigno crudSigTipoSigno(SigTipoSigno sigTipoSigno, int parametro) throws Exception {
        try {

            String SQL = "select * from crud_sigtiposigno("
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?);";
            SigTipoSigno rentipo = (SigTipoSigno) jdbcTemplate.queryForObject(SQL, new SigTipoSignoMapper(),
                    sigTipoSigno.getIdTipoSigno(),
                    sigTipoSigno.getIdSignoMarca(),
                    sigTipoSigno.getIdLogTrans(),
                    sigTipoSigno.getTipoSigno(),
                    sigTipoSigno.getDescripcionOtro(),
                    sigTipoSigno.getEstado(),
                    parametro);
            return rentipo;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public SigTipoSigno registrarSigTipoSigno(SigTipoSigno sigTipoSigno) throws Exception {
        try {
            String SQL = "select * from reg_tiposigno(?, ?, ?, ?, ?);";
            SigTipoSigno sigTipo = (SigTipoSigno) jdbcTemplate.queryForObject(
                    SQL,
                    new SigTipoSignoMapper(),
                    sigTipoSigno.getIdSignoMarca(),
                    sigTipoSigno.getIdLogTrans(),
                    sigTipoSigno.getTipoSigno(),
                    sigTipoSigno.getDescripcionOtro(),
                    sigTipoSigno.getEstado()
            );
            return sigTipo;

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<SigTipoSigno> listaSigTipoSignoXidSIgnoMarca(Long idsignoMarca) {

        String SQL = "select * from lista_sigtiposigno_idsignomarca(?);";
        List<SigTipoSigno> listatipoSigno = jdbcTemplate.query(SQL, new SigTipoSignoMapper(), idsignoMarca);
        if (!listatipoSigno.isEmpty()) {

            return listatipoSigno;
        }
        return new ArrayList<SigTipoSigno>();
    }

    @Override
    public void guardarListaSigTipoSigno(List<SigTipoSigno> listaTipoSigno, Long idLogTrans, Long idSignoMarca, int parametro) throws Exception {
        try {
            eliminarListaSigTipoSignoXIdSignoMarca(idSignoMarca);
            for (SigTipoSigno sigTipoSigno : listaTipoSigno) {
                sigTipoSigno.setIdSignoMarca(idSignoMarca);
                sigTipoSigno.setIdLogTrans(idLogTrans);
                sigTipoSigno.setEstado("AC");
                guardarListarModificarSigTipoSigno(sigTipoSigno, parametro);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public SigTipoSigno guardarListarModificarSigTipoSigno(SigTipoSigno sigTipoSigno, int parametro) {
        try {
            String SQL = "select * from crud_sigtiposigno("
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?);";
            SigTipoSigno sigTipoSignoAux = (SigTipoSigno) jdbcTemplate.queryForObject(SQL, new SigTipoSignoMapper(),
                    sigTipoSigno.getIdTipoSigno(),
                    sigTipoSigno.getIdSignoMarca(),
                    sigTipoSigno.getIdLogTrans(),
                    sigTipoSigno.getTipoSigno(),
                    sigTipoSigno.getDescripcionOtro(),
                    sigTipoSigno.getEstado(),
                    parametro);
            return sigTipoSignoAux;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void eliminarListaSigTipoSignoXIdSignoMarca(Long idSignoMarca) throws Exception {
        try {

            List<SigTipoSigno> lstSigTipoSigno = listaSigTipoSignoXidSIgnoMarca(idSignoMarca);
            for (SigTipoSigno sigTipoSigno : lstSigTipoSigno) {
                eliminarSigTipoSigno(sigTipoSigno, 3);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void eliminarSigTipoSigno(SigTipoSigno sigTipoSigno, int parametro) throws Exception {
        try {
            String SQL = "select * from crud_sigtiposigno("
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?);";
            jdbcTemplate.query(SQL, new SigTipoSignoMapper(),
                    sigTipoSigno.getIdTipoSigno(),
                    sigTipoSigno.getIdSignoMarca(),
                    sigTipoSigno.getIdLogTrans(),
                    sigTipoSigno.getTipoSigno(),
                    sigTipoSigno.getDescripcionOtro(),
                    sigTipoSigno.getEstado(),
                    parametro);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void modificarSigTipoSigno(Long idSignoMarca, List<SigTipoSigno> lstSigTipoSigno, Long idLogTrans) throws Exception {

        try {
            int parametroModificar = 2;
            int parametroGuardar = 1;
            int sw, swOtro;
            List<SigTipoSigno> listaTipoSignoBase = listaSigTipoSignoXidSIgnoMarca(idSignoMarca);

            for (SigTipoSigno sigTipoSignoBase : listaTipoSignoBase) {
                sw = 0;
                swOtro = 0;
                for (SigTipoSigno sigTipoSignoVista : lstSigTipoSigno) {
                    if (sigTipoSignoBase.getTipoSigno().equals(sigTipoSignoVista.getTipoSigno())) {
                        if (sigTipoSignoVista.getDescripcionOtro() != null) {
                            if (sigTipoSignoBase.getDescripcionOtro() != null) {
                                if (!sigTipoSignoVista.getDescripcionOtro().equals(sigTipoSignoBase.getDescripcionOtro())) {
                                    sigTipoSignoBase.setDescripcionOtro(sigTipoSignoVista.getDescripcionOtro());
                                    swOtro = 1;
                                    sw = 1;
                                    break;
                                }
                            }
                        }
                        sw = 1;
                        break;
                    }
                }
                if (sw == 0) { // Eliminación logica del tipo signo
                    sigTipoSignoBase.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                    crudSigTipoSigno(sigTipoSignoBase, parametroModificar);
                }
                if (swOtro == 1) { // Actualiza la descripcion de tipo signo:Otro
                    crudSigTipoSigno(sigTipoSignoBase, parametroModificar);
                }
            }

            for (SigTipoSigno sigTipoSignoVista : lstSigTipoSigno) {
                sw = 0;
                for (SigTipoSigno sigTipoSignoBase : listaTipoSignoBase) {
                    if (sigTipoSignoVista.getTipoSigno().equals(sigTipoSignoBase.getTipoSigno())) {
                        sw = 1;
                        break;
                    }
                }
                if (sw == 0) { // Adiciona nuevo tipo signo
                    sigTipoSignoVista.setIdSignoMarca(idSignoMarca);
                    sigTipoSignoVista.setIdLogTrans(idLogTrans);
                    sigTipoSignoVista.setEstado(EnumEstado.ACTIVO.getCodigo());
                    crudSigTipoSigno(sigTipoSignoVista, parametroGuardar);
                }
            }

        } catch (Exception e) {
            throw e;
        }

    }


    @Override
    public void modificarSigTipoSignoSubsanacion(Long idSignoMarca, List<SigTipoSigno> lstSigTipoSigno, List<SmTipoSignos> listaTipoSignoSipi, Long idLogTrans) throws Exception {

        try {
            int parametroAdicionar = 1;
            int parametroModificar = 2;
            int sw;
            
            // Recuperamos la Tipo Signo desde la base de datos
            List<SigTipoSigno> listaTipoSignoHidra = listaSigTipoSignoXidSIgnoMarca(idSignoMarca);

            for (SigTipoSigno tipoSignoHidra : listaTipoSignoHidra) {
                sw = 0;
                for (SmTipoSignos tipoSignoSipi : listaTipoSignoSipi) {
                    if (tipoSignoHidra.getTipoSigno().equals(tipoSignoSipi.getTipoSigno())) {
                        sw = 1;
                        break;
                    }
                }
                if (sw == 0) {  // Eliminación logica del tipo signo
                    tipoSignoHidra.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                    crudSigTipoSigno(tipoSignoHidra, parametroModificar);
                }
            }

            for (SmTipoSignos tipoSignoSipi : listaTipoSignoSipi) {
                sw = 0;
                for (SigTipoSigno tipoSignoHidra : listaTipoSignoHidra) {
                    if (tipoSignoSipi.getTipoSigno().equals(tipoSignoHidra.getTipoSigno())) {
                        sw = 1;
                        break;
                    }
                }
                if (sw == 0) { // Adicionar nuevo tipo signo
                    SigTipoSigno sigTipoSignoVista = new SigTipoSigno();
                    sigTipoSignoVista.setIdSignoMarca(idSignoMarca);
                    sigTipoSignoVista.setIdLogTrans(idLogTrans);
                    sigTipoSignoVista.setTipoSigno(tipoSignoSipi.getTipoSigno());
                    sigTipoSignoVista.setDescripcionOtro(tipoSignoSipi.getDescripcionOtro());
                    sigTipoSignoVista.setEstado(EnumEstado.ACTIVO.getCodigo());
                    crudSigTipoSigno(sigTipoSignoVista, parametroAdicionar);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    @Override
    public String lista_SigTipoSigno_concatenado(String prefijo, Long idSignoMarca) {
        String SQL = "select * from lista_tiposigno_tramite_concatenado(?,?);";
        return jdbcTemplate.queryForObject(SQL, String.class, prefijo, idSignoMarca);
    }

    @Override
    public String obtenerListaSigTipoSigno(Long idSignoMarca) throws Exception {
        try {
            String SQL = " select idsignomarca id, string_agg(concat(tipo_signo,' ', descripcion_otro), ' ') objeto_eliminado "
                    + "from sigtiposigno "
                    + " where idsignomarca = ? "
                    + " and estado = 'AC' "
                    + "group by 1 ";
            ObjetoEliminadoGenerico objetoEliminadoGenerico = jdbcTemplate.queryForObject(SQL,
                    new ObjetoEliminadoGenericoMapper(), idSignoMarca);

            return objetoEliminadoGenerico.getObjetoEliminado();

        } catch (EmptyResultDataAccessException e) {
            return "";
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Boolean preguntaEsDenominativo(Long idSignoMarca) {
        Boolean valor = false;
        List<SigTipoSigno> lstSigTipoSigno = listaSigTipoSignoXidSIgnoMarca(idSignoMarca);
        if (lstSigTipoSigno.size() == 1) {
            if (lstSigTipoSigno.get(0).getTipoSigno().equals("DEN")) {
                valor = true;
            }
        } else {
            valor = false;
        }
//        if (!listatipoSigno.isEmpty()) {
//            if (listatipoSigno.size()==1) {
//                for (SigTipoSigno item : listatipoSigno) {
//                    if (item.getTipoSigno().equals("DEN")) {
//                        valor = true;
//                    }
//                }
//            }
//        }
        return valor;
    }
}
