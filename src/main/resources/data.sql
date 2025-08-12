INSERT INTO product (name, price, description, manufactureDate) VALUES ('Laptop Pro 15', 45900.00, 'High performance laptop for professionals', '2020-02-01');
INSERT INTO product (name, price, description, manufactureDate) VALUES ('Wireless Mouse', 790.00, 'Ergonomic wireless mouse', '2021-05-15');
INSERT INTO product (name, price, description, manufactureDate) VALUES ('Mechanical Keyboard', 2890.00, 'RGB backlit mechanical keyboard', '2022-08-10');
INSERT INTO product (name, price, description, manufactureDate) VALUES ('Smartphone X', 32900.00, 'Flagship smartphone with OLED display', '2023-01-20');
INSERT INTO product (name, price, description, manufactureDate) VALUES ('Noise Cancelling Headphones', 5990.00, 'Over-ear headphones with active noise cancellation', '2019-11-05');

INSERT INTO CustomerTier (name) VALUES ('Bronze');
INSERT INTO CustomerTier (name) VALUES ('Silver');
INSERT INTO CustomerTier (name) VALUES ('Gold');
INSERT INTO CustomerTier (name) VALUES ('Platinum');

INSERT INTO customer (name, address, email, phone, birthDay, customerTier_id) VALUES ('Somchai Prasert', '123 Sukhumvit Rd, Bangkok', 'somchai@example.com', '0812345678', '1995-07-12', 1);
INSERT INTO customer (name, address, email, phone, birthDay, customerTier_id) VALUES ('Suda Wong', '45 Changklan Rd, Chiang Mai', 'suda@example.com', '0898765432', '1998-03-05', 2);
INSERT INTO customer (name, address, email, phone, birthDay, customerTier_id) VALUES ('Anan Phongchai', '78 Mittraphap Rd, Khon Kaen', 'anan@example.com', '0851239876', '1988-12-20', 3);
INSERT INTO customer (name, address, email, phone, birthDay, customerTier_id) VALUES ('Kanya Lek', '56 Prachuap Rd, Hua Hin', 'kanya@example.com', '0825551234', '1992-09-18', 4);
INSERT INTO customer (name, address, email, phone, birthDay, customerTier_id) VALUES ('Prasit Jinda', '99 Ratchada Rd, Bangkok', 'prasit@example.com', '0867894561', '2000-06-25', 1);
