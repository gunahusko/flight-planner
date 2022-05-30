--liquibase formatted sql

--changeset guna:1

CREATE TABLE airport
(
    airport_id VARCHAR(255) PRIMARY KEY,
    country    VARCHAR(255),
    city       VARCHAR(255)
);