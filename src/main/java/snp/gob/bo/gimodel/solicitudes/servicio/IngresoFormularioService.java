/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.solicitudes.servicio;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.ElementoFormularioTramite;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI100;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI101;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI102;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI103;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI104;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI105;

/**
 *
 * @author Eddy Valero
 * @see FormularioPI100
 * @see IngresoFormularioServiceImpl
 * @version 1.0, 25/08/2016
 */
public interface IngresoFormularioService {

    /**
     * Método que permite setear la conexion con dataSource
     * -------------------------------------------------------------------------
     * Creado: Eddy Valero Fecha: 10/09/2016
     *
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;

    /**
     * Método que permite guardar un registro de formulario
     * -------------------------------------------------------------------------
     * Creado: Eddy Valero Fecha: 10/09/2016
     *
     * @param fechaIngreso
     * @param formularioPI100
     * @param plantillaVentanilla
     * @param idUsuario
     * @param codigoSM
     * @param oficina
     * @param observacion
     * @return HashMap, retornar los valores de la tabla sigseguimiento y el id
     * asociado
     * @throws java.lang.Exception
     *
     * -------------------------------------------------------------------------
     * Modificado: Eddy Valero Fecha: 02/02/2017
     *
     * setear el genero(sexo) del apoderado en la solicitud.
     
     * -------------------------------------------------------------------------
     * Modificado: Eddy Valero Fecha: 25/02/2017
     *
     * Agregar el parametro fechaIngreso, para el ingreso de los tramites por
     * ventanilla
     * 
     * -------------------------------------------------------------------------
     * Modificado: Eddy Valero Fecha: 24/05/2017
     *
     * Agregar el registro en la tabla UsuarioTramite, para relacionar el tramite con el id
     * del usuario.
     * 
     */
    public HashMap guardarFormularioPI100(Date fechaIngreso, FormularioPI100 formularioPI100, List<ElementoFormularioTramite> plantillaVentanilla, Long idUsuario, Long codigoSM, String oficina, String observacion) throws Exception;

    /**
     * Método que permite guardar un registro de formulario PI101
     * -------------------------------------------------------------------------
     * Creado: Eddy Valero Fecha: 10/09/2016
     *
     * @param fechaIngreso
     * @param formularioPI101
     * @param plantillaVentanilla
     * @param idUsuario
     * @param codigoSM
     * @param oficina
     * @param observacion
     * @return HashMap, retornar los valores de la tabla sigseguimiento y el id
     * asociado
     * @throws java.lang.Exception
     *
     * -------------------------------------------------------------------------
     * Modificado: Eddy Valero Fecha: 02/02/2017
     *
     * setear el genero(sexo) del apoderado en la solicitud.
     * -------------------------------------------------------------------------
     * Modificado: Eddy Valero Fecha: 25/02/2017
     *
     * Agregar el parametro fechaIngreso, para el ingreso de los tramites por
     * ventanilla
     * 
     * -------------------------------------------------------------------------
     * Modificado: Eddy Valero Fecha: 24/05/2017
     *
     * Agregar el registro en la tabla UsuarioTramite, para relacionar el
     * tramite con el id del usuario.
     * 
     */
    public HashMap guardarFormularioPI101(Date fechaIngreso, FormularioPI101 formularioPI101, List<ElementoFormularioTramite> plantillaVentanilla, Long idUsuario, Long codigoSM, String oficina, String observacion) throws Exception;

    /**
     * Método que permite guardar un registro de formulario PI102
     * -------------------------------------------------------------------------
     * Creado: Eddy Valero Fecha: 10/09/2016
     *
     * @param fechaIngreso
     * @param formularioPI102
     * @param plantillaVentanilla
     * @param idUsuario
     * @param codigoSM
     * @param oficina
     * @param observacion
     * @return HashMap, retornar los valores de la tabla sigseguimiento y el id
     * asociado
     * @throws java.lang.Exception
     *
     * -------------------------------------------------------------------------
     * Modificado: Eddy Valero Fecha: 02/02/2017
     *
     * setear el genero(sexo) del apoderado en la solicitud.
     * -------------------------------------------------------------------------
     * Modificado: Eddy Valero Fecha: 25/02/2017
     *
     * Agregar el parametro fechaIngreso, para el ingreso de los tramites por
     * ventanilla
     */
    public HashMap guardarFormularioPI102(Date fechaIngreso, FormularioPI102 formularioPI102, List<ElementoFormularioTramite> plantillaVentanilla, Long idUsuario, Long codigoSM, String oficina, String observacion) throws Exception;

    /**
     * Método que permite guardar un registro de formulario PI103
     * -------------------------------------------------------------------------
     * Creado: Eddy Valero Fecha: 27/10/2016
     *
     * @param fechaIngreso
     * @param formularioPI103
     * @param plantillaVentanilla
     * @param idUsuario
     * @param codigoSM
     * @param oficina
     * @param observacion
     * @param numero
     * @param gestion
     * @param siglaModificacion
     * @return HashMap, retornar los valores de la tabla sigseguimiento y el id
     * asociado
     * @throws java.lang.Exception
     *
     * -------------------------------------------------------------------------
     * Modificado: Eddy Valero Fecha: 02/02/2017
     *
     * setear el genero(sexo) del apoderado en la solicitud.
     * -------------------------------------------------------------------------
     * Modificado: Eddy Valero Fecha: 25/02/2017
     *
     * Agregar el parametro fechaIngreso, para el ingreso de los tramites por
     * ventanilla
     */
    public HashMap guardarFormularioPI103(Date fechaIngreso, FormularioPI103 formularioPI103, List<ElementoFormularioTramite> plantillaVentanilla,
            Long idUsuario, Long codigoSM, String oficina, String observacion,
            Long numero, Integer gestion, String siglaModificacion) throws Exception;

    /**
     * Método que permite guardar un registro de formulario PI103
     * -------------------------------------------------------------------------
     * Creado: Eddy Valero Fecha: 27/10/2016
     *
     * @param fechaIngreso
     * @param formularioPI104
     * @param plantillaVentanilla
     * @param idUsuario
     * @param codigoSM
     * @param oficina
     * @param observacion
     * @param numero
     * @param gestion
     * @return HashMap, retornar los valores de la tabla sigseguimiento y el id
     * asociado
     * @throws java.lang.Exception
     *
     * -------------------------------------------------------------------------
     * Modificado: Eddy Valero Fecha: 02/02/2017
     *
     * setear el genero(sexo) del apoderado en la solicitud.
     * -------------------------------------------------------------------------
     * Modificado: Eddy Valero Fecha: 25/02/2017
     *
     * Agregar el parametro fechaIngreso, para el ingreso de los tramites por
     * ventanilla
     */
    public HashMap guardarFormularioPI104(Date fechaIngreso, FormularioPI104 formularioPI104, List<ElementoFormularioTramite> plantillaVentanilla,
            Long idUsuario, Long codigoSM, String oficina, String observacion,
            Long numero, Integer gestion) throws Exception;
    
        /**
     * Método que permite guardar un registro de formulario PI105
     * -------------------------------------------------------------------------
     * Creado: Ruben Ramirez Fecha: 22/07/2017
     *
     * @param fechaIngreso
     * @param formularioPI105
     * @param plantillaVentanilla
     * @param idUsuario
     * @param codigoSM
     * @param oficina
     * @param observacion
     * @param numero
     * @param gestion
     * @return HashMap, retornar los valores de la tabla sigseguimiento y el id
     * asociado
     * @throws java.lang.Exception
     */
    public HashMap guardarFormularioPI105(Date fechaIngreso, FormularioPI105 formularioPI105, List<ElementoFormularioTramite> plantillaVentanilla,
            Long idUsuario, Long codigoSM, String oficina, String observacion,
            Long numero, Integer gestion) throws Exception;

    /**
     * Método que verifica existencia de un registro de SignoMarca por SM
     * -------------------------------------------------------------------------
     * Creado: Eddy Valero Fecha: 09/11/2016
     *
     * @param codigoSM
     * @return Boolean
     * @throws java.lang.Exception
     */
    public Boolean verificarExistenciaRegistroMarcaXSM(Long codigoSM) throws Exception;

    /**
     * Método que verifica existencia de un registro de Modificacion
     * -------------------------------------------------------------------------
     * Creado: Eddy Valero Fecha: 09/11/2016
     *
     * @param sigla
     * @param numero
     * @param gestion
     * @return Boolean
     * @throws java.lang.Exception
     */
    public Boolean verificarExistenciaRegistroModificacion(String sigla, Long numero, Long gestion) throws Exception;

    /**
     * Método que verifica existencia de un registro de Renovacion
     * -------------------------------------------------------------------------
     * Creado: Eddy Valero Fecha: 09/11/2016
     *
     * @param sigla
     * @param numero
     * @param gestion
     * @return Boolean
     * @throws java.lang.Exception
     */
    public Boolean verificarExistenciaRegistroRenovacion(Long numero, Long gestion) throws Exception;
    /**
     * Método que verifica existencia de un registro de oposicion
     * -------------------------------------------------------------------------
     * Creado: Luis Angel Quispe Fecha: 19/07/2017
     *
     * @param numero
     * @param gestion
     * @return Boolean
     * @throws java.lang.Exception
     */
    public Boolean verificarExistenciaRegistroOposicion(Long numero, Long gestion) throws Exception;

}
