package org.commandline.grocerypos.service;

import org.commandline.grocerypos.dto.LineItemDTO;
import org.commandline.grocerypos.repository.ItemDao;
import org.commandline.grocerypos.repository.ItemPriceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LineItemServiceStandard implements LineItemService {

    private ItemPriceDao itemPriceDao;

    public LineItemServiceStandard(@Autowired  ItemPriceDao itemPriceDao) {
        this.itemPriceDao = itemPriceDao;
    }

    @Override
    public List<LineItemDTO> lookupLineItemDTOsByIds(List<Long> itemIds) {
       return itemPriceDao.findByIds(itemIds);
    }
}
