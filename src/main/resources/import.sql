-- noinspection SqlNoDataSourceInspectionForFile
TRUNCATE persons CASCADE;
INSERT INTO persons(id, email, name, surname, token) VALUES(1,'jakub.chalupa@mensa.cz', 'Jakub', 'Chalupa','a');
INSERT INTO persons(id, email, name, surname, token) VALUES(2,'aa@bb.cz', 'Tomas', 'Fuk','b');
INSERT INTO persons(id, email, name, surname, token) VALUES(3,'bb@cc.cz', 'Jiri', 'Amend','c');
INSERT INTO persons(id, email, name, surname, token) VALUES(4,'cc@dd.cz', 'Lukas', 'Ignis','d');
INSERT INTO persons(id, email, name, surname, token) VALUES(5,'dd@ee.cz', 'Petr', 'Kuchar','e');
ALTER SEQUENCE persons_id_seq RESTART WITH 30;

TRUNCATE roles CASCADE;
INSERT INTO roles(type) VALUES ('ADMIN');
INSERT INTO roles(type) VALUES ('USER');

TRUNCATE person_role CASCADE;
INSERT INTO person_role(person_id, role_id) VALUES (1, 'ADMIN');
INSERT INTO person_role(person_id, role_id) VALUES (1, 'USER');
INSERT INTO person_role(person_id, role_id) VALUES (2, 'ADMIN');
INSERT INTO person_role(person_id, role_id) VALUES (3, 'ADMIN');
INSERT INTO person_role(person_id, role_id) VALUES (4, 'USER');
INSERT INTO person_role(person_id, role_id) VALUES (5, 'USER');