package org.commandline.grocerypos.config;

import org.commandline.grocerypos.repository.ItemDao;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.postgres.PostgresPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;

@Configuration
public class JdbiConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource driverManagerDataSource() {
        return new DriverManagerDataSource();
    }

    @Bean
    public Jdbi jdbi(DataSource ds) {
        TransactionAwareDataSourceProxy proxy = new TransactionAwareDataSourceProxy(ds);
        Jdbi jdbi = Jdbi.create(proxy);
        jdbi.installPlugin(new SqlObjectPlugin());
        jdbi.installPlugin(new PostgresPlugin());
        return jdbi;
    }

    @Bean
    public ItemDao itemDao(Jdbi jdbi) {
        return jdbi.onDemand(ItemDao.class);
    }

}
