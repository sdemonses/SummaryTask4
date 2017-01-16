/*CONNECT 'jdbc:mysql://localhost:3306/tourOp;create=true;user=testuser;password=testpass';*/

SET NAMES 'utf8' COLLATE 'utf8_general_ci';
SET CHARACTER SET UTF8;

DROP TABLE roles;
DROP TABLE statuses;
DROP TABLE cities;
DROP TABLE countries;
DROP TABLE hotels;
DROP TABLE tours;
DROP TABLE type;
DROP TABLE users;

CREATE TABLE roles (
  id   INTEGER PRIMARY KEY,
  name VARCHAR(10) NOT NULL UNIQUE
);

INSERT INTO roles VALUES (0, 'admin');
INSERT INTO roles VALUES (1, 'client');
INSERT INTO roles VALUES (2, 'manager');

CREATE TABLE users (
  id         INTEGER      PRIMARY KEY AUTO_INCREMENT,
  login      VARCHAR(25)  NOT NULL UNIQUE,
  email      VARCHAR(40)  NOT NULL UNIQUE,
  password   VARCHAR(255) NOT NULL,
  first_name VARCHAR(50)  NOT NULL,
  last_name  VARCHAR(50)  NOT NULL,
  role_id    INTEGER      NOT NULL REFERENCES roles (id)
);

INSERT INTO users VALUES (DEFAULT, 'admin', 'sdemonses@gmail.com', 'admin', 'Ivan', 'Ivanov', 0);
INSERT INTO users VALUES (DEFAULT, 'client', 'sdemones@gmail.com',  'client', 'Petr', 'Petrov', 1);
INSERT INTO users VALUES (DEFAULT, 'manager', 'sdemoses@gmail.com', 'manager', 'Дмитрий', 'Дмитриев', 2);


CREATE TABLE countries (
  id      INTEGER PRIMARY KEY AUTO_INCREMENT,
  name_ru VARCHAR(30) NOT NULL,
  name_en VARCHAR(30) NOT NULL
);

CREATE TABLE cities (
  id         INTEGER PRIMARY KEY AUTO_INCREMENT,
  country_id INTEGER     NOT NULL REFERENCES countries (id),
  name_ru    VARCHAR(30) NOT NULL,
  name_en    VARCHAR(30) NOT NULL
);


CREATE TABLE hotels (
  id             INTEGER PRIMARY KEY AUTO_INCREMENT,
  city_id        INTEGER     NOT NULL REFERENCES cities (id),
  name           VARCHAR(10) NOT NULL,
  stars          INTEGER     NOT NULL,
  description_ru VARCHAR(255),
  description_en VARCHAR(255)
);

CREATE TABLE statuses (
  id   INTEGER PRIMARY KEY,
  name VARCHAR(10) NOT NULL UNIQUE
);

INSERT INTO statuses VALUES (0, 'empty');
INSERT INTO statuses VALUES (1, 'hot');
INSERT INTO statuses VALUES (2, 'register');
INSERT INTO statuses VALUES (3, 'paid');
INSERT INTO statuses VALUES (4, 'canceled');

CREATE TABLE type (
  id   INTEGER PRIMARY KEY,
  name VARCHAR(10) NOT NULL UNIQUE
);

INSERT INTO type VALUES (0, 'excursion');
INSERT INTO type VALUES (1, 'shopping');
INSERT INTO type VALUES (2, 'rest');

CREATE TABLE tours (
  id        INTEGER PRIMARY KEY AUTO_INCREMENT,
  name      VARCHAR(10) NOT NULL,
  county_id INTEGER     NOT NULL REFERENCES countries (id),
  hotel_id  INTEGER     NOT NULL REFERENCES hotels (id),
  type_id   INTEGER     NOT NULL REFERENCES type (id),
  status_id INTEGER     NOT NULL REFERENCES statuses (id),
  cost      INTEGER,
  person    INTEGER
);





