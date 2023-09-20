drop table sigdatoelementoformulario;

drop table moddatoelementoformulario;

drop table rendatoelementoformulario;

--Creacion de sequencias
CREATE SEQUENCE sec_sigdatoelementoformulario INCREMENT 1 MINVALUE 1 START 1 CACHE 1; ALTER TABLE sigdatoelementoformulario ALTER COLUMN iddatoelementoformulario SET DEFAULT NEXTVAL ('sec_sigdatoelementoformulario');
CREATE SEQUENCE sec_moddatoelementoformulario INCREMENT 1 MINVALUE 1 START 1 CACHE 1; ALTER TABLE moddatoelementoformulario ALTER COLUMN iddatoelementoformulario SET DEFAULT NEXTVAL ('sec_moddatoelementoformulario');
CREATE SEQUENCE sec_rendatoelementoformulario INCREMENT 1 MINVALUE 1 START 1 CACHE 1; ALTER TABLE rendatoelementoformulario ALTER COLUMN iddatoelementoformulario SET DEFAULT NEXTVAL ('sec_rendatoelementoformulario');



CREATE TABLE sigdatoelementoformulario(
    iddatoelementoformulario    int8            NOT NULL,
    nombre_tabla                varchar(50),
    idseguimiento               int8,
    idlogtrans                  int8,
    pestania                    varchar(150),
    seccion                     int4,
    fila                        int4,
    tipo_elemento               varchar(50),
    nombre_elemento             text,
    orden                       int4,
    orden_literal               varchar(20),
    idpadre                     int8,
    fechainicio                 timestamp,
    fechafin                    timestamp,
    respuesta                   text,
    opcion_respuesta            text,
    estado                      varchar(2),
    CONSTRAINT "PK2000_1" PRIMARY KEY (iddatoelementoformulario)
)
;

CREATE TABLE moddatoelementoformulario(
    iddatoelementoformulario    int8            NOT NULL,
    nombre_tabla                varchar(50),
    idseguimiento               int8,
    idlogtrans                  int8,
    pestania                    varchar(150),
    seccion                     int4,
    fila                        int4,
    tipo_elemento               varchar(50),
    nombre_elemento             text,
    orden                       int4,
    orden_literal               varchar(20),
    idpadre                     int8,
    fechainicio                 timestamp,
    fechafin                    timestamp,
    respuesta                   text,
    opcion_respuesta            text,
    estado                      varchar(2),
    CONSTRAINT "PK2001_1" PRIMARY KEY (iddatoelementoformulario)
)
;

CREATE TABLE rendatoelementoformulario(
    iddatoelementoformulario    int8            NOT NULL,
    nombre_tabla                varchar(50),
    idseguimiento               int8,
    idlogtrans                  int8,
    pestania                    varchar(150),
    seccion                     int4,
    fila                        int4,
    tipo_elemento               varchar(50),
    nombre_elemento             text,
    orden                       int4,
    orden_literal               varchar(20),
    idpadre                     int8,
    fechainicio                 timestamp,
    fechafin                    timestamp,
    respuesta                   text,
    opcion_respuesta            text,
    estado                      varchar(2),
    CONSTRAINT "PK2002_1" PRIMARY KEY (iddatoelementoformulario)
)
;


--modificar el constraint
alter table sigdatoelementoformulario add constraint datoelementoformulario_sigseguimiento
foreign key (idseguimiento)
references sigseguimiento (idseguimiento);

alter table moddatoelementoformulario add constraint datoelementoformulario_modseguimiento
foreign key (idseguimiento)
references modseguimiento (idseguimiento);

alter table rendatoelementoformulario add constraint datoelementoformulario_renseguimiento
foreign key (idseguimiento)
references renseguimiento (idseguimiento);

--eliminar el constraint
alter table sigdatoelementoformulario drop constraint datoelementoformulario_modseguimiento









select *from datoelementoformulario



/*************************************************************************/

CREATE OR REPLACE FUNCTION obtplantillaventanillatramiteingresado(
						pnumeroformulario character varying,
						ptipoformulario character varying
						)
  RETURNS SETOF datoelementoformulario AS
$BODY$
/*
Creado: Eddy Valero Fecha:19/09/2016
metodo para obtener la plantilla de acuerdo a un numero de formulario
y un tipo de formulario
*/
 DECLARE 
    reg datoelementoformulario%ROWTYPE; 
BEGIN

	if ptipoformulario = 'PI100'
		or ptipoformulario = 'PI101'
		or ptipoformulario = 'PI102' then
	
		for reg in
			(
			select *
			from datoelementoformulario def
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
			from datoelementoformulario def
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

	
end;
$BODY$
  LANGUAGE plpgsql VOLATILE;


select * from modmodificacion where idmodificacion= 26751

select * FROM obtplantillaventanillatramiteingresado(
						'201612058',
						'PI103'
						);

select * FROM obtplantillaventanillatramiteingresado(
						'2016110955',
						'PI103'
						);



						select * from modseguimiento;


						select * from modmodificacion where idmodificacion in (26751, 26662);

select *
from datoelementoformulario def
where def.nombre_tabla = 'modseguimiento'
and def.idseguimiento in (
	select max(md.idseguimiento)
	from modmodificacion mod, modseguimiento md
	where
		md.idmodificacion = mod.idmodificacion
		and mod.nro_formulario = '201612058'
		and md.etapa = 'VENT'
		and md.estado = 'AC'
	)
and estado = 'AC'
order by orden asc
)						
				



/************************************************************************************************************/
delete from etapa

drop table etapa;


delete from usuarioetapa;

drop table usuarioetapa;




drop table usuarioetapa;

CREATE TABLE etapa
(
  idetapa bigint NOT NULL DEFAULT nextval('sec_etapa'::regclass),
  idlogtrans bigint,
  tipo_tramite character varying(4),
  tipo_etapa character varying(4),
  descripcion character varying(150),
  fecha_creacion timestamp without time zone,
  plazo integer,
  estado character varying(2),
  CONSTRAINT "PK120" PRIMARY KEY (idetapa)
);
ALTER SEQUENCE sec_etapa restart WITH 1;

ALTER SEQUENCE sec_usuarioetapa restart WITH 1;


CREATE TABLE usuarioetapa
(
  idusuarioetapa bigint NOT NULL DEFAULT nextval('sec_usuarioetapa'::regclass),
  idlogtrans bigint,
  idusuario bigint NOT NULL,
  idetapa bigint NOT NULL,
  estado character varying(2),
  CONSTRAINT "PK120_1" PRIMARY KEY (idusuarioetapa),
  CONSTRAINT "Refetapa406" FOREIGN KEY (idetapa)
      REFERENCES etapa (idetapa) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "Refusuario407" FOREIGN KEY (idusuario)
      REFERENCES usuario (idusuario) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
;

select * from usuarioetapa

INSERT INTO etapa (tipo_tramite, tipo_etapa, descripcion, fecha_creacion, plazo, estado) VALUES ('REGS',  'VENT', 'Ventanilla (Admisión de la solicitud)', current_timestamp, 1, 'AC');
INSERT INTO etapa (tipo_tramite, tipo_etapa, descripcion, fecha_creacion, plazo, estado) VALUES ('REGS',  'RECA', 'Recaudaciones (Recaudaciones)', current_timestamp, 1, 'AC');
INSERT INTO etapa (tipo_tramite, tipo_etapa, descripcion, fecha_creacion, plazo, estado) VALUES ('REGS',  'EXFS', 'Examen de Forma', current_timestamp, 14, 'AC');
INSERT INTO etapa (tipo_tramite, tipo_etapa, descripcion, fecha_creacion, plazo, estado) VALUES ('REGS',  'PPPR', 'Preparación para prepublicación', current_timestamp, 45, 'AC');
INSERT INTO etapa (tipo_tramite, tipo_etapa, descripcion, fecha_creacion, plazo, estado) VALUES ('REGS',  'PPP', 'Preparación para publicación', current_timestamp, 30, 'AC');
INSERT INTO etapa (tipo_tramite, tipo_etapa, descripcion, fecha_creacion, plazo, estado) VALUES ('REGS',  'OPO', 'Oposicion', current_timestamp, 30, 'AC');
INSERT INTO etapa (tipo_tramite, tipo_etapa, descripcion, fecha_creacion, plazo, estado) VALUES ('REGS',  'ANR', 'Análisis de registrabilidad', current_timestamp, 100, 'AC');
INSERT INTO etapa (tipo_tramite, tipo_etapa, descripcion, fecha_creacion, plazo, estado) VALUES ('REGS',  'ERA', 'Emisión de resolución administrativa', current_timestamp, 10, 'AC');

select * from etapa

truncate table etapa;


drop function crud_usuarioetapa(bigint,bigint,bigint,bigint,character varying,integer )

select * from crud_usuarioetapa(1::bigint,
				1::bigint,
				1::bigint,
				3::bigint,
				'AC'::character varying,
				1::integer);


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
Realizar el crud de la tabla de usuarioetapa
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


select * from logtrans

delete from logtrans

select * from crud_logtrans(1::bigint, 1::bigint, null, 1::integer);

select * from crud_logtrans(1, 1, null, 1);






<!-- bloque en caso de revertir buscar por la extension-->

<p:panelGrid columns="1">
                                                            <p:inputText id="txtnumero" size="5" value="#{examenSignosBean.numeroExpediente}"/>
                                                            <p:outputLabel value="Número" />
                                                        </p:panelGrid>
                                                        <p:panelGrid columns="1"  rendered="#{examenSignosBean.buscarSMRendered}">
                                                            <p:inputText id="txtgestion" size="4" value="#{examenSignosBean.gestionExpediente}" />
                                                            <p:outputLabel value="Gestión"/>
                                                        </p:panelGrid>
                                                        <p:panelGrid columns="1"  rendered="#{examenSignosBean.buscarSMRendered}">
                                                            <p:inputText id="txtextension" size="2" value="#{examenSignosBean.extensionExpediente}"/>
                                                            <p:outputLabel value="Extensión"/>
                                                        </p:panelGrid>



select * 
  from 
  ( 
 select 
  ssm.idsignomarca idsignomarca, 
  ssm.sm as sm, 
  ssm.signo as marca, 
  ssc.numero_clase_niza as claseniza, 
  ssm.fecha_solicitud as fecha_solicitud, 
  ssm.numero_publicacion as numero_publicacion, 
  ssm.numero_registro as numero_registro, 
  obt_solicitantesapoderados_idsignomarca(ssm.idsignomarca, 'SOLI') as solicitante, 
  obt_solicitantesapoderados_idsignomarca(ssm.idsignomarca, 'APOD') as apoderado, 
  (
     select dom.nombre
     from 
      dominio dom 
  where dom.dominio = 'estado_marca' 
  and dom.codigo = ssm.estado_marca 
  ) as estado,
  ssm.fecha_registro  as fecha_registro, 
  ssm.fecha_renovacion  as fecha_renovacion, 
  (
  select ren.titular
  from renrenovacion ren, rensolicitudrenovacion renso
  where ren.idsolicitudrenovacion = renso.idsolicitudrenovacion
  and ren.estado = 'AC'
  and renso.estado = 'AC'
  and renso.sm = ssm.sm
  ) as titular, 
  (
  select ren.numero_renovacion
  from renrenovacion ren, rensolicitudrenovacion renso
  where ren.idsolicitudrenovacion = renso.idsolicitudrenovacion
  and ren.estado = 'AC'
  and renso.estado = 'AC'
  and renso.sm = ssm.sm
  ) as sr, 
  (
  select ren.gestion_renovacion
  from renrenovacion ren, rensolicitudrenovacion renso
  where ren.idsolicitudrenovacion = renso.idsolicitudrenovacion
  and ren.estado = 'AC'
  and renso.estado = 'AC'
  and renso.sm = ssm.sm
  )
  as gestion_sr 
 from 
  sigsignomarca ssm, 
  sigsignoclaseniza ssc 
  where 
  ssc.idsignomarca = ssm.idsignomarca 
  and ssm.estado = 'AC'
  and ssc.estado = 'AC'
  )  consulta
  where 
	consulta.marca like '%PAN%'



alter table rentiposigno
drop sequence sec_rentiposigno


obtener
listar
eliminar
guardar
actualizar

busqueda de signos

select
	ssm.idsignomarca as idsignomarca,
	ssm.sm as sm,
	ssm.signo as marca,
	ssc.numero_clase_niza as clase,
	ssm.fecha_solicitud as fecha_solicitud,
	ssm.numero_publicacion as numero_publicacion,
	ssm.numero_registro as numero_registro,
	obt_solicitantesapoderados_idsignomarca(sss.idsignomarca, 'SOLI') as solicitante,
	'apoderado' apoderado,
	(
	select dom.nombre
	from
		dominio dom
	where dom.dominio = 'estado_marca'
		and dom.codigo = ssm.estado_marca	
	) as estado,
	ssm.fecha_registro fecha_registro,
	ssm.fecha_renovacion fecha_renovacion,
	'SR Titular' titular,
	'SR' sr,
	1990 gestion_sr
from
sigsignomarca ssm, 
sigsignoclaseniza ssc
where ssm.signo like '%PINK%PANTHER%'
and ssc.idsignomarca = ssm.idsignomarca
and solicitante like  '%cadena%'





--------------------------------------------------
select *
from
(
select
	ssm.sm,
	ssm.signo,
	ssc.numero_clase_niza,
	ssm.fecha_solicitud,
	ssm.numero_publicacion,
	ssm.numero_registro,
	(select * from current_time) as tiempo,
	'cadena cualquiera' as solicitante,
	'apoderado' apoderado
from sigsignomarca ssm, sigsignoclaseniza ssc
where ssm.signo like '%PINK%PANTHER%'
and ssc.idsignomarca = ssm.idsignomarca
) resultado
where
solicitante like '%soli%'







CREATE OR REPLACE FUNCTION crud_siglogotipo(
    pidlogotipo bigint,
    pidsignomarca bigint,
    pidlogtrans bigint,
    purllogotipo character varying,
    pprincipal boolean,
    pnombre_archivo character varying,
    pextension_archivo character varying,
    pestado character varying,
    operacion integer)
  RETURNS SETOF siglogotipo AS
$BODY$
/*
Creado: Eddy Valero Fecha: 11/10/2016
Realizar el crud de la tabla siglogotipo
*/
DECLARE
  vsec_siglogotipo bigint;
  vsiglogotipo siglogotipo%ROWTYPE;
BEGIN

  if operacion = 1 then
      insert into siglogotipo(
        idsignomarca,
        idlogtrans,
        urllogotipo,
        principal,
        nombre_archivo,
        extension_archivo,
        estado
        )
      values(
        pidsignomarca,
        pidlogtrans,
        purllogotipo,
        pprincipal,
        pnombre_archivo,
        pextension_archivo,
        pestado
        );

      vsec_siglogotipo = (select currval('sec_siglogotipo'));

      for vsiglogotipo in (
            select * from siglogotipo
            where idlogotipo = vsec_siglogotipo
            )
      loop
              return next vsiglogotipo;
      end loop;

  end if;

  if operacion = 2 then
      update siglogotipo
        set
            -- idlogotipo = pidlogotipo,
            idsignomarca = pidsignomarca,
            idlogtrans = pidlogtrans,
            urllogotipo = purllogotipo,
            principal = pprincipal,
            nombre_archivo = pnombre_archivo,
            extension_archivo = pextension_archivo,
            estado = pestado
      where idlogotipo = pidlogotipo
        and estado = 'AC';;

      for vsiglogotipo in (
            select * from siglogotipo
            where idlogotipo = pidlogotipo
            )
      loop
              return next vsiglogotipo;
      end loop;

  end if;

  if operacion = 3 then
      delete from siglogotipo
            where idlogotipo = pidlogotipo;
  end if;

  if operacion=4 then --read
      for vsiglogotipo in (
            select * from siglogotipo
            where idlogotipo = pidlogotipo
            )
      loop
              return next vsiglogotipo;
      end loop;
 
  end if; ---fin read
        
END;

$BODY$
  LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION crud_sigimagen(
    pidimagen bigint,
    pidlogotipo bigint,
    pidlogtrans bigint,
    pimagen bytea,
    operacion integer)
  RETURNS SETOF sigimagen AS
$BODY$
/*
Creado: Eddy Valero Fecha: 11/10/2016
Realizar el crud de la tabla imagen
*/
DECLARE
  vsec_sigimagen bigint;
  vsigimagen sigimagen%ROWTYPE;
BEGIN

  if operacion = 1 then
      insert into sigimagen(
        idlogotipo,
        idlogtrans,
        imagen)
      values(
        pidlogotipo,
        pidlogtrans,
        pimagen
        );

      vsec_sigimagen = (select currval('sec_sigimagen'));

      for vsigimagen in (
            select * from sigimagen
            where idimagen = vsec_sigimagen
            )
      loop
              return next vsigimagen;
      end loop;

  end if;

  if operacion = 2 then
      update sigimagen
        set
            -- idsigimagen = pidimagen,
            idlogotipo = pidlogotipo,
            idlogtrans = pidlogtrans,
            imagen = pimagen
      where idsigimagen = pidimagen;

      for vsigimagen in (
            select * from sigimagen
            where idimagen = pidimagen
            )
      loop
              return next vsigimagen;
      end loop;

  end if;

  if operacion = 3 then
      delete from sigimagen
            where idimagen = pidimagen;
  end if;

  if operacion=4 then --read
      for vsigimagen in (
            select * from sigimagen
            where idimagen = pidimagen
            )
      loop
              return next vsigimagen;
      end loop;
 
  end if; ---fin read
        
END;

$BODY$
  LANGUAGE plpgsql;




/*******************************************************************************/
CREATE OR REPLACE FUNCTION crud_sigsignoclaseniza(
  pidsignoclaseniza bigint,
  pidsignomarca bigint,
  pidclaseniza bigint,
  pidlogtrans bigint,
  pnumero_clase_niza bigint,
  plista_producto text,
  pestado character varying,
  operacion integer)
  RETURNS SETOF sigsignoclaseniza AS
$BODY$
/*
********************************************************************************
Creado: Eddy Valero Fecha:10/09/2016
Realizar registro en tabla sigsignoclaseniza
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
          estado
        )
        values(  
          pidsignomarca,
          pidclaseniza,
          pidlogtrans,
          pnumero_clase_niza,
          plista_producto,
          pestado
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
              estado = pestado
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
  operacion integer)

RETURNS SETOF sigprioridadpreferencia AS
$BODY$

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
              estado
          )
          values(
              pidprioridadpreferencia,
              pidsignomarca,
              pidlogtrans,
              ptipo_prioridad,
              ptipo_interes,
              pnombre_numero,
              pfecha,
              plugar,
              pestado
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
              estado = pestado
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
Creado: Eddy Valero Fecha:15/09/2016
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
Creado: Chano Rojas Fecha:30/08/2016
Realizar el crud de la tabla de crud_rensolicitudrenovacion
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
********************************************************************************
Creado: Eddy Valero Fecha:03/10/2016
Realizar registro en tabla sigdirecciondenotificacion
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
Creado: Eddy Valero Fecha:19/09/2016
metodo para obtener la plantilla de acuerdo a un numero de formulario
y un tipo de formulario
*/

CREATE OR REPLACE FUNCTION obtplantillaventanillatramiteingresado(
						pnumeroformulario character varying,
						ptipoformulario character varying
						)
  RETURNS SETOF datoelementoformulario AS
$BODY$
/*
Creado: Eddy Valero Fecha:19/09/2016
metodo para obtener la plantilla de acuerdo a un numero de formulario
y un tipo de formulario
*/
 DECLARE 
    reg datoelementoformulario%ROWTYPE; 
BEGIN

	if ptipoformulario = 'PI100'
		or ptipoformulario = 'PI101'
		or ptipoformulario = 'PI102' then
	
		for reg in
			(
			select *
			from datoelementoformulario def
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
end;
$BODY$
  LANGUAGE plpgsql VOLATILE;

select * from obtplantillaventanillatramiteingresado('201608313', 'PI100');



CREATE OR REPLACE FUNCTION obtenerfechasistema(
	pidregional bigint
)
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


select * from obtenerfechasistema(1);

drop function obenerfechasistema(bigint);
select cast(fecha as date) from fechasistema

select (cast(fecha as date) || ' ' || to_char(current_timestamp , 'HH12:MI:SS'))::timestamp from fechasistema;




*******************************************************************************************

CREATE OR REPLACE FUNCTION public.crud_observacion_tramite(
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
 *
 * autor: Eddy Valero
 * fecha: 14/09/2016
 * descripcion: obtener todos los registros de una plantilla de seguimiento.
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


  select * from crud_observacion_tramite(
    1,
    'SIG',
    1,
    1,
    1,
    TRUE,
    NULL,
    'VENT',
    'COSA',
    1);






Viernes 09/09/2016
----------------------------------------------


CREATE OR REPLACE FUNCTION obtener_seguimiento_smmarca(
	psm bigint,
	petapa character varying,
	pprefijo character varying
    )
  RETURNS SETOF sigseguimiento AS
$BODY$
/*
********************************************************
Creado: Eddy Valero Fecha:09/09/2016
Obtener registro de seguimiento
*/
DECLARE
-- vsec_sigseguimiento bigint;
vsigseguimiento sigseguimiento%ROWTYPE;
vsigsignomarca sigsignomarca%ROWTYPE;
BEGIN

	select marca.* into vsigsignomarca
	from sigsignomarca
	where sigsignomarca.sm = psm;


	for vsigseguimiento in (
		select *
		from sigseguimiento
		where etapa = petapa
			and idsignomarca = marca.idsignomarca
			and estado = 'AC'
			order by 1 desc
		) loop
		return next vsigseguimiento;
	end loop;

	exception
		when others then
		RAISE EXCEPTION 'Falló la orden SQL: %. El error fue: %',sql,SQLERRM;
		
END;
$BODY$
  LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION obtener_seguimiento_etapa(
	pidsignomarca bigint,
	petapa character varying,
	pprefijo character varying
    )
  RETURNS SETOF sigseguimiento AS
$BODY$
/*
********************************************************
Creado: Eddy Valero Fecha:09/09/2016
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
			and estado = 'AC'
			order by 1 desc
		) loop
		return next vsigseguimiento;
	end loop;

	exception
		when others then
		RAISE EXCEPTION 'Falló la orden SQL: %. El error fue: %',sql,SQLERRM;
		
END;
$BODY$
  LANGUAGE plpgsql;

select * from dominio where dominio like 'estado_marca'



select *from sigseguimiento;
select * from datoelementoformulario
delete from datoelementoformulario;
delete from sigseguimiento;
se debe obtener el seguimiento de la ultima etapa

select * from sigseguimiento
where etapa = 'VEN'
and estado = 'AC'
order by 1 desc






Jueves 08/09/2016
----------------------------------------------
CREATE OR REPLACE FUNCTION crud_usuario(
	pidusuario bigint,
	pidlogtrans bigint,
	pnombre character varying,
	pprimer_apellido character varying,
	psegundo_apellido character varying,
	plogin character varying,
	ppassword character varying,
	pcargo character varying,
	pidregional integer,
	pnumero_documento character varying,
	ptipo_documento character varying,
	plugar_expedicion character varying,
	pcorreo_electronico character varying,
	pestado character varying,
	poperacion integer
    )
  RETURNS SETOF usuario AS
$BODY$
/*
********************************************************
Creado: Eddy Valero Fecha:08/09/2016
Realizar registro en tabla usuario
*/
DECLARE
vseq_usuario bigint;
vusuario usuario%ROWTYPE;
BEGIN

	if poperacion=1 then -- Create
		insert into usuario
			(idlogtrans, nombre, primer_apellido, segundo_apellido, login, contrasenia, cargo, idregional, numero_documento, tipo_documento, lugar_expedicion, correo_electronico, estado)
		values
			(pidlogtrans, pnombre, pprimer_apellido, psegundo_apellido, plogin, ppassword, pcargo, pidregional, pnumero_documento, ptipo_documento, plugar_expedicion, pcorreo_electronico, pestado);
			
		vseq_usuario = (select currval('sec_usuario')) ;
		for vusuario in (
			select *
			from usuario
			where idusuario = vseq_usuario
			) loop
			return next vusuario;
		end loop;
	end if;----fin create

/*
	if operacion=2 then -- update
	update usuario set
			idmodificacion = pidmodificacion,
			idlogtrans = pidlogtrans,
			sigla = psigla,
			numero = pnumero,
			gestion = pgestion,
			sm = psm,
			fecha_ingreso = pfecha_ingreso,
			nro_formulario = pnro_formulario,
			oficina = poficina,
			numero_registro = pnumero_registro,
			serie_registro = pserie_registro,
			numero_renovacion = pnumero_renovacion,
			serie_renovacion = pserie_renovacion,
			numero_publicacion = pnumero_publicacion,
			signo_registrado = psigno_registrado,
			clase_registrado = pclase_registrado,
			tipo_genero_registrado = ptipo_genero_registrado,
			tipo_marca_registrado = ptipo_marca_registrado,
			marca_acomp = pmarca_acomp,
			reg_lc_registrado = preg_lc_registrado,
			serie_lc_registrado = pserie_lc_registrado,
			fecha_lc_registrado = pfecha_lc_registrado,
			nuevo_domicilio = pnuevo_domicilio,
			nueva_nacionalidad = pnueva_nacionalidad,
			nuevo_departamento = pnuevo_departamento,
			luinicio = pluinicio,
			lu_fin = plu_fin,
			fecha_ultima_operacion = pfecha_ultima_operacion,
			usuario = pusuario,
			titular_signo = ptitular_signo,
			nacionalidad_signo = pnacionalidad_signo,
			departamento_signo = pdepartamento_signo,
			domicilio_signo = pdomicilio_signo,
			telefono_signo = ptelefono_signo,
			fax_signo = pfax_signo,
			email_signo = pemail_signo,
			tipo_modificacion = ptipo_modificacion,
			estado_modificacion = pestado_modificacion,
			ubicacion = pubicacion,
			lista_producto = plista_producto,
			estado = pestado,
			nro_recibo = pnro_recibo,
			serie_recibo =pserie_recibo
			
		where 	idmodificacion = pidmodificacion;
		
	--seq_usuario = (select currval('sec_usuario')) ;
	for vmodificacion in (select *
	 from usuario
	 where idmodificacion = pidmodificacion
	 ) loop
	return next vmodificacion;
	end loop;
	end if;----fin update

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
  LANGUAGE plpgsql ;


select * from crud_usuario(
	null,
	1,
	'jose',
	'quiroga',
	'parisaca',
	'eduardo',
	'xxxx',
	'cargo',
	null,
	'458963255',
	'CI',
	'LPZ',
	'correonet@gmail.com',
	'AC',
	1
);

  select *from usuario;



----------------------------------------------
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
	idsignomarca, idusuario, idlogtrans, sm, numero_publicacion, numero_registro, serie_registro, etapa, fecha_recepcion, fecha_fin, plazo_real, observacion, editable, estado
	)
  values(
	pidsignomarca, pidusuario, pidlogtrans, psm, pnumero_publicacion, pnumero_registro, pserie_registro, petapa, pfecha_recepcion, pfecha_fin, pplazo_real, pobservacion, peditable, pestado
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




select *
from crud_sigseguimiento(
	null,
	1,
	1,
	1,
	2016000110,
	null,
	null,
	null,
	'VEN',
	NULL,
	NULL,
	10,
	'OBSERVACION',
	TRUE,
	'AC',
	'SIG',
	1
  )

select *from sigsignomarca;

delete from sigseguimiento;

select * from sigseguimiento;
  

Miercoles 07/09/2016
-------------------------------------------------------------
CREATE OR REPLACE FUNCTION crud_datoelementoformulario(
	  piddatoelementoformulario bigint,
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
	  operacion integer
  )
  RETURNS SETOF datoelementoformulario AS
$BODY$
/*
Creado: Eddy Valero Fecha:07/09/2016
Realizar el crud de la tabla de datoelementoformulario
*/
DECLARE
vid bigint;
vsec_datoelementoformulario bigint;
vdatoelementoformulario datoelementoformulario%ROWTYPE;
BEGIN


if operacion=1 then -- Create

insert into datoelementoformulario(
	idseguimiento, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, orden_literal, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado
	)
  values(
	pidseguimiento, pidlogtrans, ppestania, pseccion, pfila, ptipo_elemento, pnombre_elemento, porden, porden_literal, pidpadre, pfechainicio, pfechafin, prespuesta, popcion_respuesta, pestado
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


  if operacion!=4 then 
	vsec_datoelementoformulario = (select currval('sec_datoelementoformulario')) ;
	for vdatoelementoformulario in (select *
	 from datoelementoformulario
	 where iddatoelementoformulario = vsec_datoelementoformulario
	 ) loop
	return next vdatoelementoformulario;
	end loop;

  end if;


END;
$BODY$
  LANGUAGE plpgsql;


select * from sigseguimiento;

  select * from crud_datoelementoformulario(
	  NULL,
	  NULL,
	  1,
	  'pestania',
	  1,
	  1,
	  'TEH2',
	  'nombreelemento',
	  1,
	  NULL,
	  NULL,
	  NULL,
	  NULL,
	  NULL,
	  NULL,
	  'AC',
	  1
  );




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


  if operacion!=4 then 
	vsec_datoelementoformulario = (select currval('sec_datoelementoformulario')) ;
	for vdatoelementoformulario in (select *
	 from datoelementoformulario
	 where iddatoelementoformulario = vsec_datoelementoformulario
	 ) loop
	return next vdatoelementoformulario;
	end loop;

  end if;


END;
$BODY$
  LANGUAGE plpgsql;



Martes 06/09/2016
-------------------------------------------------------------
CREATE OR REPLACE FUNCTION listar_elementosventanilla_codigo(pcodigo character varying)
  RETURNS SETOF elementoformulariotramite AS
$BODY$
 DECLARE 
    velementoformulariotramite elementoformulariotramite%ROWTYPE; 
BEGIN

	for velementoformulariotramite in (
		select * from elementoformulariotramite e
		where exists (
			select * from formulariotramite f
			where f.estado = 'AC'
			     and f.idformulariotramite = e.idformulariotramite
			)
		order by orden asc	
		)
	loop
		return next velementoformulariotramite;
	end loop;
end;
$BODY$
  LANGUAGE plpgsql;

select * from listar_elementosventanilla_codigo('SM');






lunes 05/09/2016
-------------------------------------------------------------
CREATE OR REPLACE FUNCTION obtplantillaventanillaseguimiento(pidseguimiento bigint)
  RETURNS SETOF datoelementoformulario AS
$BODY$
 DECLARE 
    reg datoelementoformulario%ROWTYPE; 
BEGIN

for reg in
	(
	select * from datoelementoformulario
	where estado = 'AC'
	and idseguimiento = pidseguimiento
	order by orden asc
	)
loop
    return next reg;
end loop;
end;
$BODY$
  LANGUAGE plpgsql VOLATILE;




CREATE OR REPLACE FUNCTION generarplantillaventanilla(
		pidseguimiento bigint,
		pidformulariotramite bigint
		)
  RETURNS int AS
$BODY$
   DECLARE 
	vnroregistros int;
	velementoformulariotramite elementoformulariotramite%rowtype; 
	vidlogtrans bigint;
	vidseguimiento bigint;
BEGIN

	SELECT count(*)
	into vnroregistros
	FROM datoelementoformulario
	where idseguimiento = pidseguimiento
	and estado = 'AC';

        select pidseguimiento into vidseguimiento;
	
	--si existen registro no se realiza la insercion de registros
	--si no existen registros se realiza la inserción de registros
	if vnroregistros > 0 then
		raise notice 'existen registros';
	else
		raise notice 'no existen registros';
		-- clonar registros desde la tabla de elementoformulariotramite

		INSERT INTO datoelementoformulario(idseguimiento, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, orden_literal, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado)
						SELECT vidseguimiento, vidlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, orden_literal, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, 'AC'
						FROM elementoformulariotramite WHERE idformulariotramite = pidformulariotramite order by orden asc;
		 return 1;

	end if;
	
	 RETURN vnroregistros;

	
END;
$BODY$
LANGUAGE plpgsql;


-- DROP FUNCTION generarplantillaventanilla(bigint,bigint)
-- select * from generarplantillaventanilla(1,1);

-- select generarplantillaventanilla(1,1) as table;


-- select * from datoelementoformulario;
-- delete from datoelementoformulario;



/*********************************************************************************************/

Sabado 03/09/2016


CREATE OR REPLACE FUNCTION generarplantillaventanilla(
		pidseguimiento bigint,
		pidformulariotramite bigint
		)
  RETURNS int AS
$BODY$
   DECLARE 
	vnroregistros bigint;
	velementoformulariotramite elementoformulariotramite%rowtype; 
BEGIN

	SELECT count(*)
	into vnroregistros
	FROM datoelementoformulario
	where idseguimiento = pidseguimiento;
	
	

	if vnroregistros > 0 then
		raise notice 'existen registros';
	else
		raise notice 'no existen registros';
		-- clonar registros desde la tabla de registros

		for velementoformulariotramite in (
						  select * 
						  from elementoformulariotramite
						  where idformulariotramite = pidformulariotramite
						  order by orden asc
						  ) loop
		raise notice '%', velementoformulariotramite.idelementoformulariotramite;
		-- raise notice 'uno';
		
		end loop;
		return 1;

	end if;
	
	
	RETURN vnroregistros;


	
END;
$BODY$
LANGUAGE plpgsql;



select * from generarplantillaventanilla(1,1);

/******************************************************/






viernes 02/09/2016




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



SELECT * FROM crud_siglemacomercial(
	NULL,
	2,
	'signo padre',
	201600000000,
	'SM-2016-0000',
	152632,
	'C',
	'12/05/2016',
	'AC',
	'REGISTRAR'
	);





-- Lunes 29/08/2016
-- DROP FUNCTION reg_sigsolicitanteapoderado

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
/**
 *
 * autor: Eddy Valero
 * fecha: 29/08/2016
 * descripcion: registrar siglogotipo
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



select * from reg_sigdireccionnotificacion(
  2,
  1,
  'LPZ',
  'ZONA BARRIO',
  'AVENIDA CALLE',
  '125',
  'LAS LOMAS',
  'A1',
  'SE',
  'CERCA DE LA PLAZA',
  'CORREONET@gmail.com',
  '2859632',
  '7859632',
  'AC'
);



DROP FUNCTION reg_sigdireccionnotificacion(bigint,bigint,character varying,character varying,character varying,character varying,character varying,character varying,character varying,character varying,character varying,character varying,character varying,character varying)
/****************************************************/

-- Lunes 29/08/2016
-- DROP FUNCTION reg_sigsolicitanteapoderado

CREATE OR REPLACE FUNCTION reg_sigprioridadpreferencia (
  pidsignomarca bigint,
  pidlogtrans bigint,
  ptipo_prioridad character varying(4),
  ptipo_interes character varying(4),
  pnombre_numero character varying(150),
  pfecha timestamp without time zone,
  plugar character varying(100),
  pestado character varying(2)
  )
RETURNS SETOF sigprioridadpreferencia AS
$BODY$
/**
 *
 * autor: Eddy Valero
 * fecha: 29/08/2016
 * descripcion: registrar siglogotipo
 */
DECLARE
	vsec_sigprioridadpreferencia bigint;
	vsigprioridadpreferencia sigprioridadpreferencia%ROWTYPE;
BEGIN

	insert into sigprioridadpreferencia
	(idsignomarca, idlogtrans, tipo_prioridad, tipo_interes, nombre_numero, fecha, lugar, estado)
	values
	(pidsignomarca, pidlogtrans, ptipo_prioridad, ptipo_interes, pnombre_numero, pfecha, plugar, pestado);

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



select * from reg_sigprioridadpreferencia(2, 1, 'OA', 'OA', '125632', NULL, '', 'AC');


/******************************************************************************/
-- Lunes 29/08/2016
-- DROP FUNCTION reg_sigsolicitanteapoderado

CREATE OR REPLACE FUNCTION reg_sigsolicitanteapoderado(
  pidsignomarca bigint,
  pidlogtrans bigint,
  ptipo_titular character varying(50),
  ptipo_persona character varying(4),
  pnombre_razonsocial character varying(2000),
  pprimer_apellido character varying(50),
  psegundo_apellido character varying(50),
  pnumero_documento character varying(1000),
  ptipo_documento character varying(4),
  plugar_expedicion character varying(4),
  ppais character varying(4),
  pgenero character varying(4),
  psolicitud_departamento character varying(100),
  ppoder character varying(150),
  pdireccion character varying(100),
  ptelefono character varying(50),
  pcelular character varying(50),
  pemail character varying(50),
  pfax character varying(50),
  pestado character varying(2)
  )
RETURNS SETOF sigsolicitanteapoderado AS
$BODY$
/**
 *
 * autor: Eddy Valero
 * fecha: 29/08/2016
 * descripcion: registrar siglogotipo
 */
DECLARE
	vsec_sigsolicitanteapoderado bigint;
	vsigsolicitanteapoderado sigsolicitanteapoderado%ROWTYPE;
BEGIN

	insert into sigsolicitanteapoderado
	(idsignomarca, idlogtrans, tipo_titular, tipo_persona, nombre_razonsocial, primer_apellido, segundo_apellido, numero_documento, tipo_documento, lugar_expedicion, pais, genero, solicitud_departamento, poder, direccion, telefono, celular, email, fax, estado)
	values
	(pidsignomarca, pidlogtrans, ptipo_titular, ptipo_persona, pnombre_razonsocial, pprimer_apellido, psegundo_apellido, pnumero_documento, ptipo_documento, plugar_expedicion, ppais, pgenero, psolicitud_departamento, ppoder, pdireccion, ptelefono, pcelular, pemail, pfax, pestado);

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



select * from reg_sigsolicitanteapoderado(2, 1, 'SOL', 'APOD', 'EMPRESA SRL', '', '', '123456789', 'NIT', 'LPZ', 'BO', 'MAS', 'LPZ', '', 'DIRECCION', '285963', '', '', '', 'AC');

insert into sigsolicitanteapoderado
	(idsignomarca, idlogtrans, tipo_titular, tipo_persona, nombre_razonsocial, primer_apellido, segundo_apellido, numero_documento, tipo_documento, lugar_expedicion, pais, genero, solicitud_departamento, poder, direccion, telefono, celular, email, fax, estado)
	values
	(2, 1, 'SOL', 'APOD', 'EMPRESA SRL', '', '', '123456789', 'NIT', 'LPZ', 'BO', 'MAS', 'LPZ', '', 'DIRECCION', '285963', '', '', '', 'AC');



/******************************************************************************/

-- Sabado 27/08/2016
-- DROP FUNCTION reg_sigimagen(bigint,bigint,bytea)

CREATE OR REPLACE FUNCTION reg_sigimagen(
  pidlogotipo bigint,
  pidlogtrans bigint,
  pimagen bytea
  )
RETURNS SETOF sigimagen AS
$BODY$
/**
 *
 * autor: Eddy Valero
 * fecha: 27/08/2016
 * descripcion: registrar siglogotipo
 */
DECLARE
	vsec_sigimagen bigint;
	vsigimagen sigimagen%ROWTYPE;
BEGIN

	insert into sigimagen
	(idlogotipo, idlogtrans, imagen)
	values
	(pidlogotipo, pidlogtrans, pimagen);

	vsec_sigimagen = (select currval('sec_sigimagen'));

	for vsigimagen in (
				select * from sigimagen
				where idimagen = vsec_sigimagen
				)loop
					return next vsigimagen;
	end loop;
				
END;

$BODY$
  LANGUAGE plpgsql;



  select * from reg_sigimagen(2, 1, 'CODIGO BYTE AZXDFAÑLDFKJAÑKJF ÑAJKF ÑAFJ AÑ FKJASLÑ');




CREATE OR REPLACE FUNCTION reg_siglogotipo(
  pidsignomarca bigint,
  pidlogtrans bigint,
  purllogotipo character varying(250),
  pprincipal boolean,
  pnombre_archivo character varying(100),
  pextension_archivo character varying(10),
  pestado character varying(2)
  )
RETURNS SETOF siglogotipo AS
$BODY$
/**
 *
 * autor: Eddy Valero
 * fecha: 27/08/2016
 * descripcion: registrar siglogotipo
 */
DECLARE
	vsec_siglogotipo bigint;
	vsiglogotipo siglogotipo%ROWTYPE;
BEGIN

	insert into siglogotipo
	(idsignomarca, idlogtrans, urllogotipo, principal, nombre_archivo, extension_archivo, estado)
	values
	(pidsignomarca, pidlogtrans, purllogotipo, pprincipal, pnombre_archivo, pextension_archivo, pestado);

	vsec_siglogotipo = (select currval('sec_siglogotipo'));

	for vsiglogotipo in (
				select * from siglogotipo
				where idlogotipo = vsec_siglogotipo
				)loop
					return next vsiglogotipo;
	end loop;
				
END;

$BODY$
  LANGUAGE plpgsql;



  select * from reg_siglogotipo(2, 1, 'direccion url', TRUE, 'NOMBREARCHIVO', 'jpg', 'AC');



/************************************************************************/
CREATE OR REPLACE FUNCTION obt_claseniza(pnumeroclaseniza character varying)
  RETURNS SETOF claseniza AS
$BODY$
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
  LANGUAGE plpgsql

select * from obt_claseniza('1');

select * from claseniza;


-- DROP FUNCTION reg_signoclaseniza(bigint,bigint,bigint,character varying,text,character varying) 

CREATE OR REPLACE FUNCTION reg_signoclaseniza(
   pidsignomarca bigint,
  pidclaseniza bigint,
  pidlogtrans bigint,
  pnumero_clase_niza character varying(20),
  plista_producto text,
  pestado character varying(2)
  )
RETURNS SETOF sigsignoclaseniza AS
$BODY$
/**
 *
 * autor: Eddy Valero
 * fecha: 26/08/2016
 * descripcion: registra una sigsignomarca
 */
DECLARE
	vsec_sigsignoclaseniza bigint;
	vsigsignoclaseniza sigsignoclaseniza%ROWTYPE;
BEGIN

	insert into sigsignoclaseniza
	(idsignomarca, idclaseniza, idlogtrans, numero_clase_niza, lista_producto, estado)
	values
	(pidsignomarca, pidclaseniza, pidlogtrans, pnumero_clase_niza, plista_producto, pestado);

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

  select * from  reg_signoclaseniza(2, 1, 1, '1', 'lista de productos', 'AC');

  select * from sigsignoclaseniza;
  select *from claseniza;


/*****************************************/







CREATE OR REPLACE FUNCTION reg_tiposigno(
  pidsignomarca bigint,
  pidlogtrans bigint,
  ptipo_signo character varying(4),
  pdescripcion_otro character varying(100),
  pestado character varying(2)
  )
  RETURNS SETOF sigtiposigno AS
$BODY$
/**
 *
 * autor: Eddy Valero
 * fecha: 25/08/2016
 * descripcion: registra una sigsignomarca
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

  select reg_tiposigno(2, 1, 'DEN', 'OTRA DESCRIPCION', 'AC');

  SELECT * FROM sigtiposigno;



/**************************************************************************************/


CREATE OR REPLACE FUNCTION reg_tiposigno(
  pidsignomarca bigint,
  pidlogtrans bigint,
  ptipo_signo character varying(4),
  pdescripcion_otro character varying(100),
  pestado character varying(2)
  )
  RETURNS SETOF sigtiposigno AS
$BODY$
/**
 *
 * autor: Eddy Valero
 * fecha: 25/08/2016
 * descripcion: registra una sigsignomarca
 */
DECLARE
	vsec_sigtiposigno bigint;
	vsigtiposigno sigtiposigno%ROWTYPE;
BEGIN

	insert into sigtiposigno
	(idtiposigno, idsignomarca, idlogtrans, tipo_signo, descripcion_otro, estado)
	values
	(pidsignomarca, pidlogtrans, ptipo_signo, pdescripcion_otro, pestado);

	vsec_sigtiposigno = (select currval('sec_sigtiposigno'));

	for vsigtiposigno in (
				select * from sigsignomarca
				where idsignomarca = vsec_sigtiposigno
				)loop
					return next vsigtiposigno;
	end loop;
				
	

END;

$BODY$
  LANGUAGE plpgsql;







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
  pestado character varying(2)
  )
  RETURNS SETOF sigsignomarca AS
$BODY$
/**
 *
 * autor: Eddy Valero
 * fecha: 25/08/2016
 * descripcion: registra una sigsignomarca
 */
DECLARE
	vsec_sigsignomarca bigint;
	vsigsignomarca sigsignomarca%ROWTYPE;
BEGIN
	insert into sigsignomarca
	(idlogtrans, numero_formulario, sm, signo, tipo_genero, descripcion_signo, descripcion_logotipo, ubicacion, estado_marca, nro_recibo, serie, numero_titulo, serie_titulo, origen_solicitud, numero_gaceta, numero_publicacion, fecha_publicacion, numero_registro, serie_registro, resolucion_registro, fecha_registro, orden_renovacion, numero_renovacion, extension_renovacion, numero_resolucion_renovacion, fecha_renovacion, oficina, fecha_solicitud, fecha_ingreso, entregado_usuario, notoriedad_marca, estado)
	values
	(pidlogtrans, pnumero_formulario, psm, psigno, ptipo_genero, pdescripcion_signo, pdescripcion_logotipo, pubicacion, pestado_marca, pnro_recibo, pserie, pnumero_titulo, pserie_titulo, porigen_solicitud, pnumero_gaceta, pnumero_publicacion, pfecha_publicacion, pnumero_registro, pserie_registro, presolucion_registro, pfecha_registro, porden_renovacion, pnumero_renovacion, pextension_renovacion, pnumero_resolucion_renovacion, pfecha_renovacion, poficina, pfecha_solicitud, pfecha_ingreso, pentregado_usuario, pnotoriedad_marca, pestado);

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


select reg_signomarca(1,
'12563200',
201600000000,
'pollos micheline',
'LC',
'descripcion_signo',
'descripcion_logotipo',
'UBIC',
'ESTA',
NULL,
NULL,
NULL,
NULL,
NULL,
NULL,
NULL,
NULL,
NULL,
NULL,
NULL,
NULL,
NULL,
NULL,
NULL,
NULL,
NULL,
'LA PAZ',
CURRENT_DATE,
CURRENT_DATE,
FALSE,
FALSE,
'AC');




/**************************************************************************/

CREATE OR REPLACE FUNCTION actualiza_reg_observacion(
	prefijo character varying,
	pidobservaciontramite bigint,
	piddescripcion character varying
)
RETURNS void as
$BODY$
/**
 *
 * autor: Eddy Valero
 * fecha: 22/08/2016
 * descripcion: eliminar un determinado registro de observaciones
 */
DECLARE
	
BEGIN
	
	IF prefijo = 'SIG' THEN
		update sigobservaciontramite
		set 
			descripcion = piddescripcion,
			editable = FALSE
		where
			idobservaciontramite = pidobservaciontramite;
		
	END IF;

	IF prefijo = 'MOD' THEN
		update modobservaciontramite
		set 
			descripcion = piddescripcion,
			editable = FALSE
		where
			idobservaciontramite = pidobservaciontramite;
	END IF;

	IF prefijo = 'REN' THEN
		update renobservaciontramite
		set 
			descripcion = piddescripcion,
			editable = FALSE
		where
			idobservaciontramite = pidobservaciontramite;
	END IF;

END;

$BODY$
LANGUAGE plpgsql;




-- Function: public.inserta_reg_observacion(character varying, bigint, bigint, bigint, boolean, character varying, character varying)

-- DROP FUNCTION public.inserta_reg_observacion(character varying, bigint, bigint, bigint, boolean, character varying, character varying);

CREATE OR REPLACE FUNCTION public.inserta_reg_observacion(
    prefijo character varying,
    pidreferencia bigint,
    pidusuario bigint,
    pidlogtrans bigint,
    peditable boolean,
    pestapa_tramite character varying,
    pdescripcion character varying)
  RETURNS void AS
$BODY$
/**
 *
 * autor: Eddy Valero
 * fecha: 22/08/2016
 * descripcion: obtener todos los registros de una plantilla de seguimiento.
 */
DECLARE
	seq_observacion_tramite bigint;
	observacion sigobservaciontramite%ROWTYPE;
BEGIN
	
	IF prefijo = 'SIG' THEN
	
		INSERT INTO sigobservaciontramite(idsignomarca, idusuario, idlogtrans, editable, fecha_observacion, estapa_tramite, descripcion, estado)
		values(pidreferencia, pidusuario, pidlogtrans, peditable, CURRENT_TIMESTAMP , pestapa_tramite, pdescripcion,  'AC');

		-- seq_observacion_tramite = (select currval('sec_sigobservaciontramite'));

	END IF;

	IF prefijo = 'MOD' THEN
	
		INSERT INTO modobservaciontramite(idmodificacion, idusuario, idlogtrans, editable, fecha_observacion, estapa_tramite, descripcion, estado)
		values(pidreferencia, pidusuario, pidlogtrans, peditable, CURRENT_TIMESTAMP , pestapa_tramite, pdescripcion,  'AC');

		-- seq_observacion_tramite = (select currval('sec_modobservaciontramite'));

	END IF;

	IF prefijo = 'REN' THEN
	
		INSERT INTO renobservaciontramite(idsolicitudrenovacion, idusuario, idlogtrans, editable, fecha_observacion, estapa_tramite, descripcion, estado)
		values(pidreferencia, pidusuario, pidlogtrans, peditable, CURRENT_TIMESTAMP , pestapa_tramite, pdescripcion,  'AC');

		-- seq_observacion_tramite = (select currval('sec_modobservaciontramite'));

	END IF;

END;

$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION public.inserta_reg_observacion(character varying, bigint, bigint, bigint, boolean, character varying, character varying)
  OWNER TO senapi;




  select  inserta_reg_observacion('SIG',1,1,1,TRUE, 'EXFM','esta es una observacion');

  select  inserta_reg_observacion('REN',1,1,1,TRUE, 'EXFM','esta es una observacion');


-- DROP FUNCTION inserta_reg_observacion(character varying,bigint,bigint,bigint,boolean,character varying,character varying);



-- select  inserta_reg_observacion('SIG',1,1,1,TRUE, 'EXFM','esta es una observacion');

-- select * from sigobservaciontramite;

-- delete from sigobservaciontramite;



/*
 * autor: Eddy Valero
 * fecha: 15/08/2016
 * descripcion: obtener todos los registros de una plantilla de seguimiento.
 */


CREATE OR REPLACE FUNCTION public.obt_observaciones_tramite(
    prefijo character varying,
    pidreferencia bigint)
  RETURNS SETOF sigobservaciontramite AS
$BODY$
/*
 * autor: Eddy Valero
 * fecha: 22/08/2016
 * descripcion: obtener todos los registros de una plantilla de seguimiento.
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
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION public.obt_observaciones_tramite(character varying, bigint)
  OWNER TO senapi;




select * FROM obtobservacionestramite('SIG', 1);







/******************************************************************/

drop SEQUENCE sec_smdatoelementoformulario;

drop table smdatoelementoformulario;

create table smdatoelementoformulario(
	iddatoelementoformulario 	int8 	NOT NULL,
	idseguimiento 			int8	NOT NULL,
	idlogtrans			int8	NOT NULL,
	pestania			varchar(150),
	seccion				int8,
	fila				int8,
	tipo_elemento			varchar(10),
	nombreelemento			text,
	orden				int8,
	ordenliteral			varchar(20),
	idpadre				int8,
	respuesta			text,
	opcionrespuesta			text,
	estado				varchar(2),
	primary key (iddatoelementoformulario)	
);



-- Para La Tabla actividadplazo
CREATE SEQUENCE sec_smdatoelementoformulario
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE smdatoelementoformulario
    ALTER COLUMN iddatoelementoformulario
        SET DEFAULT NEXTVAL('sec_smdatoelementoformulario');

INSERT INTO smdatoelementoformulario(idseguimiento, idlogtrans, pestania, seccion, fila, tipo_elemento, nombreelemento, orden, idpadre, respuesta, opcionrespuesta, estado) VALUES ( 1, 1, 'MINIMO', NULL, NULL, 'TEH2', 'Tres copias de la primera hoja y una solca copia de las páginas: dos, tres, cuatro y cinco.', 1, NULL, NULL, NULL, 'AC');
INSERT INTO smdatoelementoformulario(idseguimiento, idlogtrans, pestania, seccion, fila, tipo_elemento, nombreelemento, orden, idpadre, respuesta, opcionrespuesta, estado) VALUES ( 1, 1, 'MINIMO', NULL, NULL, 'TEH7', 'Pago por concepto de solicitud de registro de Signos Distintivos a la cuenta del SENAPI', 2, NULL, NULL, NULL, 'AC');
INSERT INTO smdatoelementoformulario(idseguimiento, idlogtrans, pestania, seccion, fila, tipo_elemento, nombreelemento, orden, idpadre, respuesta, opcionrespuesta, estado) VALUES ( 1, 1, 'MINIMO', NULL, NULL, 'TEH7', 'Pago por concepto de publicación a la cuenta de la Gaceta Oficial de Bolivia, con copia simple', 3, NULL, NULL, NULL, 'AC');


/**
drop function obtplantillaventanillaseguimiento(bigint);

drop table smdatoelementoformulario;


drop sequence sec_smdatoelementoformulario

*/

CREATE TABLE smformulariotramite
(
  idformulariotramite bigint NOT NULL,
  idlogtrans bigint,
  codigo varchar(10),
  nombreformulario character varying(100),
  fechainicio timestamp without time zone,
  fechafin timestamp without time zone,
  estado varchar(2),
  PRIMARY KEY (idformulariotramite)
);


create table smelementoformulariotramite(
	idelementoformulariotramite 	int8 	NOT NULL,
	idlogtrans			int8	NOT NULL,
	pestania			varchar(150),
	seccion				int8,
	fila				int8,
	tipo_elemento			varchar(10),
	nombreelemento			text,
	orden				int8,
	ordenliteral			varchar(20),
	idpadre				int8,
	respuesta			text,
	opcionrespuesta			text,
	estado				varchar(2),
	primary key (idelementoformulariotramite)	
);

-- Para La Tabla smformulariotramite
CREATE SEQUENCE sec_smformulariotramite
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE smformulariotramite
    ALTER COLUMN idformulariotramite
        SET DEFAULT NEXTVAL('sec_smformulariotramite');


-- Para La Tabla smformulariotramite
CREATE SEQUENCE sec_smelementoformulariotramite
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE smelementoformulariotramite
    ALTER COLUMN idelementoformulariotramite
        SET DEFAULT NEXTVAL('sec_smelementoformulariotramite');








/**
 *
 * autor: Eddy Valero
 * fecha: 15/08/2016
 * descripcion: obtener todos los registros de una plantilla de seguimiento.
 */
-- CREATE OR REPLACE FUNCTION public.obtplantillaventanillaseguimiento(pidseguimiento bigint)
--   RETURNS SETOF smdatoelementoformulario AS
-- $BODY$
--  DECLARE 
--     reg smdatoelementoformulario%ROWTYPE; 
-- BEGIN
-- 
-- for reg in
-- 	(
-- 	select * from smdatoelementoformulario
-- 	where estado = 'AC'
-- 	and idseguimiento = pidseguimiento
-- 	order by orden asc
-- 	)
-- loop
--     return next reg;
-- end loop;
-- end;
-- $BODY$
--   LANGUAGE plpgsql VOLATILE;



-- select 
-- 	iddatoelementoformulario,
-- 	idseguimiento,
-- 	idlogtrans,
-- 	pestania,
-- 	seccion,
-- 	fila,
-- 	tipo_elemento,
-- 	nombreelemento,
-- 	case
-- 		when idpadre=0 then
-- 			cast(orden as text)
-- 		else
-- 			(cast(idpadre as text) || '.' ||cast(orden as text))
-- 	end orden,
-- 	idpadre,
-- 	respuesta,
-- 	opcionrespuesta,
-- 	estado
-- 	
-- from smdatoelementoformulario 
-- order by orden asc;



-- CREATE OR REPLACE FUNCTION public.obtenerlistaregional()
--   RETURNS SETOF regional AS
-- $BODY$
--  DECLARE 
--     reg regional%ROWTYPE; 
-- BEGIN
-- 
-- for reg in
-- 	(
-- 	select * from regional
-- 	where estado = 'AC'
-- 	order by nombre asc
-- 	)
-- loop
--     return next reg;
-- end loop;
-- end;
-- $BODY$
--   LANGUAGE plpgsql VOLATILE
--   COST 100
--   ROWS 1000;
-- ALTER FUNCTION public.obtenerlistaregional()
--   OWNER TO senapi;



