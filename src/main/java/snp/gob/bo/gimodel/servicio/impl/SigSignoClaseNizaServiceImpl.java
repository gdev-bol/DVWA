/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.ClaseNiza;
import snp.gob.bo.gimodel.entidad.ObjetoEliminadoGenerico;
import snp.gob.bo.gimodel.entidad.SigSignoClaseNiza;
import snp.gob.bo.gimodel.enums.EnumEstado;
import snp.gob.bo.gimodel.mapper.ClaseNizaMapper;
import snp.gob.bo.gimodel.mapper.ObjetoEliminadoGenericoMapper;
import snp.gob.bo.gimodel.mapper.SigSignoClaseNizaMapper;
import snp.gob.bo.gimodel.servicio.SigSignoClaseNizaService;
import snp.gob.bo.gimodel.solicitudes.entidades.SmSignoClaseNizas;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 26/08/2016
 */
@Service("sigSignoClaseNizaService")
public class SigSignoClaseNizaServiceImpl implements SigSignoClaseNizaService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public SigSignoClaseNiza registrarSigSignoMarca(SigSignoClaseNiza sigSignoClaseNiza) throws Exception {
        try {
            String SQL = "select * from reg_signoclaseniza(?, ?, ?, ?, ?, ?, ?);";
            SigSignoClaseNiza sigSignoClaseNizaRegistro = jdbcTemplate.queryForObject(SQL,
                    new SigSignoClaseNizaMapper(),
                    sigSignoClaseNiza.getIdSignoMarca(),
                    sigSignoClaseNiza.getIdClaseNiza(),
                    sigSignoClaseNiza.getIdLogTrans(),
                    sigSignoClaseNiza.getNumeroClaseNiza(),
                    sigSignoClaseNiza.getListaProducto(),
                    sigSignoClaseNiza.getEstado(),
                    sigSignoClaseNiza.getIdSipi());

            return sigSignoClaseNizaRegistro;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<SigSignoClaseNiza> obtenerListaSigSignoClazeNizaXIdSignoMarca(Long idSignoMarca) throws Exception {
        try {
            
          //  System.out.println("idsignomarca: "+idSignoMarca);
            String SQL = "select * from sigsignoclaseniza where idsignomarca = ? and estado = 'AC'";
            List<SigSignoClaseNiza> listaSigSignoClaseNiza = jdbcTemplate.query(SQL,
                    new SigSignoClaseNizaMapper(),
                    idSignoMarca);

            return listaSigSignoClaseNiza;
        } catch (Exception e) {
            throw e;
        }

    }
    @Override
    public SigSignoClaseNiza obtenerListaSigSignoClazeNizaXIdSignoMarcaNumeroNiza(Long idSignoMarca, int numeroClaseNizaAnterior) throws Exception {
        try {
            
            System.out.println("idsignomarca: "+idSignoMarca);
            String SQL = "select * from sigsignoclaseniza where idsignomarca = ? and numero_clase_niza=?";
            List<SigSignoClaseNiza> listaSigSignoClaseNiza = jdbcTemplate.query(SQL,
                    new SigSignoClaseNizaMapper(),
                    idSignoMarca,numeroClaseNizaAnterior);

            return listaSigSignoClaseNiza.get(0);
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public int encuentraPosicionListadoSigSignoClaseNiza(List<SigSignoClaseNiza> listaSigSignoClaseNiza, SigSignoClaseNiza sigSignoClaseNiza) throws Exception {
        try {
            int posicion = 0;
            int cont = 0;

            for (SigSignoClaseNiza itemSigSignoClaseNiza : listaSigSignoClaseNiza) {
                if ((itemSigSignoClaseNiza.getNumeroClaseNiza() == sigSignoClaseNiza.getNumeroClaseNiza())
                        && itemSigSignoClaseNiza.getListaProducto().equals(sigSignoClaseNiza.getListaProducto())) {
                    posicion = cont;

                }
                cont++;
            }

            return posicion;

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public SigSignoClaseNiza crudSigSignoClaseNiza(SigSignoClaseNiza sigSignoClaseNiza, int parametro) throws Exception {
        try {
            String SQL = "select * from crud_sigsignoclaseniza("
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"                    
                    + "?);";

            List<SigSignoClaseNiza> listaSigSignoClaseNiza = jdbcTemplate.query(SQL, new SigSignoClaseNizaMapper(),
                    sigSignoClaseNiza.getIdSignoClaseNiza(),
                    sigSignoClaseNiza.getIdSignoMarca(),
                    sigSignoClaseNiza.getIdClaseNiza(),
                    sigSignoClaseNiza.getIdLogTrans(),
                    sigSignoClaseNiza.getNumeroClaseNiza(),
                    sigSignoClaseNiza.getListaProducto(),
                    sigSignoClaseNiza.getEstado(),
                    sigSignoClaseNiza.getIdSipi(),                    
                    parametro);

            if (!listaSigSignoClaseNiza.isEmpty()) {
                return listaSigSignoClaseNiza.get(0);
            }
            return new SigSignoClaseNiza();

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void modificaListaSigSignoClaseNiza(Long idSignoMarca, List<SigSignoClaseNiza> lstSignoClaseNiza) throws Exception {
        try {
            int parametro = 2;
            int parametroGuardar = 1;
            int sw = 0;
            List<SigSignoClaseNiza> listaSigSignoClaseNizaBase = obtenerListaSigSignoClazeNizaXIdSignoMarca(idSignoMarca);

            //actualizar los registros modificados
            for (SigSignoClaseNiza sigSignoClaseNizaBase : listaSigSignoClaseNizaBase) {

                sw = 0;
                for (SigSignoClaseNiza sigSignoClaseNiza : lstSignoClaseNiza) {
                    if (sigSignoClaseNizaBase.getIdSignoClaseNiza().equals(sigSignoClaseNiza.getIdSignoClaseNiza())) {
                        sigSignoClaseNiza.setIdLogTrans(1L);
                        crudSigSignoClaseNiza(sigSignoClaseNiza, parametro);
                        sw = 1;
                    }
                }

                if (sw == 0) {
                    sigSignoClaseNizaBase.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                    crudSigSignoClaseNiza(sigSignoClaseNizaBase, parametro);
                }
            }

            //agregar los nuevos registros de clase niza
            for (SigSignoClaseNiza sigSignoClaseNizaNuevo : lstSignoClaseNiza) {
                if (sigSignoClaseNizaNuevo.getIdSignoClaseNiza() == null) {
                    sigSignoClaseNizaNuevo.setIdSignoMarca(idSignoMarca);
                    sigSignoClaseNizaNuevo.setIdLogTrans(1L);
                    sigSignoClaseNizaNuevo.setEstado(EnumEstado.ACTIVO.getCodigo());
                    crudSigSignoClaseNiza(sigSignoClaseNizaNuevo, parametroGuardar);
                }
            }

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void modificarListaSigSignoClaseNizaSubsanacion(Long idSignoMarca, Long idLogTrans, List<SmSignoClaseNizas> listaSignoClaseNizasSipi) throws Exception {
        try {
            int operacionAdicionar = 1;
            int operacionModificar = 2;

            // Recuperamos la Clase Niza desde la base de datos
            List<SigSignoClaseNiza> listaSignoClaseNizasHidra = obtenerListaSigSignoClazeNizaXIdSignoMarca(idSignoMarca);

            if (!listaSignoClaseNizasHidra.isEmpty()) {
                if (!listaSignoClaseNizasSipi.isEmpty()) {
                    // modificar
                    for (SigSignoClaseNiza signoClaseNizaHidra : listaSignoClaseNizasHidra) {
                        for (SmSignoClaseNizas signoClaseNizaSipi : listaSignoClaseNizasSipi) {
                            if (signoClaseNizaHidra.getIdSipi().equals(signoClaseNizaSipi.getIdPadre())) {

                                signoClaseNizaHidra.setNumeroClaseNiza(signoClaseNizaSipi.getNumeroClaseNiza());
                                signoClaseNizaHidra.setListaProducto(signoClaseNizaSipi.getListaProductos().trim());
                                signoClaseNizaHidra.setIdLogTrans(idLogTrans);
                                signoClaseNizaHidra.setIdSipi(signoClaseNizaSipi.getId());
                                
                                crudSigSignoClaseNiza(signoClaseNizaHidra, operacionModificar);
                                break;
                            }
                        }
                    }
                } else {
                    // eliminar
                    for (SigSignoClaseNiza signoClaseNizaHidra : listaSignoClaseNizasHidra) {
                        signoClaseNizaHidra.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                        crudSigSignoClaseNiza(signoClaseNizaHidra, operacionModificar);
                    }
                }

            } else {
                // adicionar
                for (SmSignoClaseNizas sigSignoClaseNizaEx : listaSignoClaseNizasSipi) {
                    if (sigSignoClaseNizaEx.getIdPadre() != null) {
                        SigSignoClaseNiza sigSignoClaseNiza = new SigSignoClaseNiza();
                        sigSignoClaseNiza.setIdSignoMarca(idSignoMarca);
                        sigSignoClaseNiza.setIdClaseNiza(sigSignoClaseNizaEx.getClaseNizaId());
                        sigSignoClaseNiza.setIdLogTrans(idLogTrans);
                        sigSignoClaseNiza.setNumeroClaseNiza(sigSignoClaseNizaEx.getNumeroClaseNiza());
                        sigSignoClaseNiza.setListaProducto(sigSignoClaseNizaEx.getListaProductos());
                        sigSignoClaseNiza.setEstado(EnumEstado.ACTIVO.getCodigo());
                        sigSignoClaseNiza.setIdSipi(sigSignoClaseNizaEx.getId());
                        crudSigSignoClaseNiza(sigSignoClaseNiza, operacionAdicionar);
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    @Override
    public SigSignoClaseNiza obtenerSigSignoClaseNiza(Long idSignoClaseNiza) throws Exception {
      
            try {
                String SQL = "select * from sigsignoclaseniza "
                        + " where idsignoclaseniza = ? "
                        + " and estado = 'AC' ";
                
                SigSignoClaseNiza signoClaseNiza = jdbcTemplate.queryForObject(SQL, 
                        new SigSignoClaseNizaMapper(), 
                        idSignoClaseNiza);
                
                return signoClaseNiza;

            } catch (EmptyResultDataAccessException e) {
                return new SigSignoClaseNiza();
            } catch (Exception e) {
                throw e;
            }

        
    }
    @Override
    public ClaseNiza obtenerClaseNizaporNumeroClaseNiza(int numeroNiza) throws Exception {
      
            try {
                String SQL = "select * from claseniza "
                        + " where numero_clase_niza = ? "
                        + " and estado = 'AC' ";
                ClaseNiza signoClaseNiza = jdbcTemplate.queryForObject(SQL, 
                        new ClaseNizaMapper(), 
                        numeroNiza);
                
                return signoClaseNiza;

            } catch (EmptyResultDataAccessException e) {
                return new ClaseNiza();
            } catch (Exception e) {
                throw e;
            }

        
    }

    @Override
    public ObjetoEliminadoGenerico obtenerRegistrosEliminadosSigSignoClaseNiza(Long idSignoMarca, String criterioBusqueda) throws Exception {
        try {
            String criterio = criterioBusqueda; 
            String SQL = "select idsignomarca id, "
                    + " string_agg(concat('Clase='||numero_clase_niza, '; Productos=', lista_producto), '; ') objeto_eliminado  "
                    + " from sigsignoclaseniza "
                    + " where idsignoclaseniza not in ("+ criterio+") "
                    + " and idsignomarca = ? "
                    + " and estado = 'AC' "
                    + " group by 1";

            ObjetoEliminadoGenerico objetoEliminadoGenerico = jdbcTemplate.queryForObject(SQL,
                    new ObjetoEliminadoGenericoMapper(),
                    idSignoMarca);

            return objetoEliminadoGenerico;
        } catch (EmptyResultDataAccessException e) {
            return new ObjetoEliminadoGenerico();
        } catch (Exception e) {
            throw e;
        }
    }

}
