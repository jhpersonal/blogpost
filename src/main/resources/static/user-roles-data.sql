INSERT INTO blogpostdb.user (id, name, email, password) VALUES (1, 'blogeditor', 'editor@mt.com', '$2a$10$BUwO3EktX49jCtwjb.Xi4.UqdRTFwtrpqimJZndxWsCzH0uJhPgyK');
INSERT INTO blogpostdb.user (id, name, email, password)VALUES (2, 'blogauthor', 'author@mt.com', '$2a$10$BUwO3EktX49jCtwjb.Xi4.UqdRTFwtrpqimJZndxWsCzH0uJhPgyK');

INSERT INTO role VALUES (3, 'EDITOR');
INSERT INTO role VALUES (4, 'AUTHOR');

INSERT INTO user_roles VALUES (1, 3);
INSERT INTO user_roles VALUES (1, 4);
INSERT INTO user_roles VALUES (2, 4);

SELECT users.id, users.email, users.name, role.id AS role_id, role.name AS role_name
FROM blogpostdb.user AS users
         JOIN user_roles on (users.id=user_roles.user_id)
         JOIN role on (role.id=user_roles.roles_id);

SELECT * FROM blogpostdb.user;
SELECT * FROM role;