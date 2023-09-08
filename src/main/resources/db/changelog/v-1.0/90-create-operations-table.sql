create table operations (
    id                 binary(16) not null,
    currency           varchar(255),
    debit              bit,
    operation_details  varchar(255) not null,
    operation_end_mark datetime(6) not null,
    sum                decimal(38, 2),
    type               enum ('EARLY_REPAYMENT','MONTHLY_PAYMENT','PAYMENT_WITH_FINE','REPLENISHMENT') not null,
    account_id         binary(16),
    primary key (id)
) engine = InnoDB;
