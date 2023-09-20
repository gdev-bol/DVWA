/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.BusquedaModificacionResultado;
import snp.gob.bo.gimodel.entidad.ModModificacion;
import snp.gob.bo.gimodel.entidad.RenRenovacion;
import snp.gob.bo.gimodel.entidad.RenSolicitudRenovacion;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.mapper.ModModificacionMapper;
import snp.gob.bo.gimodel.mapper.RenRenovacionMapper;
import snp.gob.bo.gimodel.mapper.RenSolicitudRenovacionMapper;
import snp.gob.bo.gimodel.mapper.SigSignoMarcaMapper;
import snp.gob.bo.gimodel.pojo.HistorialRenovacionModificacionPojo;
import snp.gob.bo.gimodel.servicio.BusquedaModificacionResultadoService;
import snp.gob.bo.gimodel.servicio.RenResolucionService;
import snp.gob.bo.gimodel.servicio.SigSignoMarcaService;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI100;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI101;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI102;

/**
 *
 * @author eddy
 */
@Service("sigSignoMarcaService")
public class SigSignoMarcaServiceImpl implements SigSignoMarcaService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    BusquedaModificacionResultadoService busquedaModificacionResultadoService;

    @Override
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public SigSignoMarca obtenerSigSignoMarcaXidSignoMarca(Long idSignoMarca) throws Exception {
        try {
            String SQL = "select * from sigsignomarca where idsignomarca = ?";
            SigSignoMarca signoMarca = (SigSignoMarca) jdbcTemplate.queryForObject(SQL,
                    new SigSignoMarcaMapper(),
                    idSignoMarca);
            return signoMarca;

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<SigSignoMarca> obtenerRegistrosSMSignoMarca() {
        String SQL = "select * from smsignomarca";
        List<SigSignoMarca> listaSMSignoMarcas = jdbcTemplate.query(SQL, new SigSignoMarcaMapper());
        if (!listaSMSignoMarcas.isEmpty()) {
            return listaSMSignoMarcas;
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public SigSignoMarca registrarSigSignoMarca(SigSignoMarca sigSignoMarca) throws Exception {

        try {
            String SQL = "select * from reg_signomarca(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            SigSignoMarca signoMarca = (SigSignoMarca) jdbcTemplate.queryForObject(SQL,
                    new SigSignoMarcaMapper(),
                    sigSignoMarca.getIdLogTrans(),
                    sigSignoMarca.getNumeroFormulario(),
                    sigSignoMarca.getSm(),
                    sigSignoMarca.getSigno(),
                    sigSignoMarca.getTipoGenero(),
                    sigSignoMarca.getDescripcionSigno(),
                    sigSignoMarca.getDescripcionLogotipo(),
                    sigSignoMarca.getUbicacion(),
                    sigSignoMarca.getEstadoMarca(),
                    sigSignoMarca.getNumeroRecibo(),
                    sigSignoMarca.getSerie(),
                    sigSignoMarca.getNumeroTitulo(),
                    sigSignoMarca.getSerieTitulo(),
                    sigSignoMarca.getOrigenSolicitud(),
                    sigSignoMarca.getNumeroGaceta(),
                    sigSignoMarca.getNumeroPublicacion(),
                    sigSignoMarca.getFechaPublicacion(),
                    sigSignoMarca.getNumeroRegistro(),
                    sigSignoMarca.getSerieRegistro(),
                    sigSignoMarca.getResolucionRegistro(),
                    sigSignoMarca.getFechaRegistro(),
                    sigSignoMarca.getOrdenRenovacion(),
                    sigSignoMarca.getNumeroRenovacion(),
                    sigSignoMarca.getExtensionRenovacion(),
                    sigSignoMarca.getNumeroResolucionRenovacion(),
                    sigSignoMarca.getFechaRenovacion(),
                    sigSignoMarca.getOficina(),
                    sigSignoMarca.getFechaSolicitud(),
                    sigSignoMarca.getFechaIngreso(),
                    sigSignoMarca.getEntregadoUsuario(),
                    sigSignoMarca.getNotoriedadMarca(),
                    sigSignoMarca.getEstado(),
                    sigSignoMarca.getNumero(),
                    sigSignoMarca.getGestion(),
                    sigSignoMarca.getExtensionMarca());
            return signoMarca;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public SigSignoMarca listaSigSignoMarcaXSM(Long sm) {
        SigSignoMarca signoMarca;
        String SQL = "select * from lista_sigsignomarca_sm(?);";
        if (!jdbcTemplate.query(SQL, new SigSignoMarcaMapper(), sm).isEmpty()) {
            signoMarca = (SigSignoMarca) jdbcTemplate.queryForObject(SQL, new SigSignoMarcaMapper(), sm);
            return signoMarca;
        }
        return null;
    }

    @Override
    public SigSignoMarca listaSigSignoMarcaXPublicacion(Long publicacion) {
        SigSignoMarca signoMarca;

        //String SQL = "select * from lista_sigsignomarca_publicacion(?);";
        String SQL = " select * from sigsignomarca "
                + " where numero_publicacion = ? "
                + " and estado = 'AC' "
                + " order by 1 desc "
                + " limit 1;";

        if (!jdbcTemplate.query(SQL, new SigSignoMarcaMapper(), publicacion).isEmpty()) {
            signoMarca = (SigSignoMarca) jdbcTemplate.queryForObject(SQL, new SigSignoMarcaMapper(), publicacion);
            return signoMarca;
        }
        return null;
    }

    @Override
    public SigSignoMarca listaSigSignoMarcaXRenovacion(Long renovacion, String serie) {
        SigSignoMarca signoMarca;
        String SQL = "select * from lista_sigsignomarca_renovacion(?,?);";
        if (!jdbcTemplate.query(SQL, new SigSignoMarcaMapper(), renovacion, serie).isEmpty()) {
            signoMarca = (SigSignoMarca) jdbcTemplate.queryForObject(SQL, new SigSignoMarcaMapper(), renovacion, serie);
            return signoMarca;
        }
        return null;
    }

    @Override
    public SigSignoMarca listaSigSignoMarcaXRegistro(Long registro, String serie, String signo) {
        List<SigSignoMarca> signoMarca;
        //String SQL = "select * from lista_sigsignomarca_registro(?,?,?);";
        //signoMarca = (SigSignoMarca) jdbcTemplate.queryForObject(SQL, new SigSignoMarcaMapper(), registro, serie, signo);
        String SQL = " select * from sigsignomarca where numero_registro=? and serie_registro=? "
                + " and estado='AC' order by 1 desc limit 1 ";
        if (!jdbcTemplate.query(SQL, new SigSignoMarcaMapper(), registro, serie).isEmpty()) {

            signoMarca = jdbcTemplate.query(SQL, new SigSignoMarcaMapper(), registro, serie);
            if (!signoMarca.isEmpty()) {
                return signoMarca.get(0);
            }
        }
        return null;
    }

    
     @Override
    public SigSignoMarca listaSigSignoMarcaXRegistroyClaseNiza(Long registro, String serie, String signo,int clase) {
        List<SigSignoMarca> signoMarca;
        String SQL = " select * from sigsignomarca where numero_registro=? and serie_registro=? and estado='AC' and idsignomarca in (select idsignomarca from sigsignoclaseniza where numero_clase_niza=?) ";
        if (!jdbcTemplate.query(SQL, new SigSignoMarcaMapper(), registro, serie,clase).isEmpty()) {

            signoMarca = jdbcTemplate.query(SQL, new SigSignoMarcaMapper(), registro, serie,clase);
            if (!signoMarca.isEmpty()) {
                return signoMarca.get(0);
            }
        }
        return null;
    }

    @Override
    public SigSignoMarca buscar_SigSignoMarca_sm_publicacion_registro_or(Long sm, Long publicacion, Long registro, String serie, String signo) {
        String[] sigTexto = signo.split(" ");
        String signoBuscar = sigTexto[0];
        SigSignoMarca signoMarca;
        if ((registro != null && registro != 0) && (serie != null && !serie.equals(""))) {
            signoMarca = listaSigSignoMarcaXRegistro(registro, serie, signoBuscar);
            return signoMarca;
        } else if (sm != 0) {
            signoMarca = listaSigSignoMarcaXSM(sm);
            return signoMarca;
        } else if (publicacion != null && publicacion != 0) {
            signoMarca = listaSigSignoMarcaXPublicacion(publicacion);
            return signoMarca;
        }
        return null;
    }

    @Override
    public SigSignoMarca crudSigSignoMarca(SigSignoMarca sigSignoMarca, int parametro) throws Exception {
        try {
            String SQL = "select * from crud_sigsignomarca(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            List<SigSignoMarca> listaSigSignoMarca = jdbcTemplate.query(SQL,
                    new SigSignoMarcaMapper(),
                    sigSignoMarca.getIdSignoMarca(),
                    sigSignoMarca.getIdLogTrans(),
                    sigSignoMarca.getNumeroFormulario(),
                    sigSignoMarca.getSm(),
                    sigSignoMarca.getSigno(),
                    sigSignoMarca.getTipoGenero(),
                    sigSignoMarca.getDescripcionSigno(),
                    sigSignoMarca.getDescripcionLogotipo(),
                    sigSignoMarca.getUbicacion(),
                    sigSignoMarca.getEstadoMarca(),
                    sigSignoMarca.getNumeroRecibo(),
                    sigSignoMarca.getSerie(),
                    sigSignoMarca.getNumeroTitulo(),
                    sigSignoMarca.getSerieTitulo(),
                    sigSignoMarca.getOrigenSolicitud(),
                    sigSignoMarca.getNumeroGaceta(),
                    sigSignoMarca.getNumeroPublicacion(),
                    sigSignoMarca.getFechaPublicacion(),
                    sigSignoMarca.getNumeroRegistro(),
                    sigSignoMarca.getSerieRegistro(),
                    sigSignoMarca.getResolucionRegistro(),
                    sigSignoMarca.getFechaRegistro(),
                    sigSignoMarca.getOrdenRenovacion(),
                    sigSignoMarca.getNumeroRenovacion(),
                    sigSignoMarca.getExtensionRenovacion(),
                    sigSignoMarca.getNumeroResolucionRenovacion(),
                    sigSignoMarca.getFechaRenovacion(),
                    sigSignoMarca.getOficina(),
                    sigSignoMarca.getFechaSolicitud(),
                    sigSignoMarca.getFechaIngreso(),
                    sigSignoMarca.getEntregadoUsuario(),
                    sigSignoMarca.getNotoriedadMarca(),
                    sigSignoMarca.getEstado(),
                    parametro
            );
            if (!listaSigSignoMarca.isEmpty()) {
                return listaSigSignoMarca.get(0);
            }
            return new SigSignoMarca();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public SigSignoMarca crudSigSignoMarcaExternoPI100(SigSignoMarca sigSignoMarca, FormularioPI100 formularioPI100, int parametro) throws Exception {
        try {
            String SQL = "select * from crud_sigsignomarca(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            List<SigSignoMarca> listaSigSignoMarca = jdbcTemplate.query(SQL,
                    new SigSignoMarcaMapper(),
                    sigSignoMarca.getIdSignoMarca(),
                    sigSignoMarca.getIdLogTrans(),
                    formularioPI100.getFormularios().getNumeroFormulario(), //sigSignoMarca.getNumeroFormulario(),
                    sigSignoMarca.getSm(),
                    formularioPI100.getSignoMarca().getMarca(), //sigSignoMarca.getSigno(),
                    formularioPI100.getSignoMarca().getTipoGenero(), //sigSignoMarca.getTipoGenero(),
                    formularioPI100.getSignoMarca().getDescripcionLogo(), //sigSignoMarca.getDescripcionSigno(),
                    sigSignoMarca.getDescripcionLogotipo(),
                    sigSignoMarca.getUbicacion(),
                    sigSignoMarca.getEstadoMarca(),
                    sigSignoMarca.getNumeroRecibo(),
                    sigSignoMarca.getSerie(),
                    sigSignoMarca.getNumeroTitulo(),
                    sigSignoMarca.getSerieTitulo(),
                    sigSignoMarca.getOrigenSolicitud(),
                    sigSignoMarca.getNumeroGaceta(),
                    sigSignoMarca.getNumeroPublicacion(),
                    sigSignoMarca.getFechaPublicacion(),
                    sigSignoMarca.getNumeroRegistro(),
                    sigSignoMarca.getSerieRegistro(),
                    sigSignoMarca.getResolucionRegistro(),
                    sigSignoMarca.getFechaRegistro(),
                    sigSignoMarca.getOrdenRenovacion(),
                    sigSignoMarca.getNumeroRenovacion(),
                    sigSignoMarca.getExtensionRenovacion(),
                    sigSignoMarca.getNumeroResolucionRenovacion(),
                    sigSignoMarca.getFechaRenovacion(),
                    sigSignoMarca.getOficina(),
                    sigSignoMarca.getFechaSolicitud(),
                    sigSignoMarca.getFechaIngreso(),
                    sigSignoMarca.getEntregadoUsuario(),
                    sigSignoMarca.getNotoriedadMarca(),
                    sigSignoMarca.getEstado(),
                    parametro
            );
            if (!listaSigSignoMarca.isEmpty()) {
                return listaSigSignoMarca.get(0);
            }
            return new SigSignoMarca();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public SigSignoMarca crudSigSignoMarcaExternoPI101(SigSignoMarca sigSignoMarca, FormularioPI101 formularioPI101, int parametro) throws Exception {
        try {
            String SQL = "select * from crud_sigsignomarca(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            List<SigSignoMarca> listaSigSignoMarca = jdbcTemplate.query(SQL,
                    new SigSignoMarcaMapper(),
                    sigSignoMarca.getIdSignoMarca(),
                    sigSignoMarca.getIdLogTrans(),
                    formularioPI101.getFormularios().getNumeroFormulario(), //sigSignoMarca.getNumeroFormulario(),
                    sigSignoMarca.getSm(),
                    formularioPI101.getSignoMarca().getMarca(), //sigSignoMarca.getSigno(),
                    formularioPI101.getSignoMarca().getTipoGenero(), //sigSignoMarca.getTipoGenero(),
                    formularioPI101.getSignoMarca().getDescripcionLogo(), //sigSignoMarca.getDescripcionSigno(),
                    sigSignoMarca.getDescripcionLogotipo(),
                    sigSignoMarca.getUbicacion(),
                    sigSignoMarca.getEstadoMarca(),
                    sigSignoMarca.getNumeroRecibo(),
                    sigSignoMarca.getSerie(),
                    sigSignoMarca.getNumeroTitulo(),
                    sigSignoMarca.getSerieTitulo(),
                    sigSignoMarca.getOrigenSolicitud(),
                    sigSignoMarca.getNumeroGaceta(),
                    sigSignoMarca.getNumeroPublicacion(),
                    sigSignoMarca.getFechaPublicacion(),
                    sigSignoMarca.getNumeroRegistro(),
                    sigSignoMarca.getSerieRegistro(),
                    sigSignoMarca.getResolucionRegistro(),
                    sigSignoMarca.getFechaRegistro(),
                    sigSignoMarca.getOrdenRenovacion(),
                    sigSignoMarca.getNumeroRenovacion(),
                    sigSignoMarca.getExtensionRenovacion(),
                    sigSignoMarca.getNumeroResolucionRenovacion(),
                    sigSignoMarca.getFechaRenovacion(),
                    sigSignoMarca.getOficina(),
                    sigSignoMarca.getFechaSolicitud(),
                    sigSignoMarca.getFechaIngreso(),
                    sigSignoMarca.getEntregadoUsuario(),
                    sigSignoMarca.getNotoriedadMarca(),
                    sigSignoMarca.getEstado(),
                    parametro
            );
            if (!listaSigSignoMarca.isEmpty()) {
                return listaSigSignoMarca.get(0);
            }
            return new SigSignoMarca();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public SigSignoMarca crudSigSignoMarcaExternoPI102(SigSignoMarca sigSignoMarca, FormularioPI102 formularioPI102, int parametro) throws Exception {
        try {
            String SQL = "select * from crud_sigsignomarca(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            List<SigSignoMarca> listaSigSignoMarca = jdbcTemplate.query(SQL,
                    new SigSignoMarcaMapper(),
                    sigSignoMarca.getIdSignoMarca(),
                    sigSignoMarca.getIdLogTrans(),
                    formularioPI102.getFormularios().getNumeroFormulario(), //sigSignoMarca.getNumeroFormulario(),
                    sigSignoMarca.getSm(),
                    formularioPI102.getSignoMarca().getMarca(), //sigSignoMarca.getSigno(),
                    formularioPI102.getSignoMarca().getTipoGenero(), //sigSignoMarca.getTipoGenero(),
                    formularioPI102.getSignoMarca().getDescripcionLogo(), //sigSignoMarca.getDescripcionSigno(),
                    sigSignoMarca.getDescripcionLogotipo(),
                    sigSignoMarca.getUbicacion(),
                    sigSignoMarca.getEstadoMarca(),
                    sigSignoMarca.getNumeroRecibo(),
                    sigSignoMarca.getSerie(),
                    sigSignoMarca.getNumeroTitulo(),
                    sigSignoMarca.getSerieTitulo(),
                    sigSignoMarca.getOrigenSolicitud(),
                    sigSignoMarca.getNumeroGaceta(),
                    sigSignoMarca.getNumeroPublicacion(),
                    sigSignoMarca.getFechaPublicacion(),
                    sigSignoMarca.getNumeroRegistro(),
                    sigSignoMarca.getSerieRegistro(),
                    sigSignoMarca.getResolucionRegistro(),
                    sigSignoMarca.getFechaRegistro(),
                    sigSignoMarca.getOrdenRenovacion(),
                    sigSignoMarca.getNumeroRenovacion(),
                    sigSignoMarca.getExtensionRenovacion(),
                    sigSignoMarca.getNumeroResolucionRenovacion(),
                    sigSignoMarca.getFechaRenovacion(),
                    sigSignoMarca.getOficina(),
                    sigSignoMarca.getFechaSolicitud(),
                    sigSignoMarca.getFechaIngreso(),
                    sigSignoMarca.getEntregadoUsuario(),
                    sigSignoMarca.getNotoriedadMarca(),
                    sigSignoMarca.getEstado(),
                    parametro
            );
            if (!listaSigSignoMarca.isEmpty()) {
                return listaSigSignoMarca.get(0);
            }
            return new SigSignoMarca();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public SigSignoMarca obtenerRegistroSigSignoMarcaXNumeroFormulario(String numeroFormulario) throws Exception {
        try {
            SigSignoMarca sigSignoMarca;
            String SQL = "select * from sigsignomarca where numero_formulario = ? and estado  = 'AC' limit 1";
            if (!jdbcTemplate.query(SQL, new SigSignoMarcaMapper(), numeroFormulario).isEmpty()) {
                sigSignoMarca = (SigSignoMarca) jdbcTemplate.queryForObject(SQL, new SigSignoMarcaMapper(), numeroFormulario);
                return sigSignoMarca;
            }
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public SigSignoMarca lista_SigSignoMarca_SigRegistro(Long sm) {
        String SQL = "SELECT  ssm.* "
                + "FROM    sigsignomarca ssm "
                + "WHERE ssm.sm = " + sm + " and NOT EXISTS "
                + "("
                + "SELECT  r.idsignomarca "
                + "FROM    sigregistro r "
                + "WHERE   r.idsignomarca = ssm.idsignomarca and estado ='AC'"
                + ")"
                + " and (ssm.estado_marca='P' or ssm.estado_marca='AR') and (ssm.numero_publicacion <> 0 and ssm.numero_publicacion is not null)";
        if (!jdbcTemplate.query(SQL, new SigSignoMarcaMapper()).isEmpty()) {
            SigSignoMarca signoMarca = (SigSignoMarca) jdbcTemplate.queryForObject(SQL, new SigSignoMarcaMapper());
            return signoMarca;
        }
        return null;
    }

    @Override
    public SigSignoMarca buscarSignoMarca_NumeroFormulario(String numeroFormulario) throws Exception {
        numeroFormulario = (numeroFormulario).trim();
        try {
            String SQL = "select * from sigsignomarca where numero_formulario=? and estado='AC' ";
            if (!jdbcTemplate.query(SQL, new SigSignoMarcaMapper(), numeroFormulario).isEmpty()) {
                return (SigSignoMarca) jdbcTemplate.queryForObject(SQL, new SigSignoMarcaMapper(), numeroFormulario);
            } else {
                return new SigSignoMarca();
            }
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public List<SigSignoMarca> listar_SigSignoMarca_registro(Long registro, String serie) {
        try {
            String SQL = "";
            SQL = "select * from sigsignomarca where numero_registro = " + registro + " and serie_registro = '" + serie + "'";
            List<SigSignoMarca> lista = jdbcTemplate.query(SQL, new SigSignoMarcaMapper());
            if (!lista.isEmpty()) {
                return lista;
            }
            return new ArrayList<SigSignoMarca>();
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<SigSignoMarca>();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<HistorialRenovacionModificacionPojo> listaHistorialRenovacionesModificacion(SigSignoMarca signoMarca) {
        try {
            List<HistorialRenovacionModificacionPojo> lista = new ArrayList<>();
            String SQLMOD = "";
            SQLMOD = "select * from modmodificacion where numero_registro = " + signoMarca.getNumeroRegistro() + " and serie_registro = '" + signoMarca.getSerieRegistro() + "'";
            List<ModModificacion> listaMod = jdbcTemplate.query(SQLMOD, new ModModificacionMapper());
            String SQLREN = "";
            SQLREN = "select * from renrenovacion where numero_registro = " + signoMarca.getNumeroRegistro() + " and serie_registro = '" + signoMarca.getSerieRegistro() + "' and idclase_niza_renovacion='" + 8 + "'";
            List<RenRenovacion> listaRenovacion = jdbcTemplate.query(SQLREN, new RenRenovacionMapper());
            String SQLSOLREN = "";
            SQLREN = "select * from rensolicitudrenovacion where numero_registro_registrado = " + signoMarca.getNumeroRegistro() + " and serie_registro_registrado = '" + signoMarca.getSerieRegistro() + "'";
            List<RenSolicitudRenovacion> listaSolicitudRenovacion = jdbcTemplate.query(SQLREN, new RenSolicitudRenovacionMapper());
            if (listaSolicitudRenovacion != null) {
                for (RenSolicitudRenovacion listarensol : listaSolicitudRenovacion) {
                    HistorialRenovacionModificacionPojo historialRenovacionModificacionPojo = new HistorialRenovacionModificacionPojo();
                    historialRenovacionModificacionPojo.setRenSolicitudRenovacion(listarensol);
                    lista.add(historialRenovacionModificacionPojo);

                }
            }
            if (listaRenovacion != null) {
                for (RenRenovacion listaren : listaRenovacion) {
                    HistorialRenovacionModificacionPojo historialRenovacionModificacionPojo = new HistorialRenovacionModificacionPojo();
                    historialRenovacionModificacionPojo.setRenRenovacion(listaren);
                    lista.add(historialRenovacionModificacionPojo);

                }

            }
            if (listaMod != null) {
                for (ModModificacion listamod : listaMod) {
                    HistorialRenovacionModificacionPojo historialRenovacionModificacionPojo = new HistorialRenovacionModificacionPojo();
                    historialRenovacionModificacionPojo.setModModificacion(listamod);
                    lista.add(historialRenovacionModificacionPojo);

                }

            }

            return lista;
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<HistorialRenovacionModificacionPojo>();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public SigSignoMarca listaSigSignoMarcaXRegistroyClaseNiza(Long registro, String serie, String signo, Long idClase) {
        List<SigSignoMarca> signoMarca;
        String SQL = " select * from sigsignomarca where numero_registro=? and serie_registro=? and estado='AC' and idsignomarca in (select idsignomarca from sigsignoclaseniza where idclaseniza=?) ";
        if (!jdbcTemplate.query(SQL, new SigSignoMarcaMapper(), registro, serie, idClase).isEmpty()) {

            signoMarca = jdbcTemplate.query(SQL, new SigSignoMarcaMapper(), registro, serie, idClase);
            if (!signoMarca.isEmpty()) {
                return signoMarca.get(0);
            }
        }
        return null;
    }

}
