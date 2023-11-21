--liquibase formatted sql
--changeset malyjasiak:1
create table clients(
                        id serial not null primary key,
                        name varchar not null,
                        lastName varchar not null,
                        email varchar not null,
                        phone varchar not null,
                        address varchar not null
);

