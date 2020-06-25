insert into `role` (role_id, name)
values (1, 'ADMIN'),
       (2, 'EDITOR'),
       (3, 'PROFILE');

insert into `permission` (permission_id, name)
values (1, 'ACCESS_ADMIN'),
(2, 'ACCESS_EDITOR'),
(3, 'ACCESS_PROFILE');

insert into `role_permission_sw` (role_id, permission_id)
values (1,1),
       (1,2),
       (1,3),
       (2,2),
       (3,3);

insert into `user` (user_id, user_name, first_name, last_name, password)
values (1, 'Admin', 'Admin', 'Admin', '$2y$04$BeoBiTk36Q306CgdJ9Q3/OkXqKmy/eFiQwG59XqfHPG3WYEyJxFXu'),
       (2, 'User1', 'User1', 'User1', '$2y$04$BeoBiTk36Q306CgdJ9Q3/OkXqKmy/eFiQwG59XqfHPG3WYEyJxFXu'),
       (3, 'User2', 'User2', 'User2', '$2y$04$BeoBiTk36Q306CgdJ9Q3/OkXqKmy/eFiQwG59XqfHPG3WYEyJxFXu'),
       (4, 'User3', 'User3', 'User3', '$2y$04$BeoBiTk36Q306CgdJ9Q3/OkXqKmy/eFiQwG59XqfHPG3WYEyJxFXu');


insert into `users_role_sw` (user_id, role_id)
values (1, 1),
       (2, 2),
       (2, 3),
       (3, 2),
       (4, 3);