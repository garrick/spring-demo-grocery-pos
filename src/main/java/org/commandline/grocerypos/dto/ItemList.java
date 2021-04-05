package org.commandline.grocerypos.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ItemList {

    public List<Integer> currentItemIds = new ArrayList<>();
    public Integer nextItemId = -1;

    public ItemList() {
    }

    public ItemList(ItemList source) {
        currentItemIds.addAll(source.currentItemIds);
        currentItemIds.add(source.nextItemId);
    }

    public String getStreamOut(){
        List<String> itemStrings = new ArrayList<>();
        for (Integer integerId: currentItemIds) {
            itemStrings.add(integerId.toString());
        }
        return String.join(",", itemStrings);
    }
}
