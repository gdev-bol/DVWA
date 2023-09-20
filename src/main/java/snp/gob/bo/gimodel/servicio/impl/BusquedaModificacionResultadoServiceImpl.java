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
import snp.gob.bo.gimodel.entidad.BusquedaModificacionResultado;
import snp.gob.bo.gimodel.mapper.BusquedaModificacionResultadoMapper;
import snp.gob.bo.gimodel.servicio.BusquedaModificacionResultadoService;

/**
 *
 * @author susana
 */
@Service("busquedaModificacionResultadoService")
public class BusquedaModificacionResultadoServiceImpl implements BusquedaModificacionResultadoService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

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
    public List<BusquedaModificacionResultado> lista_BusquedaModificacionResultado_sm_publicacion_registro_or(Long sm, Long publicacion, Long registro, String serie) {
        List<BusquedaModificacionResultado> listaModificacion;

        try {
            String SQL = "";

            SQL = "select mod.idmodificacion, mod.sigla, mod.numero, mod.gestion, mod.sm, mod.fecha_ingreso,\n"
                    + "'' as oficina, mod.numero_registro, mod.serie_registro, mod.numero_renovacion, mod.serie_renovacion,\n"
                    + " mod.numero_publicacion, mod.signo_registrado, mod.idclase_registrado, mod.tipo_genero_registrado,\n"
                    + "soli.nombre as solicitante, treg.nombre as apoderado, ntit.nombres as nuevo_titular, \n"
                    + " ntit.pais_titular as pais_nuevo_titular, ntit.descripcion_departamento_titular,\n"
                    + " ntit.direccion_titular, \n"
                    + " mre.numero_resolucion, mre.gestion_resolucion, mre.fecha_resolucion, \n"
                    + " mod.tipo_modificacion as tipo_modificacion, \n"
                    + " '' as estado_modificacion, usu.nombre as usuario,\n"
                    + "  \n"
                    + "\n  '' as titular_registrado,"
                    + "\n  '' as direccion_registrado "
                    + "from modmodificacion mod\n"
                    + "--join usuario usu on  (usu.idusuario = mod.usuario)\n"
                    + "full outer join (select idusuario, string_agg(concat(nombre, ' ',primer_apellido, ' ',segundo_apellido), '; ') nombre \n"
                    + "from usuario --us where us.idusuario=mod.usuario\n"
                    + "group by 1) \n"
                    + "usu on (usu.idusuario = mod.usuario)\n"
                    + "--join dominio dom on (dom.dominio='estado_modificacion' and dom.estado = 'AC' \n"
                    + "--and mod.estado_modificacion = dom.codigo)\n"
                    + "\n"
                    + "full outer join (select idmodificacion, string_agg(concat(nombre_razonsocial, ' ',primer_apellido, ' ',segundo_apellido), '; ') nombre \n"
                    + "from modsolicitanteapoderado sol \n"
                    + "where sol.idmodificacion = idmodificacion and sol.tipo_persona= 'SOLI'\n"
                    + "and sol.estado = 'AC'  group by 1) \n"
                    + "soli on (mod.idmodificacion = soli.idmodificacion)\n"
                    + "\n"
                    + "full outer join (select idmodificacion,\n"
                    + "string_agg(concat((select dom.codigo from dominio dom where dom.codigo = nti.pais\n"
                    + "            and dom.dominio='pais' and estado = 'AC')\n"
                    + "), '; ') pais_titular, \n"
                    + "string_agg(concat((select dom.nombre from dominio dom where dom.codigo = nti.solicitud_departamento\n"
                    + "            and dom.dominio='lugar_expedicion' and estado = 'AC')\n"
                    + "), '; ') descripcion_departamento_titular, \n"
                    + "string_agg(concat(nombre_razonsocial,' ', primer_apellido,' ',segundo_apellido), '; ') nombres, \n"
                    + "string_agg(concat(direccion), '; ') direccion_titular    \n"
                    + "from modtitularlicenciatarionuevo nti \n"
                    + "where nti.idmodificacion = idmodificacion  \n"
                    + "and nti.estado = 'AC' group by 1) \n"
                    + "ntit on ( mod.idmodificacion = ntit.idmodificacion )\n"
                    + "\n"
                    + "full outer join (select idmodificacion, string_agg(concat(nombre_razonsocial, ' ',primer_apellido, ' ',segundo_apellido), '; ') nombre \n"
                    + "from modtitularlicenciatarioregistrado reg \n"
                    + "where reg.idmodificacion = idmodificacion and reg.tipo_persona_registrado= 'TREG'\n"
                    + "and reg.estado = 'AC'  group by 1) \n"
                    + "treg on (mod.idmodificacion = treg.idmodificacion)"
                    + "\n"
                    + "full outer join modresolucion mre on (mod.idmodificacion=mre.idmodificacion \n"
                    + "and mre.estado = 'AC')   "
                    + " where ((numero_registro = " + registro + " and numero_registro<>0)"
                    + " OR (numero_publicacion = " + publicacion + " and numero_publicacion<>0) OR sm=" + sm + ") and mod.estado = 'AC' order by 6 asc;";//order by mre.gestion_resolucion asc; para ordenar por resolucion

            //System.out.println("SQL ----->>>  " + SQL);
            listaModificacion = jdbcTemplate.query(SQL, new BusquedaModificacionResultadoMapper());

            if (!listaModificacion.isEmpty()) {
                return listaModificacion;
            }
            return new ArrayList<BusquedaModificacionResultado>();
        } catch (EmptyResultDataAccessException e) {
            //Como no se encontro ningun registro enviar la instancia de la nueva marca.
            return new ArrayList<BusquedaModificacionResultado>();
        } catch (Exception e) {
            throw e;
        }
    }

}
