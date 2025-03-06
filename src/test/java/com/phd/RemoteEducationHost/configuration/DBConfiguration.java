package com.phd.RemoteEducationHost.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class DBConfiguration {
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
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
