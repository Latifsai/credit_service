create table agreements (
    id binary(16) not null,
    active bit,
    agreement_date date not null,
    number varchar(20),
    termination_date date,
    primary key (id)
) engine = InnoDB;
