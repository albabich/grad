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
VALUES ('Admin', 'admin@gmail.com', 'admin'),
       ('User1', 'user1@mail.ru', 'password1'),
       ('User2', 'user2@mail.ru', 'password2'),
       ('User3', 'user3@mail.ru', 'password3');

INSERT INTO user_role (role, user_id)
VALUES ('ADMIN', 100000),
       ('USER', 100000),
       ('USER', 100001),
       ('USER', 100002),
       ('USER', 100003);

INSERT INTO restaurant (name)
VALUES ('Khachapuri and Wine'),
       ('Munhell'),
       ('Kwakinn');

INSERT INTO menu_item (restaurant_id, date, name, price)
VALUES (100004, current_timestamp, 'salad', 25000),
       (100004, current_timestamp, 'khachapuri', 28000),
       (100004, current_timestamp, 'lobio', 20000),
       (100004, '2021-03-24', 'phali', 5000),
       (100004, '2021-03-24', 'wine', 26000),
       (100005, '2021-03-24', 'salad', 23050),
       (100005, '2021-03-24', 'ribs BBQ', 55050),
       (100005, current_timestamp, 'steak', 75050),
       (100005, '2021-03-24', 'shashlik', 45000),
       (100005, '2021-03-24', 'beer', 29000),
       (100006, '2021-03-24', 'salad', 20000),
       (100006, '2021-03-24', 'pork knuckle', 65000),
       (100006, current_timestamp, 'chicken', 45000),
       (100006, '2021-03-24', 'rib eye steak', 95000),
       (100006, '2021-03-24', 'beer', 35000);

INSERT INTO vote (user_id, date, restaurant_id)
VALUES (100000, current_timestamp, 100004),
       (100001, '2021-04-5', 100005),
       (100002, current_timestamp, 100005),
       (100003, current_timestamp, 100006),
       (100000, '2021-04-6', 100005),
       (100001, '2021-04-6', 100006);