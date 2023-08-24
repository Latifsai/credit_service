create table managers (
    id binary(16) not null,
    `e-mail` varchar(255),
    name varchar(255) not null,
    surname varchar(255) not null,
    primary key (id)
) engine=InnoDB;
