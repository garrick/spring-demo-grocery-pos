package org.commandline.grocerypos.dto;

import org.commandline.grocerypos.model.Item;
import org.commandline.grocerypos.model.ItemPrice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Execution(ExecutionMode.CONCURRENT)
public class LineItemDTOTest {

    @Test
    public void testRelevantDataExtractedFromItemAndItemPriceDefaultQuantityOne() {
        Item item = new Item("Soda", "Something to drink");
        item.setId(500L);
        ItemPrice itemPrice = new ItemPrice(item.getId(), 150);
        LineItemDTO unit = new LineItemDTO(item, itemPrice);
        assertEquals("Soda", unit.getDisplayName());
        assertEquals("Something to drink", unit.getDescription());
        assertEquals(150, unit.getPrice());
        assertEquals(1, unit.getQuantity());
    }

    @Test
    public void testRelevantDataExtractedFromItemAndItemPriceWithQuantity() {
        Item item = new Item("Soda", "Something to drink");
        item.setId(500L);
        ItemPrice itemPrice = new ItemPrice(item.getId(), 150);
        LineItemDTO unit = new LineItemDTO(item, itemPrice, 3);
        assertEquals("Soda", unit.getDisplayName());
        assertEquals("Something to drink", unit.getDescription());
        assertEquals(150, unit.getPrice());
        assertEquals(3, unit.getQuantity());
    }
}
