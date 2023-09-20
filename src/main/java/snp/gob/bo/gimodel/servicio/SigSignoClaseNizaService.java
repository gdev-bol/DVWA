/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.ClaseNiza;
import snp.gob.bo.gimodel.entidad.ObjetoEliminadoGenerico;
import snp.gob.bo.gimodel.entidad.SigSignoClaseNiza;
import snp.gob.bo.gimodel.solicitudes.entidades.SmSignoClaseNizas;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 26/08/2016
 */
public interface SigSignoClaseNizaService {

    public void setDataSource(DataSource dataSource);

    public SigSignoClaseNiza registrarSigSignoMarca(SigSignoClaseNiza sigSignoClaseNiza) throws Exception;

    
    /**
     * Metodo que obtiene el listado de las clases niza asociadas a sus
     * productos
     *
     * Creado: Eddy Valero Fecha: 26/09/2016
     *
     * @param idSignoMarca
     * @return List<SigSignoClaseNiza>
     * @throws java.lang.Exception
     */
    public List<SigSignoClaseNiza> obtenerListaSigSignoClazeNizaXIdSignoMarca(Long idSignoMarca) throws Exception;

    /**
     * Metodo que obtiene la posicion en la que se encuentre un
     * sigSignoClaseNiza en una lista
     *
     * Creado: Eddy Valero Fecha: 07/10/2016
     *
     * @param listaSigSignoClaseNiza
     * @param sigSignoClaseNiza
     * @return
     * @throws java.lang.Exception
     */
    public int encuentraPosicionListadoSigSignoClaseNiza(List<SigSignoClaseNiza> listaSigSignoClaseNiza,
            SigSignoClaseNiza sigSignoClaseNiza) throws Exception;

    /**
     * Metodo que realiza el crud de la tabla sigsignoclaseniza
     *
     * Creado: Eddy Valero Fecha: 10/10/2016
     *
     * @param parametro
     * @param sigSignoClaseNiza
     * @return
     * @throws java.lang.Exception
     */
    public SigSignoClaseNiza crudSigSignoClaseNiza(SigSignoClaseNiza sigSignoClaseNiza, int parametro) throws Exception;

    /**
     * Metodo que realiza la modificacion del listado enviado de
     * sigSignoClasesNiza
     *
     * Creado: Eddy Valero Fecha: 10/10/2016
     *
     * @param idSignoMarca
     * @param lstSignoClaseNiza
     * @throws java.lang.Exception
     */
    public void modificaListaSigSignoClaseNiza(Long idSignoMarca, List<SigSignoClaseNiza> lstSignoClaseNiza) throws Exception;
    
    /**
     * Metodo que realiza la modificacion del listado enviado de
     * sigSignoClasesNiza
     *
     * Creado:Placido Castro Fecha: 24/11/2017
     *
     * @param idSignoMarca
     * @param idLogTrans
     * @param listaSignoClaseNizasSipi
     * @throws java.lang.Exception
     */
    public void modificarListaSigSignoClaseNizaSubsanacion(Long idSignoMarca, Long idLogTrans, List <SmSignoClaseNizas> listaSignoClaseNizasSipi) throws Exception;
    
    /**
     * Metodo para obtener un determinado registro de sigSignoClaseNiza
     * sigSignoClasesNiza
     *
     * Creado: Eddy Valero Fecha: 03/12/2016
     *
     * @param idSignoClaseNiza
     * @return 
     * @throws java.lang.Exception
     */
    public SigSignoClaseNiza obtenerSigSignoClaseNiza(Long idSignoClaseNiza) throws Exception;
    
    /**
     * Metodo para obtener un determinado registro de sigSignoClaseNiza
     * sigSignoClasesNiza
     *
     * Creado: Eddy Valero Fecha: 03/12/2016
     *
     * @param idSignoMarca
     * @param criterioBusqueda
     * @return 
     * @throws java.lang.Exception
     */
    public ObjetoEliminadoGenerico obtenerRegistrosEliminadosSigSignoClaseNiza(Long idSignoMarca, String criterioBusqueda) throws Exception;
    /**
     * Metodo para obtener un determinado registro de sigSignoClaseNiza
     * sigSignoClasesNiza
     *
     * Creado: Eddy Valero Fecha: 03/12/2016
     *
     * @param idSignoMarca
     * @param numeroClaseNizaAnterior
     * @return 
     * @throws java.lang.Exception
     */
     SigSignoClaseNiza obtenerListaSigSignoClazeNizaXIdSignoMarcaNumeroNiza(Long idSignoMarca, int numeroClaseNizaAnterior) throws Exception;
      /**
     * Metodo para obtener un determinado registro de sigSignoClaseNiza
     * sigSignoClasesNiza
     *
     * Creado: Eddy Valero Fecha: 03/12/2016
     *
     * @param idSignoMarca
     * @param numeroClaseNizaAnterior
     * @return 
     * @throws java.lang.Exception
     */
    
    /**
     * Metodo para obtener un determinado registro de sigSignoClaseNiza
 sigSignoClasesNiza

 Creado: Eddy Valero Fecha: 03/12/2016
     * @param numeroNiza
     * @return
     * @throws java.lang.Exception
     */
    ClaseNiza obtenerClaseNizaporNumeroClaseNiza(int numeroNiza) throws Exception;

    

}
