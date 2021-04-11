package org.commandline.grocerypos.controller;

import org.commandline.grocerypos.dto.ItemList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReceiptController {

    @GetMapping("/index")
    public String index(@ModelAttribute("model") ModelMap model) {
        model.addAttribute("itemList", new ItemList());
        return "index";
    }

    @PostMapping("/index")
    public String index(ItemList oldItemList, @ModelAttribute("model") ModelMap model) {
        ItemList newList = new ItemList(oldItemList);
        model.addAttribute("itemList", newList);
        return "index";
    }
}
