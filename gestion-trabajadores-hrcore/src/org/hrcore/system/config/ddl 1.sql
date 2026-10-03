Drop database if exists HR_Core_In4am;
Create database HR_Core_In4am;
use HR_Core_In4am;

-- ============================================================
-- TABLAS
-- ============================================================

Create table Department(
                           id int primary key auto_increment,
                           name varchar(100) not null,
                           description varchar(255)
);

Create table role(
                     id int primary key auto_increment,
                     role_type varchar(50) not null,
                     description varchar(255)
);

Create table users(
                      id int primary key auto_increment,
                      first_names varchar(100) not null,
                      last_names varchar(100) not null,
                      username varchar(50) not null unique,
                      monthly_salary decimal(10, 2),
                      hire_date date not null,
                      password varchar(255) not null,
                      type_encrypt int not null default 1,
                      department_id int not null,
                      role_id int not null,
                      constraint fk_user_department foreign key (department_id) references Department(id),
                      constraint fk_user_role foreign key (role_id) references role(id)
);

-- NUEVA TABLA: Historial de observaciones y descuentos de sueldo
Create table salary_observations(
                                    id int primary key auto_increment,
                                    user_id int not null,
                                    previous_salary decimal(10, 2) not null,
                                    new_salary decimal(10, 2) not null,
                                    amount_discounted decimal(10, 2) not null,
                                    observation varchar(255) not null,
                                    modification_date datetime default current_timestamp,
                                    constraint fk_observation_user foreign key (user_id) references users(id) on delete cascade
);

-- ============================================================
-- STORED PROCEDURES - WORKERS (Actualizados)
-- ============================================================

Drop procedure if exists insert_worker;
DELIMITER //
Create procedure insert_worker(
    in p_first_names varchar(100),
    in p_last_names varchar(100),
    in p_username varchar(50),
    in p_monthly_salary decimal(10, 2),
    in p_hire_date date,
    in p_password varchar(255),
    in p_department_id int,
    in p_role_id int,
    in p_type_encrypt int
)
begin
insert into users(
    first_names, last_names, username,
    monthly_salary, hire_date, password,
    department_id, role_id, type_encrypt
)
values(
          p_first_names, p_last_names, p_username,
          p_monthly_salary, p_hire_date,
          case
              when p_type_encrypt = 2 then md5(p_password)
              else p_password
              end,
          p_department_id, p_role_id, p_type_encrypt
      );
end //
DELIMITER ;

-- NUEVO PROCEDIMIENTO: Actualiza sueldo y guarda observación
Drop procedure if exists update_salary_with_observation;
DELIMITER //
Create procedure update_salary_with_observation(
    in p_user_id int,
    in p_new_salary decimal(10, 2),
    in p_observation varchar(255)
)
begin
    DECLARE v_old_salary decimal(10, 2);
    DECLARE v_discount decimal(10, 2);

    -- Obtenemos el sueldo actual antes de actualizar
SELECT monthly_salary INTO v_old_salary FROM users WHERE id = p_user_id;

-- Calculamos el descuento (si el nuevo sueldo es menor, la diferencia es el descuento)
SET v_discount = v_old_salary - p_new_salary;

    -- Actualizamos el sueldo en la tabla users
UPDATE users SET monthly_salary = p_new_salary WHERE id = p_user_id;

-- Insertamos la observación en la nueva tabla
INSERT INTO salary_observations(user_id, previous_salary, new_salary, amount_discounted, observation)
VALUES(p_user_id, v_old_salary, p_new_salary, v_discount, p_observation);
end //
DELIMITER ;

Drop procedure if exists update_worker;
DELIMITER //
Create procedure update_worker(
    in p_id INT,
    in p_first_names varchar(100),
    in p_last_names varchar(100),
    in p_username varchar(50),
    in p_monthly_salary decimal(10, 2),
    in p_hire_date date,
    in p_password varchar(255),
    in p_department_id int,
    in p_role_id int,
    in p_type_encrypt int
)
begin
update users
set
    first_names = p_first_names,
    last_names = p_last_names,
    username = p_username,
    monthly_salary = p_monthly_salary,
    hire_date = p_hire_date,
    password = case
                   when p_password is null or trim(p_password) = '' then password
                   when p_type_encrypt = 2 then md5(p_password)
                   else p_password
        end,
    department_id = p_department_id,
    role_id = p_role_id,
    type_encrypt = p_type_encrypt
where id = p_id;
end //
DELIMITER ;

Drop procedure if exists delete_worker;
DELIMITER //
Create procedure delete_worker(
    in p_id int
)
begin
delete from users where id = p_id;
end //
DELIMITER ;

Drop procedure if exists query_workers;
DELIMITER //
Create procedure query_workers()
begin
select
    u.id, u.first_names, u.last_names, u.username,
    u.monthly_salary, u.hire_date, u.type_encrypt,
    d.name AS department, r.role_type AS role
from users u
         inner join Department d on u.department_id = d.id
         inner join role r on u.role_id = r.id
order by u.id;
end //
DELIMITER ;

Drop procedure if exists sp_find_person_by_id;
DELIMITER //
Create procedure sp_find_person_by_id(in p_id int)
begin
select
    u.id, u.first_names, u.last_names, u.username,
    u.monthly_salary, u.hire_date, u.password, u.type_encrypt,
    d.name as department, r.role_type as role
from users u
         inner join Department d on u.department_id = d.id
         inner join role r on u.role_id = r.id
where u.id = p_id;
end //
DELIMITER ;

Drop procedure if exists sp_search_worker_by_username;
DELIMITER //
Create procedure sp_search_worker_by_username(in p_username varchar(50))
begin
select
    u.id, u.first_names, u.last_names, u.username,
    u.monthly_salary, u.hire_date, u.type_encrypt,
    d.name as department, r.role_type as role
from users u
         inner join Department d on u.department_id = d.id
         inner join role r on u.role_id = r.id
where u.username = p_username;
end //
DELIMITER ;

Drop procedure if exists sp_exists_worker_by_username;
DELIMITER //
Create procedure sp_exists_worker_by_username(in p_username varchar(50))
begin
select count(*) as total from users where username = p_username;
end //
DELIMITER ;

Drop procedure if exists sp_exists_worker_by_username_and_password;
DELIMITER //
Create procedure sp_exists_worker_by_username_and_password(
    in p_username varchar(50),
    in p_password varchar(255)
)
begin
select count(*) as total
from users
where username = p_username
  and (
    (type_encrypt = 1 and password = p_password)
        or (type_encrypt = 2 and password = md5(p_password))
    );
end //
DELIMITER ;

Drop procedure if exists authenticate_user;
DELIMITER //
Create procedure authenticate_user(
    in p_username varchar(50),
    in p_password varchar(255)
)
begin
select
    u.id, u.first_names, u.last_names, u.username,
    u.password, u.type_encrypt,
    d.name as department, r.role_type as role
from users u
         inner join Department d on u.department_id = d.id
         inner join role r on u.role_id = r.id
where u.username = p_username
  and (
    (u.type_encrypt = 1 and u.password = p_password)
        or (u.type_encrypt = 2 and u.password = md5(p_password))
        or (u.type_encrypt = 3)
    );
end //
DELIMITER ;

-- ============================================================
-- STORED PROCEDURES - DEPARTMENTS
-- ============================================================

Drop procedure if exists insert_department;
DELIMITER //
Create procedure insert_department(
    in p_name varchar(100),
    in p_description varchar(255)
)
begin
insert into Department(name, description) values(p_name, p_description);
end //
DELIMITER ;

Drop procedure if exists query_departments;
DELIMITER //
Create procedure query_departments()
begin
select id, name, description from Department order by id;
end //
DELIMITER ;