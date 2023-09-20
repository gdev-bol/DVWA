/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.solicitudes.servicio.impl;

import java.util.HashMap;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import snp.gob.bo.gimodel.entidad.ObjetoEliminadoGenerico;
import snp.gob.bo.gimodel.mapper.ObjetoEliminadoGenericoMapper;
import snp.gob.bo.gimodel.solicitudes.entidades.ClasesNiza;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI100;
import snp.gob.bo.gimodel.solicitudes.entidades.Formularios;
import snp.gob.bo.gimodel.solicitudes.entidades.SmSignoClaseNizas;
import snp.gob.bo.gimodel.solicitudes.mapper.ClaseNizaMapper;
import snp.gob.bo.gimodel.solicitudes.mapper.FormulariosMapper;
import snp.gob.bo.gimodel.solicitudes.servicio.FormularioPI100Service;
import snp.gob.bo.gimodel.solicitudes.servicio.FormularioService;

/**
 *
 * @author Eddy Valero
 * @see FormularioPI100
 * @see FormularioPI100Service
 * @version 1.0, 31/08/2016
 */
@Service("formularioService")
public class FormularioServiceImpl implements FormularioService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    @Autowired
    public void setDataSource(DataSource dataSource) throws Exception {
        try {
            this.dataSource = dataSource;
            this.jdbcTemplate = new JdbcTemplate(dataSource);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void actualizarRegistroFormulario(Long idFormulario) throws Exception {
        try {
            String SQL = "update formulario.formularios "
                    + "set estado = 'IN' "
                    + "where id = ?";
            jdbcTemplate.update(SQL, idFormulario);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void actualizarRegistroFormularioSubsanacion(Long idFormulario) throws Exception {
        try {
            String SQL = "update formulario.formularios "
                    + "set estado = 'SU' "
                    + "where id = ?";
            jdbcTemplate.update(SQL, idFormulario);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void habilitarFormularioSubsanacion(Long idFormulario, String subsanacion) throws Exception {
        try {
            String SQL = "update formulario.formularios "
                    + "set subsanacionsec= '" + subsanacion + "'"
                    + "where id = ?";
            jdbcTemplate.update(SQL, idFormulario);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String obtenerListaSigTipoSignoExt(Long idSignoMarca) throws Exception {
        try {
            String SQL = " select signomarca_id::bigint as id, string_agg(sd.nombre,'-') as objeto_eliminado "
                    + " from formulario.smtiposignos st, formulario.smdominio sd "
                    + " where st.tiposigno=sd.codigo and sd.dominio='tipo_signo' "
                    + " and st.signomarca_id=? "
                    + " and st.estado='AC' and sd.estado='AC'"
                    + " group by signomarca_id; ";
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
    public HashMap obtenerNumeroFormularioSubsanacion(String numeroFormularioSub, String numeroFormularioAct) throws Exception {
        HashMap mapResultado = new HashMap();
        try {

            Formularios formularioSubsanacion = jdbcTemplate.queryForObject("select * from formulario.formularios where numeroformulario = ?", new FormulariosMapper(), numeroFormularioSub);
            Formularios formularioActual = jdbcTemplate.queryForObject("select * from formulario.formularios where numeroformulario = ? and (estado='IN' or estado='SU')", new FormulariosMapper(), numeroFormularioAct);
            if (formularioSubsanacion.getId() != null) {
                if (formularioActual.getIdPadre() != null) {
                    if (formularioActual.getId().equals(formularioSubsanacion.getIdPadre().longValue()) && formularioSubsanacion.getEstado().equals("AC")) {

                        mapResultado.put("continuarFlujo", "true");
                        mapResultado.put("tipoFormulario", formularioSubsanacion.getTipoFormulario()); // PI100, PI101, PI102, PI103, PI104 y PI105
                        mapResultado.put("idFormulario", formularioSubsanacion.getId().toString());
                        mapResultado.put("estado", formularioSubsanacion.getEstado());
                        mapResultado.put("mensaje", "Formulario encontrado");

                    } else if (formularioSubsanacion.getEstado().equals("IN")) {
                        mapResultado.put("continuarFlujo", "false");
                        mapResultado.put("mensaje", "El formulario ya fue ingresado");
                    } else if (formularioSubsanacion.getEstado().equals("SU")) {
                        mapResultado.put("continuarFlujo", "false");
                        mapResultado.put("mensaje", "El formulario ya ingresó como subsanación");
                    } else {
                        mapResultado.put("continuarFlujo", "false");
                        mapResultado.put("mensaje", "El Nro. formulario no corresponde para subsanar el trámite actual");
                    }
                } else {
                    mapResultado.put("continuarFlujo", "false");
                    mapResultado.put("mensaje", "No existe el formulario padre para subsanar");
                }

            } else {
                mapResultado.put("continuarFlujo", "false");
                mapResultado.put("mensaje", "No existe el formulario");
                return mapResultado;
            }
            return mapResultado;

        } catch (EmptyResultDataAccessException t) {
            mapResultado.put("continuarFlujo", "false");
            mapResultado.put("mensaje", "No existe el formulario");
            return mapResultado;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public HashMap verificarIngresoTramiteSubsanacion(String numeroFormulario) throws Exception {
        HashMap mapResultado = new HashMap();
        try {
            //Verificar si el formulario ya existe y ademas esta como solicitado
            String SQL = "select * from formulario.formularios where numeroformulario = ? and estado = 'AC'";
            Formularios formularios = jdbcTemplate.queryForObject(SQL, new FormulariosMapper(), numeroFormulario);

            mapResultado.put("mensaje", "el tramite ya fue ingresado");
            mapResultado.put("tipoTramite", formularios.getTipoFormulario());
            mapResultado.put("id", formularios.getId());
            mapResultado.put("continuarFlujo", "false");
            return mapResultado;
        } catch (EmptyResultDataAccessException t) {
            //el tramite no fue ingresado
            mapResultado.put("continuarFlujo", "true");
            return mapResultado;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean existeSubsanacionSignoMarca(String numeroFormulario) throws Exception {
        HashMap mapResultado = new HashMap();
        try {
            //Verificar si el formulario ya existe y ademas esta como solicitado
            /*String SQL = "select * from formulario.formularios where numeroformulario = ? and estado = 'AC'";
             Formularios formularios = jdbcTemplate.queryForObject(SQL, new FormulariosMapper(), numeroFormulario);
             //el tramite ya fue ingresado
             mapResultado.put("mensaje", "el tramite ya fue ingresado");
             mapResultado.put("tipoTramite", formularios.getTipoFormulario());
             mapResultado.put("id", formularios.getId());
             mapResultado.put("continuarFlujo", "false");*/
            return false;
        } catch (EmptyResultDataAccessException t) {
            //el tramite no fue ingresado
            //mapResultado.put("continuarFlujo", "true");
            return false;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ClasesNiza obtenerClaseNiza(Long idClaseNiza) throws Exception {

        try {
            String SQL = "select * from formulario.claseniza where id= ? and estado='AC'";
            ClasesNiza claseNiza = jdbcTemplate.queryForObject(SQL, new ClaseNizaMapper(), idClaseNiza.intValue());
            return claseNiza;

        } catch (EmptyResultDataAccessException e) {
            return new ClasesNiza();

        }      
    }

}
