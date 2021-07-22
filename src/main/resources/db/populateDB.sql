DELETE
FROM user_role;
DELETE
FROM users;
DELETE
FROM menu_item;
DELETE
FROM restaurant;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('User1', 'user1@mail.ru', '{noop}password'),
       ('User2', 'user2@mail.ru', '{noop}password'),
       ('User3', 'user3@mail.ru', '{noop}password');

INSERT INTO user_role (role, user_id)
VALUES ('ADMIN', 100000),
       ('USER', 100000),
       ('USER', 100001),
       ('USER', 100002),
       ('USER', 100003);

INSERT INTO restaurant (name)
VALUES ('Хачапури и вино'),
       ('Munhell'),
       ('Kwakinn');

INSERT INTO menu_item (restaurant_id, date, name, price)
VALUES (100004, current_timestamp, 'салат', 25000),
       (100004, current_timestamp, 'хачапури', 28000),
       (100004, current_timestamp, 'лобио', 20000),
       (100004, '2021-03-24', 'пхали', 5000),
       (100004, '2021-03-24', 'вино', 26000),
       (100005, '2021-03-24', 'салат', 23050),
       (100005, '2021-03-24', 'ребрышки BBQ', 55050),
       (100005, current_timestamp, 'стейк', 75050),
       (100005, '2021-03-24', 'шашлык', 45000),
       (100005, '2021-03-24', 'пиво', 29000),
       (100006, '2021-03-24', 'салат', 20000),
       (100006, '2021-03-24', 'рулька', 65000),
       (100006, current_timestamp, 'курица', 45000),
       (100006, '2021-03-24', 'стейк рибай', 95000),
       (100006, '2021-03-24', 'пиво', 35000);

INSERT INTO vote (user_id, date, restaurant_id)
VALUES (100000, '2021-05-01', 100004),
       (100001, current_timestamp, 100005),
       (100002, current_timestamp, 100005),
       (100003, current_timestamp, 100006),
       (100000, '2021-04-6', 100005),
       (100001, '2021-04-6', 100006);