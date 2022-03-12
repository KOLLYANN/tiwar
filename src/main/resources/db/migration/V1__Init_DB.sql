create table usr
(
    id                 bigserial    not null,
    activate_mail_code varchar(255) not null,
    active             boolean      not null,
    active_code        boolean      not null,
    count              int4         not null,
    email              varchar(255) not null,
    password           varchar(255) not null,
    username           varchar(255) not null,
    primary key (id)
);
create table usr_role
(
    id_user int8 not null,
    roles   varchar(255)
);

alter table usr_role
    add constraint usr_role_fk foreign key (id_user) references usr;

create table person
(
    id        bigserial not null,
    age       int4      not null,
    firstname varchar(255),
    lastname  varchar(255),
    path      varchar(255),
    primary key (id)
);