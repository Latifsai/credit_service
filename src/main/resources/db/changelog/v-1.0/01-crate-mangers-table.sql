create table managers
(
    id       binary(16) not null,
    `e-mail` varchar(255),
    name     varchar(255) not null,
    surname  varchar(255) not null,
    primary key (id)
) engine=InnoDB

GO

alter table clients
    add constraint FKb04ux70c9nn0yi4y3pmitlfo9
    foreign key (manager_id)
    references managers (id)
