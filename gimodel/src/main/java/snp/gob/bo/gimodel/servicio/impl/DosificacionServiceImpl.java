/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.Dosificacion;
import snp.gob.bo.gimodel.mapper.DosificacionMapper;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.DosificacionService;
import snp.gob.bo.gimodel.servicio.ModTitularLicenciatarioNuevoService;
import snp.gob.bo.gimodel.servicio.SigSolicitanteApoderadoService;

/**
 *
 * @author ChanoRojas
 */
@Service("dosificacionService")
public class DosificacionServiceImpl implements DosificacionService {

    @Autowired
    private DominioService dominioService;
    @Autowired
    private SigSolicitanteApoderadoService sigSolicitanteApoderadoService;
    @Autowired
    private ModTitularLicenciatarioNuevoService modTitularLicenciatarioNuevoService;

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
    public Dosificacion crudDosificacion(Dosificacion dosificacion, int parametro) throws Exception {
        try {
            Dosificacion dos = new Dosificacion();
            String SQL = "select * from crud_dosificacion("
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?);";
            dos =  (Dosificacion) jdbcTemplate.queryForObject(SQL, new DosificacionMapper(),
            dosificacion.getIdDosificacion(),
            dosificacion.getIdLogTrans(),
            dosificacion.getValorInicio(),
            dosificacion.getValorFinal(),
            dosificacion.getSiguiente(),
            dosificacion.getGestion(),
            dosificacion.getFechaDosificacion(),
            dosificacion.getEstado(),
            parametro
            );
            return dos;
        } catch ( Exception e) {
            throw e;
        }
    }
  

    @Override
    public Dosificacion encuentraDosificacionPorIdDosificacion(Long idDosificacion) {
        String SQL = "select * from dosificacion where iddosificacion=?  and  estado='AC' for update  ";
        if (!jdbcTemplate.query(SQL, new DosificacionMapper(),idDosificacion).isEmpty()) {
            return jdbcTemplate.query(SQL, new DosificacionMapper(), idDosificacion).get(0);
        }
        return new Dosificacion();
    } 
    
    

//    @Override
//    public Integer actualizaSeguienteDosificacion(TipoDosificacion tipoDosificacion, Regional regional) throws Exception {
//        int numero = 0;
//        List<Dosificacion> list = hibernateTemplate.find(""
//                + "select ch "
//                + "from Dosificacion ch "
//                + "where ch.tipoDosificacion.codigo='" + tipoDosificacion.getCodigo() + "'"
//                + "and ch.vigente=true "
//                + "and ch.regional.idRegional='" + regional.getIdRegional() + "' "
//                + "order by ch.siguiente");
//        if (!list.isEmpty()) {
//            numero = list.get(0).getSiguiente();
//            list.get(0).setSiguiente(numero + 1);
//            mergeDosificacion(list.get(0));
//            System.out.println("el numero actualizado es" + list.get(0).getSiguiente());
//        }
//
//        return numero;
//    }
//
//    @Override
//    public List<Dosificacion> listaDosificacionPorRegional(Regional regional) {
//        List<Dosificacion> list = hibernateTemplate.find(""
//                + "select ch "
//                + "from Dosificacion ch "
//                + "where ch.regional='" + regional.getIdRegional() + "' "
//                + "and ch.vigente=true");
//        if (!list.isEmpty()) {
//            return list;
//        } else {
//            return Collections.EMPTY_LIST;
//
//        }
//
//    }
//
//    @Override
//    public Integer valorRestoDeposito(Regional regional, String serie) {
//        int resto = 0;
//        List<Dosificacion> list = hibernateTemplate.find(" "
//                + "select r "
//                + "from Dosificacion r "
//                + "where r.regional.idRegional= '" + regional.getIdRegional() + "' "
//                + "and r.serie= '" + serie + "'");
//        if (!list.isEmpty()) {
//            resto = (list.get(0).getValorFinal() - list.get(0).getSiguiente());
//        }
//        return resto;
//    }
//
//    @Override
//    public List<TipoCiudadNotificacion> listaTipoCiudadDeNotificacionFiltrado() {
//        List<TipoCiudadNotificacion> listaAux = new ArrayList<TipoCiudadNotificacion>();
//        List<TipoCiudadNotificacion> listCiudad = clasificadorService.listadoCiudadClasificadorNotificacion();
//        for (TipoCiudadNotificacion tipoCiudadNotificacion : listCiudad) {
//            if (!verificaRegistroCompletoDeDosificacion(tipoCiudadNotificacion)) {
//                listaAux.add(tipoCiudadNotificacion);
//            }
//        }
//        return listaAux;
//    }
//
//    public Boolean verificaRegistroCompletoDeDosificacion(TipoCiudadNotificacion tipoCiudadNotificacion) {
//        Boolean estaRegistrado;
//        Regional regional = regionalService.obtieneRegionalPorCiudadNotificacion(tipoCiudadNotificacion.getCodigo());
//        List<Dosificacion> list = hibernateTemplate.find(""
//                + "select ch "
//                + "from Dosificacion ch where ch.regional.idRegional='" + regional.getIdRegional() + "' and ch.vigente=true");
//        return !list.isEmpty() && list.size() == 4;
//    }
//
//    @Override
//    public Boolean[] validaCamposDosificacion(Dosificacion dosificacion, String tipoDosificacion, String regional) {
//        Boolean sw = true;
//        Boolean tipoDosificacionValida = false;
//        Boolean tipoRegionalValida = false;
//        Boolean rangoInicioValida = false;
//        Boolean rangoFinValida = false;
//
//        if (tipoDosificacion == null || tipoDosificacion.equals("")) {
//            sw = false;
//            tipoDosificacionValida = true;
//        }
//
//        if (regional == null || regional.equals("")) {
//            sw = false;
//            tipoRegionalValida = true;
//        }
//
//        if (dosificacion.getValorInicial() == null) {
//            sw = false;
//            rangoInicioValida = true;
//        }
//
//        if (dosificacion.getValorFinal() == null) {
//            sw = false;
//            rangoFinValida = true;
//        }
//
//        Boolean[] listaError = {sw, tipoDosificacionValida, tipoRegionalValida, rangoInicioValida, rangoFinValida};
//        return listaError;
//    }
//
//    @Override
//    public int validaCampoDosificacionColor(Dosificacion dosificacion) {
//        if (dosificacion.getValorInicial() == null) {
//            return 1;
//        } else {
//            if (dosificacion.getValorFinal() == null) {
//                return 2;
//            }
//        }
//        return 0;
//    }
//
//    @Override
//    public List<TipoDosificacion> listaDosificaciones(Regional regional) {
//        List<TipoDosificacion> listaAux = new ArrayList<TipoDosificacion>();
//        List<TipoDosificacion> listaDosificaion = clasificadorService.listaTipoDosificacion();
//        if (!listaDosificaion.isEmpty()) {
//            for (TipoDosificacion tipoDosificacion : listaDosificaion) {
//                if (!verificaRegistroDosificacion(tipoDosificacion, regional)) {
//                    listaAux.add(tipoDosificacion);
//                }
//            }
//        }
//        return listaAux;
//    }
//
//    @Override
//    public Boolean verificaRegistroDosificacion(TipoDosificacion tipoDosificacion, Regional regional) {
//        List<Dosificacion> lista = hibernateTemplate.find(""
//                + "select r "
//                + "from Dosificacion r where r.regional.idRegional='" + regional.getIdRegional() + "' "
//                + "and r.tipoDosificacion.codigo='" + tipoDosificacion.getCodigo() + "' "
//                + "and r.vigente = true");
//        return !lista.isEmpty();
//    }
//
//    @Override
//    public Integer encuentraRestanteSegunTipoDosificacion(TipoDosificacion tipoDosificacion, Regional regional) {
//        int numeroRestante;
//        numeroRestante = 0;
//        List<Dosificacion> list = hibernateTemplate.find(""
//                + "select ch "
//                + "from Dosificacion ch "
//                + "where ch.tipoDosificacion.codigo='" + tipoDosificacion.getCodigo() + "'"
//                + "and ch.vigente=true "
//                + "and ch.regional.idRegional='" + regional.getIdRegional() + "' "
//                + "order by ch.siguiente");
//        if (!list.isEmpty()) {
//            numeroRestante = list.get(0).getValorFinal() - list.get(0).getSiguiente();
//
//        }
//        return numeroRestante;
//    }
//

    
    
    
    
    
    
    
    
}
