CREATE TABLE IF NOT EXISTS products (
                          id SERIAL PRIMARY KEY,
                          product_type VARCHAR(20) NOT NULL CHECK (product_type IN ('ACCOUNT', 'CARD')),
                          user_id BIGINT NOT NULL,
                          CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Тестовые данные
INSERT INTO products (product_type, user_id)
VALUES ('ACCOUNT', 1),
       ('CARD', 2);
