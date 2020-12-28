
INSERT INTO users (id, name, email, password) VALUES (1, 'blogeditor', 'editor@mt.com', 'password');
INSERT INTO users (id, name, email, password)VALUES (2, 'blogauthor', 'author@mt.com', 'password');

INSERT INTO roles VALUES (3, 'EDITOR');
INSERT INTO roles VALUES (4, 'AUTHOR');

INSERT INTO users_roles VALUES (1, 3);
INSERT INTO users_roles VALUES (1, 4);
INSERT INTO users_roles VALUES (2, 4);

SELECT users.id, users.email, users.name, roles.id AS role_id, roles.name AS role_name
FROM users
         JOIN users_roles on (users.id=users_roles.user_id)
         JOIN roles on (roles.id=users_roles.role_id);

select * from users;