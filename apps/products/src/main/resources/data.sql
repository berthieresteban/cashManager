DROP TABLE IF EXISTS carts;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users
(
 username varchar(50) NOT NULL PRIMARY KEY,
 password varchar(100) NOT NULL,
 enabled boolean NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS user_roles (
  user_role_id SERIAL PRIMARY KEY,
  username varchar(20) NOT NULL,
  role varchar(20) NOT NULL,
  UNIQUE (username,role),
  FOREIGN KEY (username) REFERENCES users (username)
);

CREATE TABLE IF NOT EXISTS carts
(
 id SERIAL PRIMARY KEY,
 total_bill FLOAT NOT NULL ,
 articles text DEFAULT NULL,
 paid boolean NOT NULL,
 payment_mode VARCHAR(100) DEFAULT NULL,
 username text NOT NULL
);

CREATE TABLE IF NOT EXISTS products
(
 id SERIAL PRIMARY KEY,
 name VARCHAR(20) NOT NULL,
 img_url VARCHAR(100),
 description VARCHAR(255) NOT NULL,
 price FLOAT NOT NULL,
 quantity INT NOT NULL
);

TRUNCATE carts, products, users, user_roles;

INSERT INTO users (username, password, enabled) VALUES
('user-admin', '$2a$10$.55rTjh3gK2/9spanwVJvuxD1oLzSEzTcmuzC1cLHxJ9RtGi6CqZ6', true),
('user1', '$2a$10$XigVm4fz3pIZrraUjUo1iuX56FSGoL8JxdXsm.gleUslV4JXMhGme', true),
('user2', '$2a$10$ps1/H.RkGpkWycS7btTPpOT5ldZWceWhVb9L4Qow5RIww65O1K46y', true),
('user3', '$2a$10$mLmgbVEPrZNnpejPTONT8.fMXxN9ZDNU2ILzgW9KTlAzrwqhn5v2.', true);

INSERT INTO user_roles (username, role) VALUES
('user-admin', 'ROLE_ADMIN'),
('user1', 'ROLE_USER'),
('user2', 'ROLE_USER'),
('user3', 'ROLE_USER');

INSERT INTO products (name, img_url, description, price, quantity) VALUES
('Tomate', 'tomateURL', 'Une bonne tomate rouge qui vient despagne.', 1.99, 72),
('Concombre', 'concombreURL', 'Le truc qui fait peur aux chats.', 1.32, 18),
('Avocat', 'avocatsURL', 'Ca coute une blinde.', 2.99, 27),
('Salade', 'saladeURL', 'Sur la tête.', 1.99, 14);

INSERT INTO carts (total_bill, articles, paid, payment_mode, username) VALUES
(25.95, '[1, 2, 3]', false, NULL, 'user1'),
(23.95, '[1, 2]', true, 'CB', 'user2'),
(21.95, '[1, 3]', true, 'QRCODE', 'user3');