CREATE TABLE users_subscriptions
(
    user_id         BIGINT NOT NULL,
    subscription_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, subscription_id),
    CONSTRAINT fk_user_sub_user FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_user_sub_subscription FOREIGN KEY (subscription_id) REFERENCES subscriptions (id)
);