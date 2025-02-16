-- V1__init.sql
CREATE TABLE home_inv_user
(
    id         BIGSERIAL PRIMARY KEY,
    username   VARCHAR(50)  NOT NULL UNIQUE,
    email      VARCHAR(100) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table for containers
CREATE TABLE containers
(
    id       BIGSERIAL PRIMARY KEY,
    name     VARCHAR(100) NOT NULL,
    color    VARCHAR(50),
    location VARCHAR(100)
);

-- Table for items
CREATE TABLE inventory_item
(
    id           BIGSERIAL PRIMARY KEY,
    description  TEXT,
    quantity     INTEGER        NOT NULL CHECK (quantity > 0),
    color        VARCHAR(50),
    price        NUMERIC(10, 2) NOT NULL CHECK (price >= 0),
    owner_id     BIGSERIAL,
    container_id BIGSERIAL,
    category     VARCHAR(50),
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_owner FOREIGN KEY (owner_id) REFERENCES home_inv_user (id) ON DELETE SET NULL,
    CONSTRAINT fk_container FOREIGN KEY (container_id) REFERENCES containers (id) ON DELETE SET NULL
);

-- Table for tags
CREATE TABLE tags
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- Join table for the many-to-many relationship between items and tags
CREATE TABLE item_tags
(
    item_id BIGSERIAL NOT NULL,
    tag_id  BIGSERIAL NOT NULL,
    PRIMARY KEY (item_id, tag_id),
    CONSTRAINT fk_item FOREIGN KEY (item_id) REFERENCES inventory_item (id) ON DELETE CASCADE,
    CONSTRAINT fk_tag FOREIGN KEY (tag_id) REFERENCES tags (id) ON DELETE CASCADE
);