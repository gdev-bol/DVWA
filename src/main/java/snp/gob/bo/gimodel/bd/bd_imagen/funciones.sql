/*
Creado: Victor Hugo Almendras Murillo Fecha: 17/11/2016
********************************************************************************
Inserta elementos en la tabla siglogotipo y tabla sigimagen
*/
CREATE OR REPLACE FUNCTION inserta_valores_siglogotipo_sigimagen(
pidsignomarca bigint,
purllogotipo varchar(250),
pnombre_archivo varchar(100),
pextension_archivo varchar(10),
pimagen bytea
)
RETURNS TABLE(NumeroFilasAfectas integer) As

$$

/*
Creado: Victor Hugo Almendras Murillo Fecha: 17/11/2016
Inserta elementos en la tabla siglogotipo y tabla sigimagen
*/

--DECLARANDO VARIABLES AUXILIARES

DECLARE vRegistro record;
    vResultado bigint;
    vNumeroFilasAfectadas1 integer;
    vNumeroFilasAfectadas2 integer;
    vnumeroFilasAfectadas integer;

BEGIN

INSERT INTO siglogotipo(idsignomarca, idlogtrans, urllogotipo, principal, nombre_archivo, extension_archivo, estado)
VALUES(pidsignomarca, 1, purllogotipo, true, pnombre_archivo, pextension_archivo, 'AC');

--Obteniendo el numero de filas afectadas luego de la insercion
GET DIAGNOSTICS vNumeroFilasAfectadas1 = ROW_COUNT;

--Oteniendo el valor de la secuencia de siglogotipo
SELECT last_value
INTO vRegistro
FROM sec_siglogotipo;

vResultado = vRegistro.last_value;

INSERT INTO sigimagen(idlogotipo, idlogtrans, imagen)
VALUES(vResultado, 1, pimagen);

--Obteniendo el numero de filas afectadas luego de la insercion
GET DIAGNOSTICS vNumeroFilasAfectadas2 = ROW_COUNT;

--Sumatoria de la cantidad de filas en total
vnumeroFilasAfectadas = vNumeroFilasAfectadas1 + vNumeroFilasAfectadas2;

RETURN query
SELECT vnumeroFilasAfectadas As NumeroFilasAfectas;

END;

$$ LANGUAGE plpgsql;

/*
*******************************************************************************
Creado: Eddy Valero Fecha: 11/10/2016
Realizar el crud de la tabla imagen

*/

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

/*
*******************************************************************************
Creado: Eddy Valero Fecha: 11/10/2016
Realizar el crud de la tabla siglogotipo
*/
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
        and estado = 'AC';

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

/*
********************************************************************************************************
Creado: Eddy Valero Fecha:Sabado 27/08/2016
Realizar el registro de una imagen
*/
CREATE OR REPLACE FUNCTION reg_sigimagen(
  pidlogotipo bigint,
  pidlogtrans bigint,
  pimagen bytea
  )
RETURNS SETOF sigimagen AS
$BODY$
/*
Creado: Eddy Valero Fecha:Sabado 27/08/2016
Realizar el registro de una imagen
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



/*
********************************************************************************************************
Creado: Eddy Valero Fecha:Sabado 27/08/2016
Realizar el registro de un logotipo
*/
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
/*
Creado: Eddy Valero Fecha:Sabado 27/08/2016
Realizar el registro de un logotipo
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

/*
*************************************************************
Creado: Levi Laura Fecha:29/11/2016
Lista todos los documentos asociados a una marca
*/
CREATE OR REPLACE FUNCTION lista_sigdocumento_idsignomarca(pidsignomarca bigint)
  RETURNS SETOF sigdocumento AS
$BODY$
/*
Creado: Levi Laura Fecha:29/11/2016
Lista todos los documentos asociados a una marca
*/
DECLARE
    vsigdocumento sigdocumento%ROWTYPE;
BEGIN
    for vsigdocumento in (select *
                        from sigdocumento
                       where idsignomarca = pidsignomarca
             ) loop
              
               return next vsigdocumento;
             end loop;  
            
   
END;
$BODY$
  LANGUAGE plpgsql;
/*
*************************************************************
Creado: Levi Laura Fecha:29/11/2016
Lista el archivo asociado a un documento
*/
CREATE OR REPLACE FUNCTION lista_sigarchivodig_iddoc(piddocumento bigint)
  RETURNS SETOF sigarchivodig AS
$BODY$
/*
Creado: Levi Laura Fecha:29/11/2016
Lista el archivo asociado a un documento
*/
DECLARE
vsigarchivodig sigarchivodig%ROWTYPE;
BEGIN
   

    for vsigarchivodig in (select               *                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
                           from sigarchivodig
                           where iddocumento = piddocumento
        ) loop
            return next vsigarchivodig;
        end loop;               
   


   END;
$BODY$
  LANGUAGE plpgsql;
/*
*************************************************************
Creado: Levi Laura Fecha:29/11/2016
Se realiza el crud para la tabal sigdocumento
*/
CREATE OR REPLACE FUNCTION crud_sigdocumento(
    piddocumento bigint,
    pidsignomarca bigint,
    pnombre_archivo character varying,
    pdescripcion character varying,
    pnro_folios integer,
    ptipo_documento character varying,
    pextension_archivo character varying,
    pfecha_creacion timestamp without time zone,
    pmime character varying,
    pestado character varying,
    poperacion integer)
  RETURNS SETOF sigdocumento AS
$BODY$
/*
*************************************************************
Creado: Levi Laura Fecha:29/11/2016
Se realiza el crud para la tabal sigdocumento
*/
DECLARE
seq_sigdocumento bigint;
vsigdocumento sigdocumento%ROWTYPE;
BEGIN
   if poperacion=1 then

       insert into sigdocumento(idsignomarca ,  nombre_archivo, descripcion , nro_folios, tipo_documento,extension_archivo,fecha_creacion,mime, estado)
                       values(pidsignomarca ,  pnombre_archivo, pdescripcion , pnro_folios, ptipo_documento, pextension_archivo, pfecha_creacion,pmime,pestado);

      seq_sigdocumento = (select currval('sec_sigdocumento')) ;
        for vsigdocumento in (select *                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
                      from sigdocumento
                       where iddocumento = seq_sigdocumento
        ) loop
            return next vsigdocumento;
        end loop;               
   
   end if; -- Es Adicion
   if poperacion=2 then -- update
      update sigdocumento set
         idsignomarca =pidsignomarca,
         nombre_archivo=pnombre_archivo, 
         descripcion=pdescripcion , 
         nro_folios=pnro_folios, 
         tipo_documento=ptipo_documento,
         extension_archivo=pextension_archivo,
         fecha_creacion=pfecha_creacion,
         mime=pmime,
         estado=pestado
         where iddocumento=piddocumento;
          seq_sigdocumento = (select currval('sec_sigdocumento')) ;
        for vsigdocumento in (select *                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
                      from sigdocumento
                       where iddocumento = seq_sigdocumento
        ) loop
            return next vsigdocumento;
        end loop;    
     end if;
   if poperacion =3 then-- delete
       delete from sigdocumento where iddocumento = piddocumento;
       --     seq_sigdocumento = (select currval('sec_sigdocumento')) ;
        for vsigdocumento in (select *                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
                      from sigdocumento
                       where iddocumento = 1
        ) loop
            return next vsigdocumento;
        end loop;               
   
       
   end if; ---fin delete    
   if poperacion=4 then
    RAISE NOTICE 'Entra aqui ';
            for vsigdocumento in (select *
                      from sigdocumento
                       where iddocumento = piddocumento
             ) loop
              
               return next vsigdocumento;
             end loop;        

    
   end if; ---fin consulta
   
END;
$BODY$
  LANGUAGE plpgsql;
/*
*************************************************************
Creado: Levi Laura Fecha:29/11/2016
Se realiza el crud para la tabal sigarchivodig
*/
CREATE OR REPLACE FUNCTION crud_sigarchivodig(
    pidarchivodig bigint,
    piddocumento bigint,
    parchivo bytea,
    poperacion integer)
  RETURNS SETOF sigarchivodig AS
$BODY$
/*
*************************************************************
Creado: Levi Laura Fecha:29/11/2016
Se realiza el crud para la tabal sigarchivodig
*/
DECLARE
seq_sigarchivodig bigint;
vsigarchivodig sigarchivodig%ROWTYPE;
BEGIN
   if poperacion=1 then

   insert into sigarchivodig(iddocumento , archivo)
   values(piddocumento , parchivo);
    seq_sigarchivodig = (select currval('sec_sigarchivodig')) ;
        for vsigarchivodig in (select *                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
                      from sigarchivodig
                       where idarchivodig = seq_sigarchivodig
        ) loop
            return next vsigarchivodig;
        end loop;               
   
   end if; -- Es Adicion
   if poperacion=3 then

      delete from sigarchivodig where iddocumento = piddocumento;
       for vsigarchivodig in (select *                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
                      from sigarchivodig
                       where idarchivodig = 1
        ) loop
            return next vsigarchivodig;
        end loop;  
   end if;

   END;
$BODY$
  LANGUAGE plpgsql;
/*
*************************************************************
Creado: Levi Laura Fecha:02/12/2016
Se realiza el crud para la tabla modarchivodig
*/
CREATE OR REPLACE FUNCTION crud_modarchivodig(
    pidarchivodig bigint,
    piddocumento bigint,
    parchivo bytea,
    poperacion integer)
  RETURNS SETOF modarchivodig AS
$BODY$
/*
Creado: Levi Laura Fecha:02/12/2016
Se realiza el crud para la tabla modarchivodig
*/
DECLARE
seq_modarchivodig bigint;
vmodarchivodig modarchivodig%ROWTYPE;
BEGIN
   if poperacion=1 then

   insert into modarchivodig(iddocumento , archivo)
   values(piddocumento , parchivo);
    seq_modarchivodig = (select currval('sec_modarchivodig')) ;
        for vmodarchivodig in (select *                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
                      from modarchivodig
                       where idarchivodig = seq_modarchivodig
        ) loop
            return next vmodarchivodig;
        end loop;               
   
   end if; -- Es Adicion
   if poperacion=3 then

      delete from modarchivodig where iddocumento = piddocumento;
      return next vmodarchivodig;
      -- for vmodarchivodig in (select *                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
      --                from modarchivodig
      --                 where idarchivodig = 1
      --  ) loop
      --      return next vmodarchivodig;
      --  end loop;  
   end if;

   END;
$BODY$
  LANGUAGE plpgsql;
/*
*************************************************************
Creado: Levi Laura Fecha:02/12/2016
Se realiza el crud para la tabla moddocumento
*/

CREATE OR REPLACE FUNCTION crud_moddocumento(
    piddocumento bigint,
    pidmodificacion bigint,
    pnombre_archivo character varying,
    pdescripcion character varying,
    pnro_folios integer,
    ptipo_documento character varying,
    pextension_archivo character varying,
    pfecha_creacion timestamp without time zone,
    pmime character varying,
    pestado character varying,
    poperacion integer)
  RETURNS SETOF moddocumento AS
$BODY$
/*
Creado: Levi Laura Fecha:02/12/2016
Se realiza el crud para la tabla moddocumento
*/
DECLARE
seq_moddocumento bigint;
vmoddocumento moddocumento%ROWTYPE;
BEGIN
   if poperacion=1 then

       insert into moddocumento(idmodificacion ,nombre_archivo, descripcion , nro_folios, tipo_documento, extension_archivo, fecha_creacion,mime, estado)
                  values(pidmodificacion ,  pnombre_archivo, pdescripcion , pnro_folios, ptipo_documento,pextension_archivo, pfecha_creacion,pmime,pestado);

      seq_moddocumento = (select currval('sec_moddocumento')) ;
        for vmoddocumento in (select *                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
                      from moddocumento
                       where iddocumento = seq_moddocumento
        ) loop
            return next vmoddocumento;
        end loop;               
   
   end if; -- Es Adicion
   if poperacion=2 then -- update
      update moddocumento set
         idmodificacion =pidmodificacion,
         nombre_archivo=pnombre_archivo, 
         descripcion=pdescripcion , 
         nro_folios=pnro_folios, 
         tipo_documento=ptipo_documento, 
         extension_archivo=pextension_archivo,
         fecha_creacion=pfecha_creacion,
         mime=pmime,
         estado=pestado
         where iddocumento=piddocumento;
          seq_moddocumento = (select currval('sec_moddocumento')) ;
        for vmoddocumento in (select *                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
                      from moddocumento
                       where iddocumento = seq_moddocumento
        ) loop
            return next vmoddocumento;
        end loop;    
     end if;
   if poperacion =3 then-- delete
       delete from moddocumento where iddocumento = piddocumento;
           return next vmoddocumento;
       --     seq_sigdocumento = (select currval('sec_sigdocumento')) ;
      --  for vmoddocumento in (select *                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
      --                from moddocumento
      --                 where iddocumento = 1
       -- ) loop
       --     return next vmoddocumento;
       -- end loop;               
   
       
   end if; ---fin delete    
   if poperacion=4 then
    RAISE NOTICE 'Entra aqui ';
            for vmoddocumento in (select *
                      from moddocumento
                       where iddocumento = piddocumento
             ) loop
              
               return next vmoddocumento;
             end loop;        

    
   end if; ---fin consulta
   
END;
$BODY$
  LANGUAGE plpgsql;
/*
***********************************************************
Creado: Levi Laura Fecha:02/12/2016
Lista todos los documentos asociados a una modificacion
*/
CREATE OR REPLACE FUNCTION lista_moddocumento_idmodificacion(pidmodificacion bigint)
  RETURNS SETOF moddocumento AS
$BODY$
/*
Creado: Levi Laura Fecha:02/12/2016
Lista todos los documentos asociados a una modificacion
*/
DECLARE
    vmoddocumento moddocumento%ROWTYPE;
BEGIN
    for vmoddocumento in (select *
                        from moddocumento
                       where idmodificacion = pidmodificacion
             ) loop
              
               return next vmoddocumento;
             end loop;  
            
   
END;
$BODY$
  LANGUAGE plpgsql ;
/*
********************************
Creado: Levi Laura Fecha:02/12/2016
Lista el archivo asociado a un documento de modifiacion
*/
CREATE OR REPLACE FUNCTION lista_modarchivodig_iddoc(piddocumento bigint)
  RETURNS SETOF modarchivodig AS
$BODY$
/*
Creado: Levi Laura Fecha:02/12/2016
Lista el archivo asociado a un documento de modifiacion
*/
DECLARE
vmodarchivodig modarchivodig%ROWTYPE;
BEGIN
   

    for vmodarchivodig in (select               *                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
                           from modarchivodig
                           where iddocumento = piddocumento
        ) loop
            return next vmodarchivodig;
        end loop;               
   


   END;
$BODY$
  LANGUAGE plpgsql;
/*
*************************************************************
Creado: Levi Laura Fecha:05/12/2016
Se realiza el crud para la tabal rendocumento
*/

CREATE OR REPLACE FUNCTION crud_rendocumento(
    piddocumento bigint,
    pidsolicitudrenovacion bigint,
    pnombre_archivo character varying,
    pdescripcion character varying,
    pnro_folios integer,
    ptipo_documento character varying,
    pextension_archivo character varying,
    pfecha_creacion timestamp without time zone,
    pmime character varying,
    pestado character varying,
    poperacion integer)
  RETURNS SETOF rendocumento AS
$BODY$
/*
Creado: Levi Laura Fecha:05/12/2016
Se realiza el crud para la tabal rendocumento
*/
DECLARE
seq_rendocumento bigint;
vrendocumento rendocumento%ROWTYPE;
BEGIN
   if poperacion=1 then

       insert into rendocumento(idsolicitudrenovacion ,nombre_archivo, descripcion , nro_folios, tipo_documento, extension_archivo, fecha_creacion,mime, estado)
                  values(pidsolicitudrenovacion ,  pnombre_archivo, pdescripcion , pnro_folios, ptipo_documento,pextension_archivo, pfecha_creacion,pmime,pestado);

      seq_rendocumento = (select currval('sec_rendocumento')) ;
        for vrendocumento in (select *                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
                      from rendocumento
                       where iddocumento = seq_rendocumento
        ) loop
            return next vrendocumento;
        end loop;               
   
   end if; -- Es Adicion
   if poperacion=2 then -- update
      update moddocumento set
         idsolicitudrenovacion =pidsolicitudrenovacion,
         nombre_archivo=pnombre_archivo, 
         descripcion=pdescripcion , 
         nro_folios=pnro_folios, 
         tipo_documento=ptipo_documento, 
         extension_archivo=pextension_archivo,
         fecha_creacion=pfecha_creacion,
         mime=pmime,
         estado=pestado
         where iddocumento=piddocumento;
          seq_rendocumento = (select currval('sec_rendocumento')) ;
        for vrendocumento in (select *                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
                      from rendocumento
                       where iddocumento = seq_rendocumento
        ) loop
            return next vrendocumento;
        end loop;    
     end if;
   if poperacion =3 then-- delete
       delete from rendocumento where iddocumento = piddocumento;
           return next vrendocumento;
       --     seq_sigdocumento = (select currval('sec_sigdocumento')) ;
      --  for vmoddocumento in (select *                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
      --                from moddocumento
      --                 where iddocumento = 1
       -- ) loop
       --     return next vmoddocumento;
       -- end loop;               
   
       
   end if; ---fin delete    
   if poperacion=4 then
    RAISE NOTICE 'Entra aqui ';
            for vrendocumento in (select *
                      from rendocumento
                       where iddocumento = piddocumento
             ) loop
              
               return next vrendocumento;
             end loop;        

    
   end if; ---fin consulta
   
END;
$BODY$
  LANGUAGE plpgsql;
/*
************************************************
Creado: Levi Laura Fecha:05/12/2016
Se realiza el crud para la tabla renarchivodig
*/
CREATE OR REPLACE FUNCTION crud_renarchivodig(
    pidarchivodig bigint,
    piddocumento bigint,
    parchivo bytea,
    poperacion integer)
  RETURNS SETOF modarchivodig AS
$BODY$
/*
Creado: Levi Laura Fecha:05/12/2016
Se realiza el crud para la tabla renarchivodig
*/
DECLARE
seq_renarchivodig bigint;
vrenarchivodig renarchivodig%ROWTYPE;
BEGIN
   if poperacion=1 then

   insert into renarchivodig(iddocumento , archivo)
   values(piddocumento , parchivo);
    seq_renarchivodig = (select currval('sec_renarchivodig')) ;
        for vrenarchivodig in (select *                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
                      from renarchivodig
                       where idarchivodig = seq_renarchivodig
        ) loop
            return next vrenarchivodig;
        end loop;               
   
   end if; -- Es Adicion
   if poperacion=3 then

      delete from renarchivodig where iddocumento = piddocumento;
      return next vrenarchivodig;
      -- for vmodarchivodig in (select *                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
      --                from modarchivodig
      --                 where idarchivodig = 1
      --  ) loop
      --      return next vmodarchivodig;
      --  end loop;  
   end if;

   END;
$BODY$
  LANGUAGE plpgsql;
/*
***************************************
Creado: Levi Laura Fecha:05/12/2016
Lista el archivo asociado a un documento de renovacion
*/
CREATE OR REPLACE FUNCTION lista_renarchivodig_iddoc(piddocumento bigint)
  RETURNS SETOF renarchivodig AS
$BODY$
/*
Creado: Levi Laura Fecha:05/12/2016
Lista el archivo asociado a un documento de modifiacion
*/
DECLARE
vrenarchivodig renarchivodig%ROWTYPE;
BEGIN
   

    for vrenarchivodig in (select               *                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
                           from renarchivodig
                           where iddocumento = piddocumento
        ) loop
            return next vrenarchivodig;
        end loop;               
   


   END;
$BODY$
  LANGUAGE plpgsql;
/*
**************************************************
Creado: Levi Laura Fecha:02/12/2016
Lista todos los documentos asociados a una renovacion
*/
CREATE OR REPLACE FUNCTION lista_rendocumento_idmodificacion(pidsolicitudrenovacion bigint)
  RETURNS SETOF rendocumento AS
$BODY$
/*
Creado: Levi Laura Fecha:02/12/2016
Lista todos los documentos asociados a una renovacion
*/
DECLARE
    vrendocumento rendocumento%ROWTYPE;
BEGIN
    for vrendocumento in (select *
                        from rendocumento
                       where idsolicitudrenovacion = pidsolicitudrenovacion
             ) loop
              
               return next vrendocumento;
             end loop;  
            
   
END;
$BODY$
  LANGUAGE plpgsql;


