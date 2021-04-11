package org.commandline.grocerypos.service;

import org.commandline.grocerypos.dto.LineItemDTO;

import java.util.List;

public interface LineItemService {
    List<LineItemDTO> lookupLineItemDTOsByIds(List<Long> itemIds);
}
