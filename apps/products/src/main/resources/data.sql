CREATE TABLE IF NOT EXISTS carts
(
 id SERIAL PRIMARY KEY,
 total_bill FLOAT NOT NULL ,
 articles text DEFAULT NULL,
 paid boolean NOT NULL,
 payment_mode VARCHAR(100) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS products
(
 id SERIAL PRIMARY KEY,
 name VARCHAR(20) NOT NULL,
 img_url VARCHAR(100),
 description VARCHAR(255) NOT NULL,
 price INT NOT NULL,
 quantity INT NOT NULL
);

TRUNCATE carts, products;

INSERT INTO products (name, img_url, description, price, quantity) VALUES
('Tomate', 'tomateURL', 'Une bonne tomate rouge qui vient despagne.', 1.99, 72),
('Concombre', 'concombreURL', 'Le truc qui fait peur aux chats.', 1.32, 18),
('Avocat', 'avocatsURL', 'Ca coute une blinde.', 2.99, 27),
('Salade', 'saladeURL', 'Sur la tête.', 1.99, 14);