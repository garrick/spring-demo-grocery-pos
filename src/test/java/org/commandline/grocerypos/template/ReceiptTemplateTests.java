package org.commandline.grocerypos.template;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.commandline.grocerypos.dto.ItemList;
import org.commandline.grocerypos.dto.LineItemDTO;
import org.commandline.grocerypos.dto.TestDataLineItemDTOBuilder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReceiptTemplateTests {
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    private String expectedHTMLHeaderContent = "Hello, POS system!";
    private String itemListIndicatorComment = "<!-- Rendering List of Items -->";

    @Test
    public void testGetIndex() {
        assertNotNull(freeMarkerConfigurer);
        try {
            Configuration configuration = freeMarkerConfigurer.getConfiguration();
            Template template = configuration.getTemplate("index.ftl");
            StringWriter fakeOut = new StringWriter();

            HashMap itemListMap = new HashMap();
            itemListMap.put("itemList", new ItemList());
            HashMap modelMap = new HashMap();
            modelMap.put("model", itemListMap);
            template.process(modelMap, fakeOut);
            String output = fakeOut.toString();
            assertTrue(output.contains(expectedHTMLHeaderContent));
            assertFalse(output.contains(itemListIndicatorComment));
        } catch (Exception e) {
            fail("Rut-roh, Raggy!" + e.getMessage());
        }
    }

    @Test
    public void testPostIndex() {
        assertNotNull(freeMarkerConfigurer);
        try {
            Configuration configuration = freeMarkerConfigurer.getConfiguration();
            Template template = configuration.getTemplate("index.ftl");
            StringWriter fakeOut = new StringWriter();

            TestDataLineItemDTOBuilder dataLineItemDTOBuilder = new TestDataLineItemDTOBuilder();
            List<LineItemDTO> lineItemDTOList = new ArrayList<>();
            lineItemDTOList.add(dataLineItemDTOBuilder.buildSlurm());
            lineItemDTOList.add(dataLineItemDTOBuilder.buildBuzzCola());
            HashMap itemListMap = new HashMap();
            ItemList realItemList = new ItemList();
            realItemList.currentItemIds = Arrays.asList(7,9);
            itemListMap.put("itemList", realItemList);
            itemListMap.put("lineItemDtoList", lineItemDTOList);
            HashMap modelMap = new HashMap();
            modelMap.put("model", itemListMap);
            template.process(modelMap, fakeOut);
            String output = fakeOut.toString();
            System.out.println(output);
            assertTrue(output.contains(expectedHTMLHeaderContent));                 //Always there
            assertTrue(output.contains(itemListIndicatorComment));                  //We have items in our list
            assertTrue(output.contains("Item: #1 Slurm, $1.50"));                                //Two items, to be specific
            assertTrue(output.contains("Item: #2 Buzz Cola, $1.75"));
            assertTrue(output.contains("name=\"currentItemIds\" value=\"7,9\""));   //Our form should be populated
        } catch (Exception e) {
            fail("Rut-roh, Raggy!" + e.getMessage());
        }
    }
}
