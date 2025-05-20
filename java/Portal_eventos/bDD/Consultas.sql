use proyectoIndra;

select * from usuarios;
select * from eventos;
select * from categorias;
select * from ubicaciones;
select * from organizadores;

select nombre, fecha, duracion, estado, id_categoria, id_organizador, id_ubicacion from eventos;

INSERT INTO organizadores (nombre, informacion_contacto) VALUES ('David', "David@gmail.com");

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


