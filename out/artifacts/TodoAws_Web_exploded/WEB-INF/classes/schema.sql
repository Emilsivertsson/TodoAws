CREATE TABLE if not exists todos
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    title         VARCHAR(255)          NOT NULL,
    `description` VARCHAR(255)          NOT NULL,
    completed     BIT(1)                NOT NULL,
    CONSTRAINT pk_todos PRIMARY KEY (id)
);