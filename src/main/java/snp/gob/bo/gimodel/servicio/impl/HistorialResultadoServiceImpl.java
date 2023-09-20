/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.HistorialResultado;
import snp.gob.bo.gimodel.enums.EnumPrefijoTablas;
import snp.gob.bo.gimodel.mapper.HistorialResultadoMapper;
import snp.gob.bo.gimodel.servicio.HistorialResultadoService;

/**
 *
 * @author Eddy Valero
 * @see HistorialResultadoServiceImpl
 * @version 1.0, 19/11/2016
 */
@Service("historialCompletoService")
public class HistorialResultadoServiceImpl implements HistorialResultadoService {

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
    public List<HistorialResultado> obtenerListaHistorialCompletoXId(Long id, String prefijo) throws Exception {
        try {
            String SQL = "";
            List<HistorialResultado> listaHistorialResultado;

            if (prefijo.equals(EnumPrefijoTablas.SIGNO.getCodigo())) {
                SQL = "select "
                        + "	row_number () over (order by his.idhistorial desc) posicion, "
                        + "	his.idhistorial idhistorial, "
                        + "	his.idsignomarca id, "
                        + "	his.idlogtrans idlogtrans, "
                        + "	his.idusuario idusuario, "
                        + "	his.tipo tipo, "
                        + "	his.operacion operacion, "
                        + "	his.estado_marca_descripcion estado_marca_descripcion, "
                        + "	his.observacion observacion, "
                        + "	his.ubicacion_descripcion ubicacion_descripcion, "
                        + "	his.seccion seccion, "
                        + "	his.gestion_renovacion gestion_renovacion, "
                        + "	his.descripcion descripcion, "
                        + "	his.descripcion_lista_productos descripcion_lista_productos, "
                        + "	his.fecha_operacion fecha_operacion, "
                        //                        + "	case when us.nombre is null then '' else us.nombre end "
                        //                        + "	|| ' ' || "
                        //                        + "	case when us.primer_apellido is null then '' else us.primer_apellido end "
                        //                        + "	|| ' '|| "
                        //                        + "	case when us.segundo_apellido is null then '' else us.segundo_apellido end "
                        //                        + "	:: text as usuario_nombre_completo, "
                        + "     usu.nombre as usuario_nombre_completo,"
                        + "	usu.login usuario, "
                        + "	his.estado estado "
                        + "from sighistorial his "
                        //                        + "join "
                        //                        + "usuario us "
                        //                        + "	on (us.idusuario = his.idusuario) "
                        + "join (select idusuario, login, string_agg(concat(nombre, ' ',primer_apellido, ' ',segundo_apellido), '; ') nombre \n"
                        + "from usuario "
                        + "group by 1) \n"
                        + "usu on (usu.idusuario = his.idusuario) "
                        + "where "
                        + "	idsignomarca = ? "
                        + "	and his.estado = 'AC' "
                        + "	order by fecha_operacion desc;"
                        + ""
                        + "";
             
                listaHistorialResultado = jdbcTemplate.query(SQL, new HistorialResultadoMapper(), id);
                if (!listaHistorialResultado.isEmpty()) {
                    return listaHistorialResultado;
                }
                return new ArrayList<HistorialResultado>();
            }//fin de preguntar por la opción de sighistorial

            if (prefijo.equals(EnumPrefijoTablas.MODIFICACION.getCodigo())) {
                SQL = "select "
                        + "	row_number () over (order by his.idhistorial desc) posicion, "
                        + "	his.idhistorial idhistorial, "
                        + "	his.idmodificacion id, "
                        + "	his.idlogtrans idlogtrans, "
                        + "	his.idusuario idusuario, "
                        + "	his.tipo tipo, "
                        + "	his.operacion operacion, "
                        + "	his.estado_marca_descripcion estado_marca_descripcion, "
                        + "	his.observacion observacion, "
                        + "	his.ubicacion_descripcion ubicacion_descripcion, "
                        + "	his.seccion seccion, "
                        + "	his.gestion_renovacion gestion_renovacion, "
                        + "	his.descripcion descripcion, "
                        + "	his.descripcion_lista_productos descripcion_lista_productos, "
                        + "	his.fecha_operacion fecha_operacion, "
                        //                        + "	case when us.nombre is null then '' else us.nombre end "
                        //                        + "	|| ' ' || "
                        //                        + "	case when us.primer_apellido is null then '' else us.primer_apellido end "
                        //                        + "	|| ' '|| "
                        //                        + "	case when us.segundo_apellido is null then '' else us.segundo_apellido end "
                        //                        + "	:: text as usuario_nombre_completo, "
                        + "     usu.nombre as usuario_nombre_completo,"
                        + "	usu.login usuario, "
                        + "	his.estado estado "
                        + "from modhistorial his "
                        //                        + "join "
                        //                        + "usuario us "
                        //                        + "	on (us.idusuario = his.idusuario) "
                        + "join (select idusuario, login, string_agg(concat(nombre, ' ',primer_apellido, ' ',segundo_apellido), '; ') nombre \n"
                        + "from usuario "
                        + "group by 1) \n"
                        + "usu on (usu.idusuario = his.idusuario) "
                        + "where "
                        + "	idmodificacion = ? "
                        + "	and his.estado = 'AC' "
                        + "	order by fecha_operacion desc;"
                        + ""
                        + "";
                 
                listaHistorialResultado = jdbcTemplate.query(SQL, new HistorialResultadoMapper(), id);
                if (!listaHistorialResultado.isEmpty()) {
                    return listaHistorialResultado;
                }
                return new ArrayList<HistorialResultado>();
            }

            if (prefijo.equals(EnumPrefijoTablas.RENOVACION.getCodigo())) {
                SQL = "select "
                        + "	row_number () over (order by his.idhistorial desc) posicion, "
                        + "	his.idhistorial idhistorial, "
                        + "	his.idsolicitudrenovacion id, "
                        + "	his.idlogtrans idlogtrans, "
                        + "	his.idusuario idusuario, "
                        + "	his.tipo tipo, "
                        + "	his.operacion operacion, "
                        + "	his.estado_marca_descripcion estado_marca_descripcion, "
                        + "	his.observacion observacion, "
                        + "	his.ubicacion_descripcion ubicacion_descripcion, "
                        + "	his.seccion seccion, "
                        + "	his.gestion_renovacion gestion_renovacion, "
                        + "	his.descripcion descripcion, "
                        + "	his.descripcion_lista_productos descripcion_lista_productos, "
                        + "	his.fecha_operacion fecha_operacion, "
                        + "	case when us.nombre is null then '' else us.nombre end "
                        + "	|| ' ' || "
                        + "	case when us.primer_apellido is null then '' else us.primer_apellido end "
                        + "	|| ' '|| "
                        + "	case when us.segundo_apellido is null then '' else us.segundo_apellido end "
                        + "	:: text as usuario_nombre_completo, "
                        + "	us.login usuario, "
                        + "	his.estado estado "
                        + "from renhistorial his "
                        + "join "
                        + "usuario us "
                        + "	on (us.idusuario = his.idusuario) "
                        + "where "
                        + "	idsolicitudrenovacion = ? "
                        + "	and his.estado = 'AC' "
                        + "	order by fecha_operacion desc;"
                        + ""
                        + "";
                listaHistorialResultado = jdbcTemplate.query(SQL, new HistorialResultadoMapper(), id);
                if (!listaHistorialResultado.isEmpty()) {
                    return listaHistorialResultado;
                }
                return new ArrayList<HistorialResultado>();
            }

            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

}
