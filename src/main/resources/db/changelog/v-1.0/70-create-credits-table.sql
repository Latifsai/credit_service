create table credits (
    id binary(16) not null,
    credit_status enum ('ACTIVE','CLOSED','DECLINED') not null,
    credit_limit decimal(38, 2),
    credit_type varchar(255) not null,
    currency varchar(255),
    fine decimal(38, 2),
    interest_rate decimal(38, 2),
    need_deposit bit,
    period_month integer,
    account_id binary(16),
    agreement_id binary(16),
    credit_order_id binary(16),
    primary key (id)
) engine = InnoDB;



