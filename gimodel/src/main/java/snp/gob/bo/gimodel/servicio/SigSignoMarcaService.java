/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.pojo.HistorialRenovacionModificacionPojo;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI100;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI101;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI102;

/**
 *
 * @author eddy
 */
public interface SigSignoMarcaService {

    public void setDataSource(DataSource dataSource);
    //public SigSignoMarca RegistrarSMSignoMarca(SigSignoMarca sMSignoMarca);

    public List<SigSignoMarca> obtenerRegistrosSMSignoMarca();
    
    /**
     * Metodo que obtiene un sigsignomarca por idsignoMarca
     * 
     * Responsable: Eddy Valero Fecha: 31/10/2016
     *
     * @param idSignoMarca
     * @return SigSignoMarca
     * @throws java.lang.Exception
     * 
     */
    public SigSignoMarca obtenerSigSignoMarcaXidSignoMarca(Long idSignoMarca) throws Exception;
    
    public SigSignoMarca registrarSigSignoMarca(SigSignoMarca sigSignoMarca) throws Exception;
    
    

    /**
     * Metodo de busca un objeto SigSignoMarca por sm
     * 
     * Responsable: Susana Escobar Paz Fecha: 14/09/2016
     *
     * @param sm
     * @return SigSignoMarca
     * 
     */
    public SigSignoMarca listaSigSignoMarcaXSM(Long sm);

    /**
     * Metodo de busca un objeto SigSignoMarca por sm Susana Escobar Paz Fecha:
     * 14/09/2016
     *
     * @param publicacion
     * @return ************************************************
     */
    public SigSignoMarca listaSigSignoMarcaXPublicacion(Long publicacion);

    /**
     * Metodo de busca un objeto SigSignoMarca por sm Susana Escobar Paz Fecha:
     * 14/09/2016
     *
     * @param renovacion
     * @param serie
     * @return ************************************************
     */
    public SigSignoMarca listaSigSignoMarcaXRenovacion(Long renovacion, String serie);

    /**
     * Metodo de busca un objeto SigSignoMarca por sm Susana Escobar Paz Fecha:
     * 14/09/2016
     *
     * @param registro
     * @param serie
     * @param signo
     * @return ************************************************
     */
    public SigSignoMarca listaSigSignoMarcaXRegistro(Long registro, String serie, String signo);

    
    public SigSignoMarca listaSigSignoMarcaXRegistroyClaseNiza(Long registro, String serie, String signo,int clase);
    
    
    /**
     * Metodo de busca un objeto SigSignoMarca por sm Susana Escobar Paz Fecha:
     * 14/09/2016
     *
     * @param sm
     * @param publicacion
     * @param registro
     * @param serie
     * @param signo
     * @return ************************************************
     */
    public SigSignoMarca buscar_SigSignoMarca_sm_publicacion_registro_or(Long sm, Long publicacion, Long registro, String serie, String signo);
    
     /**
     * Metodo de busca un objeto SigSignoMarca por sm 
     * 
     * Creado: Ruben Ramirez Fecha: 11/10/2016
     * 
     * @param numeroFormulario
     * @return SigSignoMarca
     * @throws java.lang.Exception
     */
    public SigSignoMarca obtenerRegistroSigSignoMarcaXNumeroFormulario(String numeroFormulario) throws Exception;

    /**
     * Método crud de la tabla sigsignomarca
     *
     * Eddy Valero Kari Fecha: 08/10/2016
     *
     *
     * @param sigSignoMarca
     * @param parametro
     * @return SigSignoMarca
     * @throws java.lang.Exception
     */
    public SigSignoMarca crudSigSignoMarca(SigSignoMarca sigSignoMarca, int parametro) throws Exception;
    
    /**
     * Método crud de la tabla sigsignomarca
     *
     * Placido Castro Fecha: 12/10/2017
     *
     *
     * @param sigSignoMarca
     * @param formularioPI100
     * @param parametro
     * @return SigSignoMarca
     * @throws java.lang.Exception
     */
    public SigSignoMarca crudSigSignoMarcaExternoPI100(SigSignoMarca sigSignoMarca, FormularioPI100 formularioPI100, int parametro) throws Exception;
   
    /**
     * Método crud de la tabla sigsignomarca
     *
     * Placido Castro Fecha: 12/10/2017
     *
     *
     * @param sigSignoMarca
     * @param formularioPI101
     * @param parametro
     * @return SigSignoMarca
     * @throws java.lang.Exception
     */
    public SigSignoMarca crudSigSignoMarcaExternoPI101(SigSignoMarca sigSignoMarca, FormularioPI101 formularioPI101, int parametro) throws Exception;
    
    /**
     * Método crud de la tabla sigsignomarca
     *
     * Placido Castro Fecha: 12/10/2017
     *
     *
     * @param sigSignoMarca
     * @param formularioPI102
     * @param parametro
     * @return SigSignoMarca
     * @throws java.lang.Exception
     */
    public SigSignoMarca crudSigSignoMarcaExternoPI102(SigSignoMarca sigSignoMarca, FormularioPI102 formularioPI102, int parametro) throws Exception;
    
    /**
     * Metodo de busca un objeto SigSignoMarca por sm cuando no existe en tabla sigregistro
     * 
     * Creado: Susana Escobar Paz Fecha: 04/11/2016
     *
     * @param sm
     * @return SigSignoMarca
     * 
     */
    public SigSignoMarca lista_SigSignoMarca_SigRegistro(Long sm);
    
      
    /**
     * Metodo de busca un objeto SigSignoMarca por numero de formulario 
     * Creado por Chano Rojas Fecha:14/10/2016
     * @param numeroFormulario     
     * @return
     * ************************************************
     * @throws java.lang.Exception
     */
    
    SigSignoMarca buscarSignoMarca_NumeroFormulario(String numeroFormulario)throws Exception;
    
    /** Metodo para listar sigregistros con el numero y serie seleccionados, se utiliza para generar registros que no fueron generados 
     * automaticamente.
     *
     * Creado: Susana Escobar Paz Fecha: 12/12/2016
     * 
     * @param registro
     * @param serie
     * @return
     */
    public List<SigSignoMarca> listar_SigSignoMarca_registro(Long registro, String serie);
    /** Metodo para listar historial de renovaciones en signos
     * automaticamente.
     *
     * Creado: Chano Rojas  Fecha: 12/12/2016
     * 
     * @param signoMarca
     * @return
     */
     public List<HistorialRenovacionModificacionPojo> listaHistorialRenovacionesModificacion(SigSignoMarca signoMarca);
    /** Metodo para listar historial de renovaciones en signos
     * automaticamente.
     *
     * Creado: Chano Rojas  Fecha: 12/12/2016
     * 
     * @param registro
     * @param serie
     * @param signo
     * @param idClase
     * @return
     */
      SigSignoMarca listaSigSignoMarcaXRegistroyClaseNiza(Long registro, String serie, String signo,Long idClase);
     
}
