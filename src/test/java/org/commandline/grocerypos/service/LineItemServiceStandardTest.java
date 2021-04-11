package org.commandline.grocerypos.service;

import org.commandline.grocerypos.dto.LineItemDTO;
import org.commandline.grocerypos.dto.TestDataLineItemDTOBuilder;
import org.commandline.grocerypos.repository.FakeItemPriceDao;
import org.commandline.grocerypos.repository.ItemPriceDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Execution(ExecutionMode.CONCURRENT)
public class LineItemServiceStandardTest {


    private LineItemService itemService;
    private LineItemDTO slurmDto = new TestDataLineItemDTOBuilder().buildSlurm();

    @BeforeEach
    public void setUp(){
        FakeItemPriceDao fakeItemPriceDao = new FakeItemPriceDao();
        fakeItemPriceDao.addLineItemDtoWithId(slurmDto,1L);
        itemService = new LineItemServiceStandard(fakeItemPriceDao);
    }

    @Test
    public void testServiceReturnsEmptyServiceResponseWithNoItemId(){
        List<LineItemDTO> lineItemDTOS = itemService.lookupLineItemDTOsByIds(new ArrayList<>() {
        });
        assertTrue(lineItemDTOS.isEmpty());
    }

    @Test
    public void testServiceReturnsSingleItem(){
        List<LineItemDTO> lineItemDTOS = itemService.lookupLineItemDTOsByIds(new ArrayList<>(Arrays.asList(1L)) {
        });
        LineItemDTO actual = lineItemDTOS.get(0);
        assertEquals(TestDataLineItemDTOBuilder.QUANTITY_ONE, actual.getQuantity());
        assertEquals(TestDataLineItemDTOBuilder.SLURM_DISPLAY_NAME, actual.getDisplayName());
        assertEquals(TestDataLineItemDTOBuilder.SLURM_PRICE, actual.getPrice());
    }
}
