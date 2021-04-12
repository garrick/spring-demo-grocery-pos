package org.commandline.grocerypos.service;

import org.commandline.grocerypos.dto.LineItemDTO;

import java.util.List;

public interface TotalItemService {
    Integer computeTotalPrice(List<LineItemDTO> lineItemDTOList);
}
