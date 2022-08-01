CREATE DATABASE  IF NOT EXISTS `inflearn`;
USE `inflearn`;

DROP TABLE IF EXISTS `inflearn`;
drop table if exists member CASCADE;
create table member
(
    id bigint NOT NULL AUTO_INCREMENT,
    name varchar(255),
    primary key (id)
);