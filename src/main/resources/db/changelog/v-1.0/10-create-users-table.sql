create table users
(
    id                binary(16)   not null,
    address           varchar(255) not null,
    email             varchar(255),
    expenses          decimal(38, 2),
    name              varchar(255) not null,
    passive_income    decimal(38, 2),
    phone             varchar(255),
    registration_date date         not null,
    salary            decimal(38, 2),
    surname           varchar(255) not null,
    update_date       date         not null,
    role_id           int          not null,
    primary key (id)
) engine = InnoDB;
