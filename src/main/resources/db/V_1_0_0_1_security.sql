CREATE TABLE IF NOT EXISTS users
(
    id bigint AUTO_INCREMENT PRIMARY KEY,
    login varchar(64) UNIQUE NOT NULL,
    password varchar(64) NOT NULL,
    email varchar(64)
);

CREATE TABLE IF NOT EXISTS roles
(
    id bigint AUTO_INCREMENT PRIMARY KEY,
    name varchar(32) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS user_role
(
    user_id bigint REFERENCES users ON DELETE CASCADE,
    role_id bigint REFERENCES roles ON DELETE CASCADE,
    PRIMARY KEY (user_id, role_id)
);

INSERT INTO roles(name)
VALUES ('ADMIN'),
       ('DOCTOR'),
       ('USER');
