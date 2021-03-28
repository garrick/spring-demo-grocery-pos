package org.commandline.grocerypos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class ReceiptController {

        @GetMapping("/index")
        public String index(@ModelAttribute("model")ModelMap model) {
            model.addAttribute("luser", "Joe Luser");
            return "index";
        }
}
