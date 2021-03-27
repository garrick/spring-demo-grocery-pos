create table if not exists items_prices (
  "id" serial primary key,
  "item_id" serial references items (id),
  price int not null default 0,
  start_date timestamp not null default current_date,
  end_date timestamp not null default current_date
);