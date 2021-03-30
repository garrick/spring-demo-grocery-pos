package org.commandline.grocerypos.controller;

import lombok.val;
import org.commandline.grocerypos.dto.ItemList;
import org.commandline.grocerypos.model.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class ReceiptController {

    @GetMapping("/index")
    public String index(@ModelAttribute("model") ModelMap model) {
        model.addAttribute("items", new ItemList());
        return "index";
    }

    @PostMapping("/index")
    public String index(@ModelAttribute ItemList itemList, ModelMap model) {
        ArrayList<Integer> currentItemIds = itemList.currentItemIds;
        currentItemIds.add(itemList.additionalItemId);
        val newList = new ItemList();
        newList.currentItemIds = currentItemIds;
        model.addAttribute("items", newList);
        return "index";
    }
}
