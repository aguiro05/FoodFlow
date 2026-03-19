CREATE TABLE orders (
                        id               BIGSERIAL     PRIMARY KEY,
                        customer_id      BIGINT        NOT NULL REFERENCES users(id),
                        restaurant_id    BIGINT        NOT NULL REFERENCES restaurants(id),
                        status           VARCHAR(50)   NOT NULL DEFAULT 'PENDING',
                        total_amount     NUMERIC(10,2) NOT NULL,
                        delivery_address VARCHAR(255)  NOT NULL,
                        created_at       TIMESTAMP     NOT NULL DEFAULT NOW(),
                        updated_at       TIMESTAMP
);

CREATE TABLE order_items (
                             id           BIGSERIAL     PRIMARY KEY,
                             order_id     BIGINT        NOT NULL REFERENCES orders(id),
                             menu_item_id BIGINT        NOT NULL REFERENCES menu_items(id),
                             quantity     INTEGER       NOT NULL,
                             unit_price   NUMERIC(10,2) NOT NULL
);