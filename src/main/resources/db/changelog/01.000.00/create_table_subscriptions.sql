CREATE TABLE subscriptions (
                               id BIGSERIAL PRIMARY KEY,
                               title VARCHAR(255) NOT NULL,
                               description TEXT,
                               price NUMERIC(19, 2) NOT NULL
);