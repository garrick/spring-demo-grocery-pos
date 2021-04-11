select i.id, i.displayname, i.description, ip.price
from items as i, items_prices as ip
where i.id = ip.item_id
and i.id in (<itemIds>);