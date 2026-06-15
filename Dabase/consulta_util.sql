USE biblioteca;
SELECT
u.nombre,
l.titulo,
p.fecha_prestamo
FROM prestamos p
JOIN usuarios u
ON p.usuario_id=u.id
JOIN libros l
ON p.libro_id=l.id;