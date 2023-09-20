/*
Creado: Victor Hugo Almendras Murillo Fecha: 14/11/2016
********************************************************************************
Obtiene todos los idsignomarca y sm de la tabla sigsignomarca
ordenado de forma ascendente
*/

CREATE OR REPLACE FUNCTION obtiene_id_sm_tabla_sigsignomarca()

RETURNS TABLE(vidsignomarca bigint, vsm bigint) As

$$

/*
Creado: Victor Hugo Almendras Murillo Fecha: 14/11/2016
Obtiene todos los idsignomarca y sm de la tabla sigsignomarca
ordenado de forma ascendente
*/

BEGIN

RETURN query
SELECT idsignomarca As vidsignomarca, sm As vsm
FROM sigsignomarca
ORDER BY idsignomarca;

END;

$$ LANGUAGE plpgsql;

/*
********************************************************************************
Creado: Victor Hugo Almendras Murillo Fecha: 28/11/2016

Coloca el valor maximo de la columna de la secuencia + 1,
por mas que la secuencia tenga agujeros

*/

CREATE OR REPLACE FUNCTION modifica_secuencia_tabla_migracion_agujeros(pnombretabla varchar(30), pnombreatributo varchar(30), pnombresecuencia varchar(50))

RETURNS VOID AS

$$

/*

Creado: Victor Hugo Almendras Murillo Fecha: 28/11/2016

Coloca el valor maximo de la columna de la secuencia + 1,
por mas que la secuencia tenga agujeros

*/

--DECLARANDO VARIABLES AUXILIARES
DECLARE vQUERY1 text;
    vQUERY2 text;
    vValorColumna integer;
    vRegistro record;

BEGIN

    vQUERY1 := format('SELECT %s + 1 As ValorColumna
               FROM %s
               ORDER BY %s DESC;', pnombreatributo, pnombretabla, pnombreatributo);

    EXECUTE vQUERY1 INTO vRegistro;

    vValorColumna = vRegistro.ValorColumna;

    --COLOCANDO LA SECUENCIA EN EN VALOR DE COLUMNAS + 1

    vQUERY2 := 'ALTER SEQUENCE ' || pnombresecuencia || ' restart ' || vValorColumna || ';'
            || 'ALTER TABLE ' || pnombretabla || ' OWNER TO senapi';

    EXECUTE vQUERY2;

END;

$$ LANGUAGE plpgsql;



/*
*******************************************************************************
Creado: Victor Hugo Almendras Murillo Fecha: 27/10/2016
Verifica la cantidad de filas de una tabla y modifica 
la cantidad de filas + 1 y se coloca determinado valor
en la secuencia
*/


CREATE OR REPLACE FUNCTION modifica_secuencia_tabla_migracion(pnombretabla varchar(30), pnombreatributo varchar(30), pnombresecuencia varchar(50))

RETURNS VOID AS

$$

/*
Creado: Victor Hugo Almendras Murillo Fecha: 27/10/2016
Verifica la cantidad de filas de una tabla y modifica 
la cantidad de filas + 1 y se coloca determinado valor
en la secuencia
*/

--DECLARANDO VARIABLES AUXILIARES
DECLARE vQUERY1 text;
    vQUERY2 text;
    vNumeroFilas integer;
    vRegistro record;

BEGIN

     vQUERY1 := format('SELECT COUNT(%s) + 1 As NumeroFilas
                FROM %s;', pnombreatributo, pnombretabla);
     EXECUTE vQUERY1 INTO vRegistro;


    vNumeroFilas = vRegistro.NumeroFilas;

    --COLOCANDO LA SECUENCIA EN EN NUMERO DE FILAS + 1
    
     vQUERY2 := 'ALTER SEQUENCE ' || pnombresecuencia || ' restart ' || vNumeroFilas || ';'
            || 'ALTER TABLE ' || pnombretabla || ' OWNER TO senapi';

    
     --RAISE NOTICE 'La salida es: %', vQUERY1;
     EXECUTE vQUERY2;

END;

$$ LANGUAGE plpgsql;

/*
*******************************************************************************
Creado: Eddy Valero Fecha:29/08/2016
Realizar el registro de la direccion de notificacion
*/

CREATE OR REPLACE FUNCTION reg_sigdireccionnotificacion (
  pidsignomarca bigint,
  pidlogtrans bigint,
  pciudad_notificacion character varying(4),
  pzona_barrio character varying(500),
  pavenida_calle character varying(50),
  pnumero character varying(20),
  pedificio character varying(50),
  ppiso character varying(20),
  pdepartamento character varying(10),
  preferencia_direccion character varying(250),
  pcorreo_electronico character varying(50),
  ptelefono character varying(100),
  pcelular character varying(100),
  pestado character varying(2)
  )
RETURNS SETOF sigdirecciondenotificacion AS
$BODY$
/*
Creado: Eddy Valero Fecha:29/08/2016
Realizar el registro de la direccion de notificacion
*/
DECLARE
	vsec_sigdirecciondenotificacion bigint;
	vsigdirecciondenotificacion sigdirecciondenotificacion%ROWTYPE;
BEGIN

	insert into sigdirecciondenotificacion
	(idsignomarca, idlogtrans, ciudad_notificacion, zona_barrio, avenida_calle, numero, edificio, piso, departamento, referencia_direccion, correo_electronico, telefono, celular, estado)
	values
	(pidsignomarca, pidlogtrans, pciudad_notificacion, pzona_barrio, pavenida_calle, pnumero, pedificio, ppiso, pdepartamento, preferencia_direccion, pcorreo_electronico, ptelefono, pcelular, pestado);

	vsec_sigdirecciondenotificacion = (select currval('sec_sigdirecciondenotificacion'));

	for vsigdirecciondenotificacion in (
				select * from sigdirecciondenotificacion
				where iddirecciondenotificacion = vsec_sigdirecciondenotificacion
				)loop
					return next vsigdirecciondenotificacion;
	end loop;
				
END;

$BODY$
  LANGUAGE plpgsql;



/*
********************************************************************************************************
Creado: Eddy Valero Fecha:29/08/2016
Realizar el registro de una prioridad o preferencia
*/
CREATE OR REPLACE FUNCTION reg_sigprioridadpreferencia(
	pidsignomarca bigint,
	pidlogtrans bigint,
	ptipo_prioridad character varying,
	ptipo_interes character varying,
	pnombre_numero character varying,
	pfecha timestamp without time zone,
	plugar character varying,
	pestado character varying,
    pid_sipi bigint)
    RETURNS SETOF sigprioridadpreferencia 
    LANGUAGE 'plpgsql'

    COST 100
    VOLATILE 
    ROWS 1000
AS $BODY$

/*
Creado: Eddy Valero Fecha:29/08/2016
Modificado: Placido Castro Fecha:08/12/2017
Realizar el registro de una prioridad o preferencia
*/
DECLARE
	vsec_sigprioridadpreferencia bigint;
	vsigprioridadpreferencia sigprioridadpreferencia%ROWTYPE;
BEGIN

	insert into sigprioridadpreferencia
	(idsignomarca, idlogtrans, tipo_prioridad, tipo_interes, nombre_numero, fecha, lugar, estado, id_sipi)
	values
	(pidsignomarca, pidlogtrans, ptipo_prioridad, ptipo_interes, pnombre_numero, pfecha, plugar, pestado, pid_sipi);

	vsec_sigprioridadpreferencia = (select currval('sec_sigprioridadpreferencia'));

	for vsigprioridadpreferencia in (
				select * from sigprioridadpreferencia
				where idprioridadpreferencia = vsec_sigprioridadpreferencia
				)loop
					return next vsigprioridadpreferencia;
	end loop;
				
END;

$BODY$
  LANGUAGE plpgsql;



/*
********************************************************************************************************
Creado: Eddy Valero Fecha:29/08/2016
Realizar el registro de un solicitante o apoderado
*/
CREATE OR REPLACE FUNCTION reg_sigsolicitanteapoderado(
	pidsignomarca bigint,
	pidlogtrans bigint,
	ptipo_titular character varying,
	ptipo_persona character varying,
	pnombre_razonsocial character varying,
	pprimer_apellido character varying,
	psegundo_apellido character varying,
	pnumero_documento character varying,
	ptipo_documento character varying,
	plugar_expedicion character varying,
	ppais character varying,
	pgenero character varying,
	psolicitud_departamento character varying,
	ppoder character varying,
	pdireccion character varying,
	ptelefono character varying,
	pcelular character varying,
	pemail character varying,
	pfax character varying,
	pestado character varying,
    pid_sipi bigint)
    RETURNS SETOF sigsolicitanteapoderado 
    LANGUAGE 'plpgsql'

    COST 100
    VOLATILE 
    ROWS 1000
AS $BODY$

/*
Creado: Eddy Valero Fecha:29/08/2016
Modificado: Placido Castro Fecha:08/12/2017
Realizar el registro de un solicitante o apoderado
*/
DECLARE
	vsec_sigsolicitanteapoderado bigint;
	vsigsolicitanteapoderado sigsolicitanteapoderado%ROWTYPE;
BEGIN

	insert into sigsolicitanteapoderado
	(idsignomarca, idlogtrans, tipo_titular, tipo_persona, nombre_razonsocial, primer_apellido, segundo_apellido, numero_documento, tipo_documento, lugar_expedicion, pais, genero, solicitud_departamento, poder, direccion, telefono, celular, email, fax, estado, id_sipi)
	values
	(pidsignomarca, pidlogtrans, ptipo_titular, ptipo_persona, pnombre_razonsocial, pprimer_apellido, psegundo_apellido, pnumero_documento, ptipo_documento, plugar_expedicion, ppais, pgenero, psolicitud_departamento, ppoder, pdireccion, ptelefono, pcelular, pemail, pfax, pestado, pid_sipi);

	vsec_sigsolicitanteapoderado = (select currval('sec_sigsolicitanteapoderado'));

	for vsigsolicitanteapoderado in (
				select * from sigsolicitanteapoderado
				where idsolicitanteapoderado = vsec_sigsolicitanteapoderado
				)loop
					return next vsigsolicitanteapoderado;
	end loop;
				
END;

$BODY$
  LANGUAGE plpgsql;

/*
********************************************************************************************************
Creado: Eddy Valero Fecha: 26/08/2016
Obtener un registro de la claseniza
*/
CREATE OR REPLACE FUNCTION obt_claseniza(pnumeroclaseniza bigint)
  RETURNS SETOF claseniza AS
$BODY$
/*
Creado: Eddy Valero Fecha: 26/08/2016
Obtener un registro de la claseniza
*/

 DECLARE 
    cniza claseniza%ROWTYPE; 
BEGIN

for cniza in (
	select *
	from claseniza
	where numero_clase_niza = pnumeroclaseniza
	and estado = 'AC'
	) loop
    return next cniza;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;


/*
********************************************************************************************************
Creado: Eddy Valero Fecha: 26/08/2016
Realizar el registro de la relacion signoclaseniza
*/
CREATE OR REPLACE FUNCTION reg_signoclaseniza(
	pidsignomarca bigint,
	pidclaseniza bigint,
	pidlogtrans bigint,
	pnumero_clase_niza bigint,
	plista_producto text,
	pestado character varying,
	pid_sipi bigint)
    RETURNS SETOF sigsignoclaseniza 
    LANGUAGE 'plpgsql'

    COST 100
    VOLATILE 
    ROWS 1000
AS $BODY$

/*
Creado: Eddy Valero Fecha: 26/08/2016
Modificado: Placido Castro Fecha:08/12/2017
Realizar el registro de la relacion signoclaseniza
 */
DECLARE
	vsec_sigsignoclaseniza bigint;
	vsigsignoclaseniza sigsignoclaseniza%ROWTYPE;
BEGIN

	insert into sigsignoclaseniza
	(idsignomarca, idclaseniza, idlogtrans, numero_clase_niza, lista_producto, estado, id_sipi)
	values
	(pidsignomarca, pidclaseniza, pidlogtrans, pnumero_clase_niza, plista_producto, pestado, pid_sipi);

	vsec_sigsignoclaseniza = (select currval('sec_sigsignoclaseniza'));

	for vsigsignoclaseniza in (
				select * from sigsignoclaseniza
				where idsignoclaseniza = vsec_sigsignoclaseniza
				)loop
					return next vsigsignoclaseniza;
	end loop;
				
END;

$BODY$
  LANGUAGE plpgsql;


/*
********************************************************************************************************
Creado: Eddy Valero Fecha: 25/08/2016
Realizar el registro en tiposigno
*/
CREATE OR REPLACE FUNCTION reg_tiposigno(
  pidsignomarca bigint,
  pidlogtrans bigint,
  ptipo_signo character varying(4),
  pdescripcion_otro character varying(100),
  pestado character varying(2)
  )
  RETURNS SETOF sigtiposigno AS
$BODY$
/*
Creado: Eddy Valero Fecha: 25/08/2016
Realizar el registro en tiposigno
*/
DECLARE
	vsec_sigtiposigno bigint;
	vsigtiposigno sigtiposigno%ROWTYPE;
BEGIN

	insert into sigtiposigno
	(idsignomarca, idlogtrans, tipo_signo, descripcion_otro, estado)
	values
	(pidsignomarca, pidlogtrans, ptipo_signo, pdescripcion_otro, pestado);

	vsec_sigtiposigno = (select currval('sec_sigtiposigno'));

	for vsigtiposigno in (
				select * from sigtiposigno
				where idtiposigno = vsec_sigtiposigno
				)loop
					return next vsigtiposigno;
	end loop;
				
	

END;

$BODY$
  LANGUAGE plpgsql;


/*
********************************************************************************************************
Creado: Eddy Valero Fecha: 25/08/2016
Realizar el registro en signomarca
*/
CREATE OR REPLACE FUNCTION reg_signomarca(
  pidlogtrans bigint,
  pnumero_formulario character varying(20),
  psm bigint,
  psigno character varying(1000),
  ptipo_genero character varying(4),
  pdescripcion_signo character varying(2500),
  pdescripcion_logotipo character varying(4000),
  pubicacion character varying(4),
  pestado_marca character varying(4),
  pnro_recibo bigint,
  pserie character varying(5),
  pnumero_titulo bigint,
  pserie_titulo character varying(4),
  porigen_solicitud character varying(20),
  pnumero_gaceta bigint,
  pnumero_publicacion bigint,
  pfecha_publicacion timestamp without time zone,
  pnumero_registro bigint,
  pserie_registro character varying(2),
  presolucion_registro character varying(9),
  pfecha_registro timestamp without time zone,
  porden_renovacion integer,
  pnumero_renovacion integer,
  pextension_renovacion character varying(5),
  pnumero_resolucion_renovacion bigint,
  pfecha_renovacion timestamp without time zone,
  poficina character varying(4),
  pfecha_solicitud timestamp without time zone,
  pfecha_ingreso timestamp without time zone,
  pentregado_usuario boolean,
  pnotoriedad_marca boolean,
  pestado character varying(2),
  pnumero integer,
  pgestion integer,
  pextension_marca integer
  )
  RETURNS SETOF sigsignomarca AS
$BODY$
/*
Creado: Eddy Valero Fecha: 25/08/2016
Realizar el registro en signomarca
*/
DECLARE
	vsec_sigsignomarca bigint;
	vsigsignomarca sigsignomarca%ROWTYPE;
BEGIN
	insert into sigsignomarca
	(idlogtrans, numero_formulario, sm, signo, tipo_genero, descripcion_signo, descripcion_logotipo, ubicacion, estado_marca, nro_recibo, serie, numero_titulo, serie_titulo, origen_solicitud, numero_gaceta, numero_publicacion, fecha_publicacion, numero_registro, serie_registro, resolucion_registro, fecha_registro, orden_renovacion, numero_renovacion, extension_renovacion, numero_resolucion_renovacion, fecha_renovacion, oficina, fecha_solicitud, fecha_ingreso, entregado_usuario, notoriedad_marca, estado, numero, gestion, extension_marca)
	values
	(pidlogtrans, pnumero_formulario, psm, psigno, ptipo_genero, pdescripcion_signo, pdescripcion_logotipo, pubicacion, pestado_marca, pnro_recibo, pserie, pnumero_titulo, pserie_titulo, porigen_solicitud, pnumero_gaceta, pnumero_publicacion, pfecha_publicacion, pnumero_registro, pserie_registro, presolucion_registro, pfecha_registro, porden_renovacion, pnumero_renovacion, pextension_renovacion, pnumero_resolucion_renovacion, pfecha_renovacion, poficina, pfecha_solicitud, pfecha_ingreso, pentregado_usuario, pnotoriedad_marca, pestado, pnumero, pgestion, pextension_marca);

	vsec_sigsignomarca = (select currval('sec_sigsignomarca'));

	for vsigsignomarca in (
				select * from sigsignomarca
				where idsignomarca = vsec_sigsignomarca
				)loop
					return next vsigsignomarca;
	end loop;
				
	

END;

$BODY$
  LANGUAGE plpgsql;




/*
********************************************************************************
Creado: Eddy Valero Fecha:30/07/2016
Obtener registros a partir de un determinado dominio
*/
CREATE OR REPLACE FUNCTION obt_lista_dominio(pdominio character varying)
  RETURNS SETOF dominio AS
$BODY$
/*
Creado: Eddy Valero Fecha:30/07/2016
Obtener registros a partir de un determinado dominio
*/

 DECLARE 
    sm dominio%ROWTYPE; 
BEGIN

for sm in select * from dominio where  dominio = pdominio and estado='AC' order by iddominio asc loop
    return next sm;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;



/*
********************************************************************************
Creado: Levi Laura Fecha:30/07/2016
Elimina un registro filtrado por bloque y id_usuario_solicitante
*/
CREATE OR REPLACE FUNCTION noti_eliminaregistroxbloquexusuario(
    pbloque bigint,
    pidusuario bigint)
  RETURNS void AS
$BODY$
/*

Creado: Levi Laura Fecha:30/07/2016
Elimina un registro filtrado por bloque y id_usuario_solicitante
*/

DECLARE
BEGIN
  delete from notificacion where bloque=pbloque and id_usuario_solicitante=pidusuario;
END;
$BODY$
  LANGUAGE plpgsql;

/*
********************************************************************************
Creado: Levi Laura Fecha:30/07/2016
Elimina un registro por idnotificacion
*/


CREATE OR REPLACE FUNCTION noti_eliminaregistroxidnotificacion(pidnotificacion bigint)
  RETURNS void AS
$BODY$
/*

Creado: Levi Laura Fecha:30/07/2016
Elimina un registro por idnotificacion
*/
DECLARE
BEGIN
  delete from notificacion where idnotificacion=pidnotificacion;
END;
$BODY$
  LANGUAGE plpgsql;

/*
********************************************************************************
Creado: Levi Laura Fecha:30/07/2016
Obtiene registros de notificacion dado el numero de bloque y id_usuario_solicitante
*/


CREATE OR REPLACE FUNCTION noti_getnotificacionxbloquexusuariosol(
    pbloque integer,
    pidusuario bigint)
  RETURNS SETOF notificacion AS
$BODY$
/*

Creado: Levi Laura Fecha:30/07/2016
Obtiene registros de notificacion dado el numero de bloque y id_usuario_solicitante
*/
 DECLARE 
    sm notificacion%ROWTYPE; 
BEGIN

for sm in select * from notificacion where bloque=pbloque and id_usuario_solicitante=pidusuario loop
    return next sm;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;


/*
********************************************************************************
Creado: Levi Laura Fecha:30/07/2016
Obtiene el nuevo numero de bloque para nuevas peticiones, segun el id_usuario_solicitante
*/

CREATE OR REPLACE FUNCTION noti_getnumerobloquenuevo(in_id_usuario_solicitante bigint)
  RETURNS bigint AS
$BODY$
/*

Creado: Levi Laura Fecha:30/07/2016
Obtiene el nuevo numero de bloque para nuevas peticiones, segun el id_usuario_solicitante
*/
DECLARE
bloqueresul bigint;

BEGIN

  select bloque into bloqueresul from notificacion where id_usuario_solicitante=in_id_usuario_solicitante order by bloque desc, nro_exped desc;
  bloqueresul:=bloqueresul+1;
 
  return bloqueresul;
END;
$BODY$
  LANGUAGE plpgsql;

/*
********************************************************************************
Creado: Levi Laura Fecha:30/07/2016
Inserta un registor de notificacion
*/
CREATE OR REPLACE FUNCTION noti_insertanotificacion(
    idlogtrans bigint,
    bloque integer,
    nro_exped integer,
    id_usuario_solicitante bigint,
    tipo_tramite_notificacion character varying,
    expediente character varying,
    gestion integer,
    extension character varying,
    demandante character varying,
    demandante_cod_estado character varying,
    demandante_fecha_devol timestamp without time zone,
    demandante_fecha_noti timestamp without time zone,
    demandante_lugar_notificacion character varying,
    demandante_solic character varying,
    demandante_apod character varying,
    demandante_fojas character varying,
    demandante_con character varying,
    demandante_direc character varying,
    demandante_cel character varying,
    demandado character varying,
    demandado_cod_estado character varying,
    demandado_fecha_devol timestamp without time zone,
    demandado_fecha_noti timestamp without time zone,
    demandado_lugar_notificacion character varying,
    demandado_solic character varying,
    demandado_apod character varying,
    demandado_fojas character varying,
    demandado_con character varying,
    demandado_direc character varying,
    demandado_cel character varying,
    fecha_ingreso timestamp without time zone,
    tipo integer,
    obs text,
    historial text,
    fecha_recep timestamp without time zone,
    obs_notifi text,
    id_usuario_notificador bigint,
    ciudad_notificacion character varying,
    estado_marca character varying,
    form_noti_pre text,
    form_noti_cuerpo text)
  RETURNS SETOF notificacion AS
$BODY$
/*

Creado: Levi Laura Fecha:30/07/2016
Inserta un registor de notificacion
*/
DECLARE
seq_notificacion bigint;
obj_notificacion notificacion%ROWTYPE;
BEGIN


insert into notificacion(idlogtrans,bloque,nro_exped,id_usuario_solicitante,tipo_tramite_notificacion,expediente,gestion,extension,demandante,demandante_cod_estado,demandante_fecha_devol,
                          demandante_fecha_noti,demandante_lugar_notificacion,demandante_solic,demandante_apod,demandante_fojas,demandante_con,demandante_direc,demandante_cel,demandado,
                          demandado_cod_estado,demandado_fecha_devol,demandado_fecha_noti,demandado_lugar_notificacion,demandado_solic,demandado_apod,demandado_fojas,demandado_con,demandado_direc,
                          demandado_cel,fecha_ingreso,tipo,obs,historial,fecha_recep,obs_notifi,id_usuario_notificador ,ciudad_notificacion,estado_marca,form_noti_pre,form_noti_cuerpo)

                          values(idlogtrans,bloque,nro_exped,id_usuario_solicitante,tipo_tramite_notificacion,expediente,gestion,extension,demandante,demandante_cod_estado,demandante_fecha_devol,
                          demandante_fecha_noti,demandante_lugar_notificacion,demandante_solic,demandante_apod,demandante_fojas,demandante_con,demandante_direc,demandante_cel,demandado,
                          demandado_cod_estado,demandado_fecha_devol,demandado_fecha_noti,demandado_lugar_notificacion,demandado_solic,demandado_apod,demandado_fojas,demandado_con,demandado_direc,
                          demandado_cel,fecha_ingreso,tipo,obs,historial,fecha_recep,obs_notifi,id_usuario_notificador ,ciudad_notificacion,estado_marca,form_noti_pre,form_noti_cuerpo);

seq_notificacion = (select currval('sec_notificacion'));


for obj_notificacion in (select *
 from notificacion
 where idnotificacion = seq_notificacion
 ) loop
return next obj_notificacion;
end loop;

       
END;
$BODY$
  LANGUAGE plpgsql;

/*
********************************************************************************
Creado: Levi Laura Fecha:30/07/2016
Obtiene un listado de la tabla dominio dado el nombre de dominio y el codigo
*/
CREATE OR REPLACE FUNCTION obt_lista_dominioxcodigo(
    pdominio character varying,
    pcodigo character varying)
  RETURNS SETOF dominio AS
$BODY$
/*

Creado: Levi Laura Fecha:30/07/2016
Obtiene un listado de la tabla dominio dado el nombre de dominio y el codigo
*/
 DECLARE 
    sm dominio%ROWTYPE; 
BEGIN

for sm in select * from dominio where  dominio = pdominio and codigo= pcodigo  and estado ='AC' loop
    return next sm;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;

/*
********************************************************************************
Creado: Susana Escobar Fecha:06/09/2016
Modificado: Susana Escobar Fecha:15/09/2016
Realizar registro en tabla modmodificacion
*/

CREATE OR REPLACE FUNCTION crud_modmodificacion(
  pidmodificacion bigint,
  pidlogtrans bigint,
  psigla character varying,
  pnumero bigint,
  pgestion integer,
  psm bigint,
  pfecha_ingreso timestamp without time zone,
  pnro_formulario character varying,
  poficina character varying,
  pnumero_registro bigint,
  pserie_registro character varying,
  pnumero_renovacion bigint,
  pserie_renovacion character varying,
  pnumero_publicacion bigint,
  psigno_registrado character varying,
  pclase_registrado integer,
  ptipo_genero_registrado character varying,
  pmarca_acomp character varying,
  preg_lc_registrado bigint,
  pserie_lc_registrado character varying,
  pfecha_lc_registrado timestamp without time zone,
  pnuevo_domicilio character varying,
  pnueva_nacionalidad character varying,
  pnuevo_pais_constitucion character varying,
  pnuevo_departamento character varying,
  pluinicio timestamp without time zone,
  plu_fin timestamp without time zone,
  pfecha_ultima_operacion timestamp without time zone,
  pusuario bigint,
  ptitular_signo character varying,
  pnacionalidad_signo character varying,
  pdepartamento_signo character varying,
  pdomicilio_signo character varying,
  ptelefono_signo character varying,
  pfax_signo character varying,
  pemail_signo character varying,
  ptipo_modificacion character varying,
  pestado_modificacion character varying,
  pubicacion_modificacion character varying,
  plista_producto text,
  pestado character varying,
  pnro_recibo bigint,
  pserie_recibo character varying,
    operacion integer)
  RETURNS SETOF modmodificacion AS
$BODY$
/*
********************************************************************************
Creado: Susana Escobar Fecha:06/09/2016
Modificado: Susana Escobar Fecha:15/09/2016
Realizar registro en tabla modmodificacion
*/
DECLARE
seq_modmodificacion bigint;
vmodificacion modmodificacion%ROWTYPE;
BEGIN

if operacion=1 then -- Create
insert into modmodificacion(    
  
  idlogtrans,
  sigla,
  numero,
  gestion,
  sm,
  fecha_ingreso,
  nro_formulario,
  oficina,
  numero_registro,
  serie_registro,
  numero_renovacion,
  serie_renovacion,
  numero_publicacion,
  signo_registrado,
  clase_registrado,
  tipo_genero_registrado,
  marca_acomp,
  reg_lc_registrado,
  serie_lc_registrado,
  fecha_lc_registrado,
  nuevo_domicilio,
  nueva_nacionalidad,
  nuevo_pais_constitucion,
  nuevo_departamento,
  luinicio,
  lu_fin,
  fecha_ultima_operacion,
  usuario,
  titular_signo,
  nacionalidad_signo,
  departamento_signo,
  domicilio_signo,
  telefono_signo,
  fax_signo,
  email_signo,
  tipo_modificacion,
  estado_modificacion,
  ubicacion_modificacion,
  lista_producto,
  estado,
  nro_recibo,
  serie_recibo
)
values(         
  pidlogtrans,
  psigla,
  pnumero,
  pgestion,
  psm,
  pfecha_ingreso,
  pnro_formulario,
  poficina,
  pnumero_registro,
  pserie_registro,
  pnumero_renovacion,
  pserie_renovacion,
  pnumero_publicacion,
  psigno_registrado,
  pclase_registrado,
  ptipo_genero_registrado,
  pmarca_acomp,
  preg_lc_registrado,
  pserie_lc_registrado,
  pfecha_lc_registrado,
  pnuevo_domicilio,
  pnueva_nacionalidad,
  pnuevo_pais_constitucion,
  pnuevo_departamento,
  pluinicio,
  plu_fin,
  pfecha_ultima_operacion,
  pusuario,
  ptitular_signo,
  pnacionalidad_signo,
  pdepartamento_signo,
  pdomicilio_signo,
  ptelefono_signo,
  pfax_signo,
  pemail_signo,
  ptipo_modificacion,
  pestado_modificacion,
  pubicacion_modificacion,
  plista_producto,
  pestado,
  pnro_recibo,
  pserie_recibo
);
seq_modmodificacion = (select currval('sec_modmodificacion')) ;
for vmodificacion in (select *
 from modmodificacion
 where idmodificacion = seq_modmodificacion
 ) loop
return next vmodificacion;
end loop;
end if;----fin create


if operacion=2 then -- update
update modmodificacion set
	  idmodificacion= pidmodificacion,
	  idlogtrans= pidlogtrans,
	  sigla= psigla,
	  numero= pnumero,
	  gestion= pgestion,
	  sm= psm,
	  fecha_ingreso= pfecha_ingreso,
	  nro_formulario= pnro_formulario,
	  oficina= poficina,
	  numero_registro= pnumero_registro,
	  serie_registro= pserie_registro,
	  numero_renovacion= pnumero_renovacion,
	  serie_renovacion= pserie_renovacion,
	  numero_publicacion= pnumero_publicacion,
	  signo_registrado= psigno_registrado,
	  clase_registrado= pclase_registrado,
	  tipo_genero_registrado= ptipo_genero_registrado,
	  marca_acomp= pmarca_acomp,
	  reg_lc_registrado= preg_lc_registrado,
	  serie_lc_registrado= pserie_lc_registrado,
	  fecha_lc_registrado= pfecha_lc_registrado,
	  nuevo_domicilio= pnuevo_domicilio,
	  nueva_nacionalidad= pnueva_nacionalidad,
	  nuevo_pais_constitucion= pnuevo_pais_constitucion,
	  nuevo_departamento= pnuevo_departamento,
	  luinicio= pluinicio,
	  lu_fin= plu_fin,
	  fecha_ultima_operacion= pfecha_ultima_operacion,
	  usuario= pusuario,
	  titular_signo= ptitular_signo,
	  nacionalidad_signo= pnacionalidad_signo,
	  departamento_signo= pdepartamento_signo,
	  domicilio_signo= pdomicilio_signo,
	  telefono_signo= ptelefono_signo,
	  fax_signo= pfax_signo,
	  email_signo= pemail_signo,
	  tipo_modificacion= ptipo_modificacion,
	  estado_modificacion= pestado_modificacion,
	  ubicacion_modificacion= pubicacion_modificacion,
	  lista_producto= plista_producto,
	  estado= pestado,
	  nro_recibo= pnro_recibo,
	  serie_recibo= pserie_recibo
		
	where 	idmodificacion = pidmodificacion;
	
--seq_modmodificacion = (select currval('sec_modmodificacion')) ;
for vmodificacion in (select *
 from modmodificacion
 where idmodificacion = pidmodificacion
 ) loop
return next vmodificacion;
end loop;
end if;----fin update

if operacion =3 then-- delete
     delete from modmodificacion where idmodificacion = pidmodificacion;
end if; ---fin delete


if operacion=4 then --read
    for vmodificacion in (select *
 from modmodificacion
 where idmodificacion = pidmodificacion
 ) loop
return next vmodificacion;
end loop;
 
end if; ---fin read
       
END;
$BODY$
  LANGUAGE plpgsql;


/*
********************************************************************************
Creado: Susana Escobar Fecha:06/09/2016
Realizar registro en tabla modsolicitanteapoderado
*/
CREATE OR REPLACE FUNCTION crud_modsolicitanteapoderado(
	pidsolicitanteapoderado bigint,
	pidmodificacion bigint,
	pidlogtrans bigint,
	ptipo_titular character varying,
	ptipo_persona character varying,
	pnombre_razonsocial character varying,
	pprimer_apellido character varying,
	psegundo_apellido character varying,
	pnumero_documento character varying,
	ptipo_documento character varying,
	plugar_expedicion character varying,
	ppais character varying,
	pgenero character varying,
	psolicitud_departamento character varying,
	ppoder character varying,
	pdireccion character varying,
	ptelefono character varying,
	pcelular character varying,
	pemail character varying,
	pfax character varying,
	pestado character varying,
    pid_sipi bigint,
	operacion integer)
    RETURNS SETOF modsolicitanteapoderado 
    LANGUAGE 'plpgsql'

    COST 100
    VOLATILE 
    ROWS 1000
AS $BODY$

/*
********************************************************************************
Creado: Susana Escobar Fecha:06/09/2016
Modificado: Placido Castro Fecha:08/12/2017
Realizar registro en tabla modsolicitanteapoderado
*/
DECLARE
seq_modsolicitanteapoderado bigint;
vsolicitanteapoderado modsolicitanteapoderado%ROWTYPE;
BEGIN

if operacion=1 then -- Create
insert into modsolicitanteapoderado(
  idmodificacion,
  idlogtrans,
  tipo_titular,
  tipo_persona,
  nombre_razonsocial,
  primer_apellido,
  segundo_apellido,
  numero_documento,
  tipo_documento,
  lugar_expedicion,
  pais,
  genero,
  solicitud_departamento,
  poder,
  direccion,
  telefono,
  celular,
  email,
  fax,
  estado,
  id_sipi
)
values(  
  pidmodificacion,
  pidlogtrans,
  ptipo_titular,
  ptipo_persona,
  pnombre_razonsocial,
  pprimer_apellido,
  psegundo_apellido,
  pnumero_documento,
  ptipo_documento,
  plugar_expedicion,
  ppais,
  pgenero,
  psolicitud_departamento,
  ppoder,
  pdireccion,
  ptelefono,
  pcelular,
  pemail,
  pfax,
  pestado,
  pid_sipi
);
seq_modsolicitanteapoderado = (select currval('sec_modsolicitanteapoderado')) ;
for vsolicitanteapoderado in (select *
 from modsolicitanteapoderado
 where idsolicitanteapoderado = seq_modsolicitanteapoderado
 ) loop
return next vsolicitanteapoderado;
end loop;
end if;----fin create

if operacion=2 then -- update
update modsolicitanteapoderado set
  idsolicitanteapoderado =   pidsolicitanteapoderado,
  idmodificacion  =   pidmodificacion,
  idlogtrans =   pidlogtrans,
  tipo_titular =   ptipo_titular,
  tipo_persona =   ptipo_persona,
  nombre_razonsocial =   pnombre_razonsocial,
  primer_apellido =   pprimer_apellido,
  segundo_apellido =   psegundo_apellido,
  numero_documento =   pnumero_documento,
  tipo_documento =   ptipo_documento,
  lugar_expedicion =   plugar_expedicion,
  pais =   ppais,
  genero =   pgenero,
  solicitud_departamento = psolicitud_departamento,
  poder =   ppoder,
  direccion =   pdireccion,
  telefono =   ptelefono,
  celular =   pcelular,
  email =   pemail,
  fax =   pfax,
  estado =   pestado,
  id_sipi = pid_sipi
    where   idsolicitanteapoderado = pidsolicitanteapoderado;
  

for vsolicitanteapoderado in (select *
 from modsolicitanteapoderado
 where idsolicitanteapoderado = pidsolicitanteapoderado
 ) loop
return next vsolicitanteapoderado;
end loop;
end if;----fin update

if operacion =3 then-- delete
     delete from modsolicitanteapoderado 
      where idsolicitanteapoderado = pidsolicitanteapoderado;
end if; ---fin delete

if operacion=4 then --read
for vsolicitanteapoderado in (select *
 from modsolicitanteapoderado
 where idsolicitanteapoderado = pidsolicitanteapoderado
 ) loop
return next vsolicitanteapoderado;
end loop;
 
end if; ---fin read
       
END;

$BODY$
LANGUAGE plpgsql;


/*
********************************************************************************
Creado: Susana Escobar Fecha:06/09/2016
Modificado: Susana Escobar Fecha:15/09/2016
Modificado: Susana Escobar Fecha:14/10/2016
Realizar registro en tabla modtitularlicenciatarionuevo
*/
CREATE OR REPLACE FUNCTION crud_modtitularlicenciatarionuevo(
	pidtitularlicenciatario bigint,
	pidmodificacion bigint,
	pidlogtrans bigint,
	ptipo_persona character varying,
	ptipo_titular character varying,
	pnombre_razonsocial character varying,
	pprimer_apellido character varying,
	psegundo_apellido character varying,
	pnumero_documento character varying,
	ptipo_documento character varying,
	plugar_expedicion character varying,
	ppais character varying,
	ppais_constitucion character varying,
	pgenero character varying,
	psolicitud_departamento character varying,
	pdireccion character varying,
	ptelefono character varying,
	pcelular character varying,
	pemail character varying,
	pfax character varying,
	pestado character varying,
	pmodificar boolean,
	pid_referencia bigint,
    pid_sipi bigint,
	operacion integer)
    RETURNS SETOF modtitularlicenciatarionuevo 
    LANGUAGE 'plpgsql'

    COST 100
    VOLATILE 
    ROWS 1000
AS $BODY$

/*
Creado: Susana Escobar Fecha:06/09/2016
Modificado: Susana Escobar Fecha:15/09/2016
Modificado: Susana Escobar Fecha:14/10/2016
Modificado: Placido Castro Fecha:08/12/2017
Realizar registro en tabla modtitularlicenciatarionuevo
*/
DECLARE
seq_modtitularlicenciatarionuevo bigint;
vtitularlicenciatarionuevo modtitularlicenciatarionuevo%ROWTYPE;
BEGIN

if operacion=1 then -- Create
insert into modtitularlicenciatarionuevo(
  idmodificacion,
  idlogtrans,
  tipo_persona,
  tipo_titular,
  nombre_razonsocial,
  primer_apellido,
  segundo_apellido,
  numero_documento,
  tipo_documento,
  lugar_expedicion,
  pais,
  pais_constitucion,
  genero,
  solicitud_departamento,
  direccion,
  telefono,
  celular,
  email,
  fax,
  estado,
  modificar,
  id_referencia,
  id_sipi
)
values(  
  pidmodificacion,
  pidlogtrans,
  ptipo_persona,
  ptipo_titular,
  pnombre_razonsocial,
  pprimer_apellido,
  psegundo_apellido,
  pnumero_documento,
  ptipo_documento,
  plugar_expedicion,
  ppais,
  ppais_constitucion,
  pgenero,
  psolicitud_departamento,
  pdireccion,
  ptelefono,
  pcelular,
  pemail,
  pfax,
  pestado,
  pmodificar,
  pid_referencia,
  pid_sipi
);
seq_modtitularlicenciatarionuevo = (select currval('sec_modtitularlicenciatarionuevo')) ;
for vtitularlicenciatarionuevo in (select *
 from modtitularlicenciatarionuevo
 where idtitularlicenciatario = seq_modtitularlicenciatarionuevo
 ) loop
return next vtitularlicenciatarionuevo;
end loop;
end if;----fin create

if operacion=2 then -- update
update modtitularlicenciatarionuevo set
    idtitularlicenciatario = pidtitularlicenciatario,
    idmodificacion = pidmodificacion,
    idlogtrans = pidlogtrans,
    tipo_persona = ptipo_persona,
    tipo_titular = ptipo_titular,
    nombre_razonsocial = pnombre_razonsocial,
    primer_apellido = pprimer_apellido,
    segundo_apellido = psegundo_apellido,
    numero_documento = pnumero_documento,
    tipo_documento = ptipo_documento,
    lugar_expedicion = plugar_expedicion,
    pais = ppais,
    pais_constitucion = ppais_constitucion,
    genero = pgenero,
    solicitud_departamento = psolicitud_departamento,
    direccion = pdireccion,
    telefono = ptelefono,
    celular = pcelular,
    email = pemail,
    fax = pfax,
    estado = pestado,
    modificar = pmodificar,
    id_referencia = pid_referencia,
    id_sipi = pid_sipi
    where   idtitularlicenciatario = pidtitularlicenciatario;
  
for vtitularlicenciatarionuevo in (select *
 from modtitularlicenciatarionuevo
 where idtitularlicenciatario = pidtitularlicenciatario
 ) loop
return next vtitularlicenciatarionuevo;
end loop;
end if;----fin update

if operacion =3 then-- delete
     delete from modtitularlicenciatarionuevo 
      where idtitularlicenciatario = pidtitularlicenciatario;
end if; ---fin delete

if operacion=4 then --read
for vtitularlicenciatarionuevo in (select *
 from modtitularlicenciatarionuevo
 where idtitularlicenciatario = pidtitularlicenciatario
 ) loop
return next vtitularlicenciatarionuevo;
end loop;
 
end if; ---fin read
       
END;

$BODY$
LANGUAGE plpgsql;


/*
********************************************************************************
Creado: Susana Escobar Fecha:06/09/2016
Modificado: Susana Escobar Fecha: 14/09/2016
Modificado: Susana Escobar Fecha: 14/10/2016
Realizar registro en tabla modtitularlicenciatarioregistrado
*/
CREATE OR REPLACE FUNCTION crud_modtitularlicenciatarioregistrado(
	pidtitularlicenciatarioregistrado bigint,
	pidmodificacion bigint,
	pidlogtrans bigint,
	ptipo_persona_registrado character varying,
	ptipo_titular character varying,
	pnombre_razonsocial character varying,
	pprimer_apellido character varying,
	psegundo_apellido character varying,
	pdireccion character varying,
	pestado character varying,
    pid_sipi bigint,
	operacion integer)
    RETURNS SETOF modtitularlicenciatarioregistrado 
    LANGUAGE 'plpgsql'

    COST 100
    VOLATILE 
    ROWS 1000
AS $BODY$

/*
Creado: Susana Escobar Fecha:06/09/2016
Modificado: Susana Escobar Fecha: 14/09/2016
Modificado: Susana Escobar Fecha: 14/10/2016
Modificado: Placido Castro Fecha:08/12/2017
Realizar registro en tabla modtitularlicenciatarioregistrado
*/
DECLARE
seq_modtitularlicenciatarioregistrado bigint;
vtitularlicenciatarioregistrado modtitularlicenciatarioregistrado%ROWTYPE;
BEGIN

if operacion=1 then -- Create
insert into modtitularlicenciatarioregistrado(
  idmodificacion,
  idlogtrans,
  tipo_persona_registrado,
  tipo_titular,
  nombre_razonsocial,
  primer_apellido,
  segundo_apellido,
  direccion,
  estado,
  id_sipi
)
values(  
  pidmodificacion,
  pidlogtrans,
  ptipo_persona_registrado,
  ptipo_titular,
  pnombre_razonsocial,
  pprimer_apellido,
  psegundo_apellido,
  pdireccion,
  pestado,
  pid_sipi
);
seq_modtitularlicenciatarioregistrado = (select currval('sec_modtitularlicenciatarioregistrado')) ;
for vtitularlicenciatarioregistrado in (select *
 from modtitularlicenciatarioregistrado
 where idtitularlicenciatarioregistrado = seq_modtitularlicenciatarioregistrado
 ) loop
return next vtitularlicenciatarioregistrado;
end loop;
end if;----fin create

if operacion=2 then -- update
update modtitularlicenciatarioregistrado set
    idtitularlicenciatarioregistrado = pidtitularlicenciatarioregistrado,
    idmodificacion = pidmodificacion,
    idlogtrans = pidlogtrans,
    tipo_persona_registrado = tipo_persona_registrado,
    tipo_titular = tipo_titular,
    nombre_razonsocial = pnombre_razonsocial,
    primer_apellido = pprimer_apellido,
    segundo_apellido = psegundo_apellido,
    direccion = pdireccion,
    estado = pestado,
    id_sipi = pid_sipi
    where   idtitularlicenciatarioregistrado = pidtitularlicenciatarioregistrado;
  
for vtitularlicenciatarioregistrado in (select *
 from modtitularlicenciatarioregistrado
 where idtitularlicenciatarioregistrado = pidtitularlicenciatarioregistrado
 ) loop
return next vtitularlicenciatarioregistrado;
end loop;
end if;----fin update

if operacion =3 then-- delete
     delete from modtitularlicenciatarioregistrado 
      where idtitularlicenciatarioregistrado = pidtitularlicenciatarioregistrado;
end if; ---fin delete

if operacion=4 then --read
for vtitularlicenciatarioregistrado in (select *
 from modtitularlicenciatarioregistrado
 where idtitularlicenciatarioregistrado = pidtitularlicenciatarioregistrado
 ) loop
return next vtitularlicenciatarioregistrado;
end loop;
 
end if; ---fin read
       
END;

$BODY$
LANGUAGE plpgsql;

/*
********************************************************************************
Creado: Susana Escobar Fecha:06/09/2016
Realizar registro en tabla moddirecciondenotificacion
*/
CREATE OR REPLACE FUNCTION crud_moddirecciondenotificacion(
    piddirecciondenotificacion bigint,
    pidmodificacion bigint,
    pidlogtrans bigint,
    pciudad_notificacion character varying,
    pzona_barrio character varying,
    pavenida_calle character varying,
    pnumero character varying,
    pedificio character varying,
    ppiso character varying,
    pdepartamento character varying,
    pcorreo_electronico character varying,
    preferencia_direccion character varying,
    ptelefono character varying,
    pcelular character varying,
    pestado character varying,
    operacion integer)
  RETURNS SETOF moddirecciondenotificacion AS
$BODY$
/*
********************************************************************************
Creado: Susana Escobar Fecha:06/09/2016
Realizar registro en tabla moddirecciondenotificacion
*/
DECLARE
seq_moddirecciondenotificacion bigint;
vdirecciondenotificacion moddirecciondenotificacion%ROWTYPE;
BEGIN

if operacion=1 then -- Create
insert into moddirecciondenotificacion(
  idmodificacion,
  idlogtrans,
  ciudad_notificacion,
  zona_barrio,
  avenida_calle,
  numero,
  edificio,
  piso,
  departamento,
  correo_electronico,
  referencia_direccion,
  telefono,
  celular,
  estado
)
values(
  pidmodificacion,
  pidlogtrans,
  pciudad_notificacion,
  pzona_barrio,
  pavenida_calle,
  pnumero,
  pedificio,
  ppiso,
  pdepartamento,
  pcorreo_electronico,
  preferencia_direccion,
  ptelefono,
  pcelular,
  pestado
);
seq_moddirecciondenotificacion = (select currval('sec_moddirecciondenotificacion')) ;
for vdirecciondenotificacion in (select *
 from moddirecciondenotificacion
 where iddirecciondenotificacion = seq_moddirecciondenotificacion
 ) loop
return next vdirecciondenotificacion;
end loop;
end if;----fin create


if operacion=2 then -- update
update moddirecciondenotificacion set
  iddirecciondenotificacion = piddirecciondenotificacion, 
  idmodificacion =   pidmodificacion,
  idlogtrans =   pidlogtrans,
  ciudad_notificacion =   pciudad_notificacion,
  zona_barrio =   pzona_barrio,
  avenida_calle =   pavenida_calle,
  numero =   pnumero,
  edificio =   pedificio,
  piso =   ppiso,
  departamento =   pdepartamento,
  correo_electronico =   pcorreo_electronico,
  referencia_direccion = preferencia_direccion,
  telefono =   ptelefono,
  celular =   pcelular,
  estado =   pestado
  
  where   iddirecciondenotificacion = piddirecciondenotificacion;
  

for vdirecciondenotificacion in (select *
 from moddirecciondenotificacion
 where iddirecciondenotificacion = piddirecciondenotificacion
 ) loop
return next vdirecciondenotificacion;
end loop;
end if;----fin update

if operacion =3 then-- delete
     delete from moddirecciondenotificacion where iddirecciondenotificacion = piddirecciondenotificacion;
end if; ---fin delete


if operacion=4 then --read
for vdirecciondenotificacion in (select *
 from moddirecciondenotificacion
 where iddirecciondenotificacion = piddirecciondenotificacion
 ) loop
return next vdirecciondenotificacion;
end loop;
 
end if; ---fin read
       
END;
$BODY$
  LANGUAGE plpgsql ;

/*
********************************************************************************
Creado: Susana Escobar Fecha:30/08/2016
Listar modificacion por codigo (sigla, numero, gestion)
Modificado: 06/09/2016
*/
CREATE OR REPLACE FUNCTION lista_modmodificacion_codigo(
    psigla character varying,
    pnumero bigint,
    pgestion integer)
  RETURNS SETOF modmodificacion AS
$BODY$
/*
********************************************************************************
Creado: Susana Escobar Fecha:30/08/2016
Listar modificacion por codigo (sigla, numero, gestion)
*/
 DECLARE 
    vmodmodificacion modmodificacion%ROWTYPE; 
BEGIN

for vmodmodificacion in select * from modmodificacion where sigla=psigla and numero=pnumero
and gestion=pgestion and estado='AC' loop
    return next vmodmodificacion;
end loop;
end;
$BODY$
  LANGUAGE plpgsql; 
/*
********************************************************************************
Creado: Luis Angel Quispe Limachi:22/09/2016
Realizar Insercion de datos notificacion
*/

CREATE OR REPLACE FUNCTION inserta_oponotificacion(
  codidmarcademandada bigint,
  codidmarcademandante bigint,
  codciudad_notificacion character varying,
  codzona_barrio character varying,
  codavenida_calle character varying,
  codnumero character varying,
  codedificio character varying,
  codpiso character varying,
  codnumero_departamento character varying,
  codreferencia_direccion character varying,
  codemail character varying,
  codtelefono character varying,
  codcelular character varying,
  codestado character varying
)
  RETURNS SETOF oponotificacion AS
$BODY$
/*
********************************************************************************
Creado: Luis Angel Quispe Limachi:22/09/2016
Realizar Insercion de datos notificacion
*/
DECLARE
sec_oponotificacion bigint;
oponotificacionobj oponotificacion%ROWTYPE;
BEGIN
insert into oponotificacion(idmarcademandada, idmarcademandante, ciudad_notificacion, 
zona_barrio, avenida_calle, numero, edificio, piso, numero_departamento, 
referencia_direccion, email, telefono, celular, estado)
values(codidmarcademandada,codidmarcademandante,codciudad_notificacion,
  codzona_barrio,codavenida_calle,codnumero,codedificio,codpiso,
  codnumero_departamento,codreferencia_direccion,codemail,codtelefono,codcelular,
  codestado);

sec_oponotificacion = (select currval('sec_oponotificacion')) ;


for oponotificacionobj in (select *
 from oponotificacion
 where idnotificacion = sec_oponotificacion
 ) loop
return next oponotificacionobj;
end loop;




       
END;
$BODY$
  LANGUAGE plpgsql; 
  /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar Insercion de datos Marcademandada
*/
CREATE OR REPLACE FUNCTION inserta_marcademandada(
  codidoposicion bigint,
  codidlogtrans bigint,
  codnro_opo numeric,
  coddmdo_public numeric,
  codgaceta numeric,
  coddmdo_clase character varying,
  codfecha_public timestamp without time zone,
  coddmdo_marca_lnv character varying,
  coddmdo_sm character varying,   
  codfec_lim timestamp without time zone, 
  codverif boolean,
  codestado character varying
  )

  RETURNS SETOF opomarcademandada AS
$BODY$
  /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar Insercion de datos Marcademandada
*/

DECLARE
sec_opomarcademandada bigint;
opomarcademandadaobj opomarcademandada%ROWTYPE;
BEGIN

insert into opomarcademandada(
idoposicion, idlogtrans, nro_opo, dmdo_public, 
gaceta, dmdo_clase, fecha_public, dmdo_marca_lnv, dmdo_sm, 
fec_lim, verif,estado
)
values(
codidoposicion, codidlogtrans, codnro_opo, coddmdo_public, 
codgaceta, coddmdo_clase, codfecha_public, coddmdo_marca_lnv, coddmdo_sm, 
codfec_lim, codverif,codestado);

sec_opomarcademandada = (select currval('sec_opomarcademandada')) ;


for opomarcademandadaobj in (select *
 from opomarcademandada
 where idmarcademandada = sec_opomarcademandada
 ) loop
return next opomarcademandadaobj;
end loop;

       
END;
$BODY$
  LANGUAGE plpgsql; 
    
 /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar Insercion de datos Marcademandante
*/

CREATE OR REPLACE FUNCTION inserta_marcademandante(
    
  codidoposicion bigint,
  codidtramite bigint,
  codidpatente bigint,
  codidarea bigint,
  codidmarca bigint,
  codidlogtrans bigint,
  codorden_opo integer,
  coddmte_reg numeric,
  coddmte_serie character varying,
  coddmte_public numeric,
  coddmte_sm bigint,
  coddmte_sp numeric,
  coddmte_marca_lnv character varying,
  coddmte_uso character varying,
  coddmte_clase integer,
  codestado character varying
  )

  RETURNS SETOF opomarcademandante AS
$BODY$
    /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar Insercion de datos Marcademandante
*/
DECLARE
sec_opomarcademandante bigint;
opomarcademandanteobj opomarcademandante%ROWTYPE;
BEGIN

insert into opomarcademandante(
idoposicion, idtramite, idpatente, idarea, 
idmarca, idlogtrans, orden_opo, dmte_reg, dmte_serie, dmte_public, 
dmte_sm, dmte_sp, dmte_marca_lnv, dmte_uso, dmte_clase,estado
)
values(
codidoposicion, codidtramite, codidpatente, codidarea, 
codidmarca, codidlogtrans, codorden_opo, coddmte_reg, coddmte_serie, coddmte_public, 
coddmte_sm, coddmte_sp, coddmte_marca_lnv, coddmte_uso, coddmte_clase,codestado
  );

sec_opomarcademandante = (select currval('sec_opomarcademandante')) ;


for opomarcademandanteobj in (select *
 from opomarcademandante
 where idmarcademandante = sec_opomarcademandante
 ) loop
return next opomarcademandanteobj;
end loop;

       
END;
$BODY$
  LANGUAGE plpgsql;

      /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar Insercion de datos opoevento
*/
CREATE OR REPLACE FUNCTION inserta_opoevento(
 
  codidactividad bigint,
  codidoposicion bigint,
  codidlogtrans bigint,
  codfecha timestamp without time zone,
  codobservacion character varying,
  codusuario bigint,
  codorden_o integer,
  codfecha_operacion timestamp without time zone,
  codestado character varying

)
  RETURNS SETOF opoevento AS
$BODY$
      /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar Insercion de datos opoevento
*/
DECLARE
sec_opoevento bigint;
opoeventoobj opoevento%ROWTYPE;
BEGIN

insert into opoevento(
idactividad, idoposicion, idlogtrans, fecha, observacion, 
usuario, orden_o, fecha_operacion,estado)
values(codidactividad, codidoposicion, codidlogtrans, codfecha, codobservacion, 
codusuario, codorden_o, codfecha_operacion,codestado);

sec_opoevento = (select currval('sec_opoevento')) ;


for opoeventoobj in (select *
 from opoevento
 where idevento = sec_opoevento
 ) loop
return next opoeventoobj;
end loop;

       
END;
$BODY$
 LANGUAGE plpgsql;

        /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar Insercion de datos opofechalimite
*/
CREATE OR REPLACE FUNCTION inserta_opofechalimite(
    codidevento bigint,
    codidactividadplazo bigint,
    codidoposicion bigint,
    codidlogtrans bigint,
    codorden integer,
    codfechalimite timestamp without time zone,
    codorden_o integer)
  RETURNS SETOF opofechalimite AS
$BODY$
        /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar Insercion de datos opofechalimite
*/
DECLARE
sec_opofechalimite bigint;
opofechalimiteobj opofechalimite%ROWTYPE;
BEGIN


insert into opofechalimite(
idevento, idactividadplazo, idoposicion, idlogtrans, 
orden, fechalimite, orden_o)
values(codidevento, codidactividadplazo, codidoposicion, codidlogtrans, 
codorden, codfechalimite, codorden_o);

sec_opofechalimite = (select currval('sec_opofechalimite')) ;


for opofechalimiteobj in (select *
 from opofechalimite
 where idfechalimite = sec_opofechalimite
 ) loop
return next opofechalimiteobj;
end loop;

       
END;
$BODY$
  LANGUAGE plpgsql;

          /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar Insercion de datos opohistorial
*/

CREATE OR REPLACE FUNCTION inserta_opohistorial(
  
  codidoposicion bigint,
  codidlogtrans bigint,
  codestado character varying,
  codfecha_lim timestamp without time zone,
  codobservacion character varying,
  codubicacion character varying,
  codapoderado character varying,
  codoperacion character varying,
  codfecha_operacion timestamp without time zone,
  codid_usuario bigint

)
  RETURNS SETOF opohistorial AS
$BODY$
          /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar Insercion de datos opohistorial
*/

DECLARE
sec_opohistorial bigint;
opohistorialobj opohistorial%ROWTYPE;
BEGIN

-- select current_date;
insert into opohistorial(
idoposicion, idlogtrans, estado, fecha_lim, observacion, 
ubicacion, apoderado, operacion, fecha_operacion, id_usuario)
values(codidoposicion, codidlogtrans, codestado, codfecha_lim, codobservacion, 
codubicacion, codapoderado, codoperacion, codfecha_operacion, codid_usuario);

sec_opohistorial = (select currval('sec_opohistorial')) ;


for opohistorialobj in (select *
 from opohistorial
 where idhistorial = sec_opohistorial
 ) loop
return next opohistorialobj;
end loop;

       
END;
$BODY$
  LANGUAGE plpgsql;

           
 /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar Insercion de datos oporecurso
*/
CREATE OR REPLACE FUNCTION inserta_oporecurso(
  
  codidevento bigint,
  codidoposicion bigint,
  codidlogtrans bigint,
  codnumero_resolucion integer,
  codfecha_resolucion timestamp without time zone,
  codtipo character varying,
  codresolucion character varying,
  codorden_op integer,
  codestado character varying
)
  RETURNS SETOF oporecurso AS
$BODY$
           /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar Insercion de datos oporecurso
*/

DECLARE
sec_oporecurso bigint;
oporecursoobj oporecurso%ROWTYPE;
BEGIN

insert into oporecurso(
idevento, idoposicion, idlogtrans, numero_resolucion, 
fecha_resolucion, tipo, resolucion, orden_op,estado)
values(codidevento, codidoposicion, codidlogtrans, codnumero_resolucion, 
codfecha_resolucion, codtipo, codresolucion, codorden_op,codestado);

sec_oporecurso = (select currval('sec_oporecurso')) ;


for oporecursoobj in (select *
 from oporecurso
 where idrecurso = sec_oporecurso
 ) loop
return next oporecursoobj;
end loop;

       
END;
$BODY$
  LANGUAGE plpgsql;

             /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar Insercion de datos oporesolucion
*/


CREATE OR REPLACE FUNCTION inserta_oporesolucion(
  
  codidoposicion bigint,
  codidevento bigint,
  codidlogtrans bigint,
  codnumero_resolucion integer,
  codfecha timestamp without time zone,
  codresolucion_cancelacion character varying,
  codresolucion_oposicion character varying,
  codresolucion_signo character varying,
  codorden_o integer,
  codestado character varying

)
  RETURNS SETOF oporesolucion AS
$BODY$

             /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar Insercion de datos oporesolucion
*/
DECLARE
sec_oporesolucion bigint;
oporesolucionobj oporesolucion%ROWTYPE;
BEGIN

insert into oporesolucion(
idoposicion, idevento, idlogtrans, numero_resolucion, 
fecha, resolucion_cancelacion, resolucion_oposicion, 
resolucion_signo, orden_o,estado)
values(codidoposicion, codidevento, codidlogtrans, codnumero_resolucion, 
codfecha, codresolucion_cancelacion, codresolucion_oposicion, 
codresolucion_signo, codorden_o,codestado);

sec_oporesolucion = (select currval('sec_oporesolucion')) ;


for oporesolucionobj in (select *
 from oporesolucion
 where idresolucion = sec_oporesolucion
 ) loop
return next oporesolucionobj;
end loop;

       
END;
$BODY$
 LANGUAGE plpgsql;

          /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar Insercion de datos oposicion
*/

  CREATE OR REPLACE FUNCTION inserta_oposicion(
    codnro_opo bigint,
    codgaceta integer,
    codfecha_pub timestamp without time zone,
    codfecha_presentacion timestamp without time zone,
    codubicacion character varying,
    codobservacion character varying,
    codestado character varying,
    codorden_o integer,
    codid_estado bigint,
    codestadoopo character varying)
  RETURNS SETOF oposicion AS
$BODY$
             /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar Insercion de datos oposicion
*/
DECLARE
sec_oposicion bigint;
oposicionobj oposicion%ROWTYPE;
BEGIN

-- select current_date;
insert into oposicion(
nro_opo, gaceta, fecha_pub,fecha_presentacion, 
ubicacion, observacion, estado, orden_o,id_estado,estadoopo)
values(codnro_opo, codgaceta, codfecha_pub,codfecha_presentacion, 
codubicacion, codobservacion,codestado,codorden_o, codid_estado,codestadoopo);

sec_oposicion = (select currval('sec_oposicion')) ;


for oposicionobj in (select *
 from oposicion
 where idoposicion = sec_oposicion
 ) loop
return next oposicionobj;
end loop;

       
END;
$BODY$
  LANGUAGE plpgsql;

               /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar Insercion de datos oposolicitanteapoderado
*/
CREATE OR REPLACE FUNCTION inserta_oposolicitanteapoderado(
  codidmarcademandada bigint,
  codidmarcademandante bigint,
  codnombre_razonsocial character varying,
  codprimer_apellido character varying,
  codsegundo_apellido character varying,
  codnumero_documento character varying,
  codtipo_documento character varying,
  codlugar_expedicion character varying,
  codpais character varying,
  codgenero character varying,
  codsolicitud_depa character varying,
  codpoder character varying,
  coddireccion character varying,
  codtelefono character varying,
  codcelular character varying,
  codemail character varying,
  codfax character varying,
  codtiposoliapo character varying,
  codtipo_titular character varying,
  codnropoder character varying,
  codtipo_persona character varying,
  codestado character varying)

  RETURNS SETOF oposolicitanteapoderado AS
$BODY$
               /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar Insercion de datos oposolicitanteapoderado
*/
DECLARE
sec_oposolicitanteapoderado bigint;
oposolicitanteapoderadoobj oposolicitanteapoderado%ROWTYPE;
BEGIN

insert into oposolicitanteapoderado(
idmarcademandada, idmarcademandante, 
nombre_razonsocial, primer_apellido, segundo_apellido, numero_documento, 
tipo_documento, lugar_expedicion, pais, genero, solicitud_depa, 
poder, direccion, telefono, celular, email, fax, tiposoliapo, tipo_titular, 
nropoder, tipo_persona, estado
)
values(
codidmarcademandada,codidmarcademandante,codnombre_razonsocial,codprimer_apellido,codsegundo_apellido,codnumero_documento, 
codtipo_documento,codlugar_expedicion,codpais,codgenero,codsolicitud_depa, 
codpoder,coddireccion,codtelefono,codcelular,codemail,codfax,codtiposoliapo,codtipo_titular, 
codnropoder,codtipo_persona,codestado);

sec_oposolicitanteapoderado = (select currval('sec_oposolicitanteapoderado')) ;


for oposolicitanteapoderadoobj in (select *
 from oposolicitanteapoderado
 where idsolicitanteapoderado = sec_oposolicitanteapoderado
 ) loop
return next oposolicitanteapoderadoobj;
end loop;

       
END;
$BODY$
  LANGUAGE plpgsql;

                 /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar Insercion de datos opoactividadplazo
*/

CREATE OR REPLACE FUNCTION inserta_opoactividadplazo(
  
  codidactividad bigint,
  coddescri_idactividadplazo character varying,
  codidactividadanterior bigint,
  codidlogtrans bigint,
  codidarea bigint,
  codplazo integer,
  codsumarplazoanterior integer

)
  RETURNS SETOF opoactividadplazo AS
$BODY$
                 /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar Insercion de datos opoactividadplazo
*/
DECLARE
sec_opoactividadplazo bigint;
opoactividadplazoobj opoactividadplazo%ROWTYPE;
BEGIN

insert into opoactividadplazo(
idactividad, descri_idactividadplazo, idactividadanterior, 
idlogtrans, idarea, plazo, sumarplazoanterior)
values(codidactividad, coddescri_idactividadplazo, codidactividadanterior, 
codidlogtrans, codidarea, codplazo, codsumarplazoanterior);

sec_opoactividadplazo = (select currval('sec_opoactividadplazo')) ;


for opoactividadplazoobj in (select *
 from opoactividadplazo
 where idactividadplazo = sec_opoactividadplazo
 ) loop
return next opoactividadplazoobj;
end loop;

       
END;
$BODY$
  LANGUAGE plpgsql;

                   /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar Modificacion opoactividadplazo
*/

CREATE OR REPLACE FUNCTION modifica_opoactividadplazo(
  
  codidactividadplazo bigint,
  codidactividad bigint,
  coddescri_idactividadplazo character varying,
  codidactividadanterior bigint,
  codidlogtrans bigint,
  codidarea bigint,
  codplazo integer,
  codsumarplazoanterior integer
    )

RETURNS SETOF opoactividadplazo AS
$BODY$
                   /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar Modificacion opoactividadplazo
*/
DECLARE
sec_opoactividadplazo bigint;
opoactividadplazoobj opoactividadplazo%ROWTYPE;

BEGIN

update opoactividadplazo 
    set
  idactividadplazo=codidactividadplazo,
  idactividad=codidactividad,
  descri_idactividadplazo=coddescri_idactividadplazo,
  idactividadanterior=codidactividadanterior,
  idlogtrans=codidlogtrans,
  idarea=codidarea,
  plazo=codplazo,
  sumarplazoanterior=codsumarplazoanterior
    where idactividadplazo=codidactividadplazo;


for opoactividadplazoobj in (select *
 from opoactividadplazo
 where idactividadplazo=codidactividadplazo
 ) loop
return next opoactividadplazoobj;
end loop;

END;
$BODY$
  LANGUAGE plpgsql;

/*
********************************************************************************
Creado: Luis Angel Quispe Limachi:19/09/2016
Realizar Modificacion oposolicitanteapoderado
*/

CREATE OR REPLACE FUNCTION modifica_oposolicitanteapoderado(
    codidsolicitanteapoderado bigint,
    codidmarcademandada bigint,
    codidmarcademandante bigint,
    codnombre_razonsocial character varying,
    codprimer_apellido character varying,
    codsegundo_apellido character varying,
    codnumero_documento character varying,
    codtipo_documento character varying,
    codlugar_expedicion character varying,
    codpais character varying,
    codgenero character varying,
    codsolicitud_depa character varying,
    codpoder character varying,
    coddireccion character varying,
    codtelefono character varying,
    codcelular character varying,
    codemail character varying,
    codfax character varying,
    codtiposoliapo character varying,
    codtipo_titular character varying,
    codnropoder character varying,
    codtipo_persona character varying,
    codestado character varying)
  RETURNS SETOF oposolicitanteapoderado AS
$BODY$
                     /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar Modificacion oposolicitanteapoderado
*/

DECLARE
sec_oposolicitanteapoderado bigint;
oposolicitanteapoderadoobj oposolicitanteapoderado%ROWTYPE;

BEGIN

update oposolicitanteapoderado 
     set 
  idsolicitanteapoderado=codidsolicitanteapoderado,
  idmarcademandada=codidmarcademandada,
  idmarcademandante=codidmarcademandante,
  nombre_razonsocial=codnombre_razonsocial,
  primer_apellido=codprimer_apellido,
  segundo_apellido=codsegundo_apellido,
  numero_documento=codnumero_documento,
  tipo_documento=codtipo_documento,
  lugar_expedicion=codlugar_expedicion,
  pais=codpais,
  genero=codgenero,
  solicitud_depa=codsolicitud_depa,
  poder=codpoder,
  direccion=coddireccion,
  telefono=codtelefono,
  celular=codcelular,
  email=codemail,
  fax=codfax,
  tiposoliapo=codtiposoliapo,
  tipo_titular=codtipo_titular,
  nropoder=codnropoder,
  tipo_persona=codtipo_persona,
  estado=codestado
  

        
        where idsolicitanteapoderado=codidsolicitanteapoderado;


for oposolicitanteapoderadoobj in (select *
 from oposolicitanteapoderado
 where idsolicitanteapoderado=codidsolicitanteapoderado
 ) loop
return next oposolicitanteapoderadoobj;
end loop;

END;
$BODY$
  LANGUAGE plpgsql;
                       /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar Modificacion marcademandada
*/

CREATE OR REPLACE FUNCTION modifica_marcademandada(
  codidmarcademandada bigint,
  codidoposicion bigint,
  codidlogtrans bigint,
  codnro_opo numeric,
  coddmdo_public numeric,
  codgaceta numeric,
  coddmdo_clase character varying,
  codfecha_public timestamp without time zone,
  coddmdo_marca_lnv character varying,
  coddmdo_sm character varying,   
  codfec_lim timestamp without time zone, 
  codverif boolean,
  codestado character varying
    )
  RETURNS SETOF opomarcademandada AS
$BODY$

                       /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar Modificacion marcademandada
*/
DECLARE
id bigint;
sec_opomarcademandada bigint;
opomarcademandadaobj opomarcademandada%ROWTYPE;

BEGIN

update opomarcademandada 
    set 
  idmarcademandada=codidmarcademandada,
  idoposicion=codidoposicion,
  idlogtrans=codidlogtrans,
  nro_opo=codnro_opo,
  dmdo_public=coddmdo_public,
  gaceta=codgaceta,
  dmdo_clase=coddmdo_clase,
  fecha_public=codfecha_public,
  dmdo_marca_lnv=coddmdo_marca_lnv,
  dmdo_sm=coddmdo_sm,   
  fec_lim=codfec_lim, 
  verif=codverif,
  estado=codestado
        
        where idmarcademandada=codidmarcademandada;


for opomarcademandadaobj in (select *
 from opomarcademandada
 where idmarcademandada=codidmarcademandada
 ) loop
return next opomarcademandadaobj;
end loop;

END;
$BODY$
 LANGUAGE plpgsql;

                        /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:19/08/2016
Realizar Modificacion marcademandante
*/
CREATE OR REPLACE FUNCTION modifica_marcademandante(
    codidmarcademandante bigint,
    codidoposicion bigint,
    codidtramite bigint,
    codidpatente bigint,
    codidarea bigint,
    codidmarca bigint,
    codidlogtrans bigint,
    codorden_opo integer,
    coddmte_reg numeric,
    coddmte_serie character varying,
    coddmte_public numeric,
    coddmte_sm bigint,
    coddmte_sp numeric,
    coddmte_marca_lnv character varying,
    coddmte_uso character varying,
    coddmte_clase integer,
    codestado character varying)
  RETURNS SETOF opomarcademandante AS
$BODY$
                         /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:19/09/2016
Realizar Modificacion marcademandante
*/
DECLARE
id bigint;
sec_opomarcademandante bigint;
opomarcademandanteobj opomarcademandante%ROWTYPE;

BEGIN

update opomarcademandante 
    set idoposicion=codidoposicion,
        idtramite=codidtramite,
        idpatente=codidpatente,
        idarea=codidarea,
        idmarca=codidmarca,
        idlogtrans=codidlogtrans,
        orden_opo=codorden_opo,
        dmte_reg=coddmte_reg,
        dmte_serie=coddmte_serie,
        dmte_public=coddmte_public,
        dmte_sm=coddmte_sm,
        dmte_marca_lnv=coddmte_marca_lnv,
        dmte_uso=coddmte_uso,
        dmte_clase=coddmte_clase,
        estado=codestado
        where idmarcademandante=codidmarcademandante;


for opomarcademandanteobj in (select *
 from opomarcademandante
 where idmarcademandante=codidmarcademandante
 ) loop
return next opomarcademandanteobj;
end loop;

END;
$BODY$
  LANGUAGE plpgsql;
                          
/*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar Modificacion opoevento
*/
CREATE OR REPLACE FUNCTION modifica_opoevento(
  codidevento bigint,
  codidactividad bigint,
  codidoposicion bigint,
  codidlogtrans bigint,
  codfecha timestamp without time zone,
  codobservacion character varying,
  codusuario bigint,
  codorden_o integer,
  codfecha_operacion timestamp without time zone,
  codestado character varying
    )
RETURNS SETOF opoevento AS
$BODY$

                           /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar Modificacion opoevento
*/
DECLARE
sec_opoevento bigint;
opoeventoobj opoevento%ROWTYPE;

BEGIN

update opoevento 
    set 
  idevento=codidevento,
  idactividad=codidactividad,
  idoposicion=codidoposicion,
  idlogtrans=codidlogtrans,
  fecha=codfecha,
  observacion=codobservacion,
  usuario=codusuario,
  orden_o=codorden_o,
  fecha_operacion=codfecha_operacion,
  estado=codestado
  where idevento=codidevento;


for opoeventoobj in (select *
 from opoevento
 where idevento=codidevento
 ) loop
return next opoeventoobj;
end loop;

END;
$BODY$
 LANGUAGE plpgsql;


                           /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar Modificacion opofechalimite
*/

CREATE OR REPLACE FUNCTION modifica_opofechalimite(
 
  codidfechalimite bigint,
  codidevento bigint,
  codidactividadplazo bigint,
  codidoposicion bigint,
  codidlogtramite bigint,
  codorden integer,
  codfechalimite timestamp without time zone,
  codorden_o integer

    )
RETURNS SETOF opofechalimite AS
$BODY$
                           /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar Modificacion opofechalimite
*/

DECLARE
sec_opofechalimite bigint;
opofechalimiteobj opofechalimite%ROWTYPE;

BEGIN

update opofechalimite 
    set 
  idfechalimite=codidfechalimite,
  idevento=codidevento,
  idactividadplazo=codidactividadplazo,
  idoposicion=codidoposicion,
  idlogtramite=codidlogtramite,
  orden=codorden,
  fechalimite=codfechalimite,
  orden_o=codorden_o
    where idfechalimite=codidfechalimite;


for opofechalimiteobj in (select *
 from opofechalimite
 where idfechalimite=codidfechalimite
 ) loop
return next opofechalimiteobj;
end loop;

END;
$BODY$
  LANGUAGE plpgsql;

                             /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar Modificacion opohistorial
*/
CREATE OR REPLACE FUNCTION modifica_opohistorial(
  
  codidhistorial bigint,
  codidoposicion bigint,
  codidlogtrans bigint,
  codestado character varying,
  codfecha_lim timestamp without time zone,
  codobservacion character varying,
  codubicacion character varying,
  codapoderado character varying,
  codoperacion character varying,
  codfecha_operacion timestamp without time zone,
  codid_usuario bigint
    )

RETURNS SETOF opohistorial AS
$BODY$
                             /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar Modificacion opohistorial
*/
DECLARE
sec_opohistorial bigint;
opohistorialobj opohistorial%ROWTYPE;

BEGIN

update opohistorial 
    set idhistorial=codidhistorial ,
  idoposicion=codidoposicion,
  idlogtrans=codidlogtrans,
  estado=codestado,
  fecha_lim=codfecha_lim,
  observacion=codobservacion,
  ubicacion=codubicacion,
  apoderado=codapoderado,
  operacion=codoperacion,
  fecha_operacion=codfecha_operacion,
  id_usuario=codid_usuario

    where idhistorial=codidhistorial;


for opohistorialobj in (select *
 from opohistorial
 where idhistorial=codidhistorial
 ) loop
return next opohistorialobj;
end loop;

END;
$BODY$
  LANGUAGE plpgsql;

                               /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar Modificacion oponotificacion
*/
CREATE OR REPLACE FUNCTION modifica_oponotificacion(
  codidnotificacion bigint,
  codidmarcademandada bigint,
  codidmarcademandante bigint,
  codciudad_notificacion character varying,
  codzona_barrio character varying,
  codavenida_calle character varying,
  codnumero character varying,
  codedificio character varying,
  codpiso character varying,
  codnumero_departamento character varying,
  codreferencia_direccion character varying,
  codemail character varying,
  codtelefono character varying,
  codcelular character varying,
  codestado character varying
    )
RETURNS SETOF oponotificacion AS
$BODY$
                               /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar Modificacion oponotificacion
*/
DECLARE
sec_oponotificacion bigint;
oponotificacionobj oponotificacion%ROWTYPE;

BEGIN

update oponotificacion 
    set idnotificacion=codidnotificacion,
        idmarcademandada=codidmarcademandada,
        idmarcademandante=codidmarcademandante,
        ciudad_notificacion=codciudad_notificacion,
        zona_barrio=codzona_barrio,
        avenida_calle=codavenida_calle,
        numero=codnumero,        
        edificio=codedificio,
        piso=codpiso, 
        numero_departamento=codnumero_departamento, 
        referencia_direccion=codreferencia_direccion,
        email=codemail,
        telefono=codtelefono,        
        celular=codcelular,
        estado=codestado

    where idnotificacion=codidnotificacion;


for oponotificacionobj in (select *
 from oponotificacion
 where idnotificacion=codidnotificacion
 ) loop
return next oponotificacionobj;
end loop;

END;
$BODY$
  LANGUAGE plpgsql;

                                 /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar Modificacion oporecurso
*/
CREATE OR REPLACE FUNCTION modifica_oporecurso(
  codidrecurso bigint,
  codidevento bigint,
  codidoposicion bigint,
  codidlogtrans bigint,
  codnumero_resolucion integer,
  codfecha_resolucion timestamp without time zone,
  codtipo character varying,
  codresolucion character varying,
  codorden_op integer,
codestado character varying

    )
RETURNS SETOF oporecurso AS
$BODY$
                                 /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar Modificacion oporecurso
*/
DECLARE
sec_oporecurso bigint;
oporecursoobj oporecurso%ROWTYPE;

BEGIN

update oporecurso 
    set 
  idrecurso=codidrecurso,
  idevento=codidevento,
  idoposicion=codidoposicion,
  idlogtrans=codidlogtrans,
  numero_resolucion=codnumero_resolucion,
  fecha_resolucion=codfecha_resolucion,
  tipo=codtipo,
  resolucion=codresolucion,
  orden_op=codorden_op,
estado=codestado
    where idrecurso=codidrecurso;


for oporecursoobj in (select *
 from oporecurso
 where idrecurso=codidrecurso
 ) loop
return next oporecursoobj;
end loop;

END;
$BODY$
  LANGUAGE plpgsql;

                                   /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar Modificacion oporesolucion
*/

CREATE OR REPLACE FUNCTION modifica_oporesolucion(
  codidresolucion bigint,
  codidoposicion bigint,
  codidevento bigint,
  codidlogtrans bigint,
  codnumero_resolucion integer,
  codfecha timestamp without time zone,
  codresolucion_cancelacion character varying,
  codresolucion_oposicion character varying,
  codresolucion_signo character varying,
  codorden_o integer,
  codestado character varying

    )
RETURNS SETOF oporesolucion AS
$BODY$
                                   /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar Modificacion oporesolucion
*/

DECLARE
sec_oporesolucion bigint;
oporesolucionobj oporesolucion%ROWTYPE;

BEGIN

update oporesolucion 
    set 
  idresolucion=codidresolucion,
  idoposicion=codidoposicion,
  idevento=codidevento,
  idlogtrans=codidlogtrans,
  numero_resolucion=codnumero_resolucion,
  fecha=codfecha,
  resolucion_cancelacion=codresolucion_cancelacion,
  resolucion_oposicion=codresolucion_oposicion,
  resolucion_signo=codresolucion_signo,
  orden_o=codorden_o,
  estado=codestado
  where idresolucion=codidresolucion;


for oporesolucionobj in (select *
 from oporesolucion
 where idresolucion=codidresolucion
 ) loop
return next oporesolucionobj;
end loop;

END;
$BODY$
  LANGUAGE plpgsql;


       /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:19/09/2016
Realizar Modificacion oposicion
*/
CREATE OR REPLACE FUNCTION modifica_oposicion(
    codidoposicion bigint,
    codnro_opo bigint,
    codgaceta integer,
    codfecha_pub timestamp without time zone,
    codfecha_presentacion timestamp without time zone,
    codubicacion character varying,
    codobservacion character varying,
    codestado character varying,
    codorden_o integer,
    codid_estado bigint,
    codestadoopo character varying)
  RETURNS SETOF oposicion AS
$BODY$
                                   /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:19/09/2016
Realizar Modificacion oposicion
*/
DECLARE
sec_oposicion bigint;
oposicionobj oposicion%ROWTYPE;

BEGIN

update oposicion 

  set idoposicion=codidoposicion,
  
  nro_opo=codnro_opo,
  gaceta=codgaceta,
  fecha_pub=codfecha_pub,         
  fecha_presentacion=codfecha_presentacion, 
  ubicacion=codubicacion,          
  observacion=codobservacion,   
  estado=codestado,  
  orden_o=codorden_o,   
  id_estado=codid_estado,          
  estadoopo=codestadoopo
  where idoposicion=codidoposicion;

for oposicionobj in (select *
 from oposicion
 where idoposicion=codidoposicion
 ) loop
return next oposicionobj;
end loop;

END;
$BODY$
  LANGUAGE plpgsql;                      

   /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:26/09/2016
Realizar la eliminacion de opomarcademandada
*/
CREATE OR REPLACE FUNCTION elimina_opomarcademandada(
pidmarcademandada bigint,pidoposicion bigint)
RETURNS character varying AS
$BODY$
  /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:26/09/2016
Realizar la eliminacion de opomarcademandada
*/
DECLARE
datejos character varying;
BEGIN
delete from opomarcademandada where idmarcademandada = pidmarcademandada and idoposicion=pidoposicion;
return 'datos eliminados';
END;
$BODY$
  LANGUAGE plpgsql;
    
    /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:26/09/2016
Realizar la eliminacion de opomarcademandante
*/
CREATE OR REPLACE FUNCTION elimina_opomarcademandante(
pidmarcademandante bigint,pidoposicion bigint)
RETURNS character varying AS
$BODY$
  /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:26/09/2016
Realizar la eliminacion de opomarcademandante
*/
DECLARE
datejos character varying;
BEGIN
delete from opomarcademandante where idmarcademandante = pidmarcademandante and idoposicion=pidoposicion;
return 'datos eliminados';
END;
$BODY$
  LANGUAGE plpgsql;
    
     /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar la eliminacion de opoactividadplazo
*/

CREATE OR REPLACE FUNCTION elimina_opoactividadplazo(
codidactividadplazo bigint
    )
RETURNS SETOF opoactividadplazo AS
$BODY$
    /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar la eliminacion de opoactividadplazo
*/
DECLARE
sec_opoactividadplazo bigint;
opoactividadplazoobj opoactividadplazo%ROWTYPE;

BEGIN
delete from opoactividadplazo where idactividadplazo = codidactividadplazo;

for opoactividadplazoobj in (select *
 from opoactividadplazo
 where idactividadplazo = sec_opoactividadplazo
 ) loop
return next opoactividadplazoobj;
end loop;

END;
$BODY$
  LANGUAGE plpgsql;

      /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar la eliminacion de opoevento
*/
CREATE OR REPLACE FUNCTION elimina_opoevento(
codidevento bigint
    )
RETURNS SETOF opoevento AS
$BODY$
      /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar la eliminacion de opoevento
*/
DECLARE
sec_opoevento bigint;
opoeventoobj opoevento%ROWTYPE;

BEGIN
delete from opoevento where idevento = codidevento;

for opoeventoobj in (select *
 from opoevento
 where idevento = sec_opoevento
 ) loop
return next opoeventoobj;
end loop;

END;
$BODY$
  LANGUAGE plpgsql;

        /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar la eliminacion de opofechalimite
*/

CREATE OR REPLACE FUNCTION elimina_opofechalimite(
codidfechalimite bigint
    )
RETURNS SETOF opofechalimite AS
$BODY$
        /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar la eliminacion de opofechalimite
*/
DECLARE
sec_opofechalimite bigint;
opofechalimiteobj opofechalimite%ROWTYPE;

BEGIN
delete from opofechalimite where idfechalimite = codidfechalimite;

for opofechalimiteobj in (select *
 from opofechalimite
 where idfechalimite = sec_opofechalimite
 ) loop
return next opofechalimiteobj;
end loop;

END;
$BODY$
 LANGUAGE plpgsql;

          /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar la eliminacion de opohistorial
*/
CREATE OR REPLACE FUNCTION elimina_opohistorial(
codidhistorial bigint
    )
RETURNS SETOF opohistorial AS
$BODY$
 /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar la eliminacion de opohistorial
*/
DECLARE
sec_opohistorial bigint;
opohistorialobj opohistorial%ROWTYPE;

BEGIN
delete from opohistorial where idhistorial = codidhistorial;

for opohistorialobj in (select *
 from opohistorial
 where idhistorial = sec_opohistorial
 ) loop
return next opohistorialobj;
end loop;

END;
$BODY$
  LANGUAGE plpgsql;

   /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:26/09/2016
Realizar la eliminacion de oponotificacion
*/
CREATE OR REPLACE FUNCTION elimina_oponotificaciondemandada(
codidnotificacion bigint,codidmarcademandada bigint)
RETURNS character varying AS
$BODY$
  /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:26/09/2016
Realizar la eliminacion de oponotificacion
*/
DECLARE
datejos character varying;
BEGIN
delete from oponotificacion where idnotificacion = codidnotificacion and idmarcademandada=codidmarcademandada;
return 'datos eliminados';
END;
$BODY$
  LANGUAGE plpgsql;

    /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:26/09/2016
Realizar la eliminacion de oponotificacion
*/
CREATE OR REPLACE FUNCTION elimina_oponotificaciondemandante(
codidnotificacion bigint,codidmarcademandante bigint)
RETURNS character varying AS
$BODY$
  /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:26/09/2016
Realizar la eliminacion de oponotificacion
*/
DECLARE
datejos character varying;
BEGIN
delete from oponotificacion where idnotificacion = codidnotificacion and idmarcademandante=codidmarcademandante;
return 'datos eliminados';
END;
$BODY$
  LANGUAGE plpgsql;

    /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar la eliminacion de oporecurso
*/
CREATE OR REPLACE FUNCTION elimina_oporecurso(
codidrecurso bigint
    )
RETURNS SETOF oporecurso AS
$BODY$

    /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar la eliminacion de oporecurso
*/
DECLARE
sec_oporecurso bigint;
oporecursoobj oporecurso%ROWTYPE;

BEGIN
delete from oporecurso where idrecurso = codidrecurso;

for oporecursoobj in (select *
 from oporecurso
 where idrecurso = sec_oporecurso
 ) loop
return next oporecursoobj;
end loop;

END;
$BODY$
  LANGUAGE plpgsql;


    /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar la eliminacion de oporesolucion
*/
CREATE OR REPLACE FUNCTION elimina_oporesolucion(
codidresolucion bigint
    )
RETURNS SETOF oporesolucion AS
$BODY$
    /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:31/08/2016
Realizar la eliminacion de oporesolucion
*/
DECLARE
sec_oporesolucion bigint;
oporesolucionobj oporesolucion%ROWTYPE;

BEGIN
delete from oporesolucion where idresolucion = codidresolucion;

for oporesolucionobj in (select *
 from oporesolucion
 where idresolucion = sec_oporesolucion
 ) loop
return next oporesolucionobj;
end loop;

END;
$BODY$
  LANGUAGE plpgsql;

        /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:26/09/2016
Realizar la eliminacion de oposicion
*/
CREATE OR REPLACE FUNCTION elimina_oposicion(
pidoposicion bigint)
RETURNS character varying AS
$BODY$
  /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:26/09/2016
Realizar la eliminacion de oposicion
*/
DECLARE
datejos character varying;
BEGIN
delete from oposicion where idoposicion = pidoposicion;
return 'datos eliminados';
END;
$BODY$
  LANGUAGE plpgsql;


      /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:26/09/2016
Realizar la eliminacion de oposolicitanteapoderadodemandante
*/
CREATE OR REPLACE FUNCTION elimina_oposolicitanteapoderadodemandante(
pidsolicitanteapoderado bigint,pidmarcademandante bigint)
RETURNS character varying AS
$BODY$
  /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:26/09/2016
Realizar la eliminacion de oposolicitanteapoderadodemandante
*/
DECLARE
datejos character varying;
BEGIN
delete from oposolicitanteapoderado where idsolicitanteapoderado = pidsolicitanteapoderado and idmarcademandante=pidmarcademandante;
return 'datos eliminados';
END;
$BODY$
  LANGUAGE plpgsql;


   /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:26/09/2016
Realizar la eliminacion de oposolicitanteapoderadodemandada
*/
CREATE OR REPLACE FUNCTION elimina_oposolicitanteapoderadodemandada(
pidsolicitanteapoderado bigint,pidmarcademandada bigint)
RETURNS character varying AS
$BODY$
  /*
********************************************************************************
Creado: Luis Angel Quispe Limachi:26/09/2016
Realizar la eliminacion de oposolicitanteapoderadodemandada
*/
DECLARE
datejos character varying;
BEGIN
delete from oposolicitanteapoderado where idsolicitanteapoderado = pidsolicitanteapoderado and idmarcademandada=pidmarcademandada;
return 'datos eliminados';
END;
$BODY$
  LANGUAGE plpgsql;



/*
********************************************************************************
Creado: Luis Angel Quispe Limachi Fecha:14/09/2016
Listar oposicion por idpublicacion
*/

CREATE OR REPLACE FUNCTION lista_oposicion_nroopo(pnrooposicion bigint)
  RETURNS SETOF oposicion AS
$BODY$

/*
********************************************************************************
Creado: Luis Angel Quispe Limachi Fecha:14/09/2016
Listar oposicion por idpublicacion
*/

 DECLARE 
    voposicion oposicion%ROWTYPE; 
BEGIN

for voposicion in select * from oposicion 
    where  nro_opo = pnrooposicion and estadoopo='AC' loop
    return next voposicion;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;

/*
********************************************************************************
Creado: Luis Angel Quispe Limachi Fecha:15/09/2016
Listar opomarcademandante en idoposicion
*/

CREATE OR REPLACE FUNCTION lista_opomarcademandada_idoposicion(pidopsicion bigint)
  RETURNS SETOF opomarcademandada AS
$BODY$

/*
********************************************************************************
Creado: Luis Angel Quispe Limachi Fecha:15/09/2016
Listar opomarcademandante en idoposicion
*/

 DECLARE 
    vopomarcademandada opomarcademandada%ROWTYPE; 
BEGIN

for vopomarcademandada in select * from opomarcademandada
    where  idoposicion = pidopsicion loop
    return next vopomarcademandada;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;



  /*
********************************************************************************
Creado: Luis Angel Quispe Limachi Fecha:15/09/2016
Listar opomarcademandante por idoposicion
*/

CREATE OR REPLACE FUNCTION lista_opomarcademandante_idoposicion(pidopsicion bigint)
  RETURNS SETOF opomarcademandante AS
$BODY$

/*
********************************************************************************
Creado: Luis Angel Quispe Limachi Fecha:15/09/2016
Listar opomarcademandante por idoposicion
*/

 DECLARE 
    vopomarcademandante opomarcademandante%ROWTYPE; 
BEGIN

for vopomarcademandante in select * from opomarcademandante
    where  idoposicion = pidopsicion loop
    return next vopomarcademandante;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;


   /*
********************************************************************************
Creado: Luis Angel Quispe Limachi Fecha:15/09/2016
Listar oponotificacion por idmarcademandada
*/

CREATE OR REPLACE FUNCTION lista_oponotificacion_idmarcademandada(pidmarcademandada bigint)
  RETURNS SETOF oponotificacion AS
$BODY$

/*
********************************************************************************
Creado: Luis Angel Quispe Limachi Fecha:15/09/2016
Listar oponotificacion por idmarcademandada
*/

 DECLARE 
    voponotificacion oponotificacion%ROWTYPE; 
BEGIN

for voponotificacion in select * from oponotificacion
    where  idmarcademandada = pidmarcademandada loop
    return next voponotificacion;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;

/*
********************************************************************************
Creado: Luis Angel Quispe Limachi Fecha:15/09/2016
Listar oponotificacion por iddemandante
*/

CREATE OR REPLACE FUNCTION lista_oponotificacion_idmarcademandante(pidmarcademandante bigint)
  RETURNS SETOF oponotificacion AS
$BODY$

/*
********************************************************************************
Creado: Luis Angel Quispe Limachi Fecha:15/09/2016
Listar oponotificacion por iddemandante
*/

 DECLARE 
    voponotificacion oponotificacion%ROWTYPE; 
BEGIN

for voponotificacion in select * from oponotificacion
    where  idmarcademandante = pidmarcademandante loop
    return next voponotificacion;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;

/*
********************************************************************************
Creado: Luis Angel Quispe Limachi Fecha:15/09/2016
Listar oposolicitanteapoderado por idmarcademandada
*/

  CREATE OR REPLACE FUNCTION lista_oposolicitanteapoderado_idmarcademandada(pidmarcademandada bigint)
  RETURNS SETOF oposolicitanteapoderado AS
$BODY$

/*
********************************************************************************
Creado: Luis Angel Quispe Limachi Fecha:15/09/2016
Listar oposolicitanteapoderado por idmarcademandada
*/

 DECLARE 
    voposolicitanteapoderado oposolicitanteapoderado%ROWTYPE; 
BEGIN

for voposolicitanteapoderado in select * from oposolicitanteapoderado
    where  idmarcademandada = pidmarcademandada loop
    return next voposolicitanteapoderado;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;

  /*
********************************************************************************
Creado: Luis Angel Quispe Limachi Fecha:15/09/2016
Listar oposolicitanteapoderado por idemarcademandante
*/

  CREATE OR REPLACE FUNCTION lista_oposolicitanteapoderado_idmarcademandante(pidmarcademandante bigint)
  RETURNS SETOF oposolicitanteapoderado AS
$BODY$

/*
********************************************************************************
Creado: Luis Angel Quispe Limachi Fecha:15/09/2016
Listar oposolicitanteapoderado por idemarcademandante
*/

 DECLARE 
    voposolicitanteapoderado oposolicitanteapoderado%ROWTYPE; 
BEGIN

for voposolicitanteapoderado in select * from oposolicitanteapoderado
    where  idmarcademandante = pidmarcademandante loop
    return next voposolicitanteapoderado;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;

/*
********************************************************************************
Creado: Luis Angel Quispe Limachi Fecha:16/09/2016
Listar opomarcademandante por numero de oposicion
*/
CREATE OR REPLACE FUNCTION lista_opomarcademandante_nropprue(pnroopo bigint)
  RETURNS SETOF opomarcademandante AS
$BODY$

/*
********************************************************************************
Creado: Luis Angel Quispe Limachi Fecha:15/09/2016
Listar opomarcademandante por numero de oposicion
*/

 DECLARE 
    vopomarcademandante opomarcademandante%ROWTYPE; 
BEGIN

for vopomarcademandante in select a.*
from opomarcademandante as a,oposicion as b
where a.idoposicion=b.idoposicion and b.nro_opo=pnroopo
    loop
    return next vopomarcademandante;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;


/*
********************************************************************************
Creado: Luis Angel Quispe Limachi Fecha:19/09/2016
Listar oposicionsolicitanteapoderado por idmarcademandada que sea solicitante 
*/

CREATE OR REPLACE FUNCTION lista_oposolicitanteapoderado_solid_idmarcademandada(pidmarcademandada bigint)
  RETURNS SETOF oposolicitanteapoderado AS
$BODY$

/*
********************************************************************************
Creado: Luis Angel Quispe Limachi Fecha:19/09/2016
Listar oposicionsolicitanteapoderado por idmarcademandada que sea solicitante 
*/

 DECLARE 
    voposolicitanteapoderado oposolicitanteapoderado%ROWTYPE; 
BEGIN

for voposolicitanteapoderado in select * from oposolicitanteapoderado
    where  idmarcademandada = pidmarcademandada and tipo_persona='SOLI' and estado='AC' loop
    return next voposolicitanteapoderado;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;



  /*
******************************************************************************
Creado: Luis Angel Quispe Limachi Fecha:19/09/2016
Listar oposicionsolicitanteapoderado por idmarcademandada que sea apoderado 
*/

CREATE OR REPLACE FUNCTION lista_oposolicitanteapoderado_apod_idmarcademandada(pidmarcademandada bigint)
RETURNS SETOF oposolicitanteapoderado AS
$BODY$

/*
********************************************************************************
Creado: Luis Angel Quispe Limachi Fecha:19/09/2016
Listar oposicionsolicitanteapoderado por idmarcademandada que sea apoderado 
*/

 DECLARE 
    voposolicitanteapoderado oposolicitanteapoderado%ROWTYPE; 
BEGIN

for voposolicitanteapoderado in select * from oposolicitanteapoderado
    where  idmarcademandada = pidmarcademandada and tipo_persona='APOD' and estado='AC' loop
    return next voposolicitanteapoderado;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;

  /*
********************************************************************************
Creado: Luis Angel Quispe Limachi Fecha:19/09/2016
Listar oposicionsolicitanteapoderado por idmarcademandante que sea apoderado 
*/
CREATE OR REPLACE FUNCTION lista_oposolicitanteapoderado_apod_idmarcademandante(pidmarcademandante bigint)
RETURNS SETOF oposolicitanteapoderado AS
$BODY$

/*
********************************************************************************
Creado: Luis Angel Quispe Limachi Fecha:19/09/2016
Listar oposicionsolicitanteapoderado por idmarcademandante que sea apoderado 
*/

 DECLARE 
    voposolicitanteapoderado oposolicitanteapoderado%ROWTYPE; 
BEGIN

for voposolicitanteapoderado in select * from oposolicitanteapoderado
    where  idmarcademandante = pidmarcademandante and tipo_persona='APOD' and estado='AC'  loop
    return next voposolicitanteapoderado;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;


/*
********************************************************************************
Creado: Luis Angel Quispe Limachi Fecha:19/09/2016
Listar oposicionsolicitanteapoderado por idmarcademandante que sea solicitante 
*/
CREATE OR REPLACE FUNCTION lista_oposolicitanteapoderado_solid_idmarcademandante(pidmarcademandante bigint)
RETURNS SETOF oposolicitanteapoderado AS
$BODY$

/*
********************************************************************************
Creado: Luis Angel Quispe Limachi Fecha:19/09/2016
Listar oposicionsolicitanteapoderado por idmarcademandante que sea solicitante
*/

 DECLARE 
    voposolicitanteapoderado oposolicitanteapoderado%ROWTYPE; 
BEGIN

for voposolicitanteapoderado in select * from oposolicitanteapoderado
    where  idmarcademandante = pidmarcademandante and tipo_persona='SOLI' and estado='AC' loop
    return next voposolicitanteapoderado;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;


/*
********************************************************
Creado: Luis Angel Quispe  Limachi Fecha:10/10/2016
busqueda por idsignomarca en tabla sigdirecciondenotificacion
*/
CREATE OR REPLACE FUNCTION lista_sigdirecciondenotificacion_idsignomarca(pidsignomarca bigint)
  RETURNS SETOF sigdirecciondenotificacion AS
$BODY$
/*
********************************************************
Creado: Luis Angel Quispe  Limachi Fecha:10/10/2016
busqueda por idsignomarca en tabla sigdirecciondenotificacion
*/
 DECLARE 
    vtiposigno sigdirecciondenotificacion%ROWTYPE; 
BEGIN

for vtiposigno in select * from sigdirecciondenotificacion where idsignomarca=pidsignomarca 	
 loop
    return next vtiposigno;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;

/*
********************************************************
Creado  Luis Angel Quispe Limachi Fecha:10/10/2016
lista por idsignomarca en tabla sigsolicitanteapoderado
*/
CREATE OR REPLACE FUNCTION lista_sigsolicitanteapoderado_apod_idsignomarca(pidsignomarca bigint)
  RETURNS SETOF sigsolicitanteapoderado AS
$BODY$
/*
********************************************************
Creado  Luis Angel Quispe Limachi Fecha:10/10/2016
lista por idsignomarca en tabla sigsolicitanteapoderado
*/
 DECLARE 
    vsolicitanteapoderado sigsolicitanteapoderado%ROWTYPE; 
BEGIN

for vsolicitanteapoderado in select * from sigsolicitanteapoderado where idsignomarca=pidsignomarca 	
	and tipo_persona = 'APOD' and estado='AC'
 loop
    return next vsolicitanteapoderado;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;





/*
********************************************************************************
Creado: Susana Escobar Fecha:31/08/2016
Listar solicitantes por un idmodificacion
Modificado: 06/09/2016
*/
CREATE OR REPLACE FUNCTION lista_modsolicitanteapoderado_idmodificacionsoli(pidmodificacion bigint)
  RETURNS SETOF modsolicitanteapoderado AS
$BODY$
/*
********************************************************************************
Creado: Susana Escobar Fecha:31/08/2016
Listar solicitantes por un idmodificacion
*/
 DECLARE 
    vmodsolicitanteapoderado modsolicitanteapoderado%ROWTYPE; 
BEGIN

for vmodsolicitanteapoderado in select * from modsolicitanteapoderado 
    where  idmodificacion = pidmodificacion and tipo_persona='SOLI' and estado='AC' loop
    return next vmodsolicitanteapoderado;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;  

/*
********************************************************************************
Creado: Susana Escobar Fecha:31/08/2016
Listar apoderados por un idmodificacion
Modificado: 06/09/2016
*/
CREATE OR REPLACE FUNCTION lista_modsolicitanteapoderado_idmodificacion(pidmodificacion bigint)
  RETURNS SETOF modsolicitanteapoderado AS
$BODY$
/*
********************************************************************************
Creado: Susana Escobar Fecha:31/08/2016
Listar apoderados por un idmodificacion
*/
 DECLARE 
    vmodsolicitanteapoderado modsolicitanteapoderado%ROWTYPE; 
BEGIN

for vmodsolicitanteapoderado in select * from modsolicitanteapoderado 
    where  idmodificacion = pidmodificacion and tipo_persona='APOD' and estado='AC' loop
    return next vmodsolicitanteapoderado;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;  

/*
********************************************************************************
Creado: Susana Escobar Fecha:31/08/2016
Listar titulares o licencitarios nuevos por un idmodificacion
*/
CREATE OR REPLACE FUNCTION lista_modtitularlicenciatarionuevo_idmodificacion(pidmodificacion bigint)
  RETURNS SETOF modtitularlicenciatarionuevo AS
$BODY$
/*
********************************************************************************
Creado: Susana Escobar Fecha:31/08/2016
Listar titulares o licencitarios nuevos por un idmodificacion
*/
 DECLARE 
    vmodtitularlicenciatarionuevo modtitularlicenciatarionuevo%ROWTYPE; 
BEGIN

for vmodtitularlicenciatarionuevo in select * from modtitularlicenciatarionuevo 
    where  idmodificacion = pidmodificacion and (tipo_persona='NTIT' OR tipo_persona='LICE') and estado='AC' order by 1 asc loop
    return next vmodtitularlicenciatarionuevo;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;


/*
********************************************************************************
Creado: Susana Escobar Fecha:31/08/2016
Modificado: 15/10/2016
Listar titulares registrados por un idmodificacion
Modificado: 09/07/2016
*/
CREATE OR REPLACE FUNCTION lista_modtitularlicenciatarioregistrado_idmodificacion(pidmodificacion bigint)
  RETURNS SETOF modtitularlicenciatarioregistrado AS
$BODY$
/*
********************************************************************************
Creado: Susana Escobar Fecha:31/08/2016
Listar titulares registrados por un idmodificacion
*/
 DECLARE 
    vmodtitularlicenciatarioregistrado modtitularlicenciatarioregistrado%ROWTYPE; 
BEGIN

for vmodtitularlicenciatarioregistrado in select * from modtitularlicenciatarioregistrado 
    where  idmodificacion = pidmodificacion and tipo_persona_registrado ='TREG' and estado='AC' order by 1 asc loop
    return next vmodtitularlicenciatarioregistrado;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;


/*
********************************************************************************
Creado: Susana Escobar Fecha:31/08/2016
Modificado: 15/10/2016
Listar titulares participantes en la fusion por un idmodificacion
Modificado: 09/07/2016
*/
CREATE OR REPLACE FUNCTION lista_modtitularlicenciatarioregistrado_idmodificacionfusion(pidmodificacion bigint)
  RETURNS SETOF modtitularlicenciatarioregistrado AS
$BODY$
/*
********************************************************************************
Creado: Susana Escobar Fecha:31/08/2016
Modificado: 15/10/2016
Listar titulares participantes en la fusion por un idmodificacion
*/
 DECLARE 
    vmodtitularlicenciatarioregistrado modtitularlicenciatarioregistrado%ROWTYPE; 
BEGIN

for vmodtitularlicenciatarioregistrado in select * from modtitularlicenciatarioregistrado 
    where  idmodificacion = pidmodificacion and tipo_persona_registrado ='PFUS' and estado='AC' order by 1 asc loop
    return next vmodtitularlicenciatarioregistrado;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;

/*
********************************************************************************
Creado: Susana Escobar Fecha:31/08/2016
Listar direccion de notificacion por un idmodificacion
Modificado: 09/07/2016
*/
CREATE OR REPLACE FUNCTION lista_moddireccionnotificacion_idmodificacion(pidmodificacion bigint)
  RETURNS SETOF moddirecciondenotificacion AS
$BODY$
/*
********************************************************************************
Creado: Susana Escobar Fecha:31/08/2016
Listar direccion de notificacion por un idmodificacion
*/
 DECLARE 
    vmoddirecciondenotificacion moddirecciondenotificacion%ROWTYPE; 
BEGIN

for vmoddirecciondenotificacion in select * from moddirecciondenotificacion 
    where  idmodificacion = pidmodificacion loop
    return next vmoddirecciondenotificacion;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;


---------------------------------------------------------------------
--------------Creado: Chano Rojas Fecha:30/08/2016
------------- Realizar el crud de la tabla de crud_rensolicitudrenovacion------------------------
---------------------------------------------------------------------

  CREATE OR REPLACE FUNCTION crud_rensolicitudrenovacion(
   codidsolicitudrenovacion bigint,
   codidlogtrans bigint,
   codid_recibo_solicitud bigint,
   codid_recibo_titulo bigint,
   codsr bigint, 
   codgestion_sr integer,
   codfecha_ingreso timestamp without time zone,
   codnro_formulario bigint,
   codestado_renovacion character varying,
   codubicacion_renovacion character varying,
   codnumero_ultima_renovacion integer,
   codserie_ultima_renovacion character varying,
   codnumero_penultima_renovacion integer,
   codserie_penultima_renovacion character varying,
   codoficina character varying,
   codnumero_recibo bigint,
   codserie_recibo character varying,
   codnumero_titulo bigint,
   codserie_titulo character varying, 
   codclase_niza_reclasificacion integer, 
   codlista_productos_limitacion text, 
   codsm bigint, 
   codsigno_registrado character varying, 
   codclase_niza_registrado integer, 
   codtipo_genero character varying, 
   codnumero_registro_registrado bigint, 
   codserie_registro_registrado character varying, 
   codfecha_registro_registrado timestamp without time zone, 
   codmarca_acomp character varying, 
   codreg_lc_registrado bigint, 
   codserie_lc_registrado character varying, 
   codfecha_lc_registrado timestamp without time zone, 
   codestado character varying,
   operacion integer)
  RETURNS SETOF rensolicitudrenovacion AS
$BODY$
/*
Creado: Chano Rojas Fecha:30/08/2016
Realizar el crud de la tabla de crud_rensolicitudrenovacion
*/

DECLARE
id bigint;
seq_rensolicitudrenovacion bigint;
obj_rensolicitudrenovacion rensolicitudrenovacion%ROWTYPE;
BEGIN
if operacion=1 then -- Create

insert into rensolicitudrenovacion(
  idlogtrans,
  id_recibo_solicitud,
  id_recibo_titulo,
  sr,
  gestion_sr,
  fecha_ingreso,
  nro_formulario,
  estado_renovacion,
  ubicacion_renovacion,
  numero_ultima_renovacion,
  serie_ultima_renovacion,
  numero_penultima_renovacion,
  serie_penultima_renovacion,
  oficina,
  numero_recibo,
  serie_recibo,
  numero_titulo,
  serie_titulo,
  clase_niza_reclasificacion,
  lista_productos_limitacion,
  sm,
  signo_registrado,
  clase_niza_registrado,
  tipo_genero,
  numero_registro_registrado,
  serie_registro_registrado,
  fecha_registro_registrado,
  marca_acomp,
  reg_lc_registrado,
  serie_lc_registrado,
  fecha_lc_registrado,
  estado
	)
  values(
  codidlogtrans,
  codid_recibo_solicitud,
  codid_recibo_titulo,
  codsr,
  codgestion_sr,
  codfecha_ingreso,
  codnro_formulario,
  codestado_renovacion,
  codubicacion_renovacion,
  codnumero_ultima_renovacion,
  codserie_ultima_renovacion,
  codnumero_penultima_renovacion,
  codserie_penultima_renovacion,
  codoficina,
  codnumero_recibo,
  codserie_recibo,
  codnumero_titulo,
  codserie_titulo,
  codclase_niza_reclasificacion,
  codlista_productos_limitacion,
  codsm,
  codsigno_registrado,
  codclase_niza_registrado,
  codtipo_genero,
  codnumero_registro_registrado,
  codserie_registro_registrado,
  codfecha_registro_registrado,
  codmarca_acomp ,
  codreg_lc_registrado,
  codserie_lc_registrado,
  codfecha_lc_registrado,
  codestado 
  );

seq_rensolicitudrenovacion = (select currval('sec_rensolicitudrenovacion')) ;
for obj_rensolicitudrenovacion in (select *
 from rensolicitudrenovacion
 where idsolicitudrenovacion = seq_rensolicitudrenovacion
 ) loop
return next obj_rensolicitudrenovacion;
end loop;
end if;

if operacion=2 then -- update
		update rensolicitudrenovacion set
		idlogtrans=codidlogtrans,
		id_recibo_solicitud=codid_recibo_solicitud,
		id_recibo_titulo=codid_recibo_titulo,
		sr=codsr,
		gestion_sr=codgestion_sr,
		fecha_ingreso=codfecha_ingreso,
                                    nro_formulario=codnro_formulario,
		estado_renovacion=codestado_renovacion,
		ubicacion_renovacion=codubicacion_renovacion,
		numero_ultima_renovacion=codnumero_ultima_renovacion,
		serie_ultima_renovacion=codserie_ultima_renovacion,
		numero_penultima_renovacion=codnumero_penultima_renovacion,
		serie_penultima_renovacion=codserie_penultima_renovacion,
		oficina=codoficina,
		numero_recibo=codnumero_recibo,
		serie_recibo=codserie_recibo,
		numero_titulo=codnumero_titulo,
		serie_titulo=codserie_titulo,
		clase_niza_reclasificacion=codclase_niza_reclasificacion,
		lista_productos_limitacion=codlista_productos_limitacion,
		sm=codsm,
		signo_registrado=codsigno_registrado,
		clase_niza_registrado=codclase_niza_registrado,
		tipo_genero=codtipo_genero,
		numero_registro_registrado=codnumero_registro_registrado,
		serie_registro_registrado=codserie_registro_registrado,
		fecha_registro_registrado=codfecha_registro_registrado,
		marca_acomp=codmarca_acomp,
		reg_lc_registrado=codreg_lc_registrado,
		serie_lc_registrado=codserie_lc_registrado,
		fecha_lc_registrado=codfecha_lc_registrado,
		estado=codestado
        where idsolicitudrenovacion=codidsolicitudrenovacion;
 for obj_rensolicitudrenovacion in (select *
 from rensolicitudrenovacion
 where idsolicitudrenovacion = codidsolicitudrenovacion
 ) loop
 return next obj_rensolicitudrenovacion;
 end loop;

end if;
	
if operacion =3 then-- delete
     delete from rensolicitudrenovacion where idsolicitudrenovacion = codidsolicitudrenovacion;
end if;

if operacion=4 then --read
	for obj_rensolicitudrenovacion  in
		(
		select * from rensolicitudrenovacion
		where estado = 'AC'
		)
	loop
	return next obj_rensolictudrenovacion;
	end loop;

end if;
END;
$BODY$
LANGUAGE plpgsql;


---------------------------------------------------------------------
--------------Creado: Chano Rojas Fecha:30/08/2016
------------- Realizar el crud de la tabla de crud_rensolicitanteapoderado-------
---------------------------------------------------------------------

CREATE OR REPLACE FUNCTION crud_rensolicitanteapoderado(
	codidsolicitanteapoderado bigint,
	codidsolicitudrenovacion bigint,
	codidlogtrans bigint,
	codtipo_titular character varying,
	codtipo_persona character varying,
	codnombre_razonsocial character varying,
	codprimer_apellido character varying,
	codsegundo_apellido character varying,
	codnumero_documento character varying,
	codtipo_documento character varying,
	codlugar_expedicion character varying,
	codpais character varying,
	codgenero character varying,
	codsolicitud_departamento character varying,
	codpoder character varying,
	coddireccion character varying,
	codtelefono character varying,
	codcelular character varying,
	codemail character varying,
	codfax character varying,
	codestado character varying,
    pid_sipi bigint,
	operacion integer)
    RETURNS SETOF rensolicitanteapoderado 
    LANGUAGE 'plpgsql'

    COST 100
    VOLATILE 
    ROWS 1000
AS $BODY$

/*
Creado: Chano Rojas Fecha:30/08/2016
Modificado: Placido Castro Fecha:08/12/2017
Realizar el crud de la tabla de crud_rensolicitanteapoderado
*/

DECLARE
id bigint;
seq_rensolicitanteapoderado bigint;
obj_rensolicitanteapoderado rensolicitanteapoderado%ROWTYPE;
BEGIN
if operacion=1 then -- Create

insert into rensolicitanteapoderado(
  idsolicitudrenovacion,
  idlogtrans,
  tipo_titular,
  tipo_persona,
  nombre_razonsocial,
  primer_apellido,
  segundo_apellido,
  numero_documento,
  tipo_documento,
  lugar_expedicion,
  pais,
  genero,
  solicitud_departamento,
  poder,
  direccion,
  telefono,
  celular,
  email,
  fax,
  estado,
  id_sipi
	)
  values(
  codidsolicitudrenovacion,
  codidlogtrans,
  codtipo_titular,
  codtipo_persona,
  codnombre_razonsocial,
  codprimer_apellido,
  codsegundo_apellido,
  codnumero_documento,
  codtipo_documento,
  codlugar_expedicion,
  codpais,
  codgenero,
  codsolicitud_departamento,
  codpoder,
  coddireccion,
  codtelefono,
  codcelular,
  codemail,
  codfax,
  codestado,
  pid_sipi
    );

seq_rensolicitanteapoderado = (select currval('sec_rensolicitanteapoderado')) ;
for obj_rensolicitanteapoderado in (select *
 from rensolicitanteapoderado
 where idsolicitanteapoderado = seq_rensolicitanteapoderado
 ) loop
return next obj_rensolicitanteapoderado;
end loop;
    
end if;
if operacion=2 then -- update
		update rensolicitanteapoderado set 
		idsolicitudrenovacion=codidsolicitudrenovacion,
		idlogtrans=codidlogtrans,
		tipo_titular=codtipo_titular,
		tipo_persona=codtipo_persona,
		nombre_razonsocial=codnombre_razonsocial,
		primer_apellido=codprimer_apellido,
		segundo_apellido=codsegundo_apellido,
		numero_documento=codnumero_documento,
		tipo_documento=codtipo_documento,
		lugar_expedicion=codlugar_expedicion,
		pais=codpais,
		genero=codgenero,
		solicitud_departamento=codsolicitud_departamento,
		poder=codpoder,
		direccion=coddireccion,
		telefono=codtelefono,
		celular=codcelular,
		email=codemail,
		fax=codfax,
		estado=codestado,
        id_sipi=pid_sipi
  		where idsolicitanteapoderado=codidsolicitanteapoderado;
for obj_rensolicitanteapoderado in (select *
 from rensolicitanteapoderado
 where idsolicitanteapoderado = codidsolicitanteapoderado
 ) loop
 return next obj_rensolicitanteapoderado;
 end loop;
		
end if;
if operacion =3 then-- delete
     delete from rensolicitanteapoderado where idsolicitanteapoderado= codidsolicitanteapoderado;
end if;
if operacion=4 then --read
	for obj_rensolicitanteapoderado  in
		(
		select * from rensolicitanteapoderado
		where estado = 'AC'
		order by idsolicitanteapoderado asc
		)
	loop
	return next obj_rensolicitanteapoderado;
	end loop;

end if;
END;

$BODY$
  LANGUAGE plpgsql;

---------------------------------------------------------------------
--------------Creado: Chano Rojas Fecha:30/08/2016
------------- Realizar el crud de la tabla de crud_rendirecciondenotificacion-------
---------------------------------------------------------------------

CREATE OR REPLACE FUNCTION crud_rendirecciondenotificacion(codiddirecciondenotificacion bigint, codidsolicitudrenovacion bigint, codidmodificacion bigint, codidlogtrans bigint, codciudad_notificacion character varying, codzona_barrio character varying, codavenida_calle character varying, codnumero character varying, codedificio character varying, codpiso character varying, coddepartamento character varying, codreferencia_direccion character varying, codcorreo_electronico character varying, codtelefono character varying, codcelular character varying, codestado character varying, operacion integer)
RETURNS SETOF rendirecciondenotificacion AS
$BODY$DECLARE
id bigint;
seq_rendirecciondenotificacion bigint;
obj_rendirecciondenotificacion rendirecciondenotificacion%ROWTYPE;
BEGIN
if operacion=1 then -- Create

insert into rendirecciondenotificacion(
  idsolicitudrenovacion,
  idmodificacion,
  idlogtrans,
  ciudad_notificacion,
  zona_barrio,
  avenida_calle,
  numero,
  edificio,
  piso,
  departamento,
  referencia_direccion,
  correo_electronico,
  telefono,
  celular,
  estado
	)
  values(
  codidsolicitudrenovacion,
  codidmodificacion,
  codidlogtrans,
  codciudad_notificacion,
  codzona_barrio,
  codavenida_calle,
  codnumero,
  codedificio,
  codpiso,
  coddepartamento,
  codreferencia_direccion,
  codcorreo_electronico,
  codtelefono,
  codcelular,
  codestado
  );
seq_rendirecciondenotificacion = (select currval('sec_rendirecciondenotificacion')) ;
for obj_rendirecciondenotificacion in (select *
 from rendirecciondenotificacion
 where iddirecciondenotificacion = seq_rendirecciondenotificacion
 ) loop
return next obj_rendirecciondenotificacion;
end loop;
  
end if;
if operacion=2 then -- update
		update rendirecciondenotificacion set 
		idsolicitudrenovacion=codidsolicitudrenovacion,
		idmodificacion=codidmodificacion,
		idlogtrans=codidlogtrans,
		ciudad_notificacion=codciudad_notificacion,
		zona_barrio=codzona_barrio,
		avenida_calle=codavenida_calle,
		numero=codnumero,
		edificio=codedificio,
		piso=codpiso,
		departamento=coddepartamento,
		referencia_direccion=codreferencia_direccion,
		correo_electronico=codcorreo_electronico,
		telefono=codtelefono,
		celular=codcelular,
		estado=codestado
  		where iddirecciondenotificacion=codiddirecciondenotificacion;
for obj_rendirecciondenotificacion in (select *
 from rendirecciondenotificacion
 where iddirecciondenotificacion = codiddirecciondenotificacion
 ) loop
 return next obj_rendirecciondenotificacion;
 end loop;
end if;

if operacion =3 then-- delete
     delete from rendirecciondenotificacion where iddirecciondenotificacion= codiddirecciondenotificacion;
end if;

if operacion=4 then --read
	for obj_rendirecciondenotificacion  in
		(
		select * from rendirecciondenotificacion
		where estado = 'AC'
		order by iddirecciondenotificacion asc
		)
	loop
	return next obj_rendirecciondenotificacion;
	end loop;
end if;

END;
$BODY$
LANGUAGE plpgsql;

---------------------------------------------------------------------
--------------Creado: Chano Rojas Fecha:30/08/2016
------------- Realizar el crud de la tabla de crud_rentitularregistrado-------
---------------------------------------------------------------------
CREATE OR REPLACE FUNCTION crud_rentitularregistrado(
	codidtitularregistrado bigint,
	codidsolicitudrenovacion bigint,
	codidlogtrans bigint,
	codnombre_razonsocial character varying,
	codprimer_apellido character varying,
	codsegundo_apellido character varying,
	coddireccion character varying,
	codestado character varying,
    pid_sipi bigint,
	operacion integer)
    RETURNS SETOF rentitularregistrado 
    LANGUAGE 'plpgsql'

    COST 100
    VOLATILE 
    ROWS 1000
AS $BODY$

/*
Creado: Chano Rojas Fecha:30/08/2016
Modificado: Placido Castro Fecha:08/12/2017
Realizar el crud de la tabla de rentitularregistrado
*/
DECLARE
id bigint;
seq_rentitularregistrado bigint;
obj_rentitularregistrado rentitularregistrado%ROWTYPE;
BEGIN
if operacion=1 then -- Create

insert into rentitularregistrado(
  idsolicitudrenovacion,
  idlogtrans,
  nombre_razonsocial,
  primer_apellido,
  segundo_apellido,
  direccion,
  estado,
  id_sipi
 	)
  values(
  codidsolicitudrenovacion,
  codidlogtrans,
  codnombre_razonsocial,
  codprimer_apellido,
  codsegundo_apellido,
  coddireccion,
  codestado,
  pid_sipi
  );

seq_rentitularregistrado = (select currval('sec_rentitularregistrado')) ;
for obj_rentitularregistrado in (select *
 from rentitularregistrado
 where idtitularregistrado = seq_rentitularregistrado
 ) loop
return next obj_rentitularregistrado;
end loop;
  
end if;
if operacion=2 then -- update
		update rentitularregistrado set 
		idsolicitudrenovacion=codidsolicitudrenovacion,
		idlogtrans=codidlogtrans,
		nombre_razonsocial=codnombre_razonsocial,
		primer_apellido=codprimer_apellido,
		segundo_apellido=codsegundo_apellido,
		direccion=coddireccion,
		estado=codestado,
        id_sipi = pid_sipi
  		where idtitularregistrado=codidtitularregistrado;
  		
for obj_rentitularregistrado in (select *
 from rentitularregistrado
 where idtitularregistrado=codidtitularregistrado
 ) loop
 return next obj_rentitularregistrado;
 end loop;
 		
end if;
if operacion =3 then-- delete
     delete from rentitularregistrado where idtitularregistrado=codidtitularregistrado;
end if;
if operacion=4 then --read
	for obj_rentitularregistrado  in
		(
		select * from rentitularregistrado
		where estado = 'AC'
		order by idtitularregistrado asc
		)
	loop
	return next obj_rentitularregistrado;
	end loop;

end if;
END;

$BODY$
LANGUAGE plpgsql;

---------------------------------------------------------------------
--------------Creado: Chano Rojas Fecha:14/09/2016
------------- Realizar el crud de la tabla de crud_correlativo-------
------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION crud_correlativo(pidcorrelativo bigint, pidlogtrans bigint, pnombre_criterio character varying, pincremento integer, pultimo_numero_asignado integer, pacronimo character varying, pseparador character varying, pgestion integer, pestado character varying, operacion integer)
  RETURNS SETOF correlativo AS
$BODY$
/*
Creado: Chano Rojas Fecha:14/09/2016
Realizar el crud de la tabla de correlativo
*/
DECLARE
id bigint;
seq_correlativo bigint;
obj_correlativo correlativo%ROWTYPE;
BEGIN
if operacion=1 then -- Create

insert into correlativo(
  idlogtrans,
  nombre_criterio,
  incremento,
  ultimo_numero_asignado,
  acronimo,
  separador,
  gestion,
  estado
    )
  values(
  pidlogtrans,
  pnombre_criterio,
  pincremento,
  pultimo_numero_asignado,
  pacronimo,
  pseparador,
  pgestion,
  pestado
  );

seq_correlativo = (select currval('sec_correlativo')) ;
for obj_correlativo in (select *
 from correlativo
 where idcorrelativo = seq_correlativo
 ) loop
return next obj_correlativo;
end loop;
  
end if;
if operacion=2 then -- update
    update correlativo set 
        idcorrelativo=pidcorrelativo,
        idlogtrans=pidlogtrans,
        nombre_criterio=pnombre_criterio,
        incremento=pincremento,
        ultimo_numero_asignado=pultimo_numero_asignado,
        acronimo=pacronimo,
        separador=pseparador,
        gestion=pgestion,
        estado=pestado
                      where idcorrelativo=pidcorrelativo;
      
for obj_correlativo in (select *
 from correlativo
 where idcorrelativo=pidcorrelativo
 ) loop
 return next obj_correlativo;
 end loop;
    
end if;
if operacion =3 then-- delete
     delete from correlativo where idcorrelativo=pidcorrelativo;
end if;
if operacion=4 then --read
  for obj_correlativo  in
    (
    select * from correlativo
    where estado = 'AC'
    order by idcorrelativo asc
    )
  loop
  return next obj_correlativo;
  end loop;

end if;
END;
$BODY$
  LANGUAGE plpgsql;


---------------------------------------------------------------------
--------------Creado: Chano Rojas Fecha:14/09/2016
------------- Realizar el crud de la tabla de crud_correlativoregional-------
------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION crud_correlativoregional(
  pidcorrelativoregional bigint,
  pidregional bigint,
  pidcorrelativo bigint,
  pidlogtrans bigint,
  ptipo_tramite character varying,
  pestado character varying,
  operacion integer
)
  RETURNS SETOF correlativoregional AS
$BODY$
/*
Creado: Chano Rojas Fecha:14/09/2016
Realizar el crud de la tabla de correlativoregional
*/
DECLARE
id bigint;
seq_correlativoregional bigint;
obj_correlativoregional correlativoregional%ROWTYPE;
BEGIN
if operacion=1 then -- Create

insert into correlativoregional(
  idcorrelativoregional,
  idregional,
  idcorrelativo,
  idlogtrans,
  tipo_tramite,
  estado
    )
  values(
  pidcorrelativoregional,
  pidregional,
  pidcorrelativo,
  pidlogtrans,
  ptipo_tramite,
  pestado
  );

seq_correlativoregional = (select currval('sec_correlativoregional')) ;
for obj_correlativoregional in (select *
 from correlativoregional
 where idcorrelativoregional = seq_correlativoregional
 ) loop
return next obj_correlativoregional;
end loop;
  
end if;
if operacion=2 then -- update
    update correlativoregional set 
          idcorrelativoregional=pidcorrelativoregional,
          idregional=pidregional,
          idcorrelativo=pidcorrelativo,
          idlogtrans=pidlogtrans,
          tipo_tramite=ptipo_tramite,
          estado=pestado
                      where idcorrelativoregional=pcorrelativoregional;
      
for obj_correlativoregional in (select *
 from correlativoregional
 where idcorrelativoregional=pidcorrelativoregional
 ) loop
 return next obj_correlativoregional;
 end loop;
    
end if;
if operacion =3 then-- delete
     delete from correlativoregional where idcorrelativoregional=pidcorrelativoregional;
end if;
if operacion=4 then --read
  for obj_correlativoregional  in
    (
    select * from correlativoregional
    where estado = 'AC'
    order by idcorrelativoregional asc
    )
  loop
  return next obj_correlativoregional;
  end loop;

seq_correlativoregional = (select currval('sec_correlativoregional')) ;
for obj_correlativoregional in (select *
 from correlativoregional
 where idcorrelativoregional = seq_correlativoregional
 ) loop
return next obj_correlativoregional;
end loop;
end if;
END;
$BODY$
LANGUAGE plpgsql;


---------------------------------------------------------------------
--------------Creado: Chano Rojas Fecha:15/09/2016
------------- Realizar el crud de la tabla de crud_rentiposigno-------
------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION crud_rentiposigno(pidtiposigno bigint, pidsolicitudrenovacion bigint, pidlogtrans bigint, ptipo_signo character varying, pdescripcion_otro character varying, pestado character varying, operacion integer)
  RETURNS SETOF rentiposigno AS
$BODY$
/*
Creado: Chano Rojas Fecha:15/09/2016
Realizar el crud de la tabla rentiposigno
*/
DECLARE
id bigint;
seq_rentiposigno bigint;
obj_rentiposigno rentiposigno%ROWTYPE;
BEGIN
if operacion=1 then -- Create

insert into rentiposigno(
  idsolicitudrenovacion,
  idlogtrans,
  tipo_signo,
  descripcion_otro,
  estado
    )
  values(
  pidsolicitudrenovacion,
  pidlogtrans,
  ptipo_signo,
  pdescripcion_otro,
  pestado
  );

seq_rentiposigno = (select currval('sec_rentiposigno')) ;
for obj_rentiposigno in (select *
 from rentiposigno
 where idtiposigno = seq_rentiposigno
 ) loop
return next obj_rentiposigno;
end loop;
  
end if;
if operacion=2 then -- update
    update rentiposigno set 
          idsolicitudrenovacion=pidsolicitudrenovacion,
          idlogtrans=pidlogtrans,
    tipo_signo=ptipo_signo,
    descripcion_otro=pdescripcion_otro,
          estado=pestado
                      where idtiposigno=pidtiposigno;
      
for obj_rentiposigno in (select *
 from rentiposigno
 where idtiposigno=pidtiposigno
 ) loop
 return next obj_rentiposigno;
 end loop;
    
end if;
if operacion =3 then-- delete
     delete from rentiposigno where idtiposigno=pidtiposigno;
end if;
if operacion=4 then --read
  for obj_rentiposigno  in
    (
    select * from rentiposigno
    where estado = 'AC'
    order by idtiposigno asc
    )
  loop
  return next obj_rentiposigno;
  end loop;

end if;
END;
$BODY$
  LANGUAGE plpgsql;



/*
*****************************************************
Creado: Chano Rojas Fecha:30/08/2016
Realizar el obtiene_rendirecciondenotificacionporidsolicitud
*/

CREATE OR REPLACE FUNCTION obtiene_rendirecciondenotificacionporidsolicitud(idsolicitud bigint)
  RETURNS SETOF rendirecciondenotificacion AS
$BODY$
/*
Creado: Chano Rojas Fecha:30/08/2016
Realizar el obtiene_rendirecciondenotificacionporidsolicitud
*/

 DECLARE 
    ren rendirecciondenotificacion%ROWTYPE; 
BEGIN

for ren in select * from rendirecciondenotificacion where  idsolicitudrenovacion=idsolicitud and estado='AC' loop
    return next ren;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;

/*
*****************************************************
Creado: Chano Rojas Fecha:30/08/2016
Realizar el crud de la tabla de obtiene_rensolicitanteapoderadoporidslicitud
*/

CREATE OR REPLACE FUNCTION obtiene_rensolicitanteapoderadoporidsolicitud(codidsolicitudrenovacion bigint)
  RETURNS SETOF rensolicitanteapoderado AS
$BODY$
/*
Creado: Chano Rojas Fecha:30/08/2016
Realizar el crud de la tabla de obtiene_rensolicitanteapoderadoporidslicitud
*/
DECLARE 
    rensoliapo rensolicitanteapoderado%ROWTYPE; 
BEGIN

for rensoliapo in select * from rensolicitanteapoderado where  idsolicitudrenovacion=codidsolicitudrenovacion AND estado='AC' 
loop
    return next rensoliapo;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;


/*
*****************************************************
Creado: Chano Rojas Fecha:30/08/2016
Realizar el crud de la tabla de obtiene_rensolicitanteapoderadoporidslicitudytipopersona
*/

CREATE OR REPLACE FUNCTION obtiene_rensolicitanteapoderadoporidsolicitudytipopersona(codidsolicitudrenovacion bigint, codtipo_persona character varying)
  RETURNS SETOF rensolicitanteapoderado AS
$BODY$
/*
Creado: Chano Rojas Fecha:30/08/2016
Realizar el crud de la tabla de obtiene_rensolicitanteapoderadoporidslicitudytipopersona
*/
DECLARE 
    rensoliapo rensolicitanteapoderado%ROWTYPE; 
BEGIN

for rensoliapo in select * from rensolicitanteapoderado where  idsolicitudrenovacion=codidsolicitudrenovacion AND tipo_persona=codtipo_persona AND estado='AC' 
loop
    return next rensoliapo;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;



/*
*****************************************************
Creado: Chano Rojas Fecha:30/08/2016
Realizar el crud de la tabla de obtiene_rensolicitudrenovacion
*/

CREATE OR REPLACE FUNCTION obtiene_rensolicitudrenovacion(
    numerosr bigint,
    gestion integer)
  RETURNS SETOF rensolicitudrenovacion AS
$BODY$
/*
Creado: Chano Rojas Fecha:30/08/2016
Modificado: Placido Castro Fecha: 28/03/2017
Realizar el crud de la tabla de obtiene_rensolicitudrenovacion
*/

DECLARE 
    ren rensolicitudrenovacion%ROWTYPE; 
BEGIN

for ren in select * from rensolicitudrenovacion where  sr=numeroSr AND gestion_sr=gestion AND estado='AC' loop
    return next ren;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;

/*
*****************************************************
Creado: Chano Rojas Fecha:30/08/2016
Realizar el crud de la tabla de obtiene_rentitularregistrado
*/
CREATE OR REPLACE FUNCTION obtiene_rentitularregistrado(codidsolicitudrenovacion bigint)
  RETURNS SETOF rentitularregistrado AS
$BODY$
/*
Creado: Chano Rojas Fecha:30/08/2016
Realizar el crud de la tabla de obtiene_rentitularregistrado
*/
DECLARE 
    ren rentitularregistrado%ROWTYPE; 
BEGIN

for ren in select * from rentitularregistrado where  idsolicitudrenovacion=codidsolicitudrenovacion AND estado='AC' 
loop
    return next ren;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;



/*
********************************************************************************************************
Creado: Chano Rojas Fecha:30/08/2016
Realizar el crud de la tabla de obtiene_rentitularregistradoporidsolicitudrenovacion
*/
CREATE OR REPLACE FUNCTION obtiene_rentitularregistradoporidsolicitudrenovacion(codidsolicitudrenovacion bigint)
  RETURNS SETOF rentitularregistrado AS
$BODY$
/*
Creado: Chano Rojas Fecha:30/08/2016
Realizar el crud de la tabla de obtiene_rentitularregistradoporidsolicitudrenovacion
*/
DECLARE 
    ren rentitularregistrado%ROWTYPE; 
BEGIN
for ren in select * from rentitularregistrado where  idsolicitudrenovacion=codidsolicitudrenovacion AND estado='AC' 
loop
    return next ren;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;





/*
********************************************************************************************************
Creado: Chano Rojas Fecha:30/08/2016
Realizar el crud de la tabla de obtiene_rentitularregistradoporidsolicitudrenovacion
*/
CREATE OR REPLACE FUNCTION lista_rentiposigno_idrensolicitudrenovacion(pidsolicitudrenovacion bigint)
 RETURNS SETOF rentiposigno AS
$BODY$
/*
********************************************************************************
Creado: Susana Escobar Fecha: 16/09/2016
Listar tipo signo por un idmodificacion en solicitud de modificacion
*/
DECLARE 
   vtiposigno rentiposigno%ROWTYPE; 
BEGIN

for vtiposigno in select * from rentiposigno 
   where  idsolicitudrenovacion = pidsolicitudrenovacion and estado='AC' loop
   return next vtiposigno;
end loop;
end;
$BODY$
 LANGUAGE plpgsql;



/*
********************************************************************************************************
Creado: Chano Rojas Fecha:19/09/2016
Realizar el crud de la tabla de obtiene_renrenovacion_idsolicitud
*/

CREATE OR REPLACE FUNCTION obtener_renrenovacion_idsolicitud(idsolicitud bigint)
  RETURNS SETOF renrenovacion AS
$BODY$
/*
Creado: Chano Rojas Fecha:19/09/2016
Realizar la busqueda de la renrenovacion por medio de un idrensolictudrenovacion
*/
DECLARE 
    ren renrenovacion%ROWTYPE; 
BEGIN

for ren in select * from renrenovacion where  idsolicitudrenovacion=idsolicitud and estado='AC' loop
    return next ren;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;

-------------------------------------------------------------------------------------------------


CREATE OR REPLACE FUNCTION crud_renrenovacion(
  codidrenovacion bigint, 
  codidsolicitudrenovacion bigint, 
  codidlogtrans bigint, 
  codnumero_renovacion integer, 
  codserie_renovacion character varying, 
  codorden_renovacion integer, 
  codclase_niza_renovacion integer, 
  codfecha_concesion timestamp without time zone, 
  codtitular character varying, 
  codapoderado character varying, 
  codtipo_marca character varying, 
  codtipo_genero character varying, 
  codsigno_registrado character varying, 
  codetiqueta_renovacion character varying, 
  codnumero_registro integer, 
  codserie_registro character varying, 
  codnumero_clase_niza_actual integer, 
  codsr_manual integer, 
  codgestion_sr_manual integer, 
  codfecha_registro_manual timestamp without time zone, 
  codfecha_ingreso timestamp without time zone, 
  codlista_producto_actual text, 
  codlista_producto_renovacion text, 
  codversion_clase_niza character varying, 
  codfecha_renovacion timestamp without time zone, 
  codgestion_renovacion integer, codestado character varying, operacion integer)
  RETURNS SETOF renrenovacion AS
$BODY$
/*
Creado: Chano Rojas Fecha:30/08/2016
Realizar el crud de la tabla de renrenovacion
*/

DECLARE
id bigint;
seq_renrenovacion bigint;
obj_renrenovacion renrenovacion%ROWTYPE;
BEGIN
if operacion=1 then -- Create

insert into renrenovacion(
  idsolicitudrenovacion,
  idlogtrans,
  numero_renovacion,
  serie_renovacion,
  orden_renovacion,
  clase_niza_renovacion,
  fecha_concesion,
  titular,
  apoderado,
  tipo_marca,
  tipo_genero,
  signo_registrado,
  etiqueta_renovacion,
  numero_registro,
  serie_registro,
  numero_clase_niza_actual,
  sr_manual,
  gestion_sr_manual,
  fecha_registro_manual,
  fecha_ingreso,
  lista_producto_actual,
  lista_producto_renovacion,
  version_clase_niza,
  fecha_renovacion,
  gestion_renovacion,
  estado
  )
  values(
  codidsolicitudrenovacion,
  codidlogtrans,
  codnumero_renovacion,
  codserie_renovacion,
  codorden_renovacion,
  codclase_niza_renovacion,
  codfecha_concesion,
  codtitular,
  codapoderado,
  codtipo_marca,
  codtipo_genero,
  codsigno_registrado,
  codetiqueta_renovacion,
  codnumero_registro,
  codserie_registro,
  codnumero_clase_niza_actual,
  codsr_manual,
  codgestion_sr_manual,
  codfecha_registro_manual,
  codfecha_ingreso,
  codlista_producto_actual,
  codlista_producto_renovacion,
  codversion_clase_niza,
  codfecha_renovacion,
  codgestion_renovacion,
  codestado
  );

seq_renrenovacion = (select currval('sec_renrenovacion')) ;
for obj_renrenovacion in (select *
 from renrenovacion
 where idrenovacion = seq_renrenovacion
 ) loop
return next obj_renrenovacion;
end loop;
end if;
if operacion=2 then -- update
    update renrenovacion set
    idsolicitudrenovacion=codidsolicitudrenovacion,
    idlogtrans=codidlogtrans,
    numero_renovacion=codnumero_renovacion,
    serie_renovacion=codserie_renovacion,
    orden_renovacion=codorden_renovacion,
    clase_niza_renovacion=codclase_niza_renovacion,
    fecha_concesion=codfecha_concesion,
    titular=codtitular,
    apoderado=codapoderado,
    tipo_marca=codtipo_marca,
    tipo_genero=codtipo_genero,
    signo_registrado=codsigno_registrado,
    etiqueta_renovacion=codetiqueta_renovacion,
    numero_registro=codnumero_registro,
    serie_registro=codserie_registro,
    numero_clase_niza_actual=codnumero_clase_niza_actual,
    sr_manual=codsr_manual,
    gestion_sr_manual=codgestion_sr_manual,
    fecha_registro_manual=codfecha_registro_manual,
    fecha_ingreso=codfecha_ingreso,
    lista_producto_actual=codlista_producto_actual,
    lista_producto_renovacion=codlista_producto_renovacion,
    version_clase_niza=codversion_clase_niza,
    fecha_renovacion=codfecha_renovacion,
    gestion_renovacion=codgestion_renovacion,
    estado=codestado  
      where idrenovacion=codidrenovacion;

for obj_renrenovacion in (select *
 from renrenovacion
 where idrenovacion=codidrenovacion
 ) loop
 return next obj_renrenovacion;
 end loop;



end if;
if operacion =3 then-- delete
     delete from renrenovacion where idrenovacion= codidrenovacion;
end if;
if operacion=4 then --read
  for obj_renrenovacion  in
    (
    select * from renrenovacion
    where estado = 'AC'
    order by idrenovacion asc
    )
  loop
  return next obj_renrenovacion;
  end loop;

end if;
END;
$BODY$
  LANGUAGE plpgsql;

/*
************************************************************************************************************************************************
-- Function: crud_renresolucion(bigint, bigint, integer, integer, timestamp without time zone, character varying, text, character varying, integer)

-- DROP FUNCTION crud_renresolucion(bigint, bigint, integer, integer, timestamp without time zone, character varying, text, character varying, integer);
*/
CREATE OR REPLACE FUNCTION crud_renresolucion(codidresolucion bigint, codidrenovacion bigint, codnumero_resolucion integer, codgestion_resolucion integer, codfecha_resolucion timestamp without time zone, codobservacion_resolucion character varying, coddocumento_resolucion text, codestado character varying, operacion integer)
  RETURNS SETOF renresolucion AS
$BODY$

/*
Creado: Chano Rojas Fecha:30/08/2016
Realizar el crud de la tabla de crud_renresolucion
*/

DECLARE
id bigint;
seq_renresolucion bigint;
obj_renresolucion renresolucion%ROWTYPE;
BEGIN
if operacion=1 then -- Create

insert into renresolucion(
  idrenovacion,
  numero_resolucion,
  gestion_resolucion,
  fecha_resolucion,
  observacion_resolucion,
  documento_resolucion,
  estado 
  )
  values(
   codidrenovacion ,
   codnumero_resolucion ,
   codgestion_resolucion ,
   codfecha_resolucion  ,
   codobservacion_resolucion ,
   coddocumento_resolucion ,
   codestado);
  
  seq_renresolucion = (select currval('sec_renresolucion')) ;
  for obj_renresolucion in (select *
  from renresolucion
  where idresolucion = seq_renresolucion
  ) loop
  return next obj_renresolucion;
  end loop;

end if;
if operacion=2 then -- update
    update renresolucion set 
    idrenovacion=codidrenovacion,
    numero_resolucion=codnumero_resolucion,
    gestion_resolucion=codgestion_resolucion,
    fecha_resolucion=codfecha_resolucion,
    observacion_resolucion=codobservacion_resolucion,
    documento_resolucion=coddocumento_resolucion,
    estado=codestado
      where idresolucion=codidresolucion;

for obj_renresolucion in (select *
 from renresolucion
 where idresolucion=codidresolucion
 ) loop
 return next obj_renresolucion;
 end loop;
end if;
if operacion =3 then-- delete
     delete from renresolucion where idresolucion= codidresolucion;
end if;
if operacion=4 then --read
  for obj_renresolucion  in
    (
    select * from renresolucion
    where estado = 'AC'
    order by idresolucion asc
    )
  loop
  return next obj_renresolucion;
  end loop;

end if;
END;
$BODY$
LANGUAGE plpgsql;

/*
Creado: ChanoRojas Fecha: 20/09/2016
Listar modresolucion por un idrenresolucion en solicitud de modificacion
*/
CREATE OR REPLACE FUNCTION obtener_renresolucion_idrenovacion(pidrenovacion bigint)
  RETURNS SETOF renresolucion AS
$BODY$
DECLARE 
vresolucion renresolucion%ROWTYPE; 
/*
Creado: Chano Rojas Fecha:30/08/2016
Realizar el crud de la tabla de crud_renresolucion
*/
BEGIN


for vresolucion in select * from renresolucion 
   where  idrenovacion = pidrenovacion and estado='AC'

   loop
   return next vresolucion;
end loop;
end;
$BODY$
 LANGUAGE plpgsql;



/*
*********************************************************************************
Creado: Chano Rojas Fecha:29/09/2016
Realizar crud en historial de forma general
*/



CREATE OR REPLACE FUNCTION crud_historial_general(
  pidhistorial bigint,
  pidsolicitudrenovacion bigint,
  pidusuario bigint,
  pidlogtrans bigint,
  ptipo character varying,
  poperacion character varying,
  pestado_marca_descripcion character varying,
  pobservacion text,
  pubicacion_descripcion character varying,
  pseccion character varying,
  pgestion_renovacion integer,
  pdescripcion text,
  pdescripcion_lista_productos text,
  pfecha_operacion timestamp without time zone,
  pestado character varying,
  prefijo character varying,
  operacion integer
  )
  RETURNS SETOF renhistorial AS
$BODY$

/*
Creado: Chano Rojas Fecha:22/09/2016
Realizar crud en historial de forma general
*/


DECLARE
seq_sighistorial bigint;
seq_modhistorial bigint;
seq_renhistorial bigint;
vmodhistorial modhistorial%ROWTYPE;
vsighistorial sighistorial%ROWTYPE;
vrenhistorial renhistorial%ROWTYPE;
BEGIN

if operacion=1 then -- Create
IF prefijo = 'MOD' THEN
insert into modhistorial(

  idmodificacion,
  idusuario,
  idlogtrans,
  tipo,
  operacion,
  estado_marca_descripcion,
  observacion,
  ubicacion_descripcion,
  seccion,
  gestion_renovacion,
  descripcion,
  descripcion_lista_productos,
  fecha_operacion,
  estado
)
values( 
  pidsolicitudrenovacion,
  pidusuario,
  pidlogtrans,
  ptipo,
  poperacion,
  pestado_marca_descripcion,
  pobservacion,
  pubicacion_descripcion,
  pseccion,
  pgestion_renovacion,
  pdescripcion,
  pdescripcion_lista_productos,
  pfecha_operacion,
  pestado
);
seq_modhistorial = (select currval('sec_modhistorial')) ;
for vmodhistorial in (select *
 from modhistorial
 where idhistorial = seq_modhistorial
 ) loop
return next vmodhistorial;
end loop;
END IF;  
-------renovaciones
IF prefijo = 'REN' THEN
insert into renhistorial(
  idsolicitudrenovacion,
  idusuario,
  idlogtrans,
  tipo,
  operacion,
  estado_marca_descripcion,
  observacion,
  ubicacion_descripcion,
  seccion,
  gestion_renovacion,
  descripcion,
  descripcion_lista_productos,
  fecha_operacion,
  estado
)
values( 
  pidsolicitudrenovacion,
  pidusuario,
  pidlogtrans,
  ptipo,
  poperacion,
  pestado_marca_descripcion,
  pobservacion,
  pubicacion_descripcion,
  pseccion,
  pgestion_renovacion,
  pdescripcion,
  pdescripcion_lista_productos,
  pfecha_operacion,
  pestado
);
seq_renhistorial = (select currval('sec_renhistorial')) ;
for vrenhistorial in (select *
 from renhistorial
 where idhistorial = seq_renhistorial
 ) loop
return next vrenhistorial;
end loop;
END IF;  
-----------signos
IF prefijo = 'SIG' THEN
insert into sighistorial(
  idsignomarca,
  idusuario,
  idlogtrans,
  tipo,
  operacion,
  estado_marca_descripcion,
  observacion,
  ubicacion_descripcion,
  seccion,
  gestion_renovacion,
  descripcion,
  descripcion_lista_productos,
  fecha_operacion,
  estado
  )
values( 
  pidsolicitudrenovacion,
  pidusuario,
  pidlogtrans,
  ptipo,
  poperacion,
  pestado_marca_descripcion,
  pobservacion,
  pubicacion_descripcion,
  pseccion,
  pgestion_renovacion,
  pdescripcion,
  pdescripcion_lista_productos,
  pfecha_operacion,
  pestado
);
seq_sighistorial = (select currval('sec_sighistorial')) ;
for vsighistorial in (select *
 from sighistorial
 where idhistorial = seq_sighistorial 
 ) loop
return next vsighistorial;
end loop;
END IF;  
end if;----fin create

       
END;
$BODY$
  LANGUAGE plpgsql;

/*
********************************************************************************
Creado: Susana Escobar Paz Fecha:01/09/2016
Lista de domijio filtrado por dominio y dominiopadre
*/
CREATE OR REPLACE FUNCTION lista_dominio_dominiopadre(pdominio character varying, pdominiopadre character varying)
  RETURNS SETOF dominio AS
$BODY$
/*
Creado: Susana Escobar Paz Fecha:01/09/2016
Lista de domijio filtrado por dominio y dominiopadre
*/
 DECLARE 
    vdominio dominio%ROWTYPE; 
BEGIN

for vdominio in select * from dominio where  dominio = pdominio
	and dominiopadre = pdominiopadre loop
    return next vdominio;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;

/*
********************************************************************************
Creado: Eddy Valero Fecha: 06/09/2016
Obtener el listado de los items de ventanilla dependiendo si el codigo ingresado es SM, REN o MOD.
*/

CREATE OR REPLACE FUNCTION listar_elementosventanilla_codigo(pcodigo character varying)
  RETURNS SETOF elementoformulariotramite AS
$BODY$
/*
Creado: Eddy Valero Fecha:06/09/2016
Obtener el listado de elementosventanilla por un codigo
*/
 DECLARE 
    velementoformulariotramite elementoformulariotramite%ROWTYPE; 
BEGIN

	for velementoformulariotramite in (
		select * from elementoformulariotramite e
		where exists (
			select * from formulariotramite f
			where f.estado = 'AC'
			     and f.idformulariotramite = e.idformulariotramite
			     and f.etapa = 'VENT'
			     and f.codigo = pcodigo
			)
		and estado = 'AC'	
		order by orden asc	
		)
	loop
		return next velementoformulariotramite;
	end loop;
end;
$BODY$
  LANGUAGE plpgsql;


/*
********************************************************************************
Creado: Susana Escobar Paz Fecha: 06/01/2017
Realizar funcion para guardar o modificar plantilla en tablas sigdatoelementoformulario, moddatoelementoformulario, rendatoelementoformulario
*/
CREATE OR REPLACE FUNCTION crud_datoelementoformulario(
    piddatoelementoformulario bigint,
    pnombre_tabla character varying,
    pidseguimiento bigint,
    pidlogtrans bigint,
    ppestania character varying,
    pseccion integer,
    pfila integer,
    ptipo_elemento character varying,
    pnombre_elemento text,
    porden integer,
    porden_literal character varying,
    pidpadre bigint,
    pfechainicio timestamp without time zone,
    pfechafin timestamp without time zone,
    prespuesta text,
    popcion_respuesta text,
    pestado character varying,
    prefijotabla character varying,
    operacion integer)
  RETURNS SETOF moddatoelementoformulario AS
$BODY$

DECLARE
vid bigint;
seq_sigdatoelementoformulario bigint;
seq_moddatoelementoformulario bigint;
seq_rendatoelementoformulario bigint;
vsigdatoelementoformulario sigdatoelementoformulario%ROWTYPE;
vmoddatoelementoformulario moddatoelementoformulario%ROWTYPE;
vrendatoelementoformulario rendatoelementoformulario%ROWTYPE;

BEGIN

if operacion=1 then -- Create
	--------Signos
	IF prefijotabla = 'SIG' THEN	
	insert into sigdatoelementoformulario(
	nombre_tabla, idseguimiento, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, orden_literal, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado
	)values(
	pnombre_tabla, pidseguimiento, pidlogtrans, ppestania, pseccion, pfila, ptipo_elemento, pnombre_elemento, porden, porden_literal, pidpadre, pfechainicio, pfechafin, prespuesta, popcion_respuesta, pestado
	);
	seq_sigdatoelementoformulario = (select currval('sec_sigdatoelementoformulario')) ;
	for vsigdatoelementoformulario in (select *
	 from sigdatoelementoformulario
	 where iddatoelementoformulario = seq_sigdatoelementoformulario
	 ) loop
	return next vsigdatoelementoformulario;
	end loop;
	END IF; 
	--------Modificaciones
	IF prefijotabla = 'MOD' THEN
	insert into moddatoelementoformulario(
	nombre_tabla, idseguimiento, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, orden_literal, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado
	)values(
	pnombre_tabla, pidseguimiento, pidlogtrans, ppestania, pseccion, pfila, ptipo_elemento, pnombre_elemento, porden, porden_literal, pidpadre, pfechainicio, pfechafin, prespuesta, popcion_respuesta, pestado
	);
	seq_moddatoelementoformulario = (select currval('sec_moddatoelementoformulario')) ;
	for vmoddatoelementoformulario in (select *
	 from moddatoelementoformulario
	 where iddatoelementoformulario = seq_moddatoelementoformulario
	 ) loop
	return next vmoddatoelementoformulario;
	end loop;
        END IF; 
        --------Renovaciones
	IF prefijotabla = 'REN' THEN	
	insert into rendatoelementoformulario(
	nombre_tabla, idseguimiento, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, orden_literal, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado
	)values(
	pnombre_tabla, pidseguimiento, pidlogtrans, ppestania, pseccion, pfila, ptipo_elemento, pnombre_elemento, porden, porden_literal, pidpadre, pfechainicio, pfechafin, prespuesta, popcion_respuesta, pestado
	);
	seq_rendatoelementoformulario = (select currval('sec_rendatoelementoformulario')) ;
	for vrendatoelementoformulario in (select *
	 from rendatoelementoformulario
	 where iddatoelementoformulario = seq_rendatoelementoformulario
	 ) loop
	return next vrendatoelementoformulario;
	end loop;
        END IF; 
end if;


if operacion=2 then -- update
	---signos
        IF prefijotabla = 'SIG' THEN	
	  update sigdatoelementoformulario set
	  iddatoelementoformulario = piddatoelementoformulario,
          nombre_tabla = pnombre_tabla,
	  idseguimiento = pidseguimiento,
	  idlogtrans = pidlogtrans,
	  pestania = ppestania,
	  seccion = pseccion,
	  fila = pfila,
	  tipo_elemento = ptipo_elemento,
	  nombre_elemento = pnombre_elemento,
	  orden = porden,
	  orden_literal = porden_literal,
	  idpadre = pidpadre,
	  fechainicio = pfechainicio,
	  fechafin = pfechafin,
	  respuesta = prespuesta,
	  opcion_respuesta = popcion_respuesta,
	  estado = pestado	
	  where iddatoelementoformulario = piddatoelementoformulario;	
	  for vsigdatoelementoformulario in (select *
          from sigdatoelementoformulario
          where iddatoelementoformulario = piddatoelementoformulario
          ) loop
          return next vsigdatoelementoformulario;
          end loop;
	END IF; 
	----modificaciones
	IF prefijotabla = 'MOD' THEN	
	  update moddatoelementoformulario set
	  iddatoelementoformulario = piddatoelementoformulario,
          nombre_tabla = pnombre_tabla,
	  idseguimiento = pidseguimiento,
	  idlogtrans = pidlogtrans,
	  pestania = ppestania,
	  seccion = pseccion,
	  fila = pfila,
	  tipo_elemento = ptipo_elemento,
	  nombre_elemento = pnombre_elemento,
	  orden = porden,
	  orden_literal = porden_literal,
	  idpadre = pidpadre,
	  fechainicio = pfechainicio,
	  fechafin = pfechafin,
	  respuesta = prespuesta,
	  opcion_respuesta = popcion_respuesta,
	  estado = pestado	
	  where iddatoelementoformulario = piddatoelementoformulario;	
	  for vmoddatoelementoformulario in (select *
          from moddatoelementoformulario
          where iddatoelementoformulario = piddatoelementoformulario
          ) loop
          return next vmoddatoelementoformulario;
          end loop;
	END IF; 
	----renovaciones
	IF prefijotabla = 'REN' THEN	
	  update rendatoelementoformulario set
	  iddatoelementoformulario = piddatoelementoformulario,
          nombre_tabla = pnombre_tabla,
	  idseguimiento = pidseguimiento,
	  idlogtrans = pidlogtrans,
	  pestania = ppestania,
	  seccion = pseccion,
	  fila = pfila,
	  tipo_elemento = ptipo_elemento,
	  nombre_elemento = pnombre_elemento,
	  orden = porden,
	  orden_literal = porden_literal,
	  idpadre = pidpadre,
	  fechainicio = pfechainicio,
	  fechafin = pfechafin,
	  respuesta = prespuesta,
	  opcion_respuesta = popcion_respuesta,
	  estado = pestado	
	  where iddatoelementoformulario = piddatoelementoformulario;	
	  for vrendatoelementoformulario in (select *
          from rendatoelementoformulario
          where iddatoelementoformulario = piddatoelementoformulario
          ) loop
          return next vrendatoelementoformulario;
          end loop;
	END IF; 
end if;


/*
if operacion =3 then-- delete
     delete from regional where idregional = codidregional;
end if;
if operacion=4 then --read
	for obj_regional  in
		(
		select * from regional
		where estado = 'AC'
		order by nombre asc
		)
	loop
	return next obj_regional;
	end loop;

end if;



  if operacion!=4 then 
	vsec_datoelementoformulario = (select currval('sec_datoelementoformulario')) ;
	for vdatoelementoformulario in (select *
	 from datoelementoformulario
	 where iddatoelementoformulario = vsec_datoelementoformulario
	 ) loop
	return next vdatoelementoformulario;
	end loop;

  end if;
*/

END;
$BODY$
  LANGUAGE plpgsql;
	

/*
********************************************************************************
Creado: Eddy Valero Fecha:08/09/2016
Realizar el crud de la tabla sigseguimiento
*/

CREATE OR REPLACE FUNCTION crud_sigseguimiento(
	pidseguimiento bigint,
	pidsignomarca bigint,
	pidusuario bigint,
	pidlogtrans bigint,
	psm bigint,
	pnumero_publicacion bigint,
	pnumero_registro bigint,
	pserie_registro character varying,
	petapa character varying,
	pfecha_recepcion timestamp without time zone,
	pfecha_fin timestamp without time zone,
	pplazo_real integer,
        ptotal_tiempo bigint,
	pobservacion character varying,
	peditable boolean,
	pestado character varying,
	prefijotabla character varying,
	poperacion integer
  )
  RETURNS SETOF sigseguimiento AS
$BODY$
/*
Creado: Eddy Valero Fecha:08/09/2016
Realizar el crud de la tabla sigseguimiento
*/
DECLARE
vsec_sigseguimiento bigint;
vsigseguimiento sigseguimiento%ROWTYPE;
BEGIN


if poperacion=1 then -- Create

insert into sigseguimiento(
	idsignomarca, idusuario, idlogtrans, sm, numero_publicacion, numero_registro, serie_registro, etapa, fecha_recepcion, fecha_fin, plazo_real,total_tiempo, observacion, editable, estado
	)
  values(
	pidsignomarca, pidusuario, pidlogtrans, psm, pnumero_publicacion, pnumero_registro, pserie_registro, petapa, pfecha_recepcion, pfecha_fin, pplazo_real,ptotal_tiempo, pobservacion, peditable, pestado
	);
end if;



/*
if operacion=2 then -- update
		update regional set idlogtrans=codidlogtrans,
		central=codcentral,
		nombre=codnombre,
		direccion=coddireccion,
		telefono=codtelefono,
		fax=codfax,
		tipo_ciudad_notificacion=codtipo_ciudad_notificacion,
		estado=codestado 
		where idregional=codidregional;
end if;
if operacion =3 then-- delete
     delete from regional where idregional = codidregional;
end if;
if operacion=4 then --read
	for obj_regional  in
		(
		select * from regional
		where estado = 'AC'
		order by nombre asc
		)
	loop
	return next obj_regional;
	end loop;

end if;
*/


  if poperacion!=4 then 
	vsec_sigseguimiento = (select currval('sec_sigseguimiento')) ;
	for vsigseguimiento in (select *
	 from sigseguimiento
	 where idseguimiento = vsec_sigseguimiento
	 ) loop
	return next vsigseguimiento;
	end loop;

  end if;


END;
$BODY$
  LANGUAGE plpgsql;




/*
*************************************************************
Creado: Eddy Valero Fecha:18/11/2016
Realizar registro y modificacion  en tabla usuario
*/
CREATE OR REPLACE FUNCTION crud_usuario(
    pidusuario bigint,
    pidlogtrans bigint,
    pnombre character varying,
    pprimer_apellido character varying,
    psegundo_apellido character varying,
    plogin character varying,
    pcontrasenia character varying,
    pcargo character varying,
    pidregional bigint,
    pnumero_documento character varying,
    ptipo_documento character varying,
    plugar_expedicion character varying,
    pcorreo_electronico character varying,
    pestado character varying,
    poperacion integer)
  RETURNS SETOF usuario AS
$BODY$
/*
Creado: Eddy Valero Fecha:18/11/2016
Realizar registro y modificacion  en tabla usuario
*/
DECLARE
vseq_usuario bigint;
vusuario usuario%ROWTYPE;
BEGIN

	if poperacion=1 then -- Create
		insert into usuario
			(idlogtrans, nombre, primer_apellido, segundo_apellido, login, contrasenia, cargo, idregional, numero_documento, tipo_documento, lugar_expedicion, correo_electronico, estado)
		values
			(pidlogtrans, pnombre, pprimer_apellido, psegundo_apellido, plogin, pcontrasenia, pcargo, pidregional, pnumero_documento, ptipo_documento, plugar_expedicion, pcorreo_electronico, pestado);
			
		vseq_usuario = (select currval('sec_usuario')) ;
		for vusuario in (
			select *
			from usuario
			where idusuario = vseq_usuario
			) loop
			return next vusuario;
		end loop;
	end if;----fin create


	if poperacion=2 then -- update
	update usuario set
		idusuario =pidusuario,
                idlogtrans=pidlogtrans, 
                nombre=pnombre ,
                primer_apellido =pprimer_apellido ,
                segundo_apellido=psegundo_apellido ,
                login=plogin ,
                contrasenia=pcontrasenia,
                cargo=pcargo ,
                idregional=pidregional,
                numero_documento =pnumero_documento ,
                tipo_documento=ptipo_documento ,
                lugar_expedicion=plugar_expedicion ,
                correo_electronico=pcorreo_electronico ,
                estado=pestado
			
		where 	idusuario= pidusuario;
		
	--vseq_usuario = (select currval('sec_usuario')) ;
	for vusuario in (select *
	 from usuario
	 where idusuario = 2
	 ) loop
	return next vusuario;
	end loop;
	end if;----fin update
/*
	if operacion =3 then-- delete
	     delete from usuario where idmodificacion = pidmodificacion;
	end if; ---fin delete


	if operacion=4 then --read
	    for vmodificacion in (select *
	 from usuario
	 where idmodificacion = pidmodificacion
	 ) loop
	return next vmodificacion;
	end loop;
	 
	end if; ---fin read
  */     
END;
$BODY$
  LANGUAGE plpgsql;


/*
********************************************************************************
Creado: Eddy Valero Fecha:10/09/2016
metodo para obtener el seguimiento de un determinado sm
*/
CREATE OR REPLACE FUNCTION obtener_seguimiento_smmarca(
	psm bigint,
	petapa character varying,
	pprefijo character varying
    )
  RETURNS SETOF sigseguimiento AS
$BODY$
/*
Creado: Eddy Valero Fecha:10/09/2016
metodo para obtener el seguimiento de un determinado sm
*/
DECLARE
-- vsec_sigseguimiento bigint;
vsigseguimiento sigseguimiento%ROWTYPE;
vsigsignomarca sigsignomarca%ROWTYPE;
BEGIN

	select marca.* into vsigsignomarca
	from sigsignomarca marca
	where marca.sm = psm;


RAISE notice '%', vsigsignomarca.idsignomarca;

	for vsigseguimiento in (
		select *
		from sigseguimiento
		where etapa = petapa
			and idsignomarca = vsigsignomarca.idsignomarca
			and estado = 'AC'
			order by 1 desc
		) loop
		return next vsigseguimiento;
	end loop;

	--exception
	--	when others then
	--	RAISE EXCEPTION 'Falló la orden SQL: %. El error fue: %',SQLERRM;
		
END;
$BODY$
  LANGUAGE plpgsql;


/*
********************************************************************************
Creado: Eddy Valero Fecha:10/09/2016
metodo para obtener el seguimiento de acuerdo a un idsignomarca indicado
*/
CREATE OR REPLACE FUNCTION listar_sigseguimiento_etapa_iniciado(
	pidsignomarca bigint,
	petapa character varying
	
    )
  RETURNS SETOF sigseguimiento AS
$BODY$
/*
********************************************************
Modificado: Susana Escobar Paz Fecha: 15/03/2017
Obtener registro de seguimiento
*/
DECLARE
-- vsec_sigseguimiento bigint;
vsigseguimiento sigseguimiento%ROWTYPE;
BEGIN
	for vsigseguimiento in (
		select *
from sigseguimiento
where etapa = petapa
and idsignomarca = pidsignomarca
and estado = 'AC' and fecha_fin is null
order by fecha_recepcion desc limit 1
		) loop
		return next vsigseguimiento;
	end loop;

	exception
		when others then
		RAISE EXCEPTION 'Falló la orden SQL: %. El error fue: %',sql,SQLERRM;
		
END;
$BODY$
  LANGUAGE plpgsql;

/*
********************************************************************************
Creado: Eddy Valero Fecha:10/09/2016
metodo para obtener la plantilla de acuerdo a un seguimiento
*/

CREATE OR REPLACE FUNCTION obtplantillaventanillaseguimiento(
						pidseguimiento bigint,
						pnombre_tabla character varying
						)
  RETURNS SETOF datoelementoformulario AS
$BODY$
/*
Creado: Eddy Valero Fecha:10/09/2016
metodo para obtener la plantilla de acuerdo a un seguimiento
*/
 DECLARE 
    reg datoelementoformulario%ROWTYPE; 
BEGIN

for reg in
	(
	select *
	from datoelementoformulario
	where estado = 'AC'
		and idseguimiento = pidseguimiento
		and nombre_tabla = pnombre_tabla
	order by orden asc
	)
loop
    return next reg;
end loop;
end;
$BODY$
  LANGUAGE plpgsql VOLATILE;



/*
********************************************************
Creado: Eddy Valero Fecha:02/09/2016
Realizar el crud de la tabla siglemacomercial
*/
CREATE OR REPLACE FUNCTION crud_siglemacomercial(
	pidlemacomercial bigint,
	pidsignomarca bigint,
	psigno_padre character varying(1000),
	psm_padre bigint,
	pcodigo_sm_padre character varying(250),
	pnumero_registro_padre bigint,
	pserie_registro_padre character varying(2),
	pvigencia character varying(50),
	pestado character varying(2),
	poperacion character varying(50)
	)
  RETURNS SETOF siglemacomercial AS
$BODY$
/*
Creado: Eddy Valero Fecha:02/09/2016
Realizar el crud de la tabla siglemacomercial
*/
DECLARE
	vidlemacomercial bigint;
	vsec_siglemacomercial bigint;
	vsiglemacomercial siglemacomercial%ROWTYPE;
BEGIN

	IF poperacion='REGISTRAR' THEN-- Create

		insert into siglemacomercial
			(idsignomarca, signo_padre, sm_padre, codigo_sm_padre, numero_registro_padre, serie_registro_padre, vigencia, estado)
		values
			(pidsignomarca, psigno_padre, psm_padre, pcodigo_sm_padre, pnumero_registro_padre, pserie_registro_padre, pvigencia, pestado);
	END IF;

	vsec_siglemacomercial = (select currval('sec_siglemacomercial'));

	for vsiglemacomercial in (
				select * from siglemacomercial
				where idlemacomercial = vsec_siglemacomercial
				)loop
					return next vsiglemacomercial;
	end loop;

/*
if operacion=2 then -- update
		update regional set idlogtrans=codidlogtrans,
		central=codcentral,
		nombre=codnombre,
		direccion=coddireccion,
		telefono=codtelefono,
		fax=codfax,
		tipo_ciudad_notificacion=codtipo_ciudad_notificacion,
		estado=codestado 
		where idregional=codidregional;
end if;
if operacion =3 then-- delete
     delete from regional where idregional = codidregional;
end if;
if operacion=4 then --read
	for obj_regional  in
		(
		select * from regional
		where estado = 'AC'
		order by nombre asc
		)
	loop
	return next obj_regional;
	end loop;

end if;
if operacion!=4 then 
seq_regional = (select currval('sec_regional')) ;
for obj_regional in (select *
 from regional
 where idregional = seq_regional
 ) loop
return next obj_regional;
end loop;




end if;
*/
END;
$BODY$
LANGUAGE plpgsql;

/*
********************************************************
Creado: Susana Escobar Paz Fecha:14/09/2016
busqueda por codigo sm en tabla sigsignomarca
*/
CREATE OR REPLACE FUNCTION lista_sigsignomarca_sm(    
    psm bigint
    )
  RETURNS SETOF sigsignomarca AS
$BODY$
/*
********************************************************
Creado: Susana Escobar Paz Fecha:14/09/2016
busqueda por codigo sm en tabla sigsignomarca
*/
 DECLARE 
    vsignomarca sigsignomarca%ROWTYPE; 
BEGIN

for vsignomarca in select * from sigsignomarca where sm=psm and estado= 'AC' loop
    return next vsignomarca;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;
/*
********************************************************************************
Creado: Levi Laura Fecha:31/08/2016
elimina un registro de notificacion tomando en cuenta  el numero de bloque y el usuario
*/
CREATE OR REPLACE FUNCTION elimina_notificacion_bloqueusuario(pbloque bigint,pidusuario bigint)
  RETURNS void AS
$BODY$
/*
Creado: Levi Laura Fecha:31/08/2016
elimina un registro de notificacion tomando en cuenta  el numero de bloque y el usuario
*/
DECLARE
BEGIN
  delete from notificacion where bloque=pbloque and id_usuario_solicitante=pidusuario;
END;
$BODY$
  LANGUAGE plpgsql;
/*
********************************************************************************
Creado: Levi Laura Fecha:31/08/2016
elimina un registro de notificacion tomando en cuenta  el numero de idnotificacion
*/
CREATE OR REPLACE FUNCTION elimina_registro_idnotificacion(pidnotificacion bigint)
  RETURNS void AS
$BODY$
/*

Creado: Levi Laura Fecha:31/08/2016
elimina un registro de notificacion tomando en cuenta  el numero de idnotificacion
*/
DECLARE
BEGIN
  delete from notificacion where idnotificacion=pidnotificacion;
END;
$BODY$
  LANGUAGE plpgsql;

/*
********************************************************************************
Creado: Levi Laura Fecha:31/08/2016
Inerta un registro de notificacion
*/
CREATE OR REPLACE FUNCTION inserta_notificacion(
    idlogtrans bigint,
    bloque integer,
    nro_exped integer,
    id_usuario_solicitante bigint,
    tipo_tramite_notificacion character varying,
    expediente character varying,
    gestion integer,
    extension character varying,
    demandante character varying,
    demandante_cod_estado character varying,
    demandante_fecha_devol timestamp without time zone,
    demandante_fecha_noti timestamp without time zone,
    demandante_lugar_notificacion character varying,
    demandante_solic character varying,
    demandante_apod character varying,
    demandante_fojas character varying,
    demandante_con character varying,
    demandante_direc character varying,
    demandante_cel character varying,
    demandado character varying,
    demandado_cod_estado character varying,
    demandado_fecha_devol timestamp without time zone,
    demandado_fecha_noti timestamp without time zone,
    demandado_lugar_notificacion character varying,
    demandado_solic character varying,
    demandado_apod character varying,
    demandado_fojas character varying,
    demandado_con character varying,
    demandado_direc character varying,
    demandado_cel character varying,
    fecha_ingreso timestamp without time zone,
    tipo integer,
    obs text,
    historial text,
    fecha_recep timestamp without time zone,
    obs_notifi text,
    id_usuario_notificador bigint,
    estado_marca character varying,
    form_noti_pre text,
    form_noti_cuerpo text)
  RETURNS SETOF notificacion AS
$BODY$
/*

Creado: Levi Laura Fecha:31/08/2016
Inerta un registro de notificacion
*/
DECLARE
seq_notificacion bigint;
obj_notificacion notificacion%ROWTYPE;
BEGIN


insert into notificacion(idlogtrans,bloque,nro_exped,id_usuario_solicitante,tipo_tramite_notificacion,expediente,gestion,extension,demandante,demandante_cod_estado,demandante_fecha_devol,
                          demandante_fecha_noti,demandante_lugar_notificacion,demandante_solic,demandante_apod,demandante_fojas,demandante_con,demandante_direc,demandante_cel,demandado,
                          demandado_cod_estado,demandado_fecha_devol,demandado_fecha_noti,demandado_lugar_notificacion,demandado_solic,demandado_apod,demandado_fojas,demandado_con,demandado_direc,
                          demandado_cel,fecha_ingreso,tipo,obs,historial,fecha_recep,obs_notifi,id_usuario_notificador ,estado_marca,form_noti_pre,form_noti_cuerpo)

                          values(idlogtrans,bloque,nro_exped,id_usuario_solicitante,tipo_tramite_notificacion,expediente,gestion,extension,demandante,demandante_cod_estado,demandante_fecha_devol,
                          demandante_fecha_noti,demandante_lugar_notificacion,demandante_solic,demandante_apod,demandante_fojas,demandante_con,demandante_direc,demandante_cel,demandado,
                          demandado_cod_estado,demandado_fecha_devol,demandado_fecha_noti,demandado_lugar_notificacion,demandado_solic,demandado_apod,demandado_fojas,demandado_con,demandado_direc,
                          demandado_cel,fecha_ingreso,tipo,obs,historial,fecha_recep,obs_notifi,id_usuario_notificador ,estado_marca,form_noti_pre,form_noti_cuerpo);

seq_notificacion = (select currval('sec_notificacion'));


for obj_notificacion in (select *
 from notificacion
 where idnotificacion = seq_notificacion
 ) loop
return next obj_notificacion;
end loop;

       
END;
$BODY$
  LANGUAGE plpgsql; 
/*
********************************************************************************
Creado: Levi Laura Fecha:31/08/2016
Lista objeto notificacion dado datos de modificacion, y se busca en sus tablas de modificacion 
*/

CREATE OR REPLACE FUNCTION lista_notificaciondatosmodi_modisigla(
    psigla character varying,
    pnumero bigint,
    pgestion bigint)
  RETURNS notificacion AS
$BODY$
/*

Creado: Levi Laura Fecha:31/08/2016
Lista objeto notificacion dado datos de modificacion, y se busca en sus tablas de modificacion 
*/
 DECLARE 
    descripcion character varying;
    idmodifica bigint;
   
    vlistsolici modsolicitanteapoderado%ROWTYPE;
    vlistapod modsolicitanteapoderado%ROWTYPE;
    vlistanotifi moddirecciondenotificacion%ROWTYPE;
    vlistanoticel moddirecciondenotificacion%ROWTYPE;
    vlistanotificacion  notificacion;
    vsolicitante character varying;
    vapoderado character varying;
    vdirnotifica character varying;
    vcelnotifica character varying;
    vciudadnotifica character varying;

        BEGIN
      vsolicitante:='';
      vapoderado:='';
      vcelnotifica:='';
      vdirnotifica:='';
      select signo_registrado into descripcion from modmodificacion where sigla=psigla and  numero=pnumero  and gestion = pgestion ;
      raise notice 'descripcion: %' ,descripcion;
     if (descripcion is not null) then

        select idmodificacion into idmodifica from modmodificacion where sigla=psigla and  numero=pnumero  and gestion = pgestion ;
                -- SACO LOS SOLICITANTES Y LSO VOY CONCATENANDO
       for vlistsolici in
          select * from modsolicitanteapoderado where tipo_persona='SOLI' and idmodificacion=idmodifica order by idsolicitanteapoderado asc
       loop
               

                if (vlistsolici.primer_apellido = '' or  vlistsolici.primer_apellido is null)
                then
                    vsolicitante:=vsolicitante ||'; '|| vlistsolici.nombre_razonsocial;
                else
                  -- vsolicitante:=vsolicitante ||'; '|| vlistsolici.nombre_razonsocial||' '||vlistsolici.primer_apellido||' '||vlistsolici.segundo_apellido;
                      vsolicitante:=vsolicitante ||'; '|| vlistsolici.nombre_razonsocial||' '||vlistsolici.primer_apellido;
                end if;

       end loop;
       -- aqui si osi debo quitar el primer ';'
       vsolicitante:=substring(vsolicitante from 2);
       -- aquie si o si debo quitar el espacio vacio que dejo en la parte izquierda
       vsolicitante:= trim(leading ' ' from vsolicitante);
       RAISE NOTICE '%d',vsolicitante;

-- SACO LOS APODERADOS Y LOS VOY CONCATENANDO
     
       for vlistapod in
          select * from modsolicitanteapoderado where tipo_persona='APOD' and idmodificacion=idmodifica order by idsolicitanteapoderado asc
       loop
          --      RAISE NOTICE '%',vlistapod.nombre_razonsocial;

                if (vlistapod.primer_apellido = '' or vlistapod.primer_apellido is null)
                then
                    vapoderado:=vapoderado ||'; '|| vlistapod.nombre_razonsocial;
                else
                   --vapoderado:=vapoderado ||'; '|| vlistapod.nombre_razonsocial||' '||vlistapod.primer_apellido||' '||vlistapod.segundo_apellido;
                    vapoderado:=vapoderado ||'; '|| vlistapod.nombre_razonsocial||' '||vlistapod.primer_apellido;
                end if;

       end loop;
       if (vapoderado = '')
       then
           raise notice 'no tiene apoderado';
       else
            -- aqui si osi debo quitar el primer ';'
            vapoderado:=substring(vapoderado from 2);
           -- aquie si o si debo quitar el espacio vacio que dejo en la parte izquierda
           vapoderado:= trim(leading ' ' from vapoderado);
       end if;
         RAISE NOTICE '%',vapoderado;

        -- SACO LOS CELULARES PARA NOTIFICAR
     
         
        for vlistanoticel in
          select * from moddirecciondenotificacion where idmodificacion=idmodifica 
       loop
            if (vlistanoticel.telefono !='' and  vlistanoticel.telefono is not null)
             then
               vcelnotifica:=vlistanoticel.telefono;
             
            end if; 
            if(vcelnotifica = '')
            then
                vcelnotifica:=vlistanoticel.celular;  
            else
               if (vlistanoticel.celular !='' and  vlistanoticel.celular is not null)
               then
                 vcelnotifica:=vcelnotifica||' ;'||vlistanoticel.celular;
               end if;     
            end if;
            
       end loop;
       vcelnotifica:= trim(leading ' ' from vcelnotifica);
           RAISE NOTICE '%',vcelnotifica;
       
 -- SACO LA DIRECIONDE NOTIFICACION
      
       for vlistanotifi in
          select * from moddirecciondenotificacion where idmodificacion=idmodifica 
       loop
                  select nombre into vciudadnotifica from dominio where codigo=vlistanotifi.ciudad_notificacion and dominio='ciudad_notificacion';
                  if (vciudadnotifica is not null)
                  then
                     vdirnotifica:=vciudadnotifica;
                      if (vlistanotifi.zona_barrio != '' and vlistanotifi.zona_barrio is not null)
                     then
                      
                        vdirnotifica:=vdirnotifica||' ,'||vlistanotifi.zona_barrio;
                       
                     end if;
                  else
                      if (vlistanotifi.zona_barrio != '' and vlistanotifi.zona_barrio is not null)
                      then
                      
                        vdirnotifica:=vlistanotifi.zona_barrio;
                         
                       end if; 
                     
                  end if;  




                  
                  if (vlistanotifi.avenida_calle != '' and vlistanotifi.avenida_calle is not null)
                  then
                     vdirnotifica:=vdirnotifica||' ,'||vlistanotifi.avenida_calle;
                  end if;


                  if (vlistanotifi.numero != '' and vlistanotifi.numero is not null)
                  then
                        vdirnotifica:=vdirnotifica||' ,'||vlistanotifi.numero;
                  end if;

                   if (vlistanotifi.edificio != '' and vlistanotifi.edificio is not null)
                  then
                        vdirnotifica:=vdirnotifica||' ,'||vlistanotifi.edificio;
                  end if;

                    if (vlistanotifi.piso != '' and vlistanotifi.piso is not null)
                  then
                        vdirnotifica:=vdirnotifica||' ,'||vlistanotifi.piso;
                  end if;

                    if (vlistanotifi.departamento != '' and vlistanotifi.departamento is not null)
                  then
                        vdirnotifica:=vdirnotifica||' ,'||vlistanotifi.departamento;
                  end if;

                  if (vlistanotifi.referencia_direccion != '' and vlistanotifi.referencia_direccion is not null)
                  then
                        vdirnotifica:=vdirnotifica||' ,'||vlistanotifi.referencia_direccion;
                  end if;
                
       end loop;
          -- aquie si o si debo quitar el espacio vacio que dejo en la parte izquierda
           vdirnotifica:= trim(trailing ' ' from vdirnotifica);
          RAISE NOTICE 'DirNotifica::%',vdirnotifica;
     end if; 
     


   vlistanotificacion.demandante:=descripcion;
   vlistanotificacion.demandante_solic:=vsolicitante;
   vlistanotificacion.demandante_apod:=vapoderado;
   vlistanotificacion.demandante_cel:=vcelnotifica;
    vlistanotificacion.demandante_direc:=vdirnotifica;
    return vlistanotificacion;
    END;
$BODY$
  LANGUAGE plpgsql;
/***************************************************************/
/*
Creado: Levi Laura Fecha:31/08/2016
Lista objeto notificacion dado datos de oposiciones, y se busca en sus tablas de oposicion 
*/
CREATE OR REPLACE FUNCTION lista_notificaciondatosopo_nopo(
    pnro_opo bigint,
    porden_o bigint)
  RETURNS notificacion AS
$BODY$

/*
Creado: Levi Laura Fecha:31/08/2016
Lista objeto notificacion dado datos de oposiciones, y se busca en sus tablas de oposicion 
*/

 DECLARE 
    vidoposicion bigint;
    vidmarcademandada bigint;
    vidmarcademandante bigint;

    descripcion_dante character varying;
    descripcion_dada character varying;
    vsolicitante_dante character varying;
    vsolicitante_dada character varying;
    vapoderado_dante character varying;
    vapoderado_dada character varying;
    vcelnotifica_dante character varying;
    vcelnotifica_dada character varying;
    vdirnotifica_dante character varying;
    vdirnotifica_dada character varying;

    
    idsignomar bigint;
    --vsigsolicitanteapoderado sigsolicitanteapoderado%ROWTYPE;
    vlistsolici_dante oposolicitanteapoderado%ROWTYPE;
    vlistsolici_dada oposolicitanteapoderado%ROWTYPE;
    vlistapod_dante oposolicitanteapoderado%ROWTYPE;
    vlistapod_dada oposolicitanteapoderado%ROWTYPE;
    vlistapod oposolicitanteapoderado%ROWTYPE;
    vlistanoticel_dante oponotificacion%ROWTYPE;
    vlistanoticel_dada oponotificacion%ROWTYPE;
    vlistanotifi_dante oponotificacion%ROWTYPE;
    vlistanotifi_dada oponotificacion%ROWTYPE;

    
    vlistanoticel oponotificacion%ROWTYPE;
    vlistanotificacion  notificacion;
   


    vapoderado character varying;
    
  
  
BEGIN
   -- SACO PRMERAMENTE EL SIGNO SI EXISTE RECIEN SACO TODO LO DEMAS
      vsolicitante_dante:='';
      vsolicitante_dada:='';
      vapoderado:='';
      vcelnotifica_dante:='';
      vcelnotifica_dada:='';
      vdirnotifica_dante:='';
      vdirnotifica_dada:='';
      --vdirnotifica:='';
      vapoderado_dante:='';
      vapoderado_dada:='';
      select idoposicion into vidoposicion from oposicion where nro_opo=pnro_opo
      and orden_o=porden_o;
    if (vidoposicion is not null) then
      -- Primero agarrro las marcas de ambos
       select dmdo_marca_lnv into descripcion_dada from opomarcademandada where idoposicion=vidoposicion;
       select dmte_marca_lnv into descripcion_dante from opomarcademandante where idoposicion=vidoposicion;
       RAISE NOTICE '%',descripcion_dada;
       RAISE NOTICE '%',descripcion_dante;
       
      -- Ahora agarro el id ed cada uno para buscar en apoderados y notificaciones  
       select idmarcademandada into vidmarcademandada from opomarcademandada where idoposicion=vidoposicion;
       select idmarcademandante into vidmarcademandante from opomarcademandante where idoposicion=vidoposicion;


      
        -- SACO LOS SOLICITANTES DEMANDADO Y LSO VOY CONCATENANDO
       for vlistsolici_dada in
        
          select * from oposolicitanteapoderado where idmarcademandada=vidmarcademandada and tipo_persona='SOLI' order by idsolicitanteapoderado asc
       loop
               

                if (vlistsolici_dada.primer_apellido = '' or  vlistsolici_dada.primer_apellido is null)
                then
                    vsolicitante_dada:=vsolicitante_dada ||'; '|| vlistsolici_dada.nombre_razonsocial;
                else
                   vsolicitante_dada:=vsolicitante ||'; '|| vlistsolici_dada.nombre_razonsocial||' '||vlistsolici_dada.primer_apellido||' '||vlistsolici_dada.segundo_apellido;
                end if;

       end loop;
       -- aqui si osi debo quitar el primer ';'
       vsolicitante_dada:=substring(vsolicitante_dada from 2);
       -- aquie si o si debo quitar el espacio vacio que dejo en la part e izquierda
       vsolicitante_dada:= trim(leading ' ' from vsolicitante_dada);
       RAISE NOTICE 'Demandada:%',vsolicitante_dada;
       
     -- SACO LOS SOLICITANTES DEMANDANTE Y LSO VOY CONCATENANDO
      for vlistsolici_dante in
          select * from oposolicitanteapoderado where idmarcademandante=vidmarcademandante and tipo_persona='SOLI' order by idsolicitanteapoderado asc
      loop
             if (vlistsolici_dante.primer_apellido = '' or  vlistsolici_dante.primer_apellido is null)
                then
                    vsolicitante_dante:=vsolicitante_dante ||'; '|| vlistsolici_dante.nombre_razonsocial;
                else
                   vsolicitante_dante:=vsolicitante_dante ||'; '|| vlistsolici_dante.nombre_razonsocial||' '||vlistsolici_dante.primer_apellido||' '||vlistsolici_dante.segundo_apellido;
                end if;

       end loop;
    -- aqui si osi debo quitar el primer ';'
       vsolicitante_dante:=substring(vsolicitante_dante from 2);
       -- aquie si o si debo quitar el espacio vacio que dejo en la parte izquierda
       vsolicitante_dante:= trim(leading ' ' from vsolicitante_dante);
       RAISE NOTICE 'Demandante:%',vsolicitante_dante;


    -- SACO LOS APODERADOS DEL DEMANDANTE
       
      for vlistapod_dante in
          select * from oposolicitanteapoderado where idmarcademandante=vidmarcademandante and tipo_persona='APOD' order by idsolicitanteapoderado asc
      loop
          if (vlistapod_dante.primer_apellido = '' or  vlistapod_dante.primer_apellido is null)
                then
                    vapoderado_dante:=vapoderado_dante ||'; '|| vlistapod_dante.nombre_razonsocial;
                else
                   vapoderado_dante:=vapoderado_dante ||'; '|| vlistapod_dante.nombre_razonsocial||' '||vlistapod_dante.primer_apellido||' '||vlistapod_dante.segundo_apellido;
                end if;

       end loop;
        -- aqui si osi debo quitar el primer ';'
       vapoderado_dante:=substring(vapoderado_dante from 2);
       -- aquie si o si debo quitar el espacio vacio que dejo en la parte izquierda
       vapoderado_dante:= trim(leading ' ' from vapoderado_dante);
         RAISE NOTICE 'Apoderado Demandante:%',vapoderado_dante;
    -- SACO LOS APODERADOS DEL DEMANDADO
      for vlistapod_dada in
          select * from oposolicitanteapoderado where idmarcademandada=vidmarcademandada and tipo_persona='APOD' order by idsolicitanteapoderado asc
      loop
          if (vlistapod_dada.primer_apellido = '' or  vlistapod_dada.primer_apellido is null)
                then
                    vapoderado_dada:=vapoderado_dada ||'; '|| vlistapod_dada.nombre_razonsocial;
                else
                   vapoderado_dada:=vapoderado_dada ||'; '|| vlistapod_dada.nombre_razonsocial||' '||vlistapod_dada.primer_apellido||' '||vlistapod_dada.segundo_apellido;
                end if;

       end loop;
            -- aqui si osi debo quitar el primer ';'
       vapoderado_dada:=substring(vapoderado_dada from 2);
       -- aquie si o si debo quitar el espacio vacio que dejo en la parte izquierda
       vapoderado_dada:= trim(leading ' ' from vapoderado_dada);
       RAISE NOTICE 'Apoderado Demandado:%',vapoderado_dada;
       --SACO LOS CELULARES DE NOTIFICACION DEMANDANTE
      
      for vlistanoticel_dante in
          select * from oponotificacion where idmarcademandante=vidmarcademandante
       loop
            if (vlistanoticel_dante.telefono !='' and  vlistanoticel_dante.telefono is not null)
             then
               vcelnotifica_dante:=vlistanoticel_dante.telefono;
             
            end if; 
            if(vcelnotifica_dante = '')
            then
                vcelnotifica_dante:=vlistanoticel_dante.celular;  
            else
               if (vlistanoticel_dante.celular !='' and  vlistanoticel_dante.celular is not null)
               then
                 vcelnotifica_dante:=vcelnotifica_dante||' ;'||vlistanoticel_dante.celular;
               end if;     
            end if;
            
       end loop;
       vcelnotifica_dante:= trim(leading ' ' from vcelnotifica_dante);
           RAISE NOTICE 'Cel del demandante%',vcelnotifica_dante;

            --SACO LOS CELULARES DE NOTIFICACION DEMANDANTE
      
      for vlistanoticel_dada in
          select * from oponotificacion where idmarcademandada=vidmarcademandada
            loop 
            if (vlistanoticel_dada.telefono !='' and  vlistanoticel_dada.telefono is not null)
             then
               vcelnotifica_dada:=vlistanoticel_dada.telefono;
             
            end if; 
            if(vcelnotifica_dada = '')
            then
                vcelnotifica_dada:=vlistanoticel_dada.celular;  
            else
               if (vlistanoticel_dada.celular !='' and  vlistanoticel_dada.celular is not null)
               then
                 vcelnotifica_dada:=vcelnotifica_dada||' ;'||vlistanoticel_dada.celular;
               end if;     
            end if;
            
       end loop;
       vcelnotifica_dada:= trim(leading ' ' from vcelnotifica_dada);
           RAISE NOTICE 'Cel del demandada%',vcelnotifica_dada;
       -- SACO LA DIRECCION DE NOTIFICAICON DE DEMANDANTE
        for vlistanotifi_dante in
          select * from oponotificacion where idmarcademandante=vidmarcademandante
       loop
                  vdirnotifica_dante:=vlistanotifi_dante.ciudad_notificacion;
                  if (vlistanotifi_dante.zona_barrio != '' and vlistanotifi_dante.zona_barrio is not null)
                  then
                     vdirnotifica_dante:=vdirnotifica_dante||' ,'||vlistanotifi_dante.zona_barrio;
                  end if;

                 
                  if (vlistanotifi_dante.avenida_calle != '' and vlistanotifi_dante.avenida_calle is not null)
                  then
                     vdirnotifica_dante:=vdirnotifica_dante||' ,'||vlistanotifi_dante.avenida_calle;
                  end if;


                  if (vlistanotifi_dante.numero != '' and vlistanotifi_dante.numero is not null)
                  then
                        vdirnotifica_dante:=vdirnotifica_dante||' ,'||vlistanotifi_dante.numero;
                  end if;

                   if (vlistanotifi_dante.edificio != '' and vlistanotifi_dante.edificio is not null)
                  then
                        vdirnotifica_dante:=vdirnotifica_dante||' ,'||vlistanotifi_dante.edificio;
                  end if;

                    if (vlistanotifi_dante.piso != '' and vlistanotifi_dante.piso is not null)
                  then
                        vdirnotifica_dante:=vdirnotifica_dante||' ,'||vlistanotifi_dante.piso;
                  end if;

                    if (vlistanotifi_dante.numero_departamento != '' and vlistanotifi_dante.numero_departamento is not null)
                  then
                        vdirnotifica_dante:=vdirnotifica_dante||' ,'||vlistanotifi_dante.numero_departamento;
                  end if;

                  if (vlistanotifi_dante.referencia_direccion != '' and vlistanotifi_dante.referencia_direccion is not null)
                  then
                        vdirnotifica_dante:=vdirnotifica_dante||' ,'||vlistanotifi_dante.referencia_direccion;
                  end if;
                 
       end loop;
-- aquie si o si debo quitar el espacio vacio que dejo en la parte izquierda
           vdirnotifica_dante:= trim(trailing ' ' from vdirnotifica_dante);
          RAISE NOTICE 'direccion demandante:%',vdirnotifica_dante;
  -- SACO LA DIRECCION DE NOTIFICAICON DE DEMANDADO
        for vlistanotifi_dada in
          select * from oponotificacion where idmarcademandada=vidmarcademandada
       loop
                  vdirnotifica_dada:=vlistanotifi_dada.ciudad_notificacion;
                  if (vlistanotifi_dada.zona_barrio != '' and vlistanotifi_dada.zona_barrio is not null)
                  then
                     vdirnotifica_dada:=vdirnotifica_dada||' ,'||vlistanotifi_dada.zona_barrio;
                  end if;

                 
                  if (vlistanotifi_dada.avenida_calle != '' and vlistanotifi_dada.avenida_calle is not null)
                  then
                     vdirnotifica_dada:=vdirnotifica_dada||' ,'||vlistanotifi_dada.avenida_calle;
                  end if;


                  if (vlistanotifi_dada.numero != '' and vlistanotifi_dada.numero is not null)
                  then
                        vdirnotifica_dada:=vdirnotifica_dada||' ,'||vlistanotifi_dada.numero;
                  end if;

                   if (vlistanotifi_dada.edificio != '' and vlistanotifi_dada.edificio is not null)
                  then
                        vdirnotifica_dada:=vdirnotifica_dada||' ,'||vlistanotifi_dada.edificio;
                  end if;

                    if (vlistanotifi_dada.piso != '' and vlistanotifi_dada.piso is not null)
                  then
                        vdirnotifica_dada:=vdirnotifica_dada||' ,'||vlistanotifi_dada.piso;
                  end if;

                    if (vlistanotifi_dada.numero_departamento != '' and vlistanotifi_dada.numero_departamento is not null)
                  then
                        vdirnotifica_dada:=vdirnotifica_dada||' ,'||vlistanotifi_dada.numero_departamento;
                  end if;

                  if (vlistanotifi_dada.referencia_direccion != '' and vlistanotifi_dada.referencia_direccion is not null)
                  then
                        vdirnotifica_dada:=vdirnotifica_dada||' ,'||vlistanotifi_dada.referencia_direccion;
                  end if;
                 
       end loop;
-- aquie si o si debo quitar el espacio vacio que dejo en la parte izquierda
           vdirnotifica_dada:= trim(trailing ' ' from vdirnotifica_dada);
          RAISE NOTICE 'direccion demandada:%',vdirnotifica_dada;
    



     
   end if;

  vlistanotificacion.demandante:=descripcion_dante;
  vlistanotificacion.demandado:=descripcion_dada;
   vlistanotificacion.demandante_solic:=vsolicitante_dante;
   vlistanotificacion.demandado_solic:=vsolicitante_dada;
  vlistanotificacion.demandante_apod:=vapoderado_dante;
  vlistanotificacion.demandado_apod:=vapoderado_dada;
  vlistanotificacion.demandante_cel:=vcelnotifica_dante;
  vlistanotificacion.demandado_cel:=vcelnotifica_dada;
  vlistanotificacion.demandante_direc:=vdirnotifica_dante;
  vlistanotificacion.demandado_direc:=vdirnotifica_dada;

/*   

   vlistanotificacion.demandante_direc:=vdirnotifica;
*/
   RETURN vlistanotificacion;
END;
$BODY$
  LANGUAGE plpgsql ;

/*
********************************************************************************
Creado: Levi Laura Fecha:31/08/2016
Lista objeto notificacion dado el numero de publicacion, y se busca en sus tablas de publicacion 
*/
CREATE OR REPLACE FUNCTION lista_notificaciondatossm_numpubl(
    pnumero_publicacion bigint
   )
  RETURNS notificacion AS
$BODY$
/*
Creado: Levi Laura Fecha:31/08/2016
Lista objeto notificacion dado el numero de publicacion, y se busca en sus tablas de publicacion
*/
 DECLARE 
    descripcion character varying;
    idsignomar bigint;
    
    --vsigsolicitanteapoderado sigsolicitanteapoderado%ROWTYPE;
    vlistsolici sigsolicitanteapoderado%ROWTYPE;
    vlistapod sigsolicitanteapoderado%ROWTYPE;
    vlistanotifi sigdirecciondenotificacion%ROWTYPE;
    vlistanoticel sigdirecciondenotificacion%ROWTYPE;
    vlistanotificacion  notificacion;
    vsolicitante character varying;
    vapoderado character varying;
    vdirnotifica character varying;
    vcelnotifica character varying;
  
BEGIN
   -- SACO PRMERAMENTE EL SIGNO SI EXISTE RECIEN SACO TODO LO DEMAS
      vsolicitante:='';
      vapoderado:='';
      vcelnotifica:='';
      vdirnotifica:='';
     select idsignomarca into idsignomar from sigsignomarca where numero_publicacion=pnumero_publicacion and estado ='AC';
      

--select signo into descripcion from sigsignomarca where sm=psm;

    if (idsignomar is not null) then
       select signo into descripcion from sigsignomarca where idsignomarca=idsignomar;
        
      
      -- select idsignomarca into idsignomar from sigsignomarca where sm=psm;
        -- SACO LOS SOLICITANTES Y LSO VOY CONCATENANDO
       for vlistsolici in
          select * from sigsolicitanteapoderado where tipo_persona='SOLI' and idsignomarca=idsignomar order by idsolicitanteapoderado asc
       loop
               

                if (vlistsolici.primer_apellido = '' or  vlistsolici.primer_apellido is null)
                then
                    vsolicitante:=vsolicitante ||'; '|| vlistsolici.nombre_razonsocial;
                else
                  -- vsolicitante:=vsolicitante ||'; '|| vlistsolici.nombre_razonsocial||' '||vlistsolici.primer_apellido||' '||vlistsolici.segundo_apellido;
                     vsolicitante:=vsolicitante ||'; '|| vlistsolici.nombre_razonsocial||' '||vlistsolici.primer_apellido|;
                end if;

       end loop;
       -- aqui si osi debo quitar el primer ';'
       vsolicitante:=substring(vsolicitante from 2);
       -- aquie si o si debo quitar el espacio vacio que dejo en la parte izquierda
       vsolicitante:= trim(leading ' ' from vsolicitante);
       RAISE NOTICE '%d',vsolicitante;
       -- SACO LOS APODERADOS Y LOS VOY CONCATENANDO
     
       for vlistapod in
          select * from sigsolicitanteapoderado where tipo_persona='APOD' and idsignomarca=idsignomar order by idsolicitanteapoderado asc
       loop
          --      RAISE NOTICE '%',vlistapod.nombre_razonsocial;

                if (vlistapod.primer_apellido = '' or vlistapod.primer_apellido is null)
                then
                    vapoderado:=vapoderado ||'; '|| vlistapod.nombre_razonsocial;
                else
                  -- vapoderado:=vapoderado ||'; '|| vlistapod.nombre_razonsocial||' '||vlistapod.primer_apellido||' '||vlistapod.segundo_apellido;
                     vapoderado:=vapoderado ||'; '|| vlistapod.nombre_razonsocial||' '||vlistapod.primer_apellido;
                end if;

       end loop;
       if (vapoderado = '')
       then
           raise notice 'no tiene apoderado';
       else
            -- aqui si osi debo quitar el primer ';'
            vapoderado:=substring(vapoderado from 2);
           -- aquie si o si debo quitar el espacio vacio que dejo en la parte izquierda
           vapoderado:= trim(leading ' ' from vapoderado);
       end if;
         RAISE NOTICE '%',vapoderado;
       -- SACO LOS CELULARES PARA NOTIFICAR
     
         
        for vlistanoticel in
          select * from sigdirecciondenotificacion where idsignomarca=idsignomar 
       loop
            if (vlistanoticel.telefono !='' and  vlistanoticel.telefono is not null)
             then
               vcelnotifica:=vlistanoticel.telefono;
             
            end if; 
            if(vcelnotifica = '')
            then
                vcelnotifica:=vlistanoticel.celular;  
            else
               if (vlistanoticel.celular !='' and  vlistanoticel.celular is not null)
               then
                 vcelnotifica:=vcelnotifica||' ;'||vlistanoticel.celular;
               end if;     
            end if;
            
       end loop;
       vcelnotifica:= trim(leading ' ' from vcelnotifica);
           RAISE NOTICE '%',vcelnotifica;
       -- SACO LA DIRECIONDE NOTIFICACION
      
       for vlistanotifi in
          select * from sigdirecciondenotificacion where idsignomarca=idsignomar 
       loop
                       vdirnotifica:=vlistanotifi.ciudad_notificacion;
                  if (vdirnotifica is not null)
                  then
                      if (vlistanotifi.zona_barrio != '' and vlistanotifi.zona_barrio is not null)
                      then
                           vdirnotifica:=vdirnotifica||' ,'||vlistanotifi.zona_barrio;
                      end if;
                  else
                      if (vlistanotifi.zona_barrio != '' and vlistanotifi.zona_barrio is not null)
                       then
                            vdirnotifica:=vlistanotifi.zona_barrio;
                      end if;
                  end if;  



                 
                  if (vlistanotifi.avenida_calle != '' and vlistanotifi.avenida_calle is not null)
                  then
                     vdirnotifica:=vdirnotifica||' ,'||vlistanotifi.avenida_calle;
                  end if;


                  if (vlistanotifi.numero != '' and vlistanotifi.numero is not null)
                  then
                        vdirnotifica:=vdirnotifica||' ,'||vlistanotifi.numero;
                  end if;

                   if (vlistanotifi.edificio != '' and vlistanotifi.edificio is not null)
                  then
                        vdirnotifica:=vdirnotifica||' ,'||vlistanotifi.edificio;
                  end if;

                    if (vlistanotifi.piso != '' and vlistanotifi.piso is not null)
                  then
                        vdirnotifica:=vdirnotifica||' ,'||vlistanotifi.piso;
                  end if;

                    if (vlistanotifi.departamento != '' and vlistanotifi.departamento is not null)
                  then
                        vdirnotifica:=vdirnotifica||' ,'||vlistanotifi.departamento;
                  end if;

                  if (vlistanotifi.referencia_direccion != '' and vlistanotifi.referencia_direccion is not null)
                  then
                        vdirnotifica:=vdirnotifica||' ,'||vlistanotifi.referencia_direccion;
                  end if;
                 
       end loop;
          -- aquie si o si debo quitar el espacio vacio que dejo en la parte izquierda
           vdirnotifica:= trim(trailing ' ' from vdirnotifica);
          RAISE NOTICE '%',vdirnotifica;
   end if;


   
   vlistanotificacion.demandante:=descripcion;
   vlistanotificacion.demandante_solic:=vsolicitante;
   vlistanotificacion.demandante_apod:=vapoderado;
   vlistanotificacion.demandante_cel:=vcelnotifica;
   vlistanotificacion.demandante_direc:=vdirnotifica;

   RETURN vlistanotificacion;
END;
$BODY$
  LANGUAGE plpgsql;
/*
********************************************************************************
Creado: Levi Laura Fecha:31/08/2016
Lista objeto notificacion dado el numero de registro, y se busca en sus tablas de Registro 
*/
CREATE OR REPLACE FUNCTION lista_notificaciondatossm_numreg(pnumero_registro bigint, pserie character varying)
  RETURNS notificacion AS
$BODY$

/*

Creado: Levi Laura Fecha:31/08/2016
Lista objeto notificacion dado el numero de registro, y se busca en sus tablas de Registro
*/
 DECLARE 
    descripcion character varying;
    idsignomar bigint;
    
    --vsigsolicitanteapoderado sigsolicitanteapoderado%ROWTYPE;
    vlistsolici sigsolicitanteapoderado%ROWTYPE;
    vlistapod sigsolicitanteapoderado%ROWTYPE;
    vlistanotifi sigdirecciondenotificacion%ROWTYPE;
    vlistanoticel sigdirecciondenotificacion%ROWTYPE;
    vlistanotificacion  notificacion;
    vsolicitante character varying;
    vapoderado character varying;
    vdirnotifica character varying;
    vcelnotifica character varying;
  
BEGIN
   -- SACO PRMERAMENTE EL SIGNO SI EXISTE RECIEN SACO TODO LO DEMAS
      vsolicitante:='';
      vapoderado:='';
      vcelnotifica:='';
      vdirnotifica:='';
     --select idsignomarca into idsignomar from sigregistro where numero_registro=pnumero_registro and serie=pserie and estado ='AC';
     select idsignomarca into idsignomar from sigsignomarca where numero_registro=pnumero_registro and serie_registro=pserie and estado ='AC';

--select signo into descripcion from sigsignomarca where sm=psm;

    if (idsignomar is not null) then
       select signo into descripcion from sigsignomarca where idsignomarca=idsignomar;
        
      
      -- select idsignomarca into idsignomar from sigsignomarca where sm=psm;
        -- SACO LOS SOLICITANTES Y LSO VOY CONCATENANDO
       for vlistsolici in
          select * from sigsolicitanteapoderado where tipo_persona='SOLI' and idsignomarca=idsignomar order by idsolicitanteapoderado asc
       loop
               

                if (vlistsolici.primer_apellido = '' or  vlistsolici.primer_apellido is null)
                then
                    vsolicitante:=vsolicitante ||'; '|| vlistsolici.nombre_razonsocial;
                else
                  -- vsolicitante:=vsolicitante ||'; '|| vlistsolici.nombre_razonsocial||' '||vlistsolici.primer_apellido||' '||vlistsolici.segundo_apellido;
                     vsolicitante:=vsolicitante ||'; '|| vlistsolici.nombre_razonsocial||' '||vlistsolici.primer_apellido;
                end if;

       end loop;
       -- aqui si osi debo quitar el primer ';'
       vsolicitante:=substring(vsolicitante from 2);
       -- aquie si o si debo quitar el espacio vacio que dejo en la parte izquierda
       vsolicitante:= trim(leading ' ' from vsolicitante);
       RAISE NOTICE '%d',vsolicitante;
       -- SACO LOS APODERADOS Y LOS VOY CONCATENANDO
     
       for vlistapod in
          select * from sigsolicitanteapoderado where tipo_persona='APOD' and idsignomarca=idsignomar order by idsolicitanteapoderado asc
       loop
          --      RAISE NOTICE '%',vlistapod.nombre_razonsocial;

                if (vlistapod.primer_apellido = '' or vlistapod.primer_apellido is null)
                then
                    vapoderado:=vapoderado ||'; '|| vlistapod.nombre_razonsocial;
                else
                    -- vapoderado:=vapoderado ||'; '|| vlistapod.nombre_razonsocial||' '||vlistapod.primer_apellido||' '||vlistapod.segundo_apellido;
                       vapoderado:=vapoderado ||'; '|| vlistapod.nombre_razonsocial||' '||vlistapod.primer_apellido;
                end if;

       end loop;
       if (vapoderado = '')
       then
           raise notice 'no tiene apoderado';
       else
            -- aqui si osi debo quitar el primer ';'
            vapoderado:=substring(vapoderado from 2);
           -- aquie si o si debo quitar el espacio vacio que dejo en la parte izquierda
           vapoderado:= trim(leading ' ' from vapoderado);
       end if;
         RAISE NOTICE '%',vapoderado;
       -- SACO LOS CELULARES PARA NOTIFICAR
     
         
        for vlistanoticel in
          select * from sigdirecciondenotificacion where idsignomarca=idsignomar 
       loop
            if (vlistanoticel.telefono !='' and  vlistanoticel.telefono is not null)
             then
               vcelnotifica:=vlistanoticel.telefono;
             
            end if; 
            if(vcelnotifica = '')
            then
                vcelnotifica:=vlistanoticel.celular;  
            else
               if (vlistanoticel.celular !='' and  vlistanoticel.celular is not null)
               then
                 vcelnotifica:=vcelnotifica||' ;'||vlistanoticel.celular;
               end if;     
            end if;
            
       end loop;
       vcelnotifica:= trim(leading ' ' from vcelnotifica);
           RAISE NOTICE '%',vcelnotifica;
       -- SACO LA DIRECIONDE NOTIFICACION
      
       for vlistanotifi in
          select * from sigdirecciondenotificacion where idsignomarca=idsignomar 
       loop
                 vdirnotifica:=vlistanotifi.ciudad_notificacion;
                  if (vdirnotifica is not null)
                  then
                      if (vlistanotifi.zona_barrio != '' and vlistanotifi.zona_barrio is not null)
                      then
                           vdirnotifica:=vdirnotifica||' ,'||vlistanotifi.zona_barrio;
                      end if;
                  else
                      if (vlistanotifi.zona_barrio != '' and vlistanotifi.zona_barrio is not null)
                       then
                            vdirnotifica:=vlistanotifi.zona_barrio;
                      end if;
                  end if;  

                 
                  if (vlistanotifi.avenida_calle != '' and vlistanotifi.avenida_calle is not null)
                  then
                     vdirnotifica:=vdirnotifica||' ,'||vlistanotifi.avenida_calle;
                  end if;


                  if (vlistanotifi.numero != '' and vlistanotifi.numero is not null)
                  then
                        vdirnotifica:=vdirnotifica||' ,'||vlistanotifi.numero;
                  end if;

                   if (vlistanotifi.edificio != '' and vlistanotifi.edificio is not null)
                  then
                        vdirnotifica:=vdirnotifica||' ,'||vlistanotifi.edificio;
                  end if;

                    if (vlistanotifi.piso != '' and vlistanotifi.piso is not null)
                  then
                        vdirnotifica:=vdirnotifica||' ,'||vlistanotifi.piso;
                  end if;

                    if (vlistanotifi.departamento != '' and vlistanotifi.departamento is not null)
                  then
                        vdirnotifica:=vdirnotifica||' ,'||vlistanotifi.departamento;
                  end if;

                  if (vlistanotifi.referencia_direccion != '' and vlistanotifi.referencia_direccion is not null)
                  then
                        vdirnotifica:=vdirnotifica||' ,'||vlistanotifi.referencia_direccion;
                  end if;
                 
       end loop;
          -- aquie si o si debo quitar el espacio vacio que dejo en la parte izquierda
           vdirnotifica:= trim(trailing ' ' from vdirnotifica);
          RAISE NOTICE '%',vdirnotifica;
   end if;


   
   vlistanotificacion.demandante:=descripcion;
   vlistanotificacion.demandante_solic:=vsolicitante;
   vlistanotificacion.demandante_apod:=vapoderado;
   vlistanotificacion.demandante_cel:=vcelnotifica;
   vlistanotificacion.demandante_direc:=vdirnotifica;

   RETURN vlistanotificacion;
END;
$BODY$
  LANGUAGE plpgsql;
/*
********************************************************************************
Creado: Levi Laura Fecha:31/08/2016
Lista objeto notificacion dado el numero de sm, y se busca en sus tablas de Signos 
*/
CREATE OR REPLACE FUNCTION lista_notificaciondatossm_sm(psm bigint)
  RETURNS notificacion AS
$BODY$
/*

Creado: Levi Laura Fecha:31/08/2016
Lista objeto notificacion dado el numero de sm, y se busca en sus tablas de Signos
*/
 DECLARE 
    descripcion character varying;
    idsignomar bigint;
    --vsigsolicitanteapoderado sigsolicitanteapoderado%ROWTYPE;
    vlistsolici sigsolicitanteapoderado%ROWTYPE;
    vlistapod sigsolicitanteapoderado%ROWTYPE;
    vlistanotifi sigdirecciondenotificacion%ROWTYPE;
    vlistanoticel sigdirecciondenotificacion%ROWTYPE;
    vlistanotificacion  notificacion;
    vsolicitante character varying;
    vapoderado character varying;
    vdirnotifica character varying;
    vcelnotifica character varying;
  
BEGIN
   -- SACO PRMERAMENTE EL SIGNO SI EXISTE RECIEN SACO TODO LO DEMAS
      vsolicitante:='';
      vapoderado:='';
      vcelnotifica:='';
      vdirnotifica:='';
      select signo into descripcion from sigsignomarca where sm=psm and estado='AC';
    if (descripcion is not null) then
       
        
      
       select idsignomarca into idsignomar from sigsignomarca where sm=psm estado='AC';
        -- SACO LOS SOLICITANTES Y LSO VOY CONCATENANDO
       for vlistsolici in
          select * from sigsolicitanteapoderado where tipo_persona='SOLI' and idsignomarca=idsignomar order by idsolicitanteapoderado asc
       loop
               

                if (vlistsolici.primer_apellido = '' or  vlistsolici.primer_apellido is null)
                then
                    vsolicitante:=vsolicitante ||'; '|| vlistsolici.nombre_razonsocial;
                else
                  -- vsolicitante:=vsolicitante ||'; '|| vlistsolici.nombre_razonsocial||' '||vlistsolici.primer_apellido||' '||vlistsolici.segundo_apellido;
                      vsolicitante:=vsolicitante ||'; '|| vlistsolici.nombre_razonsocial||' '||vlistsolici.primer_apellido;
                end if;

       end loop;
       -- aqui si osi debo quitar el primer ';'
       vsolicitante:=substring(vsolicitante from 2);
       -- aquie si o si debo quitar el espacio vacio que dejo en la parte izquierda
       vsolicitante:= trim(leading ' ' from vsolicitante);
       RAISE NOTICE '%d',vsolicitante;
       -- SACO LOS APODERADOS Y LOS VOY CONCATENANDO
     
       for vlistapod in
          select * from sigsolicitanteapoderado where tipo_persona='APOD' and idsignomarca=idsignomar order by idsolicitanteapoderado asc
       loop
          --      RAISE NOTICE '%',vlistapod.nombre_razonsocial;

                if (vlistapod.primer_apellido = '' or vlistapod.primer_apellido is null)
                then
                    vapoderado:=vapoderado ||'; '|| vlistapod.nombre_razonsocial;
                else
                   --vapoderado:=vapoderado ||'; '|| vlistapod.nombre_razonsocial||' '||vlistapod.primer_apellido||' '||vlistapod.segundo_apellido;
                     vapoderado:=vapoderado ||'; '|| vlistapod.nombre_razonsocial||' '||vlistapod.primer_apellido; 
                end if;

       end loop;
       if (vapoderado = '')
       then
           raise notice 'no tiene apoderado';
       else
            -- aqui si osi debo quitar el primer ';'
            vapoderado:=substring(vapoderado from 2);
           -- aquie si o si debo quitar el espacio vacio que dejo en la parte izquierda
           vapoderado:= trim(leading ' ' from vapoderado);
       end if;
         RAISE NOTICE '%',vapoderado;
       -- SACO LOS CELULARES PARA NOTIFICAR
     
         
        for vlistanoticel in
          select * from sigdirecciondenotificacion where idsignomarca=idsignomar 
       loop
            if (vlistanoticel.telefono !='' and  vlistanoticel.telefono is not null)
             then
               vcelnotifica:=vlistanoticel.telefono;
             
            end if; 
            if(vcelnotifica = '')
            then
                vcelnotifica:=vlistanoticel.celular;  
            else
               if (vlistanoticel.celular !='' and  vlistanoticel.celular is not null)
               then
                 vcelnotifica:=vcelnotifica||' ;'||vlistanoticel.celular;
               end if;     
            end if;
            
       end loop;
       vcelnotifica:= trim(leading ' ' from vcelnotifica);
           RAISE NOTICE '%',vcelnotifica;
       -- SACO LA DIRECIONDE NOTIFICACION
      
       for vlistanotifi in
          select * from sigdirecciondenotificacion where idsignomarca=idsignomar 
       loop
                 vdirnotifica:=vlistanotifi.ciudad_notificacion;
                  if (vdirnotifica is not null)
                  then

                     if (vlistanotifi.zona_barrio != '' and vlistanotifi.zona_barrio is not null)
                     then
                            vdirnotifica:=vdirnotifica||' ,'||vlistanotifi.zona_barrio;
                      end if;
                  else
                      if (vlistanotifi.zona_barrio != '' and vlistanotifi.zona_barrio is not null)
                       then
                            vdirnotifica:=vlistanotifi.zona_barrio;
                      end if;
                  end if;  

                 
                  if (vlistanotifi.avenida_calle != '' and vlistanotifi.avenida_calle is not null)
                  then
                     vdirnotifica:=vdirnotifica||' ,'||vlistanotifi.avenida_calle;
                  end if;


                  if (vlistanotifi.numero != '' and vlistanotifi.numero is not null)
                  then
                        vdirnotifica:=vdirnotifica||' ,'||vlistanotifi.numero;
                  end if;

                   if (vlistanotifi.edificio != '' and vlistanotifi.edificio is not null)
                  then
                        vdirnotifica:=vdirnotifica||' ,'||vlistanotifi.edificio;
                  end if;

                    if (vlistanotifi.piso != '' and vlistanotifi.piso is not null)
                  then
                        vdirnotifica:=vdirnotifica||' ,'||vlistanotifi.piso;
                  end if;

                    if (vlistanotifi.departamento != '' and vlistanotifi.departamento is not null)
                  then
                        vdirnotifica:=vdirnotifica||' ,'||vlistanotifi.departamento;
                  end if;

                  if (vlistanotifi.referencia_direccion != '' and vlistanotifi.referencia_direccion is not null)
                  then
                        vdirnotifica:=vdirnotifica||' ,'||vlistanotifi.referencia_direccion;
                  end if;
                 
       end loop;
          -- aquie si o si debo quitar el espacio vacio que dejo en la parte izquierda
           vdirnotifica:= trim(trailing ' ' from vdirnotifica);
          RAISE NOTICE '%',vdirnotifica;
   end if;


   
   vlistanotificacion.demandante:=descripcion;
   vlistanotificacion.demandante_solic:=vsolicitante;
   vlistanotificacion.demandante_apod:=vapoderado;
   vlistanotificacion.demandante_cel:=vcelnotifica;
   vlistanotificacion.demandante_direc:=vdirnotifica;

   RETURN vlistanotificacion;
END;
$BODY$
  LANGUAGE plpgsql;
/*
********************************************************************************
Creado: Levi Laura Fecha:31/08/2016
Lista objeto notificacion dado el numero de srm, y se busca en sus tablas de Renovaciones
*/
CREATE OR REPLACE FUNCTION lista_notificaciondatossr_sr(psr bigint, pgestion bigint)
  RETURNS notificacion AS
$BODY$
/*

Creado: Levi Laura Fecha:31/08/2016
Lista objeto notificacion dado el numero de srm, y se busca en sus tablas de Renovaciones
*/

 DECLARE 
    descripcion character varying;
    idrenova bigint;
   
    vlistsolici rensolicitanteapoderado%ROWTYPE;
    vlistapod rensolicitanteapoderado%ROWTYPE;
    vlistanotifi rendirecciondenotificacion%ROWTYPE;
    vlistanoticel rendirecciondenotificacion%ROWTYPE;
    vlistanotificacion  notificacion;
    vsolicitante character varying;
    vapoderado character varying;
    vdirnotifica character varying;
    vcelnotifica character varying;
    vciudadnotifica character varying;

    BEGIN
      vsolicitante:='';
      vapoderado:='';
      vcelnotifica:='';
      vdirnotifica:='';
      select signo_registrado into descripcion from rensolicitudrenovacion where sr=psr and gestion_sr = pgestion;
      raise notice 'descripcion: %' ,descripcion;
     if (descripcion is not null) then

        select idsolicitudrenovacion into idrenova from rensolicitudrenovacion where sr=psr and gestion_sr = pgestion;
                -- SACO LOS SOLICITANTES Y LSO VOY CONCATENANDO
       for vlistsolici in
          select * from rensolicitanteapoderado where tipo_persona='SOLI' and idsolicitudrenovacion=idrenova order by idsolicitanteapoderado asc
       loop
               

                if (vlistsolici.primer_apellido = '' or  vlistsolici.primer_apellido is null)
                then
                    vsolicitante:=vsolicitante ||'; '|| vlistsolici.nombre_razonsocial;
                else
                    --vsolicitante:=vsolicitante ||'; '|| vlistsolici.nombre_razonsocial||' '||vlistsolici.primer_apellido||' '||vlistsolici.segundo_apellido;
                    vsolicitante:=vsolicitante ||'; '|| vlistsolici.nombre_razonsocial||' '||vlistsolici.primer_apellido;
                end if;

       end loop;
       -- aqui si osi debo quitar el primer ';'
       vsolicitante:=substring(vsolicitante from 2);
       -- aquie si o si debo quitar el espacio vacio que dejo en la parte izquierda
       vsolicitante:= trim(leading ' ' from vsolicitante);
       RAISE NOTICE '%d',vsolicitante;

-- SACO LOS APODERADOS Y LOS VOY CONCATENANDO
     
       for vlistapod in
          select * from rensolicitanteapoderado where tipo_persona='APOD' and idsolicitudrenovacion=idrenova order by idsolicitanteapoderado asc
       loop
          --      RAISE NOTICE '%',vlistapod.nombre_razonsocial;

                if (vlistapod.primer_apellido = '' or vlistapod.primer_apellido is null)
                then
                    vapoderado:=vapoderado ||'; '|| vlistapod.nombre_razonsocial;
                else
                   --vapoderado:=vapoderado ||'; '|| vlistapod.nombre_razonsocial||' '||vlistapod.primer_apellido||' '||vlistapod.segundo_apellido;
                     vapoderado:=vapoderado ||'; '|| vlistapod.nombre_razonsocial||' '||vlistapod.primer_apellido;
                end if;

       end loop;
       if (vapoderado = '')
       then
           raise notice 'no tiene apoderado';
       else
            -- aqui si osi debo quitar el primer ';'
            vapoderado:=substring(vapoderado from 2);
           -- aquie si o si debo quitar el espacio vacio que dejo en la parte izquierda
           vapoderado:= trim(leading ' ' from vapoderado);
       end if;
         RAISE NOTICE '%',vapoderado;

        -- SACO LOS CELULARES PARA NOTIFICAR
     
         
        for vlistanoticel in
          select * from rendirecciondenotificacion where idsolicitudrenovacion=idrenova 
       loop
            if (vlistanoticel.telefono !='' and  vlistanoticel.telefono is not null)
             then
               vcelnotifica:=vlistanoticel.telefono;
             
            end if; 
            if(vcelnotifica = '')
            then
                vcelnotifica:=vlistanoticel.celular;  
            else
               if (vlistanoticel.celular !='' and  vlistanoticel.celular is not null)
               then
                 vcelnotifica:=vcelnotifica||' ;'||vlistanoticel.celular;
               end if;     
            end if;
            
       end loop;
       vcelnotifica:= trim(leading ' ' from vcelnotifica);
           RAISE NOTICE '%',vcelnotifica;
       
 -- SACO LA DIRECIONDE NOTIFICACION
      
       for vlistanotifi in
          select * from rendirecciondenotificacion where idsolicitudrenovacion=idrenova 
       loop
                    select nombre into vciudadnotifica from dominio where codigo=vlistanotifi.ciudad_notificacion and dominio='ciudad_notificacion';
                  if (vciudadnotifica is not null)
                  then
                     vdirnotifica:=vciudadnotifica;
                      if (vlistanotifi.zona_barrio != '' and vlistanotifi.zona_barrio is not null)
                     then
                      
                        vdirnotifica:=vdirnotifica||' ,'||vlistanotifi.zona_barrio;
                       
                     end if;
                  else
                      if (vlistanotifi.zona_barrio != '' and vlistanotifi.zona_barrio is not null)
                      then
                      
                        vdirnotifica:=vlistanotifi.zona_barrio;
                         
                       end if; 
                     
                  end if;  

                 
                  if (vlistanotifi.avenida_calle != '' and vlistanotifi.avenida_calle is not null)
                  then
                     vdirnotifica:=vdirnotifica||' ,'||vlistanotifi.avenida_calle;
                  end if;


                  if (vlistanotifi.numero != '' and vlistanotifi.numero is not null)
                  then
                        vdirnotifica:=vdirnotifica||' ,'||vlistanotifi.numero;
                  end if;

                   if (vlistanotifi.edificio != '' and vlistanotifi.edificio is not null)
                  then
                        vdirnotifica:=vdirnotifica||' ,'||vlistanotifi.edificio;
                  end if;

                    if (vlistanotifi.piso != '' and vlistanotifi.piso is not null)
                  then
                        vdirnotifica:=vdirnotifica||' ,'||vlistanotifi.piso;
                  end if;

                    if (vlistanotifi.departamento != '' and vlistanotifi.departamento is not null)
                  then
                        vdirnotifica:=vdirnotifica||' ,'||vlistanotifi.departamento;
                  end if;

                  if (vlistanotifi.referencia_direccion != '' and vlistanotifi.referencia_direccion is not null)
                  then
                        vdirnotifica:=vdirnotifica||' ,'||vlistanotifi.referencia_direccion;
                  end if;
                 
       end loop;
          -- aquie si o si debo quitar el espacio vacio que dejo en la parte izquierda
           vdirnotifica:= trim(trailing ' ' from vdirnotifica);
          RAISE NOTICE '%',vdirnotifica;
     end if; 
     


   vlistanotificacion.demandante:=descripcion;
   vlistanotificacion.demandante_solic:=vsolicitante;
   vlistanotificacion.demandante_apod:=vapoderado;
   vlistanotificacion.demandante_cel:=vcelnotifica;
   vlistanotificacion.demandante_direc:=vdirnotifica;
    return vlistanotificacion;
    END;
$BODY$
  LANGUAGE plpgsql;
/*
********************************************************************************
Creado: Levi Laura Fecha:31/08/2016
Obtiene una lista de la tabla dominio tomando en cuenta el campo dominio y el codigo
*/
CREATE OR REPLACE FUNCTION obtiene_listadominiocodigo(
    pdominio character varying,
    pcodigo character varying)
  RETURNS SETOF dominio AS
$BODY$
/*

Creado: Levi Laura Fecha:31/08/2016
Obtiene una lista de la tabla dominio tomando en cuenta el campo dominio y el codigo
*/
 DECLARE 
    sm dominio%ROWTYPE; 
BEGIN

for sm in select * from dominio where  dominio = pdominio and codigo= pcodigo loop
    return next sm;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;

/*
********************************************************************************
Creado: Levi Laura Fecha:31/08/2016
Obtiene un nuevo numero de bloque por usuario
*/

CREATE OR REPLACE FUNCTION obtiene_numerobloquenuevonotifica(in_id_usuario_solicitante bigint)
  RETURNS bigint AS
$BODY$
/*

Creado: Levi Laura Fecha:31/08/2016
Obtiene un nuevo numero de bloque por usuario
*/
DECLARE
bloqueresul bigint;



BEGIN

  select bloque into bloqueresul from notificacion where id_usuario_solicitante=in_id_usuario_solicitante order by bloque desc, nro_exped desc;
  bloqueresul:=bloqueresul+1;
 
  return bloqueresul;
END;
$BODY$
  LANGUAGE plpgsql;

/*
********************************************************
Creado: Susana Escobar Paz Fecha:14/09/2016
busqueda por numero de publicacion sm en tabla sigsignomarca
*/
CREATE OR REPLACE FUNCTION lista_sigsignomarca_publicacion(    
    pnumero_publicacion bigint
    )
  RETURNS SETOF sigsignomarca AS
$BODY$
/*
********************************************************
Creado: Susana Escobar Paz Fecha:14/09/2016
busqueda por numero de publicacion sm en tabla sigsignomarca
*/
 DECLARE 
    vsignomarca sigsignomarca%ROWTYPE; 
BEGIN

for vsignomarca in select * from sigsignomarca where numero_publicacion=pnumero_publicacion and estado='AC' loop
    return next vsignomarca;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;


/*
********************************************************
Creado: Susana Escobar Paz Fecha:14/09/2016
busqueda por numero de RENOVACION Y EXTENSION sm en tabla sigsignomarca
*/
CREATE OR REPLACE FUNCTION lista_sigsignomarca_renovacion(    
    prenovacion bigint,
    pserie character varying
    )
  RETURNS SETOF sigsignomarca AS
$BODY$
/*
********************************************************
Creado: Susana Escobar Paz Fecha:14/09/2016
busqueda por numero de RENOVACION Y EXTENSION sm en tabla sigsignomarca
*/
 DECLARE 
    vsignomarca sigsignomarca%ROWTYPE; 
BEGIN

for vsignomarca in select * from sigsignomarca where numero_renovacion=prenovacion 
	and extension_renovacion=pserie and estado='AC'
 loop
    return next vsignomarca;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;


/*
********************************************************
Creado: Susana Escobar Paz Fecha:14/09/2016
busqueda por numero de registro y serie sm en tabla sigsignomarca
*/
CREATE OR REPLACE FUNCTION lista_sigsignomarca_registro(    
    pregistro bigint,
    pserie character varying,
    psigno character varying
    )
  RETURNS SETOF sigsignomarca AS
$BODY$
/*
********************************************************
Creado: Susana Escobar Paz Fecha:14/09/2016
busqueda por numero de registro y serie sm en tabla sigsignomarca
*/
 DECLARE 
    vsignomarca sigsignomarca%ROWTYPE; 
BEGIN

for vsignomarca in select * from sigsignomarca where numero_registro=pregistro 
	and serie_registro=pserie and estado='AC' and signo like '%'||psigno||'%'
 loop
    return next vsignomarca;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;

/*
********************************************************
Creado: Susana Escobar Paz Fecha:24/10/2016
buscar clase la primera clase niza por idsignomarca 
*/
CREATE OR REPLACE FUNCTION lista_sigsignoclaseniza_idsignomarcaprimera(pidsignomarca bigint)
  RETURNS integer AS
$BODY$
/*
Creado: Susana Escobar Paz Fecha:24/10/2016
buscar clase la primera clase niza por idsignomarca 
*/
 DECLARE 
    vclaseniza integer; 
    vtextoclase text;
    vclase integer;
BEGIN
vtextoclase:='';
for vclaseniza in (select numero_clase_niza from sigsignoclaseniza 
		    where sigsignoclaseniza.idsignomarca = pidsignomarca and estado = 'AC') 	
 loop
	 IF vtextoclase = '' THEN		  
		  vtextoclase:= vclaseniza;
		  vclase:=vclaseniza	;	  		                     
         ELSE
                  --vtextoclase:= vtextoclase||' - '||vclaseniza.numero_clase_niza;                   
	END IF; 
end loop;
	return vclase::integer;
end;
$BODY$
  LANGUAGE plpgsql;


/*
********************************************************
Creado: Susana Escobar Paz Fecha:14/09/2016
busqueda por idsignomarca en tabla sigtiposigno
*/
CREATE OR REPLACE FUNCTION lista_sigtiposigno_idsignomarca(    
    pidsignomarca bigint   
    )
  RETURNS SETOF sigtiposigno AS
$BODY$
/*
********************************************************
Creado: Susana Escobar Paz Fecha:14/09/2016
busqueda por idsignomarca en tabla sigtiposigno
*/
 DECLARE 
    vtiposigno sigtiposigno%ROWTYPE; 
BEGIN

for vtiposigno in select * from sigtiposigno where idsignomarca=pidsignomarca and estado = 'AC' 	
 loop
    return next vtiposigno;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;


/*
********************************************************
Creado: Susana Escobar Paz Fecha:14/09/2016
lista por idsignomarca en tabla sigsolicitanteapoderado
*/
CREATE OR REPLACE FUNCTION lista_sigsolicitanteapoderado_idsignomarca(    
    pidsignomarca bigint   
    )
  RETURNS SETOF sigsolicitanteapoderado AS
$BODY$
/*
********************************************************
Creado: Susana Escobar Paz Fecha:14/09/2016
lista por idsignomarca en tabla sigsolicitanteapoderado
*/
 DECLARE 
    vsolicitanteapoderado sigsolicitanteapoderado%ROWTYPE; 
BEGIN

for vsolicitanteapoderado in select * from sigsolicitanteapoderado where idsignomarca=pidsignomarca 	
	and tipo_persona = 'SOLI' and estado='AC'
 loop
    return next vsolicitanteapoderado;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;

/*
********************************************************
Creado: Eddy Valero Fecha:27/09/2016
lista de apoderados para un determinado idmarca
*/
CREATE OR REPLACE FUNCTION lista_apoderados_idsignomarca(    
    pidsignomarca bigint   
    )
  RETURNS SETOF sigsolicitanteapoderado AS
$BODY$
/*
********************************************************
Creado: Susana Escobar Paz Fecha:14/09/2016
lista por idsignomarca en tabla sigsolicitanteapoderado
*/
 DECLARE 
    vsolicitanteapoderado sigsolicitanteapoderado%ROWTYPE; 
BEGIN

for vsolicitanteapoderado in select * from sigsolicitanteapoderado where idsignomarca=pidsignomarca 	
	and tipo_persona = 'APOD' and estado='AC'
 loop
    return next vsolicitanteapoderado;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;


/*
Creado: Susana Paz Escobar Fecha:15/09/2016
Realizar el crud de la tabla modtiposigno
*/
CREATE OR REPLACE FUNCTION crud_modtiposigno(
    pidtiposigno bigint,
    pidmodificacion bigint,
    pidlogtrans bigint,
    ptipo_signo character varying,
    pdescripcion_otro character varying,
    pestado character varying,
    operacion integer)
  RETURNS SETOF modtiposigno AS
$BODY$
/*
Creado: Susana Paz Escobar Fecha:15/09/2016
Realizar el crud de la tabla modtiposigno
*/
DECLARE
seq_modtiposigno bigint;
obj_modtiposigno modtiposigno%ROWTYPE;
BEGIN
if operacion=1 then -- Create

insert into modtiposigno(
  idmodificacion,
  idlogtrans,
  tipo_signo,
  descripcion_otro,
  estado
    )
  values(
  pidmodificacion,
  pidlogtrans,
  ptipo_signo,
  pdescripcion_otro,
  pestado
  );

seq_modtiposigno = (select currval('sec_modtiposigno')) ;
for obj_modtiposigno in (select *
 from modtiposigno
 where idtiposigno = seq_modtiposigno
 ) loop
return next obj_modtiposigno;
end loop;
  
end if;
if operacion=2 then -- update
    update modtiposigno set 
          idmodificacion=pidmodificacion,
          idlogtrans=pidlogtrans,
	  tipo_signo=ptipo_signo,
	  descripcion_otro=pdescripcion_otro,
          estado=pestado
                      where idtiposigno=pidtiposigno;
      
for obj_modtiposigno in (select *
 from modtiposigno
 where idtiposigno=pidtiposigno
 ) loop
 return next obj_modtiposigno;
 end loop;
    
end if;
if operacion =3 then-- delete
     delete from modtiposigno where idtiposigno=pidtiposigno;
end if;

if operacion=4 then --read
  for obj_modtiposigno  in
    (
    select * from modtiposigno
    where idtiposigno=pidtiposigno and estado = 'AC'
    order by idtiposigno asc
    )
  loop
  return next obj_modtiposigno;
  end loop;

end if;
END;
$BODY$
  LANGUAGE plpgsql;
/*
********************************************************************************
Creado: Levi Laura Fecha:30/07/2016
Obtiene registros de notificacion dado el numero de bloque y id_usuario_solicitante
*/
CREATE OR REPLACE FUNCTION obtiene_listanotifi_bloqueusuariosol(
    pbloque integer,
    pidusuario bigint)
  RETURNS SETOF notificacion AS
$BODY$
/*

Creado: Levi Laura Fecha:30/07/2016
Obtiene registros de notificacion dado el numero de bloque y id_usuario_solicitante
*/
 DECLARE 
    sm notificacion%ROWTYPE; 
BEGIN

for sm in select * from notificacion where bloque=pbloque and id_usuario_solicitante=pidusuario 
    order by nro_exped
loop
    return next sm;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;


/*
********************************************************************************************************
Creado: Eddy Valero Fecha:15/09/2016
descripcion: crud de la tabla observacion_tramite
*/
CREATE OR REPLACE FUNCTION crud_observacion_tramite(
    idobservaciontramite bigint,
    prefijo character varying,
    pidreferencia bigint,
    pidusuario bigint,
    pidlogtrans bigint,
    peditable boolean,
    pfecha_observacion timestamp without time zone,
    petapa_tramite character varying,
    pdescripcion character varying,
    poperacion integer)
  RETURNS bigint AS
$BODY$
/**
Creado: Eddy Valero Fecha:15/09/2016
descripcion: crud de la tabla observacion_tramite
 */
DECLARE
	seq_observacion_tramite bigint;
	
BEGIN
	IF poperacion = 1 THEN --INSERT
		IF prefijo = 'SIG' THEN
		
			INSERT INTO sigobservaciontramite(idsignomarca, idusuario, idlogtrans, editable, fecha_observacion, etapa_tramite, descripcion, estado)
			values(pidreferencia, pidusuario, pidlogtrans, peditable, pfecha_observacion , petapa_tramite, pdescripcion,  'AC');

			seq_observacion_tramite = (select currval('sec_sigobservaciontramite'));
			return seq_observacion_tramite;

		END IF;

		IF prefijo = 'MOD' THEN
		
			INSERT INTO modobservaciontramite(idmodificacion, idusuario, idlogtrans, editable, fecha_observacion, etapa_tramite, descripcion, estado)
			values(pidreferencia, pidusuario, pidlogtrans, peditable, pfecha_observacion , petapa_tramite, pdescripcion,  'AC');

			seq_observacion_tramite = (select currval('sec_modobservaciontramite'));

		END IF;

		IF prefijo = 'REN' THEN
		
			INSERT INTO renobservaciontramite(idsolicitudrenovacion, idusuario, idlogtrans, editable, fecha_observacion, etapa_tramite, descripcion, estado)
			values(pidreferencia, pidusuario, pidlogtrans, peditable, pfecha_observacion , petapa_tramite, pdescripcion,  'AC');

			seq_observacion_tramite = (select currval('sec_renobservaciontramite'));

		END IF;
	END IF; -- FIN DE LA OPERACION DE REGISTRAR

	IF OPERACION = 2 THEN --UPDATE

		
	END IF;-- FIN DE LA OPERACION DE UPDATE

	IF OPERACION = 3 THEN --DELETE

		
	END IF;-- FIN DE LA OPERACION DE DELETE
	
	

END;
$BODY$
  LANGUAGE plpgsql;

/*
********************************************************************************
Creado: Eddy Valero Fecha:16/09/2016
Obtener la fecha actual del sistema de acuerdo a la regional
*/
CREATE OR REPLACE FUNCTION obtenerfechasistema(pidregional bigint)
  RETURNS date AS
$BODY$
/*
Creado: Eddy Valero Fecha:16/09/2016
Obtener la fecha actual del sistema de acuerdo a la regional
*/
DECLARE

	vfecha date;
BEGIN

  select cast(fecha as date) into vfecha
  from fechasistema
  where idregional = pidregional;
 
  return vfecha;
END;
$BODY$
  LANGUAGE plpgsql;


/*
********************************************************************************************************
Creado: Eddy Valero Fecha:16/09/2016
descripcion: Obtener la fecha y hora actual del sistema de acuerdo a la regional
*/
CREATE OR REPLACE FUNCTION obtenerfechahorasistema(pidregional bigint)
  RETURNS timestamp AS
$BODY$
/*

Creado: Eddy Valero Fecha:16/09/2016
descripcion: Obtener la fecha y hora actual del sistema de acuerdo a la regional
*/
DECLARE

	vfecha timestamp;
BEGIN

  select (cast(fecha as date) || ' ' || to_char(current_timestamp , 'HH24:MI:SS'))::timestamp into vfecha
  from fechasistema
  where idregional = pidregional;
 
  return vfecha;
END;
$BODY$
  LANGUAGE plpgsql;

/***************************************************************/
/*
Creado: Levi Laura Fecha:31/08/2016
Lista objeto notificacion dado datos de oposiciones, y se busca en sus tablas de oposicion 
*/
CREATE OR REPLACE FUNCTION lista_notificaciondatosopo_nopo(
    pnro_opo bigint,
    porden_o bigint)
  RETURNS notificacion AS
$BODY$

/*
Creado: Levi Laura Fecha:31/08/2016
Lista objeto notificacion dado datos de oposiciones, y se busca en sus tablas de oposicion 
*/

 DECLARE 
    vidoposicion bigint;
    vidmarcademandada bigint;
    vidmarcademandante bigint;

    descripcion_dante character varying;
    descripcion_dada character varying;
    vsolicitante_dante character varying;
    vsolicitante_dada character varying;
    vapoderado_dante character varying;
    vapoderado_dada character varying;
    vcelnotifica_dante character varying;
    vcelnotifica_dada character varying;
    vdirnotifica_dante character varying;
    vdirnotifica_dada character varying;

    
    idsignomar bigint;
    --vsigsolicitanteapoderado sigsolicitanteapoderado%ROWTYPE;
    vlistsolici_dante oposolicitanteapoderado%ROWTYPE;
    vlistsolici_dada oposolicitanteapoderado%ROWTYPE;
    vlistapod_dante oposolicitanteapoderado%ROWTYPE;
    vlistapod_dada oposolicitanteapoderado%ROWTYPE;
    vlistapod oposolicitanteapoderado%ROWTYPE;
    vlistanoticel_dante oponotificacion%ROWTYPE;
    vlistanoticel_dada oponotificacion%ROWTYPE;
    vlistanotifi_dante oponotificacion%ROWTYPE;
    vlistanotifi_dada oponotificacion%ROWTYPE;

    
    vlistanoticel oponotificacion%ROWTYPE;
    vlistanotificacion  notificacion;
   


    vapoderado character varying;
    
  
  
BEGIN
   -- SACO PRMERAMENTE EL SIGNO SI EXISTE RECIEN SACO TODO LO DEMAS
      vsolicitante_dante:='';
      vsolicitante_dada:='';
      vapoderado:='';
      vcelnotifica_dante:='';
      vcelnotifica_dada:='';
      vdirnotifica_dante:='';
      vdirnotifica_dada:='';
      --vdirnotifica:='';
      vapoderado_dante:='';
      vapoderado_dada:='';
      select idoposicion into vidoposicion from oposicion where nro_opo=pnro_opo
      and orden_o=porden_o;
    if (vidoposicion is not null) then
      -- Primero agarrro las marcas de ambos
       select dmdo_marca_lnv into descripcion_dada from opomarcademandada where idoposicion=vidoposicion;
       select dmte_marca_lnv into descripcion_dante from opomarcademandante where idoposicion=vidoposicion;
       RAISE NOTICE '%',descripcion_dada;
       RAISE NOTICE '%',descripcion_dante;
       
      -- Ahora agarro el id ed cada uno para buscar en apoderados y notificaciones  
       select idmarcademandada into vidmarcademandada from opomarcademandada where idoposicion=vidoposicion;
       select idmarcademandante into vidmarcademandante from opomarcademandante where idoposicion=vidoposicion;


      
        -- SACO LOS SOLICITANTES DEMANDADO Y LSO VOY CONCATENANDO
       for vlistsolici_dada in
        
          select * from oposolicitanteapoderado where idmarcademandada=vidmarcademandada and tipo_persona='SOLI' order by idsolicitanteapoderado asc
       loop
               

                if (vlistsolici_dada.primer_apellido = '' or  vlistsolici_dada.primer_apellido is null)
                then
                    vsolicitante_dada:=vsolicitante_dada ||'; '|| vlistsolici_dada.nombre_razonsocial;
                else
                  -- vsolicitante_dada:=vsolicitante ||'; '|| vlistsolici_dada.nombre_razonsocial||' '||vlistsolici_dada.primer_apellido||' '||vlistsolici_dada.segundo_apellido;
                     vsolicitante_dada:=vsolicitante ||'; '|| vlistsolici_dada.nombre_razonsocial||' '||vlistsolici_dada.primer_apellido;
                end if;

       end loop;
       -- aqui si osi debo quitar el primer ';'
       vsolicitante_dada:=substring(vsolicitante_dada from 2);
       -- aquie si o si debo quitar el espacio vacio que dejo en la part e izquierda
       vsolicitante_dada:= trim(leading ' ' from vsolicitante_dada);
       RAISE NOTICE 'Demandada:%',vsolicitante_dada;
       
     -- SACO LOS SOLICITANTES DEMANDANTE Y LSO VOY CONCATENANDO
      for vlistsolici_dante in
          select * from oposolicitanteapoderado where idmarcademandante=vidmarcademandante and tipo_persona='SOLI' order by idsolicitanteapoderado asc
      loop
             if (vlistsolici_dante.primer_apellido = '' or  vlistsolici_dante.primer_apellido is null)
                then
                    vsolicitante_dante:=vsolicitante_dante ||'; '|| vlistsolici_dante.nombre_razonsocial;
                else
                   --vsolicitante_dante:=vsolicitante_dante ||'; '|| vlistsolici_dante.nombre_razonsocial||' '||vlistsolici_dante.primer_apellido||' '||vlistsolici_dante.segundo_apellido;
                     vsolicitante_dante:=vsolicitante_dante ||'; '|| vlistsolici_dante.nombre_razonsocial||' '||vlistsolici_dante.primer_apellido;
                end if;

       end loop;
    -- aqui si osi debo quitar el primer ';'
       vsolicitante_dante:=substring(vsolicitante_dante from 2);
       -- aquie si o si debo quitar el espacio vacio que dejo en la parte izquierda
       vsolicitante_dante:= trim(leading ' ' from vsolicitante_dante);
       RAISE NOTICE 'Demandante:%',vsolicitante_dante;


    -- SACO LOS APODERADOS DEL DEMANDANTE
       
      for vlistapod_dante in
          select * from oposolicitanteapoderado where idmarcademandante=vidmarcademandante and tipo_persona='APOD' order by idsolicitanteapoderado asc
      loop
          if (vlistapod_dante.primer_apellido = '' or  vlistapod_dante.primer_apellido is null)
                then
                    vapoderado_dante:=vapoderado_dante ||'; '|| vlistapod_dante.nombre_razonsocial;
                else
                   vapoderado_dante:=vapoderado_dante ||'; '|| vlistapod_dante.nombre_razonsocial||' '||vlistapod_dante.primer_apellido||' '||vlistapod_dante.segundo_apellido;
                end if;

       end loop;
        -- aqui si osi debo quitar el primer ';'
       vapoderado_dante:=substring(vapoderado_dante from 2);
       -- aquie si o si debo quitar el espacio vacio que dejo en la parte izquierda
       vapoderado_dante:= trim(leading ' ' from vapoderado_dante);
         RAISE NOTICE 'Apoderado Demandante:%',vapoderado_dante;
    -- SACO LOS APODERADOS DEL DEMANDADO
      for vlistapod_dada in
          select * from oposolicitanteapoderado where idmarcademandada=vidmarcademandada and tipo_persona='APOD' order by idsolicitanteapoderado asc
      loop
          if (vlistapod_dada.primer_apellido = '' or  vlistapod_dada.primer_apellido is null)
                then
                    vapoderado_dada:=vapoderado_dada ||'; '|| vlistapod_dada.nombre_razonsocial;
                else
                   vapoderado_dada:=vapoderado_dada ||'; '|| vlistapod_dada.nombre_razonsocial||' '||vlistapod_dada.primer_apellido||' '||vlistapod_dada.segundo_apellido;
                end if;

       end loop;
            -- aqui si osi debo quitar el primer ';'
       vapoderado_dada:=substring(vapoderado_dada from 2);
       -- aquie si o si debo quitar el espacio vacio que dejo en la parte izquierda
       vapoderado_dada:= trim(leading ' ' from vapoderado_dada);
       RAISE NOTICE 'Apoderado Demandado:%',vapoderado_dada;
       --SACO LOS CELULARES DE NOTIFICACION DEMANDANTE
      
      for vlistanoticel_dante in
          select * from oponotificacion where idmarcademandante=vidmarcademandante
       loop
            if (vlistanoticel_dante.telefono !='' and  vlistanoticel_dante.telefono is not null)
             then
               vcelnotifica_dante:=vlistanoticel_dante.telefono;
             
            end if; 
            if(vcelnotifica_dante = '')
            then
                vcelnotifica_dante:=vlistanoticel_dante.celular;  
            else
               if (vlistanoticel_dante.celular !='' and  vlistanoticel_dante.celular is not null)
               then
                 vcelnotifica_dante:=vcelnotifica_dante||' ;'||vlistanoticel_dante.celular;
               end if;     
            end if;
            
       end loop;
       vcelnotifica_dante:= trim(leading ' ' from vcelnotifica_dante);
           RAISE NOTICE 'Cel del demandante%',vcelnotifica_dante;

            --SACO LOS CELULARES DE NOTIFICACION DEMANDANTE
      
      for vlistanoticel_dada in
          select * from oponotificacion where idmarcademandada=vidmarcademandada
            loop 
            if (vlistanoticel_dada.telefono !='' and  vlistanoticel_dada.telefono is not null)
             then
               vcelnotifica_dada:=vlistanoticel_dada.telefono;
             
            end if; 
            if(vcelnotifica_dada = '')
            then
                vcelnotifica_dada:=vlistanoticel_dada.celular;  
            else
               if (vlistanoticel_dada.celular !='' and  vlistanoticel_dada.celular is not null)
               then
                 vcelnotifica_dada:=vcelnotifica_dada||' ;'||vlistanoticel_dada.celular;
               end if;     
            end if;
            
       end loop;
       vcelnotifica_dada:= trim(leading ' ' from vcelnotifica_dada);
           RAISE NOTICE 'Cel del demandada%',vcelnotifica_dada;
       -- SACO LA DIRECCION DE NOTIFICAICON DE DEMANDANTE
        for vlistanotifi_dante in
          select * from oponotificacion where idmarcademandante=vidmarcademandante
       loop
                  vdirnotifica_dante:=vlistanotifi_dante.ciudad_notificacion;
                   if (vdirnotifica_dante is not null)
                  then
                       if (vlistanotifi_dante.zona_barrio != '' and vlistanotifi_dante.zona_barrio is not null)
                       then
                          vdirnotifica_dante:=vdirnotifica_dante||' ,'||vlistanotifi_dante.zona_barrio;
                       end if;
                   else
                      if (vlistanotifi_dante.zona_barrio != '' and vlistanotifi_dante.zona_barrio is not null)
                       then
                            vdirnotifica_dante:=vlistanotifi_dante.zona_barrio;
                      end if;
                  end if;       

                 
                  if (vlistanotifi_dante.avenida_calle != '' and vlistanotifi_dante.avenida_calle is not null)
                  then
                     vdirnotifica_dante:=vdirnotifica_dante||' ,'||vlistanotifi_dante.avenida_calle;
                  end if;


                  if (vlistanotifi_dante.numero != '' and vlistanotifi_dante.numero is not null)
                  then
                        vdirnotifica_dante:=vdirnotifica_dante||' ,'||vlistanotifi_dante.numero;
                  end if;

                   if (vlistanotifi_dante.edificio != '' and vlistanotifi_dante.edificio is not null)
                  then
                        vdirnotifica_dante:=vdirnotifica_dante||' ,'||vlistanotifi_dante.edificio;
                  end if;

                    if (vlistanotifi_dante.piso != '' and vlistanotifi_dante.piso is not null)
                  then
                        vdirnotifica_dante:=vdirnotifica_dante||' ,'||vlistanotifi_dante.piso;
                  end if;

                    if (vlistanotifi_dante.numero_departamento != '' and vlistanotifi_dante.numero_departamento is not null)
                  then
                        vdirnotifica_dante:=vdirnotifica_dante||' ,'||vlistanotifi_dante.numero_departamento;
                  end if;

                  if (vlistanotifi_dante.referencia_direccion != '' and vlistanotifi_dante.referencia_direccion is not null)
                  then
                        vdirnotifica_dante:=vdirnotifica_dante||' ,'||vlistanotifi_dante.referencia_direccion;
                  end if;
                 
       end loop;
-- aquie si o si debo quitar el espacio vacio que dejo en la parte izquierda
           vdirnotifica_dante:= trim(trailing ' ' from vdirnotifica_dante);
          RAISE NOTICE 'direccion demandante:%',vdirnotifica_dante;
  -- SACO LA DIRECCION DE NOTIFICAICON DE DEMANDADO
        for vlistanotifi_dada in
          select * from oponotificacion where idmarcademandada=vidmarcademandada
       loop
                    vdirnotifica_dada:=vlistanotifi_dada.ciudad_notificacion;
                  if (vdirnotifica_dada is not null)
                  then
                       if (vlistanotifi_dada.zona_barrio != '' and vlistanotifi_dada.zona_barrio is not null)
                       then
                            vdirnotifica_dada:=vdirnotifica_dada||' ,'||vlistanotifi_dada.zona_barrio;
                        end if;
                   else
                      if (vlistanotifi_dada.zona_barrio != '' and vlistanotifi_dada.zona_barrio is not null)
                       then
                            vdirnotifica_dada:=vlistanotifi_dada.zona_barrio;
                      end if;
                  end if;  

                 
                  if (vlistanotifi_dada.avenida_calle != '' and vlistanotifi_dada.avenida_calle is not null)
                  then
                     vdirnotifica_dada:=vdirnotifica_dada||' ,'||vlistanotifi_dada.avenida_calle;
                  end if;


                  if (vlistanotifi_dada.numero != '' and vlistanotifi_dada.numero is not null)
                  then
                        vdirnotifica_dada:=vdirnotifica_dada||' ,'||vlistanotifi_dada.numero;
                  end if;

                   if (vlistanotifi_dada.edificio != '' and vlistanotifi_dada.edificio is not null)
                  then
                        vdirnotifica_dada:=vdirnotifica_dada||' ,'||vlistanotifi_dada.edificio;
                  end if;

                    if (vlistanotifi_dada.piso != '' and vlistanotifi_dada.piso is not null)
                  then
                        vdirnotifica_dada:=vdirnotifica_dada||' ,'||vlistanotifi_dada.piso;
                  end if;

                    if (vlistanotifi_dada.numero_departamento != '' and vlistanotifi_dada.numero_departamento is not null)
                  then
                        vdirnotifica_dada:=vdirnotifica_dada||' ,'||vlistanotifi_dada.numero_departamento;
                  end if;

                  if (vlistanotifi_dada.referencia_direccion != '' and vlistanotifi_dada.referencia_direccion is not null)
                  then
                        vdirnotifica_dada:=vdirnotifica_dada||' ,'||vlistanotifi_dada.referencia_direccion;
                  end if;
                 
       end loop;
-- aquie si o si debo quitar el espacio vacio que dejo en la parte izquierda
           vdirnotifica_dada:= trim(trailing ' ' from vdirnotifica_dada);
          RAISE NOTICE 'direccion demandada:%',vdirnotifica_dada;
    



     
   end if;

  vlistanotificacion.demandante:=descripcion_dante;
  vlistanotificacion.demandado:=descripcion_dada;
   vlistanotificacion.demandante_solic:=vsolicitante_dante;
   vlistanotificacion.demandado_solic:=vsolicitante_dada;
  vlistanotificacion.demandante_apod:=vapoderado_dante;
  vlistanotificacion.demandado_apod:=vapoderado_dada;
  vlistanotificacion.demandante_cel:=vcelnotifica_dante;
  vlistanotificacion.demandado_cel:=vcelnotifica_dada;
  vlistanotificacion.demandante_direc:=vdirnotifica_dante;
  vlistanotificacion.demandado_direc:=vdirnotifica_dada;

/*   

   vlistanotificacion.demandante_direc:=vdirnotifica;
*/
   RETURN vlistanotificacion;
END;
$BODY$
  LANGUAGE plpgsql;
/*
********************************************************************************
Creado: Eddy Valero Fecha:19/09/2016
metodo para obtener la plantilla de acuerdo a un numero de formulario
y un tipo de formulario
Modificado : Susana Escobar Paz Fecha:10/10/2017
Listado de elementos de las plantillas de modulo de Signos, Modificaciones y Renovaciones
*/

CREATE OR REPLACE FUNCTION obtplantillaventanillatramiteingresado(
						pnumeroformulario character varying,
						ptipoformulario character varying
						)
  RETURNS SETOF moddatoelementoformulario AS
$BODY$
/*
********************************************************************************
Creado: Eddy Valero Fecha:19/09/2016
metodo para obtener la plantilla de acuerdo a un numero de formulario
y un tipo de formulario
Modificado : Susana Escobar Paz Fecha:10/10/2017
Listado de elementos de las plantillas de modulo de Signos, Modificaciones y Renovaciones
*/
 DECLARE 
    reg moddatoelementoformulario%ROWTYPE; 
BEGIN

	if ptipoformulario = 'PI100'
		or ptipoformulario = 'PI101'
		or ptipoformulario = 'PI102' then
	
		for reg in
			(
			select *
			from sigdatoelementoformulario def
			where def.nombre_tabla = 'sigseguimiento'
			and def.idseguimiento in (
				select max(ss.idseguimiento)
				from sigsignomarca sm, sigseguimiento ss
				where
					ss.idsignomarca = sm.idsignomarca
					and sm.numero_formulario = pnumeroformulario
					and ss.etapa = 'VENT'
					and ss.estado = 'AC'
				)
			and estado = 'AC'
			order by orden asc
			)
		loop
		    return next reg;
		end loop;
	end if;
	
	if ptipoformulario = 'PI103' then
		for reg in
			(
			select *
			from moddatoelementoformulario def
			where def.nombre_tabla = 'modseguimiento'
			and def.idseguimiento in (
				select max(md.idseguimiento)
				from modmodificacion mod, modseguimiento md
				where
					md.idmodificacion = mod.idmodificacion
					and mod.nro_formulario = pnumeroformulario
					and md.etapa = 'VENT'
					and md.estado = 'AC'
				)
			and estado = 'AC'
			order by orden asc
			)
		loop
		    return next reg;
		end loop;
	end if;

	if ptipoformulario = 'PI104' then
		for reg in
			(
			select *
			from rendatoelementoformulario def
			where def.nombre_tabla = 'renseguimiento'
			and def.idseguimiento in (
				select max(rs.idseguimiento)
				from rensolicitudrenovacion ren, renseguimiento rs
				where
					rs.idsolicitudrenovacion = ren.idsolicitudrenovacion
					and ren.nro_formulario = pnumeroformulario::bigint
					and rs.etapa = 'VENT'
					and rs.estado = 'AC'
				)
			and estado = 'AC'
			order by orden asc
			)
		loop
		    return next reg;
		end loop;
	end if;
end;
$BODY$
  LANGUAGE plpgsql;

/*
********************************************************************************
Creado: Susana Escobar Fecha:19/09/2016
Realizar registro en tabla modresolucion
*/
CREATE OR REPLACE FUNCTION crud_modresolucion(
  pidresolucion bigint,
  pidmodificacion bigint,
  pnumero_resolucion integer,
  pgestion_resolucion integer,
  pfecha_resolucion timestamp without time zone,
  pobservacion_resolucion character varying,
  pdocumento_resolucion text,
  pestado character varying,
  operacion integer)
  RETURNS SETOF modresolucion AS
$BODY$
/*
********************************************************************************
Creado: Susana Escobar Fecha:19/09/2016
Realizar registro en tabla modresolucion
*/
DECLARE
seq_modresolucion bigint;
vresolucion modresolucion%ROWTYPE;
BEGIN

if operacion=1 then -- Create
insert into modresolucion(  
  idmodificacion,
  numero_resolucion,
  gestion_resolucion,
  fecha_resolucion,
  observacion_resolucion,
  documento_resolucion,
  estado
)
values(  
  pidmodificacion,
  pnumero_resolucion,
  pgestion_resolucion,
  pfecha_resolucion,
  pobservacion_resolucion,
  pdocumento_resolucion,
  pestado
);
seq_modresolucion = (select currval('sec_modresolucion')) ;
for vresolucion in (select *
 from modresolucion
 where idresolucion = seq_modresolucion
 ) loop
return next vresolucion;
end loop;
end if;----fin create


if operacion=2 then -- update
update modresolucion set
	  idresolucion =  pidresolucion,
	  idmodificacion =  pidmodificacion,
	  numero_resolucion =  pnumero_resolucion,
	  gestion_resolucion =  pgestion_resolucion,
	  fecha_resolucion =  pfecha_resolucion,
	  observacion_resolucion =  pobservacion_resolucion,
	  documento_resolucion =  pdocumento_resolucion,
	  estado = pestado
    where   idresolucion = pidresolucion;
  
for vresolucion in (select *
 from modresolucion
 where idresolucion = pidresolucion
 ) loop
return next vresolucion;
end loop;
end if;----fin update

if operacion =3 then-- delete
     delete from modresolucion 
      where idresolucion = pidresolucion;
end if; ---fin delete


if operacion=4 then --read
for vresolucion in (select *
 from modresolucion
 where idresolucion = pidresolucion
 ) loop
return next vresolucion;
end loop;
 
end if; ---fin read
       
END;
$BODY$
  LANGUAGE plpgsql; 

/*
********************************************************************************
Creado: Susana Escobar Fecha: 20/09/2016
Listar modresolucion por un idmodificacion en solicitud de modificacion
*/
CREATE OR REPLACE FUNCTION lista_modresolucion_idmodificacion(pidmodificacion bigint)
 RETURNS SETOF modresolucion AS
$BODY$
/*
********************************************************************************
Creado: Susana Escobar Fecha: 20/09/2016
Listar modresolucion por un idmodificacion en solicitud de modificacion
*/
DECLARE 
   vresolucion modresolucion%ROWTYPE; 
BEGIN

for vresolucion in select * from modresolucion 
   where  idmodificacion = pidmodificacion and estado='AC' loop
   return next vresolucion;
end loop;
end;
$BODY$
 LANGUAGE plpgsql;

/*
Creado: Chano Rojas Fecha:22/09/2016
Realizar obtiene correlativo por regional y tipotramite
*/
CREATE OR REPLACE FUNCTION obtiene_correlativo_regional_tipotramite(
    vidregional bigint,
    vtipo_tramite character varying)
  RETURNS SETOF correlativo AS
$BODY$
/*
Creado: Chano Rojas Fecha:22/09/2016
Realizar obtiene correlativo por regional y tipotramite
*/
DECLARE 
    co correlativo%ROWTYPE;
BEGIN

for co in 
  SELECT * FROM correlativo where correlativo.idcorrelativo in 
(select correlativoregional.idcorrelativo from correlativoregional 
where correlativoregional.idregional=vidregional 
and correlativoregional.tipo_tramite=vtipo_tramite and estado='AC')
loop
    return next co;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;

/*
********************************************************************************
Creado: Susana Escobar Fecha: 16/09/2016
Listar tipo signo por un idmodificacion en solicitud de modificacion
*/
CREATE OR REPLACE FUNCTION lista_modtiposigno_idmodificacion(pidmodificacion bigint)
  RETURNS SETOF modtiposigno AS
$BODY$
/*
********************************************************************************
Creado: Susana Escobar Fecha: 16/09/2016
Listar tipo signo por un idmodificacion en solicitud de modificacion
*/
 DECLARE 
    vtiposigno modtiposigno%ROWTYPE; 
BEGIN

for vtiposigno in select * from modtiposigno 
    where  idmodificacion = pidmodificacion and estado='AC' loop
    return next vtiposigno;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;

/*
********************************************************************************************************
Creado: Eddy Valero Fecha: 27/09/2016
descripcion: obtener la ultima observación de la tabla observacion_tramite
de cualquiera de los prefijos.
*/
CREATE OR REPLACE FUNCTION obtener_observacion_tramite(
    prefijo character varying,
    pidreferencia bigint)
  RETURNS sigobservaciontramite AS
$BODY$
/**
Creado: Eddy Valero Fecha:15/09/2016
descripcion: crud de la tabla observacion_tramite
 */
DECLARE
	vidobservaciontramite bigint;
	vobservaciontramite sigobservaciontramite%ROWTYPE;
	
BEGIN
	--Signos
	IF prefijo = 'SIG' THEN
		select max(idobservaciontramite) into vidobservaciontramite
		from sigobservaciontramite
		where idsignomarca = pidreferencia
		and estado = 'AC';

		SELECT * into vobservaciontramite
		from sigobservaciontramite
		where idobservaciontramite = vidobservaciontramite;

		return vobservaciontramite;
	END IF;

	--Modificación
	IF prefijo = 'MOD' THEN
		select max(idobservaciontramite) into vidobservaciontramite
		from modobservaciontramite
		where idmodificacion = pidreferencia
		and estado = 'AC';

		
	
		SELECT mod.* into vobservaciontramite
		from modobservaciontramite mod
		where idobservaciontramite = vidobservaciontramite;

		return vobservaciontramite;
	END IF;

	--Renovación
	IF prefijo = 'REN' THEN
		select max(idobservaciontramite) into vidobservaciontramite
		from renobservaciontramite
		where idsolicitudrenovacion = pidreferencia
		and estado = 'AC';
		
		SELECT ren.* into vobservaciontramite
		from renobservaciontramite ren
		where idobservaciontramite = vidobservaciontramite;

		return vobservaciontramite;
	END IF;

END;
$BODY$
  LANGUAGE plpgsql;
/*********************************************************************/
/*
Creado: Levi Laura Fecha:31/08/2016
Modifica los campos de notificacion: historial, fecha de recepcion dado el id de notificación,
además de que el campo historial lo va armando se gun el idnotificacion ya guarddado, y el campo de fecha lo parsea
al formato adecuado

*/
CREATE OR REPLACE FUNCTION modifica_notificacion_datosbuzon(
    pidnotificacion bigint,
    pid_usuario_notificador bigint
     --pfecha_recep timestamp without time zone
    )
    
  RETURNS void AS
$BODY$

/*

Creado: Levi Laura Fecha:31/08/2016
Modifica los campos de notificacion: historial, fecha de recepcion dado el id de notificación,
además de que el campo historial lo va armando se gun el idnotificacion ya guarddado, y el campo de fecha lo parsea
al formato adecuado

*/
DECLARE

vhistorial character varying;
vnombre character varying;
vprimer_apellido character varying;
vsegundo_apellido  character varying;
vnombre_completo character varying;

BEGIN       
           
            select nombre,primer_apellido,segundo_apellido into vnombre,vprimer_apellido,vsegundo_apellido from usuario where idusuario=pid_usuario_notificador;
            if (vprimer_apellido is null)
            then 
           --    RAISE NOTICE 'es nulo ';
               vprimer_apellido:='';

            end if;
            
            if (vsegundo_apellido is null)
            then 
--               RAISE NOTICE 'es nulo ';
               vsegundo_apellido:='';

            end if;
            vnombre_completo:=vnombre||' '||vprimer_apellido||' '||vsegundo_apellido;
            RAISE NOTICE 'nombre es %',vnombre_completo;
            
            select historial into vhistorial from notificacion where idnotificacion=pidnotificacion;
            if (vhistorial is null)
            then 
                 vhistorial:='RECIBIDO:'||vnombre_completo||','||TO_CHAR(NOW(), 'DD-MM-YYYY  HH24:MI:SS'); 
            else
                 vhistorial:=vhistorial || ';RECIBIDO:'||vnombre_completo||','||TO_CHAR(NOW(), 'DD-MM-YYYY  HH24:MI:SS'); 
            end if;
            
            RAISE NOTICE 'historial es %',vhistorial;

            
            update notificacion set id_usuario_notificador=pid_usuario_notificador,
                                    fecha_recep=now(),
                                    historial=vhistorial,
                                    demandante_cod_estado='REC',
                                    demandado_cod_estado='REC'
                                    where idnotificacion=pidnotificacion;
                                       


END;
$BODY$
  LANGUAGE plpgsql;

/*******************************************************/
/*
Creado: Levi Laura Fecha:27/09/2016
Devuelve el registro de usuarios
*/
CREATE OR REPLACE FUNCTION lista_usuario()
  RETURNS SETOF usuario AS
$BODY$
/*

Creado: Levi Laura Fecha:27/09/2016
Devuelve el registro de usuarios
*/
 DECLARE 
    vusuario usuario%ROWTYPE; 
BEGIN

for  vusuario  in 
   select * from usuario where estado='AC' order by nombre asc
loop
    return next vusuario;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;
  
/*
 * autor: Eddy Valero
 * fecha: 28/09/2016
 * descripcion: obtener todos los de tablas sigobservaciontramite, modobservaciontramite, renobservaciontramite.
 */
CREATE OR REPLACE FUNCTION lista_observaciones_tramite(
    prefijo character varying,
    pidreferencia bigint)
  RETURNS SETOF sigobservaciontramite AS
$BODY$
/*
 * autor: Eddy Valero
 * fecha: 28/09/2016
 * descripcion: obtener todos los de tablas sigobservaciontramite, modobservaciontramite, renobservaciontramite.
 */
 DECLARE 
    reg sigobservaciontramite%ROWTYPE; 
BEGIN

    IF prefijo = 'SIG' THEN
        for reg in
            (
            select * from sigobservaciontramite
            where estado = 'AC'
            and idsignomarca = pidreferencia
            order by fecha_observacion asc
            )
        loop
            return next reg;
        end loop;
    END IF;
    IF prefijo = 'REN' THEN
        for reg in
            (
            select * from renobservaciontramite
            where estado = 'AC'
            and idsolicitudrenovacion = pidreferencia
            order by fecha_observacion asc
            )
        loop
            return next reg;
        end loop;
    END IF;
    IF prefijo = 'MOD' THEN
        for reg in
            (
            select * from modobservaciontramite
            where estado = 'AC'
            and idmodificacion = pidreferencia
            order by fecha_observacion asc
            )
        loop
            return next reg;
        end loop;
    END IF;    
end;
$BODY$
  LANGUAGE plpgsql; 

/*******************************************************/
/*
Creado: Levi Laura Fecha:27/09/2016
Devuelve el registro de un usuariodado el idusuario
*/
CREATE OR REPLACE FUNCTION lista_usuario_idusuario(pidusuario bigint)
  RETURNS SETOF usuario AS
$BODY$
/*

Creado: Levi Laura Fecha:27/09/2016
Devuelve el registro de un usuariodado el idusuario
*/
 DECLARE 
    vusuario usuario%ROWTYPE; 
BEGIN

for  vusuario  in 
   select * from usuario where idusuario=pidusuario
loop
    return next vusuario;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;
/*
********************************************************************************
Creado: Levi Laura Fecha:01/10/2016
Lista notificaciones distintas del estado ENV, esta funcion se usa para el modulo de notificaciones en si
*/
CREATE OR REPLACE FUNCTION lista_notificacion_idusuariobloque(pidusuario bigint,pbloque integer)
  RETURNS SETOF notificacion AS
$BODY$

/*

Creado: Levi Laura Fecha:01/10/2016
Lista notificaciones distintas del estado ENV, esta funcion se usa para el modulo de notificaciones en si
*/

 DECLARE 
    vnotificacion notificacion%ROWTYPE; 
BEGIN

for vnotificacion in 
   select * from notificacion where id_usuario_solicitante=pidusuario and bloque =pbloque and demandante_cod_estado <> 'PREE'
   and demandante_cod_estado <> 'ENV' order by nro_exped asc
loop
    return next vnotificacion;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;


/****************************************************/
/*Creado: Levi Laura Fecha:31/08/2016
Modifica un registro de notificacion
*/
CREATE OR REPLACE FUNCTION modifica_notificacion(
    pidnotificacion bigint,
    pidlogtrans bigint,
    pbloque integer,
    pnro_exped integer,
    pid_usuario_solicitante bigint,
    ptipo_tramite_notificacion character varying,
    pexpediente character varying,
    pgestion integer,
    pextension character varying,
    pdemandante character varying,
    pdemandante_cod_estado character varying,
    pdemandante_fecha_devol timestamp without time zone,
    pdemandante_fecha_noti timestamp without time zone,
    pdemandante_lugar_notificacion character varying,
    pdemandante_solic character varying,
    pdemandante_apod character varying,
    pdemandante_fojas character varying,
    pdemandante_con character varying,
    pdemandante_direc character varying,
    pdemandante_cel character varying,
    pdemandante_ciudad_notifi character varying,
    pdemandado character varying,
    pdemandado_cod_estado character varying,
    pdemandado_fecha_devol timestamp without time zone,
    pdemandado_fecha_noti timestamp without time zone,
    pdemandado_lugar_notificacion character varying,
    pdemandado_solic character varying,
    pdemandado_apod character varying,
    pdemandado_fojas character varying,
    pdemandado_con character varying,
    pdemandado_direc character varying,
    pdemandado_cel character varying,
    pdemandado_ciudad_notifi character varying,    
    pfecha_ingreso timestamp without time zone,
    ptipo integer,
    pobs text,
    phistorial text,
    pfecha_recep timestamp without time zone,
    pobs_notifi text,
    pid_usuario_notificador bigint,
    pestado_marca character varying,
    pform_noti_pre text,
    pform_noti_cuerpo text)
  RETURNS SETOF notificacion AS
$BODY$
/*

Creado: Levi Laura Fecha:31/08/2016
Modifica un registro de notificacion
*/
DECLARE
seq_notificacion bigint;
obj_notificacion notificacion%ROWTYPE;
BEGIN

update notificacion
set idlogtrans=pidlogtrans,
    bloque=pbloque,
    nro_exped=pnro_exped,
    id_usuario_solicitante=pid_usuario_solicitante,
    tipo_tramite_notificacion=ptipo_tramite_notificacion,
    expediente=pexpediente,
    gestion=pgestion,
    extension=pextension,
    demandante=pdemandante,
    demandante_cod_estado=pdemandante_cod_estado,
    demandante_fecha_devol=pdemandante_fecha_devol,
    demandante_fecha_noti=pdemandante_fecha_noti,
    demandante_lugar_notificacion=pdemandante_lugar_notificacion,
    demandante_solic=pdemandante_solic,
    demandante_apod=pdemandante_apod,
    demandante_fojas=pdemandante_fojas,
    demandante_con=pdemandante_con,
    demandante_direc=pdemandante_direc,
    demandante_cel=pdemandante_cel,
    demandante_ciudad_notifi=pdemandante_ciudad_notifi,
    demandado=pdemandado,
    demandado_cod_estado=pdemandado_cod_estado,
    demandado_fecha_devol=pdemandado_fecha_devol,
    demandado_fecha_noti=pdemandado_fecha_noti,
    demandado_lugar_notificacion=pdemandado_lugar_notificacion,
    demandado_solic=pdemandado_solic,
    demandado_apod=pdemandado_apod,
    demandado_fojas=pdemandado_fojas,
    demandado_con=pdemandado_con,
    demandado_direc=pdemandado_direc,
    demandado_cel=pdemandado_cel,
    demandado_ciudad_notifi=pdemandado_ciudad_notifi,
    fecha_ingreso=pfecha_ingreso,
    tipo=ptipo,
    obs=pobs,
    --historial=phistorial,
    fecha_recep=pfecha_recep,
    obs_notifi=pobs_notifi,
    --id_usuario_notificador=pid_usuario_notificador ,
    estado_marca=pestado_marca,
    form_noti_pre=pform_noti_pre,
    form_noti_cuerpo=pform_noti_cuerpo
where idnotificacion=pidnotificacion;


for obj_notificacion in (select *
 from notificacion
 where idnotificacion = pidnotificacion
 ) loop
return next obj_notificacion;
end loop;

       
END;
$BODY$
  LANGUAGE plpgsql;



/*
 * autor: Susana Escobar Paz
 * fecha: 29/09/2016
 * descripcion: funcion crud, para crear, modificar, borrar y listar registros de tablas sigobservaciontramite,  
 * modobservaciontramite, renobservaciontramite   
 */
CREATE OR REPLACE FUNCTION crud_observaciontramite(
    pidobservaciontramite bigint,
    pidmodificacion bigint,
    pidusuario bigint,
    pidlogtrans bigint,
    peditable boolean,
    pfecha_observacion timestamp without time zone,
    petapa_tramite character varying,
    pdescripcion character varying,
    pestado character varying,
    prefijo character varying,
    operacion integer)
  RETURNS SETOF sigobservaciontramite AS
$BODY$
/*
 * autor: Susana Escobar Paz
 * fecha: 29/09/2016
 * descripcion: funcion crud, para crear, modificar, borrar y listar registros de tablas sigobservaciontramite,  
 * modobservaciontramite, renobservaciontramite   
 */
DECLARE
seq_sigobservaciontramite bigint;
seq_modobservaciontramite bigint;
seq_renobservaciontramite bigint;
vmodobservaciontramite modobservaciontramite%ROWTYPE;
vsigobservaciontramite sigobservaciontramite%ROWTYPE;
vrenobservaciontramite renobservaciontramite%ROWTYPE;
BEGIN

if operacion=1 then -- Create
IF prefijo = 'MOD' THEN
insert into modobservaciontramite(
  idmodificacion,
  idusuario,
  idlogtrans,
  editable,
  fecha_observacion,
  etapa_tramite,
  descripcion,
  estado
)
values( 
  pidmodificacion,
  pidusuario,
  pidlogtrans,
  peditable,
  pfecha_observacion,
  petapa_tramite,
  pdescripcion,
  pestado
);
seq_modobservaciontramite = (select currval('sec_modobservaciontramite')) ;
for vmodobservaciontramite in (select *
 from modobservaciontramite
 where idobservaciontramite = seq_modobservaciontramite
 ) loop
return next vmodobservaciontramite;
end loop;
END IF;  
-------renovaciones
IF prefijo = 'REN' THEN
insert into renobservaciontramite(
  idsolicitudrenovacion,
  idusuario,
  idlogtrans,
  editable,
  fecha_observacion,
  etapa_tramite,
  descripcion,
  estado
)
values( 
  pidmodificacion,
  pidusuario,
  pidlogtrans,
  peditable,
  pfecha_observacion,
  petapa_tramite,
  pdescripcion,
  pestado
);
seq_renobservaciontramite = (select currval('sec_renobservaciontramite')) ;
for vrenobservaciontramite in (select *
 from renobservaciontramite
 where idobservaciontramite = seq_renobservaciontramite
 ) loop
return next vrenobservaciontramite;
end loop;
END IF;  
-----------signos
IF prefijo = 'SIG' THEN
insert into sigobservaciontramite(
  idsignomarca,
  idusuario,
  idlogtrans,
  editable,
  fecha_observacion,
  etapa_tramite,
  descripcion,
  estado
)
values( 
  pidmodificacion,
  pidusuario,
  pidlogtrans,
  peditable,
  pfecha_observacion,
  petapa_tramite,
  pdescripcion,
  pestado
);
seq_sigobservaciontramite = (select currval('sec_sigobservaciontramite')) ;
for vsigobservaciontramite in (select *
 from sigobservaciontramite
 where idobservaciontramite = seq_sigobservaciontramite 
 ) loop
return next vsigobservaciontramite;
end loop;
END IF;  
end if;----fin create
----------------------------------------------------------------------------
if operacion=2 then -- update
-----modificaciones
IF prefijo = 'MOD' THEN
update modobservaciontramite set
	  idobservaciontramite =  pidobservaciontramite,
	  idmodificacion =  pidmodificacion,
	  idusuario =  pidusuario,
	  idlogtrans =  pidlogtrans,
	  editable =  peditable,
	  fecha_observacion =  pfecha_observacion,
	  etapa_tramite =  petapa_tramite,
	  descripcion =  pdescripcion,
	  estado =  pestado		
	where 	idobservaciontramite = pidobservaciontramite;	
for vmodobservaciontramite in (select *
 from modobservaciontramite
 where idobservaciontramite = pidobservaciontramite
 ) loop
return next vmodobservaciontramite;
end loop;
END IF;  
---------renovaciones
IF prefijo = 'REN' THEN
update renobservaciontramite set
	  idobservaciontramite =  pidobservaciontramite,
	  idsolicitudrenovacion =  pidmodificacion,
	  idusuario =  pidusuario,
	  idlogtrans =  pidlogtrans,
	  editable =  peditable,
	  fecha_observacion =  pfecha_observacion,
	  etapa_tramite =  petapa_tramite,
	  descripcion =  pdescripcion,
	  estado =  pestado		
	where 	idobservaciontramite = pidobservaciontramite;	
for vrenobservaciontramite in (select *
 from renobservaciontramite
 where idobservaciontramite = pidobservaciontramite
 ) loop
return next vrenobservaciontramite;
end loop;
END IF;  
---------signos
IF prefijo = 'SIG' THEN
update sigobservaciontramite set
	  idobservaciontramite =  pidobservaciontramite,
	  idsignomarca =  pidmodificacion,
	  idusuario =  pidusuario,
	  idlogtrans =  pidlogtrans,
	  editable =  peditable,
	  fecha_observacion =  pfecha_observacion,
	  etapa_tramite =  petapa_tramite,
	  descripcion =  pdescripcion,
	  estado =  pestado		
	where 	idobservaciontramite = pidobservaciontramite;	
for vsigobservaciontramite in (select *
 from sigobservaciontramite
 where idobservaciontramite = pidobservaciontramite
 ) loop
return next vsigobservaciontramite;
end loop;
END IF;  
end if;----fin update
------------------------------------------------------------------
if operacion =3 then-- delete
     IF prefijo = 'MOD' THEN
     delete from modobservaciontramite where idobservaciontramite = pidobservaciontramite;
     END IF;  
     IF prefijo = 'REN' THEN
     delete from renobservaciontramite where idobservaciontramite = pidobservaciontramite;
     END IF;  
     IF prefijo = 'SIG' THEN
     delete from sigobservaciontramite where idobservaciontramite = pidobservaciontramite;
     END IF;  
end if; ---fin delete
---------------------------------------------------------------------
if operacion=4 then --read
 IF prefijo = 'MOD' THEN
	 for vmodobservaciontramite in (select *
	 from modobservaciontramite
	 where idobservaciontramite = pidobservaciontramite
	 ) loop
	 return next vmodobservaciontramite;
	end loop;
 END IF; 
 IF prefijo = 'REN' THEN
	 for vrenobservaciontramite in (select *
	 from renobservaciontramite
	 where idobservaciontramite = pidobservaciontramite
	 ) loop
	 return next vrenobservaciontramite;
	end loop;
 END IF; 
 IF prefijo = 'SIG' THEN
	 for vsigobservaciontramite in (select *
	 from sigobservaciontramite
	 where idobservaciontramite = pidobservaciontramite
	 ) loop
	 return next vsigobservaciontramite;
	end loop;
 END IF;  

end if; ---fin read

END;
$BODY$
  LANGUAGE plpgsql;


/*
********************************************************************************
Creado: Susana Escobar Fecha:20/09/2016
Realizar registro en tabla sigsolicitanteapoderado
*/
CREATE OR REPLACE FUNCTION crud_sigsolicitanteapoderado(
	pidsolicitanteapoderado bigint,
	pidsignomarca bigint,
	pidlogtrans bigint,
	ptipo_titular character varying,
	ptipo_persona character varying,
	pnombre_razonsocial character varying,
	pprimer_apellido character varying,
	psegundo_apellido character varying,
	pnumero_documento character varying,
	ptipo_documento character varying,
	plugar_expedicion character varying,
	ppais character varying,
	pgenero character varying,
	psolicitud_departamento character varying,
	ppoder character varying,
	pdireccion character varying,
	ptelefono character varying,
	pcelular character varying,
	pemail character varying,
	pfax character varying,
	pestado character varying,
    pid_sipi bigint,
	operacion integer)
    RETURNS SETOF sigsolicitanteapoderado 
    LANGUAGE 'plpgsql'

    COST 100
    VOLATILE 
    ROWS 1000
AS $BODY$

/*
********************************************************************************
Creado: Susana Escobar Fecha:20/09/2016
Modificado: Placido Castro Fecha:08/12/2017
Realizar registro en tabla sigsolicitanteapoderado
*/
DECLARE
seq_sigsolicitanteapoderado bigint;
vsolicitanteapoderado sigsolicitanteapoderado%ROWTYPE;
BEGIN

if operacion=1 then -- Create
insert into sigsolicitanteapoderado(
  idsignomarca,
  idlogtrans,
  tipo_titular,
  tipo_persona,
  nombre_razonsocial,
  primer_apellido,
  segundo_apellido,
  numero_documento,
  tipo_documento,
  lugar_expedicion,
  pais,
  genero,
  solicitud_departamento,
  poder,
  direccion,
  telefono,
  celular,
  email,
  fax,
  estado,
  id_sipi
)
values(  
  pidsignomarca,
  pidlogtrans,
  ptipo_titular,
  ptipo_persona,
  pnombre_razonsocial,
  pprimer_apellido,
  psegundo_apellido,
  pnumero_documento,
  ptipo_documento,
  plugar_expedicion,
  ppais,
  pgenero,
  psolicitud_departamento,
  ppoder,
  pdireccion,
  ptelefono,
  pcelular,
  pemail,
  pfax,
  pestado,
  pid_sipi
);
seq_sigsolicitanteapoderado = (select currval('sec_sigsolicitanteapoderado')) ;
for vsolicitanteapoderado in (select *
 from sigsolicitanteapoderado
 where idsolicitanteapoderado = seq_sigsolicitanteapoderado
 ) loop
return next vsolicitanteapoderado;
end loop;
end if;----fin create

if operacion=2 then -- update
update sigsolicitanteapoderado set
  idsolicitanteapoderado =   pidsolicitanteapoderado,
  idsignomarca  =   pidsignomarca,
  idlogtrans =   pidlogtrans,
  tipo_titular =   ptipo_titular,
  tipo_persona =   ptipo_persona,
  nombre_razonsocial =   pnombre_razonsocial,
  primer_apellido =   pprimer_apellido,
  segundo_apellido =   psegundo_apellido,
  numero_documento =   pnumero_documento,
  tipo_documento =   ptipo_documento,
  lugar_expedicion =   plugar_expedicion,
  pais =   ppais,
  genero =   pgenero,
  solicitud_departamento = psolicitud_departamento,
  poder =   ppoder,
  direccion =   pdireccion,
  telefono =   ptelefono,
  celular =   pcelular,
  email =   pemail,
  fax =   pfax,
  estado =   pestado,
  id_sipi = pid_sipi
    where   idsolicitanteapoderado = pidsolicitanteapoderado;
  

for vsolicitanteapoderado in (select *
 from sigsolicitanteapoderado
 where idsolicitanteapoderado = pidsolicitanteapoderado
 ) loop
return next vsolicitanteapoderado;
end loop;
end if;----fin update

if operacion =3 then-- delete
     delete from sigsolicitanteapoderado 
      where idsolicitanteapoderado = pidsolicitanteapoderado;
end if; ---fin delete

if operacion=4 then --read
for vsolicitanteapoderado in (select *
 from sigsolicitanteapoderado
 where idsolicitanteapoderado = pidsolicitanteapoderado
 ) loop
return next vsolicitanteapoderado;
end loop;
 
end if; ---fin read
       
END;

$BODY$
LANGUAGE plpgsql;

/*
********************************************************************************
Creado: Eddy Valero Fecha:01/10/2016
Realizar el crud de la tabla sigtiposigno
*/
CREATE OR REPLACE FUNCTION crud_sigtiposigno(
    pidtiposigno bigint,
    pidsignomarca bigint,
    pidlogtrans bigint,
    ptipo_signo character varying,
    pdescripcion_otro character varying,
    pestado character varying,
    operacion integer)
  RETURNS SETOF sigtiposigno AS
$BODY$
/*
Creado: Eddy Valero Fecha:01/10/2016
Realizar el crud de la tabla sigtiposigno
*/
DECLARE
id bigint;
seq_sigtiposigno bigint;
vsigtiposigno sigtiposigno%ROWTYPE;
BEGIN
  if operacion=1 then -- Create
    insert into sigtiposigno(
      idsignomarca,
      idlogtrans,
      tipo_signo,
      descripcion_otro,
      estado
        )
      values(
      pidsignomarca,
      pidlogtrans,
      ptipo_signo,
      pdescripcion_otro,
      pestado
      );

    seq_sigtiposigno = (select currval('sec_sigtiposigno')) ;

    for vsigtiposigno in
      (
       select *
       from sigtiposigno
       where idtiposigno = seq_sigtiposigno
       )
    loop
      return next vsigtiposigno;
    end loop;
    
  end if;
  if operacion=2 then -- update
      update sigtiposigno set 
              idsignomarca=pidsignomarca,
              idlogtrans=pidlogtrans,
              tipo_signo=ptipo_signo,
              descripcion_otro=pdescripcion_otro,
              estado=pestado
      where idtiposigno=pidtiposigno;
        
  for vsigtiposigno in
    (
     select *
     from sigtiposigno
     where idtiposigno=pidtiposigno
     )
   loop
      return next vsigtiposigno;
   end loop;
      
  end if;
  if operacion =3 then-- delete
       delete from sigtiposigno where idtiposigno=pidtiposigno;
  end if;
  if operacion=4 then --read
    for vsigtiposigno  in
      (
      select * from sigtiposigno
      where estado = 'AC'
      order by idtiposigno asc
      )
    loop
    return next vsigtiposigno;
    end loop;

  end if;
END;
$BODY$
  LANGUAGE plpgsql;


/*
********************************************************************************
Creado: Susana Escobar Fecha:04/10/2016
Funcion para guardar, modificar, eliminar y listar registros en tablas sigseguimiento, modseguimiento, renseguimiento
*/
CREATE OR REPLACE FUNCTION crud_seguimiento(
    pidseguimiento bigint,
    pidsignomarca bigint,
    pidusuario bigint,
    pidlogtrans bigint,
    psm bigint,
    pnumero_publicacion bigint,
    pnumero_registro bigint,
    pserie_registro character varying,
    petapa character varying,
    pfecha_recepcion timestamp without time zone,
    pfecha_fin timestamp without time zone,
    pplazo_real integer,
    ptotal_tiempo bigint,
    pobservacion character varying,
    peditable boolean,
    pestado character varying,
    prefijotabla character varying,
    poperacion integer)
  RETURNS SETOF sigseguimiento AS
$BODY$
/*
********************************************************************************
Creado: Susana Escobar Fecha:04/10/2016
Funcion para guardar, modificar, eliminar y listar registros en tablas sigseguimiento, modseguimiento, renseguimiento
*/
DECLARE
seq_sigseguimiento bigint;
seq_modseguimiento bigint;
seq_renseguimiento bigint;
vsigseguimiento sigseguimiento%ROWTYPE;
vmodseguimiento modseguimiento%ROWTYPE;
vrenseguimiento renseguimiento%ROWTYPE;
/*
********************************************************************************
Creado: Susana Escobar Fecha:04/10/2016
Funcion para guardar, modificar, eliminar y listar registros en tablas sigseguimiento, modseguimiento, renseguimiento
*/
BEGIN
if poperacion=1 then -- Create
	--------Signos
	IF prefijotabla = 'SIG' THEN
	insert into sigseguimiento(
		idsignomarca, idusuario, idlogtrans, sm, numero_publicacion, numero_registro, 
		serie_registro, etapa, fecha_recepcion, fecha_fin, plazo_real, total_tiempo, observacion, 
		editable, estado
		)
	  values(
		pidsignomarca, pidusuario, pidlogtrans, psm, pnumero_publicacion, pnumero_registro, 
		pserie_registro, petapa, pfecha_recepcion, pfecha_fin, pplazo_real, ptotal_tiempo, pobservacion, 
		peditable, pestado
		);
	seq_sigseguimiento = (select currval('sec_sigseguimiento')) ;
	for vsigseguimiento in (select *
	 from sigseguimiento
	 where idseguimiento = seq_sigseguimiento
	 ) loop
	return next vsigseguimiento;
	end loop;
	END IF; 	
	--------Modificaciones
	IF prefijotabla = 'MOD' THEN
	insert into modseguimiento(
		idmodificacion, idusuario, idlogtrans, sm, numero_publicacion, numero_registro, 
		serie_registro, etapa, fecha_recepcion, fecha_fin, plazo_real,total_tiempo, observacion, 
		editable, estado
		)
	  values(
		pidsignomarca, pidusuario, pidlogtrans, psm, pnumero_publicacion, pnumero_registro, 
		pserie_registro, petapa, pfecha_recepcion, pfecha_fin, pplazo_real, ptotal_tiempo,pobservacion, 
		peditable, pestado
		);
	seq_modseguimiento = (select currval('sec_modseguimiento')) ;
	for vmodseguimiento in (select *
	 from modseguimiento
	 where idseguimiento = seq_modseguimiento
	 ) loop
	return next vmodseguimiento;
	end loop;
	END IF; 
	--------Renovaciones
	IF prefijotabla = 'REN' THEN
	insert into renseguimiento(
		idsolicitudrenovacion, idusuario, idlogtrans, sm, numero_publicacion, numero_registro, 
		serie_registro, etapa, fecha_recepcion, fecha_fin, plazo_real, total_tiempo,observacion, 
		editable, estado
		)
	  values(
		pidsignomarca, pidusuario, pidlogtrans, psm, pnumero_publicacion, pnumero_registro, 
		pserie_registro, petapa, pfecha_recepcion, pfecha_fin, pplazo_real,ptotal_tiempo, pobservacion, 
		peditable, pestado
		);
	seq_renseguimiento = (select currval('sec_renseguimiento')) ;
	for vrenseguimiento in (select *
	 from renseguimiento
	 where idseguimiento = seq_renseguimiento
	 ) loop
	return next vrenseguimiento;
	end loop;
	END IF; 
end if;-----fin create

---------------------------------------------------------------------------
if poperacion=2 then -- update
--------Signos
	IF prefijotabla = 'SIG' THEN
	update sigseguimiento set
	  	idseguimiento = pidseguimiento,
		idsignomarca = pidsignomarca,
		idusuario = pidusuario,
		idlogtrans = pidlogtrans,
		sm = psm,
		numero_publicacion = pnumero_publicacion,
		numero_registro = pnumero_registro,
		serie_registro = pserie_registro,
		etapa = petapa,
		fecha_recepcion = pfecha_recepcion,
		fecha_fin = pfecha_fin,
		plazo_real = pplazo_real,
                total_tiempo=ptotal_tiempo,
		observacion = pobservacion,
		editable = peditable,
		estado = pestado		
	where 	idseguimiento = pidseguimiento;	
	for vsigseguimiento in (select *
	 from sigseguimiento
	 where idseguimiento = pidseguimiento
	 ) loop
	return next vsigseguimiento;
	end loop;
	END IF; 
	--------Modificaciones
	IF prefijotabla = 'MOD' THEN
	update modseguimiento set
	  	idseguimiento = pidseguimiento,
		idmodificacion = pidsignomarca,
		idusuario = pidusuario,
		idlogtrans = pidlogtrans,
		sm = psm,
		numero_publicacion = pnumero_publicacion,
		numero_registro = pnumero_registro,
		serie_registro = pserie_registro,
		etapa = petapa,
		fecha_recepcion = pfecha_recepcion,
		fecha_fin = pfecha_fin,
		plazo_real = pplazo_real,
		total_tiempo=ptotal_tiempo,
		observacion = pobservacion,
		editable = peditable,
		estado = pestado		
	where 	idseguimiento = pidseguimiento;	
	for vmodseguimiento in (select *
	 from modseguimiento
	 where idseguimiento = pidseguimiento
	 ) loop
	return next vmodseguimiento;
	end loop;
	END IF; 
	--------Renovaciones
	IF prefijotabla = 'REN' THEN
	update renseguimiento set
	  	idseguimiento = pidseguimiento,
		idsolicitudrenovacion = pidsignomarca,
		idusuario = pidusuario,
		idlogtrans = pidlogtrans,
		sm = psm,
		numero_publicacion = pnumero_publicacion,
		numero_registro = pnumero_registro,
		serie_registro = pserie_registro,
		etapa = petapa,
		fecha_recepcion = pfecha_recepcion,
		fecha_fin = pfecha_fin,
		plazo_real = pplazo_real,
		total_tiempo=ptotal_tiempo,
		observacion = pobservacion,
		editable = peditable,
		estado = pestado		
	where 	idseguimiento = pidseguimiento;	
	for vrenseguimiento in (select *
	 from renseguimiento
	 where idseguimiento = pidseguimiento
	 ) loop
	return next vrenseguimiento;
	end loop;
	END IF;
end if;----fin update
------------------------------------------------------------------
if poperacion =3 then-- delete
     IF prefijotabla = 'SIG' THEN
     delete from sigseguimiento where idseguimiento = pidseguimiento;
     END IF;  
     IF prefijotabla = 'MOD' THEN
     delete from modseguimiento where idseguimiento = pidseguimiento;
     END IF;  
     IF prefijotabla = 'REN' THEN
     delete from renseguimiento where idseguimiento = pidseguimiento;
     END IF;      
end if; ---fin delete
---------------------------------------------------------------------
if poperacion=4 then --read
	 IF prefijotabla = 'SIG' THEN
		 for vsigseguimiento in (select *
		 from sigseguimiento
		 where idseguimiento = pidseguimiento
		 ) loop
		 return next vsigseguimiento;
		end loop;
	 END IF;
	 IF prefijotabla = 'MOD' THEN
		 for vmodseguimiento in (select *
		 from modseguimiento
		 where idseguimiento = pidseguimiento
		 ) loop
		 return next vmodseguimiento;
		end loop;
	 END IF; 
	 IF prefijotabla = 'REN' THEN
		 for vrenseguimiento in (select *
		 from renseguimiento
		 where idseguimiento = pidseguimiento
		 ) loop
		 return next vrenseguimiento;
		end loop;
	 END IF; 	   
end if; ---fin read
END;
$BODY$
  LANGUAGE plpgsql;

/*
********************************************************************************
Creado: Susana Escobar Fecha:04/10/2016
Funcion para listar registros de tablas sigseguimiento, modseguimiento, renseguimiento por id
*/
CREATE OR REPLACE FUNCTION lista_seguimiento_tramite(
    prefijo character varying,
    pidreferencia bigint)
  RETURNS SETOF sigseguimiento AS
$BODY$
/*
********************************************************************************
Creado: Susana Escobar Fecha:04/10/2016
Funcion para listar registros de tablas sigseguimiento, modseguimiento, renseguimiento por id
*/
DECLARE 
    reg sigseguimiento%ROWTYPE; 
BEGIN

    IF prefijo = 'SIG' THEN
        for reg in
            (
            select * from sigseguimiento
            where estado = 'AC'
            and idsignomarca = pidreferencia
            order by fecha_recepcion asc
            )
        loop
            return next reg;
        end loop;
    END IF;
    IF prefijo = 'REN' THEN
        for reg in
            (
            select * from renseguimiento
            where estado = 'AC'
            and idsolicitudrenovacion = pidreferencia
            order by fecha_recepcion asc
            )
        loop
            return next reg;
        end loop;
    END IF;
    IF prefijo = 'MOD' THEN
        for reg in
            (
            select * from modseguimiento
            where estado = 'AC'
            and idmodificacion = pidreferencia
            order by fecha_recepcion asc
            )
        loop
            return next reg;
        end loop;
    END IF;    
end;
$BODY$
  LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION lista_seguimiento_etapa(
    prefijo character varying,
    petapa character varying,
    pidreferencia bigint)
RETURNS SETOF sigseguimiento AS
$BODY$
DECLARE 
    reg sigseguimiento%ROWTYPE; 
BEGIN
   IF prefijo = 'SIG' THEN
        for reg in
            (
            select * from sigseguimiento
            where etapa = petapa and estado = 'AC'
            and idsignomarca = pidreferencia
            order by fecha_recepcion desc
            )
        loop
            return next reg;
        end loop;
    END IF;
    IF prefijo = 'REN' THEN
        for reg in
            (
            select * from renseguimiento
            where etapa = petapa and estado = 'AC'
            and idsolicitudrenovacion = pidreferencia
            order by fecha_recepcion desc
            )
        loop
            return next reg;
        end loop;
    END IF;
    IF prefijo = 'MOD' THEN
        for reg in
            (
            select * from modseguimiento
            where etapa = petapa and estado = 'AC'
            and idmodificacion = pidreferencia
            order by fecha_recepcion desc
            )
        loop
            return next reg;
        end loop;
    END IF;    
end;
$BODY$
LANGUAGE plpgsql; 

/*
********************************************************************************
Creado: Susana Escobar Fecha:17/10/2016
Listar modificacion por tipo, numero, gestion)
*/
CREATE OR REPLACE FUNCTION lista_modmodificacion_tipo(
    ptipo character varying,
    pnumero bigint,
    pgestion integer)
  RETURNS SETOF modmodificacion AS
$BODY$
/*
********************************************************************************
Creado: Susana Escobar Fecha:17/10/2016
Listar modificacion por tipo, numero, gestion)
*/
 DECLARE 
    vmodmodificacion modmodificacion%ROWTYPE; 
BEGIN

for vmodmodificacion in select * from modmodificacion where tipo_modificacion=ptipo 
and numero=pnumero
and gestion=pgestion and estado='AC' loop
    return next vmodmodificacion;
end loop;
end;
$BODY$
  LANGUAGE plpgsql; 


/*
********************************************************************************
Creado: Eddy Valero Fecha:03/10/2016
Realizar crud de la tabla sigdirecciondenotificacion
*/
CREATE OR REPLACE FUNCTION crud_sigdirecciondenotificacion(
    piddirecciondenotificacion bigint,
    pidsignomarca bigint,
    pidlogtrans bigint,
    pciudad_notificacion character varying,
    pzona_barrio character varying,
    pavenida_calle character varying,
    pnumero character varying,
    pedificio character varying,
    ppiso character varying,
    pdepartamento character varying,
    pcorreo_electronico character varying,
    preferencia_direccion character varying,
    ptelefono character varying,
    pcelular character varying,
    pestado character varying,
    operacion integer)
  RETURNS SETOF sigdirecciondenotificacion AS
$BODY$
/*
Creado: Eddy Valero Fecha:03/10/2016
Realizar crud de la tabla sigdirecciondenotificacion
*/
DECLARE
seq_sigdirecciondenotificacion bigint;
vdirecciondenotificacion sigdirecciondenotificacion%ROWTYPE;
BEGIN

if operacion=1 then -- Create
insert into sigdirecciondenotificacion(
  idsignomarca,
  idlogtrans,
  ciudad_notificacion,
  zona_barrio,
  avenida_calle,
  numero,
  edificio,
  piso,
  departamento,
  correo_electronico,
  referencia_direccion,
  telefono,
  celular,
  estado
)
values(
  pidsignomarca,
  pidlogtrans,
  pciudad_notificacion,
  pzona_barrio,
  pavenida_calle,
  pnumero,
  pedificio,
  ppiso,
  pdepartamento,
  pcorreo_electronico,
  preferencia_direccion,
  ptelefono,
  pcelular,
  pestado
);
seq_sigdirecciondenotificacion = (select currval('sec_sigdirecciondenotificacion')) ;
for vdirecciondenotificacion in (select *
 from sigdirecciondenotificacion
 where iddirecciondenotificacion = seq_sigdirecciondenotificacion
 ) loop
return next vdirecciondenotificacion;
end loop;
end if;----fin create


if operacion=2 then -- update
update sigdirecciondenotificacion set
  iddirecciondenotificacion = piddirecciondenotificacion, 
  idsignomarca =   pidsignomarca,
  idlogtrans =   pidlogtrans,
  ciudad_notificacion =   pciudad_notificacion,
  zona_barrio =   pzona_barrio,
  avenida_calle =   pavenida_calle,
  numero =   pnumero,
  edificio =   pedificio,
  piso =   ppiso,
  departamento =   pdepartamento,
  correo_electronico =   pcorreo_electronico,
  referencia_direccion = preferencia_direccion,
  telefono =   ptelefono,
  celular =   pcelular,
  estado =   pestado
  where   iddirecciondenotificacion = piddirecciondenotificacion;
  

for vdirecciondenotificacion in (select *
 from sigdirecciondenotificacion
 where iddirecciondenotificacion = piddirecciondenotificacion
 ) loop
return next vdirecciondenotificacion;
end loop;
end if;----fin update

if operacion =3 then-- delete
     delete from sigdirecciondenotificacion where iddirecciondenotificacion = piddirecciondenotificacion;
end if; ---fin delete


if operacion=4 then --read
for vdirecciondenotificacion in (select *
 from sigdirecciondenotificacion
 where iddirecciondenotificacion = piddirecciondenotificacion
 ) loop
return next vdirecciondenotificacion;
end loop;
 
end if; ---fin read
       
END;
$BODY$
  LANGUAGE plpgsql;

/*
********************************************************************************
Creado: Eddy Valero Fecha:03/10/2016
Realizar el crud de la tabla de sigsignomarca
*/

CREATE OR REPLACE FUNCTION crud_sigsignomarca(
  pidsignomarca bigint,
  pidlogtrans bigint,
  pnumero_formulario character varying,
  psm bigint,
  psigno character varying,
  ptipo_genero character varying,
  pdescripcion_signo character varying,
  pdescripcion_logotipo character varying,
  pubicacion character varying,
  pestado_marca character varying,
  pnro_recibo bigint,
  pserie character varying,
  pnumero_titulo bigint,
  pserie_titulo character varying,
  porigen_solicitud character varying,
  pnumero_gaceta bigint,
  pnumero_publicacion bigint,
  pfecha_publicacion timestamp without time zone,
  pnumero_registro bigint,
  pserie_registro character varying,
  presolucion_registro character varying,
  pfecha_registro timestamp without time zone,
  porden_renovacion integer,
  pnumero_renovacion integer,
  pextension_renovacion character varying,
  pnumero_resolucion_renovacion bigint,
  pfecha_renovacion timestamp without time zone,
  poficina character varying,
  pfecha_solicitud timestamp without time zone,
  pfecha_ingreso timestamp without time zone,
  pentregado_usuario boolean,
  pnotoriedad_marca boolean,
  pestado character varying,
  operacion integer)
  RETURNS SETOF sigsignomarca AS
$BODY$
/*
Creado: Eddy Valero Fecha:03/10/2016
Realizar el crud de la tabla de sigsignomarca
*/

DECLARE
seq_sigsignomarca bigint;
vsigsignomarca sigsignomarca%ROWTYPE;
BEGIN
if operacion=1 then -- Create
    insert into sigsignomarca(
      idlogtrans,
      numero_formulario,
      sm,
      signo,
      tipo_genero,
      descripcion_signo,
      descripcion_logotipo,
      ubicacion,
      estado_marca,
      nro_recibo,
      serie,
      numero_titulo,
      serie_titulo,
      origen_solicitud,
      numero_gaceta,
      numero_publicacion,
      fecha_publicacion,
      numero_registro,
      serie_registro,
      resolucion_registro,
      fecha_registro,
      orden_renovacion,
      numero_renovacion,
      extension_renovacion,
      numero_resolucion_renovacion,
      fecha_renovacion,
      oficina,
      fecha_solicitud,
      fecha_ingreso,
      entregado_usuario,
      notoriedad_marca,
      estado
      )
      values(
      pidlogtrans,
      pnumero_formulario,
      psm,
      psigno,
      ptipo_genero,
      pdescripcion_signo,
      pdescripcion_logotipo,
      pubicacion,
      pestado_marca,
      pnro_recibo,
      pserie,
      pnumero_titulo,
      pserie_titulo,
      porigen_solicitud,
      pnumero_gaceta,
      pnumero_publicacion,
      pfecha_publicacion,
      pnumero_registro,
      pserie_registro,
      presolucion_registro,
      pfecha_registro,
      porden_renovacion,
      pnumero_renovacion,
      pextension_renovacion,
      pnumero_resolucion_renovacion,
      pfecha_renovacion,
      poficina,
      pfecha_solicitud,
      pfecha_ingreso,
      pentregado_usuario,
      pnotoriedad_marca,
      pestado
      );

  seq_sigsignomarca = (select currval('sec_sigsignomarca')) ;
  for vsigsignomarca in (
    select *
    from sigsignomarca
    where idsignomarca = seq_sigsignomarca
    )
    loop
      return next vsigsignomarca;
    end loop;
end if;

if operacion=2 then -- update
    update sigsignomarca
      set
        idsignomarca = pidsignomarca,
        idlogtrans = pidlogtrans,
        numero_formulario = pnumero_formulario,
        sm = psm,
        signo = psigno,
        tipo_genero = ptipo_genero,
        descripcion_signo = pdescripcion_signo,
        descripcion_logotipo = pdescripcion_logotipo,
        ubicacion = pubicacion,
        estado_marca = pestado_marca,
        nro_recibo = pnro_recibo,
        serie = pserie,
        numero_titulo = pnumero_titulo,
        serie_titulo = pserie_titulo,
        origen_solicitud = porigen_solicitud,
        numero_gaceta = pnumero_gaceta,
        numero_publicacion = pnumero_publicacion,
        fecha_publicacion = pfecha_publicacion,
        numero_registro = pnumero_registro,
        serie_registro = pserie_registro,
        resolucion_registro = presolucion_registro,
        fecha_registro = pfecha_registro,
        orden_renovacion = porden_renovacion,
        numero_renovacion = pnumero_renovacion,
        extension_renovacion = pextension_renovacion,
        numero_resolucion_renovacion = pnumero_resolucion_renovacion,
        fecha_renovacion = pfecha_renovacion,
        oficina = poficina,
        fecha_solicitud = pfecha_solicitud,
        fecha_ingreso = pfecha_ingreso,
        entregado_usuario = pentregado_usuario,
        notoriedad_marca = pnotoriedad_marca,
        estado = pestado
      where idsignomarca = pidsignomarca;
     for vsigsignomarca in (
         select *
         from sigsignomarca
         where idsignomarca = pidsignomarca
     )
     loop
      return next vsigsignomarca;
     end loop;

end if;
  
if operacion =3 then-- delete
     delete from sigsignomarca where idsignomarca = pidsignomarca;
end if;

if operacion=4 then --read
  for vsigsignomarca  in
    (
    select * from sigsignomarca
    where estado = 'AC'
    )
  loop
  return next vsigsignomarca;
  end loop;

end if;
END;
$BODY$
  LANGUAGE plpgsql;



/*
********************************************************************************
Creado: Eddy Valero Fecha:08/10/2016
Realizar el listado de solicitantes o apoderados de acuerdo a un determinado parametro
*/
CREATE OR REPLACE FUNCTION obtiene_sigsolicitanteapoderadoporidsolicitudytipopersona(
    pidsignomarca bigint,
    ptipo_persona character varying)
  RETURNS SETOF sigsolicitanteapoderado AS
$BODY$
/*
Creado: Eddy Valero Fecha:08/10/2016
Realizar el listado de solicitantes o apoderados de acuerdo a un determinado parametro
*/
DECLARE 
    vsigsolicitanteapoderado sigsolicitanteapoderado%ROWTYPE; 
BEGIN

for vsigsolicitanteapoderado in 
    select *
    from sigsolicitanteapoderado
    where
      idsignomarca=pidsignomarca
      and tipo_persona=ptipo_persona
      and estado='AC' 
loop
    return next vsigsolicitanteapoderado;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;


/*
********************************************************************************
Creado: Eddy Valero Fecha:08/10/2016
Realizar el crud de la tabla sigprioridadpreferencia
*/

CREATE OR REPLACE FUNCTION crud_sigprioridadpreferencia(
	pidprioridadpreferencia bigint,
	pidsignomarca bigint,
	pidlogtrans bigint,
	ptipo_prioridad character varying,
	ptipo_interes character varying,
	pnombre_numero character varying,
	pfecha timestamp without time zone,
	plugar character varying,
	pestado character varying,
    pid_sipi bigint,
	operacion integer)
    RETURNS SETOF sigprioridadpreferencia 
    LANGUAGE 'plpgsql'

    COST 100
    VOLATILE 
    ROWS 1000
AS $BODY$

/*
Creado: Eddy Valero Fecha:08/10/2016
Modificado: Placido Castro Fecha:08/12/2017
Realizar el crud de la tabla sigprioridadpreferencia
*/
DECLARE
  seq_idprioridadpreferencia bigint;
  vsigprioridadpreferencia sigprioridadpreferencia%ROWTYPE;

BEGIN

    if operacion=1 then -- Create

        insert into sigprioridadpreferencia(
              idsignomarca,
              idlogtrans,
              tipo_prioridad,
              tipo_interes,
              nombre_numero,
              fecha,
              lugar,
              estado,
              id_sipi
          )
          values(
              pidsignomarca,
              pidlogtrans,
              ptipo_prioridad,
              ptipo_interes,
              pnombre_numero,
              pfecha,
              plugar,
              pestado,
              pid_sipi
          );

        seq_idprioridadpreferencia = (select currval('sec_sigprioridadpreferencia')) ;

        for vsigprioridadpreferencia in
        (
          select *
          from sigprioridadpreferencia
          where idprioridadpreferencia = seq_idprioridadpreferencia
         ) loop
          return next vsigprioridadpreferencia;
        end loop;
      
    end if;
    if operacion=2 then -- update
          update sigprioridadpreferencia set 
              idprioridadpreferencia = pidprioridadpreferencia,
              idsignomarca = pidsignomarca,
              idlogtrans = pidlogtrans,
              tipo_prioridad = ptipo_prioridad,
              tipo_interes = ptipo_interes,
              nombre_numero = pnombre_numero,
              fecha = pfecha,
              lugar = plugar,
              estado = pestado,
              id_sipi = pid_sipi
          where idprioridadpreferencia=pidprioridadpreferencia;

          for vsigprioridadpreferencia in (
             select *
             from sigprioridadpreferencia
             where idprioridadpreferencia = pidprioridadpreferencia
           )
           loop
              return next vsigprioridadpreferencia;
           end loop;
    end if;

    if operacion =3 then-- delete
            delete from sigprioridadpreferencia where idprioridadpreferencia= pidprioridadpreferencia;
    end if;

    if operacion=4 then --read
          for vsigprioridadpreferencia  in
            (
            select *
            from sigprioridadpreferencia
            where estado = 'AC'
            order by idprioridadpreferencia asc
            )
          loop
          return next vsigprioridadpreferencia;
          end loop;
    end if;

END;

$BODY$
  LANGUAGE plpgsql;

/*
********************************************************************************
Creado: Eddy Valero Fecha:10/09/2016
Realizar crud de la tabla sigsignoclaseniza
*/
CREATE OR REPLACE FUNCTION crud_sigsignoclaseniza(
	pidsignoclaseniza bigint,
	pidsignomarca bigint,
	pidclaseniza bigint,
	pidlogtrans bigint,
	pnumero_clase_niza bigint,
	plista_producto text,
	pestado character varying,
    pid_sipi bigint,
	operacion integer)
    RETURNS SETOF sigsignoclaseniza 
    LANGUAGE 'plpgsql'

    COST 100
    VOLATILE 
    ROWS 1000
AS $BODY$

/*
Creado: Eddy Valero Fecha:10/09/2016
Modificado: Placido Castro Fecha:08/12/2017
Realizar crud de la tabla sigsignoclaseniza
*/
DECLARE
  seq_sigsignoclaseniza bigint;
  vsigsignoclaseniza sigsignoclaseniza%ROWTYPE;
BEGIN

    if operacion = 1 then -- Create
        insert into sigsignoclaseniza(
          idsignomarca,
          idclaseniza,
          idlogtrans,
          numero_clase_niza,
          lista_producto,
          estado,
          id_sipi
        )
        values(  
          pidsignomarca,
          pidclaseniza,
          pidlogtrans,
          pnumero_clase_niza,
          plista_producto,
          pestado,
          pid_sipi
        );
        seq_sigsignoclaseniza = (select currval('sec_sigsignoclaseniza')) ;

        for vsigsignoclaseniza in (
             select *
             from sigsignoclaseniza
             where idsignoclaseniza = seq_sigsignoclaseniza
         )
        loop
          return next vsigsignoclaseniza;
        end loop;
    end if;----fin create

    if operacion = 2 then -- update
          update sigsignoclaseniza set
              idsignoclaseniza = pidsignoclaseniza,
              idclaseniza = pidclaseniza,
              idlogtrans = pidlogtrans,
              numero_clase_niza = pnumero_clase_niza,
              lista_producto = plista_producto,
              estado = pestado,
              id_sipi = pid_sipi              
              where  idsignoclaseniza = pidsignoclaseniza;
            

          for vsigsignoclaseniza in(
            select *
            from sigsignoclaseniza
            where idsignoclaseniza = pidsignoclaseniza
           )
          loop
              return next vsigsignoclaseniza;
          end loop;
    end if;----fin update

    if operacion = 3 then-- delete
         delete from sigsignoclaseniza 
          where idsignoclaseniza = pidsignoclaseniza;
    end if; ---fin delete

    if operacion = 4 then --read
      for vsigsignoclaseniza in (
         select *
         from sigsignoclaseniza
         where idsignoclaseniza = pidsignoclaseniza
       )
      loop
            return next vsigsignoclaseniza;
      end loop;
     
    end if; ---fin read
       
END;

$BODY$
  LANGUAGE plpgsql;




/*
********************************************************************************
Creado: Chano Rojas Fecha:11/10/2016
Realizar registro en tabla recibo
*/

CREATE OR REPLACE FUNCTION crud_recibo(pidrecibo bigint, pidtasa bigint, pidlogtrans bigint, pnumero_recibo bigint, pserie_recibo character varying, pfecha_emision_recibo timestamp without time zone, pmonto numeric, pliteral_monto character varying, pconcepto character varying, pdato_recibo character varying, pcantidad integer, pestado_recibido character varying, porigen character varying, pestado character varying, psolicitante character varying, papoderado character varying, pnumero_titulo bigint, pserie_titulo character, ptipo_titulo character, ptipo_tramite_ingresado character varying, pnumero_tramite_ingresado character varying, pgestion_tramite_ingresado character varying, pextension_tramite_ingresado character varying, pclase_ingresado bigint, pdesc_ingresado character varying, papoderado_ingresado character varying, pdep_ingresado bigint, ptitular_ingresado bigint, pserie_titular_ingresado character varying, ptipo_titular_ingresado integer, pcodigotramite character, pnumero_tramite bigint, pgestion_tramite bigint, pclasetramite character, pextencion_tramite character varying, pdescripcionmarca character, pexpediente bigint, ptipo_tramite character varying, psigla character varying, pobservacion character varying, operacion integer)
  RETURNS SETOF recibo AS
$BODY$
/*
Creado: Chano Rojas Fecha:11/10/2016
Realizar registro en tabla recibo
*/
DECLARE
seq_recibo bigint;
vrecibo recibo%ROWTYPE;
BEGIN

if operacion=1 then -- Create
insert into recibo(    
  idtasa,
  idlogtrans,
  numero_recibo,
  serie_recibo,
  fecha_emision_recibo,
  monto,
  literal_monto,
  concepto,
  dato_recibo,
  cantidad,
  estado_recibido,
  origen,
  estado,
  solicitante,
  apoderado,
  numero_titulo,
  serie_titulo,
  tipo_titulo,
  tipo_tramite_ingresado,
  numero_tramite_ingresado,
  gestion_tramite_ingresado,
  extension_tramite_ingresado,
  clase_ingresado,
  desc_ingresado,
  apoderado_ingresado,
  dep_ingresado,
  titular_ingresado,
  serie_titular_ingresado,
  tipo_titular_ingresado,
  codigotramite,
  numero_tramite,
  gestion_tramite,
  clasetramite,
  extencion_tramite,
  descripcionmarca,
  expediente,
  tipo_tramite,
  sigla,
  observacion
)
values(         
  pidtasa,
  pidlogtrans,
  pnumero_recibo,
  pserie_recibo,
  pfecha_emision_recibo,
  pmonto,
  pliteral_monto,
  pconcepto,
  pdato_recibo,
  pcantidad,
  pestado_recibido,
  porigen,
  pestado,
  psolicitante,
  papoderado,
  pnumero_titulo,
  pserie_titulo,
  ptipo_titulo,
  ptipo_tramite_ingresado,
  pnumero_tramite_ingresado,
  pgestion_tramite_ingresado,
  pextension_tramite_ingresado,
  pclase_ingresado,
  pdesc_ingresado,
  papoderado_ingresado,
  pdep_ingresado,
  ptitular_ingresado,
  pserie_titular_ingresado,
  ptipo_titular_ingresado,
  pcodigotramite,
  pnumero_tramite,
  pgestion_tramite,
  pclasetramite,
  pextencion_tramite,
  pdescripcionmarca,
  pexpediente,
  ptipo_tramite,
  psigla,
  pobservacion
);
seq_recibo = (select currval('sec_recibo')) ;
for vrecibo in (select *
 from recibo
 where idrecibo= seq_recibo
 ) loop
return next vrecibo;
end loop;
end if;----fin create

if operacion=2 then -- update
update recibo set
	idtasa=pidtasa,
  idlogtrans=pidlogtrans,
  numero_recibo=pnumero_recibo,
  serie_recibo=pserie_recibo,
  fecha_emision_recibo=pfecha_emision_recibo,
  monto=pmonto,
  literal_monto=pliteral_monto,
  concepto=pconcepto,
  dato_recibo=pdato_recibo,
  cantidad=pcantidad,
  estado_recibido=pestado_recibido,
  origen=porigen,
  estado=pestado,
  solicitante=psolicitante,
  apoderado=papoderado,
  numero_titulo=pnumero_titulo,
  serie_titulo=pserie_titulo,
  tipo_titulo=ptipo_titulo,
  tipo_tramite_ingresado=ptipo_tramite_ingresado,
  numero_tramite_ingresado=pnumero_tramite_ingresado,
  gestion_tramite_ingresado=pgestion_tramite_ingresado,
  extension_tramite_ingresado=pextension_tramite_ingresado,
  clase_ingresado=pclase_ingresado,
  desc_ingresado=pdesc_ingresado,
  apoderado_ingresado=papoderado_ingresado,
  dep_ingresado=pdep_ingresado,
  titular_ingresado=ptitular_ingresado,
  serie_titular_ingresado=pserie_titular_ingresado,
  tipo_titular_ingresado=ptipo_titular_ingresado,
  codigotramite=pcodigotramite,
  numero_tramite=pnumero_tramite,
  gestion_tramite=pgestion_tramite,
  clasetramite=pclasetramite,
  extencion_tramite=pextencion_tramite,
  descripcionmarca=pdescripcionmarca,
  expediente=pexpediente,
  tipo_tramite=ptipo_tramite,
  sigla=psigla,
  observacion=pobservacion
	where 	idrecibo = pidrecibo;
	
--seq_modmodificacion = (select currval('sec_modmodificacion')) ;
for vrecibo in (select *
 from recibo
 where idrecibo = pidrecibo
 ) loop
return next vrecibo;
end loop;
end if;----fin update

if operacion =3 then-- delete
     delete from recibo where idrecibo = pidrecibo;
end if; ---fin delete


if operacion=4 then --read
    for vrecibo in (select *
 from recibo
 where idrecibo = pidrecibo
 ) loop
return next vrecibo;
end loop;
 
end if; ---fin read
       
END;
$BODY$
  LANGUAGE plpgsql;

/*
********************************************************************************
Creado: Susana Escobar Fecha:18/10/2016
Realizar registro en tabla sigpublicacion
*/
CREATE OR REPLACE FUNCTION crud_sigpublicacion(
	  pidpublicacion bigint,
	  pidlogtrans bigint,
	  pnumero_gaceta bigint,
	  pfecha_publicacion timestamp without time zone,
	  pfecha_envio timestamp without time zone,
	  pmes character varying,
	  pgestion integer,
	  pinicio bigint,
	  pfin bigint,
	  ptotal bigint,
	  pobservacion text,
	  pestado_publicacion character varying,
	  pestado character varying, 
          operacion integer)
  RETURNS SETOF sigpublicacion AS
$BODY$
/*
Creado: Susana Escobar Fecha:18/10/2016
Realizar registro en tabla sigpublicacion
*/
DECLARE
seq_sigpublicacion bigint;
vpublicacion sigpublicacion%ROWTYPE;
BEGIN

if operacion=1 then -- Create
insert into sigpublicacion(  
  idlogtrans,
  numero_gaceta,
  fecha_publicacion,
  fecha_envio,
  mes,
  gestion,
  inicio,
  fin,
  total,
  observacion,
  estado_publicacion,
  estado
)
values(  
  pidlogtrans,
  pnumero_gaceta,
  pfecha_publicacion,
  pfecha_envio,
  pmes,
  pgestion,
  pinicio,
  pfin,
  ptotal,
  pobservacion,
  pestado_publicacion,
  pestado
);
seq_sigpublicacion = (select currval('sec_sigpublicacion')) ;
for vpublicacion in (select *
 from sigpublicacion
 where idpublicacion = seq_sigpublicacion
 ) loop
return next vpublicacion;
end loop;
end if;----fin create


if operacion=2 then -- update
update sigpublicacion set
  idpublicacion = pidpublicacion,
  idlogtrans = pidlogtrans,
  numero_gaceta = pnumero_gaceta,
  fecha_publicacion = pfecha_publicacion,
  fecha_envio = pfecha_envio,
  mes = pmes,
  gestion = pgestion,
  inicio = pinicio,
  fin = pfin,
  total = ptotal,
  observacion = pobservacion,
  estado_publicacion = pestado_publicacion,
  estado = pestado
    where   idpublicacion = pidpublicacion;
  
for vpublicacion in (select *
 from sigpublicacion
 where idpublicacion = pidpublicacion
 ) loop
return next vpublicacion;
end loop;
end if;----fin update

if operacion =3 then-- delete
     delete from sigpublicacion 
      where idpublicacion = pidpublicacion;
end if; ---fin delete


if operacion=4 then --read
for vpublicacion in (select *
 from sigpublicacion
 where idpublicacion = pidpublicacion
 ) loop
return next vpublicacion;
end loop;
 
end if; ---fin read
       
END;
$BODY$
LANGUAGE plpgsql;

/*
********************************************************************************
Creado: Susana Escobar Fecha:18/10/2016
Realizar registro en tabla sigdetallepublicacion
*/
CREATE OR REPLACE FUNCTION crud_sigdetallepublicacion(
          piddetallepublicacion bigint,
	  pidpublicacion bigint,
	  pidsignomarca bigint,
	  pidlogtrans bigint,
	  pnumero_publicacion bigint,
	  psm bigint,
	  psm_descripcion character varying,
	  pfecha_ingreso timestamp without time zone,
	  pmarca character varying,
	  pclase integer,
	  ptipo_signo_descripcion character varying,
	  ptipo_genero character varying,
	  plista_productos text,
	  pdescripcion_signo character varying,
	  pnombre_titular character varying,
	  pdocumento_titular character varying,
	  ppais_titular character varying,
	  pdescripcion_pais_titular character varying,
	  pdescripcion_departamento_titular character varying,
	  pdireccion_titular character varying,
	  pnombre_apoderado character varying,
	  pdocumento_apoderado character varying,
	  pdireccion_apoderado character varying,
	  pprioridad character varying,
	  pfecha_prioridad character varying,
	  ppais_prioridad character varying,
	  ppais_prio_descripcion character varying,
	  pnumero_seccion integer,
	  pseccion character varying,
	  psubseccion character varying,
	  ppublicado boolean,
	  pestado character varying,
          operacion integer)
  RETURNS SETOF sigdetallepublicacion AS
$BODY$
/*
Creado: Susana Escobar Fecha:18/10/2016
Realizar registro en tabla sigdetallepublicacion
*/
DECLARE
seq_sigdetallepublicacion bigint;
vdetallepublicacion sigdetallepublicacion%ROWTYPE;
BEGIN

if operacion=1 then -- Create
insert into sigdetallepublicacion(  
    idpublicacion,
    idsignomarca,
    idlogtrans,
    numero_publicacion,
    sm,
    sm_descripcion,
    fecha_ingreso,
    marca,
    clase,
    tipo_signo_descripcion,
    tipo_genero,
    lista_productos,
    descripcion_signo,
    nombre_titular,
    documento_titular,
    pais_titular,
    descripcion_pais_titular,
    descripcion_departamento_titular,
    direccion_titular,
    nombre_apoderado,
    documento_apoderado,
    direccion_apoderado,
    prioridad,
    fecha_prioridad,
    pais_prioridad,
    pais_prio_descripcion,
    numero_seccion,
    seccion,
    subseccion,
    publicado,
    estado
)
values(  
    pidpublicacion,
    pidsignomarca,
    pidlogtrans,
    pnumero_publicacion,
    psm,
    psm_descripcion,
    pfecha_ingreso,
    pmarca,
    pclase,
    ptipo_signo_descripcion,
    ptipo_genero,
    plista_productos,
    pdescripcion_signo,
    pnombre_titular,
    pdocumento_titular,
    ppais_titular,
    pdescripcion_pais_titular,
    pdescripcion_departamento_titular,
    pdireccion_titular,
    pnombre_apoderado,
    pdocumento_apoderado,
    pdireccion_apoderado,
    pprioridad,
    pfecha_prioridad,
    ppais_prioridad,
    ppais_prio_descripcion,
    pnumero_seccion,
    pseccion,
    psubseccion,
    ppublicado,
    pestado
);
seq_sigdetallepublicacion = (select currval('sec_sigdetallepublicacion')) ;
for vdetallepublicacion in (select *
 from sigdetallepublicacion
 where iddetallepublicacion = seq_sigdetallepublicacion
 ) loop
return next vdetallepublicacion;
end loop;
end if;----fin create


if operacion=2 then -- update
update sigdetallepublicacion set
	iddetallepublicacion = piddetallepublicacion,
	idpublicacion = pidpublicacion,
	idsignomarca = pidsignomarca,
	idlogtrans = pidlogtrans,
	numero_publicacion = pnumero_publicacion,
	sm = psm,
	sm_descripcion = psm_descripcion,
	fecha_ingreso = pfecha_ingreso,
	marca = pmarca,
	clase = pclase,
	tipo_signo_descripcion = ptipo_signo_descripcion,
	tipo_genero = ptipo_genero,
	lista_productos = plista_productos,
	descripcion_signo = pdescripcion_signo, 
	nombre_titular = pnombre_titular,
	documento_titular = pdocumento_titular,
	pais_titular = ppais_titular,
	descripcion_pais_titular = pdescripcion_pais_titular,
	descripcion_departamento_titular = pdescripcion_departamento_titular,
	direccion_titular = pdireccion_titular,
	nombre_apoderado = pnombre_apoderado,
	documento_apoderado = pdocumento_apoderado,
	direccion_apoderado = pdireccion_apoderado,
	prioridad = pprioridad,
	fecha_prioridad = pfecha_prioridad,
	pais_prioridad = ppais_prioridad,
	pais_prio_descripcion = ppais_prio_descripcion,
	numero_seccion = pnumero_seccion,
	seccion = pseccion,
	subseccion = psubseccion,
	publicado = ppublicado,
	estado = pestado
    where   iddetallepublicacion = piddetallepublicacion;
  
for vdetallepublicacion in (select *
 from sigdetallepublicacion
 where iddetallepublicacion = piddetallepublicacion
 ) loop
return next vdetallepublicacion;
end loop;
end if;----fin update

if operacion =3 then-- delete
     delete from sigdetallepublicacion 
      where iddetallepublicacion = piddetallepublicacion;
end if; ---fin delete


if operacion=4 then --read
for vdetallepublicacion in (select *
 from sigdetallepublicacion
 where iddetallepublicacion = piddetallepublicacion
 ) loop
return next vdetallepublicacion;
end loop;
 
end if; ---fin read
       
END;
$BODY$
LANGUAGE plpgsql;


/*
********************************************************************************
Creado: Susana Escobar Paz Fecha:18/10/2016
Obtener todos los registros activos de tabla sigpublicacion
*/
CREATE OR REPLACE FUNCTION lista_sigpublicacion()
  RETURNS SETOF sigpublicacion AS
$BODY$
/*
Creado: Susana Escobar Paz Fecha:18/10/2016
Obtener todos los registros activos de tabla sigpublicacion
*/
 DECLARE 
    vpublicacion sigpublicacion%ROWTYPE; 
BEGIN

for vpublicacion in 
    select * from sigpublicacion where estado='AC' order by fecha_publicacion desc, idpublicacion desc loop
    return next vpublicacion;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;


/*
********************************************************************************
Creado: Susana Escobar Paz Fecha:18/10/2016
Obtener todos los registros activos de tabla sigdetallepublicacion por idpublicacion
*/
CREATE OR REPLACE FUNCTION lista_sigdetallepublicacion_idpublicacion(pidpublicacion bigint)
  RETURNS SETOF sigdetallepublicacion AS
$BODY$
/*
Creado: Susana Escobar Paz Fecha:18/10/2016
Obtener todos los registros activos de tabla sigdetallepublicacion por idpublicacion
*/
 DECLARE 
    vdetallepublicacion sigdetallepublicacion%ROWTYPE; 
BEGIN

for vdetallepublicacion in 
    select * from sigdetallepublicacion where idpublicacion=pidpublicacion
    and estado='AC' order by 1 loop
    return next vdetallepublicacion;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;

/*
********************************************************************************
Creado: Eddy Valero Fecha:19/10/2016
Modificado: Susana Escobar Paz Fecha:21/10/2016
obtener todos los registros ya sea de solicitantes o apoderados
de acuerdo a una marca
*/
CREATE OR REPLACE FUNCTION obt_solicitantesapoderados_idsignomarca(
    pidsignomarca bigint,
    ptipo_persona character varying)
  RETURNS text AS
$BODY$
/*
Creado: Eddy Valero Fecha:19/10/2016
Modificado: Susana Escobar Paz Fecha:21/10/2016
obtener todos los registros ya sea de solicitantes o apoderados
de acuerdo a una marca
*/
DECLARE 
    vsigsolicitanteapoderado sigsolicitanteapoderado%ROWTYPE;
    vtextosolicitantes text;
	
BEGIN
vtextosolicitantes := '';
	FOR vsigsolicitanteapoderado in (
		SELECT *
		FROM sigsolicitanteapoderado
		WHERE
			idsignomarca = pidsignomarca
			AND tipo_persona = ptipo_persona
			AND estado = 'AC'
	)
	LOOP				      
		    IF vtextosolicitantes = '' THEN
		    
		      vtextosolicitantes := vsigsolicitanteapoderado.nombre_razonsocial;
		      IF vsigsolicitanteapoderado.primer_apellido is not null then 
			 vtextosolicitantes := vtextosolicitantes||' '||vsigsolicitanteapoderado.primer_apellido;
		      END IF; 
		      IF vsigsolicitanteapoderado.segundo_apellido is not null then 
			 vtextosolicitantes := vtextosolicitantes||' '||vsigsolicitanteapoderado.segundo_apellido;
		      END IF;
		    
		    ELSE
		    
		      vtextosolicitantes := vtextosolicitantes||'; '||vsigsolicitanteapoderado.nombre_razonsocial;
		      IF vsigsolicitanteapoderado.primer_apellido is not null then 
			 vtextosolicitantes := vtextosolicitantes||' '||vsigsolicitanteapoderado.primer_apellido;
		      END IF; 
		      IF vsigsolicitanteapoderado.segundo_apellido is not null then 
			 vtextosolicitantes := vtextosolicitantes||' '||vsigsolicitanteapoderado.segundo_apellido;
		      END IF;
		    
		    END IF;		
	END LOOP;
RAISE NOTICE '%', vtextosolicitantes;	
	return vtextosolicitantes::text;
END;
$BODY$
  LANGUAGE plpgsql;


/*
********************************************************
Creado: Susana Escobar Paz Fecha:21/10/2016
crear texto concatenado con valores de busqueda 
por idsignomarca en tabla sigtiposigno
*/
CREATE OR REPLACE FUNCTION lista_sigtiposigno_idsignomarca_descripcion(    
    pidsignomarca bigint   
    )
  RETURNS text AS
$BODY$
/*
Creado: Susana Escobar Paz Fecha:21/10/2016
crear texto concatenado con valores de busqueda 
por idsignomarca en tabla sigtiposigno
*/
 DECLARE 
    vtiposigno sigtiposigno%ROWTYPE; 
    vtextotiposigno text;
BEGIN
vtextotiposigno := '';
for vtiposigno in select * from sigtiposigno where idsignomarca=pidsignomarca and estado='AC'
 loop
	 IF vtextotiposigno = '' THEN		
		    vtextotiposigno:= (select dom.nombre from dominio dom 
                    where dom.dominio = 'tipo_signo' 
                    and dom.codigo = vtiposigno.tipo_signo); 
         ELSE
                    --vtextotiposigno:= vtextotiposigno||' - '||(select dom.nombre from dominio dom 
                    --where dom.dominio = 'tipo_signo' 
                    --and dom.codigo = vtiposigno.tipo_signo); 	
                    vtextotiposigno:='Mixta';		
	END IF; 
end loop;
	return vtextotiposigno::text;
end;
$BODY$
  LANGUAGE plpgsql;


/*
********************************************************
Creado: Susana Escobar Paz Fecha:21/10/2016
crear texto concatenado con valores de numero de clase niza 
por idsignomarca en tabla sigsignoclaseniza
*/
CREATE OR REPLACE FUNCTION lista_sigsignoclaseniza_clase_descripcion(    
    pidsignomarca bigint   
    )
  RETURNS text AS
$BODY$
/*
Creado: Susana Escobar Paz Fecha:21/10/2016
crear texto concatenado con valores de numero de clase niza 
por idsignomarca en tabla sigsignoclaseniza
*/
 DECLARE 
    vclaseniza sigsignoclaseniza%ROWTYPE; 
    vtextoclase text;
BEGIN
vtextoclase := '';
for vclaseniza in (select * from sigsignoclaseniza 
		    where sigsignoclaseniza.idsignomarca = pidsignomarca and estado = 'AC') 	
 loop
	 IF vtextoclase = '' THEN
		  
		  vtextoclase:= vclaseniza.numero_clase_niza;		                      
         ELSE
                  vtextoclase:= vtextoclase||' - '||vclaseniza.numero_clase_niza; 
                  
	END IF; 
end loop;
	return vtextoclase::text;
end;
$BODY$
  LANGUAGE plpgsql;

/*
Creado: Susana Escobar Paz Fecha:21/10/2016
crear texto concatenado con valores de lista de producto 
por idsignomarca en tabla sigsignoclaseniza
*/
CREATE OR REPLACE FUNCTION lista_sigsignoclaseniza_listaproductos_descripcion(    
    pidsignomarca bigint   
    )
  RETURNS text AS
$BODY$
/*
********************************************************
Creado: Susana Escobar Paz Fecha:21/10/2016
crear texto concatenado con valores de lista de producto 
por idsignomarca en tabla sigsignoclaseniza
*/
 DECLARE 
    vlistaprod sigsignoclaseniza%ROWTYPE; 
    vtextolista text;
BEGIN
vtextolista := '';
for vlistaprod in (select * from sigsignoclaseniza 
		    where sigsignoclaseniza.idsignomarca = pidsignomarca and estado = 'AC') 	
loop
	 IF vtextolista = '' THEN
		 
		  vtextolista:= vlistaprod.lista_producto;		                      
         ELSE
                  vtextolista:= vtextolista||'\n '||vlistaprod.lista_producto;                   
	END IF; 
end loop;
	return vtextolista::text;
end;
$BODY$
  LANGUAGE plpgsql;


/*
********************************************************************************
Creado: Susana Escobar Paz Fecha:21/10/2016
crear texto concatenado con valores de numeros
de documentos por idsignomarca y tipo_persona
*/
CREATE OR REPLACE FUNCTION lista_solicitantesapoderados_numerodoc(
    pidsignomarca bigint,
    ptipo_persona character varying)
  RETURNS text AS
$BODY$
/*
Creado: Susana Escobar Paz Fecha:21/10/2016
crear texto concatenado con valores de numeros
de documentos por idsignomarca y tipo_persona
*/
DECLARE 
    vsigsolicitanteapoderado sigsolicitanteapoderado%ROWTYPE;
    vnumerodoc text;
    vtextosnumerodoc text;
	
BEGIN
vtextosnumerodoc := '';
	FOR vnumerodoc in (
		SELECT numero_documento FROM sigsolicitanteapoderado
		WHERE	idsignomarca = pidsignomarca
			AND tipo_persona = ptipo_persona
			AND estado = 'AC'
			)
	LOOP				      
		    IF vtextosnumerodoc = '' THEN		    
		      IF vnumerodoc is not null then 
			 vtextosnumerodoc := vtextosnumerodoc||vnumerodoc;
		      END IF; 		      		    
		    ELSE		    		    
		      IF vnumerodoc is not null then 
			 vtextosnumerodoc := vtextosnumerodoc||'; '||vnumerodoc;
		      END IF; 		      		   
		    END IF;		
	END LOOP;
	return vtextosnumerodoc::text;
END;
$BODY$
  LANGUAGE plpgsql;


/*
********************************************************************************
Creado: Susana Escobar Paz Fecha:21/10/2016
crear texto concatenado con valores de paises
por idsignomarca y tipo_persona
*/
CREATE OR REPLACE FUNCTION lista_solicitantesapoderados_pais(
    pidsignomarca bigint,
    ptipo_persona character varying)
  RETURNS text AS
$BODY$
/*
Creado: Susana Escobar Paz Fecha:21/10/2016
crear texto concatenado con valores de paises
por idsignomarca y tipo_persona
*/
DECLARE 
    vsigsolicitanteapoderado sigsolicitanteapoderado%ROWTYPE;
    vpais text;
    vtextopais text;
	
BEGIN
vtextopais := '';
	FOR vpais in (
		SELECT pais FROM sigsolicitanteapoderado
		WHERE	idsignomarca = pidsignomarca
			AND tipo_persona = ptipo_persona
			AND estado = 'AC'
			)
	LOOP				      
		    IF vtextopais = '' THEN		    
		      IF vpais is not null then 
			 vtextopais := vtextopais||vpais;
		      END IF; 		      		    
		    ELSE		    		    
		      IF vpais is not null then 
			 vtextopais := vtextopais||', '||vpais;
		      END IF; 		      		   
		    END IF;		
	END LOOP;
	return vtextopais::text;
END;
$BODY$
  LANGUAGE plpgsql;

/*
********************************************************************************
Creado: Susana Escobar Paz Fecha:21/10/2016
crear texto concatenado con valores de paises (descripcion)
por idsignomarca y tipo_persona
*/
CREATE OR REPLACE FUNCTION lista_solicitantesapoderados_paisdesc(
    pidsignomarca bigint,
    ptipo_persona character varying)
  RETURNS text AS
$BODY$
/*
Creado: Susana Escobar Paz Fecha:21/10/2016
crear texto concatenado con valores de paises (descripcion)
por idsignomarca y tipo_persona
*/
DECLARE 
    vsigsolicitanteapoderado sigsolicitanteapoderado%ROWTYPE;
    vpais text;
    vtextopais text;
	
BEGIN
vtextopais := '';
	FOR vpais in (
		SELECT pais FROM sigsolicitanteapoderado
		WHERE	idsignomarca = pidsignomarca
			AND tipo_persona = ptipo_persona
			AND estado = 'AC'
			)
	LOOP				      
		    IF vtextopais = '' THEN		    
		      IF vpais is not null then 
			 vtextopais := vtextopais||(select dom.nombre from dominio dom 
				where dom.dominio = 'pais' 
				and dom.codigo = vpais);
		      END IF; 		      		    
		    ELSE		    		    
		      IF vpais is not null then 
			 vtextopais := vtextopais||', '||(select dom.nombre from dominio dom 
				where dom.dominio = 'pais' 
				and dom.codigo = vpais);
		      END IF; 		      		   
		    END IF;		
	END LOOP;
	return vtextopais::text;
END;
$BODY$
  LANGUAGE plpgsql;

/*
********************************************************************************
Creado: Susana Escobar Paz Fecha:21/10/2016
crear texto concatenado con valores de departamento de persona
por idsignomarca y tipo_persona
*/
CREATE OR REPLACE FUNCTION lista_solicitantesapoderados_dptodesc(
    pidsignomarca bigint,
    ptipo_persona character varying)
  RETURNS text AS
$BODY$
/*
Creado: Susana Escobar Paz Fecha:21/10/2016
crear texto concatenado con valores de departamento de persona
por idsignomarca y tipo_persona
*/
DECLARE 
    vsigsolicitanteapoderado sigsolicitanteapoderado%ROWTYPE;
    vdpto text;
    vtextodpto text;
	
BEGIN
vtextodpto := '';
	FOR vdpto in (
		SELECT solicitud_departamento FROM sigsolicitanteapoderado
		WHERE	idsignomarca = pidsignomarca
			AND tipo_persona = ptipo_persona
			AND estado = 'AC'
			)
	LOOP				      
		    IF vtextodpto = '' THEN		    
		      IF vdpto is not null then 
			 vtextodpto := vtextodpto||(select dom.nombre from dominio dom 
				where dom.dominio = 'lugar_expedicion' 
				and dom.codigo = vdpto);
		      END IF; 		      		    
		    ELSE		    		    
		      IF vdpto is not null then 
			 vtextodpto := vtextodpto||', '||(select dom.nombre from dominio dom 
				where dom.dominio = 'lugar_expedicion' 
				and dom.codigo = vdpto);
		      END IF; 		      		   
		    END IF;		
	END LOOP;
	return vtextodpto::text;
END;
$BODY$
  LANGUAGE plpgsql;


/*
********************************************************************************
Creado: Susana Escobar Paz Fecha:21/10/2016
crear texto concatenado con valores de direccion de persona
por idsignomarca y tipo_persona
*/
CREATE OR REPLACE FUNCTION lista_solicitantesapoderados_direccion(
    pidsignomarca bigint,
    ptipo_persona character varying)
  RETURNS text AS
$BODY$
/*
Creado: Susana Escobar Paz Fecha:21/10/2016
crear texto concatenado con valores de direccion de persona
por idsignomarca y tipo_persona
*/
DECLARE 
    vsigsolicitanteapoderado sigsolicitanteapoderado%ROWTYPE;
    vdireccion text;
    vtextodireccion text;
	
BEGIN
vtextodireccion := '';
	FOR vdireccion in (
		SELECT direccion FROM sigsolicitanteapoderado
		WHERE	idsignomarca = pidsignomarca
			AND tipo_persona = ptipo_persona
			AND estado = 'AC'
			)
	LOOP				      
		    IF vtextodireccion = '' THEN		    
		      IF vdireccion is not null then 
			 vtextodireccion := vtextodireccion||vdireccion;
		      END IF; 		      		    
		    ELSE		    		    
		      IF vdireccion is not null then 
			 vtextodireccion := vtextodireccion||', '||vdireccion;
		      END IF; 		      		   
		    END IF;		
	END LOOP;
	return vtextodireccion::text;
END;
$BODY$
  LANGUAGE plpgsql;


/*
********************************************************************************
Creado: Susana Escobar Paz Fecha:21/10/2016
crear texto concatenado con valores de prioridad extranjera
por idsignomarca 
*/
CREATE OR REPLACE FUNCTION lista_sigprioridadpreferencia_prioridad(
    pidsignomarca bigint
    )
  RETURNS text AS
$BODY$
/*
Creado: Susana Escobar Paz Fecha:21/10/2016
crear texto concatenado con valores de prioridad extranjera
por idsignomarca 
*/
DECLARE     
    vprioridad text;
    vtextoprioridad text;	
BEGIN
vtextoprioridad := '';
	FOR vprioridad in (
		SELECT nombre_numero FROM sigprioridadpreferencia
		WHERE	idsignomarca = pidsignomarca and tipo_prioridad='PRI'
			AND tipo_interes='EXT' AND estado = 'AC'
			)
	LOOP				      
		    IF vtextoprioridad = '' THEN		    
		      IF vprioridad is not null then 
			 vtextoprioridad := vtextoprioridad||vprioridad;
		      END IF; 		      		    
		    ELSE		    		    
		      IF vprioridad is not null then 
			 vtextoprioridad := vtextoprioridad||', '||vprioridad;
		      END IF; 		      		   
		    END IF;		
	END LOOP;
	return vtextoprioridad::text;
END;
$BODY$
  LANGUAGE plpgsql;


/*
********************************************************************************
Creado: Susana Escobar Paz Fecha:21/10/2016
crear texto concatenado con valores de fecha prioridad extranjera
por idsignomarca 
*/
CREATE OR REPLACE FUNCTION lista_sigprioridadpreferencia_fecha(
    pidsignomarca bigint
    )
  RETURNS text AS
$BODY$
/*
Creado: Susana Escobar Paz Fecha:21/10/2016
crear texto concatenado con valores de fecha prioridad extranjera
por idsignomarca 
*/
DECLARE     
    vfecha text;
    vtextofecha text;	
BEGIN
vtextofecha := '';
	FOR vfecha in (
		SELECT fecha FROM sigprioridadpreferencia
		WHERE	idsignomarca = pidsignomarca and tipo_prioridad='PRI'
			AND tipo_interes='EXT' AND estado = 'AC'
			)
	LOOP				      
		    IF vtextofecha = '' THEN		    
		      IF vfecha is not null then 
			 vtextofecha := vtextofecha||vfecha;
		      END IF; 		      		    
		    ELSE		    		    
		      IF vfecha is not null then 
			 vtextofecha := vtextofecha||', '||vfecha;
		      END IF; 		      		   
		    END IF;		
	END LOOP;
	return vtextofecha::text;
END;
$BODY$
  LANGUAGE plpgsql;

/*
********************************************************************************
Creado: Susana Escobar Paz Fecha:21/10/2016
crear texto concatenado con valores de lugar prioridad extranjera
por idsignomarca 
*/
CREATE OR REPLACE FUNCTION lista_sigprioridadpreferencia_pais(
    pidsignomarca bigint
    )
  RETURNS text AS
$BODY$
/*
Creado: Susana Escobar Paz Fecha:21/10/2016
crear texto concatenado con valores de lugar prioridad extranjera
por idsignomarca 
*/
DECLARE     
    vpais text;
    vtextopais text;	
BEGIN
vtextopais := '';
	FOR vpais in (
		SELECT lugar FROM sigprioridadpreferencia
		WHERE	idsignomarca = pidsignomarca and tipo_prioridad='PRI'
			AND tipo_interes='EXT' AND estado = 'AC'
			)
	LOOP				      
		    IF vtextopais = '' THEN		    
		      IF vpais is not null then 
			 vtextopais := vtextopais||vpais;
		      END IF; 		      		    
		    ELSE		    		    
		      IF vpais is not null then 
			 vtextopais := vtextopais||', '||vpais;
		      END IF; 		      		   
		    END IF;		
	END LOOP;
	return vtextopais::text;
END;
$BODY$
  LANGUAGE plpgsql;


/*
********************************************************************************
Creado: Susana Escobar Paz Fecha:21/10/2016
crear texto concatenado con valores de lugar descripcion prioridad extranjera
por idsignomarca 
*/
CREATE OR REPLACE FUNCTION lista_sigprioridadpreferencia_paisdesc(
    pidsignomarca bigint
    )
  RETURNS text AS
$BODY$
/*
Creado: Susana Escobar Paz Fecha:21/10/2016
crear texto concatenado con valores de lugar descripcion prioridad extranjera
por idsignomarca 
*/
DECLARE     
    vpaisdesc text;
    vtextopaisdesc text;	
BEGIN
vtextopaisdesc := '';
	FOR vpaisdesc in (
		SELECT lugar FROM sigprioridadpreferencia
		WHERE	idsignomarca = pidsignomarca and tipo_prioridad='PRI'
			AND tipo_interes='EXT' AND estado = 'AC'
			)
	LOOP			
		    IF vtextopaisdesc = '' THEN		    
		      IF vpaisdesc is not null then 
			 vtextopaisdesc := vtextopaisdesc||(select dom.nombre from dominio dom 
				where dom.dominio = 'pais' 
				and dom.codigo = vpaisdesc);
		      END IF; 		      		    
		    ELSE		    		    
		      IF vpaisdesc is not null then 
			 vtextopaisdesc := vtextopaisdesc||', '||(select dom.nombre from dominio dom 
				where dom.dominio = 'pais' 
				and dom.codigo = vpaisdesc);
		      END IF; 		      		   
		    END IF;		
	END LOOP;
	return vtextopaisdesc::text;
END;
$BODY$
  LANGUAGE plpgsql;



/*
Creado: Susana Escobar Fecha:29/10/2016
Realizar registro en tabla sigregistro
*/
CREATE OR REPLACE FUNCTION crud_sigregistro(
	  pidregistro bigint,
	  pidsignomarca bigint,
	  pidlogtrans bigint,
	  pnumero_registro bigint,
	  pserie character varying,
	  psm bigint,
	  psm_descripcion character varying,
	  pestado_registro character varying,
	  pfecha_ingreso timestamp without time zone,
	  pidtipo_resolucion integer,
	  pdocumento_resolucion text,
	  psigno character varying,
	  ptipo_signo_descripcion character varying,
	  ptipo_genero_descripcion character varying,
	  pclase integer,
	  pnumero_resolucion bigint,
	  pgestion_resolucion integer,
	  pfecha_registro timestamp without time zone,
	  pnumero_publicacion bigint,
	  pfecha_publicacion timestamp without time zone,
	  pnumero_gaceta bigint,
	  pfecha_primer_uso timestamp without time zone,
	  ptitular character varying,
	  pdireccion_titular character varying,
	  ppais_titular character varying,
	  ppais_titular_descripcion character varying,
	  pnombre_apoderado character varying,
	  pdireccion_apoderado character varying,
	  plista_productos text,
	  pobservacion character varying,
	  pestado character varying, 
          operacion integer)
  RETURNS SETOF sigregistro AS
$BODY$
/*
Creado: Susana Escobar Fecha:29/10/2016
Realizar registro en tabla sigregistro
*/
DECLARE
seq_sigregistro bigint;
vregistro sigregistro%ROWTYPE;
BEGIN

if operacion=1 then -- Create
insert into sigregistro(    
  idsignomarca,
  idlogtrans,
  numero_registro,
  serie,
  sm,
  sm_descripcion,
  estado_registro,
  fecha_ingreso,
  idtipo_resolucion,
  documento_resolucion,
  signo,
  tipo_signo_descripcion,
  tipo_genero_descripcion,
  clase,
  numero_resolucion,
  gestion_resolucion,
  fecha_registro,
  numero_publicacion,
  fecha_publicacion,
  numero_gaceta,
  fecha_primer_uso,
  titular,
  direccion_titular,
  pais_titular,
  pais_titular_descripcion,
  nombre_apoderado,
  direccion_apoderado,
  lista_productos,
  observacion,
  estado
)
values(     
  pidsignomarca,
  pidlogtrans,
  pnumero_registro,
  pserie,
  psm,
  psm_descripcion,
  pestado_registro,
  pfecha_ingreso,
  pidtipo_resolucion,
  pdocumento_resolucion,
  psigno,
  ptipo_signo_descripcion,
  ptipo_genero_descripcion,
  pclase,
  pnumero_resolucion,
  pgestion_resolucion,
  pfecha_registro,
  pnumero_publicacion,
  pfecha_publicacion,
  pnumero_gaceta,
  pfecha_primer_uso,
  ptitular,
  pdireccion_titular,
  ppais_titular,
  ppais_titular_descripcion,
  pnombre_apoderado,
  pdireccion_apoderado,
  plista_productos,
  pobservacion,
  pestado
);
seq_sigregistro = (select currval('sec_sigregistro')) ;
for vregistro in (select *
 from sigregistro
 where idregistro = seq_sigregistro
 ) loop
return next vregistro;
end loop;
end if;----fin create


if operacion=2 then -- update
update sigregistro set
	idregistro = pidregistro,
	idsignomarca = pidsignomarca,
	idlogtrans = pidlogtrans,
	numero_registro = pnumero_registro,
	serie = pserie,
	sm = psm,
	sm_descripcion = psm_descripcion,
	estado_registro = pestado_registro,
	fecha_ingreso = pfecha_ingreso,
	idtipo_resolucion = pidtipo_resolucion,
	documento_resolucion = pdocumento_resolucion,
	signo = psigno,
	tipo_signo_descripcion = ptipo_signo_descripcion,
	tipo_genero_descripcion = ptipo_genero_descripcion,
	clase = pclase,
	numero_resolucion = pnumero_resolucion,
	gestion_resolucion = pgestion_resolucion,
	fecha_registro = pfecha_registro,
	numero_publicacion = pnumero_publicacion,
	fecha_publicacion = pfecha_publicacion,
	numero_gaceta = pnumero_gaceta,
	fecha_primer_uso = pfecha_primer_uso,
	titular = ptitular,
	direccion_titular = pdireccion_titular,
	pais_titular = ppais_titular,
	pais_titular_descripcion = ppais_titular_descripcion,
	nombre_apoderado = pnombre_apoderado,
	direccion_apoderado = pdireccion_apoderado,
	lista_productos = plista_productos,
	observacion = pobservacion,
	estado = pestado
    where   idregistro = pidregistro;
  
for vregistro in (select *
 from sigregistro
 where idregistro = pidregistro
 ) loop
return next vregistro;
end loop;
end if;----fin update

if operacion =3 then-- delete
     delete from sigregistro 
      where idregistro = pidregistro;
end if; ---fin delete


if operacion=4 then --read
for vregistro in (select *
 from sigregistro
 where idregistro = pidregistro
 ) loop
return next vregistro;
end loop;
 
end if; ---fin read
       
END;
$BODY$
LANGUAGE plpgsql;



/*
********************************************************
Creado: Susana Escobar Paz Fecha: 31/10/2016
busqueda por numero de registro y serie sm en tabla sigregistro
*/
CREATE OR REPLACE FUNCTION lista_sigregistro_numero(    
    pregistro bigint,
    pserie character varying
    )
  RETURNS SETOF sigregistro AS
$BODY$
/*
Creado: Susana Escobar Paz Fecha: 31/10/2016
busqueda por numero de registro y serie sm en tabla sigregistro
*/
 DECLARE 
    vregistro sigregistro%ROWTYPE; 
BEGIN

for vregistro in select * from sigregistro where numero_registro=pregistro 
	and serie = pserie and estado='AC'
 loop
    return next vregistro;
end loop;
end;
$BODY$
  LANGUAGE plpgsql;

/*
********************************************************************************
Creado: Susana Escobar Paz Fecha:04/11/2016
crear texto concatenado con valores de busqueda 
por idsignomarca en tabla sigtiposigno
*/
CREATE OR REPLACE FUNCTION lista_sigtiposigno_idsignomarca_concatenado(    
    pidsignomarca bigint   
    )
  RETURNS text AS
$BODY$
/*
Creado: Susana Escobar Paz Fecha:04/11/2016
crear texto concatenado con valores de busqueda 
por idsignomarca en tabla sigtiposigno
*/
 DECLARE 
    vtiposigno sigtiposigno%ROWTYPE; 
    vtextotiposigno text;
BEGIN
vtextotiposigno := '';
for vtiposigno in select * from sigtiposigno where idsignomarca=pidsignomarca and estado='AC'
 loop
	 IF vtextotiposigno = '' THEN		
		    vtextotiposigno:= (select dom.nombre from dominio dom 
                    where dom.dominio = 'tipo_signo' 
                    and dom.codigo = vtiposigno.tipo_signo); 
         ELSE
                    vtextotiposigno:= vtextotiposigno||' - '||(select dom.nombre from dominio dom 
                    where dom.dominio = 'tipo_signo' 
                    and dom.codigo = vtiposigno.tipo_signo); 	
                  --  vtextotiposigno:='Mixta';		
	END IF; 
end loop;
	return vtextotiposigno::text;
end;
$BODY$
  LANGUAGE plpgsql;
/*
********************************************************************************
Creado: Levi Laura Fecha:09/11/2016
Creacion de CRUD para la tabala de poderes
*/
CREATE OR REPLACE FUNCTION crud_libropoder(
    pidpoder bigint,
    pidlogtrans bigint,
    ptipo_poder character varying,
    ptipo_tramite character varying,
    pnro_exped bigint,
    pgestion integer,
    pnro_testimonio character varying,
    pfecha_testimonio timestamp without time zone,
    pnotario character varying,
    pnom_confiere_poder character varying,
    pdom_confiere_poder character varying,
    papoderado character varying,
    ppoder_revocado character varying,
    pobs character varying,
    pestado character varying,
    poperacion integer)
  RETURNS SETOF poder AS
$BODY$
/*

Creado: Levi Laura Fecha:09/11/2016
Creacion de CRUD para la tabala de poderes
*/
DECLARE
seq_poder bigint;
vpoder poder%ROWTYPE;
BEGIN
   if poperacion=1 then

       insert into poder(idlogtrans,tipo_poder,tipo_tramite,nro_exped,gestion,nro_testimonio,fecha_testimonio,notario,
                         nom_confiere_poder,dom_confiere_poder, apoderado,poder_revocado,obs,estado)
                  values(
                  pidlogtrans,     
                  ptipo_poder ,                  
                  ptipo_tramite,                 
                  pnro_exped,             
                  pgestion,
                  pnro_testimonio,                
                  pfecha_testimonio,             
                  pnotario,                
                  pnom_confiere_poder,             
                  pdom_confiere_poder,            
                  papoderado,                 
                  ppoder_revocado,                 
                  pobs,                 
                  pestado
                  );   
        seq_poder = (select currval('sec_poder')) ;
        for vpoder in (select *
                      from poder
                       where idpoder = seq_poder
        ) loop
            return next vpoder;
        end loop;               
   
   end if; -- Es Adicion


   if poperacion=2 then -- update
      update poder set
                  idlogtrans=pidlogtrans,     
                  tipo_poder=ptipo_poder ,                  
                  tipo_tramite=ptipo_tramite,                 
                  nro_exped=pnro_exped,             
                  gestion=pgestion,
                  nro_testimonio=pnro_testimonio,                
                  fecha_testimonio=pfecha_testimonio,             
                  notario=pnotario,                
                  nom_confiere_poder=pnom_confiere_poder,             
                  dom_confiere_poder=pdom_confiere_poder,            
                  apoderado=papoderado,                 
                  poder_revocado=ppoder_revocado,                 
                  obs=pobs,                 
                  estado=pestado
            where idpoder=pidpoder;
            -- seq_poder = (select currval('sec_poder')) ;
            for vpoder in (select *
                      from poder
                       where idpoder = 2
             ) loop
               return next vpoder;
             end loop;        
   end if;
   if poperacion =3 then-- delete
       delete from poder where idpoder = pidpoder;
         for vpoder in (select *
                      from poder
                       where idpoder = 1
             ) loop
               return next vpoder;
             end loop;   
       
   end if; ---fin delete
   if poperacion=4 then
    RAISE NOTICE 'Entra aqui ';
            for vpoder in (select *
                      from poder
                       where idpoder = pidpoder
             ) loop
              RAISE NOTICE '%',vpoder.tipo_poder;
               return next vpoder;
             end loop;        

    
   end if; ---fin consulta
   


END;
$BODY$
  LANGUAGE plpgsql;
/*
*****************************************
Creado: Levi Laura Fecha:09/11/2016
Obtiene lista de poderes asociasdos  aun tramite
*/
CREATE OR REPLACE FUNCTION lista_poder_tipotrages(
    ptipo_tramite character varying,
    pnro_exped bigint,
    pgestion integer)
  RETURNS SETOF poder AS
$BODY$
/*
Creado: Levi Laura Fecha:09/11/2016
Obtiene lista de poderes asociasdos  aun tramite
*/
DECLARE 
    vpoderes poder%ROWTYPE; 
BEGIN

for vpoderes in 
         select * from poder 
         where  tipo_tramite=ptipo_tramite
         and nro_exped=pnro_exped
         and gestion=pgestion
         and estado='AC'
 loop
       return next vpoderes;
end loop;
end;
$BODY$
  LANGUAGE plpgsql ;
/*
*********************************************************************************
Creado: Levi Laura Fecha:15/11/2016
Muestra lista  depoderes que se utiliza para la busqueda de poderes
tomando en cuenta el filtro bajo que campo quiere buscar 
*/
CREATE OR REPLACE FUNCTION lista_poder_filtrosbusqueda(pdato character varying,popcion int)
  RETURNS SETOF poder AS
$BODY$
/*
Creado: Levi Laura Fecha:15/11/2016
Muestra lista  depoderes que se utiliza para la busqueda de poderes
tomando en cuenta el filtro bajo que campo quiere buscar 
*/
 DECLARE 
    descripcion character varying;
    idsignomar bigint;
    vlistpoder poder%ROWTYPE;
BEGIN

    
      CASE  
      WHEN popcion=1 THEN
        RAISE NOTICE '1';
          for vlistpoder in
            select * from poder where nro_testimonio=pdato
         loop
              return next vlistpoder;
         end loop;
       WHEN popcion=2 THEN
        RAISE NOTICE '2';
          for vlistpoder in
            select * from poder where fecha_testimonio=to_date(pdato, 'YYYY:MM:DD HH24:MI:SS')
         loop
              return next vlistpoder;
         end loop;
        
       WHEN popcion=3 THEN 
          RAISE NOTICE '3'; 
         for vlistpoder in
            select * from poder where notario=pdato
         loop
              return next vlistpoder;
         end loop;
      WHEN popcion=4 THEN
       RAISE NOTICE '4';
       for vlistpoder in
            select * from poder where nom_confiere_poder=pdato
         loop
              return next vlistpoder;
         end loop;
       WHEN popcion=5 THEN
       RAISE NOTICE '5';
        for vlistpoder in
            select * from poder where dom_confiere_poder=pdato
         loop
              return next vlistpoder;
         end loop;
       WHEN popcion=6 THEN
       RAISE NOTICE '6';
       for vlistpoder in
            select * from poder where apoderado=pdato
         loop
              return next vlistpoder;
         end loop;
       WHEN popcion=7 THEN
        RAISE NOTICE '7';
        for vlistpoder in
            select * from poder where poder_revocado=pdato
         loop
              return next vlistpoder;
         end loop;


       ELSE
        RAISE NOTICE 'other than 1,2,3,4';
      END CASE;
      
END;
$BODY$
  LANGUAGE plpgsql;


/*
********************************************************************************
Creado: Eddy Valero Fecha:22/11/2016
Realizar el crud de la tabla logtrans
*/

CREATE OR REPLACE FUNCTION crud_logtrans(
  pidlogtrans bigint,
  pidusuario bigint,
  pfecha timestamp without time zone,
  operacion integer)
  RETURNS SETOF logtrans AS
$BODY$
/*
Creado: Eddy Valero Fecha:22/11/2016
Realizar el crud de la tabla logtrans
*/

DECLARE
vsec_logtrans bigint;
vlogtrans logtrans%ROWTYPE;
BEGIN
if operacion=1 then -- Create
    insert into logtrans(
      idusuario,
      fecha
      )
      values(
      pidusuario,
      pfecha
      );
  vsec_logtrans = (select currval('sec_logtrans'));
  
  for vlogtrans in (
    select *
    from logtrans
    where idlogtrans = vsec_logtrans
    )
    loop
      return next vlogtrans;
    end loop;
end if;

if operacion=2 then -- update
    update logtrans
      set
        --idlogtrans = pidlogtrans,
        idusuario = pidusuario,
        fecha = pfecha
      where idlogtrans = pidlogtrans;
    for vlogtrans in (
	select *
	from logtrans
	where idlogtrans = vsec_logtrans
    )
    loop
	return next vlogtrans;
    end loop;

end if;
  
if operacion =3 then-- delete
     delete from logtrans where idlogtrans = pidlogtrans;
end if;

if operacion=4 then --read
  for vlogtrans  in
    (
    select * from logtrans
    where  idlogtrans = vsec_logtrans
    )
  loop
  return next vlogtrans;
  end loop;

end if;


END;
$BODY$
  LANGUAGE plpgsql;



/*
********************************************************************************
Creado: Ruben Ramirez Fecha:05/12/2016
Realizar el crud de la tabla de sticker
*/

CREATE OR REPLACE FUNCTION crud_sticker(
    pidsticker bigint,
    pidlogtrans bigint,
    ptipo_tramite character varying,
    pincremento integer,
    pprimer_numero_asignado integer,
    pultimo_numero_asignado integer,
    pgestion integer,
    pestado character varying,
    operacion integer)
  RETURNS SETOF sticker AS
$BODY$
/*
Creado: Ruben Ramirez Fecha:05/12/2016
Realizar el crud de la tabla de sticker
*/

DECLARE
seq_sticker bigint;
vsticker sticker%ROWTYPE;
BEGIN
if operacion = 1 then -- Create
    insert into sticker(
      idlogtrans,
      tipo_tramite,
      incremento,
      primer_numero_asignado,
      ultimo_numero_asignado,
      gestion,
      estado
      )
      values(
      pidlogtrans,
      ptipo_tramite,
      pincremento,
      pprimer_numero_asignado,
      pultimo_numero_asignado,
      pgestion,
      pestado
      );

  seq_sticker = (select currval('sec_sticker')) ;
  
  for vsticker in (
	select *
	from sticker
	where idsticker = seq_sticker
    )
   loop
      return next vsticker;
   end loop;
   
end if;

if operacion = 2 then -- update

    update sticker
      set
        idsticker = pidsticker,
        idlogtrans = pidlogtrans,
        tipo_tramite = ptipo_tramite,
        incremento = pincremento,
        primer_numero_asignado = pprimer_numero_asignado,
        ultimo_numero_asignado = pultimo_numero_asignado,
        gestion = pgestion,
        estado = pestado
      where idsticker = pidsticker;
      
     for vsticker in (
         select *
         from sticker
         where idsticker = pidsticker
     )
     loop
      return next vsticker;
     end loop;

end if;
  
if operacion = 3 then-- delete

     delete from sticker where idsticker = pidsticker;
     
end if;

if operacion = 4 then --read

  for vsticker  in  (
      select * 
      from sticker
      where estado = 'AC'
  )
  loop
  return next vsticker;
  end loop;

end if;

END;
$BODY$
  LANGUAGE plpgsql;

/*

Creado: Levi Laura Fecha:20/12/2016
Obtiene la cantidad de dias habiles  tomando en cuenta  los dias feriados de la tabla nohabiles
*/
CREATE OR REPLACE FUNCTION obtiene_cuentadiasnolaborales_fechas(
    pfecha_inicio timestamp without time zone,
    pfecha_fin timestamp without time zone,
    pidregional bigint)
  RETURNS bigint AS
$BODY$
/*

Creado: Levi Laura Fecha:20/12/2016
Obtiene la cantidad de dias habiles  tomando en cuenta  los dias feriados de la tabla nohabiles
*/
DECLARE
bloqueresul bigint;
vfechacuenta timestamp without time zone;
vcuentadiasnolab integer;
vcont integer;
BEGIN

      vcont:=0;
      vfechacuenta:=pfecha_inicio+ CAST('1 days' AS INTERVAL);
      --raise notice '1el primer vfechacuenta%',vfechacuenta;
       -- Cerea las horas minutos y segundos porque sino no puede comparar  con lso tipos 2017-05-12 00:00:00
      vfechacuenta:= date_trunc('day',vfechacuenta);
     -- raise notice '2el primer vfechacuenta%',vfechacuenta;
    -- Cerea las horas minutos y segundos porque sino no puede comparar  con lso tipos 2017-05-12 00:00:00
         pfecha_fin:= date_trunc('day',pfecha_fin);

      LOOP
    -- some computations
        IF ( vfechacuenta <= pfecha_fin) THEN
    --        raise notice '%',vfechacuenta;

            -- Abria  que descomentar esta parte para cuando el usuario tenga un idregional 
           --select count(*) into vcuentadiasnolab from nolaboral where fecha = vfechacuenta and estado ='AC' and (idregional = 0 or idregional = pidregional);
              select count(*) into vcuentadiasnolab from nolaboral where fecha = vfechacuenta and estado ='AC' and idregional = 1 ;
      --     raise notice 'vcuentadiasnolab::::%',vcuentadiasnolab;
           if(vcuentadiasnolab =0)
           then
                  --Aqui cuenta si no es sabado o domingo  o dia feriado
                vcont:=vcont+1;
           end if;
           vfechacuenta:=vfechacuenta+ CAST('1 days' AS INTERVAL);
           
         ELSE
            EXIT;

          END IF;
       --EXIT WHEN vfechacuenta > pfecha_fin;  -- same result as previous example
     END LOOP;


  return vcont;
END;
$BODY$
  LANGUAGE plpgsql;

/*
********************************************************************************
Descripcion: Realiza la busqueda por marca, solicitante, apoderado y claseniza
Creado: Eddy Valero Fecha:13/12/2016
Modificado: Placido Castro  Fecha: 08/03/2017
Se aumento parametros de salida: parecido,serie_registro, numero, gestion
Modificado: Placido Castro  Fecha: 06/04/2017
Se actualizó el algoritmo

-- Ejemplo de uso:
-- select * from busqueda_por_criterios('SIGN','%TOSHIBA%',0);
-- select * from busqueda_por_criterios('SOLI','%APPLE%',0);
-- select * from busqueda_por_criterios('APOD','%SORUCO%',0);
*/
CREATE OR REPLACE FUNCTION busqueda_por_criterios(
    IN pcampo character varying,
    IN pcriterio character varying,
    IN pclase integer)
  RETURNS TABLE(idsignomarca bigint, sm bigint, numero integer, gestion integer, marca character varying, claseniza integer, parecido integer, fecha_solicitud timestamp without time zone, numero_publicacion bigint, numero_registro bigint, serie_registro character varying, solicitante text, apoderado text, estado character varying, fecha_registro timestamp without time zone, fecha_renovacion timestamp without time zone, titular character varying, sr bigint, gestion_sr integer) AS
$BODY$
	/*
	***************************************************
   Creado: Eddy Valero Fecha:13/12/2016
	Modificado: Placido Castro Fecha: 06/04/2017
	Descripcion: Realiza la busqueda por marca, solicitante, apoderado y claseniza
	*/
DECLARE
_pcriterio character varying;
BEGIN
pcriterio:=upper(pcriterio);
_pcriterio:= substring(pcriterio from 2 for (length(pcriterio)-2));

RETURN query
WITH signo_temp as (
SELECT
     sig.idsignomarca as idsignomarca, 
     sig.sm as sm,
     sig.numero as numero,
     sig.gestion as gestion,    
     sig.signo as marca,
     ssc.numero_clase_niza as claseniza, 
     sig.fecha_ingreso as fecha_solicitud,
     sig.numero_publicacion as numero_publicacion,
     sig.numero_registro as numero_registro,
     sig.serie_registro as serie_registro,
     dom.nombre as estado,
     sig.fecha_registro as fecha_registro, 
     sig.fecha_renovacion as fecha_renovacion, 
     reno.titular as titular, 
     renso.sr as sr, 
     renso.gestion_sr as gestion_sr
     FROM sigsignomarca sig
     JOIN sigsignoclaseniza ssc on (ssc.idsignomarca = sig.idsignomarca and sig.estado = 'AC')
     JOIN dominio dom on (dom.codigo = sig.estado_marca and dom.dominio = 'estado_marca' and dom.estado = 'AC')
     LEFT JOIN sigsolicitanteapoderado sol on (sol.idsignomarca=sig.idsignomarca and sol.tipo_persona = 'SOLI' and sol.estado = 'AC')
     LEFT JOIN sigsolicitanteapoderado apo on (apo.idsignomarca=sig.idsignomarca and apo.tipo_persona = 'APOD' and apo.estado = 'AC')
     FULL OUTER JOIN renrenovacion reno on (reno.numero_renovacion=sig.numero_renovacion  and reno.estado='AC')
     FULL OUTER JOIN rensolicitudrenovacion renso on (renso.idsolicitudrenovacion = reno.idsolicitudrenovacion and renso.estado ='AC')
     WHERE CASE WHEN pcampo='SIGN' THEN 
                                upper(sig.signo) LIKE pcriterio
                 WHEN pcampo='SOLI' THEN
                                upper(sol.nombre_razonsocial) LIKE pcriterio or upper(sol.primer_apellido) LIKE pcriterio or upper(sol.segundo_apellido) LIKE pcriterio
                 WHEN pcampo='APOD' THEN
                                upper(apo.nombre_razonsocial) LIKE pcriterio or upper(apo.primer_apellido) LIKE pcriterio or upper(apo.segundo_apellido) LIKE pcriterio 
                END
                AND CASE WHEN pclase>0 THEN ssc.numero_clase_niza = pclase ELSE 1=1
                END
	  ),
     solicitante_temp as (
			SELECT sol.idsignomarca, 
			string_agg(concat(sol.nombre_razonsocial||' ', sol.primer_apellido||' ', sol.segundo_apellido), '; ') as solicitante 
			FROM signo_temp st 
			LEFT JOIN sigsolicitanteapoderado sol on (sol.idsignomarca = st.idsignomarca)
			WHERE sol.tipo_persona = 'SOLI' and sol.estado = 'AC' 
			GROUP BY sol.idsignomarca
     ),
     apoderado_temp as (
			SELECT apo.idsignomarca, 
			string_agg(concat(apo.nombre_razonsocial||' ', apo.primer_apellido||' ', apo.segundo_apellido), '; ') as apoderado 
			FROM signo_temp st
			LEFT JOIN sigsolicitanteapoderado apo on (apo.idsignomarca = st.idsignomarca)
			WHERE apo.tipo_persona = 'APOD' and apo.estado = 'AC' 
			GROUP BY apo.idsignomarca
     )
     SELECT 
		  st.idsignomarca,
		  st.sm,
		  st.numero,
		  st.gestion,
		  st.marca,
		  st.claseniza,
		  (CASE WHEN pcampo='SIGN' THEN
                            (CASE WHEN levenshtein(_pcriterio,upper(st.marca))=0 THEN 100 
                                            WHEN levenshtein(_pcriterio,upper(st.marca))=1 THEN 75 
                                            ELSE 100/(levenshtein(_pcriterio,upper(st.marca))) 
                                  END)
                          WHEN pcampo='SOLI' THEN
                            (CASE WHEN levenshtein(_pcriterio,upper(sol_temp.solicitante))=0 THEN 100
                                            WHEN levenshtein(_pcriterio,upper(sol_temp.solicitante))=1 THEN 75
                                            ELSE 100/(levenshtein(_pcriterio,upper(sol_temp.solicitante))) 
                                   END)
                          WHEN pcampo='APOD' THEN
                            (CASE WHEN levenshtein(_pcriterio,upper(apo_temp.apoderado))=0 THEN 100
                                            WHEN levenshtein(_pcriterio,upper(apo_temp.apoderado))=1 THEN 75
                                            ELSE 100/(levenshtein(_pcriterio,upper(apo_temp.apoderado))) 
                                  END)
		  END) as parecido,
		  st.fecha_solicitud,
		  st.numero_publicacion,
		  st.numero_registro,
		  st.serie_registro,
		  sol_temp.solicitante,
		  apo_temp.apoderado,
		  st.estado,
		  st.fecha_registro,
		  st.fecha_renovacion,
		  st.titular,
		  st.sr,
		  st.gestion_sr
		  FROM signo_temp st
		  LEFT JOIN  solicitante_temp sol_temp on (sol_temp.idsignomarca = st.idsignomarca)
		  LEFT JOIN  apoderado_temp apo_temp on (apo_temp.idsignomarca = st.idsignomarca)
		  ORDER BY parecido DESC;

END;
$BODY$
  LANGUAGE plpgsql;

/*
--Algoritmo anterior.
CREATE OR REPLACE FUNCTION busqueda_por_criterios(
    IN pcriterio_busqueda character varying,
    IN pcampo character varying,
    IN pclase_niza integer)
  RETURNS TABLE(idsignomarca bigint, sm bigint, marca character varying, claseniza integer, parecido integer, fecha_solicitud timestamp without time zone, numero_publicacion bigint, numero_registro bigint, serie_registro character varying, solicitante text, apoderado text, estado character varying, fecha_registro timestamp without time zone, fecha_renovacion timestamp without time zone, titular character varying, sr integer, gestion_sr integer, numero integer, gestion integer) AS
$BODY$
DECLARE
vquery text;
vquerycreatetemp text;
vconsulta_condicion text;

BEGIN

        DROP TABLE IF EXISTS  prueba;

      -- Creacion de la tabla temporal ----------------------------------------------------------
       IF (pcriterio_busqueda='SOLI') THEN
		vquery = 'and exists (
			select 1 from sigsolicitanteapoderado sol
			where sol.idsignomarca = sig.idsignomarca
			and (UPPER(sol.nombre_razonsocial) like upper('''  || pcampo || ''') or UPPER(sol.primer_apellido) like upper(''' || pcampo ||''') or UPPER(sol.segundo_apellido) like upper('''||pcampo||'''))
			and sol.tipo_persona = ''SOLI''
			and sol.estado = ''AC''
		     )';
	END IF;
	IF (pcriterio_busqueda='APOD') THEN
	RAISE NOTICE 'INGRESO APOD';
		vquery = 'and exists (
			select 1 from sigsolicitanteapoderado sol
			where sol.idsignomarca = sig.idsignomarca
			and (UPPER(sol.nombre_razonsocial) like UPPER('''  || pcampo || ''') or UPPER(sol.primer_apellido) like UPPER(''' || pcampo ||''') or UPPER(sol.segundo_apellido) like UPPER('''||pcampo||'''))
			and sol.tipo_persona = ''APOD''
			and sol.estado = ''AC''
		     )';
	END IF;
	IF (pcriterio_busqueda='SIGN') THEN
		vquery = ' and UPPER(sig.signo) LIKE upper(''' ||pcampo||''')';
	END IF;
	
	IF (pclase_niza>0) THEN
		vquery = vquery || ' and  ssc.numero_clase_niza = ' || pclase_niza || '';
	END IF;


    RAISE NOTICE '%', vquery;

    vquerycreatetemp := 'create temp table prueba as '
		|| ' select sig.idsignomarca idsignomarca, 
     sig.sm sm, 
     sig.signo as marca, 
     ssc.numero_clase_niza as claseniza, 
     (100)::integer as parecido,
     sig.fecha_solicitud as fecha_solicitud, 
     sig.numero_publicacion as numero_publicacion, 
     sig.numero_registro as numero_registro, 
     sig.serie_registro as serie_registro,
     ''''::text solicitante, 
     ''''::text apoderado, 
     sig.estado_marca::character varying(50) as estado, 
     sig.fecha_registro as fecha_registro, 
     sig.fecha_renovacion as fecha_renovacion, 
     reno.titular as titular, 
     reno.numero_renovacion as sr, 
     renso.gestion_sr as gestion_sr,
     sig.numero as numero, 
     sig.gestion as gestion
     from sigsignomarca sig
     join sigsignoclaseniza ssc
     on ssc.idsignomarca = sig.idsignomarca
     full outer join rensolicitudrenovacion renso
     on (renso.sm = sig.sm and renso.estado =''AC'')
     full outer join renrenovacion reno
     on (reno.idsolicitudrenovacion= renso.idsolicitudrenovacion and reno.estado=''AC'')
     where
     sig.estado = ''AC'' ' || vquery
     ;

raise notice '%',vquerycreatetemp;

execute vquerycreatetemp;

raise notice 'creado';
*/
-- Armar las consultas
/*
	IF (pcriterio_busqueda='SOLI') THEN
		vconsulta_condicion = ' upper(solicitante) like ''' || pcampo || '''';
	END IF;
	IF (pcriterio_busqueda='APOD') THEN
		vconsulta_condicion = ' upper(solicitante) like ''' || pcampo || '''';
	END IF;
	IF (pcriterio_busqueda='SIGN') THEN
		vconsulta_condicion = ' upper(marca) like ''' || pcampo || '''';
	END IF;
	IF (pclase_niza>0) THEN
		vconsulta_condicion = vconsulta_condicion || ' and  claseniza = ' || pclase_niza || '';
	END IF;
*/
/*

update prueba pr
set solicitante = (
		select string_agg(concat(nombre_razonsocial, primer_apellido, segundo_apellido), ';') nombres
		from sigsolicitanteapoderado sol
		where sol.idsignomarca = pr.idsignomarca
		and sol.tipo_persona = 'SOLI'
		and sol.estado = 'AC'
		group by sol.idsignomarca
),
apoderado = (
		select string_agg(concat(nombre_razonsocial, primer_apellido, segundo_apellido), ';') nombres
		from sigsolicitanteapoderado sol
		where sol.idsignomarca = pr.idsignomarca
		and sol.tipo_persona = 'APOD'
		and sol.estado = 'AC'
		group by sol.idsignomarca),
estado =(select nombre from dominio dom where dom.estado = 'AC' and dom.codigo = pr.estado and dom.dominio = 'estado_marca')
;
       
return query
select * from prueba;

END;
$BODY$
  LANGUAGE plpgsql;
*/

/*
********************************************************************************
Creado: Eddy Valero Fecha:21/12/2016
Metodo CRUD tabla usuarioetapa
*/
CREATE OR REPLACE FUNCTION crud_usuarioetapa(
    pidusuarioetapa bigint,
    pidlogtrans bigint,
    pidusuario bigint,
    pidetapa bigint,
    pestado character varying,
    operacion integer)
  RETURNS SETOF usuarioetapa AS
$BODY$
/*
Creado: Eddy Valero Fecha:21/12/2016
Metodo CRUD tabla usuarioetapa
*/

DECLARE
vsec_usuarioetapa bigint;
vusuarioetapa usuarioetapa%ROWTYPE;
BEGIN
if operacion=1 then -- Create
    insert into usuarioetapa(
      idlogtrans,
      idusuario,
      idetapa,
      estado
      )
      values(
      pidlogtrans,
      pidusuario,
      pidetapa,
      pestado
      );

  vsec_usuarioetapa = (select currval('sec_usuarioetapa')) ;
  for vusuarioetapa in (
    select *
    from usuarioetapa
    where idusuarioetapa = vsec_usuarioetapa
    )
    loop
      return next vusuarioetapa;
    end loop;
end if;

if operacion=2 then -- update
    update usuarioetapa
      set
        idusuarioetapa = pidusuarioetapa,
	idlogtrans = pidlogtrans,
	idusuario = pidusuario,
	idetapa = pidetapa,
	estado = pestado
      where idusuarioetapa = pidusuarioetapa;
     for vusuarioetapa in (
    select *
    from usuarioetapa
    where idusuarioetapa = vsec_usuarioetapa
    )
    loop
      return next vusuarioetapa;
    end loop;

end if;
  
if operacion =3 then-- delete
     delete from usuarioetapa where idusuarioetapa = pidusuarioetapa;
end if;

if operacion=4 then --read
  for vusuarioetapa  in
    (
    select * from usuarioetapa
    where estado = 'AC'
    )
  loop
  return next vusuarioetapa;
  end loop;

end if;
END;
$BODY$
  LANGUAGE plpgsql;

/*
************************************************
Creado: Levi Laura Fecha:22/12/2016
Lista objeto usuarioetapa dado el idusuario
*/
CREATE OR REPLACE FUNCTION lista_usuarioetapa_idusuario(
    pidusuario bigint
    )
  RETURNS SETOF usuarioetapa AS
$BODY$
/*

Creado: Levi Laura Fecha:22/12/2016
Lista objeto usuarioetapa dado el idusuario
*/
 DECLARE 
    
    vusuarioetapa usuarioetapa%ROWTYPE;
  
  
 BEGIN
   for vusuarioetapa in 
         select idetapa from usuarioetapa where idusuario=pidusuario
    loop
    return next vusuarioetapa;
  end loop;

END;
$BODY$
  LANGUAGE plpgsql;

/*
************************************************
Creado: Susana Escobar Paz  Fecha:11/01/2017
Lista observaciontramite segun id, tipo tramite y etapa ventanilla, para los modulos de Signos, Modificaciones y Renovaciones
*/
CREATE OR REPLACE FUNCTION listar_observaciontramite_ventanilla(
                        pidreferencia bigint,
                        ptipoformulario character varying
                        )
  RETURNS SETOF sigobservaciontramite AS
$BODY$
/*
Creado: Susana Escobar Paz  Fecha:11/01/2017
Lista observaciontramite segun id, tipo tramite y etapa ventanilla, para los modulos de Signos, Modificaciones y Renovaciones
*/
DECLARE 
    reg sigobservaciontramite%ROWTYPE; 
BEGIN

    if ptipoformulario = 'PI100'
        or ptipoformulario = 'PI101'
        or ptipoformulario = 'PI102' then    
         for reg in
                 (
                 select * from sigobservaciontramite
                 where estado = 'AC' and etapa_tramite='VENT'
                 and idsignomarca = pidreferencia
                 order by fecha_observacion desc limit 1
                 )
                 loop
            return next reg;
         end loop;
    end if;
    
    if ptipoformulario = 'PI103' then
        for reg in
                 (
                 select * from modobservaciontramite
                 where estado = 'AC' and etapa_tramite='VENT'
                 and idmodificacion = pidreferencia
                 order by fecha_observacion desc limit 1
                 )
                 loop
            return next reg;
         end loop;
    end if;

    if ptipoformulario = 'PI104' then
        for reg in
                 (
                 select * from renobservaciontramite
                 where estado = 'AC' and etapa_tramite='VENT'
                 and idsolicitudrenovacion = pidreferencia
                 order by fecha_observacion desc limit 1
                 )
                 loop
            return next reg;
         end loop;
    end if;
end;
$BODY$
  LANGUAGE plpgsql;

/*
************************************************
Creado: Susana Escobar Paz  Fecha: 26/01/2017
metodo para obtener el listado de tiposigno concatenado segun id, tipo tramite para Signos, Modificaciones y Renovacion.
*/
CREATE OR REPLACE FUNCTION lista_tiposigno_tramite_concatenado(  
    prefijo character varying,  
    pidsignomarca bigint   
    )
  RETURNS text AS
$BODY$
/*
Creado: Susana Escobar Paz Fecha:26/01/2017
metodo para obtener el listado de tiposigno concatenado segun id, tipo tramite para Signos, Modificaciones y Renovacion.
*/
 DECLARE 
    vtiposigno sigtiposigno%ROWTYPE; 
    vmodtiposigno modtiposigno%ROWTYPE; 
    vrentiposigno modtiposigno%ROWTYPE; 
    vtextotiposigno text;
BEGIN
vtextotiposigno := '';
IF prefijo = 'SIG' THEN
for vtiposigno in select * from sigtiposigno where idsignomarca=pidsignomarca and estado='AC'
 loop
     IF vtextotiposigno = '' THEN       
            vtextotiposigno:= (select dom.nombre from dominio dom 
                    where dom.dominio = 'tipo_signo' 
                    and dom.codigo = vtiposigno.tipo_signo); 
         ELSE
                    vtextotiposigno:= vtextotiposigno||' - '||(select dom.nombre from dominio dom 
                    where dom.dominio = 'tipo_signo' 
                    and dom.codigo = vtiposigno.tipo_signo);    
                  
    END IF; 
end loop;
END IF; 
-------------------------------------------------------------------
IF prefijo = 'MOD' THEN
for vmodtiposigno in select * from modtiposigno where idmodificacion=pidsignomarca and estado='AC'
 loop
     IF vtextotiposigno = '' THEN       
            vtextotiposigno:= (select dom.nombre from dominio dom 
                    where dom.dominio = 'tipo_signo' 
                    and dom.codigo = vmodtiposigno.tipo_signo); 
         ELSE
                    vtextotiposigno:= vtextotiposigno||' - '||(select dom.nombre from dominio dom 
                    where dom.dominio = 'tipo_signo' 
                    and dom.codigo = vmodtiposigno.tipo_signo);     
                  
    END IF; 
end loop;
END IF; 
------------------------------------------------------------------
IF prefijo = 'REN' THEN
for vrentiposigno in select * from rentiposigno where idsolicitudrenovacion=pidsignomarca and estado='AC'
 loop
     IF vtextotiposigno = '' THEN       
            vtextotiposigno:= (select dom.nombre from dominio dom 
                    where dom.dominio = 'tipo_signo' 
                    and dom.codigo = vrentiposigno.tipo_signo); 
         ELSE
                    vtextotiposigno:= vtextotiposigno||' - '||(select dom.nombre from dominio dom 
                    where dom.dominio = 'tipo_signo' 
                    and dom.codigo = vrentiposigno.tipo_signo);     
                 
    END IF; 
end loop;
END IF; 

    return vtextotiposigno::text;
end;
$BODY$
  LANGUAGE plpgsql;
/*
************************************************
Creado: Levi Laura  Fecha: 04/03/2017
Lista los seguimientos ordenados desc por fecha fin , y como entradad el idsignomarca y la etapa
*/

CREATE OR REPLACE FUNCTION obtener_seguimientoidsignomarca_etapa(
    pidsignomarca bigint,
    petapa character varying)
  RETURNS SETOF sigseguimiento AS
$BODY$
/*

Creado: Levi Laura  Fecha: 04/03/2017
Lista los seguimientos ordenados desc por fecha fin , y como entradad el idsignomarca y la etapa
*/
DECLARE
-- vsec_sigseguimiento bigint;
vsigseguimiento sigseguimiento%ROWTYPE;
BEGIN

  for vsigseguimiento in (
    select * from sigseguimiento where idsignomarca=pidsignomarca and etapa <> petapa order by fecha_fin desc
  ) loop
		return next vsigseguimiento;
	end loop;
    
END;
$BODY$
  LANGUAGE plpgsql;
/*
************************************************
Creado: Levi Laura  Fecha: 04/03/2017
Lista los seguimientos de modificcacion ordenados desc por fecha fin , y como entradad el idmodificacion, y etapa
*/


CREATE OR REPLACE FUNCTION obtener_seguimientoidmodificacion_etapa(
    pidmodificacion bigint,
    petapa character varying)
  RETURNS SETOF sigseguimiento AS
$BODY$
/*
Creado: Levi Laura  Fecha: 04/03/2017
Lista los seguimientos de modificcacion ordenados desc por fecha fin , y como entradad el idmodificacion, y etapa
*/

DECLARE
-- vsec_sigseguimiento bigint;
vmodseguimiento sigseguimiento%ROWTYPE;
BEGIN

  for vmodseguimiento in (
    select * from modseguimiento where idmodificacion=pidmodificacion and etapa <> petapa order by fecha_fin desc
  ) loop
		return next vmodseguimiento;
	end loop;
    
END;
$BODY$
  LANGUAGE plpgsql;

/*
************************************************
Creado: Levi Laura  Fecha: 04/03/2017
Lista los seguimientos de renovacion ordenados desc por fecha fin , y como entradad el idsolicitudrenovacion, y etapa
*/


CREATE OR REPLACE FUNCTION obtener_seguimientoidrenovacion_etapa(
    pidrenovacion bigint,
    petapa character varying)
  RETURNS SETOF sigseguimiento AS
$BODY$
/*

Creado: Levi Laura  Fecha: 04/03/2017
Lista los seguimientos de renovacion ordenados desc por fecha fin , y como entradad el idsolicitudrenovacion, y etapa
*/

DECLARE
-- vsec_sigseguimiento bigint;
vrenseguimiento sigseguimiento%ROWTYPE;
BEGIN

  for vrenseguimiento in (
    select * from renseguimiento where idsolicitudrenovacion=pidrenovacion and etapa <> petapa order by fecha_fin desc
  ) loop
		return next vrenseguimiento;
      end loop;
    
END;
$BODY$
  LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION elimina_formulario_pi(IN numero_formulario bigint)
  RETURNS TABLE(codigo_retorno integer, mensaje_retorno character varying) AS
$BODY$
	/*
	***************************************************
	Creado: Placido Castro Fecha: 10/03/2017
	Descripcion: Realiza la eliminacion logica de los formularios (PI-100, PI-101, PI-102, PI-103, PI-104)
	*/
	DECLARE

		-- variables internos
		_numero_formulario bigint;
		_tipoformulario character varying;
		_idsignomarca bigint;
		_idmodificacion bigint;
		_idsolicitudrenovacion bigint;

		-- Variables de retorno
		_codigo_retorno integer;
		_mensaje_retorno character varying;
	
	BEGIN
	
		-- inicializacion de variables
		
		_numero_formulario:= numero_formulario;
		_idsignomarca:= 0;
		_tipoformulario:='';
		_codigo_retorno:= 1;		
		_mensaje_retorno:= 'Formulario eliminado correctamente';
		
		-- validaciones
		
		IF _numero_formulario = 0 THEN
			_mensaje_retorno:='El parametro de entrada no es valido';
			raise exception transaction_rollback;
		END IF;

		-- Determina si el formulario es PI100 o PI101 o PI102 o PI103 o PI104
/*
		IF EXISTS (SELECT * FROM dblink('cnx_sipi', 
						'SELECT f.tipoformulario, f.numeroformulario FROM formulario.formularios f WHERE f.numeroformulario='''||_numero_formulario||''' and f.estado=''IN''')
						AS t1(tipoformulario character varying(15), numeroformulario character varying(40))
						) THEN
						SELECT tipoformulario INTO _tipoformulario  FROM dblink('cnx_sipi', 
						'SELECT f.tipoformulario, f.numeroformulario FROM formulario.formularios f WHERE f.numeroformulario='''||_numero_formulario||''' and f.estado=''IN''')
						AS t1(tipoformulario character varying(15), numeroformulario character varying(40));
						--_valor_retorno:=_tipoformulario;
		ELSE
			_mensaje_retorno:='No existe el formulario solicitado en bdsipi';
			raise exception transaction_rollback;
		END IF;

		IF _tipoformulario = 'PI100' OR _tipoformulario = 'PI101' OR _tipoformulario = 'PI102' THEN

			-- Determina si el formulario existe en tabla sigsignomarca

			IF EXISTS (select s.idsignomarca from sigsignomarca s where s.numero_formulario=_numero_formulario and s.estado='AC') THEN
					select s.idsignomarca INTO _idsignomarca from sigsignomarca s where s.numero_formulario=_numero_formulario and s.estado='AC';
			ELSE
				_mensaje_retorno:='No existe el formulario solicitado en sigsignomarca';
				raise exception transaction_rollback;
			END IF;

			-- Actualizacion del estado en la tabla siglogotipo de la base de datos bdhidraimg

			PERFORM dblink_exec('cnx_hidraimg',
			'UPDATE public.siglogotipo set estado=''NA'' WHERE idsignomarca= '||_idsignomarca||';');

			-- Actualizacion del estado en la tabla formularios de la base de datos bdsipi
			
			PERFORM dblink_exec('cnx_sipi', 
			'UPDATE formulario.formularios set estado=''AC'' WHERE numeroformulario='''||_numero_formulario||''';');
		
			-- Eliminacion logica, 
			-- Actualizacion de estados en las tablas de base de datos bdhidra

			UPDATE sigsignomarca SET estado='NA' WHERE idsignomarca=_idsignomarca;
			UPDATE sigtiposigno SET estado='NA' WHERE idsignomarca=_idsignomarca;
			UPDATE sigsignoclaseniza SET estado='NA' WHERE idsignomarca=_idsignomarca;
			UPDATE sigprioridadpreferencia SET estado='NA' WHERE idsignomarca=_idsignomarca;
			UPDATE sigdirecciondenotificacion SET estado='NA' WHERE idsignomarca=_idsignomarca;
			UPDATE sigsolicitanteapoderado SET estado='NA' WHERE idsignomarca=_idsignomarca;
			UPDATE sigobservaciontramite SET estado='NA'  WHERE idsignomarca=_idsignomarca;
			UPDATE sigseguimiento SET estado='NA' WHERE idsignomarca=_idsignomarca;
			UPDATE sighistorial SET estado='NA' WHERE idsignomarca=_idsignomarca;

			IF _tipoformulario = 'PI102' THEN
				UPDATE siglemacomercial SET estado='NA' WHERE idsignomarca=_idsignomarca;
			END IF;
				
		ELSIF _tipoformulario = 'PI103' THEN

			IF EXISTS (select m.idmodificacion from modmodificacion m where m.nro_formulario=_numero_formulario and m.estado='AC') THEN
					select m.idmodificacion INTO _idmodificacion from modmodificacion m where m.nro_formulario=_numero_formulario and m.estado='AC';
			ELSE
				_mensaje_retorno:='No existe el formulario solicitado en modmodificacion';
				raise exception transaction_rollback;
			END IF;
						
			UPDATE modmodificacion SET estado='NA' WHERE idmodificacion=_idmodificacion;
			UPDATE moddirecciondenotificacion SET estado='NA' WHERE idmodificacion=_idmodificacion;
			UPDATE modsolicitanteapoderado SET estado='NA' WHERE idmodificacion=_idmodificacion;
			UPDATE modtitularlicenciatarioregistrado SET estado='NA' WHERE idmodificacion=_idmodificacion;
			UPDATE modtitularlicenciatarionuevo SET estado='NA' WHERE idmodificacion=_idmodificacion;
			UPDATE modtiposigno SET estado='NA' WHERE idmodificacion=_idmodificacion;
			UPDATE modseguimiento SET estado='NA'  WHERE idmodificacion=_idmodificacion;
			UPDATE modobservaciontramite SET estado='NA' WHERE idmodificacion=_idmodificacion;
			UPDATE modhistorial SET estado='NA' WHERE idmodificacion=_idmodificacion;

		ELSIF _tipoformulario = 'PI104' THEN
*/
			IF EXISTS (select r.idsolicitudrenovacion from rensolicitudrenovacion r where r.nro_formulario=_numero_formulario and r.estado='AC') THEN
					select r.idsolicitudrenovacion INTO _idsolicitudrenovacion from rensolicitudrenovacion r where r.nro_formulario=_numero_formulario and r.estado='AC';
			ELSE
				_mensaje_retorno:='No existe el formulario en rensolicitudrenovacion';
				raise exception transaction_rollback;
			END IF;
						
			UPDATE rensolicitudrenovacion SET estado='NA' WHERE idsolicitudrenovacion=_idsolicitudrenovacion;
			UPDATE rentitularregistrado SET estado='NA' WHERE idsolicitudrenovacion=_idsolicitudrenovacion;
			UPDATE rendirecciondenotificacion SET estado='NA' WHERE idsolicitudrenovacion=_idsolicitudrenovacion;
			UPDATE rensolicitanteapoderado SET estado='NA' WHERE idsolicitudrenovacion=_idsolicitudrenovacion;
			UPDATE renseguimiento SET estado='NA' WHERE idsolicitudrenovacion=_idsolicitudrenovacion;
			UPDATE renobservaciontramite SET estado='NA' WHERE idsolicitudrenovacion=_idsolicitudrenovacion;
			UPDATE renhistorial SET estado='NA'  WHERE idsolicitudrenovacion=_idsolicitudrenovacion;

--		END IF;

		RETURN query SELECT  _codigo_retorno, _mensaje_retorno;

EXCEPTION

	WHEN transaction_rollback THEN
				 BEGIN
							
						RETURN query SELECT  0, _mensaje_retorno;
				 END;

	END;
	$BODY$
  LANGUAGE plpgsql;
/*
**************************************************
Creado: Levi Laura Fecha: 29/03/2017
Descripcion: Adiciona el historial para notificaciones, su ultima etapa es decir cuando el notificador coloca el ultimo estado
	*/
CREATE OR REPLACE FUNCTION modifica_historial_notifica(
    pidnotificacion bigint,
    pid_usuario_notificador bigint,
    pestado character varying,
    pfecha_notifica character varying,
    pfecha_devol character varying)
  RETURNS void AS
$BODY$
/*

Creado: Levi Laura Fecha: 29/03/2017
Descripcion: Adiciona el historial para notificaciones, su ultima etapa es decir cuando el notificador coloca el ultimo estado
	*/
DECLARE

vhistorial character varying;
vnombre character varying;
vprimer_apellido character varying;
vsegundo_apellido  character varying;
vnombre_completo character varying;
vnombre_estado character varying;

BEGIN       
           
            select nombre,primer_apellido,segundo_apellido into vnombre,vprimer_apellido,vsegundo_apellido 
            from usuario 
            where idusuario=pid_usuario_notificador;

            
            if (vprimer_apellido is null)
            then 
           --    RAISE NOTICE 'es nulo ';
               vprimer_apellido:='';

            end if;
            
            if (vsegundo_apellido is null)
            then 
--               RAISE NOTICE 'es nulo ';
               vsegundo_apellido:='';

            end if;
            vnombre_completo:=vnombre||' '||vprimer_apellido||' '||vsegundo_apellido;
            RAISE NOTICE 'nombre es %',vnombre_completo;

             --Saco el nombre del estado de la talba dominio
             select nombre into vnombre_estado from dominio where codigo=pestado and dominio='estado_notificacion';

            
            select historial into vhistorial from notificacion where idnotificacion=pidnotificacion;
            if (vhistorial is null)
            then 
                 --vhistorial:='RECIBIDO:'||vnombre_completo||','||TO_CHAR(NOW(), 'DD-MM-YYYY  HH24:MI:SS'); 
                 vhistorial:=upper(vnombre_estado)||':'||vnombre_completo; 
                 if(pfecha_notifica is not null and  pfecha_notifica != '')
                 then 
                     vhistorial:=vhistorial ||' [Fecha de noti:' ||pfecha_notifica||']';
                 end if;
                 if(pfecha_devol is not null and pfecha_devol != '')
                 then 
                     vhistorial:=vhistorial ||' [Fecha de  devol:' ||pfecha_devol||']';
                 end if;


                 
            else
                 vhistorial:=vhistorial || ';'||upper(vnombre_estado)||':'||vnombre_completo; 
                 if(pfecha_notifica is not null and  pfecha_notifica != '')
                 then 
                     vhistorial:=vhistorial ||' [Fecha de noti:' ||pfecha_notifica||']';
                 end if;
                 if(pfecha_devol is not null and pfecha_devol != '')
                 then 
                     vhistorial:=vhistorial ||' [Fecha de  devol:' ||pfecha_devol||']';
                 end if;
                 
            end if;
            vhistorial:=vhistorial ||','||TO_CHAR(NOW(), 'DD-MM-YYYY  HH24:MI:SS');
            RAISE NOTICE 'historial es %',vhistorial;

            

            update notificacion set id_usuario_notificador=pid_usuario_notificador,
                                      historial=vhistorial
                                      where idnotificacion=pidnotificacion;
                                       


END;
$BODY$
  LANGUAGE plpgsql;

/**************************************************************************************************************

Creado: Levi Laura  Fecha: 04/03/2017
Lista los seguimientos anteriores a un idseguimiento tomand  en cuneta el idsignomarca y la etapa
*/

CREATE OR REPLACE FUNCTION obtener_ultimoseguimientosignomarca_etapa(
    pidsignomarca bigint,
    petapa character varying,
    pidseguimiento bigint)
  RETURNS SETOF sigseguimiento AS
$BODY$
/*

Creado: Levi Laura  Fecha: 04/03/2017
Lista los seguimientos anteriores a un idseguimiento tomand  en cuneta el idsignomarca y la etapa
*/
DECLARE
-- vsec_sigseguimiento bigint;
vsigseguimiento sigseguimiento%ROWTYPE;
BEGIN

  for vsigseguimiento in (
    select * from sigseguimiento where idseguimiento<pidseguimiento and idsignomarca=pidsignomarca and etapa = petapa order by fecha_fin desc
  ) loop
		return next vsigseguimiento;
	end loop;
    
END;
$BODY$
  LANGUAGE plpgsql;
/**************************************************************************************************************

Creado: Placido Castro  Fecha: 07/04/2017
Descripcion: dado una gestion, inserta dias no laborales (sabados y domingos) a la tabla nolaborales
Ejemplo uso: 
select * from inserta_fechas_nolaborales(2017);
*/

CREATE OR REPLACE FUNCTION inserta_fechas_nolaborales(IN gestion integer)
  RETURNS TABLE(codigo_retorno integer, mensaje_retorno character varying) AS
$BODY$
	/*
	*******************************************************************
	Creado: Placido Castro 
	Fecha: 31/03/2017
	Descripcion: Inserta dias sabados y domingos a la tabla nolaborales
	*/
	
DECLARE

		-- variables internos
		_primer_dia_anio date;
		_ultimo_dia_anio date;
		_dias_anio integer;
		_idregional integer;

		-- Variables de retorno
		_codigo_retorno integer;
		_mensaje_retorno character varying;
		
BEGIN

		-- inicializacion de variables

		_codigo_retorno:= 1;		
		_mensaje_retorno:= 'Los dias sabados y domingos fueron generados correctamente';
		_idregional:=0;
		
		-- validaciones
		IF (gestion < 2000 or gestion > 2050) THEN
			_mensaje_retorno:='El parametro de entrada no es valido';
			raise exception transaction_rollback;
		END IF;

		--verificar si ya tienen fechas registradas para la gestion solicitada
		IF EXISTS (SELECT n.fecha
					 FROM nolaboral AS n
					 WHERE EXTRACT (YEAR FROM n.fecha) = gestion AND n.estado = 'AC') THEN
				 _mensaje_retorno:= 'Ya fue generado fechas no laborales para la gestión ' || gestion || '.';
				 RAISE EXCEPTION transaction_rollback;
		END IF;

		-- verificar si hay al menos una regional
      IF NOT EXISTS (SELECT r.idregional FROM regional AS r) THEN
							_mensaje_retorno:= 'No existe ninguna regional.';
      END IF;
      
		_primer_dia_anio:= (gestion||'-01-01')::date;
		_ultimo_dia_anio:= (gestion||'-12-31')::date;
		_dias_anio:= _ultimo_dia_anio-_primer_dia_anio;

		FOR _idregional IN (SELECT r.idregional FROM regional AS r) LOOP

		INSERT INTO nolaboral(
					idregional, 
					idlogtrans, 
					fecha, 
					descripcion, 
					estado
					)
					SELECT
							_idregional,
							1,
							d.fecha, 
							(CASE WHEN EXTRACT('dow' from d.fecha) = '0' THEN 'DOMINGO' ELSE 'SABADO' END):: character varying,
							'AC' 
							FROM
							(SELECT generate_series(0,_dias_anio) + date_trunc('month',_primer_dia_anio)::date as fecha) d
							WHERE EXTRACT('dow' from d.fecha) IN ('0','6');
		END LOOP;

		RETURN query
		SELECT _codigo_retorno,_mensaje_retorno;

EXCEPTION

	WHEN transaction_rollback THEN

		 BEGIN
					
				RETURN query 
				select 0,_mensaje_retorno;
		 END;
END;
$BODY$
  LANGUAGE plpgsql;



/*******************************
Creado: Levi Laura Fecha:02/05/2017
metodo para saver si en sighistorial, sigmodificacion, sigrenovacion, su ultimo registro insertado es igual al 
metido segun dos campos descripcion, y estado_marca_descripcion
*/
CREATE OR REPLACE FUNCTION obtener_ultimo_historial_signo(
    pids bigint,
    pdescripcion character varying,
    pestado_marca_descripcion character varying,
    popcion character varying)
  RETURNS bigint AS
$BODY$
/*
Creado: Levi Laura Fecha:02/05/2017
metodo para saver si en sighistorial, sigmodificacion, sigrenovacion, su ultimo registro insertado es igual al 
metido segun dos campos descripcion, y estado_marca_descripcion
*/
DECLARE
-- vsec_sigseguimiento bigint;
vsighistorial sighistorial%ROWTYPE;
vmodhistorial modhistorial%ROWTYPE;
vrenhistorial renhistorial%ROWTYPE;
vesta bigint;
--vsigsignomarca sigsignomarca%ROWTYPE;
BEGIN

     vesta:=0;

     if(popcion='SIG')
     THEN
       select * into vsighistorial from sighistorial where idsignomarca= pids order by idhistorial desc limit 1;
       if (vsighistorial.descripcion = pdescripcion)
       then
            --RAISE NOTICE 'ENta0';
           if (vsighistorial.estado_marca_descripcion = pestado_marca_descripcion)
           then 
            --   RAISE NOTICE 'ENta1';
                      vesta:=1;
           end if;           
          
        end if;
      END IF;  

      if(popcion='MOD')
     THEN
       select * into vmodhistorial from modhistorial where idmodificacion= pids order by idhistorial desc limit 1;
       if (vmodhistorial.descripcion = pdescripcion)
       then
            --RAISE NOTICE 'ENta0';
           if (vmodhistorial.estado_marca_descripcion = pestado_marca_descripcion)
           then 
            --   RAISE NOTICE 'ENta1';
                      vesta:=1;
           end if;           
          
        end if;
      END IF;  

      if(popcion='REN')
     THEN
       select * into vrenhistorial from renhistorial where idsolicitudrenovacion= pids order by idhistorial desc limit 1;
       if (vrenhistorial.descripcion = pdescripcion)
       then
            --RAISE NOTICE 'ENta0';
           if (vrenhistorial.estado_marca_descripcion = pestado_marca_descripcion)
           then 
            --   RAISE NOTICE 'ENta1';
                      vesta:=1;
           end if;           
          
        end if;
      END IF;  
    -- RAISE NOTICE 'valor%',vesta;
	
	return  vesta;

		
END;
$BODY$
  LANGUAGE plpgsql;

/*******************************************************************
Creado: Levi Laura Fecha:08/05/2017
metodo para ver si ese tramite esta en notificacion en signos
*/
CREATE OR REPLACE FUNCTION obtener_estado_signo_paranotificacion(
    pids bigint
    )
  RETURNS bigint AS
$BODY$
/*
Creado: Levi Laura Fecha:02/05/2017
metodo para ver si ese tramite esta en notificacion en signos
*/
DECLARE
-- vsec_sigseguimiento bigint;
vsigsignomarca sigsignomarca%ROWTYPE;
vesta bigint;
--vsigsignomarca sigsignomarca%ROWTYPE;
BEGIN

     vesta:=0;
     select * into vsigsignomarca from sigsignomarca where idsignomarca= pids limit 1;

     if (vsigsignomarca.estado_marca= 'NOT')
     then
         vesta:=1;
         
          
     end if;
     RAISE NOTICE 'valor%',vesta;
	
	return  vesta;

		
END;
$BODY$
  LANGUAGE plpgsql;
/**********************************************
Creado: Levi Laura Fecha:09/05/2017
metodo para saver si un registro de notificacion esta en estado NOT o DEV
*/

CREATE OR REPLACE FUNCTION obtener_EstadoTramiteNotificacion(
    pbloque integer,
    pid_iusuario_solicitante bigint,
    ptipo_tramite_notificacion character varying,
    pexpediente character varying
    )
  RETURNS bigint AS
$BODY$
/*
Creado: Levi Laura Fecha:09/05/2017
metodo para saver si un registro de notificacion esta en estado NOT o DEV
*/
DECLARE
-- vsec_sigseguimiento bigint;
vcod_estado character varying;
vesta bigint;
--vsigsignomarca sigsignomarca%ROWTYPE;
BEGIN

     vesta:=0;

     select demandante_cod_estado into vcod_estado from notificacion where bloque =pbloque and id_usuario_solicitante=pid_iusuario_solicitante and tipo_tramite_notificacion=ptipo_tramite_notificacion and expediente=pexpediente;
      if(vcod_estado='DEV')
      then
        vesta:=1;
      end if;
      if(vcod_estado='NOT')
      then
        vesta:=1;
      end if;
       RAISE NOTICE 'esta%d',vesta;
      
      return vesta;
		
END;
$BODY$
  LANGUAGE plpgsql;

/**********************************************
Creado: Susana Escobar Paz Fecha:04/08/2017
Metodod que devuelve el ultimo seguimiento
*/
CREATE OR REPLACE FUNCTION lista_seguimiento_finalizado(
    prefijo character varying,    
    pidreferencia bigint)
RETURNS SETOF sigseguimiento AS
$BODY$
DECLARE 
    reg sigseguimiento%ROWTYPE; 
BEGIN
   IF prefijo = 'SIG' THEN
        for reg in
            (
            select * from sigseguimiento
            where fecha_fin is not null and estado = 'AC'
            and idsignomarca = pidreferencia
            order by fecha_recepcion desc limit 1
            )
        loop
            return next reg;
        end loop;
    END IF;
    IF prefijo = 'REN' THEN
        for reg in
            (
            select * from renseguimiento
             where fecha_fin is not null and estado = 'AC'
            and idsolicitudrenovacion = pidreferencia
            order by fecha_recepcion desc limit 1
            )
        loop
            return next reg;
        end loop;
    END IF;
    IF prefijo = 'MOD' THEN
        for reg in
            (
            select * from modseguimiento
             where fecha_fin is not null and estado = 'AC'
            and idmodificacion = pidreferencia
            order by fecha_recepcion desc limit 1
            )
        loop
            return next reg;
        end loop;
    END IF;    
end;
$BODY$
LANGUAGE plpgsql; 