-- Script para insertar datos en la base de datos de Render
-- Ejecutar este script en la base de datos de Render

-- Insertar Rooms
INSERT INTO room (id_room, name_room, location_room) VALUES
(1, 'sala', 'casa'),
(2, 'cocina', 'casagonza'),
(3, 'baño', 'casawilly');

-- Insertar Devices
INSERT INTO device (id_device, name_device, number_device_maintenance, price_device, purchase_date_device, type_device, id_room) VALUES
(1, 'prueba', 1, 123, '2025-09-12', 'wa', 1),
(2, 'prueba1', 2, 1234, '2025-09-11', 'was', 2),
(3, 'perueba2', 3, 12345, '2025-09-10', 'wasa', 3);

-- Insertar Users (las contraseñas están sin encriptar, necesitarás encriptarlas con BCrypt)
-- NOTA: Estas contraseñas NO funcionarán porque Spring Security espera contraseñas encriptadas con BCrypt
-- Necesitarás usar la API para crear usuarios o encriptar las contraseñas primero
INSERT INTO users (id, enabled, password, username) VALUES
(1, true, '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'pepito'),  -- password: 123
(2, true, '$2a$10$DowJonesHxGBqz3B4E.8rOe5EZnhL6y6Hx9M0A6lqCZRtaEQNj5EO', 'lucho'),   -- password: 1234
(3, true, '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'jaime');   -- password: 12345

-- Insertar Roles
INSERT INTO roles (id, rol, user_id) VALUES
(1, 'camionero', 1),
(2, 'carpintero', 2),
(3, 'policia', 3);

-- Reiniciar las secuencias para que los próximos IDs sean correctos
SELECT setval('room_id_room_seq', (SELECT MAX(id_room) FROM room));
SELECT setval('device_id_device_seq', (SELECT MAX(id_device) FROM device));
SELECT setval('users_id_seq', (SELECT MAX(id) FROM users));
SELECT setval('roles_id_seq', (SELECT MAX(id) FROM roles));
