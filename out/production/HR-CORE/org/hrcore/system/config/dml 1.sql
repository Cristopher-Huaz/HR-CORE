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

insert into users
(first_names, last_names, monthly_salary, hire_date, password, department_id, role_id)
values
('Carlos Alberto', 'Lopez Garcia', 8500.00, '2022-01-15', 'pass123', 1, 2),
('Maria Fernanda', 'Perez Lopez', 6500.00, '2023-03-20', 'pass456', 2, 3),
('Juan Carlos', 'Gomez Ramirez', 5500.00, '2023-06-10', 'pass789', 3, 4),
('Ana Sofia', 'Martinez Castillo', 7200.00, '2021-11-05', 'pass321', 4, 5),
('Pedro Antonio', 'Hernandez Morales', 6000.00, '2024-02-12', 'pass654', 5, 4),
('Laura Isabel', 'Rodriguez Perez', 9000.00, '2020-08-18', 'pass987', 1, 1),
('Miguel Angel', 'Ramirez Torres', 7800.00, '2022-09-25', 'pass159', 2, 2),
('Sofia Valentina', 'Castro Mendoza', 5800.00, '2024-01-08', 'pass753', 3, 4),
('Daniel Eduardo', 'Morales Sanchez', 6700.00, '2023-07-14', 'pass852', 4, 3),
('Gabriela Elena', 'Torres Flores', 6200.00, '2024-04-22', 'pass951', 5, 5);

call insert_worker(
    'New Worker',
    'System Test',
    7000.00,
    '2026-09-08',
    'pass999',
    2,
    4
);

call update_worker(
    1,
    'Carlos Alberto',
    'Lopez Garcia',
    9000.00,
    '2022-01-15',
    'pass123',
    1,
    2
);

call query_workers();

call insert_department(
    'Production',
    'Management of the company production processes'
);

call query_departments();

call authenticate_user(
    'Carlos Alberto',
    'pass123'
);


