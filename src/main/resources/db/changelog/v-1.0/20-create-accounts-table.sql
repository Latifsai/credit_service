create table accounts
(
    id binary(16) not null,
    account_number varchar(16) not null,
    balance decimal(38, 2) not null,
    closing_date date not null,
    country varchar(255) not null,
    currency varchar(255) not null,
    loan_debt decimal(38, 2),
    opening_date date not null,
    percentage_debt decimal(38, 2),
    status enum ('ACTIVE','BLOCKED','DELETED') not null,
    unpaid_credit_sum decimal(38, 2),
    client_id binary(16),
    primary key (id)
) engine = InnoDB;


alter table accounts
    add constraint UK_d4jd6sehy1h0pudduahn0ntbe unique (client_id);

alter table accounts
    add constraint FKgymog7firrf8bnoiig61666ob
    foreign key (client_id)
    references clients (id)