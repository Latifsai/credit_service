create table clients (
    id binary(16) not null,
    address varchar(255) not null,
    email varchar(255),
    expenses decimal(38, 2),
    name varchar(255) not null,
    passive_income decimal(38, 2),
    phone varchar(255),
    registration_date date not null,
    salary decimal(38, 2),
    surname varchar(255) not null,
    update_date date not null,
    manager_id binary(16),
    primary key (id)
) engine = InnoDB;


alter table clients
    add constraint FKb04ux70c9nn0yi4y3pmitlfo9
    foreign key (manager_id)
    references managers (id)