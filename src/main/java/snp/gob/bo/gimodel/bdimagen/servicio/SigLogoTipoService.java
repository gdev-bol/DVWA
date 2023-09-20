/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.bdimagen.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.bdimagen.entidad.SigLogoTipo;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 27/08/2016
 */
public interface SigLogoTipoService {

    /**
     *
     * Creado: Eddy Valero Fecha: 27/08/2016
     *
     * @param dataSource
     */
    public void setDataSource(DataSource dataSource);

    /**
     *
     * Creado: Eddy Valero Fecha: 27/08/2016
     *
     * @param sigLogoTipo
     * @return
     * @throws java.lang.Exception
     */
    public SigLogoTipo registrarSigLogoTipo(SigLogoTipo sigLogoTipo) throws Exception;

    /**
     * Método para obtener todos los siglogotipos asociados a idMarca
     *
     * Creado: Eddy Valero Fecha: 27/09/2016
     *
     * @param idSignoMarca
     * @return List<SigLogoTipo>
     * @throws java.lang.Exception
     */
    public List<SigLogoTipo> obtenerSigLogoTipoXIdSignoMarca(Long idSignoMarca) throws Exception;

    /**
     * Método para realizar el registro o modificación de SigLogoTipo
     *
     * Creado: Eddy Valero Fecha: 11/10/2016
     *
     * @param sigLogoTipo
     * @param operacion
     * @return SigLogoTipo
     * @throws java.lang.Exception
     */
    public SigLogoTipo guardar_modificar_listar_SigLogoTipo(SigLogoTipo sigLogoTipo, Integer operacion) throws Exception;

    /**
     * Método para obtener un registro
     *
     * Creado: Eddy Valero Fecha: 11/10/2016
     *
     * @param idSignoMarca
     * @return SigLogoTipo
     * @throws java.lang.Exception
     */
    public SigLogoTipo obtenerRegistroSigLogoTipoXIdSignoMarca(Long idSignoMarca) throws Exception;

    /**
     * Método para obtener el registro pricipal
     *
     * Creado: Ruben Ramirez Fecha: 11/10/2016
     *
     * @param idSignoMarca
     * @return SigLogoTipo
     * @throws java.lang.Exception
     */
    public SigLogoTipo obtenerRegistroPrincipalSigLogoTipoXIdSignoMarca(Long idSignoMarca) throws Exception;

}
