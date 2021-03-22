package org.commandline.grocerypos.testutil;

import org.flywaydb.core.Flyway;
import org.testcontainers.containers.PostgreSQLContainer;


/*
    This was shamelessly stolen from
    https://www.baeldung.com/spring-boot-testcontainers-integration-test
 */
public class GroceryPosPostgresContainer extends PostgreSQLContainer<GroceryPosPostgresContainer> {
    private final static String IMAGE_VERSION = "postgres:11.1";
    private static GroceryPosPostgresContainer container;

    private GroceryPosPostgresContainer() {
        super(IMAGE_VERSION);
    }

    public static synchronized GroceryPosPostgresContainer getInstance() {
        if (container == null) {
            container = new GroceryPosPostgresContainer();
            container.start();
            Flyway flyway = Flyway.configure()
                    .dataSource(container.getJdbcUrl(), container.getUsername(), container.getPassword())
                    .load();
            flyway.migrate();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("DB_URL", container.getJdbcUrl());
        System.setProperty("DB_USERNAME", container.getUsername());
        System.setProperty("DB_PASSWORD", container.getPassword());
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }
}
