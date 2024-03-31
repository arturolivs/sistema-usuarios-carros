INSERT INTO app_user (first_name, last_name, email, birthday, login, password, phone, role)
VALUES ('John 1', 'Doe 1', 'john.doe1@example.com', '1991-01-01', 'johndoe1', 'password1', '12345678901', 'ROLE_USER'),
       ('John 2', 'Doe 2', 'john.doe2@example.com', '1992-01-01', 'johndoe2', 'password2', '12345678902', 'ROLE_USER'),
       ('John 3', 'Doe 3', 'john.doe3@example.com', '1993-01-01', 'johndoe3', 'password3', '12345678902', 'ROLE_USER'),
       ('John 4', 'Doe 4', 'john.doe4@example.com', '1994-01-01', 'johndoe4', 'password4', '12345678902', 'ROLE_USER'),
       ('John 5', 'Doe 5', 'john.doe5@example.com', '1995-01-01', 'johndoe5', 'password5', '12345678902', 'ROLE_USER');

INSERT INTO car (manufacture_year, license_plate, model, color, user_id)
VALUES (2010, 'ABC-1234', 'Toyota Corolla', 'Blue', 1),
       (2015, 'XYZ-5678', 'Honda Civic', 'Red', 1),
       (2018, 'LMN-9012', 'Ford Fusion', 'Silver', 1),
       (2020, 'OPQ-3456', 'Chevrolet Malibu', 'Black', 1);

INSERT INTO car (manufacture_year, license_plate, model, color, user_id)
VALUES (2011, 'RST-7890', 'Nissan Altima', 'White', 2),
       (2016, 'UVW-0123', 'Hyundai Elantra', 'Gray', 2),
       (2019, 'XYZ-7890', 'Kia Optima', 'Silver', 2),
       (2021, 'ABC-0123', 'Mazda 3', 'Blue', 2);

INSERT INTO car (manufacture_year, license_plate, model, color, user_id)
VALUES (2012, 'DEF-4567', 'Subaru Outback', 'Green', 3),
       (2017, 'GHI-8901', 'Volkswagen Jetta', 'Black', 3),
       (2020, 'JKL-2345', 'Audi A4', 'Silver', 3),
       (2022, 'MNO-6789', 'BMW 3 Series', 'White', 3);

INSERT INTO car (manufacture_year, license_plate, model, color, user_id)
VALUES (2013, 'PQR-9012', 'Mercedes-Benz C-Class', 'Black', 4),
       (2018, 'STU-3456', 'Lexus IS', 'Silver', 4),
       (2021, 'VWX-7890', 'Infiniti Q50', 'Gray', 4),
       (2023, 'YZA-1234', 'Acura TLX', 'White', 4);

INSERT INTO car (manufacture_year, license_plate, model, color, user_id)
VALUES (2014, 'BCD-2345', 'Cadillac XTS', 'Black', 5),
       (2019, 'EFG-6789', 'Lincoln Continental', 'Silver', 5),
       (2022, 'HIJ-0123', 'Buick Regal', 'Blue', 5),
       (2024, 'KLM-4567', 'GMC Acadia', 'White', 5);