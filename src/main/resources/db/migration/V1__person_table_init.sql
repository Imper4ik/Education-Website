CREATE TABLE IF NOT EXISTS "person"
(
    "id"       BIGSERIAL PRIMARY KEY,
    "username" VARCHAR(255),
    "email"    VARCHAR(255),
    "password" VARCHAR(255)
);