create table payment_schedules (
    id                  binary(16) not null,
    actual_payment_date date,
    is_paid             bit,
    monthly_payment     decimal(38, 2),
    payment_date        date       not null,
    surcharge           decimal(38, 2),
    account_id          binary(16),
    primary key (id)
) engine = InnoDB;
