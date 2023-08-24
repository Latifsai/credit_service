create table products (
    id decimal(38, 0) not null,
    calculation_type enum ('ANNUITY','DIFFERENTIATED') not null,
    currency_code varchar(255),
    details varchar(255) not null,
    early_guaranty bit,
    name varchar(255) not null,
    need_guaranty bit,
    need_income_details bit,
    sum decimal(38, 2),
    primary key (id)
) engine = InnoDB;

