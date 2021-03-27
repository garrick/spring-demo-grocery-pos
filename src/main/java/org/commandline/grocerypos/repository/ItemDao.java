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

    @SqlUpdate("insert into items(displayname, description) values (:displayname, :description)")
    @GetGeneratedKeys
    Long insert(@BindBean Item items);

    @SqlQuery("select * from items")
    @RegisterBeanMapper(Item.class)
    List<Item> findAll();
}
