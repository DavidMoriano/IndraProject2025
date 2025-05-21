use proyectoIndra;

select * from usuarios;
select * from eventos;
select * from categorias;		
select * from ubicaciones;
select * from organizadores;
select * from inscripciones;

alter table organizadores auto_increment = 2;
delete from organizadores where id_organizador = 2;

PREPARE stmt FROM 'SELECT nombre, fecha, duracion, estado, id_categoria, id_organizador, id_ubicacion FROM eventos WHERE CASE WHEN ? = 0 then true else id_categoria = ? end';
SET @param1 = 0;
	EXECUTE stmt USING @param1, @param1;
DEALLOCATE PREPARE stmt;

INSERT INTO organizadores (nombre, informacion_contacto) VALUES ('David', "David@gmail.com");

Alter table inscripciones auto_increment = 1;
INSERT INTO inscripciones (id_evento, id_usuario, fecha_inscripcion) values (2, 1, concat("2025-10-10", " ", curtime()));
delete from inscripciones where id_inscripcion = 4;

select usuarios.nombre, eventos.nombre from usuarios 
	inner join inscripciones on usuarios.id_usuario = inscripciones.id_usuario
    inner join eventos on inscripciones.id_evento = eventos.id_evento;

SELECT id_usuario, nombre, email, '' as password FROM usuarios;

SELECT nombre FROM usuario;

INSERT INTO ubicaciones (tipo, direccion) VALUES ('Presencial', 'Av. de la Sostenibilidad 123, Madrid'),
												 ('Presencial', 'Calle Verde 45, Sevilla'),
												 ('Online', NULL),
												 ('Presencial', 'Plaza del Medio Ambiente 8, Valencia'),
												 ('Online', NULL);
insert into categorias (nombre) values ('talleres');
insert into categorias (nombre) values ('conferencias');
insert into categorias (nombre) values ('exposiciones');

INSERT INTO eventos (nombre, fecha, duracion, estado, id_categoria, id_organizador, id_ubicacion) VALUES
('Taller de Huertos Urbanos', '2025-06-10', 2, 'activo', 1, 1, 1),
('Conferencia sobre Energías Renovables', '2025-06-15', 1, 'activo', 2, 1, 2),
('Exposición de Arte Reciclado', '2025-06-20', 3, 'cancelado', 3, 1, 3),
('Taller de Compostaje', '2025-06-25', 2, 'activo', 1, 1, 4),
('Conferencia Medioambiental', '2025-07-01', 1, 'cancelado', 2, 1, 5),
('Exposición Fotográfica de la Naturaleza', '2025-07-05', 1, 'activo', 3, 1, 1),
('Taller de Reciclaje Creativo', '2025-07-10', 2, 'activo', 1, 1, 2),
('Conferencia sobre Agricultura Sostenible', '2025-07-15', 1, 'cancelado', 2, 1, 3),
('Exposición de Productos Locales', '2025-07-20', 2, 'activo', 3, 1, 4),
('Taller de Construcción con Barro', '2025-07-25', 3, 'activo', 1, 1, 5);

select id_inscripcion, eventos.nombre, fecha_inscripcion from inscripciones inner join eventos on inscripciones.id_evento = eventos.id_evento where inscripciones.id_usuario = 1;

SELECT id_inscripcion, eventos.id_evento, fecha_inscripcion from inscripciones inner join eventos on inscripciones.id_evento = eventos.id_evento where inscripciones.id_usuario = 1;
