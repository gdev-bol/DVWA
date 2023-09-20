
-- Author :       LUIS ANGEL
--
-- Date Created : Tuesday, August 09, 2016 11:58:55
-- Target DBMS : PostgreSQL 8.0
--

-- 
-- TABLE: actividad 
--

CREATE TABLE actividad(
    idactividad          int8            NOT NULL,
    idestadooposicion    int8            NOT NULL,
    idlogtrans           int8,
    actividad            varchar(100),
    orden                int4,
    CONSTRAINT "PK149" PRIMARY KEY (idactividad)
)
;



-- 
-- TABLE: actividadplazo 
--

CREATE TABLE actividadplazo(
    idactividadplazo       int8    NOT NULL,
    idactividad            int8    NOT NULL,
    idactividadanterior    int8,
    idlogtrans             int8,
    idarea                 int8,
    plazo                  int4,
    sumarplazoanterior     int4,
    CONSTRAINT "PK151" PRIMARY KEY (idactividadplazo)
)
;



-- 
-- TABLE: area 
--

CREATE TABLE area(
    idarea         int8           NOT NULL,
    nombre_area    varchar(50),
    codigo_area    varchar(50),
    estado         varchar(2),
    CONSTRAINT "PK144" PRIMARY KEY (idarea)
)
;



-- 
-- TABLE: claseniza 
--

CREATE TABLE claseniza(
    idclaseniza                  int8             NOT NULL,
    idlogtrans                   int8,
    numero_clase_niza            varchar(20),
    proteccion                   varchar(500),
    vigente                      boolean,
    fecha_inicio                 timestamp,
    fecha_fin                    timestamp,
    tipo                         boolean,
    nota_tipo_claseniza          varchar(1000),
    numero_edicion               varchar(10),
    version                      varchar(10),
    usuario                      int8,
    fecha_ultima_modificacion    timestamp,
    CONSTRAINT "PK13_2" PRIMARY KEY (idclaseniza)
)
;



-- 
-- TABLE: configuracionsistema 
--

CREATE TABLE configuracionsistema(
    idconfiguracionsistema    int8             NOT NULL,
    idlogtrans                int8,
    concepto                  varchar(80),
    valor1                    varchar(50),
    valor2                    varchar(100),
    orden                     numeric(3, 0),
    abreviacion               varchar(5),
    fecha                     timestamp,
    estado                    varchar(2),
    CONSTRAINT "PK157" PRIMARY KEY (idconfiguracionsistema)
)
;



-- 
-- TABLE: correlativo 
--

CREATE TABLE correlativo(
    idcorrelativo             int8            NOT NULL,
    idlogtrans                int8,
    nombre_criterio           varchar(100),
    incremento                integer,
    ultimo_numero_asignado    integer,
    acronimo                  varchar(10),
    separador                 varchar(15),
    gestion                   integer,
    estado                    varchar(2),
    CONSTRAINT "PK128" PRIMARY KEY (idcorrelativo)
)
;



-- 
-- TABLE: correlativoregional 
--

CREATE TABLE correlativoregional(
    idcorrelativoregional    int8          NOT NULL,
    idregional               int8          NOT NULL,
    idcorrelativo            int8          NOT NULL,
    idlogtrans               int8,
    tipo_tramite             varchar(4),
    estado                   varchar(2),
    CONSTRAINT "PK131" PRIMARY KEY (idcorrelativoregional)
)
;



-- 
-- TABLE: datocontacto 
--

CREATE TABLE datocontacto(
    iddatocontacto            int8           NOT NULL,
    idsolicitanteapoderado    int8           NOT NULL,
    idlogtrans                int8,
    iddato_contacto_mod       int8,
    tipo_contacto             varchar(50),
    dato                      varchar(50),
    CONSTRAINT "PK13_1" PRIMARY KEY (iddatocontacto)
)
;



-- 
-- TABLE: datotramiteventanillaeforma 
--

CREATE TABLE datotramiteventanillaeforma(
    iddatotramiteventanillaeforma    int8              NOT NULL,
    idseguimiento                    int8              NOT NULL,
    idlogtrans                       int8,
    tipo_requisito                   varchar(5),
    codigo                           varchar(5),
    descripcion                      text,
    presento                         boolean,
    observacion                      varchar(250),
    posicion                         numeric(11, 0),
    CONSTRAINT "PK160" PRIMARY KEY (iddatotramiteventanillaeforma)
)
;



-- 
-- TABLE: deposito 
--

CREATE TABLE deposito(
    iddeposito                 int8              NOT NULL,
    idlogtrans                 int8,
    nombre_depositante         varchar(300),
    monto                      decimal(18, 0),
    saldo                      decimal(18, 0),
    fecha_deposito             timestamp,
    fecha_registro_deposito    timestamp,
    banco                      varchar(100),
    estado                     varchar(2),
    CONSTRAINT "PK233" PRIMARY KEY (iddeposito)
)
;



-- 
-- TABLE: detalleoposicion 
--

CREATE TABLE detalleoposicion(
    iddetalleoposicion    int8              NOT NULL,
    idoposicion           int8              NOT NULL,
    idtramite             int8,
    idpatente             int8,
    idarea                int8,
    idmarca               int8,
    idlogtrans            int8,
    orden_opo             int4,
    dmte_reg              numeric(18, 0),
    dmte_serie            varchar(5),
    dmte_public           numeric(18, 0),
    dmte_sm               numeric(18, 0),
    dmte_sp               numeric(18, 0),
    dmte_marca_lnv        varchar(1000),
    dmte_uso              varchar(250),
    dmte_clase            int4,
    CONSTRAINT "PK145" PRIMARY KEY (iddetalleoposicion)
)
;



-- 
-- TABLE: detalletitulo 
--

CREATE TABLE detalletitulo(
    iddetalletitulo    int8    NOT NULL,
    idrecibo           int8    NOT NULL,
    idlogtrans         int8,
    CONSTRAINT "PK155" PRIMARY KEY (iddetalletitulo)
)
;



-- 
-- TABLE: digarchivodocumento 
--

CREATE TABLE digarchivodocumento(
    iddigarchivodocumento    int8     NOT NULL,
    iddocumento              int8     NOT NULL,
    imagen                   bytea,
    CONSTRAINT "PK134" PRIMARY KEY (iddigarchivodocumento)
)
;



-- 
-- TABLE: direcciondenotificacion 
--

CREATE TABLE direcciondenotificacion(
    iddireciondenotificacion    int8           NOT NULL,
    idtramite                   int8           NOT NULL,
    idlogtrans                  int8,
    tipo_ciudad_notifiacion     varchar(20),
    zona_barrio                 varchar(20),
    avenida_calle               varchar(50),
    numero                      int4,
    edificio                    varchar(50),
    piso                        int4,
    departamento                varchar(50),
    correo_electronico          varchar(50),
    telefono                    int4,
    estado                      varchar(2),
    CONSTRAINT "PK95" PRIMARY KEY (iddireciondenotificacion)
)
;



-- 
-- TABLE: documento 
--

CREATE TABLE documento(
    iddocumento           int8            NOT NULL,
    idarea                int8,
    idtramite             int8,
    idlogtrans            int8,
    nombre_archivo        varchar(50),
    descripcion           varchar(200),
    nro_folios            char(10),
    fecha_creacion        timestamp,
    tipo_documentacion    varchar(2),
    tipo_archivo          varchar(5),
    imagen                bytea,
    estado                varchar(2),
    CONSTRAINT "PK133" PRIMARY KEY (iddocumento)
)
;



-- 
-- TABLE: dosificacion 
--

CREATE TABLE dosificacion(
    iddosificacion    int8           NOT NULL,
    idregional        int8           NOT NULL,
    numero_inicio     integer,
    numero_final      integer,
    actual            integer,
    siguiente         integer,
    tipo_recibo       varchar(4),
    serie             varchar(10),
    tipo_lugar        varchar(4),
    gestion           integer,
    incremento        integer,
    estado            varchar(2),
    CONSTRAINT "PK129" PRIMARY KEY (iddosificacion)
)
;



-- 
-- TABLE: dosificaciontasa 
--

CREATE TABLE dosificaciontasa(
    iddosificaciontasa    int8          NOT NULL,
    idtasa                int8          NOT NULL,
    iddosificacion        int8          NOT NULL,
    idlogtrans            int8,
    estado                varchar(2),
    CONSTRAINT "PK130" PRIMARY KEY (iddosificaciontasa)
)
;



-- 
-- TABLE: estadooposicion 
--

CREATE TABLE estadooposicion(
    idestadooposicion    int8            NOT NULL,
    idarea               int8,
    idlogtrans           int8,
    estado               varchar(100),
    orden                int4,
    CONSTRAINT "PK148" PRIMARY KEY (idestadooposicion)
)
;



-- 
-- TABLE: etapaplazo 
--

CREATE TABLE etapaplazo(
    idetapaplazo      int8            NOT NULL,
    nombre_etapa      varchar(500),
    plazo             int4,
    fecha_creacion    timestamp,
    estado            varchar(2),
    CONSTRAINT "PK120" PRIMARY KEY (idetapaplazo)
)
;



-- 
-- TABLE: evento 
--

CREATE TABLE evento(
    idevento              int8             NOT NULL,
    iddetalleoposicion    int8             NOT NULL,
    idactividad           int8             NOT NULL,
    idlogtrans            int8,
    fecha                 timestamp,
    observacion           varchar(1000),
    usuario               int8,
    fecha_operacion       timestamp,
    CONSTRAINT "PK147" PRIMARY KEY (idevento)
)
;



-- 
-- TABLE: fechalimite 
--

CREATE TABLE fechalimite(
    idfechalimite         int8         NOT NULL,
    iddetalleoposicion    int8         NOT NULL,
    idevento              int8         NOT NULL,
    idlogtramite          int8,
    idactividadplazo      int8,
    orden                 int4,
    fechalimite           timestamp,
    CONSTRAINT "PK153" PRIMARY KEY (idfechalimite)
)
;



-- 
-- TABLE: fechasistema 
--

CREATE TABLE fechasistema(
    idfechasistema    int8          NOT NULL,
    idlogtrans        int8,
    idregional        int8,
    fecha             timestamp,
    estado            varchar(2),
    CONSTRAINT "PK156" PRIMARY KEY (idfechasistema)
)
;



-- 
-- TABLE: flujo 
--

CREATE TABLE flujo(
    idflujo         int8            NOT NULL,
    idlogtrans      int8,
    nombre_flujo    varchar(100),
    fecha_inicio    timestamp,
    fecha_fin       timestamp,
    area_tramite    varchar(4),
    tipo_tramite    varchar(4),
    estado          varchar(2),
    CONSTRAINT "PK162" PRIMARY KEY (idflujo)
)
;



-- 
-- TABLE: flujoactividad 
--

CREATE TABLE flujoactividad(
    idflujoactividad    int8          NOT NULL,
    idactividad         int8          NOT NULL,
    idlogtrans          int8,
    tipo                varchar(1),
    CONSTRAINT "PK150" PRIMARY KEY (idflujoactividad)
)
;



-- 
-- TABLE: flujoetapa 
--

CREATE TABLE flujoetapa(
    idflujoetapa            int8              NOT NULL,
    idflujo                 int8              NOT NULL,
    idlogtrans              int8,
    orden                   numeric(11, 0),
    etapatramite            varchar(4),
    plazo                   numeric(11, 0),
    unidadtiempo            varchar(4),
    fecha_inicio            timestamp,
    fecha_fin               timestamp,
    padre                   int8,
    revision                int8,
    crear_nuevo_analisis    int8,
    estado                  varchar(2),
    CONSTRAINT "PK163" PRIMARY KEY (idflujoetapa)
)
;



-- 
-- TABLE: historial 
--

CREATE TABLE historial(
    idhistorial                    int8            NOT NULL,
    idtramite                      int8            NOT NULL,
    idlogtrans                     int8,
    idusuario                      int8,
    tipo_tramite                   varchar(4),
    operacion                      varchar(50),
    estado_tramite                 varchar(4),
    observacion                    text,
    etapa_tramite                  varchar(4),
    seccion                        varchar(100),
    gestion_renovacion             integer,
    descripcion                    text,
    descripcion_lista_productos    text,
    fecha_operacion                timestamp,
    CONSTRAINT "PK136" PRIMARY KEY (idhistorial)
)
;



-- 
-- TABLE: historialopo 
--

CREATE TABLE historialopo(
    idhistorial        int8             NOT NULL,
    idoposicion        int8             NOT NULL,
    idlogtrans         int8,
    estado             varchar(100),
    fecha_lim          timestamp,
    observacion        varchar(2000),
    ubicacion          varchar(250),
    apoderado          varchar(250),
    operacion          varchar(150),
    fecha_operacion    timestamp,
    idusuario          int8,
    CONSTRAINT "PK143" PRIMARY KEY (idhistorial)
)
;



-- 
-- TABLE: logtrans 
--

CREATE TABLE logtrans(
    idlogtrans    int8         NOT NULL,
    idusuario     int8         NOT NULL,
    fecha         timestamp,
    CONSTRAINT "PK142" PRIMARY KEY (idlogtrans)
)
;



-- 
-- TABLE: nolaboral 
--

CREATE TABLE nolaboral(
    idnolaboral    int8            NOT NULL,
    idregional     int8            NOT NULL,
    idlogtrans     int8,
    fecha          timestamp,
    descripcion    varchar(500),
    CONSTRAINT "PK121" PRIMARY KEY (idnolaboral)
)
;



-- 
-- TABLE: notificacion 
--

CREATE TABLE notificacion(
    idnotificacion            int8             NOT NULL,
    idtramite                 int8,
    idregistro                int8,
    idpublicacion             int8,
    idarea                    int8,
    idlogtrans                int8,
    bloque                    int4,
    nro_exped                 int4,
    operador                  varchar(50),
    cod_tipo_tram             varchar(2),
    num_tramite               varchar(10),
    gestion                   int4,
    ext                       varchar(2),
    demandante                varchar(500),
    demandante_solic          varchar(500),
    demandante_apod           varchar(500),
    demandante_fojas          varchar(50),
    demandante_con            varchar(200),
    demandante_notif_direc    varchar(500),
    demandado                 varchar(500),
    demandado_solic           varchar(500),
    demandado_apod            varchar(500),
    demandado_fojas           varchar(50),
    demandado_con             varchar(200),
    demandado_notif_direc     varchar(500),
    fecha_ing                 timestamp,
    obs                       text,
    historial                 text,
    fecha_recep               timestamp,
    fecha_devol               timestamp,
    fecha_noti_demandante     timestamp,
    fecha_noti_demandado      timestamp,
    obs_notifi                text,
    operador_noti             varchar(50),
    notificado_a              varchar(50),
    ciudad                    varchar(50),
    estado_anterior_unidad    int4,
    form_noti_pre             varchar(1000),
    form_noti_cuerpo          varchar(4000),
    demandante_notif_cel      varchar(20),
    demandado_notif_cel       varchar(20),
    CONSTRAINT "PK3_2" PRIMARY KEY (idnotificacion)
)
;



-- 
-- TABLE: notificaciondocumento 
--

CREATE TABLE notificaciondocumento(
    idnotificaciondocumento    int8             NOT NULL,
    idnotificacion             int8             NOT NULL,
    iddocumento                int8             NOT NULL,
    idlogtrans                 int8,
    observacion                varchar(1000),
    CONSTRAINT "PK135" PRIMARY KEY (idnotificaciondocumento)
)
;



-- 
-- TABLE: observaciontramite 
--

CREATE TABLE observaciontramite(
    idobservaciontramite    int8             NOT NULL,
    idtramite               int8             NOT NULL,
    idlogtrans              int8,
    idusuario               int4,
    usuario                 int8,
    fechademodificacion     date,
    editable                boolean,
    fecha_observacion       char(10),
    estapa_tramite          char(10),
    descripcion             varchar(2000),
    estado                  varchar(10),
    CONSTRAINT "PK87" PRIMARY KEY (idobservaciontramite)
)
;



-- 
-- TABLE: oposicion 
--

CREATE TABLE oposicion(
    idoposicion       int8              NOT NULL,
    idlogtrans        int8,
    nro_opo           numeric(18, 0),
    dmdo_public       numeric(18, 0),
    gaceta            numeric(10, 0),
    fecha_public      timestamp,
    dmdo_marca_lnv    varchar(1000),
    demandado         varchar(500),
    dmdo_id           varchar(50),
    dmdo_direc        varchar(500),
    dmdo_apod         varchar(200),
    dmdo_tel          varchar(100),
    dmdo_fax          varchar(50),
    dmdo_email        varchar(100),
    dmdo_clase        varchar(250),
    dmdo_dir_notif    varchar(500),
    idestado          int8,
    fec_lim           timestamp,
    ubicacion         varchar(100),
    verif             boolean,
    observacion       varchar(1000),
    CONSTRAINT "PK141" PRIMARY KEY (idoposicion)
)
;



-- 
-- TABLE: pagina 
--

CREATE TABLE pagina(
    idpagina       int8           NOT NULL,
    idlogtrans     int8,
    descripcion    varchar(50),
    url            varchar(50),
    estado         varchar(2),
    CONSTRAINT "PK123" PRIMARY KEY (idpagina)
)
;



-- 
-- TABLE: poderdepositado 
--

CREATE TABLE poderdepositado(
    idpoderdepositado    int8             NOT NULL,
    idlogtrans           int8,
    tipo_tramite         char(2),
    num_tramite          int4,
    gestion              int4,
    num_testim           varchar(20),
    fecha_testim         timestamp,
    notario              varchar(100),
    nom_conf_poder       varchar(200),
    dom_conf_poder       varchar(500),
    apoderado            varchar(200),
    poder_revocado       varchar(2),
    obs                  varchar(1000),
    estado               varchar(2),
    CONSTRAINT "PK109" PRIMARY KEY (idpoderdepositado)
)
;



-- 
-- TABLE: recibo 
--

CREATE TABLE recibo(
    idrecibo                int8              NOT NULL,
    idtasa                  int8              NOT NULL,
    idregional              int8              NOT NULL,
    idlogtrans              int8,
    idtramite               int8,
    idarea                  int8,
    nro_recibo              int8              NOT NULL,
    serie_recibo            varchar(5)        NOT NULL,
    monto                   decimal(18, 0),
    literal_monto           varchar(300),
    dato_recibo             varchar(1000),
    cantidad                integer,
    fecha_emision_recibo    timestamp,
    estado_recibido         varchar(4),
    origen                  varchar(50),
    estado                  varchar(2),
    CONSTRAINT "PK266" PRIMARY KEY (idrecibo)
)
;



-- 
-- TABLE: recibodeposito 
--

CREATE TABLE recibodeposito(
    idrecibodeposito    int8              NOT NULL,
    idrecibo            int8              NOT NULL,
    iddeposito          int8              NOT NULL,
    idlogtrans          int8,
    monto               decimal(18, 0),
    CONSTRAINT "PK118" PRIMARY KEY (idrecibodeposito)
)
;



-- 
-- TABLE: recurso 
--

CREATE TABLE recurso(
    idrecurso             int8           NOT NULL,
    idevento              int8           NOT NULL,
    iddetalleoposicion    int8           NOT NULL,
    idlogtrans            int8,
    numero_resolucion     int4,
    fecha_resolucion      timestamp,
    tipo                  varchar(3),
    resolucion            varchar(50),
    CONSTRAINT "PK152" PRIMARY KEY (idrecurso)
)
;



-- 
-- TABLE: referenciasolicitud 
--

CREATE TABLE referenciasolicitud(
    idreferenciasolicitud    int8            NOT NULL,
    idtramite                int8,
    idlogtrans               int8,
    tipo_tramite             varchar(4),
    campo_uno                varchar(300),
    campo_dos                varchar(300),
    campo_tres               varchar(300),
    campo_cuatro             varchar(300),
    campo_cinco              varchar(300),
    campo_seis               varchar(300),
    campo_siete              varchar(300),
    campo_ocho               varchar(300),
    campo_nueve              varchar(300),
    campo_diez               varchar(300),
    CONSTRAINT "PK154" PRIMARY KEY (idreferenciasolicitud)
)
;



-- 
-- TABLE: regional 
--

CREATE TABLE regional(
    idregional                  int8            NOT NULL,
    idlogtrans                  int8,
    central                     boolean,
    nombre                      varchar(100),
    direccion                   varchar(200),
    telefono                    varchar(10),
    fax                         varchar(10),
    tipo_ciudad_notificacion    varchar(4),
    estado                      varchar(2),
    CONSTRAINT "PK8" PRIMARY KEY (idregional)
)
;



-- 
-- TABLE: renovacion 
--

CREATE TABLE renovacion(
    idrenovacion                    int8            NOT NULL,
    idrenovaciontramite             int8            NOT NULL,
    id_tramite_renovado             int8,
    idlogtrans                      int8,
    numero_clase_niza_actual        integer,
    lista_producto_actual           text,
    numero_clase_niza_renovacion    integer,
    lista_producto_renovacion       text,
    version_clase_niza              varchar(10),
    fecha_concesion                 timestamp,
    fecha_renovacion                timestamp,
    numero_registro_renovacion      integer,
    serie_registro_renovacion       integer,
    orden_renovacion                varchar(100),
    observacion                     text,
    gestion_renovacion              integer,
    documento_resolucion            varchar(400),
    numero_registro                 integer,
    serie_registro                  varchar(20),
    sr_manual                       integer,
    gestion_sr_manual               integer,
    fecha_registro_manual           timestamp,
    CONSTRAINT "PK139" PRIMARY KEY (idrenovacion)
)
;



-- 
-- TABLE: resolucion 
--

CREATE TABLE resolucion(
    idresolucion              int8           NOT NULL,
    iddetalleoposicion        int8           NOT NULL,
    idlogtrans                int8,
    numero_resolucion         int4,
    fecha                     timestamp,
    resolucion_cancelacion    varchar(50),
    resolucion_oposicion      varchar(50),
    resolucion_signo          varchar(50),
    CONSTRAINT "PK146" PRIMARY KEY (idresolucion)
)
;



-- 
-- TABLE: rol 
--

CREATE TABLE rol(
    idrol          int8           NOT NULL,
    idlogtrans     int8,
    descripcion    varchar(50),
    orden          int4,
    estado         varchar(2),
    CONSTRAINT "PK122" PRIMARY KEY (idrol)
)
;



-- 
-- TABLE: rolpagina 
--

CREATE TABLE rolpagina(
    idrolpagina    int8          NOT NULL,
    idrol          int8          NOT NULL,
    idpagina       int8          NOT NULL,
    idlogtrans     int8,
    orden          int4,
    estado         varchar(2),
    CONSTRAINT "PK127" PRIMARY KEY (idrolpagina)
)
;



-- 
-- TABLE: seguimiento 
--

CREATE TABLE seguimiento(
    idseguimiento             int8            NOT NULL,
    idtramite                 int8            NOT NULL,
    idusuario                 int8            NOT NULL,
    idetapaplazo              int8            NOT NULL,
    idlogtrans                int8,
    sm                        int8,
    nro_publicacion           int8,
    usuario_dos               int8,
    fecha_recepcion           timestamp,
    fecha_fin                 date,
    plazo_real                int4,
    observacion               varchar(500),
    total_tiempo              int8,
    estado_etapa              varchar(4),
    ubicacion                 varchar(4),
    seguimiento_cerrado       boolean,
    historico                 boolean,
    fecha_recepcion_manual    timestamp,
    fecha_fin_manual          timestamp,
    estado_seguimiento        varchar(2),
    CONSTRAINT "PK89" PRIMARY KEY (idseguimiento)
)
;



-- 
-- TABLE: sistema 
--

CREATE TABLE sistema(
    idsistema      int8           NOT NULL,
    idlogtrans     int8,
    descripcion    varchar(50),
    estado         varchar(2),
    CONSTRAINT "PK124" PRIMARY KEY (idsistema)
)
;



-- 
-- TABLE: smdetallepublicacion 
--

CREATE TABLE smdetallepublicacion(
    iddetallepublicacion    int8             NOT NULL,
    idsignomarca            int8             NOT NULL,
    idpublicacion           int8             NOT NULL,
    idlogtrans              int8,
    numero_publicacion      int8,
    sm                      int8,
    sm_desc                 varchar(30),
    fecha_ingreso           timestamp,
    marca                   varchar(1000),
    clase                   int4,
    tipo_signo              varchar(4),
    tipo_marca              varchar(4),
    productos               text,
    descripcion_signo       varchar(2000),
    nombretitular           varchar(1000),
    pais_titular            varchar(4),
    direccion_titular       varchar(1000),
    nombre_apoderado        varchar(1000),
    direccion_apoderado     varchar(1000),
    numero_seccion          int4,
    subseccion              varchar(50),
    publicado               int4,
    estado                  varchar(2),
    CONSTRAINT "PK5_1" PRIMARY KEY (iddetallepublicacion)
)
;



-- 
-- TABLE: smdominio 
--

CREATE TABLE smdominio(
    iddominio       int8            NOT NULL,
    idlogtrans      int8,
    dominio         varchar(100),
    codigo          varchar(4),
    dominiopadre    varchar(100),
    nombre          varchar(100),
    descripcion     varchar(255),
    orden           int4,
    fechainicio     timestamp,
    fechafin        timestamp,
    estado          varchar(2),
    CONSTRAINT "PK12_1" PRIMARY KEY (iddominio)
)
;



-- 
-- TABLE: smelementoformulariotramite 
--

CREATE TABLE smelementoformulariotramite(
    idformulariotramite            int8            NOT NULL,
    idelementoformulariotramite    int8            NOT NULL,
    idpadre                        int8,
    idlogtrans                     int8,
    pestania                       varchar(150),
    seccion                        int4,
    fila                           int4,
    tipoelemento                   varchar(50),
    nombreelemento                 text,
    orden                          int4,
    fechainicio                    timestamp,
    fechafin                       timestamp,
    vigente                        boolean,
    usuario                        int8,
    fechaultimamodificacion        timestamp,
    CONSTRAINT "PK239" PRIMARY KEY (idelementoformulariotramite)
)
;



-- 
-- TABLE: smformulariotramite 
--

CREATE TABLE smformulariotramite(
    idformulariotramite        int8            NOT NULL,
    idlogtrans                 int8,
    idflujoetapa               int8            NOT NULL,
    codigo                     varchar(10),
    nombreformulario           varchar(100),
    fechainicio                timestamp,
    fechafin                   timestamp,
    vigente                    boolean,
    usuario                    int8,
    fechaultimamodificacion    timestamp,
    CONSTRAINT "PK238" PRIMARY KEY (idformulariotramite)
)
;



-- 
-- TABLE: smimagen 
--

CREATE TABLE smimagen(
    idimagen      int8     NOT NULL,
    idlogotipo    int8     NOT NULL,
    idlogtrans    int8,
    imagen        bytea,
    CONSTRAINT "PK255" PRIMARY KEY (idimagen)
)
;



-- 
-- TABLE: smlogotipo 
--

CREATE TABLE smlogotipo(
    idlogotipo      int8            NOT NULL,
    idsignomarca    int8            NOT NULL,
    idlogtrans      int8,
    urllogotipo     varchar(250),
    principal       boolean,
    CONSTRAINT "PK176_1" PRIMARY KEY (idlogotipo)
)
;



-- 
-- TABLE: smmodificacion 
--

CREATE TABLE smmodificacion(
    idmodificacion            int8             NOT NULL,
    idtramite                 int8             NOT NULL,
    idlogtrans                int8,
    idrecibo                  int8,
    codigo                    int8,
    codigo_descripcion        varchar(30),
    sm_descripcion            int8,
    estado_modificacion       varchar(4),
    ubicacion                 varchar(4),
    numero_registro           int8,
    serie_registro            varchar(5),
    numero_renovacion         int8,
    serie_renovacion          varchar(5),
    numero_publicacion        int8,
    titular                   varchar(1000),
    domicilio                 varchar(1000),
    signo                     varchar(1000),
    clase                     int4,
    tipo_marca                varchar(4),
    marca_acomp               varchar(1000),
    reg_lc                    int8,
    serie_lc                  varchar(5),
    fecha_lc                  timestamp        NOT NULL,
    nuevo_domicilio           varchar(1000),
    nueva_nacionalidad        varchar(4),
    nuevo_departamento        varchar(4),
    numero_resolucion         int8,
    gestion_resolucion        int4,
    fecha_resolucion          timestamp        NOT NULL,
    documento_resolucion      varchar(4000),
    obser_resolucion          varchar(500),
    fusion_participante       varchar(5000),
    luinicio                  timestamp        NOT NULL,
    lu_fin                    timestamp        NOT NULL,
    fecha_ultima_operacion    timestamp,
    usuario                   varchar(100),
    titular_signo             varchar(1000),
    nacionalidad_signo        varchar(5),
    departamento_signo        varchar(4),
    domicilio_signo           varchar(1000),
    telefono_signo            varchar(1000),
    fax_signo                 varchar(1000),
    email_signo               varchar(1000),
    tipo_modificacion         varchar(4),
    CONSTRAINT "PK6_1" PRIMARY KEY (idmodificacion)
)
;



-- 
-- TABLE: smpodertramite 
--

CREATE TABLE smpodertramite(
    idpodertramite       int8    NOT NULL,
    idtramite            int8    NOT NULL,
    idpoderdepositado    int8    NOT NULL,
    idlogtrans           int8,
    CONSTRAINT "PK16_1" PRIMARY KEY (idpodertramite)
)
;



-- 
-- TABLE: smprioridadpreferencia 
--

CREATE TABLE smprioridadpreferencia(
    idprioridadpreferencia        int8            NOT NULL,
    idsignomarca                  int8            NOT NULL,
    idlogtrans                    int8,
    "CodigoClasificador"          varchar(4),
    tipo_preferencia_prioridad    varchar(50),
    numero_prioridad              varchar(20),
    oficina_prioridad             varchar(255),
    fechaemision                  timestamp,
    lugar                         varchar(100),
    numero                        varchar(255),
    estado                        varchar(2),
    CONSTRAINT "PK31" PRIMARY KEY (idprioridadpreferencia)
)
;



-- 
-- TABLE: smpublicacion 
--

CREATE TABLE smpublicacion(
    idpublicacion         int8              NOT NULL,
    idlogtrans            int8,
    numero_gaceta         numeric(10, 0),
    fecha_publicacion     timestamp         NOT NULL,
    mes                   varchar(100),
    gestion               int4,
    inicio                int8,
    fin                   int8,
    total                 int8,
    observacion           text,
    estado_publicacion    varchar(4),
    CONSTRAINT "PK4_1" PRIMARY KEY (idpublicacion)
)
;



-- 
-- TABLE: smregistro 
--

CREATE TABLE smregistro(
    idregistro              int8             NOT NULL,
    idsignomarca            int8             NOT NULL,
    idlogtrans              int8,
    nro_registro            int8             NOT NULL,
    serie                   varchar(5)       NOT NULL,
    sm                      int8,
    sm_descripcion          varchar(20),
    fecha_ingreso           timestamp,
    idtipo_resolucion       int4,
    documento_resolucion    varchar(4000),
    signo                   varchar(1000),
    tipo_signo              varchar(4),
    tipo_marca              varchar(4),
    clase                   int4,
    numero_resolucion       int8,
    gestion_resolucion      int4,
    fecha_registro          timestamp        NOT NULL,
    numero_publicacion      int8,
    fecha_publicacion       timestamp        NOT NULL,
    numero_gaceta           int8,
    fecha_primer_uso        timestamp        NOT NULL,
    titular                 varchar(1000),
    direccion_titular       varchar(1000),
    pais_titular            varchar(4),
    nombre_apoderado        varchar(1000),
    direccion_apoderado     varchar(1000),
    lista_productos         text,
    estado_registro         varchar(2),
    CONSTRAINT "PK1" PRIMARY KEY (idregistro)
)
;



-- 
-- TABLE: smsignoclaseniza 
--

CREATE TABLE smsignoclaseniza(
    idsignoclaseniza     int8            NOT NULL,
    idsignomarca         int8            NOT NULL,
    idclaseniza          int8            NOT NULL,
    indlogtrans          int8,
    numero_clase_niza    varchar(10),
    lista_producto       text,
    archivo_producto     varchar(255),
    estado               varchar(2),
    CONSTRAINT "PK132" PRIMARY KEY (idsignoclaseniza)
)
;



-- 
-- TABLE: smsignomarca 
--

CREATE TABLE smsignomarca(
    idsignomarca            int8             NOT NULL,
    idlogtrans              int8,
    sm                      int8,
    signo                   varchar(1000),
    tipo_genero             varchar(4),
    descripcion_signo       varchar(2000),
    descripcion_logotipo    varchar(2000),
    ubicacion               varchar(4),
    estado_marca            varchar(4),
    entregado_usuario       boolean,
    estado                  varchar(4),
    CONSTRAINT "PK2_1" PRIMARY KEY (idsignomarca)
)
;



-- 
-- TABLE: smsolicitanteapoderado 
--

CREATE TABLE smsolicitanteapoderado(
    idsolicitanteapoderado              int8             NOT NULL,
    idsolicitanteapoderado_modificar    char(10),
    idlogtrans                          int8,
    poder                               varchar(60),
    tipo_titular                        varchar(50),
    nombre_razonsocial                  varchar(2000),
    primer_apellido                     varchar(50),
    segundo_apellido                    varchar(50),
    numero_documento                    int4,
    tipo_documento                      varchar(10),
    pais                                varchar(4),
    lugar_expedicion                    varchar(10),
    genero                              char(3),
    solicitud_departamento              varchar(100),
    departamento                        varchar(20),
    estado                              varchar(10),
    CONSTRAINT "PK12" PRIMARY KEY (idsolicitanteapoderado)
)
;



-- 
-- TABLE: smsolicitantetramite 
--

CREATE TABLE smsolicitantetramite(
    idsolicitantetramite      int8          NOT NULL,
    idsolicitanteapoderado    int8          NOT NULL,
    idtramite                 int8          NOT NULL,
    idlogtrans                int8,
    tipo_persona_tramite      varchar(4),
    CONSTRAINT "PK47" PRIMARY KEY (idsolicitantetramite)
)
;



-- 
-- TABLE: smtiposigno 
--

CREATE TABLE smtiposigno(
    idtiposigno         int8            NOT NULL,
    idsignomarca        int8            NOT NULL,
    idlogtrans          int8,
    idtramite           int8            NOT NULL,
    tipo_signo          varchar(4),
    descripcion_otro    varchar(100),
    CONSTRAINT "PK176_1_1" PRIMARY KEY (idtiposigno)
)
;



-- 
-- TABLE: smtramite 
--

CREATE TABLE smtramite(
    idtramite          int8           NOT NULL,
    idsignomarca       int8           NOT NULL,
    idregional         int8           NOT NULL,
    nroformulario      varchar(20),
    fecha_creacion     timestamp      NOT NULL,
    fecha_solicitud    timestamp      NOT NULL,
    fecha_ingreso      timestamp,
    tipo_tramite       varchar(4),
    poder              varchar(10),
    estado             varchar(2),
    CONSTRAINT "PK3_1" PRIMARY KEY (idtramite)
)
;



-- 
-- TABLE: solicitudrenovacion 
--

CREATE TABLE solicitudrenovacion(
    idrenovaciontramite            int8             NOT NULL,
    idtramite                      int8             NOT NULL,
    id_recibo_solicitud            int8,
    id_recibo_titulo               int8,
    idlogtrans                     int8,
    signo_distintivo               varchar(250),
    tipo_genero                    varchar(4),
    numero_clase_niza              varchar(20),
    registro                       varchar(20),
    serie_registro                 varchar(4),
    fecha_otorgacion_marca         timestamp,
    numero_ultima_renovacion       varchar(20),
    serie_ultima_renovacion        varchar(10),
    numero_penultima_renovacion    varchar(20),
    serie_penultima_renovacion     varchar(10),
    clase_niza_actual              varchar(20),
    lista_productos_servicios      text,
    clase_niza_reclasificacion     varchar(20),
    lista_productos_limitacion     text,
    estado_renovacion              varchar(4),
    observacion                    varchar(1000),
    fecha_solicitud                timestamp,
    ubicacion                      varchar(4),
    numero_recibo                  int4,
    serie_recibo                   varchar(5),
    numero_titulo                  int4,
    serie_titulo                   varchar(5),
    estado                         varchar(2),
    CONSTRAINT "PK140" PRIMARY KEY (idrenovaciontramite)
)
;



-- 
-- TABLE: tasa 
--

CREATE TABLE tasa(
    idtasa          int8              NOT NULL,
    idlogtrans      int8,
    tipo_tramite    varchar(4),
    descripcion     varchar(250),
    costo           decimal(18, 0),
    descuento       decimal(18, 0),
    gestion         integer,
    estado          varchar(2),
    CONSTRAINT "PK236" PRIMARY KEY (idtasa)
)
;



-- 
-- TABLE: usuario 
--

CREATE TABLE usuario(
    idusuario             int8           NOT NULL,
    idlogtrans            int8,
    nombre                varchar(50),
    ap_paterno            varchar(50),
    ap_materno            varchar(50),
    login                 varchar(50),
    password              varchar(50),
    cargo                 varchar(30),
    estado                varchar(2),
    idregional            int4,
    numero_documento      varchar(50),
    tipo_documento        varchar(50),
    lugar_expedicion      varchar(50),
    correo_electronico    varchar(70),
    CONSTRAINT "PK119" PRIMARY KEY (idusuario)
)
;



-- 
-- TABLE: usuariopagina 
--

CREATE TABLE usuariopagina(
    idusuariopagina    int8           NOT NULL,
    idusuario          int8           NOT NULL,
    idpagina           int8           NOT NULL,
    idlogtrans         int8,
    acceso             varchar(20),
    habilitado         varchar(2),
    fecha_vig_ini      timestamp,
    fecha_vig_fin      timestamp,
    hora_vig_ini       time,
    hora_vig_fin       time,
    muestra            boolean,
    estado             varchar(2),
    CONSTRAINT "PK126" PRIMARY KEY (idusuariopagina)
)
;



-- 
-- TABLE: usuariorol 
--

CREATE TABLE usuariorol(
    idusuariorol    int8    NOT NULL,
    idusuario       int8    NOT NULL,
    idrol           int8    NOT NULL,
    idsistema       int8    NOT NULL,
    idlogtrans      int8,
    CONSTRAINT "PK125" PRIMARY KEY (idusuariorol)
)
;



-- 
-- TABLE: valortramiteventanillaeforma 
--

CREATE TABLE valortramiteventanillaeforma(
    idvalortramiteventanillaeforma    int8            NOT NULL,
    iddatotramiteventanillaeforma     int8            NOT NULL,
    idlogtrans                        int8,
    presento                          int8,
    codigo                            varchar(5),
    dominio_opcion                    varchar(100),
    observacion                       varchar(250),
    CONSTRAINT "PK161" PRIMARY KEY (idvalortramiteventanillaeforma)
)
;



-- 
-- TABLE: actividad 
--

ALTER TABLE actividad ADD CONSTRAINT "Refestadooposicion150" 
    FOREIGN KEY (idestadooposicion)
    REFERENCES estadooposicion(idestadooposicion)
;


-- 
-- TABLE: actividadplazo 
--

ALTER TABLE actividadplazo ADD CONSTRAINT "Refactividad152" 
    FOREIGN KEY (idactividad)
    REFERENCES actividad(idactividad)
;


-- 
-- TABLE: correlativoregional 
--

ALTER TABLE correlativoregional ADD CONSTRAINT "Refcorrelativo129" 
    FOREIGN KEY (idcorrelativo)
    REFERENCES correlativo(idcorrelativo)
;

ALTER TABLE correlativoregional ADD CONSTRAINT "Refregional130" 
    FOREIGN KEY (idregional)
    REFERENCES regional(idregional)
;


-- 
-- TABLE: datocontacto 
--

ALTER TABLE datocontacto ADD CONSTRAINT "Refsmsolicitanteapoderado27" 
    FOREIGN KEY (idsolicitanteapoderado)
    REFERENCES smsolicitanteapoderado(idsolicitanteapoderado)
;


-- 
-- TABLE: datotramiteventanillaeforma 
--

ALTER TABLE datotramiteventanillaeforma ADD CONSTRAINT "Refseguimiento161" 
    FOREIGN KEY (idseguimiento)
    REFERENCES seguimiento(idseguimiento)
;


-- 
-- TABLE: detalleoposicion 
--

ALTER TABLE detalleoposicion ADD CONSTRAINT "Refoposicion146" 
    FOREIGN KEY (idoposicion)
    REFERENCES oposicion(idoposicion)
;


-- 
-- TABLE: detalletitulo 
--

ALTER TABLE detalletitulo ADD CONSTRAINT "Refrecibo159" 
    FOREIGN KEY (idrecibo)
    REFERENCES recibo(idrecibo)
;


-- 
-- TABLE: digarchivodocumento 
--

ALTER TABLE digarchivodocumento ADD CONSTRAINT "Refdocumento136" 
    FOREIGN KEY (iddocumento)
    REFERENCES documento(iddocumento)
;


-- 
-- TABLE: direcciondenotificacion 
--

ALTER TABLE direcciondenotificacion ADD CONSTRAINT "Refsmtramite132" 
    FOREIGN KEY (idtramite)
    REFERENCES smtramite(idtramite)
;


-- 
-- TABLE: dosificacion 
--

ALTER TABLE dosificacion ADD CONSTRAINT "Refregional126" 
    FOREIGN KEY (idregional)
    REFERENCES regional(idregional)
;


-- 
-- TABLE: dosificaciontasa 
--

ALTER TABLE dosificaciontasa ADD CONSTRAINT "Reftasa127" 
    FOREIGN KEY (idtasa)
    REFERENCES tasa(idtasa)
;

ALTER TABLE dosificaciontasa ADD CONSTRAINT "Refdosificacion128" 
    FOREIGN KEY (iddosificacion)
    REFERENCES dosificacion(iddosificacion)
;


-- 
-- TABLE: evento 
--

ALTER TABLE evento ADD CONSTRAINT "Refdetalleoposicion148" 
    FOREIGN KEY (iddetalleoposicion)
    REFERENCES detalleoposicion(iddetalleoposicion)
;

ALTER TABLE evento ADD CONSTRAINT "Refactividad149" 
    FOREIGN KEY (idactividad)
    REFERENCES actividad(idactividad)
;


-- 
-- TABLE: fechalimite 
--

ALTER TABLE fechalimite ADD CONSTRAINT "Refdetalleoposicion155" 
    FOREIGN KEY (iddetalleoposicion)
    REFERENCES detalleoposicion(iddetalleoposicion)
;

ALTER TABLE fechalimite ADD CONSTRAINT "Refevento156" 
    FOREIGN KEY (idevento)
    REFERENCES evento(idevento)
;


-- 
-- TABLE: flujoactividad 
--

ALTER TABLE flujoactividad ADD CONSTRAINT "Refactividad151" 
    FOREIGN KEY (idactividad)
    REFERENCES actividad(idactividad)
;


-- 
-- TABLE: flujoetapa 
--

ALTER TABLE flujoetapa ADD CONSTRAINT "Refflujo164" 
    FOREIGN KEY (idflujo)
    REFERENCES flujo(idflujo)
;


-- 
-- TABLE: historial 
--

ALTER TABLE historial ADD CONSTRAINT "Refsmtramite137" 
    FOREIGN KEY (idtramite)
    REFERENCES smtramite(idtramite)
;


-- 
-- TABLE: historialopo 
--

ALTER TABLE historialopo ADD CONSTRAINT "Refoposicion145" 
    FOREIGN KEY (idoposicion)
    REFERENCES oposicion(idoposicion)
;


-- 
-- TABLE: logtrans 
--

ALTER TABLE logtrans ADD CONSTRAINT "Refusuario143" 
    FOREIGN KEY (idusuario)
    REFERENCES usuario(idusuario)
;


-- 
-- TABLE: nolaboral 
--

ALTER TABLE nolaboral ADD CONSTRAINT "Refregional117" 
    FOREIGN KEY (idregional)
    REFERENCES regional(idregional)
;


-- 
-- TABLE: notificaciondocumento 
--

ALTER TABLE notificaciondocumento ADD CONSTRAINT "Refnotificacion134" 
    FOREIGN KEY (idnotificacion)
    REFERENCES notificacion(idnotificacion)
;

ALTER TABLE notificaciondocumento ADD CONSTRAINT "Refdocumento135" 
    FOREIGN KEY (iddocumento)
    REFERENCES documento(iddocumento)
;


-- 
-- TABLE: observaciontramite 
--

ALTER TABLE observaciontramite ADD CONSTRAINT "Refsmtramite64" 
    FOREIGN KEY (idtramite)
    REFERENCES smtramite(idtramite)
;


-- 
-- TABLE: recibo 
--

ALTER TABLE recibo ADD CONSTRAINT "Refregional165" 
    FOREIGN KEY (idregional)
    REFERENCES regional(idregional)
;

ALTER TABLE recibo ADD CONSTRAINT "Reftasa107" 
    FOREIGN KEY (idtasa)
    REFERENCES tasa(idtasa)
;


-- 
-- TABLE: recibodeposito 
--

ALTER TABLE recibodeposito ADD CONSTRAINT "Refrecibo108" 
    FOREIGN KEY (idrecibo)
    REFERENCES recibo(idrecibo)
;

ALTER TABLE recibodeposito ADD CONSTRAINT "Refdeposito110" 
    FOREIGN KEY (iddeposito)
    REFERENCES deposito(iddeposito)
;


-- 
-- TABLE: recurso 
--

ALTER TABLE recurso ADD CONSTRAINT "Refevento153" 
    FOREIGN KEY (idevento)
    REFERENCES evento(idevento)
;

ALTER TABLE recurso ADD CONSTRAINT "Refdetalleoposicion154" 
    FOREIGN KEY (iddetalleoposicion)
    REFERENCES detalleoposicion(iddetalleoposicion)
;


-- 
-- TABLE: renovacion 
--

ALTER TABLE renovacion ADD CONSTRAINT "Refsolicitudrenovacion142" 
    FOREIGN KEY (idrenovaciontramite)
    REFERENCES solicitudrenovacion(idrenovaciontramite)
;


-- 
-- TABLE: resolucion 
--

ALTER TABLE resolucion ADD CONSTRAINT "Refdetalleoposicion147" 
    FOREIGN KEY (iddetalleoposicion)
    REFERENCES detalleoposicion(iddetalleoposicion)
;


-- 
-- TABLE: rolpagina 
--

ALTER TABLE rolpagina ADD CONSTRAINT "Refrol124" 
    FOREIGN KEY (idrol)
    REFERENCES rol(idrol)
;

ALTER TABLE rolpagina ADD CONSTRAINT "Refpagina125" 
    FOREIGN KEY (idpagina)
    REFERENCES pagina(idpagina)
;


-- 
-- TABLE: seguimiento 
--

ALTER TABLE seguimiento ADD CONSTRAINT "Refsmtramite65" 
    FOREIGN KEY (idtramite)
    REFERENCES smtramite(idtramite)
;

ALTER TABLE seguimiento ADD CONSTRAINT "Refusuario115" 
    FOREIGN KEY (idusuario)
    REFERENCES usuario(idusuario)
;

ALTER TABLE seguimiento ADD CONSTRAINT "Refetapaplazo116" 
    FOREIGN KEY (idetapaplazo)
    REFERENCES etapaplazo(idetapaplazo)
;


-- 
-- TABLE: smdetallepublicacion 
--

ALTER TABLE smdetallepublicacion ADD CONSTRAINT "Refsmsignomarca100" 
    FOREIGN KEY (idsignomarca)
    REFERENCES smsignomarca(idsignomarca)
;

ALTER TABLE smdetallepublicacion ADD CONSTRAINT "Refsmpublicacion101" 
    FOREIGN KEY (idpublicacion)
    REFERENCES smpublicacion(idpublicacion)
;


-- 
-- TABLE: smelementoformulariotramite 
--

ALTER TABLE smelementoformulariotramite ADD CONSTRAINT "Refsmformulariotramite54" 
    FOREIGN KEY (idformulariotramite)
    REFERENCES smformulariotramite(idformulariotramite)
;


-- 
-- TABLE: smimagen 
--

ALTER TABLE smimagen ADD CONSTRAINT "Refsmlogotipo133" 
    FOREIGN KEY (idlogotipo)
    REFERENCES smlogotipo(idlogotipo)
;


-- 
-- TABLE: smlogotipo 
--

ALTER TABLE smlogotipo ADD CONSTRAINT "Refsmsignomarca35" 
    FOREIGN KEY (idsignomarca)
    REFERENCES smsignomarca(idsignomarca)
;


-- 
-- TABLE: smmodificacion 
--

ALTER TABLE smmodificacion ADD CONSTRAINT "Refsmtramite91" 
    FOREIGN KEY (idtramite)
    REFERENCES smtramite(idtramite)
;


-- 
-- TABLE: smpodertramite 
--

ALTER TABLE smpodertramite ADD CONSTRAINT "Refsmtramite30" 
    FOREIGN KEY (idtramite)
    REFERENCES smtramite(idtramite)
;

ALTER TABLE smpodertramite ADD CONSTRAINT "Refpoderdepositado118" 
    FOREIGN KEY (idpoderdepositado)
    REFERENCES poderdepositado(idpoderdepositado)
;


-- 
-- TABLE: smprioridadpreferencia 
--

ALTER TABLE smprioridadpreferencia ADD CONSTRAINT "Refsmsignomarca43" 
    FOREIGN KEY (idsignomarca)
    REFERENCES smsignomarca(idsignomarca)
;


-- 
-- TABLE: smregistro 
--

ALTER TABLE smregistro ADD CONSTRAINT "Refsmsignomarca111" 
    FOREIGN KEY (idsignomarca)
    REFERENCES smsignomarca(idsignomarca)
;


-- 
-- TABLE: smsignoclaseniza 
--

ALTER TABLE smsignoclaseniza ADD CONSTRAINT "Refsmsignomarca131" 
    FOREIGN KEY (idsignomarca)
    REFERENCES smsignomarca(idsignomarca)
;

ALTER TABLE smsignoclaseniza ADD CONSTRAINT "Refclaseniza160" 
    FOREIGN KEY (idclaseniza)
    REFERENCES claseniza(idclaseniza)
;


-- 
-- TABLE: smsolicitantetramite 
--

ALTER TABLE smsolicitantetramite ADD CONSTRAINT "Refsmsolicitanteapoderado104" 
    FOREIGN KEY (idsolicitanteapoderado)
    REFERENCES smsolicitanteapoderado(idsolicitanteapoderado)
;

ALTER TABLE smsolicitantetramite ADD CONSTRAINT "Refsmtramite25" 
    FOREIGN KEY (idtramite)
    REFERENCES smtramite(idtramite)
;


-- 
-- TABLE: smtiposigno 
--

ALTER TABLE smtiposigno ADD CONSTRAINT "Refsmsignomarca36" 
    FOREIGN KEY (idsignomarca)
    REFERENCES smsignomarca(idsignomarca)
;


-- 
-- TABLE: smtramite 
--

ALTER TABLE smtramite ADD CONSTRAINT "Refsmsignomarca21" 
    FOREIGN KEY (idsignomarca)
    REFERENCES smsignomarca(idsignomarca)
;

ALTER TABLE smtramite ADD CONSTRAINT "Refregional113" 
    FOREIGN KEY (idregional)
    REFERENCES regional(idregional)
;


-- 
-- TABLE: solicitudrenovacion 
--

ALTER TABLE solicitudrenovacion ADD CONSTRAINT "Refsmtramite157" 
    FOREIGN KEY (idtramite)
    REFERENCES smtramite(idtramite)
;


-- 
-- TABLE: usuariopagina 
--

ALTER TABLE usuariopagina ADD CONSTRAINT "Refusuario122" 
    FOREIGN KEY (idusuario)
    REFERENCES usuario(idusuario)
;

ALTER TABLE usuariopagina ADD CONSTRAINT "Refpagina123" 
    FOREIGN KEY (idpagina)
    REFERENCES pagina(idpagina)
;


-- 
-- TABLE: usuariorol 
--

ALTER TABLE usuariorol ADD CONSTRAINT "Refrol120" 
    FOREIGN KEY (idrol)
    REFERENCES rol(idrol)
;

ALTER TABLE usuariorol ADD CONSTRAINT "Refsistema121" 
    FOREIGN KEY (idsistema)
    REFERENCES sistema(idsistema)
;

ALTER TABLE usuariorol ADD CONSTRAINT "Refusuario119" 
    FOREIGN KEY (idusuario)
    REFERENCES usuario(idusuario)
;


-- 
-- TABLE: valortramiteventanillaeforma 
--

ALTER TABLE valortramiteventanillaeforma ADD CONSTRAINT "Refdatotramiteventanillaeforma162" 
    FOREIGN KEY (iddatotramiteventanillaeforma)
    REFERENCES datotramiteventanillaeforma(iddatotramiteventanillaeforma)
;


