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

TRUNCATE incident CASCADE;
INSERT INTO incident(address, title, insertedtime, latitude, longitude, state) VALUES ('Štěpánská 795/65, 110 00 Praha-Praha 1, Česká republika', 'Díra na ulici', '2014-10-22 11:41:04.777', 50.081272, 14.426791, 'IN_PROGRESS');
INSERT INTO incident(address, title, insertedtime, latitude, longitude, state) VALUES ('Nuselská 867/88, 140 00 Praha-Praha 4, Česká republika', 'zlomená značka', '2014-10-24 17:31:04.777', 50.058651, 14.448895, 'IN_PROGRESS');
INSERT INTO incident(address, title, insertedtime, latitude, longitude, state) VALUES ('Rumunská 1720/12, 120 00 Praha-Praha 2, Česká republika', 'Bordel u popelnic', '2014-10-20 09:41:04.777', 50.073835, 14.431177, 'NEW');
INSERT INTO incident(address, title, insertedtime, latitude, longitude, state) VALUES ('náměstí Svatopluka Čecha 1368/11, 101 00 Praha-Praha 10, Česká republika', 'Bezdomovci tu pijí alkohol', '2014-10-20 06:11:04.777', 50.068898, 14.460467, 'NEW');

INSERT INTO incident(address, title, description, insertedtime, latitude, longitude, state) VALUES ('Stará esta s18', 'Porouchaná sušučka', 'fakt rozbité', '2014-10-22 12:41:04.777', 50.081382, 14.427800, 'IN_PROGRESS');
INSERT INTO incident(address, title, description, insertedtime, latitude, longitude, state) VALUES ('Na poříčí hošánci', 'vyfocené kuřátko', 'fakt rozbité', '2014-10-22 13:41:04.777', 50.081272, 14.428791, 'IN_PROGRESS');
INSERT INTO incident(address, title, description, insertedtime, latitude, longitude, state) VALUES ('U suchdola 5', 'Nějaká bezejmenná škoda', 'fakt rozbité', '2014-10-22 14:41:04.777', 50.081492, 14.426810, 'IN_PROGRESS');
INSERT INTO incident(address, title, description, insertedtime, latitude, longitude, state) VALUES ('Borovského ulice 154', 'Hokejisti zničili stadion', 'fakt rozbité', '2014-10-22 15:41:04.777', 50.081500, 14.429820, 'IN_PROGRESS');
INSERT INTO incident(address, title, description, insertedtime, latitude, longitude, state) VALUES ('Adresa na kraji města', 'Ptačí výkaly na ulici', 'fakt rozbité', '2014-10-22 16:41:04.777', 50.081610, 14.430830, 'IN_PROGRESS');
INSERT INTO incident(address, title, description, insertedtime, latitude, longitude, state) VALUES ('Bitevního pole 16', 'Černá skládka v centru města!!!', 'fakt rozbité', '2014-10-22 17:41:04.777', 50.081720, 14.431840, 'IN_PROGRESS');
INSERT INTO incident(address, title, description, insertedtime, latitude, longitude, state) VALUES ('ADresa na konci svězta', 'Nejaký trouba vyndal kostky z chodníku', 'fakt rozbité', '2014-10-22 18:41:04.777', 50.081830, 14.432850, 'NEW');
INSERT INTO incident(address, title, description, insertedtime, latitude, longitude, state) VALUES ('Příčná ulice 19', 'Jen si z vás dělám srandu saláti', 'fakt rozbité', '2014-10-22 19:41:04.777', 50.081940, 14.433860, 'NEW');
INSERT INTO incident(address, title, description, insertedtime, latitude, longitude, state) VALUES ('U Olivandera 1', 'Rozbitá výloha souukromého obchodu', 'fakt rozbité', '2014-10-22 20:41:04.777', 50.082000, 14.434870, 'NEW');
INSERT INTO incident(address, title, description, insertedtime, latitude, longitude, state) VALUES ('Nádraží vršovice', 'Skákal pes přes oves', 'fakt rozbité', '2014-10-22 21:41:04.777', 50.082100, 14.423580, 'NEW');
INSERT INTO incident(address, title, description, insertedtime, latitude, longitude, state) VALUES ('I.P.Pavlova bašta', 'Tahle aplikace je strašný brak', 'fakt rozbité', '2014-10-22 22:41:04.777', 50.082200, 14.436890, 'NEW');
INSERT INTO incident(address, title, description, insertedtime, latitude, longitude, state) VALUES ('náměstí u tří lvů', 'Rozpustili se voskové figuríny', 'fakt rozbité', '2014-10-22 23:41:04.777', 50.082300, 14.437900, 'NEW');
INSERT INTO incident(address, title, description, insertedtime, latitude, longitude, state) VALUES ('náměstí Svatopluka Čecha 1368/11, 101 00 Praha-Praha 10, Česká republika', 'Lavička je uplně na kaši', 'Lavička je uplně na kaši, nechápu že to ještě nikdo nevyřešil. Chodím kolem toho každý den je to fakt hrůza. Doufám že to vyřešíte co nejdříve', '2014-10-23 01:41:04.777', 50.0812390, 14.426910, 'NEW');

INSERT INTO incident(address, title, insertedtime, latitude, longitude, state) VALUES ('Štěpánská 795/65, 110 00 Praha-Praha 1, Česká republika', 'Díra na ulici', '2014-10-22 11:41:04.777', 50.081272, 14.426791, 'INVALID');
INSERT INTO incident(address, title, insertedtime, latitude, longitude, state) VALUES ('Nuselská 867/88, 140 00 Praha-Praha 4, Česká republika', 'zlomená značka', '2014-10-24 17:31:04.777', 50.058651, 14.448895, 'INVALID');
INSERT INTO incident(address, title, insertedtime, latitude, longitude, state) VALUES ('Rumunská 1720/12, 120 00 Praha-Praha 2, Česká republika', 'Bordel u popelnic', '2014-10-20 09:41:04.777', 50.073835, 14.431177, 'INVALID');
INSERT INTO incident(address, title, insertedtime, latitude, longitude, state) VALUES ('náměstí Svatopluka Čecha 1368/11, 101 00 Praha-Praha 10, Česká republika', 'Bezdomovci tu pijí alkohol', '2014-10-20 06:11:04.777', 50.068898, 14.460467, 'INVALID');
INSERT INTO incident(address, title, insertedtime, latitude, longitude, state) VALUES ('Stará esta s18', 'Porouchaná sušučka', '2014-10-22 12:41:04.777', 50.081382, 14.427800, 'INVALID');
INSERT INTO incident(address, title, insertedtime, latitude, longitude, state) VALUES ('Na poříčí hošánci', 'vyfocené kuřátko', '2014-10-22 13:41:04.777', 50.081272, 14.428791, 'INVALID');
INSERT INTO incident(address, title, insertedtime, latitude, longitude, state) VALUES ('U suchdola 5', 'Nějaká bezejmenná škoda', '2014-10-22 14:41:04.777', 50.081492, 14.426810, 'INVALID');

TRUNCATE message CASCADE;
INSERT INTO message(insertedtime, text, person_id, incident_id) VALUES('2014-10-25 19:30:04.777', 'Děkujeme za nahlášení, koukneme se na to.', 2, 1);
INSERT INTO message(insertedtime, text, person_id, incident_id) VALUES('2014-10-26 18:30:04.777', 'Bylo vyřešeno snad.', 2, 1);
INSERT INTO message(insertedtime, text, person_id, incident_id) VALUES('2014-10-25 20:32:04.777', 'Oprava bylo to vyřešeno až nyní. Omlouváme se za komplikace', 3, 1);
INSERT INTO message(insertedtime, text, person_id, incident_id) VALUES('2014-10-25 19:30:04.777', 'Děkujeme za nahlášení této vážné škody, koukneme se na to.', 2, 2);
INSERT INTO message(insertedtime, text, person_id, incident_id) VALUES('2014-10-26 18:30:04.777', 'Je to oříšek, lavička asi nepatří městu', 2, 2);
INSERT INTO message(insertedtime, text, person_id, incident_id) VALUES('2014-10-25 20:32:04.777', 'Oprava bylo to vyřešeno až nyní. Omlouváme se za komplikace', 3, 2);

TRUNCATE comment CASCADE;
INSERT INTO comment(insertedtime, text, person_id, incident_id) VALUES('2014-10-25 21:10:04.777', 'Ta značka je OK, kdoví kdo to nahlašoval', 2, 1);
INSERT INTO comment(insertedtime, text, person_id, incident_id) VALUES('2014-10-26 15:10:04.777', 'Zavírám to a řeknu že je to vyřešené', 1, 1);
INSERT INTO comment(insertedtime, text, person_id, incident_id) VALUES('2014-10-25 15:49:04.777', 'Uff tohle máme za trest', 3, 5);
