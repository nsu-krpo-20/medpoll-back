CREATE TABLE IF NOT EXISTS users
(
    id       bigint AUTO_INCREMENT PRIMARY KEY,
    login    varchar(64) NOT NULL UNIQUE,
    password text        NOT NULL
);

CREATE TABLE IF NOT EXISTS roles
(
    id   bigint AUTO_INCREMENT PRIMARY KEY,
    name varchar(32) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS user_role
(
    user_id bigint REFERENCES users ON DELETE CASCADE,
    role_id bigint REFERENCES roles ON DELETE CASCADE,
    PRIMARY KEY (user_id, role_id)
);

INSERT INTO users(login, password)
VALUES ('admin', 'admin');

INSERT INTO roles(name)
VALUES ('ADMIN'),
       ('DOCTOR'),
       ('PATIENT');

INSERT INTO user_role(user_id, role_id)
VALUES (1, 1);

INSERT INTO user_role(user_id, role_id)
VALUES (1, 2);
