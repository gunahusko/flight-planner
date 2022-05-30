--liquibase formatted sql

--changeset guna:1

CREATE TABLE flight
(
    flight_id      SERIAL PRIMARY KEY,
    from_id        VARCHAR(255),
    to_id          VARCHAR(255),
    carrier        VARCHAR(255),
    departure_time TIMESTAMP,
    arrival_time   TIMESTAMP,
    CONSTRAINT flight_from_id_fkey FOREIGN KEY (from_id) REFERENCES airport (airport_id),
    CONSTRAINT flight_to_id_fkey FOREIGN KEY (to_id) REFERENCES airport (airport_id)
);