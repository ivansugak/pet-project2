delete from users;
delete from vacancy;
insert into users (id, first_name, last_name, age, vocation, role, login, password)
VALUES (1, 'ADMIN', 'ADMINOV', 25, 'Google', 'ADMIN', 'ADMIN', '$2a$10$ngKBn8lAHupZJD3qapOoeuoqkrRKRyag5eTp4FX4rUUdrdKHIU0vG');
insert into users (id, first_name, last_name, age, vocation, role, login, password)
VALUES (11, 'Anna', 'Annov', 25, 'Google', 'USER', 'Anna', '$2a$10$B7zsImYWJbYe1C7Tu3TdQOHONUWbqpMOGgApZ6fvrjCek3euLPHEG');
insert into vacancy (id, vocation, description, user_id)
VALUES (1, 'Google', 'Google', '1');
insert into vacancy (id, vocation, description, user_id)
VALUES (2, 'Google', 'Google', '11');