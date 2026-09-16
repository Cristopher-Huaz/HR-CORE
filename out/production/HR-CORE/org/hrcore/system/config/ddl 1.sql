Drop database if exists HR_Core_In4am;
Create database HR_Core_In4am;
use HR_Core_In4am;

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
    monthly_salary decimal(10, 2),
    hire_date date not null,
    password varchar(255) not null,
    department_id int not null,
    role_id int not null,
    constraint fk_user_department foreign key (department_id) 
        references DEPARTMENT(id),
    constraint fk_user_role foreign key (role_id) 
        references role(id)
);

DELIMITER //

Create procedure insert_worker(
    in p_first_names varchar(100),
    in p_last_names varchar(100),
    in p_monthly_salary decimal(10, 2),
    in p_hire_date date,
    in p_password varchar(255),
    in p_department_id int,
    in p_role_id int
)
begin 

    insert into users(
        first_names,
        last_names,
        monthly_salary,
        hire_date,
        password,
        department_id,
        role_id
    )
    values(
        p_first_names,
        p_last_names,
        p_monthly_salary,
        p_hire_date,
        p_password,
        p_department_id,
        p_role_id
    );

end //

Delimiter ;

Delimiter //

Create procedure update_worker(
    in p_id INT,
    in p_first_names varchar(100),
    in p_last_names varchar(100),
    in p_monthly_salary decimal(10, 2),
    in p_hire_date date,
    in p_password varchar(255),
    in p_department_id int,
    in p_role_id int
)
begin

    update users
    set
        first_names = p_first_names,
        last_names = p_last_names,
        monthly_salary = p_monthly_salary,
        hire_date = p_hire_date,
        password = p_password,
        department_id = p_department_id,
        role_id = p_role_id
    where id = p_id;

end //

delimiter ;

delimiter //

Create procedure delete_worker(
    in p_id int
)
begin
    delete from users
    where id = p_id;
end //

Delimiter ;

Delimiter //

Create procedure query_workers()
begin
    select
        u.id,
        u.first_names,
        u.last_names,
        u.monthly_salary,
        u.hire_date,
        d.name AS department,
        r.role_type AS role
    from users u
    inner join Department d
        on u.department_id = d.id
    inner join role r
        on u.role_id = r.id
    order by u.id;
end //

DELIMITER ;

DELIMITER //

Create procedure insert_department(
    in p_name varchar(100),
    in p_description varchar(255)
)
begin
    insert into Department(
        name,
        description
    )
    values(
        p_name,
        p_description
    );
end //

DELIMITER ;

DELIMITER //

Create procedure query_departments()
begin
    select
        id,
        name,
        description
    from Department
    order by id;
end //

DELIMITER ;

DELIMITER //

Create procedure authenticate_user(
    in p_first_names varchar(100),
    in p_password varchar(255)
)
begin
    select
        u.id,
        u.first_names,
        u.last_names,
        d.name as department,
        r.role_type as role
    from users u
    inner join Department d
        on u.department_id = d.id
    inner join role r
        on u.role_id = r.id
    where u.first_names = p_first_names
      and u.password = p_password;
end //

Delimiter ;



