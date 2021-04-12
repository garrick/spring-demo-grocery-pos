package org.commandline.grocerypos.service;

import org.commandline.grocerypos.dto.LineItemDTO;
import org.commandline.grocerypos.dto.TestDataLineItemDTOBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Execution(ExecutionMode.CONCURRENT)
public class TotalItemServiceStandardTest {

    private static TestDataLineItemDTOBuilder testDataLineItemDTOBuilder;
    private TotalItemService totalItemService;

    @BeforeAll
    public static void setUpClass(){
        testDataLineItemDTOBuilder = new TestDataLineItemDTOBuilder();
    }

    @BeforeEach
    public void setUp(){
        totalItemService = new TotalItemServiceStandard();
    }

    @Test
    public void testTotalIsComputedBasedOnPriceTimesQuantity(){
        ArrayList<LineItemDTO> sampleLineItemDtos = new ArrayList<>();
        sampleLineItemDtos.add(testDataLineItemDTOBuilder.buildSlurm());
        sampleLineItemDtos.add(testDataLineItemDTOBuilder.buildBuzzCola());
        assertEquals(325, totalItemService.computeTotalPrice(sampleLineItemDtos));
    }
}
