CONNECT 'jdbc:mysql://localhost:3306/tourOp;create=true;user=testuser;password=testpass';

CREATE TABLE roles(
  id INTEGER PRIMARY KEY,
  name VARCHAR(10) NOT NULL UNIQUE
)

INSERT INTO roles VALUES(0, 'admin');
INSERT INTO roles VALUES(1, 'client');
INSERT INTO roles VALUES(2, 'manager');

CREATE TABLE users(
  id INTEGER NOT NULL PRIMARY KEY,
  login VARCHAR(25) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  role_id INTEGER NOT NULL REFERENCES roles(id)
);

INSERT INTO users VALUES(DEFAULT, 'admin', 'admin', 'Ivan', 'Ivanov', 0);
INSERT INTO users VALUES(DEFAULT, 'client', 'client', 'Petr', 'Petrov', 1);
INSERT INTO users VALUES(DEFAULT, 'manager', 'manager', 'Дмитрий', 'Дмитриев', 2);


CREATE TABLE statuses(
  id INTEGER PRIMARY KEY,
  name VARCHAR(10) NOT NULL UNIQUE
);

INSERT INTO statuses VALUES(0, 'register');
INSERT INTO statuses VALUES(1, 'paid');
INSERT INTO statuses VALUES(2, 'canceled');

