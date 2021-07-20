DROP TABLE vote IF EXISTS ;
DROP TABLE menu_item IF EXISTS ;
DROP TABLE user_role IF EXISTS ;
DROP TABLE users IF EXISTS ;
DROP TABLE restaurant IF EXISTS ;
DROP SEQUENCE global_seq IF EXISTS;

CREATE SEQUENCE GLOBAL_SEQ AS INTEGER START WITH 100000;

CREATE TABLE users
(
    id               INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    name             VARCHAR(255)            NOT NULL,
    email            VARCHAR(255)            NOT NULL,
    password         VARCHAR(255)            NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON USERS (email);

CREATE TABLE user_role
(
    user_id INTEGER NOT NULL,
    role    VARCHAR(255),
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE
);

CREATE TABLE restaurant
(
    id          INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
CREATE UNIQUE INDEX restaurant_unique_name_idx ON restaurant (name);

CREATE TABLE menu_item
(
    id          INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    restaurant_id INTEGER NOT NULL,
    date          DATE    NOT NULL,
    name          VARCHAR(255) NOT NULL,
    price         INTEGER NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES RESTAURANT (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX menu_item_unique_restaurant_id_date_name_idx ON menu_item (restaurant_id, date, name);

CREATE TABLE vote
(
    id          INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    user_id       INTEGER NOT NULL,
    date          DATE    NOT NULL,
    restaurant_id INTEGER NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES RESTAURANT (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX vote_unique_date_user_id_idx ON vote (date, user_id);