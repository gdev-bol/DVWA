-- Actividad listado
﻿select *from sighistorial


select * from sigsignomarca order by 1 desc limit 100;

select * from sigregistro order by 1 desc limit 100;


select * from sigsignomarca order by 1 desc limit 10;



--condicion para gestion numero y extension

select
	idsignomarca,
	to_number(substring(to_char(sm, '999999999999') from 1 for 5), '9999') as gestion, 
	to_number(substring(to_char(sm, '999999999999') from 6 for 6), '999999') as numero,
	to_number(substring(to_char(sm, '999999999999') from 7 for 2), '99') as ext
from sigsignomarca
where to_number(substring(to_char(sm, '999999999999') from 1 for 5), '9999') = 2016
and to_number(substring(to_char(sm, '999999999999') from 6 for 6), '999999') = 1
and to_number(substring(to_char(sm, '999999999999') from 7 for 2), '99') = 0

--si no le encuentra, obtener el de la siguiente regional cochabamba
select
	idsignomarca,
	to_number(substring(to_char(sm, '999999999999') from 1 for 5), '9999') as gestion, 
	to_number(substring(to_char(sm, '999999999999') from 6 for 6), '999999') as numero,
	to_number(substring(to_char(sm, '999999999999') from 7 for 2), '99') as ext
from sigsignomarca
where to_number(substring(to_char(sm, '999999999999') from 1 for 5), '9999') = 2016
and to_number(substring(to_char(sm, '999999999999') from 6 for 6), '999999') = 100001
and to_number(substring(to_char(sm, '999999999999') from 7 for 2), '99') = 0

--si no le encuentra, obtener el de la regional santa cruz
select
	idsignomarca,
	to_number(substring(to_char(sm, '999999999999') from 1 for 5), '9999') as gestion, 
	to_number(substring(to_char(sm, '999999999999') from 6 for 6), '999999') as numero,
	to_number(substring(to_char(sm, '999999999999') from 7 for 2), '99') as ext
from sigsignomarca
where to_number(substring(to_char(sm, '999999999999') from 1 for 5), '9999') = 2016
and to_number(substring(to_char(sm, '999999999999') from 6 for 6), '999999') = 200001
and to_number(substring(to_char(sm, '999999999999') from 7 for 2), '99') = 0

--si no le encuentra, obtener el de la regional El Alto
select
	idsignomarca,
	to_number(substring(to_char(sm, '999999999999') from 1 for 5), '9999') as gestion, 
	to_number(substring(to_char(sm, '999999999999') from 6 for 6), '999999') as numero,
	to_number(substring(to_char(sm, '999999999999') from 7 for 2), '99') as ext
from sigsignomarca
where to_number(substring(to_char(sm, '999999999999') from 1 for 5), '9999') = 2016
and to_number(substring(to_char(sm, '999999999999') from 6 for 6), '999999') = 300001
and to_number(substring(to_char(sm, '999999999999') from 7 for 2), '99') = 0







 limit 10;


 	select substring('Thomas' from 2 for 3);





-- Actividad listado historial marca o modificacion



-- Function: lista_historial_id(character varying, bigint)

-- DROP FUNCTION lista_historial_id(character varying, bigint);

CREATE OR REPLACE FUNCTION lista_historial_id(
    prefijo character varying,
    pidreferencia bigint)
  RETURNS SETOF sighistorial AS
$BODY$
/*
********************************************************************************
Creado: Eddy Valero Fecha:18/11/2016
Funcion para listar registros de tablas sigseguimiento, modseguimiento, renseguimiento por id
*/
DECLARE 
    reg sighistorial%ROWTYPE; 
BEGIN

    IF prefijo = 'SIG' THEN
        for reg in
            (
            select * from sighistorial
            where estado = 'AC'
            and idsignomarca = pidreferencia
            order by idhistorial asc
            )
        loop
            return next reg;
        end loop;
    END IF;
    IF prefijo = 'REN' THEN
        for reg in
            (
            select * from renhistorial
            where estado = 'AC'
            and idsolicitudrenovacion = pidreferencia
            order by idhistorial asc
            )
        loop
            return next reg;
        end loop;
    END IF;
    IF prefijo = 'MOD' THEN
        for reg in
            (
            select * from modhistorial
            where estado = 'AC'
            and idmodificacion = pidreferencia
            order by idhistorial asc
            )
        loop
            return next reg;
        end loop;
    END IF;    
end;
$BODY$
  LANGUAGE plpgsql;

select * from lista_historial_id('SIG',152061);


select
	his.idhistorial idhistorial,
	his.idsignomarca id,
	his.idlogtrans idlogtrans,
	his.idusuario,
	his.tipo,
	his.operacion,
	his.estado_marca_descripcion,
	his.observacion,
	his.ubicacion_descripcion,
	his.seccion,
	his.gestion_renovacion,
	his.descripcion,
	his.descripcion_lista_productos,
	his.fecha_operacion,
	case when us.nombre is null then '' else us.nombre end
	|| ' ' ||
	case when us.primer_apellido is null then '' else us.primer_apellido end
	|| ' '||
	case when us.segundo_apellido is null then '' else us.segundo_apellido end
	:: text as usuario_nombre_completo,
	us.login usuario,
	his.estado
from sighistorial his
join
usuario us
	on (us.idusuario = his.idusuario)
where
	idsignomarca = 152061
	and his.estado = 'AC'
	order by fecha_operacion asc;


select *from
sighistorial
limit 10

select *from
usuario
where login = 'carlos'
limit 10


select *from
usuario
where idusuario = 18
limit 10

  


-- Actividad boton anterior y siguiente sigsignomarca


select *from sighistorial


select * from sigsignomarca order by 1 desc limit 100;

select * from sigregistro order by 1 desc limit 100;


select * from sigsignomarca order by 1 desc limit 10;



--condicion para gestion numero y extension

select
	idsignomarca,
	to_number(substring(to_char(sm, '999999999999') from 1 for 5), '9999') as gestion, 
	to_number(substring(to_char(sm, '999999999999') from 6 for 6), '999999') as numero,
	to_number(substring(to_char(sm, '999999999999') from 7 for 2), '99') as ext
from sigsignomarca
where to_number(substring(to_char(sm, '999999999999') from 1 for 5), '9999') = 2016
and to_number(substring(to_char(sm, '999999999999') from 6 for 6), '999999') = 1
and to_number(substring(to_char(sm, '999999999999') from 7 for 2), '99') = 0

--si no le encuentra, obtener el de la siguiente regional cochabamba
select
	idsignomarca,
	to_number(substring(to_char(sm, '999999999999') from 1 for 5), '9999') as gestion, 
	to_number(substring(to_char(sm, '999999999999') from 6 for 6), '999999') as numero,
	to_number(substring(to_char(sm, '999999999999') from 7 for 2), '99') as ext
from sigsignomarca
where to_number(substring(to_char(sm, '999999999999') from 1 for 5), '9999') = 2016
and to_number(substring(to_char(sm, '999999999999') from 6 for 6), '999999') = 100001
and to_number(substring(to_char(sm, '999999999999') from 7 for 2), '99') = 0

--si no le encuentra, obtener el de la regional santa cruz
select
	idsignomarca,
	to_number(substring(to_char(sm, '999999999999') from 1 for 5), '9999') as gestion, 
	to_number(substring(to_char(sm, '999999999999') from 6 for 6), '999999') as numero,
	to_number(substring(to_char(sm, '999999999999') from 7 for 2), '99') as ext
from sigsignomarca
where to_number(substring(to_char(sm, '999999999999') from 1 for 5), '9999') = 2016
and to_number(substring(to_char(sm, '999999999999') from 6 for 6), '999999') = 200001
and to_number(substring(to_char(sm, '999999999999') from 7 for 2), '99') = 0

--si no le encuentra, obtener el de la regional El Alto
select
	idsignomarca,
	to_number(substring(to_char(sm, '999999999999') from 1 for 5), '9999') as gestion, 
	to_number(substring(to_char(sm, '999999999999') from 6 for 6), '999999') as numero,
	to_number(substring(to_char(sm, '999999999999') from 7 for 2), '99') as ext
from sigsignomarca
where to_number(substring(to_char(sm, '999999999999') from 1 for 5), '9999') = 2016
and to_number(substring(to_char(sm, '999999999999') from 6 for 6), '999999') = 300001
and to_number(substring(to_char(sm, '999999999999') from 7 for 2), '99') = 0

 limit 10;


 	select substring('Thomas' from 2 for 3);
