--liquibase formatted sql
--changeset malyjasiak:1
create table products(
                           id serial not null primary key,
                           name varchar not null,
                           description varchar not null,
                           price integer not null
);
