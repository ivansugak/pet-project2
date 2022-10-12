create table if not exists users
(
    id         bigint auto_increment
        primary key,
    first_name varchar(255)                          null,
    last_name  varchar(255)                          null,
    age        int                                   null,
    vocation   varchar(255)                          null,
    role       enum ('USER', 'ADMIN')                not null,
    login      varchar(255)                          not null,
    password   varchar(255)                          not null,
    constraint users_login_uindex
        unique (login),
    constraint users_password_uindex
        unique (password)
);

create table if not exists vacancy
(
    id          bigint auto_increment
        primary key,
    vocation    varchar(255) null,
    description varchar(255) null,
    user_id     bigint       null,
    constraint vacancy_users_id_fk
        foreign key (user_id) references users (id)
);