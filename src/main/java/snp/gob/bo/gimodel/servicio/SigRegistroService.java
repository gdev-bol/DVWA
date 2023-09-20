/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.BusquedaSigRegistro;
import snp.gob.bo.gimodel.entidad.SigRegistro;

/**
 *
 * @author susana
 */
public interface SigRegistroService {

    /**
     * Método que permite setear la conexion con dataSource
     *
     * Creado: Susana Escobar Paz Fecha: 29/10/2016
     *
     * @param dataSource
     */
    public void setDataSource(DataSource dataSource);

    /**
     * Metodo para guardar, modificar y listar crear registro en tabla
     * sigregistro
     *
     * Creado: Susana Escobar Fecha: 29/10/2016
     *
     * @param sigRegistro
     * @param operacion
     * @return
     */
    public SigRegistro guardar_modificar_listar_SigRegistro(SigRegistro sigRegistro, Integer operacion);

    /**
     * Metodo para listar registro filtrado por numero y serie
     *
     * Creado: Susana Escobar Fecha: 03/11/2016
     *
     * @param numero
     * @param serie
     * @return
     */
    public SigRegistro listar_sigRegistro_numero(Long numero, String serie);

    /**
     * Metodo para listar registro por filtros: denegado, fecha registro, tipo marca, titular o signo
     *
     * Creado: Susana Escobar Fecha: 05/11/2016
     *
     * @param filtro
     * @param fecha
     * @param texto
     * @return
     */
    public List<SigRegistro> lista_SigRegistro_filtro(String filtro, Date fecha,String texto);
    
    /**
     * Metodo que devuelve dos valores booleanos, sobre la existencia de numero de registro, serie, numero de resolucion y serie
     * se utiliza para validar existencia de datos antes de asignar numero de registro.
     * 
     * Creado: Susana Escobar Fecha: 09/12/2016
     *
     * @param numero
     * @param serie
     * @param resolucion
     * @param gestion
     * @return
     */
    public Boolean[] verificaRegistroResolucionExistente(Long numero, String serie, Long resolucion, Integer gestion);
    
    /**
     * Metodo para actualizar datos de registro en tabla sighistorial
     * 
     * Creado: Susana Escobar Fecha: 09/12/2016
     *
     * @param registro
     * @param idusuario
     * @param estado
     * @param ubicacion
     */
    public void historial_SigRegistro_Signos(SigRegistro registro, Long idusuario, String estado, String ubicacion);
    
    /**
     * Metodo para guardar datos del seguimiento (control de calidad) en tabla sigseguimiento
     * 
     * Creado: Susana Escobar Fecha: 09/12/2016
     *
     * @param sm
     * @param idusuario
     */
    public void seguimiento_SigRegistro_Signos(Long sm, Long idusuario);
    
    /**
     * Metodo para listar sigregistro filtrado por idsignomarca
     * 
     * Creado: Susana Escobar Fecha: 09/12/2016
     *
     * @param idsignomarca
     * @return
     */
    public SigRegistro listar_sigRegistro_idsignomarca(Long idsignomarca);
    
    /**
     * Metodo para listar sigregistro filtrado por la gestion y el mes de la fecha de ingreso
     * 
     * Creado: Ruben Ramirez Fecha: 04/01/2017
     *
     * @param gestion
     * @param mes
     * @return
     */
    public List<SigRegistro>  listar_sigRegistro_gestion_mes(int gestion,int mes);
    
    /**
     * Metodo para listar sigregistro filtrado por la gestion de la fecha de ingreso
     * 
     * Creado: Ruben Ramirez Fecha: 04/01/2017
     *
     * @param gestion
     * @return
     */
    public List<SigRegistro>  listar_sigRegistro_gestion(int gestion);
    
    /**
     *
     * @param fechaInicio
     * @param fechaFin
     * @return
     */
    public  List<BusquedaSigRegistro> lista_SigRegistro_avanzada(Date fechaInicio, Date fechaFin);
    
    
    /**
     * Metodo para listar las fechas de registro de sigregistro filtrado por una fecha de inicio y fin.
     * 
     * Creado: Ruben Ramirez Fecha: 04/01/2017
     *
     * @param fechaInicio
     * @param fechaFin
     * @return
     */
    public List<Date> listadoFechasRegistro(Date fechaInicio, Date fechaFin);
    
     /**
     * Metodo para listar el ultimo numero de registro asignado segun la gestion de la fecha del sistema
     * 
     * Creado:Susana Escobar Paz
     * 
     * Fecha: 21/01/2017
     *
     * @return
     */
    public Long lista_UltimoRegistro_gestion();
}
