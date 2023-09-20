/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.Documento;

/**
 *
 * @author susana
 */
public interface DocumentoService {

    public void setDataSource(DataSource dataSource);

    public Documento guardarDocumento(Documento documento);
    
    public List<Documento> obtenerListadoDocumento();
}
