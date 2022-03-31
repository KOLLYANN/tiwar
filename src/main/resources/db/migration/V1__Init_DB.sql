create table boss
(
    id            bigserial not null,
    attack_exists int8,
    health        int8,
    max_health    int8,
    name          varchar(255),
    power         int8,
    title_ava     varchar(255),
    primary key (id)
);

create table clans
(
    id          bigserial
        primary key,
    countb1     bigint,
    countb2     bigint,
    countb3     bigint,
    countb4     bigint,
    countb5     bigint,
    exp         bigint,
    expy        bigint,
    gold_clan   bigint,
    level       bigint,
    owner_id    bigint,
    silver_clan bigint,
    title       varchar(255)
);

create table person
(
    id        bigserial not null,
    age       int4      not null,
    firstname varchar(255),
    lastname  varchar(255),
    path      varchar(255),
    primary key (id)
);
create table thing
(
    id          bigserial not null,
    grade       varchar(255),
    parameters  int8,
    path_image  varchar(255),
    place       varchar(255),
    position    int8,
    price       int8,
    skill_grade int8,
    state       int8,
    title       varchar(255),
    user_id     int8,
    primary key (id)
);
create table usr
(
    id                     bigserial not null,
    activate_mail_code     varchar(255),
    active                 boolean   not null,
    active_code            boolean,
    amount_gold_for_clan   int8,
    amount_silver_for_clan int8,
    boss_damage            int8,
    email                  varchar(255),
    exp                    int8,
    exp_for_clan           int8,
    expy                   int8,
    health                 int8,
    id_boss_attack         int8,
    mana                   int8,
    max_health             int8,
    max_mana               int8,
    password               varchar(255),
    path_to_avatar         varchar(255),
    power                  int8,
    price_health           int8,
    price_mana             int8,
    price_power            int8,
    silver                 int8,
    skill                  int8,
    skill_health           int8,
    skill_mana             int8,
    skill_max_health       int8,
    skill_max_mana         int8,
    skill_max_power        int8,
    skill_power            int8,
    user_gold              int8,
    user_level             int8,
    username               varchar(255),
    clan_id                int8,
    clan_request_id        int8,
    primary key (id)
);
create table usr_role
(
    id_user int8 not null,
    roles   varchar(255)
);
alter table thing
    add constraint FK5ovuiuwtoiwmbtme69kho6t3h foreign key (user_id) references usr;
alter table clans
    owner to postgres;
alter table usr
    add constraint FKyib1sci4j3ix3bym9oxtdmro foreign key (clan_id) references clans;
alter table usr
    add constraint FKik1c4k2u7kbuda801gt9w4vcf foreign key (clan_request_id) references clans;
alter table usr_role
    add constraint FKkgfg5s77b1cb3ux87dewc0f9u foreign key (id_user) references usr;
