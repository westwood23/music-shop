INSERT INTO orders (order_date) VALUES ('2019-08-1');
INSERT INTO orders (order_date) VALUES ('2019-08-2');
INSERT INTO orders (order_date) VALUES ('2019-08-3');

INSERT INTO items (item_id, item_name, item_price) VALUES(1, 'Gibson Les Paul', 1100);
INSERT INTO items (item_id, item_name, item_price) VALUES(2, 'Fender Stratocaster', 1200);
INSERT INTO items (item_id, item_name, item_price) VALUES(3, 'Fender Telecaster', 900);
INSERT INTO items (item_id, item_name, item_price) VALUES(4, 'Tama Drums', 1600);
INSERT INTO items (item_id, item_name, item_price) VALUES(5, 'Soner', 1500);
INSERT INTO items (item_id, item_name, item_price) VALUES(6, 'Yamaha', 1400);
INSERT INTO items (item_id, item_name, item_price) VALUES(7, 'Strings', 15);
INSERT INTO items (item_id, item_name, item_price) VALUES(8, 'Pics', 5);
INSERT INTO items (item_id, item_name, item_price) VALUES(9, 'Drum Kit', 30);
INSERT INTO items (item_id, item_name, item_price) VALUES(10, 'smth0', 30);
INSERT INTO items (item_id, item_name, item_price) VALUES(11, 'smth1', 31);
INSERT INTO items (item_id, item_name, item_price) VALUES(12, 'smth2', 32);
INSERT INTO items (item_id, item_name, item_price) VALUES(13, 'smth3', 33);
INSERT INTO items (item_id, item_name, item_price) VALUES(14, 'smth4', 34);
INSERT INTO items (item_id, item_name, item_price) VALUES(15, 'smth5', 35);
INSERT INTO items (item_id, item_name, item_price) VALUES(16, 'smth6', 36);

INSERT INTO order_items (order_id, item_id) VALUES (1, 1);
INSERT INTO order_items (order_id, item_id) VALUES (1, 7);
INSERT INTO order_items (order_id, item_id) VALUES (1, 8);
INSERT INTO order_items (order_id, item_id) VALUES (2, 4);
INSERT INTO order_items (order_id, item_id) VALUES (2, 9);
INSERT INTO order_items (order_id, item_id) VALUES (3, 2);
INSERT INTO order_items (order_id, item_id) VALUES (3, 5);
