DROP TABLE IF EXISTS orders;
CREATE TABLE orders
(
  order_id     INT         NOT NULL AUTO_INCREMENT UNIQUE,
  order_date   DATE        NOT NULL,
  PRIMARY KEY (order_id)
);

DROP TABLE IF EXISTS items;
CREATE TABLE items
(
  item_id     INT          NOT NULL AUTO_INCREMENT UNIQUE,
  item_name   VARCHAR(255) NOT NULL,
  item_price  DECIMAL      NOT NULL,
  is_reserved boolean,
  PRIMARY KEY (item_id),
);
DROP TABLE IF EXISTS order_items;
CREATE TABLE order_items
(
  order_id INT NOT NULL,
  item_id  INT NOT NULL,
  PRIMARY KEY (order_id, item_id),
  FOREIGN KEY (order_id) REFERENCES orders  ON DELETE CASCADE,
  FOREIGN KEY (item_id) REFERENCES items ON DELETE CASCADE
);



