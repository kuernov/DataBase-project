--liquibase formatted sql
--changeset malyjasiak:2

-- create table orders
-- (
--     id serial primary key,
--     date_created date,
--     status varchar(255)
-- );
--
-- create table order_product
-- (
--     quantity   integer not null,
--     product_id integer not null
--             references products,
--     order_id   integer not null
--             references orders,
--     primary key (order_id, product_id),
--
-- );