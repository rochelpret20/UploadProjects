CREATE DATABASE biblioteca;

USE biblioteca;

CREATE TABLE usuarios(

id INT AUTO_INCREMENT PRIMARY KEY,

nombre VARCHAR(100),

correo VARCHAR(100)

);

CREATE TABLE libros(

id INT AUTO_INCREMENT PRIMARY KEY,

titulo VARCHAR(150),

autor VARCHAR(100),

stock INT

);

CREATE TABLE prestamos(

id INT AUTO_INCREMENT PRIMARY KEY,

usuario_id INT,

libro_id INT,

fecha_prestamo DATE,

fecha_devolucion DATE,

FOREIGN KEY(usuario_id)
REFERENCES usuarios(id),

FOREIGN KEY(libro_id)
REFERENCES libros(id)

);

-- DATOS DE PRUEBA --
INSERT INTO usuarios(nombre,correo)
VALUES
('Marvin Pretel','marvin@gmail.com'),
('Juan Perez','juan@gmail.com');

INSERT INTO libros(
titulo,
autor,
stock
)
VALUES
('Clean Code','Robert Martin',10),
('Python Crash Course','Eric Matthes',5);