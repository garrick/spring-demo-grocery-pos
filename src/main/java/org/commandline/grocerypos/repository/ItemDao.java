package org.commandline.grocerypos.repository;

import org.commandline.grocerypos.model.Item;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public
interface ItemDao {

    @SqlUpdate
    @GetGeneratedKeys
    Long insert(@BindBean Item item);

    @SqlQuery("select * from item")
    @RegisterBeanMapper(Item.class)
    List<Item> findAll();
}
