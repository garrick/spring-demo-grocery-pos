package org.commandline.grocerypos.service;

import org.commandline.grocerypos.dto.LineItemDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TotalItemServiceStandard implements TotalItemService {
    @Override
    public Integer computeTotalPrice(List<LineItemDTO> lineItemDTOList) {
        return lineItemDTOList.stream().mapToInt(i -> i.getPrice()).sum();
    }
}
