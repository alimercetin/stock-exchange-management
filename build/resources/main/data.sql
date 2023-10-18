INSERT INTO stock_exchange (name, description) VALUES ('XAMS', 'Amsterdam');
INSERT INTO stock_exchange (name, description) VALUES ('XSHG', 'Shangai');
INSERT INTO stock_exchange (name, description) VALUES ('XBRU', 'Brussels');
INSERT INTO stock_exchange (name, description) VALUES ('XJPX', 'Tokyo');

INSERT INTO stock ( name, description, current_price, last_update)
VALUES ( 'META', 'Meta Platforms', 322, CURRENT_TIMESTAMP());
INSERT INTO Stock ( name, description, current_price, last_update)
VALUES ( 'NVDA', 'Nvidia', 438, CURRENT_TIMESTAMP());
INSERT INTO Stock ( name, description, current_price, last_update)
VALUES ( 'ANET', 'Arista Networks', 196, CURRENT_TIMESTAMP());
INSERT INTO Stock ( name, description, current_price, last_update)
VALUES ( 'VRT', 'Vertiv Holdings', 40, CURRENT_TIMESTAMP());
INSERT INTO Stock ( name, description, current_price, last_update)
VALUES ( 'ZS', 'Zscaler', 173, CURRENT_TIMESTAMP());
INSERT INTO Stock ( name, description, current_price, last_update)
VALUES ( 'ASELS', 'Aselsan', 40, CURRENT_TIMESTAMP());
INSERT INTO Stock ( name, description, current_price, last_update)
VALUES ( 'KHC', 'Kraft Heinz', 12, CURRENT_TIMESTAMP());
INSERT INTO Stock ( name, description, current_price, last_update)
VALUES ( 'AAPL', 'Apple', 179, CURRENT_TIMESTAMP());

INSERT INTO stock_exchange_stocks (stock_exchange_id, stock_id) VALUES (1, 1);
INSERT INTO stock_exchange_stocks (stock_exchange_id, stock_id) VALUES (1, 2);
INSERT INTO stock_exchange_stocks (stock_exchange_id, stock_id) VALUES (1, 3);
INSERT INTO stock_exchange_stocks (stock_exchange_id, stock_id) VALUES (1, 4);
INSERT INTO stock_exchange_stocks (stock_exchange_id, stock_id) VALUES (1, 5);
INSERT INTO stock_exchange_stocks (stock_exchange_id, stock_id) VALUES (2, 6);
INSERT INTO stock_exchange_stocks (stock_exchange_id, stock_id) VALUES (2, 7);
INSERT INTO stock_exchange_stocks (stock_exchange_id, stock_id) VALUES (2, 8);
INSERT INTO stock_exchange_stocks (stock_exchange_id, stock_id) VALUES (2, 3);
INSERT INTO stock_exchange_stocks (stock_exchange_id, stock_id) VALUES (3, 2);
INSERT INTO stock_exchange_stocks (stock_exchange_id, stock_id) VALUES (3, 5);
INSERT INTO stock_exchange_stocks (stock_exchange_id, stock_id) VALUES (3, 7);
