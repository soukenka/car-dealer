-- liquibase formatted sql

-- changeset soukenka:1746698449742-1
-- comment: Create car table
CREATE TABLE veh_car
(
    id_veh_car   BIGINT AUTO_INCREMENT NOT NULL,
    created_date datetime              NOT NULL,
    version      BIGINT DEFAULT 0      NOT NULL,
    name         VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_veh_car PRIMARY KEY (id_veh_car)
);
-- rollback DROP TABLE veh_car;

-- changeset soukenka:1746698449742-2
-- comment: Add unique constraint on car name
ALTER TABLE veh_car
    ADD CONSTRAINT uc_veh_car_name UNIQUE (name);
-- rollback ALTER TABLE veh_car DROP CONSTRAINT uc_veh_car_name;

