--
-- ER/Studio 8.0 SQL Code Generation
-- Company :      Microsoft
-- Project :      ModeloSENAPI.DM1
-- Author :       DELL
--
-- Date Created : Thursday, February 02, 2017 17:39:22
-- Target DBMS : PostgreSQL 8.0
--

-- 
-- TABLE: modarchivodig 
--

CREATE TABLE modarchivodig(
    idarchivodig    int8     NOT NULL,
    iddocumento     int8     NOT NULL,
    archivo         bytea,
    CONSTRAINT "PK334_1" PRIMARY KEY (idarchivodig)
)
;



-- 
-- TABLE: moddocumento 
--

CREATE TABLE moddocumento(
    iddocumento          int8            NOT NULL,
    idmodificacion       int8,
    nombre_archivo       varchar(200),
    descripcion          varchar(500),
    nro_folios           int4,
    tipo_documento       varchar(4),
    extension_archivo    varchar(4),
    fecha_creacion       timestamp,
    mime                 varchar(100),
    estado               varchar(2),
    CONSTRAINT "PK329_1" PRIMARY KEY (iddocumento)
)
;



-- 
-- TABLE: renarchivodig 
--

CREATE TABLE renarchivodig(
    idarchivodig    int8     NOT NULL,
    iddocumento     int8     NOT NULL,
    archivo         bytea,
    CONSTRAINT "PK334_1_1" PRIMARY KEY (idarchivodig)
)
;



-- 
-- TABLE: rendocumento 
--

CREATE TABLE rendocumento(
    iddocumento              int8            NOT NULL,
    idsolicitudrenovacion    int8,
    nombre_archivo           varchar(200),
    descripcion              varchar(500),
    nro_folios               int4,
    tipo_documento           varchar(4),
    extension_archivo        varchar(4),
    fecha_creacion           timestamp,
    mime                     varchar(100),
    estado                   varchar(2),
    CONSTRAINT "PK329_1_1" PRIMARY KEY (iddocumento)
)
;



-- 
-- TABLE: sigarchivodig 
--

CREATE TABLE sigarchivodig(
    idarchivodig    int8     NOT NULL,
    iddocumento     int8     NOT NULL,
    archivo         bytea,
    CONSTRAINT "PK334" PRIMARY KEY (idarchivodig)
)
;



-- 
-- TABLE: sigdocumento 
--

CREATE TABLE sigdocumento(
    iddocumento          int8            NOT NULL,
    idsignomarca         int8,
    nombre_archivo       varchar(200),
    descripcion          varchar(500),
    nro_folios           int4,
    tipo_documento       varchar(4),
    extension_archivo    varchar(4),
    fecha_creacion       timestamp,
    mime                 varchar(100),
    estado               varchar(2),
    CONSTRAINT "PK329" PRIMARY KEY (iddocumento)
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
-- TABLE: siglogotipo 
--

CREATE TABLE siglogotipo(
    idlogotipo           int8            NOT NULL,
    idsignomarca         int8,
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
-- TABLE: modarchivodig 
--

ALTER TABLE modarchivodig ADD CONSTRAINT "Refmoddocumento416" 
    FOREIGN KEY (iddocumento)
    REFERENCES moddocumento(iddocumento)
;


-- 
-- TABLE: renarchivodig 
--

ALTER TABLE renarchivodig ADD CONSTRAINT "Refrendocumento417" 
    FOREIGN KEY (iddocumento)
    REFERENCES rendocumento(iddocumento)
;


-- 
-- TABLE: sigarchivodig 
--

ALTER TABLE sigarchivodig ADD CONSTRAINT "Refsigdocumento418" 
    FOREIGN KEY (iddocumento)
    REFERENCES sigdocumento(iddocumento)
;


-- 
-- TABLE: sigimagen 
--

ALTER TABLE sigimagen ADD CONSTRAINT "Refsiglogotipo133" 
    FOREIGN KEY (idlogotipo)
    REFERENCES siglogotipo(idlogotipo)
;


