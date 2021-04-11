--Add stuff here
--Some beverage items
insert into items (displayname, description) values ('Buzz Cola', 'Twice the sugar, Twice the caffeine"');
insert into items (displayname, description) values ('Slurm', 'It''s highly addictive!' );
insert into items (displayname, description) values ('Nuka-Cola', 'Have a Nuke' );
insert into items (displayname, description) values ('Ouvoir Priced', 'Pure French water, too good for the likes of you' );
insert into items (displayname, description) values ('Tomacco Juice', 'The great taste of tomato and tobacco together' );
--Some snack items
insert into items (displayname, description) values ('Cheesy Blaster', 'Jack cheese stuffed hot dog inside a pizza');
insert into items (displayname, description) values ('Cheesy Poofs', 'Yeah, I want cheesy poofs!');
insert into items (displayname, description) values ('Scooby Snacks', 'Not just for mystery solving dogs!');
insert into items (displayname, description) values ('Everlasting Gobstopper', 'The jawbreaker that doesn''t stop.');
insert into items (displayname, description) values ('Snacky Smores', 'Eat Snacky Smores!');
--Fruit
insert into items (displayname, description) values ('Orange', 'Navel Orange');
insert into items (displayname, description) values ('Apple', 'Fuji Apple');
insert into items (displayname, description) values ('Pear', 'Bartlet Pear');
insert into items (displayname, description) values ('Banana', 'Cavendish');
insert into items (displayname, description) values ('Kiwifruit', 'Fuzzy Kiwifruit');
--
-- Beverage items_prices
insert into items_prices (item_id, price, end_date) values ((select id from items where displayname = 'Buzz Cola'), 150, '2200-01-01 00:00:00');
insert into items_prices (item_id, price, end_date) values ((select id from items where displayname = 'Slurm'), 150, '2200-01-01 00:00:00');
insert into items_prices (item_id, price, end_date) values ((select id from items where displayname = 'Nuka-Cola'), 150, '2200-01-01 00:00:00');
insert into items_prices (item_id, price, end_date) values ((select id from items where displayname = 'Ouvoir Priced'), 350, '2200-01-01 00:00:00');
insert into items_prices (item_id, price, end_date) values ((select id from items where displayname = 'Tomacco Juice'), 100, '2200-01-01 00:00:00');
-- Snack items_prices
insert into items_prices (item_id, price, end_date) values ((select id from items where displayname = 'Cheesy Blaster'), 200, '2200-01-01 00:00:00');
insert into items_prices (item_id, price, end_date) values ((select id from items where displayname = 'Cheesy Poofs'), 400, '2200-01-01 00:00:00');
insert into items_prices (item_id, price, end_date) values ((select id from items where displayname = 'Scooby Snacks'), 350, '2200-01-01 00:00:00');
insert into items_prices (item_id, price, end_date) values ((select id from items where displayname = 'Everlasting Gobstopper'), 75, '2200-01-01 00:00:00');
insert into items_prices (item_id, price, end_date) values ((select id from items where displayname = 'Snacky Smores'), 125, '2200-01-01 00:00:00');
-- Fruit items_prices
insert into items_prices (item_id, price, end_date) values ((select id from items where displayname = 'Orange'), 100, '2200-01-01 00:00:00');
insert into items_prices (item_id, price, end_date) values ((select id from items where displayname = 'Apple'), 75, '2200-01-01 00:00:00');
insert into items_prices (item_id, price, end_date) values ((select id from items where displayname = 'Pear'), 100, '2200-01-01 00:00:00');
insert into items_prices (item_id, price, end_date) values ((select id from items where displayname = 'Banana'), 90, '2200-01-01 00:00:00');
insert into items_prices (item_id, price, end_date) values ((select id from items where displayname = 'Kiwifruit'), 120, '2200-01-01 00:00:00');
