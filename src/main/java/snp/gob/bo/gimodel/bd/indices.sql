--Indice tabla sigsignomarca
create index sigsignomarca_idx01 on sigsignomarca(numero, gestion, extension_marca, nro_formulario);

--Indice tabla sigsolicitanteapoderado
create index sigsolicitanteapoderado_idx01 on sigsolicitanteapoderado(idsignomarca, tipo_titular, tipo_persona, estado);

--Indice tabla sigsignoclaseniza
create index sigsignoclaseniza_idx01 on sigsignoclaseniza(idsignomarca, estado, numero_clase_niza);

--Indice tabla modtitularlicenciatarionuevo
create index modtitularlicenciatarionuevo_idx01 on modtitularlicenciatarionuevo(idmodificacion, estado);

--Indice tabla modtitularlicenciatarioregistrado
create index modtitularlicenciatarioregistrado_idx01 on modtitularlicenciatarioregistrado(idmodificacion, estado);

--Indice tabla modresolucion
create index modresolucion_idx01 on modresolucion(idmodificacion, estado);

--Indice tabla sigdetallepublicacion
create index sigdetallepublicacion_idx01 on sigdetallepublicacion(idsignomarca, idpublicacion, estado);

--Indice tabla sigregistro
create index sigregistro_idx01 on sigregistro(idsignomarca, estado);

--Indice tabla sigtiposigno
create index sigtiposigno_idx01 on sigtiposigno(idsignomarca, estado);

--Indice tabla sigseguimiento
create index sigseguimiento_idx01 on sigseguimiento(idsignomarca, estado);

--Indice tabla modsolicitanteapoderado
create index modsolicitanteapoderado_idx01 on modsolicitanteapoderado(idmodificacion, estado);

----INDICES ADICIONALES RECAUDACIONN
--Indice tabla recibo
create index recibo_idx01 on recibo(idusuario, fecha_emision_recibo, idregional, estado_recibo, estado);

CREATE INDEX modmodificacion_idx01 ON modmodificacion (nro_formulario, estado);

--Indice tabla rensolicitudrenovacion
create index rensolicitudrenovacion_idx01 on rensolicitudrenovacion(nro_formulario, estado);

---MODIFICACION DE INDICES 
CREATE INDEX sigsignomarca_idx01 ON sigsignomarca (numero, gestion, extension_marca, numero_formulario, estado);



