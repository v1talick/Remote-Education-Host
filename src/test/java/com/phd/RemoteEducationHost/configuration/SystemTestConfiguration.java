package com.phd.RemoteEducationHost.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import static org.junit.jupiter.api.Assertions.*;
import javax.sql.DataSource;

@Configuration
@Import(JdbcConfiguration.class)
public class SystemTestConfiguration {
    @Bean
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
