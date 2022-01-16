INSERT INTO USERS (NAME, EMAIL, PASSWORD)
VALUES ('User', 'user@gmail.com', '{noop}password'),
       ('Admin', 'admin@javaops.ru', '{noop}admin');

INSERT INTO USER_ROLE (ROLE, USER_ID)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO RESTAURANTS (NAME)
VALUES ('McDonalds'),
       ('KFC'),
       ('Burger King');

INSERT INTO MEALS (NAME, PRICE, RESTAURANT_ID)
VALUES ('Big Tasty', 139, 1),
       ('Cola', 99, 1),
       ('Wings', 99, 2),
       ('Whopper', 199, 3);

INSERT INTO VOTES (VOTEDATE, RESTAURANT_ID, USER_ID)
VALUES (parsedatetime('2020-05-19 20:00:00','yyyy-MM-dd hh:mm:ss'), 3, 1),
       (parsedatetime('2020-07-20 20:00:00','yyyy-MM-dd hh:mm:ss'), 2, 2);