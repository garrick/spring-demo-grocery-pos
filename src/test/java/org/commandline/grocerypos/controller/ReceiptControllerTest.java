package org.commandline.grocerypos.controller;

import org.commandline.grocerypos.dto.ItemList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReceiptControllerTest {

    private ReceiptController unit;
    private ModelMap map;

    @BeforeEach
    public void setUp(){
        map = new ModelMap();
        unit = new ReceiptController();
    }

    @Test
    public void testGetIndexReturnsEmptyDtoWithIndexTemplate(){
        assertEquals("index", unit.index(map));
        ItemList actualItemList = (ItemList) map.get("itemList");
        assertTrue(actualItemList.currentItemIds.isEmpty());
        assertEquals(-1, actualItemList.nextItemId);
    }

    @Test
    public void testPostIndexReturnPopulatedDtoWithIndexTemplate() {
        ItemList currentItemList = new ItemList();
        currentItemList.currentItemIds = Arrays.asList(7,9);
        currentItemList.nextItemId = 11;
        assertEquals("index", unit.index(currentItemList, map));
        ItemList newItemList = (ItemList) map.get("itemList");
        assertEquals(Arrays.asList(7,9,11), newItemList.currentItemIds);
        assertEquals(-1, newItemList.nextItemId);
    }
}
