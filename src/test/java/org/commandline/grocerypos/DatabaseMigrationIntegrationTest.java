package org.commandline.grocerypos;

import org.commandline.grocerypos.dto.LineItemDTO;
import org.commandline.grocerypos.model.Item;
import org.commandline.grocerypos.model.ItemPrice;
import org.commandline.grocerypos.repository.ItemDao;
import org.commandline.grocerypos.repository.ItemPriceDao;
import org.commandline.grocerypos.testutil.GroceryPosPostgresContainer;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("integration")
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = DatabaseMigrationIntegrationTest.Initializer.class)
public class DatabaseMigrationIntegrationTest {

    private String SLURM_DISPLAY_NAME = "Slurm";
    private String SLURM_DESCRIPTION = "It's Highly Addictive!";
    private LocalDateTime SLURM_EPISODE_DATE = LocalDateTime.parse("1999-11-14T20:00:00");
    private LocalDateTime FRY_UNFREEZES_DATE = LocalDateTime.parse("2999-12-31T23:59:59");

    @Container
    private static GroceryPosPostgresContainer postgresContainer = GroceryPosPostgresContainer.getInstance();


    @Autowired
    private ItemDao itemDao;

    @Autowired
    private ItemPriceDao itemPriceDao;

    @Test
    @Transactional
    public void testItemsAndItemPrices() {
        assertEquals(0, itemDao.findAll().size(), "We should have ZERO items");
        assertEquals(0, itemPriceDao.findAll().size(), "We should have ZERO items");
        Item itemOne = new Item(SLURM_DISPLAY_NAME, SLURM_DESCRIPTION);
        Long slurm_item_id = itemDao.insert(itemOne);
        assertTrue(slurm_item_id >= 1);
        assertEquals(1, itemDao.findAll().size(), "We should have ONE item");
        ItemPrice itemPriceOne = new ItemPrice(slurm_item_id, 150);
        itemPriceOne.setStart_date(SLURM_EPISODE_DATE);
        itemPriceOne.setEnd_date(FRY_UNFREEZES_DATE);
        Long slurm_price_id = itemPriceDao.insert(itemPriceOne);
        assertTrue(slurm_price_id >= 1);
        assertEquals(1, itemPriceDao.findAll().size(), "We should have ONE itemprice");
        List<LineItemDTO> lineItemDTOs = itemPriceDao.findByIds(Arrays.asList(1L));
        assertEquals(1,lineItemDTOs.size(), "We should have ONE LineItemDTO");
        LineItemDTO oneLineItem = lineItemDTOs.get(0);
        assertEquals(slurm_item_id, oneLineItem.getId());
        assertEquals(SLURM_DISPLAY_NAME, oneLineItem.getDisplayName());
        assertEquals(SLURM_DESCRIPTION, oneLineItem.getDescription());
        assertEquals(150, oneLineItem.getPrice());
    }

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues values = TestPropertyValues.of(
                    "spring.datasource.url=" + postgresContainer.getJdbcUrl(),
                    "spring.datasource.password=" + postgresContainer.getPassword(),
                    "spring.datasource.username=" + postgresContainer.getUsername()
            );
            values.applyTo(configurableApplicationContext);
        }
    }
}
