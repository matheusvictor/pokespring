CREATE TABLE IF NOT EXISTS users (

    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password_encoded VARCHAR(50) NOT NULL
);

INSERT INTO users (id, username, password_encoded) VALUES (1, 'admin', 'admin');