-- liquibase formatted sql

-- changeset soukenka:init-1
INSERT INTO usr_user (version, username, password)
VALUES (0, 'admin', '$2a$12$gmmBtVBfg2chgpwIDkwmTOBGjh05gpvtnZt7HUu3IFIhgPaXKn8ey');

-- changeset soukenka:init-2
INSERT INTO usr_role (version, name)
VALUES (0, 'ROLE_VEH_CAR_READ');
-- changeset soukenka:init-3
INSERT INTO usr_role (version, name)
VALUES (0, 'ROLE_VEH_CAR_WRITE');

-- changeset soukenka:init-4
INSERT INTO usr_user_role (id_usr_role, id_usr_user)
VALUES (1, 1);
-- changeset soukenka:init-5
INSERT INTO usr_user_role (id_usr_role, id_usr_user)
VALUES (2, 1);
