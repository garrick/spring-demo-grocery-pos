package org.commandline.grocerypos.repository;

import org.commandline.grocerypos.model.Item;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@UseClasspathSqlLocator
public interface ItemDao {

    @SqlUpdate
    @GetGeneratedKeys
    Long insert(@BindBean Item items);

    //@SqlQuery("select * from items")
    @SqlQuery
    @RegisterBeanMapper(Item.class)
    List<Item> findAll();
}
