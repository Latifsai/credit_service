CREATE TABLE delays
(
    id                binary(16) not null,
    sum_of_delay      decimal(38, 2) not null,
    time_of_delay     datetime(6) not null,
    credit_history_id binary(16) not null,
    primary key (id)
) engine = InnoDB;