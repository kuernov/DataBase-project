--liquibase formatted sql
--changeset malyjasiak:1
create table categories(
                           id serial not null primary key,
                           name varchar not null,
                           description varchar not null
);


create table products(
                           id serial not null primary key,
                           name varchar not null,
                           description varchar not null,
                           price integer not null,
                           category_id INTEGER,
                           FOREIGN KEY (category_id) REFERENCES categories(id)
);
