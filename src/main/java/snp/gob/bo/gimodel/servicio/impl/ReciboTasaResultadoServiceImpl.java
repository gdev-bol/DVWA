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
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.ReciboTasaResultado;
import snp.gob.bo.gimodel.mapper.ReciboTasaResultadoMapper;
import snp.gob.bo.gimodel.servicio.ReciboTasaResultadoService;

/**
 *
 * @author Ruben Ramirez
 * @see ReciboTasaResultado
 * @see ReciboTasaResultadoServiceImpl
 * @version 1.0, 10/05/2017
 */
@Service("reciboTasaResultadoService")
public class ReciboTasaResultadoServiceImpl implements ReciboTasaResultadoService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
    public List<ReciboTasaResultado> listaReciboResultadoFecha(Long id, int mes, int gestion) throws Exception {
        try {
            String SQL = "select to_char(fecha_emision_recibo, 'dd/MM/yyyy') as fecha, count(*) as cantidad, sum(monto) as total\n" +
                        "from recibo\n" +
                        "where EXTRACT(MONTH FROM fecha_emision_recibo) = "+mes+" and EXTRACT(YEAR FROM fecha_emision_recibo) = "+gestion+" and estado_recibo = 'REMI' and idtasa = "+id +
                        "group by to_char(fecha_emision_recibo, 'dd/MM/yyyy')\n" +
                        "order by to_char(fecha_emision_recibo, 'dd/MM/yyyy')";
            List<ReciboTasaResultado> listaRecibo = jdbcTemplate.query(SQL, new ReciboTasaResultadoMapper());
            if (!listaRecibo.isEmpty()) {
                return listaRecibo;
            }
        } catch (Exception e) {
            throw e;
        }
        return Collections.EMPTY_LIST;
    }

}
