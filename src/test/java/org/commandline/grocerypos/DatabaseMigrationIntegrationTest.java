package org.commandline.grocerypos;

import org.commandline.grocerypos.model.Item;
import org.commandline.grocerypos.repository.ItemDao;
import org.commandline.grocerypos.testutil.GroceryPosPostgresContainer;
import org.junit.ClassRule;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseMigrationIntegrationTest {

        @ClassRule
        private GroceryPosPostgresContainer postgresContainer = GroceryPosPostgresContainer.getInstance();

        @Autowired
        private ItemDao itemDao;

        @Disabled
        @Test
        @Transactional
        public void testSomethingOnYourCustomerTable() {
                assertEquals(0, itemDao.findAll().size(),"We should have ZERO items");
                Item itemOne = new Item();
                itemOne.setDisplayname("Display Me");
                itemOne.setDescription("A lovely item that you must buy now!");
                itemDao.insert(itemOne);
                assertEquals(1, itemDao.findAll().size(),"We should have ONE item");
        }
}
