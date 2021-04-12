package org.commandline.grocerypos.controller;

import org.commandline.grocerypos.dto.ItemList;
import org.commandline.grocerypos.dto.LineItemDTO;
import org.commandline.grocerypos.dto.TestDataLineItemDTOBuilder;
import org.commandline.grocerypos.service.LineItemService;
import org.commandline.grocerypos.service.TotalItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Execution(ExecutionMode.CONCURRENT)
public class ReceiptControllerTest {

    private ReceiptController unit;
    private ModelMap map;
    private LineItemService fakeLineItemService;
    private TotalItemService fakeTotalItemService;
    private TestDataLineItemDTOBuilder lineItemDTOBuilder = new TestDataLineItemDTOBuilder();

    @BeforeEach
    public void setUp(){
        map = new ModelMap();
        fakeLineItemService = new FakeLineItemService();
        fakeTotalItemService = new FakeTotalItemService();
        unit = new ReceiptController(fakeLineItemService, fakeTotalItemService);
    }

    @Test
    public void testGetIndexReturnsEmptyDtoWithIndexTemplate(){
        assertEquals("index", unit.index(map));
        ItemList actualItemList = (ItemList) map.get("itemList");
        assertTrue(actualItemList.currentItemIds.isEmpty());
        assertEquals(-1, actualItemList.nextItemId);
    }

    @Test
    public void testPostIndexReturnPopulatedDtoWithIndexTemplateAndLooksUpLineItemDTOsAndTotals() {
        ItemList currentItemList = new ItemList();
        currentItemList.currentItemIds = Arrays.asList(7,9);
        currentItemList.nextItemId = 11;
        assertEquals("index", unit.index(currentItemList, map));
        ItemList newItemList = (ItemList) map.get("itemList");
        assertEquals(Arrays.asList(7,9,11), newItemList.currentItemIds);
        assertEquals(-1, newItemList.nextItemId);
        List<LineItemDTO> lineItemDtoList = (List<LineItemDTO>) map.get("lineItemDtoList");
        assertEquals(1, lineItemDtoList.size());
        LineItemDTO slurm = lineItemDtoList.get(0);
        assertEquals(TestDataLineItemDTOBuilder.SLURM_DISPLAY_NAME, slurm.getDisplayName());
        assertEquals(TestDataLineItemDTOBuilder.SLURM_PRICE, slurm.getPrice());
        assertEquals(TestDataLineItemDTOBuilder.QUANTITY_ONE, slurm.getQuantity());
        Integer total = (Integer) map.get("total");
        assertEquals(150, total);
    }

    private class FakeLineItemService implements LineItemService {
        @Override
        public List<LineItemDTO> lookupLineItemDTOsByIds(List<Long> itemIds) {

            return new ArrayList<LineItemDTO>(Arrays.asList(lineItemDTOBuilder.buildSlurm()));
        }
    }


    private class FakeTotalItemService implements TotalItemService {
        @Override
        public Integer computeTotalPrice(List<LineItemDTO> lineItemDTOList) {
            return 150;
        }
    }
}
