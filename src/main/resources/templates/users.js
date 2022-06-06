create table users
(
    id         bigint auto_increment
primary key,
    first_name varchar(255) null,
    last_name  varchar(255) null,
    age        int          null,
    vocation   varchar(255) null,
    role       varchar(255) null,
    login      varchar(255) not null,
    password   varchar(255) not null,
    constraint users_login_uindex
unique (login),
    constraint users_password_uindex
unique (password)
);