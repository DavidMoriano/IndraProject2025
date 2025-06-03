CREATE DATABASE IF NOT EXISTS proyectoindra;
USE proyectoindra;

CREATE TABLE usuarios (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100),
    email VARCHAR(100),
    password VARCHAR(255)
);

CREATE TABLE categorias (
    id_categoria INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100)
);

CREATE TABLE organizadores (
    id_organizador INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100),
    informacion_contacto VARCHAR(255)
);

CREATE TABLE ubicaciones (
    id_ubicacion INT PRIMARY KEY AUTO_INCREMENT,
    tipo ENUM('presencial', 'online'),
    direccion VARCHAR(255)
);

CREATE TABLE eventos (
    id_evento INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(150),
    fecha DATE,
    duracion INT,
    estado ENUM('pendiente', 'confirmado', 'cancelado'),
    id_categoria INT,
    id_organizador INT,
    id_ubicacion INT,
    FOREIGN KEY (id_categoria) REFERENCES categorias(id_categoria),
    FOREIGN KEY (id_organizador) REFERENCES organizadores(id_organizador),
    FOREIGN KEY (id_ubicacion) REFERENCES ubicaciones(id_ubicacion)
);

CREATE TABLE inscripciones (
    id_inscripcion INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT,
    id_evento INT,
    fecha_inscripcion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario),
    FOREIGN KEY (id_evento) REFERENCES eventos(id_evento)
);
