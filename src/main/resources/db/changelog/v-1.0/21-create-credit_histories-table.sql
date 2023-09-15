CREATE TABLE credit_histories(
    id binary(16) not null,
    status enum('FLAWLESS', 'WELL', 'DEPRAVED', 'BAD'),
    account_id binary(16),
    primary key(id)
) engine = InnoDB;
