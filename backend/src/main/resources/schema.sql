CREATE TABLE app_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    birthday DATE NOT NULL,
    login VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    role VARCHAR(255)
);

CREATE TABLE car (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    manufacture_year INT NOT NULL,
    license_plate VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    color VARCHAR(255) NOT NULL,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES app_user(id)
);
