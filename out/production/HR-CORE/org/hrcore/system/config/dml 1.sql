use HR_Core_In4am;

insert into Department(name, description)
values
    ('Recursos Humanos', 'Gestion del personal y procesos administrativos'),
    ('Tecnologia', 'Desarrollo y mantenimiento de sistemas informaticos'),
    ('Finanzas', 'Gestion financiera y contable de la empresa'),
    ('Marketing', 'Promocion y estrategias de marketing'),
    ('Ventas', 'Gestion de ventas y clientes');

insert into role (role_type, description)
values
    ('Administrador', 'Tiene acceso completo al sistema'),
    ('Gerente', 'Gestiona departamentos y empleados'),
    ('Supervisor', 'Supervisa las actividades de los empleados'),
    ('Empleado', 'Realiza las actividades asignadas'),
    ('Analista', 'Analiza informacion y genera reportes');

-- Usuarios con password en texto plano (type = 1)
insert into users
(first_names, last_names, username, monthly_salary, hire_date, password, type_encrypt, department_id, role_id)
values
    ('Carlos Alberto', 'Lopez Garcia',       'clopez',   8500.00, '2022-01-15', 'pass123', 1, 1, 2),
    ('Maria Fernanda', 'Perez Lopez',        'mperez',   6500.00, '2023-03-20', 'pass456', 1, 2, 3),
    ('Juan Carlos',    'Gomez Ramirez',      'jgomez',   5500.00, '2023-06-10', 'pass789', 1, 3, 4),
    ('Ana Sofia',      'Martinez Castillo',  'amartinez',7200.00, '2021-11-05', 'pass321', 1, 4, 5),
    ('Pedro Antonio',  'Hernandez Morales',  'phernandez',6000.00,'2024-02-12', 'pass654', 1, 5, 4),
    ('Laura Isabel',   'Rodriguez Perez',    'lrodriguez',9000.00,'2020-08-18', 'pass987', 1, 1, 1),
    ('Miguel Angel',   'Ramirez Torres',     'mramirez', 7800.00, '2022-09-25', 'pass159', 1, 2, 2),
    ('Sofia Valentina','Castro Mendoza',     'scastro',  5800.00, '2024-01-08', 'pass753', 1, 3, 4),
    ('Daniel Eduardo', 'Morales Sanchez',    'dmorales', 6700.00, '2023-07-14', 'pass852', 1, 4, 3),
    ('Gabriela Elena', 'Torres Flores',      'gtorres',  6200.00, '2024-04-22', 'pass951', 1, 5, 5);

-- Prueba con MD5 (type = 2)
call insert_worker('Usuario MD5', 'Prueba', 'umd5', 5000.00, '2025-01-15', 'mipass', 2, 4, 2);

-- Prueba con BCrypt (type = 3) — el hash debe generarse en Java primero
-- call insert_worker('Usuario BCrypt', 'Prueba', 'ubcrypt', 5000.00, '2025-01-15',
--     '$2a$06$XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX', 3, 2, 4);

call query_workers();
call query_departments();