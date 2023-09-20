--
-- ER/Studio 8.0 SQL Code Generation
-- Company :      Microsoft
-- Project :      ModeloSENAPI.DM1
-- Author :       DELL
--
-- Date Created : Friday, March 17, 2017 10:17:12
-- Target DBMS : PostgreSQL 8.0
--

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
    idclaseniza            int8           NOT NULL,
    idlogtrans             int8,
    numero_clase_niza      int4,
    proteccion             text,
    fecha_inicio           timestamp,
    fecha_fin              timestamp,
    tipo_niza              varchar(4),
    nota_tipo_claseniza    text,
    numero_edicion         varchar(10),
    version                varchar(10),
    estado                 varchar(2),
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
-- TABLE: datoelementoformulario 
--

CREATE TABLE datoelementoformulario(
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
    CONSTRAINT "PK239_1" PRIMARY KEY (iddatoelementoformulario)
)
;



-- 
-- TABLE: deposito 
--

CREATE TABLE deposito(
    iddeposito                 int8              NOT NULL,
    idlogtrans                 int8,
    banco                      varchar(4),
    numero_deposito            int8,
    fecha_deposito             timestamp,
    monto                      numeric(18, 2),
    depos_cod_dep              integer,
    depos_cod_agencia          integer,
    nombre_depositante         varchar(300),
    cod_agencia                varchar(50),
    cod_depositante            varchar(50),
    saldo                      numeric(18, 2),
    fecha_registro_deposito    timestamp,
    sucursal_banco             varchar(10),
    estado                     varchar(2),
    idusuario                  int8,
    CONSTRAINT "PK233" PRIMARY KEY (iddeposito)
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
-- TABLE: dominio 
--

CREATE TABLE dominio(
    iddominio       int8            NOT NULL,
    idlogtrans      int8,
    dominio         varchar(100),
    codigo          varchar(5),
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
-- TABLE: dosificacion 
--

CREATE TABLE dosificacion(
    iddosificacion        int8          NOT NULL,
    idlogtrans            int8,
    valorinicio           integer,
    valorfinal            integer,
    siguiente             integer,
    gestion               integer,
    fecha_dosificacion    timestamp,
    estado                varchar(2),
    CONSTRAINT "PK175" PRIMARY KEY (iddosificacion)
)
;



-- 
-- TABLE: dosificaciontasa 
--

CREATE TABLE dosificaciontasa(
    iddosificaciontasa    int8          NOT NULL,
    iddosificacion        int8          NOT NULL,
    idtasa                int8          NOT NULL,
    idregional            int8,
    idlogtrans            int8,
    serie                 varchar(4),
    tiporecibo            varchar(4),
    estado                varchar(2),
    CONSTRAINT "PK129" PRIMARY KEY (iddosificaciontasa)
)
;



-- 
-- TABLE: elementoformulariotramite 
--

CREATE TABLE elementoformulariotramite(
    idelementoformulariotramite    int8            NOT NULL,
    idformulariotramite            int8            NOT NULL,
    idlogtrans                     int8,
    pestania                       varchar(150),
    seccion                        int4,
    fila                           int4,
    tipo_elemento                  varchar(50),
    nombre_elemento                text,
    orden                          int4,
    orden_literal                  varchar(20),
    idpadre                        int8,
    fechainicio                    timestamp,
    fechafin                       timestamp,
    respuesta                      text,
    opcion_respuesta               text,
    estado                         varchar(2),
    CONSTRAINT "PK239" PRIMARY KEY (idelementoformulariotramite)
)
;



-- 
-- TABLE: etapa 
--

CREATE TABLE etapa(
    idetapa           int8            NOT NULL,
    idlogtrans        int8,
    tipo_tramite      varchar(4),
    tipo_etapa        varchar(4),
    descripcion       varchar(150),
    fecha_creacion    timestamp,
    plazo             integer,
    estado            varchar(2),
    CONSTRAINT "PK120" PRIMARY KEY (idetapa)
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
-- TABLE: formulariotramite 
--

CREATE TABLE formulariotramite(
    idformulariotramite    int8            NOT NULL,
    idlogtrans             int8,
    codigo                 varchar(10),
    etapa                  varchar(4),
    nombreformulario       varchar(100),
    fechainicio            timestamp,
    fechafin               timestamp,
    estado                 varchar(4),
    CONSTRAINT "PK238" PRIMARY KEY (idformulariotramite)
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
-- TABLE: menu 
--

CREATE TABLE menu(
    idmenu         int8           NOT NULL,
    idsistema      int8           NOT NULL,
    idlogtrans     int8,
    descripcion    varchar(50),
    orden          int4,
    estado         varchar(2),
    CONSTRAINT "PK122" PRIMARY KEY (idmenu)
)
;



-- 
-- TABLE: menupagina 
--

CREATE TABLE menupagina(
    idmenupagina    int8          NOT NULL,
    idmenu          int8          NOT NULL,
    idpagina        int8          NOT NULL,
    idlogtrans      int8,
    orden           int4,
    estado          varchar(2),
    CONSTRAINT "PK127" PRIMARY KEY (idmenupagina)
)
;



-- 
-- TABLE: moddatoelementoformulario 
--

CREATE TABLE moddatoelementoformulario(
    iddatoelementoformulario    int8            NOT NULL,
    nombre_tabla                varchar(50),
    idseguimiento               int8            NOT NULL,
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
    CONSTRAINT "PK239_1_1" PRIMARY KEY (iddatoelementoformulario)
)
;



-- 
-- TABLE: moddirecciondenotificacion 
--

CREATE TABLE moddirecciondenotificacion(
    iddirecciondenotificacion    int8            NOT NULL,
    idmodificacion               int8,
    idlogtrans                   int8,
    ciudad_notificacion          varchar(4),
    zona_barrio                  varchar(500),
    avenida_calle                varchar(100),
    numero                       varchar(20),
    edificio                     varchar(100),
    piso                         varchar(20),
    departamento                 varchar(10),
    correo_electronico           varchar(100),
    referencia_direccion         varchar(250),
    telefono                     varchar(100),
    celular                      varchar(100),
    estado                       varchar(2),
    CONSTRAINT "PK95_1" PRIMARY KEY (iddirecciondenotificacion)
)
;



-- 
-- TABLE: modhistorial 
--

CREATE TABLE modhistorial(
    idhistorial                    int8            NOT NULL,
    idmodificacion                 int8,
    idusuario                      int8,
    idlogtrans                     int8,
    tipo                           varchar(4),
    operacion                      varchar(50),
    estado_marca_descripcion       varchar(100),
    observacion                    text,
    ubicacion_descripcion          varchar(100),
    seccion                        varchar(100),
    gestion_renovacion             integer,
    descripcion                    text,
    descripcion_lista_productos    text,
    fecha_operacion                timestamp,
    estado                         varchar(2),
    CONSTRAINT "PK136_1" PRIMARY KEY (idhistorial)
)
;



-- 
-- TABLE: modmodificacion 
--

CREATE TABLE modmodificacion(
    idmodificacion             int8             NOT NULL,
    idlogtrans                 int8,
    sigla                      varchar(5),
    numero                     int8,
    gestion                    integer,
    sm                         int8,
    fecha_ingreso              timestamp,
    nro_formulario             varchar(20),
    oficina                    varchar(4),
    numero_registro            int8,
    serie_registro             varchar(5),
    numero_renovacion          int8,
    serie_renovacion           varchar(5),
    numero_publicacion         int8,
    signo_registrado           varchar(1000),
    clase_registrado           int4,
    tipo_genero_registrado     varchar(4),
    marca_acomp                varchar(1000),
    reg_lc_registrado          int8,
    serie_lc_registrado        varchar(5),
    fecha_lc_registrado        timestamp,
    nuevo_domicilio            varchar(1000),
    nueva_nacionalidad         varchar(4),
    nuevo_pais_constitucion    varchar(4),
    nuevo_departamento         varchar(4),
    luinicio                   timestamp,
    lu_fin                     timestamp,
    fecha_ultima_operacion     timestamp,
    usuario                    int8,
    titular_signo              text,
    nacionalidad_signo         varchar(1000),
    departamento_signo         varchar(1000),
    domicilio_signo            text,
    telefono_signo             varchar(1000),
    fax_signo                  varchar(1000),
    email_signo                varchar(1000),
    tipo_modificacion          varchar(4),
    estado_modificacion        varchar(4),
    ubicacion_modificacion     varchar(4),
    lista_producto             text,
    estado                     varchar(2),
    nro_recibo                 int8,
    serie_recibo               varchar(5),
    CONSTRAINT "PK6_1" PRIMARY KEY (idmodificacion)
)
;



-- 
-- TABLE: modobservaciontramite 
--

CREATE TABLE modobservaciontramite(
    idobservaciontramite    int8             NOT NULL,
    idmodificacion          int8,
    idusuario               int8,
    idlogtrans              int8,
    editable                boolean,
    fecha_observacion       timestamp,
    etapa_tramite           varchar(50),
    descripcion             varchar(2000),
    estado                  varchar(2),
    CONSTRAINT "PK87_1" PRIMARY KEY (idobservaciontramite)
)
;



-- 
-- TABLE: modresolucion 
--

CREATE TABLE modresolucion(
    idresolucion              int8            NOT NULL,
    idmodificacion            int8,
    numero_resolucion         integer,
    gestion_resolucion        integer,
    fecha_resolucion          timestamp,
    observacion_resolucion    varchar(500),
    documento_resolucion      text,
    estado                    varchar(2),
    CONSTRAINT "PK173" PRIMARY KEY (idresolucion)
)
;



-- 
-- TABLE: modseguimiento 
--

CREATE TABLE modseguimiento(
    idseguimiento         int8            NOT NULL,
    idmodificacion        int8,
    idusuario             int8,
    idlogtrans            int8,
    sm                    int8,
    numero_publicacion    int8,
    numero_registro       int8,
    serie_registro        varchar(10),
    etapa                 varchar(4),
    fecha_recepcion       timestamp,
    fecha_fin             timestamp,
    plazo_real            int4,
    total_tiempo          int8,
    observacion           varchar(500),
    editable              boolean,
    estado                varchar(2),
    CONSTRAINT "PK89_1" PRIMARY KEY (idseguimiento)
)
;



-- 
-- TABLE: modsolicitanteapoderado 
--

CREATE TABLE modsolicitanteapoderado(
    idsolicitanteapoderado    int8             NOT NULL,
    idmodificacion            int8,
    idlogtrans                int8,
    tipo_titular              varchar(4),
    tipo_persona              varchar(4),
    nombre_razonsocial        varchar(2000),
    primer_apellido           varchar(50),
    segundo_apellido          varchar(50),
    numero_documento          varchar(1000),
    tipo_documento            varchar(4),
    lugar_expedicion          varchar(4),
    pais                      varchar(4),
    genero                    varchar(4),
    solicitud_departamento    varchar(4),
    poder                     varchar(150),
    direccion                 varchar(1000),
    telefono                  varchar(150),
    celular                   varchar(50),
    email                     varchar(150),
    fax                       varchar(100),
    estado                    varchar(2),
    CONSTRAINT "PK12_2" PRIMARY KEY (idsolicitanteapoderado)
)
;



-- 
-- TABLE: modtiposigno 
--

CREATE TABLE modtiposigno(
    idtiposigno         int8            NOT NULL,
    idmodificacion      int8            NOT NULL,
    idlogtrans          int8,
    tipo_signo          varchar(4),
    descripcion_otro    varchar(100),
    estado              varchar(2),
    CONSTRAINT "PK176_1_1_1" PRIMARY KEY (idtiposigno)
)
;



-- 
-- TABLE: modtitularlicenciatarionuevo 
--

CREATE TABLE modtitularlicenciatarionuevo(
    idtitularlicenciatario    int8             NOT NULL,
    idmodificacion            int8,
    idlogtrans                int8,
    tipo_persona              varchar(4),
    tipo_titular              varchar(4),
    nombre_razonsocial        varchar(2000),
    primer_apellido           varchar(50),
    segundo_apellido          varchar(50),
    numero_documento          varchar(1000),
    tipo_documento            varchar(4),
    lugar_expedicion          varchar(4),
    pais                      varchar(4),
    pais_constitucion         varchar(4),
    genero                    varchar(4),
    solicitud_departamento    varchar(4),
    direccion                 varchar(450),
    telefono                  varchar(50),
    celular                   varchar(50),
    email                     varchar(50),
    fax                       varchar(50),
    estado                    varchar(2),
    modificar                 boolean,
    id_referencia             int8,
    CONSTRAINT "PK12_2_2" PRIMARY KEY (idtitularlicenciatario)
)
;



-- 
-- TABLE: modtitularlicenciatarioregistrado 
--

CREATE TABLE modtitularlicenciatarioregistrado(
    idtitularlicenciatarioregistrado    int8             NOT NULL,
    idmodificacion                      int8,
    idlogtrans                          int8,
    tipo_persona_registrado             varchar(4),
    tipo_titular                        varchar(4),
    nombre_razonsocial                  varchar(2000),
    primer_apellido                     varchar(50),
    segundo_apellido                    varchar(50),
    direccion                           varchar(450),
    estado                              varchar(2),
    CONSTRAINT "PK12_2_1" PRIMARY KEY (idtitularlicenciatarioregistrado)
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
    estado         varchar(2),
    CONSTRAINT "PK121" PRIMARY KEY (idnolaboral)
)
;



-- 
-- TABLE: notificacion 
--

CREATE TABLE notificacion(
    idnotificacion                   int8             NOT NULL,
    idlogtrans                       int8,
    bloque                           int4,
    nro_exped                        int4,
    id_usuario_solicitante           int8,
    tipo_tramite_notificacion        varchar(20),
    expediente                       varchar(10),
    gestion                          integer,
    extension                        varchar(5),
    demandante                       varchar(1000),
    demandante_cod_estado            varchar(4),
    demandante_fecha_devol           timestamp,
    demandante_fecha_noti            timestamp,
    demandante_lugar_notificacion    varchar(4),
    demandante_solic                 varchar(1000),
    demandante_apod                  varchar(1000),
    demandante_fojas                 varchar(50),
    demandante_con                   varchar(200),
    demandante_direc                 varchar(1000),
    demandante_cel                   varchar(100),
    demandante_ciudad_notifi         varchar(4),
    demandado                        varchar(1000),
    demandado_cod_estado             varchar(4),
    demandado_fecha_devol            timestamp,
    demandado_fecha_noti             timestamp,
    demandado_lugar_notificacion     varchar(4),
    demandado_solic                  varchar(1000),
    demandado_apod                   varchar(500),
    demandado_fojas                  varchar(50),
    demandado_con                    varchar(200),
    demandado_direc                  varchar(1000),
    demandado_cel                    varchar(50),
    demandado_ciudad_notifi          varchar(4),
    fecha_ingreso                    timestamp,
    tipo                             integer,
    obs                              text,
    historial                        text,
    fecha_recep                      timestamp,
    obs_notifi                       text,
    id_usuario_notificador           int8,
    estado_marca                     varchar(4),
    form_noti_pre                    text,
    form_noti_cuerpo                 text,
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
-- TABLE: opoactividad 
--

CREATE TABLE opoactividad(
    idactividad           int8            NOT NULL,
    idestado              int8            NOT NULL,
    idlogtrans            int8,
    descri_idactividad    varchar(20),
    actividad             varchar(100),
    orden                 int4,
    CONSTRAINT "PK149" PRIMARY KEY (idactividad)
)
;



-- 
-- TABLE: opoactividadplazo 
--

CREATE TABLE opoactividadplazo(
    idactividadplazo        int8           NOT NULL,
    idactividad             int8           NOT NULL,
    idlogtrans              int8,
    idactividaddesc         varchar(30),
    idactividadplazodesc    varchar(30),
    plazo                   int4,
    sumarplazoanterior      int4,
    idactividadanterior     int8,
    CONSTRAINT "PK151" PRIMARY KEY (idactividadplazo)
)
;



-- 
-- TABLE: opoestado 
--

CREATE TABLE opoestado(
    idestado                    int8            NOT NULL,
    idarea                      int8,
    idlogtrans                  int8,
    descri_idestadooposicion    varchar(20),
    estado                      varchar(100),
    orden                       int4,
    CONSTRAINT "PK148" PRIMARY KEY (idestado)
)
;



-- 
-- TABLE: opoevento 
--

CREATE TABLE opoevento(
    idevento           int8             NOT NULL,
    idactividad        int8             NOT NULL,
    idoposicion        int8             NOT NULL,
    idlogtrans         int8,
    fecha              timestamp,
    observacion        varchar(1000),
    usuario            int8,
    orden_o            int4,
    fecha_operacion    timestamp,
    estado             varchar(2),
    CONSTRAINT "PK147" PRIMARY KEY (idevento)
)
;



-- 
-- TABLE: opofechalimite 
--

CREATE TABLE opofechalimite(
    idfechalimite       int8         NOT NULL,
    idevento            int8         NOT NULL,
    idactividadplazo    int8,
    idoposicion         int8         NOT NULL,
    idlogtrans          int8,
    orden               int4,
    fechalimite         timestamp,
    orden_o             int4,
    CONSTRAINT "PK153" PRIMARY KEY (idfechalimite)
)
;



-- 
-- TABLE: opoflujoactividad 
--

CREATE TABLE opoflujoactividad(
    idflujoactividad     int8          NOT NULL,
    idactividad          int8          NOT NULL,
    idlogtrans           int8,
    actividadcontigua    int8,
    tipo                 varchar(1),
    CONSTRAINT "PK150" PRIMARY KEY (idflujoactividad)
)
;



-- 
-- TABLE: opohistorial 
--

CREATE TABLE opohistorial(
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
    id_usuario         int8,
    CONSTRAINT "PK143" PRIMARY KEY (idhistorial)
)
;



-- 
-- TABLE: opomarcademandada 
--

CREATE TABLE opomarcademandada(
    idmarcademandada    int8              NOT NULL,
    idoposicion         int8              NOT NULL,
    idlogtrans          int8,
    nro_opo             numeric(18, 0),
    dmdo_public         numeric(18, 0),
    gaceta              numeric(18, 0),
    dmdo_clase          varchar(250),
    fecha_public        timestamp,
    dmdo_marca_lnv      varchar(1000),
    dmdo_sm             varchar(50),
    fec_lim             timestamp,
    verif               boolean,
    estado              varchar(2),
    CONSTRAINT "PK141" PRIMARY KEY (idmarcademandada)
)
;



-- 
-- TABLE: opomarcademandante 
--

CREATE TABLE opomarcademandante(
    idmarcademandante    int8              NOT NULL,
    idoposicion          int8              NOT NULL,
    idtramite            int8,
    idpatente            int8,
    idarea               int8,
    idmarca              int8,
    idlogtrans           int8,
    orden_opo            int4,
    dmte_reg             numeric(18, 0),
    dmte_serie           varchar(5),
    dmte_public          numeric(18, 0),
    dmte_sm              int8,
    dmte_sp              numeric(18, 0),
    dmte_marca_lnv       varchar(1000),
    dmte_uso             varchar(250),
    dmte_clase           int4,
    estado               varchar(2),
    CONSTRAINT "PK145" PRIMARY KEY (idmarcademandante)
)
;



-- 
-- TABLE: oponotificacion 
--

CREATE TABLE oponotificacion(
    idnotificacion          int8            NOT NULL,
    idmarcademandada        int8,
    idmarcademandante       int8,
    ciudad_notificacion     varchar(100),
    zona_barrio             varchar(500),
    avenida_calle           varchar(100),
    numero                  varchar(20),
    edificio                varchar(100),
    piso                    varchar(30),
    numero_departamento     varchar(10),
    referencia_direccion    varchar(250),
    email                   varchar(200),
    telefono                varchar(100),
    celular                 varchar(20),
    estado                  varchar(2),
    CONSTRAINT "PK14" PRIMARY KEY (idnotificacion)
)
;



-- 
-- TABLE: oporecurso 
--

CREATE TABLE oporecurso(
    idrecurso            int8           NOT NULL,
    idevento             int8           NOT NULL,
    idoposicion          int8           NOT NULL,
    idlogtrans           int8,
    numero_resolucion    int4,
    fecha_resolucion     timestamp,
    tipo                 varchar(3),
    resolucion           varchar(50),
    orden_op             int4,
    estado               varchar(2),
    CONSTRAINT "PK152" PRIMARY KEY (idrecurso)
)
;



-- 
-- TABLE: oporesolucion 
--

CREATE TABLE oporesolucion(
    idresolucion              int8           NOT NULL,
    idoposicion               int8           NOT NULL,
    idevento                  int8           NOT NULL,
    idlogtrans                int8,
    numero_resolucion         int4,
    fecha                     timestamp,
    resolucion_cancelacion    varchar(50),
    resolucion_oposicion      varchar(50),
    resolucion_signo          varchar(50),
    orden_o                   int4,
    estado                    varchar(2),
    CONSTRAINT "PK146" PRIMARY KEY (idresolucion)
)
;



-- 
-- TABLE: oposicion 
--

CREATE TABLE oposicion(
    idoposicion           int8             NOT NULL,
    nro_opo               int8,
    gaceta                int4,
    fecha_pub             timestamp,
    fecha_presentacion    timestamp,
    ubicacion             varchar(100),
    observacion           varchar(1000),
    estado                varchar(50),
    orden_o               int4,
    id_estado             int8,
    estadoopo             varchar(2),
    CONSTRAINT "PK12_3" PRIMARY KEY (idoposicion)
)
;



-- 
-- TABLE: oposolicitanteapoderado 
--

CREATE TABLE oposolicitanteapoderado(
    idsolicitanteapoderado    int8            NOT NULL,
    idmarcademandada          int8,
    idmarcademandante         int8,
    nombre_razonsocial        varchar(500),
    primer_apellido           varchar(100),
    segundo_apellido          varchar(100),
    numero_documento          varchar(100),
    tipo_documento            varchar(50),
    lugar_expedicion          varchar(50),
    pais                      varchar(50),
    genero                    varchar(100),
    solicitud_depa            varchar(100),
    poder                     varchar(50),
    direccion                 varchar(200),
    telefono                  varchar(70),
    celular                   varchar(70),
    email                     varchar(200),
    fax                       varchar(70),
    tiposoliapo               varchar(4),
    tipo_titular              varchar(4),
    nropoder                  varchar(70),
    tipo_persona              varchar(4),
    estado                    varchar(2),
    CONSTRAINT "PK13" PRIMARY KEY (idsolicitanteapoderado)
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
-- TABLE: poder 
--

CREATE TABLE poder(
    idpoder               int8             NOT NULL,
    idlogtrans            int8,
    tipo_poder            varchar(4),
    tipo_tramite          varchar(20),
    nro_exped             int8,
    gestion               integer,
    nro_testimonio        varchar(50),
    fecha_testimonio      timestamp,
    notario               varchar(250),
    nom_confiere_poder    varchar(1000),
    dom_confiere_poder    varchar(1000),
    apoderado             varchar(250),
    poder_revocado        varchar(5),
    obs                   varchar(1000),
    estado                varchar(2),
    CONSTRAINT "PK323" PRIMARY KEY (idpoder)
)
;



-- 
-- TABLE: poder_efectuado 
--

CREATE TABLE poder_efectuado(
    idpoder_efectuado         int8           NOT NULL,
    idlogtrans                int8,
    tipo_tramite_efectuado    varchar(20),
    nro_exped_efectuado       int8,
    gestion_efectuado         integer,
    tipo_tram_depositado      varchar(20),
    nro_exped_depositado      int8,
    gestion_depositado        integer,
    estado                    varchar(2),
    CONSTRAINT "PK324" PRIMARY KEY (idpoder_efectuado)
)
;



-- 
-- TABLE: rechistorial 
--

CREATE TABLE rechistorial(
    idhistorial                    int8            NOT NULL,
    idrecibo                       int8,
    idusuario                      int8,
    idlogtrans                     int8,
    tipo_tramite                   varchar(4),
    operacion                      varchar(50),
    estado_marca                   varchar(4),
    observacion                    text,
    ubicacion                      varchar(4),
    seccion                        varchar(100),
    gestion_renovacion             integer,
    descripcion                    text,
    descripcion_lista_productos    text,
    fecha_operacion                timestamp,
    estado                         varchar(2),
    CONSTRAINT "PK136_2" PRIMARY KEY (idhistorial)
)
;



-- 
-- TABLE: recibo 
--

CREATE TABLE recibo(
    idrecibo                       int8              NOT NULL,
    idtasa                         int8              NOT NULL,
    idlogtrans                     int8,
    numero_recibo                  int8              NOT NULL,
    serie_recibo                   varchar(5)        NOT NULL,
    fecha_emision_recibo           timestamp,
    monto                          numeric(18, 2),
    literal_monto                  varchar(300),
    concepto                       varchar(1500),
    dato_recibo                    varchar(1000),
    cantidad                       integer,
    estado_recibo                  varchar(4),
    origen                         varchar(50),
    estado                         varchar(2),
    solicitante                    varchar(1000),
    apoderado                      varchar(1000),
    numero_titulo                  int8,
    serie_titulo                   varchar(10),
    tipo_titulo                    varchar(4),
    tipo_tramite_ingresado         varchar(20),
    numero_tramite_ingresado       varchar(10),
    gestion_tramite_ingresado      varchar(7),
    extension_tramite_ingresado    varchar(2),
    clase_ingresado                int8,
    desc_ingresado                 varchar(500),
    apoderado_ingresado            varchar(250),
    dep_ingresado                  int8,
    titulo_ingresado               int8,
    serie_titulo_ingresado         varchar(4),
    tipo_titulo_ingresado          varchar(4),
    tramite                        varchar(500),
    numero_tramite                 int8,
    gestion_tramite                int8,
    clasetramite                   integer,
    extension_tramite              varchar(5),
    descripcionmarca               varchar(1000),
    expediente                     int8,
    tipo_tramite                   varchar(4),
    sigla                          varchar(10),
    observacion                    varchar(1000),
    idregional                     int8,
    idusuario                      int8,
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
    monto               numeric(18, 2),
    CONSTRAINT "PK118" PRIMARY KEY (idrecibodeposito)
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
-- TABLE: rendatoelementoformulario 
--

CREATE TABLE rendatoelementoformulario(
    iddatoelementoformulario    int8            NOT NULL,
    nombre_tabla                varchar(50),
    idseguimiento               int8            NOT NULL,
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
    CONSTRAINT "PK239_1_3" PRIMARY KEY (iddatoelementoformulario)
)
;



-- 
-- TABLE: rendirecciondenotificacion 
--

CREATE TABLE rendirecciondenotificacion(
    iddirecciondenotificacion    int8            NOT NULL,
    idsolicitudrenovacion        int8,
    idmodificacion               int8,
    idlogtrans                   int8,
    ciudad_notificacion          varchar(4),
    zona_barrio                  varchar(500),
    avenida_calle                varchar(100),
    numero                       varchar(20),
    edificio                     varchar(100),
    piso                         varchar(20),
    departamento                 varchar(10),
    referencia_direccion         varchar(250),
    correo_electronico           varchar(100),
    telefono                     varchar(100),
    celular                      varchar(100),
    estado                       varchar(2),
    CONSTRAINT "PK95_1_1" PRIMARY KEY (iddirecciondenotificacion)
)
;



-- 
-- TABLE: renhistorial 
--

CREATE TABLE renhistorial(
    idhistorial                    int8            NOT NULL,
    idusuario                      int8,
    idsolicitudrenovacion          int8,
    idlogtrans                     int8,
    tipo                           varchar(4),
    operacion                      varchar(50),
    estado_marca_descripcion       varchar(100),
    observacion                    text,
    ubicacion_descripcion          varchar(100),
    seccion                        varchar(100),
    gestion_renovacion             integer,
    descripcion                    text,
    descripcion_lista_productos    text,
    fecha_operacion                timestamp,
    estado                         varchar(2),
    CONSTRAINT "PK136_1_1" PRIMARY KEY (idhistorial)
)
;



-- 
-- TABLE: renobservaciontramite 
--

CREATE TABLE renobservaciontramite(
    idobservaciontramite     int8             NOT NULL,
    idsolicitudrenovacion    int8,
    idusuario                int8,
    idlogtrans               int8,
    editable                 boolean,
    fecha_observacion        timestamp,
    etapa_tramite            varchar(50),
    descripcion              varchar(2000),
    estado                   varchar(2),
    CONSTRAINT "PK87_1_1" PRIMARY KEY (idobservaciontramite)
)
;



-- 
-- TABLE: renrenovacion 
--

CREATE TABLE renrenovacion(
    idrenovacion                 int8             NOT NULL,
    idsolicitudrenovacion        int8,
    idlogtrans                   int8,
    numero_renovacion            integer,
    serie_renovacion             varchar(5),
    orden_renovacion             integer,
    clase_niza_renovacion        integer,
    fecha_concesion              timestamp,
    titular                      varchar(1000),
    apoderado                    varchar(1000),
    tipo_marca                   varchar(4),
    tipo_genero                  varchar(4),
    signo_registrado             varchar(250),
    etiqueta_renovacion          varchar(50),
    numero_registro              integer,
    serie_registro               varchar(55),
    numero_clase_niza_actual     integer,
    sr_manual                    integer,
    gestion_sr_manual            integer,
    fecha_registro_manual        timestamp,
    fecha_ingreso                timestamp,
    lista_producto_actual        text,
    lista_producto_renovacion    text,
    version_clase_niza           varchar(10),
    fecha_renovacion             timestamp,
    gestion_renovacion           integer,
    estado                       varchar(2),
    CONSTRAINT "PK139" PRIMARY KEY (idrenovacion)
)
;



-- 
-- TABLE: renresolucion 
--

CREATE TABLE renresolucion(
    idresolucion              int8            NOT NULL,
    idrenovacion              int8,
    numero_resolucion         integer,
    gestion_resolucion        integer,
    fecha_resolucion          timestamp,
    observacion_resolucion    varchar(200),
    documento_resolucion      text,
    estado                    varchar(2),
    CONSTRAINT "PK173_2" PRIMARY KEY (idresolucion)
)
;



-- 
-- TABLE: renseguimiento 
--

CREATE TABLE renseguimiento(
    idseguimiento            int8            NOT NULL,
    idsolicitudrenovacion    int8,
    idusuario                int8,
    idlogtrans               int8,
    sm                       int8,
    numero_publicacion       int8,
    numero_registro          int8,
    serie_registro           varchar(10),
    etapa                    varchar(4),
    fecha_recepcion          timestamp,
    fecha_fin                timestamp,
    plazo_real               int4,
    total_tiempo             int8,
    observacion              varchar(500),
    editable                 boolean,
    estado                   varchar(2),
    CONSTRAINT "PK89_1_1" PRIMARY KEY (idseguimiento)
)
;



-- 
-- TABLE: rensolicitanteapoderado 
--

CREATE TABLE rensolicitanteapoderado(
    idsolicitanteapoderado    int8             NOT NULL,
    idsolicitudrenovacion     int8,
    idlogtrans                int8,
    tipo_titular              varchar(4),
    tipo_persona              varchar(4),
    nombre_razonsocial        varchar(2000),
    primer_apellido           varchar(50),
    segundo_apellido          varchar(50),
    numero_documento          varchar(1000),
    tipo_documento            varchar(4),
    lugar_expedicion          varchar(4),
    pais                      varchar(4),
    genero                    varchar(4),
    solicitud_departamento    varchar(4),
    poder                     varchar(150),
    direccion                 varchar(1000),
    telefono                  varchar(150),
    celular                   varchar(50),
    email                     varchar(150),
    fax                       varchar(100),
    estado                    varchar(2),
    CONSTRAINT "PK12_2_3" PRIMARY KEY (idsolicitanteapoderado)
)
;



-- 
-- TABLE: rensolicitudrenovacion 
--

CREATE TABLE rensolicitudrenovacion(
    idsolicitudrenovacion          int8             NOT NULL,
    idlogtrans                     int8,
    id_recibo_solicitud            int8,
    id_recibo_titulo               int8,
    sr                             int8,
    gestion_sr                     integer,
    fecha_ingreso                  timestamp,
    nro_formulario                 int8,
    estado_renovacion              varchar(4),
    ubicacion_renovacion           varchar(200),
    numero_ultima_renovacion       integer,
    serie_ultima_renovacion        varchar(5),
    numero_penultima_renovacion    integer,
    serie_penultima_renovacion     varchar(5),
    oficina                        varchar(4),
    numero_recibo                  int8,
    serie_recibo                   varchar(5),
    numero_titulo                  int8,
    serie_titulo                   varchar(5),
    clase_niza_reclasificacion     int4,
    lista_productos_limitacion     text,
    sm                             int8,
    signo_registrado               varchar(1000),
    clase_niza_registrado          int4,
    tipo_genero                    varchar(4),
    numero_registro_registrado     int8,
    serie_registro_registrado      varchar(5),
    fecha_registro_registrado      timestamp,
    marca_acomp                    varchar(1000),
    reg_lc_registrado              int8,
    serie_lc_registrado            varchar(5),
    fecha_lc_registrado            timestamp,
    estado                         varchar(2),
    CONSTRAINT "PK140" PRIMARY KEY (idsolicitudrenovacion)
)
;



-- 
-- TABLE: rentiposigno 
--

CREATE TABLE rentiposigno(
    idtiposigno              int8            NOT NULL,
    idsolicitudrenovacion    int8,
    idlogtrans               int8,
    tipo_signo               varchar(4),
    descripcion_otro         varchar(100),
    estado                   varchar(2),
    CONSTRAINT "PK176_1_1_1_1" PRIMARY KEY (idtiposigno)
)
;



-- 
-- TABLE: rentitularregistrado 
--

CREATE TABLE rentitularregistrado(
    idtitularregistrado      int8             NOT NULL,
    idsolicitudrenovacion    int8,
    idlogtrans               int8,
    nombre_razonsocial       varchar(2000),
    primer_apellido          varchar(50),
    segundo_apellido         varchar(50),
    direccion                varchar(500),
    estado                   varchar(2),
    CONSTRAINT "PK12_2_1_1" PRIMARY KEY (idtitularregistrado)
)
;



-- 
-- TABLE: seccionsubpublicacion 
--

CREATE TABLE seccionsubpublicacion(
    idseccionsubpublicacion    int8            NOT NULL,
    iddominio                  int8            NOT NULL,
    idlogtrans                 int8,
    seccion                    int4,
    subseccion                 int4,
    descripcion                varchar(200),
    estado                     varchar(2),
    CONSTRAINT "PK12_1_1" PRIMARY KEY (idseccionsubpublicacion)
)
;



-- 
-- TABLE: sigdatoelementoformulario 
--

CREATE TABLE sigdatoelementoformulario(
    iddatoelementoformulario    int8            NOT NULL,
    nombre_tabla                varchar(50),
    idseguimiento               int8            NOT NULL,
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
    CONSTRAINT "PK239_1_2" PRIMARY KEY (iddatoelementoformulario)
)
;



-- 
-- TABLE: sigdetallepublicacion 
--

CREATE TABLE sigdetallepublicacion(
    iddetallepublicacion                int8             NOT NULL,
    idpublicacion                       int8             NOT NULL,
    idsignomarca                        int8,
    idlogtrans                          int8,
    numero_publicacion                  int8,
    sm                                  int8,
    sm_descripcion                      varchar(50),
    fecha_ingreso                       timestamp,
    marca                               varchar(1000),
    clase                               int4,
    tipo_signo_descripcion              varchar(200),
    tipo_genero                         varchar(100),
    lista_productos                     text,
    descripcion_signo                   varchar(2500),
    nombre_titular                      varchar(2000),
    documento_titular                   varchar(200),
    pais_titular                        varchar(500),
    descripcion_pais_titular            varchar(2000),
    descripcion_departamento_titular    varchar(200),
    direccion_titular                   varchar(2000),
    nombre_apoderado                    varchar(2000),
    documento_apoderado                 varchar(200),
    direccion_apoderado                 varchar(2000),
    prioridad                           varchar(50),
    fecha_prioridad                     varchar(30),
    pais_prioridad                      varchar(4),
    pais_prio_descripcion               varchar(1000),
    numero_seccion                      int4,
    seccion                             varchar(50),
    subseccion                          varchar(50),
    publicado                           boolean,
    estado                              varchar(2),
    CONSTRAINT "PK5_1" PRIMARY KEY (iddetallepublicacion)
)
;



-- 
-- TABLE: sigdirecciondenotificacion 
--

CREATE TABLE sigdirecciondenotificacion(
    iddirecciondenotificacion    int8            NOT NULL,
    idsignomarca                 int8,
    idlogtrans                   int8,
    ciudad_notificacion          varchar(4),
    zona_barrio                  varchar(500),
    avenida_calle                varchar(100),
    numero                       varchar(20),
    edificio                     varchar(100),
    piso                         varchar(20),
    departamento                 varchar(10),
    referencia_direccion         varchar(250),
    correo_electronico           varchar(100),
    telefono                     varchar(100),
    celular                      varchar(100),
    estado                       varchar(2),
    CONSTRAINT "PK95" PRIMARY KEY (iddirecciondenotificacion)
)
;



-- 
-- TABLE: sighistorial 
--

CREATE TABLE sighistorial(
    idhistorial                    int8            NOT NULL,
    idsignomarca                   int8,
    idusuario                      int8,
    idlogtrans                     int8,
    tipo                           varchar(4),
    operacion                      varchar(50),
    estado_marca_descripcion       varchar(100),
    observacion                    text,
    ubicacion_descripcion          varchar(100),
    seccion                        varchar(100),
    gestion_renovacion             integer,
    descripcion                    text,
    descripcion_lista_productos    text,
    fecha_operacion                timestamp,
    estado                         varchar(2),
    CONSTRAINT "PK136" PRIMARY KEY (idhistorial)
)
;



-- 
-- TABLE: sigimagen 
--

CREATE TABLE sigimagen(
    idimagen      int8     NOT NULL,
    idlogotipo    int8     NOT NULL,
    idlogtrans    int8,
    imagen        bytea,
    CONSTRAINT "PK255" PRIMARY KEY (idimagen)
)
;



-- 
-- TABLE: siglemacomercial 
--

CREATE TABLE siglemacomercial(
    idlemacomercial          int8             NOT NULL,
    idsignomarca             int8,
    signo_padre              varchar(1000),
    sm_padre                 int8,
    codigo_sm_padre          varchar(250),
    numero_registro_padre    int8,
    serie_registro_padre     varchar(2),
    vigencia                 varchar(50),
    estado                   varchar(2),
    CONSTRAINT "PK186" PRIMARY KEY (idlemacomercial)
)
;



-- 
-- TABLE: siglogotipo 
--

CREATE TABLE siglogotipo(
    idlogotipo           int8            NOT NULL,
    idsignomarca         int8            NOT NULL,
    idlogtrans           int8,
    urllogotipo          varchar(250),
    principal            boolean,
    nombre_archivo       varchar(100),
    extension_archivo    varchar(10),
    estado               varchar(2),
    CONSTRAINT "PK176_1" PRIMARY KEY (idlogotipo)
)
;



-- 
-- TABLE: sigobservaciontramite 
--

CREATE TABLE sigobservaciontramite(
    idobservaciontramite    int8             NOT NULL,
    idsignomarca            int8,
    idusuario               int8,
    idlogtrans              int8,
    editable                boolean,
    fecha_observacion       timestamp,
    etapa_tramite           varchar(50),
    descripcion             varchar(2000),
    estado                  varchar(2),
    CONSTRAINT "PK87" PRIMARY KEY (idobservaciontramite)
)
;



-- 
-- TABLE: sigprioridadpreferencia 
--

CREATE TABLE sigprioridadpreferencia(
    idprioridadpreferencia    int8            NOT NULL,
    idsignomarca              int8            NOT NULL,
    idlogtrans                int8,
    tipo_prioridad            varchar(4),
    tipo_interes              varchar(4),
    nombre_numero             varchar(100),
    fecha                     timestamp,
    lugar                     varchar(100),
    estado                    varchar(2),
    CONSTRAINT "PK31" PRIMARY KEY (idprioridadpreferencia)
)
;



-- 
-- TABLE: sigpublicacion 
--

CREATE TABLE sigpublicacion(
    idpublicacion         int8            NOT NULL,
    idlogtrans            int8,
    numero_gaceta         int8,
    fecha_publicacion     timestamp,
    fecha_envio           timestamp,
    mes                   varchar(100),
    gestion               int4,
    inicio                int8,
    fin                   int8,
    total                 int8,
    observacion           text,
    estado_publicacion    varchar(4),
    estado                varchar(2),
    CONSTRAINT "PK4_1" PRIMARY KEY (idpublicacion)
)
;



-- 
-- TABLE: sigregistro 
--

CREATE TABLE sigregistro(
    idregistro                  int8             NOT NULL,
    idsignomarca                int8             NOT NULL,
    idlogtrans                  int8,
    numero_registro             int8,
    serie                       varchar(5),
    sm                          int8,
    sm_descripcion              varchar(30),
    estado_registro             varchar(4),
    fecha_ingreso               timestamp,
    idtipo_resolucion           int4,
    documento_resolucion        text,
    signo                       varchar(1000),
    tipo_signo_descripcion      varchar(100),
    tipo_genero_descripcion     varchar(100),
    clase                       int4,
    numero_resolucion           int8,
    gestion_resolucion          int4,
    fecha_registro              timestamp,
    numero_publicacion          int8,
    fecha_publicacion           timestamp,
    numero_gaceta               int8,
    fecha_primer_uso            timestamp,
    titular                     varchar(1000),
    direccion_titular           varchar(1000),
    pais_titular                varchar(50),
    pais_titular_descripcion    varchar(100),
    nombre_apoderado            varchar(1000),
    direccion_apoderado         varchar(1000),
    lista_productos             text,
    observacion                 varchar(1000),
    estado                      varchar(2),
    CONSTRAINT "PK1" PRIMARY KEY (idregistro)
)
;



-- 
-- TABLE: sigseguimiento 
--

CREATE TABLE sigseguimiento(
    idseguimiento         int8            NOT NULL,
    idsignomarca          int8,
    idusuario             int8            NOT NULL,
    idlogtrans            int8,
    sm                    int8,
    numero_publicacion    int8,
    numero_registro       int8,
    serie_registro        varchar(10),
    etapa                 varchar(4),
    fecha_recepcion       timestamp,
    fecha_fin             timestamp,
    plazo_real            int4,
    total_tiempo          int8,
    observacion           varchar(500),
    editable              boolean,
    estado                varchar(2),
    CONSTRAINT "PK89" PRIMARY KEY (idseguimiento)
)
;



-- 
-- TABLE: sigsignoclaseniza 
--

CREATE TABLE sigsignoclaseniza(
    idsignoclaseniza     int8          NOT NULL,
    idsignomarca         int8          NOT NULL,
    idclaseniza          int8          NOT NULL,
    idlogtrans           int8,
    numero_clase_niza    int4,
    lista_producto       text,
    estado               varchar(2),
    CONSTRAINT "PK132" PRIMARY KEY (idsignoclaseniza)
)
;



-- 
-- TABLE: sigsignomarca 
--

CREATE TABLE sigsignomarca(
    idsignomarca                    int8             NOT NULL,
    idlogtrans                      int8,
    numero_formulario               varchar(20),
    sm                              int8,
    signo                           varchar(1000),
    tipo_genero                     varchar(4),
    descripcion_signo               varchar(4000),
    descripcion_logotipo            varchar(4000),
    ubicacion                       varchar(4),
    estado_marca                    varchar(4),
    nro_recibo                      int8,
    serie                           varchar(5),
    numero_titulo                   int8,
    serie_titulo                    varchar(4),
    origen_solicitud                varchar(20),
    numero_gaceta                   int8,
    numero_publicacion              int8,
    fecha_publicacion               timestamp,
    numero_registro                 int8,
    serie_registro                  varchar(3),
    resolucion_registro             varchar(9),
    fecha_registro                  timestamp,
    orden_renovacion                integer,
    numero_renovacion               integer,
    extension_renovacion            varchar(5),
    numero_resolucion_renovacion    int8,
    fecha_renovacion                timestamp,
    oficina                         varchar(4),
    fecha_solicitud                 timestamp,
    fecha_ingreso                   timestamp,
    entregado_usuario               boolean,
    notoriedad_marca                boolean,
    estado                          varchar(2),
    numero                          int4,
    gestion                         int4,
    extension_marca                 int4,
    CONSTRAINT "PK2_1" PRIMARY KEY (idsignomarca)
)
;



-- 
-- TABLE: sigsolicitanteapoderado 
--

CREATE TABLE sigsolicitanteapoderado(
    idsolicitanteapoderado    int8             NOT NULL,
    idsignomarca              int8,
    idlogtrans                int8,
    tipo_titular              varchar(4),
    tipo_persona              varchar(4),
    nombre_razonsocial        varchar(2000),
    primer_apellido           varchar(50),
    segundo_apellido          varchar(50),
    numero_documento          varchar(1000),
    tipo_documento            varchar(4),
    lugar_expedicion          varchar(4),
    pais                      varchar(4),
    genero                    varchar(4),
    solicitud_departamento    varchar(4),
    poder                     varchar(150),
    direccion                 varchar(1000),
    telefono                  varchar(150),
    celular                   varchar(50),
    email                     varchar(150),
    fax                       varchar(100),
    estado                    varchar(2),
    CONSTRAINT "PK12" PRIMARY KEY (idsolicitanteapoderado)
)
;



-- 
-- TABLE: sigtiposigno 
--

CREATE TABLE sigtiposigno(
    idtiposigno         int8            NOT NULL,
    idsignomarca        int8            NOT NULL,
    idlogtrans          int8,
    tipo_signo          varchar(4),
    descripcion_otro    varchar(100),
    estado              varchar(2),
    CONSTRAINT "PK176_1_1" PRIMARY KEY (idtiposigno)
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
-- TABLE: sticker 
--

CREATE TABLE sticker(
    idsticker                 int8            NOT NULL,
    idlogtrans                int8,
    tipo_tramite              varchar(4),
    incremento                int4,
    primer_numero_asignado    varchar(100),
    ultimo_numero_asignado    int4,
    gestion                   int4,
    estado                    varchar(2),
    CONSTRAINT "PK157_1" PRIMARY KEY (idsticker)
)
;



-- 
-- TABLE: tasa 
--

CREATE TABLE tasa(
    idtasa          int8              NOT NULL,
    idlogtrans      int8,
    descripcion     varchar(250),
    costo           numeric(18, 2),
    descuento       numeric(18, 2),
    unidad          varchar(4),
    estado          varchar(2),
    gestion         integer,
    tipo_tramite    varchar(4),
    CONSTRAINT "PK236" PRIMARY KEY (idtasa)
)
;



-- 
-- TABLE: usuario 
--

CREATE TABLE usuario(
    idusuario             int8            NOT NULL,
    idlogtrans            int8,
    nombre                varchar(50),
    primer_apellido       varchar(50),
    segundo_apellido      varchar(50),
    login                 varchar(50),
    contrasenia           varchar(50),
    cargo                 varchar(100),
    idregional            int8,
    numero_documento      varchar(50),
    tipo_documento        varchar(50),
    lugar_expedicion      varchar(50),
    correo_electronico    varchar(70),
    estado                varchar(2),
    CONSTRAINT "PK119" PRIMARY KEY (idusuario)
)
;



-- 
-- TABLE: usuarioetapa 
--

CREATE TABLE usuarioetapa(
    idusuarioetapa    int8          NOT NULL,
    idlogtrans        int8,
    idusuario         int8          NOT NULL,
    idetapa           int8          NOT NULL,
    estado            varchar(2),
    CONSTRAINT "PK120_1" PRIMARY KEY (idusuarioetapa)
)
;



-- 
-- TABLE: usuariomenu 
--

CREATE TABLE usuariomenu(
    idusuariomenu    int8    NOT NULL,
    idusuario        int8    NOT NULL,
    idmenu           int8    NOT NULL,
    idlogtrans       int8,
    CONSTRAINT "PK125" PRIMARY KEY (idusuariomenu)
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
    hora_vig_ini       varchar(20),
    hora_vig_fin       varchar(20),
    muestra            boolean,
    estado             varchar(2),
    CONSTRAINT "PK126" PRIMARY KEY (idusuariopagina)
)
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
-- TABLE: digarchivodocumento 
--

ALTER TABLE digarchivodocumento ADD CONSTRAINT "Refdocumento136" 
    FOREIGN KEY (iddocumento)
    REFERENCES documento(iddocumento)
;


-- 
-- TABLE: dosificaciontasa 
--

ALTER TABLE dosificaciontasa ADD CONSTRAINT "Reftasa411" 
    FOREIGN KEY (idtasa)
    REFERENCES tasa(idtasa)
;

ALTER TABLE dosificaciontasa ADD CONSTRAINT "Refdosificacion412" 
    FOREIGN KEY (iddosificacion)
    REFERENCES dosificacion(iddosificacion)
;


-- 
-- TABLE: elementoformulariotramite 
--

ALTER TABLE elementoformulariotramite ADD CONSTRAINT "Refformulariotramite253" 
    FOREIGN KEY (idformulariotramite)
    REFERENCES formulariotramite(idformulariotramite)
;


-- 
-- TABLE: logtrans 
--

ALTER TABLE logtrans ADD CONSTRAINT "Refusuario143" 
    FOREIGN KEY (idusuario)
    REFERENCES usuario(idusuario)
;


-- 
-- TABLE: menu 
--

ALTER TABLE menu ADD CONSTRAINT "Refsistema166" 
    FOREIGN KEY (idsistema)
    REFERENCES sistema(idsistema)
;


-- 
-- TABLE: menupagina 
--

ALTER TABLE menupagina ADD CONSTRAINT "Refmenu124" 
    FOREIGN KEY (idmenu)
    REFERENCES menu(idmenu)
;

ALTER TABLE menupagina ADD CONSTRAINT "Refpagina125" 
    FOREIGN KEY (idpagina)
    REFERENCES pagina(idpagina)
;


-- 
-- TABLE: moddatoelementoformulario 
--

ALTER TABLE moddatoelementoformulario ADD CONSTRAINT "Refmodseguimiento416" 
    FOREIGN KEY (idseguimiento)
    REFERENCES modseguimiento(idseguimiento)
;


-- 
-- TABLE: moddirecciondenotificacion 
--

ALTER TABLE moddirecciondenotificacion ADD CONSTRAINT "Refmodmodificacion218" 
    FOREIGN KEY (idmodificacion)
    REFERENCES modmodificacion(idmodificacion)
;


-- 
-- TABLE: modhistorial 
--

ALTER TABLE modhistorial ADD CONSTRAINT "Refusuario219" 
    FOREIGN KEY (idusuario)
    REFERENCES usuario(idusuario)
;

ALTER TABLE modhistorial ADD CONSTRAINT "Refmodmodificacion220" 
    FOREIGN KEY (idmodificacion)
    REFERENCES modmodificacion(idmodificacion)
;


-- 
-- TABLE: modobservaciontramite 
--

ALTER TABLE modobservaciontramite ADD CONSTRAINT "Refmodmodificacion221" 
    FOREIGN KEY (idmodificacion)
    REFERENCES modmodificacion(idmodificacion)
;


-- 
-- TABLE: modresolucion 
--

ALTER TABLE modresolucion ADD CONSTRAINT "Refmodmodificacion222" 
    FOREIGN KEY (idmodificacion)
    REFERENCES modmodificacion(idmodificacion)
;


-- 
-- TABLE: modseguimiento 
--

ALTER TABLE modseguimiento ADD CONSTRAINT "Refusuario223" 
    FOREIGN KEY (idusuario)
    REFERENCES usuario(idusuario)
;

ALTER TABLE modseguimiento ADD CONSTRAINT "Refmodmodificacion224" 
    FOREIGN KEY (idmodificacion)
    REFERENCES modmodificacion(idmodificacion)
;


-- 
-- TABLE: modsolicitanteapoderado 
--

ALTER TABLE modsolicitanteapoderado ADD CONSTRAINT "Refmodmodificacion225" 
    FOREIGN KEY (idmodificacion)
    REFERENCES modmodificacion(idmodificacion)
;


-- 
-- TABLE: modtiposigno 
--

ALTER TABLE modtiposigno ADD CONSTRAINT "Refmodmodificacion388" 
    FOREIGN KEY (idmodificacion)
    REFERENCES modmodificacion(idmodificacion)
;


-- 
-- TABLE: modtitularlicenciatarionuevo 
--

ALTER TABLE modtitularlicenciatarionuevo ADD CONSTRAINT "Refmodmodificacion314" 
    FOREIGN KEY (idmodificacion)
    REFERENCES modmodificacion(idmodificacion)
;


-- 
-- TABLE: modtitularlicenciatarioregistrado 
--

ALTER TABLE modtitularlicenciatarioregistrado ADD CONSTRAINT "Refmodmodificacion227" 
    FOREIGN KEY (idmodificacion)
    REFERENCES modmodificacion(idmodificacion)
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
-- TABLE: opoactividad 
--

ALTER TABLE opoactividad ADD CONSTRAINT "Refopoestado284" 
    FOREIGN KEY (idestado)
    REFERENCES opoestado(idestado)
;


-- 
-- TABLE: opoactividadplazo 
--

ALTER TABLE opoactividadplazo ADD CONSTRAINT "Refopoactividad399" 
    FOREIGN KEY (idactividad)
    REFERENCES opoactividad(idactividad)
;


-- 
-- TABLE: opoevento 
--

ALTER TABLE opoevento ADD CONSTRAINT "Refoposicion286" 
    FOREIGN KEY (idoposicion)
    REFERENCES oposicion(idoposicion)
;

ALTER TABLE opoevento ADD CONSTRAINT "Refopoactividad287" 
    FOREIGN KEY (idactividad)
    REFERENCES opoactividad(idactividad)
;


-- 
-- TABLE: opofechalimite 
--

ALTER TABLE opofechalimite ADD CONSTRAINT "Refopoevento288" 
    FOREIGN KEY (idevento)
    REFERENCES opoevento(idevento)
;

ALTER TABLE opofechalimite ADD CONSTRAINT "Refoposicion289" 
    FOREIGN KEY (idoposicion)
    REFERENCES oposicion(idoposicion)
;

ALTER TABLE opofechalimite ADD CONSTRAINT "Refopoactividadplazo290" 
    FOREIGN KEY (idactividadplazo)
    REFERENCES opoactividadplazo(idactividadplazo)
;


-- 
-- TABLE: opoflujoactividad 
--

ALTER TABLE opoflujoactividad ADD CONSTRAINT "Refopoactividad291" 
    FOREIGN KEY (idactividad)
    REFERENCES opoactividad(idactividad)
;


-- 
-- TABLE: opohistorial 
--

ALTER TABLE opohistorial ADD CONSTRAINT "Refoposicion292" 
    FOREIGN KEY (idoposicion)
    REFERENCES oposicion(idoposicion)
;


-- 
-- TABLE: opomarcademandada 
--

ALTER TABLE opomarcademandada ADD CONSTRAINT "Refoposicion355" 
    FOREIGN KEY (idoposicion)
    REFERENCES oposicion(idoposicion)
;


-- 
-- TABLE: opomarcademandante 
--

ALTER TABLE opomarcademandante ADD CONSTRAINT "Refoposicion356" 
    FOREIGN KEY (idoposicion)
    REFERENCES oposicion(idoposicion)
;


-- 
-- TABLE: oponotificacion 
--

ALTER TABLE oponotificacion ADD CONSTRAINT "Refopomarcademandada357" 
    FOREIGN KEY (idmarcademandada)
    REFERENCES opomarcademandada(idmarcademandada)
;

ALTER TABLE oponotificacion ADD CONSTRAINT "Refopomarcademandante358" 
    FOREIGN KEY (idmarcademandante)
    REFERENCES opomarcademandante(idmarcademandante)
;


-- 
-- TABLE: oporecurso 
--

ALTER TABLE oporecurso ADD CONSTRAINT "Refoposicion293" 
    FOREIGN KEY (idoposicion)
    REFERENCES oposicion(idoposicion)
;

ALTER TABLE oporecurso ADD CONSTRAINT "Refopoevento294" 
    FOREIGN KEY (idevento)
    REFERENCES opoevento(idevento)
;


-- 
-- TABLE: oporesolucion 
--

ALTER TABLE oporesolucion ADD CONSTRAINT "Refoposicion295" 
    FOREIGN KEY (idoposicion)
    REFERENCES oposicion(idoposicion)
;

ALTER TABLE oporesolucion ADD CONSTRAINT "Refopoevento296" 
    FOREIGN KEY (idevento)
    REFERENCES opoevento(idevento)
;


-- 
-- TABLE: oposolicitanteapoderado 
--

ALTER TABLE oposolicitanteapoderado ADD CONSTRAINT "Refopomarcademandante353" 
    FOREIGN KEY (idmarcademandante)
    REFERENCES opomarcademandante(idmarcademandante)
;

ALTER TABLE oposolicitanteapoderado ADD CONSTRAINT "Refopomarcademandada354" 
    FOREIGN KEY (idmarcademandada)
    REFERENCES opomarcademandada(idmarcademandada)
;


-- 
-- TABLE: rechistorial 
--

ALTER TABLE rechistorial ADD CONSTRAINT "Refrecibo260" 
    FOREIGN KEY (idrecibo)
    REFERENCES recibo(idrecibo)
;

ALTER TABLE rechistorial ADD CONSTRAINT "Refusuario228" 
    FOREIGN KEY (idusuario)
    REFERENCES usuario(idusuario)
;


-- 
-- TABLE: recibo 
--

ALTER TABLE recibo ADD CONSTRAINT "Reftasa264" 
    FOREIGN KEY (idtasa)
    REFERENCES tasa(idtasa)
;


-- 
-- TABLE: recibodeposito 
--

ALTER TABLE recibodeposito ADD CONSTRAINT "Refdeposito265" 
    FOREIGN KEY (iddeposito)
    REFERENCES deposito(iddeposito)
;

ALTER TABLE recibodeposito ADD CONSTRAINT "Refrecibo266" 
    FOREIGN KEY (idrecibo)
    REFERENCES recibo(idrecibo)
;


-- 
-- TABLE: rendatoelementoformulario 
--

ALTER TABLE rendatoelementoformulario ADD CONSTRAINT "Refrenseguimiento417" 
    FOREIGN KEY (idseguimiento)
    REFERENCES renseguimiento(idseguimiento)
;


-- 
-- TABLE: rendirecciondenotificacion 
--

ALTER TABLE rendirecciondenotificacion ADD CONSTRAINT "Refrensolicitudrenovacion243" 
    FOREIGN KEY (idsolicitudrenovacion)
    REFERENCES rensolicitudrenovacion(idsolicitudrenovacion)
;


-- 
-- TABLE: renhistorial 
--

ALTER TABLE renhistorial ADD CONSTRAINT "Refusuario231" 
    FOREIGN KEY (idusuario)
    REFERENCES usuario(idusuario)
;

ALTER TABLE renhistorial ADD CONSTRAINT "Refrensolicitudrenovacion244" 
    FOREIGN KEY (idsolicitudrenovacion)
    REFERENCES rensolicitudrenovacion(idsolicitudrenovacion)
;


-- 
-- TABLE: renobservaciontramite 
--

ALTER TABLE renobservaciontramite ADD CONSTRAINT "Refrensolicitudrenovacion245" 
    FOREIGN KEY (idsolicitudrenovacion)
    REFERENCES rensolicitudrenovacion(idsolicitudrenovacion)
;


-- 
-- TABLE: renrenovacion 
--

ALTER TABLE renrenovacion ADD CONSTRAINT "Refrensolicitudrenovacion246" 
    FOREIGN KEY (idsolicitudrenovacion)
    REFERENCES rensolicitudrenovacion(idsolicitudrenovacion)
;


-- 
-- TABLE: renresolucion 
--

ALTER TABLE renresolucion ADD CONSTRAINT "Refrenrenovacion384" 
    FOREIGN KEY (idrenovacion)
    REFERENCES renrenovacion(idrenovacion)
;


-- 
-- TABLE: renseguimiento 
--

ALTER TABLE renseguimiento ADD CONSTRAINT "Refusuario236" 
    FOREIGN KEY (idusuario)
    REFERENCES usuario(idusuario)
;

ALTER TABLE renseguimiento ADD CONSTRAINT "Refrensolicitudrenovacion248" 
    FOREIGN KEY (idsolicitudrenovacion)
    REFERENCES rensolicitudrenovacion(idsolicitudrenovacion)
;


-- 
-- TABLE: rensolicitanteapoderado 
--

ALTER TABLE rensolicitanteapoderado ADD CONSTRAINT "Refrensolicitudrenovacion249" 
    FOREIGN KEY (idsolicitudrenovacion)
    REFERENCES rensolicitudrenovacion(idsolicitudrenovacion)
;


-- 
-- TABLE: rentiposigno 
--

ALTER TABLE rentiposigno ADD CONSTRAINT "Refrensolicitudrenovacion389" 
    FOREIGN KEY (idsolicitudrenovacion)
    REFERENCES rensolicitudrenovacion(idsolicitudrenovacion)
;


-- 
-- TABLE: rentitularregistrado 
--

ALTER TABLE rentitularregistrado ADD CONSTRAINT "Refrensolicitudrenovacion250" 
    FOREIGN KEY (idsolicitudrenovacion)
    REFERENCES rensolicitudrenovacion(idsolicitudrenovacion)
;


-- 
-- TABLE: seccionsubpublicacion 
--

ALTER TABLE seccionsubpublicacion ADD CONSTRAINT "Refdominio403" 
    FOREIGN KEY (iddominio)
    REFERENCES dominio(iddominio)
;


-- 
-- TABLE: sigdatoelementoformulario 
--

ALTER TABLE sigdatoelementoformulario ADD CONSTRAINT "Refsigseguimiento418" 
    FOREIGN KEY (idseguimiento)
    REFERENCES sigseguimiento(idseguimiento)
;


-- 
-- TABLE: sigdetallepublicacion 
--

ALTER TABLE sigdetallepublicacion ADD CONSTRAINT "Refsigsignomarca390" 
    FOREIGN KEY (idsignomarca)
    REFERENCES sigsignomarca(idsignomarca)
;

ALTER TABLE sigdetallepublicacion ADD CONSTRAINT "Refsigpublicacion391" 
    FOREIGN KEY (idpublicacion)
    REFERENCES sigpublicacion(idpublicacion)
;


-- 
-- TABLE: sigdirecciondenotificacion 
--

ALTER TABLE sigdirecciondenotificacion ADD CONSTRAINT "Refsigsignomarca181" 
    FOREIGN KEY (idsignomarca)
    REFERENCES sigsignomarca(idsignomarca)
;


-- 
-- TABLE: sighistorial 
--

ALTER TABLE sighistorial ADD CONSTRAINT "Refsigsignomarca177" 
    FOREIGN KEY (idsignomarca)
    REFERENCES sigsignomarca(idsignomarca)
;

ALTER TABLE sighistorial ADD CONSTRAINT "Refusuario179" 
    FOREIGN KEY (idusuario)
    REFERENCES usuario(idusuario)
;


-- 
-- TABLE: sigimagen 
--

ALTER TABLE sigimagen ADD CONSTRAINT "Refsiglogotipo133" 
    FOREIGN KEY (idlogotipo)
    REFERENCES siglogotipo(idlogotipo)
;


-- 
-- TABLE: siglemacomercial 
--

ALTER TABLE siglemacomercial ADD CONSTRAINT "Refsigsignomarca240" 
    FOREIGN KEY (idsignomarca)
    REFERENCES sigsignomarca(idsignomarca)
;


-- 
-- TABLE: siglogotipo 
--

ALTER TABLE siglogotipo ADD CONSTRAINT "Refsigsignomarca35" 
    FOREIGN KEY (idsignomarca)
    REFERENCES sigsignomarca(idsignomarca)
;


-- 
-- TABLE: sigobservaciontramite 
--

ALTER TABLE sigobservaciontramite ADD CONSTRAINT "Refsigsignomarca184" 
    FOREIGN KEY (idsignomarca)
    REFERENCES sigsignomarca(idsignomarca)
;


-- 
-- TABLE: sigprioridadpreferencia 
--

ALTER TABLE sigprioridadpreferencia ADD CONSTRAINT "Refsigsignomarca43" 
    FOREIGN KEY (idsignomarca)
    REFERENCES sigsignomarca(idsignomarca)
;


-- 
-- TABLE: sigregistro 
--

ALTER TABLE sigregistro ADD CONSTRAINT "Refsigsignomarca385" 
    FOREIGN KEY (idsignomarca)
    REFERENCES sigsignomarca(idsignomarca)
;


-- 
-- TABLE: sigseguimiento 
--

ALTER TABLE sigseguimiento ADD CONSTRAINT "Refusuario115" 
    FOREIGN KEY (idusuario)
    REFERENCES usuario(idusuario)
;

ALTER TABLE sigseguimiento ADD CONSTRAINT "Refsigsignomarca180" 
    FOREIGN KEY (idsignomarca)
    REFERENCES sigsignomarca(idsignomarca)
;


-- 
-- TABLE: sigsignoclaseniza 
--

ALTER TABLE sigsignoclaseniza ADD CONSTRAINT "Refclaseniza359" 
    FOREIGN KEY (idclaseniza)
    REFERENCES claseniza(idclaseniza)
;

ALTER TABLE sigsignoclaseniza ADD CONSTRAINT "Refsigsignomarca360" 
    FOREIGN KEY (idsignomarca)
    REFERENCES sigsignomarca(idsignomarca)
;


-- 
-- TABLE: sigsolicitanteapoderado 
--

ALTER TABLE sigsolicitanteapoderado ADD CONSTRAINT "Refsigsignomarca182" 
    FOREIGN KEY (idsignomarca)
    REFERENCES sigsignomarca(idsignomarca)
;


-- 
-- TABLE: sigtiposigno 
--

ALTER TABLE sigtiposigno ADD CONSTRAINT "Refsigsignomarca361" 
    FOREIGN KEY (idsignomarca)
    REFERENCES sigsignomarca(idsignomarca)
;


-- 
-- TABLE: usuarioetapa 
--

ALTER TABLE usuarioetapa ADD CONSTRAINT "Refetapa406" 
    FOREIGN KEY (idetapa)
    REFERENCES etapa(idetapa)
;

ALTER TABLE usuarioetapa ADD CONSTRAINT "Refusuario407" 
    FOREIGN KEY (idusuario)
    REFERENCES usuario(idusuario)
;


-- 
-- TABLE: usuariomenu 
--

ALTER TABLE usuariomenu ADD CONSTRAINT "Refusuario119" 
    FOREIGN KEY (idusuario)
    REFERENCES usuario(idusuario)
;

ALTER TABLE usuariomenu ADD CONSTRAINT "Refmenu120" 
    FOREIGN KEY (idmenu)
    REFERENCES menu(idmenu)
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


