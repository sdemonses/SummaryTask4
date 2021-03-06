/*CONNECT 'jdbc:mysql://localhost:3306/tourOp;create=true;user=testuser;password=testpass';*/

SET NAMES 'utf8'
COLLATE 'utf8_general_ci';
SET CHARACTER SET UTF8;

DROP TABLE roles;
DROP TABLE statuses;
DROP TABLE cities;
DROP TABLE countries;
DROP TABLE hotels;
DROP TABLE tours;
DROP TABLE type;
DROP TABLE userStatus;
DROP TABLE orders;
DROP TABLE users;

CREATE TABLE roles (
  id   INTEGER PRIMARY KEY,
  name VARCHAR(10) NOT NULL UNIQUE
);

INSERT INTO roles VALUES (0, 'admin');
INSERT INTO roles VALUES (1, 'client');
INSERT INTO roles VALUES (2, 'manager');

CREATE TABLE users (
  id            INTEGER PRIMARY KEY AUTO_INCREMENT,
  login         VARCHAR(25)  NOT NULL UNIQUE,
  email         VARCHAR(40)  NOT NULL,
  password      VARCHAR(255) NOT NULL,
  first_name    VARCHAR(50),
  last_name     VARCHAR(50),
  userStatus_id INTEGER      NOT NULL REFERENCES userStatus (id),
  role_id       INTEGER      NOT NULL REFERENCES roles (id)
);

INSERT INTO users VALUES (DEFAULT, 'admin', 'sdemonses@gmail.com', '21232f297a57a5a743894a0e4a801fc3', 'Ivan', 'Ivanov',1, 0);
INSERT INTO users VALUES (DEFAULT, 'client', 'sdemones@gmail.com', '62608e08adc29a8d6dbc9754e659f125', 'Petr', 'Petrov',1, 1);
INSERT INTO users VALUES (DEFAULT, 'manager', 'sdemoses@gmail.com', '1d0258c2440a8d19e716292b231e3190', 'Дмитрий', 'Дмитриев',1, 2);

CREATE TABLE userStatus (
  id   INTEGER PRIMARY KEY,
  name VARCHAR(15) NOT NULL
);

INSERT INTO userStatus VALUES (0, 'ban');
INSERT INTO userStatus VALUES (1, 'empty');
INSERT INTO userStatus VALUES (2, 'noConfirmed');

CREATE TABLE countries (
  id   INTEGER PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);

INSERT INTO countries VALUES (1, 'Egypt');
INSERT INTO countries VALUES (2, 'Turkey');
INSERT INTO countries VALUES (3, 'Maldives');
INSERT INTO countries VALUES (4, 'Morocco');
INSERT INTO countries VALUES (5, 'Thailand');

CREATE TABLE cities (
  id         INTEGER PRIMARY KEY AUTO_INCREMENT,
  country_id INTEGER     NOT NULL REFERENCES countries (id),
  name       VARCHAR(50) NOT NULL
);


CREATE TABLE hotels (
  id          INTEGER PRIMARY KEY AUTO_INCREMENT,
  city_id     INTEGER     NOT NULL REFERENCES cities (id),
  name        VARCHAR(50) NOT NULL,
  stars       INTEGER     NOT NULL,
  description VARCHAR(255)
);

INSERT INTO hotels VALUES (1, 1, 'The tree corners rihana resort', 4, 'Nice hotel');
INSERT INTO hotels VALUES (2, 2, 'Hilton Sharm dreams resort', 3, 'This hotel does not issue slippers');
INSERT INTO hotels VALUES (3, 3, 'Adora golf resort hotel', 5, 'Free Wi-Fi');
INSERT INTO hotels VALUES (4, 4, 'Sozer Hotel', 4, 'Free fitness');
INSERT INTO hotels VALUES (5, 5, 'Kurumba Maldives', 5, 'Party all night');
INSERT INTO hotels VALUES (6, 6, 'Riu palace tikida agair', 3, 'Broken beds');
INSERT INTO hotels VALUES (7, 7, 'Sunshine one pattaya', 2, 'cockroaches on the tables');


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
  name      VARCHAR(40) NOT NULL,
  duration  INTEGER,
  hotel_id  INTEGER     NOT NULL REFERENCES hotels (id),
  type_id   INTEGER     NOT NULL REFERENCES type (id),
  status_id INTEGER     NOT NULL REFERENCES statuses (id),
  cost      INTEGER,
  person    INTEGER
);

INSERT INTO cities VALUES (1, 1, 'Hurghada');
INSERT INTO cities VALUES (2, 1, 'Sharm El Sheikh');
INSERT INTO cities VALUES (3, 2, 'Antalya');
INSERT INTO cities VALUES (4, 2, 'Bodrum');
INSERT INTO cities VALUES (5, 3, 'Male');
INSERT INTO cities VALUES (6, 4, 'Casablanca');
INSERT INTO cities VALUES (7, 5, 'Bangkok');

INSERT INTO tours VALUES (DEFAULT, 'Excursion Hurghada', 6, 1, 0, 0, 250, 2);
INSERT INTO tours VALUES (DEFAULT, 'Rest Sharm', 5, 2, 2, 0, 250, 2);
INSERT INTO tours VALUES (DEFAULT, 'Rest Antalya', 5, 3, 2, 0, 250, 2);
INSERT INTO tours VALUES (DEFAULT, 'Shopping Bodrum', 5, 4, 1, 0, 250, 2);


CREATE TABLE orders (
  id           INTEGER PRIMARY KEY AUTO_INCREMENT,
  user_id      INTEGER NOT NULL,
  status_id    INTEGER NOT NULL,
  tour_id INTEGER NOT NULL,
  FOREIGN KEY (tour_id) REFERENCES tours(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  maxValueSale INTEGER,
  step         INTEGER
);





