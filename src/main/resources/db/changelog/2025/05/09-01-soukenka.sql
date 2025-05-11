-- liquibase formatted sql

-- changeset soukenka:1746793248633-1
-- comment: Creates the role table to store user roles
CREATE TABLE usr_role
(
    id_usr_role BIGINT AUTO_INCREMENT NOT NULL,
    version     BIGINT DEFAULT 0      NOT NULL,
    name        VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_usr_role PRIMARY KEY (id_usr_role)
);
-- rollback DROP TABLE usr_role;

-- changeset soukenka:1746793248633-2
-- comment: Creates the user table to store user information
CREATE TABLE usr_user
(
    id_usr_user BIGINT AUTO_INCREMENT NOT NULL,
    version     BIGINT DEFAULT 0      NOT NULL,
    username    VARCHAR(255)          NOT NULL,
    password    VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_usr_user PRIMARY KEY (id_usr_user)
);
-- rollback DROP TABLE usr_user;

-- changeset soukenka:1746793248633-3
-- comment: Creates the join table to establish many-to-many relationship between users and roles
CREATE TABLE usr_user_role
(
    id_usr_role BIGINT NOT NULL,
    id_usr_user BIGINT NOT NULL,
    CONSTRAINT pk_usr_user_role PRIMARY KEY (id_usr_role, id_usr_user)
);
-- rollback DROP TABLE usr_user_role;

-- changeset soukenka:1746793248633-4
-- comment: Creates unique constraint on the 'name' column of usr_role table
ALTER TABLE usr_role
    ADD CONSTRAINT uc_usr_role_name UNIQUE (name);
-- rollback ALTER TABLE usr_role DROP CONSTRAINT uc_usr_role_name;

-- changeset soukenka:1746793248633-5
-- comment: Creates unique constraint on the 'username' column of usr_user table
ALTER TABLE usr_user
    ADD CONSTRAINT uc_usr_user_username UNIQUE (username);
-- rollback ALTER TABLE usr_user DROP CONSTRAINT uc_usr_user_username;

-- changeset soukenka:1746793248633-6
-- comment: Creates foreign key constraint linking usr_user_role to usr_role table
ALTER TABLE usr_user_role
    ADD CONSTRAINT fk_usruserol_on_usr_role FOREIGN KEY (id_usr_role) REFERENCES usr_role (id_usr_role);
-- rollback ALTER TABLE usr_user_role DROP CONSTRAINT fk_usruserol_on_usr_role;

-- changeset soukenka:1746793248633-7
-- comment: Creates foreign key constraint linking usr_user_role to usr_user table
ALTER TABLE usr_user_role
    ADD CONSTRAINT fk_usruserol_on_usr_user FOREIGN KEY (id_usr_user) REFERENCES usr_user (id_usr_user);
-- rollback ALTER TABLE usr_user_role DROP CONSTRAINT fk_usruserol_on_usr_user;

