/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.OpoMarcademandante;
import snp.gob.bo.gimodel.entidad.Oposicion;
import snp.gob.bo.gimodel.mapper.OpoMarcademandanteMapper;
import snp.gob.bo.gimodel.mapper.OposicionMapper;
import snp.gob.bo.gimodel.servicio.OposicionService;
import snp.gob.bo.gimodel.test.oposicionesTest;

/**
 *
 * @author Luis Angel Quispe Limachi
 */
@Service("oposicionService")
public class OposicionServiceImpl implements OposicionService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    @Autowired
    public void setDataSource(DataSource dataSource) {
        try {
            this.dataSource = dataSource;
            this.jdbcTemplate = new JdbcTemplate(dataSource);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Oposicion guardaOpodemandante(Oposicion oposicion) throws Exception {
        try {
            String SQL = "select * from inserta_oposicion(?,?,?,?,?,?,?,?);";
            Oposicion oposicion1 = (Oposicion) jdbcTemplate.queryForObject(SQL, new OposicionMapper(),
                    oposicion.getNro_opo(),
                    oposicion.getGaceta(),
                    oposicion.getFecha_pub(),
                    oposicion.getFecha_presentacion(),
                    oposicion.getUbicacion(),
                    oposicion.getObservacion(),
                    oposicion.getEstado(),
                    oposicion.getId_estado());
            return oposicion1;

        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public Oposicion guardaroposicion(Oposicion opox) throws Exception {

        try {
            String SQL = "select * from inserta_oposicion(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            Oposicion opores = (Oposicion) jdbcTemplate.queryForObject(SQL, new OposicionMapper(),
                    opox.getNro_opo(),
                    opox.getGaceta(),
                    opox.getFecha_pub(),
                    opox.getFecha_presentacion(),
                    opox.getUbicacion(),
                    opox.getObservacion(),
                    opox.getEstado(),
                    opox.getOrden_o(),
                    opox.getId_estado(),
                    opox.getEstadoopo(),
                    opox.getNumero_formulario(),
                    opox.getGestion_opo(),
                    opox.getCodigo_opo(),
                    opox.getIngreso_opo(),
                    opox.getOficina());
            return opores;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public List<Oposicion> obtenerListadoOposicion(Long nroopo) throws Exception {

        try {
            String SQL = "select * from lista_oposicion_nroopo(?);";
            List<Oposicion> listaOposicion = jdbcTemplate.query(SQL, new OposicionMapper(), nroopo);
            if (!listaOposicion.isEmpty()) {
                return listaOposicion;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public String obtenernombremarcaxidopo(Long numeroopo) {
        try {
            String SQL = "select dmte_marca_lnv from opomarcademandante where idoposicion=?;";

            return jdbcTemplate.queryForObject(SQL, String.class, numeroopo);

        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public Oposicion modificaOposicion(Oposicion opomodi) throws Exception {

        try {
            String SQL = "select * from modifica_oposicion(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            Oposicion modiobjeto1 = (Oposicion) jdbcTemplate.queryForObject(SQL, new OposicionMapper(), opomodi.getIdoposicion(),
                    opomodi.getNro_opo(),
                    opomodi.getGaceta(),
                    opomodi.getFecha_pub(),
                    opomodi.getFecha_presentacion(),
                    opomodi.getUbicacion(),
                    opomodi.getObservacion(),
                    opomodi.getEstado(),
                    opomodi.getOrden_o(),
                    opomodi.getId_estado(),
                    opomodi.getEstadoopo(),
                    opomodi.getNumero_formulario(),
                    opomodi.getGestion_opo(),
                    opomodi.getCodigo_opo(),
                    opomodi.getIngreso_opo(),
                    opomodi.getOficina());
            return modiobjeto1;

        } catch (Exception e) {
            throw e;
        }
    }

    public Long encuentraclaveprin(Date intronropubli) throws Exception {

        String SQL = "select idoposicion from oposicion where fecha_presentacion=?;";

        return jdbcTemplate.queryForObject(SQL, Long.class, intronropubli);

    }

    @Override
    public Boolean verificarexi(Long numeropubli) throws Exception {

        String SQL = "select * from oposicion where ingreso_opo='IN' and nro_opo=?;";
        List<Oposicion> objetnroopo = jdbcTemplate.query(SQL, new OposicionMapper(), numeropubli);

        return !objetnroopo.isEmpty();

    }

    @Override
    public Boolean verificarexi2(Long numeropubli, Integer nroopo) throws Exception {
        String SQL = "select * from oposicion where ingreso_opo='IN' and nro_opo=? and orden_o=?;";
        List<Oposicion> objetnroopo = jdbcTemplate.query(SQL, new OposicionMapper(), numeropubli,nroopo);

        return !objetnroopo.isEmpty();
    }

    @Override
    public Integer extraerultimonorden(Long numeropubli) throws Exception {
        String SQL = "select count(*) from oposicion where nro_opo=?;";
        return jdbcTemplate.queryForObject(SQL, Integer.class, numeropubli);

    }

    @Override
    public Integer extranroorden(Long numeropublic) throws Exception {
        String SQL = "select orden_o from oposicion where idoposicion=?;";
        return jdbcTemplate.queryForObject(SQL, Integer.class, numeropublic);
    }

    @Override
    public Long encuentraclaveprin(Long intronropubli, Integer introorden) throws Exception {
        String SQL = "select idoposicion from oposicion where nro_opo=? and orden_o=?;";
        return jdbcTemplate.queryForObject(SQL, Long.class, intronropubli, introorden);
    }

    @Override
    public String eliminaOposicion(Long opoeli) throws Exception {

        try {
            String SQL = "select * from elimina_oposicion(?);";
            return jdbcTemplate.queryForObject(SQL, String.class, opoeli);

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String obtenernombremarcaxidopo2(Long numeroopo) {
        try {
            String SQL = "select dmdo_marca_lnv from opomarcademandada where idoposicion=?;";

            return jdbcTemplate.queryForObject(SQL, String.class, numeroopo);

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Oposicion> obtnroopoxnroydmte(Long nroopo, String tipdmte) throws Exception {

        try {
            String SQL = "select * from oposicion "
                    + "            where nro_opo=? and idoposicion in (select idoposicion "
                    + " from opomarcademandante"
                    + " where idmarcademandante in (select idmarcademandante "
                    + "from oposolicitanteapoderado where tiposoliapo=? and tipo_persona='SOLI'));";
            List<Oposicion> listaOposicion = jdbcTemplate.query(SQL, new OposicionMapper(), nroopo, tipdmte);
            if (!listaOposicion.isEmpty()) {
                return listaOposicion;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public List<Oposicion> obtnroopoxnroydmdo(Long nroopo, String tipdmdo) throws Exception {

        try {
            String SQL = "select * from oposicion "
                    + "            where nro_opo=? and idoposicion in (select idoposicion "
                    + " from opomarcademandada"
                    + " where idmarcademandada in (select idmarcademandada "
                    + "from oposolicitanteapoderado where tiposoliapo=? and tipo_persona='SOLI'));";
            List<Oposicion> listaOposicion = jdbcTemplate.query(SQL, new OposicionMapper(), nroopo, tipdmdo);
            if (!listaOposicion.isEmpty()) {
                return listaOposicion;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Oposicion> obtnlistopoxregisydmte(Integer nroopo, String tipdmte) throws Exception {

        try {
            String SQL = "select  * from oposicion \n"
                    + "	                           where idoposicion in(select idoposicion\n"
                    + "                                                       from opomarcademandante\n"
                    + "                                                       where dmte_reg=? and dmte_serie=?);";
            List<Oposicion> listaOposicion = jdbcTemplate.query(SQL, new OposicionMapper(), nroopo, tipdmte);
            if (!listaOposicion.isEmpty()) {
                return listaOposicion;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public List<Oposicion> obtnlistopoxexpediydmte(Long nrosm) throws Exception {

        try {
            String SQL = "select  * from oposicion \n"
                    + "	                           where idoposicion in(select idoposicion\n"
                    + "                                                       from opomarcademandante\n"
                    + "                                                       where dmte_sm=?);";
            List<Oposicion> listaOposicion = jdbcTemplate.query(SQL, new OposicionMapper(), nrosm);
            if (!listaOposicion.isEmpty()) {
                return listaOposicion;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public List<Oposicion> obtnroopoxmarcaydmte(String marcax, String tipdmte) throws Exception {

        try {
            String SQL = "select * from oposicion \n"
                    + "         where idoposicion in (select idoposicion \n"
                    + "                                             from opomarcademandante\n"
                    + "                                             where dmte_marca_lnv=? and idmarcademandante in (select idmarcademandante \n"
                    + "                                                                         from oposolicitanteapoderado\n"
                    + "                                                                         where tiposoliapo=? and tipo_persona='SOLI'));";
            List<Oposicion> listaOposicion = jdbcTemplate.query(SQL, new OposicionMapper(), marcax, tipdmte);
            if (!listaOposicion.isEmpty()) {
                return listaOposicion;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public List<Oposicion> obtnroopoxmarcaydmdo(String marcax, String tipdmdo) throws Exception {

        try {
            String SQL = "select * from oposicion \n"
                    + "         where idoposicion in (select idoposicion \n"
                    + "                                             from opomarcademandada\n"
                    + "                                             where dmdo_marca_lnv=? and idmarcademandada in (select idmarcademandada \n"
                    + "                                                                           from oposolicitanteapoderado\n"
                    + "                                                                           where tiposoliapo=? and tipo_persona='SOLI'));";
            List<Oposicion> listaOposicion = jdbcTemplate.query(SQL, new OposicionMapper(), marcax, tipdmdo);
            if (!listaOposicion.isEmpty()) {
                return listaOposicion;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public List<Oposicion> obtlistopoxnomdmte(String marcadmte) throws Exception {

        try {
            String SQL = "select * from oposicion \n"
                    + "         where idoposicion in (select idoposicion \n"
                    + "                                             from opomarcademandante\n"
                    + "                                             where idmarcademandante in (select idmarcademandante \n"
                    + "                                                                         from oposolicitanteapoderado\n"
                    + "                                                                         where tiposoliapo='DMTE' and tipo_persona='SOLI' and nombre_razonsocial=?));";
            List<Oposicion> listaOposicion = jdbcTemplate.query(SQL, new OposicionMapper(), marcadmte);
            if (!listaOposicion.isEmpty()) {
                return listaOposicion;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public List<Oposicion> obtlistopoxnomdmte(String nombreapo, String tipdmte) throws Exception {
        try {
            String SQL = "select * from oposicion \n"
                    + "         where idoposicion in (select idoposicion \n"
                    + "                                             from opomarcademandante\n"
                    + "                                             where idmarcademandante in (select idmarcademandante \n"
                    + "                                                                         from oposolicitanteapoderado\n"
                    + "                                                                         where nombre_razonsocial like ? and tipo_persona='APOD' and tiposoliapo=? ));";
            List<Oposicion> listaOposicion = jdbcTemplate.query(SQL, new OposicionMapper(), nombreapo, tipdmte);
            if (!listaOposicion.isEmpty()) {
                return listaOposicion;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public List<Oposicion> obtlistopoxnomdmdo(String nombreapo, String tipdmte) throws Exception {

        try {
            String SQL = "select * from oposicion \n"
                    + "         where idoposicion in (select idoposicion\n"
                    + "                                             from opomarcademandada\n"
                    + "                                             where idmarcademandada in (select idmarcademandada \n"
                    + "                                                                         from oposolicitanteapoderado\n"
                    + "                                                                         where nombre_razonsocial like ? and tipo_persona='APOD' and tiposoliapo=?));";
            List<Oposicion> listaOposicion = jdbcTemplate.query(SQL, new OposicionMapper(), nombreapo, tipdmte);
            if (!listaOposicion.isEmpty()) {
                return listaOposicion;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public List<Oposicion> obtlistopoxfechapres(Date fechapre) throws Exception {
        try {
            String SQL = "select * from oposicion where fecha_presentacion=?";
            List<Oposicion> listaOposicion = jdbcTemplate.query(SQL, new OposicionMapper(), fechapre);
            if (!listaOposicion.isEmpty()) {
                return listaOposicion;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public Integer obtregistroxnumeroopo(Long nrooposic) throws Exception {

        try {
            String SQL = "SELECT dmte_reg FROM opomarcademandante WHERE idoposicion=?;";

            return jdbcTemplate.queryForObject(SQL, Integer.class, nrooposic);

        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public String obtrseriexnumeroopo(Long nrooposic) throws Exception {
        try {
            String SQL = "SELECT dmte_serie FROM opomarcademandante WHERE idoposicion=?;";

            return jdbcTemplate.queryForObject(SQL, String.class, nrooposic);

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Long obtexpedientexnroopo(Long nrooposic) throws Exception {

        try {
            String SQL = "SELECT dmte_sm FROM opomarcademandante WHERE idoposicion=?;";

            return jdbcTemplate.queryForObject(SQL, Long.class, nrooposic);

        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public String obtapoderadodmtexnroopo(Long nrooposic) throws Exception {

        try {
            String SQL = "select nombre_razonsocial\n"
                    + "from oposolicitanteapoderado\n"
                    + "where tipo_persona='APOD' and idmarcademandante in (select idmarcademandante \n"
                    + "					            from opomarcademandante\n"
                    + "						    where idoposicion=?);";

            return jdbcTemplate.queryForObject(SQL, String.class, nrooposic);

        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public String obtapoderadodmdoxnroopo(Long nrooposic) throws Exception {
        try {
            String SQL = "select nombre_razonsocial\n"
                    + "from oposolicitanteapoderado\n"
                    + "where tipo_persona='APOD' and idmarcademandada in (select idmarcademandada \n"
                    + "        from opomarcademandada\n"
                    + "						     where idoposicion=?);";

            if (jdbcTemplate.queryForObject(SQL, String.class, nrooposic) != null || (jdbcTemplate.queryForObject(SQL, String.class, nrooposic)).equals("")) {
                return jdbcTemplate.queryForObject(SQL, String.class, nrooposic);
            } else {
                return "";
            }

        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public String obtestadoxnroopo(Long nroestado) throws Exception {

        try {
            String SQL = "select estado from opoestado where idestado=?;";

            return jdbcTemplate.queryForObject(SQL, String.class, nroestado);

        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public String obtienestadoopo(Long idoposicion) {
        try {
            String SQL = "select estado from oposicion where idoposicion=?;";

            return jdbcTemplate.queryForObject(SQL, String.class, idoposicion);

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String obtienenombreapoderado(Long idoposicion) throws Exception {

        try {
            String SQL = "select (nombre_razonsocial||' '||primer_apellido||' '||segundo_apellido) as nombrecom \n"
                    + "from oposolicitanteapoderado\n"
                    + "where tipo_persona='APOD' and idmarcademandada=(select idmarcademandada \n"
                    + "						from opomarcademandada\n"
                    + "						where idoposicion=?);";

            return jdbcTemplate.queryForObject(SQL, String.class, idoposicion);

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String obtienerobservacionopo(Long idoposicion) throws Exception {
        try {
            String SQL = "select observacion from oposicion where idoposicion=?;";

            return jdbcTemplate.queryForObject(SQL, String.class, idoposicion);

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Oposicion> obtoposicionxnro_opo(Long nroopo) throws Exception {

        try {
            String SQL = "select * from oposicion where nro_opo=? and estadoopo='AC'";
            List<Oposicion> listaOposicion = jdbcTemplate.query(SQL, new OposicionMapper(), nroopo);
            if (!listaOposicion.isEmpty()) {
                return listaOposicion;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public Integer obttotaloposicionesxnro_opo(Long nroopo) throws Exception {
        try {
            String SQL = "select count(idoposicion) from oposicion where nro_opo=? and estadoopo='AC';";

            return jdbcTemplate.queryForObject(SQL, Integer.class, nroopo);

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Oposicion obtoposicionxnroopo(Long idoposicionpri) throws Exception {

        try {
            String SQL = "select * from oposicion where idoposicion=?;";
            Oposicion oposicion1 = (Oposicion) jdbcTemplate.queryForObject(SQL, new OposicionMapper(), idoposicionpri);
            return oposicion1;

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Date encuentrafechapres(Long idoposicion) throws Exception {
        try {

            Date fechapre = new Date();
            String SQL = "select fecha_presentacion from oposicion where idoposicion=?;";
            fechapre = jdbcTemplate.queryForObject(SQL, Date.class, idoposicion);
            return fechapre;

        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public Boolean verificadmtexregistro(Integer dmtereg, String dmteserie) throws Exception {

        String SQL = "select * from opomarcademandante where dmte_reg=? and dmte_serie=?;";
        List<OpoMarcademandante> objetnroreg = jdbcTemplate.query(SQL, new OpoMarcademandanteMapper(), dmtereg, dmteserie);
        return !objetnroreg.isEmpty();

    }

    @Override
    public Boolean verificadmtexsm(Long dmtesm) throws Exception {

        String SQL = "select * from opomarcademandante where dmte_sm=?;";
        List<OpoMarcademandante> objetnrosm = jdbcTemplate.query(SQL, new OpoMarcademandanteMapper(), dmtesm);
        return !objetnrosm.isEmpty();

    }

    @Override
    public Boolean verificadmtexnropubli(Integer dmtepubli) throws Exception {

        String SQL = "select * from opomarcademandante where dmte_public=?;";
        List<OpoMarcademandante> objetxnropu = jdbcTemplate.query(SQL, new OpoMarcademandanteMapper(), dmtepubli);
        return !objetxnropu.isEmpty();

    }

    @Override
    public String completasmxnroexp(Long nroexpediente) throws Exception {
        String cadesm = "";

        if (nroexpediente != null) {

            int canticarct = (Long.toString(nroexpediente)).length();

            switch (canticarct) {

                case 1:
                    cadesm = "00000" + Long.toString(nroexpediente);
                    break;
                case 2:
                    cadesm = "0000" + Long.toString(nroexpediente);
                    break;
                case 3:
                    cadesm = "000" + Long.toString(nroexpediente);
                    break;
                case 4:
                    cadesm = "00" + Long.toString(nroexpediente);
                    break;
                case 5:
                    cadesm = "0" + Long.toString(nroexpediente);
                    break;
                default:
                    System.out.println("este sm tiene una cantidad de digitos raro");
                    ;
                    break;
            }

        }
        return cadesm;
    }

    @Override
    public Oposicion obtoposicionxnroformulario(Long numeroformulario) throws Exception {
        Oposicion oposicion;
        String SQL = "select * from oposicion where numero_formulario = ?;";
        if (!jdbcTemplate.query(SQL, new OposicionMapper(), numeroformulario).isEmpty()) {
            oposicion = (Oposicion) jdbcTemplate.queryForObject(SQL, new OposicionMapper(), numeroformulario);
            return oposicion;
        }
        return null;
    }
    
    
    @Override
    public Oposicion obtoposicionxnroformularioRecaudaciones(Long numeroformulario) throws Exception {
        Oposicion oposicion;
        String SQL = "select * from oposicion where numero_formulario = ?;";
        if (!jdbcTemplate.query(SQL, new OposicionMapper(), numeroformulario).isEmpty()) {
            oposicion = (Oposicion) jdbcTemplate.queryForObject(SQL, new OposicionMapper(), numeroformulario);
            return oposicion;
        }
        return new Oposicion();
    }
    
    
    
    
    
    
    

    @Override
    public List<Oposicion> obtenerListadoOposicionXgaceta(Integer gaceta) throws Exception {
        try {
            String SQL = "select *\n"
                    + "from oposicion \n"
                    + "where gaceta = ?\n"
                    + "and estadoopo = 'AC'\n"
                    + "and ingreso_opo = 'IN'\n"
                    + "order by nro_opo asc";
            List<Oposicion> listaOposicion = jdbcTemplate.query(SQL, new OposicionMapper(), gaceta);
            if (!listaOposicion.isEmpty()) {
                return listaOposicion;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }

    }
}
