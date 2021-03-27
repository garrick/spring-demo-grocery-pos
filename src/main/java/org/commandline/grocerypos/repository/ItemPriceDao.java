package org.commandline.grocerypos.repository;

import org.commandline.grocerypos.model.ItemPrice;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface ItemPriceDao {

    @SqlUpdate("insert into items_prices(item_id, price, start_date, end_date) " +
            "VALUES(:item_id, :price, :start_date, :end_date)")
    @GetGeneratedKeys
    Long insert(@BindBean ItemPrice items_prices);

    @SqlQuery("select * from items_prices")
    @RegisterBeanMapper(ItemPrice.class)
    List<ItemPrice> findAll();
}
