CREATE TABLE menu_items (
                            id            BIGSERIAL PRIMARY KEY,
                            restaurant_id BIGINT        NOT NULL REFERENCES restaurants(id),
                            name          VARCHAR(255)  NOT NULL,
                            description   TEXT,
                            price         NUMERIC(10,2) NOT NULL,
                            available     BOOLEAN       NOT NULL DEFAULT TRUE
);

CREATE TABLE users (
                       id         BIGSERIAL    PRIMARY KEY,
                       email      VARCHAR(255) NOT NULL UNIQUE,
                       password   VARCHAR(255) NOT NULL,
                       name       VARCHAR(255) NOT NULL,
                       role       VARCHAR(50)  NOT NULL,
                       active     BOOLEAN      NOT NULL DEFAULT TRUE,
                       created_at TIMESTAMP    NOT NULL DEFAULT NOW()
);