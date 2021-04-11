package org.commandline.grocerypos.repository;

import org.commandline.grocerypos.dto.LineItemDTO;
import org.commandline.grocerypos.model.ItemPrice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FakeItemPriceDao implements ItemPriceDao {
    private HashMap<Long, LineItemDTO> lineItemDTOHashMap = new HashMap<>();

    public void addLineItemDtoWithId(LineItemDTO lineItemDTO, Long id) {
        lineItemDTOHashMap.put(id, lineItemDTO);
    }
    @Override
    public Long insert(ItemPrice items_prices) {
        throw new RuntimeException("This shouldn't be called!");
    }

    @Override
    public List<ItemPrice> findAll() {
        throw new RuntimeException("This shouldn't be called!");
    }

    @Override
    public List<LineItemDTO> findByIds(List<Long> itemIds) {
        ArrayList<LineItemDTO> returnList = new ArrayList<>();
        itemIds.forEach( id -> returnList.add(lineItemDTOHashMap.get(id)));
        return returnList;
    }
}
