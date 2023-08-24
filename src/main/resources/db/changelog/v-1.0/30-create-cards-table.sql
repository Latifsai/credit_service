create table cards (
  id binary(16) not null,
  iban varchar(255) not null,
  balance decimal(38,2),
  card_number varchar(12) not null,
  card_status enum ('ACTIVE','BLOCKED','ENDED') not null,
  delivery_address varchar(255) not null,
  expiration_date date not null,
  holder_name varchar(255) not null,
  is_digital_valet bit,
  opening_date date not null,
  payment_system enum ('AMERICAN_EXPRESS','MAESTRO','MASTERCARD','MIR','VISA') not null,
  account_id binary(16),
  primary key (id)
) engine=InnoDB;

