/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.expediente;

import com.sun.faces.context.FacesFileNotFoundException;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperRunManager;
import static org.apache.logging.log4j.core.util.Closer.close;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import snp.gob.bo.gimodel.bdimagen.entidad.ImagenPojo;
import snp.gob.bo.gimodel.bdimagen.servicio.ImagenPojoService;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.enums.EnumCarpetasTemporales;
import snp.gob.bo.gimodel.servicio.SigSignoMarcaService;
import snp.gob.bo.gimodel.servicio.UsuarioPaginaService;
import org.apache.tools.ant.util.FileUtils;
import snp.gob.bo.gimodel.bdimagen.entidad.SigImagen;
import snp.gob.bo.gimodel.bdimagen.entidad.SigLogoTipo;
import snp.gob.bo.gimodel.bdimagen.servicio.SigImagenService;
import snp.gob.bo.gimodel.bdimagen.servicio.SigLogoTipoService;
/**
 *
 * @author Eddy Valero
 * @version 1.0, 11/10/2016
 *
 */
@ManagedBean(name = "agregarSignoImagenBean")
@ViewScoped
public class AgregarImagenSignoBean implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Definicion de servicios y atributos... verificar estos datos">
    private UploadedFile file = null;
//    private SigLogoTipo sigLogoTipo = new SigLogoTipo();
//    private SigImagen sigImagen = new SigImagen();
    private ImagenPojo imagenPojo = new ImagenPojo();
    private StreamedContent image;
    //</editor-fold>
    @ManagedProperty("#{imagenPojoService}")
    private ImagenPojoService imagenPojoService;
    @ManagedProperty("#{sigSignoMarcaService}")
    private SigSignoMarcaService sigSignoMarcaService;
      @ManagedProperty(value = "#{usuarioPaginaService}")
    private UsuarioPaginaService usuarioPaginaService;
    @ManagedProperty(value = "#{sigLogoTipoService}")
    private SigLogoTipoService sigLogoTipoService;  
    @ManagedProperty(value = "#{sigImagenService}")
    private SigImagenService sigImagenService;  
     
    
    public StreamedContent stream;
    private SigSignoMarca sigoMarca;
     private   String idSignoMarca;
     private int sw=0;
     /************************************/
     private byte[] fotoMemo;
     private String nombreImage;
     private boolean muestraDescarga;
     
     
    /**
     * Creates a new instance of AgregarPersonaEmpresaBean
     */
    public AgregarImagenSignoBean() {

    }

    @PostConstruct
    public void initAgregarImagenSignoBean() {
        
        
        try {
           
            fotoMemo=null;
             idSignoMarca=null;
            Map<String, String[]> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterValuesMap();
            if(!params.get("datosBusLogo")[0].equals("no hay id"))
            {   if(!params.get("datosBusLogo")[1].equals("no hay imagen") )
                 {  
                     //System.out.println("No Es nulo"+params.get("datosBusLogo")[1]);
                     this.nombreImage=params.get("datosBusLogo")[1];
                 }
                 else{
                 //    System.out.println("Es nulo");
                 }
                idSignoMarca=params.get("datosBusLogo")[2];
            }
            else{
                //  System.out.println(" no hay ide siempre..");    
                 if(!params.get("datosBusLogo")[1].equals("no hay imagen") )
                 {  
                     //System.out.println("No Es nulo"+params.get("datosBusLogo")[1]);
                     this.nombreImage=params.get("datosBusLogo")[1];
                 }
                 else{
                   //  System.out.println("Es nulo");
                 }
                 
              }
            
       //     this.imagenPojo = imagenPojoService.obtenerImagePojoXSignoMarca(Long.parseLong(idSignoMarca));
        //    InputStream myInputStream = new ByteArrayInputStream(imagenPojo.getSigImagen().getImagen()); 
          //  image = new DefaultStreamedContent(myInputStream,"image/jpeg");
           
            
            muestraDescarga=false;
            
            
        } catch (Exception ex) {
           // Logger.getLogger(AgregarImagenSignoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }
//    public StreamedContent getImage() sthrows IOException, Exception {
//         FacesContext context = FacesContext.getCurrentInstance();
//           // System.out.println("context.getCurrentPhaseId()::"+context.getCurrentPhaseId());  
//            //if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
//         if(idSignoMarca!=null &&!idSignoMarca.equals("") )
//         {  if(sw==0)
//           {
//                // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
//                //System.out.println("entra a qui11");
//                sw=1;
//                return new DefaultStreamedContent();
//                 
//              }
//             else {
//                
//                  this.imagenPojo = imagenPojoService.obtenerImagePojoXSignoMarca(Long.parseLong(idSignoMarca));
//            //    System.out.println("imagenpojo:"+imagenPojo.getSigImagen().getIdImagen());
//            //   System.out.println("entra a qui2.2");
//            //    System.out.println("imagenPojo.getSigImagen().getImagen()):::"+imagenPojo.getSigImagen().getImagen());
//                if(imagenPojo.getSigImagen().getImagen()==null)
//                {System.out.println("es nulo");}
//                else{
//                    System.out.println("no es nulo");
//                }
//                
//               return new DefaultStreamedContent(new ByteArrayInputStream(imagenPojo.getSigImagen().getImagen()));
//               
//              
//            }
//         }
//         else{
//              if (fotoMemo!=null)        
//              { return new DefaultStreamedContent(new ByteArrayInputStream(fotoMemo));
//              }
//              else{
//                return null;
//              }
//           }
//         
//    }
   /* public DynamicImageController() {
         InputStream stream = this.getClass().getResourceAsStream("barcalogo.jpg");
            image = new DefaultStreamedContent(stream, "image/jpeg");
      }*/
   

    /**
     * Metodo guardar la imagen
     *
     * Creado: Eddy Valero Fecha: 11/10/2016
     * @param event
     */
    public void accionCargarImagenProyecto(FileUploadEvent event) {
       try {
           muestraDescarga=true;
         //  System.out.println("idSignoMarca:::"+idSignoMarca);
         if(idSignoMarca==null || idSignoMarca.equals("") )
         {
             /* lstImagenPojos = new ArrayList<ImagenPojo>();
                this.imagenPojo = imagenPojoService.obtenerImagePojoXSignoMarca(this.sigSignoMarca.getIdSignoMarca());
              if (imagenPojo.getSigLogoTipo().getIdLogoTipo() != null) {
                    this.lstImagenPojos.add(imagenPojo);
                    cargarArchivoWAR();
                }
             */
             FacesContext facesContext = FacesContext.getCurrentInstance();
             ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
             String rutaWar = servletContext.getRealPath(EnumCarpetasTemporales.CARPETA_IMAGENES.getCodigo());

             File targetFolder = new File(rutaWar);

             if (!targetFolder.exists()) {
                 targetFolder.mkdirs();
             }

             byte[] fotoByte = event.getFile().getContents();
             imagenPojo = new ImagenPojo();
             imagenPojo.getSigLogoTipo().setIdLogTrans(1L);
             imagenPojo.getSigLogoTipo().setNombreArchivo(event.getFile().getFileName());
             imagenPojo.getSigLogoTipo().setEstado("AC");
             imagenPojo.getSigLogoTipo().setPrincipal(Boolean.TRUE);
             imagenPojo.getSigLogoTipo().setExtensionArchivo(event.getFile().getContentType());
             imagenPojo.getSigImagen().setImagen(fotoByte);
             String imagenDibuja = rutaWar + File.separator + event.getFile().getFileName();
            // System.out.println("imagenDIbuja:::"+imagenDibuja); 
            // System.out.println("nombre::"+event.getFile().getFileName()); 
             crearArchivo(fotoByte, imagenDibuja);
             nombreImage=event.getFile().getFileName();
             
             
             
             //fotoMemo=event.getFile().getContents();
            // nombreImage
             
         }
         else  
         {  
          
            FacesContext facesContext = FacesContext.getCurrentInstance();
             ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
             String rutaWar = servletContext.getRealPath(EnumCarpetasTemporales.CARPETA_IMAGENES.getCodigo());

             File targetFolder = new File(rutaWar);

             if (!targetFolder.exists()) {
                 targetFolder.mkdirs();
             }

             byte[] fotoByte = event.getFile().getContents();
             imagenPojo = new ImagenPojo();
             imagenPojo.getSigLogoTipo().setIdLogTrans(1L);
             imagenPojo.getSigLogoTipo().setIdSignoMarca(Long.parseLong(idSignoMarca));
             imagenPojo.getSigLogoTipo().setNombreArchivo(event.getFile().getFileName());
             imagenPojo.getSigLogoTipo().setEstado("AC");
             imagenPojo.getSigLogoTipo().setPrincipal(Boolean.TRUE);
             imagenPojo.getSigLogoTipo().setExtensionArchivo(event.getFile().getContentType());
             imagenPojo.getSigImagen().setImagen(fotoByte);
             String imagenDibuja = rutaWar + File.separator + event.getFile().getFileName();
            // System.out.println("imagenDIbuja:::"+imagenDibuja); 
            // System.out.println("nombre::"+event.getFile().getFileName()); 
             crearArchivo(fotoByte, imagenDibuja);
             nombreImage=event.getFile().getFileName();
         }
         
            //   registrarImagePojo
        } catch (Exception ex) {
            Logger.getLogger(AgregarImagenSignoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    /*public void accionCargarImagenProyecto1(FileUploadEvent event) {
        
        
        try {
            //el atributo file contiene toda la imagen, que se quiere insertar
            
//            InputStream inputStream = event.getFile().getInputstream();
            String nombreImagenCargar = event.getFile().getFileName();
            
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
            String rutaWar = servletContext.getRealPath(EnumCarpetasTemporales.CARPETA_IMAGENES.getCodigo());
            
            File targetFolder = new File(rutaWar);
            
            if (!targetFolder.exists()) {
                targetFolder.mkdirs();
            }
            
            byte[] fotoByte = event.getFile().getContents();
            imagenPojo = new ImagenPojo();
            
            imagenPojo.getSigLogoTipo().setNombreArchivo(nombreImagenCargar);
            imagenPojo.getSigLogoTipo().setEstado("AC");
            imagenPojo.getSigLogoTipo().setPrincipal(Boolean.TRUE);
            imagenPojo.getSigImagen().setImagen(fotoByte);
            
            String imagenDibuja = rutaWar + File.separator + nombreImagenCargar;
            
            
            //enviar el imagenPojo a la vista, antes hacemos una previsualizacion
            crearArchivo(fotoByte, imagenDibuja);
            
            RequestContext.getCurrentInstance().closeDialog(imagenPojo);
            
            //cerrar el panel de la imagen
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    public void guardaMemoria(){
         System.out.println("tam:::::"+imagenPojo.getSigImagen().getIdImagen());
        RequestContext.getCurrentInstance().closeDialog(imagenPojo);
    
    }
    /***
     *  Metodo que guarda el archivo en una ruta determinada
     * 
     * 
     * 
     */
    private void crearArchivo(byte[] bytes, String archivo){
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(archivo);
            fos.write(bytes);
            
            fos.flush();
            fos.close();
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex){
            ex.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public void bajaArchivo() throws Exception {
        
        
        
        SigLogoTipo sigLogo=sigLogoTipoService.obtenerRegistroSigLogoTipoXIdSignoMarca(Long.parseLong(idSignoMarca));
        SigImagen sigImage=sigImagenService.obtenerSigImagenXIdSigLogoTipo(sigLogo.getIdLogoTipo());
        String fileName =sigLogo.getNombreArchivo();
        byte[] fotoByteImprime=sigImage.getImagen();
        //En esta parte se filtra archivos que esten guardados con ya una extension y tenga punto, y ademas despues si tiene en el campo extension archivo su tipo mime con / tambien se haga split para qe  no tome eso en cuenta al concatenar el nombre
        String extension=null;
        if(fileName.contains("."))
        {   
           extension=sigLogo.getExtensionArchivo();
        }
        else{
           
                      
            if(sigLogo.getExtensionArchivo().contains("/"))
          {
             String [] extens =sigLogo.getExtensionArchivo().split("/");
             extension=extens[1];
           }
          else{
             extension=sigLogo.getExtensionArchivo();
              
          }
        }
        /*
        if (filtroArea.equals("SIG")) {
            // System.out.println("Entra al botono de vbajar, iddocumento:"+documento.getIddocumento());
            List<SigArchivoDig> listSigArch = sigArchivoDigService.listaSigArchivoXiddoc(tramite.getIddocumento());
            // System.out.println("tam de el el byte::"+listSigArch.get(0).getArchivo().length);
            fotoByteImprime = listSigArch.get(0).getArchivo();
            mimeImprime = tramite.getMime();

        }
        if (filtroArea.equals("MOD")) {
            List<ModArchivoDig> lisModArch = modArchivoDigService.listaModArchivoXiddoc(tramite.getIddocumento());
            fotoByteImprime = lisModArch.get(0).getArchivo();
            mimeImprime = tramite.getMime();

        }
        if (filtroArea.equals("REN")) {
            List<RenArchivoDig> lisRenArch = renArchivoDigService.listaRenArchivoXiddoc(tramite.getIddocumento());
            fotoByteImprime = lisRenArch.get(0).getArchivo();
            mimeImprime = tramite.getMime();

        }*/
      
      //  String fileName = tramite.getNombre_archivo();
        HttpServletResponse resp = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        // resp.setContentType("text/plain");
        resp.setHeader("Content-Disposition", "attachment;filename=" + fileName+"."+extension);
        // resp.getOutputStream().write(fotoByteImprime);

        resp.setContentType(sigLogo.getExtensionArchivo());
        resp.setContentLength(fotoByteImprime.length);

        ServletOutputStream outStream = resp.getOutputStream();
        outStream.write(fotoByteImprime, 0, fotoByteImprime.length);
        outStream.flush();
        outStream.close();
        FacesContext.getCurrentInstance().responseComplete();

    }
    /**
     * Metodo cerrar el dialogo
     *
     * Creado: Eddy Valero Fecha: 11/10/2016
     */
    public void cerrarDialogoClaseNiza() {
        RequestContext.getCurrentInstance().closeDialog("Exit");
        
    }
  
    //</editor-fold>
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    
    public ImagenPojo getImagenPojo() {
        return imagenPojo;
    }

    public void setImagenPojo(ImagenPojo imagenPojo) {
        this.imagenPojo = imagenPojo;
    }
    //<editor-fold defaultstate="collapsed" desc="---------------- getters y setters -----------------">

    public ImagenPojoService getImagenPojoService() {
        return imagenPojoService;
    }

    public void setImagenPojoService(ImagenPojoService imagenPojoService) {
        this.imagenPojoService = imagenPojoService;
    }

    public StreamedContent getStream() {
        return stream;
    }

    public void setStream(StreamedContent stream) {
        this.stream = stream;
    }

    public String getIdSignoMarca() {
        return idSignoMarca;
    }

    public void setIdSignoMarca(String idSignoMarca) {
        this.idSignoMarca = idSignoMarca;
    }

    public int getSw() {
        return sw;
    }

    public void setSw(int sw) {
        this.sw = sw;
    }

    public SigSignoMarcaService getSigSignoMarcaService() {
        return sigSignoMarcaService;
    }

    public void setSigSignoMarcaService(SigSignoMarcaService sigSignoMarcaService) {
        this.sigSignoMarcaService = sigSignoMarcaService;
    }

    public SigSignoMarca getSigoMarca() {
        return sigoMarca;
    }

    public void setSigoMarca(SigSignoMarca sigoMarca) {
        this.sigoMarca = sigoMarca;
    }

    public UsuarioPaginaService getUsuarioPaginaService() {
        return usuarioPaginaService;
    }

    public void setUsuarioPaginaService(UsuarioPaginaService usuarioPaginaService) {
        this.usuarioPaginaService = usuarioPaginaService;
    }

    public byte[] getFotoMemo() {
        return fotoMemo;
    }

    public void setFotoMemo(byte[] fotoMemo) {
        this.fotoMemo = fotoMemo;
    }

    public String getNombreImage() {
        return nombreImage;
    }

    public void setNombreImage(String nombreImage) {
        this.nombreImage = nombreImage;
    }

    public StreamedContent getImage() {
        return image;
    }

    public void setImage(StreamedContent image) {
        this.image = image;
    }

    public SigLogoTipoService getSigLogoTipoService() {
        return sigLogoTipoService;
    }

    public void setSigLogoTipoService(SigLogoTipoService sigLogoTipoService) {
        this.sigLogoTipoService = sigLogoTipoService;
    }

    public SigImagenService getSigImagenService() {
        return sigImagenService;
    }

    public void setSigImagenService(SigImagenService sigImagenService) {
        this.sigImagenService = sigImagenService;
    }

    public boolean isMuestraDescarga() {
        return muestraDescarga;
    }

    public void setMuestraDescarga(boolean muestraDescarga) {
        this.muestraDescarga = muestraDescarga;
    }


}
