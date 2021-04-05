package org.commandline.grocerypos.template;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.commandline.grocerypos.dto.ItemList;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
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

            HashMap itemListMap = new HashMap();
            ItemList realItemList = new ItemList();
            realItemList.currentItemIds = Arrays.asList(7,9);
            itemListMap.put("itemList", realItemList);
            HashMap modelMap = new HashMap();
            modelMap.put("model", itemListMap);
            template.process(modelMap, fakeOut);
            String output = fakeOut.toString();
            assertTrue(output.contains(expectedHTMLHeaderContent));                 //Always there
            assertTrue(output.contains(itemListIndicatorComment));                  //We have items in our list
            assertTrue(output.contains("Item: #7"));                                //Two items, to be specific
            assertTrue(output.contains("Item: #9"));
            assertTrue(output.contains("name=\"currentItemIds\" value=\"7,9\""));   //Our form should be populated
        } catch (Exception e) {
            fail("Rut-roh, Raggy!" + e.getMessage());
        }
    }
}
