package org.commandline.grocerypos;

import org.commandline.grocerypos.model.Item;
import org.commandline.grocerypos.model.ItemPrice;
import org.commandline.grocerypos.repository.ItemDao;
import org.commandline.grocerypos.repository.ItemPriceDao;
import org.commandline.grocerypos.testutil.GroceryPosPostgresContainer;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = DatabaseMigrationIntegrationTest.Initializer.class)
public class DatabaseMigrationIntegrationTest {

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
        Item itemOne = new Item("Slurm", "It's Highly Addictive!");
        Long slurm_item_id = itemDao.insert(itemOne);
        assertTrue(slurm_item_id >= 1);
        assertEquals(1, itemDao.findAll().size(), "We should have ONE item");
        ItemPrice itemPriceOne = new ItemPrice(slurm_item_id, 150);
        itemPriceOne.setStart_date( LocalDateTime.parse("1999-11-14T20:00:00"));
        itemPriceOne.setEnd_date(   LocalDateTime.parse("2999-12-31T23:59:59"));
        Long slurm_price_id = itemPriceDao.insert(itemPriceOne);
        assertTrue(slurm_price_id >= 1);
        assertEquals(1, itemPriceDao.findAll().size(), "We should have ONE itemprice");
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
