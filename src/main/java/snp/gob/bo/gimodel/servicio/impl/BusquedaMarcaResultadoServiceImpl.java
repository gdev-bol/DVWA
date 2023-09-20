/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.util.Collections;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.BusquedaMarcaResultado;
import snp.gob.bo.gimodel.mapper.BusquedaMarcaResultadoMapper;
import snp.gob.bo.gimodel.servicio.BusquedaMarcaResultadoService;

/**
 *
 * @author Eddy Valero
 * @see BusquedaMarcaResultadoService
 * @version 1.0, 17/10/2016
 */
@Service("busquedaMarcaResultadoService")
public class BusquedaMarcaResultadoServiceImpl implements BusquedaMarcaResultadoService {

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
    public List<BusquedaMarcaResultado> realizarBusquedaPorCriterios(String criterioBusqueda, String campo, int claseNiza) throws Exception {

        String consultaCondicion = "";
        switch (criterioBusqueda) {
            case "SOLI":
                consultaCondicion = " soap.nombres like '" + campo + "' ";
                break;
            case "APOD":
                consultaCondicion = " apo.nombres like '" + campo + "' ";
                break;
            case "SIGN":
                consultaCondicion = " upper(sig.signo) like upper('" + campo + "') ";
                break;
        }

        if (claseNiza > 0) {
            consultaCondicion = consultaCondicion + " and consulta.claseniza = " + claseNiza + " ";
        }


        try {
            String SQL = ""
                    + "select "
                    + "sig.idsignomarca, "
                    + "sig.sm, "
                    + "sig.signo as marca, "
                    + "ssc.numero_clase_niza as claseniza, "
                    + "sig.fecha_solicitud as fecha_solicitud, "
                    + "sig.numero_publicacion as numero_publicacion, "
                    + "sig.numero_registro as numero_registro, "
                    + "soap.nombres solicitante, "
                    + "apo.nombres apoderado, "
                    + "dom.nombre as estado, "
                    + "sig.fecha_registro as fecha_registro, "
                    + "sig.fecha_renovacion as fecha_renovacion, "
                    + "reno.titular as titular, "
                    + "reno.numero_renovacion as sr, "
                    + "renso.gestion_sr as gestion_sr "
                    + "from sigsignomarca sig "
                    + " join dominio dom "
                    + " on "
                    + "    dom.dominio = 'estado_marca' "
                    + "join sigsignoclaseniza ssc "
                    + "on ssc.idsignomarca = sig.idsignomarca "
                    + "join ( "
                    + "  select idsignomarca, string_agg(concat(nombre_razonsocial, primer_apellido, segundo_apellido), '; ') nombres "
                    + "  from sigsolicitanteapoderado sol "
                    + "  where sol.idsignomarca = idsignomarca "
                    + "  and sol.tipo_persona = 'SOLI' "
                    + "  and sol.estado = 'AC' "
                    + "  group by 1 "
                    + "  ) soap "
                    + "on ( "
                    + "sig.idsignomarca = soap.idsignomarca "
                    + ") "
                    + "join ( "
                    + "  select idsignomarca, string_agg(concat(nombre_razonsocial, primer_apellido, segundo_apellido), '; ') nombres "
                    + "  from sigsolicitanteapoderado apod "
                    + "  where apod.idsignomarca = idsignomarca "
                    + "  and apod.tipo_persona = 'APOD' "
                    + "  and apod.estado = 'AC' "
                    + "  group by 1 "
                    + "  ) apo "
                    + "on ( "
                    + "sig.idsignomarca = apo.idsignomarca "
                    + ") "
                    + "full outer join "
                    + "rensolicitudrenovacion renso "
                    + "on ( "
                    + "renso.sm = sig.sm "
                    + ") "
                    + "full outer join "
                    + "renrenovacion reno "
                    + "on( "
                    + "reno.idsolicitudrenovacion = renso.idsolicitudrenovacion "
                    + ") "
                    + "where "
                    + consultaCondicion
                    + "  and dom.codigo = sig.estado_marca "
                    + "  and dom.estado = 'AC' "
                    + "  and sig.estado = 'AC'"
                    + ""
                    + "";

            List<BusquedaMarcaResultado> listaBusquedaMarcaResultado = this.jdbcTemplate.query(SQL,
                    new BusquedaMarcaResultadoMapper());
            if (!listaBusquedaMarcaResultado.isEmpty()) {
                return listaBusquedaMarcaResultado;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<BusquedaMarcaResultado> realizarBusquedaMarca(String criterioBusqueda, String campo, int claseNiza) throws Exception {
        try {
            String SQL = "select * from busqueda_por_criterios(?,?,?) ";
            List<BusquedaMarcaResultado> listaBusquedaMarcaResultado = jdbcTemplate.query(SQL, new BusquedaMarcaResultadoMapper(),criterioBusqueda, campo, claseNiza);
            return listaBusquedaMarcaResultado;
        } catch (Exception e) {
            throw e;
        }
    }

}
