create table credit_orders (
    id binary(16) not null,
    amount decimal(38, 2),
    client_monthly_expenditure decimal(38, 2),
    client_income decimal(38, 2) not null,
    creation_date date not null,
    status enum ('APPROVED','CLOSED','DECLINED','IN_REVIEW') not null,
    last_update_date date not null,
    max_period_months integer,
    min_period_months integer,
    number varchar(20) not null,
    passive_income decimal(38, 2),
    product_id decimal(38, 0),
    primary key (id)
) engine = InnoDB;


