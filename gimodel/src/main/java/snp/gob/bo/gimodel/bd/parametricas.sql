
/**
 * autor: Eddy Valero
 * version 1.0, 19/09/2016
 * insertar la fecha en fecha sistema
 */
insert into fechasistema (idlogtrans, idregional, fecha, estado) values (1, 1, current_timestamp,'AC');

/**
 * autor: Eddy Valero
 * version 1.0, 11/08/2016
 * tabla smdominio, dominio = 'tipo_genero'
 */
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_genero', 'DO', 'PI100', 'Denominación de Origen', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_genero', 'LC', 'PI102', 'Lema Comercial', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_genero', 'MC', 'PI100', 'Marca Colectiva', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_genero', 'MCE', 'PI100', 'Marca de Certificación', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_genero', 'MP', 'PI100', 'Marca Producto', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_genero', 'MS', 'PI100', 'Marca Servicio', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_genero', 'NC', 'PI101', 'Nombre Comercial', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_genero', 'RC', 'PI101', 'Rotulo Comercial', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_genero', 'EC', 'PI101', 'Enseña comercial', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_genero', 'MF', '', 'Marca Fábrica', NULL, NULL, current_date, current_date, 'AC');


/**
 * autor: Eddy Valero
 * version 1.0, 09/08/2016
 * tabla smdominio, dominio = 'tipo_titular'
 */
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_titular', 'JUR', NULL, 'Jurídica', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_titular', 'NAT', NULL, 'Natural', NULL, NULL, current_date, current_date, 'AC');

/**
 * autor: Eddy Valero
 * version 1.0, 09/08/2016
 * tabla smdominio, dominio = 'tipo_documento'
 */
    INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_documento', 'CI', 'NATU', 'Carnet de identidad', NULL, NULL, current_date, current_date, 'AC');
    INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_documento', 'DNI', 'NATU', 'D.N.I.', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_documento', 'PAS', 'NATU', 'Pasaporte', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_documento', 'LMIL', 'NATU', 'Libreta militar', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_documento', 'OTR', 'TODO', 'Otro', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_documento', 'NIT', 'TODO', 'Número de identificación tributaria', NULL, NULL, current_date, current_date, 'AC');

/**
 * autor: Eddy Valero
 * version 1.0, 09/08/2016
 * tabla smdominio, dominio = 'genero'
 */
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'genero', 'MA', NULL, 'Masculino', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'genero', 'FE', NULL, 'Femenino', NULL, NULL, current_date, current_date, 'AC');


/**
 * autor: Eddy Valero
 * version 1.0, 09/08/2016
 * tabla smdominio, dominio = 'lugar_expedicion'
 */
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'lugar_expedicion', 'LPZ', NULL, 'La Paz', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'lugar_expedicion', 'ORU', NULL, 'Oruro', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'lugar_expedicion', 'CBA', NULL, 'Cochabamba', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'lugar_expedicion', 'SCZ', NULL, 'Santa Cruz', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'lugar_expedicion', 'PTS', NULL, 'Potosi', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'lugar_expedicion', 'PND', NULL, 'Pando', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'lugar_expedicion', 'CHQ', NULL, 'Chuquisaca', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'lugar_expedicion', 'TJA', NULL, 'Tarija', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'lugar_expedicion', 'BEN', NULL, 'Beni', NULL, NULL, current_date, current_date, 'AC');



/**
 * autor: Eddy Valero
 * version 1.0, 09/08/2016
 * tabla smdominio, dominio = 'pais'
 */
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'AF', NULL, 'Afganistan', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'AL', NULL, 'Albania', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'DE', NULL, 'Alemania', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'HV', NULL, 'Alto Volta', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'AD', NULL, 'Andorra', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'AO', NULL, 'Angola', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'AI', NULL, 'Anguilla', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'AG', NULL, 'Antigua y Barbuda', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'AN', NULL, 'Antillas Holandezas o Neerlandesas', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'SA', NULL, 'Arabia Saudita', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'DZ', NULL, 'Argelia', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'AR', NULL, 'Argentina', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'AM', NULL, 'Armenia', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'AW', NULL, 'Aruba', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'AU', NULL, 'Australia', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'AT', NULL, 'Austria', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'AZ', NULL, 'Azerbaiyán', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'BS', NULL, 'Bahamas', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'BH', NULL, 'Bahrein', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'BD', NULL, 'Bangladesh', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'BB', NULL, 'Barbados', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'BE', NULL, 'Belgica', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'BZ', NULL, 'Belice', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'BL', NULL, 'Benelux', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'BJ', NULL, 'Benin', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'BM', NULL, 'Bermuda', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'BT', NULL, 'Bhután', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'BY', NULL, 'Bielorrusia, Belarús', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'BU', NULL, 'Birmania', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'BO', NULL, 'Bolivia', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'BA', NULL, 'Bosnia y Herzegovina', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'BW', NULL, 'Botswana', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'BR', NULL, 'Brasil', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'BN', NULL, 'Brunei Darussalam', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'BG', NULL, 'Bulgaria', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'BF', NULL, 'Burkina Faso', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'BI', NULL, 'Burundi', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'CV', NULL, 'Cabo Verde', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'KH', NULL, 'Camboya', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'CM', NULL, 'Camerún', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'CA', NULL, 'Canadá', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'TD', NULL, 'Chad', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'CL', NULL, 'Chile', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'CN', NULL, 'China', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'CY', NULL, 'Chipre', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'CO', NULL, 'Colombia', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'KM', NULL, 'Comoras', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'CG', NULL, 'Congo', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'CI', NULL, 'Costa de Marfil, Côte D''ivoire', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'CR', NULL, 'Costa Rica', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'HR', NULL, 'Croacia', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'CU', NULL, 'Cuba', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'DK', NULL, 'Dinamarca', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'DJ', NULL, 'Djibounti', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'DM', NULL, 'Dominica', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'EC', NULL, 'Ecuador', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'EG', NULL, 'Egipto', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'SV', NULL, 'El Salvador', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'AE', NULL, 'Emiratos Arabes Unidos', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'ER', NULL, 'Eritrea', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'EI', NULL, 'Escocia', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'SK', NULL, 'Eslovaquia', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'SI', NULL, 'Eslovenia', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'ES', NULL, 'España', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'US', NULL, 'Estados Unidos de América', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'EE', NULL, 'Estonia', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'ET', NULL, 'Etiopia', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'MK', NULL, 'Ex República Yugoslava de Macedonia', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'RU', NULL, 'Federación de Rusia', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'FJ', NULL, 'Fiji', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'PH', NULL, 'Filipinas', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'FI', NULL, 'Finlandia', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'FR', NULL, 'Francia', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'GA', NULL, 'Gabón', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'GM', NULL, 'Gambia', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'GE', NULL, 'Georgia', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'GH', NULL, 'Ghana', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'GI', NULL, 'Gibraltar', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'GD', NULL, 'Granada', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'GR', NULL, 'Grecia', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'GL', NULL, 'Groenlandia', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'GT', NULL, 'Guatemala', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'GN', NULL, 'Guinea', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'GQ', NULL, 'Guinea Ecuatorial', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'GW', NULL, 'Guinea-Bissau', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'GY', NULL, 'Guyana', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'HT', NULL, 'Haití', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'NL', NULL, 'Holanda, Netherlands, Países Bajos', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'HN', NULL, 'Honduras', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'HK', NULL, 'Hong-Kong, ', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'HU', NULL, 'Hungría', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'IN', NULL, 'India', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'ID', NULL, 'Indonesia', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'IR', NULL, 'Iran, Republica Islamica', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'IQ', NULL, 'Iraq', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'IE', NULL, 'Irlanda', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'BV', NULL, 'Isla Bouvet', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'IS', NULL, 'Islandia', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'IA', NULL, 'Islas Antillas', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'KY', NULL, 'Islas Caiman', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'CK', NULL, 'Islas Cook', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'FK', NULL, 'Islas Falkland (Malvinas)', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'FO', NULL, 'Islas Feroe', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'GS', NULL, 'Islas Georgia del Sur e Islas Sandwich del Sur', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'MP', NULL, 'Islas Marianas Septentrionales', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'SB', NULL, 'Islas Salomón', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'TC', NULL, 'Islas Turcos y Caicos', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'VG', NULL, 'Islas Vírgenes (Británicas)', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'IL', NULL, 'Israel', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'IT', NULL, 'Italia', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'LY', NULL, 'Jamahiriya Arabe Libia', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'JM', NULL, 'Jamaica', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'JP', NULL, 'Japon', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'JO', NULL, 'Jordania', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'KZ', NULL, 'Kazajstán', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'KE', NULL, 'Kenya', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'KG', NULL, 'Kirguistán', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'KI', NULL, 'Kiribati', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'KW', NULL, 'Kuwait', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'LS', NULL, 'Lesotho', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'LV', NULL, 'Letonia', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'LB', NULL, 'Líbano', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'LR', NULL, 'Liberia', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'LI', NULL, 'Liechtenstein', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'LT', NULL, 'Lituania', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'LU', NULL, 'Luxemburgo', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'MO', NULL, 'Macao', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'MG', NULL, 'Magadascar', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'MY', NULL, 'Malasia', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'MW', NULL, 'Malawi', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'MV', NULL, 'Maldivas', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'ML', NULL, 'Malí', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'MT', NULL, 'Malta', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'MA', NULL, 'Marruecos', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'MU', NULL, 'Mauricio', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'MR', NULL, 'Mauritania', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'MX', NULL, 'México', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'MC', NULL, 'Mónaco', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'MN', NULL, 'Mongolia', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'MS', NULL, 'Montserrat', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'MZ', NULL, 'Mozambique', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'MM', NULL, 'Myanmar', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'NA', NULL, 'Namibia', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'NR', NULL, 'Nauru', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'NP', NULL, 'Nepal', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'NI', NULL, 'Nicaragua', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'NE', NULL, 'Níger', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'NG', NULL, 'Nigeria', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'NO', NULL, 'Noruega', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'NZ', NULL, 'Nueva Zelandia', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'BX', NULL, 'Oficina Benelux de Marcas (BBM) y Oficina Benelux de Dibujos y Modelos (BBDM)', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'GC', NULL, 'Oficina de Patentes del Consejo de Cooperación de los Estados Arabes del Golfo (CCG)', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'EM', NULL, 'Oficina Europea de la Marca Comunitaria, Oficina para la Armonización del Mercado Interior (Marcas, ', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'EP', NULL, 'Oficina Europea de Patentes (OEP)', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'WO', NULL, 'Oficina Internacional de la Organización Mundial de Propiedad Intelectual (OMPI)', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'OM', NULL, 'Oman', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'OA', NULL, 'Organización Africana de la Propiedad Intelectual (OAPI)', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'EA', NULL, 'Organización Eurasiática de Patentes', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'AP', NULL, 'Organización Regional Africana de la Propiedad Industrial (ARIPO)', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'PK', NULL, 'Pakistán', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'PW', NULL, 'Palau', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'PA', NULL, 'Panamá', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'PG', NULL, 'Papua Nueva Guinea', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'PY', NULL, 'Paraguay', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'PE', NULL, 'Peru', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'PL', NULL, 'Polonia', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'PT', NULL, 'Portugal', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'PR', NULL, 'Puerto Rico', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'QA', NULL, 'Qatar', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'GB', NULL, 'Reino Unido, Gran Bretaña, Inglaterra', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'CF', NULL, 'República Centroafricana', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'CZ', NULL, 'República Checa, Checoslovaquia', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'KR', NULL, 'República de Corea', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'MD', NULL, 'República de Moldova', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'CD', NULL, 'República Democrática del Congo', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'LA', NULL, 'República Democrática Popular Lao', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'DO', NULL, 'República Dominicana', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'KP', NULL, 'República Popular Democrática de Corea', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'RK', NULL, 'Republica Slovaka', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'TZ', NULL, 'República Unida de Tanzania', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'RH', NULL, 'Rhodesia del Sur', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'RO', NULL, 'Rumania', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'RW', NULL, 'Rwanda', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'EH', NULL, 'Sahara Occidental', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'KN', NULL, 'Saint Kitts y Nevis', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'WS', NULL, 'Samoa', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'SM', NULL, 'San Marino', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'VC', NULL, 'San Vicente y la Granadinas', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'SH', NULL, 'Santa Helena', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'LC', NULL, 'Santa Lucía', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'VA', NULL, 'Santa Sede, Vaticano', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'ST', NULL, 'Santo Tomé y Príncipe', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'SN', NULL, 'Senegal', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'YU', NULL, 'Serbia y Montenegro, Yugoeslavia, Yugoslavia', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'SC', NULL, 'Seychelles', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'SL', NULL, 'Sierra Leona', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'SG', NULL, 'Singapur', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'SY', NULL, 'Siria, República Arabe Siria', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'SO', NULL, 'Somalia', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'WS', NULL, 'Somoa', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'LK', NULL, 'Sri Lanka', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'ZA', NULL, 'Sudáfrica', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'SD', NULL, 'Sudán', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'SE', NULL, 'Suecia', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'CH', NULL, 'Suiza', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'SR', NULL, 'Suriname', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'SZ', NULL, 'Swazilandia', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'TH', NULL, 'Tailandia', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'TW', NULL, 'Taiwán, Provincia de China', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'TJ', NULL, 'Tayikistán', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'TL', NULL, 'Timor-Leste', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'TG', NULL, 'Togo', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'TO', NULL, 'Tonga', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'TT', NULL, 'Trinidad y Tobago', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'TN', NULL, 'Túnez', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'TM', NULL, 'Turkmenistán', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'TR', NULL, 'Turquia', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'TV', NULL, 'Tuvalu', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'SU', NULL, 'U.R.S.S.', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'UA', NULL, 'Ucrania RSS', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'UG', NULL, 'Uganda', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'EU', NULL, 'Unión Europea', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'UY', NULL, 'Uruguay', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'UZ', NULL, 'Uzbekistán', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'VU', NULL, 'Vanuato', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'VE', NULL, 'Venezuela', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'VM', NULL, 'Viet Nam', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'YE', NULL, 'Yemen', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'YD', NULL, 'Yemen Democratico', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'ZR', NULL, 'Zaire', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'ZM', NULL, 'Zambia', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'ZW', NULL, 'Zimbabwe', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'GG', NULL, 'Guernsey', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'MH', NULL, 'República de las Islas Marshall', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'PS', NULL, 'Estado de Palestina', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'IC', NULL, 'Islas del Canal', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'JE', NULL, 'Jersey', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'pais', 'S/P', NULL, 'Sin País', NULL, NULL, current_date, current_date, 'AC');

/**
 * autor: Eddy Valero
 * version 1.0, 09/08/2016
 * tabla smdominio, dominio = 'tipo_contacto'
 */
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_contacto', 'CEL', NULL, 'Celular', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_contacto', 'TEL', NULL, 'Teléfono', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_contacto', 'DIR', NULL, 'Dirección', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_contacto', 'CE', NULL, 'Correo Electrónico', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_contacto', 'FAX', NULL, 'Fax', NULL, NULL, current_date, current_date, 'AC');

/**
 * autor: Eddy Valero
 * version 1.0, 09/08/2016
 * tabla smdominio, dominio = 'tipo_signo'
 */
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_signo', 'DEN', NULL, 'Denominación', 'Denominación', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_signo', 'FIG', NULL, 'Figurativa', 'Figurativa', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_signo', 'TRI', NULL, 'Tridimensional', 'Tridimensional', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_signo', 'AUD', NULL, 'Auditiva', 'Auditiva', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_signo', 'ETI', NULL, 'Etiqueta', 'Etiqueta', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_signo', 'LOG', NULL, 'Logotipo', 'Logotipo', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_signo', 'OTRO', NULL, 'Otro', 'Otro', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_signo', 'SON', NULL, 'Sonora', 'Sonora', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_signo', 'ENV', NULL, 'Envase', 'Envase', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_signo', 'MIXT', NULL, 'Mixta', 'Mixta', NULL, current_date, current_date, 'AC');

/**
 * autor: Eddy Valero
 * version 1.0, 11/08/2016
 * tabla smdominio, dominio = 'tipo_prioridad'
 */
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_prioridad', 'OA', NULL, 'Oposición andina', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_prioridad', 'PRI', NULL, 'Prioridad', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_prioridad', 'PRE', NULL, 'Preferencia', NULL, NULL, current_date, current_date, 'AC');

/**
 * autor: Eddy Valero
 * version 1.0, 11/08/2016
 * tabla smdominio, dominio = 'tipo_interes'
 */
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_interes', 'OA', NULL, 'Oposición andina', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_interes', 'EXT', NULL, 'Extranjera', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_interes', 'EXP', NULL, 'Exposición', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_interes', 'OTR', NULL, 'Otro', NULL, NULL, current_date, current_date, 'IN');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_interes', 'ME', NULL, 'Micro Empresa', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_interes', 'PE', NULL, 'Pequeña Empresa', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio(idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_interes', 'AR', NULL, 'Artesano', NULL, NULL, current_date, current_date, 'AC');

/**
 * autor: Eddy Valero
 * version 1.0, 11/08/2016
 * tabla smdominio, dominio = 'ciudad_notificacion'
 */
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'ciudad_notificacion', 'LPZ', NULL, 'La Paz', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'ciudad_notificacion', 'SCZ', NULL, 'Santa Cruz', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'ciudad_notificacion', 'CBA', NULL, 'Cochabamba', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'ciudad_notificacion', 'ALT', NULL, 'El Alto', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'ciudad_notificacion', 'TJA', NULL, 'Tarija', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'ciudad_notificacion', 'CHQ', NULL, 'Chuquisaca', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'ciudad_notificacion', 'ORU', NULL, 'Oruro', NULL, NULL, current_date, current_date, 'AC');

/**
Creación Dominio ubicacion
Creado: Eddy Valero Fecha: 25/07/2016
*/
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'ubicacion', 'DESC', NULL, 'DESCONOCIDO', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'ubicacion', 'VENT', NULL, 'VENTANILLA UNICA  (RECEPCION)', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'ubicacion', 'PIND', NULL, 'PROPIEDAD INDUSTRIAL (EXAMEN DE FORMA)', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'ubicacion', 'SIPU', NULL, 'SISTEMAS (ASIGNACION NRO. DE PUBLICACION)', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'ubicacion', 'COMU', NULL, 'COMUNICACION (PARA PUBLICACION)', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'ubicacion', 'SIGA', NULL, 'SISTEMAS (EN GACETA PARA PUBLICACION)', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'ubicacion', 'PIPE', NULL, 'PROPIEDAD INDUSTRIAL (PERIODO DEMANDA OPOSICIONES)', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'ubicacion', 'PIOP', NULL, 'PROPIEDAD INDUSTRIAL (OPOSICION)', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'ubicacion', 'PIES', NULL, 'PROPIEDAD INDUSTRIAL (ESPERA DE SOLIC.II Parte)', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'ubicacion', 'PIAR', NULL, 'PROPIEDAD INDUSTRIAL (ANALISIS DE REGISTRABILIDAD)', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'ubicacion', 'BIBL', NULL, 'BIBLIOTECA (REGISTRADA)', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'ubicacion', 'API', NULL, 'ARCHIVO (PROP. INDUSTRIAL)', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'ubicacion', 'ARO', NULL, 'ARCHIVO (REGISTRADA-OTORGADA)', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'ubicacion', 'ADPI', NULL, 'ARCHIVO (DENEGADA - PROP. INDUSTRIAL)', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'ubicacion', 'AA', NULL, 'ARCHIVO (ABANDONADA)', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'ubicacion', 'AO', NULL, 'ARCHIVO (OPOSICIONES)', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'ubicacion', 'COGA', NULL, 'COMUNICACIONES (ENVIADO A GACETA PARA PUBLICACION)', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'ubicacion', 'COOP', NULL, 'COMUNICACIONES (EN ESPERA DE OPOSICION)', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'ubicacion', 'PIDE', NULL, 'PROPIEDAD INDUSTRIAL (DENAGADA)', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'ubicacion', 'RECA', NULL, 'RECAUDACIONES', NULL, NULL, current_date, current_date, 'AC');


/**
Creación Dominio estado_marca
Creado: Eddy Valero Fecha: 25/07/2016
*/
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'NUL', NULL, 'NULO', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'SO', NULL, 'SOLICITADA', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'SU', NULL, 'SUBSANADA', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'EF', NULL, 'EXAMEN DE FORMA', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'NOT', NULL, 'NOTIFICACION', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'OBS', NULL, 'OBSERVADA', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'PPO', NULL, 'PARA PUBLIC. EN OMISIONES', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'REV', NULL, 'REVIZADO', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'FERR', NULL, 'FE DE ERRATA', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'PR', NULL, 'PARA REPOSICION', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'RECH', NULL, 'RECHAZADA', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'PP', NULL, 'PARA PUBLICACION', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'EGPP', NULL, 'EN GACETA PARA PUBLICACION', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'SPP', NULL, 'SUBSANADAS PARA PUBLICACION', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'FEPP', NULL, 'FE DE ERRATAS PARA PUBLICACION', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'P', NULL, 'PUBLICADA', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'PO', NULL, 'PUBLICADA EN OMISIONES', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'PS', NULL, 'PUBLICADA EN SUBSANADOS', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'PFE', NULL, 'PUBLICADO EN FE DE ERRATAS', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'DESE', NULL, 'DESESTIMADA', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'OPO', NULL, 'EN OPOSICION', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'AOPO', NULL, 'ALERTA DE OPOCISION', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'AR', NULL, 'ANALISIS DE REGISTRABILIDAD', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'DC', NULL, 'DEMANDA DE CANCELACION', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'EADO', NULL, 'EN ARCHIVO DE OPOSICIONES', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'DENE', NULL, 'DENEGADA', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'ARCH', NULL, 'ARCHIVADA', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'REVO', NULL, 'EN REVOCATORIA', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'JERA', NULL, 'EN JERARQUICO', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'ANUL', NULL, 'ANULADA', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'CANC', NULL, 'CANCELADA', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'RETI', NULL, 'RETIRADA', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'MS', NULL, 'MAL SOLICITADA', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'DESI', NULL, 'DESISTIDA', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'PEPR', NULL, 'PERDIO PRIORIDAD', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'REG', NULL, 'REGISTRADA', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'PREN', NULL, 'PRESENTADA RENOVACION', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'CDDN', NULL, 'CON DEMANDA DE NULIDAD', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'RAR', NULL, 'RENUNCIA A REGISTRO', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'AP', NULL, 'ANOTACION PREVENTIVA', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'GH', NULL, 'GARANTIA HIPOTECARIA', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'LG', NULL, 'LIBERACION DE GRAVAMEN', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'ABAN', NULL, 'ABANDONADA', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'DESC', NULL, 'DESCONOCIDA', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'CADU', NULL, 'CADUCADA', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'RENO', NULL, 'RENOVADA', NULL, NULL, current_date, current_date, 'AC');


/**
Creación Dominio serie_titulo
Creado: Eddy Valero Fecha: 11/08/2016
*/
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'serie_titulo', 'A-01', NULL, 'A-01', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'serie_titulo', 'B-01', NULL, 'B-01', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'serie_titulo', 'C-01', NULL, 'C-01', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'serie_titulo', 'D-01', NULL, 'D-01', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'serie_titulo', 'E-01', NULL, 'E-01', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'serie_titulo', 'F-01', NULL, 'F-01', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'serie_titulo', 'G-01', NULL, 'G-01', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'serie_titulo', 'H-01', NULL, 'H-01', NULL, NULL, current_date, current_date, 'AC');

/**
Creación Dominio serie_registro
Creado: Eddy Valero Fecha: 11/08/2016
*/
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'serie_registro', 'C', NULL, 'C', NULL, 1, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'serie_registro', 'B', NULL, 'B', NULL, 2, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'serie_registro', 'RC', NULL, 'RC', NULL, 3, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'serie_registro', 'RC-A', NULL, 'RC-A', NULL, 4, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'serie_registro', 'RC-B', NULL, 'RC-B', NULL, 5, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'serie_registro', 'RC-C', NULL, 'RC-C', NULL, 6, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'serie_registro', 'C-A', NULL, 'C-A', NULL, 7, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'serie_registro', 'C-B', NULL, 'C-B', NULL, 8, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'serie_registro', 'C-C', NULL, 'C-C', NULL, 9, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'serie_registro', 'C-D', NULL, 'C-D', NULL, 10, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'serie_registro', 'C-E', NULL, 'C-E', NULL, 11, current_date, current_date, 'AC');


/**
Creación Dominio tipo_tramite_notificacion
Creado: Eddy Valero Fecha: 20/08/2016
*/
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_notificacion', 'SM', NULL, 'SM', 'Signo Marca', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_notificacion', 'SP', NULL, 'SP', 'Patente', NULL, current_date, current_date, 'AN');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_notificacion', 'SR', NULL, 'SR', 'Renovación', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_notificacion', 'ST-S', NULL, 'ST-S', 'Transferencia Santa Cruz', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_notificacion', 'ST-C', NULL, 'ST-C', 'Transferencia Cochabamba', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_notificacion', 'ST-E', NULL, 'ST-E', 'Transferencia El Alto', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_notificacion', 'ST', NULL, 'ST', 'Transferencia', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_notificacion', 'SF-S', NULL, 'SF-S', 'Fusión Santa Cruz', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_notificacion', 'SF-C', NULL, 'SF-C', 'Fusión Cochabamba', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_notificacion', 'SF-E', NULL, 'SF-E', 'Fusión El Alto', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_notificacion', 'SF', NULL, 'SF', 'Fusión', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_notificacion', 'LU-S', NULL, 'LU-S', 'Licencia de Uso Santa Cruz', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_notificacion', 'LU-C', NULL, 'LU-C', 'Licencia de Uso Cochabamba', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_notificacion', 'LU-E', NULL, 'LU-E', 'Licencia de Uso El Alto', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_notificacion', 'LU', NULL, 'LU', 'Licencia de Uso', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_notificacion', 'CD-S', NULL, 'CD-S', 'Cambio de Domicilio Santa Cruz', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_notificacion', 'CD-C', NULL, 'CD-C', 'Cambio de Domicilio Cochabamba', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_notificacion', 'CD-E', NULL, 'CD-E', 'Cambio de Domicilio El Alto', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_notificacion', 'CD', NULL, 'CD', 'Cambio de Domicilio Cochabamba', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_notificacion', 'CN-S', NULL, 'CN-S', 'Cambio de Nombre Santa Cruz', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_notificacion', 'CN-C', NULL, 'CN-C', 'Cambio de Nombre Cochabamba', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_notificacion', 'CN-E', NULL, 'CN-E', 'Cambio de Nombre El Alto', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_notificacion', 'CN', NULL, 'CN', 'Cambio de Nombre', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_notificacion', 'NPS', NULL, 'N° PUB SIGNO', 'Número de Publicación Signo', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_notificacion', 'NPP', NULL, 'N° PUB PATENTE', 'Número de Publicación Patente', NULL, current_date, current_date, 'AN');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_notificacion', 'NR', NULL, 'N° REG', 'Número de Registro', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_notificacion', 'IA', NULL, 'IA', '', NULL, current_date, current_date, 'AN');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_notificacion', 'OP', NULL, 'OPOSICION PAT', 'Oposición Patente', NULL, current_date, current_date, 'AN');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_notificacion', 'OS', NULL, 'OPOSICION SIG', 'Oposición Signos', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_notificacion', 'NO', NULL, 'N° OTORG', 'Número de Otorgación', NULL, current_date, current_date, 'AN');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_notificacion', 'RA', NULL, 'RA', 'Resolución Administrativa', NULL, current_date, current_date, 'AN');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_notificacion', 'CANC', NULL, 'CANCELACION', 'Cancelación', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_notificacion', 'NS', NULL, 'NULIDAD SIG', 'Nulidad Signos', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_notificacion', 'NP', NULL, 'NULIDAD PAT', 'Nulidad Patentes', NULL, current_date, current_date, 'AN');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_notificacion', 'COR', NULL, 'CORTE', 'Corte', NULL, current_date, current_date, 'AN');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_notificacion', 'TRAM', NULL, 'TRAM', '', NULL, current_date, current_date, 'AN');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_notificacion', 'BQ', NULL, 'BQ', 'Busqueda', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_notificacion', 'CE', NULL, 'CE', 'Certificación', NULL, current_date, current_date, 'AC');

/**
Creación Dominio estado_notificacion
Creado: Eddy Valero Fecha: 20/08/2016
*/
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_notificacion', 'DEV', NULL, 'Devuelto', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_notificacion', 'NOT', NULL, 'Notificado', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_notificacion', 'OBS', NULL, 'Observado', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_notificacion', 'ENV', NULL, 'Enviado', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_notificacion', 'DSC', NULL, 'En Distrital SCZ', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_notificacion', 'DCB', NULL, 'En Distrital CBBA', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_notificacion', 'DEA', NULL, 'En Distrital El Alto', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_notificacion', 'DSUC', NULL, 'En Distrital SCR', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_notificacion', 'DORU', NULL, 'En Distrital ORU', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_notificacion', 'DTRJ', NULL, 'En Distrital TRJ', NULL, NULL, current_date, current_date, 'AC');

INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_notificacion', 'REC', NULL, 'Recibido', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_notificacion', 'PRE', NULL, 'Prestado', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_notificacion', 'PREE', NULL, 'Pre envio', NULL, NULL, current_date, current_date, 'AC');


/**
Creación Dominio lugar_notificacion
Creado: Eddy Valero Fecha: 20/08/2016
*/
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'lugar_notificacion', 'DS', NULL, 'su domicilio señalado', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'lugar_notificacion', 'SE', NULL, 'secretaria de despacho', NULL, NULL, current_date, current_date, 'AC');


/**
Creación Dominio oficina
Creado: Eddy Valero Fecha: 22/08/2016
*/

INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'oficina', 'LPZ', NULL, 'La Paz', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'oficina', 'SCZ', NULL, 'Santa Cruz', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'oficina', 'CBA', NULL, 'Cochabamba', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'oficina', 'ALT', NULL, 'El Alto', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'oficina', 'TJA', NULL, 'Tarija', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'oficina', 'CHQ', NULL, 'Chuquisaca', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'oficina', 'ORU', NULL, 'Oruro', NULL, NULL, current_date, current_date, 'AC');

/**
Creación Dominio tipo_persona
Creado: Eddy Valero Fecha: 29/08/2016
*/
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_persona', 'SOLI', NULL, 'Solicitante', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_persona', 'APOD', NULL, 'Apoderado', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_persona', 'NTIT', NULL, 'Nuevo licenciatario', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_persona', 'LICE', NULL, 'Licenciatario', NULL, NULL, current_date, current_date, 'AC');



/**
Creación Dominio estado_renovacion
Creado: Chano Rojas Fecha: 02/09/2016
*/
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'estado_renovacion', 'SOLI', NULL, 'Solicitado(a)', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'estado_renovacion', 'OBSE', NULL, 'Observado(a)', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'estado_renovacion', 'OBSA', NULL, 'Observado(a) y en archivo', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'estado_renovacion', 'EMOD', NULL, 'En espera de modificación', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'estado_renovacion', 'RECH', NULL, 'Rechazado(a)', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'estado_renovacion', 'CADU', NULL, 'Caducado(a)', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'estado_renovacion', 'CONC', NULL, 'Concedido(a)', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'estado_renovacion', 'REDE', NULL, 'Retirado(a)/Desistido(a)', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'estado_renovacion', 'DESC', NULL, 'Desconocido(a)', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'estado_renovacion', 'NOTR', NULL, 'En notificación', NULL, NULL, current_date, current_date, 'AC');

/**
Creación Dominio estado_modificacion
Creado: Susana Escobar Fecha: 02/09/2016
*/
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'estado_modificacion', 'INGR', NULL, 'Ingresada', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'estado_modificacion', 'ACEP', NULL, 'Aceptada', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'estado_modificacion', 'RETI', NULL, 'Retirada', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'estado_modificacion', 'RECH', NULL, 'Rechazada', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'estado_modificacion', 'OBSE', NULL, 'Observada', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'estado_modificacion', 'DESI', NULL, 'Desistida', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'estado_modificacion', 'SUSP', NULL, 'Suspendida', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'estado_modificacion', 'ARCH', NULL, 'Archivo de obrados', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'estado_modificacion', 'ACMO', NULL, 'Aceptada – Modificada', NULL, NULL, current_date, current_date, 'AC');

/**
Creación Dominio tipo_titular_registrado
Creado: Eddy Valero Fecha: 02/09/2016
*/
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'tipo_titular_registrado', 'TREG', NULL, 'Titular registrado', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'tipo_titular_registrado', 'PFUS', NULL, 'Participante de la lista de fusión', NULL, NULL, current_date, current_date, 'AC');

/**
Creación Dominio estado_registro
Creado: Eddy Valero Fecha: 09/09/2016
Modificado: Susana Escobar Fecha: 07/12/2016
*/
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'estado_registro', 'ACEP', NULL, 'Aceptado', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'estado_registro', 'DENR', NULL, 'Denegado', NULL, NULL, current_date, current_date, 'AC');


/**
Creación Dominio serie_recibo
Creado: Eddy Valero Fecha: 12/09/2016
*/
INSERT INTO dominio( idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'serie_recibo', 'B', NULL, 'B', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio( idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'serie_recibo', 'C', NULL, 'C', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio( idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'serie_recibo', 'D', NULL, 'D', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio( idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'serie_recibo', 'E', NULL, 'E', NULL, NULL, current_date, current_date, 'AC');



/**
Creación Dominio tipo_modificacion
Creado: Eddy Valero Fecha: 13/09/2016
*/
INSERT INTO dominio( idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'tipo_modificacion', 'CANO', NULL, 'Cambio de Nombre', NULL, NULL, current_date, NULL, 'AC');
INSERT INTO dominio( idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'tipo_modificacion', 'CADO', NULL, 'Cambio de Domicilio', NULL, NULL, current_date, NULL, 'AC');
INSERT INTO dominio( idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'tipo_modificacion', 'CATR', NULL, 'Transferencia', NULL, NULL, current_date, NULL, 'AC');
INSERT INTO dominio( idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'tipo_modificacion', 'CAFU', NULL, 'Fusión', NULL, NULL, current_date, NULL, 'AC');
INSERT INTO dominio( idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'tipo_modificacion', 'CALU', NULL, 'Licencia de Uso', NULL, NULL, current_date, NULL, 'AC');


/**
Creación Dominio ubicacion_modificacion
Creado: Eddy Valero Fecha: 13/09/2016
*/
INSERT INTO dominio( idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'ubicacion_modificacion', 'UNMO', NULL, 'Unidad de modificaciones', NULL, NULL, current_date, NULL, 'AC');
INSERT INTO dominio( idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'ubicacion_modificacion', 'ARCH', NULL, 'Archivo central', NULL, NULL, current_date, NULL, 'AC');
INSERT INTO dominio( idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'ubicacion_modificacion', 'ARSE', NULL, 'Archivo SENAPI', NULL, NULL, current_date, NULL, 'AC');
INSERT INTO dominio( idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'ubicacion_modificacion', 'UNNO', NULL, 'Unidad de notificaciones', NULL, NULL, current_date, NULL, 'AC');

/**
Creación Dominio departamento
Creado: Eddy Valero Fecha: 13/09/2016
*/

INSERT INTO dominio( idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'departamento', 'LPZ', NULL, 'La Paz', NULL, NULL, current_date, NULL, 'AC');
INSERT INTO dominio( idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'departamento', 'ORU', NULL, 'Oruro', NULL, NULL, current_date, NULL, 'AC');
INSERT INTO dominio( idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'departamento', 'PTS', NULL, 'Potosi', NULL, NULL, current_date, NULL, 'AC');
INSERT INTO dominio( idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'departamento', 'CBA', NULL, 'Cochabamba', NULL, NULL, current_date, NULL, 'AC');
INSERT INTO dominio( idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'departamento', 'TJA', NULL, 'Tarija', NULL, NULL, current_date, NULL, 'AC');
INSERT INTO dominio( idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'departamento', 'SCZ', NULL, 'Santa Cruz', NULL, NULL, current_date, NULL, 'AC');
INSERT INTO dominio( idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'departamento', 'CHQ', NULL, 'Chuquisaca', NULL, NULL, current_date, NULL, 'AC');
INSERT INTO dominio( idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'departamento', 'BEN', NULL, 'Beni', NULL, NULL, current_date, NULL, 'AC');
INSERT INTO dominio( idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'departamento', 'PND', NULL, 'Pando', NULL, NULL, current_date, NULL, 'AC');

/**
Creación Dominio serie_renovacion
Creado: Eddy Valero Fecha: 14/09/2016
*/
INSERT INTO dominio( idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'serie_renovacion', 'A', NULL, 'A', NULL, NULL, current_date, current_date, 'AC');

/**
Creación Dominio serie_renovacion
Creado: Levi Laura Fecha: 25/11/2016
*/
INSERT INTO dominio( idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'serie_renovacion', 'A', NULL, 'A', NULL, NULL, current_date, current_date, 'AC');
/**
Creación Dominio tipo_documento para digitalizacion
Creado: Levi Laura Fecha: 25/11/2016
*/


INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_documento_dig', 'IMAG', NULL, 'Imagen', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_documento_dig', 'DECR', NULL, 'Decreto', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_documento_dig', 'DIBU', NULL, 'Dibujo', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_documento_dig', 'FORM', NULL, 'Formulario', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_documento_dig', 'RESO', NULL, 'Resolución', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_documento_dig', 'RESU', NULL, 'Resumen', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_documento_dig', 'OTRO', NULL, 'Otros', 'NULL', NULL, current_date, current_date, 'AC');

/**
Creación Dominio tipo_tramite_sticker para sticker
Creado: Ruben Ramirez Fecha: 03/12/2016
*/

INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'BQ', NULL, 'BÚSQUEDAS', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'BQ-C', NULL, 'BÚSQUEDAS CBBA', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'BQ-E', NULL, 'BÚSQUEDAS EL ALTO', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'BQ-S', NULL, 'BÚSQUEDAS SCZ', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'CD', NULL, 'CAMBIO DE DOMICILIO', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'CD-C', NULL, 'CAMBIO DE DOMICILIO CBBA', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'CD-E', NULL, 'CAMBIO DE DOMICILIO EL ALTO', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'CD-S', NULL, 'CAMBIO DE DOMICILIO SCZ', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'CE', NULL, 'CERTIFICACIONES', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'CE-C', NULL, 'CERTIFICACIONES CBBA', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'CE-E', NULL, 'CERTIFICACIONES EL ALTO', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'CE-S', NULL, 'CERTIFICACIONES SCZ', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'CN', NULL, 'CAMBIO DE NOMBRE', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'CN-C', NULL, 'CAMBIO DE NOMBRE CBBA', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'CN-E', NULL, 'CAMBIO DE NOMBRE EL ALTO', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'CN-S', NULL, 'CAMBIO DE NOMBRE SCZ', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'DA', NULL, 'DERECHOS DE AUTOR', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'DA-C', NULL, 'DERECHOS DE AUTOR CBBA', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'DA-E', NULL, 'DERECHOS DE AUTOR EL ALTO', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'DA-S', NULL, 'DERECHOS DE AUTOR SCZ', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'IF', NULL, 'INFRACCIONES', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'IF-C', NULL, 'INFRACCIONES CBBA', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'IF-E', NULL, 'INFRACCIONES EL ALTO', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'IF-S', NULL, 'INFRACCIONES SCZ', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'LU', NULL, 'LICENCIA DE USO', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'LU-E', NULL, 'LICENCIA DE USO EL ALTO', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'LU-S', NULL, 'LICENCIA DE USO SCZ', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'LU-C', NULL, 'LICENCIA DE USO CBBA', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'OP', NULL, 'OPOSICIONES', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'OP-C', NULL, 'OPOSICIONES CBBA', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'OP-E', NULL, 'OPOSICIONES EL ALTO', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'OP-S', NULL, 'OPOSICIONES SCZ', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'SF', NULL, 'FUSIÓN', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'SF-E', NULL, 'FUSIÓN EL ALTO', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'SF-C', NULL, 'FUSIÓN CBBA', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'SF-S', NULL, 'FUSIÓN SCZ', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'SM', NULL, 'SOLICITUD DE MARCA', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'SM-C', NULL, 'SOLICITUD DE MARCA CBBA', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'SM-E', NULL, 'SOLICITUD DE MARCA EL ALTO', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'SM-S', NULL, 'SOLICITUD DE MARCA SCZ', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'SP', NULL, 'PATENTES DE INVENCIÓN', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'SP-C', NULL, 'PATENTES DE INVENCIÓN CBBA', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'SP-E', NULL, 'PATENTES DE INVENCIÓN EL ALTO', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'SP-S', NULL, 'PATENTES DE INVENCIÓN SCZ', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'SR', NULL, 'RENOVACIÓN DE MARCA', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'SR-C', NULL, 'RENOVACIÓN DE MARCA CBBA', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'SR-E', NULL, 'RENOVACIÓN DE MARCA EL ALTO', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'SR-S', NULL, 'RENOVACIÓN DE MARCA SCZ', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'ST', NULL, 'TRANSFERENCIA', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'ST-C', NULL, 'TRANSFERENCIA CBBA', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'ST-E', NULL, 'TRANSFERENCIA EL ALTO', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_sticker', 'ST-S', NULL, 'TRANSFERENCIA SCZ', 'NULL', NULL, current_date, current_date, 'AC');


/***
Configuracion parametricas claseniza
Creado: Eddy Valero Fecha: 30/08/2016
*/
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  1, 'Productos químicos para la industria, la ciencia y la fotografía, así como para la agricultura, la horticultura y la silvicultura; resinas artificiales en bruto, materias plásticas en bruto; abonos para el suelo; composiciones extintoras; preparaciones para templar y soldar metales; productos químicos para conservar alimentos; materias curtientes; adhesivos (pegamentos) para la industria.', current_timestamp, NULL, '1', 'La clase 1 comprende principalmente los productos químicos que se utilizan en la industria, la ciencia y la agricultura, incluidos los que entran en la composición de productos comprendidos en otras clases.;Esta clase comprende en particular:;- el compost y los abonos orgánicos;- la sal de conservación que no sea para conservar alimentos;- ciertos aditivos destinados a la industria alimentaria (consultar la lista alfabética de productos).;Esta clase no comprende en particular:;- las resinas naturales en bruto (cl. 2);- los productos químicos para las ciencias médicas (cl. 5);- los fungicidas, los herbicidas y los productos para eliminar animales dañinos (cl. 5);- los adhesivos (pegamentos) de papelería o para uso doméstico (cl. 16);- la sal para conservar alimentos (cl. 30);- el pajote (cobertura de humus) (cl. 31).', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  2, 'Pinturas, barnices, lacas; productos contra la herrumbre y el deterioro de la madera; materias tintóreas; mordientes; resinas naturales en bruto; metales en hojas y en polvo para la pintura, la decoración, la imprenta y trabajos artísticos.', current_timestamp, NULL, '1', 'La clase 2 comprende principalmente las pinturas, los colorantes y los productos anticorrosivos.;Esta clase comprende en particular:;- las pinturas, los barnices y las lacas para la industria, la artesanía y el arte;- los tintes para prendas de vestir;- los colorantes para alimentos y bebidas.;Esta clase no comprende en particular:;- las resinas artificiales en bruto (cl. 1);- los colorantes para lavar y blanquear la ropa (cl. 3);- los tintes cosméticos (cl. 3);- las cajas de pinturas (material escolar) (cl. 16);- las pinturas y los barnices aislantes (cl. 17).', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  3, 'Preparaciones para blanquear y otras sustancias para lavar la ropa; preparaciones para limpiar, pulir, desengrasar y raspar; jabones no medicinales; productos de perfumería, aceites esenciales, cosméticos no medicinales, lociones capilares no medicinales; dentífricos no medicinales.', current_timestamp, NULL, '1', 'La clase 2 comprende principalmente las pinturas, los colorantes y los productos anticorrosivos.;Esta clase comprende en particular:;- las pinturas, los barnices y las lacas para la industria, la artesanía y el arte;- los tintes para prendas de vestir;- los colorantes para alimentos y bebidas.;Esta clase no comprende en particular:;- las resinas artificiales en bruto (cl. 1);- los colorantes para lavar y blanquear la ropa (cl. 3);- los tintes cosméticos (cl. 3);- las cajas de pinturas (material escolar) (cl. 16);- las pinturas y los barnices aislantes (cl. 17).', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  4, 'Aceites y grasas para uso industrial; lubricantes; composiciones para absorber, rociar y asentar el polvo; combustibles (incluida la gasolina para motores) y materiales de alumbrado; velas y mechas de iluminación.', current_timestamp, NULL, '1', 'La clase 4 comprende principalmente los aceites y las grasas para uso industrial, los combustibles y los materiales de alumbrado.;Esta clase no comprende en particular:;- ciertos aceites y grasas industriales especiales (consultar la lista alfabética de productos).', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  5, 'Productos farmacéuticos, preparaciones para uso médico y veterinario; productos higiénicos y sanitarios para uso médico; alimentos y sustancias dietéticas para uso médico o veterinario, alimentos para bebés; complementos alimenticios para personas o animales; emplastos, material para apósitos; material para empastes e improntas dentales; desinfectantes; productos para eliminar animales dañinos; fungicidas, herbicidas.', current_timestamp, NULL, '1', 'La clase 5 comprende principalmente los productos farmacéuticos y otros productos para uso médico o veterinario.;Esta clase comprende en particular:;- los productos de higiene personal que no sean de tocador;- los pañales para bebés e incontinentes;- los desodorizantes que no sean para personas o animales;- los complementos alimenticios destinados a completar una dieta normal o a beneficiar la salud;- los sustitutos de comidas, alimentos y bebidas dietéticas para uso médico o veterinario;- los cigarrillos sin tabaco para uso médico.;Esta clase no comprende en particular:;- los productos de higiene personal que sean de tocador (cl. 3);- los desodorantes para personas o animales (productos de perfumería) (cl. 3);- las vendas ortopédicas (cl. 10);- los sustitutos de comidas, alimentos y bebidas dietéticas que no sean para uso médico o veterinario (cl. 29, 30, 31, 32 ó 33).', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  6, 'Metales comunes y sus aleaciones, minerales metalíferos; materiales de construcción y edificación metálicos; construcciones transportables metálicas; cables e hilos metálicos no eléctricos; pequeños artículos de ferretería metálicos; recipientes metálicos de almacenamiento y transporte; cajas de caudales.', current_timestamp, NULL, '1', 'La clase 6 comprende principalmente los metales comunes en bruto y semielaborados, así como los productos simples fabricados a partir de éstos.Esta clase no comprende en particular:- la bauxita (cl. 1);- el mercurio, el antimonio, los metales alcalinos y los metales alcalinotérreos (cl. 1);- los metales en hojas y en polvo para pintores, decoradores, impresores y artistas (cl. 2).', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  7, 'Máquinas y máquinas herramientas; motores (excepto motores para vehículos terrestres); acoplamientos y elementos de transmisión (excepto para vehículos terrestres); instrumentos agrícolas que no sean accionados manualmente; incubadoras de huevos; distribuidores automáticos ', current_timestamp, NULL, '1', 'La clase 7 comprende principalmente las máquinas, las máquinas herramientas y los motores.;Esta clase comprende en particular:;- las partes de motores (de todo tipo);- las máquinas y aparatos eléctricos de limpieza.;Esta clase no comprende en particular:;- ciertas máquinas y máquinas herramientas especiales (consultar la lista alfabética de productos);- las herramientas e instrumentos de mano accionados manualmente (cl. 8);- los motores para vehículos terrestres (cl. 12).', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  8, 'Herramientas e instrumentos de mano accionados manualmente; artículos de cuchillería, tenedores y cucharas; armas blancas; maquinillas de afeitar.', current_timestamp, NULL, '1', 'La clase 8 comprende principalmente las herramientas e instrumentos de mano accionados manualmente.;Esta clase comprende en particular:;- los cuchillos, tenedores y cucharas de metales preciosos;- las navajas y maquinillas de afeitar, las esquiladoras y cortadoras de pelo (instrumentos de mano) y los cortaúñas eléctricos.;Esta clase no comprende en particular:- ciertos instrumentos especiales (consultar la lista alfabética de productos);- las herramientas e instrumentos accionados por motor (cl. 7);- los instrumentos cortantes para uso quirúrgico (cl. 10);- los cortapapeles (cl. 16);- las armas de esgrima (cl. 28).', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  9, 'Aparatos e instrumentos científicos, náuticos, geodésicos, fotográficos, cinematográficos, ópticos, de pesaje, de medición, de señalización, de control (inspección), de salvamento y de enseñanza; aparatos e instrumentos de conducción, distribución, transformación, acumulación, regulación o control de la electricidad; aparatos de grabación, transmisión o reproducción de sonido o imágenes; soportes de registro magnéticos, discos acústicos; discos compactos, DVD y otros soportes de grabación digitales; mecanismos para aparatos de previo pago; cajas registradoras, máquinas de calcular, equipos de procesamiento de datos, ordenadores; software; extintores. ', current_timestamp, NULL, '1', 'La Clase 9 comprende en particular:;- los aparatos e instrumentos de investigación científica para laboratorios;- los aparatos e instrumentos de control para embarcaciones, tales como los aparatos de medición y transmisión de órdenes;- los transportadores de ángulos;- las máquinas de tarjetas perforadas para oficinas;- los programas informáticos y el software de todo tipo, independientemente de su soporte de grabación o medio de difusión, incluido el software grabado en soportes magnéticos o descargado de una red informática remota.;Esta clase no comprende en particular:;- los siguientes aparatos eléctricos:a) los aparatos electromecánicos para uso culinario (trituradoras y batidoras de alimentos, exprimidores de fruta, molinillos de café eléctricos, etc.) y otros aparatos e instrumentos accionados por motor eléctrico, todos ellos clasificados en la clase 7;b) los aparatos de bombeo o distribución de combustibles (cl. 7);c) las navajas y maquinillas de afeitar, las esquiladoras y corta', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  10, 'Aparatos e instrumentos quirúrgicos, médicos, odontológicos y veterinarios; miembros, ojos y dientes artificiales; artículos ortopédicos; material de sutura; dispositivos terapéuticos y de asistencia para personas discapacitadas; aparatos de masaje; aparatos, dispositivos y artículos de puericultura; aparatos, dispositivos y artículos para actividades sexuales.', current_timestamp, NULL, '1', 'La clase 10 comprende principalmente los aparatos, instrumentos y artículos médicos.;Esta clase comprende en particular:;- el mobiliario especial para uso médico;- ciertos artículos sanitarios de caucho (consultar la lista alfabética de productos);- las vendas ortopédicas.', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  11, 'Aparatos de alumbrado, calefacción, producción de vapor, cocción, refrigeración, secado, ventilación y distribución de agua, así como instalaciones sanitarias.', current_timestamp, NULL, '1', 'La clase 11 comprende en particular:;- los aparatos de aire acondicionado;- los calentadores de cama, las bolsas de agua caliente, los calientacamas, eléctricos o no;- las almohadillas y mantas eléctricas que no sean para uso médico;- los hervidores eléctricos;- los utensilios de cocción eléctricos.;Esta clase no comprende en particular:- los aparatos de producción de vapor (partes de máquinas) (cl. 7);- la vestimenta electrotérmica (cl. 9).', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  12, 'Vehículos; aparatos de locomoción terrestre, aérea o acuática.', current_timestamp, NULL, '1', 'La clase 12 comprende en particular:;- los motores para vehículos terrestres;- los acoplamientos y elementos de transmisión para vehículos terrestres;- los aerodeslizadores.Esta clase no comprende en particular:;- ciertas partes de vehículos (consultar la lista alfabética de productos);- los materiales metálicos para vías férreas (cl. 6);- los motores, acoplamientos y elementos de transmisión que no sean para vehículos terrestres (cl. 7);- las partes de motores (de todo tipo) (cl. 7).', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  13, 'Armas de fuego; municiones y proyectiles; explosivos; fuegos artificiales.', current_timestamp, NULL, '1', 'La clase 13 comprende principalmente las armas de fuego y los productos pirotécnicos.;Esta clase no comprende en particular:;- las cerillas (cl. 34).', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  14, 'Metales preciosos y sus aleaciones; artículos de joyería, piedras preciosas y semipreciosas; artículos de relojería e instrumentos cronométricos.', current_timestamp, NULL, '1', 'La clase 14 comprende principalmente los metales preciosos, los productos fabricados con estas materias que no estén comprendidos en otras clases y, en general, los artículos de joyería, bisutería y relojería.;lista clase comprende en particular:;- los artículos de joyería, auténticos y de imitación;- los gemelos y los alfileres de corbata.;Esta clase no comprende en particular:;- los productos de metales preciosos clasificados según su función o destino, por ejemplo, los metales en hojas o en polvo para pintores, decoradores, impresores y artistas (cl. 2), las amalgamas dentales de oro (cl. 5), los artículos de cuchillería, los tenedores y cucharas (cl. 8), los contactos eléctricos (cl. 9), los plumines de oro para escribir (cl. 16), las teteras (cl. 21), los bordados en oro y plata (cl. 26), las cajas para puros (cl. 34);- los objetos de arte que no sean de metales preciosos (clasificados según la materia de la que están constituidos).', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  15, 'Instrumentos musicales.', current_timestamp, NULL, '1', 'La Clase 15 comprende en particular:;- los pianos mecánicos y sus accesorios;- las cajas de música;- los instrumentos musicales eléctricos y electrónicos.Esta clase no comprende en particular:;- los aparatos de grabación, transmisión, amplificación y reproducción de sonido (cl. 9).', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  16, 'Papel y cartón; productos de imprenta; material de encuadernación; fotografías; artículos de papelería y artículos de oficina, excepto muebles; adhesivos (pegamentos) de papelería o para uso doméstico; material para artistas y material de dibujo; pinceles; material de instrucción y material didáctico; hojas, películas y bolsas de materias plásticas para embalar y empaquetar; caracteres de imprenta, clichés de imprenta.', current_timestamp, NULL, '1', 'La clase 16 comprende principalmente el papel, los productos de papel y los artículos de oficina.Esta clase comprende en particular:;- los cortapapeles;- las multicopistas;- las hojas o bolsas de materias plásticas para embalar.;Esta clase no comprende en particular:;- ciertos productos de papel o cartón (consultar la lista alfabética de productos);- las pinturas (cl. 2);- las herramientas de mano para artistas (por ejemplo: las espátulas y cinceles de escultor) (cl. 8).', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  17, 'Caucho, gutapercha, goma, amianto y mica en bruto o semielaborados, así como sucedáneos de estos materiales; materias plásticas y resinas semielaboradas; materiales para calafatear, estopar y aislar; tubos flexibles no metálicos.', current_timestamp, NULL, '1', 'La clase 17 comprende principalmente los aislantes eléctricos, térmicos o acústicos, así como las materias plásticas semielaboradas en forma de hojas, placas o varillas.;Esta clase comprende en particular:;- la goma para recauchutar neumáticos;- los materiales de relleno de caucho o materias plásticas;- las barreras flotantes anticontaminación.', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  18, 'Cuero y cuero de imitación; pieles de animales; artículos de equipaje y bolsas de transporte; paraguas y sombrillas; bastones; fustas, arneses y artículos de guarnicionería; collares, correas y ropa para animales.', current_timestamp, NULL, '1', 'La clase 18 comprende principalmente el cuero, el cuero de imitación, los artículos de viaje no comprendidos en otras clases y los artículos de guarnicionería.;Esta clase no comprende en particular:;- las prendas de vestir, el calzado y los artículos de sombrerería (consultar la lista alfabética de productos).', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  19, 'Materiales de construcción no metálicos; tubos rígidos no metálicos para la construcción; asfalto, pez y betún; construcciones transportables no metálicas; monumentos no metálicos.', current_timestamp, NULL, '1', 'La clase 19 comprende principalmente los materiales de construcción no metálicos.;Esta clase comprende en particular:;- las maderas semielaboradas (por ejemplo: vigas, tablas, paneles);- las maderas contrachapadas;- el vidrio de construcción (por ejemplo: losas, tejas de vidrio);- las microesferas de vidrio para la señalización de carreteras;- los buzones de obra.;Esta clase no comprende en particular:;- los productos para conservar o impermeabilizar cemento (cl. 1);- los productos ignífugos (cl. 1);- la pez negra de zapatero (cl. 3).', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  20, 'Muebles, espejos, marcos; contenedores no metálicos de almacenamiento o transporte; hueso, cuerno, ballena o nácar, en bruto o semielaborados; conchas; espuma de mar; ámbar amarillo.', current_timestamp, NULL, '1', 'La clase 20 comprende principalmente los muebles y sus partes, así como los productos de materias plásticas no comprendidos en otras clases.;Esta clase comprende en particular:;- los muebles metálicos y los muebles de camping;- los accesorios para camas (por ejemplo: colchones, somieres, almohadas);- los espejos de mobiliario o de tocador;- las placas de matriculación no metálicas;- los buzones de correo no metálicos ni de obra.;Esta clase no comprende en particular:;- ciertos espejos especiales clasificados según su función o destino (consultar la lista alfabética de productos);- el mobiliario especial de laboratorio (cl. 9);- el mobiliario especial para uso médico (cl. 10);- la ropa de cama (cl. 24);- los edredones (cl. 24).', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  21, 'Utensilios y recipientes para uso doméstico y culinario; peines y esponjas; cepillos; materiales para fabricar cepillos; material de limpieza; vidrio en bruto o semielaborado, excepto el vidrio de construcción; artículos de cristalería, porcelana y loza.', current_timestamp, NULL, '1', 'La clase 21 comprende principalmente los pequeños aparatos y utensilios accionados manualmente para uso doméstico y culinario, así como los utensilios de tocador, los artículos de cristalería y porcelana.;Esta clase comprende en particular:;- los utensilios y recipientes para uso doméstico y culinario, por ejemplo: las baterías de cocina, los baldes, los barreños de hojalata, aluminio, materias plásticas u otras materiales y los aparatos pequeños para picar, moler o exprimir, accionados manualmente;- los peines eléctricos;- los cepillos de dientes eléctricos;- los salvamanteles, posabotellas y posavasos (vajilla).;Esta clase no comprende en particular:;- ciertos artículos de cristal, porcelana y loza (consultar la lista alfabética de productos);- los productos de limpieza, los jabones, etc. (cl. 3);- los aparatos pequeños para cortar, moler o exprimir, accionados eléctricamente (cl. 7);- las navajas y maquinillas de afeitar, las esquiladoras y cortadoras de pelo, los instrumentos metál', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  22, 'Cuerdas y cordeles; redes; tiendas de campaña y lonas; toldos de materias textiles o sintéticas; velas de navegación; sacos para el transporte y almacenamiento de mercancías a granel; materiales de acolchado y relleno, excepto el papel, cartón, caucho o materias plásticas; materias textiles fibrosas en bruto y sus sucedáneos.', current_timestamp, NULL, '1', 'La clase 22 comprende principalmente la cordelería y el velamen, los materiales de acolchado y relleno, así como las materias textiles fibrosas en bruto.;Esta clase comprende en particular:;- las cuerdas y cordeles de fibra textil natural o artificial, de papel o de materias plásticas.;Esta clase no comprende en particular:;- ciertas, redes, sacos y bolsas especiales (consultar la lista alfabética de productos);- las cuerdas para instrumentos musicales (cl. 15).', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  23, 'Hilos para uso textil.', current_timestamp, NULL, '1', 'La clase 23 comprende, en particular:;- cierta ropa y calzado especiales (consultar la lista alfabética de productos).', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  24, 'Tejidos y sus sucedáneos; ropa de hogar; cortinas de materias textiles o de materias plásticas.', current_timestamp, NULL, '1', 'La clase 24 comprende principalmente los tejidos y los cobertores.;Esta clase comprende en particular:;- la ropa de cama de papel.;Esta clase no comprende en particular:;- ciertos tejidos especiales (consultar la lista alfabética de productos);- las mantas eléctricas para uso médico (cl. 10) y no médico (cl. 11);- las mantelerías de papel (cl. 16);- las mantas para caballos (cl. 18).', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  25, 'Prendas de vestir,calzado, artículos de sombrerería.', current_timestamp, NULL, '1', '', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  26, 'Encajes y bordados, cintas y cordones; botones, ganchos y ojetes, alfileres y agujas; flores artificiales; adornos para el cabello; cabello postizo.', current_timestamp, NULL, '1', 'La clase 26 comprende principalmente los artículos de mercería y pasamanería.;Esta clase comprende en particular:;- las cremalleras (cierres).;Esta clase no comprende en particular:;- ciertos ganchos especiales (consultar la lista alfabética de productos);- ciertas agujas especiales (consultar la lista alfabética de productos);- los hilos para uso textil (cl. 23).', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  27, 'Alfombras, felpudos, esteras, linóleo y otros revestimientos de suelos; tapices murales que no sean de materias textiles.', current_timestamp, NULL, '1', 'La clase 27 comprende principalmente los productos destinados a recubrir o revestir, con el fin de acondicionar, los suelos o paredes ya construidos.;Esta clase no comprende en particular:;- los parqués.', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  28, 'Juegos y juguetes; aparatos de videojuegos; artículos de gimnasia y deporte; adornos para árboles de Navidad.', current_timestamp, NULL, '1', '', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  29, 'Carne, pescado, carne de ave y carne de caza; extractos de carne; frutas y verduras, hortalizas y legumbres en conserva, congeladas, secas y cocidas; jaleas, confituras, compotas; huevos; leche y productos lácteos; aceites y grasas comestibles.', current_timestamp, NULL, '1', 'La clase 29 comprende principalmente los productos alimenticios de origen animal, así como las verduras, hortalizas y legumbres, y otros productos hortícolas comestibles preparados para su consumo o conservación.;Esta clase comprende en particular:;- las bebidas lacteadas en las que predomine la leche.;Esta clase no comprende en particular:;- ciertos productos alimenticios de origen vegetal (consultar la lista alfabética de productos);- los alimentos para bebés (cl. 5);- los alimentos y sustancias dietéticas para uso médico (cl. 5);- los complementos alimenticios (cl. 5);- los aliños para ensaladas (cl. 30);- los huevos para incubar (cl. 31);- los alimentos para animales (cl. 31);- los animales vivos (cl. 31).', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  30, 'Café, té, cacao y sucedáneos del café; arroz; tapioca y sagú; harinas y preparaciones a base de cereales; pan, productos de pastelería y confitería; helados; azúcar, miel, jarabe de melaza; levadura, polvos de hornear; sal; mostaza; vinagre, salsas (condimentos); especias; hielo.', current_timestamp, NULL, '1', 'La clase 30 comprende principalmente los productos alimenticios de origen vegetal preparados para su consumo o conservación, así como los aditivos para realzar el sabor de los alimentos.;Esta clase comprende en particular:;- las bebidas a base de café, cacao, chocolate o té;- los cereales preparados para la alimentación humana (por ejemplo: copos de avena o de otros cereales).;Esta clase no comprende, en particular:;- ciertos productos alimenticios de origen vegetal (consultar la lista alfabética de productos);- la sal de conservación que no sea para conservar alimentos (cl. 1);- las infusiones medicinales y los alimentos y sustancias dietéticas para uso médico (cl. 5);- los alimentos para bebés (cl. 5);- los complementos alimenticios (cl. 5);- los cereales sin procesar (cl. 31);- los alimentos para animales (cl. 31).', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  31, 'Productos agrícolas, acuícolas, hortícolas y forestales en bruto y sin procesar; granos y semillas en bruto o sin procesar; frutas y verduras, hortalizas y legumbres frescas, hierbas aromáticas frescas; plantas y flores naturales; bulbos, plantones y semillas para plantar; animales vivos; productos alimenticios y bebidas para animales; malta.', current_timestamp, NULL, '1', 'La clase 31 comprende principalmente los productos de la tierra que no hayan sido procesados para su consumo, los animales vivos y las plantas vivas, así como los alimentos para animales.;Esta clase comprende en particular:;- las maderas en bruto;- los cereales sin procesar;- los huevos para incubar;- los moluscos y crustáceos vivos.;Esta clase no comprende en particular:;- los cultivos de microorganismos y las sanguijuelas para uso médico (cl. 5);- los complementos alimenticios para animales (cl. 5);- las maderas semielaboradas (cl. 19);- los cebos de pesca artificiales (cl. 28);- el arroz (cl. 30);- el tabaco (cl. 34).', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  32, 'Cervezas; aguas minerales y otras bebidas sin alcohol; bebidas a base de frutas y zumos de frutas; siropes y otras preparaciones para elaborar bebidas.', current_timestamp, NULL, '1', 'La clase 32 comprende principalmente las bebidas sin alcohol, así como las cervezas.;Esta clase comprende en particular:;- las bebidas desalcoholizadas.;Esta clase no comprende en particular:;- las bebidas para uso médico (cl. 5);- las bebidas lacteadas en las que predomine la leche (cl. 29);- las bebidas a base de café, cacao, chocolate o té (cl. 30).', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  33, 'Bebidas alcohólicas (excepto cerveza)', current_timestamp, NULL, '1', 'La clase 33 comprende en particular:;- las pociones medicinales (cl. 5);- las bebidas desalcoholizadas (cl. 32).', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  34, 'Tabaco; artículos para fumadores; cerillas.', current_timestamp, NULL, '1', 'La clase 34 comprende en particular:;- los sucedáneos del tabaco (que no sean para uso médico).;Esta clase no comprende en particular:;- los cigarrillos sin tabaco para uso médico (cl. 5).', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  35, 'Publicidad; gestión de negocios comerciales; administración comercial; trabajos de oficina.', current_timestamp, NULL, '2', 'La clase 35 comprende principalmente los servicios prestados por personas u organizaciones cuyo objetivo primordial es prestar asistencia en: 1) la explotación o dirección de una empresa comercial,; 2) la dirección de los negocios o actividades comerciales de una empresa industrial o comercial, así como los servicios prestados por empresas publicitarias cuya actividad principal consiste en publicar, en cualquier medio de difusión, comunicaciones, declaraciones o anuncios relacionados con todo tipo de productos o servicios.;Esta clase comprende en particular:;- el agrupamiento, en beneficio de terceros, de productos diversos (excepto su transporte), para que los consumidores puedan examinarlos y comprarlos a su conveniencia; este servicio puede ser prestado por comercios minoristas o mayoristas, o mediante catálogos de venta por correo o medios de comunicación electrónicos, por ejemplo, sitios web o programas de televenta;- los servicios que comprenden el registro, transcripción, compos', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  36, 'Servicios de seguros; operaciones financieras; operaciones monetarias; negocios inmobiliarios.', current_timestamp, NULL, '2', 'La clase 36 comprende principalmente los servicios prestados en el marco de operaciones financieras y monetarias, así como los servicios relacionados con contratos de seguros de todo tipo.;Esta clase comprende en particular:;- los servicios relacionados con operaciones financieras o monetarias, a saber:a) los servicios de instituciones bancarias o instituciones afines, tales como las operaciones de cambio o de compensación;b) los servicios de instituciones de crédito que no sean bancos, tales como las cooperativas de crédito, las compañías financieras individuales, los prestamistas, etc.;c) los servicios de sociedades de inversión y de sociedades de cartera;d) los servicios de corredores de bienes y valores;e) los servicios relacionados con operaciones monetarias con garantía de agentes fiduciarios;f) los servicios relacionados con la emisión de cheques de viaje y de cartas de crédito.;- los servicios de arrendamiento con opción de compra (leasing);- los servicios de administradores de', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  37, 'Servicios de construcción; servicios de reparación; servicios de instalación.', current_timestamp, NULL, '2', 'La clase 37 comprende principalmente los servicios prestados por empresarios o subcontratistas para la construcción o fabricación de edificios permanentes, así como los servicios prestados por personas u organizaciones que se encargan de restablecer el estado original de objetos o de preservarlos sin alterar sus propiedades físicas o químicas.;Esta clase comprende en particular:;- los servicios relacionados con la construcción de edificios, carreteras, puentes, presas o líneas de transmisión, así como los servicios de empresas especializadas en el campo de la construcción, tales como las empresas de pintura, fontanería, instalación de calefacción o techado;- los servicios anexos a los servicios de construcción, tales como la inspección de proyectos de construcción;- los servicios de construcción naval;- los servicios de alquiler de herramientas o de materiales de construcción;- los servicios de reparación, a saber, los servicios que consisten en dejar en buen estado cualquier objeto de', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  38, 'Telecomunicaciones.', current_timestamp, NULL, '2', 'La clase 38 comprende principalmente los servicios que permitan la comunicación, por medios sensoriales, entre dos o más personas, en particular:;1) la conversación entre dos personas;2) la transmisión de mensajes entre dos personas;3) la comunicación oral o visual (radio y televisión).;Esta clase comprende en particular:;- los servicios que consisten principalmente en la difusión de programas radiofónicos o de televisión.;Esta clase no comprende en particular:;- los servicios de publicidad radiofónica (cl. 35);- los servicios de marketing telefónico (telemarketing) (cl. 35).', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  39, 'Transporte; embalaje y almacenamiento de mercancías; organización de viajes.', current_timestamp, NULL, '2', 'La clase 39 comprende principalmente los servicios relacionados con el transporte de personas o mercancías de un lugar a otro (por ferrocarril, carretera, agua, aire o conducto) y los servicios afines, así como los servicios relacionados con el almacenamiento de mercancías en depósitos u otros edificios para su preservación o custodia.;Esta clase comprende en particular:- los servicios prestados por compañías que explotan estaciones, puentes, transbordadores (ferris), etc., utilizados por transportistas;- los servicios relacionados con el alquiler de vehículos de transporte;- los servicios relacionados con el remolque marítimo, la descarga de mercancías, el funcionamiento de puertos y muelles, así como el salvamento de buques en peligro y de sus cargamentos;- los servicios relacionados con el embalaje y empaquetado de productos previa expedición;- los servicios de información sobre viajes o transporte de mercancías prestados por intermediarios y agencias de turismo, así como los servic', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  40, 'Tratamiento de materiales.', current_timestamp, NULL, '2', 'La clase 40 comprende principalmente los servicios prestados en el marco del tratamiento o transformación mecánica o química de objetos o sustancias orgánicas o inorgánicas, no comprendidos en otras clases.;A efectos de clasificación, la marca se considera marca de servicio únicamente en los casos en que el tratamiento o la transformación sean efectuados por cuenta de terceros. Asimismo, la marca se considera marca de fábrica siempre que la persona que haya tratado o transformado el objeto o la sustancia también se encargue de su comercialización.;Esta clase comprende en particular:;- los servicios relacionados con la transformación de un objeto o sustancia y cualquier tratamiento que implique una modificación de sus propiedades esenciales (por ejemplo, el teñido de una prenda de vestir); por lo tanto, los servicios de mantenimiento que impliquen tal modificación se clasificarán en la clase 40, a pesar de que suelan estar comprendidos en la clase 37 (por ejemplo, el cromado del paracho', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  41, 'Educación; formación; servicios de entretenimiento; actividades deportivas y culturales.', current_timestamp, NULL, '2', 'La clase 41 comprende principalmente los servicios prestados por personas o instituciones para desarrollar las facultades mentales de personas o animales, así como los servicios destinados a divertir o entretener.;Esta clase comprende en particular:;- todos los servicios relacionados con la educación de personas o la doma y adiestramiento de animales;- los servicios cuyos principales propósitos son el recreo, diversión y entretenimiento de personas;- los servicios de presentación al público de obras de artes plásticas o de literatura con fines culturales o educativos.', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  42, 'Servicios científicos y tecnológicos, así como servicios de investigación y diseño en estos ámbitos; servicios de análisis e investigación industriales; diseño y desarrollo de equipos informáticos y de software.', current_timestamp, NULL, '2', 'La clase 42 comprende principalmente los servicios prestados por personas, a título individual o colectivo, relacionados con aspectos teóricos o prácticos de sectores de actividades de alta complejidad; dichos servicios son prestados por profesionales, tales como químicos, físicos, ingenieros, programadores informáticos, etc.;Esta clase comprende en particular:;- los servicios de ingenieros encargados de efectuar evaluaciones, estimaciones, investigaciones e informes en los ámbitos científico y tecnológico;- los servicios de investigación científica con fines médicos.;Esta clase no comprende en particular:;- la investigación y evaluación de negocios comerciales (cl. 35);- los servicios de tratamiento de texto y de gestión de archivos informáticos (cl. 35);- la evaluación fiscal y financiera (cl. 36);- los servicios de extracción minera y petrolera (cl. 37);- los servicios de instalación y reparación de ordenadores (cl. 37);- los servicios prestados por profesionales, tales como médicos', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  43, 'Servicios de restauración (alimentación); hospedaje temporal.', current_timestamp, NULL, '2', 'La clase 43 comprende principalmente los servicios que consisten en preparar alimentos y bebidas para el consumo, prestados por personas o establecimientos, así como los servicios de alojamiento, albergue y abastecimiento de comida en hoteles, pensiones u otros establecimientos que proporcionen hospedaje temporal.;Esta clase comprende en particular:;- los servicios de reserva de alojamiento para viajeros, prestados principalmente por agencias de viajes o corredores;- las residencias para animales.;Esta clase no comprende en particular:;- los servicios de alquiler de bienes inmuebles, tales como casas, apartamentos, etc., para la ocupación permanente (cl. 36);- los servicios de organización de viajes prestados por agencias de turismo (cl. 39);- los servicios de conservación de alimentos y bebidas (cl. 40);- los servicios de discotecas (cl. 41);- los servicios de internados (cl. 41);- los servicios de casas de reposo y convalecencia (cl. 44).', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  44, 'Servicios médicos; servicios veterinarios; tratamientos de higiene y de belleza para personas o animales; servicios de agricultura, horticultura y silvicultura.', current_timestamp, NULL, '2', 'La clase 44 comprende principalmente los tratamientos médicos, de higiene corporal y de belleza destinados a personas o animales, prestados por personas o establecimientos; comprende asimismo los servicios relacionados con los sectores de la agricultura, la horticultura y la silvicultura. Esta clase comprende en particular: - los servicios de análisis médicos relacionados con el tratamiento de personas (tales como los exámenes radiográficos y las extracciones de sangre);- los servicios de inseminación artificial;- las consultas farmacéuticas;- la cría de animales;- los servicios relacionados con el cultivo de plantas, tales como la jardinería;- los servicios relacionados con el arte floral, tales como los arreglos florales, y los servicios prestados por jardineros paisajistas.;Esta clase no comprende en particular:;- los servicios de eliminación de animales dañinos (que no estén relacionados con la agricultura, la horticultura y la silvicultura) (cl. 37);- los servicios de instalación ', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  45, 'Servicios jurídicos; servicios de seguridad para la protección física de bienes materiales y personas; servicios personales y sociales prestados por terceros para satisfacer necesidades individuales.', current_timestamp, NULL, '2', 'La clase 45 comprende en particular:- los servicios prestados por juristas a personas, grupos de personas, organizaciones o empresas;- los servicios de investigación y vigilancia relacionados con la seguridad de personas y colectividades;- los servicios prestados a personas en relación con acontecimientos sociales, tales como los servicios de acompañamiento en sociedad, las agencias matrimoniales y los servicios funerarios.;Esta clase no comprende en particular:;- los servicios profesionales de asistencia directa en operaciones o actividades de una empresa comercial (cl. 35);- los servicios relacionados con operaciones financieras o monetarias y los servicios relacionados con seguros (cl. 36);- los servicios de acompañamiento de viajeros (cl. 39);- los servicios de transporte de seguridad (cl. 39);- todos los servicios relacionados con la educación de personas (cl. 41);- los servicios de cantantes o bailarines (cl. 41);- los servicios informáticos relacionados con la protección de soft', '11', '2017', 'AC');
--INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  -1, 'Sin clase niza', current_timestamp, NULL, '0', 'Sin clase niza', '11', '2017', 'AC');
INSERT INTO claseniza(idlogtrans, numero_clase_niza, proteccion, fecha_inicio, fecha_fin, tipo_niza, nota_tipo_claseniza, numero_edicion, version, estado) VALUES (1,  0, '', current_timestamp, NULL, '0', 'Ninguno', '11', '2017', 'AC');


/***
Configuracion parametricas formulario PI100, PI101 y PI102
Creado: Eddy Valero Fecha: 10/09/2016
*/
INSERT INTO formulariotramite (idlogtrans, codigo, nombreformulario, fechainicio, fechafin,  estado, etapa) VALUES ( 1, 'REGS', 'SOLICITUD PI100, PI101 Y PI102', current_timestamp, NULL, 'AC', 'VENT');

INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 1, 1, 'MINIMO', NULL, NULL, 'TEH2', 'Requisitos mínimos', 10, NULL, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 1, 1, 'MINIMO', NULL, NULL, 'TEH7', 'Tres copias de la primera hoja y una sola copia de las páginas; dos, tres cuatro y cinco', 20, NULL, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 1, 1, 'MINIMO', NULL, NULL, 'TEH7', 'Pago por concepto de solicitud de registro de Signos Distintivos a la cuenta del SENAPI', 30, NULL, current_timestamp, NULL, NULL, true, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 1, 1, 'MINIMO', NULL, NULL, 'TEH7', 'Nacional', 40, 3, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 1, 1, 'MINIMO', NULL, NULL, 'TEH7', 'Extranjera', 50, 3, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 1, 1, 'MINIMO', NULL, NULL, 'TEH7', 'Pago por concepto de publicación a la cuenta de la Gaceta Oficial de Bolivia, con copia simple', 60, NULL, current_timestamp, NULL, NULL, true, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 1, 1, 'OPCIONAL', NULL, NULL, 'TEH2', 'Requisitos opcionales', 70, NULL, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 1, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'La solicitud de registro de signos distintivos mediante nota o memorial', 80, NULL, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 1, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Nota', 90, 8, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 1, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Memorial', 100, 8, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 1, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Juego de Logo 4x4 en tres ejemplares', 110, NULL, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 1, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Poderes necesarios en caso de representación (Testimonios)', 120, NULL, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 1, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Original', 130, 12, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 1, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Fotocopia Legalizada', 140, 12, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 1, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Fotocopia Simple', 150, 12, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 1, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Empresa Unipersonal (NIT) o Registro de Comercio', 160, NULL, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 1, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Original', 170, 16, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 1, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Fotocopia Legalizada', 180, 16, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 1, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Fotocopia Simple', 190, 16, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 1, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Persona Natural', 200, NULL, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 1, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Fotocopia CI', 210, 20, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 1, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Otro', 220, 20, current_timestamp, NULL, NULL, NULL, 'AC');

/***
Configuracion parametricas formulario PI103
Creado: Eddy Valero Fecha: 27/10/2016
*/
INSERT INTO formulariotramite (idlogtrans, codigo, nombreformulario, fechainicio, fechafin,  estado, etapa) VALUES ( 1, 'MODI', 'SOLICITUD PI103', current_timestamp, NULL, 'AC', 'VENT');

INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 2, 1, 'MINIMO', NULL, NULL, 'TEH2', 'Requisitos mínimos', 10, NULL, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 2, 1, 'MINIMO', NULL, NULL, 'TEH7', 'El formulario de solicitud de modificación de registro una copia de la primera hoja (ver página del SENAPI)', 20, NULL, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 2, 1, 'MINIMO', NULL, NULL, 'TEH7', 'Pago por concepto de solicitud de modificación de registro de signo distintivo a la cuenta del SENAPI', 30, NULL, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 2, 1, 'MINIMO', NULL, NULL, 'TEH7', 'Nacional', 40, 25, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 2, 1, 'MINIMO', NULL, NULL, 'TEH7', 'Extranjera', 50, 25, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 2, 1, 'OPCIONAL', NULL, NULL, 'TEH2', 'Requisitos opcionales', 60, NULL, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 2, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'La solicitud de modificación de registro de signo distintivo mediante carta o memorial', 70, NULL, current_timestamp, NULL, NULL, true, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 2, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Nota', 80, 29, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 2, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Memorial', 90, 29, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 2, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Los poderes necesarios en caso de representación o testimonio', 100, NULL, current_timestamp, NULL, NULL, true, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 2, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Original', 110, 32, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 2, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Fotocopia Legalizada', 120, 32, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 2, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Fotocopia Simple', 130, 32, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 2, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Empresa Unipersonal (NIT) o Registro de Comercio', 140, NULL, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 2, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Original', 150, 36, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 2, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Fotocopia Legalizada', 160, 36, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 2, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Fotocopia Simple', 170, 36, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 2, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Persona Natural', 180, NULL, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 2, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Fotocopia CI', 190, 40, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 2, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Otro', 200, 40, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 2, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Testimonio respecto a la modificación', 300, NULL, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 2, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Original', 310, 43, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 2, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Fotocopia Legalizada', 320, 43, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 2, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Fotocopia Simple', 330, 43, current_timestamp, NULL, NULL, NULL, 'AC');

/***
Configuracion parametricas formulario PI104
Creado: Eddy Valero Fecha: 27/10/2016
*/
INSERT INTO formulariotramite (idlogtrans, codigo, nombreformulario, fechainicio, fechafin,  estado, etapa) VALUES ( 1, 'RENO', 'SOLICITUD PI104', current_timestamp, NULL, 'AC', 'VENT');

INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 3, 1, 'MINIMO', NULL, NULL, 'TEH2', 'Requisitos mínimos', 10, NULL, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 3, 1, 'MINIMO', NULL, NULL, 'TEH7', 'Formulario de solicitud de Renovación de Registro, con un ejemplar', 20, NULL, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 3, 1, 'MINIMO', NULL, NULL, 'TEH7', 'Pago por concepto de solicitud de registro de Renovación de Registro, a la cuenta del SENAPI', 30, NULL, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 3, 1, 'MINIMO', NULL, NULL, 'TEH7', 'Nacional', 40, 49, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 3, 1, 'MINIMO', NULL, NULL, 'TEH7', 'Extranjera', 50, 49, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 3, 1, 'OPCIONAL', NULL, NULL, 'TEH2', 'Requisitos opcionales', 60, NULL, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 3, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Solicitud de Renovación mediante nota o memorial', 70, NULL, current_timestamp, NULL, NULL, true, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 3, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Nota', 80, 53, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 3, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Memorial', 90, 53, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 3, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Los poderes necesarios en caso de representación o testimonio', 100, NULL, current_timestamp, NULL, NULL, true, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 3, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Original', 110, 56, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 3, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Fotocopia Legalizada', 120, 56, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 3, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Fotocopia Simple', 130, 56, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 3, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Empresa Unipersonal (NIT) o Registro de Comercio', 140, NULL, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 3, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Original', 150, 60, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 3, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Fotocopia Legalizada', 160, 60, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 3, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Fotocopia Simple', 170, 60, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 3, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Persona Natural', 180, NULL, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 3, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Fotocopia CI', 190, 64, current_timestamp, NULL, NULL, NULL, 'AC');
INSERT INTO elementoformulariotramite(idformulariotramite, idlogtrans, pestania, seccion, fila, tipo_elemento, nombre_elemento, orden, idpadre, fechainicio, fechafin, respuesta, opcion_respuesta, estado) VALUES ( 3, 1, 'OPCIONAL', NULL, NULL, 'TEH7', 'Otro', 200, 64, current_timestamp, NULL, NULL, NULL, 'AC');

/*********************************************************************/

/**
Creación Tabla opoestado
Creado: Luis Angel Quispe Limachi 06/09/2016
*/

INSERT INTO opoestado(idarea,idlogtrans,descri_idestadooposicion,estado,orden)VALUES(1, 1,'S','SUSPENDIDA',0);
INSERT INTO opoestado(idarea,idlogtrans,descri_idestadooposicion,estado,orden)VALUES(1, 1,'P','PRESENTADA',1);
INSERT INTO opoestado(idarea,idlogtrans,descri_idestadooposicion,estado,orden)VALUES(1, 1,'L','LIMITACION',5);
INSERT INTO opoestado(idarea,idlogtrans,descri_idestadooposicion,estado,orden)VALUES(1, 1,'A','ADMITIDA',10);
INSERT INTO opoestado(idarea,idlogtrans,descri_idestadooposicion,estado,orden)VALUES(1, 1,'OB','OBSERVADA',20);
INSERT INTO opoestado(idarea,idlogtrans,descri_idestadooposicion,estado,orden)VALUES(1, 1,'C','CONTESTADA',30);
INSERT INTO opoestado(idarea,idlogtrans,descri_idestadooposicion,estado,orden)VALUES(1, 1,'CC','CONTESTADA Y CANCELACION',40);
INSERT INTO opoestado(idarea,idlogtrans,descri_idestadooposicion,estado,orden)VALUES(1, 1,'PP','PRESENTADAS PRUEBAS',45);
INSERT INTO opoestado(idarea,idlogtrans,descri_idestadooposicion,estado,orden)VALUES(1, 1,'R','RECHAZADA',50);
INSERT INTO opoestado(idarea,idlogtrans,descri_idestadooposicion,estado,orden)VALUES(1, 1,'SA','SUBSANADA Y ADMITIDA',60);
INSERT INTO opoestado(idarea,idlogtrans,descri_idestadooposicion,estado,orden)VALUES(1, 1,'AR','AUTOS PARA RESOLUCION',70);
INSERT INTO opoestado(idarea,idlogtrans,descri_idestadooposicion,estado,orden)VALUES(1, 1,'IP','PRESENTADO INFORME PERITO',75);
INSERT INTO opoestado(idarea,idlogtrans,descri_idestadooposicion,estado,orden)VALUES(1, 1,'R1','RESOLUCION 1RA INSTANCIA',80);
INSERT INTO opoestado(idarea,idlogtrans,descri_idestadooposicion,estado,orden)VALUES(1, 1,'RR','EN REVOCATORIA',90);
INSERT INTO opoestado(idarea,idlogtrans,descri_idestadooposicion,estado,orden)VALUES(1, 1,'RJ','EN JERARQUICO',100);
INSERT INTO opoestado(idarea,idlogtrans,descri_idestadooposicion,estado,orden)VALUES(1, 1,'RS','REMITIDO A SIGNOS DISTINTIVOS',110);
INSERT INTO opoestado(idarea,idlogtrans,descri_idestadooposicion,estado,orden)VALUES(1, 1,'RA','REMITIDO A ARCHIVO',120);
INSERT INTO opoestado(idarea,idlogtrans,descri_idestadooposicion,estado,orden)VALUES(1, 1,'RT','RETIRADA',125);
INSERT INTO opoestado(idarea,idlogtrans,descri_idestadooposicion,estado,orden)VALUES(1, 1,'RTS','SOLICITUD DE REGISTRO RETIRADA',126);

/**
Creación Tabla opoactividad
Creado: Luis Angel Quispe Limachi 06/09/2016
*/

INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(2,1,'PUB','Publicación',1);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(2,1,'P','Presentación',2);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(5,1,'OB','Observación',4);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(5,1,'NOB','Notificación con observación',6);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(9,1,'R','Rechazo',7);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(9,1,'NR','Notificación con Rechazo',8);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(9,1,'PRRR','Presentación R. Revocatoria Rechazo',9);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(9,1,'NRRR','Notificación con R. Revocatoria Rechazo',10);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(9,1,'RARRR','Resolución R. Revocatoria Rechazo',11);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(9,1,'NRARRR','Notificación con RA de R. Revocatoria Rechazo',12);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(9,1,'PRJR','Presentación R. Jerárquico Rechazo',13);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(9,1,'NRJR','Notificación con R. Jerárquico Rechazo',14);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(9,1,'RARJR','Resolución R. Jerárquico Rechazo',15);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(9,1,'NRARJR','Notificación con RA de R. Jerárquico Rechazo',16);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(8,1,'PPDT','Presentación de Pruebas del Opositor',18);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(10,1,'SA','Subsanación y Admisión',19);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(4,1,'A','Admisión',20);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(4,1,'NAO','Notificación Admisión de Oposición',24);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(4,1,'NDO','Notificación con Demanda de Oposición',25);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(6,1,'C','Contestación',30);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(7,1,'CC','Contestación con Cancelación como Defensa',31);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(7,1,'AC','Admisión Cancelación',32);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(7,1,'OC','Observación Cancelación',33);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(7,1,'NOC','Notificación con observación Cancelación',34);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(7,1,'SAC','Subsanación y Admisión Cancelación',35);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(7,1,'RC','Rechazo Cancelación',36);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(7,1,'NDCC','Notificación con Demanda de Cancelación',37);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(7,1,'CCC','Contestación de Cancelación',38);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(7,1,'NRC','Notificación con Rechazo Cancelación',39);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(7,1,'PRRRC','Presentación R. Revocatoria Rechazo Cancelación',40);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(7,1,'NRRRC','Notificación con R. Revocactoria Rechazo Cancelación',41);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(7,1,'RARRRC','Resolución R. Revocatoria Rechazo Cancelación',42);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(7,1,'NRRRRC','Notificación con RA de R. Revocatoria Rechazo Cancelación',43);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(7,1,'PRJRC','Presentación R. Jerárquico Rechazo Cancelación',44);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(7,1,'NRJRC','Notificación con R. Jerárquico Rechazo Cancelación',45);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(7,1,'RARJRC','Resolución R. Jerárquico Rechazo Cancelación',46);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(7,1,'NRRJRC','Notificación con RA de R. Jerárquico Rechazo Cancelación',47);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(8,1,'PPDO','Presentación de Pruebas del Solicitante',49);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(11,1,'AR','Autos para Resolución',50);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(12,1,'PIP','Presentación Informe de Perito',52);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(13,1,'R1','Resolución 1ra Instancia',55);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(13,1,'NR1DO','Notificación con Resolución 1ra Instancia al Solicitante',60);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(13,1,'NR1DT','Notificación con Resolución 1ra Instancia al Opositor',61);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(14,1,'PRRDO','Presentación Recurso Revocatoria del Solicitante',65);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(14,1,'PRRDT','Presentación Recurso Revocatoria Opositor',66);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(14,1,'NRRDO','Notificación con Recurso Revocatoria a Solicitante',70);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(14,1,'NRRDT','Notificación con Recurso Revocatoria a Opositor',71);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(14,1,'RRDO','Resolución R. Revocatoria del Solicitante',75);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(14,1,'RRDT','Resolución R. Revocatoria del Opositor',76);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(14,1,'NRARDO','Notificación con Resolución  de Revocatoria a Solicitante',80);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(14,1,'NRARDT','Notificación con Resolución  de Revocatoria al Opositor',81);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(15,1,'PRJDO','Presentación Recurso Jerárquico del Solicitante',85);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(15,1,'PRJDT','Presentación Recurso Jerárquico delOpositor',86);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(15,1,'NRJDO','Notificación con Recurso Jerárquico al Solicitante',90);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(15,1,'NRJDT','Notificación con Recurso Jerárquico al Opositor',91);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(15,1,'RJDO','Resolución R. Jerárquico del Solicitante',95);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(15,1,'RJDT','Resolución R. Jerárquico del Opositor',96);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(15,1,'NRAJDO','Notificación con Resolución de Jerarquico al Solicitante',97);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(15,1,'NRAJDT','Notificación con Resolución de Jerarquico al Opositor',98);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(16,1,'RS','Remisión a Signos Distintivos',100);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(17,1,'RA','Remisión a Archivo',105);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(1,1,'S','Suspensión',110);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(18,1,'RT','Retiro de Oposición',115);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(19,1,'RTS','Retiro de Solicitud de Registro',116);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(13,1,'RTRRDO','Retiro de Recurso Revocatorio Solicitante',117);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(14,1,'RTRJDO','Retiro de Recurso Jerárquico Solicitante',118);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(6,1,'RTC','Retiro de Cancelación',119);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(7,1,'RTRRRC','Retiro de Recurso Revocatorio Rechazo Cancelación',120);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(9,1,'RTRRR','Retiro de Recurso Revocatorio Rechazo',121);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(9,1,'RTRJR','Retiro de Recurso Jerárquico Rechazo',122);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(7,1,'RTRJRC','Retiro de Recurso Jerárquico Rechazo Cancelación',123);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(13,1,'RTRRDT','Retiro de Recurso Revocatorio Opositor',124);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(14,1,'RTRJDT','Retiro de Recurso Jerárquico Opositor',125);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(3,1,'L','Limitación',126);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(17,1,'NRADT','Notificación Remisión Archivo Opositor',127);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(17,1,'NRADO','Notificación Remisión Archivo Solicitante',128);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(16,1,'NRSDT','Notificación Remisión a Signos Opositor',129);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(16,1,'NRSDO','Notificación Remisión a Signos Solicitante',130);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(7,1,'CRRRC','Contestación Recurso Revocatorio Rechazo Cancelación',130);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(7,1,'CRJRC','Contestación Recurso Jerárquico Rechazo Cancelación',131);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(9,1,'CRRR','Contestación Recurso Revocatorio Rechazo',132);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(9,1,'CRJR','Contestación Recurso Jerárquico Rechazo',133);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(14,1,'CRRDO','Contestación Recurso Revocatorio del Solicitante',134);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(14,1,'CRRDT','Contestación Recurso Revocatorio del Opositor',135);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(15,1,'CRJDO','Contestación Recurso Jerárquico del Solicitante',136);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(15,1,'CRJDT','Contestación Recurso Jerárquico del Opositor',137);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(18,1,'NRTDO','Notificación del Retiro de Oposición al Demandado',138);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(18,1,'NRTDT','Notificación del Retiro de Oposición al Demandante',139);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(19,1,'NRTSDO','Notificación del Retiro de la Solicitud al Demandado',140);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(19,1,'NRTSDT','Notificación del Retiro de la Solicitud al Demandante',141);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(6,1,'NRTCDO','Notificación del Retiro de la Cancelación al Demandado',142);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(6,1,'NRTCDT','Notificación del Retiro de la Cancelación al Demandado',143);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(14,1,'RR','Presentación Recurso de Revocatoria',144);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(15,1,'RJ','Presentación Recurso Jerárquico',145);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(14,1,'NRR','Notificación con Recurso de Revocatoria',146);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(15,1,'NRJ','Notificación con Recurso Jerárquico',147);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(14,1,'RRR','Resolución Recurso Revocatoria',148);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(15,1,'RRJ','Resolución Recurso Jerárquico',149);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(14,1,'NRARR','Notificación con RA de Recurso de Revocatoria',150);
INSERT INTO opoactividad(idestado,idlogtrans,descri_idactividad,actividad,orden)VALUES(15,1,'NRARJ','Notificación con RA de Recurso Jerárquico',151);


/**
Creación opoflujoactividad
Creado: Luis Angel Quispe Limachi 06/09/2016
*/

INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(17,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(17,1,18,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(17,1,19,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(17,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(17,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(17,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(17,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(17,1,2,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(17,1,15,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(17,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(17,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(17,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(17,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(17,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(17,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(17,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(22,1,20,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(22,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(22,1,27,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(22,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(22,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(22,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(22,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(22,1,38,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(22,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(22,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(22,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(22,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(22,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(22,1,67,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(22,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(22,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(39,1,20,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(39,1,28,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(39,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(39,1,27,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(39,1,19,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(39,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(39,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(39,1,29,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(39,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(39,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(39,1,37,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(39,1,33,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(39,1,40,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(39,1,38,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(39,1,15,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(39,1,41,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(39,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(39,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(39,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(39,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(39,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(39,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(39,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(20,1,39,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(20,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(20,1,19,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(20,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(20,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(20,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(20,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(20,1,38,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(20,1,15,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(20,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(20,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(20,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(20,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(20,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(20,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(20,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(21,1,22,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(21,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(21,1,19,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(21,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(21,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(21,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(21,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(21,1,23,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(21,1,38,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(21,1,15,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(21,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(21,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(21,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(21,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(21,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(21,1,67,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(21,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(21,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(28,1,39,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(28,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(28,1,27,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(28,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(28,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(28,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(28,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(28,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(28,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(28,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(28,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(28,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(28,1,67,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(28,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(28,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(85,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(85,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(85,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(85,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(85,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(85,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(85,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(85,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(85,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(85,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(86,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(86,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(86,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(86,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(86,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(86,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(86,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(86,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(86,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(86,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(82,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(82,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(82,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(82,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(82,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(82,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(82,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(82,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(82,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(82,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(80,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(80,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(80,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(80,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(80,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(80,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(80,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(80,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(80,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(80,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(83,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(83,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(83,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(83,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(83,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(83,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(83,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(83,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(83,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(83,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(84,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(84,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(84,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(84,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(84,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(84,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(84,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(84,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(84,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(84,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(81,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(81,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(81,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(81,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(81,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(81,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(81,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(81,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(81,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(81,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(79,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(79,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(79,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(79,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(79,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(79,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(79,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(79,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(79,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(79,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(74,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(74,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(74,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(74,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(74,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(74,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(74,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(74,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(74,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(74,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(18,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(18,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(18,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(18,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(18,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(18,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(18,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(18,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(18,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(18,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(27,1,22,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(27,1,39,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(27,1,28,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(27,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(27,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(27,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(27,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(27,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(27,1,38,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(27,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(27,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(27,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(27,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(27,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(27,1,67,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(27,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(27,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(27,1,25,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(19,1,17,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(19,1,39,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(19,1,20,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(19,1,21,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(19,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(19,1,18,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(19,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(19,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(19,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(19,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(19,1,15,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(19,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(19,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(19,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(19,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(19,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(19,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(19,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(19,1,16,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(4,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(4,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(4,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(4,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(4,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(4,1,3,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(4,1,3,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(4,1,15,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(4,1,5,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(4,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(4,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(4,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(4,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(4,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(4,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(4,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(4,1,16,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(24,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(24,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(24,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(24,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(24,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(24,1,23,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(24,1,38,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(24,1,26,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(24,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(24,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(24,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(24,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(24,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(24,1,67,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(24,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(24,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(24,1,25,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(6,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(6,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(6,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(6,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(6,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(6,1,15,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(6,1,7,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(6,1,5,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(6,1,61,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(6,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(6,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(6,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(6,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(6,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(6,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(6,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(42,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(42,1,43,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(42,1,43,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(42,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(42,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(42,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(42,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(42,1,44,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(42,1,45,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(42,1,41,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(42,1,61,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(42,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(42,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(42,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(42,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(42,1,60,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(42,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(42,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(42,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(43,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(43,1,42,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(43,1,42,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(43,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(43,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(43,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(43,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(43,1,44,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(43,1,45,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(43,1,41,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(43,1,61,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(43,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(43,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(43,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(43,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(43,1,60,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(43,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(43,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(43,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(76,1,75,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(76,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(76,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(76,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(76,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(76,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(76,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(76,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(76,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(76,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(75,1,76,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(75,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(75,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(75,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(75,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(75,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(75,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(75,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(75,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(75,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(58,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(58,1,59,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(58,1,59,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(58,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(58,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(58,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(58,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(58,1,61,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(58,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(58,1,56,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(58,1,57,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(58,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(58,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(58,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(58,1,60,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(58,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(58,1,66,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(58,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(58,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(59,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(59,1,58,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(59,1,58,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(59,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(59,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(59,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(59,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(59,1,61,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(59,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(59,1,56,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(59,1,57,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(59,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(59,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(59,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(59,1,60,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(59,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(59,1,73,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(59,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(59,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(50,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(50,1,51,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(50,1,51,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(50,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(50,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(50,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(50,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(50,1,52,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(50,1,53,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(50,1,61,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(50,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(50,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(50,1,48,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(50,1,49,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(50,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(50,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(50,1,60,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(50,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(50,1,65,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(50,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(50,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(51,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(51,1,50,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(51,1,50,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(51,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(51,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(51,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(51,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(51,1,52,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(51,1,53,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(51,1,61,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(51,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(51,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(51,1,48,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(51,1,49,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(51,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(51,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(51,1,60,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(51,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(51,1,72,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(51,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(51,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(14,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(14,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(14,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(14,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(14,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(14,1,61,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(14,1,13,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(14,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(14,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(14,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(14,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(14,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(14,1,70,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(14,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(14,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(14,1,16,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(10,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(10,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(10,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(10,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(10,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(10,1,15,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(10,1,11,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(10,1,61,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(10,1,9,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(10,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(10,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(10,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(10,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(10,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(10,1,69,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(10,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(10,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(10,1,16,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(29,1,39,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(29,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(29,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(29,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(29,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(29,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(29,1,30,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(29,1,26,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(29,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(29,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(29,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(29,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(29,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(29,1,67,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(29,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(29,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(54,1,85,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(54,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(54,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(54,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(54,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(54,1,55,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(54,1,55,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(54,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(54,1,40,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(54,1,52,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(54,1,53,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(54,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(54,1,56,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(54,1,57,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(54,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(54,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(54,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(54,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(54,1,73,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(54,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(54,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(55,1,86,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(55,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(55,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(55,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(55,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(55,1,54,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(55,1,54,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(55,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(55,1,40,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(55,1,52,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(55,1,53,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(55,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(55,1,56,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(55,1,57,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(55,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(55,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(55,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(55,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(55,1,66,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(55,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(55,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(12,1,82,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(12,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(12,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(12,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(12,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(12,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(12,1,11,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(12,1,13,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(12,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(12,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(12,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(12,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(12,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(12,1,70,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(12,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(12,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(35,1,80,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(35,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(35,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(35,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(35,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(35,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(35,1,34,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(35,1,36,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(35,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(35,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(35,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(35,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(35,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(35,1,67,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(35,1,71,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(35,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(35,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(46,1,83,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(46,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(46,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(46,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(46,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(46,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(46,1,47,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(46,1,47,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(46,1,40,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(46,1,44,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(46,1,45,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(46,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(46,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(46,1,48,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(46,1,49,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(46,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(46,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(46,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(46,1,72,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(46,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(46,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(47,1,84,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(47,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(47,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(47,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(47,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(47,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(47,1,46,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(47,1,46,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(47,1,40,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(47,1,44,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(47,1,45,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(47,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(47,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(47,1,48,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(47,1,49,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(47,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(47,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(47,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(47,1,65,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(47,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(47,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(37,1,39,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(37,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(37,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(37,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(37,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(37,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(37,1,36,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(37,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(37,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(37,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(37,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(37,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(37,1,67,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(37,1,71,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(37,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(37,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(8,1,81,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(8,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(8,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(8,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(8,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(8,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(8,1,15,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(8,1,7,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(8,1,9,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(8,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(8,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(8,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(8,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(8,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(8,1,69,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(8,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(8,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(31,1,79,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(31,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(31,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(31,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(31,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(31,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(31,1,30,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(31,1,32,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(31,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(31,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(31,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(31,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(31,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(31,1,67,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(31,1,68,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(31,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(31,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(33,1,39,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(33,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(33,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(33,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(33,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(33,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(33,1,34,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(33,1,32,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(33,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(33,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(33,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(33,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(33,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(33,1,67,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(33,1,68,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(33,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(33,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(78,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(78,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(78,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(78,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(78,1,77,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(78,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(78,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(78,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(78,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(78,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(77,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(77,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(77,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(77,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(77,1,78,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(77,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(77,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(77,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(77,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(77,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(87,1,88,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(87,1,57,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(87,1,60,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(88,1,87,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(88,1,57,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(88,1,60,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(89,1,90,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(89,1,61,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(89,1,56,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(90,1,89,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(90,1,61,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(90,1,56,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(3,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(3,1,4,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(3,1,4,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(3,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(3,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(3,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(3,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(3,1,2,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(3,1,15,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(3,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(3,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(3,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(3,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(3,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(3,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(3,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(23,1,21,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(23,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(23,1,24,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(23,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(23,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(23,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(23,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(23,1,38,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(23,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(23,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(23,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(23,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(23,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(23,1,67,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(23,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(23,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(2,1,17,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(2,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(2,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(2,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(2,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(2,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(2,1,3,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(2,1,15,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(2,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(2,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(2,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(2,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(2,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(2,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(2,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(40,1,39,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(40,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(40,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(40,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(40,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(40,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(40,1,41,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(40,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(40,1,56,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(40,1,57,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(40,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(40,1,48,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(40,1,49,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(40,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(40,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(40,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(40,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(40,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(38,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(38,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(38,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(38,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(38,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(38,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(38,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(38,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(38,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(38,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(38,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(15,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(15,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(15,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(15,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(15,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(15,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(15,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(15,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(15,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(15,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(15,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(52,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(52,1,50,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(52,1,51,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(52,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(52,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(52,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(52,1,54,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(52,1,55,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(52,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(52,1,53,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(52,1,53,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(52,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(52,1,56,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(52,1,57,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(52,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(52,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(52,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(52,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(52,1,66,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(52,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(52,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(53,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(53,1,50,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(53,1,51,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(53,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(53,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(53,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(53,1,54,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(53,1,55,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(53,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(53,1,52,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(53,1,52,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(53,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(53,1,56,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(53,1,57,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(53,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(53,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(53,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(53,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(53,1,73,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(53,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(53,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(11,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(11,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(11,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(11,1,10,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(11,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(11,1,12,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(11,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(11,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(11,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(11,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(11,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(11,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(11,1,70,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(11,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(11,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(34,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(34,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(34,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(34,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(34,1,35,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(34,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(34,1,33,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(34,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(34,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(34,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(34,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(34,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(34,1,67,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(34,1,71,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(34,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(34,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(44,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(44,1,42,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(44,1,43,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(44,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(44,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(44,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(44,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(44,1,46,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(44,1,47,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(44,1,45,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(44,1,45,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(44,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(44,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(44,1,48,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(44,1,49,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(44,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(44,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(44,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(44,1,65,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(44,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(44,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(45,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(45,1,42,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(45,1,43,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(45,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(45,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(45,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(45,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(45,1,46,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(45,1,47,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(45,1,44,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(45,1,44,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(45,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(45,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(45,1,48,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(45,1,49,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(45,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(45,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(45,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(45,1,72,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(45,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(45,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(7,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(7,1,6,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(7,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(7,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(7,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(7,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(7,1,8,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(7,1,15,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(7,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(7,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(7,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(7,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(7,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(7,1,69,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(7,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(7,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(30,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(30,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(30,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(30,1,29,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(30,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(30,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(30,1,31,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(30,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(30,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(30,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(30,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(30,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(30,1,67,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(30,1,68,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(30,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(30,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(1,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(1,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(1,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(1,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(1,1,2,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(1,1,15,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(1,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(1,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(1,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(1,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(5,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(5,1,4,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(5,1,6,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(5,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(5,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(5,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(5,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(5,1,15,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(5,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(5,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(5,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(5,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(5,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(5,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(5,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(41,1,39,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(41,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(41,1,42,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(41,1,43,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(41,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(41,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(41,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(41,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(41,1,40,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(41,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(41,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(41,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(41,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(41,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(41,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(41,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(61,1,76,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(61,1,75,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(61,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(61,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(61,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(61,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(61,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(61,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(61,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(61,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(13,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(13,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(13,1,14,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(13,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(13,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(13,1,12,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(13,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(13,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(13,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(13,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(13,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(13,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(13,1,70,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(13,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(13,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(36,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(36,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(36,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(36,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(36,1,35,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(36,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(36,1,37,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(36,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(36,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(36,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(36,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(36,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(36,1,67,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(36,1,71,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(36,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(36,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(9,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(9,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(9,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(9,1,10,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(9,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(9,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(9,1,8,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(9,1,15,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(9,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(9,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(9,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(9,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(9,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(9,1,69,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(9,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(9,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(32,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(32,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(32,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(32,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(32,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(32,1,31,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(32,1,33,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(32,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(32,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(32,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(32,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(32,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(32,1,67,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(32,1,68,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(32,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(32,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(26,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(26,1,24,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(26,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(26,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(26,1,29,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(26,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(26,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(26,1,38,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(26,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(26,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(26,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(26,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(26,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(26,1,67,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(26,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(26,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(56,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(56,1,58,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(56,1,59,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(56,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(56,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(56,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(56,1,54,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(56,1,55,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(56,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(56,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(56,1,57,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(56,1,57,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(56,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(56,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(56,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(56,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(56,1,66,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(56,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(56,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(57,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(57,1,58,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(57,1,59,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(57,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(57,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(57,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(57,1,54,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(57,1,55,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(57,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(57,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(57,1,56,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(57,1,56,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(57,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(57,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(57,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(57,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(57,1,73,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(57,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(57,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(48,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(48,1,50,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(48,1,51,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(48,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(48,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(48,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(48,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(48,1,46,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(48,1,47,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(48,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(48,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(48,1,49,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(48,1,49,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(48,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(48,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(48,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(48,1,65,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(48,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(48,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(49,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(49,1,50,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(49,1,51,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(49,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(49,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(49,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(49,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(49,1,46,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(49,1,47,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(49,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(49,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(49,1,48,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(49,1,48,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(49,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(49,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(49,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(49,1,72,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(49,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(49,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(60,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(60,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(60,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(60,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(60,1,78,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(60,1,77,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(60,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(60,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(60,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(60,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(63,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(63,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(63,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(63,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(63,1,87,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(63,1,88,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(63,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(63,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(63,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(63,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(67,1,39,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(67,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(67,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(67,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(67,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(67,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(67,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(67,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(67,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(66,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(66,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(66,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(66,1,55,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(66,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(66,1,53,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(66,1,61,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(66,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(66,1,56,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(66,1,57,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(66,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(66,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(66,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(73,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(73,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(73,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(73,1,54,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(73,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(73,1,52,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(73,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(73,1,56,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(73,1,57,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(73,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(73,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(73,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(73,1,60,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(70,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(70,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(70,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(70,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(70,1,61,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(70,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(70,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(70,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(70,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(71,1,39,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(71,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(71,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(71,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(71,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(71,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(71,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(71,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(71,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(65,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(65,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(65,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(65,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(65,1,47,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(65,1,45,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(65,1,61,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(65,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(65,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(65,1,49,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(65,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(65,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(72,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(72,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(72,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(72,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(72,1,46,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(72,1,44,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(72,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(72,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(72,1,48,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(72,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(72,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(72,1,60,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(69,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(69,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(69,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(69,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(69,1,61,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(69,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(69,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(69,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(69,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(68,1,39,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(68,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(68,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(68,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(68,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(68,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(68,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(68,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(68,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(64,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(64,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(64,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(64,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(64,1,89,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(64,1,90,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(64,1,61,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(64,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(64,1,56,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(64,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(64,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(64,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(62,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(62,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(62,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(62,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(62,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(62,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(62,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(62,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(62,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(62,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(62,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(16,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(16,1,19,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(16,1,4,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(16,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(16,1,14,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(16,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(16,1,10,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(16,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(16,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(16,1,15,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(16,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(16,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(16,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(16,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(16,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(16,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(16,1,62,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(25,1,74,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(25,1,27,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(25,1,24,'a');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(25,1,100,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(25,1,99,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(25,1,96,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(25,1,95,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(25,1,38,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(25,1,94,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(25,1,93,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(25,1,98,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(25,1,97,'n');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(25,1,63,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(25,1,67,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(25,1,64,'s');
INSERT INTO opoflujoactividad(idactividad,idlogtrans,actividadcontigua,tipo)VALUES(25,1,62,'n');

/**
 * autor: Luis Angel Quispe Limachi
 * version 1.0, 06/10/2016
 * insertar la tabla opoactividad
 */
INSERT INTO opoactividadplazo(idactividad,idlogtrans,idactividaddesc,idactividadplazodesc,plazo,sumarplazoanterior)VALUES(11,1,'PRJR','RARJR',90,0);
INSERT INTO opoactividadplazo(idactividad,idlogtrans,idactividaddesc,idactividadplazodesc,plazo,sumarplazoanterior)VALUES(34,1,'PRJRC','RARJRC',90,0);
INSERT INTO opoactividadplazo(idactividad,idlogtrans,idactividaddesc,idactividadplazodesc,plazo,sumarplazoanterior)VALUES(44,1,'PRRDO','RRDO',20,0);
INSERT INTO opoactividadplazo(idactividad,idlogtrans,idactividaddesc,idactividadplazodesc,plazo,sumarplazoanterior)VALUES(45,1,'PRRDT','RRDT',20,0);
INSERT INTO opoactividadplazo(idactividad,idlogtrans,idactividaddesc,idactividadplazodesc,plazo,sumarplazoanterior)VALUES(7,1,'PRRR','RARRR',20,0);
INSERT INTO opoactividadplazo(idactividad,idlogtrans,idactividaddesc,idactividadplazodesc,plazo,sumarplazoanterior)VALUES(30,1,'PRRRC','RARRRC',20,0);
INSERT INTO opoactividadplazo(idactividad,idlogtrans,idactividaddesc,idactividadplazodesc,plazo,sumarplazoanterior)VALUES(1,1,'PUB','P',30,0);
INSERT INTO opoactividadplazo(idactividad,idlogtrans,idactividaddesc,idactividadplazodesc,plazo,sumarplazoanterior)VALUES(94,1,'RJ','RRJ',90,0);
INSERT INTO opoactividadplazo(idactividad,idlogtrans,idactividaddesc,idactividadplazodesc,plazo,sumarplazoanterior)VALUES(93,1,'RR','RRR',20,0);





/**
 * autor: Luis Angel Quispe Limachi
 * version 1.0, 03/10/2016
 * insertar registros en el dominio tiposoliapo
 */
INSERT INTO dominio( idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado)VALUES ( 1, 'tiposoliapo', 'DMDO', NULL, 'Demandado', NULL, NULL, current_date, NULL, 'AC');
INSERT INTO dominio( idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado)VALUES ( 1, 'tiposoliapo', 'DMTE', NULL, 'Demandante', NULL, NULL, current_date, NULL, 'AC');

/**
 * autor: Luis Angel Quispe Limachi
 * version 1.0, 02/12/2016
 * insertar registros en el dominio para el recurso y resolucion
 */
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipoSignoSeguimiento', 'CO', NULL, 'Conceder', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipoSignoSeguimiento', 'DE', NULL, 'Denegar', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipoSignoSeguimiento', 'DO', NULL, 'Denegar de oficio', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipoSignoSeguimiento', 'DS', NULL, 'Desistido', 'NULL', NULL, current_date, current_date, 'AC');



INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipoOposicionSeguimiento', 'PD', NULL, 'Probada', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipoOposicionSeguimiento', 'ID', NULL, 'Improbada', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipoOposicionSeguimiento', 'IT', NULL, 'Improcedente', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipoOposicionSeguimiento', 'RD', NULL, 'Retirada', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (2, 'tipoOposicionSeguimiento', 'DD', NULL, 'Desestimada', 'NULL', NULL, current_date, current_date, 'AC');



INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipoCancelacionSeguimiento', 'PT', NULL, 'Probada total', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipoCancelacionSeguimiento', 'PP', NULL, 'Probada parcial', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipoCancelacionSeguimiento', 'IT', NULL, 'Improbada total', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipoCancelacionSeguimiento', 'IP', NULL, 'Improbada parcial', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipoCancelacionSeguimiento', 'IM', NULL, 'Improcedente', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipoCancelacionSeguimiento', 'DD', NULL, 'Desestimada', 'NULL', NULL, current_date, current_date, 'AC');


INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipoResuelveSeguimiento', 'CO', NULL, 'Conceder', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipoResuelveSeguimiento', 'DE', NULL, 'Denegar', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipoResuelveSeguimiento', 'DO', NULL, 'Denegar de oficio', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipoResuelveSeguimiento', 'DS', NULL, 'Desistido', 'NULL', NULL, current_date, current_date, 'AC');


/**
 * autor: Eddy Valero
 * version 1.0, 17/10/2016
 * insertar registros en el dominio estado_publicacion
 */
INSERT INTO dominio( idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'estado_publicacion', 'PPUB', NULL, 'EN REVISION', NULL, NULL, current_date, NULL, 'AC');
INSERT INTO dominio( idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'estado_publicacion', 'PUBL', NULL, 'PUBLICADO', NULL, NULL, current_date, NULL, 'AC');
INSERT INTO dominio( idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'estado_publicacion', 'ENVI', NULL, 'ENVIADO', NULL, NULL, current_date, NULL, 'AC');


/**
 * autor: Eddy Valero
 * version 1.0, 23/09/2016
 * insertar registros en la tabla regional
 */

INSERT INTO regional(idlogtrans, central, nombre, direccion, telefono, fax, tipo_ciudad_notificacion, estado) VALUES (1, TRUE, 'LA PAZ', 'Calle Potosí­ Esq. Colon No 1278 Edif. Atalaya - Piso 1', '2115700', NULL, 'LPZ', 'AC');
INSERT INTO regional(idlogtrans, central, nombre, direccion, telefono, fax, tipo_ciudad_notificacion, estado) VALUES (1, FALSE, 'EL ALTO', 'Av. 6 de Marzo No 80 entre calles 2 y 3 Galeria Armendia - Piso 2 Of. 205 Zona 12 de Octubre', '2141001', NULL, 'ALT', 'AC');
INSERT INTO regional(idlogtrans, central, nombre, direccion, telefono, fax, tipo_ciudad_notificacion, estado) VALUES (1, FALSE, 'COCHABAMBA', 'Calle Chuquisaca No 649 entre Antezana y Lanza - Piso 2 Zona Central - Noreste', '4141403', NULL, 'CBA', 'AC');
INSERT INTO regional(idlogtrans, central, nombre, direccion, telefono, fax, tipo_ciudad_notificacion, estado) VALUES (1, FALSE, 'SANTA CRUZ', 'Prolongación Quijarro, Esq. Uruguay No 29 Edif. Bicentenario - 1er Anillo', '3121752', NULL, 'SCZ', 'AC');
INSERT INTO regional(idlogtrans, central, nombre, direccion, telefono, fax, tipo_ciudad_notificacion, estado) VALUES (1, FALSE, 'TARIJA', 'Calle Ingavi No 156 entre Colón y Suipacha Edif. Coronado - Piso 2 Of. 202 Zona Central', '70451020', NULL, 'TJA', 'AC');
INSERT INTO regional(idlogtrans, central, nombre, direccion, telefono, fax, tipo_ciudad_notificacion, estado) VALUES (1, FALSE, 'CHUQUISACA', 'Calle Luis Paz Arce (ex Pilinco) No 182 esq. G. Mendizabal - Piso 1 Of. 1 Zona Central', '72873611', NULL, 'CHQ', 'AC');


/**
 * autor: Susana Escobar Paz
 * version 1.0, 26/09/2016
 * insertar registros en la tabla correlativo
 */
INSERT INTO correlativo(idlogtrans,  nombre_criterio, incremento,  ultimo_numero_asignado,  acronimo, separador, gestion, estado)  VALUES (1,  'resolucioncn', 1, 0, null, null, 2016, 'AC');
INSERT INTO correlativo(idlogtrans,  nombre_criterio, incremento,  ultimo_numero_asignado,  acronimo, separador, gestion, estado)  VALUES (1,  'resolucioncd', 1, 0, null, null, 2016, 'AC');
INSERT INTO correlativo(idlogtrans,  nombre_criterio, incremento,  ultimo_numero_asignado,  acronimo, separador, gestion, estado)  VALUES (1,  'resolucionst', 1, 0, null, null, 2016, 'AC');
INSERT INTO correlativo(idlogtrans,  nombre_criterio, incremento,  ultimo_numero_asignado,  acronimo, separador, gestion, estado)  VALUES (1,  'resolucionsf', 1, 0, null, null, 2016, 'AC');
INSERT INTO correlativo(idlogtrans,  nombre_criterio, incremento,  ultimo_numero_asignado,  acronimo, separador, gestion, estado)  VALUES (1,  'resolucionlu', 1, 0, null, null, 2016, 'AC');
INSERT INTO correlativo(idlogtrans,  nombre_criterio, incremento,  ultimo_numero_asignado,  acronimo, separador, gestion, estado)  VALUES (1,  'numero_publicacion', 1, 0, null, null, 2016, 'AC');
INSERT INTO correlativo(idlogtrans,  nombre_criterio, incremento,  ultimo_numero_asignado,  acronimo, separador, gestion, estado)  VALUES (1,  'numero_registro', 1, 0, 'C', null, 2016, 'AC');
INSERT INTO correlativo(idlogtrans,  nombre_criterio, incremento,  ultimo_numero_asignado,  acronimo, separador, gestion, estado)  VALUES (1,  'resolucion_registro', 1, 0, null, null, 2016, 'AC');
INSERT INTO correlativo(idlogtrans,  nombre_criterio, incremento,  ultimo_numero_asignado,  acronimo, separador, gestion, estado)  VALUES (1,  'numerorenovacion', 1, 15151,'A','/', 2016, 'AC');
INSERT INTO correlativo(idlogtrans,  nombre_criterio, incremento,  ultimo_numero_asignado,  acronimo, separador, gestion, estado)  VALUES (1,  'resolucionren', 1, 121212,'NULL','NULL', 2016, 'AC');

INSERT INTO correlativoregional(idregional, idcorrelativo, idlogtrans,  tipo_tramite, estado) VALUES (1, 1, 1, 'CANO', 'AC');
INSERT INTO correlativoregional(idregional, idcorrelativo, idlogtrans,  tipo_tramite, estado) VALUES (1, 2, 1, 'CADO', 'AC');
INSERT INTO correlativoregional(idregional, idcorrelativo, idlogtrans,  tipo_tramite, estado) VALUES (1, 3, 1, 'CATR', 'AC');
INSERT INTO correlativoregional(idregional, idcorrelativo, idlogtrans,  tipo_tramite, estado) VALUES (1, 4, 1, 'CAFU', 'AC');
INSERT INTO correlativoregional(idregional, idcorrelativo, idlogtrans,  tipo_tramite, estado) VALUES (1, 5, 1, 'CALU', 'AC');
INSERT INTO correlativoregional(idregional, idcorrelativo, idlogtrans,  tipo_tramite, estado) VALUES (1, 6, 1, 'NPUB', 'AC');
INSERT INTO correlativoregional(idregional, idcorrelativo, idlogtrans,  tipo_tramite, estado) VALUES (1, 7, 1, 'NREG', 'AC');
INSERT INTO correlativoregional(idregional, idcorrelativo, idlogtrans,  tipo_tramite, estado) VALUES (1, 8, 1, 'RREG', 'AC');
INSERT INTO correlativoregional(idregional, idcorrelativo, idlogtrans,  tipo_tramite, estado) VALUES (1, 9, 1, 'RENR', 'AC');
INSERT INTO correlativoregional(idregional, idcorrelativo, idlogtrans,  tipo_tramite, estado) VALUES (1, 10, 1, 'RERE', 'AC');

INSERT INTO etapa (tipo_tramite, tipo_etapa, descripcion, fecha_creacion, plazo, estado) VALUES ('',  'VENT', 'Ventanilla (Admisión de la solicitud)', current_timestamp, 1, 'AC');
INSERT INTO etapa (tipo_tramite, tipo_etapa, descripcion, fecha_creacion, plazo, estado) VALUES ('',  'RECA', 'Recaudación', current_timestamp, 1, 'AC');
INSERT INTO etapa (tipo_tramite, tipo_etapa, descripcion, fecha_creacion, plazo, estado) VALUES ('REGS',  'EXFM', 'Examen de Forma', current_timestamp, 14, 'AC');
INSERT INTO etapa (tipo_tramite, tipo_etapa, descripcion, fecha_creacion, plazo, estado) VALUES ('REGS',  'PPPR', 'Preparación para prepublicación', current_timestamp, 45, 'AC');
INSERT INTO etapa (tipo_tramite, tipo_etapa, descripcion, fecha_creacion, plazo, estado) VALUES ('REGS',  'PPP', 'Preparación para publicación', current_timestamp, 30, 'AC');
INSERT INTO etapa (tipo_tramite, tipo_etapa, descripcion, fecha_creacion, plazo, estado) VALUES ('REGS',  'OPO', 'Oposición', current_timestamp, 30, 'AC');
INSERT INTO etapa (tipo_tramite, tipo_etapa, descripcion, fecha_creacion, plazo, estado) VALUES ('REGS',  'ANR', 'Análisis de registrabilidad', current_timestamp, 100, 'AC');
INSERT INTO etapa (tipo_tramite, tipo_etapa, descripcion, fecha_creacion, plazo, estado) VALUES ('REGS',  'ERA', 'Emisión de resolución administrativa', current_timestamp, 10, 'AC');
INSERT INTO etapa (tipo_tramite, tipo_etapa, descripcion, fecha_creacion, plazo, estado) VALUES ('',  'NOT', 'Notificación', current_timestamp, 5, 'AC');
INSERT INTO etapa (tipo_tramite, tipo_etapa, descripcion, fecha_creacion, plazo, estado) VALUES ('',  'DIG', 'Digitalización', current_timestamp, 0, 'AC');
INSERT INTO etapa (tipo_tramite, tipo_etapa, descripcion, fecha_creacion, plazo, estado) VALUES ('MODI',  'EXMO', 'Examen de Forma Modificación', current_timestamp, 0, 'AC');
INSERT INTO etapa (tipo_tramite, tipo_etapa, descripcion, fecha_creacion, plazo, estado) VALUES ('RENO',  'EXRE', 'Examen de Renovacion', current_timestamp, 0, 'AC');
INSERT INTO etapa (tipo_tramite, tipo_etapa, descripcion, fecha_creacion, plazo, estado) VALUES ('',  'CONS', 'Consultas', current_timestamp, 0, 'AC');
INSERT INTO etapa (tipo_tramite, tipo_etapa, descripcion, fecha_creacion, plazo, estado) VALUES ('',  'INFR', 'Infracciones', current_timestamp, 0, 'AC');
INSERT INTO etapa (tipo_tramite, tipo_etapa, descripcion, fecha_creacion, plazo, estado) VALUES ('',  'REJE', 'Recursos Jerarquicos', current_timestamp, 0, 'AC');
INSERT INTO etapa (tipo_tramite, tipo_etapa, descripcion, fecha_creacion, plazo, estado) VALUES ('',  'RERE', 'Recursos Revocatorio', current_timestamp, 20, 'AC');

/**
Creación Dominio tipo_tramite_poder
Creado: Levi Laura Fecha: 03/11/2016
*/

INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_poder', 'CD', NULL, 'CD', 'Cambio Direccion', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_poder', 'CD-C', NULL, 'CD-C', 'Cambio Direccion Cochabamba', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_poder', 'CD-S', NULL, 'CD-S', 'Cambio Direccion Santa cruz', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_poder', 'CD-E', NULL, 'CD-E', 'Cambio Direccion El Alto', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_poder', 'CN', NULL, 'CN', 'Cambio Nombre', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_poder', 'CN-C', NULL, 'CN-C', 'Cambio Nombre Cochabamba', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_poder', 'CN-S', NULL, 'CN-S', 'Cambio Nombre Santa Cruz', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_poder', 'CN-E', NULL, 'CN-E', 'Cambio Nombre El Alto', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_poder', 'DA', NULL, 'DA', 'Derechos de Autor', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_poder', 'DA-C', NULL, 'DA-C', 'Derechos de Autor Cochabamba', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_poder', 'DA-S', NULL, 'DA-S', 'Derechos de Autor Santa Cruz', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_poder', 'LU', NULL, 'LU', 'Licencia de Uso', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_poder', 'LU-C', NULL, 'LU-C', 'Licencia de Uso Cochabamba', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_poder', 'LU-S', NULL, 'LU-S', 'Licencia de Uso Santa Cruz', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_poder', 'LU-E', NULL, 'LU-E', 'Licencia de Uso El Alto', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_poder', 'SF', NULL, 'SF', 'Fusion', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_poder', 'SF-C', NULL, 'SF-C', 'Fusion Cochabamba', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_poder', 'SF-S', NULL, 'SF-S', 'Fusion Santa Cruz', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_poder', 'SF-E', NULL, 'SF-E', 'Fusion El Alto', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_poder', 'SM', NULL, 'SM', 'Signo', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_poder', 'SM-C', NULL, 'SM-C', 'Signo Cochabamba', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_poder', 'SM-S', NULL, 'SM-S', 'Signo Santa Cruz', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_poder', 'SP', NULL, 'SP', 'Patente', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_poder', 'SP-C', NULL, 'SP-C', 'Patente Cochabamba', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_poder', 'SP-S', NULL, 'SP-S', 'Patente Santa Cruz', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_poder', 'SR', NULL, 'SR', 'Renovacion', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_poder', 'SR-C', NULL, 'SR-C', 'Renovacion Cochabamba', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_poder', 'SR-S', NULL, 'SR-S', 'Renovacion Santa Cruz', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_poder', 'ST', NULL, 'ST', 'Transferencia', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_poder', 'ST-C', NULL, 'ST-C', 'Transferencia Cochabamba', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_tramite_poder', 'ST-S', NULL, 'ST-S', 'Transferencia Santa Cruz', NULL, current_date, current_date, 'AC');

/**
Creación Dominio tipo_poder
Creado: Levi Laura Fecha: 03/11/2016
*/
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_poder', 'PO', NULL, 'Poder', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_poder', 'TENO', NULL, 'Testimonio Cambio Nombre', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_poder', 'TEDO', NULL, 'Testimonio Cambio Domicilio', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_poder', 'TETR', NULL, 'Testimonio Transferencia', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_poder', 'TEFU', NULL, 'Testimonio Fusion', 'NULL', NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'tipo_poder', 'TELU', NULL, 'Testimonio Licencia Uso', 'NULL', NULL, current_date, current_date, 'AC');

/**
Creación Dominio tipo_poder
Creado: Levi Laura Fecha: 9/11/2016CD-N
*/
/**********************************************Para la barra de menus************************************************/   
 insert into sistema (descripcion,estado)      values ('Signos','AC');

             
INSERT INTO menu(idsistema,descripcion, estado,orden) VALUES (1,'Ventanilla', 'AC',1);
INSERT INTO menu(idsistema,descripcion, estado,orden) VALUES (1,'Solicitudes', 'AC',2);      
INSERT INTO menu(idsistema,descripcion, estado,orden) VALUES (1,'Publicaciones', 'AC',3);
INSERT INTO menu(idsistema,descripcion, estado,orden) VALUES (1,'Oposiciones', 'AC',4);        
INSERT INTO menu(idsistema,descripcion, estado,orden) VALUES (1,'Registros', 'AC',5);
INSERT INTO menu(idsistema,descripcion, estado,orden) VALUES (1,'Modificaciones', 'AC',6);
INSERT INTO menu(idsistema,descripcion, estado,orden) VALUES (1,'Renovaciones', 'AC',7);
INSERT INTO menu(idsistema,descripcion, estado,orden) VALUES (1,'Seguimiento', 'AC',8);
INSERT INTO menu(idsistema,descripcion, estado,orden) VALUES (1,'Notificacion', 'AC',9);    
INSERT INTO menu(idsistema,descripcion, estado,orden) VALUES (1,'Resoluciones', 'AC',10);    
INSERT INTO menu(idsistema,descripcion, estado,orden) VALUES (1,'Digitalización', 'AC',11);  

insert into pagina (descripcion,url,estado) values ('Entrega','/enconstruccion/construccion.xhtml','NA');
insert into pagina (descripcion,url,estado) values ('Recepcion Documentos','/ventanilla/ingresoSolicitudSubsanacion.xhtml','NA');
insert into pagina (descripcion,url,estado) values ('Tramites Nuevos','/ventanilla/ingresoSolicitud.xhtml','AC');

insert into pagina (descripcion,url,estado) values ('Expediente Signo Distintivo','/signo/examenSignos.xhtml','AC');
insert into pagina (descripcion,url,estado) values ('Resoluciones Modificatorias','/enconstruccion/construccion.xhtml','NA');
insert into pagina (descripcion,url,estado) values ('Imprimir Expediente','/signo/imprimirExpediente.xhtml','AC');
insert into pagina (descripcion,url,estado) values ('Eliminar Solicitud','/ventanilla/eliminarSolicitud.xhtml','AC');
insert into pagina (descripcion,url,estado) values ('Busqueda de Parecidos','/busquedas/busquedaSignos.xhtml','AC');
insert into pagina (descripcion,url,estado) values ('Busqueda Figurativa','/enconstruccion/construccion.xhtml','AC');
insert into pagina (descripcion,url,estado) values ('Libro de Poderes','/libropoder/libroDePoderes.xhtml','AC');
insert into pagina (descripcion,url,estado) values ('Generador de Stickers','/stickers/sticker.xhtml','AC');



insert into pagina (descripcion,url,estado) values ('Generacion de Publicacion','/publicaciones/adminPublicacion.xhtml','AC');
insert into pagina (descripcion,url,estado) values ('Busqueda Publicacion','/publicaciones/buscadorPublicacion.xhtml','AC');

insert into pagina (descripcion,url,estado) values ('Expediente Oposicion','/oposicion/examenoposicion.xhtml','AC');
insert into pagina (descripcion,url,estado) values ('Busqueda Oposicion','/oposicion/busquedaOpo.xhtml','AC');
insert into pagina (descripcion,url,estado) values ('Resoluciones Modificatorias','/enconstruccion/construccion.xhtmll','AC');

insert into pagina (descripcion,url,estado) values ('Generar Registro','/registros/registroNuevoAntiguo.xhtml','AC');
insert into pagina (descripcion,url,estado) values ('Lista y Busquedas de Registros Emitidos','/registros/buscadorRegistro.xhtml','AC');
insert into pagina (descripcion,url,estado) values ('Catalogo de Marcas Registradas','/registros/catalogoMarcasRegistradas.xhtml','AC');

insert into pagina (descripcion,url,estado) values ('Expediente Modificacion','/modificaciones/examenModificacion.xhtml','AC');
insert into pagina (descripcion,url,estado) values ('Busqueda Modificacion','/modificaciones/buscadorModificacion.xhtml','AC');
insert into pagina (descripcion,url,estado) values ('Resoluciones Modificatorias','/enconstruccion/construccion.xhtml','NA');

insert into pagina (descripcion,url,estado) values ('Expediente Renovacion','/renovacion/examenRenovacion.xhtml','AC');
insert into pagina (descripcion,url,estado) values ('Renovaciones Registradas','/renovacion/busquedaRenovacion.xhtml','AC');
insert into pagina (descripcion,url,estado) values ('Busqueda Renovacion','/renovacion/listaRenovacion.xhtml','AC');
insert into pagina (descripcion,url,estado) values ('Resoluciones Modificatorias ','/renovacion/examenRenovacion.xhtml?req=MODRENO','AC');


insert into pagina (descripcion,url,estado) values ('Control de Calidad','/seguimiento/controlPlazos.xhtml','AC');
insert into pagina (descripcion,url,estado) values ('Historial Tramite','/historial/historialTramite.xhtml','AC');

insert into pagina (descripcion,url,estado) values ('Peticiones','/notificacion/notiPeticionDialog.xhtml','AC');
insert into pagina (descripcion,url,estado) values ('Busquedas','/notificacion/notiBusquedaExpediente.xhtml','AC');
insert into pagina (descripcion,url,estado) values ('Imprimir Peticiones','/enconstruccion/construccion.xhtml','AN');

insert into pagina (descripcion,url,estado) values ('Editor de Resoluciones','/enconstruccion/construccion.xhtml','AC');

insert into pagina (descripcion,url,estado) values ('Digitalizar','/digitalizacion/digitalizacion.xhtml','AC');




insert into menupagina (idmenu,idpagina,estado,orden) values (1,1,'AC',1);
insert into menupagina (idmenu,idpagina,estado,orden) values (1,2,'AC',2);
insert into menupagina (idmenu,idpagina,estado,orden) values (1,3,'AC',3); 

                        
insert into menupagina (idmenu,idpagina,estado,orden) values (2,4,'AC',1);
insert into menupagina (idmenu,idpagina,estado,orden) values (2,5,'AC',2);
insert into menupagina (idmenu,idpagina,estado,orden) values (2,6,'AC',3);
insert into menupagina (idmenu,idpagina,estado,orden) values (2,7,'AC',4);            
insert into menupagina (idmenu,idpagina,estado,orden) values (2,8,'AC',5);            
insert into menupagina (idmenu,idpagina,estado,orden) values (2,9,'AC',6);            
insert into menupagina (idmenu,idpagina,estado,orden) values (2,10,'AC',7);                                    
insert into menupagina (idmenu,idpagina,estado,orden) values (2,11,'AC',8);     
            

insert into menupagina (idmenu,idpagina,estado,orden) values (3,12,'AC',1);
insert into menupagina (idmenu,idpagina,estado,orden) values (3,13,'AC',2);
                                                     

insert into menupagina (idmenu,idpagina,estado,orden) values (4,14,'AC',1);
insert into menupagina (idmenu,idpagina,estado,orden) values (4,15,'AC',2);
insert into menupagina (idmenu,idpagina,estado,orden) values (4,16,'AC',3);                                    



insert into menupagina (idmenu,idpagina,estado,orden) values (5,17,'AC',1);
insert into menupagina (idmenu,idpagina,estado,orden) values (5,18,'AC',2);
insert into menupagina (idmenu,idpagina,estado,orden) values (5,19,'AC',3); 


insert into menupagina (idmenu,idpagina,estado,orden) values (6,20,'AC',1);   
insert into menupagina (idmenu,idpagina,estado,orden) values (6,21,'AC',2);   
insert into menupagina (idmenu,idpagina,estado,orden) values (6,22,'AC',3);   

insert into menupagina (idmenu,idpagina,estado,orden) values (7,23,'AC',1);
insert into menupagina (idmenu,idpagina,estado,orden) values (7,24,'AC',2);
insert into menupagina (idmenu,idpagina,estado,orden) values (7,25,'AC',3);
insert into menupagina (idmenu,idpagina,estado,orden) values (7,26,'AC',4);                                    

insert into menupagina (idmenu,idpagina,estado,orden) values (8,27,'AC',1);                                                                                                   
insert into menupagina (idmenu,idpagina,estado,orden) values (8,28,'AC',2); 

insert into menupagina (idmenu,idpagina,estado,orden) values (9,29,'AC',1);
insert into menupagina (idmenu,idpagina,estado,orden) values (9,30,'AC',2);
insert into menupagina (idmenu,idpagina,estado,orden) values (9,31,'AC',2);   

insert into menupagina (idmenu,idpagina,estado,orden) values (10,32,'AC',1);  

insert into menupagina (idmenu,idpagina,estado,orden) values (11,33,'AC',1);  
/******************Pa Recaudaciones*******************/
insert into sistema (descripcion,estado) values ('Recaudaciones','AC');


INSERT INTO menu(idsistema,descripcion, estado,orden) VALUES (2,'Recibos', 'AC',1);
INSERT INTO menu(idsistema,descripcion, estado,orden) VALUES (2,'Reportes', 'AC',2);  

insert into pagina (descripcion,url,estado) values ('Nuevo Recibo','/recaudaciones/recibo.xhtml','AC');
insert into pagina (descripcion,url,estado) values ('Buscar Recibo','/recaudaciones/busquedaRecibo.xhtml','AC');
insert into pagina (descripcion,url,estado) values ('Eliminar Recibo','/recaudaciones/eliminaRecibo.xhtml','AC');
insert into pagina (descripcion,url,estado) values ('Dosificacion de Recibos','/recaudaciones/dosificacionRecibos.xhtml','AC');
insert into pagina (descripcion,url,estado) values ('Dosificacion de Titulos','/recaudaciones/dosificacionTitulos.xhtml','AC');
insert into pagina (descripcion,url,estado) values ('Tasas de Servicios','/recaudaciones/tasas.xhtml','AC');

insert into pagina (descripcion,url,estado) values ('Detalle Diario de Recibos','/recaudacionesReportes/reportediarioRecibos.xhtml','AC');
insert into pagina (descripcion,url,estado) values ('Resumen diarios de Depositos','/recaudacionesReportes/reporteDiaDepo.xhtml','AC');
insert into pagina (descripcion,url,estado) values ('Resumen por Concepto','/recaudacionesReportes/reportePorConcepto.xhtml','AC');
insert into pagina (descripcion,url,estado) values ('Resumen por Concepto Mensual','/recaudacionesReportes/reportePorConceMesl.xhtml','AC');
insert into pagina (descripcion,url,estado) values ('Resumen de Tasa por Mes','/recaudacionesReportes/reporteResTasasMes.xhtml','AC');
insert into pagina (descripcion,url,estado) values ('Resumen Tasas Hasta el Momento','/recaudacionesReportes/reporteTasasMomento.xhtml','AC');   

insert into menupagina (idmenu,idpagina,estado,orden) values (12,34,'AC',1);
insert into menupagina (idmenu,idpagina,estado,orden) values (12,35,'AC',2);
insert into menupagina (idmenu,idpagina,estado,orden) values (12,36,'AC',3);
insert into menupagina (idmenu,idpagina,estado,orden) values (12,37,'AC',4);
insert into menupagina (idmenu,idpagina,estado,orden) values (12,38,'AC',5);
insert into menupagina (idmenu,idpagina,estado,orden) values (12,39,'AC',6);   

insert into menupagina (idmenu,idpagina,estado,orden) values (13,40,'AC',1);   
insert into menupagina (idmenu,idpagina,estado,orden) values (13,41,'AC',2);   
insert into menupagina (idmenu,idpagina,estado,orden) values (13,42,'AC',3);   
insert into menupagina (idmenu,idpagina,estado,orden) values (13,43,'AC',4);   
insert into menupagina (idmenu,idpagina,estado,orden) values (13,44,'AC',5);   
insert into menupagina (idmenu,idpagina,estado,orden) values (13,45,'AC',6);  
/**************************Pa notificaciones***********************/
insert into sistema (descripcion,estado) values ('Notificaciones','AC');


INSERT INTO menu(idsistema,descripcion, estado,orden) VALUES (3,'Solicitudes', 'AC',1);
INSERT INTO menu(idsistema,descripcion, estado,orden) VALUES (3,'Reportes', 'AC',2);    

insert into pagina (descripcion,url,estado) values ('Notificaciones','/notificacion/notiNotifica.xhtml','AC');
insert into pagina (descripcion,url,estado) values ('Peticiones','/notificacion/notiPeticionDialog.xhtml','AC');
insert into pagina (descripcion,url,estado) values ('Entrada de Notificaciones','/notificacion/notiVerifica.xhtml','AC');
insert into pagina (descripcion,url,estado) values ('Busquedas','/notificacion/notiBusquedaExpediente.xhtml','AC');
insert into pagina (descripcion,url,estado) values ('Historial','/notificacion/notiExpediBusqueda.xhtml','AC');

insert into pagina (descripcion,url,estado) values ('Imprimir Derivaciones','/enconstruccion/construccion.xhtml','AN');
insert into pagina (descripcion,url,estado) values ('Imprimir Notificaciones','/enconstruccion/construccion.xhtml','AN');
insert into pagina (descripcion,url,estado) values ('Imprimir Peticiones','/enconstruccion/construccion.xhtml','AN');



insert into menupagina (idmenu,idpagina,estado,orden) values (14,46,'AC',1);      
insert into menupagina (idmenu,idpagina,estado,orden) values (14,47,'AC',2);  
insert into menupagina (idmenu,idpagina,estado,orden) values (14,48,'AC',3);  
insert into menupagina (idmenu,idpagina,estado,orden) values (14,49,'AC',4);  
insert into menupagina (idmenu,idpagina,estado,orden) values (14,50,'AC',5);      

insert into menupagina (idmenu,idpagina,estado,orden) values (15,51,'AC',1);   
insert into menupagina (idmenu,idpagina,estado,orden) values (15,52,'AC',2); 
insert into menupagina (idmenu,idpagina,estado,orden) values (15,53,'AC',3);
/*****************************Pa digitalizacion*****************************/
insert into sistema (descripcion,estado) values ('Digitalizacion','AC');

INSERT INTO menu(idsistema,descripcion, estado,orden) VALUES (4,'Digitalización', 'AC',1);

insert into pagina (descripcion,url,estado) values ('Digitalizar','/digitalizacion/digitalizacion.xhtml','AC');


insert into menupagina (idmenu,idpagina,estado,orden) values (16,54,'AC',1);      



/**************PAra poder diferenciar checkbox a la lhora de consultar los adiciona y modifica******************************/
insert into pagina (descripcion,url,estado) values ('Examen Signo Adiciona','','AC');
insert into pagina (descripcion,url,estado) values ('Examen Signo Modifica','','AC');

/**
Creación Registros en tabla seccionsubpublicacion para modulo e Publicaciones
Creado: Susana Escobar Paz  Fecha: 12/01/2017
*/
INSERT INTO seccionsubpublicacion( iddominio, idlogtrans, seccion, subseccion, descripcion, estado) VALUES ( 330, 1, 1,null, 'SOLICITADAS', 'AC');
INSERT INTO seccionsubpublicacion( iddominio, idlogtrans, seccion, subseccion, descripcion, estado) VALUES ( 331, 1, 1,null, 'SOLICITADAS', 'AC');
INSERT INTO seccionsubpublicacion( iddominio, idlogtrans, seccion, subseccion, descripcion, estado) VALUES ( 333, 1, 2,null, 'SUBSANADAS', 'AC');
INSERT INTO seccionsubpublicacion( iddominio, idlogtrans, seccion, subseccion, descripcion, estado) VALUES ( 326, 1, 3,null, 'OMITIDAS', 'AC');
INSERT INTO seccionsubpublicacion( iddominio, idlogtrans, seccion, subseccion, descripcion, estado) VALUES ( 328, 1, 4,null, 'FE DE ERRATAS', 'AC');
INSERT INTO seccionsubpublicacion( iddominio, idlogtrans, seccion, subseccion, descripcion, estado) VALUES ( 334, 1, 4,null, 'FE DE ERRATAS', 'AC');
INSERT INTO seccionsubpublicacion( iddominio, idlogtrans, seccion, subseccion, descripcion, estado) VALUES ( 276, 1, null,2, 'AUDITIVAS', 'AC');
INSERT INTO seccionsubpublicacion( iddominio, idlogtrans, seccion, subseccion, descripcion, estado) VALUES ( 273, 1, null,1, 'DENOMINATIVAS', 'AC');
INSERT INTO seccionsubpublicacion( iddominio, idlogtrans, seccion, subseccion, descripcion, estado) VALUES ( 277, 1, null,2, 'FIGURATIVAS', 'AC');
INSERT INTO seccionsubpublicacion( iddominio, idlogtrans, seccion, subseccion, descripcion, estado) VALUES ( 274, 1, null,2, 'FIGURATIVAS', 'AC');
INSERT INTO seccionsubpublicacion( iddominio, idlogtrans, seccion, subseccion, descripcion, estado) VALUES ( 278, 1, null,2, 'FIGURATIVAS', 'AC');
INSERT INTO seccionsubpublicacion( iddominio, idlogtrans, seccion, subseccion, descripcion, estado) VALUES ( 282, 1, null,2, 'FIGURATIVAS', 'AC');
INSERT INTO seccionsubpublicacion( iddominio, idlogtrans, seccion, subseccion, descripcion, estado) VALUES ( 279, 1, null,3, 'OTROS', 'AC');
INSERT INTO seccionsubpublicacion( iddominio, idlogtrans, seccion, subseccion, descripcion, estado) VALUES ( 280, 1, null,1, 'AUDITIVAS', 'AC');
INSERT INTO seccionsubpublicacion( iddominio, idlogtrans, seccion, subseccion, descripcion, estado) VALUES ( 275, 1, null,2, 'FIGURATIVAS', 'AC');
INSERT INTO seccionsubpublicacion( iddominio, idlogtrans, seccion, subseccion, descripcion, estado) VALUES ( 281, 1, null,2, 'FIGURATIVAS', 'AC');


/**
Creación Registros dominio sucursal bancaria
Creado: Chano Rojas  Fecha: 12/01/2017
*/


INSERT INTO dominio( idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'sucursal_bancaria_recibos', '2', NULL, 'LA PAZ', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio( idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'sucursal_bancaria_recibos', '1', NULL, 'CHUQUISACA', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio( idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'sucursal_bancaria_recibos', '3', NULL, 'COCHABAMBA', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio( idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'sucursal_bancaria_recibos', '4', NULL, 'ORURO', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio( idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'sucursal_bancaria_recibos', '5', NULL, 'PANDO', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio( idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'sucursal_bancaria_recibos', '6', NULL, 'POTOSI', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio( idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'sucursal_bancaria_recibos', '8', NULL, 'TARIJA', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio( idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES ( 1, 'sucursal_bancaria_recibos', '7', NULL, 'SANTA CRUZ', NULL, NULL, current_date, current_date, 'AC');

/**
Creación de estado PARA  DESGLOSE 
Creado: Susana Escobar  Fecha: 01/08/2017
*/
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'estado_marca', 'PDE', NULL, 'PARA DESGLOSE', NULL, NULL, current_date, current_date, 'AC');

INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'ubicacion', 'PIDS', NULL, 'PROPIEDAD INDUSTRIAL (DESGLOSE)', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'ubicacion', 'VENE', NULL, 'VENTANILLA UNICA (ENTREGA DE DOCUMENTOS)', NULL, NULL, current_date, current_date, 'AC');

INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'ubicacion_modificacion', 'PIDM', NULL, 'PROPIEDAD INDUSTRIAL (DESGLOSE)', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'ubicacion_modificacion', 'VEEM', NULL, 'VENTANILLA UNICA (ENTREGA DE DOCUMENTOS)', NULL, NULL, current_date, current_date, 'AC');

INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'ubicacion_renovacion', 'PIDR', NULL, 'PROPIEDAD INDUSTRIAL (DESGLOSE)', NULL, NULL, current_date, current_date, 'AC');
INSERT INTO dominio (idlogtrans, dominio, codigo, dominiopadre, nombre, descripcion,orden, fechainicio, fechafin, estado) VALUES (1, 'ubicacion_renovacion', 'VEER', NULL, 'VENTANILLA UNICA (ENTREGA DE DOCUMENTOS)', NULL, NULL, current_date, current_date, 'AC');
