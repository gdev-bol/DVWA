/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.LogTrans;
import snp.gob.bo.gimodel.entidad.Seguimiento;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.enums.EnumEstado;
import snp.gob.bo.gimodel.enums.EnumPrefijoTablas;
import snp.gob.bo.gimodel.mapper.SeguimientoMapper;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.LogTransService;
import snp.gob.bo.gimodel.servicio.SeguimientoService;
import snp.gob.bo.gimodel.servicio.UsuarioService;

/**
 * Entidad responsable de la Tabla Seguimiento
 *
 * @author Eddy Valero
 * @see SeguimientoMapper
 * @version 1.0, 08/09/2016
 */
@Service("seguimientoService")
public class SeguimientoServiceImpl implements SeguimientoService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private ComunService comunServiceImpl;

    @Autowired
    private LogTransService logTransService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
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
    public Seguimiento guardarRegistroSigSeguimiento(Seguimiento seguimientoNuevo) throws Exception {
        try {
            Seguimiento seguimientoNuevoResultado = new Seguimiento();
            String SQL = "select * from crud_seguimiento(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?);";
//            System.out.println("datos couslkta::"
//                    + seguimientoNuevo.getIdSeguimiento() + ","
//                    + seguimientoNuevo.getId() + ","
//                    + seguimientoNuevo.getIdUsuario() + ","
//                    + seguimientoNuevo.getIdLogTrans() + ","
//                    + seguimientoNuevo.getSm() + ","
//                    + seguimientoNuevo.getNumeroPublicacion() + ","
//                    + seguimientoNuevo.getNumeroRegistro() + ","
//                    + seguimientoNuevo.getSerieRegistro() + ","
//                    + seguimientoNuevo.getEtapa() + ","
//                    + seguimientoNuevo.getFechaRecepcion() + ","
//                    + seguimientoNuevo.getFechaFin() + ","
//                    + seguimientoNuevo.getPlazoReal() + ","
//                    + seguimientoNuevo.getTotalTiempo() + ","
//                    + seguimientoNuevo.getObservacion() + ","
//                    + seguimientoNuevo.getEditable() + ","
//                    + seguimientoNuevo.getEstado() + ","
//                    + seguimientoNuevo.getPlazo_limite() + ","
//                    + "SIG" + ","
//                    + 1);

            seguimientoNuevoResultado = jdbcTemplate.queryForObject(SQL,
                    new SeguimientoMapper(),
                    seguimientoNuevo.getIdSeguimiento(),
                    seguimientoNuevo.getId(),
                    seguimientoNuevo.getIdUsuario(),
                    seguimientoNuevo.getIdLogTrans(),
                    seguimientoNuevo.getSm(),
                    seguimientoNuevo.getNumeroPublicacion(),
                    seguimientoNuevo.getNumeroRegistro(),
                    seguimientoNuevo.getSerieRegistro(),
                    seguimientoNuevo.getEtapa(),
                    seguimientoNuevo.getFechaRecepcion(),
                    seguimientoNuevo.getFechaFin(),
                    seguimientoNuevo.getPlazoReal(),
                    seguimientoNuevo.getTotalTiempo(),
                    seguimientoNuevo.getObservacion(),
                    seguimientoNuevo.getEditable(),
                    seguimientoNuevo.getEstado(),
                    seguimientoNuevo.getPlazo_limite(),
                    seguimientoNuevo.getDia_pasivo(),
                    "SIG",
                    1
            );
            return seguimientoNuevoResultado;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Seguimiento listar_SigSeguimiento_etapa_iniciado(Long idSignoMarca, String etapa) throws Exception {
        try {
            Seguimiento seguimientoNuevoResultado = new Seguimiento();
            String SQL = "select * from listar_sigseguimiento_etapa_iniciado(?, ?);";
            seguimientoNuevoResultado = jdbcTemplate.queryForObject(SQL,
                    new SeguimientoMapper(),
                    idSignoMarca,
                    etapa
            );
            return seguimientoNuevoResultado;
        } catch (EmptyResultDataAccessException e) {
            //System.out.println("vacio no existe publicacion para la marca");
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Seguimiento obtenerRegistroSigSeguimientoPorCodigoSM(Long codigoSM, String etapa, String prefijo) throws Exception {
        try {
            Seguimiento seguimientoNuevoResultado = new Seguimiento();
            String SQL = "select * from obtener_seguimiento_smmarca(?, ?, ?);";
            seguimientoNuevoResultado = jdbcTemplate.queryForObject(SQL,
                    new SeguimientoMapper(),
                    codigoSM,
                    etapa,
                    prefijo
            );
            if (seguimientoNuevoResultado.getIdSeguimiento() != null) {
                return seguimientoNuevoResultado;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Seguimiento guardar_modificar_listar_Seguimiento(Seguimiento seguimiento, String prefijo, Integer operacion) {
        try {
            Seguimiento sseguimientoResultado = new Seguimiento();
            String SQL = "select * from crud_seguimiento(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?);";
            sseguimientoResultado = jdbcTemplate.queryForObject(SQL,
                    new SeguimientoMapper(),
                    seguimiento.getIdSeguimiento(),
                    seguimiento.getId(),
                    seguimiento.getIdUsuario(),
                    seguimiento.getIdLogTrans(),
                    seguimiento.getSm(),
                    seguimiento.getNumeroPublicacion(),
                    seguimiento.getNumeroRegistro(),
                    seguimiento.getSerieRegistro(),
                    seguimiento.getEtapa(),
                    seguimiento.getFechaRecepcion(),
                    seguimiento.getFechaFin(),
                    seguimiento.getPlazoReal(),
                    seguimiento.getTotalTiempo(),
                    seguimiento.getObservacion(),
                    seguimiento.getEditable(),
                    seguimiento.getEstado(),
                    seguimiento.getPlazo_limite(),
                    seguimiento.getDia_pasivo(),
                    prefijo,
                    operacion
            );
            return sseguimientoResultado;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Seguimiento> lista_SeguimientoXid(String prefijo, Long id) {
        try {
            String SQL = "select * from lista_seguimiento_tramite(?,?)";
            List<Seguimiento> listaSeguimiento = jdbcTemplate.query(SQL, new SeguimientoMapper(), prefijo, id);
            if (!listaSeguimiento.isEmpty()) {
                return listaSeguimiento;
            }
            return new ArrayList<Seguimiento>();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Seguimiento ultimo_Seguimiento_etapa(String prefijo, Long id, String etapa) {
        try {

            String SQL = "select * from lista_seguimiento_etapa(?,?,?)";
            List<Seguimiento> listaSeguimiento = jdbcTemplate.query(SQL, new SeguimientoMapper(), prefijo, etapa, id);
            if (!listaSeguimiento.isEmpty()) {
                return listaSeguimiento.get(0);
            }
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Seguimiento guardar_modificar_Seguimiento_etapa(Seguimiento seguimiento, String prefijo) {
        Seguimiento seguimientoFinal = new Seguimiento();
        Seguimiento seguimientoNuevo = new Seguimiento();
        int dias = 0;
        Long totalTiempoAux = 0l;
        List<Seguimiento> listaSeguiUltimos = null;
        try {
            Date fechaServidor = comunServiceImpl.obtenerFechaHoraServidor(1l);

            //Inserta POR EJEMPLO  SIG,idsignomarca,DIG
            Seguimiento seguimientoEtapa = ultimo_Seguimiento_etapa(prefijo, seguimiento.getId(), seguimiento.getEtapa());

            List<Usuario> listUsuario = usuarioService.listaUsuarioXidPagina(seguimiento.getIdUsuario());
            // System.out.println("seguimientoEtapa::"+seguimientoEtapa);          
            if (seguimientoEtapa != null) {

                //pREGUNTA SI ES NULO PORQUE SIGNIFICA QUE HIZO SU RECEPCION PERO NO SU FINALIZACION
                if (seguimientoEtapa.getFechaFin() == null) {

                    //  System.out.println("fechaFin.........."+seguimiento.getFechaFin());
                    //  System.out.println("fechaRecep........"+seguimientoEtapa.getFechaRecepcion());
                    //  System.out.println("listUsuario.get(0).getIdregional()......."+listUsuario.get(0).getIdregional());
                    // DEL CODIGO DEA BAJO SACA LOS DIAS LABORALES ENTRE ESAS FECHAS
                    //  System.out.println("seguimientoEtapa.getFechaRecepcion()::"+seguimientoEtapa.getFechaRecepcion());
                    //  System.out.println("seguimiento.getFechaFin()::"+seguimiento.getFechaFin());
                    //  System.out.println("listUsuario.get(0).getIdregional()::"+listUsuario.get(0).getIdregional());
                    dias = this.diasLaboralesSigno(seguimientoEtapa.getFechaRecepcion(), seguimiento.getFechaFin(), listUsuario.get(0).getIdregional());
                    //  System.out.println("dias::"+dias);                  

                    //int dias = diferenciaEnDias(seguimiento.getFechaFin(), seguimientoEtapa.getFechaRecepcion());//HYA UQE ARREGLAR
                    //actualizar finalizar 
                    seguimientoEtapa.setSm((seguimiento.getSm()));

                    seguimientoEtapa.setNumeroPublicacion(seguimiento.getNumeroPublicacion());
                    seguimientoEtapa.setNumeroRegistro(seguimiento.getNumeroRegistro());
                    seguimientoEtapa.setSerieRegistro(seguimiento.getSerieRegistro());
                    seguimientoEtapa.setEtapa(seguimiento.getEtapa());
                    //seguimientoNuevo.setFechaRecepcion(fechaServidor);
                    seguimientoEtapa.setPlazoReal(dias);

                    if (seguimiento.getFechaFin() != null) {
                        //        System.out.println("Entra a que coloca la fecha obtenida");
                        seguimientoEtapa.setFechaFin(seguimiento.getFechaFin());
                    } else {
                        //       System.out.println("Entra a que coloca la misma fecha");
                        seguimientoEtapa.setFechaFin(fechaServidor);
                    }
                    //Agarro de la anterior etapa su tiempo total par aluego sumarlo al plazo real

                    if (prefijo.equals(EnumPrefijoTablas.SIGNO.getCodigo())) {
                        listaSeguiUltimos = this.listaSeguimientoXIdsegEtapaUltMov(seguimiento.getId(), seguimiento.getEtapa());
                    }
                    if (prefijo.equals(EnumPrefijoTablas.MODIFICACION.getCodigo())) {
                        listaSeguiUltimos = this.listaSeguimientoXIdsegEtapaModUltMov(seguimiento.getId(), seguimiento.getEtapa());
                    }
                    if (prefijo.equals(EnumPrefijoTablas.RENOVACION.getCodigo())) {
                        listaSeguiUltimos = this.listaSeguimientoXIdsegEtapaRenUltMov(seguimiento.getId(), seguimiento.getEtapa());
                    }

                    if (listaSeguiUltimos == null) {
                        totalTiempoAux = new Long(dias);
                    } else {
                        totalTiempoAux = conteoPLazoReal(prefijo, seguimiento.getId()) + dias;
                    }

                    //  System.out.println("TiempoEstimado:::"+totalTiempoAux);
                    seguimientoEtapa.setTotalTiempo(totalTiempoAux);
                    seguimientoNuevo.setDia_pasivo(this.cuentaDiasPasivo(seguimientoNuevo.getFechaRecepcion(),
                            prefijo,
                            seguimiento.getId(),
                            listUsuario.get(0).getIdregional()));
                    seguimientoEtapa.setEstado(EnumEstado.ACTIVO.getCodigo());
                    seguimientoEtapa.setIdUsuario(seguimiento.getIdUsuario());
                    seguimientoFinal = guardar_modificar_listar_Seguimiento(seguimientoEtapa, prefijo, 2);
                } //existe fecha fin  
                else {

                    //crea otro registro , si ya existe del mismo tipo
                    seguimientoNuevo.setId(seguimiento.getId());
                    seguimientoNuevo.setIdUsuario((seguimiento.getIdUsuario()));
                    seguimientoNuevo.setIdLogTrans(seguimiento.getIdLogTrans());
                    seguimientoNuevo.setSm((seguimiento.getSm()));
                    seguimientoNuevo.setNumeroPublicacion(seguimiento.getNumeroPublicacion());
                    seguimientoNuevo.setNumeroRegistro(seguimiento.getNumeroRegistro());
                    seguimientoNuevo.setSerieRegistro(seguimiento.getSerieRegistro());
                    seguimientoNuevo.setEtapa(seguimiento.getEtapa());

                    if (seguimiento.getFechaRecepcion() != null) {
                        seguimientoNuevo.setFechaRecepcion(seguimiento.getFechaRecepcion());
                    } else {
                        seguimientoNuevo.setFechaRecepcion(fechaServidor);
                    }
                    seguimientoNuevo.setObservacion(seguimiento.getObservacion());
                    seguimientoNuevo.setEditable(false);
                    seguimientoNuevo.setEstado(EnumEstado.ACTIVO.getCodigo());
                    seguimientoNuevo.setPlazo_limite(seguimiento.getPlazo_limite());
                    seguimientoNuevo.setDia_pasivo(this.cuentaDiasPasivo(seguimientoNuevo.getFechaRecepcion(),
                            prefijo,
                            seguimiento.getId(),
                            listUsuario.get(0).getIdregional()));
                    seguimientoFinal = guardar_modificar_listar_Seguimiento(seguimientoNuevo, prefijo, 1);
                }
            } else {
                //seguimientoNuevo.setIdSeguimiento();
//                if (seguimientoEtapa.getFechaFin() == null) {
//                
//                }                
                seguimientoNuevo.setId(seguimiento.getId());
                seguimientoNuevo.setIdUsuario((seguimiento.getIdUsuario()));
                LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(seguimiento.getIdUsuario(), fechaServidor), 1);
                seguimientoNuevo.setIdLogTrans(logTransGuardado.getIdLogTrans());
                seguimientoNuevo.setSm((seguimiento.getSm()));
                seguimientoNuevo.setNumeroPublicacion(seguimiento.getNumeroPublicacion());
                seguimientoNuevo.setNumeroRegistro(seguimiento.getNumeroRegistro());
                seguimientoNuevo.setSerieRegistro(seguimiento.getSerieRegistro());
                seguimientoNuevo.setEtapa(seguimiento.getEtapa());
                if (seguimiento.getFechaRecepcion() != null) {
                    seguimientoNuevo.setFechaRecepcion(seguimiento.getFechaRecepcion());
                } else {
                    seguimientoNuevo.setFechaRecepcion(fechaServidor);
                }
                seguimientoNuevo.setFechaFin(seguimiento.getFechaFin());

                if (prefijo.equals(EnumPrefijoTablas.SIGNO.getCodigo())) {
                    listaSeguiUltimos = this.listaSeguimientoXIdsegEtapaUltMov(seguimiento.getId(), seguimiento.getEtapa());
                }
                if (prefijo.equals(EnumPrefijoTablas.MODIFICACION.getCodigo())) {
                    listaSeguiUltimos = this.listaSeguimientoXIdsegEtapaModUltMov(seguimiento.getId(), seguimiento.getEtapa());
                }
                if (prefijo.equals(EnumPrefijoTablas.RENOVACION.getCodigo())) {
                    listaSeguiUltimos = this.listaSeguimientoXIdsegEtapaRenUltMov(seguimiento.getId(), seguimiento.getEtapa());
                }

                //conteoPLazoReal
                dias = this.diasLaboralesSigno(seguimientoNuevo.getFechaRecepcion(), seguimientoNuevo.getFechaFin(), listUsuario.get(0).getIdregional());

                if (listaSeguiUltimos == null) {
                    totalTiempoAux = new Long(dias);
                } else {
                    //totalTiempoAux=listaSeguiUltimos.get(0).getTotalTiempo()+dias;                   
                    totalTiempoAux = conteoPLazoReal(prefijo, seguimiento.getId()) + dias;
                }
                seguimientoNuevo.setTotalTiempo(totalTiempoAux);
                seguimientoNuevo.setPlazoReal(dias);
                seguimientoNuevo.setObservacion(seguimiento.getObservacion());
                seguimientoNuevo.setEditable(false);
                seguimientoNuevo.setEstado(EnumEstado.ACTIVO.getCodigo());
                seguimientoNuevo.setPlazo_limite(seguimiento.getPlazo_limite());
                seguimientoNuevo.setDia_pasivo(this.cuentaDiasPasivo(seguimientoNuevo.getFechaRecepcion(),
                        prefijo,
                        seguimiento.getId(),
                        listUsuario.get(0).getIdregional()));
                seguimientoFinal = guardar_modificar_listar_Seguimiento(seguimientoNuevo, prefijo, 1);
            }
        } catch (Exception ex) {
            Logger.getLogger(SeguimientoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return seguimientoFinal;
    }

    public static int diferenciaEnDias(Date fechaMayor, Date fechaMenor) {
        long diferenciaEn_ms = fechaMayor.getTime() - fechaMenor.getTime();
        long dias = diferenciaEn_ms / (1000 * 60 * 60 * 24);
        return (int) dias;
    }

    @Override
    public int diasLaboralesSigno(Date fechaIni, Date fechaFin, Long idRegional) {
        Integer num = 0;
        try {
            String SQL = "select * from obtiene_cuentadiasnolaborales_fechas(?,?,?);";//Modifica
            //Integer numBloque =  jdbcTemplate.queryForObject(SQL, new Object[]{id_usuariosolicitante}, Integer.class);
            num = jdbcTemplate.queryForObject(SQL, new Object[]{fechaIni, fechaFin, idRegional}, Integer.class);
            //  System.out.println("num::"+num);         
            return num;
        } catch (EmptyResultDataAccessException e) {
            //Como no se encontro ningun registro enviar la instancia de la nueva marca.
            return num;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Seguimiento registraSeguimientoSignos(Long id, Long idUsuario, Long idLogTrans,
            Long codigoSM, String etapa, Date fechaRecep, Date fechaFin,
            String obs, String estado, int plazo_limite) throws Exception {
        Seguimiento seguiReturn = new Seguimiento();

        Seguimiento segui = new Seguimiento();
        segui.setId(id);
        segui.setIdUsuario(idUsuario);
        List<Usuario> listUsuario = usuarioService.listaUsuarioXidPagina(idUsuario);
        Date fechaSistema = comunServiceImpl.obtenerFechaHoraServidor(1L);

        segui.setIdLogTrans(idLogTrans);
        segui.setSm(codigoSM);
        segui.setEtapa(etapa);
        segui.setFechaRecepcion(fechaRecep);
        segui.setFechaFin(fechaFin);
        segui.setEditable(Boolean.TRUE);

        if (fechaFin == null || fechaFin.equals("")) {
            segui.setPlazoReal(0);
            segui.setTotalTiempo(0L);
        } else {
            segui.setPlazoReal(this.diasLaboralesSigno(fechaRecep, fechaFin, listUsuario.get(0).getIdregional()));
            Long intObj = new Long(this.diasLaboralesSigno(fechaRecep, fechaFin, listUsuario.get(0).getIdregional()));
            segui.setTotalTiempo(intObj);
        }

        segui.setObservacion(obs);
        segui.setEditable(Boolean.FALSE);
        segui.setEstado(estado);
        segui.setPlazo_limite(plazo_limite);

        seguiReturn = this.guardarRegistroSigSeguimiento(segui);
        return seguiReturn;
    }

    @Override
    public List<Seguimiento> listaSeguimientoXSM(Long sm, String etapa) throws Exception {
        try {
            String SQL = "select * from sigseguimiento where sm = ? and  etapa=?";
            List<Seguimiento> seguimientos = jdbcTemplate.query(SQL,
                    new SeguimientoMapper(),
                    sm,
                    etapa
            );

            //List<Seguimiento> listaSeguimiento = jdbcTemplate.query(SQL, new SeguimientoMapper(), prefijo, etapa, id);
            if (!seguimientos.isEmpty()) {
                return seguimientos;
            }
            return null;
        } catch (Exception e) {
            throw e;
        }
        /*
         List<Seguimiento> listaSeguimiento = jdbcTemplate.query(SQL, new SeguimientoMapper(), prefijo, id);
         if (!listaSeguimiento.isEmpty()) {
         return listaSeguimiento;
         }
      
         */
    }

    @Override
    public List<Seguimiento> listaSeguimientoXIdsegEtapaUltMov(Long idSignoMarca, String etapa) throws Exception {
        String SQL = "select * from obtener_seguimientoidsignomarca_etapa(?,?);";
        List<Seguimiento> seguimientos = jdbcTemplate.query(SQL,
                new SeguimientoMapper(),
                idSignoMarca,
                etapa
        );
        if (!seguimientos.isEmpty()) {
            return seguimientos;
        }
        return null;
    }

    @Override
    public List<Seguimiento> listaSeguimientoXIdsegEtapaModUltMov(Long idModificacion, String etapa) throws Exception {
        String SQL = "select * from obtener_seguimientoidmodificacion_etapa(?,?);";
        List<Seguimiento> seguimientos = jdbcTemplate.query(SQL,
                new SeguimientoMapper(),
                idModificacion,
                etapa
        );
        if (!seguimientos.isEmpty()) {
            return seguimientos;
        }
        return null;
    }

    @Override
    public List<Seguimiento> listaSeguimientoXIdsegEtapaRenUltMov(Long idRenovacion, String etapa) throws Exception {
        String SQL = "select * from obtener_seguimientoidrenovacion_etapa(?,?);";
        List<Seguimiento> seguimientos = jdbcTemplate.query(SQL,
                new SeguimientoMapper(),
                idRenovacion,
                etapa
        );
        if (!seguimientos.isEmpty()) {
            return seguimientos;
        }
        return null;
    }

    @Override
    public List<Seguimiento> listaSeguimientoUltimoXIdsignoEtapaUltMov(Long idSignoMarca, String etapa, Long idSeguimiento) throws Exception {
        String SQL = "select * from obtener_ultimoseguimientosignomarca_etapa(?,?,?);";
        List<Seguimiento> seguimientos = jdbcTemplate.query(SQL,
                new SeguimientoMapper(),
                idSignoMarca,
                etapa,
                idSeguimiento
        );
        if (!seguimientos.isEmpty()) {
            return seguimientos;
        }
        return null;
    }

    @Override
    public List<Seguimiento> verificaseguimientosm(Long sm) throws Exception {

        String SQL = "select * from sigseguimiento where sm=?;";
        List<Seguimiento> seguimientos = jdbcTemplate.query(SQL, new SeguimientoMapper(), sm);
        if (!seguimientos.isEmpty()) {
            return seguimientos;
        }
        return null;
    }

    @Override
    public List<Seguimiento> verificaseguimientopub(Long nropubli) throws Exception {
        String SQL = "select * from sigseguimiento where numero_publicacion=?;";
        List<Seguimiento> seguimientos = jdbcTemplate.query(SQL, new SeguimientoMapper(), nropubli);
        if (!seguimientos.isEmpty()) {
            return seguimientos;
        }
        return null;
    }

    @Override
    public List<Seguimiento> verificaseguimientoreg(Long nroreg, String regis) throws Exception {
        String SQL = "select * from sigseguimiento where numero_registro=? and serie_registro=?;";
        List<Seguimiento> seguimientos = jdbcTemplate.query(SQL, new SeguimientoMapper(), nroreg, regis);
        if (!seguimientos.isEmpty()) {
            return seguimientos;
        }
        return null;
    }

    /**/
    @Override
    public List<Seguimiento> listaSeguiUltimoXIdsignoEtapaUltMov(Long idSignoMarca, String etapa) throws Exception {
        String SQL = "select * from obtener_ultimoseguisignomarca_etapa(?,?);";
        List<Seguimiento> seguimientos = jdbcTemplate.query(SQL,
                new SeguimientoMapper(),
                idSignoMarca,
                etapa
        );
        if (!seguimientos.isEmpty()) {
            return seguimientos;
        }
        return null;
    }

    @Override
    public List<Seguimiento> listaPenultSegui_idSigno(Long idSignoMarca) throws Exception {
        String SQL = "select * from obtener_anteriorseguis_etapa(?);";
        List<Seguimiento> seguimientos = jdbcTemplate.query(SQL,
                new SeguimientoMapper(),
                idSignoMarca
        );
        if (!seguimientos.isEmpty()) {
            return seguimientos;
        }
        return null;
    }

    @Override
    public Seguimiento lista_seguimiento_ultimo(String prefijo, Long id) {
        try {

            String SQL = "select * from lista_seguimiento_finalizado(?,?)";
            List<Seguimiento> listaSeguimiento = jdbcTemplate.query(SQL, new SeguimientoMapper(), prefijo, id);
            if (!listaSeguimiento.isEmpty()) {
                return listaSeguimiento.get(0);
            }
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    //public int diasLaboralesSigno(Date fechaIni, Date fechaFin, Long idRegional) {
    @Override
    public int cuentaDiasPasivo(Date fechaFin, String prefijo, Long id, Long idRegional) throws Exception {
        Integer dias;
        List<Seguimiento> lista;

        if (!this.lista_SeguimientoXid(prefijo, id).isEmpty()) {
            lista = this.lista_SeguimientoXid(prefijo, id);
            if (lista.get(lista.size() - 1).getFechaFin() != null) {

                dias = this.diasLaboralesSigno(lista.get(lista.size() - 1).getFechaFin(), fechaFin, idRegional);
                return dias;
            } else {

                return 0;
            }

        } else {
            return 0;
        }

    }

    @Override
    public Long conteoPLazoReal(String prefijo, Long id) {
        Long total = 0L;
        try {
            String SQL = "select * from conteo_plazo_real_id(?,?);";
            total = jdbcTemplate.queryForObject(SQL, Long.class, prefijo, id);
            return total;
        } catch (DataAccessException e) {
            return total;
        }
    }

    @Override
    public Integer conteoPlazoLimite(String prefijo, Long id) {
        Integer total = 0;
        try {
            String SQL = "select * from conteo_plazo_limite_id(?,?);";
            total = jdbcTemplate.queryForObject(SQL, Integer.class, prefijo, id);
            return total;
        } catch (DataAccessException e) {
            return total;
        }
    }

    @Override
    public Long conteoTotalTiempo(List<Seguimiento> lstSeguimiento, String prefijo, Long idregional) {
        Long totalTiempo = 0l;
        Seguimiento seguimiento;
        if (!lstSeguimiento.isEmpty()) {

            if (lstSeguimiento.size() > 1) {
                seguimiento = lstSeguimiento.get(lstSeguimiento.size() - 1);
                totalTiempo = lstSeguimiento.get(lstSeguimiento.size() - 1).getTotalTiempo();
                if (totalTiempo == null || totalTiempo == 0) {
                    for (Seguimiento item : lstSeguimiento) {
                        totalTiempo = totalTiempo + item.getPlazoReal();
                    }
                    seguimiento.setTotalTiempo(totalTiempo);
                    guardar_modificar_listar_Seguimiento(seguimiento, prefijo, 2);
                }
            } else {
                seguimiento = lstSeguimiento.get(0);
                totalTiempo = new Long(this.diasLaboralesSigno(seguimiento.getFechaRecepcion(), seguimiento.getFechaFin(), 1l));
                seguimiento.setTotalTiempo(totalTiempo);
                guardar_modificar_listar_Seguimiento(seguimiento, prefijo, 2);
            }
        }
        return totalTiempo;
    }

    @Override
    public Integer conteoTiempoPasivo(String prefijo, Long id) {
        Integer total = 0;
        try {
            String SQL = "select * from conteo_dia_pasivo_id(?,?);";
            total = jdbcTemplate.queryForObject(SQL, Integer.class, prefijo, id);
            return total;
        } catch (DataAccessException e) {
            return total;
        }
    }

    @Override
    public List<Seguimiento> listaSeguiUltimoXIdrenoEtapaUltMov(Long idsolicitudrenovacion, String etapa) throws Exception {
        String SQL = "select * from obtener_ultimoseguirenova_etapa(?,?);";
        List<Seguimiento> seguimientos = jdbcTemplate.query(SQL,
                new SeguimientoMapper(),
                idsolicitudrenovacion,
                etapa
        );
        if (!seguimientos.isEmpty()) {
            return seguimientos;
        }
        return null;
    }

    @Override
    public List<Seguimiento> listaSeguiUltimoXIdmodiEtapaUltMov(Long idmodificacion, String etapa) throws Exception {
        String SQL = "select * from obtener_ultimoseguimodi_etapa(?,?);";
        List<Seguimiento> seguimientos = jdbcTemplate.query(SQL,
                new SeguimientoMapper(),
                idmodificacion,
                etapa
        );
        if (!seguimientos.isEmpty()) {
            return seguimientos;
        }
        return null;
    }
    
    @Override
    public List<Seguimiento> listaSeguimientoXIdSignomarca(Long idSignoMarca) throws Exception {
        try {
            String SQL = "select * from sigseguimiento where idsignomarca=? and estado ='AC' order by fecha_recepcion desc";
            List<Seguimiento> seguimientos = jdbcTemplate.query(SQL,
                    new SeguimientoMapper(),
                    idSignoMarca
            );

            //List<Seguimiento> listaSeguimiento = jdbcTemplate.query(SQL, new SeguimientoMapper(), prefijo, etapa, id);
            if (!seguimientos.isEmpty()) {
                return seguimientos;
            }
            return null;
        } catch (Exception e) {
            throw e;
        }
        /*
         List<Seguimiento> listaSeguimiento = jdbcTemplate.query(SQL, new SeguimientoMapper(), prefijo, id);
         if (!listaSeguimiento.isEmpty()) {
         return listaSeguimiento;
         }
      
         */
    }
    
    @Override
    public Integer obtieneCantidadEtapa(String prefijo,String etapa, Long id) {
        
      Integer total = 0;
        try {
            String SQL = "select * from obtener_cantidad_etapas(?,?,?);";
            total = jdbcTemplate.queryForObject(SQL, Integer.class, prefijo, etapa, id);
            return total;
        } catch (DataAccessException e) {
            return total;
        }
    
    }
    
    
}
