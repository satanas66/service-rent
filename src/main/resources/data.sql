CREATE TABLE tbl_car (id BIGINT AUTO_INCREMENT  PRIMARY KEY, type VARCHAR(250), stock INTEGER, price DOUBLE, points INTEGER);
CREATE TABLE tbl_customer_loyalty (id BIGINT AUTO_INCREMENT  PRIMARY KEY, name VARCHAR(250), dni VARCHAR(250), points INTEGER);
CREATE TABLE tbl_rent (id BIGINT AUTO_INCREMENT  PRIMARY KEY, start_date TIMESTAMP, end_date TIMESTAMP, devolution_date TIMESTAMP, payable DOUBLE, surcharge DOUBLE, total DOUBLE, number_rented_cars INTEGER, car_id BIGINT, customer_loyalty_id BIGINT);

INSERT INTO tbl_car (id, type, stock, price, points) VALUES (1, 'premium', 10, 300, 5);
INSERT INTO tbl_car (id, type, stock, price, points) VALUES (2, 'suv', 10, 150, 3);
INSERT INTO tbl_car (id, type, stock, price, points) VALUES (3, 'small', 10, 50, 1);

INSERT INTO tbl_customer_loyalty (id, points, name, dni) VALUES (21, 5, 'Francisco Valenzuela', '77926228A');
INSERT INTO tbl_customer_loyalty (id, points, name, dni) VALUES (22, 3, 'Angel Arévalo', '07926228B');
INSERT INTO tbl_customer_loyalty (id, points, name, dni) VALUES (23, 2, 'Daniel Rivas', '17926228C');

INSERT INTO tbl_rent (id, start_date, end_date, devolution_date, payable, surcharge, total, number_rented_cars, car_id, customer_loyalty_id)
VALUES (20, '2022-10-24', '2022-10-25', '2022-10-25', 300.0, 0.0, 300.0, 1, 1, 21);

INSERT INTO tbl_rent (id, start_date, end_date, devolution_date, payable, surcharge, total, number_rented_cars, car_id, customer_loyalty_id)
VALUES (21, '2022-10-24', '2022-10-26', '2022-10-26', 300.0, 0.0, 300.0, 1, 2, 22);

INSERT INTO tbl_rent (id, start_date, end_date, devolution_date, payable, surcharge, total, number_rented_cars, car_id, customer_loyalty_id)
VALUES (22, '2022-10-24', '2022-10-25', '2022-10-25', 50.0, 0.0, 50.0, 1, 3, 23);

INSERT INTO tbl_rent (id, start_date, end_date, devolution_date, payable, surcharge, total, number_rented_cars, car_id, customer_loyalty_id)
VALUES (23, '2022-10-26', '2022-10-29', '2022-10-29', 150.0, 0.0, 150.0, 1, 3, 23);