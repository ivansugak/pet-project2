create table vacancy
(
    id              bigint auto_increment
primary key,
    description     varchar(255) null,
    vocationVacancy varchar(255) null,
    user_id         bigint       null,
    vocation        varchar(255) null,
    constraint vacancy_users_id_fk
foreign key (user_id) references users (id)
);