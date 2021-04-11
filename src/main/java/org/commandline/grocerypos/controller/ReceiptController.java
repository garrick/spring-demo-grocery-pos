package org.commandline.grocerypos.controller;

import org.commandline.grocerypos.dto.ItemList;
import org.commandline.grocerypos.dto.LineItemDTO;
import org.commandline.grocerypos.service.LineItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ReceiptController {

    private LineItemService lineItemService;

    public ReceiptController(@Autowired LineItemService fakeLineItemService) {

        this.lineItemService = fakeLineItemService;
    }

    @GetMapping("/index")
    public String index(@ModelAttribute("model") ModelMap model) {
        model.addAttribute("itemList", new ItemList());
        return "index";
    }

    @PostMapping("/index")
    public String index(ItemList oldItemList, @ModelAttribute("model") ModelMap model) {
        ItemList newList = new ItemList(oldItemList);
        List<Long> logIds = newList.currentItemIds.stream().mapToLong(Integer::longValue).boxed().collect(Collectors.toList());
        List<LineItemDTO> lineItemDTOS = lineItemService.lookupLineItemDTOsByIds(logIds);
        model.addAttribute("itemList", newList);
        model.addAttribute("lineItemDtoList", lineItemDTOS);
        return "index";
    }
}
