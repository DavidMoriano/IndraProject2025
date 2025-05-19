create database if not exists ProyectoIndra;
use ProyectoIndra;


CREATE TABLE usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    `password` VARCHAR(255) NOT NULL
);

CREATE TABLE ubicaciones (
    id_ubicacion INT AUTO_INCREMENT PRIMARY KEY,
    tipo ENUM('online', 'presencial') NOT NULL,
    direccion VARCHAR(255) -- solo se usa si es presencial
);


CREATE TABLE organizadores (
    id_organizador INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    informacion_contacto VARCHAR(255) NOT NULL
);

CREATE TABLE categorias (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE eventos (
    id_evento INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    fecha DATE NOT NULL,
    duracion INT NOT NULL, -- duración en minutos
    estado ENUM('activo', 'cancelado') DEFAULT 'activo',
    id_categoria INT,
    id_organizador INT,
    id_ubicacion INT,
    FOREIGN KEY (id_categoria) REFERENCES categorias(id_categoria),
    FOREIGN KEY (id_organizador) REFERENCES organizadores(id_organizador),
    FOREIGN KEY (id_ubicacion) REFERENCES ubicaciones(id_ubicacion)
);


CREATE TABLE inscripciones (
    id_inscripcion INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_evento INT NOT NULL,
    fecha_inscripcion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario),
    FOREIGN KEY (id_evento) REFERENCES eventos(id_evento),
    UNIQUE (id_usuario, id_evento) -- un usuario no puede inscribirse dos veces al mismo evento
);
