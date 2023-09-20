/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.util.Collections;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.BusquedaOposicion;
import snp.gob.bo.gimodel.mapper.BusquedaOposicionMapper;
import snp.gob.bo.gimodel.servicio.BusquedaOposicionService;

/**
 *
 * @author Luis Angel Quispe Limachi
 */
@Service("busquedaOposicionService")
public class BusquedaOposicionServiceImpl implements BusquedaOposicionService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
 

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
    public List<BusquedaOposicion> realizarBusquedaxnropublicacion(Long publicacion, String soliapo) throws Exception {

        try {

            String SQL = "";

            if (soliapo.equals("DMDO")) {
                SQL = "select DISTINCT ON(opodmda2.fecha_presentacion)opodmda2.nro_opo as publicacion,opodmda2.fecha_presentacion as fechapresentacion,opodmda2.dmdo_public as nrocasopubl,null::numeric(18,0) as registro,null::character varying as serie,null::Bigint as expedientesm,opodmda2.dmdo_marca_lnv as marca,oposol.nombre_razonsocial as firma,oposol.nombre_razonsocial as apoderado,opodmda2.estaok as estado,opodmda2.fec_lim as fechalim,opodmda2.ubicacion as ubicacion,opodmda2.gaceta as gaceta\n"
                        + "from (select opo.fecha_presentacion,opo.ubicacion,opo.estado as estaok,opodmda.*\n"
                        + "      from oposicion opo inner join opomarcademandada opodmda\n"
                        + "      on opo.idoposicion=opodmda.idoposicion) opodmda2 inner join oposolicitanteapoderado oposol                                                                                                        \n"
                        + "                                                       on opodmda2.idmarcademandada=oposol.idmarcademandada\n"
                        + "where opodmda2.nro_opo=?;";
            } else {
                SQL = "select DISTINCT ON(opodmte2.fecha_presentacion)opodmte2.nro_opok as publicacion,opodmte2.fecha_presentacion as fechapresentacion,opodmte2.dmte_public as nrocasopubl,opodmte2.dmte_reg as registro,opodmte2.dmte_serie as serie,opodmte2.dmte_sm as expedientesm,opodmte2.dmte_marca_lnv as marca,oposol.nombre_razonsocial as firma,oposol.nombre_razonsocial as apoderado,opodmte2.estaok as estado,null::timestamp without time zone as fechalim,opodmte2.ubicacion as ubicacion,null::numeric(18,0) as gaceta                                            \n"
                        + "from (select opo.nro_opo as nro_opok,opo.estado as estaok,opo.ubicacion,opo.fecha_presentacion,opodmte.*\n"
                        + "      from oposicion opo inner join opomarcademandante opodmte\n"
                        + "      on opo.idoposicion=opodmte.idoposicion) opodmte2 inner join oposolicitanteapoderado oposol                                                                                                        \n"
                        + "                                                       on opodmte2.idmarcademandante=oposol.idmarcademandante\n"
                        + "where opodmte2.dmte_public=?;";
            }

            List<BusquedaOposicion> listaBusquedaxnropubli = this.jdbcTemplate.query(SQL, new BusquedaOposicionMapper(), publicacion);
            if (!listaBusquedaxnropubli.isEmpty()) {
                return listaBusquedaxnropubli;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public List<BusquedaOposicion> realizarBusquedaxnroregistro(Integer registro, String serie, String soliapo) throws Exception {
        try {

            String SQL = "";

            if (soliapo.equals("DMTE")) {
                SQL = "select DISTINCT ON(opodmte2.nro_opok)opodmte2.nro_opok as publicacion,opodmte2.fecha_presentacion as fechapresentacion,opodmte2.dmte_public as nrocasopubl,opodmte2.dmte_reg as registro,opodmte2.dmte_serie as serie,opodmte2.dmte_sm as expedientesm,opodmte2.dmte_marca_lnv as marca,oposol.nombre_razonsocial as firma,oposol.nombre_razonsocial as apoderado,opodmte2.estaok as estado,null::timestamp without time zone as fechalim,opodmte2.ubicacion as ubicacion,null::numeric(18,0) as gaceta                                          \n"
                        + "from (select opo.nro_opo as nro_opok,opo.estado as estaok,opo.ubicacion,opo.fecha_presentacion,opodmte.*\n"
                        + "      from oposicion opo inner join opomarcademandante opodmte\n"
                        + "      on opo.idoposicion=opodmte.idoposicion) opodmte2 inner join oposolicitanteapoderado oposol                                                                                                        \n"
                        + "      on opodmte2.idmarcademandante=oposol.idmarcademandante\n"
                        + "where opodmte2.dmte_reg=? and opodmte2.dmte_serie=?;";
            }

            List<BusquedaOposicion> listaBusquedaxnropubli = this.jdbcTemplate.query(SQL, new BusquedaOposicionMapper(), registro, serie);
            if (!listaBusquedaxnropubli.isEmpty()) {
                return listaBusquedaxnropubli;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<BusquedaOposicion> realizarBusquedaxnrosm(Long expesm, Long expeanio, String soliapo) throws Exception {
           Formatter fmt = new Formatter();
 String concate = Long.toString(expeanio) + fmt.format("%06d", expesm) + "00";
        long numerosm = Long.parseLong(concate);
               try {

            String SQL = "";

            if (soliapo.equals("DMTE")) {
                SQL = "select DISTINCT ON(opodmte2.nro_opok)opodmte2.nro_opok as publicacion,opodmte2.fecha_presentacion as fechapresentacion,opodmte2.dmte_public as nrocasopubl,opodmte2.dmte_reg as registro,opodmte2.dmte_serie as serie,opodmte2.dmte_sm as expedientesm,opodmte2.dmte_marca_lnv as marca,oposol.nombre_razonsocial as firma,oposol.nombre_razonsocial as apoderado,opodmte2.estaok as estado,null::timestamp without time zone as fechalim,opodmte2.ubicacion as ubicacion,null::numeric(18,0) as gaceta                                          \n"
                        + "from (select opo.nro_opo as nro_opok,opo.estado as estaok,opo.ubicacion,opo.fecha_presentacion,opodmte.*\n"
                        + "      from oposicion opo inner join opomarcademandante opodmte\n"
                        + "      on opo.idoposicion=opodmte.idoposicion) opodmte2 inner join oposolicitanteapoderado oposol                                                                                                        \n"
                        + "      on opodmte2.idmarcademandante=oposol.idmarcademandante\n"
                        + "where opodmte2.dmte_sm=?;";
            }

            List<BusquedaOposicion> listaBusquedaxnrosm = this.jdbcTemplate.query(SQL, new BusquedaOposicionMapper(), numerosm);
            if (!listaBusquedaxnrosm.isEmpty()) {
                return listaBusquedaxnrosm;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public List<BusquedaOposicion> realizarBusquedaxmarca(String marca, String soliapo) throws Exception {

        try {
            String cadenaconcate = "%" + marca + "%";
            String consultaCondicion = " '" + cadenaconcate + "' ";

            String SQL = "";

            if (soliapo.equals("DMDO")) {
                SQL = "select DISTINCT ON(opodmda2.nro_opo)opodmda2.nro_opo as publicacion,opodmda2.fecha_presentacion as fechapresentacion,opodmda2.dmdo_public as nrocasopubl,null::numeric(18,0) as registro,null::character varying as serie,null::Bigint as expedientesm,opodmda2.dmdo_marca_lnv as marca,oposol.nombre_razonsocial as firma,oposol.nombre_razonsocial as apoderado,opodmda2.estaok as estado,opodmda2.fec_lim as fechalim,opodmda2.ubicacion as ubicacion,opodmda2.gaceta as gaceta                       \n"
                        + "from (select opo.fecha_presentacion,opo.ubicacion,opo.estado as estaok,opodmda.*\n"
                        + "      from oposicion opo inner join opomarcademandada opodmda\n"
                        + "                        on opo.idoposicion=opodmda.idoposicion) opodmda2 inner join oposolicitanteapoderado oposol                                                                                                 \n"
                        + "									 on opodmda2.idmarcademandada=oposol.idmarcademandada\n"
                        + "where opodmda2.dmdo_marca_lnv like " + consultaCondicion + ";";
            } else {
                SQL = "select DISTINCT ON(opodmte2.nro_opok)opodmte2.nro_opok as publicacion,opodmte2.fecha_presentacion as fechapresentacion,opodmte2.dmte_public as nrocasopubl,opodmte2.dmte_reg as registro,opodmte2.dmte_serie as serie,opodmte2.dmte_sm as expedientesm,opodmte2.dmte_marca_lnv as marca,oposol.nombre_razonsocial as firma,oposol.nombre_razonsocial as apoderado,opodmte2.estaok as estado,null::timestamp without time zone as fechalim,opodmte2.ubicacion as ubicacion,null::numeric(18,0) as gaceta                                          \n"
                        + "from (select opo.nro_opo as nro_opok,opo.estado as estaok,opo.ubicacion,opo.fecha_presentacion,opodmte.*\n"
                        + "      from oposicion opo inner join opomarcademandante opodmte\n"
                        + "      on opo.idoposicion=opodmte.idoposicion) opodmte2 inner join oposolicitanteapoderado oposol                                                                                                        \n"
                        + "							on opodmte2.idmarcademandante=oposol.idmarcademandante\n"
                        + "where opodmte2.dmte_marca_lnv like " + consultaCondicion + ";";
            }

            List<BusquedaOposicion> listaBusquedaxmarca = this.jdbcTemplate.query(SQL, new BusquedaOposicionMapper());
            if (!listaBusquedaxmarca.isEmpty()) {
                return listaBusquedaxmarca;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<BusquedaOposicion> realizarBusquedaxdmdodmte(String nombredmtedmdo, String soliapo) throws Exception {

        try {
            String cadenaconcate = "%" + nombredmtedmdo + "%";
            String consultaCondicion = " '" + cadenaconcate + "' ";

            String SQL = "";

            if (soliapo.equals("DMDO")) {
                SQL = "select DISTINCT ON(opodmda2.nro_opo)opodmda2.nro_opo as publicacion,opodmda2.fecha_presentacion as 	fechapresentacion,opodmda2.dmdo_public as nrocasopubl,null::numeric(18,0) as registro,null::character varying as serie,null::Bigint as expedientesm,opodmda2.dmdo_marca_lnv as marca,oposol.nombre_razonsocial as firma,oposol.nombre_razonsocial as apoderado,opodmda2.estaok as estado,opodmda2.fec_lim as fechalim,opodmda2.ubicacion as ubicacion,opodmda2.gaceta as gaceta                       \n"
                        + "from (select opo.fecha_presentacion,opo.ubicacion,opo.estado as estaok,opodmda.*\n"
                        + "      from oposicion opo inner join opomarcademandada opodmda\n"
                        + "                        on opo.idoposicion=opodmda.idoposicion) opodmda2 inner join oposolicitanteapoderado oposol                                                                                                 \n"
                        + "									 on opodmda2.idmarcademandada=oposol.idmarcademandada\n"
                        + "where oposol.nombre_razonsocial like  " + consultaCondicion + ";";
            } else {
                SQL = "select DISTINCT ON(opodmte2.nro_opok)opodmte2.nro_opok as publicacion,opodmte2.fecha_presentacion as fechapresentacion,opodmte2.dmte_public as nrocasopubl,opodmte2.dmte_reg as registro,opodmte2.dmte_serie as serie,opodmte2.dmte_sm as expedientesm,opodmte2.dmte_marca_lnv as marca,oposol.nombre_razonsocial as firma,oposol.nombre_razonsocial as apoderado,opodmte2.estaok as estado,null::timestamp without time zone as fechalim,opodmte2.ubicacion as ubicacion,null::numeric(18,0) as gaceta                                          \n"
                        + "from (select opo.nro_opo as nro_opok,opo.estado as estaok,opo.ubicacion,opo.fecha_presentacion,opodmte.*\n"
                        + "      from oposicion opo inner join opomarcademandante opodmte\n"
                        + "      on opo.idoposicion=opodmte.idoposicion) opodmte2 inner join oposolicitanteapoderado oposol                                                                                                        \n"
                        + "							on opodmte2.idmarcademandante=oposol.idmarcademandante\n"
                        + "where oposol.nombre_razonsocial like  " + consultaCondicion + ";";
            }

            List<BusquedaOposicion> listaBusquedaxnropubli = this.jdbcTemplate.query(SQL, new BusquedaOposicionMapper());
            if (!listaBusquedaxnropubli.isEmpty()) {
                return listaBusquedaxnropubli;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public List<BusquedaOposicion> realizarBusquedaxapoderado(String nombreapoderado, String soliapo) throws Exception {
      
         try {
            String cadenaconcate = "%" + nombreapoderado + "%";
            String consultaCondicion = " '" + cadenaconcate + "' ";

            String SQL = "";

            if (soliapo.equals("DMDO")) {
                SQL = "select DISTINCT ON(opodmda2.nro_opo)opodmda2.nro_opo as publicacion,opodmda2.fecha_presentacion as 	fechapresentacion,opodmda2.dmdo_public as nrocasopubl,null::numeric(18,0) as registro,null::character varying as serie,null::Bigint as expedientesm,opodmda2.dmdo_marca_lnv as marca,oposol.nombre_razonsocial as firma,oposol.nombre_razonsocial as apoderado,opodmda2.estaok as estado,opodmda2.fec_lim as fechalim,opodmda2.ubicacion as ubicacion,opodmda2.gaceta as gaceta                       \n"
                        + "from (select opo.fecha_presentacion,opo.ubicacion,opo.estado as estaok,opodmda.*\n"
                        + "      from oposicion opo inner join opomarcademandada opodmda\n"
                        + "                        on opo.idoposicion=opodmda.idoposicion) opodmda2 inner join oposolicitanteapoderado oposol                                                                                                 \n"
                        + "									 on opodmda2.idmarcademandada=oposol.idmarcademandada\n"
                        + "where oposol.nombre_razonsocial like  " + consultaCondicion +"and oposol.tipo_persona='APOD'"+ ";";
            } else {
                SQL = "select DISTINCT ON(opodmte2.nro_opok)opodmte2.nro_opok as publicacion,opodmte2.fecha_presentacion as fechapresentacion,opodmte2.dmte_public as nrocasopubl,opodmte2.dmte_reg as registro,opodmte2.dmte_serie as serie,opodmte2.dmte_sm as expedientesm,opodmte2.dmte_marca_lnv as marca,oposol.nombre_razonsocial as firma,oposol.nombre_razonsocial as apoderado,opodmte2.estaok as estado,null::timestamp without time zone as fechalim,opodmte2.ubicacion as ubicacion,null::numeric(18,0) as gaceta                                          \n"
                        + "from (select opo.nro_opo as nro_opok,opo.estado as estaok,opo.ubicacion,opo.fecha_presentacion,opodmte.*\n"
                        + "      from oposicion opo inner join opomarcademandante opodmte\n"
                        + "      on opo.idoposicion=opodmte.idoposicion) opodmte2 inner join oposolicitanteapoderado oposol                                                                                                        \n"
                        + "							on opodmte2.idmarcademandante=oposol.idmarcademandante\n"
                        + "where oposol.nombre_razonsocial like  " + consultaCondicion +"and oposol.tipo_persona='APOD'"+ ";";
            }

            List<BusquedaOposicion> listaBusquedaxnropubli = this.jdbcTemplate.query(SQL, new BusquedaOposicionMapper());
            if (!listaBusquedaxnropubli.isEmpty()) {
                return listaBusquedaxnropubli;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
        
        
    }

    
    
    
    
    @Override
    public List<BusquedaOposicion> realizarBusquedaxfechapresentacion(Date fechapresent, String soliapo) throws Exception {

        try {

            String SQL = "";

            if (soliapo.equals("DMDO")) {
                SQL = "select opodmda2.nro_opo as publicacion,opodmda2.fecha_presentacion as fechapresentacion,opodmda2.dmdo_public as nrocasopubl,null::numeric(18,0) as registro,null::character varying as serie,null::Bigint as expedientesm,opodmda2.dmdo_marca_lnv as marca,oposol.nombre_razonsocial as firma,oposol.nombre_razonsocial as apoderado,opodmda2.estaok as estado,opodmda2.fec_lim as fechalim,opodmda2.ubicacion as ubicacion,opodmda2.gaceta as gaceta                       \n"
                        + "from (select opo.fecha_presentacion,opo.ubicacion,opo.estado as estaok,opodmda.*\n"
                        + "      from oposicion opo inner join opomarcademandada opodmda\n"
                        + "                        on opo.idoposicion=opodmda.idoposicion) opodmda2 inner join oposolicitanteapoderado oposol                                                                                                 \n"
                        + "									 on opodmda2.idmarcademandada=oposol.idmarcademandada\n"
                        + "where opodmda2.fecha_presentacion = ?;";
            } else {
                SQL = "select opodmte2.nro_opok as publicacion,opodmte2.fecha_presentacion as fechapresentacion,opodmte2.dmte_public as nrocasopubl,opodmte2.dmte_reg as registro,opodmte2.dmte_serie as serie,opodmte2.dmte_sm as expedientesm,opodmte2.dmte_marca_lnv as marca,oposol.nombre_razonsocial as firma,oposol.nombre_razonsocial as apoderado,opodmte2.estaok as estado,null::timestamp without time zone as fechalim,opodmte2.ubicacion as ubicacion,null::numeric(18,0) as gaceta                                          \n"
                        + "from (select opo.nro_opo as nro_opok,opo.estado as estaok,opo.ubicacion,opo.fecha_presentacion,opodmte.*\n"
                        + "      from oposicion opo inner join opomarcademandante opodmte\n"
                        + "      on opo.idoposicion=opodmte.idoposicion) opodmte2 inner join oposolicitanteapoderado oposol                                                                                                        \n"
                        + "							on opodmte2.idmarcademandante=oposol.idmarcademandante\n"
                        + "where opodmte2.fecha_presentacion=?;";
            }

            List<BusquedaOposicion> listaBusquedaxnropubli = this.jdbcTemplate.query(SQL, new BusquedaOposicionMapper(), fechapresent);
            if (!listaBusquedaxnropubli.isEmpty()) {
                return listaBusquedaxnropubli;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }

    }

}
