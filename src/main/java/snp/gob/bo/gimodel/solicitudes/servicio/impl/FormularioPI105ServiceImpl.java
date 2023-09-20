/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.solicitudes.servicio.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import snp.gob.bo.gimodel.solicitudes.entidades.Apoderados;
import snp.gob.bo.gimodel.solicitudes.entidades.DireccionNotificaciones;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI105;
import snp.gob.bo.gimodel.solicitudes.entidades.Formularios;
import snp.gob.bo.gimodel.solicitudes.entidades.OpoMarcaDemandadaExt;
import snp.gob.bo.gimodel.solicitudes.entidades.OpoMarcaDemandanteExt;
import snp.gob.bo.gimodel.solicitudes.entidades.Solicitantes;
import snp.gob.bo.gimodel.solicitudes.mapper.FormulariosMapper;
import snp.gob.bo.gimodel.solicitudes.mapper.OpoMarcaDemandadaExtMapper;
import snp.gob.bo.gimodel.solicitudes.mapper.OpoMarcaDemandanteExtMapper;
import snp.gob.bo.gimodel.solicitudes.servicio.FormularioPI105Service;

/**
 *
 * @author luis_angel
 */
public class FormularioPI105ServiceImpl implements FormularioPI105Service {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    @Override
    public void setDataSource(DataSource dataSource) throws Exception {
         try {
            this.dataSource = dataSource;
            this.jdbcTemplate = new JdbcTemplate(dataSource);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public FormularioPI105 obtenerDatosFormularioPI105(Long idFormulario) throws Exception {
   
     try {
            FormularioPI105 formularioPI105 = new FormularioPI105();
            formularioPI105.setMensaje(null);

            //obtener el idFormulario
            Formularios formularios = jdbcTemplate.queryForObject("select * from formulario.formularios where id = ? ", new FormulariosMapper(), idFormulario);
            formularioPI105.setFormularios(formularios);

            //obtener numero publicacion demandante
            Integer numeropubdmte=jdbcTemplate.queryForObject("select dmte_public from formulario.opomarcademandante where idformulario = ? ", Integer.class, idFormulario);
            formularioPI105.setNropublicacion(numeropubdmte);
            
            //obtener valor sin-marca demandante
            String nombresinmarcadmte=jdbcTemplate.queryForObject("select dmte_sin_marca from formulario.opomarcademandante where idformulario= ? ", String.class, idFormulario);
            formularioPI105.setSinmarca(nombresinmarcadmte);
            
            //obtener valor tipo formulario dmte
            String tipoformulariodmte=jdbcTemplate.queryForObject("select tipoformulario from formulario.formularios where id = ? ", String.class, idFormulario);
            formularioPI105.setTipoFormulario(tipoformulariodmte);
            
            
            //Obtener el listado de todos los solicitantes
            List<Solicitantes> listaSolicitantes = new ArrayList<Solicitantes>();

            List<Map<String, Object>> solicitantes = jdbcTemplate.queryForList("select * from formulario.solicitantes where formulario_id = ? and estado='AC'", formularios.getId());
            for (Map<String, Object> map : solicitantes) {
                Solicitantes solicitante = new Solicitantes();

                solicitante.setNumeroDocumento(map.get("numerodocumento").toString());

                if (map.get("tipodocumento") != null) {
                    solicitante.setTipoDocumento(map.get("tipodocumento").toString());
                } else {
                    solicitante.setTipoDocumento(null);
                }
                
                if (map.get("lugarexpedicion") != null) {
                    solicitante.setLugarExpedicion(map.get("lugarexpedicion").toString());
                } else {
                    solicitante.setLugarExpedicion(null);
                }

                if (map.get("tipopersona") != null) {
                    solicitante.setTipoPersona(map.get("tipopersona").toString());
                } else {
                    solicitante.setTipoPersona(null);

                }

                if (map.get("pais") != null) {
                    solicitante.setPais(map.get("pais").toString());
                } else {
                    solicitante.setPais(null);
                }

                if (map.get("departamentosolicitud") != null) {
                    solicitante.setDepartamentoSolicitud(map.get("departamentosolicitud").toString());
                } else {
                    solicitante.setDepartamentoSolicitud(null);
                }

                if (map.get("nombrerazonsocial") != null) {
                    solicitante.setNombreRazonSocial(map.get("nombrerazonsocial").toString());
                } else {
                    solicitante.setNombreRazonSocial(null);
                }

                if (map.get("primerapellido") != null) {
                    solicitante.setPrimerApellido(map.get("primerapellido").toString());
                } else {
                    solicitante.setPrimerApellido(null);
                }

                if (map.get("segundoapellido") != null) {
                    solicitante.setSegundoApellido(map.get("segundoapellido").toString());
                } else {
                    solicitante.setSegundoApellido(null);
                }

                if (map.get("genero") != null) {
                    solicitante.setGenero(map.get("genero").toString());
                } else {
                    solicitante.setGenero(null);
                }

                if (map.get("domicilio") != null) {
                    solicitante.setDomicilio(map.get("domicilio").toString());
                } else {
                    solicitante.setDomicilio(null);
                }

                if (map.get("telefono") != null) {
                    solicitante.setTelefono(map.get("telefono").toString());
                } else {
                    solicitante.setTelefono(null);
                }

                if (map.get("celular") != null) {
                    solicitante.setCelular(map.get("celular").toString());
                } else {
                    solicitante.setCelular(null);
                }

                if (map.get("correoelectronico") != null) {
                    solicitante.setCorreoElectronico(map.get("correoelectronico").toString());
                } else {
                    solicitante.setCorreoElectronico(null);
                }

                if (map.get("estado") != null) {
                    solicitante.setEstado(map.get("estado").toString());
                } else {
                    solicitante.setEstado(null);
                }

                //armar la lista con cada solicitante
                listaSolicitantes.add(solicitante);
            }
            formularioPI105.setSolicitantes(listaSolicitantes);

//                //Obtener el listado de todos los apoderados del formulario determinado
            List<Apoderados> listaApoderados = new ArrayList<Apoderados>();
            List<Map<String, Object>> apoderados = jdbcTemplate.queryForList("select * from formulario.apoderados where formulario_id = ? and estado='AC'", formularios.getId());
            for (Map<String, Object> map : apoderados) {
                Apoderados apoderado = new Apoderados();

                if (map.get("nombres") != null) {
                    apoderado.setNombres(map.get("nombres").toString());
                } else {
                    apoderado.setNombres(null);
                }

                if (map.get("primerapellido") != null) {
                    apoderado.setPrimerApellido(map.get("primerapellido").toString());
                } else {
                    apoderado.setPrimerApellido(null);
                }

                if (map.get("segundoapellido") != null) {
                    apoderado.setSegundoApellido(map.get("segundoapellido").toString());
                } else {
                    apoderado.setSegundoApellido(null);
                }

                if (map.get("tipodocumento") != null) {
                    apoderado.setTipoDocumento(map.get("tipodocumento").toString());
                } else {
                    apoderado.setTipoDocumento(null);
                }

                if (map.get("numerodocumento") != null) {
                    apoderado.setNumeroDocumento(map.get("numerodocumento").toString());
                } else {
                    apoderado.setNumeroDocumento(null);
                }

                if (map.get("lugarexpedicion") != null) {
                    apoderado.setLugarExpedicion(map.get("lugarexpedicion").toString());
                } else {
                    apoderado.setLugarExpedicion(null);
                }
                
                if (map.get("genero") != null) {
                    apoderado.setGenero(map.get("genero").toString());
                } else {
                    apoderado.setGenero(null);
                }

                if (map.get("domicilio") != null) {
                    apoderado.setDomicilio(map.get("domicilio").toString());
                } else {
                    apoderado.setDomicilio(null);
                }

                if (map.get("telefono") != null) {
                    apoderado.setTelefono(map.get("telefono").toString());
                } else {
                    apoderado.setTelefono(null);
                }

                if (map.get("celular") != null) {
                    apoderado.setCelular(map.get("celular").toString());
                } else {
                    apoderado.setCelular(null);
                }

                if (map.get("numeropoder") != null) {
                    apoderado.setNumeroPoder(map.get("numeropoder").toString());
                } else {
                    apoderado.setNumeroPoder(null);
                }
                
                if (map.get("fechapoder") != null) {
                    apoderado.setFechaPoder((Date) map.get("fechapoder"));
                } else {
                    apoderado.setFechaPoder(null);
                }

                if (map.get("correoelectronico") != null) {
                    apoderado.setCorreoElectronico(map.get("correoelectronico").toString());
                } else {
                    apoderado.setCorreoElectronico(null);
                }

                listaApoderados.add(apoderado);
            }

            formularioPI105.setApoderados(listaApoderados);

            //Obtener el listado de las direcciones
            List<DireccionNotificaciones> listaDirecciones = new ArrayList<DireccionNotificaciones>();
            List<Map<String, Object>> direcciones = jdbcTemplate.queryForList("select * from formulario.direccionnotificaciones where formulario_id = ? and estado='AC'", formularios.getId());
            for (Map<String, Object> map : direcciones) {
                DireccionNotificaciones direccion = new DireccionNotificaciones();

                if (map.get("ciudadnotificacion") != null) {
                    direccion.setCiudadNotificacion(map.get("ciudadnotificacion").toString());
                } else {
                    direccion.setCiudadNotificacion(null);
                }

                if (map.get("zonabarrio") != null) {
                    direccion.setZonaBarrio(map.get("zonabarrio").toString());
                } else {
                    direccion.setZonaBarrio(null);
                }

                if (map.get("avenidacalle") != null) {
                    direccion.setAvenidaCalle(map.get("avenidacalle").toString());
                } else {
                    direccion.setAvenidaCalle(null);
                }

                if (map.get("numerodomicilio") != null) {
                    direccion.setNumeroDomicilio(map.get("numerodomicilio").toString());
                } else {
                    direccion.setNumeroDomicilio(null);
                }

                if (map.get("nombreedificio") != null) {
                    direccion.setNombreEdificio(map.get("nombreedificio").toString());
                } else {
                    direccion.setNombreEdificio(null);
                }

                if (map.get("piso") != null) {
                    direccion.setPiso(map.get("piso").toString());
                } else {
                    direccion.setPiso(null);
                }

                if (map.get("departamento") != null) {
                    direccion.setDepartamento(map.get("departamento").toString());
                } else {
                    direccion.setDepartamento(null);
                }

                if (map.get("telefono") != null) {
                    direccion.setTelefono(map.get("telefono").toString());
                } else {
                    direccion.setTelefono(null);
                }

                if (map.get("celular") != null) {
                    direccion.setCelular(map.get("celular").toString());
                } else {
                    direccion.setCelular(null);
                }

                if (map.get("correoelectronico") != null) {
                    direccion.setCorreoelectronico(map.get("correoelectronico").toString());
                } else {
                    direccion.setCorreoelectronico(null);
                }

                if (map.get("referencia") != null) {
                    direccion.setReferencia(map.get("referencia").toString());
                } else {
                    direccion.setReferencia(null);
                }

                if (map.get("estado") != null) {
                    direccion.setEstado(map.get("estado").toString());
                } else {
                    direccion.setEstado(null);
                }
                listaDirecciones.add(direccion);
            }
            formularioPI105.setDirecciones(listaDirecciones);

     
            OpoMarcaDemandanteExt opoMarcaDemandanteExt = jdbcTemplate.queryForObject("select * from formulario.opomarcademandante where idformulario = ? ", new OpoMarcaDemandanteExtMapper(), formularios.getId());
            formularioPI105.setOpomarcaDemandanteExt(opoMarcaDemandanteExt);

            OpoMarcaDemandadaExt opoMarcaDemandadaExt = jdbcTemplate.queryForObject("select * from formulario.opomarcademandada where idformulario = ? ", new OpoMarcaDemandadaExtMapper(), formularios.getId());
            formularioPI105.setOpoMarcademandadaExt(opoMarcaDemandadaExt);   
        
            return formularioPI105;

        } catch (Exception e) {
            throw e;
        }
    
    }

   
}
