package org.commandline.grocerypos;

import org.commandline.grocerypos.model.Item;
import org.commandline.grocerypos.repository.ItemDao;
import org.commandline.grocerypos.testutil.GroceryPosPostgresContainer;
import org.junit.ClassRule;
import org.junit.jupiter.api.Disabled;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = DatabaseMigrationIntegrationTest.Initializer.class)
public class DatabaseMigrationIntegrationTest {

    @Container
    private static GroceryPosPostgresContainer postgresContainer = GroceryPosPostgresContainer.getInstance();


    @Autowired
    private ItemDao itemDao;

    @Test
    @Transactional
    public void testSomethingOnYourCustomerTable() {
        assertEquals(0, itemDao.findAll().size(), "We should have ZERO items");
        Item itemOne = new Item();
        itemOne.setDisplayname("Display Me");
        itemOne.setDescription("A lovely item that you must buy now!");
        itemDao.insert(itemOne);
        assertEquals(1, itemDao.findAll().size(), "We should have ONE item");
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
