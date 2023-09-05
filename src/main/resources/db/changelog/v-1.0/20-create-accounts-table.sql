create table accounts (
    id binary(16) not null,
    account_number varchar(16) not null,
    balance decimal(38, 2) not null,
    closing_date date not null,
    country varchar(255) not null,
    currency varchar(255) not null,
    loan_debt decimal(38, 2),
    opening_date date not null,
    last_update_date date not null,
    percentage_debt decimal(38, 2),
    status enum ('ACTIVE','BLOCKED','DELETED') not null,
    unpaid_credit_sum decimal(38, 2),
    user_id binary(16),
    primary key (id)
) engine = InnoDB;
