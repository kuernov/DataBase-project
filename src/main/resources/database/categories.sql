--liquibase formatted sql
--changeset malyjasiak:1
create table categories(
                         id serial not null primary key,
                         name varchar not null,
                         description varchar not null
);
