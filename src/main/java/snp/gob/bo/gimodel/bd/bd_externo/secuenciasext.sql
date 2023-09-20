-- Crea Secuencias para cada tabla q tiene clves primarias
-- Para La Tabla actividad
CREATE SEQUENCE sec_actividad
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE actividad
    ALTER COLUMN idactividad
        SET DEFAULT NEXTVAL('sec_actividad');
        
-- Para La Tabla actividadplazo
CREATE SEQUENCE sec_actividadplazo
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE actividadplazo
    ALTER COLUMN idactividadplazo
        SET DEFAULT NEXTVAL('sec_actividadplazo');

-- Para La Tabla area

CREATE SEQUENCE sec_area
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE area
    ALTER COLUMN idarea
        SET DEFAULT NEXTVAL('sec_area');

-- Para la claseniza

CREATE SEQUENCE sec_claseniza
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE claseniza
    ALTER COLUMN idclaseniza
        SET DEFAULT NEXTVAL('sec_claseniza');

-- Para la configuracionsistema

CREATE SEQUENCE sec_configuracionsistema
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE configuracionsistema
    ALTER COLUMN idconfiguracionsistema
        SET DEFAULT NEXTVAL('sec_configuracionsistema');

-- Para La Tabla Correlativo

CREATE SEQUENCE sec_correlativo
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE correlativo
    ALTER COLUMN idcorrelativo
        SET DEFAULT NEXTVAL('sec_correlativo');

-- Para La Tabla Correlativoregional

  CREATE SEQUENCE sec_correlativoregional
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE correlativoregional
    ALTER COLUMN idcorrelativoregional
        SET DEFAULT NEXTVAL('sec_correlativoregional');


     -- Para La Tabla datocontacto

  CREATE SEQUENCE sec_datocontacto
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE datocontacto
    ALTER COLUMN iddatocontacto
        SET DEFAULT NEXTVAL('sec_datocontacto');


     -- Para La Tabla datotramiteventanillaeforma

  CREATE SEQUENCE sec_datotramiteventanillaeforma
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE datotramiteventanillaeforma
    ALTER COLUMN iddatotramiteventanillaeforma
        SET DEFAULT NEXTVAL('sec_datotramiteventanillaeforma');

        

        -- Para La Tabla deposito

  CREATE SEQUENCE sec_deposito
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE deposito
    ALTER COLUMN iddeposito
        SET DEFAULT NEXTVAL('sec_deposito');

        -- Para La Tabla detalleoposicion

  CREATE SEQUENCE sec_detalleoposicion
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE detalleoposicion
    ALTER COLUMN iddetalleoposicion
        SET DEFAULT NEXTVAL('sec_detalleoposicion');

-- Para La Tabla detalletitulo

  CREATE SEQUENCE sec_detalletitulo
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE detalletitulo
    ALTER COLUMN iddetalletitulo
        SET DEFAULT NEXTVAL('sec_detalletitulo');
        
   -- Para La Tabla digarchivodocumento

  CREATE SEQUENCE sec_digarchivodocumento
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE digarchivodocumento
    ALTER COLUMN iddigarchivodocumento
        SET DEFAULT NEXTVAL('sec_digarchivodocumento');
        
        -- Para La Tabla direccionnotificacion

  CREATE SEQUENCE sec_direcciondenotificacion
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE direcciondenotificacion
    ALTER COLUMN iddireciondenotificacion
        SET DEFAULT NEXTVAL('sec_direcciondenotificacion');



        -- Para La Tabla documento

  CREATE SEQUENCE sec_documento
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE documento
    ALTER COLUMN iddocumento
        SET DEFAULT NEXTVAL('sec_documento');


        -- Para La Tabla dosificacion

  CREATE SEQUENCE sec_dosificacion
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE dosificacion
    ALTER COLUMN iddosificacion
        SET DEFAULT NEXTVAL('sec_dosificacion');



        -- Para La Tabla dosificaciontasa

  CREATE SEQUENCE sec_dosificaciontasa
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE dosificaciontasa
    ALTER COLUMN iddosificaciontasa
        SET DEFAULT NEXTVAL('sec_dosificaciontasa');

    -- Para La Tabla estadooposicion

  CREATE SEQUENCE sec_estadooposicion
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE estadooposicion
    ALTER COLUMN idestadooposicion
        SET DEFAULT NEXTVAL('sec_estadooposicion');
        


        -- Para La Tabla etapaplazo

  CREATE SEQUENCE sec_etapaplazo
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE etapaplazo
    ALTER COLUMN idetapaplazo
        SET DEFAULT NEXTVAL('sec_etapaplazo');

        -- Para La Tabla evento

  CREATE SEQUENCE sec_evento
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE evento
    ALTER COLUMN idevento
        SET DEFAULT NEXTVAL('sec_evento');

       
   
-- Para La Tabla fechalimite

  CREATE SEQUENCE sec_fechalimite
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE fechalimite
    ALTER COLUMN idfechalimite
        SET DEFAULT NEXTVAL('sec_fechalimite');


-- Para La Tabla fechasistema

  CREATE SEQUENCE sec_fechasistema
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE fechasistema
    ALTER COLUMN idfechasistema
        SET DEFAULT NEXTVAL('sec_fechasistema');



-- Para La Tabla flujo

  CREATE SEQUENCE sec_flujo
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE flujo
    ALTER COLUMN idflujo
        SET DEFAULT NEXTVAL('sec_flujo');
        
-- Para La Tabla flujoactividad

  CREATE SEQUENCE sec_flujoactividad
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE flujoactividad
    ALTER COLUMN idflujoactividad
        SET DEFAULT NEXTVAL('sec_flujoactividad');


-- Para La Tabla flujoetapa

  CREATE SEQUENCE sec_flujoetapa
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE flujoetapa
    ALTER COLUMN idflujoetapa
        SET DEFAULT NEXTVAL('sec_flujoetapa');
        
        
        


        -- Para La Tabla historial

  CREATE SEQUENCE sec_historial
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE historial
    ALTER COLUMN idhistorial
        SET DEFAULT NEXTVAL('sec_historial');

     -- Para La Tabla historialopo

  CREATE SEQUENCE sec_historialopo
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE historialopo
    ALTER COLUMN idhistorial
        SET DEFAULT NEXTVAL('sec_historialopo');



        -- Para La Tabla logtrans

  CREATE SEQUENCE sec_logtrans
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE logtrans
    ALTER COLUMN idlogtrans
        SET DEFAULT NEXTVAL('sec_logtrans');
        

        -- Para La Tabla nolaboral

  CREATE SEQUENCE sec_nolaboral
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE nolaboral
    ALTER COLUMN idnolaboral
        SET DEFAULT NEXTVAL('sec_nolaboral');


        -- Para La Tabla notificacion

  CREATE SEQUENCE sec_notificacion
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE notificacion
    ALTER COLUMN idnotificacion
        SET DEFAULT NEXTVAL('sec_notificacion');

 -- Para La Tabla notificaciondocumento

  CREATE SEQUENCE sec_notificaciondocumento
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE notificaciondocumento
    ALTER COLUMN idnotificaciondocumento
        SET DEFAULT NEXTVAL('sec_notificaciondocumento');
        

        -- Para La Tabla observaciontramite

  CREATE SEQUENCE sec_observaciontramite
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE observaciontramite
    ALTER COLUMN idobservaciontramite
        SET DEFAULT NEXTVAL('sec_observaciontramite');
        

       -- Para La Tabla oposicion

  CREATE SEQUENCE sec_oposicion
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE oposicion
    ALTER COLUMN idoposicion
        SET DEFAULT NEXTVAL('sec_oposicion');

        -- Para La Tabla pagina

  CREATE SEQUENCE sec_pagina
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE pagina
    ALTER COLUMN idpagina
        SET DEFAULT NEXTVAL('sec_pagina');



        -- Para La Tabla poderdepositado

  CREATE SEQUENCE sec_poderdepositado
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE poderdepositado
    ALTER COLUMN idpoderdepositado
        SET DEFAULT NEXTVAL('sec_poderdepositado');



        -- Para La Tabla recibo

  CREATE SEQUENCE sec_recibo
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE recibo
    ALTER COLUMN idrecibo
        SET DEFAULT NEXTVAL('sec_recibo');


        -- Para La Tabla recibodeposito

  CREATE SEQUENCE sec_recibodeposito
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE recibodeposito
    ALTER COLUMN idrecibodeposito
        SET DEFAULT NEXTVAL('sec_recibodeposito');



        -- Para La Tabla recurso

  CREATE SEQUENCE sec_recurso
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE recurso
    ALTER COLUMN idrecurso
        SET DEFAULT NEXTVAL('sec_recurso');

 -- Para La Tabla referenciasolicitud

  CREATE SEQUENCE sec_referenciasolicitud
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE referenciasolicitud
    ALTER COLUMN idreferenciasolicitud
        SET DEFAULT NEXTVAL('sec_referenciasolicitud');


        -- Para La Tabla regional

  CREATE SEQUENCE sec_regional
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE regional
    ALTER COLUMN idregional
        SET DEFAULT NEXTVAL('sec_regional');

        -- Para La Tabla renovacion

  CREATE SEQUENCE sec_renovacion
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE renovacion
    ALTER COLUMN idrenovacion
        SET DEFAULT NEXTVAL('sec_renovacion');

         -- Para La Tabla resolucion

  CREATE SEQUENCE sec_resolucion
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE resolucion
    ALTER COLUMN idresolucion
        SET DEFAULT NEXTVAL('sec_resolucion');

        
 -- Para La Tabla rol

  CREATE SEQUENCE sec_rol
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE rol
    ALTER COLUMN idrol
        SET DEFAULT NEXTVAL('sec_rol');

        
-- Para tabla rolpagina
  CREATE SEQUENCE sec_rolpagina
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE rolpagina
    ALTER COLUMN idrolpagina
        SET DEFAULT NEXTVAL('sec_rolpagina');




 -- Para La Tabla seguimiento

  CREATE SEQUENCE sec_seguimiento
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE seguimiento
    ALTER COLUMN idseguimiento
        SET DEFAULT NEXTVAL('sec_seguimiento');



 -- Para La Tabla sistema

  CREATE SEQUENCE sec_sistema
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE sistema
    ALTER COLUMN idsistema
        SET DEFAULT NEXTVAL('sec_sistema');



 -- Para La Tabla smdetallepublicacion

  CREATE SEQUENCE sec_smdetallepublicacion
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE smdetallepublicacion
    ALTER COLUMN iddetallepublicacion
        SET DEFAULT NEXTVAL('sec_smdetallepublicacion');


        
 -- Para La Tabla smdominio

  CREATE SEQUENCE sec_smdominio
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE smdominio
    ALTER COLUMN iddominio
        SET DEFAULT NEXTVAL('sec_smdominio');

        
 -- Para La Tabla smelementoformulariotramite

  CREATE SEQUENCE sec_smelementoformulariotramite
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE smelementoformulariotramite
    ALTER COLUMN idelementoformulariotramite
        SET DEFAULT NEXTVAL('sec_smelementoformulariotramite');

       
 -- Para La Tabla smformulariotramite

  CREATE SEQUENCE sec_smformulariotramite
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE smformulariotramite
    ALTER COLUMN idformulariotramite
        SET DEFAULT NEXTVAL('sec_smformulariotramite');



 -- Para La Tabla smimagen

  CREATE SEQUENCE sec_smimagen
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE smimagen
    ALTER COLUMN idimagen
        SET DEFAULT NEXTVAL('sec_smimagen');



 -- Para La Tabla smlogotipo

  CREATE SEQUENCE sec_smlogotipo
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE smlogotipo
    ALTER COLUMN idlogotipo
        SET DEFAULT NEXTVAL('sec_smlogotipo');



 -- Para La Tabla smmodificacion

  CREATE SEQUENCE sec_smmodificacion
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE smmodificacion
    ALTER COLUMN idmodificacion
        SET DEFAULT NEXTVAL('sec_smmodificacion');



 -- Para La Tabla smpodertramite

  CREATE SEQUENCE sec_smpodertramite
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE smpodertramite
    ALTER COLUMN idpodertramite
        SET DEFAULT NEXTVAL('sec_smpodertramite');


 -- Para La Tabla smprioridadpreferencia

  CREATE SEQUENCE sec_smprioridadpreferencia
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE smprioridadpreferencia
    ALTER COLUMN idprioridadpreferencia
        SET DEFAULT NEXTVAL('sec_smprioridadpreferencia');



 -- Para La Tabla smpublicacion

  CREATE SEQUENCE sec_smpublicacion
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE smpublicacion
    ALTER COLUMN idpublicacion
        SET DEFAULT NEXTVAL('sec_smpublicacion');




 -- Para La Tabla smregistro

  CREATE SEQUENCE sec_smregistro
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE smregistro
    ALTER COLUMN idregistro
        SET DEFAULT NEXTVAL('sec_smregistro');



 -- Para La Tabla smsignoclaseniza

  CREATE SEQUENCE sec_smsignoclaseniza
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE smsignoclaseniza
    ALTER COLUMN idsignoclaseniza
        SET DEFAULT NEXTVAL('sec_smsignoclaseniza');




 -- Para La Tabla smsignomarca

  CREATE SEQUENCE sec_smsignomarca
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE smsignomarca
    ALTER COLUMN idsignomarca
        SET DEFAULT NEXTVAL('sec_smsignomarca');


 -- Para La Tabla smsolicitanteapoderado

  CREATE SEQUENCE sec_smsolicitanteapoderado
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE smsolicitanteapoderado
    ALTER COLUMN idsolicitanteapoderado
        SET DEFAULT NEXTVAL('sec_smsolicitanteapoderado');


         -- Para La Tabla smsolicitantetramite

  CREATE SEQUENCE sec_smsolicitantetramite
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE smsolicitantetramite
    ALTER COLUMN idsolicitantetramite
        SET DEFAULT NEXTVAL('sec_smsolicitantetramite');




         -- Para La Tabla smtiposigno

  CREATE SEQUENCE sec_smtiposigno
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE smtiposigno
    ALTER COLUMN idtiposigno
        SET DEFAULT NEXTVAL('sec_smtiposigno');


  -- Para La Tabla smtramite

  CREATE SEQUENCE sec_smtramite
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE smtramite
    ALTER COLUMN idtramite
        SET DEFAULT NEXTVAL('sec_smtramite');
        
-- Para La Tabla solicitudrenovacion

  CREATE SEQUENCE sec_solicitudrenovacion
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE solicitudrenovacion
    ALTER COLUMN idrenovaciontramite
        SET DEFAULT NEXTVAL('sec_solicitudrenovacion');


  -- Para La Tabla tasa

  CREATE SEQUENCE sec_tasa
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE tasa
    ALTER COLUMN idtasa
        SET DEFAULT NEXTVAL('sec_tasa');


          -- Para La Tabla usuario

  CREATE SEQUENCE sec_usuario
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE usuario
    ALTER COLUMN idusuario
        SET DEFAULT NEXTVAL('sec_usuario');

 -- Tabla para usuariopagina
   CREATE SEQUENCE sec_usuariopagina
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE usuariopagina
    ALTER COLUMN idusuariopagina
        SET DEFAULT NEXTVAL('sec_usuariopagina');        

 -- Tabla para usuariorol
   CREATE SEQUENCE sec_usuariorol
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE usuariorol
    ALTER COLUMN idusuariorol
        SET DEFAULT NEXTVAL('sec_usuariorol'); 

 -- Para La Tabla valortramiteventanillaeforma

  CREATE SEQUENCE sec_valortramiteventanillaeforma
  INCREMENT 1
  MINVALUE 1
  START 1
  CACHE 1;

  ALTER TABLE valortramiteventanillaeforma
    ALTER COLUMN idvalortramiteventanillaeforma
        SET DEFAULT NEXTVAL('sec_valortramiteventanillaeforma');










        
