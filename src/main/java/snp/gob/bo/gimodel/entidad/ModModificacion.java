/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.entidad;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author susana
 */
public class ModModificacion implements Serializable {

    Long idmodificacion;
    Long idlogtrans;
    String sigla;
    Long numero;
    Integer gestion;
    Long sm;
    Date fecha_ingreso;
    //orden_opcional character(10), 
    String nro_formulario;
    String oficina;
    Long numero_registro;
    String serie_registro;
    Long numero_renovacion;
    String serie_renovacion;
    Long numero_publicacion;
    String signo_registrado;
//    Integer clase_registrado;
    Long idclase_registrado;
    String tipo_genero_registrado;
    String marca_acomp;
    Long reg_lc_registrado;
    String serie_lc_registrado;
    Date fecha_lc_registrado;
    String nuevo_domicilio;
    String nueva_nacionalidad;
    String nuevo_pais_constitucion;
    String nuevo_departamento;
    //fusion_participante character varying(5000),
    Date luinicio;
    Date lu_fin;
    Date fecha_ultima_operacion;
    Long usuario;
    String titular_signo;
    String nacionalidad_signo;
    String departamento_signo;
    String domicilio_signo;
    String telefono_signo;
    String fax_signo;
    String email_signo;
    String tipo_modificacion;
    String estado_modificacion;
    String ubicacion_modificacion;
    String lista_producto;
    String estado;
    Long nro_recibo;
    String serie_recibo;

    public Long getIdmodificacion() {
        return idmodificacion;
    }

    public void setIdmodificacion(Long idmodificacion) {
        this.idmodificacion = idmodificacion;
    }

    public Long getIdlogtrans() {
        return idlogtrans;
    }

    public void setIdlogtrans(Long idlogtrans) {
        this.idlogtrans = idlogtrans;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Integer getGestion() {
        return gestion;
    }

    public void setGestion(Integer gestion) {
        this.gestion = gestion;
    }

    public Long getSm() {
        return sm;
    }

    public void setSm(Long sm) {
        this.sm = sm;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public String getOficina() {
        return oficina;
    }

    public void setOficina(String oficina) {
        this.oficina = oficina;
    }

    public Long getNumero_registro() {
        return numero_registro;
    }

    public void setNumero_registro(Long numero_registro) {
        this.numero_registro = numero_registro;
    }

    public String getSerie_registro() {
        return serie_registro;
    }

    public void setSerie_registro(String serie_registro) {
        this.serie_registro = serie_registro;
    }

    public Long getNumero_renovacion() {
        return numero_renovacion;
    }

    public void setNumero_renovacion(Long numero_renovacion) {
        this.numero_renovacion = numero_renovacion;
    }

    public String getSerie_renovacion() {
        return serie_renovacion;
    }

    public void setSerie_renovacion(String serie_renovacion) {
        this.serie_renovacion = serie_renovacion;
    }

    public Long getNumero_publicacion() {
        return numero_publicacion;
    }

    public void setNumero_publicacion(Long numero_publicacion) {
        this.numero_publicacion = numero_publicacion;
    }

    public String getSigno_registrado() {
        return signo_registrado;
    }

    public void setSigno_registrado(String signo_registrado) {
        this.signo_registrado = signo_registrado;
    }

//    public Integer getClase_registrado() {
//        return clase_registrado;
//    }
//
//    public void setClase_registrado(Integer clase_registrado) {
//        this.clase_registrado = clase_registrado;
//    }

    public Long getIdclase_registrado() {
        return idclase_registrado;
    }

    public void setIdclase_registrado(Long idclase_registrado) {
        this.idclase_registrado = idclase_registrado;
    }

    
    
    
    
    public String getTipo_genero_registrado() {
        return tipo_genero_registrado;
    }

    public void setTipo_genero_registrado(String tipo_genero_registrado) {
        this.tipo_genero_registrado = tipo_genero_registrado;
    }

    public String getMarca_acomp() {
        return marca_acomp;
    }

    public void setMarca_acomp(String marca_acomp) {
        this.marca_acomp = marca_acomp;
    }

    public Long getReg_lc_registrado() {
        return reg_lc_registrado;
    }

    public void setReg_lc_registrado(Long reg_lc_registrado) {
        this.reg_lc_registrado = reg_lc_registrado;
    }

    public String getSerie_lc_registrado() {
        return serie_lc_registrado;
    }

    public void setSerie_lc_registrado(String serie_lc_registrado) {
        this.serie_lc_registrado = serie_lc_registrado;
    }

    public Date getFecha_lc_registrado() {
        return fecha_lc_registrado;
    }

    public void setFecha_lc_registrado(Date fecha_lc_registrado) {
        this.fecha_lc_registrado = fecha_lc_registrado;
    }

    public String getNuevo_domicilio() {
        return nuevo_domicilio;
    }

    public void setNuevo_domicilio(String nuevo_domicilio) {
        this.nuevo_domicilio = nuevo_domicilio;
    }

    public String getNueva_nacionalidad() {
        return nueva_nacionalidad;
    }

    public void setNueva_nacionalidad(String nueva_nacionalidad) {
        this.nueva_nacionalidad = nueva_nacionalidad;
    }

    public String getNuevo_departamento() {
        return nuevo_departamento;
    }

    public void setNuevo_departamento(String nuevo_departamento) {
        this.nuevo_departamento = nuevo_departamento;
    }

    public Date getLuinicio() {
        return luinicio;
    }

    public void setLuinicio(Date luinicio) {
        this.luinicio = luinicio;
    }

    public Date getLu_fin() {
        return lu_fin;
    }

    public void setLu_fin(Date lu_fin) {
        this.lu_fin = lu_fin;
    }

    public Date getFecha_ultima_operacion() {
        return fecha_ultima_operacion;
    }

    public void setFecha_ultima_operacion(Date fecha_ultima_operacion) {
        this.fecha_ultima_operacion = fecha_ultima_operacion;
    }

    public Long getUsuario() {
        return usuario;
    }

    public void setUsuario(Long usuario) {
        this.usuario = usuario;
    }

    public String getTitular_signo() {
        return titular_signo;
    }

    public void setTitular_signo(String titular_signo) {
        this.titular_signo = titular_signo;
    }

    public String getNacionalidad_signo() {
        return nacionalidad_signo;
    }

    public void setNacionalidad_signo(String nacionalidad_signo) {
        this.nacionalidad_signo = nacionalidad_signo;
    }

    public String getDepartamento_signo() {
        return departamento_signo;
    }

    public void setDepartamento_signo(String departamento_signo) {
        this.departamento_signo = departamento_signo;
    }

    public String getDomicilio_signo() {
        return domicilio_signo;
    }

    public void setDomicilio_signo(String domicilio_signo) {
        this.domicilio_signo = domicilio_signo;
    }

    public String getTelefono_signo() {
        return telefono_signo;
    }

    public void setTelefono_signo(String telefono_signo) {
        this.telefono_signo = telefono_signo;
    }

    public String getFax_signo() {
        return fax_signo;
    }

    public void setFax_signo(String fax_signo) {
        this.fax_signo = fax_signo;
    }

    public String getEmail_signo() {
        return email_signo;
    }

    public void setEmail_signo(String email_signo) {
        this.email_signo = email_signo;
    }

    public String getTipo_modificacion() {
        return tipo_modificacion;
    }

    public void setTipo_modificacion(String tipo_modificacion) {
        this.tipo_modificacion = tipo_modificacion;
    }

    public String getEstado_modificacion() {
        return estado_modificacion;
    }

    public void setEstado_modificacion(String estado_modificacion) {
        this.estado_modificacion = estado_modificacion;
    }

    public String getNuevo_pais_constitucion() {
        return nuevo_pais_constitucion;
    }

    public void setNuevo_pais_constitucion(String nuevo_pais_constitucion) {
        this.nuevo_pais_constitucion = nuevo_pais_constitucion;
    }

    public String getUbicacion_modificacion() {
        return ubicacion_modificacion;
    }

    public void setUbicacion_modificacion(String ubicacion_modificacion) {
        this.ubicacion_modificacion = ubicacion_modificacion;
    }

    public String getLista_producto() {
        return lista_producto;
    }

    public void setLista_producto(String lista_producto) {
        this.lista_producto = lista_producto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getNro_recibo() {
        return nro_recibo;
    }

    public void setNro_recibo(Long nro_recibo) {
        this.nro_recibo = nro_recibo;
    }

    public String getSerie_recibo() {
        return serie_recibo;
    }

    public void setSerie_recibo(String serie_recibo) {
        this.serie_recibo = serie_recibo;
    }

    public String getNro_formulario() {
        return nro_formulario;
    }

    public void setNro_formulario(String nro_formulario) {
        this.nro_formulario = nro_formulario;
    }

    @Override
    public String toString() {
        String inicio = "";
        String fin = "";
        String fecha_lc = "";
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

        String fechaIngreso = "";
        if (fecha_ingreso != null) {
            fechaIngreso = formateador.format(fecha_ingreso);
        }
        String numero_reciboString = "";

        if (serie_registro == null) {
            serie_registro = "";
        }

        if (oficina == null) {
            oficina = "";
        }
        if (nro_formulario == null) {
            nro_formulario = "";
        }
        if (serie_renovacion == null) {
            serie_renovacion = "";
        }
        if (luinicio == null) {
            inicio = "";
        } else {
            inicio = formateador.format(luinicio);
        }
        if (lu_fin == null) {
            fin = "";
        } else {
            fin = formateador.format(lu_fin);
        }

        if (marca_acomp == null) {
            marca_acomp = "";
        }

        if (ubicacion_modificacion == null) {
            ubicacion_modificacion = "";
        }

        if (serie_lc_registrado == null) {
            serie_lc_registrado = "";
        }

        if (serie_recibo == null) {
            serie_recibo = "";
        }

        if (fecha_lc_registrado == null) {
            fecha_lc = "";
        } else {
            fecha_lc = formateador.format(fecha_lc_registrado);
        }

        if (nro_recibo == 0 || nro_recibo == null) {
            numero_reciboString = "";
        } else {
            numero_reciboString = nro_recibo.toString();
        }

        return "|" + "idmodificacion=" + idmodificacion
                + "|" + "idlogtrans=" + idlogtrans
                + "|" + "sigla=" + sigla
                + "|" + "numero=" + numero
                + "|" + "gestion=" + gestion
                + "|" + "sm=" + sm
                //   + "|" + "fecha_ingreso=" + fecha_ingreso
                + "|" + "fecha_ingreso=" + fechaIngreso
                + "|" + "nro_formulario=" + nro_formulario
                + "|" + "oficina=" + oficina
                + "|" + "numero_registro=" + numero_registro
                + "|" + "serie_registro=" + serie_registro
                + "|" + "numero_renovacion=" + numero_renovacion
                + "|" + "serie_renovacion=" + serie_renovacion
                + "|" + "numero_publicacion=" + numero_publicacion
                // + "|" + "nro_recibo=" + nro_recibo
                + "|" + "nro_recibo=" + numero_reciboString
                + "|" + "serie_recibo=" + serie_recibo
                + "|" + "signo_registrado=" + signo_registrado
                + "|" + "idclase_registrado=" + idclase_registrado
                + "|" + "tipo_genero_registrado=" + tipo_genero_registrado
                + "|" + "marca_acomp=" + marca_acomp
                + "|" + "reg_lc_registrado=" + reg_lc_registrado
                //+ "|" + "fecha_lc_registrado=" + fecha_lc_registrado
                + "|" + "fecha_lc_registrado=" + fecha_lc
                + "|" + "nuevo_domicilio=" + nuevo_domicilio
                + "|" + "nueva_nacionalidad=" + nueva_nacionalidad
                + "|" + "nuevo_pais_constitucion=" + nuevo_pais_constitucion
                + "|" + "nuevo_departamento=" + nuevo_departamento
                //+ "|" + "luinicio=" + luinicio
                + "|" + "luinicio=" + inicio
                //+ "|" + "lu_fin=" + lu_fin
                + "|" + "lu_fin=" + fin
                + "|" + "fecha_ultima_operacion=" + fecha_ultima_operacion
                + "|" + "usuario=" + usuario
                + "|" + "titular_signo=" + titular_signo
                + "|" + "nacionalidad_signo=" + nacionalidad_signo
                + "|" + "departamento_signo=" + departamento_signo
                + "|" + "domicilio_signo=" + domicilio_signo
                + "|" + "telefono_signo=" + telefono_signo
                + "|" + "fax_signo=" + fax_signo
                + "|" + "email_signo=" + email_signo
                + "|" + "tipo_modificacion=" + tipo_modificacion
                + "|" + "estado_modificacion=" + estado_modificacion
                + "|" + "lista_producto=" + lista_producto
                + "|" + "estado=" + estado + "|";

    }
}
