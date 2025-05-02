package com.phd.RemoteEducationHost.configuration;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.phd.RemoteEducationHost")
public class JdbcConfiguration {
    //    @Bean
//    public DataSource dataSource(){
//        PGSimpleDataSource dataSource = new PGSimpleDataSource();
//        dataSource.setServerName();
//        return dataSource;
//    }
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
