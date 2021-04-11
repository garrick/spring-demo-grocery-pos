package org.commandline.grocerypos.repository;

import org.commandline.grocerypos.dto.LineItemDTO;
import org.commandline.grocerypos.model.ItemPrice;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.BindList;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@UseClasspathSqlLocator
public interface ItemPriceDao {

    @SqlUpdate
    @GetGeneratedKeys
    Long insert(@BindBean ItemPrice items_prices);

    @SqlQuery
    @RegisterBeanMapper(ItemPrice.class)
    List<ItemPrice> findAll();

    @SqlQuery
    @RegisterBeanMapper(LineItemDTO.class)
    List<LineItemDTO> findByIds(@BindList("itemIds") List<Long> itemIds);
}
