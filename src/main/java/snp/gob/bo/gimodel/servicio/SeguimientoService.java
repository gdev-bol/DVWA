/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.Seguimiento;

/**
 * Entidad responsable de la Tabla Seguimiento
 *
 * @author Eddy Valero
 * @see SigSeguimientoMapper
 * @version 1.0, 08/09/2016
 */
public interface SeguimientoService {

    public void setDataSource(DataSource dataSource);

    /**
     *
     * @param sigSeguimiento
     * @return
     * @throws Exception
     */
    public Seguimiento guardarRegistroSigSeguimiento(Seguimiento sigSeguimiento) throws Exception;

    /**
     * metodo para listar seguimiento segun id, etapa y area Signos
     *
     * Creado: Susana Escobar Fecha: 16/03/2017
     *
     * @param idSignoMarca
     * @param etapa
     * @return
     * @throws java.lang.Exception
     */
    public Seguimiento listar_SigSeguimiento_etapa_iniciado(Long idSignoMarca, String etapa) throws Exception;

    public Seguimiento obtenerRegistroSigSeguimientoPorCodigoSM(Long codigoSM, String etapa, String prefijo) throws Exception;

    /**
     * metodo para guardar, modificar y listar registros en tablas
     * sigseguimiento, modseguimiento, renseguimiento
     *
     * Creado: Susana Escobar Fecha: 03/10/2016
     *
     * @param seguimiento
     * @param prefijo
     * @param operacion
     * @return
     */
    public Seguimiento guardar_modificar_listar_Seguimiento(Seguimiento seguimiento, String prefijo, Integer operacion);

    /**
     * metodo para listar registros de tablas sigseguimiento, modseguimiento,
     * renseguimiento por id
     *
     * Creado: Susana Escobar Fecha: 04/10/2016
     *
     * @param prefijo
     * @param idSignoMarca
     * @return
     */
    public List<Seguimiento> lista_SeguimientoXid(String prefijo, Long idSignoMarca);

    /**
     * metodo para obtener el ultimo seguimiento por prefijo area, id y etapa,
     *
     * Creado: Susana Escobar Fecha: 05/10/2016
     *
     * @param prefijo
     * @param id
     * @param etapa
     * @return
     */
    public Seguimiento ultimo_Seguimiento_etapa(String prefijo, Long id, String etapa);

    /**
     * metodo guardar y modificar un registro de seguimiento em Modulos Signos, Modificaciones y Renovaciones
     *
     * Creado: Susana Escobar Fecha: 05/10/2016
     * Modificado: Susana Escobar Paz Fecha: 06/09/2017
     *
     * @param seguimiento
     * @param prefijo
     * @return
     */
    public Seguimiento guardar_modificar_Seguimiento_etapa(Seguimiento seguimiento, String prefijo);

    /**
     * metodo obtiee dias laborales tomand en cuenta la tabla noalboral
     *
     * Creado: Levi Laura Fecha: 12/12/2016
     *
     * @param fechaIni
     * @param fechaFin
     *
     * @param idRegional
     * @return
     * @throws java.lang.Exception
     */
    public int diasLaboralesSigno(Date fechaIni, Date fechaFin, Long idRegional) throws Exception;

    /**
     * metodo registra en la tabla sigseguiiento tomando en cuenta los dias
     * laborales
     *
     * Creado: Levi Laura Fecha: 12/12/2016
     *
     * @param id
     * @param idUsuario
     * @param idLogTrans
     * @param codigoSM
     * @param etapa
     * @param fechaRecep
     * @param fechaFin
     * @param obs
     * @param estado
     * @param plazo_limite
     * @return
     * @throws java.lang.Exception
     */
    public Seguimiento registraSeguimientoSignos(Long id, Long idUsuario, Long idLogTrans, Long codigoSM, String etapa,
            Date fechaRecep, Date fechaFin, String obs, String estado, int plazo_limite) throws Exception;

    /**
     * metodo que lista el registro de seguimiento especificamente para los
     * casos de signos
     *
     * Creado: Levi Laura Fecha: 12/12/2016
     *
     * @param sm
     * @param etapa
     * @return
     * @throws java.lang.Exception
     */
    public List<Seguimiento> listaSeguimientoXSM(Long sm, String etapa) throws Exception;

    /**
     * metodo que lista seguimientos en forma descendente segun idsignomarca y
     * su etapa, est para agarrrar el ultimo
     *
     * Creado: Levi Laura Fecha: 12/12/2016
     *
     * @param idSignoMarca
     * @param etapa
     * @return
     * @throws java.lang.Exception
     */
    public List<Seguimiento> listaSeguimientoXIdsegEtapaUltMov(Long idSignoMarca, String etapa) throws Exception;

    /**
     * metodo que lista seguimientos en forma descendente segun idsignomarca y
     * su etapa, est para agarrrar el ultimo
     *
     * Creado: Levi Laura Fecha: 12/12/2016
     *
     * @param idModificacion
     * @param etapa
     * @return
     * @throws java.lang.Exception
     */
    public List<Seguimiento> listaSeguimientoXIdsegEtapaModUltMov(Long idModificacion, String etapa) throws Exception;

    /**
     * metodo que lista seguimientos en forma descendente segun idsignomarca y
     * su etapa, est para agarrrar el ultimo Creado: Levi Laura Fecha:
     * 12/12/2016
     *
     * @param idRenovacion
     * @param etapa
     * @return
     * @throws java.lang.Exception
     */
    public List<Seguimiento> listaSeguimientoXIdsegEtapaRenUltMov(Long idRenovacion, String etapa) throws Exception;

    /**
     * metodo que lista los seguimietnos iguales a la etapa insertada com
     * oparametro de ingreso
     *
     * Creado: Levi Laura Fecha: 12/12/2016
     *
     * @param idSignoMarca
     * @param etapa
     * @param idSeguimiento
     * @return
     * @throws java.lang.Exception
     */
    public List<Seguimiento> listaSeguimientoUltimoXIdsignoEtapaUltMov(Long idSignoMarca, String etapa, Long idSeguimiento) throws Exception;

    /**
     * metodo verifica si el sm existe en la lista de seguimiento
     *
     * Creado: Luis Angel Fecha: 10/04/2016
     *
     * @param sm
     * @return
     * @throws java.lang.Exception
     */
    public List<Seguimiento> verificaseguimientosm(Long sm) throws Exception;

    /**
     * metodo verifica si el numero publicacion existe en la lista de
     * seguimiento
     *
     * Creado: Luis Angel Fecha: 10/04/2016
     *
     * @param nropubli
     * @return
     * @throws java.lang.Exception
     */
    public List<Seguimiento> verificaseguimientopub(Long nropubli) throws Exception;

    /**
     * metodo verifica nro de registro y registro existe en la lista de
     * seguimiento
     *
     * Creado: Luis Angel Fecha: 10/04/2016
     *
     * @param nroreg
     * @param regis
     * @return
     * @throws java.lang.Exception
     */
    public List<Seguimiento> verificaseguimientoreg(Long nroreg, String regis) throws Exception;

    /**
     * metodo que lista los seguimietnos iguales a la etapa insertada com
     * oparametro de ingreso
     *
     * Creado: Levi Laura Fecha: 21/07/2017
     *
     * @param idSignoMarca
     * @param etapa
     * @return
     * @throws java.lang.Exception
     */
    public List<Seguimiento> listaSeguiUltimoXIdsignoEtapaUltMov(Long idSignoMarca, String etapa) throws Exception;

    /**
     * metodo que lista el penultimo seguimiento dado el idseguimiento
     *
     * Creado: Levi Laura Fecha: 21/07/2017
     *
     * @param idSignoMarca
     *
     * @return
     * @throws java.lang.Exception
     */
    public List<Seguimiento> listaPenultSegui_idSigno(Long idSignoMarca) throws Exception;

    /**
     * metodo para obtener el ultimo seguimiento por prefijo area, id,
     *
     * Creado: Susana Escobar Fecha: 04/08/2017
     *
     * @param prefijo
     * @param id
     * @return
     */
    public Seguimiento lista_seguimiento_ultimo(String prefijo, Long id);

    /**
     * Metodo para obtener la sumatoria del campo plazo_real de las tablas de
     * segimiento en Signos, Modificaciones y Renovaciones
     *
     * Creado: Susana Escobar Fecha: 11/09/2017
     *
     * @param prefijo
     * @param id
     * @return
     */
    public Long conteoPLazoReal(String prefijo, Long id);

    /**
     * Metodo para obtener la sumatoria del campo plazo_limite de las tablas de
     * segimiento en Signos, Modificaciones y Renovaciones
     *
     * Creado: Susana Escobar Fecha: 11/09/2017
     *
     * @param prefijo
     * @param id
     * @return
     */
    public Integer conteoPlazoLimite(String prefijo, Long id);

    /**
     * Metodo para obtener la sumatoria del campo total_tiempo acumulada en el
     * ultimo registros de las tablas de segimiento en Signos, Modificaciones y
     * Renovaciones en caso de ser 0 o null se realiza un conteo de plazo real y
     * se actualiza, en caso de ser solo un registro se actualiza los dias
     * habiles transcurridos.
     *
     * Creado: Susana Escobar Fecha: 08/09/2017
     *
     * @param lstSeguimiento
     * @param prefijo
     * @param idregional
     * @return
     */
    public Long conteoTotalTiempo(List<Seguimiento> lstSeguimiento, String prefijo, Long idregional);

    /*     
     * Metodo para obtener el conteo de dias sin actividad entre etapas, se utiliza en los modulos de seguimiento de Signos, Modificaciones y  Renovaciones
     *    
     * @param fechaFin
     * @param prefijo
     * @param id
     * @param idRegional
     * @return
     * @throws Exception
     */
    public int cuentaDiasPasivo(Date fechaFin, String prefijo, Long id, Long idRegional) throws Exception;

     /**
     * Metodo para obtener la sumatoria del campo tiempo_pasivo de las tablas de
     * segimiento en Signos, Modificaciones y Renovaciones
     *
     * Creado: Susana Escobar Fecha: 12/09/2017
     *
     * @param prefijo
     * @param id
     * @return
     */
    public Integer conteoTiempoPasivo(String prefijo, Long id);

    public List<Seguimiento> listaSeguiUltimoXIdrenoEtapaUltMov(Long idsolicitudrenovacion, String etapa) throws Exception;

    public List<Seguimiento> listaSeguiUltimoXIdmodiEtapaUltMov(Long idmodificacion, String etapa) throws Exception;
    
      /**
     * metodo para obtener el ultimo seguimiento por prefijo area, id y etapa, si el seguimiento existe finalizado devuelve null,
     * si el seguimiento no existe devuelve null, sis existe iniciado devuelve valor.
     *
     * Creado: Susana Escobar Fecha: 05/10/2016
     *
     * @param prefijo
     * @param id
     * @param etapa
     * @return
     */
  //  public Seguimiento verifica_Seguimiento_etapa(String prefijo, Long id, String etapa);
    
    public List<Seguimiento> listaSeguimientoXIdSignomarca(Long idSignoMarca) throws Exception;
     /**
     * metodo para obtener la cantidad de repeticiones de una misma etapa. Por ej: si EXFM se repite mas  deun a vez para un tramite se mostrara 2
     *
     * Creado: Levi Laura Fecha: 27/10/2016
     *
     * @param prefijo
     * @param id
     * @param etapa
     * @return
     */
    
    public Integer obtieneCantidadEtapa(String prefijo,String etapa, Long id) throws Exception;
}
