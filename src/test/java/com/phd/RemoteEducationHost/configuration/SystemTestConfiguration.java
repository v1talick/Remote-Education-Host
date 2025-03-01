package com.phd.RemoteEducationHost.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import static org.junit.jupiter.api.Assertions.*;
import javax.sql.DataSource;

@Configuration
@Import(JdbcConfiguration.class)
//@PropertySource("classpath:src/test/resources/test-application.properties")
public class SystemTestConfiguration {
    @Bean
    @Primary
    public DataSource testDataSource() {
        return
                (new EmbeddedDatabaseBuilder())
                        .setType(EmbeddedDatabaseType.H2)
                        .addScript("classpath:testdb/schema.sql")
                        .addScript("classpath:testdb/data.sql")
                        .setScriptEncoding("UTF-8")
                        .build();
    }

}
