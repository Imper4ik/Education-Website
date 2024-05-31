CREATE TABLE IF NOT EXISTS "person_roles"
(
    "person_id"   BIGINT REFERENCES person("id") NOT NULL,
    "roles" VARCHAR(255)
);