/*
 * To change this license header, choose License Headers in Project Properties.  
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.Oposicion;

/**
 *
 * @author Luis Angel Quispe Limachi
 */
public interface OposicionService {

    /**
     * Método que permite setear la conexion con dataSource
     *
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;

    /**
     * Metodo para guardar opomarcademandante Creado: Luis Quispe Fecha:
     * 28/09/2016
     *
     * @param oposicion
     * @return Oposicion
     * @throws java.lang.Exception
     */
    public Oposicion guardaOpodemandante(Oposicion oposicion) throws Exception;

    /**
     * Metodo para guardar oposicion Creado: Luis Quispe Fecha: 28/09/2016
     *
     * @param opox
     * @return Oposicion
     * @throws java.lang.Exception
     */
    public Oposicion guardaroposicion(Oposicion opox) throws Exception;

    /**
     * Metodo para obtener la lista de oposicion por el numero de oposicion
     * Creado: Luis Quispe Fecha: 30/09/2016
     *
     * @param nroopo
     * @return List<Oposicion>
     * @throws java.lang.Exception
     */
    public List<Oposicion> obtenerListadoOposicion(Long nroopo) throws Exception;

    /**
     * Metodo para obtener el nombre de la marca demandante por el idoposicion
     * creado: Luis Quispe Fecha: 30/09/2016
     *
     * @param numeroopo
     * @return String
     * @throws java.lang.Exception
     */
    public String obtenernombremarcaxidopo(Long numeroopo) throws Exception;

    /**
     * Metodo para obtener el nombre de la marca demandada por el idoposicion
     * creado: Luis Quispe Fecha: 30/09/2016
     *
     * @param numeroopo
     * @return String
     * @throws java.lang.Exception
     */
    public String obtenernombremarcaxidopo2(Long numeroopo) throws Exception;

    /**
     * Metodo para modificar oposicion creado: Luis Quispe Fecha: 05/10/2016
     *
     * @param opomodi
     * @return Oposicion
     * @throws java.lang.Exception
     */
    public Oposicion modificaOposicion(Oposicion opomodi) throws Exception;

    /**
     * Metodo para encontrar la clave primaria por numeropublicaion y el numero
     * de orden Creado: Luis Quispe Fecha: 08/10/2016
     *
     * @param intronropubli
     * @param introorden
     * @return Long
     * @throws java.lang.Exception
     */
    public Long encuentraclaveprin(Long intronropubli, Integer introorden) throws Exception;

    /**
     * Metodo para verificar que el numero de oposicion no existe Creado: Luis
     * Quispe Fecha: 10/10/2016
     *
     * @param numeropubli
     * @return Boolean
     * @throws java.lang.Exception
     */
    public Boolean verificarexi(Long numeropubli) throws Exception;
    
    /**
     * Metodo para verificar que el numero de oposicion no existe Creado: Luis
     * Quispe Fecha: 10/10/2016
     *
     * @param numeropubli
     * @return Boolean
     * @throws java.lang.Exception
     */
    public Boolean verificarexi2(Long numeropubli,Integer nroopo) throws Exception;

    /**
     * Metodo para extraer el ultimo numero de orden Creado: Luis Quispe
     * Fecha:11/10/2016
     *
     * @param numeropubli
     * @return Integer
     * @throws java.lang.Exception
     */
    public Integer extraerultimonorden(Long numeropubli) throws Exception;

    /**
     * Metodo para encontrar el numero de orden por el numero de publicacion
     * Creado: Luis Quispe Fecha: 12/10/2016
     *
     * @param numeropublic
     * @return Integer
     * @throws java.lang.Exception
     */
    public Integer extranroorden(Long numeropublic) throws Exception;

    /**
     * Metodo para eliminar oposicion Creado: Luis Quispe Fecha: 12/10/2016
     *
     * @param opoeli
     * @return String
     * @throws java.lang.Exception
     */
    public String eliminaOposicion(Long opoeli) throws Exception;

    /**
     * Metodo para obtener la lista de actividades de la tabla actividad
     * Creado:Luis Quispe Fecha: 15/10/2016
     *
     * @param nroopo
     * @param tipdmte
     * @return List<Oposicion>
     * @throws java.lang.Exception
     */
    public List<Oposicion> obtnroopoxnroydmte(Long nroopo, String tipdmte) throws Exception;

    /**
     * Metodo para obtener oposicion por numero oposicion
     * ytiposolisitanteapoderado Creado: Luis Quispe Fecha: 15/10/2016
     *
     * @param nroopo
     * @param tipdmdo
     * @return List<Oposicion>
     * @throws java.lang.Exception
     */
    public List<Oposicion> obtnroopoxnroydmdo(Long nroopo, String tipdmdo) throws Exception;

    /**
     * Metodo para obtener oposicion por numero oposicion y
     * tiposoliapo(demandado) Creado: Luis Quispe Fecha: 15/10/2016
     *
     * @param nroopo
     * @param tipdmte
     * @return List<Oposicion>
     * @throws java.lang.Exception
     */
    public List<Oposicion> obtnlistopoxregisydmte(Integer nroopo, String tipdmte) throws Exception;

    /**
     * Metodo para obtener oposicion por numero oposicion y
     * tiposoliapo(demandante) Creado: Luis Quispe Fecha: 17/10/2016
     *
     * @param nrosm
     * @return List<Oposicion>
     * @throws java.lang.Exception
     */
    public List<Oposicion> obtnlistopoxexpediydmte(Long nrosm) throws Exception;

    /**
     * Metodo para obtener oposicion por numero oposicion y nombre da la marca
     * demandante Creado: Luis Quispe Fecha: 17/10/2016
     *
     * @param marcax
     * @param tipdmte
     * @return List<Oposicion>
     * @throws java.lang.Exception
     */
    public List<Oposicion> obtnroopoxmarcaydmte(String marcax, String tipdmte) throws Exception;

    /**
     * Metodo para obtener oposicion por numero oposicion y nombre da la marca
     * demandada Creado: Luis Quispe Fecha: 17/10/2016
     *
     * @param marcax
     * @param tipdmdo
     * @return List<Oposicion>
     * @throws java.lang.Exception
     */
    public List<Oposicion> obtnroopoxmarcaydmdo(String marcax, String tipdmdo) throws Exception;

    /**
     * Metodo para obtener oposicion por la marcademandante Creado: Luis Quispe
     * Fecha: 17/10/2016
     *
     * @param marcadmte
     * @return List<Oposicion>
     * @throws java.lang.Exception
     */
    public List<Oposicion> obtlistopoxnomdmte(String marcadmte) throws Exception;

    /**
     * Metodo para obtener oposicion nombre del demandante Creado: Luis Quispe
     * Fecha: 17/10/2016
     *
     * @param nombreapo
     * @param tipdmte
     * @return List<Oposicion>
     * @throws java.lang.Exception
     */
    public List<Oposicion> obtlistopoxnomdmte(String nombreapo, String tipdmte) throws Exception;

    /**
     * Metodo para obtener oposicion nombre del demandado Creado: Luis Quispe
     * Fecha: 18/10/2016
     *
     * @param nombreapo
     * @param tipdmte
     * @return List<Oposicion>
     * @throws java.lang.Exception
     */
    public List<Oposicion> obtlistopoxnomdmdo(String nombreapo, String tipdmte) throws Exception;

    /**
     * Metodo para listado por fecha de presentacion Creado: Luis Quispe Fecha:
     * 17/10/2016
     *
     * @param fechapre
     * @return List<Oposicion>
     * @throws java.lang.Exception
     */
    public List<Oposicion> obtlistopoxfechapres(Date fechapre) throws Exception;

    /**
     * Metodo obtener el numero de registro por el numero de oposicion Creado:
     * Luis Quispe Fecha:17/10/2016
     *
     * @param nrooposic
     * @return Integer
     * @throws java.lang.Exception
     */
    public Integer obtregistroxnumeroopo(Long nrooposic) throws Exception;

    /**
     * Metodo obtener el numero de serie por el numero de oposicion Creado: Luis
     * Quispe Fecha:17/10/2016
     *
     * @param nrooposic
     * @return String
     * @throws java.lang.Exception
     */
    public String obtrseriexnumeroopo(Long nrooposic) throws Exception;

    /**
     * Metodo obtener el numero de expediente por el numero de oposicion Creado:
     * Luis Quispe Fecha:17/10/2016
     *
     * @param nrooposic
     * @return String
     * @throws java.lang.Exception
     */
    public Long obtexpedientexnroopo(Long nrooposic) throws Exception;

    /**
     * Metodo obtener el apderado de demandante por el numero de oposicion
     * Creado: Luis Quispe Fecha:17/10/2016
     *
     * @param nrooposic
     * @return String
     * @throws java.lang.Exception
     */
    public String obtapoderadodmtexnroopo(Long nrooposic) throws Exception;

    /**
     * Metodo obtener el apderado de demandando por el numero de oposicion
     * Creado: Luis Quispe Fecha:19/10/2016
     *
     * @param nrooposic
     * @return String
     * @throws java.lang.Exception
     */
    public String obtapoderadodmdoxnroopo(Long nrooposic) throws Exception;

    /**
     * Metodo obtener el apderado de demandando por el numero de oposicion
     * Creado: Luis Quispe Fecha:19/10/2016
     *
     * @param nroestado
     * @return String
     * @throws java.lang.Exception
     */
    public String obtestadoxnroopo(Long nroestado) throws Exception;

    /**
     * Metodo obtener el estado oposicion por el numero de oposicion Creado:
     * Luis Quispe Fecha:19/10/2016
     *
     * @param idoposicion
     * @return String
     * @throws java.lang.Exception
     */
    public String obtienestadoopo(Long idoposicion) throws Exception;

    /**
     * Metodo obtener el nombre de apoderado oposicion por el numero de
     * oposicion Creado: Luis Quispe Fecha:21/10/2016
     *
     * @param idoposicion
     * @return String
     * @throws java.lang.Exception
     */
    public String obtienenombreapoderado(Long idoposicion) throws Exception;

    /**
     * Metodo obtener observacion de la oposicion oposicion por el numero de
     * oposicion Creado: Luis Quispe Fecha:21/10/2016
     *
     * @param idoposicion
     * @return String
     * @throws java.lang.Exception
     */
    public String obtienerobservacionopo(Long idoposicion) throws Exception;

    /**
     * Metodo para obtener la lista de oposicion por el numero de oposicion
     * Creado:Luis Quispe Fecha: 03/11/2016
     *
     * @param nroopo
     * @return List<Oposicion>
     * @throws java.lang.Exception
     */
    public List<Oposicion> obtoposicionxnro_opo(Long nroopo) throws Exception;

    /**
     * Metodo para obtener cuantos valores se tiene en total el numero de
     * publicacion de oposicion Creado:Luis Quispe Fecha: 03/11/2016
     *
     * @param nroopo
     * @return List<Oposicion>
     * @throws java.lang.Exception
     */
    public Integer obttotaloposicionesxnro_opo(Long nroopo) throws Exception;

    /**
     * Metodo para extraer un objeto tipo oposicion Creado:Luis Quispe Fecha:
     * 09/11/2016
     *
     * @param idoposicionpri
     * @param nroopo
     * @return Oposicion
     * @throws java.lang.Exception
     */
    public Oposicion obtoposicionxnroopo(Long idoposicionpri) throws Exception;

    /**
     * Metodo para encontrar la Fecha de Presentacion por idoposicion
     *
     * Luis Quispe Fecha: 08/10/2016
     *
     * @param idoposicion
     * @return Long
     * @throws java.lang.Exception
     */
    public Date encuentrafechapres(Long idoposicion) throws Exception;
    
    /**
     * Metodo para verificar que el numero de Registro exista en oposicion:
     * 
     * Luis Quispe 
     * 
     * Fecha: 02/01/2016
     *
     * @param dmtereg
     * @param dmteserie
     * @return Boolean
     * @throws java.lang.Exception
     */
    public Boolean verificadmtexregistro(Integer dmtereg,String dmteserie) throws Exception;
     
    /**
     * Metodo para verificar que el numero de SM exista en oposicion:
     * 
     * Luis Quispe 
     * 
     * Fecha: 02/01/2016
     *
     * @param dmtesm
     * @return Boolean
     * @throws java.lang.Exception
     */
    public Boolean verificadmtexsm(Long dmtesm) throws Exception;
    /**
     * Metodo para verificar que el numero de SM exista en oposicion:
     * 
     * Luis Quispe 
     * 
     * Fecha: 02/01/2016
     *
     * @param dmtepubli
     * @return Boolean
     * @throws java.lang.Exception
     */
    public Boolean verificadmtexnropubli(Integer dmtepubli) throws Exception;
/**
     * Metodo para complete el numero de SM el numero de expediente y su extencion
     * 
     * Luis Quispe 
     * 
     * Fecha: 16/02/2016
     *
     * @param nroexpediente
     * @return Boolean
     * @throws java.lang.Exception
     */
    public String completasmxnroexp(Long nroexpediente) throws Exception;
    
    /**
     * Metodo para extraer un objeto tipo oposicion por numerooformulario
     * Creado: Ruben Ramirez 
     * Fecha: 20/07/2017
     * @param numeroformulario
     * @return Oposicion
     * @throws java.lang.Exception
     */
    public Oposicion obtoposicionxnroformulario(Long numeroformulario) throws Exception;
    /**
     * Metodo para extraerun objeto tipo oposicion por numerooformulario, no existencia devuelve nuevo objeto.
     * Creado: chano Rojas
     * Fecha: 20/07/2017
     * @param numeroformulario
     * @return Oposicion
     * @throws java.lang.Exception
     */
      public Oposicion obtoposicionxnroformularioRecaudaciones(Long numeroformulario) throws Exception;
    
    /**
     * Metodo para obtener la lista de oposicion por gaceta
     * Creado: Ruben Ramirez
     * Fecha: 26/07/2017
     *
     * @param gaceta
     * @return 
     * @throws java.lang.Exception
     */
    public List<Oposicion> obtenerListadoOposicionXgaceta(Integer gaceta) throws Exception;
}
