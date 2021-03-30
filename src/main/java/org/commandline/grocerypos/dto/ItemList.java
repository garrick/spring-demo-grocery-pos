package org.commandline.grocerypos.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ItemList {
    public ArrayList<Integer> currentItemIds = new ArrayList<>();
    public Integer additionalItemId = -1;

    public String streamOut(){
        List<String> itemStrings = new ArrayList<>();
        for (Integer integerId: currentItemIds) {
            itemStrings.add(integerId.toString());
        }
        return String.join(",", itemStrings);
    }
}
